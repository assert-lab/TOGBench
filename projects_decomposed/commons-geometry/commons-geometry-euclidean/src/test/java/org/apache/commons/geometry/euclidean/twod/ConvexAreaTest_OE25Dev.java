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

import static org.junit.jupiter.api.Assertions.fail;

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
        final ConvexArea area = ConvexArea.full();

        Assertions.assertTrue(area.isFull());
    }

    @Test
    void testFull_2_oe() {
        final ConvexArea area = ConvexArea.full();

        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFull_3_oe() {
        final ConvexArea area = ConvexArea.full();


        Assertions.assertEquals(0.0, area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFull_5_oe() {
        final ConvexArea area = ConvexArea.full();


        Assertions.assertNull(area.getCentroid());
    }

    @Test
    void testFull_6_oe() {
        final ConvexArea area = ConvexArea.full();


        Assertions.assertNull(area.getBounds());
    }

    @Test
    void testBoundaryStream_1_oe() {
        final Line line = Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION);
        final ConvexArea area = ConvexArea.fromBounds(line);

        final List<LineConvexSubset> segments = area.boundaryStream().collect(Collectors.toList());

        Assertions.assertEquals(1, segments.size());
    }

    @Test
    void testBoundaryStream_2_oe() {
        final Line line = Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION);
        final ConvexArea area = ConvexArea.fromBounds(line);

        final List<LineConvexSubset> segments = area.boundaryStream().collect(Collectors.toList());

        final LineConvexSubset segment = segments.get(0);
        Assertions.assertNull(segment.getStartPoint());
    }

    @Test
    void testBoundaryStream_3_oe() {
        final Line line = Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION);
        final ConvexArea area = ConvexArea.fromBounds(line);

        final List<LineConvexSubset> segments = area.boundaryStream().collect(Collectors.toList());

        final LineConvexSubset segment = segments.get(0);
        Assertions.assertNull(segment.getEndPoint());
    }

    @Test
    void testBoundaryStream_4_oe() {
        final Line line = Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION);
        final ConvexArea area = ConvexArea.fromBounds(line);

        final List<LineConvexSubset> segments = area.boundaryStream().collect(Collectors.toList());

        final LineConvexSubset segment = segments.get(0);
        Assertions.assertSame(line, segment.getLine());
    }

    @Test
    void testBoundaryStream_full_1_oe() {
        final ConvexArea area = ConvexArea.full();

        final List<LineConvexSubset> segments = area.boundaryStream().collect(Collectors.toList());

        Assertions.assertEquals(0, segments.size());
    }

    @Test
    void testToList_1_oe() {
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(0, 1)
                ), TEST_PRECISION);

        final BoundaryList2D list = area.toList();

        Assertions.assertEquals(3, list.count());
    }

    @Test
    void testToList_2_oe() {
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(0, 1)
                ), TEST_PRECISION);

        final BoundaryList2D list = area.toList();

        Assertions.assertEquals(area.getBoundaries(), list.getBoundaries());
    }

    @Test
    void testToList_full_1_oe() {
        final ConvexArea area = ConvexArea.full();

        final BoundaryList2D list = area.toList();

        Assertions.assertEquals(0, list.count());
    }

    @Test
    void testToTree_1_oe() {
        final ConvexArea area = ConvexArea.fromBounds(
                    Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(1, 0), Angle.PI_OVER_TWO, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(1, 1), Math.PI, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(0, 1), -Angle.PI_OVER_TWO, TEST_PRECISION)
                );

        final RegionBSPTree2D tree = area.toTree();

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testToTree_2_oe() {
        final ConvexArea area = ConvexArea.fromBounds(
                    Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(1, 0), Angle.PI_OVER_TWO, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(1, 1), Math.PI, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(0, 1), -Angle.PI_OVER_TWO, TEST_PRECISION)
                );

        final RegionBSPTree2D tree = area.toTree();

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testToTree_3_oe() {
        final ConvexArea area = ConvexArea.fromBounds(
                    Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(1, 0), Angle.PI_OVER_TWO, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(1, 1), Math.PI, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(0, 1), -Angle.PI_OVER_TWO, TEST_PRECISION)
                );

        final RegionBSPTree2D tree = area.toTree();


        Assertions.assertEquals(1, tree.getSize(), TEST_EPS);
    }

    @Test
    void testToTree_full_1_oe() {
        final ConvexArea area = ConvexArea.full();

        final RegionBSPTree2D tree = area.toTree();

        Assertions.assertTrue(tree.isFull());
    }

    @Test
    void testToTree_full_2_oe() {
        final ConvexArea area = ConvexArea.full();

        final RegionBSPTree2D tree = area.toTree();

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testTransform_full_1_oe() {
        final AffineTransformMatrix2D transform = AffineTransformMatrix2D.createScale(3);
        final ConvexArea area = ConvexArea.full();

        final ConvexArea transformed = area.transform(transform);

        Assertions.assertSame(area, transformed);
    }

    @Test
    void testTransform_infinite_1_oe() {
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D
                .createRotation(Vector2D.of(0, 1), Angle.PI_OVER_TWO)
                .scale(Vector2D.of(3, 2));

        final ConvexArea area = ConvexArea.fromBounds(
                Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION),
                Lines.fromPointAndAngle(Vector2D.ZERO, -0.25 * Math.PI, TEST_PRECISION));

        final ConvexArea transformed = area.transform(mat);

        Assertions.assertNotSame(area, transformed);
    }

    @Test
    void testTransform_infinite_2_oe() {
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D
                .createRotation(Vector2D.of(0, 1), Angle.PI_OVER_TWO)
                .scale(Vector2D.of(3, 2));

        final ConvexArea area = ConvexArea.fromBounds(
                Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION),
                Lines.fromPointAndAngle(Vector2D.ZERO, -0.25 * Math.PI, TEST_PRECISION));

        final ConvexArea transformed = area.transform(mat);


        final List<LinePath> paths = transformed.getBoundaryPaths();
        Assertions.assertEquals(1, paths.size());
    }

    @Test
    void testTransform_infinite_3_oe() {
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D
                .createRotation(Vector2D.of(0, 1), Angle.PI_OVER_TWO)
                .scale(Vector2D.of(3, 2));

        final ConvexArea area = ConvexArea.fromBounds(
                Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION),
                Lines.fromPointAndAngle(Vector2D.ZERO, -0.25 * Math.PI, TEST_PRECISION));

        final ConvexArea transformed = area.transform(mat);


        final List<LinePath> paths = transformed.getBoundaryPaths();

        final List<LineConvexSubset> segments = paths.get(0).getElements();
        Assertions.assertEquals(2, segments.size());
    }

    @Test
    void testTransform_infinite_4_oe() {
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D
                .createRotation(Vector2D.of(0, 1), Angle.PI_OVER_TWO)
                .scale(Vector2D.of(3, 2));

        final ConvexArea area = ConvexArea.fromBounds(
                Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION),
                Lines.fromPointAndAngle(Vector2D.ZERO, -0.25 * Math.PI, TEST_PRECISION));

        final ConvexArea transformed = area.transform(mat);


        final List<LinePath> paths = transformed.getBoundaryPaths();

        final List<LineConvexSubset> segments = paths.get(0).getElements();

        final LineConvexSubset firstSegment = segments.get(0);
        Assertions.assertNull(firstSegment.getStartPoint());
    }

    @Test
    void testTransform_infinite_6_oe() {
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D
                .createRotation(Vector2D.of(0, 1), Angle.PI_OVER_TWO)
                .scale(Vector2D.of(3, 2));

        final ConvexArea area = ConvexArea.fromBounds(
                Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION),
                Lines.fromPointAndAngle(Vector2D.ZERO, -0.25 * Math.PI, TEST_PRECISION));

        final ConvexArea transformed = area.transform(mat);


        final List<LinePath> paths = transformed.getBoundaryPaths();

        final List<LineConvexSubset> segments = paths.get(0).getElements();

        final LineConvexSubset firstSegment = segments.get(0);
        Assertions.assertEquals(Math.atan2(2, 3), firstSegment.getLine().getAngle(), TEST_EPS);
    }

    @Test
    void testTransform_infinite_8_oe() {
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D
                .createRotation(Vector2D.of(0, 1), Angle.PI_OVER_TWO)
                .scale(Vector2D.of(3, 2));

        final ConvexArea area = ConvexArea.fromBounds(
                Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION),
                Lines.fromPointAndAngle(Vector2D.ZERO, -0.25 * Math.PI, TEST_PRECISION));

        final ConvexArea transformed = area.transform(mat);


        final List<LinePath> paths = transformed.getBoundaryPaths();

        final List<LineConvexSubset> segments = paths.get(0).getElements();

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);
        Assertions.assertNull(secondSegment.getEndPoint());
    }

    @Test
    void testTransform_infinite_9_oe() {
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D
                .createRotation(Vector2D.of(0, 1), Angle.PI_OVER_TWO)
                .scale(Vector2D.of(3, 2));

        final ConvexArea area = ConvexArea.fromBounds(
                Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION),
                Lines.fromPointAndAngle(Vector2D.ZERO, -0.25 * Math.PI, TEST_PRECISION));

        final ConvexArea transformed = area.transform(mat);


        final List<LinePath> paths = transformed.getBoundaryPaths();

        final List<LineConvexSubset> segments = paths.get(0).getElements();

        final LineConvexSubset firstSegment = segments.get(0);

        final LineConvexSubset secondSegment = segments.get(1);
        Assertions.assertEquals(Math.atan2(2, -3), secondSegment.getLine().getAngle(), TEST_EPS);
    }

    @Test
    void testTransform_finite_1_oe() {
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D.createScale(Vector2D.of(1, 2));

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1),
                    Vector2D.of(2, 2), Vector2D.of(1, 2)
                ), TEST_PRECISION);

        final ConvexArea transformed = area.transform(mat);

        Assertions.assertNotSame(area, transformed);
    }

    @Test
    void testTransform_finite_2_oe() {
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D.createScale(Vector2D.of(1, 2));

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1),
                    Vector2D.of(2, 2), Vector2D.of(1, 2)
                ), TEST_PRECISION);

        final ConvexArea transformed = area.transform(mat);


        final List<LineConvexSubset> segments = transformed.getBoundaries();
        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testTransform_finite_3_oe() {
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D.createScale(Vector2D.of(1, 2));

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1),
                    Vector2D.of(2, 2), Vector2D.of(1, 2)
                ), TEST_PRECISION);

        final ConvexArea transformed = area.transform(mat);


        final List<LineConvexSubset> segments = transformed.getBoundaries();

        Assertions.assertEquals(2, transformed.getSize(), TEST_EPS);
    }

    @Test
    void testTransform_finite_4_oe() {
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D.createScale(Vector2D.of(1, 2));

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1),
                    Vector2D.of(2, 2), Vector2D.of(1, 2)
                ), TEST_PRECISION);

        final ConvexArea transformed = area.transform(mat);


        final List<LineConvexSubset> segments = transformed.getBoundaries();

        Assertions.assertEquals(6, transformed.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testTransform_finite_withSingleReflection_1_oe() {
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D.createScale(Vector2D.of(-1, 2));

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1),
                    Vector2D.of(2, 2), Vector2D.of(1, 2)
                ), TEST_PRECISION);

        final ConvexArea transformed = area.transform(mat);

        Assertions.assertNotSame(area, transformed);
    }

    @Test
    void testTransform_finite_withSingleReflection_2_oe() {
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D.createScale(Vector2D.of(-1, 2));

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1),
                    Vector2D.of(2, 2), Vector2D.of(1, 2)
                ), TEST_PRECISION);

        final ConvexArea transformed = area.transform(mat);


        final List<LineConvexSubset> segments = transformed.getBoundaries();
        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testTransform_finite_withSingleReflection_3_oe() {
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D.createScale(Vector2D.of(-1, 2));

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1),
                    Vector2D.of(2, 2), Vector2D.of(1, 2)
                ), TEST_PRECISION);

        final ConvexArea transformed = area.transform(mat);


        final List<LineConvexSubset> segments = transformed.getBoundaries();

        Assertions.assertEquals(2, transformed.getSize(), TEST_EPS);
    }

    @Test
    void testTransform_finite_withSingleReflection_4_oe() {
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D.createScale(Vector2D.of(-1, 2));

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1),
                    Vector2D.of(2, 2), Vector2D.of(1, 2)
                ), TEST_PRECISION);

        final ConvexArea transformed = area.transform(mat);


        final List<LineConvexSubset> segments = transformed.getBoundaries();

        Assertions.assertEquals(6, transformed.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testTransform_finite_withDoubleReflection_1_oe() {
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D.createScale(Vector2D.of(-1, -2));

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1),
                    Vector2D.of(2, 2), Vector2D.of(1, 2)
                ), TEST_PRECISION);

        final ConvexArea transformed = area.transform(mat);

        Assertions.assertNotSame(area, transformed);
    }

    @Test
    void testTransform_finite_withDoubleReflection_2_oe() {
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D.createScale(Vector2D.of(-1, -2));

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1),
                    Vector2D.of(2, 2), Vector2D.of(1, 2)
                ), TEST_PRECISION);

        final ConvexArea transformed = area.transform(mat);


        final List<LineConvexSubset> segments = transformed.getBoundaries();
        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testTransform_finite_withDoubleReflection_3_oe() {
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D.createScale(Vector2D.of(-1, -2));

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1),
                    Vector2D.of(2, 2), Vector2D.of(1, 2)
                ), TEST_PRECISION);

        final ConvexArea transformed = area.transform(mat);


        final List<LineConvexSubset> segments = transformed.getBoundaries();

        Assertions.assertEquals(2, transformed.getSize(), TEST_EPS);
    }

    @Test
    void testTransform_finite_withDoubleReflection_4_oe() {
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D.createScale(Vector2D.of(-1, -2));

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1),
                    Vector2D.of(2, 2), Vector2D.of(1, 2)
                ), TEST_PRECISION);

        final ConvexArea transformed = area.transform(mat);


        final List<LineConvexSubset> segments = transformed.getBoundaries();

        Assertions.assertEquals(6, transformed.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testGetVertices_full_1_oe() {
        final ConvexArea area = ConvexArea.full();

        Assertions.assertEquals(0, area.getVertices().size());
    }

    @Test
    void testGetVertices_twoParallelLines_1_oe() {
        final ConvexArea area = ConvexArea.fromBounds(
                    Lines.fromPointAndAngle(Vector2D.of(0, 1), Math.PI, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(0, -1), 0.0, TEST_PRECISION)
                );

        Assertions.assertEquals(0, area.getVertices().size());
    }

    @Test
    void testGetVertices_infiniteWithVertices_1_oe() {
        final ConvexArea area = ConvexArea.fromBounds(
                    Lines.fromPointAndAngle(Vector2D.of(0, 1), Math.PI, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(0, -1), 0.0, TEST_PRECISION),
                    Lines.fromPointAndAngle(Vector2D.of(1, 0), Angle.PI_OVER_TWO, TEST_PRECISION)
                );

        final List<Vector2D> vertices = area.getVertices();

        Assertions.assertEquals(2, vertices.size());
    }

    @Test
    void testGetVertices_finite_1_oe() {
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO,
                    Vector2D.Unit.PLUS_X,
                    Vector2D.Unit.PLUS_Y
                ), TEST_PRECISION);

        final List<Vector2D> vertices = area.getVertices();

        Assertions.assertEquals(3, vertices.size());
    }

    @Test
    void testGetVertices_mismatchedEndpoints_1_oe() {

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

        final List<Vector2D> vertices = area.getVertices();

        Assertions.assertEquals(Arrays.asList(p1, p2, p3, p5), vertices);
    }

    @Test
    void testGetBounds_infinite_1_oe() {
        Assertions.assertNull(ConvexArea.full().getBounds());
    }

    @Test
    void testGetBounds_infinite_2_oe() {
        Assertions.assertNull(ConvexArea.fromBounds(Lines.fromPointAndAngle(Vector2D.ZERO,Angle.PI_OVER_TWO,TEST_PRECISION)).getBounds());
    }

    @Test
    void testProject_full_1_oe() {
        final ConvexArea area = ConvexArea.full();

        Assertions.assertNull(area.project(Vector2D.ZERO));
    }

    @Test
    void testProject_full_2_oe() {
        final ConvexArea area = ConvexArea.full();

        Assertions.assertNull(area.project(Vector2D.Unit.PLUS_X));
    }

    @Test
    void testTrim_full_1_oe() {
        final ConvexArea area = ConvexArea.full();
        final Segment segment = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_Y, TEST_PRECISION);

        final LineConvexSubset trimmed = area.trim(segment);

        Assertions.assertSame(segment, trimmed);
    }

    @Test
    void testTrim_segmentOutsideOfRegion_1_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.ZERO, 1, 1));
        final LineConvexSubset segment = Lines.fromPoints(Vector2D.of(-0.5, 0), Vector2D.of(-0.5, 1), TEST_PRECISION).span();

        final LineConvexSubset trimmed = area.trim(segment);

        Assertions.assertNull(trimmed);
    }

    @Test
    void testTrim_segmentDirectlyOnBoundaryOfRegion_1_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.ZERO, 1, 1));
        final LineConvexSubset segment = Lines.fromPoints(Vector2D.of(1, 0), Vector2D.of(1, 1), TEST_PRECISION).span();

        final LineConvexSubset trimmed = area.trim(segment);

        Assertions.assertNull(trimmed);
    }

    @Test
    void testSplit_full_1_oe() {
        final ConvexArea input = ConvexArea.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);

        final Split<ConvexArea> split = input.split(splitter);

        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_full_2_oe() {
        final ConvexArea input = ConvexArea.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);

        final Split<ConvexArea> split = input.split(splitter);


        final ConvexArea minus = split.getMinus();
        Assertions.assertFalse(minus.isFull());
    }

    @Test
    void testSplit_full_3_oe() {
        final ConvexArea input = ConvexArea.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);

        final Split<ConvexArea> split = input.split(splitter);


        final ConvexArea minus = split.getMinus();
        Assertions.assertFalse(minus.isEmpty());
    }

    @Test
    void testSplit_full_6_oe() {
        final ConvexArea input = ConvexArea.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);

        final Split<ConvexArea> split = input.split(splitter);


        final ConvexArea minus = split.getMinus();

        Assertions.assertNull(minus.getCentroid());
    }

    @Test
    void testSplit_full_7_oe() {
        final ConvexArea input = ConvexArea.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);

        final Split<ConvexArea> split = input.split(splitter);


        final ConvexArea minus = split.getMinus();


        final List<LineConvexSubset> minusSegments = minus.getBoundaries();
        Assertions.assertEquals(1, minusSegments.size());
    }

    @Test
    void testSplit_full_8_oe() {
        final ConvexArea input = ConvexArea.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);

        final Split<ConvexArea> split = input.split(splitter);


        final ConvexArea minus = split.getMinus();


        final List<LineConvexSubset> minusSegments = minus.getBoundaries();
        Assertions.assertEquals(splitter, minusSegments.get(0).getLine());
    }

    @Test
    void testSplit_full_9_oe() {
        final ConvexArea input = ConvexArea.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);

        final Split<ConvexArea> split = input.split(splitter);


        final ConvexArea minus = split.getMinus();


        final List<LineConvexSubset> minusSegments = minus.getBoundaries();

        final ConvexArea plus = split.getPlus();
        Assertions.assertFalse(plus.isFull());
    }

    @Test
    void testSplit_full_10_oe() {
        final ConvexArea input = ConvexArea.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);

        final Split<ConvexArea> split = input.split(splitter);


        final ConvexArea minus = split.getMinus();


        final List<LineConvexSubset> minusSegments = minus.getBoundaries();

        final ConvexArea plus = split.getPlus();
        Assertions.assertFalse(plus.isEmpty());
    }

    @Test
    void testSplit_full_13_oe() {
        final ConvexArea input = ConvexArea.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);

        final Split<ConvexArea> split = input.split(splitter);


        final ConvexArea minus = split.getMinus();


        final List<LineConvexSubset> minusSegments = minus.getBoundaries();

        final ConvexArea plus = split.getPlus();

        Assertions.assertNull(plus.getCentroid());
    }

    @Test
    void testSplit_full_14_oe() {
        final ConvexArea input = ConvexArea.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);

        final Split<ConvexArea> split = input.split(splitter);


        final ConvexArea minus = split.getMinus();


        final List<LineConvexSubset> minusSegments = minus.getBoundaries();

        final ConvexArea plus = split.getPlus();


        final List<LineConvexSubset> plusSegments = plus.getBoundaries();
        Assertions.assertEquals(1, plusSegments.size());
    }

    @Test
    void testSplit_full_15_oe() {
        final ConvexArea input = ConvexArea.full();

        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);

        final Split<ConvexArea> split = input.split(splitter);


        final ConvexArea minus = split.getMinus();


        final List<LineConvexSubset> minusSegments = minus.getBoundaries();

        final ConvexArea plus = split.getPlus();


        final List<LineConvexSubset> plusSegments = plus.getBoundaries();
        Assertions.assertEquals(splitter, plusSegments.get(0).getLine().reverse());
    }

    @Test
    void testSplit_halfSpace_split_1_oe() {
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);

        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_halfSpace_split_2_oe() {
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();
        Assertions.assertFalse(minus.isFull());
    }

    @Test
    void testSplit_halfSpace_split_3_oe() {
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();
        Assertions.assertFalse(minus.isEmpty());
    }

    @Test
    void testSplit_halfSpace_split_6_oe() {
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();

        Assertions.assertNull(minus.getCentroid());
    }

    @Test
    void testSplit_halfSpace_split_7_oe() {
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();


        Assertions.assertEquals(2, minus.getBoundaries().size());
    }

    @Test
    void testSplit_halfSpace_split_8_oe() {
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();



        final ConvexArea plus = split.getPlus();
        Assertions.assertFalse(plus.isFull());
    }

    @Test
    void testSplit_halfSpace_split_9_oe() {
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();



        final ConvexArea plus = split.getPlus();
        Assertions.assertFalse(plus.isEmpty());
    }

    @Test
    void testSplit_halfSpace_split_12_oe() {
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();



        final ConvexArea plus = split.getPlus();

        Assertions.assertNull(plus.getCentroid());
    }

    @Test
    void testSplit_halfSpace_split_13_oe() {
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.ZERO, 0.25 * Math.PI, TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();



        final ConvexArea plus = split.getPlus();


        Assertions.assertEquals(2, plus.getBoundaries().size());
    }

    @Test
    void testSplit_halfSpace_splitOnBoundary_1_oe() {
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);

        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());
    }

    @Test
    void testSplit_halfSpace_splitOnBoundary_2_oe() {
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        Assertions.assertSame(area, split.getMinus());
    }

    @Test
    void testSplit_halfSpace_splitOnBoundary_3_oe() {
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplit_halfSpace_splitOnBoundaryWithReversedSplitter_1_oe() {
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION).reverse();

        final Split<ConvexArea> split = area.split(splitter);

        Assertions.assertEquals(SplitLocation.PLUS, split.getLocation());
    }

    @Test
    void testSplit_halfSpace_splitOnBoundaryWithReversedSplitter_2_oe() {
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION).reverse();

        final Split<ConvexArea> split = area.split(splitter);


        Assertions.assertNull(split.getMinus());
    }

    @Test
    void testSplit_halfSpace_splitOnBoundaryWithReversedSplitter_3_oe() {
        final ConvexArea area = ConvexArea.fromBounds(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));
        final Line splitter = Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION).reverse();

        final Split<ConvexArea> split = area.split(splitter);


        Assertions.assertSame(area, split.getPlus());
    }

    @Test
    void testSplit_square_split_1_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 2, 1));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(2, 1), Angle.PI_OVER_TWO, TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);

        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_square_split_2_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 2, 1));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(2, 1), Angle.PI_OVER_TWO, TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();
        Assertions.assertFalse(minus.isFull());
    }

    @Test
    void testSplit_square_split_3_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 2, 1));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(2, 1), Angle.PI_OVER_TWO, TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();
        Assertions.assertFalse(minus.isEmpty());
    }

    @Test
    void testSplit_square_split_4_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 2, 1));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(2, 1), Angle.PI_OVER_TWO, TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();

        Assertions.assertEquals(4, minus.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testSplit_square_split_5_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 2, 1));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(2, 1), Angle.PI_OVER_TWO, TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();

        Assertions.assertEquals(1, minus.getSize(), TEST_EPS);
    }

    @Test
    void testSplit_square_split_7_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 2, 1));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(2, 1), Angle.PI_OVER_TWO, TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();


        Assertions.assertEquals(4, minus.getBoundaries().size());
    }

    @Test
    void testSplit_square_split_8_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 2, 1));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(2, 1), Angle.PI_OVER_TWO, TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();



        final ConvexArea plus = split.getPlus();
        Assertions.assertFalse(plus.isFull());
    }

    @Test
    void testSplit_square_split_9_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 2, 1));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(2, 1), Angle.PI_OVER_TWO, TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();



        final ConvexArea plus = split.getPlus();
        Assertions.assertFalse(plus.isEmpty());
    }

    @Test
    void testSplit_square_split_10_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 2, 1));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(2, 1), Angle.PI_OVER_TWO, TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();



        final ConvexArea plus = split.getPlus();

        Assertions.assertEquals(4, plus.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testSplit_square_split_11_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 2, 1));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(2, 1), Angle.PI_OVER_TWO, TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();



        final ConvexArea plus = split.getPlus();

        Assertions.assertEquals(1, plus.getSize(), TEST_EPS);
    }

    @Test
    void testSplit_square_split_13_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 2, 1));
        final Line splitter = Lines.fromPointAndAngle(Vector2D.of(2, 1), Angle.PI_OVER_TWO, TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();



        final ConvexArea plus = split.getPlus();


        Assertions.assertEquals(4, plus.getBoundaries().size());
    }

    @Test
    void testSplit_square_splitOnVertices_1_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);

        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_square_splitOnVertices_2_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();
        Assertions.assertFalse(minus.isFull());
    }

    @Test
    void testSplit_square_splitOnVertices_3_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();
        Assertions.assertFalse(minus.isEmpty());
    }

    @Test
    void testSplit_square_splitOnVertices_4_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();

        Assertions.assertEquals(2 + Math.sqrt(2), minus.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testSplit_square_splitOnVertices_5_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();

        Assertions.assertEquals(0.5, minus.getSize(), TEST_EPS);
    }

    @Test
    void testSplit_square_splitOnVertices_7_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();


        Assertions.assertEquals(3, minus.getBoundaries().size());
    }

    @Test
    void testSplit_square_splitOnVertices_8_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();



        final ConvexArea plus = split.getPlus();
        Assertions.assertFalse(plus.isFull());
    }

    @Test
    void testSplit_square_splitOnVertices_9_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();



        final ConvexArea plus = split.getPlus();
        Assertions.assertFalse(plus.isEmpty());
    }

    @Test
    void testSplit_square_splitOnVertices_10_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();



        final ConvexArea plus = split.getPlus();

        Assertions.assertEquals(2 + Math.sqrt(2), plus.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testSplit_square_splitOnVertices_11_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();



        final ConvexArea plus = split.getPlus();

        Assertions.assertEquals(0.5, plus.getSize(), TEST_EPS);
    }

    @Test
    void testSplit_square_splitOnVertices_13_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();



        final ConvexArea plus = split.getPlus();


        Assertions.assertEquals(3, plus.getBoundaries().size());
    }

    @Test
    void testSplit_square_splitOnVerticesWithReversedSplitter_1_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).reverse();

        final Split<ConvexArea> split = area.split(splitter);

        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_square_splitOnVerticesWithReversedSplitter_2_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).reverse();

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();
        Assertions.assertFalse(minus.isFull());
    }

    @Test
    void testSplit_square_splitOnVerticesWithReversedSplitter_3_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).reverse();

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();
        Assertions.assertFalse(minus.isEmpty());
    }

    @Test
    void testSplit_square_splitOnVerticesWithReversedSplitter_4_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).reverse();

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();

        Assertions.assertEquals(2 + Math.sqrt(2), minus.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testSplit_square_splitOnVerticesWithReversedSplitter_5_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).reverse();

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();

        Assertions.assertEquals(0.5, minus.getSize(), TEST_EPS);
    }

    @Test
    void testSplit_square_splitOnVerticesWithReversedSplitter_7_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).reverse();

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();


        Assertions.assertEquals(3, minus.getBoundaries().size());
    }

    @Test
    void testSplit_square_splitOnVerticesWithReversedSplitter_8_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).reverse();

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();



        final ConvexArea plus = split.getPlus();
        Assertions.assertFalse(plus.isFull());
    }

    @Test
    void testSplit_square_splitOnVerticesWithReversedSplitter_9_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).reverse();

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();



        final ConvexArea plus = split.getPlus();
        Assertions.assertFalse(plus.isEmpty());
    }

    @Test
    void testSplit_square_splitOnVerticesWithReversedSplitter_10_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).reverse();

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();



        final ConvexArea plus = split.getPlus();

        Assertions.assertEquals(2 + Math.sqrt(2), plus.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testSplit_square_splitOnVerticesWithReversedSplitter_11_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).reverse();

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();



        final ConvexArea plus = split.getPlus();

        Assertions.assertEquals(0.5, plus.getSize(), TEST_EPS);
    }

    @Test
    void testSplit_square_splitOnVerticesWithReversedSplitter_13_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(2, 2), TEST_PRECISION).reverse();

        final Split<ConvexArea> split = area.split(splitter);


        final ConvexArea minus = split.getMinus();



        final ConvexArea plus = split.getPlus();


        Assertions.assertEquals(3, plus.getBoundaries().size());
    }

    @Test
    void testSplit_square_entirelyOnMinus_1_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(3, 1), Vector2D.of(3, 2), TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);

        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());
    }

    @Test
    void testSplit_square_entirelyOnMinus_2_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(3, 1), Vector2D.of(3, 2), TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);

        Assertions.assertSame(area, split.getMinus());
    }

    @Test
    void testSplit_square_entirelyOnMinus_3_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(3, 1), Vector2D.of(3, 2), TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);

        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplit_square_onMinusBoundary_1_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(2, 1), Vector2D.of(2, 2), TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);

        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());
    }

    @Test
    void testSplit_square_onMinusBoundary_2_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(2, 1), Vector2D.of(2, 2), TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);

        Assertions.assertSame(area, split.getMinus());
    }

    @Test
    void testSplit_square_onMinusBoundary_3_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(2, 1), Vector2D.of(2, 2), TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);

        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplit_square_entirelyOnPlus_1_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(0, 1), Vector2D.of(0, 2), TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);

        Assertions.assertEquals(SplitLocation.PLUS, split.getLocation());
    }

    @Test
    void testSplit_square_entirelyOnPlus_2_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(0, 1), Vector2D.of(0, 2), TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);

        Assertions.assertNull(split.getMinus());
    }

    @Test
    void testSplit_square_entirelyOnPlus_3_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(0, 1), Vector2D.of(0, 2), TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);

        Assertions.assertSame(area, split.getPlus());
    }

    @Test
    void testSplit_square_onPlusBoundary_1_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(1, 2), TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);

        Assertions.assertEquals(SplitLocation.PLUS, split.getLocation());
    }

    @Test
    void testSplit_square_onPlusBoundary_2_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(1, 2), TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);

        Assertions.assertNull(split.getMinus());
    }

    @Test
    void testSplit_square_onPlusBoundary_3_oe() {
        final ConvexArea area = ConvexArea.fromBounds(createSquareBoundingLines(Vector2D.of(1, 1), 1, 1));
        final Line splitter = Lines.fromPoints(Vector2D.of(1, 1), Vector2D.of(1, 2), TEST_PRECISION);

        final Split<ConvexArea> split = area.split(splitter);

        Assertions.assertSame(area, split.getPlus());
    }

    @Test
    void testSplit_fannedLines_1_oe() {
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

        final Split<ConvexArea> split = area.split(splitter);

        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());
    }

    @Test
    void testSplit_fannedLines_2_oe() {
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

        final Split<ConvexArea> split = area.split(splitter);

        Assertions.assertSame(area, split.getMinus());
    }

    @Test
    void testSplit_fannedLines_3_oe() {
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

        final Split<ConvexArea> split = area.split(splitter);

        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplit_trimmedSplitterDiscrepancy_1_oe() {

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

        final Split<ConvexArea> minusSplit = area.split(splitter);
        final Split<ConvexArea> plusSplit = area.split(splitter.reverse());

        Assertions.assertEquals(SplitLocation.MINUS, minusSplit.getLocation());
    }

    @Test
    void testSplit_trimmedSplitterDiscrepancy_2_oe() {

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

        final Split<ConvexArea> minusSplit = area.split(splitter);
        final Split<ConvexArea> plusSplit = area.split(splitter.reverse());


        Assertions.assertSame(area, minusSplit.getMinus());
    }

    @Test
    void testSplit_trimmedSplitterDiscrepancy_3_oe() {

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

        final Split<ConvexArea> minusSplit = area.split(splitter);
        final Split<ConvexArea> plusSplit = area.split(splitter.reverse());


        Assertions.assertNull(minusSplit.getPlus());
    }

    @Test
    void testSplit_trimmedSplitterDiscrepancy_4_oe() {

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

        final Split<ConvexArea> minusSplit = area.split(splitter);
        final Split<ConvexArea> plusSplit = area.split(splitter.reverse());



        Assertions.assertEquals(SplitLocation.PLUS, plusSplit.getLocation());
    }

    @Test
    void testSplit_trimmedSplitterDiscrepancy_5_oe() {

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

        final Split<ConvexArea> minusSplit = area.split(splitter);
        final Split<ConvexArea> plusSplit = area.split(splitter.reverse());




        Assertions.assertNull(plusSplit.getMinus());
    }

    @Test
    void testSplit_trimmedSplitterDiscrepancy_6_oe() {

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

        final Split<ConvexArea> minusSplit = area.split(splitter);
        final Split<ConvexArea> plusSplit = area.split(splitter.reverse());




        Assertions.assertSame(area, plusSplit.getPlus());
    }

    @Test
    void testToString_1_oe() {
        final ConvexArea area = ConvexArea.full();

        final String str = area.toString();

        Assertions.assertTrue(str.contains("ConvexArea"));
    }

    @Test
    void testToString_2_oe() {
        final ConvexArea area = ConvexArea.full();

        final String str = area.toString();

        Assertions.assertTrue(str.contains("boundaries= "));
    }

    @Test
    void testConvexPolygonFromVertices_triangle_1_oe() {
        final Vector2D p0 = Vector2D.of(1, 2);
        final Vector2D p1 = Vector2D.of(2, 2);
        final Vector2D p2 = Vector2D.of(2, 3);

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(p0, p1, p2), TEST_PRECISION);

        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testConvexPolygonFromVertices_triangle_2_oe() {
        final Vector2D p0 = Vector2D.of(1, 2);
        final Vector2D p1 = Vector2D.of(2, 2);
        final Vector2D p2 = Vector2D.of(2, 3);

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(p0, p1, p2), TEST_PRECISION);

        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testConvexPolygonFromVertices_triangle_3_oe() {
        final Vector2D p0 = Vector2D.of(1, 2);
        final Vector2D p1 = Vector2D.of(2, 2);
        final Vector2D p2 = Vector2D.of(2, 3);

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(p0, p1, p2), TEST_PRECISION);


        Assertions.assertEquals(0.5, area.getSize(), TEST_EPS);
    }

    @Test
    void testConvexPolygonFromVertices_triangle_4_oe() {
        final Vector2D p0 = Vector2D.of(1, 2);
        final Vector2D p1 = Vector2D.of(2, 2);
        final Vector2D p2 = Vector2D.of(2, 3);

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(p0, p1, p2), TEST_PRECISION);


        Assertions.assertEquals(2 + Math.sqrt(2), area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testConvexPolygonFromVertices_square_closeRequired_1_oe() {
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO,
                    Vector2D.Unit.PLUS_X,
                    Vector2D.of(1, 1),
                    Vector2D.of(0, 1)
                ), TEST_PRECISION);

        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testConvexPolygonFromVertices_square_closeRequired_2_oe() {
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO,
                    Vector2D.Unit.PLUS_X,
                    Vector2D.of(1, 1),
                    Vector2D.of(0, 1)
                ), TEST_PRECISION);

        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testConvexPolygonFromVertices_square_closeRequired_3_oe() {
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO,
                    Vector2D.Unit.PLUS_X,
                    Vector2D.of(1, 1),
                    Vector2D.of(0, 1)
                ), TEST_PRECISION);


        Assertions.assertEquals(1, area.getSize(), TEST_EPS);
    }

    @Test
    void testConvexPolygonFromVertices_square_closeRequired_4_oe() {
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO,
                    Vector2D.Unit.PLUS_X,
                    Vector2D.of(1, 1),
                    Vector2D.of(0, 1)
                ), TEST_PRECISION);


        Assertions.assertEquals(4, area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testConvexPolygonFromVertices_square_closeNotRequired_1_oe() {
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO,
                    Vector2D.Unit.PLUS_X,
                    Vector2D.of(1, 1),
                    Vector2D.of(0, 1),
                    Vector2D.ZERO
                ), TEST_PRECISION);

        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testConvexPolygonFromVertices_square_closeNotRequired_2_oe() {
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO,
                    Vector2D.Unit.PLUS_X,
                    Vector2D.of(1, 1),
                    Vector2D.of(0, 1),
                    Vector2D.ZERO
                ), TEST_PRECISION);

        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testConvexPolygonFromVertices_square_closeNotRequired_3_oe() {
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO,
                    Vector2D.Unit.PLUS_X,
                    Vector2D.of(1, 1),
                    Vector2D.of(0, 1),
                    Vector2D.ZERO
                ), TEST_PRECISION);


        Assertions.assertEquals(1, area.getSize(), TEST_EPS);
    }

    @Test
    void testConvexPolygonFromVertices_square_closeNotRequired_4_oe() {
        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.ZERO,
                    Vector2D.Unit.PLUS_X,
                    Vector2D.of(1, 1),
                    Vector2D.of(0, 1),
                    Vector2D.ZERO
                ), TEST_PRECISION);


        Assertions.assertEquals(4, area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testConvexPolygonFromVertices_handlesDuplicatePoints_1_oe() {
        final double eps = 1e-3;
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(eps);

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

        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testConvexPolygonFromVertices_handlesDuplicatePoints_2_oe() {
        final double eps = 1e-3;
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(eps);

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

        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testConvexPolygonFromVertices_handlesDuplicatePoints_3_oe() {
        final double eps = 1e-3;
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(eps);

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


        Assertions.assertEquals(1, area.getSize(), eps);
    }

    @Test
    void testConvexPolygonFromVertices_handlesDuplicatePoints_4_oe() {
        final double eps = 1e-3;
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(eps);

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


        Assertions.assertEquals(4, area.getBoundarySize(), eps);
    }

    @Test
    void testConvexPolygonFromPath_1_oe() {
        final ConvexArea area = ConvexArea.convexPolygonFromPath(LinePath.fromVertexLoop(
                Arrays.asList(
                        Vector2D.ZERO,
                        Vector2D.Unit.PLUS_X,
                        Vector2D.of(1, 1),
                        Vector2D.Unit.PLUS_Y
                ), TEST_PRECISION));

        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testConvexPolygonFromPath_2_oe() {
        final ConvexArea area = ConvexArea.convexPolygonFromPath(LinePath.fromVertexLoop(
                Arrays.asList(
                        Vector2D.ZERO,
                        Vector2D.Unit.PLUS_X,
                        Vector2D.of(1, 1),
                        Vector2D.Unit.PLUS_Y
                ), TEST_PRECISION));

        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testConvexPolygonFromPath_3_oe() {
        final ConvexArea area = ConvexArea.convexPolygonFromPath(LinePath.fromVertexLoop(
                Arrays.asList(
                        Vector2D.ZERO,
                        Vector2D.Unit.PLUS_X,
                        Vector2D.of(1, 1),
                        Vector2D.Unit.PLUS_Y
                ), TEST_PRECISION));


        Assertions.assertEquals(1, area.getSize(), TEST_EPS);
    }

    @Test
    void testConvexPolygonFromPath_4_oe() {
        final ConvexArea area = ConvexArea.convexPolygonFromPath(LinePath.fromVertexLoop(
                Arrays.asList(
                        Vector2D.ZERO,
                        Vector2D.Unit.PLUS_X,
                        Vector2D.of(1, 1),
                        Vector2D.Unit.PLUS_Y
                ), TEST_PRECISION));


        Assertions.assertEquals(4, area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFromBounds_noLines_1_oe() {
        final ConvexArea area = ConvexArea.fromBounds(Collections.emptyList());

        Assertions.assertSame(ConvexArea.full(), area);
    }

    @Test
    void testFromBounds_singleLine_1_oe() {
        final Line line = Lines.fromPoints(Vector2D.of(0, 1), Vector2D.of(1, 3), TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(line);

        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testFromBounds_singleLine_2_oe() {
        final Line line = Lines.fromPoints(Vector2D.of(0, 1), Vector2D.of(1, 3), TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(line);

        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFromBounds_singleLine_5_oe() {
        final Line line = Lines.fromPoints(Vector2D.of(0, 1), Vector2D.of(1, 3), TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(line);


        Assertions.assertNull(area.getCentroid());
    }

    @Test
    void testFromBounds_singleLine_6_oe() {
        final Line line = Lines.fromPoints(Vector2D.of(0, 1), Vector2D.of(1, 3), TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(line);



        final List<LineConvexSubset> segments = area.getBoundaries();
        Assertions.assertEquals(1, segments.size());
    }

    @Test
    void testFromBounds_singleLine_7_oe() {
        final Line line = Lines.fromPoints(Vector2D.of(0, 1), Vector2D.of(1, 3), TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(line);



        final List<LineConvexSubset> segments = area.getBoundaries();
        Assertions.assertSame(line, segments.get(0).getLine());
    }

    @Test
    void testFromBounds_twoLines_1_oe() {
        final Line a = Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.ZERO, Math.PI, TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(a, b);

        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testFromBounds_twoLines_2_oe() {
        final Line a = Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.ZERO, Math.PI, TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(a, b);

        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFromBounds_twoLines_5_oe() {
        final Line a = Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.ZERO, Math.PI, TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(a, b);


        Assertions.assertNull(area.getCentroid());
    }

    @Test
    void testFromBounds_twoLines_6_oe() {
        final Line a = Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.ZERO, Math.PI, TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(a, b);



        final List<LineConvexSubset> segments = area.getBoundaries();
        Assertions.assertEquals(2, segments.size());
    }

    @Test
    void testFromBounds_triangle_1_oe() {
        final Line a = Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.ZERO, Math.PI, TEST_PRECISION);
        final Line c = Lines.fromPointAndAngle(Vector2D.of(-2, 0), -0.25 * Math.PI, TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(a, b, c);

        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testFromBounds_triangle_2_oe() {
        final Line a = Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.ZERO, Math.PI, TEST_PRECISION);
        final Line c = Lines.fromPointAndAngle(Vector2D.of(-2, 0), -0.25 * Math.PI, TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(a, b, c);

        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFromBounds_triangle_3_oe() {
        final Line a = Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.ZERO, Math.PI, TEST_PRECISION);
        final Line c = Lines.fromPointAndAngle(Vector2D.of(-2, 0), -0.25 * Math.PI, TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(a, b, c);


        Assertions.assertEquals(4 + (2 * Math.sqrt(2)), area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFromBounds_triangle_4_oe() {
        final Line a = Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.ZERO, Math.PI, TEST_PRECISION);
        final Line c = Lines.fromPointAndAngle(Vector2D.of(-2, 0), -0.25 * Math.PI, TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(a, b, c);


        Assertions.assertEquals(2, area.getSize(), TEST_EPS);
    }

    @Test
    void testFromBounds_triangle_6_oe() {
        final Line a = Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.ZERO, Math.PI, TEST_PRECISION);
        final Line c = Lines.fromPointAndAngle(Vector2D.of(-2, 0), -0.25 * Math.PI, TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(a, b, c);



        final List<LineConvexSubset> segments = area.getBoundaries();
        Assertions.assertEquals(3, segments.size());
    }

    @Test
    void testFromBounds_square_1_oe() {
        final List<Line> square = createSquareBoundingLines(Vector2D.ZERO, 1, 1);

        final ConvexArea area = ConvexArea.fromBounds(square);

        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testFromBounds_square_2_oe() {
        final List<Line> square = createSquareBoundingLines(Vector2D.ZERO, 1, 1);

        final ConvexArea area = ConvexArea.fromBounds(square);

        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFromBounds_square_3_oe() {
        final List<Line> square = createSquareBoundingLines(Vector2D.ZERO, 1, 1);

        final ConvexArea area = ConvexArea.fromBounds(square);


        Assertions.assertEquals(4, area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFromBounds_square_4_oe() {
        final List<Line> square = createSquareBoundingLines(Vector2D.ZERO, 1, 1);

        final ConvexArea area = ConvexArea.fromBounds(square);


        Assertions.assertEquals(1, area.getSize(), TEST_EPS);
    }

    @Test
    void testFromBounds_square_6_oe() {
        final List<Line> square = createSquareBoundingLines(Vector2D.ZERO, 1, 1);

        final ConvexArea area = ConvexArea.fromBounds(square);



        final List<LineConvexSubset> segments = area.getBoundaries();
        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testFromBounds_square_extraLines_1_oe() {
        final List<Line> extraLines = new ArrayList<>();
        extraLines.add(Lines.fromPoints(Vector2D.of(10, 10), Vector2D.of(10, 11), TEST_PRECISION));
        extraLines.add(Lines.fromPoints(Vector2D.of(-10, 10), Vector2D.of(-10, 9), TEST_PRECISION));
        extraLines.add(Lines.fromPoints(Vector2D.of(0, 10), Vector2D.of(-1, 11), TEST_PRECISION));
        extraLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));

        final ConvexArea area = ConvexArea.fromBounds(extraLines);

        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testFromBounds_square_extraLines_2_oe() {
        final List<Line> extraLines = new ArrayList<>();
        extraLines.add(Lines.fromPoints(Vector2D.of(10, 10), Vector2D.of(10, 11), TEST_PRECISION));
        extraLines.add(Lines.fromPoints(Vector2D.of(-10, 10), Vector2D.of(-10, 9), TEST_PRECISION));
        extraLines.add(Lines.fromPoints(Vector2D.of(0, 10), Vector2D.of(-1, 11), TEST_PRECISION));
        extraLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));

        final ConvexArea area = ConvexArea.fromBounds(extraLines);

        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFromBounds_square_extraLines_3_oe() {
        final List<Line> extraLines = new ArrayList<>();
        extraLines.add(Lines.fromPoints(Vector2D.of(10, 10), Vector2D.of(10, 11), TEST_PRECISION));
        extraLines.add(Lines.fromPoints(Vector2D.of(-10, 10), Vector2D.of(-10, 9), TEST_PRECISION));
        extraLines.add(Lines.fromPoints(Vector2D.of(0, 10), Vector2D.of(-1, 11), TEST_PRECISION));
        extraLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));

        final ConvexArea area = ConvexArea.fromBounds(extraLines);


        Assertions.assertEquals(4, area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFromBounds_square_extraLines_4_oe() {
        final List<Line> extraLines = new ArrayList<>();
        extraLines.add(Lines.fromPoints(Vector2D.of(10, 10), Vector2D.of(10, 11), TEST_PRECISION));
        extraLines.add(Lines.fromPoints(Vector2D.of(-10, 10), Vector2D.of(-10, 9), TEST_PRECISION));
        extraLines.add(Lines.fromPoints(Vector2D.of(0, 10), Vector2D.of(-1, 11), TEST_PRECISION));
        extraLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));

        final ConvexArea area = ConvexArea.fromBounds(extraLines);


        Assertions.assertEquals(1, area.getSize(), TEST_EPS);
    }

    @Test
    void testFromBounds_square_extraLines_6_oe() {
        final List<Line> extraLines = new ArrayList<>();
        extraLines.add(Lines.fromPoints(Vector2D.of(10, 10), Vector2D.of(10, 11), TEST_PRECISION));
        extraLines.add(Lines.fromPoints(Vector2D.of(-10, 10), Vector2D.of(-10, 9), TEST_PRECISION));
        extraLines.add(Lines.fromPoints(Vector2D.of(0, 10), Vector2D.of(-1, 11), TEST_PRECISION));
        extraLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));

        final ConvexArea area = ConvexArea.fromBounds(extraLines);



        final List<LineConvexSubset> segments = area.getBoundaries();
        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testFromBounds_square_duplicateLines_1_oe() {
        final List<Line> duplicateLines = new ArrayList<>();
        duplicateLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));
        duplicateLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));

        final ConvexArea area = ConvexArea.fromBounds(duplicateLines);

        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testFromBounds_square_duplicateLines_2_oe() {
        final List<Line> duplicateLines = new ArrayList<>();
        duplicateLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));
        duplicateLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));

        final ConvexArea area = ConvexArea.fromBounds(duplicateLines);

        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFromBounds_square_duplicateLines_3_oe() {
        final List<Line> duplicateLines = new ArrayList<>();
        duplicateLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));
        duplicateLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));

        final ConvexArea area = ConvexArea.fromBounds(duplicateLines);


        Assertions.assertEquals(4, area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFromBounds_square_duplicateLines_4_oe() {
        final List<Line> duplicateLines = new ArrayList<>();
        duplicateLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));
        duplicateLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));

        final ConvexArea area = ConvexArea.fromBounds(duplicateLines);


        Assertions.assertEquals(1, area.getSize(), TEST_EPS);
    }

    @Test
    void testFromBounds_square_duplicateLines_6_oe() {
        final List<Line> duplicateLines = new ArrayList<>();
        duplicateLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));
        duplicateLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));

        final ConvexArea area = ConvexArea.fromBounds(duplicateLines);



        final List<LineConvexSubset> segments = area.getBoundaries();
        Assertions.assertEquals(4, segments.size());
    }

    @Test
    void testFromBounds_duplicateLines_similarOrientation_1_oe() {
        final Line a = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);
        final Line c = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(a, b, c);

        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testFromBounds_duplicateLines_similarOrientation_2_oe() {
        final Line a = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);
        final Line c = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(a, b, c);

        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFromBounds_duplicateLines_similarOrientation_5_oe() {
        final Line a = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);
        final Line c = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(a, b, c);


        Assertions.assertNull(area.getCentroid());
    }

    @Test
    void testFromBounds_duplicateLines_similarOrientation_6_oe() {
        final Line a = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);
        final Line c = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(a, b, c);



        final List<LineConvexSubset> segments = area.getBoundaries();
        Assertions.assertEquals(1, segments.size());
    }

    @Test
    void testFromBounds_duplicateLines_differentOrientation_1_oe() {
        final Line a = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.of(0, 1), Math.PI, TEST_PRECISION);
        final Line c = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);

        try {
    ConvexArea.fromBounds(a, b, c);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testFromBounds_boundsDoNotProduceAConvexRegion_1_oe() {
        try {
    ConvexArea.fromBounds(Arrays.asList( Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION), Lines.fromPointAndAngle(Vector2D.of(0, -1), Math.PI, TEST_PRECISION), Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION) ));
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

@Test
    void testTransform_finite_6_oe() {
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D.createScale(Vector2D.of(1, 2));

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1),
                    Vector2D.of(2, 2), Vector2D.of(1, 2)
                ), TEST_PRECISION);

        final ConvexArea transformed = area.transform(mat);


        final List<LineConvexSubset> segments = transformed.getBoundaries();


        EuclideanTestUtils.assertRegionLocation(transformed, RegionLocation.BOUNDARY, Vector2D.of(1, 2), Vector2D.of(2, 2), Vector2D.of(2, 4), Vector2D.of(1, 4));
    }

