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
package org.apache.commons.geometry.euclidean.twod.path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.core.RegionLocation;
import org.apache.commons.geometry.core.partitioning.Split;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.twod.AffineTransformMatrix2D;
import org.apache.commons.geometry.euclidean.twod.Line;
import org.apache.commons.geometry.euclidean.twod.LineConvexSubset;
import org.apache.commons.geometry.euclidean.twod.LinecastChecker2D;
import org.apache.commons.geometry.euclidean.twod.Lines;
import org.apache.commons.geometry.euclidean.twod.Ray;
import org.apache.commons.geometry.euclidean.twod.RegionBSPTree2D;
import org.apache.commons.geometry.euclidean.twod.ReverseRay;
import org.apache.commons.geometry.euclidean.twod.Segment;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.geometry.euclidean.twod.path.LinePath.Builder;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LinePathTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    @Test
    void testFrom_empty() {
        // act
        final LinePath path = LinePath.from(new ArrayList<>());

        // assert
        Assertions.assertTrue(path.isEmpty());
        Assertions.assertFalse(path.isInfinite());
        Assertions.assertTrue(path.isFinite());
        Assertions.assertFalse(path.isClosed());

        Assertions.assertEquals(0, path.getSize(), TEST_EPS);

        Assertions.assertNull(path.getStart());
        Assertions.assertNull(path.getEnd());

        Assertions.assertEquals(0, path.getElements().size());

        Assertions.assertEquals(0, path.getVertexSequence().size());
    }

    @Test
    void testFrom_singleFiniteSegment() {
        // arrange
        final Segment a = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 0), TEST_PRECISION);

        // act
        final LinePath path = LinePath.from(a);

        // assert
        Assertions.assertFalse(path.isEmpty());
        Assertions.assertFalse(path.isInfinite());
        Assertions.assertTrue(path.isFinite());
        Assertions.assertFalse(path.isClosed());

        Assertions.assertEquals(1, path.getSize(), TEST_EPS);

        Assertions.assertSame(a, path.getStart());
        Assertions.assertSame(a, path.getEnd());

        final List<LineConvexSubset> segments = path.getElements();
        Assertions.assertEquals(1, segments.size());
        Assertions.assertSame(a, segments.get(0));

        Assertions.assertEquals(Arrays.asList(Vector2D.ZERO, Vector2D.of(1, 0)), path.getVertexSequence());
    }

    @Test
    void testFrom_singleInfiniteSegment() {
        // arrange
        final LineConvexSubset a = Lines.fromPoints(Vector2D.ZERO, Vector2D.of(1, 0), TEST_PRECISION).span();

        // act
        final LinePath path = LinePath.from(a);

        // assert
        Assertions.assertFalse(path.isEmpty());
        Assertions.assertTrue(path.isInfinite());
        Assertions.assertFalse(path.isFinite());
        Assertions.assertFalse(path.isClosed());

        GeometryTestUtils.assertPositiveInfinity(path.getSize());

        Assertions.assertSame(a, path.getStart());
        Assertions.assertSame(a, path.getEnd());

        final List<LineConvexSubset> segments = path.getElements();
        Assertions.assertEquals(1, segments.size());
        Assertions.assertSame(a, segments.get(0));

        Assertions.assertEquals(0, path.getVertexSequence().size());
    }

    @Test
    void testFrom_finiteSegments_notClosed() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        final Segment a = Lines.segmentFromPoints(p1, p2, TEST_PRECISION);
        final Segment b = Lines.segmentFromPoints(p2, p3, TEST_PRECISION);

        // act
        final LinePath path = LinePath.from(a, b);

        // assert
        Assertions.assertFalse(path.isEmpty());
        Assertions.assertFalse(path.isInfinite());
        Assertions.assertTrue(path.isFinite());
        Assertions.assertFalse(path.isClosed());

        Assertions.assertEquals(2, path.getSize(), TEST_EPS);

        Assertions.assertSame(a, path.getStart());
        Assertions.assertSame(b, path.getEnd());

        final List<LineConvexSubset> segments = path.getElements();
        Assertions.assertEquals(2, segments.size());
        Assertions.assertSame(a, segments.get(0));
        Assertions.assertSame(b, segments.get(1));

        Assertions.assertEquals(Arrays.asList(p1, p2, p3), path.getVertexSequence());
    }

    @Test
    void testFrom_finiteSegments_closed() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        final Segment a = Lines.segmentFromPoints(p1, p2, TEST_PRECISION);
        final Segment b = Lines.segmentFromPoints(p2, p3, TEST_PRECISION);
        final Segment c = Lines.segmentFromPoints(p3, p1, TEST_PRECISION);

        // act
        final LinePath path = LinePath.from(Arrays.asList(a, b, c));

        // assert
        Assertions.assertFalse(path.isEmpty());
        Assertions.assertFalse(path.isInfinite());
        Assertions.assertTrue(path.isFinite());
        Assertions.assertTrue(path.isClosed());

        Assertions.assertSame(a, path.getStart());
        Assertions.assertSame(c, path.getEnd());

        Assertions.assertEquals(2 + Math.sqrt(2), path.getSize(), TEST_EPS);

        final List<LineConvexSubset> segments = path.getElements();
        Assertions.assertEquals(3, segments.size());
        Assertions.assertSame(a, segments.get(0));
        Assertions.assertSame(b, segments.get(1));
        Assertions.assertSame(c, segments.get(2));

        Assertions.assertEquals(Arrays.asList(p1, p2, p3, p1), path.getVertexSequence());
    }

    @Test
    void testFrom_infiniteSegments() {
        // arrange
        final ReverseRay a = Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION)
                .reverseRayTo(1.0);
        final Ray b = Lines.fromPointAndAngle(Vector2D.of(1, 0), Angle.PI_OVER_TWO, TEST_PRECISION)
                .rayFrom(0.0);

        // act
        final LinePath path = LinePath.from(Arrays.asList(a, b));

        // assert
        Assertions.assertFalse(path.isEmpty());
        Assertions.assertTrue(path.isInfinite());
        Assertions.assertFalse(path.isFinite());
        Assertions.assertFalse(path.isClosed());

        GeometryTestUtils.assertPositiveInfinity(path.getSize());

        Assertions.assertSame(a, path.getStart());
        Assertions.assertSame(b, path.getEnd());

        final List<LineConvexSubset> segments = path.getElements();
        Assertions.assertEquals(2, segments.size());
        Assertions.assertSame(a, segments.get(0));
        Assertions.assertSame(b, segments.get(1));

        Assertions.assertEquals(Collections.singletonList(Vector2D.of(1, 0)), path.getVertexSequence());
    }

    @Test
    void testFrom_finiteAndInfiniteSegments_startInfinite() {
        // arrange
        final ReverseRay a = Lines.fromPointAndAngle(Vector2D.ZERO, 0, TEST_PRECISION).reverseRayTo(1.0);
        final Segment b = Lines.segmentFromPoints(Vector2D.of(1, 0), Vector2D.of(1, 1), TEST_PRECISION);

        // act
        final LinePath path = LinePath.from(Arrays.asList(a, b));

        // assert
        Assertions.assertFalse(path.isEmpty());
        Assertions.assertTrue(path.isInfinite());
        Assertions.assertFalse(path.isFinite());
        Assertions.assertFalse(path.isClosed());

        Assertions.assertSame(a, path.getStart());
        Assertions.assertSame(b, path.getEnd());

        final List<LineConvexSubset> segments = path.getElements();
        Assertions.assertEquals(2, segments.size());
        Assertions.assertSame(a, segments.get(0));
        Assertions.assertSame(b, segments.get(1));

        Assertions.assertEquals(Arrays.asList(Vector2D.of(1, 0), Vector2D.of(1, 1)), path.getVertexSequence());
    }

    @Test
    void testFrom_finiteAndInfiniteSegments_endInfinite() {
        // arrange
        final Segment a = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 0), TEST_PRECISION);
        final Ray b = Lines.fromPointAndAngle(Vector2D.of(1, 0), Angle.PI_OVER_TWO, TEST_PRECISION)
                .rayFrom(0.0);

        // act
        final LinePath path = LinePath.from(Arrays.asList(a, b));

        // assert
        Assertions.assertFalse(path.isEmpty());
        Assertions.assertTrue(path.isInfinite());
        Assertions.assertFalse(path.isFinite());
        Assertions.assertFalse(path.isClosed());

        Assertions.assertSame(a, path.getStart());
        Assertions.assertSame(b, path.getEnd());

        final List<LineConvexSubset> segments = path.getElements();
        Assertions.assertEquals(2, segments.size());
        Assertions.assertSame(a, segments.get(0));
        Assertions.assertSame(b, segments.get(1));

        Assertions.assertEquals(Arrays.asList(Vector2D.ZERO, Vector2D.of(1, 0)), path.getVertexSequence());
    }

    @Test
    void testFrom_segmentsNotConnected() {
        // arrange
        final Segment a = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 0), TEST_PRECISION);
        final Segment b = Lines.segmentFromPoints(Vector2D.of(1.01, 0), Vector2D.of(1, 0), TEST_PRECISION);

        final LineConvexSubset c = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION).span();
        final LineConvexSubset d = Lines.fromPointAndAngle(Vector2D.of(1, 0), Angle.PI_OVER_TWO, TEST_PRECISION).span();

        // act/assert
        Assertions.assertThrows(IllegalStateException.class, () -> LinePath.from(a, b));
        Assertions.assertThrows(IllegalStateException.class, () -> LinePath.from(c, b));
        Assertions.assertThrows(IllegalStateException.class, () -> LinePath.from(a, d));
    }

    @Test
    void testFromVertices_empty() {
        // act
        final LinePath path = LinePath.fromVertices(new ArrayList<>(), TEST_PRECISION);

        // assert
        Assertions.assertTrue(path.isEmpty());
        Assertions.assertFalse(path.isInfinite());
        Assertions.assertTrue(path.isFinite());
        Assertions.assertFalse(path.isClosed());

        Assertions.assertNull(path.getStart());
        Assertions.assertNull(path.getEnd());

        Assertions.assertEquals(0, path.getElements().size());

        Assertions.assertEquals(0, path.getVertexSequence().size());
    }

    @Test
    void testFromVertices_singleVertex_failsToCreatePath() {
        // act/assert
        Assertions.assertThrows(IllegalStateException.class, () -> LinePath.fromVertices(Collections.singletonList(Vector2D.ZERO), TEST_PRECISION));
    }

    @Test
    void testFromVertexLoop_empty() {
        // act
        final LinePath path = LinePath.fromVertexLoop(new ArrayList<>(), TEST_PRECISION);

        // assert
        Assertions.assertTrue(path.isEmpty());
        Assertions.assertFalse(path.isInfinite());
        Assertions.assertTrue(path.isFinite());
        Assertions.assertFalse(path.isClosed());

        Assertions.assertNull(path.getStart());
        Assertions.assertNull(path.getEnd());

        Assertions.assertEquals(0, path.getElements().size());

        Assertions.assertEquals(0, path.getVertexSequence().size());
    }

    @Test
    void testFromVertexLoop_singleVertex_failsToCreatePath() {
        // act/assert
        Assertions.assertThrows(IllegalStateException.class, () -> LinePath.fromVertexLoop(Collections.singletonList(Vector2D.ZERO), TEST_PRECISION));
    }

    @Test
    void testGetElements_listIsNotModifiable() {
        // arrange
        final Segment a = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 0), TEST_PRECISION);
        final List<LineConvexSubset> inputSegments = new ArrayList<>(Collections.singletonList(a));

        // act
        final LinePath path = LinePath.from(inputSegments);

        inputSegments.clear();

        // assert
        Assertions.assertNotSame(inputSegments, path.getElements());
        Assertions.assertEquals(1, path.getElements().size());

        Assertions.assertThrows(UnsupportedOperationException.class, () -> path.getElements().add(a));
    }

    @Test
    void testBoundaryStream() {
        // arrange
        final Segment seg = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 0), TEST_PRECISION);
        final LinePath path = LinePath.from(Collections.singletonList(seg));

        // act
        final List<LineConvexSubset> segments = path.boundaryStream().collect(Collectors.toList());

        // assert
        Assertions.assertEquals(1, segments.size());
        Assertions.assertSame(seg, segments.get(0));
    }

    @Test
    void testBoundaryStream_empty() {
        // arrange
        final LinePath path = LinePath.empty();

        // act
        final List<LineConvexSubset> segments = path.boundaryStream().collect(Collectors.toList());

        // assert
        Assertions.assertEquals(0, segments.size());
    }

    @Test
    void testTransform_empty() {
        // arrange
        final LinePath path = LinePath.empty();
        final AffineTransformMatrix2D t = AffineTransformMatrix2D.createTranslation(Vector2D.Unit.PLUS_X);

        // act/assert
        Assertions.assertSame(path, path.transform(t));
    }

    @Test
    void testTransform_infinite() {
        // arrange
        final LinePath path = LinePath.from(
                Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_Y, TEST_PRECISION).span());

        final AffineTransformMatrix2D t = AffineTransformMatrix2D.createTranslation(Vector2D.Unit.PLUS_X);

        // act
        final LinePath result = path.transform(t);

        // assert
        Assertions.assertNotSame(path, result);
        Assertions.assertFalse(result.isClosed());
        Assertions.assertFalse(result.isFinite());

        final List<LineConvexSubset> segments = result.getElements();

        Assertions.assertEquals(1, segments.size());
        final LineConvexSubset segment = segments.get(0);
        Assertions.assertTrue(segment.isInfinite());
        Assertions.assertNull(segment.getStartPoint());
        Assertions.assertNull(segment.getEndPoint());
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.Unit.PLUS_X, segment.getLine().getOrigin(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.Unit.PLUS_Y, segment.getLine().getDirection(), TEST_EPS);
    }

    @Test
    void testReverse_empty() {
        // arrange
        final LinePath path = LinePath.empty();

        // act/assert
        Assertions.assertSame(path, path.reverse());
    }

    @Test
    void testReverse_singleInfinite() {
        // arrange
        final LinePath path = LinePath.from(
                Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_Y, TEST_PRECISION).span());

        // act
        final LinePath result = path.reverse();

        // assert
        Assertions.assertNotSame(path, result);
        Assertions.assertFalse(result.isClosed());
        Assertions.assertFalse(result.isFinite());

        final List<LineConvexSubset> segments = result.getElements();

        Assertions.assertEquals(1, segments.size());
        final LineConvexSubset segment = segments.get(0);
        Assertions.assertTrue(segment.isInfinite());
        Assertions.assertNull(segment.getStartPoint());
        Assertions.assertNull(segment.getEndPoint());
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.Unit.ZERO, segment.getLine().getOrigin(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.Unit.MINUS_Y, segment.getLine().getDirection(), TEST_EPS);
    }

    @Test
    void testReverse_doubleInfinite() {
        // arrange
        final LineConvexSubset a = Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_Y, TEST_PRECISION).reverseRayTo(Vector2D.ZERO);
        final LineConvexSubset b = Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION).rayFrom(Vector2D.ZERO);

        final LinePath path = LinePath.from(a, b);

        // act
        final LinePath result = path.reverse();

        // assert
        Assertions.assertNotSame(path, result);
        Assertions.assertFalse(result.isClosed());
        Assertions.assertFalse(result.isFinite());

        final List<LineConvexSubset> segments = result.getElements();
        Assertions.assertEquals(2, segments.size());

        final LineConvexSubset bResult = segments.get(0);
        Assertions.assertTrue(bResult.isInfinite());
        Assertions.assertNull(bResult.getStartPoint());
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.ZERO, bResult.getEndPoint(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.ZERO, bResult.getLine().getOrigin(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.Unit.MINUS_X, bResult.getLine().getDirection(), TEST_EPS);

        final LineConvexSubset aResult = segments.get(1);
        Assertions.assertTrue(aResult.isInfinite());
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.ZERO, aResult.getStartPoint(), TEST_EPS);
        Assertions.assertNull(aResult.getEndPoint());
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.ZERO, aResult.getLine().getOrigin(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector2D.Unit.MINUS_Y, aResult.getLine().getDirection(), TEST_EPS);
    }

    @Test
    void testToTree() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.ZERO, Vector2D.Unit.PLUS_X, Vector2D.of(1, 1), Vector2D.of(0, 1))
                .close();

        // act
        final RegionBSPTree2D tree = path.toTree();

        // assert
        Assertions.assertEquals(1, tree.getSize(), TEST_EPS);
        Assertions.assertEquals(4, tree.getBoundarySize(), TEST_EPS);

        Assertions.assertEquals(RegionLocation.INSIDE, tree.classify(Vector2D.of(0.5, 0.5)));

        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(Vector2D.of(0.5, -1)));
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(Vector2D.of(0.5, 2)));
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(Vector2D.of(-1, 0.5)));
        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.classify(Vector2D.of(2, 0.5)));
    }

    @Test
    void testSimplify_empty() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder.build();

        // act
        final LinePath result = path.simplify();

        // assert
        Assertions.assertNotSame(path, result);
        Assertions.assertFalse(result.isClosed());
        Assertions.assertFalse(result.isInfinite());

        final List<LineConvexSubset> segments = result.getElements();
        Assertions.assertEquals(0, segments.size());
    }

    @Test
    void testSimplify_infiniteSegment() {
        // arrange
        final Line line = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);

        final Builder builder = LinePath.builder(TEST_PRECISION);
        final LinePath path = builder
                .append(line.span())
                .build();

        // act
        final LinePath result = path.simplify();

        // assert
        Assertions.assertNotSame(path, result);
        Assertions.assertFalse(result.isClosed());
        Assertions.assertTrue(result.isInfinite());

        Assertions.assertNotNull(path.getStart());
        Assertions.assertNotNull(path.getEnd());
        Assertions.assertSame(path.getStart(), path.getEnd());

        final List<LineConvexSubset> segments = result.getElements();
        Assertions.assertEquals(1, segments.size());
        Assertions.assertSame(line, segments.get(0).getLine());
    }

    @Test
    void testSimplify_combinedInfiniteSegment() {
        // arrange
        final Line line = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);
        final Split<LineConvexSubset> split = line.span().split(
                Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO, TEST_PRECISION));

        final Builder builder = LinePath.builder(TEST_PRECISION);
        final LinePath path = builder
                .append(split.getMinus())
                .append(split.getPlus())
                .build();

        // act
        final LinePath result = path.simplify();

        // assert
        Assertions.assertNotSame(path, result);
        Assertions.assertFalse(result.isClosed());
        Assertions.assertTrue(result.isInfinite());

        Assertions.assertNotNull(result.getStart());
        Assertions.assertNotNull(result.getEnd());
        Assertions.assertSame(result.getStart(), result.getEnd());

        final List<LineConvexSubset> segments = result.getElements();
        Assertions.assertEquals(1, segments.size());
        Assertions.assertSame(line, segments.get(0).getLine());
    }

    @Test
    void testSimplify_subsequentCallsToReturnedObjectReturnSameObject() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);
        final LinePath path = builder.appendVertices(
                    Vector2D.ZERO,
                    Vector2D.of(1, 0),
                    Vector2D.of(2, 0))
                .build();

        // act
        final LinePath result = path.simplify();

        // assert
        Assertions.assertNotSame(path, result);
        Assertions.assertSame(result, result.simplify());
    }

    @Test
    void testLinecast_empty() {
        // arrange
        final LinePath path = LinePath.empty();

        // act/assert
        LinecastChecker2D.with(path)
            .expectNothing()
            .whenGiven(Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));

        LinecastChecker2D.with(path)
            .expectNothing()
            .whenGiven(Lines.segmentFromPoints(Vector2D.Unit.MINUS_X, Vector2D.Unit.PLUS_X, TEST_PRECISION));
    }

    @Test
    void testLinecast() {
        // arrange
        final LinePath path = LinePath.fromVertexLoop(Arrays.asList(
                    Vector2D.ZERO, Vector2D.of(1, 0),
                    Vector2D.of(1, 1), Vector2D.of(0, 1)
                ), TEST_PRECISION);

        // act/assert
        LinecastChecker2D.with(path)
            .expectNothing()
            .whenGiven(Lines.fromPoints(Vector2D.of(0, 5), Vector2D.of(1, 6), TEST_PRECISION));

        LinecastChecker2D.with(path)
            .expect(Vector2D.ZERO, Vector2D.Unit.MINUS_X)
            .and(Vector2D.ZERO, Vector2D.Unit.MINUS_Y)
            .and(Vector2D.of(1, 1), Vector2D.Unit.PLUS_Y)
            .and(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X)
            .whenGiven(Lines.fromPoints(Vector2D.ZERO, Vector2D.of(1, 1), TEST_PRECISION));

        LinecastChecker2D.with(path)
            .expect(Vector2D.of(1, 1), Vector2D.Unit.PLUS_Y)
            .and(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X)
            .whenGiven(Lines.segmentFromPoints(Vector2D.of(0.5, 0.5), Vector2D.of(1, 1), TEST_PRECISION));
    }

    @Test
    void testToString() {
        // arrange
        final Line yAxis = Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_Y, TEST_PRECISION);
        final Line xAxis = Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);

        final LinePath empty = LinePath.empty();

        final LinePath singleFullSegment = LinePath.from(xAxis.span());
        final LinePath singleFiniteSegment = LinePath.from(
                Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION));

        final LinePath startOpenPath = LinePath.builder(TEST_PRECISION)
                .append(xAxis.reverseRayTo(Vector2D.Unit.PLUS_X))
                .append(Vector2D.of(1, 1))
                .build();

        final LinePath endOpenPath = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.of(0, 1))
                .append(Vector2D.ZERO)
                .append(xAxis.rayFrom(Vector2D.ZERO))
                .build();

        final LinePath doubleOpenPath = LinePath.from(yAxis.reverseRayTo(Vector2D.ZERO),
                xAxis.rayFrom(Vector2D.ZERO));

        final LinePath nonOpenPath = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.ZERO)
                .append(Vector2D.Unit.PLUS_X)
                .append(Vector2D.of(1, 1))
                .build();

        // act/assert
        final String emptyStr = empty.toString();
        GeometryTestUtils.assertContains("LinePath[empty= true", emptyStr);

        final String singleFullStr = singleFullSegment.toString();
        GeometryTestUtils.assertContains("LinePath[single= LineSpanningSubset[", singleFullStr);

        final String singleFiniteStr = singleFiniteSegment.toString();
        GeometryTestUtils.assertContains("LinePath[single= Segment[", singleFiniteStr);

        final String startOpenStr = startOpenPath.toString();
        GeometryTestUtils.assertContains("LinePath[startDirection= ", startOpenStr);
        GeometryTestUtils.assertContains("vertexSequence=", startOpenStr);

        final String endOpenStr = endOpenPath.toString();
        GeometryTestUtils.assertContains("LinePath[vertexSequence= ", endOpenStr);
        GeometryTestUtils.assertContains("endDirection= ", endOpenStr);

        final String doubleOpenStr = doubleOpenPath.toString();
        GeometryTestUtils.assertContains("startDirection= ", doubleOpenStr);
        GeometryTestUtils.assertContains("vertexSequence= ", doubleOpenStr);
        GeometryTestUtils.assertContains("endDirection= ", doubleOpenStr);

        final String nonOpenStr = nonOpenPath.toString();
        GeometryTestUtils.assertContains("LinePath[vertexSequence= ", nonOpenStr);
    }

    @Test
    void testBuilder_prependAndAppend_segments() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(1, 0);

        final Segment a = Lines.segmentFromPoints(p1, p2, TEST_PRECISION);
        final Segment b = Lines.segmentFromPoints(p2, p3, TEST_PRECISION);
        final Segment c = Lines.segmentFromPoints(p3, p4, TEST_PRECISION);
        final Segment d = Lines.segmentFromPoints(p4, p1, TEST_PRECISION);

        final Builder builder = LinePath.builder(null);

        // act
        builder.prepend(b)
            .append(c)
            .prepend(a)
            .append(d);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        Assertions.assertEquals(4, segments.size());
        Assertions.assertSame(a, segments.get(0));
        Assertions.assertSame(b, segments.get(1));
        Assertions.assertSame(c, segments.get(2));
        Assertions.assertSame(d, segments.get(3));
    }

    @Test
    void testBuilder_prependAndAppend_disconnectedSegments() {
        // arrange
        final Segment a = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 0), TEST_PRECISION);

        final Builder builder = LinePath.builder(null);
        builder.append(a);

        // act
        Assertions.assertThrows(IllegalStateException.class, () -> builder.append(a));
        Assertions.assertThrows(IllegalStateException.class, () -> builder.prepend(a));
    }

    @Test
    void testBuilder_prependAndAppend_noPrecisionSpecified() {
        // arrange
        final Vector2D p = Vector2D.ZERO;
        final Builder builder = LinePath.builder(null);

        final String msg = "Unable to create line segment: no vertex precision specified";

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.append(p);
        }, IllegalStateException.class, msg);

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            builder.prepend(p);
        }, IllegalStateException.class, msg);
    }

    @Test
    void testBuilder_prependAndAppend_addingToInfinitePath() {
        // arrange
        final Vector2D p = Vector2D.Unit.PLUS_X;
        final Builder builder = LinePath.builder(TEST_PRECISION);

        builder.append(Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION).span());

        // act/assert
        Assertions.assertThrows(IllegalStateException.class, () -> builder.prepend(p));
        Assertions.assertThrows(IllegalStateException.class, () -> builder.append(p));
    }

    @Test
    void testBuilder_close_infiniteSegmentAtStart() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);

        builder.append(Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION)
                .reverseRayTo(1))
            .append(Vector2D.of(1, 1));

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(builder::close, IllegalStateException.class,
                "Unable to close line path: line path is infinite");
    }

    @Test
    void testBuilder_close_infiniteSegmentAtEnd() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);

        builder
            .append(Vector2D.ZERO)
            .append(Vector2D.Unit.PLUS_X)
            .append(Lines.fromPointAndAngle(Vector2D.Unit.PLUS_X, Angle.PI_OVER_TWO, TEST_PRECISION)
                .rayFrom(0));

        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(builder::close, IllegalStateException.class,
                "Unable to close line path: line path is infinite");
    }

    @Test
    void testBuilder_close_emptyPath() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        final LinePath path = builder.close();

        // assert
        Assertions.assertEquals(0, path.getElements().size());
    }

    private static void assertFiniteSegment(final LineConvexSubset segment, final Vector2D start, final Vector2D end) {
        Assertions.assertFalse(segment.isInfinite());
        Assertions.assertTrue(segment.isFinite());

        EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_twoVertices_5_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final LineConvexSubset segment = path.getStart();
        final Vector2D start = p1;
        final Vector2D end = p2;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testFromVertices_twoVertices_5_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final LineConvexSubset segment = path.getStart();
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testFromVertices_twoVertices_5_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final LineConvexSubset segment = path.getStart();
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_twoVertices_5_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final LineConvexSubset segment = path.getStart();
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_twoVertices_8_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testFromVertices_twoVertices_8_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testFromVertices_twoVertices_8_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_twoVertices_8_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_multipleVertices_notClosed_5_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final LineConvexSubset segment = path.getStart();
        final Vector2D start = p1;
        final Vector2D end = p2;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testFromVertices_multipleVertices_notClosed_5_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final LineConvexSubset segment = path.getStart();
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testFromVertices_multipleVertices_notClosed_5_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final LineConvexSubset segment = path.getStart();
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_multipleVertices_notClosed_5_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final LineConvexSubset segment = path.getStart();
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_multipleVertices_notClosed_6_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final LineConvexSubset segment = path.getEnd();
        final Vector2D start = p3;
        final Vector2D end = p4;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testFromVertices_multipleVertices_notClosed_6_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final LineConvexSubset segment = path.getEnd();
        final Vector2D start = p3;
        final Vector2D end = p4;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testFromVertices_multipleVertices_notClosed_6_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final LineConvexSubset segment = path.getEnd();
        final Vector2D start = p3;
        final Vector2D end = p4;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_multipleVertices_notClosed_6_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final LineConvexSubset segment = path.getEnd();
        final Vector2D start = p3;
        final Vector2D end = p4;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_multipleVertices_notClosed_8_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testFromVertices_multipleVertices_notClosed_8_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testFromVertices_multipleVertices_notClosed_8_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_multipleVertices_notClosed_8_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_multipleVertices_notClosed_9_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testFromVertices_multipleVertices_notClosed_9_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testFromVertices_multipleVertices_notClosed_9_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_multipleVertices_notClosed_9_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_multipleVertices_notClosed_10_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p4;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testFromVertices_multipleVertices_notClosed_10_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p4;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testFromVertices_multipleVertices_notClosed_10_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p4;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_multipleVertices_notClosed_10_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p4;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_multipleVertices_closed_5_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4, p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final LineConvexSubset segment = path.getStart();
        final Vector2D start = p1;
        final Vector2D end = p2;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testFromVertices_multipleVertices_closed_5_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4, p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final LineConvexSubset segment = path.getStart();
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testFromVertices_multipleVertices_closed_5_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4, p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final LineConvexSubset segment = path.getStart();
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_multipleVertices_closed_5_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4, p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final LineConvexSubset segment = path.getStart();
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_multipleVertices_closed_6_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4, p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final LineConvexSubset segment = path.getEnd();
        final Vector2D start = p4;
        final Vector2D end = p1;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testFromVertices_multipleVertices_closed_6_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4, p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final LineConvexSubset segment = path.getEnd();
        final Vector2D start = p4;
        final Vector2D end = p1;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testFromVertices_multipleVertices_closed_6_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4, p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final LineConvexSubset segment = path.getEnd();
        final Vector2D start = p4;
        final Vector2D end = p1;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_multipleVertices_closed_6_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4, p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
                final LineConvexSubset segment = path.getEnd();
        final Vector2D start = p4;
        final Vector2D end = p1;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_multipleVertices_closed_8_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4, p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testFromVertices_multipleVertices_closed_8_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4, p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testFromVertices_multipleVertices_closed_8_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4, p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_multipleVertices_closed_8_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4, p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_multipleVertices_closed_9_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4, p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testFromVertices_multipleVertices_closed_9_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4, p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testFromVertices_multipleVertices_closed_9_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4, p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_multipleVertices_closed_9_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4, p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_multipleVertices_closed_10_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4, p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p4;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testFromVertices_multipleVertices_closed_10_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4, p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p4;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testFromVertices_multipleVertices_closed_10_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4, p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p4;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_multipleVertices_closed_10_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4, p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p4;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_multipleVertices_closed_11_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4, p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(3);
        final Vector2D start = p4;
        final Vector2D end = p1;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testFromVertices_multipleVertices_closed_11_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4, p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(3);
        final Vector2D start = p4;
        final Vector2D end = p1;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testFromVertices_multipleVertices_closed_11_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4, p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(3);
        final Vector2D start = p4;
        final Vector2D end = p1;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_multipleVertices_closed_11_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        // act
        final LinePath path = LinePath.fromVertices(Arrays.asList(p1, p2, p3, p4, p1), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(3);
        final Vector2D start = p4;
        final Vector2D end = p1;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertexLoop_closeRequired_6_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        // act
        final LinePath path = LinePath.fromVertexLoop(Arrays.asList(p1, p2, p3), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testFromVertexLoop_closeRequired_6_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        // act
        final LinePath path = LinePath.fromVertexLoop(Arrays.asList(p1, p2, p3), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testFromVertexLoop_closeRequired_6_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        // act
        final LinePath path = LinePath.fromVertexLoop(Arrays.asList(p1, p2, p3), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertexLoop_closeRequired_6_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        // act
        final LinePath path = LinePath.fromVertexLoop(Arrays.asList(p1, p2, p3), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertexLoop_closeRequired_7_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        // act
        final LinePath path = LinePath.fromVertexLoop(Arrays.asList(p1, p2, p3), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testFromVertexLoop_closeRequired_7_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        // act
        final LinePath path = LinePath.fromVertexLoop(Arrays.asList(p1, p2, p3), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testFromVertexLoop_closeRequired_7_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        // act
        final LinePath path = LinePath.fromVertexLoop(Arrays.asList(p1, p2, p3), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertexLoop_closeRequired_7_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        // act
        final LinePath path = LinePath.fromVertexLoop(Arrays.asList(p1, p2, p3), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertexLoop_closeRequired_8_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        // act
        final LinePath path = LinePath.fromVertexLoop(Arrays.asList(p1, p2, p3), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p1;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testFromVertexLoop_closeRequired_8_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        // act
        final LinePath path = LinePath.fromVertexLoop(Arrays.asList(p1, p2, p3), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p1;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testFromVertexLoop_closeRequired_8_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        // act
        final LinePath path = LinePath.fromVertexLoop(Arrays.asList(p1, p2, p3), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p1;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertexLoop_closeRequired_8_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        // act
        final LinePath path = LinePath.fromVertexLoop(Arrays.asList(p1, p2, p3), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p1;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertexLoop_closeNotRequired_6_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        // act
        final LinePath path = LinePath.fromVertexLoop(Arrays.asList(p1, p2, p3, Vector2D.of(0, 0)), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testFromVertexLoop_closeNotRequired_6_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        // act
        final LinePath path = LinePath.fromVertexLoop(Arrays.asList(p1, p2, p3, Vector2D.of(0, 0)), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testFromVertexLoop_closeNotRequired_6_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        // act
        final LinePath path = LinePath.fromVertexLoop(Arrays.asList(p1, p2, p3, Vector2D.of(0, 0)), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertexLoop_closeNotRequired_6_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        // act
        final LinePath path = LinePath.fromVertexLoop(Arrays.asList(p1, p2, p3, Vector2D.of(0, 0)), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertexLoop_closeNotRequired_7_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        // act
        final LinePath path = LinePath.fromVertexLoop(Arrays.asList(p1, p2, p3, Vector2D.of(0, 0)), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testFromVertexLoop_closeNotRequired_7_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        // act
        final LinePath path = LinePath.fromVertexLoop(Arrays.asList(p1, p2, p3, Vector2D.of(0, 0)), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testFromVertexLoop_closeNotRequired_7_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        // act
        final LinePath path = LinePath.fromVertexLoop(Arrays.asList(p1, p2, p3, Vector2D.of(0, 0)), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertexLoop_closeNotRequired_7_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        // act
        final LinePath path = LinePath.fromVertexLoop(Arrays.asList(p1, p2, p3, Vector2D.of(0, 0)), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertexLoop_closeNotRequired_8_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        // act
        final LinePath path = LinePath.fromVertexLoop(Arrays.asList(p1, p2, p3, Vector2D.of(0, 0)), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p1;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testFromVertexLoop_closeNotRequired_8_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        // act
        final LinePath path = LinePath.fromVertexLoop(Arrays.asList(p1, p2, p3, Vector2D.of(0, 0)), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p1;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testFromVertexLoop_closeNotRequired_8_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        // act
        final LinePath path = LinePath.fromVertexLoop(Arrays.asList(p1, p2, p3, Vector2D.of(0, 0)), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p1;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertexLoop_closeNotRequired_8_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        // act
        final LinePath path = LinePath.fromVertexLoop(Arrays.asList(p1, p2, p3, Vector2D.of(0, 0)), TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p1;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_booleanArg_3_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(0, 1);

        // act
        final LinePath open = LinePath.fromVertices(Arrays.asList(p1, p2, p3), false, TEST_PRECISION);
        final LinePath closed = LinePath.fromVertices(Arrays.asList(p1, p2, p3), true, TEST_PRECISION);

        // assert
        // removed other assertion

        final List<LineConvexSubset> openSegments = open.getElements();
        // removed other assertion
                final LineConvexSubset segment = openSegments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testFromVertices_booleanArg_3_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(0, 1);

        // act
        final LinePath open = LinePath.fromVertices(Arrays.asList(p1, p2, p3), false, TEST_PRECISION);
        final LinePath closed = LinePath.fromVertices(Arrays.asList(p1, p2, p3), true, TEST_PRECISION);

        // assert
        // removed other assertion

        final List<LineConvexSubset> openSegments = open.getElements();
        // removed other assertion
                final LineConvexSubset segment = openSegments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testFromVertices_booleanArg_3_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(0, 1);

        // act
        final LinePath open = LinePath.fromVertices(Arrays.asList(p1, p2, p3), false, TEST_PRECISION);
        final LinePath closed = LinePath.fromVertices(Arrays.asList(p1, p2, p3), true, TEST_PRECISION);

        // assert
        // removed other assertion

        final List<LineConvexSubset> openSegments = open.getElements();
        // removed other assertion
                final LineConvexSubset segment = openSegments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_booleanArg_3_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(0, 1);

        // act
        final LinePath open = LinePath.fromVertices(Arrays.asList(p1, p2, p3), false, TEST_PRECISION);
        final LinePath closed = LinePath.fromVertices(Arrays.asList(p1, p2, p3), true, TEST_PRECISION);

        // assert
        // removed other assertion

        final List<LineConvexSubset> openSegments = open.getElements();
        // removed other assertion
                final LineConvexSubset segment = openSegments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_booleanArg_4_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(0, 1);

        // act
        final LinePath open = LinePath.fromVertices(Arrays.asList(p1, p2, p3), false, TEST_PRECISION);
        final LinePath closed = LinePath.fromVertices(Arrays.asList(p1, p2, p3), true, TEST_PRECISION);

        // assert
        // removed other assertion

        final List<LineConvexSubset> openSegments = open.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = openSegments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testFromVertices_booleanArg_4_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(0, 1);

        // act
        final LinePath open = LinePath.fromVertices(Arrays.asList(p1, p2, p3), false, TEST_PRECISION);
        final LinePath closed = LinePath.fromVertices(Arrays.asList(p1, p2, p3), true, TEST_PRECISION);

        // assert
        // removed other assertion

        final List<LineConvexSubset> openSegments = open.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = openSegments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testFromVertices_booleanArg_4_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(0, 1);

        // act
        final LinePath open = LinePath.fromVertices(Arrays.asList(p1, p2, p3), false, TEST_PRECISION);
        final LinePath closed = LinePath.fromVertices(Arrays.asList(p1, p2, p3), true, TEST_PRECISION);

        // assert
        // removed other assertion

        final List<LineConvexSubset> openSegments = open.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = openSegments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_booleanArg_4_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(0, 1);

        // act
        final LinePath open = LinePath.fromVertices(Arrays.asList(p1, p2, p3), false, TEST_PRECISION);
        final LinePath closed = LinePath.fromVertices(Arrays.asList(p1, p2, p3), true, TEST_PRECISION);

        // assert
        // removed other assertion

        final List<LineConvexSubset> openSegments = open.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = openSegments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_booleanArg_7_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(0, 1);

        // act
        final LinePath open = LinePath.fromVertices(Arrays.asList(p1, p2, p3), false, TEST_PRECISION);
        final LinePath closed = LinePath.fromVertices(Arrays.asList(p1, p2, p3), true, TEST_PRECISION);

        // assert
        // removed other assertion

        final List<LineConvexSubset> openSegments = open.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<LineConvexSubset> closedSegments = closed.getElements();
        // removed other assertion
                final LineConvexSubset segment = closedSegments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testFromVertices_booleanArg_7_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(0, 1);

        // act
        final LinePath open = LinePath.fromVertices(Arrays.asList(p1, p2, p3), false, TEST_PRECISION);
        final LinePath closed = LinePath.fromVertices(Arrays.asList(p1, p2, p3), true, TEST_PRECISION);

        // assert
        // removed other assertion

        final List<LineConvexSubset> openSegments = open.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<LineConvexSubset> closedSegments = closed.getElements();
        // removed other assertion
                final LineConvexSubset segment = closedSegments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testFromVertices_booleanArg_7_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(0, 1);

        // act
        final LinePath open = LinePath.fromVertices(Arrays.asList(p1, p2, p3), false, TEST_PRECISION);
        final LinePath closed = LinePath.fromVertices(Arrays.asList(p1, p2, p3), true, TEST_PRECISION);

        // assert
        // removed other assertion

        final List<LineConvexSubset> openSegments = open.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<LineConvexSubset> closedSegments = closed.getElements();
        // removed other assertion
                final LineConvexSubset segment = closedSegments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_booleanArg_7_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(0, 1);

        // act
        final LinePath open = LinePath.fromVertices(Arrays.asList(p1, p2, p3), false, TEST_PRECISION);
        final LinePath closed = LinePath.fromVertices(Arrays.asList(p1, p2, p3), true, TEST_PRECISION);

        // assert
        // removed other assertion

        final List<LineConvexSubset> openSegments = open.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<LineConvexSubset> closedSegments = closed.getElements();
        // removed other assertion
                final LineConvexSubset segment = closedSegments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_booleanArg_8_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(0, 1);

        // act
        final LinePath open = LinePath.fromVertices(Arrays.asList(p1, p2, p3), false, TEST_PRECISION);
        final LinePath closed = LinePath.fromVertices(Arrays.asList(p1, p2, p3), true, TEST_PRECISION);

        // assert
        // removed other assertion

        final List<LineConvexSubset> openSegments = open.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<LineConvexSubset> closedSegments = closed.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = closedSegments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testFromVertices_booleanArg_8_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(0, 1);

        // act
        final LinePath open = LinePath.fromVertices(Arrays.asList(p1, p2, p3), false, TEST_PRECISION);
        final LinePath closed = LinePath.fromVertices(Arrays.asList(p1, p2, p3), true, TEST_PRECISION);

        // assert
        // removed other assertion

        final List<LineConvexSubset> openSegments = open.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<LineConvexSubset> closedSegments = closed.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = closedSegments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testFromVertices_booleanArg_8_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(0, 1);

        // act
        final LinePath open = LinePath.fromVertices(Arrays.asList(p1, p2, p3), false, TEST_PRECISION);
        final LinePath closed = LinePath.fromVertices(Arrays.asList(p1, p2, p3), true, TEST_PRECISION);

        // assert
        // removed other assertion

        final List<LineConvexSubset> openSegments = open.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<LineConvexSubset> closedSegments = closed.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = closedSegments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_booleanArg_8_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(0, 1);

        // act
        final LinePath open = LinePath.fromVertices(Arrays.asList(p1, p2, p3), false, TEST_PRECISION);
        final LinePath closed = LinePath.fromVertices(Arrays.asList(p1, p2, p3), true, TEST_PRECISION);

        // assert
        // removed other assertion

        final List<LineConvexSubset> openSegments = open.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<LineConvexSubset> closedSegments = closed.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = closedSegments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_booleanArg_9_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(0, 1);

        // act
        final LinePath open = LinePath.fromVertices(Arrays.asList(p1, p2, p3), false, TEST_PRECISION);
        final LinePath closed = LinePath.fromVertices(Arrays.asList(p1, p2, p3), true, TEST_PRECISION);

        // assert
        // removed other assertion

        final List<LineConvexSubset> openSegments = open.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<LineConvexSubset> closedSegments = closed.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = closedSegments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p1;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testFromVertices_booleanArg_9_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(0, 1);

        // act
        final LinePath open = LinePath.fromVertices(Arrays.asList(p1, p2, p3), false, TEST_PRECISION);
        final LinePath closed = LinePath.fromVertices(Arrays.asList(p1, p2, p3), true, TEST_PRECISION);

        // assert
        // removed other assertion

        final List<LineConvexSubset> openSegments = open.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<LineConvexSubset> closedSegments = closed.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = closedSegments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p1;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testFromVertices_booleanArg_9_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(0, 1);

        // act
        final LinePath open = LinePath.fromVertices(Arrays.asList(p1, p2, p3), false, TEST_PRECISION);
        final LinePath closed = LinePath.fromVertices(Arrays.asList(p1, p2, p3), true, TEST_PRECISION);

        // assert
        // removed other assertion

        final List<LineConvexSubset> openSegments = open.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<LineConvexSubset> closedSegments = closed.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = closedSegments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p1;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_booleanArg_9_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(0, 1);

        // act
        final LinePath open = LinePath.fromVertices(Arrays.asList(p1, p2, p3), false, TEST_PRECISION);
        final LinePath closed = LinePath.fromVertices(Arrays.asList(p1, p2, p3), true, TEST_PRECISION);

        // assert
        // removed other assertion

        final List<LineConvexSubset> openSegments = open.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        final List<LineConvexSubset> closedSegments = closed.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = closedSegments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p1;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testTransform_finite_5_oe_1_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.Unit.ZERO)
                .append(Vector2D.Unit.PLUS_X)
                .append(Vector2D.Unit.PLUS_Y)
                .close();

        final AffineTransformMatrix2D t =
                AffineTransformMatrix2D.createRotation(Vector2D.of(1, 1), Angle.PI_OVER_TWO);

        // act
        final LinePath result = path.transform(t);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();

        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = Vector2D.of(2, 0);
        final Vector2D end = Vector2D.of(2, 1);
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testTransform_finite_5_oe_2_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.Unit.ZERO)
                .append(Vector2D.Unit.PLUS_X)
                .append(Vector2D.Unit.PLUS_Y)
                .close();

        final AffineTransformMatrix2D t =
                AffineTransformMatrix2D.createRotation(Vector2D.of(1, 1), Angle.PI_OVER_TWO);

        // act
        final LinePath result = path.transform(t);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();

        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = Vector2D.of(2, 0);
        final Vector2D end = Vector2D.of(2, 1);
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testTransform_finite_5_oe_3_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.Unit.ZERO)
                .append(Vector2D.Unit.PLUS_X)
                .append(Vector2D.Unit.PLUS_Y)
                .close();

        final AffineTransformMatrix2D t =
                AffineTransformMatrix2D.createRotation(Vector2D.of(1, 1), Angle.PI_OVER_TWO);

        // act
        final LinePath result = path.transform(t);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();

        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = Vector2D.of(2, 0);
        final Vector2D end = Vector2D.of(2, 1);
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testTransform_finite_5_oe_4_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.Unit.ZERO)
                .append(Vector2D.Unit.PLUS_X)
                .append(Vector2D.Unit.PLUS_Y)
                .close();

        final AffineTransformMatrix2D t =
                AffineTransformMatrix2D.createRotation(Vector2D.of(1, 1), Angle.PI_OVER_TWO);

        // act
        final LinePath result = path.transform(t);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();

        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = Vector2D.of(2, 0);
        final Vector2D end = Vector2D.of(2, 1);
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testTransform_finite_6_oe_1_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.Unit.ZERO)
                .append(Vector2D.Unit.PLUS_X)
                .append(Vector2D.Unit.PLUS_Y)
                .close();

        final AffineTransformMatrix2D t =
                AffineTransformMatrix2D.createRotation(Vector2D.of(1, 1), Angle.PI_OVER_TWO);

        // act
        final LinePath result = path.transform(t);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();

        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = Vector2D.of(2, 1);
        final Vector2D end = Vector2D.Unit.PLUS_X;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testTransform_finite_6_oe_2_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.Unit.ZERO)
                .append(Vector2D.Unit.PLUS_X)
                .append(Vector2D.Unit.PLUS_Y)
                .close();

        final AffineTransformMatrix2D t =
                AffineTransformMatrix2D.createRotation(Vector2D.of(1, 1), Angle.PI_OVER_TWO);

        // act
        final LinePath result = path.transform(t);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();

        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = Vector2D.of(2, 1);
        final Vector2D end = Vector2D.Unit.PLUS_X;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testTransform_finite_6_oe_3_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.Unit.ZERO)
                .append(Vector2D.Unit.PLUS_X)
                .append(Vector2D.Unit.PLUS_Y)
                .close();

        final AffineTransformMatrix2D t =
                AffineTransformMatrix2D.createRotation(Vector2D.of(1, 1), Angle.PI_OVER_TWO);

        // act
        final LinePath result = path.transform(t);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();

        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = Vector2D.of(2, 1);
        final Vector2D end = Vector2D.Unit.PLUS_X;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testTransform_finite_6_oe_4_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.Unit.ZERO)
                .append(Vector2D.Unit.PLUS_X)
                .append(Vector2D.Unit.PLUS_Y)
                .close();

        final AffineTransformMatrix2D t =
                AffineTransformMatrix2D.createRotation(Vector2D.of(1, 1), Angle.PI_OVER_TWO);

        // act
        final LinePath result = path.transform(t);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();

        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = Vector2D.of(2, 1);
        final Vector2D end = Vector2D.Unit.PLUS_X;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testTransform_finite_7_oe_1_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.Unit.ZERO)
                .append(Vector2D.Unit.PLUS_X)
                .append(Vector2D.Unit.PLUS_Y)
                .close();

        final AffineTransformMatrix2D t =
                AffineTransformMatrix2D.createRotation(Vector2D.of(1, 1), Angle.PI_OVER_TWO);

        // act
        final LinePath result = path.transform(t);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = Vector2D.Unit.PLUS_X;
        final Vector2D end = Vector2D.of(2, 0);
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testTransform_finite_7_oe_2_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.Unit.ZERO)
                .append(Vector2D.Unit.PLUS_X)
                .append(Vector2D.Unit.PLUS_Y)
                .close();

        final AffineTransformMatrix2D t =
                AffineTransformMatrix2D.createRotation(Vector2D.of(1, 1), Angle.PI_OVER_TWO);

        // act
        final LinePath result = path.transform(t);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = Vector2D.Unit.PLUS_X;
        final Vector2D end = Vector2D.of(2, 0);
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testTransform_finite_7_oe_3_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.Unit.ZERO)
                .append(Vector2D.Unit.PLUS_X)
                .append(Vector2D.Unit.PLUS_Y)
                .close();

        final AffineTransformMatrix2D t =
                AffineTransformMatrix2D.createRotation(Vector2D.of(1, 1), Angle.PI_OVER_TWO);

        // act
        final LinePath result = path.transform(t);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = Vector2D.Unit.PLUS_X;
        final Vector2D end = Vector2D.of(2, 0);
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testTransform_finite_7_oe_4_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.Unit.ZERO)
                .append(Vector2D.Unit.PLUS_X)
                .append(Vector2D.Unit.PLUS_Y)
                .close();

        final AffineTransformMatrix2D t =
                AffineTransformMatrix2D.createRotation(Vector2D.of(1, 1), Angle.PI_OVER_TWO);

        // act
        final LinePath result = path.transform(t);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = Vector2D.Unit.PLUS_X;
        final Vector2D end = Vector2D.of(2, 0);
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testReverse_5_oe_1_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.Unit.ZERO)
                .append(Vector2D.Unit.PLUS_X)
                .append(Vector2D.Unit.PLUS_Y)
                .close();

        // act
        final LinePath result = path.reverse();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();

        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = Vector2D.Unit.ZERO;
        final Vector2D end = Vector2D.Unit.PLUS_Y;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testReverse_5_oe_2_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.Unit.ZERO)
                .append(Vector2D.Unit.PLUS_X)
                .append(Vector2D.Unit.PLUS_Y)
                .close();

        // act
        final LinePath result = path.reverse();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();

        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = Vector2D.Unit.ZERO;
        final Vector2D end = Vector2D.Unit.PLUS_Y;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testReverse_5_oe_3_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.Unit.ZERO)
                .append(Vector2D.Unit.PLUS_X)
                .append(Vector2D.Unit.PLUS_Y)
                .close();

        // act
        final LinePath result = path.reverse();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();

        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = Vector2D.Unit.ZERO;
        final Vector2D end = Vector2D.Unit.PLUS_Y;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testReverse_5_oe_4_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.Unit.ZERO)
                .append(Vector2D.Unit.PLUS_X)
                .append(Vector2D.Unit.PLUS_Y)
                .close();

        // act
        final LinePath result = path.reverse();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();

        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = Vector2D.Unit.ZERO;
        final Vector2D end = Vector2D.Unit.PLUS_Y;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testReverse_6_oe_1_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.Unit.ZERO)
                .append(Vector2D.Unit.PLUS_X)
                .append(Vector2D.Unit.PLUS_Y)
                .close();

        // act
        final LinePath result = path.reverse();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();

        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = Vector2D.Unit.PLUS_Y;
        final Vector2D end = Vector2D.Unit.PLUS_X;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testReverse_6_oe_2_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.Unit.ZERO)
                .append(Vector2D.Unit.PLUS_X)
                .append(Vector2D.Unit.PLUS_Y)
                .close();

        // act
        final LinePath result = path.reverse();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();

        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = Vector2D.Unit.PLUS_Y;
        final Vector2D end = Vector2D.Unit.PLUS_X;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testReverse_6_oe_3_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.Unit.ZERO)
                .append(Vector2D.Unit.PLUS_X)
                .append(Vector2D.Unit.PLUS_Y)
                .close();

        // act
        final LinePath result = path.reverse();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();

        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = Vector2D.Unit.PLUS_Y;
        final Vector2D end = Vector2D.Unit.PLUS_X;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testReverse_6_oe_4_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.Unit.ZERO)
                .append(Vector2D.Unit.PLUS_X)
                .append(Vector2D.Unit.PLUS_Y)
                .close();

        // act
        final LinePath result = path.reverse();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();

        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = Vector2D.Unit.PLUS_Y;
        final Vector2D end = Vector2D.Unit.PLUS_X;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testReverse_7_oe_1_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.Unit.ZERO)
                .append(Vector2D.Unit.PLUS_X)
                .append(Vector2D.Unit.PLUS_Y)
                .close();

        // act
        final LinePath result = path.reverse();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = Vector2D.Unit.PLUS_X;
        final Vector2D end = Vector2D.Unit.ZERO;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testReverse_7_oe_2_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.Unit.ZERO)
                .append(Vector2D.Unit.PLUS_X)
                .append(Vector2D.Unit.PLUS_Y)
                .close();

        // act
        final LinePath result = path.reverse();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = Vector2D.Unit.PLUS_X;
        final Vector2D end = Vector2D.Unit.ZERO;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testReverse_7_oe_3_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.Unit.ZERO)
                .append(Vector2D.Unit.PLUS_X)
                .append(Vector2D.Unit.PLUS_Y)
                .close();

        // act
        final LinePath result = path.reverse();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = Vector2D.Unit.PLUS_X;
        final Vector2D end = Vector2D.Unit.ZERO;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testReverse_7_oe_4_oe() {
        // arrange
        final LinePath path = LinePath.builder(TEST_PRECISION)
                .append(Vector2D.Unit.ZERO)
                .append(Vector2D.Unit.PLUS_X)
                .append(Vector2D.Unit.PLUS_Y)
                .close();

        // act
        final LinePath result = path.reverse();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();

        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = Vector2D.Unit.PLUS_X;
        final Vector2D end = Vector2D.Unit.ZERO;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testSimplify_2_oe_1_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder.appendVertices(
                Vector2D.of(-1, 0),
                Vector2D.ZERO,
                Vector2D.of(1, 0),
                Vector2D.of(1, 1),
                Vector2D.of(1, 2))
            .build();

        // act
        final LinePath result = path.simplify();

        // assert
        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = Vector2D.of(-1, 0);
        final Vector2D end = Vector2D.of(1, 0);
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testSimplify_2_oe_2_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder.appendVertices(
                Vector2D.of(-1, 0),
                Vector2D.ZERO,
                Vector2D.of(1, 0),
                Vector2D.of(1, 1),
                Vector2D.of(1, 2))
            .build();

        // act
        final LinePath result = path.simplify();

        // assert
        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = Vector2D.of(-1, 0);
        final Vector2D end = Vector2D.of(1, 0);
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testSimplify_2_oe_3_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder.appendVertices(
                Vector2D.of(-1, 0),
                Vector2D.ZERO,
                Vector2D.of(1, 0),
                Vector2D.of(1, 1),
                Vector2D.of(1, 2))
            .build();

        // act
        final LinePath result = path.simplify();

        // assert
        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = Vector2D.of(-1, 0);
        final Vector2D end = Vector2D.of(1, 0);
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testSimplify_2_oe_4_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder.appendVertices(
                Vector2D.of(-1, 0),
                Vector2D.ZERO,
                Vector2D.of(1, 0),
                Vector2D.of(1, 1),
                Vector2D.of(1, 2))
            .build();

        // act
        final LinePath result = path.simplify();

        // assert
        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = Vector2D.of(-1, 0);
        final Vector2D end = Vector2D.of(1, 0);
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testSimplify_3_oe_1_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder.appendVertices(
                Vector2D.of(-1, 0),
                Vector2D.ZERO,
                Vector2D.of(1, 0),
                Vector2D.of(1, 1),
                Vector2D.of(1, 2))
            .build();

        // act
        final LinePath result = path.simplify();

        // assert
        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = Vector2D.of(1, 0);
        final Vector2D end = Vector2D.of(1, 2);
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testSimplify_3_oe_2_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder.appendVertices(
                Vector2D.of(-1, 0),
                Vector2D.ZERO,
                Vector2D.of(1, 0),
                Vector2D.of(1, 1),
                Vector2D.of(1, 2))
            .build();

        // act
        final LinePath result = path.simplify();

        // assert
        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = Vector2D.of(1, 0);
        final Vector2D end = Vector2D.of(1, 2);
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testSimplify_3_oe_3_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder.appendVertices(
                Vector2D.of(-1, 0),
                Vector2D.ZERO,
                Vector2D.of(1, 0),
                Vector2D.of(1, 1),
                Vector2D.of(1, 2))
            .build();

        // act
        final LinePath result = path.simplify();

        // assert
        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = Vector2D.of(1, 0);
        final Vector2D end = Vector2D.of(1, 2);
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testSimplify_3_oe_4_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder.appendVertices(
                Vector2D.of(-1, 0),
                Vector2D.ZERO,
                Vector2D.of(1, 0),
                Vector2D.of(1, 1),
                Vector2D.of(1, 2))
            .build();

        // act
        final LinePath result = path.simplify();

        // assert
        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = Vector2D.of(1, 0);
        final Vector2D end = Vector2D.of(1, 2);
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testSimplify_startAndEndCombined_5_oe_1_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder.appendVertices(
                Vector2D.ZERO,
                Vector2D.of(1, 0),
                Vector2D.of(0, 1),
                Vector2D.of(-1, 0))
            .close();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = Vector2D.of(-1, 0);
        final Vector2D end = Vector2D.of(1, 0);
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testSimplify_startAndEndCombined_5_oe_2_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder.appendVertices(
                Vector2D.ZERO,
                Vector2D.of(1, 0),
                Vector2D.of(0, 1),
                Vector2D.of(-1, 0))
            .close();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = Vector2D.of(-1, 0);
        final Vector2D end = Vector2D.of(1, 0);
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testSimplify_startAndEndCombined_5_oe_3_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder.appendVertices(
                Vector2D.ZERO,
                Vector2D.of(1, 0),
                Vector2D.of(0, 1),
                Vector2D.of(-1, 0))
            .close();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = Vector2D.of(-1, 0);
        final Vector2D end = Vector2D.of(1, 0);
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testSimplify_startAndEndCombined_5_oe_4_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder.appendVertices(
                Vector2D.ZERO,
                Vector2D.of(1, 0),
                Vector2D.of(0, 1),
                Vector2D.of(-1, 0))
            .close();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = Vector2D.of(-1, 0);
        final Vector2D end = Vector2D.of(1, 0);
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testSimplify_startAndEndCombined_6_oe_1_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder.appendVertices(
                Vector2D.ZERO,
                Vector2D.of(1, 0),
                Vector2D.of(0, 1),
                Vector2D.of(-1, 0))
            .close();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = Vector2D.of(1, 0);
        final Vector2D end = Vector2D.of(0, 1);
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testSimplify_startAndEndCombined_6_oe_2_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder.appendVertices(
                Vector2D.ZERO,
                Vector2D.of(1, 0),
                Vector2D.of(0, 1),
                Vector2D.of(-1, 0))
            .close();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = Vector2D.of(1, 0);
        final Vector2D end = Vector2D.of(0, 1);
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testSimplify_startAndEndCombined_6_oe_3_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder.appendVertices(
                Vector2D.ZERO,
                Vector2D.of(1, 0),
                Vector2D.of(0, 1),
                Vector2D.of(-1, 0))
            .close();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = Vector2D.of(1, 0);
        final Vector2D end = Vector2D.of(0, 1);
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testSimplify_startAndEndCombined_6_oe_4_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder.appendVertices(
                Vector2D.ZERO,
                Vector2D.of(1, 0),
                Vector2D.of(0, 1),
                Vector2D.of(-1, 0))
            .close();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = Vector2D.of(1, 0);
        final Vector2D end = Vector2D.of(0, 1);
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testSimplify_startAndEndCombined_7_oe_1_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder.appendVertices(
                Vector2D.ZERO,
                Vector2D.of(1, 0),
                Vector2D.of(0, 1),
                Vector2D.of(-1, 0))
            .close();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = Vector2D.of(0, 1);
        final Vector2D end = Vector2D.of(-1, 0);
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testSimplify_startAndEndCombined_7_oe_2_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder.appendVertices(
                Vector2D.ZERO,
                Vector2D.of(1, 0),
                Vector2D.of(0, 1),
                Vector2D.of(-1, 0))
            .close();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = Vector2D.of(0, 1);
        final Vector2D end = Vector2D.of(-1, 0);
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testSimplify_startAndEndCombined_7_oe_3_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder.appendVertices(
                Vector2D.ZERO,
                Vector2D.of(1, 0),
                Vector2D.of(0, 1),
                Vector2D.of(-1, 0))
            .close();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = Vector2D.of(0, 1);
        final Vector2D end = Vector2D.of(-1, 0);
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testSimplify_startAndEndCombined_7_oe_4_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder.appendVertices(
                Vector2D.ZERO,
                Vector2D.of(1, 0),
                Vector2D.of(0, 1),
                Vector2D.of(-1, 0))
            .close();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = Vector2D.of(0, 1);
        final Vector2D end = Vector2D.of(-1, 0);
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testSimplify_startAndEndNotCombinedWhenNotClosed_5_oe_1_oe() {
        // arrange
        final Line xAxis = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder
                .append(xAxis.segment(0, 1))
                .appendVertices(
                        Vector2D.of(2, 1),
                        Vector2D.of(3, 0))
                .append(xAxis.segment(3, 4))
            .build();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = Vector2D.ZERO;
        final Vector2D end = Vector2D.of(1, 0);
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testSimplify_startAndEndNotCombinedWhenNotClosed_5_oe_2_oe() {
        // arrange
        final Line xAxis = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder
                .append(xAxis.segment(0, 1))
                .appendVertices(
                        Vector2D.of(2, 1),
                        Vector2D.of(3, 0))
                .append(xAxis.segment(3, 4))
            .build();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = Vector2D.ZERO;
        final Vector2D end = Vector2D.of(1, 0);
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testSimplify_startAndEndNotCombinedWhenNotClosed_5_oe_3_oe() {
        // arrange
        final Line xAxis = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder
                .append(xAxis.segment(0, 1))
                .appendVertices(
                        Vector2D.of(2, 1),
                        Vector2D.of(3, 0))
                .append(xAxis.segment(3, 4))
            .build();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = Vector2D.ZERO;
        final Vector2D end = Vector2D.of(1, 0);
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testSimplify_startAndEndNotCombinedWhenNotClosed_5_oe_4_oe() {
        // arrange
        final Line xAxis = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder
                .append(xAxis.segment(0, 1))
                .appendVertices(
                        Vector2D.of(2, 1),
                        Vector2D.of(3, 0))
                .append(xAxis.segment(3, 4))
            .build();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = Vector2D.ZERO;
        final Vector2D end = Vector2D.of(1, 0);
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testSimplify_startAndEndNotCombinedWhenNotClosed_6_oe_1_oe() {
        // arrange
        final Line xAxis = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder
                .append(xAxis.segment(0, 1))
                .appendVertices(
                        Vector2D.of(2, 1),
                        Vector2D.of(3, 0))
                .append(xAxis.segment(3, 4))
            .build();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = Vector2D.of(1, 0);
        final Vector2D end = Vector2D.of(2, 1);
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testSimplify_startAndEndNotCombinedWhenNotClosed_6_oe_2_oe() {
        // arrange
        final Line xAxis = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder
                .append(xAxis.segment(0, 1))
                .appendVertices(
                        Vector2D.of(2, 1),
                        Vector2D.of(3, 0))
                .append(xAxis.segment(3, 4))
            .build();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = Vector2D.of(1, 0);
        final Vector2D end = Vector2D.of(2, 1);
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testSimplify_startAndEndNotCombinedWhenNotClosed_6_oe_3_oe() {
        // arrange
        final Line xAxis = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder
                .append(xAxis.segment(0, 1))
                .appendVertices(
                        Vector2D.of(2, 1),
                        Vector2D.of(3, 0))
                .append(xAxis.segment(3, 4))
            .build();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = Vector2D.of(1, 0);
        final Vector2D end = Vector2D.of(2, 1);
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testSimplify_startAndEndNotCombinedWhenNotClosed_6_oe_4_oe() {
        // arrange
        final Line xAxis = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder
                .append(xAxis.segment(0, 1))
                .appendVertices(
                        Vector2D.of(2, 1),
                        Vector2D.of(3, 0))
                .append(xAxis.segment(3, 4))
            .build();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = Vector2D.of(1, 0);
        final Vector2D end = Vector2D.of(2, 1);
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testSimplify_startAndEndNotCombinedWhenNotClosed_7_oe_1_oe() {
        // arrange
        final Line xAxis = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder
                .append(xAxis.segment(0, 1))
                .appendVertices(
                        Vector2D.of(2, 1),
                        Vector2D.of(3, 0))
                .append(xAxis.segment(3, 4))
            .build();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = Vector2D.of(2, 1);
        final Vector2D end = Vector2D.of(3, 0);
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testSimplify_startAndEndNotCombinedWhenNotClosed_7_oe_2_oe() {
        // arrange
        final Line xAxis = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder
                .append(xAxis.segment(0, 1))
                .appendVertices(
                        Vector2D.of(2, 1),
                        Vector2D.of(3, 0))
                .append(xAxis.segment(3, 4))
            .build();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = Vector2D.of(2, 1);
        final Vector2D end = Vector2D.of(3, 0);
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testSimplify_startAndEndNotCombinedWhenNotClosed_7_oe_3_oe() {
        // arrange
        final Line xAxis = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder
                .append(xAxis.segment(0, 1))
                .appendVertices(
                        Vector2D.of(2, 1),
                        Vector2D.of(3, 0))
                .append(xAxis.segment(3, 4))
            .build();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = Vector2D.of(2, 1);
        final Vector2D end = Vector2D.of(3, 0);
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testSimplify_startAndEndNotCombinedWhenNotClosed_7_oe_4_oe() {
        // arrange
        final Line xAxis = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder
                .append(xAxis.segment(0, 1))
                .appendVertices(
                        Vector2D.of(2, 1),
                        Vector2D.of(3, 0))
                .append(xAxis.segment(3, 4))
            .build();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = Vector2D.of(2, 1);
        final Vector2D end = Vector2D.of(3, 0);
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testSimplify_startAndEndNotCombinedWhenNotClosed_8_oe_1_oe() {
        // arrange
        final Line xAxis = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder
                .append(xAxis.segment(0, 1))
                .appendVertices(
                        Vector2D.of(2, 1),
                        Vector2D.of(3, 0))
                .append(xAxis.segment(3, 4))
            .build();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(3);
        final Vector2D start = Vector2D.of(3, 0);
        final Vector2D end = Vector2D.of(4, 0);
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testSimplify_startAndEndNotCombinedWhenNotClosed_8_oe_2_oe() {
        // arrange
        final Line xAxis = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder
                .append(xAxis.segment(0, 1))
                .appendVertices(
                        Vector2D.of(2, 1),
                        Vector2D.of(3, 0))
                .append(xAxis.segment(3, 4))
            .build();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(3);
        final Vector2D start = Vector2D.of(3, 0);
        final Vector2D end = Vector2D.of(4, 0);
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testSimplify_startAndEndNotCombinedWhenNotClosed_8_oe_3_oe() {
        // arrange
        final Line xAxis = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder
                .append(xAxis.segment(0, 1))
                .appendVertices(
                        Vector2D.of(2, 1),
                        Vector2D.of(3, 0))
                .append(xAxis.segment(3, 4))
            .build();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(3);
        final Vector2D start = Vector2D.of(3, 0);
        final Vector2D end = Vector2D.of(4, 0);
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testSimplify_startAndEndNotCombinedWhenNotClosed_8_oe_4_oe() {
        // arrange
        final Line xAxis = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION);
        final Builder builder = LinePath.builder(TEST_PRECISION);

        final LinePath path = builder
                .append(xAxis.segment(0, 1))
                .appendVertices(
                        Vector2D.of(2, 1),
                        Vector2D.of(3, 0))
                .append(xAxis.segment(3, 4))
            .build();

        // act
        final LinePath result = path.simplify();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<LineConvexSubset> segments = result.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(3);
        final Vector2D start = Vector2D.of(3, 0);
        final Vector2D end = Vector2D.of(4, 0);
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prependAndAppend_vertices_2_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(1, 0);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .prepend(p1)
            .append(p4)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testBuilder_prependAndAppend_vertices_2_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(1, 0);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .prepend(p1)
            .append(p4)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testBuilder_prependAndAppend_vertices_2_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(1, 0);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .prepend(p1)
            .append(p4)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prependAndAppend_vertices_2_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(1, 0);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .prepend(p1)
            .append(p4)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prependAndAppend_vertices_3_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(1, 0);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .prepend(p1)
            .append(p4)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testBuilder_prependAndAppend_vertices_3_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(1, 0);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .prepend(p1)
            .append(p4)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testBuilder_prependAndAppend_vertices_3_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(1, 0);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .prepend(p1)
            .append(p4)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prependAndAppend_vertices_3_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(1, 0);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .prepend(p1)
            .append(p4)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prependAndAppend_vertices_4_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(1, 0);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .prepend(p1)
            .append(p4)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p4;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testBuilder_prependAndAppend_vertices_4_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(1, 0);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .prepend(p1)
            .append(p4)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p4;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testBuilder_prependAndAppend_vertices_4_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(1, 0);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .prepend(p1)
            .append(p4)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p4;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prependAndAppend_vertices_4_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(1, 0);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .prepend(p1)
            .append(p4)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p4;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prependAndAppend_vertices_5_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(1, 0);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .prepend(p1)
            .append(p4)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(3);
        final Vector2D start = p4;
        final Vector2D end = p1;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testBuilder_prependAndAppend_vertices_5_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(1, 0);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .prepend(p1)
            .append(p4)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(3);
        final Vector2D start = p4;
        final Vector2D end = p1;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testBuilder_prependAndAppend_vertices_5_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(1, 0);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .prepend(p1)
            .append(p4)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(3);
        final Vector2D start = p4;
        final Vector2D end = p1;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prependAndAppend_vertices_5_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(1, 0);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .prepend(p1)
            .append(p4)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(3);
        final Vector2D start = p4;
        final Vector2D end = p1;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prependAndAppend_ignoresEquivalentVertices_2_oe_1_oe() {
        // arrange
        final Vector2D p = Vector2D.ZERO;

        final Builder builder = LinePath.builder(TEST_PRECISION);
        builder.append(p);

        // act
        builder.append(p)
            .prepend(p)
            .append(Vector2D.of(0, 1e-20))
            .prepend(Vector2D.of(1e-20, 0));

        builder.append(Vector2D.Unit.PLUS_X);

        // assert
        final LinePath path = builder.build();

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p;
        final Vector2D end = Vector2D.Unit.PLUS_X;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testBuilder_prependAndAppend_ignoresEquivalentVertices_2_oe_2_oe() {
        // arrange
        final Vector2D p = Vector2D.ZERO;

        final Builder builder = LinePath.builder(TEST_PRECISION);
        builder.append(p);

        // act
        builder.append(p)
            .prepend(p)
            .append(Vector2D.of(0, 1e-20))
            .prepend(Vector2D.of(1e-20, 0));

        builder.append(Vector2D.Unit.PLUS_X);

        // assert
        final LinePath path = builder.build();

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p;
        final Vector2D end = Vector2D.Unit.PLUS_X;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testBuilder_prependAndAppend_ignoresEquivalentVertices_2_oe_3_oe() {
        // arrange
        final Vector2D p = Vector2D.ZERO;

        final Builder builder = LinePath.builder(TEST_PRECISION);
        builder.append(p);

        // act
        builder.append(p)
            .prepend(p)
            .append(Vector2D.of(0, 1e-20))
            .prepend(Vector2D.of(1e-20, 0));

        builder.append(Vector2D.Unit.PLUS_X);

        // assert
        final LinePath path = builder.build();

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p;
        final Vector2D end = Vector2D.Unit.PLUS_X;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prependAndAppend_ignoresEquivalentVertices_2_oe_4_oe() {
        // arrange
        final Vector2D p = Vector2D.ZERO;

        final Builder builder = LinePath.builder(TEST_PRECISION);
        builder.append(p);

        // act
        builder.append(p)
            .prepend(p)
            .append(Vector2D.of(0, 1e-20))
            .prepend(Vector2D.of(1e-20, 0));

        builder.append(Vector2D.Unit.PLUS_X);

        // assert
        final LinePath path = builder.build();

        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p;
        final Vector2D end = Vector2D.Unit.PLUS_X;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prependAndAppend_mixedVerticesAndSegments_2_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Segment a = Lines.segmentFromPoints(p1, p2, TEST_PRECISION);
        final Segment c = Lines.segmentFromPoints(p3, p4, TEST_PRECISION);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .append(c)
            .prepend(a)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testBuilder_prependAndAppend_mixedVerticesAndSegments_2_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Segment a = Lines.segmentFromPoints(p1, p2, TEST_PRECISION);
        final Segment c = Lines.segmentFromPoints(p3, p4, TEST_PRECISION);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .append(c)
            .prepend(a)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testBuilder_prependAndAppend_mixedVerticesAndSegments_2_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Segment a = Lines.segmentFromPoints(p1, p2, TEST_PRECISION);
        final Segment c = Lines.segmentFromPoints(p3, p4, TEST_PRECISION);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .append(c)
            .prepend(a)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prependAndAppend_mixedVerticesAndSegments_2_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Segment a = Lines.segmentFromPoints(p1, p2, TEST_PRECISION);
        final Segment c = Lines.segmentFromPoints(p3, p4, TEST_PRECISION);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .append(c)
            .prepend(a)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prependAndAppend_mixedVerticesAndSegments_3_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Segment a = Lines.segmentFromPoints(p1, p2, TEST_PRECISION);
        final Segment c = Lines.segmentFromPoints(p3, p4, TEST_PRECISION);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .append(c)
            .prepend(a)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testBuilder_prependAndAppend_mixedVerticesAndSegments_3_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Segment a = Lines.segmentFromPoints(p1, p2, TEST_PRECISION);
        final Segment c = Lines.segmentFromPoints(p3, p4, TEST_PRECISION);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .append(c)
            .prepend(a)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testBuilder_prependAndAppend_mixedVerticesAndSegments_3_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Segment a = Lines.segmentFromPoints(p1, p2, TEST_PRECISION);
        final Segment c = Lines.segmentFromPoints(p3, p4, TEST_PRECISION);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .append(c)
            .prepend(a)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prependAndAppend_mixedVerticesAndSegments_3_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Segment a = Lines.segmentFromPoints(p1, p2, TEST_PRECISION);
        final Segment c = Lines.segmentFromPoints(p3, p4, TEST_PRECISION);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .append(c)
            .prepend(a)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prependAndAppend_mixedVerticesAndSegments_4_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Segment a = Lines.segmentFromPoints(p1, p2, TEST_PRECISION);
        final Segment c = Lines.segmentFromPoints(p3, p4, TEST_PRECISION);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .append(c)
            .prepend(a)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p4;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testBuilder_prependAndAppend_mixedVerticesAndSegments_4_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Segment a = Lines.segmentFromPoints(p1, p2, TEST_PRECISION);
        final Segment c = Lines.segmentFromPoints(p3, p4, TEST_PRECISION);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .append(c)
            .prepend(a)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p4;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testBuilder_prependAndAppend_mixedVerticesAndSegments_4_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Segment a = Lines.segmentFromPoints(p1, p2, TEST_PRECISION);
        final Segment c = Lines.segmentFromPoints(p3, p4, TEST_PRECISION);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .append(c)
            .prepend(a)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p4;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prependAndAppend_mixedVerticesAndSegments_4_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Segment a = Lines.segmentFromPoints(p1, p2, TEST_PRECISION);
        final Segment c = Lines.segmentFromPoints(p3, p4, TEST_PRECISION);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .append(c)
            .prepend(a)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p4;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prependAndAppend_mixedVerticesAndSegments_5_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Segment a = Lines.segmentFromPoints(p1, p2, TEST_PRECISION);
        final Segment c = Lines.segmentFromPoints(p3, p4, TEST_PRECISION);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .append(c)
            .prepend(a)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(3);
        final Vector2D start = p4;
        final Vector2D end = p1;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testBuilder_prependAndAppend_mixedVerticesAndSegments_5_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Segment a = Lines.segmentFromPoints(p1, p2, TEST_PRECISION);
        final Segment c = Lines.segmentFromPoints(p3, p4, TEST_PRECISION);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .append(c)
            .prepend(a)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(3);
        final Vector2D start = p4;
        final Vector2D end = p1;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testBuilder_prependAndAppend_mixedVerticesAndSegments_5_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Segment a = Lines.segmentFromPoints(p1, p2, TEST_PRECISION);
        final Segment c = Lines.segmentFromPoints(p3, p4, TEST_PRECISION);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .append(c)
            .prepend(a)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(3);
        final Vector2D start = p4;
        final Vector2D end = p1;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prependAndAppend_mixedVerticesAndSegments_5_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Segment a = Lines.segmentFromPoints(p1, p2, TEST_PRECISION);
        final Segment c = Lines.segmentFromPoints(p3, p4, TEST_PRECISION);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prepend(p2)
            .append(p3)
            .append(c)
            .prepend(a)
            .append(p1);

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(3);
        final Vector2D start = p4;
        final Vector2D end = p1;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_appendVertices_2_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.appendVertices(p1, p2)
            .appendVertices(Arrays.asList(p3, p4, p1));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testBuilder_appendVertices_2_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.appendVertices(p1, p2)
            .appendVertices(Arrays.asList(p3, p4, p1));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testBuilder_appendVertices_2_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.appendVertices(p1, p2)
            .appendVertices(Arrays.asList(p3, p4, p1));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_appendVertices_2_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.appendVertices(p1, p2)
            .appendVertices(Arrays.asList(p3, p4, p1));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_appendVertices_3_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.appendVertices(p1, p2)
            .appendVertices(Arrays.asList(p3, p4, p1));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testBuilder_appendVertices_3_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.appendVertices(p1, p2)
            .appendVertices(Arrays.asList(p3, p4, p1));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testBuilder_appendVertices_3_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.appendVertices(p1, p2)
            .appendVertices(Arrays.asList(p3, p4, p1));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_appendVertices_3_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.appendVertices(p1, p2)
            .appendVertices(Arrays.asList(p3, p4, p1));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_appendVertices_4_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.appendVertices(p1, p2)
            .appendVertices(Arrays.asList(p3, p4, p1));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p4;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testBuilder_appendVertices_4_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.appendVertices(p1, p2)
            .appendVertices(Arrays.asList(p3, p4, p1));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p4;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testBuilder_appendVertices_4_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.appendVertices(p1, p2)
            .appendVertices(Arrays.asList(p3, p4, p1));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p4;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_appendVertices_4_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.appendVertices(p1, p2)
            .appendVertices(Arrays.asList(p3, p4, p1));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p4;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_appendVertices_5_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.appendVertices(p1, p2)
            .appendVertices(Arrays.asList(p3, p4, p1));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(3);
        final Vector2D start = p4;
        final Vector2D end = p1;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testBuilder_appendVertices_5_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.appendVertices(p1, p2)
            .appendVertices(Arrays.asList(p3, p4, p1));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(3);
        final Vector2D start = p4;
        final Vector2D end = p1;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testBuilder_appendVertices_5_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.appendVertices(p1, p2)
            .appendVertices(Arrays.asList(p3, p4, p1));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(3);
        final Vector2D start = p4;
        final Vector2D end = p1;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_appendVertices_5_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.appendVertices(p1, p2)
            .appendVertices(Arrays.asList(p3, p4, p1));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(3);
        final Vector2D start = p4;
        final Vector2D end = p1;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prependVertices_2_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prependVertices(p3, p4, p1)
            .prependVertices(Arrays.asList(p1, p2));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testBuilder_prependVertices_2_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prependVertices(p3, p4, p1)
            .prependVertices(Arrays.asList(p1, p2));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testBuilder_prependVertices_2_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prependVertices(p3, p4, p1)
            .prependVertices(Arrays.asList(p1, p2));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prependVertices_2_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prependVertices(p3, p4, p1)
            .prependVertices(Arrays.asList(p1, p2));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prependVertices_3_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prependVertices(p3, p4, p1)
            .prependVertices(Arrays.asList(p1, p2));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testBuilder_prependVertices_3_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prependVertices(p3, p4, p1)
            .prependVertices(Arrays.asList(p1, p2));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testBuilder_prependVertices_3_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prependVertices(p3, p4, p1)
            .prependVertices(Arrays.asList(p1, p2));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prependVertices_3_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prependVertices(p3, p4, p1)
            .prependVertices(Arrays.asList(p1, p2));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prependVertices_4_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prependVertices(p3, p4, p1)
            .prependVertices(Arrays.asList(p1, p2));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p4;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testBuilder_prependVertices_4_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prependVertices(p3, p4, p1)
            .prependVertices(Arrays.asList(p1, p2));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p4;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testBuilder_prependVertices_4_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prependVertices(p3, p4, p1)
            .prependVertices(Arrays.asList(p1, p2));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p4;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prependVertices_4_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prependVertices(p3, p4, p1)
            .prependVertices(Arrays.asList(p1, p2));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p4;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prependVertices_5_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prependVertices(p3, p4, p1)
            .prependVertices(Arrays.asList(p1, p2));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(3);
        final Vector2D start = p4;
        final Vector2D end = p1;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testBuilder_prependVertices_5_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prependVertices(p3, p4, p1)
            .prependVertices(Arrays.asList(p1, p2));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(3);
        final Vector2D start = p4;
        final Vector2D end = p1;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testBuilder_prependVertices_5_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prependVertices(p3, p4, p1)
            .prependVertices(Arrays.asList(p1, p2));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(3);
        final Vector2D start = p4;
        final Vector2D end = p1;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prependVertices_5_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);
        final Vector2D p4 = Vector2D.of(0, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.prependVertices(p3, p4, p1)
            .prependVertices(Arrays.asList(p1, p2));

        final LinePath path = builder.build();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(3);
        final Vector2D start = p4;
        final Vector2D end = p1;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_close_notYetClosed_2_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.append(p1)
            .append(p2)
            .append(p3);

        final LinePath path = builder.close();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testBuilder_close_notYetClosed_2_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.append(p1)
            .append(p2)
            .append(p3);

        final LinePath path = builder.close();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testBuilder_close_notYetClosed_2_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.append(p1)
            .append(p2)
            .append(p3);

        final LinePath path = builder.close();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_close_notYetClosed_2_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.append(p1)
            .append(p2)
            .append(p3);

        final LinePath path = builder.close();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_close_notYetClosed_3_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.append(p1)
            .append(p2)
            .append(p3);

        final LinePath path = builder.close();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testBuilder_close_notYetClosed_3_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.append(p1)
            .append(p2)
            .append(p3);

        final LinePath path = builder.close();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testBuilder_close_notYetClosed_3_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.append(p1)
            .append(p2)
            .append(p3);

        final LinePath path = builder.close();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_close_notYetClosed_3_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.append(p1)
            .append(p2)
            .append(p3);

        final LinePath path = builder.close();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_close_notYetClosed_4_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.append(p1)
            .append(p2)
            .append(p3);

        final LinePath path = builder.close();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p1;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testBuilder_close_notYetClosed_4_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.append(p1)
            .append(p2)
            .append(p3);

        final LinePath path = builder.close();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p1;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testBuilder_close_notYetClosed_4_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.append(p1)
            .append(p2)
            .append(p3);

        final LinePath path = builder.close();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p1;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_close_notYetClosed_4_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.append(p1)
            .append(p2)
            .append(p3);

        final LinePath path = builder.close();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p1;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_close_alreadyClosed_2_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.append(p1)
            .append(p2)
            .append(p3)
            .append(p1);

        final LinePath path = builder.close();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testBuilder_close_alreadyClosed_2_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.append(p1)
            .append(p2)
            .append(p3)
            .append(p1);

        final LinePath path = builder.close();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testBuilder_close_alreadyClosed_2_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.append(p1)
            .append(p2)
            .append(p3)
            .append(p1);

        final LinePath path = builder.close();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_close_alreadyClosed_2_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.append(p1)
            .append(p2)
            .append(p3)
            .append(p1);

        final LinePath path = builder.close();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
                final LineConvexSubset segment = segments.get(0);
        final Vector2D start = p1;
        final Vector2D end = p2;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_close_alreadyClosed_3_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.append(p1)
            .append(p2)
            .append(p3)
            .append(p1);

        final LinePath path = builder.close();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testBuilder_close_alreadyClosed_3_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.append(p1)
            .append(p2)
            .append(p3)
            .append(p1);

        final LinePath path = builder.close();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testBuilder_close_alreadyClosed_3_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.append(p1)
            .append(p2)
            .append(p3)
            .append(p1);

        final LinePath path = builder.close();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_close_alreadyClosed_3_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.append(p1)
            .append(p2)
            .append(p3)
            .append(p1);

        final LinePath path = builder.close();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(1);
        final Vector2D start = p2;
        final Vector2D end = p3;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_close_alreadyClosed_4_oe_1_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.append(p1)
            .append(p2)
            .append(p3)
            .append(p1);

        final LinePath path = builder.close();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p1;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testBuilder_close_alreadyClosed_4_oe_2_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.append(p1)
            .append(p2)
            .append(p3)
            .append(p1);

        final LinePath path = builder.close();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p1;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testBuilder_close_alreadyClosed_4_oe_3_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.append(p1)
            .append(p2)
            .append(p3)
            .append(p1);

        final LinePath path = builder.close();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p1;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_close_alreadyClosed_4_oe_4_oe() {
        // arrange
        final Vector2D p1 = Vector2D.ZERO;
        final Vector2D p2 = Vector2D.of(1, 0);
        final Vector2D p3 = Vector2D.of(1, 1);

        final Builder builder = LinePath.builder(TEST_PRECISION);

        // act
        builder.append(p1)
            .append(p2)
            .append(p3)
            .append(p1);

        final LinePath path = builder.close();

        // assert
        final List<LineConvexSubset> segments = path.getElements();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = segments.get(2);
        final Vector2D start = p3;
        final Vector2D end = p1;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_close_obtuseTriangle_2_oe_1_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);
        builder.appendVertices(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(2, 1));

        // act
        final LinePath path = builder.close();

        // assert
        // removed other assertion
                final LineConvexSubset segment = path.getElements().get(0);
        final Vector2D start = Vector2D.ZERO;
        final Vector2D end = Vector2D.of(1, 0);
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testBuilder_close_obtuseTriangle_2_oe_2_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);
        builder.appendVertices(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(2, 1));

        // act
        final LinePath path = builder.close();

        // assert
        // removed other assertion
                final LineConvexSubset segment = path.getElements().get(0);
        final Vector2D start = Vector2D.ZERO;
        final Vector2D end = Vector2D.of(1, 0);
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testBuilder_close_obtuseTriangle_2_oe_3_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);
        builder.appendVertices(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(2, 1));

        // act
        final LinePath path = builder.close();

        // assert
        // removed other assertion
                final LineConvexSubset segment = path.getElements().get(0);
        final Vector2D start = Vector2D.ZERO;
        final Vector2D end = Vector2D.of(1, 0);
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_close_obtuseTriangle_2_oe_4_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);
        builder.appendVertices(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(2, 1));

        // act
        final LinePath path = builder.close();

        // assert
        // removed other assertion
                final LineConvexSubset segment = path.getElements().get(0);
        final Vector2D start = Vector2D.ZERO;
        final Vector2D end = Vector2D.of(1, 0);
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_close_obtuseTriangle_3_oe_1_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);
        builder.appendVertices(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(2, 1));

        // act
        final LinePath path = builder.close();

        // assert
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = path.getElements().get(1);
        final Vector2D start = Vector2D.of(1, 0);
        final Vector2D end = Vector2D.of(2, 1);
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testBuilder_close_obtuseTriangle_3_oe_2_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);
        builder.appendVertices(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(2, 1));

        // act
        final LinePath path = builder.close();

        // assert
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = path.getElements().get(1);
        final Vector2D start = Vector2D.of(1, 0);
        final Vector2D end = Vector2D.of(2, 1);
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testBuilder_close_obtuseTriangle_3_oe_3_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);
        builder.appendVertices(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(2, 1));

        // act
        final LinePath path = builder.close();

        // assert
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = path.getElements().get(1);
        final Vector2D start = Vector2D.of(1, 0);
        final Vector2D end = Vector2D.of(2, 1);
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_close_obtuseTriangle_3_oe_4_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);
        builder.appendVertices(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(2, 1));

        // act
        final LinePath path = builder.close();

        // assert
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = path.getElements().get(1);
        final Vector2D start = Vector2D.of(1, 0);
        final Vector2D end = Vector2D.of(2, 1);
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_close_obtuseTriangle_4_oe_1_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);
        builder.appendVertices(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(2, 1));

        // act
        final LinePath path = builder.close();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = path.getElements().get(2);
        final Vector2D start = Vector2D.of(2, 1);
        final Vector2D end = Vector2D.ZERO;
        Assertions.assertFalse(segment.isInfinite());
    }

    @Test
    void testBuilder_close_obtuseTriangle_4_oe_2_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);
        builder.appendVertices(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(2, 1));

        // act
        final LinePath path = builder.close();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = path.getElements().get(2);
        final Vector2D start = Vector2D.of(2, 1);
        final Vector2D end = Vector2D.ZERO;
        // removed other assertion
                Assertions.assertTrue(segment.isFinite());
    }

    @Test
    void testBuilder_close_obtuseTriangle_4_oe_3_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);
        builder.appendVertices(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(2, 1));

        // act
        final LinePath path = builder.close();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = path.getElements().get(2);
        final Vector2D start = Vector2D.of(2, 1);
        final Vector2D end = Vector2D.ZERO;
        // removed other assertion
                // removed other assertion
        
                EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_close_obtuseTriangle_4_oe_4_oe() {
        // arrange
        final Builder builder = LinePath.builder(TEST_PRECISION);
        builder.appendVertices(Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(2, 1));

        // act
        final LinePath path = builder.close();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final LineConvexSubset segment = path.getElements().get(2);
        final Vector2D start = Vector2D.of(2, 1);
        final Vector2D end = Vector2D.ZERO;
        // removed other assertion
                // removed other assertion
        
                // removed other assertion
                EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

}
