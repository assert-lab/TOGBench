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
    void testFull_1_oe() {
        // act
        final ConvexArea area = ConvexArea.full();

        // assert
        Assertions.assertTrue(area.isFull());
    }

    @Test
    void testFull_2_oe() {
        // act
        final ConvexArea area = ConvexArea.full();

        // assert
        // removed other assertion
        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFull_3_oe() {
        // act
        final ConvexArea area = ConvexArea.full();

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0.0, area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFull_4_oe() {
        // act
        final ConvexArea area = ConvexArea.full();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        GeometryTestUtils.assertPositiveInfinity(area.getSize());
        Assertions.assertNull(area.getCentroid());
    }

    @Test
    void testFull_5_oe() {
        // act
        final ConvexArea area = ConvexArea.full();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        GeometryTestUtils.assertPositiveInfinity(area.getSize());
        // removed other assertion
        Assertions.assertNull(area.getBounds());
    }

    @Test
    void testBoundaryStream_1_oe() {
        // arrange
        final Line line = Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION);
        final ConvexArea area = ConvexArea.fromBounds(line);

        // act
        final List<LineConvexSubset> segments = area.boundaryStream().collect(Collectors.toList());

        // assert
        Assertions.assertEquals(1, segments.size());
    }

    @Test
    void testBoundaryStream_2_oe() {
        // arrange
        final Line line = Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION);
        final ConvexArea area = ConvexArea.fromBounds(line);

        // act
        final List<LineConvexSubset> segments = area.boundaryStream().collect(Collectors.toList());

        // assert
        // removed other assertion
        final LineConvexSubset segment = segments.get(0);
        Assertions.assertNull(segment.getStartPoint());
    }

    @Test
    void testBoundaryStream_3_oe() {
        // arrange
        final Line line = Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION);
        final ConvexArea area = ConvexArea.fromBounds(line);

        // act
        final List<LineConvexSubset> segments = area.boundaryStream().collect(Collectors.toList());

        // assert
        // removed other assertion
        final LineConvexSubset segment = segments.get(0);
        // removed other assertion
        Assertions.assertNull(segment.getEndPoint());
    }

    @Test
    void testBoundaryStream_4_oe() {
        // arrange
        final Line line = Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION);
        final ConvexArea area = ConvexArea.fromBounds(line);

        // act
        final List<LineConvexSubset> segments = area.boundaryStream().collect(Collectors.toList());

        // assert
        // removed other assertion
        final LineConvexSubset segment = segments.get(0);
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(line, segment.getLine());
    }

    @Test
    void testBoundaryStream_full_1_oe() {
        // arrange
        final ConvexArea area = ConvexArea.full();

        // act
        final List<LineConvexSubset> segments = area.boundaryStream().collect(Collectors.toList());

        // assert
        Assertions.assertEquals(0, segments.size());
    }

    @Test
    void testToList_1_oe() {
        // arrange
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(0, 1)
                ), TEST_PRECISION);

        // act
        final BoundaryList2D list = area.toList();

        // assert
        Assertions.assertEquals(3, list.count());
    }

    @Test
    void testToList_2_oe() {
        // arrange
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(0, 1)
                ), TEST_PRECISION);

        // act
        final BoundaryList2D list = area.toList();

        // assert
        // removed other assertion
        Assertions.assertEquals(area.getBoundaries(), list.getBoundaries());
    }

    @Test
    void testToList_full_1_oe() {
        // arrange
        final ConvexArea area = ConvexArea.full();

        // act
        final BoundaryList2D list = area.toList();

        // assert
        Assertions.assertEquals(0, list.count());
    }

    @Test
    void testToTree_1_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(
                    Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(1, 0), Angle.PI_OVER_TWO, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(1, 1), Math.PI, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(0, 1), -Angle.PI_OVER_TWO, TEST_PRECISION)
                );

        // act
        final RegionBSPTree2D tree = area.toTree();

        // assert
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testToTree_2_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(
                    Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(1, 0), Angle.PI_OVER_TWO, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(1, 1), Math.PI, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(0, 1), -Angle.PI_OVER_TWO, TEST_PRECISION)
                );

        // act
        final RegionBSPTree2D tree = area.toTree();

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testToTree_3_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(
                    Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(1, 0), Angle.PI_OVER_TWO, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(1, 1), Math.PI, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(0, 1), -Angle.PI_OVER_TWO, TEST_PRECISION)
                );

        // act
        final RegionBSPTree2D tree = area.toTree();

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1, tree.getSize(), TEST_EPS);
    }

    @Test
    void testToTree_full_1_oe() {
        // arrange
        final ConvexArea area = ConvexArea.full();

        // act
        final RegionBSPTree2D tree = area.toTree();

        // assert
        Assertions.assertTrue(tree.isFull());
    }

    @Test
    void testToTree_full_2_oe() {
        // arrange
        final ConvexArea area = ConvexArea.full();

        // act
        final RegionBSPTree2D tree = area.toTree();

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testTransform_full_1_oe() {
        // arrange
        final AffineTransformMatrix2D transform = AffineTransformMatrix2D.createScale(3);
        final ConvexArea area = ConvexArea.full();

        // act
        final ConvexArea transformed = area.transform(transform);

        // assert
        Assertions.assertSame(area, transformed);
    }

    @Test
    void testTransform_infinite_1_oe() {
        // arrange
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D
                .createRotation(Vector2D.of(0, 1), Angle.PI_OVER_TWO)
                .scale(Vector2D.of(3, 2));

        final ConvexArea area = ConvexArea.fromBounds(
                Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION),
                Lines.fromPointAndAngle(Vector2D.ZERO, -0.25 * Math.PI, TEST_PRECISION));

        // act
        final ConvexArea transformed = area.transform(mat);

        // assert
        Assertions.assertNotSame(area, transformed);
    }

    @Test
    void testTransform_infinite_2_oe() {
        // arrange
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D
                .createRotation(Vector2D.of(0, 1), Angle.PI_OVER_TWO)
                .scale(Vector2D.of(3, 2));

        final ConvexArea area = ConvexArea.fromBounds(
                Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION),
                Lines.fromPointAndAngle(Vector2D.ZERO, -0.25 * Math.PI, TEST_PRECISION));

        // act
        final ConvexArea transformed = area.transform(mat);

        // assert
        // removed other assertion

        final List<LinePath> paths = transformed.getBoundaryPaths();
        Assertions.assertEquals(1, paths.size());
    }

    @Test
    void testTransform_infinite_3_oe() {
        // arrange
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D
                .createRotation(Vector2D.of(0, 1), Angle.PI_OVER_TWO)
                .scale(Vector2D.of(3, 2));

        final ConvexArea area = ConvexArea.fromBounds(
                Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION),
                Lines.fromPointAndAngle(Vector2D.ZERO, -0.25 * Math.PI, TEST_PRECISION));

        // act
        final ConvexArea transformed = area.transform(mat);

        // assert
        // removed other assertion

        final List<LinePath> paths = transformed.getBoundaryPaths();
        // removed other assertion

        final List<LineConvexSubset> segments = paths.get(0).getElements();
        Assertions.assertEquals(2, segments.size());
    }

    @Test
    void testTransform_infinite_4_oe() {
        // arrange
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D
                .createRotation(Vector2D.of(0, 1), Angle.PI_OVER_TWO)
                .scale(Vector2D.of(3, 2));

        final ConvexArea area = ConvexArea.fromBounds(
                Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION),
                Lines.fromPointAndAngle(Vector2D.ZERO, -0.25 * Math.PI, TEST_PRECISION));

        // act
        final ConvexArea transformed = area.transform(mat);

        // assert
        // removed other assertion

        final List<LinePath> paths = transformed.getBoundaryPaths();
        // removed other assertion

        final List<LineConvexSubset> segments = paths.get(0).getElements();
        // removed other assertion

        final LineConvexSubset firstSegment = segments.get(0);
        Assertions.assertNull(firstSegment.getStartPoint());
    }

    @Test
    void testTransform_infinite_5_oe() {
        // arrange
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D
                .createRotation(Vector2D.of(0, 1), Angle.PI_OVER_TWO)
                .scale(Vector2D.of(3, 2));

        final ConvexArea area = ConvexArea.fromBounds(
                Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION),
                Lines.fromPointAndAngle(Vector2D.ZERO, -0.25 * Math.PI, TEST_PRECISION));

        // act
        final ConvexArea transformed = area.transform(mat);

        // assert
        // removed other assertion

        final List<LinePath> paths = transformed.getBoundaryPaths();
        // removed other assertion

        final List<LineConvexSubset> segments = paths.get(0).getElements();
        // removed other assertion

        final LineConvexSubset firstSegment = segments.get(0);
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(3, 2), firstSegment.getEndPoint(), TEST_EPS);
        Assertions.assertEquals(Math.atan2(2, 3), firstSegment.getLine().getAngle(), TEST_EPS);
    }

    @Test
    void testTransform_infinite_6_oe() {
        // arrange
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D
                .createRotation(Vector2D.of(0, 1), Angle.PI_OVER_TWO)
                .scale(Vector2D.of(3, 2));

        final ConvexArea area = ConvexArea.fromBounds(
                Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION),
                Lines.fromPointAndAngle(Vector2D.ZERO, -0.25 * Math.PI, TEST_PRECISION));

        // act
        final ConvexArea transformed = area.transform(mat);

        // assert
        // removed other assertion

        final List<LinePath> paths = transformed.getBoundaryPaths();
        // removed other assertion

        final List<LineConvexSubset> segments = paths.get(0).getElements();
        // removed other assertion

        final LineConvexSubset firstSegment = segments.get(0);
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(3, 2), firstSegment.getEndPoint(), TEST_EPS);
        // removed other assertion

        final LineConvexSubset secondSegment = segments.get(1);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(3, 2), secondSegment.getStartPoint(), TEST_EPS);
        Assertions.assertNull(secondSegment.getEndPoint());
    }

    @Test
    void testTransform_infinite_7_oe() {
        // arrange
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D
                .createRotation(Vector2D.of(0, 1), Angle.PI_OVER_TWO)
                .scale(Vector2D.of(3, 2));

        final ConvexArea area = ConvexArea.fromBounds(
                Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION),
                Lines.fromPointAndAngle(Vector2D.ZERO, -0.25 * Math.PI, TEST_PRECISION));

        // act
        final ConvexArea transformed = area.transform(mat);

        // assert
        // removed other assertion

        final List<LinePath> paths = transformed.getBoundaryPaths();
        // removed other assertion

        final List<LineConvexSubset> segments = paths.get(0).getElements();
        // removed other assertion

        final LineConvexSubset firstSegment = segments.get(0);
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(3, 2), firstSegment.getEndPoint(), TEST_EPS);
        // removed other assertion

        final LineConvexSubset secondSegment = segments.get(1);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(3, 2), secondSegment.getStartPoint(), TEST_EPS);
        // removed other assertion
        Assertions.assertEquals(Math.atan2(2, -3), secondSegment.getLine().getAngle(), TEST_EPS);
    }

    @Test
    void testTransform_finite_1_oe() {
        // arrange
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D.createScale(Vector2D.of(1, 2));

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1),
                    Vector2D.of(2, 2), Vector2D.of(1, 2)
                ), TEST_PRECISION);

        // act
        final ConvexArea transformed = area.transform(mat);

        // assert
        Assertions.assertNotSame(area, transformed);
    }

    @Test
    void testTransform_finite_2_oe() {
        // arrange
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D.createScale(Vector2D.of(1, 2));

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1),
                    Vector2D.of(2, 2), Vector2D.of(1, 2)
                ), TEST_PRECISION);

        // act
        final ConvexArea transformed = area.transform(mat);

        // assert
        // removed other assertion

        final List<LineConvexSubset> segments = transformed.getBoundaries();
        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testTransform_finite_3_oe() {
        // arrange
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D.createScale(Vector2D.of(1, 2));

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1),
                    Vector2D.of(2, 2), Vector2D.of(1, 2)
                ), TEST_PRECISION);

        // act
        final ConvexArea transformed = area.transform(mat);

        // assert
        // removed other assertion

        final List<LineConvexSubset> segments = transformed.getBoundaries();
        // removed other assertion

        Assertions.assertEquals(2, transformed.getSize(), TEST_EPS);
    }

    @Test
    void testTransform_finite_4_oe() {
        // arrange
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D.createScale(Vector2D.of(1, 2));

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1),
                    Vector2D.of(2, 2), Vector2D.of(1, 2)
                ), TEST_PRECISION);

        // act
        final ConvexArea transformed = area.transform(mat);

        // assert
        // removed other assertion

        final List<LineConvexSubset> segments = transformed.getBoundaries();
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(6, transformed.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testTransform_finite_withSingleReflection_1_oe() {
        // arrange
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D.createScale(Vector2D.of(-1, 2));

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1),
                    Vector2D.of(2, 2), Vector2D.of(1, 2)
                ), TEST_PRECISION);

        // act
        final ConvexArea transformed = area.transform(mat);

        // assert
        Assertions.assertNotSame(area, transformed);
    }

    @Test
    void testTransform_finite_withSingleReflection_2_oe() {
        // arrange
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D.createScale(Vector2D.of(-1, 2));

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1),
                    Vector2D.of(2, 2), Vector2D.of(1, 2)
                ), TEST_PRECISION);

        // act
        final ConvexArea transformed = area.transform(mat);

        // assert
        // removed other assertion

        final List<LineConvexSubset> segments = transformed.getBoundaries();
        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testTransform_finite_withSingleReflection_3_oe() {
        // arrange
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D.createScale(Vector2D.of(-1, 2));

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1),
                    Vector2D.of(2, 2), Vector2D.of(1, 2)
                ), TEST_PRECISION);

        // act
        final ConvexArea transformed = area.transform(mat);

        // assert
        // removed other assertion

        final List<LineConvexSubset> segments = transformed.getBoundaries();
        // removed other assertion

        Assertions.assertEquals(2, transformed.getSize(), TEST_EPS);
    }

    @Test
    void testTransform_finite_withSingleReflection_4_oe() {
        // arrange
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D.createScale(Vector2D.of(-1, 2));

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1),
                    Vector2D.of(2, 2), Vector2D.of(1, 2)
                ), TEST_PRECISION);

        // act
        final ConvexArea transformed = area.transform(mat);

        // assert
        // removed other assertion

        final List<LineConvexSubset> segments = transformed.getBoundaries();
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(6, transformed.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testTransform_finite_withDoubleReflection_1_oe() {
        // arrange
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D.createScale(Vector2D.of(-1, -2));

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1),
                    Vector2D.of(2, 2), Vector2D.of(1, 2)
                ), TEST_PRECISION);

        // act
        final ConvexArea transformed = area.transform(mat);

        // assert
        Assertions.assertNotSame(area, transformed);
    }

    @Test
    void testTransform_finite_withDoubleReflection_2_oe() {
        // arrange
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D.createScale(Vector2D.of(-1, -2));

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1),
                    Vector2D.of(2, 2), Vector2D.of(1, 2)
                ), TEST_PRECISION);

        // act
        final ConvexArea transformed = area.transform(mat);

        // assert
        // removed other assertion

        final List<LineConvexSubset> segments = transformed.getBoundaries();
        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testTransform_finite_withDoubleReflection_3_oe() {
        // arrange
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D.createScale(Vector2D.of(-1, -2));

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1),
                    Vector2D.of(2, 2), Vector2D.of(1, 2)
                ), TEST_PRECISION);

        // act
        final ConvexArea transformed = area.transform(mat);

        // assert
        // removed other assertion

        final List<LineConvexSubset> segments = transformed.getBoundaries();
        // removed other assertion

        Assertions.assertEquals(2, transformed.getSize(), TEST_EPS);
    }

    @Test
    void testTransform_finite_withDoubleReflection_4_oe() {
        // arrange
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D.createScale(Vector2D.of(-1, -2));

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1),
                    Vector2D.of(2, 2), Vector2D.of(1, 2)
                ), TEST_PRECISION);

        // act
        final ConvexArea transformed = area.transform(mat);

        // assert
        // removed other assertion

        final List<LineConvexSubset> segments = transformed.getBoundaries();
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(6, transformed.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testGetVertices_full_1_oe() {
        // arrange
        final ConvexArea area = ConvexArea.full();

        // act/assert
        Assertions.assertEquals(0, area.getVertices().size());
    }

    @Test
    void testGetVertices_twoParallelLines_1_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(
                    Lines.fromPointAndAngle(Vector2D.of(0, 1), Math.PI, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(0, -1), 0.0, TEST_PRECISION)
                );

        // act/assert
        Assertions.assertEquals(0, area.getVertices().size());
    }

    @Test
    void testGetVertices_infiniteWithVertices_1_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(
                    Lines.fromPointAndAngle(Vector2D.of(0, 1), Math.PI, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(0, -1), 0.0, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(1, 0), Angle.PI_OVER_TWO, TEST_PRECISION)
                );

        // act
        final List<Vector2D> vertices = area.getVertices();

        // assert
        Assertions.assertEquals(2, vertices.size());
    }

    @Test
    void testGetVertices_finite_1_oe() {
        // arrange
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO,
                    Vector2D.Unit.PLUS_X,
                    Vector2D.Unit.PLUS_Y
                ), TEST_PRECISION);

        // act
        final List<Vector2D> vertices = area.getVertices();

        // assert
        Assertions.assertEquals(3, vertices.size());
    }

    @Test
    void testGetVertices_mismatchedEndpoints_1_oe() {
        // This test checks the case where we have a valid set of boundary segments but
        // with a small mismatch in the endpoints of some of the segments (possibly due
        // to floating point errors).

        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(0.99, 0);
        final Vector2D p3 = Vector2D.of(1, 0.002);
        final Vector2D p4 = Vector2D.of(0.995, -0.001);
        final Vector2D p5 = Vector2D.of(1, 1);

        final ConvexArea area = new ConvexArea(Arrays.asList(
                    Lines.segmentFromPoints(p1, p2, precision),
                    Lines.segmentFromPoints(p2, p3, precision),
                    Lines.segmentFromPoints(p4, p5, precision),
                    Lines.segmentFromPoints(p5, p1, precision)
                ));

        // act
        final List<Vector2D> vertices = area.getVertices();

        // assert
        Assertions.assertEquals(Arrays.asList(p1, p2, p3, p5), vertices);
    }

    @Test
    void testGetBounds_infinite_1_oe() {
        // act/assert
        Assertions.assertNull(ConvexArea.full().getBounds());
    }

    @Test
    void testGetBounds_infinite_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertNull(ConvexArea.fromBounds( Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION)).getBounds());
    }

    @Test
    void testProject_full_1_oe() {
        // arrange
        final ConvexArea area = ConvexArea.full();

        // act/assert
        Assertions.assertNull(area.project(Vector2D.ZERO));
    }

    @Test
    void testProject_full_2_oe() {
        // arrange
        final ConvexArea area = ConvexArea.full();

        // act/assert
        // removed other assertion
        Assertions.assertNull(area.project(Vector2D.Unit.PLUS_X));
    }

    @Test
    void testTrim_full_1_oe() {
        // arrange
        final ConvexArea area = ConvexArea.full();
        final Segment segment = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_Y, TEST_PRECISION);

        // act
        final LineConvexSubset trimmed = area.trim(segment);

        // assert
        Assertions.assertSame(segment, trimmed);
    }

    @Test
    void testTrim_segmentOutsideOfRegion_1_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.ZERO, 1, 1));
        final LineConvexSubset segment = Lines.fromPoints(Vector2D.of(-0.5, 0), Vector2D.of(-0.5, 1), TEST_PRECISION).span();

        // act
        final LineConvexSubset trimmed = area.trim(segment);

        // assert
        Assertions.assertNull(trimmed);
    }

    @Test
    void testTrim_segmentDirectlyOnBoundaryOfRegion_1_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.ZERO, 1, 1));
        final LineConvexSubset segment = Lines.fromPoints(Vector2D.of(1, 0), Vector2D.of(1, 1), TEST_PRECISION).span();

        // act
        final LineConvexSubset trimmed = area.trim(segment);

        // assert
        Assertions.assertNull(trimmed);
    }

    @Test
    void testSplit_full_1_oe() {
        // arrange
        final ConvexArea input = ConvexArea.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = input.split(splitter);

        // act
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_full_2_oe() {
        // arrange
        final ConvexArea input = ConvexArea.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = input.split(splitter);

        // act
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        Assertions.assertFalse(minus.isFull());
    }

    @Test
    void testSplit_full_3_oe() {
        // arrange
        final ConvexArea input = ConvexArea.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = input.split(splitter);

        // act
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        Assertions.assertFalse(minus.isEmpty());
    }

    @Test
    void testSplit_full_4_oe() {
        // arrange
        final ConvexArea input = ConvexArea.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = input.split(splitter);

        // act
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertPositiveInfinity(minus.getBoundarySize());
        GeometryTestUtils.assertPositiveInfinity(minus.getSize());
        Assertions.assertNull(minus.getCentroid());
    }

    @Test
    void testSplit_full_5_oe() {
        // arrange
        final ConvexArea input = ConvexArea.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = input.split(splitter);

        // act
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertPositiveInfinity(minus.getBoundarySize());
        GeometryTestUtils.assertPositiveInfinity(minus.getSize());
        // removed other assertion

        final List<LineConvexSubset> minusSegments = minus.getBoundaries();
        Assertions.assertEquals(1, minusSegments.size());
    }

    @Test
    void testSplit_full_6_oe() {
        // arrange
        final ConvexArea input = ConvexArea.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = input.split(splitter);

        // act
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertPositiveInfinity(minus.getBoundarySize());
        GeometryTestUtils.assertPositiveInfinity(minus.getSize());
        // removed other assertion

        final List<LineConvexSubset> minusSegments = minus.getBoundaries();
        // removed other assertion
        Assertions.assertEquals(splitter, minusSegments.get(0).getLine());
    }

    @Test
    void testSplit_full_7_oe() {
        // arrange
        final ConvexArea input = ConvexArea.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = input.split(splitter);

        // act
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertPositiveInfinity(minus.getBoundarySize());
        GeometryTestUtils.assertPositiveInfinity(minus.getSize());
        // removed other assertion

        final List<LineConvexSubset> minusSegments = minus.getBoundaries();
        // removed other assertion
        // removed other assertion

        final ConvexArea plus = split.getPlus();
        Assertions.assertFalse(plus.isFull());
    }

    @Test
    void testSplit_full_8_oe() {
        // arrange
        final ConvexArea input = ConvexArea.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = input.split(splitter);

        // act
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertPositiveInfinity(minus.getBoundarySize());
        GeometryTestUtils.assertPositiveInfinity(minus.getSize());
        // removed other assertion

        final List<LineConvexSubset> minusSegments = minus.getBoundaries();
        // removed other assertion
        // removed other assertion

        final ConvexArea plus = split.getPlus();
        // removed other assertion
        Assertions.assertFalse(plus.isEmpty());
    }

    @Test
    void testSplit_full_9_oe() {
        // arrange
        final ConvexArea input = ConvexArea.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = input.split(splitter);

        // act
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertPositiveInfinity(minus.getBoundarySize());
        GeometryTestUtils.assertPositiveInfinity(minus.getSize());
        // removed other assertion

        final List<LineConvexSubset> minusSegments = minus.getBoundaries();
        // removed other assertion
        // removed other assertion

        final ConvexArea plus = split.getPlus();
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertPositiveInfinity(plus.getBoundarySize());
        GeometryTestUtils.assertPositiveInfinity(plus.getSize());
        Assertions.assertNull(plus.getCentroid());
    }

    @Test
    void testSplit_full_10_oe() {
        // arrange
        final ConvexArea input = ConvexArea.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = input.split(splitter);

        // act
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertPositiveInfinity(minus.getBoundarySize());
        GeometryTestUtils.assertPositiveInfinity(minus.getSize());
        // removed other assertion

        final List<LineConvexSubset> minusSegments = minus.getBoundaries();
        // removed other assertion
        // removed other assertion

        final ConvexArea plus = split.getPlus();
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertPositiveInfinity(plus.getBoundarySize());
        GeometryTestUtils.assertPositiveInfinity(plus.getSize());
        // removed other assertion

        final List<LineConvexSubset> plusSegments = plus.getBoundaries();
        Assertions.assertEquals(1, plusSegments.size());
    }

    @Test
    void testSplit_full_11_oe() {
        // arrange
        final ConvexArea input = ConvexArea.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = input.split(splitter);

        // act
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertPositiveInfinity(minus.getBoundarySize());
        GeometryTestUtils.assertPositiveInfinity(minus.getSize());
        // removed other assertion

        final List<LineConvexSubset> minusSegments = minus.getBoundaries();
        // removed other assertion
        // removed other assertion

        final ConvexArea plus = split.getPlus();
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertPositiveInfinity(plus.getBoundarySize());
        GeometryTestUtils.assertPositiveInfinity(plus.getSize());
        // removed other assertion

        final List<LineConvexSubset> plusSegments = plus.getBoundaries();
        // removed other assertion
        Assertions.assertEquals(splitter, plusSegments.get(0).getLine().reverse());
    }

    @Test
    void testSplit_halfSpace_split_1_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_halfSpace_split_2_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        Assertions.assertFalse(minus.isFull());
    }

    @Test
    void testSplit_halfSpace_split_3_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        Assertions.assertFalse(minus.isEmpty());
    }

    @Test
    void testSplit_halfSpace_split_4_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertPositiveInfinity(minus.getBoundarySize());
        GeometryTestUtils.assertPositiveInfinity(minus.getSize());
        Assertions.assertNull(minus.getCentroid());
    }

    @Test
    void testSplit_halfSpace_split_5_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertPositiveInfinity(minus.getBoundarySize());
        GeometryTestUtils.assertPositiveInfinity(minus.getSize());
        // removed other assertion

        Assertions.assertEquals(2, minus.getBoundaries().size());
    }

    @Test
    void testSplit_halfSpace_split_6_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertPositiveInfinity(minus.getBoundarySize());
        GeometryTestUtils.assertPositiveInfinity(minus.getSize());
        // removed other assertion

        // removed other assertion

        final ConvexArea plus = split.getPlus();
        Assertions.assertFalse(plus.isFull());
    }

    @Test
    void testSplit_halfSpace_split_7_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertPositiveInfinity(minus.getBoundarySize());
        GeometryTestUtils.assertPositiveInfinity(minus.getSize());
        // removed other assertion

        // removed other assertion

        final ConvexArea plus = split.getPlus();
        // removed other assertion
        Assertions.assertFalse(plus.isEmpty());
    }

    @Test
    void testSplit_halfSpace_split_8_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertPositiveInfinity(minus.getBoundarySize());
        GeometryTestUtils.assertPositiveInfinity(minus.getSize());
        // removed other assertion

        // removed other assertion

        final ConvexArea plus = split.getPlus();
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertPositiveInfinity(plus.getBoundarySize());
        GeometryTestUtils.assertPositiveInfinity(plus.getSize());
        Assertions.assertNull(plus.getCentroid());
    }

    @Test
    void testSplit_halfSpace_split_9_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertPositiveInfinity(minus.getBoundarySize());
        GeometryTestUtils.assertPositiveInfinity(minus.getSize());
        // removed other assertion

        // removed other assertion

        final ConvexArea plus = split.getPlus();
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertPositiveInfinity(plus.getBoundarySize());
        GeometryTestUtils.assertPositiveInfinity(plus.getSize());
        // removed other assertion

        Assertions.assertEquals(2, plus.getBoundaries().size());
    }

    @Test
    void testSplit_halfSpace_splitOnBoundary_1_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());
    }

    @Test
    void testSplit_halfSpace_splitOnBoundary_2_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        Assertions.assertSame(area, split.getMinus());
    }

    @Test
    void testSplit_halfSpace_splitOnBoundary_3_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplit_halfSpace_splitOnBoundaryWithReversedSplitter_1_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION).reverse();

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.PLUS, split.getLocation());
    }

    @Test
    void testSplit_halfSpace_splitOnBoundaryWithReversedSplitter_2_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION).reverse();

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        Assertions.assertNull(split.getMinus());
    }

    @Test
    void testSplit_halfSpace_splitOnBoundaryWithReversedSplitter_3_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION).reverse();

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        // removed other assertion
        Assertions.assertSame(area, split.getPlus());
    }

    @Test
    void testSplit_square_split_1_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 2, 1));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(2, 1), Angle.PI_OVER_TWO, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_square_split_2_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 2, 1));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(2, 1), Angle.PI_OVER_TWO, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        Assertions.assertFalse(minus.isFull());
    }

    @Test
    void testSplit_square_split_3_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 2, 1));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(2, 1), Angle.PI_OVER_TWO, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        Assertions.assertFalse(minus.isEmpty());
    }

    @Test
    void testSplit_square_split_4_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 2, 1));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(2, 1), Angle.PI_OVER_TWO, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(4, minus.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testSplit_square_split_5_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 2, 1));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(2, 1), Angle.PI_OVER_TWO, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(1, minus.getSize(), TEST_EPS);
    }

    @Test
    void testSplit_square_split_6_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 2, 1));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(2, 1), Angle.PI_OVER_TWO, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1.5, 1.5), minus.getCentroid(), TEST_EPS);

        Assertions.assertEquals(4, minus.getBoundaries().size());
    }

    @Test
    void testSplit_square_split_7_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 2, 1));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(2, 1), Angle.PI_OVER_TWO, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1.5, 1.5), minus.getCentroid(), TEST_EPS);

        // removed other assertion

        final ConvexArea plus = split.getPlus();
        Assertions.assertFalse(plus.isFull());
    }

    @Test
    void testSplit_square_split_8_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 2, 1));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(2, 1), Angle.PI_OVER_TWO, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1.5, 1.5), minus.getCentroid(), TEST_EPS);

        // removed other assertion

        final ConvexArea plus = split.getPlus();
        // removed other assertion
        Assertions.assertFalse(plus.isEmpty());
    }

    @Test
    void testSplit_square_split_9_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 2, 1));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(2, 1), Angle.PI_OVER_TWO, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1.5, 1.5), minus.getCentroid(), TEST_EPS);

        // removed other assertion

        final ConvexArea plus = split.getPlus();
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(4, plus.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testSplit_square_split_10_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 2, 1));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(2, 1), Angle.PI_OVER_TWO, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1.5, 1.5), minus.getCentroid(), TEST_EPS);

        // removed other assertion

        final ConvexArea plus = split.getPlus();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(1, plus.getSize(), TEST_EPS);
    }

    @Test
    void testSplit_square_split_11_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 2, 1));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(2, 1), Angle.PI_OVER_TWO, TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(1.5, 1.5), minus.getCentroid(), TEST_EPS);

        // removed other assertion

        final ConvexArea plus = split.getPlus();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(2.5, 1.5), plus.getCentroid(), TEST_EPS);

        Assertions.assertEquals(4, plus.getBoundaries().size());
    }

    @Test
    void testSplit_square_splitOnVertices_1_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_square_splitOnVertices_2_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        Assertions.assertFalse(minus.isFull());
    }

    @Test
    void testSplit_square_splitOnVertices_3_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        Assertions.assertFalse(minus.isEmpty());
    }

    @Test
    void testSplit_square_splitOnVertices_4_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(2 + Math.sqrt(2), minus.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testSplit_square_splitOnVertices_5_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0.5, minus.getSize(), TEST_EPS);
    }

    @Test
    void testSplit_square_splitOnVertices_6_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(4.0 / 3.0, 5.0 / 3.0), minus.getCentroid(), TEST_EPS);

        Assertions.assertEquals(3, minus.getBoundaries().size());
    }

    @Test
    void testSplit_square_splitOnVertices_7_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(4.0 / 3.0, 5.0 / 3.0), minus.getCentroid(), TEST_EPS);

        // removed other assertion

        final ConvexArea plus = split.getPlus();
        Assertions.assertFalse(plus.isFull());
    }

    @Test
    void testSplit_square_splitOnVertices_8_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(4.0 / 3.0, 5.0 / 3.0), minus.getCentroid(), TEST_EPS);

        // removed other assertion

        final ConvexArea plus = split.getPlus();
        // removed other assertion
        Assertions.assertFalse(plus.isEmpty());
    }

    @Test
    void testSplit_square_splitOnVertices_9_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(4.0 / 3.0, 5.0 / 3.0), minus.getCentroid(), TEST_EPS);

        // removed other assertion

        final ConvexArea plus = split.getPlus();
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(2 + Math.sqrt(2), plus.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testSplit_square_splitOnVertices_10_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(4.0 / 3.0, 5.0 / 3.0), minus.getCentroid(), TEST_EPS);

        // removed other assertion

        final ConvexArea plus = split.getPlus();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0.5, plus.getSize(), TEST_EPS);
    }

    @Test
    void testSplit_square_splitOnVertices_11_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(4.0 / 3.0, 5.0 / 3.0), minus.getCentroid(), TEST_EPS);

        // removed other assertion

        final ConvexArea plus = split.getPlus();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(5.0 / 3.0, 4.0 / 3.0), plus.getCentroid(), TEST_EPS);

        Assertions.assertEquals(3, plus.getBoundaries().size());
    }

    @Test
    void testSplit_square_splitOnVerticesWithReversedSplitter_1_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).reverse();

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_square_splitOnVerticesWithReversedSplitter_2_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).reverse();

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        Assertions.assertFalse(minus.isFull());
    }

    @Test
    void testSplit_square_splitOnVerticesWithReversedSplitter_3_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).reverse();

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        Assertions.assertFalse(minus.isEmpty());
    }

    @Test
    void testSplit_square_splitOnVerticesWithReversedSplitter_4_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).reverse();

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(2 + Math.sqrt(2), minus.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testSplit_square_splitOnVerticesWithReversedSplitter_5_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).reverse();

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0.5, minus.getSize(), TEST_EPS);
    }

    @Test
    void testSplit_square_splitOnVerticesWithReversedSplitter_6_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).reverse();

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(5.0 / 3.0, 4.0 / 3.0), minus.getCentroid(), TEST_EPS);

        Assertions.assertEquals(3, minus.getBoundaries().size());
    }

    @Test
    void testSplit_square_splitOnVerticesWithReversedSplitter_7_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).reverse();

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(5.0 / 3.0, 4.0 / 3.0), minus.getCentroid(), TEST_EPS);

        // removed other assertion

        final ConvexArea plus = split.getPlus();
        Assertions.assertFalse(plus.isFull());
    }

    @Test
    void testSplit_square_splitOnVerticesWithReversedSplitter_8_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).reverse();

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(5.0 / 3.0, 4.0 / 3.0), minus.getCentroid(), TEST_EPS);

        // removed other assertion

        final ConvexArea plus = split.getPlus();
        // removed other assertion
        Assertions.assertFalse(plus.isEmpty());
    }

    @Test
    void testSplit_square_splitOnVerticesWithReversedSplitter_9_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).reverse();

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(5.0 / 3.0, 4.0 / 3.0), minus.getCentroid(), TEST_EPS);

        // removed other assertion

        final ConvexArea plus = split.getPlus();
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(2 + Math.sqrt(2), plus.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testSplit_square_splitOnVerticesWithReversedSplitter_10_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).reverse();

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(5.0 / 3.0, 4.0 / 3.0), minus.getCentroid(), TEST_EPS);

        // removed other assertion

        final ConvexArea plus = split.getPlus();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0.5, plus.getSize(), TEST_EPS);
    }

    @Test
    void testSplit_square_splitOnVerticesWithReversedSplitter_11_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).reverse();

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion

        final ConvexArea minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(5.0 / 3.0, 4.0 / 3.0), minus.getCentroid(), TEST_EPS);

        // removed other assertion

        final ConvexArea plus = split.getPlus();
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(4.0 / 3.0, 5.0 / 3.0), plus.getCentroid(), TEST_EPS);

        Assertions.assertEquals(3, plus.getBoundaries().size());
    }

    @Test
    void testSplit_square_entirelyOnMinus_1_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(3, 1), Vector2D.of(3, 2), TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());
    }

    @Test
    void testSplit_square_entirelyOnMinus_2_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(3, 1), Vector2D.of(3, 2), TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion
        Assertions.assertSame(area, split.getMinus());
    }

    @Test
    void testSplit_square_entirelyOnMinus_3_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(3, 1), Vector2D.of(3, 2), TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplit_square_onMinusBoundary_1_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(2, 1), Vector2D.of(2, 2), TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());
    }

    @Test
    void testSplit_square_onMinusBoundary_2_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(2, 1), Vector2D.of(2, 2), TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion
        Assertions.assertSame(area, split.getMinus());
    }

    @Test
    void testSplit_square_onMinusBoundary_3_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(2, 1), Vector2D.of(2, 2), TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplit_square_entirelyOnPlus_1_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(0, 1), Vector2D.of(0, 2), TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.PLUS, split.getLocation());
    }

    @Test
    void testSplit_square_entirelyOnPlus_2_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(0, 1), Vector2D.of(0, 2), TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion
        Assertions.assertNull(split.getMinus());
    }

    @Test
    void testSplit_square_entirelyOnPlus_3_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(0, 1), Vector2D.of(0, 2), TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(area, split.getPlus());
    }

    @Test
    void testSplit_square_onPlusBoundary_1_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(1, 2), TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.PLUS, split.getLocation());
    }

    @Test
    void testSplit_square_onPlusBoundary_2_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(1, 2), TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion
        Assertions.assertNull(split.getMinus());
    }

    @Test
    void testSplit_square_onPlusBoundary_3_oe() {
        // arrange
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(1, 2), TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(area, split.getPlus());
    }

    @Test
    void testSplit_fannedLines_1_oe() {
        // arrange
        final Line a = Lines.fromPointAndDirection(
                Vector2D.of(0.00600526260605261, -0.3392565140336253),
                Vector2D.of(0.9998433697734339, 0.017698472253402094), TEST_PRECISION);
        final Line b = Lines.fromPointAndDirection(
                Vector2D.of(-0.05020576603061953, 1.7524758059156824),
                Vector2D.of(0.9995898847600798, 0.02863672965494457), TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(a, b.reverse());

        final Line splitter = Lines.fromPointAndDirection(
                Vector2D.of(0.01581855191043128, -2.5270731411451215),
                Vector2D.of(0.999980409069402, 0.006259510954681248), TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());
    }

    @Test
    void testSplit_fannedLines_2_oe() {
        // arrange
        final Line a = Lines.fromPointAndDirection(
                Vector2D.of(0.00600526260605261, -0.3392565140336253),
                Vector2D.of(0.9998433697734339, 0.017698472253402094), TEST_PRECISION);
        final Line b = Lines.fromPointAndDirection(
                Vector2D.of(-0.05020576603061953, 1.7524758059156824),
                Vector2D.of(0.9995898847600798, 0.02863672965494457), TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(a, b.reverse());

        final Line splitter = Lines.fromPointAndDirection(
                Vector2D.of(0.01581855191043128, -2.5270731411451215),
                Vector2D.of(0.999980409069402, 0.006259510954681248), TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion
        Assertions.assertSame(area, split.getMinus());
    }

    @Test
    void testSplit_fannedLines_3_oe() {
        // arrange
        final Line a = Lines.fromPointAndDirection(
                Vector2D.of(0.00600526260605261, -0.3392565140336253),
                Vector2D.of(0.9998433697734339, 0.017698472253402094), TEST_PRECISION);
        final Line b = Lines.fromPointAndDirection(
                Vector2D.of(-0.05020576603061953, 1.7524758059156824),
                Vector2D.of(0.9995898847600798, 0.02863672965494457), TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(a, b.reverse());

        final Line splitter = Lines.fromPointAndDirection(
                Vector2D.of(0.01581855191043128, -2.5270731411451215),
                Vector2D.of(0.999980409069402, 0.006259510954681248), TEST_PRECISION);

        // act
        final Split<ConvexArea> split = area.split(splitter);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplit_trimmedSplitterDiscrepancy_1_oe() {
        // The following example came from a failed invocation of the Sphere.toTree() method.
        // This test checks the case where the splitter trimmed to the area is non-empty but
        // the boundaries split by the splitter all lies on a single side.

        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-10);

        final Vector2D p1 = Vector2D.of(-100.27622744776312, -39.236143934478704);
        final Vector2D p2 = Vector2D.of(-100.23149336840831, -39.28090397981739);
        final Vector2D p3 = Vector2D.of(-96.28607710958399, -39.25486984391497);
        final ConvexArea area = ConvexArea.fromBounds(
                    Lines.fromPointAndDirection(p1, Vector2D.of(-0.00601644753700725, -0.9999819010157307), precision),
                    Lines.fromPoints(p1, p2, precision),
                    Lines.fromPoints(p2, p3, precision),
                    Lines.fromPointAndDirection(p3, Vector2D.of(0.9999648811047153, 0.008380725340508379), precision)
                );

        final Line splitter = Lines.fromPointAndDirection(
                Vector2D.of(-68.9981806624852, -70.04669274578112),
                Vector2D.of(0.7124186895479748, -0.7017546656651072),
                precision);

        // act
        final Split<ConvexArea> minusSplit = area.split(splitter);
        final Split<ConvexArea> plusSplit = area.split(splitter.reverse());

        // assert
        Assertions.assertEquals(SplitLocation.MINUS, minusSplit.getLocation());
    }

    @Test
    void testSplit_trimmedSplitterDiscrepancy_2_oe() {
        // The following example came from a failed invocation of the Sphere.toTree() method.
        // This test checks the case where the splitter trimmed to the area is non-empty but
        // the boundaries split by the splitter all lies on a single side.

        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-10);

        final Vector2D p1 = Vector2D.of(-100.27622744776312, -39.236143934478704);
        final Vector2D p2 = Vector2D.of(-100.23149336840831, -39.28090397981739);
        final Vector2D p3 = Vector2D.of(-96.28607710958399, -39.25486984391497);
        final ConvexArea area = ConvexArea.fromBounds(
                    Lines.fromPointAndDirection(p1, Vector2D.of(-0.00601644753700725, -0.9999819010157307), precision),
                    Lines.fromPoints(p1, p2, precision),
                    Lines.fromPoints(p2, p3, precision),
                    Lines.fromPointAndDirection(p3, Vector2D.of(0.9999648811047153, 0.008380725340508379), precision)
                );

        final Line splitter = Lines.fromPointAndDirection(
                Vector2D.of(-68.9981806624852, -70.04669274578112),
                Vector2D.of(0.7124186895479748, -0.7017546656651072),
                precision);

        // act
        final Split<ConvexArea> minusSplit = area.split(splitter);
        final Split<ConvexArea> plusSplit = area.split(splitter.reverse());

        // assert
        // removed other assertion

        Assertions.assertSame(area, minusSplit.getMinus());
    }

    @Test
    void testSplit_trimmedSplitterDiscrepancy_3_oe() {
        // The following example came from a failed invocation of the Sphere.toTree() method.
        // This test checks the case where the splitter trimmed to the area is non-empty but
        // the boundaries split by the splitter all lies on a single side.

        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-10);

        final Vector2D p1 = Vector2D.of(-100.27622744776312, -39.236143934478704);
        final Vector2D p2 = Vector2D.of(-100.23149336840831, -39.28090397981739);
        final Vector2D p3 = Vector2D.of(-96.28607710958399, -39.25486984391497);
        final ConvexArea area = ConvexArea.fromBounds(
                    Lines.fromPointAndDirection(p1, Vector2D.of(-0.00601644753700725, -0.9999819010157307), precision),
                    Lines.fromPoints(p1, p2, precision),
                    Lines.fromPoints(p2, p3, precision),
                    Lines.fromPointAndDirection(p3, Vector2D.of(0.9999648811047153, 0.008380725340508379), precision)
                );

        final Line splitter = Lines.fromPointAndDirection(
                Vector2D.of(-68.9981806624852, -70.04669274578112),
                Vector2D.of(0.7124186895479748, -0.7017546656651072),
                precision);

        // act
        final Split<ConvexArea> minusSplit = area.split(splitter);
        final Split<ConvexArea> plusSplit = area.split(splitter.reverse());

        // assert
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(minusSplit.getPlus());
    }

    @Test
    void testSplit_trimmedSplitterDiscrepancy_4_oe() {
        // The following example came from a failed invocation of the Sphere.toTree() method.
        // This test checks the case where the splitter trimmed to the area is non-empty but
        // the boundaries split by the splitter all lies on a single side.

        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-10);

        final Vector2D p1 = Vector2D.of(-100.27622744776312, -39.236143934478704);
        final Vector2D p2 = Vector2D.of(-100.23149336840831, -39.28090397981739);
        final Vector2D p3 = Vector2D.of(-96.28607710958399, -39.25486984391497);
        final ConvexArea area = ConvexArea.fromBounds(
                    Lines.fromPointAndDirection(p1, Vector2D.of(-0.00601644753700725, -0.9999819010157307), precision),
                    Lines.fromPoints(p1, p2, precision),
                    Lines.fromPoints(p2, p3, precision),
                    Lines.fromPointAndDirection(p3, Vector2D.of(0.9999648811047153, 0.008380725340508379), precision)
                );

        final Line splitter = Lines.fromPointAndDirection(
                Vector2D.of(-68.9981806624852, -70.04669274578112),
                Vector2D.of(0.7124186895479748, -0.7017546656651072),
                precision);

        // act
        final Split<ConvexArea> minusSplit = area.split(splitter);
        final Split<ConvexArea> plusSplit = area.split(splitter.reverse());

        // assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(SplitLocation.PLUS, plusSplit.getLocation());
    }

    @Test
    void testSplit_trimmedSplitterDiscrepancy_5_oe() {
        // The following example came from a failed invocation of the Sphere.toTree() method.
        // This test checks the case where the splitter trimmed to the area is non-empty but
        // the boundaries split by the splitter all lies on a single side.

        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-10);

        final Vector2D p1 = Vector2D.of(-100.27622744776312, -39.236143934478704);
        final Vector2D p2 = Vector2D.of(-100.23149336840831, -39.28090397981739);
        final Vector2D p3 = Vector2D.of(-96.28607710958399, -39.25486984391497);
        final ConvexArea area = ConvexArea.fromBounds(
                    Lines.fromPointAndDirection(p1, Vector2D.of(-0.00601644753700725, -0.9999819010157307), precision),
                    Lines.fromPoints(p1, p2, precision),
                    Lines.fromPoints(p2, p3, precision),
                    Lines.fromPointAndDirection(p3, Vector2D.of(0.9999648811047153, 0.008380725340508379), precision)
                );

        final Line splitter = Lines.fromPointAndDirection(
                Vector2D.of(-68.9981806624852, -70.04669274578112),
                Vector2D.of(0.7124186895479748, -0.7017546656651072),
                precision);

        // act
        final Split<ConvexArea> minusSplit = area.split(splitter);
        final Split<ConvexArea> plusSplit = area.split(splitter.reverse());

        // assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        Assertions.assertNull(plusSplit.getMinus());
    }

    @Test
    void testSplit_trimmedSplitterDiscrepancy_6_oe() {
        // The following example came from a failed invocation of the Sphere.toTree() method.
        // This test checks the case where the splitter trimmed to the area is non-empty but
        // the boundaries split by the splitter all lies on a single side.

        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-10);

        final Vector2D p1 = Vector2D.of(-100.27622744776312, -39.236143934478704);
        final Vector2D p2 = Vector2D.of(-100.23149336840831, -39.28090397981739);
        final Vector2D p3 = Vector2D.of(-96.28607710958399, -39.25486984391497);
        final ConvexArea area = ConvexArea.fromBounds(
                    Lines.fromPointAndDirection(p1, Vector2D.of(-0.00601644753700725, -0.9999819010157307), precision),
                    Lines.fromPoints(p1, p2, precision),
                    Lines.fromPoints(p2, p3, precision),
                    Lines.fromPointAndDirection(p3, Vector2D.of(0.9999648811047153, 0.008380725340508379), precision)
                );

        final Line splitter = Lines.fromPointAndDirection(
                Vector2D.of(-68.9981806624852, -70.04669274578112),
                Vector2D.of(0.7124186895479748, -0.7017546656651072),
                precision);

        // act
        final Split<ConvexArea> minusSplit = area.split(splitter);
        final Split<ConvexArea> plusSplit = area.split(splitter.reverse());

        // assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        Assertions.assertSame(area, plusSplit.getPlus());
    }

    @Test
    void testToString_1_oe() {
        // arrange
        final ConvexArea area = ConvexArea.full();

        // act
        final String str = area.toString();

        // assert
        Assertions.assertTrue(str.contains("ConvexArea"));
    }

    @Test
    void testToString_2_oe() {
        // arrange
        final ConvexArea area = ConvexArea.full();

        // act
        final String str = area.toString();

        // assert
        // removed other assertion
        Assertions.assertTrue(str.contains("boundaries= "));
    }

    @Test
    void testConvexPolygonFromVertices_triangle_1_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 2);
        final Vector2D p1 = Vector2D.of(2, 2);
        final Vector2D p2 = Vector2D.of(2, 3);

        // act
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(p0, p1, p2), TEST_PRECISION);

        // assert
        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testConvexPolygonFromVertices_triangle_2_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 2);
        final Vector2D p1 = Vector2D.of(2, 2);
        final Vector2D p2 = Vector2D.of(2, 3);

        // act
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(p0, p1, p2), TEST_PRECISION);

        // assert
        // removed other assertion
        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testConvexPolygonFromVertices_triangle_3_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 2);
        final Vector2D p1 = Vector2D.of(2, 2);
        final Vector2D p2 = Vector2D.of(2, 3);

        // act
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(p0, p1, p2), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0.5, area.getSize(), TEST_EPS);
    }

    @Test
    void testConvexPolygonFromVertices_triangle_4_oe() {
        // arrange
        final Vector2D p0 = Vector2D.of(1, 2);
        final Vector2D p1 = Vector2D.of(2, 2);
        final Vector2D p2 = Vector2D.of(2, 3);

        // act
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(p0, p1, p2), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(2 + Math.sqrt(2), area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testConvexPolygonFromVertices_square_closeRequired_1_oe() {
        // act
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO,
                    Vector2D.Unit.PLUS_X,
                    Vector2D.of(1, 1),
                    Vector2D.of(0, 1)
                ), TEST_PRECISION);

        // assert
        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testConvexPolygonFromVertices_square_closeRequired_2_oe() {
        // act
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO,
                    Vector2D.Unit.PLUS_X,
                    Vector2D.of(1, 1),
                    Vector2D.of(0, 1)
                ), TEST_PRECISION);

        // assert
        // removed other assertion
        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testConvexPolygonFromVertices_square_closeRequired_3_oe() {
        // act
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO,
                    Vector2D.Unit.PLUS_X,
                    Vector2D.of(1, 1),
                    Vector2D.of(0, 1)
                ), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1, area.getSize(), TEST_EPS);
    }

    @Test
    void testConvexPolygonFromVertices_square_closeRequired_4_oe() {
        // act
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO,
                    Vector2D.Unit.PLUS_X,
                    Vector2D.of(1, 1),
                    Vector2D.of(0, 1)
                ), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(4, area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testConvexPolygonFromVertices_square_closeNotRequired_1_oe() {
        // act
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO,
                    Vector2D.Unit.PLUS_X,
                    Vector2D.of(1, 1),
                    Vector2D.of(0, 1),
                    Vector2D.ZERO
                ), TEST_PRECISION);

        // assert
        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testConvexPolygonFromVertices_square_closeNotRequired_2_oe() {
        // act
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO,
                    Vector2D.Unit.PLUS_X,
                    Vector2D.of(1, 1),
                    Vector2D.of(0, 1),
                    Vector2D.ZERO
                ), TEST_PRECISION);

        // assert
        // removed other assertion
        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testConvexPolygonFromVertices_square_closeNotRequired_3_oe() {
        // act
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO,
                    Vector2D.Unit.PLUS_X,
                    Vector2D.of(1, 1),
                    Vector2D.of(0, 1),
                    Vector2D.ZERO
                ), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1, area.getSize(), TEST_EPS);
    }

    @Test
    void testConvexPolygonFromVertices_square_closeNotRequired_4_oe() {
        // act
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO,
                    Vector2D.Unit.PLUS_X,
                    Vector2D.of(1, 1),
                    Vector2D.of(0, 1),
                    Vector2D.ZERO
                ), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(4, area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testConvexPolygonFromVertices_handlesDuplicatePoints_1_oe() {
        // arrange
        final double eps = 1e-3;
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(eps);

        // act
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO,
                    Vector2D.of(1e-4, 1e-4),
                    Vector2D.Unit.PLUS_X,
                    Vector2D.of(1, 1e-4),
                    Vector2D.of(1, 1),
                    Vector2D.of(0, 1),
                    Vector2D.of(1e-4, 1),
                    Vector2D.of(1e-4, 1e-4)
                ), precision);

        // assert
        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testConvexPolygonFromVertices_handlesDuplicatePoints_2_oe() {
        // arrange
        final double eps = 1e-3;
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(eps);

        // act
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO,
                    Vector2D.of(1e-4, 1e-4),
                    Vector2D.Unit.PLUS_X,
                    Vector2D.of(1, 1e-4),
                    Vector2D.of(1, 1),
                    Vector2D.of(0, 1),
                    Vector2D.of(1e-4, 1),
                    Vector2D.of(1e-4, 1e-4)
                ), precision);

        // assert
        // removed other assertion
        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testConvexPolygonFromVertices_handlesDuplicatePoints_3_oe() {
        // arrange
        final double eps = 1e-3;
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(eps);

        // act
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO,
                    Vector2D.of(1e-4, 1e-4),
                    Vector2D.Unit.PLUS_X,
                    Vector2D.of(1, 1e-4),
                    Vector2D.of(1, 1),
                    Vector2D.of(0, 1),
                    Vector2D.of(1e-4, 1),
                    Vector2D.of(1e-4, 1e-4)
                ), precision);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1, area.getSize(), eps);
    }

    @Test
    void testConvexPolygonFromVertices_handlesDuplicatePoints_4_oe() {
        // arrange
        final double eps = 1e-3;
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(eps);

        // act
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO,
                    Vector2D.of(1e-4, 1e-4),
                    Vector2D.Unit.PLUS_X,
                    Vector2D.of(1, 1e-4),
                    Vector2D.of(1, 1),
                    Vector2D.of(0, 1),
                    Vector2D.of(1e-4, 1),
                    Vector2D.of(1e-4, 1e-4)
                ), precision);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(4, area.getBoundarySize(), eps);
    }

    @Test
    void testConvexPolygonFromPath_1_oe() {
        // act
        final ConvexArea area = ConvexArea.convexPolygonFromPath(LinePath.fromVertexLoop(
                Arrays.asList(
                        Vector2D.ZERO,
                        Vector2D.Unit.PLUS_X,
                        Vector2D.of(1, 1),
                        Vector2D.Unit.PLUS_Y
                ), TEST_PRECISION));

        // assert
        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testConvexPolygonFromPath_2_oe() {
        // act
        final ConvexArea area = ConvexArea.convexPolygonFromPath(LinePath.fromVertexLoop(
                Arrays.asList(
                        Vector2D.ZERO,
                        Vector2D.Unit.PLUS_X,
                        Vector2D.of(1, 1),
                        Vector2D.Unit.PLUS_Y
                ), TEST_PRECISION));

        // assert
        // removed other assertion
        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testConvexPolygonFromPath_3_oe() {
        // act
        final ConvexArea area = ConvexArea.convexPolygonFromPath(LinePath.fromVertexLoop(
                Arrays.asList(
                        Vector2D.ZERO,
                        Vector2D.Unit.PLUS_X,
                        Vector2D.of(1, 1),
                        Vector2D.Unit.PLUS_Y
                ), TEST_PRECISION));

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(1, area.getSize(), TEST_EPS);
    }

    @Test
    void testConvexPolygonFromPath_4_oe() {
        // act
        final ConvexArea area = ConvexArea.convexPolygonFromPath(LinePath.fromVertexLoop(
                Arrays.asList(
                        Vector2D.ZERO,
                        Vector2D.Unit.PLUS_X,
                        Vector2D.of(1, 1),
                        Vector2D.Unit.PLUS_Y
                ), TEST_PRECISION));

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(4, area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFromBounds_noLines_1_oe() {
        // act
        final ConvexArea area = ConvexArea.fromBounds(Collections.emptyList());

        // assert
        Assertions.assertSame(ConvexArea.full(), area);
    }

    @Test
    void testFromBounds_singleLine_1_oe() {
        // arrange
        final Line line = Lines.fromPoints(Vector2D.of(0, 1), Vector2D.of(1, 3), TEST_PRECISION);

        // act
        final ConvexArea area = ConvexArea.fromBounds(line);

        // assert
        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testFromBounds_singleLine_2_oe() {
        // arrange
        final Line line = Lines.fromPoints(Vector2D.of(0, 1), Vector2D.of(1, 3), TEST_PRECISION);

        // act
        final ConvexArea area = ConvexArea.fromBounds(line);

        // assert
        // removed other assertion
        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFromBounds_singleLine_3_oe() {
        // arrange
        final Line line = Lines.fromPoints(Vector2D.of(0, 1), Vector2D.of(1, 3), TEST_PRECISION);

        // act
        final ConvexArea area = ConvexArea.fromBounds(line);

        // assert
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertPositiveInfinity(area.getBoundarySize());
        GeometryTestUtils.assertPositiveInfinity(area.getSize());
        Assertions.assertNull(area.getCentroid());
    }

    @Test
    void testFromBounds_singleLine_4_oe() {
        // arrange
        final Line line = Lines.fromPoints(Vector2D.of(0, 1), Vector2D.of(1, 3), TEST_PRECISION);

        // act
        final ConvexArea area = ConvexArea.fromBounds(line);

        // assert
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertPositiveInfinity(area.getBoundarySize());
        GeometryTestUtils.assertPositiveInfinity(area.getSize());
        // removed other assertion

        final List<LineConvexSubset> segments = area.getBoundaries();
        Assertions.assertEquals(1, segments.size());
    }

    @Test
    void testFromBounds_singleLine_5_oe() {
        // arrange
        final Line line = Lines.fromPoints(Vector2D.of(0, 1), Vector2D.of(1, 3), TEST_PRECISION);

        // act
        final ConvexArea area = ConvexArea.fromBounds(line);

        // assert
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertPositiveInfinity(area.getBoundarySize());
        GeometryTestUtils.assertPositiveInfinity(area.getSize());
        // removed other assertion

        final List<LineConvexSubset> segments = area.getBoundaries();
        // removed other assertion
        Assertions.assertSame(line, segments.get(0).getLine());
    }

    @Test
    void testFromBounds_twoLines_1_oe() {
        // arrange
        final Line a = Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.ZERO, Math.PI, TEST_PRECISION);

        // act
        final ConvexArea area = ConvexArea.fromBounds(a, b);

        // assert
        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testFromBounds_twoLines_2_oe() {
        // arrange
        final Line a = Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.ZERO, Math.PI, TEST_PRECISION);

        // act
        final ConvexArea area = ConvexArea.fromBounds(a, b);

        // assert
        // removed other assertion
        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFromBounds_twoLines_3_oe() {
        // arrange
        final Line a = Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.ZERO, Math.PI, TEST_PRECISION);

        // act
        final ConvexArea area = ConvexArea.fromBounds(a, b);

        // assert
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertPositiveInfinity(area.getBoundarySize());
        GeometryTestUtils.assertPositiveInfinity(area.getSize());
        Assertions.assertNull(area.getCentroid());
    }

    @Test
    void testFromBounds_twoLines_4_oe() {
        // arrange
        final Line a = Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.ZERO, Math.PI, TEST_PRECISION);

        // act
        final ConvexArea area = ConvexArea.fromBounds(a, b);

        // assert
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertPositiveInfinity(area.getBoundarySize());
        GeometryTestUtils.assertPositiveInfinity(area.getSize());
        // removed other assertion

        final List<LineConvexSubset> segments = area.getBoundaries();
        Assertions.assertEquals(2, segments.size());
    }

    @Test
    void testFromBounds_triangle_1_oe() {
        // arrange
        final Line a = Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.ZERO, Math.PI, TEST_PRECISION);
        final Line c = Lines.fromPointAndAngle(Vector2D.of(-2, 0), -0.25 * Math.PI, TEST_PRECISION);

        // act
        final ConvexArea area = ConvexArea.fromBounds(a, b, c);

        // assert
        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testFromBounds_triangle_2_oe() {
        // arrange
        final Line a = Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.ZERO, Math.PI, TEST_PRECISION);
        final Line c = Lines.fromPointAndAngle(Vector2D.of(-2, 0), -0.25 * Math.PI, TEST_PRECISION);

        // act
        final ConvexArea area = ConvexArea.fromBounds(a, b, c);

        // assert
        // removed other assertion
        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFromBounds_triangle_3_oe() {
        // arrange
        final Line a = Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.ZERO, Math.PI, TEST_PRECISION);
        final Line c = Lines.fromPointAndAngle(Vector2D.of(-2, 0), -0.25 * Math.PI, TEST_PRECISION);

        // act
        final ConvexArea area = ConvexArea.fromBounds(a, b, c);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(4 + (2 * Math.sqrt(2)), area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFromBounds_triangle_4_oe() {
        // arrange
        final Line a = Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.ZERO, Math.PI, TEST_PRECISION);
        final Line c = Lines.fromPointAndAngle(Vector2D.of(-2, 0), -0.25 * Math.PI, TEST_PRECISION);

        // act
        final ConvexArea area = ConvexArea.fromBounds(a, b, c);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(2, area.getSize(), TEST_EPS);
    }

    @Test
    void testFromBounds_triangle_5_oe() {
        // arrange
        final Line a = Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.ZERO, Math.PI, TEST_PRECISION);
        final Line c = Lines.fromPointAndAngle(Vector2D.of(-2, 0), -0.25 * Math.PI, TEST_PRECISION);

        // act
        final ConvexArea area = ConvexArea.fromBounds(a, b, c);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(-2.0 / 3.0, -2.0 / 3.0), area.getCentroid(), TEST_EPS);

        final List<LineConvexSubset> segments = area.getBoundaries();
        Assertions.assertEquals(3, segments.size());
    }

    @Test
    void testFromBounds_square_1_oe() {
        // arrange
        final List<Line> square = createSquareBoundingLines(Vector2D.ZERO, 1, 1);

        // act
        final ConvexArea area = ConvexArea.fromBounds(square);

        // assert
        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testFromBounds_square_2_oe() {
        // arrange
        final List<Line> square = createSquareBoundingLines(Vector2D.ZERO, 1, 1);

        // act
        final ConvexArea area = ConvexArea.fromBounds(square);

        // assert
        // removed other assertion
        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFromBounds_square_3_oe() {
        // arrange
        final List<Line> square = createSquareBoundingLines(Vector2D.ZERO, 1, 1);

        // act
        final ConvexArea area = ConvexArea.fromBounds(square);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(4, area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFromBounds_square_4_oe() {
        // arrange
        final List<Line> square = createSquareBoundingLines(Vector2D.ZERO, 1, 1);

        // act
        final ConvexArea area = ConvexArea.fromBounds(square);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(1, area.getSize(), TEST_EPS);
    }

    @Test
    void testFromBounds_square_5_oe() {
        // arrange
        final List<Line> square = createSquareBoundingLines(Vector2D.ZERO, 1, 1);

        // act
        final ConvexArea area = ConvexArea.fromBounds(square);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0.5, 0.5), area.getCentroid(), TEST_EPS);

        final List<LineConvexSubset> segments = area.getBoundaries();
        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testFromBounds_square_extraLines_1_oe() {
        // arrange
        final List<Line> extraLines = new ArrayList<>();
        extraLines.add(Lines.fromPoints(Vector2D.of(10, 10), Vector2D.of(10, 11), TEST_PRECISION));
        extraLines.add(Lines.fromPoints(Vector2D.of(-10, 10), Vector2D.of(-10, 9), TEST_PRECISION));
        extraLines.add(Lines.fromPoints(Vector2D.of(0, 10), Vector2D.of(-1, 11), TEST_PRECISION));
        extraLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));

        // act
        final ConvexArea area = ConvexArea.fromBounds(extraLines);

        // assert
        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testFromBounds_square_extraLines_2_oe() {
        // arrange
        final List<Line> extraLines = new ArrayList<>();
        extraLines.add(Lines.fromPoints(Vector2D.of(10, 10), Vector2D.of(10, 11), TEST_PRECISION));
        extraLines.add(Lines.fromPoints(Vector2D.of(-10, 10), Vector2D.of(-10, 9), TEST_PRECISION));
        extraLines.add(Lines.fromPoints(Vector2D.of(0, 10), Vector2D.of(-1, 11), TEST_PRECISION));
        extraLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));

        // act
        final ConvexArea area = ConvexArea.fromBounds(extraLines);

        // assert
        // removed other assertion
        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFromBounds_square_extraLines_3_oe() {
        // arrange
        final List<Line> extraLines = new ArrayList<>();
        extraLines.add(Lines.fromPoints(Vector2D.of(10, 10), Vector2D.of(10, 11), TEST_PRECISION));
        extraLines.add(Lines.fromPoints(Vector2D.of(-10, 10), Vector2D.of(-10, 9), TEST_PRECISION));
        extraLines.add(Lines.fromPoints(Vector2D.of(0, 10), Vector2D.of(-1, 11), TEST_PRECISION));
        extraLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));

        // act
        final ConvexArea area = ConvexArea.fromBounds(extraLines);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(4, area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFromBounds_square_extraLines_4_oe() {
        // arrange
        final List<Line> extraLines = new ArrayList<>();
        extraLines.add(Lines.fromPoints(Vector2D.of(10, 10), Vector2D.of(10, 11), TEST_PRECISION));
        extraLines.add(Lines.fromPoints(Vector2D.of(-10, 10), Vector2D.of(-10, 9), TEST_PRECISION));
        extraLines.add(Lines.fromPoints(Vector2D.of(0, 10), Vector2D.of(-1, 11), TEST_PRECISION));
        extraLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));

        // act
        final ConvexArea area = ConvexArea.fromBounds(extraLines);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(1, area.getSize(), TEST_EPS);
    }

    @Test
    void testFromBounds_square_extraLines_5_oe() {
        // arrange
        final List<Line> extraLines = new ArrayList<>();
        extraLines.add(Lines.fromPoints(Vector2D.of(10, 10), Vector2D.of(10, 11), TEST_PRECISION));
        extraLines.add(Lines.fromPoints(Vector2D.of(-10, 10), Vector2D.of(-10, 9), TEST_PRECISION));
        extraLines.add(Lines.fromPoints(Vector2D.of(0, 10), Vector2D.of(-1, 11), TEST_PRECISION));
        extraLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));

        // act
        final ConvexArea area = ConvexArea.fromBounds(extraLines);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0.5, 0.5), area.getCentroid(), TEST_EPS);

        final List<LineConvexSubset> segments = area.getBoundaries();
        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testFromBounds_square_duplicateLines_1_oe() {
        // arrange
        final List<Line> duplicateLines = new ArrayList<>();
        duplicateLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));
        duplicateLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));

        // act
        final ConvexArea area = ConvexArea.fromBounds(duplicateLines);

        // assert
        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testFromBounds_square_duplicateLines_2_oe() {
        // arrange
        final List<Line> duplicateLines = new ArrayList<>();
        duplicateLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));
        duplicateLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));

        // act
        final ConvexArea area = ConvexArea.fromBounds(duplicateLines);

        // assert
        // removed other assertion
        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFromBounds_square_duplicateLines_3_oe() {
        // arrange
        final List<Line> duplicateLines = new ArrayList<>();
        duplicateLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));
        duplicateLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));

        // act
        final ConvexArea area = ConvexArea.fromBounds(duplicateLines);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(4, area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFromBounds_square_duplicateLines_4_oe() {
        // arrange
        final List<Line> duplicateLines = new ArrayList<>();
        duplicateLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));
        duplicateLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));

        // act
        final ConvexArea area = ConvexArea.fromBounds(duplicateLines);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(1, area.getSize(), TEST_EPS);
    }

    @Test
    void testFromBounds_square_duplicateLines_5_oe() {
        // arrange
        final List<Line> duplicateLines = new ArrayList<>();
        duplicateLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));
        duplicateLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));

        // act
        final ConvexArea area = ConvexArea.fromBounds(duplicateLines);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.of(0.5, 0.5), area.getCentroid(), TEST_EPS);

        final List<LineConvexSubset> segments = area.getBoundaries();
        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testFromBounds_duplicateLines_similarOrientation_1_oe() {
        // arrange
        final Line a = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);
        final Line c = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);

        // act
        final ConvexArea area = ConvexArea.fromBounds(a, b, c);

        // assert
        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testFromBounds_duplicateLines_similarOrientation_2_oe() {
        // arrange
        final Line a = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);
        final Line c = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);

        // act
        final ConvexArea area = ConvexArea.fromBounds(a, b, c);

        // assert
        // removed other assertion
        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFromBounds_duplicateLines_similarOrientation_3_oe() {
        // arrange
        final Line a = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);
        final Line c = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);

        // act
        final ConvexArea area = ConvexArea.fromBounds(a, b, c);

        // assert
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertPositiveInfinity(area.getBoundarySize());
        GeometryTestUtils.assertPositiveInfinity(area.getSize());
        Assertions.assertNull(area.getCentroid());
    }

    @Test
    void testFromBounds_duplicateLines_similarOrientation_4_oe() {
        // arrange
        final Line a = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);
        final Line c = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);

        // act
        final ConvexArea area = ConvexArea.fromBounds(a, b, c);

        // assert
        // removed other assertion
        // removed other assertion

        GeometryTestUtils.assertPositiveInfinity(area.getBoundarySize());
        GeometryTestUtils.assertPositiveInfinity(area.getSize());
        // removed other assertion

        final List<LineConvexSubset> segments = area.getBoundaries();
        Assertions.assertEquals(1, segments.size());
    }

    @Test
    void testFromBounds_duplicateLines_differentOrientation_1_oe() {
        // arrange
        final Line a = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.of(0, 1), Math.PI, TEST_PRECISION);
        final Line c = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);

        // act/assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> ConvexArea.fromBounds(a, b, c));
    }

    @Test
    void testFromBounds_boundsDoNotProduceAConvexRegion_1_oe() {
        // act/assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> ConvexArea.fromBounds(Arrays.asList( Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION), Lines.fromPointAndAngle(Vector2D.of(0, -1), Math.PI, TEST_PRECISION), Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION) )));
    }

}