@Test
    void testTransform_finite_withSingleReflection_6_oe() {
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D.createScale(Vector2D.of(-1, 2));

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1),
                    Vector2D.of(2, 2), Vector2D.of(1, 2)
                ), TEST_PRECISION);

        final ConvexArea transformed = area.transform(mat);


        final List<LineConvexSubset> segments = transformed.getBoundaries();


        EuclideanTestUtils.assertRegionLocation(transformed, RegionLocation.BOUNDARY, Vector2D.of(-1, 2), Vector2D.of(-2, 2), Vector2D.of(-2, 4), Vector2D.of(-1, 4));
    }

@Test
    void testTransform_finite_withDoubleReflection_6_oe() {
        final AffineTransformMatrix2D mat = AffineTransformMatrix2D.createScale(Vector2D.of(-1, -2));

        final ConvexArea area = ConvexArea.convexPolygonFromVertices(Arrays.asList(
                    Vector2D.of(1, 1), Vector2D.of(2, 1),
                    Vector2D.of(2, 2), Vector2D.of(1, 2)
                ), TEST_PRECISION);

        final ConvexArea transformed = area.transform(mat);


        final List<LineConvexSubset> segments = transformed.getBoundaries();


        EuclideanTestUtils.assertRegionLocation(transformed, RegionLocation.BOUNDARY, Vector2D.of(-1, -2), Vector2D.of(-2, -2), Vector2D.of(-2, -4), Vector2D.of(-1, -4));
    }

