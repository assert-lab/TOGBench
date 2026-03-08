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

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.core.RegionLocation;
import org.apache.commons.geometry.core.partitioning.Split;
import org.apache.commons.geometry.core.partitioning.SplitLocation;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.threed.rotation.QuaternionRotation;
import org.apache.commons.geometry.euclidean.twod.ConvexArea;
import org.apache.commons.geometry.euclidean.twod.Lines;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.geometry.euclidean.twod.path.LinePath;
import org.apache.commons.geometry.euclidean.twod.shape.Parallelogram;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class EmbeddedAreaPlaneConvexSubsetTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    private static final EmbeddingPlane XY_PLANE_Z1 = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
            Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);

    @Test
    void testSpaceConversion() {
        // arrange
        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(1, 0, 0),
                Vector3D.Unit.PLUS_Y, Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(plane, ConvexArea.full());

        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, 2), ps.toSubspace(Vector3D.of(-5, 1, 2)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(1, -2, 4), ps.toSpace(Vector2D.of(-2, 4)), TEST_EPS);
    }

    @Test
    void testClassify() {
        // arrange
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1,
                Parallelogram.builder(TEST_PRECISION)
                    .setPosition(Vector2D.of(2, 3))
                    .setScale(2, 2)
                    .build());

        // act/assert
        checkPoints(ps, RegionLocation.INSIDE, Vector3D.of(2, 3, 1));
        checkPoints(ps, RegionLocation.BOUNDARY,
                Vector3D.of(1, 3, 1), Vector3D.of(3, 3, 1),
                Vector3D.of(2, 2, 1), Vector3D.of(2, 4, 1));
        checkPoints(ps, RegionLocation.OUTSIDE,
                Vector3D.of(2, 3, 0), Vector3D.of(2, 3, 2),
                Vector3D.of(0, 3, 1), Vector3D.of(4, 3, 1),
                Vector3D.of(2, 1, 1), Vector3D.of(2, 5, 1));
    }

    @Test
    void testClosest() {
        // arrange
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1,
                Parallelogram.builder(TEST_PRECISION)
                    .setPosition(Vector2D.of(2, 3))
                    .setScale(2, 2)
                    .build());

        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(2, 3, 1), ps.closest(Vector3D.of(2, 3, 1)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(2, 3, 1), ps.closest(Vector3D.of(2, 3, 100)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(1, 2, 1),
                ps.closest(Vector3D.of(-100, -100, -100)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(3, 3.5, 1),
                ps.closest(Vector3D.of(100, 3.5, 100)), TEST_EPS);
    }

    @Test
    void testGetBounds_hasBounds() {
        // arrange
        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_Y, Vector3D.Unit.MINUS_X, TEST_PRECISION);

        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(plane,
                ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1), Vector2D.of(1, 2)
                ), TEST_PRECISION));

        // act
        final Bounds3D bounds = ps.getBounds();

        // assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(-2, 1, 1), bounds.getMin(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(-1, 2, 1), bounds.getMax(), TEST_EPS);
    }

    @Test
    void testToString() {
        // arrange
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1,
                ConvexArea.convexPolygonFromVertices(Arrays.asList(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(0, 1)),
                        TEST_PRECISION));

        // act
        final String str = ps.toString();

        // assert
        GeometryTestUtils.assertContains("EmbeddedAreaPlaneConvexSubset[plane= EmbeddingPlane[", str);
        GeometryTestUtils.assertContains("subspaceRegion= ConvexArea[", str);
    }

    private static void checkPoints(final EmbeddedAreaPlaneConvexSubset ps, final RegionLocation loc, final Vector3D... pts) {
        for (final Vector3D pt : pts) {
            Assertions.assertEquals(loc, ps.classify(pt), "Unexpected location for point " + pt);
        }
    }

    @Test
    void testProperties_infinite_1_oe() {
        // arrange
        final ConvexArea area = ConvexArea.full();

        // act
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1, area);

        // assert
        Assertions.assertTrue(ps.isFull());
    }

    @Test
    void testProperties_infinite_2_oe() {
        // arrange
        final ConvexArea area = ConvexArea.full();

        // act
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1, area);

        // assert
        // removed other assertion
        Assertions.assertFalse(ps.isEmpty());
    }

    @Test
    void testProperties_infinite_3_oe() {
        // arrange
        final ConvexArea area = ConvexArea.full();

        // act
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1, area);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(ps.isFinite());
    }

    @Test
    void testProperties_infinite_4_oe() {
        // arrange
        final ConvexArea area = ConvexArea.full();

        // act
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1, area);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(ps.isInfinite());
    }

    @Test
    void testProperties_infinite_6_oe() {
        // arrange
        final ConvexArea area = ConvexArea.full();

        // act
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1, area);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        Assertions.assertSame(XY_PLANE_Z1, ps.getPlane());
    }

    @Test
    void testProperties_infinite_7_oe() {
        // arrange
        final ConvexArea area = ConvexArea.full();

        // act
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1, area);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        Assertions.assertSame(area, ps.getSubspaceRegion());
    }

    @Test
    void testProperties_infinite_8_oe() {
        // arrange
        final ConvexArea area = ConvexArea.full();

        // act
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1, area);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0, ps.getVertices().size());
    }

    @Test
    void testProperties_finite_1_oe() {
        // arrange
        final ConvexArea area = ConvexArea.convexPolygonFromPath(LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(0, 1))
                .build(true));

        // act
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1, area);

        // assert
        Assertions.assertFalse(ps.isFull());
    }

    @Test
    void testProperties_finite_2_oe() {
        // arrange
        final ConvexArea area = ConvexArea.convexPolygonFromPath(LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(0, 1))
                .build(true));

        // act
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1, area);

        // assert
        // removed other assertion
        Assertions.assertFalse(ps.isEmpty());
    }

    @Test
    void testProperties_finite_3_oe() {
        // arrange
        final ConvexArea area = ConvexArea.convexPolygonFromPath(LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(0, 1))
                .build(true));

        // act
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1, area);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(ps.isFinite());
    }

    @Test
    void testProperties_finite_4_oe() {
        // arrange
        final ConvexArea area = ConvexArea.convexPolygonFromPath(LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(0, 1))
                .build(true));

        // act
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1, area);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(ps.isInfinite());
    }

    @Test
    void testProperties_finite_5_oe() {
        // arrange
        final ConvexArea area = ConvexArea.convexPolygonFromPath(LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(0, 1))
                .build(true));

        // act
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1, area);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0.5, ps.getSize(), TEST_EPS);
    }

    @Test
    void testProperties_finite_6_oe() {
        // arrange
        final ConvexArea area = ConvexArea.convexPolygonFromPath(LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(0, 1))
                .build(true));

        // act
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1, area);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        Assertions.assertSame(XY_PLANE_Z1, ps.getPlane());
    }

    @Test
    void testProperties_finite_7_oe() {
        // arrange
        final ConvexArea area = ConvexArea.convexPolygonFromPath(LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(0, 1))
                .build(true));

        // act
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1, area);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        Assertions.assertSame(area, ps.getSubspaceRegion());
    }

    @Test
    void testGetVertices_twoParallelLines_1_oe() {
        // arrange
        final EmbeddingPlane plane = Planes.fromNormal(Vector3D.Unit.PLUS_Z, TEST_PRECISION).getEmbedding();
        final PlaneConvexSubset sp = new EmbeddedAreaPlaneConvexSubset(plane, ConvexArea.fromBounds(
                    Lines.fromPointAndAngle(Vector2D.of(0, 1), Math.PI, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(0, -1), 0.0, TEST_PRECISION)
                ));

        // act
        final List<Vector3D> vertices = sp.getVertices();

        // assert
        Assertions.assertEquals(0, vertices.size());
    }

    @Test
    void testGetVertices_infiniteWithVertices_1_oe() {
        // arrange
        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1), Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final PlaneConvexSubset sp = new EmbeddedAreaPlaneConvexSubset(plane, ConvexArea.fromBounds(
                    Lines.fromPointAndAngle(Vector2D.of(0, 1), Math.PI, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(0, -1), 0.0, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(1, 0), Angle.PI_OVER_TWO, TEST_PRECISION)
                ));

        // act
        final List<Vector3D> vertices = sp.getVertices();

        // assert
        Assertions.assertEquals(2, vertices.size());
    }

    @Test
    void testToTriangles_finite_1_oe() {
        // arrange
        final Vector3D p1 = Vector3D.of(0, 0, 1);
        final Vector3D p2 = Vector3D.of(1, 0, 1);
        final Vector3D p3 = Vector3D.of(2, 1, 1);
        final Vector3D p4 = Vector3D.of(1.5, 1, 1);

        final List<Vector2D> subPts = XY_PLANE_Z1.toSubspace(Arrays.asList(p1, p2, p3, p4));

        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1,
                ConvexArea.convexPolygonFromVertices(subPts, TEST_PRECISION));

        // act
        final List<Triangle3D> tris = ps.toTriangles();

        // assert
        Assertions.assertEquals(2, tris.size());
    }

    @Test
    void testGetBounds_noBounds_1_oe() {
        // arrange
        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_Y, Vector3D.Unit.MINUS_X, TEST_PRECISION);

        final EmbeddedAreaPlaneConvexSubset full = new EmbeddedAreaPlaneConvexSubset(plane, ConvexArea.full());
        final EmbeddedAreaPlaneConvexSubset halfPlane = new EmbeddedAreaPlaneConvexSubset(plane,
                ConvexArea.fromBounds(Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION)));

        // act/assert
        Assertions.assertNull(full.getBounds());
    }

    @Test
    void testGetBounds_noBounds_2_oe() {
        // arrange
        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
                Vector3D.Unit.PLUS_Y, Vector3D.Unit.MINUS_X, TEST_PRECISION);

        final EmbeddedAreaPlaneConvexSubset full = new EmbeddedAreaPlaneConvexSubset(plane, ConvexArea.full());
        final EmbeddedAreaPlaneConvexSubset halfPlane = new EmbeddedAreaPlaneConvexSubset(plane,
                ConvexArea.fromBounds(Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION)));

        // act/assert
        // removed other assertion
        Assertions.assertNull(halfPlane.getBounds());
    }

    @Test
    void testTransform_1_oe() {
        // arrange
        final AffineTransformMatrix3D t = AffineTransformMatrix3D.identity()
                .rotate(QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, -Angle.PI_OVER_TWO))
                .scale(1, 1, 2)
                .translate(Vector3D.of(1, 0, 0));

        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1,
                Parallelogram.builder(TEST_PRECISION)
                    .setPosition(Vector2D.of(2, 3))
                    .setScale(2, 2)
                    .build());

        // act
        final EmbeddedAreaPlaneConvexSubset result = ps.transform(t);

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

        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1,
                Parallelogram.builder(TEST_PRECISION)
                    .setPosition(Vector2D.of(2, 3))
                    .setScale(2, 2)
                    .build());

        // act
        final EmbeddedAreaPlaneConvexSubset result = ps.transform(t);

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

        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1,
                Parallelogram.builder(TEST_PRECISION)
                    .setPosition(Vector2D.of(2, 3))
                    .setScale(2, 2)
                    .build());

        // act
        final EmbeddedAreaPlaneConvexSubset result = ps.transform(t);

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

        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1,
                Parallelogram.builder(TEST_PRECISION)
                    .setPosition(Vector2D.of(2, 3))
                    .setScale(2, 2)
                    .build());

        // act
        final EmbeddedAreaPlaneConvexSubset result = ps.transform(t);

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

        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1,
                Parallelogram.builder(TEST_PRECISION)
                    .setPosition(Vector2D.of(2, 3))
                    .setScale(2, 2)
                    .build());

        // act
        final EmbeddedAreaPlaneConvexSubset result = ps.transform(t);

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
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1,
                Parallelogram.builder(TEST_PRECISION)
                    .setPosition(Vector2D.of(2, 3))
                    .setScale(2, 2)
                    .build());

        // act
        final EmbeddedAreaPlaneConvexSubset result = ps.reverse();

        // assert
        Assertions.assertFalse(result.isFull());
    }

    @Test
    void testReverse_2_oe() {
        // arrange
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1,
                Parallelogram.builder(TEST_PRECISION)
                    .setPosition(Vector2D.of(2, 3))
                    .setScale(2, 2)
                    .build());

        // act
        final EmbeddedAreaPlaneConvexSubset result = ps.reverse();

        // assert
        // removed other assertion
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    void testReverse_3_oe() {
        // arrange
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1,
                Parallelogram.builder(TEST_PRECISION)
                    .setPosition(Vector2D.of(2, 3))
                    .setScale(2, 2)
                    .build());

        // act
        final EmbeddedAreaPlaneConvexSubset result = ps.reverse();

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(result.isFinite());
    }

    @Test
    void testReverse_4_oe() {
        // arrange
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1,
                Parallelogram.builder(TEST_PRECISION)
                    .setPosition(Vector2D.of(2, 3))
                    .setScale(2, 2)
                    .build());

        // act
        final EmbeddedAreaPlaneConvexSubset result = ps.reverse();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertFalse(result.isInfinite());
    }

    @Test
    void testReverse_5_oe() {
        // arrange
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1,
                Parallelogram.builder(TEST_PRECISION)
                    .setPosition(Vector2D.of(2, 3))
                    .setScale(2, 2)
                    .build());

        // act
        final EmbeddedAreaPlaneConvexSubset result = ps.reverse();

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
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1,
                ConvexArea.convexPolygonFromVertices(Arrays.asList(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(0, 1)),
                        TEST_PRECISION));

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = ps.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.PLUS, split.getLocation());
    }

    @Test
    void testSplit_plus_2_oe() {
        // arrange
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1,
                ConvexArea.convexPolygonFromVertices(Arrays.asList(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(0, 1)),
                        TEST_PRECISION));

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = ps.split(splitter);

        // assert
        // removed other assertion

        Assertions.assertNull(split.getMinus());
    }

    @Test
    void testSplit_plus_3_oe() {
        // arrange
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1,
                ConvexArea.convexPolygonFromVertices(Arrays.asList(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(0, 1)),
                        TEST_PRECISION));

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = ps.split(splitter);

        // assert
        // removed other assertion

        // removed other assertion
        Assertions.assertSame(ps, split.getPlus());
    }

    @Test
    void testSplit_minus_1_oe() {
        // arrange
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1,
                ConvexArea.convexPolygonFromVertices(Arrays.asList(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(0, 1)),
                        TEST_PRECISION));

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_Z, TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = ps.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());
    }

    @Test
    void testSplit_minus_2_oe() {
        // arrange
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1,
                ConvexArea.convexPolygonFromVertices(Arrays.asList(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(0, 1)),
                        TEST_PRECISION));

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_Z, TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = ps.split(splitter);

        // assert
        // removed other assertion

        Assertions.assertSame(ps, split.getMinus());
    }

    @Test
    void testSplit_minus_3_oe() {
        // arrange
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1,
                ConvexArea.convexPolygonFromVertices(Arrays.asList(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(0, 1)),
                        TEST_PRECISION));

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.Unit.MINUS_Z, TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = ps.split(splitter);

        // assert
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplit_both_1_oe() {
        // arrange
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1,
                ConvexArea.convexPolygonFromVertices(Arrays.asList(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(0, 1)),
                        TEST_PRECISION));

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.ZERO, Vector3D.of(-1, 1, 0), TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = ps.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_neither_1_oe() {
        // arrange
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1,
                ConvexArea.convexPolygonFromVertices(Arrays.asList(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(0, 1)),
                        TEST_PRECISION));

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.of(0, 0, 1), Vector3D.of(0, 1e-15, -1), TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = ps.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.NEITHER, split.getLocation());
    }

    @Test
    void testSplit_neither_2_oe() {
        // arrange
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1,
                ConvexArea.convexPolygonFromVertices(Arrays.asList(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(0, 1)),
                        TEST_PRECISION));

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.of(0, 0, 1), Vector3D.of(0, 1e-15, -1), TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = ps.split(splitter);

        // assert
        // removed other assertion

        Assertions.assertNull(split.getMinus());
    }

    @Test
    void testSplit_neither_3_oe() {
        // arrange
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1,
                ConvexArea.convexPolygonFromVertices(Arrays.asList(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(0, 1)),
                        TEST_PRECISION));

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.of(0, 0, 1), Vector3D.of(0, 1e-15, -1), TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = ps.split(splitter);

        // assert
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplit_usesVertexBasedSubsetsWhenPossible_1_oe() {
        // arrange
        // create an infinite subset
        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.ZERO,
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(plane, ConvexArea.fromBounds(
                    Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(1, 0), Angle.PI_OVER_TWO, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(0, 1), -Angle.PI_OVER_TWO, TEST_PRECISION)
                ));

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.of(0.5, 0.5, 0), Vector3D.of(-1, 1, 0), TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = ps.split(splitter);

        // assert
        Assertions.assertTrue(ps.isInfinite());
    }

    @Test
    void testSplit_usesVertexBasedSubsetsWhenPossible_2_oe() {
        // arrange
        // create an infinite subset
        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.ZERO,
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(plane, ConvexArea.fromBounds(
                    Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(1, 0), Angle.PI_OVER_TWO, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(0, 1), -Angle.PI_OVER_TWO, TEST_PRECISION)
                ));

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.of(0.5, 0.5, 0), Vector3D.of(-1, 1, 0), TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = ps.split(splitter);

        // assert
        // removed other assertion

        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_usesVertexBasedSubsetsWhenPossible_3_oe() {
        // arrange
        // create an infinite subset
        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.ZERO,
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(plane, ConvexArea.fromBounds(
                    Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(1, 0), Angle.PI_OVER_TWO, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(0, 1), -Angle.PI_OVER_TWO, TEST_PRECISION)
                ));

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.of(0.5, 0.5, 0), Vector3D.of(-1, 1, 0), TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = ps.split(splitter);

        // assert
        // removed other assertion

        // removed other assertion

        final PlaneConvexSubset plus = split.getPlus();
        Assertions.assertNotNull(plus);
    }

    @Test
    void testSplit_usesVertexBasedSubsetsWhenPossible_4_oe() {
        // arrange
        // create an infinite subset
        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.ZERO,
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(plane, ConvexArea.fromBounds(
                    Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(1, 0), Angle.PI_OVER_TWO, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(0, 1), -Angle.PI_OVER_TWO, TEST_PRECISION)
                ));

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.of(0.5, 0.5, 0), Vector3D.of(-1, 1, 0), TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = ps.split(splitter);

        // assert
        // removed other assertion

        // removed other assertion

        final PlaneConvexSubset plus = split.getPlus();
        // removed other assertion
        Assertions.assertTrue(plus.isInfinite());
    }

    @Test
    void testSplit_usesVertexBasedSubsetsWhenPossible_5_oe() {
        // arrange
        // create an infinite subset
        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.ZERO,
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(plane, ConvexArea.fromBounds(
                    Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(1, 0), Angle.PI_OVER_TWO, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(0, 1), -Angle.PI_OVER_TWO, TEST_PRECISION)
                ));

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.of(0.5, 0.5, 0), Vector3D.of(-1, 1, 0), TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = ps.split(splitter);

        // assert
        // removed other assertion

        // removed other assertion

        final PlaneConvexSubset plus = split.getPlus();
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(plus instanceof EmbeddedAreaPlaneConvexSubset);
    }

    @Test
    void testSplit_usesVertexBasedSubsetsWhenPossible_6_oe() {
        // arrange
        // create an infinite subset
        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.ZERO,
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(plane, ConvexArea.fromBounds(
                    Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(1, 0), Angle.PI_OVER_TWO, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(0, 1), -Angle.PI_OVER_TWO, TEST_PRECISION)
                ));

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.of(0.5, 0.5, 0), Vector3D.of(-1, 1, 0), TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = ps.split(splitter);

        // assert
        // removed other assertion

        // removed other assertion

        final PlaneConvexSubset plus = split.getPlus();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset minus = split.getMinus();
        Assertions.assertNotNull(minus);
    }

    @Test
    void testSplit_usesVertexBasedSubsetsWhenPossible_7_oe() {
        // arrange
        // create an infinite subset
        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.ZERO,
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(plane, ConvexArea.fromBounds(
                    Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(1, 0), Angle.PI_OVER_TWO, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(0, 1), -Angle.PI_OVER_TWO, TEST_PRECISION)
                ));

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.of(0.5, 0.5, 0), Vector3D.of(-1, 1, 0), TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = ps.split(splitter);

        // assert
        // removed other assertion

        // removed other assertion

        final PlaneConvexSubset plus = split.getPlus();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset minus = split.getMinus();
        // removed other assertion
        Assertions.assertFalse(minus.isInfinite());
    }

    @Test
    void testSplit_usesVertexBasedSubsetsWhenPossible_8_oe() {
        // arrange
        // create an infinite subset
        final EmbeddingPlane plane = Planes.fromPointAndPlaneVectors(Vector3D.ZERO,
                Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final EmbeddedAreaPlaneConvexSubset ps = new EmbeddedAreaPlaneConvexSubset(plane, ConvexArea.fromBounds(
                    Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(1, 0), Angle.PI_OVER_TWO, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(0, 1), -Angle.PI_OVER_TWO, TEST_PRECISION)
                ));

        final Plane splitter = Planes.fromPointAndNormal(Vector3D.of(0.5, 0.5, 0), Vector3D.of(-1, 1, 0), TEST_PRECISION);

        // act
        final Split<PlaneConvexSubset> split = ps.split(splitter);

        // assert
        // removed other assertion

        // removed other assertion

        final PlaneConvexSubset plus = split.getPlus();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PlaneConvexSubset minus = split.getMinus();
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(minus instanceof SimpleTriangle3D);
    }

