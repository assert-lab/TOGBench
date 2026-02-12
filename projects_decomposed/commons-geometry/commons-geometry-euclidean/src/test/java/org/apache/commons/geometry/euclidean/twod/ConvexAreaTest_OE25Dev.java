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
package org.apache.commons.geometry.euclidean.twod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.core.RegionLocation;
import org.apache.commons.geometry.core.partitioning.Split;
import org.apache.commons.geometry.core.partitioning.SplitLocation;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.twod.path.LinePath;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConvexAreaTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    @Test
    void testGetBounds_square() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(-1, -1), 2, 1));

        // act
        final Bounds2D bounds = area.getBounds();

        // assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(-1, -1), bounds.getMin(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, 0), bounds.getMax(), TEST_EPS);
    }

    @Test
    void testProject_halfSpace() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(
                Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION));

        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0, 1), area.project(Vector2D.of(1, 1)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0, 2), area.project(Vector2D.of(-2, 2)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0, -3), area.project(Vector2D.of(1, -3)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0, -4), area.project(Vector2D.of(-2, -4)), TEST_EPS);
    }

    @Test
    void testProject_square() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.ZERO, 1, 1));

        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, 1), area.project(Vector2D.of(1, 1)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1, 1), area.project(Vector2D.of(2, 2)), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.ZERO, area.project(Vector2D.ZERO), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.ZERO, area.project(Vector2D.of(-1, -1)), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0, 0.5), area.project(Vector2D.of(0.1, 0.5)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0.2, 1), area.project(Vector2D.of(0.2, 0.9)), TEST_EPS);

        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0.5, 0), area.project(Vector2D.of(0.5, 0.5)), TEST_EPS);
    }

    @Test
    void testTrim_halfSpace() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION));
        final LineConvexSubset segment = Lines.fromPoints(Vector2D.Unit.MINUS_Y, Vector2D.Unit.PLUS_Y, TEST_PRECISION).span();

        // act
        final LineConvexSubset trimmed = area.trim(segment);

        // assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.ZERO, trimmed.getStartPoint(), TEST_EPS);
        GeometryTestUtils.assertPositiveInfinity(trimmed.getSubspaceEnd());
    }

    @Test
    void testTrim_square() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.ZERO, 1, 1));
        final LineConvexSubset segment = Lines.fromPoints(Vector2D.of(0.5, 0), Vector2D.of(0.5, 1), TEST_PRECISION).span();

        // act
        final LineConvexSubset trimmed = area.trim(segment);

        // assert
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0.5, 0), trimmed.getStartPoint(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0.5, 1), trimmed.getEndPoint(), TEST_EPS);
    }

    @Test
    void testLinecast_full() {
        // arrange
        final ConvexArea area = ConvexArea.full();

        // act/assert
        LinecastChecker2D.with(area)
            .expectNothing()
            .whenGiven(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));

        LinecastChecker2D.with(area)
            .expectNothing()
            .whenGiven(Lines.segmentFromPoints(Vector2D.Unit.MINUS_X, Vector2D.Unit.PLUS_X, TEST_PRECISION));
    }

    @Test
    void testLinecast() {
        // arrange
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO, Vector2D.of(1, 0),
                    Vector2D.of(1, 1), Vector2D.of(0, 1)
                ), TEST_PRECISION);

        // act/assert
        LinecastChecker2D.with(area)
            .expectNothing()
            .whenGiven(Lines.fromPoints(Vector2D.of(0, 5), Vector2D.of(1, 6), TEST_PRECISION));

        LinecastChecker2D.with(area)
            .expect(Vector2D.ZERO, Vector2D.Unit.MINUS_X)
            .and(Vector2D.ZERO, Vector2D.Unit.MINUS_Y)
            .and(Vector2D.of(1, 1), Vector2D.Unit.PLUS_Y)
            .and(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X)
            .whenGiven(Lines.fromPoints(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION));

        LinecastChecker2D.with(area)
            .expect(Vector2D.of(1, 1), Vector2D.Unit.PLUS_Y)
            .and(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X)
            .whenGiven(Lines.segmentFromPoints(Vector2D.of(0.5, 0.5), Vector2D.of(1, 1), TEST_PRECISION));
    }

    @Test
    void testConvexPolygonFromVertices_notEnoughUniqueVertices() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final Pattern unclosedPattern = Pattern.compile("Cannot construct convex polygon from unclosed path.*");
        final Pattern notEnoughElementsPattern =
                Pattern.compile("Cannot construct convex polygon from path with less than 3 elements.*");
        final Pattern nonConvexPattern = Pattern.compile("Cannot construct convex polygon from non-convex path.*");

        final Pattern singleVertexPattern =
                Pattern.compile("Unable to create line path; only a single unique vertex provided.*");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            ConvexArea.convexPolygonFromVertices(Collections.emptyList(), precision);
        }, IllegalArgumentException.class, unclosedPattern);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            ConvexArea.convexPolygonFromVertices(Collections.singletonList(Vector2D.ZERO), precision);
        }, IllegalStateException.class, singleVertexPattern);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            ConvexArea.convexPolygonFromVertices(Arrays.asList(Vector2D.ZERO, Vector2D.of(1e-4, 1e-4)), precision);
        }, IllegalStateException.class, singleVertexPattern);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            ConvexArea.convexPolygonFromVertices(Arrays.asList(Vector2D.ZERO, Vector2D.Unit.PLUS_X), precision);
        }, IllegalArgumentException.class, notEnoughElementsPattern);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            ConvexArea.convexPolygonFromVertices(
                    Arrays.asList(Vector2D.ZERO, Vector2D.Unit.PLUS_X, Vector2D.of(1, 1e-4)), precision);
        }, IllegalArgumentException.class, notEnoughElementsPattern);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            ConvexArea.convexPolygonFromVertices(
                    Arrays.asList(Vector2D.ZERO, Vector2D.Unit.PLUS_X, Vector2D.of(1, -1)), precision);
        }, IllegalArgumentException.class, nonConvexPattern);
    }

    @Test
    void testConvexPolygonFromVertices_notConvex() {
        // arrange
        final Pattern msgPattern = Pattern.compile("Cannot construct convex polygon from non-convex path.*");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            ConvexArea.convexPolygonFromVertices(Arrays.asList(
                        Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(2, 0)
                    ), TEST_PRECISION);
        }, IllegalArgumentException.class, msgPattern);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            ConvexArea.convexPolygonFromVertices(Arrays.asList(
                        Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(1, -1)
                    ), TEST_PRECISION);
        }, IllegalArgumentException.class, msgPattern);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            ConvexArea.convexPolygonFromVertices(
                    Arrays.asList(
                            Vector2D.ZERO,
                            Vector2D.Unit.PLUS_Y,
                            Vector2D.of(1, 1),
                            Vector2D.Unit.PLUS_X
                    ), TEST_PRECISION);
        }, IllegalArgumentException.class, msgPattern);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            ConvexArea.convexPolygonFromVertices(Arrays.asList(
                        Vector2D.ZERO, Vector2D.of(2, 0),
                        Vector2D.of(2, 2), Vector2D.of(1, 1),
                        Vector2D.of(1.5, 1)
                    ), TEST_PRECISION);
        }, IllegalArgumentException.class, msgPattern);
    }

    @Test
    void testConvexPolygonFromPath_invalidPaths() {
        // arrange
        final Pattern unclosedPattern = Pattern.compile("Cannot construct convex polygon from unclosed path.*");
        final Pattern notEnoughElementsPattern =
                Pattern.compile("Cannot construct convex polygon from path with less than 3 elements.*");
        final Pattern nonConvexPattern = Pattern.compile("Cannot construct convex polygon from non-convex path.*");

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            ConvexArea.convexPolygonFromPath(LinePath.empty());
        }, IllegalArgumentException.class, unclosedPattern);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            ConvexArea.convexPolygonFromPath(LinePath.fromVertices(
                    Arrays.asList(Vector2D.ZERO, Vector2D.Unit.PLUS_X), TEST_PRECISION));
        }, IllegalArgumentException.class, unclosedPattern);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            ConvexArea.convexPolygonFromPath(LinePath.fromVertices(
                    Arrays.asList(Vector2D.ZERO, Vector2D.Unit.PLUS_X, Vector2D.ZERO), TEST_PRECISION));
        }, IllegalArgumentException.class, notEnoughElementsPattern);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            ConvexArea.convexPolygonFromPath(LinePath.fromVertexLoop(
                    Arrays.asList(
                            Vector2D.ZERO,
                            Vector2D.Unit.PLUS_Y,
                            Vector2D.of(1, 1),
                            Vector2D.Unit.PLUS_X
                    ), TEST_PRECISION));
        }, IllegalArgumentException.class, nonConvexPattern);
    }

    private static List<Line> createSquareBoundingLines(final Vector2D lowerLeft, final double width, final double height) {
        final Vector2D lowerRight = Vector2D.of(lowerLeft.getX() + width, lowerLeft.getY());
        final Vector2D upperRight = Vector2D.of(lowerLeft.getX() + width, lowerLeft.getY() + height);
        final Vector2D upperLeft = Vector2D.of(lowerLeft.getX(), lowerLeft.getY() + height);

        return Arrays.asList(
                    Lines.fromPoints(lowerLeft, lowerRight, TEST_PRECISION),
                    Lines.fromPoints(upperRight, upperLeft, TEST_PRECISION),
                    Lines.fromPoints(lowerRight, upperRight, TEST_PRECISION),
                    Lines.fromPoints(upperLeft, lowerLeft, TEST_PRECISION)
                );
    }

    @Test
    void testFromBounds_duplicateLines_differentOrientation_1_oe() {
        // arrange
        final Line a = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.of(0, 1), Math.PI, TEST_PRECISION);
        final Line c = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);

        // act/assert
        try {
    ConvexArea.fromBounds(a, b, c);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFromBounds_boundsDoNotProduceAConvexRegion_1_oe() {
        // act/assert
        try {
    ConvexArea.fromBounds(Arrays.asList( Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION), Lines.fromPointAndAngle(Vector2D.of(0, -1), Math.PI, TEST_PRECISION), Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION) ));
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