@Test
    void testConvexPolygonFromVertices_notEnoughUniqueVertices_1_oe() {
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final Pattern unclosedPattern = Pattern.compile("Cannot construct convex polygon from unclosed path.*");
        final Pattern notEnoughElementsPattern =
                Pattern.compile("Cannot construct convex polygon from path with less than 3 elements.*");
        final Pattern nonConvexPattern = Pattern.compile("Cannot construct convex polygon from non-convex path.*");

        final Pattern singleVertexPattern =
                Pattern.compile("Unable to create line path; only a single unique vertex provided.*");

        GeometryTestUtils.assertThrowsWithMessage(() -> { ConvexArea.convexPolygonFromVertices(Collections.emptyList(), precision); }, IllegalArgumentException.class, unclosedPattern);
    }

@Test
    void testConvexPolygonFromVertices_notEnoughUniqueVertices_2_oe() {
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final Pattern unclosedPattern = Pattern.compile("Cannot construct convex polygon from unclosed path.*");
        final Pattern notEnoughElementsPattern =
                Pattern.compile("Cannot construct convex polygon from path with less than 3 elements.*");
        final Pattern nonConvexPattern = Pattern.compile("Cannot construct convex polygon from non-convex path.*");

        final Pattern singleVertexPattern =
                Pattern.compile("Unable to create line path; only a single unique vertex provided.*");


        GeometryTestUtils.assertThrowsWithMessage(() -> { ConvexArea.convexPolygonFromVertices(Collections.singletonList(Vector2D.ZERO), precision); }, IllegalStateException.class, singleVertexPattern);
    }