@Test
    void testToTriangles_infinite_1_oe() {
        // arrange
        final Pattern pattern = Pattern.compile("^Cannot convert infinite plane subset to triangles: .*");

        // act/assert
        try {
     new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1, ConvexArea.full()).toTriangles();
    fail("Expected IllegalStateException with message: " + pattern);
} catch (IllegalStateException e) {
}
    }

@Test
    void testToTriangles_infinite_2_oe() {
        // arrange
        final Pattern pattern = Pattern.compile("^Cannot convert infinite plane subset to triangles: .*");

        // act/assert
        // removed other assertion

        try {
     final ConvexArea area = ConvexArea.fromBounds(Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION)); final EmbeddedAreaPlaneConvexSubset halfSpace = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1, area); halfSpace.toTriangles();
    fail("Expected IllegalStateException with message: " + pattern);
} catch (IllegalStateException e) {
}
    }

@Test
    void testToTriangles_infinite_3_oe() {
        // arrange
        final Pattern pattern = Pattern.compile("^Cannot convert infinite plane subset to triangles: .*");

        // act/assert
        // removed other assertion

        // removed other assertion

        try {
     final ConvexArea area = ConvexArea.fromBounds( Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION), Lines.fromPointAndAngle(Vector2D.ZERO, 0.5 * Math.PI, TEST_PRECISION)); final EmbeddedAreaPlaneConvexSubset halfSpaceWithVertices = new EmbeddedAreaPlaneConvexSubset(XY_PLANE_Z1, area); halfSpaceWithVertices.toTriangles();
    fail("Expected IllegalStateException with message: " + pattern);
} catch (IllegalStateException e) {
}
    }

}
