/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.geometry.euclidean.threed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.core.RegionLocation;
import org.apache.commons.geometry.core.partitioning.Split;
import org.apache.commons.geometry.core.partitioning.SplitLocation;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.threed.rotation.QuaternionRotation;
import org.apache.commons.geometry.euclidean.twod.ConvexArea;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VertexListConvexPolygon3DTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    private static final Plane XY_PLANE_Z1 = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
            Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);

    private static final List<Vector3D> TRIANGLE_VERTICES =
            Arrays.asList(Vector3D.of(0, 0, 1), Vector3D.of(1, 0, 1), Vector3D.of(0, 1, 1));

    @Test
    void testCtor_validatesVertexListSize() {
        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            new VertexListConvexPolygon3D(XY_PLANE_Z1, Arrays.asList(Vector3D.ZERO, Vector3D.Unit.PLUS_X));
        }, IllegalArgumentException.class, "Convex polygon requires at least 3 points; found 2");
    }

    @Test
    void testGetCentroid_linearVertices() {
        // this should not happen with all of the checks in place for constructing these
        // instances; this test is to ensure that the centroid computation can still handle
        // the situation

        // arrange
        final List<Vector3D> vertices = Arrays.asList(Vector3D.ZERO, Vector3D.of(0.5, 0, 0), Vector3D.of(2, 0, 0));
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, vertices);

        // act
        final Vector3D center = p.getCentroid();

        // assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(1, 0, 0), center, TEST_EPS);
    }

    @Test
    void testClassify() {
        // arrange
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, Arrays.asList(
                    Vector3D.of(1, 2, 1), Vector3D.of(3, 2, 1),
                    Vector3D.of(3, 4, 1), Vector3D.of(1, 4, 1)
                ));

        // act/assert
        checkPoints(p, RegionLocation.INSIDE, Vector3D.of(2, 3, 1));
        checkPoints(p, RegionLocation.BOUNDARY,
                Vector3D.of(1, 3, 1), Vector3D.of(3, 3, 1),
                Vector3D.of(2, 2, 1), Vector3D.of(2, 4, 1));
        checkPoints(p, RegionLocation.OUTSIDE,
                Vector3D.of(2, 3, 0), Vector3D.of(2, 3, 2),
                Vector3D.of(0, 3, 1), Vector3D.of(4, 3, 1),
                Vector3D.of(2, 1, 1), Vector3D.of(2, 5, 1));
    }

    @Test
    void testClosest() {
        // arrange
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, Arrays.asList(
                Vector3D.of(1, 2, 1), Vector3D.of(3, 2, 1),
                Vector3D.of(3, 4, 1), Vector3D.of(1, 4, 1)
            ));

        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(2, 3, 1), p.closest(Vector3D.of(2, 3, 1)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(2, 3, 1), p.closest(Vector3D.of(2, 3, 100)), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(3, 4, 1), p.closest(Vector3D.of(3, 5, 10)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(3, 4, 1), p.closest(Vector3D.of(3, 4, 10)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(3, 3, 1), p.closest(Vector3D.of(3, 3, 10)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(3, 2, 1), p.closest(Vector3D.of(3, 2, 10)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(3, 2, 1), p.closest(Vector3D.of(3, 1, 10)), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(1, 4, 1), p.closest(Vector3D.of(0, 5, -10)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(1, 4, 1), p.closest(Vector3D.of(1, 5, -10)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(2, 4, 1), p.closest(Vector3D.of(2, 5, -10)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(3, 4, 1), p.closest(Vector3D.of(3, 5, -10)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(3, 4, 1), p.closest(Vector3D.of(4, 5, -10)), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(1, 2, 1), p.closest(Vector3D.of(0, 2, 1)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(1, 2, 1), p.closest(Vector3D.of(1, 2, 1)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(2, 2, 1), p.closest(Vector3D.of(2, 2, 1)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(3, 2, 1), p.closest(Vector3D.of(3, 2, 1)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(3, 2, 1), p.closest(Vector3D.of(4, 2, 1)), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(1, 3, 1), p.closest(Vector3D.of(0, 3, -10)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(1, 3, 1), p.closest(Vector3D.of(1, 3, -10)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(2, 3, 1), p.closest(Vector3D.of(2, 3, -10)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(3, 3, 1), p.closest(Vector3D.of(3, 3, -10)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(3, 3, 1), p.closest(Vector3D.of(4, 3, -10)), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(1, 2, 1),
                p.closest(Vector3D.of(-100, -100, -100)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(3, 3.5, 1),
                p.closest(Vector3D.of(100, 3.5, 100)), TEST_EPS);
    }

    @Test
    void testToString() {
        // arrange
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, TRIANGLE_VERTICES);

        // act
        final String str = p.toString();

        // assert
        GeometryTestUtils.assertContains("VertexListConvexPolygon3D[normal= (", str);
        GeometryTestUtils.assertContains("vertices= [", str);
    }

    private static void checkPoints(final ConvexPolygon3D ps, final RegionLocation loc, final Vector3D... pts) {
        for (final Vector3D pt : pts) {
            Assertions.assertEquals(loc, ps.classify(pt), "Unexpected location for point " + pt);
        }
    }

@Test
    void testProperties_1_oe() {
        // act
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, TRIANGLE_VERTICES);

        // assert
        Assertions.assertFalse(p.isFull());
    }

@Test
    void testProperties_2_oe() {
        // act
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, TRIANGLE_VERTICES);

        // assert
        // removed other assertion
        Assertions.assertFalse(p.isEmpty());
    }

@Test
    void testProperties_3_oe() {
        // act
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, TRIANGLE_VERTICES);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(p.isFinite());
    }

@Test
    void testProperties_4_oe() {
        // act
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, TRIANGLE_VERTICES);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(p.isInfinite());
    }

@Test
    void testProperties_5_oe() {
        // act
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, TRIANGLE_VERTICES);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0.5, p.getSize(), TEST_EPS);
    }

@Test
    void testProperties_7_oe() {
        // act
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, TRIANGLE_VERTICES);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertSame(XY_PLANE_Z1, p.getPlane());
    }

@Test
    void testVertices_listIsImmutable_1_oe() {
        // arrange
        final List<Vector3D> vertices = new ArrayList<>(TRIANGLE_VERTICES);
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, vertices);

        // act/assert
        Assertions.assertThrows(UnsupportedOperationException.class, () -> p.getVertices().add(Vector3D.of(-1, 0, 1)));
    }

@Test
    void testGetSubspaceRegion_1_oe() {
        // arrange
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, TRIANGLE_VERTICES);

        // act
        final ConvexArea area = p.getEmbedded().getSubspaceRegion();

        // assert
        Assertions.assertFalse(area.isFull());
    }

@Test
    void testGetSubspaceRegion_2_oe() {
        // arrange
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, TRIANGLE_VERTICES);

        // act
        final ConvexArea area = p.getEmbedded().getSubspaceRegion();

        // assert
        // removed other assertion
        Assertions.assertFalse(area.isEmpty());
    }

@Test
    void testGetSubspaceRegion_3_oe() {
        // arrange
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, TRIANGLE_VERTICES);

        // act
        final ConvexArea area = p.getEmbedded().getSubspaceRegion();

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(area.isFinite());
    }

@Test
    void testGetSubspaceRegion_4_oe() {
        // arrange
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, TRIANGLE_VERTICES);

        // act
        final ConvexArea area = p.getEmbedded().getSubspaceRegion();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(area.isInfinite());
    }

@Test
    void testGetSubspaceRegion_5_oe() {
        // arrange
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, TRIANGLE_VERTICES);

        // act
        final ConvexArea area = p.getEmbedded().getSubspaceRegion();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0.5, area.getSize(), TEST_EPS);
    }

@Test
    void testGetSubspaceRegion_6_oe() {
        // arrange
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, TRIANGLE_VERTICES);

        // act
        final ConvexArea area = p.getEmbedded().getSubspaceRegion();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<Vector2D> vertices = area.getVertices();
        Assertions.assertEquals(3, vertices.size());
    }