@Test
    void testConvexPolygonFromVertices_notEnoughUniqueVertices_3_oe() {
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final Pattern unclosedPattern = Pattern.compile("Cannot construct convex polygon from unclosed path.*");
        final Pattern notEnoughElementsPattern =
                Pattern.compile("Cannot construct convex polygon from path with less than 3 elements.*");
        final Pattern nonConvexPattern = Pattern.compile("Cannot construct convex polygon from non-convex path.*");

        final Pattern singleVertexPattern =
                Pattern.compile("Unable to create line path; only a single unique vertex provided.*");



        GeometryTestUtils.assertThrowsWithMessage(() -> { ConvexArea.convexPolygonFromVertices(Arrays.asList(Vector2D.ZERO, Vector2D.of(1e-4, 1e-4)), precision); }, IllegalStateException.class, singleVertexPattern);
    }

@Test
    void testConvexPolygonFromVertices_notEnoughUniqueVertices_4_oe() {
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final Pattern unclosedPattern = Pattern.compile("Cannot construct convex polygon from unclosed path.*");
        final Pattern notEnoughElementsPattern =
                Pattern.compile("Cannot construct convex polygon from path with less than 3 elements.*");
        final Pattern nonConvexPattern = Pattern.compile("Cannot construct convex polygon from non-convex path.*");

        final Pattern singleVertexPattern =
                Pattern.compile("Unable to create line path; only a single unique vertex provided.*");




        GeometryTestUtils.assertThrowsWithMessage(() -> { ConvexArea.convexPolygonFromVertices(Arrays.asList(Vector2D.ZERO, Vector2D.Unit.PLUS_X), precision); }, IllegalArgumentException.class, notEnoughElementsPattern);
    }

