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
    void testVertices_listIsImmutable_1_oe() {
        // arrange
        final List<Vector3D> vertices = new ArrayList<>(TRIANGLE_VERTICES);
        final VertexListConvexPolygon3D p = new VertexListConvexPolygon3D(XY_PLANE_Z1, vertices);

        // act/assert
        try {
    p.getVertices().add(Vector3D.of(-1, 0, 1));
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

}