@Test
    void testToTriangles_threeVertices_1_oe() {
        // arrange
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, TRIANGLE_VERTICES);

        // act
        final List<Triangle3D> tris = p.toTriangles();

        // assert
        Assertions.assertEquals(1, tris.size());
    }

@Test
    void testToTriangles_threeVertices_2_oe() {
        // arrange
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, TRIANGLE_VERTICES);

        // act
        final List<Triangle3D> tris = p.toTriangles();

        // assert
        // removed other assertion

        final Triangle3D a = tris.get(0);
        Assertions.assertSame(XY_PLANE_Z1, a.getPlane());
    }

@Test
    void testToTriangles_fiveVertices_1_oe() {
        // arrange
        final Vector3D p1 = Vector3D.of(1, 1, 1);
        final Vector3D p2 = Vector3D.of(2, 1.2, 1);
        final Vector3D p3 = Vector3D.of(3, 2, 1);
        final Vector3D p4 = Vector3D.of(1, 4, 1);
        final Vector3D p5 = Vector3D.of(0, 2, 1);

        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, Arrays.asList(p1, p2, p3, p4, p5));

        // act
        final List<Triangle3D> tris = p.toTriangles();

        // assert
        Assertions.assertEquals(3, tris.size());
    }

@Test
    void testToTriangles_fiveVertices_2_oe() {
        // arrange
        final Vector3D p1 = Vector3D.of(1, 1, 1);
        final Vector3D p2 = Vector3D.of(2, 1.2, 1);
        final Vector3D p3 = Vector3D.of(3, 2, 1);
        final Vector3D p4 = Vector3D.of(1, 4, 1);
        final Vector3D p5 = Vector3D.of(0, 2, 1);

        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, Arrays.asList(p1, p2, p3, p4, p5));

        // act
        final List<Triangle3D> tris = p.toTriangles();

        // assert
        // removed other assertion

        final Triangle3D a = tris.get(0);
        Assertions.assertSame(XY_PLANE_Z1, a.getPlane());
    }