@Test
    void testConvexPolygonFromVertices_notEnoughUniqueVertices_5_oe() {
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final Pattern unclosedPattern = Pattern.compile("Cannot construct convex polygon from unclosed path.*");
        final Pattern notEnoughElementsPattern =
                Pattern.compile("Cannot construct convex polygon from path with less than 3 elements.*");
        final Pattern nonConvexPattern = Pattern.compile("Cannot construct convex polygon from non-convex path.*");

        final Pattern singleVertexPattern =
                Pattern.compile("Unable to create line path; only a single unique vertex provided.*");





        GeometryTestUtils.assertThrowsWithMessage(() -> { ConvexArea.convexPolygonFromVertices( Arrays.asList(Vector2D.ZERO, Vector2D.Unit.PLUS_X, Vector2D.of(1, 1e-4)), precision); }, IllegalArgumentException.class, notEnoughElementsPattern);
    }

@Test
    void testConvexPolygonFromVertices_notEnoughUniqueVertices_6_oe() {
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final Pattern unclosedPattern = Pattern.compile("Cannot construct convex polygon from unclosed path.*");
        final Pattern notEnoughElementsPattern =
                Pattern.compile("Cannot construct convex polygon from path with less than 3 elements.*");
        final Pattern nonConvexPattern = Pattern.compile("Cannot construct convex polygon from non-convex path.*");

        final Pattern singleVertexPattern =
                Pattern.compile("Unable to create line path; only a single unique vertex provided.*");






        GeometryTestUtils.assertThrowsWithMessage(() -> { ConvexArea.convexPolygonFromVertices( Arrays.asList(Vector2D.ZERO, Vector2D.Unit.PLUS_X, Vector2D.of(1, -1)), precision); }, IllegalArgumentException.class, nonConvexPattern);
    }

