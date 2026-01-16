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
    void testUnitSquare_1_oe() {
        // act
        final Parallelogram box = Parallelogram.unitSquare(TEST_PRECISION);

        // assert
        Assertions.assertEquals(1, box.getSize(), TEST_EPS);
    }

    @Test
    void testUnitSquare_2_oe() {
        // act
        final Parallelogram box = Parallelogram.unitSquare(TEST_PRECISION);

        // assert
        // removed other assertion
        Assertions.assertEquals(4, box.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testUnitSquare_3_oe() {
        // act
        final Parallelogram box = Parallelogram.unitSquare(TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.ZERO, box.getCentroid(), TEST_EPS);

        final List<Vector2D> vertices = box.getVertices();
        Assertions.assertEquals(4, vertices.size());
    }

    @Test
    void testFromTransformedUnitSquare_1_oe() {
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
    }

    @Test
    void testFromTransformedUnitSquare_2_oe() {
        // arrange
        final AffineTransformMatrix2D t = AffineTransformMatrix2D.createTranslation(Vector2D.of(1, 0))
                .rotate(Math.PI * 0.25)
                .scale(Vector2D.of(2, 1));

        // act
        final Parallelogram p = Parallelogram.fromTransformedUnitSquare(t, TEST_PRECISION);

        // assert
        final double sqrt2 = Math.sqrt(2);
        final double invSqrt2 = 1 / sqrt2;

        // removed other assertion
        Assertions.assertEquals(4 * Math.sqrt(2.5), p.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFromTransformedUnitSquare_3_oe() {
        // arrange
        final AffineTransformMatrix2D t = AffineTransformMatrix2D.createTranslation(Vector2D.of(1, 0))
                .rotate(Math.PI * 0.25)
                .scale(Vector2D.of(2, 1));

        // act
        final Parallelogram p = Parallelogram.fromTransformedUnitSquare(t, TEST_PRECISION);

        // assert
        final double sqrt2 = Math.sqrt(2);
        final double invSqrt2 = 1 / sqrt2;

        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(2 * invSqrt2, invSqrt2), p.getCentroid(), TEST_EPS);

        final List<Vector2D> vertices = p.getVertices();
        Assertions.assertEquals(4, vertices.size());
    }

    @Test
    void testFromTransformedUnitSquare_transformDoesNotPreserveOrientation_1_oe() {
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
    }

    @Test
    void testFromTransformedUnitSquare_transformDoesNotPreserveOrientation_2_oe() {
        // arrange
        final AffineTransformMatrix2D t = AffineTransformMatrix2D.createTranslation(Vector2D.of(1, 0))
                .rotate(Math.PI * 0.25)
                .scale(Vector2D.of(-2, 1));

        // act
        final Parallelogram p = Parallelogram.fromTransformedUnitSquare(t, TEST_PRECISION);

        // assert
        final double sqrt2 = Math.sqrt(2);
        final double invSqrt2 = 1 / sqrt2;

        // removed other assertion
        Assertions.assertEquals(4 * Math.sqrt(2.5), p.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFromTransformedUnitSquare_transformDoesNotPreserveOrientation_3_oe() {
        // arrange
        final AffineTransformMatrix2D t = AffineTransformMatrix2D.createTranslation(Vector2D.of(1, 0))
                .rotate(Math.PI * 0.25)
                .scale(Vector2D.of(-2, 1));

        // act
        final Parallelogram p = Parallelogram.fromTransformedUnitSquare(t, TEST_PRECISION);

        // assert
        final double sqrt2 = Math.sqrt(2);
        final double invSqrt2 = 1 / sqrt2;

        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(-2 * invSqrt2, invSqrt2), p.getCentroid(), TEST_EPS);

        final List<Vector2D> vertices = p.getVertices();
        Assertions.assertEquals(4, vertices.size());
    }

    @Test
    void testFromTransformedUnitSquare_zeroSizeRegion_1_oe() {
        // act/assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->  Parallelogram.fromTransformedUnitSquare(AffineTransformMatrix2D.createScale(Vector2D.of(1e-16, 1)), TEST_PRECISION));
    }

    @Test
    void testFromTransformedUnitSquare_zeroSizeRegion_2_oe() {
        // act/assert
        // removed other assertion

        Assertions.assertThrows(IllegalArgumentException.class, () ->  Parallelogram.fromTransformedUnitSquare(AffineTransformMatrix2D.createScale(Vector2D.of(1, 1e-16)), TEST_PRECISION));
    }

    @Test
    void testAxisAligned_minFirst_1_oe() {
        // act
        final Parallelogram box = Parallelogram.axisAligned(Vector2D.of(1, 2), Vector2D.of(3, 4), TEST_PRECISION);

        // assert
        Assertions.assertEquals(1, box.getBoundaryPaths().size());
    }

    @Test
    void testAxisAligned_minFirst_2_oe() {
        // act
        final Parallelogram box = Parallelogram.axisAligned(Vector2D.of(1, 2), Vector2D.of(3, 4), TEST_PRECISION);

        // assert
        // removed other assertion
        final LinePath path = box.getBoundaryPaths().get(0);

        final List<LineConvexSubset> segments = path.getElements();
        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testAxisAligned_maxFirst_1_oe() {
        // act
        final Parallelogram box = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(-1, -2), TEST_PRECISION);

        // assert
        Assertions.assertEquals(1, box.getBoundaryPaths().size());
    }

    @Test
    void testAxisAligned_maxFirst_2_oe() {
        // act
        final Parallelogram box = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(-1, -2), TEST_PRECISION);

        // assert
        // removed other assertion
        final LinePath path = box.getBoundaryPaths().get(0);

        final List<LineConvexSubset> segments = path.getElements();
        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testAxisAligned_illegalArgs_1_oe() {
        // act/assert

        Assertions.assertThrows(IllegalArgumentException.class, () ->  Parallelogram.axisAligned(Vector2D.of(1, 1), Vector2D.of(1, 3), TEST_PRECISION));
    }

    @Test
    void testAxisAligned_illegalArgs_2_oe() {
        // act/assert

        // removed other assertion
        Assertions.assertThrows(IllegalArgumentException.class, () ->  Parallelogram.axisAligned(Vector2D.of(1, 1), Vector2D.of(3, 1), TEST_PRECISION));
    }

    @Test
    void testAxisAligned_illegalArgs_3_oe() {
        // act/assert

        // removed other assertion
        // removed other assertion
        Assertions.assertThrows(IllegalArgumentException.class, () ->  Parallelogram.axisAligned(Vector2D.of(2, 3), Vector2D.of(2, 3), TEST_PRECISION));
    }

    @Test
    void testBuilder_defaultValues_1_oe() {
        // arrange
        final Parallelogram.Builder builder = Parallelogram.builder(TEST_PRECISION);

        // act
        final Parallelogram p = builder.build();

        // assert
        Assertions.assertEquals(1, p.getSize(), TEST_EPS);
    }

    @Test
    void testBuilder_defaultValues_2_oe() {
        // arrange
        final Parallelogram.Builder builder = Parallelogram.builder(TEST_PRECISION);

        // act
        final Parallelogram p = builder.build();

        // assert
        // removed other assertion
        Assertions.assertEquals(4, p.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testBuilder_defaultValues_3_oe() {
        // arrange
        final Parallelogram.Builder builder = Parallelogram.builder(TEST_PRECISION);

        // act
        final Parallelogram p = builder.build();

        // assert
        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.ZERO, p.getCentroid(), TEST_EPS);

        final List<Vector2D> vertices = p.getVertices();
        Assertions.assertEquals(4, vertices.size());
    }

    @Test
    void testBuilder_rotatedRect_withXDirection_1_oe() {
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
    }

    @Test
    void testBuilder_rotatedRect_withXDirection_2_oe() {
        // arrange
        final Parallelogram.Builder builder = Parallelogram.builder(TEST_PRECISION);

        // act
        final Parallelogram p = builder
                .setScale(1, 2)
                .setXDirection(Vector2D.Unit.PLUS_Y)
                .setPosition(Vector2D.of(1, 2))
                .build();

        // assert
        // removed other assertion
        Assertions.assertEquals(6, p.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testBuilder_rotatedRect_withXDirection_3_oe() {
        // arrange
        final Parallelogram.Builder builder = Parallelogram.builder(TEST_PRECISION);

        // act
        final Parallelogram p = builder
                .setScale(1, 2)
                .setXDirection(Vector2D.Unit.PLUS_Y)
                .setPosition(Vector2D.of(1, 2))
                .build();

        // assert
        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, 2), p.getCentroid(), TEST_EPS);

        final List<Vector2D> vertices = p.getVertices();
        Assertions.assertEquals(4, vertices.size());
    }

    @Test
    void testBuilder_rotatedRect_withYDirection_1_oe() {
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
    }

    @Test
    void testBuilder_rotatedRect_withYDirection_2_oe() {
        // arrange
        final Parallelogram.Builder builder = Parallelogram.builder(TEST_PRECISION);

        // act
        final Parallelogram p = builder
                .setScale(Vector2D.of(2, 1))
                .setYDirection(Vector2D.Unit.MINUS_X)
                .setPosition(Vector2D.of(1, 2))
                .build();

        // assert
        // removed other assertion
        Assertions.assertEquals(6, p.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testBuilder_rotatedRect_withYDirection_3_oe() {
        // arrange
        final Parallelogram.Builder builder = Parallelogram.builder(TEST_PRECISION);

        // act
        final Parallelogram p = builder
                .setScale(Vector2D.of(2, 1))
                .setYDirection(Vector2D.Unit.MINUS_X)
                .setPosition(Vector2D.of(1, 2))
                .build();

        // assert
        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, 2), p.getCentroid(), TEST_EPS);

        final List<Vector2D> vertices = p.getVertices();
        Assertions.assertEquals(4, vertices.size());
    }

    @Test
    void testBuilder_rotatedRect_withRotation_1_oe() {
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
    }

    @Test
    void testBuilder_rotatedRect_withRotation_2_oe() {
        // arrange
        final Parallelogram.Builder builder = Parallelogram.builder(TEST_PRECISION);

        // act
        final Parallelogram p = builder
                .setScale(2)
                .setRotation(Rotation2D.of(0.25 * Math.PI))
                .setPosition(Vector2D.of(1, 2))
                .build();

        // assert
        // removed other assertion
        Assertions.assertEquals(8, p.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testBuilder_rotatedRect_withRotation_3_oe() {
        // arrange
        final Parallelogram.Builder builder = Parallelogram.builder(TEST_PRECISION);

        // act
        final Parallelogram p = builder
                .setScale(2)
                .setRotation(Rotation2D.of(0.25 * Math.PI))
                .setPosition(Vector2D.of(1, 2))
                .build();

        // assert
        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, 2), p.getCentroid(), TEST_EPS);

        final List<Vector2D> vertices = p.getVertices();
        Assertions.assertEquals(4, vertices.size());
    }

    @Test
    void testToTree_1_oe() {
        // act
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(1, 4), TEST_PRECISION)
                .toTree();

        // assert
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testToTree_2_oe() {
        // act
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(1, 4), TEST_PRECISION)
                .toTree();

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testToTree_3_oe() {
        // act
        final RegionBSPTree2D tree = Parallelogram.axisAligned(Vector2D.ZERO, Vector2D.of(1, 4), TEST_PRECISION)
                .toTree();

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(4, tree.getSize(), TEST_EPS);
    }

}