@Test
    void testToTriangles_fiveVertices_4_oe() {
        // arrange
        final Vector3D p1 = Vector3D.of(1, 1, 1);
        final Vector3D p2 = Vector3D.of(2, 1.2, 1);
        final Vector3D p3 = Vector3D.of(3, 2, 1);
        final Vector3D p4 = Vector3D.of(1, 4, 1);
        final Vector3D p5 = Vector3D.of(0, 2, 1);

        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, Arrays.asList(p1, p2, p3, p4, p5));

        // act
        final List<Triangle3D> tris = p.toTriangles();

        // assert
        // removed other assertion

        final Triangle3D a = tris.get(0);
        // removed other assertion
        // removed other assertion

        final Triangle3D b = tris.get(1);
        Assertions.assertSame(XY_PLANE_Z1, b.getPlane());
    }

@Test
    void testToTriangles_fiveVertices_6_oe() {
        // arrange
        final Vector3D p1 = Vector3D.of(1, 1, 1);
        final Vector3D p2 = Vector3D.of(2, 1.2, 1);
        final Vector3D p3 = Vector3D.of(3, 2, 1);
        final Vector3D p4 = Vector3D.of(1, 4, 1);
        final Vector3D p5 = Vector3D.of(0, 2, 1);

        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, Arrays.asList(p1, p2, p3, p4, p5));

        // act
        final List<Triangle3D> tris = p.toTriangles();

        // assert
        // removed other assertion

        final Triangle3D a = tris.get(0);
        // removed other assertion
        // removed other assertion

        final Triangle3D b = tris.get(1);
        // removed other assertion
        // removed other assertion

        final Triangle3D c = tris.get(2);
        Assertions.assertSame(XY_PLANE_Z1, c.getPlane());
    }

@Test
    void testTransform_1_oe() {
        // arrange
        final AffineTransformMatrix3D t = AffineTransformMatrix3D.identity()
                .rotate(QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, -Angle.PI_OVER_TWO))
                .scale(1, 1, 2)
                .translate(Vector3D.of(1, 0, 0));

        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, Arrays.asList(
                Vector3D.of(1, 2, 1), Vector3D.of(3, 2, 1),
                Vector3D.of(3, 4, 1), Vector3D.of(1, 4, 1)
            ));

        // act
        final VertexListConvexPolygon3D result = p.transform(t);

        // assert
        Assertions.assertFalse(result.isFull());
    }

@Test
    void testTransform_2_oe() {
        // arrange
        final AffineTransformMatrix3D t = AffineTransformMatrix3D.identity()
                .rotate(QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, -Angle.PI_OVER_TWO))
                .scale(1, 1, 2)
                .translate(Vector3D.of(1, 0, 0));

        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, Arrays.asList(
                Vector3D.of(1, 2, 1), Vector3D.of(3, 2, 1),
                Vector3D.of(3, 4, 1), Vector3D.of(1, 4, 1)
            ));

        // act
        final VertexListConvexPolygon3D result = p.transform(t);

        // assert
        // removed other assertion
        Assertions.assertFalse(result.isEmpty());
    }

@Test
    void testTransform_3_oe() {
        // arrange
        final AffineTransformMatrix3D t = AffineTransformMatrix3D.identity()
                .rotate(QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, -Angle.PI_OVER_TWO))
                .scale(1, 1, 2)
                .translate(Vector3D.of(1, 0, 0));

        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, Arrays.asList(
                Vector3D.of(1, 2, 1), Vector3D.of(3, 2, 1),
                Vector3D.of(3, 4, 1), Vector3D.of(1, 4, 1)
            ));

        // act
        final VertexListConvexPolygon3D result = p.transform(t);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(result.isFinite());
    }

@Test
    void testTransform_4_oe() {
        // arrange
        final AffineTransformMatrix3D t = AffineTransformMatrix3D.identity()
                .rotate(QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, -Angle.PI_OVER_TWO))
                .scale(1, 1, 2)
                .translate(Vector3D.of(1, 0, 0));

        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, Arrays.asList(
                Vector3D.of(1, 2, 1), Vector3D.of(3, 2, 1),
                Vector3D.of(3, 4, 1), Vector3D.of(1, 4, 1)
            ));

        // act
        final VertexListConvexPolygon3D result = p.transform(t);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(result.isInfinite());
    }