@Test
    void testConvexPolygonFromVertices_notConvex_1_oe() {
        final Pattern msgPattern = Pattern.compile("Cannot construct convex polygon from non-convex path.*");

        GeometryTestUtils.assertThrowsWithMessage(() -> { ConvexArea.convexPolygonFromVertices(Arrays.asList( Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(2, 0) ), TEST_PRECISION); }, IllegalArgumentException.class, msgPattern);
    }

@Test
    void testConvexPolygonFromVertices_notConvex_2_oe() {
        final Pattern msgPattern = Pattern.compile("Cannot construct convex polygon from non-convex path.*");


        GeometryTestUtils.assertThrowsWithMessage(() -> { ConvexArea.convexPolygonFromVertices(Arrays.asList( Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(1, -1) ), TEST_PRECISION); }, IllegalArgumentException.class, msgPattern);
    }

@Test
    void testConvexPolygonFromVertices_notConvex_3_oe() {
        final Pattern msgPattern = Pattern.compile("Cannot construct convex polygon from non-convex path.*");



        GeometryTestUtils.assertThrowsWithMessage(() -> { ConvexArea.convexPolygonFromVertices( Arrays.asList( Vector2D.ZERO, Vector2D.Unit.PLUS_Y, Vector2D.of(1, 1), Vector2D.Unit.PLUS_X ), TEST_PRECISION); }, IllegalArgumentException.class, msgPattern);
    }

