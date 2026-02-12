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
package org.apache.commons.geometry.euclidean.twod.shape;

import java.util.List;

import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.twod.AffineTransformMatrix2D;
import org.apache.commons.geometry.euclidean.twod.LineConvexSubset;
import org.apache.commons.geometry.euclidean.twod.RegionBSPTree2D;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.geometry.euclidean.twod.path.LinePath;
import org.apache.commons.geometry.euclidean.twod.rotation.Rotation2D;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParallelogramTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    private static void assertSegment(final LineConvexSubset segment, final Vector2D start, final Vector2D end) {
        EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromTransformedUnitSquare_zeroSizeRegion_1_oe() {
        // act/assert
        try {
    Parallelogram.fromTransformedUnitSquare(AffineTransformMatrix2D.createScale(Vector2D.of(1e-16, 1)), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFromTransformedUnitSquare_zeroSizeRegion_2_oe() {
        // act/assert
        // removed other assertion

        try {
    Parallelogram.fromTransformedUnitSquare(AffineTransformMatrix2D.createScale(Vector2D.of(1, 1e-16)), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAxisAligned_illegalArgs_1_oe() {
        // act/assert

        try {
    Parallelogram.axisAligned(Vector2D.of(1, 1), Vector2D.of(1, 3), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAxisAligned_illegalArgs_2_oe() {
        // act/assert

        // removed other assertion
        try {
    Parallelogram.axisAligned(Vector2D.of(1, 1), Vector2D.of(3, 1), TEST_PRECISION);
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
    Parallelogram.axisAligned(Vector2D.of(2, 3), Vector2D.of(2, 3), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
