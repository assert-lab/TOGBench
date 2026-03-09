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

import static org.junit.jupiter.api.Assertions.fail;

class ParallelogramTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    @Test
    void testUnitSquare() {
        // act
        final Parallelogram box = Parallelogram.unitSquare(TEST_PRECISION);

        // assert
        Assertions.assertEquals(1, box.getSize(), TEST_EPS);
        Assertions.assertEquals(4, box.getBoundarySize(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.ZERO, box.getCentroid(), TEST_EPS);

        final List<Vector2D> vertices = box.getVertices();
        Assertions.assertEquals(4, vertices.size());
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(-0.5, -0.5), vertices.get(0), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0.5, -0.5), vertices.get(1), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0.5, 0.5), vertices.get(2), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(-0.5, 0.5), vertices.get(3), TEST_EPS);
    }

    @Test
    void testFromTransformedUnitSquare() {
        // arrange
        final AffineTransformMatrix2D t = AffineTransformMatrix2D.createTranslation(Vector2D.of(1, 0))
                .rotate(Math.PI * 0.25)
                .scale(Vector2D.of(2, 1));

        // act
        final Parallelogram p = Parallelogram.fromTransformedUnitSquare(t, TEST_PRECISION);

        // assert
        final double sqrt2 = Math.sqrt(2);
        final double invSqrt2 = 1 / sqrt2;

        Assertions.assertEquals(2, p.getSize(), TEST_EPS);
        Assertions.assertEquals(4 * Math.sqrt(2.5), p.getBoundarySize(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(2 * invSqrt2, invSqrt2), p.getCentroid(), TEST_EPS);

        final List<Vector2D> vertices = p.getVertices();
        Assertions.assertEquals(4, vertices.size());
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0, invSqrt2), vertices.get(0), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(2 * invSqrt2, 0), vertices.get(1), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(2 * sqrt2, invSqrt2), vertices.get(2), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(2 * invSqrt2, sqrt2), vertices.get(3), TEST_EPS);
    }

    @Test
    void testFromTransformedUnitSquare_transformDoesNotPreserveOrientation() {
        // arrange
        final AffineTransformMatrix2D t = AffineTransformMatrix2D.createTranslation(Vector2D.of(1, 0))
                .rotate(Math.PI * 0.25)
                .scale(Vector2D.of(-2, 1));

        // act
        final Parallelogram p = Parallelogram.fromTransformedUnitSquare(t, TEST_PRECISION);

        // assert
        final double sqrt2 = Math.sqrt(2);
        final double invSqrt2 = 1 / sqrt2;

        Assertions.assertEquals(2, p.getSize(), TEST_EPS);
        Assertions.assertEquals(4 * Math.sqrt(2.5), p.getBoundarySize(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(-2 * invSqrt2, invSqrt2), p.getCentroid(), TEST_EPS);

        final List<Vector2D> vertices = p.getVertices();
        Assertions.assertEquals(4, vertices.size());
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(-2 * sqrt2, invSqrt2), vertices.get(0), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(-2 * invSqrt2, 0), vertices.get(1), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0, invSqrt2), vertices.get(2), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(-2 * invSqrt2, sqrt2), vertices.get(3), TEST_EPS);
    }

    @Test
    void testFromTransformedUnitSquare_zeroSizeRegion() {
        // act/assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->  Parallelogram.fromTransformedUnitSquare(AffineTransformMatrix2D.createScale(Vector2D.of(1e-16, 1)),
                TEST_PRECISION));

        Assertions.assertThrows(IllegalArgumentException.class, () ->  Parallelogram.fromTransformedUnitSquare(AffineTransformMatrix2D.createScale(Vector2D.of(1, 1e-16)),
                TEST_PRECISION));
    }

    @Test
    void testAxisAligned_minFirst() {
        // act
        final Parallelogram box = Parallelogram.axisAligned(Vector2D.of(1, 2), Vector2D.of(3, 4), TEST_PRECISION);

        // assert
        Assertions.assertEquals(1, box.getBoundaryPaths().size());
        final LinePath path = box.getBoundaryPaths().get(0);

        final List<LineConvexSubset> segments = path.getElements();
        Assertions.assertEquals(4, segments.size());

        assertSegment(segments.get(0), Vector2D.of(1, 2), Vector2D.of(3, 2));
        assertSegment(segments.get(1), Vector2D.of(3, 2), Vector2D.of(3, 4));
        assertSegment(segments.get(2), Vector2D.of(3, 4), Vector2D.of(1, 4));
        assertSegment(segments.get(3), Vector2D.of(1, 4), Vector2D.of(1, 2));
    }

    @Test
    void testAxisAligned_maxFirst() {
        // act
        final Parallelogram box = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(-1, -2), TEST_PRECISION);

        // assert
        Assertions.assertEquals(1, box.getBoundaryPaths().size());
        final LinePath path = box.getBoundaryPaths().get(0);

        final List<LineConvexSubset> segments = path.getElements();
        Assertions.assertEquals(4, segments.size());

        assertSegment(segments.get(0), Vector2D.of(-1, -2), Vector2D.of(0, -2));
        assertSegment(segments.get(1), Vector2D.of(0, -2), Vector2D.ZERO);
        assertSegment(segments.get(2), Vector2D.ZERO, Vector2D.of(-1, 0));
        assertSegment(segments.get(3), Vector2D.of(-1, 0), Vector2D.of(-1, -2));
    }

    @Test
    void testAxisAligned_illegalArgs() {
        // act/assert

        Assertions.assertThrows(IllegalArgumentException.class, () ->  Parallelogram.axisAligned(Vector2D.of(1, 1), Vector2D.of(1, 3), TEST_PRECISION));
        Assertions.assertThrows(IllegalArgumentException.class, () ->  Parallelogram.axisAligned(Vector2D.of(1, 1), Vector2D.of(3, 1), TEST_PRECISION));
        Assertions.assertThrows(IllegalArgumentException.class, () ->  Parallelogram.axisAligned(Vector2D.of(2, 3), Vector2D.of(2, 3), TEST_PRECISION));
    }

    @Test
    void testBuilder_defaultValues() {
        // arrange
        final Parallelogram.Builder builder = Parallelogram.builder(TEST_PRECISION);

        // act
        final Parallelogram p = builder.build();

        // assert
        Assertions.assertEquals(1, p.getSize(), TEST_EPS);
        Assertions.assertEquals(4, p.getBoundarySize(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.ZERO, p.getCentroid(), TEST_EPS);

        final List<Vector2D> vertices = p.getVertices();
        Assertions.assertEquals(4, vertices.size());
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(-0.5, -0.5), vertices.get(0), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0.5, -0.5), vertices.get(1), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0.5, 0.5), vertices.get(2), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(-0.5, 0.5), vertices.get(3), TEST_EPS);
    }

    @Test
    void testBuilder_rotatedRect_withXDirection() {
        // arrange
        final Parallelogram.Builder builder = Parallelogram.builder(TEST_PRECISION);

        // act
        final Parallelogram p = builder
                .setScale(1, 2)
                .setXDirection(Vector2D.Unit.PLUS_Y)
                .setPosition(Vector2D.of(1, 2))
                .build();

        // assert
        Assertions.assertEquals(2, p.getSize(), TEST_EPS);
        Assertions.assertEquals(6, p.getBoundarySize(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, 2), p.getCentroid(), TEST_EPS);

        final List<Vector2D> vertices = p.getVertices();
        Assertions.assertEquals(4, vertices.size());
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0, 1.5), vertices.get(0), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(2, 1.5), vertices.get(1), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(2, 2.5), vertices.get(2), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0, 2.5), vertices.get(3), TEST_EPS);
    }

    @Test
    void testBuilder_rotatedRect_withYDirection() {
        // arrange
        final Parallelogram.Builder builder = Parallelogram.builder(TEST_PRECISION);

        // act
        final Parallelogram p = builder
                .setScale(Vector2D.of(2, 1))
                .setYDirection(Vector2D.Unit.MINUS_X)
                .setPosition(Vector2D.of(1, 2))
                .build();

        // assert
        Assertions.assertEquals(2, p.getSize(), TEST_EPS);
        Assertions.assertEquals(6, p.getBoundarySize(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, 2), p.getCentroid(), TEST_EPS);

        final List<Vector2D> vertices = p.getVertices();
        Assertions.assertEquals(4, vertices.size());
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0.5, 1), vertices.get(0), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1.5, 1), vertices.get(1), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1.5, 3), vertices.get(2), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0.5, 3), vertices.get(3), TEST_EPS);
    }

    @Test
    void testBuilder_rotatedRect_withRotation() {
        // arrange
        final Parallelogram.Builder builder = Parallelogram.builder(TEST_PRECISION);

        // act
        final Parallelogram p = builder
                .setScale(2)
                .setRotation(Rotation2D.of(0.25 * Math.PI))
                .setPosition(Vector2D.of(1, 2))
                .build();

        // assert
        Assertions.assertEquals(4, p.getSize(), TEST_EPS);
        Assertions.assertEquals(8, p.getBoundarySize(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, 2), p.getCentroid(), TEST_EPS);

        final List<Vector2D> vertices = p.getVertices();
        Assertions.assertEquals(4, vertices.size());

        final double sqrt2 = Math.sqrt(2);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1 - sqrt2, 2), vertices.get(0), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, 2 - sqrt2), vertices.get(1), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1 + sqrt2, 2), vertices.get(2), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, 2 + sqrt2), vertices.get(3), TEST_EPS);
    }

    @Test
    void testToTree() {
        // act
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(1, 4), TEST_PRECISION)
                .toTree();

        // assert
        Assertions.assertFalse(tree.isFull());
        Assertions.assertFalse(tree.isEmpty());

        Assertions.assertEquals(4, tree.getSize(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0.5, 2), tree.getCentroid(), TEST_EPS);
    }

    private static void assertSegment(final LineConvexSubset segment, final Vector2D start, final Vector2D end) {
        EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testUnitSquare_1_oe() {
        final Parallelogram box = Parallelogram.unitSquare(TEST_PRECISION);

        Assertions.assertEquals(1, box.getSize(), TEST_EPS);
    }

    @Test
    void testUnitSquare_2_oe() {
        final Parallelogram box = Parallelogram.unitSquare(TEST_PRECISION);

        Assertions.assertEquals(4, box.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testUnitSquare_4_oe() {
        final Parallelogram box = Parallelogram.unitSquare(TEST_PRECISION);


        final List<Vector2D> vertices = box.getVertices();
        Assertions.assertEquals(4, vertices.size());
    }

    @Test
    void testFromTransformedUnitSquare_1_oe() {
        final AffineTransformMatrix2D t = AffineTransformMatrix2D.createTranslation(Vector2D.of(1, 0))
                .rotate(Math.PI * 0.25)
                .scale(Vector2D.of(2, 1));

        final Parallelogram p = Parallelogram.fromTransformedUnitSquare(t, TEST_PRECISION);

        final double sqrt2 = Math.sqrt(2);
        final double invSqrt2 = 1 / sqrt2;

        Assertions.assertEquals(2, p.getSize(), TEST_EPS);
    }

    @Test
    void testFromTransformedUnitSquare_2_oe() {
        final AffineTransformMatrix2D t = AffineTransformMatrix2D.createTranslation(Vector2D.of(1, 0))
                .rotate(Math.PI * 0.25)
                .scale(Vector2D.of(2, 1));

        final Parallelogram p = Parallelogram.fromTransformedUnitSquare(t, TEST_PRECISION);

        final double sqrt2 = Math.sqrt(2);
        final double invSqrt2 = 1 / sqrt2;

        Assertions.assertEquals(4 * Math.sqrt(2.5), p.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFromTransformedUnitSquare_4_oe() {
        final AffineTransformMatrix2D t = AffineTransformMatrix2D.createTranslation(Vector2D.of(1, 0))
                .rotate(Math.PI * 0.25)
                .scale(Vector2D.of(2, 1));

        final Parallelogram p = Parallelogram.fromTransformedUnitSquare(t, TEST_PRECISION);

        final double sqrt2 = Math.sqrt(2);
        final double invSqrt2 = 1 / sqrt2;


        final List<Vector2D> vertices = p.getVertices();
        Assertions.assertEquals(4, vertices.size());
    }

    @Test
    void testFromTransformedUnitSquare_transformDoesNotPreserveOrientation_1_oe() {
        final AffineTransformMatrix2D t = AffineTransformMatrix2D.createTranslation(Vector2D.of(1, 0))
                .rotate(Math.PI * 0.25)
                .scale(Vector2D.of(-2, 1));

        final Parallelogram p = Parallelogram.fromTransformedUnitSquare(t, TEST_PRECISION);

        final double sqrt2 = Math.sqrt(2);
        final double invSqrt2 = 1 / sqrt2;

        Assertions.assertEquals(2, p.getSize(), TEST_EPS);
    }

    @Test
    void testFromTransformedUnitSquare_transformDoesNotPreserveOrientation_2_oe() {
        final AffineTransformMatrix2D t = AffineTransformMatrix2D.createTranslation(Vector2D.of(1, 0))
                .rotate(Math.PI * 0.25)
                .scale(Vector2D.of(-2, 1));

        final Parallelogram p = Parallelogram.fromTransformedUnitSquare(t, TEST_PRECISION);

        final double sqrt2 = Math.sqrt(2);
        final double invSqrt2 = 1 / sqrt2;

        Assertions.assertEquals(4 * Math.sqrt(2.5), p.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFromTransformedUnitSquare_transformDoesNotPreserveOrientation_4_oe() {
        final AffineTransformMatrix2D t = AffineTransformMatrix2D.createTranslation(Vector2D.of(1, 0))
                .rotate(Math.PI * 0.25)
                .scale(Vector2D.of(-2, 1));

        final Parallelogram p = Parallelogram.fromTransformedUnitSquare(t, TEST_PRECISION);

        final double sqrt2 = Math.sqrt(2);
        final double invSqrt2 = 1 / sqrt2;


        final List<Vector2D> vertices = p.getVertices();
        Assertions.assertEquals(4, vertices.size());
    }

    @Test
    void testFromTransformedUnitSquare_zeroSizeRegion_1_oe() {
        try {
    Parallelogram.fromTransformedUnitSquare(AffineTransformMatrix2D.createScale(Vector2D.of(1e-16, 1)), TEST_PRECISION);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFromTransformedUnitSquare_zeroSizeRegion_2_oe() {

        try {
    Parallelogram.fromTransformedUnitSquare(AffineTransformMatrix2D.createScale(Vector2D.of(1, 1e-16)), TEST_PRECISION);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAxisAligned_minFirst_1_oe() {
        final Parallelogram box = Parallelogram.axisAligned(Vector2D.of(1, 2), Vector2D.of(3, 4), TEST_PRECISION);

        Assertions.assertEquals(1, box.getBoundaryPaths().size());
    }

    @Test
    void testAxisAligned_minFirst_2_oe() {
        final Parallelogram box = Parallelogram.axisAligned(Vector2D.of(1, 2), Vector2D.of(3, 4), TEST_PRECISION);

        final LinePath path = box.getBoundaryPaths().get(0);

        final List<LineConvexSubset> segments = path.getElements();
        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testAxisAligned_maxFirst_1_oe() {
        final Parallelogram box = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(-1, -2), TEST_PRECISION);

        Assertions.assertEquals(1, box.getBoundaryPaths().size());
    }

    @Test
    void testAxisAligned_maxFirst_2_oe() {
        final Parallelogram box = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(-1, -2), TEST_PRECISION);

        final LinePath path = box.getBoundaryPaths().get(0);

        final List<LineConvexSubset> segments = path.getElements();
        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testAxisAligned_illegalArgs_1_oe() {

        try {
    Parallelogram.axisAligned(Vector2D.of(1, 1), Vector2D.of(1, 3), TEST_PRECISION);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAxisAligned_illegalArgs_2_oe() {

        try {
    Parallelogram.axisAligned(Vector2D.of(1, 1), Vector2D.of(3, 1), TEST_PRECISION);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testAxisAligned_illegalArgs_3_oe() {

        try {
    Parallelogram.axisAligned(Vector2D.of(2, 3), Vector2D.of(2, 3), TEST_PRECISION);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testBuilder_defaultValues_1_oe() {
        final Parallelogram.Builder builder = Parallelogram.builder(TEST_PRECISION);

        final Parallelogram p = builder.build();

        Assertions.assertEquals(1, p.getSize(), TEST_EPS);
    }

    @Test
    void testBuilder_defaultValues_2_oe() {
        final Parallelogram.Builder builder = Parallelogram.builder(TEST_PRECISION);

        final Parallelogram p = builder.build();

        Assertions.assertEquals(4, p.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testBuilder_defaultValues_4_oe() {
        final Parallelogram.Builder builder = Parallelogram.builder(TEST_PRECISION);

        final Parallelogram p = builder.build();


        final List<Vector2D> vertices = p.getVertices();
        Assertions.assertEquals(4, vertices.size());
    }

    @Test
    void testBuilder_rotatedRect_withXDirection_1_oe() {
        final Parallelogram.Builder builder = Parallelogram.builder(TEST_PRECISION);

        final Parallelogram p = builder
                .setScale(1, 2)
                .setXDirection(Vector2D.Unit.PLUS_Y)
                .setPosition(Vector2D.of(1, 2))
                .build();

        Assertions.assertEquals(2, p.getSize(), TEST_EPS);
    }

    @Test
    void testBuilder_rotatedRect_withXDirection_2_oe() {
        final Parallelogram.Builder builder = Parallelogram.builder(TEST_PRECISION);

        final Parallelogram p = builder
                .setScale(1, 2)
                .setXDirection(Vector2D.Unit.PLUS_Y)
                .setPosition(Vector2D.of(1, 2))
                .build();

        Assertions.assertEquals(6, p.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testBuilder_rotatedRect_withXDirection_4_oe() {
        final Parallelogram.Builder builder = Parallelogram.builder(TEST_PRECISION);

        final Parallelogram p = builder
                .setScale(1, 2)
                .setXDirection(Vector2D.Unit.PLUS_Y)
                .setPosition(Vector2D.of(1, 2))
                .build();


        final List<Vector2D> vertices = p.getVertices();
        Assertions.assertEquals(4, vertices.size());
    }

    @Test
    void testBuilder_rotatedRect_withYDirection_1_oe() {
        final Parallelogram.Builder builder = Parallelogram.builder(TEST_PRECISION);

        final Parallelogram p = builder
                .setScale(Vector2D.of(2, 1))
                .setYDirection(Vector2D.Unit.MINUS_X)
                .setPosition(Vector2D.of(1, 2))
                .build();

        Assertions.assertEquals(2, p.getSize(), TEST_EPS);
    }

    @Test
    void testBuilder_rotatedRect_withYDirection_2_oe() {
        final Parallelogram.Builder builder = Parallelogram.builder(TEST_PRECISION);

        final Parallelogram p = builder
                .setScale(Vector2D.of(2, 1))
                .setYDirection(Vector2D.Unit.MINUS_X)
                .setPosition(Vector2D.of(1, 2))
                .build();

        Assertions.assertEquals(6, p.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testBuilder_rotatedRect_withYDirection_4_oe() {
        final Parallelogram.Builder builder = Parallelogram.builder(TEST_PRECISION);

        final Parallelogram p = builder
                .setScale(Vector2D.of(2, 1))
                .setYDirection(Vector2D.Unit.MINUS_X)
                .setPosition(Vector2D.of(1, 2))
                .build();


        final List<Vector2D> vertices = p.getVertices();
        Assertions.assertEquals(4, vertices.size());
    }

    @Test
    void testBuilder_rotatedRect_withRotation_1_oe() {
        final Parallelogram.Builder builder = Parallelogram.builder(TEST_PRECISION);

        final Parallelogram p = builder
                .setScale(2)
                .setRotation(Rotation2D.of(0.25 * Math.PI))
                .setPosition(Vector2D.of(1, 2))
                .build();

        Assertions.assertEquals(4, p.getSize(), TEST_EPS);
    }

    @Test
    void testBuilder_rotatedRect_withRotation_2_oe() {
        final Parallelogram.Builder builder = Parallelogram.builder(TEST_PRECISION);

        final Parallelogram p = builder
                .setScale(2)
                .setRotation(Rotation2D.of(0.25 * Math.PI))
                .setPosition(Vector2D.of(1, 2))
                .build();

        Assertions.assertEquals(8, p.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testBuilder_rotatedRect_withRotation_4_oe() {
        final Parallelogram.Builder builder = Parallelogram.builder(TEST_PRECISION);

        final Parallelogram p = builder
                .setScale(2)
                .setRotation(Rotation2D.of(0.25 * Math.PI))
                .setPosition(Vector2D.of(1, 2))
                .build();


        final List<Vector2D> vertices = p.getVertices();
        Assertions.assertEquals(4, vertices.size());
    }

    @Test
    void testToTree_1_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(1, 4), TEST_PRECISION)
                .toTree();

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testToTree_2_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(1, 4), TEST_PRECISION)
                .toTree();

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testToTree_3_oe() {
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(1, 4), TEST_PRECISION)
                .toTree();


        Assertions.assertEquals(4, tree.getSize(), TEST_EPS);
    }

    @Test
    void testAxisAligned_minFirst_3_oe_1_oe() {
        final Parallelogram box = Parallelogram.axisAligned(Vector2D.of(1, 2), Vector2D.of(3, 4), TEST_PRECISION);

        final LinePath path = box.getBoundaryPaths().get(0);

        final List<LineConvexSubset> segments = path.getElements();

                final LineConvexSubset segment0 = segments.get(0);
        final Vector2D start0 = Vector2D.of(1, 2);
        final Vector2D end0 = Vector2D.of(3, 2);
        EuclideanTestUtils.assertCoordinatesEqual(start0, segment0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testAxisAligned_minFirst_3_oe_2_oe() {
        final Parallelogram box = Parallelogram.axisAligned(Vector2D.of(1, 2), Vector2D.of(3, 4), TEST_PRECISION);

        final LinePath path = box.getBoundaryPaths().get(0);

        final List<LineConvexSubset> segments = path.getElements();

                final LineConvexSubset segment0 = segments.get(0);
        final Vector2D start0 = Vector2D.of(1, 2);
        final Vector2D end0 = Vector2D.of(3, 2);
                EuclideanTestUtils.assertCoordinatesEqual(end0, segment0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testAxisAligned_minFirst_4_oe_1_oe() {
        final Parallelogram box = Parallelogram.axisAligned(Vector2D.of(1, 2), Vector2D.of(3, 4), TEST_PRECISION);

        final LinePath path = box.getBoundaryPaths().get(0);

        final List<LineConvexSubset> segments = path.getElements();

                final LineConvexSubset segment0 = segments.get(1);
        final Vector2D start0 = Vector2D.of(3, 2);
        final Vector2D end0 = Vector2D.of(3, 4);
        EuclideanTestUtils.assertCoordinatesEqual(start0, segment0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testAxisAligned_minFirst_4_oe_2_oe() {
        final Parallelogram box = Parallelogram.axisAligned(Vector2D.of(1, 2), Vector2D.of(3, 4), TEST_PRECISION);

        final LinePath path = box.getBoundaryPaths().get(0);

        final List<LineConvexSubset> segments = path.getElements();

                final LineConvexSubset segment0 = segments.get(1);
        final Vector2D start0 = Vector2D.of(3, 2);
        final Vector2D end0 = Vector2D.of(3, 4);
                EuclideanTestUtils.assertCoordinatesEqual(end0, segment0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testAxisAligned_minFirst_5_oe_1_oe() {
        final Parallelogram box = Parallelogram.axisAligned(Vector2D.of(1, 2), Vector2D.of(3, 4), TEST_PRECISION);

        final LinePath path = box.getBoundaryPaths().get(0);

        final List<LineConvexSubset> segments = path.getElements();

                final LineConvexSubset segment0 = segments.get(2);
        final Vector2D start0 = Vector2D.of(3, 4);
        final Vector2D end0 = Vector2D.of(1, 4);
        EuclideanTestUtils.assertCoordinatesEqual(start0, segment0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testAxisAligned_minFirst_5_oe_2_oe() {
        final Parallelogram box = Parallelogram.axisAligned(Vector2D.of(1, 2), Vector2D.of(3, 4), TEST_PRECISION);

        final LinePath path = box.getBoundaryPaths().get(0);

        final List<LineConvexSubset> segments = path.getElements();

                final LineConvexSubset segment0 = segments.get(2);
        final Vector2D start0 = Vector2D.of(3, 4);
        final Vector2D end0 = Vector2D.of(1, 4);
                EuclideanTestUtils.assertCoordinatesEqual(end0, segment0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testAxisAligned_minFirst_6_oe_1_oe() {
        final Parallelogram box = Parallelogram.axisAligned(Vector2D.of(1, 2), Vector2D.of(3, 4), TEST_PRECISION);

        final LinePath path = box.getBoundaryPaths().get(0);

        final List<LineConvexSubset> segments = path.getElements();

                final LineConvexSubset segment0 = segments.get(3);
        final Vector2D start0 = Vector2D.of(1, 4);
        final Vector2D end0 = Vector2D.of(1, 2);
        EuclideanTestUtils.assertCoordinatesEqual(start0, segment0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testAxisAligned_minFirst_6_oe_2_oe() {
        final Parallelogram box = Parallelogram.axisAligned(Vector2D.of(1, 2), Vector2D.of(3, 4), TEST_PRECISION);

        final LinePath path = box.getBoundaryPaths().get(0);

        final List<LineConvexSubset> segments = path.getElements();

                final LineConvexSubset segment0 = segments.get(3);
        final Vector2D start0 = Vector2D.of(1, 4);
        final Vector2D end0 = Vector2D.of(1, 2);
                EuclideanTestUtils.assertCoordinatesEqual(end0, segment0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testAxisAligned_maxFirst_3_oe_1_oe() {
        final Parallelogram box = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(-1, -2), TEST_PRECISION);

        final LinePath path = box.getBoundaryPaths().get(0);

        final List<LineConvexSubset> segments = path.getElements();

                final LineConvexSubset segment0 = segments.get(0);
        final Vector2D start0 = Vector2D.of(-1, -2);
        final Vector2D end0 = Vector2D.of(0, -2);
        EuclideanTestUtils.assertCoordinatesEqual(start0, segment0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testAxisAligned_maxFirst_3_oe_2_oe() {
        final Parallelogram box = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(-1, -2), TEST_PRECISION);

        final LinePath path = box.getBoundaryPaths().get(0);

        final List<LineConvexSubset> segments = path.getElements();

                final LineConvexSubset segment0 = segments.get(0);
        final Vector2D start0 = Vector2D.of(-1, -2);
        final Vector2D end0 = Vector2D.of(0, -2);
                EuclideanTestUtils.assertCoordinatesEqual(end0, segment0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testAxisAligned_maxFirst_4_oe_1_oe() {
        final Parallelogram box = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(-1, -2), TEST_PRECISION);

        final LinePath path = box.getBoundaryPaths().get(0);

        final List<LineConvexSubset> segments = path.getElements();

                final LineConvexSubset segment0 = segments.get(1);
        final Vector2D start0 = Vector2D.of(0, -2);
        final Vector2D end0 = Vector2D.ZERO;
        EuclideanTestUtils.assertCoordinatesEqual(start0, segment0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testAxisAligned_maxFirst_4_oe_2_oe() {
        final Parallelogram box = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(-1, -2), TEST_PRECISION);

        final LinePath path = box.getBoundaryPaths().get(0);

        final List<LineConvexSubset> segments = path.getElements();

                final LineConvexSubset segment0 = segments.get(1);
        final Vector2D start0 = Vector2D.of(0, -2);
        final Vector2D end0 = Vector2D.ZERO;
                EuclideanTestUtils.assertCoordinatesEqual(end0, segment0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testAxisAligned_maxFirst_5_oe_1_oe() {
        final Parallelogram box = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(-1, -2), TEST_PRECISION);

        final LinePath path = box.getBoundaryPaths().get(0);

        final List<LineConvexSubset> segments = path.getElements();

                final LineConvexSubset segment0 = segments.get(2);
        final Vector2D start0 = Vector2D.ZERO;
        final Vector2D end0 = Vector2D.of(-1, 0);
        EuclideanTestUtils.assertCoordinatesEqual(start0, segment0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testAxisAligned_maxFirst_5_oe_2_oe() {
        final Parallelogram box = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(-1, -2), TEST_PRECISION);

        final LinePath path = box.getBoundaryPaths().get(0);

        final List<LineConvexSubset> segments = path.getElements();

                final LineConvexSubset segment0 = segments.get(2);
        final Vector2D start0 = Vector2D.ZERO;
        final Vector2D end0 = Vector2D.of(-1, 0);
                EuclideanTestUtils.assertCoordinatesEqual(end0, segment0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testAxisAligned_maxFirst_6_oe_1_oe() {
        final Parallelogram box = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(-1, -2), TEST_PRECISION);

        final LinePath path = box.getBoundaryPaths().get(0);

        final List<LineConvexSubset> segments = path.getElements();

                final LineConvexSubset segment0 = segments.get(3);
        final Vector2D start0 = Vector2D.of(-1, 0);
        final Vector2D end0 = Vector2D.of(-1, -2);
        EuclideanTestUtils.assertCoordinatesEqual(start0, segment0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testAxisAligned_maxFirst_6_oe_2_oe() {
        final Parallelogram box = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(-1, -2), TEST_PRECISION);

        final LinePath path = box.getBoundaryPaths().get(0);

        final List<LineConvexSubset> segments = path.getElements();

                final LineConvexSubset segment0 = segments.get(3);
        final Vector2D start0 = Vector2D.of(-1, 0);
        final Vector2D end0 = Vector2D.of(-1, -2);
                EuclideanTestUtils.assertCoordinatesEqual(end0, segment0.getEndPoint(), TEST_EPS);
    }

}