@Test
    void testConvexPolygonFromVertices_notConvex_4_oe() {
        final Pattern msgPattern = Pattern.compile("Cannot construct convex polygon from non-convex path.*");




        GeometryTestUtils.assertThrowsWithMessage(() -> { ConvexArea.convexPolygonFromVertices(Arrays.asList( Vector2D.ZERO, Vector2D.of(2, 0), Vector2D.of(2, 2), Vector2D.of(1, 1), Vector2D.of(1.5, 1) ), TEST_PRECISION); }, IllegalArgumentException.class, msgPattern);
    }

@Test
    void testConvexPolygonFromPath_invalidPaths_1_oe() {
        final Pattern unclosedPattern = Pattern.compile("Cannot construct convex polygon from unclosed path.*");
        final Pattern notEnoughElementsPattern =
                Pattern.compile("Cannot construct convex polygon from path with less than 3 elements.*");
        final Pattern nonConvexPattern = Pattern.compile("Cannot construct convex polygon from non-convex path.*");

        GeometryTestUtils.assertThrowsWithMessage(() -> { ConvexArea.convexPolygonFromPath(LinePath.empty()); }, IllegalArgumentException.class, unclosedPattern);
    }

@Test
    void testConvexPolygonFromPath_invalidPaths_2_oe() {
        final Pattern unclosedPattern = Pattern.compile("Cannot construct convex polygon from unclosed path.*");
        final Pattern notEnoughElementsPattern =
                Pattern.compile("Cannot construct convex polygon from path with less than 3 elements.*");
        final Pattern nonConvexPattern = Pattern.compile("Cannot construct convex polygon from non-convex path.*");


        GeometryTestUtils.assertThrowsWithMessage(() -> { ConvexArea.convexPolygonFromPath(LinePath.fromVertices( Arrays.asList(Vector2D.ZERO, Vector2D.Unit.PLUS_X), TEST_PRECISION)); }, IllegalArgumentException.class, unclosedPattern);
    }

@Test
    void testConvexPolygonFromPath_invalidPaths_3_oe() {
        final Pattern unclosedPattern = Pattern.compile("Cannot construct convex polygon from unclosed path.*");
        final Pattern notEnoughElementsPattern =
                Pattern.compile("Cannot construct convex polygon from path with less than 3 elements.*");
        final Pattern nonConvexPattern = Pattern.compile("Cannot construct convex polygon from non-convex path.*");



        GeometryTestUtils.assertThrowsWithMessage(() -> { ConvexArea.convexPolygonFromPath(LinePath.fromVertices( Arrays.asList(Vector2D.ZERO, Vector2D.Unit.PLUS_X, Vector2D.ZERO), TEST_PRECISION)); }, IllegalArgumentException.class, notEnoughElementsPattern);
    }

