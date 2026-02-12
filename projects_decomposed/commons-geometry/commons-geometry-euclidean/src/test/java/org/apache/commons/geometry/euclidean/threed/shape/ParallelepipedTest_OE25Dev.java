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
package org.apache.commons.geometry.euclidean.threed.shape;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.threed.AffineTransformMatrix3D;
import org.apache.commons.geometry.euclidean.threed.PlaneConvexSubset;
import org.apache.commons.geometry.euclidean.threed.RegionBSPTree3D;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.geometry.euclidean.threed.rotation.QuaternionRotation;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParallelepipedTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    private static final Comparator<Vector3D> VERTEX_COMPARATOR = (a, b) -> {
        int cmp = TEST_PRECISION.compare(a.getX(), b.getX());
        if (cmp == 0) {
            cmp = TEST_PRECISION.compare(a.getY(), b.getY());
            if (cmp == 0) {
                cmp = TEST_PRECISION.compare(a.getZ(), b.getZ());
            }
        }
        return cmp;
    };

    private static void assertVertices(final Parallelepiped p, final Vector3D... vertices) {
        final Set<Vector3D> expectedVertices = new TreeSet<>(VERTEX_COMPARATOR);
        expectedVertices.addAll(Arrays.asList(vertices));

        final Set<Vector3D> actualVertices = new TreeSet<>(VERTEX_COMPARATOR);
        for (final PlaneConvexSubset boundary : p.getBoundaries()) {
            actualVertices.addAll(boundary.getVertices());
        }

        Assertions.assertEquals(expectedVertices.size(), actualVertices.size());
        for (final Vector3D expected : expectedVertices) {
            Assertions.assertTrue(actualVertices.contains(expected), "Expected vertices to contain " + expected);
        }
    }

    @Test
    void testFromTransformedUnitCube_zeroSizeRegion_1_oe() {
        // act/assert
        try {
    Parallelepiped.fromTransformedUnitCube(AffineTransformMatrix3D.createScale(Vector3D.of(1e-16, 1, 1)), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFromTransformedUnitCube_zeroSizeRegion_2_oe() {
        // act/assert
        // removed other assertion
        try {
    Parallelepiped.fromTransformedUnitCube(AffineTransformMatrix3D.createScale(Vector3D.of(1, 1e-16, 1)), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFromTransformedUnitCube_zeroSizeRegion_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        try {
    Parallelepiped.fromTransformedUnitCube(AffineTransformMatrix3D.createScale(Vector3D.of(1, 1, 1e-16)), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAxisAligned_illegalArgs_1_oe() {
        // act/assert
        try {
    Parallelepiped.axisAligned(Vector3D.of(1, 2, 3), Vector3D.of(1, 5, 6), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAxisAligned_illegalArgs_2_oe() {
        // act/assert
        // removed other assertion
        try {
    Parallelepiped.axisAligned(Vector3D.of(1, 2, 3), Vector3D.of(4, 2, 6), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAxisAligned_illegalArgs_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        try {
    Parallelepiped.axisAligned(Vector3D.of(1, 2, 3), Vector3D.of(1, 5, 3), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