@Test
    void testTransform_5_oe() {
        // arrange
        final AffineTransformMatrix3D t = AffineTransformMatrix3D.identity()
                .rotate(QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, -Angle.PI_OVER_TWO))
                .scale(1, 1, 2)
                .translate(Vector3D.of(1, 0, 0));

        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, Arrays.asList(
                Vector3D.of(1, 2, 1), Vector3D.of(3, 2, 1),
                Vector3D.of(3, 4, 1), Vector3D.of(1, 4, 1)
            ));

        // act
        final VertexListConvexPolygon3D result = p.transform(t);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(8, result.getSize(), TEST_EPS);
    }

@Test
    void testReverse_1_oe() {
        // arrange
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, Arrays.asList(
                Vector3D.of(1, 2, 1), Vector3D.of(3, 2, 1),
                Vector3D.of(3, 4, 1), Vector3D.of(1, 4, 1)
            ));

        // act
        final VertexListConvexPolygon3D result = p.reverse();

        // assert
        Assertions.assertFalse(result.isFull());
    }

@Test
    void testReverse_2_oe() {
        // arrange
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, Arrays.asList(
                Vector3D.of(1, 2, 1), Vector3D.of(3, 2, 1),
                Vector3D.of(3, 4, 1), Vector3D.of(1, 4, 1)
            ));

        // act
        final VertexListConvexPolygon3D result = p.reverse();

        // assert
        // removed other assertion
        Assertions.assertFalse(result.isEmpty());
    }

@Test
    void testReverse_3_oe() {
        // arrange
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, Arrays.asList(
                Vector3D.of(1, 2, 1), Vector3D.of(3, 2, 1),
                Vector3D.of(3, 4, 1), Vector3D.of(1, 4, 1)
            ));

        // act
        final VertexListConvexPolygon3D result = p.reverse();

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(result.isFinite());
    }

@Test
    void testReverse_4_oe() {
        // arrange
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, Arrays.asList(
                Vector3D.of(1, 2, 1), Vector3D.of(3, 2, 1),
                Vector3D.of(3, 4, 1), Vector3D.of(1, 4, 1)
            ));

        // act
        final VertexListConvexPolygon3D result = p.reverse();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(result.isInfinite());
    }

@Test
    void testReverse_5_oe() {
        // arrange
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, Arrays.asList(
                Vector3D.of(1, 2, 1), Vector3D.of(3, 2, 1),
                Vector3D.of(3, 4, 1), Vector3D.of(1, 4, 1)
            ));

        // act
        final VertexListConvexPolygon3D result = p.reverse();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(4, result.getSize(), TEST_EPS);
    }

@Test
    void testSplit_plus_1_oe() {
        // arrange
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, TRIANGLE_VERTICES);

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = p.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.PLUS, split.getLocation());
    }

@Test
    void testSplit_plus_2_oe() {
        // arrange
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, TRIANGLE_VERTICES);

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = p.split(splitter);

        // assert
        // removed other assertion

        Assertions.assertNull(split.getMinus());
    }

@Test
    void testSplit_plus_3_oe() {
        // arrange
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, TRIANGLE_VERTICES);

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = p.split(splitter);

        // assert
        // removed other assertion

        // removed other assertion
        Assertions.assertSame(p, split.getPlus());
    }

@Test
    void testSplit_minus_1_oe() {
        // arrange
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, TRIANGLE_VERTICES);

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_Z, TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = p.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());
    }

@Test
    void testSplit_minus_2_oe() {
        // arrange
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, TRIANGLE_VERTICES);

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_Z, TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = p.split(splitter);

        // assert
        // removed other assertion

        Assertions.assertSame(p, split.getMinus());
    }

@Test
    void testSplit_minus_3_oe() {
        // arrange
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, TRIANGLE_VERTICES);

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_Z, TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = p.split(splitter);

        // assert
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(split.getPlus());
    }

@Test
    void testSplit_both_1_oe() {
        // arrange
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, TRIANGLE_VERTICES);

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.of(-1, 1, 0), TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = p.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

@Test
    void testSplit_neither_1_oe() {
        // arrange
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, TRIANGLE_VERTICES);

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.of(0, 0, 1), Vector3D.of(0, 1e-15, -1), TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = p.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.NEITHER, split.getLocation());
    }

@Test
    void testSplit_neither_2_oe() {
        // arrange
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, TRIANGLE_VERTICES);

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.of(0, 0, 1), Vector3D.of(0, 1e-15, -1), TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = p.split(splitter);

        // assert
        // removed other assertion

        Assertions.assertNull(split.getMinus());
    }

@Test
    void testSplit_neither_3_oe() {
        // arrange
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, TRIANGLE_VERTICES);

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.of(0, 0, 1), Vector3D.of(0, 1e-15, -1), TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = p.split(splitter);

        // assert
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(split.getPlus());
    }

}