@Test
    void testConvexPolygonFromPath_invalidPaths_4_oe() {
        final Pattern unclosedPattern = Pattern.compile("Cannot construct convex polygon from unclosed path.*");
        final Pattern notEnoughElementsPattern =
                Pattern.compile("Cannot construct convex polygon from path with less than 3 elements.*");
        final Pattern nonConvexPattern = Pattern.compile("Cannot construct convex polygon from non-convex path.*");




        GeometryTestUtils.assertThrowsWithMessage(() -> { ConvexArea.convexPolygonFromPath(LinePath.fromVertexLoop( Arrays.asList( Vector2D.ZERO, Vector2D.Unit.PLUS_Y, Vector2D.of(1, 1), Vector2D.Unit.PLUS_X ), TEST_PRECISION)); }, IllegalArgumentException.class, nonConvexPattern);
    }

@Test
    void testFromBounds_singleLine_8_oe() {
        final Line line = Lines.fromPoints(Vector2D.of(0, 1), Vector2D.of(1, 3), TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(line);



        final List<LineConvexSubset> segments = area.getBoundaries();

        EuclideanTestUtils.assertRegionLocation(area, RegionLocation.INSIDE, Vector2D.of(-1, 1), Vector2D.of(0, 2));
    }

@Test
    void testFromBounds_singleLine_9_oe() {
        final Line line = Lines.fromPoints(Vector2D.of(0, 1), Vector2D.of(1, 3), TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(line);



        final List<LineConvexSubset> segments = area.getBoundaries();

        EuclideanTestUtils.assertRegionLocation(area, RegionLocation.BOUNDARY, Vector2D.of(0, 1), Vector2D.of(2, 5));
    }

@Test
    void testFromBounds_singleLine_10_oe() {
        final Line line = Lines.fromPoints(Vector2D.of(0, 1), Vector2D.of(1, 3), TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(line);



        final List<LineConvexSubset> segments = area.getBoundaries();

        EuclideanTestUtils.assertRegionLocation(area, RegionLocation.OUTSIDE, Vector2D.ZERO, Vector2D.of(2, 3));
    }

@Test
    void testFromBounds_twoLines_8_oe() {
        final Line a = Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.ZERO, Math.PI, TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(a, b);



        final List<LineConvexSubset> segments = area.getBoundaries();

        EuclideanTestUtils.assertRegionLocation(area, RegionLocation.BOUNDARY, Vector2D.ZERO, Vector2D.of(-1, 0), Vector2D.of(0, -1));
    }

@Test
    void testFromBounds_twoLines_9_oe() {
        final Line a = Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.ZERO, Math.PI, TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(a, b);



        final List<LineConvexSubset> segments = area.getBoundaries();

        EuclideanTestUtils.assertRegionLocation(area, RegionLocation.OUTSIDE, Vector2D.of(-1, 1), Vector2D.of(1, 1), Vector2D.of(1, -1));
    }

@Test
    void testFromBounds_triangle_8_oe() {
        final Line a = Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.ZERO, Math.PI, TEST_PRECISION);
        final Line c = Lines.fromPointAndAngle(Vector2D.of(-2, 0), -0.25 * Math.PI, TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(a, b, c);



        final List<LineConvexSubset> segments = area.getBoundaries();

        EuclideanTestUtils.assertRegionLocation(area, RegionLocation.BOUNDARY, Vector2D.ZERO, Vector2D.of(-1, 0), Vector2D.of(0, -1));
    }

@Test
    void testFromBounds_triangle_9_oe() {
        final Line a = Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.ZERO, Math.PI, TEST_PRECISION);
        final Line c = Lines.fromPointAndAngle(Vector2D.of(-2, 0), -0.25 * Math.PI, TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(a, b, c);



        final List<LineConvexSubset> segments = area.getBoundaries();

        EuclideanTestUtils.assertRegionLocation(area, RegionLocation.OUTSIDE, Vector2D.of(-1, 1), Vector2D.of(1, 1), Vector2D.of(1, -1), Vector2D.of(-2, -2));
    }

@Test
    void testFromBounds_square_8_oe() {
        final List<Line> square = createSquareBoundingLines(Vector2D.ZERO, 1, 1);

        final ConvexArea area = ConvexArea.fromBounds(square);



        final List<LineConvexSubset> segments = area.getBoundaries();

        EuclideanTestUtils.assertRegionLocation(area, RegionLocation.BOUNDARY, Vector2D.ZERO, Vector2D.of(1, 1), Vector2D.of(0.5, 0), Vector2D.of(0.5, 1), Vector2D.of(0, 0.5), Vector2D.of(1, 0.5));
    }

@Test
    void testFromBounds_square_9_oe() {
        final List<Line> square = createSquareBoundingLines(Vector2D.ZERO, 1, 1);

        final ConvexArea area = ConvexArea.fromBounds(square);



        final List<LineConvexSubset> segments = area.getBoundaries();

        EuclideanTestUtils.assertRegionLocation(area, RegionLocation.OUTSIDE, Vector2D.of(-1, -1), Vector2D.of(2, 2));
    }

@Test
    void testFromBounds_square_extraLines_8_oe() {
        final List<Line> extraLines = new ArrayList<>();
        extraLines.add(Lines.fromPoints(Vector2D.of(10, 10), Vector2D.of(10, 11), TEST_PRECISION));
        extraLines.add(Lines.fromPoints(Vector2D.of(-10, 10), Vector2D.of(-10, 9), TEST_PRECISION));
        extraLines.add(Lines.fromPoints(Vector2D.of(0, 10), Vector2D.of(-1, 11), TEST_PRECISION));
        extraLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));

        final ConvexArea area = ConvexArea.fromBounds(extraLines);



        final List<LineConvexSubset> segments = area.getBoundaries();

        EuclideanTestUtils.assertRegionLocation(area, RegionLocation.BOUNDARY, Vector2D.ZERO, Vector2D.of(1, 1), Vector2D.of(0.5, 0), Vector2D.of(0.5, 1), Vector2D.of(0, 0.5), Vector2D.of(1, 0.5));
    }

@Test
    void testFromBounds_square_extraLines_9_oe() {
        final List<Line> extraLines = new ArrayList<>();
        extraLines.add(Lines.fromPoints(Vector2D.of(10, 10), Vector2D.of(10, 11), TEST_PRECISION));
        extraLines.add(Lines.fromPoints(Vector2D.of(-10, 10), Vector2D.of(-10, 9), TEST_PRECISION));
        extraLines.add(Lines.fromPoints(Vector2D.of(0, 10), Vector2D.of(-1, 11), TEST_PRECISION));
        extraLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));

        final ConvexArea area = ConvexArea.fromBounds(extraLines);



        final List<LineConvexSubset> segments = area.getBoundaries();

        EuclideanTestUtils.assertRegionLocation(area, RegionLocation.OUTSIDE, Vector2D.of(-1, -1), Vector2D.of(2, 2));
    }

@Test
    void testFromBounds_square_duplicateLines_8_oe() {
        final List<Line> duplicateLines = new ArrayList<>();
        duplicateLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));
        duplicateLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));

        final ConvexArea area = ConvexArea.fromBounds(duplicateLines);



        final List<LineConvexSubset> segments = area.getBoundaries();

        EuclideanTestUtils.assertRegionLocation(area, RegionLocation.BOUNDARY, Vector2D.ZERO, Vector2D.of(1, 1), Vector2D.of(0.5, 0), Vector2D.of(0.5, 1), Vector2D.of(0, 0.5), Vector2D.of(1, 0.5));
    }

@Test
    void testFromBounds_square_duplicateLines_9_oe() {
        final List<Line> duplicateLines = new ArrayList<>();
        duplicateLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));
        duplicateLines.addAll(createSquareBoundingLines(Vector2D.ZERO, 1, 1));

        final ConvexArea area = ConvexArea.fromBounds(duplicateLines);



        final List<LineConvexSubset> segments = area.getBoundaries();

        EuclideanTestUtils.assertRegionLocation(area, RegionLocation.OUTSIDE, Vector2D.of(-1, -1), Vector2D.of(2, 2));
    }

@Test
    void testFromBounds_duplicateLines_similarOrientation_7_oe() {
        final Line a = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);
        final Line c = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(a, b, c);



        final List<LineConvexSubset> segments = area.getBoundaries();

        EuclideanTestUtils.assertRegionLocation(area, RegionLocation.BOUNDARY, Vector2D.of(0, 1), Vector2D.of(1, 1), Vector2D.of(-1, 1));
    }

@Test
    void testFromBounds_duplicateLines_similarOrientation_8_oe() {
        final Line a = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);
        final Line c = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(a, b, c);



        final List<LineConvexSubset> segments = area.getBoundaries();

        EuclideanTestUtils.assertRegionLocation(area, RegionLocation.INSIDE, Vector2D.of(0, 2), Vector2D.of(1, 2), Vector2D.of(-1, 2));
    }

@Test
    void testFromBounds_duplicateLines_similarOrientation_9_oe() {
        final Line a = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);
        final Line b = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);
        final Line c = Lines.fromPointAndAngle(Vector2D.of(0, 1), 0.0, TEST_PRECISION);

        final ConvexArea area = ConvexArea.fromBounds(a, b, c);



        final List<LineConvexSubset> segments = area.getBoundaries();

        EuclideanTestUtils.assertRegionLocation(area, RegionLocation.OUTSIDE, Vector2D.of(0, 0), Vector2D.of(1, 0), Vector2D.of(-1, 0));
    }

}
