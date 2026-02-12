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

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.core.RegionLocation;
import org.apache.commons.geometry.core.partitioning.Split;
import org.apache.commons.geometry.core.partitioning.SplitLocation;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.threed.rotation.QuaternionRotation;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SimpleTriangle3DTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    private static final Plane XY_PLANE_Z1 = Planes.fromPointAndPlaneVectors(Vector3D.of(0, 0, 1),
            Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);

    @Test
    void testClassify() {
        // arrange
        final Vector3D p1 = Vector3D.of(1, 2, 1);
        final Vector3D p2 = Vector3D.of(3, 2, 1);
        final Vector3D p3 = Vector3D.of(2, 3, 1);

        final SimpleTriangle3D tri = new SimpleTriangle3D(XY_PLANE_Z1, p1, p2, p3);

        // act/assert
        checkPoints(tri, RegionLocation.INSIDE, Vector3D.of(2, 2.5, 1), Vector3D.of(2, 2.5, 1 + 1e-15));
        checkPoints(tri, RegionLocation.BOUNDARY,
                p1, p2, p3,
                p1.lerp(p2, 0.5), p2.lerp(p3, 0.5), p3.lerp(p1,  0.5));
        checkPoints(tri, RegionLocation.OUTSIDE,
                Vector3D.of(2, 2.5, 0), Vector3D.of(2, 2.5, 2),
                Vector3D.of(0, 2, 1), Vector3D.of(4, 2, 1),
                Vector3D.of(2, 4, 1), Vector3D.of(2, 1, 1));
    }

    @Test
    void testClosest() {
        // arrange
        final Vector3D p1 = Vector3D.of(1, 2, 1);
        final Vector3D p2 = Vector3D.of(3, 2, 1);
        final Vector3D p3 = Vector3D.of(2, 3, 1);

        final Vector3D centroid = Vector3D.centroid(p1, p2, p3);

        final SimpleTriangle3D tri = new SimpleTriangle3D(XY_PLANE_Z1, p1, p2, p3);

        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(centroid, tri.closest(centroid), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(centroid, tri.closest(centroid.add(Vector3D.Unit.PLUS_Z)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(centroid, tri.closest(centroid.add(Vector3D.Unit.MINUS_Z)), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(p1, tri.closest(Vector3D.of(0, 2, 5)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(p1, tri.closest(Vector3D.of(1, 2, 5)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(2, 2, 1), tri.closest(Vector3D.of(2, 2, 5)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(p2, tri.closest(Vector3D.of(3, 2, 5)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(p2, tri.closest(Vector3D.of(4, 2, 5)), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(p1, tri.closest(Vector3D.of(0, 1, 5)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(p1, tri.closest(Vector3D.of(1, 1, 5)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(2, 2, 1), tri.closest(Vector3D.of(2, 1, 5)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(p2, tri.closest(Vector3D.of(3, 1, 5)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(p2, tri.closest(Vector3D.of(4, 1, 5)), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(1.5, 2.5, 1),
                tri.closest(Vector3D.of(1, 3, -10)), TEST_EPS);
    }

    @Test
    void testToString() {
        // arrange
        final SimpleTriangle3D tri = new SimpleTriangle3D(XY_PLANE_Z1,
                Vector3D.of(0, 0, 1), Vector3D.of(1, 0, 1), Vector3D.of(0, 1, 1));

        // act
        final String str = tri.toString();

        // assert
        GeometryTestUtils.assertContains("SimpleTriangle3D[normal= (", str);
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
        final SimpleTriangle3D tri = new SimpleTriangle3D(XY_PLANE_Z1,
                Vector3D.of(0, 0, 1), Vector3D.of(1, 0, 1), Vector3D.of(0, 1, 1));

        // act/assert
        try {
    tri.getVertices().add(Vector3D.of(-1, 0, 1));
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

}
