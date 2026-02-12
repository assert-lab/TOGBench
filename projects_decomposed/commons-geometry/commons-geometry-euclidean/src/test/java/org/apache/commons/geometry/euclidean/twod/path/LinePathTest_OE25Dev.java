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

    private static void assertFiniteSegment(final LineConvexSubset segment, final Vector2D start, final Vector2D end) {
        Assertions.assertFalse(segment.isInfinite());
        Assertions.assertTrue(segment.isFinite());

        EuclideanTestUtils.assertCoordinatesEqual(start, segment.getStartPoint(), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(end, segment.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFrom_segmentsNotConnected_1_oe() {
        // arrange
        final Segment a = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 0), TEST_PRECISION);
        final Segment b = Lines.segmentFromPoints(Vector2D.of(1.01, 0), Vector2D.of(1, 0), TEST_PRECISION);

        final LineConvexSubset c = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION).span();
        final LineConvexSubset d = Lines.fromPointAndAngle(Vector2D.of(1, 0), Angle.PI_OVER_TWO, TEST_PRECISION).span();

        // act/assert
        try {
    LinePath.from(a, b);
    org.junit.jupiter.api.Assertions.fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testFrom_segmentsNotConnected_2_oe() {
        // arrange
        final Segment a = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 0), TEST_PRECISION);
        final Segment b = Lines.segmentFromPoints(Vector2D.of(1.01, 0), Vector2D.of(1, 0), TEST_PRECISION);

        final LineConvexSubset c = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION).span();
        final LineConvexSubset d = Lines.fromPointAndAngle(Vector2D.of(1, 0), Angle.PI_OVER_TWO, TEST_PRECISION).span();

        // act/assert
        // removed other assertion
        try {
    LinePath.from(c, b);
    org.junit.jupiter.api.Assertions.fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testFrom_segmentsNotConnected_3_oe() {
        // arrange
        final Segment a = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 0), TEST_PRECISION);
        final Segment b = Lines.segmentFromPoints(Vector2D.of(1.01, 0), Vector2D.of(1, 0), TEST_PRECISION);

        final LineConvexSubset c = Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION).span();
        final LineConvexSubset d = Lines.fromPointAndAngle(Vector2D.of(1, 0), Angle.PI_OVER_TWO, TEST_PRECISION).span();

        // act/assert
        // removed other assertion
        // removed other assertion
        try {
    LinePath.from(a, d);
    org.junit.jupiter.api.Assertions.fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testFromVertices_singleVertex_failsToCreatePath_1_oe() {
        // act/assert
        try {
    LinePath.fromVertices(Collections.singletonList(Vector2D.ZERO), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testFromVertexLoop_singleVertex_failsToCreatePath_1_oe() {
        // act/assert
        try {
    LinePath.fromVertexLoop(Collections.singletonList(Vector2D.ZERO), TEST_PRECISION);
    org.junit.jupiter.api.Assertions.fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testGetElements_listIsNotModifiable_3_oe() {
        // arrange
        final Segment a = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 0), TEST_PRECISION);
        final List<LineConvexSubset> inputSegments = new ArrayList<>(Collections.singletonList(a));

        // act
        final LinePath path = LinePath.from(inputSegments);

        inputSegments.clear();

        // assert
        // removed other assertion
        // removed other assertion

        try {
    path.getElements().add(a);
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    void testBuilder_prependAndAppend_disconnectedSegments_1_oe() {
        // arrange
        final Segment a = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 0), TEST_PRECISION);

        final Builder builder = LinePath.builder(null);
        builder.append(a);

        // act
        try {
    builder.append(a);
    org.junit.jupiter.api.Assertions.fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testBuilder_prependAndAppend_disconnectedSegments_2_oe() {
        // arrange
        final Segment a = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.of(1, 0), TEST_PRECISION);

        final Builder builder = LinePath.builder(null);
        builder.append(a);

        // act
        // removed other assertion
        try {
    builder.prepend(a);
    org.junit.jupiter.api.Assertions.fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testBuilder_prependAndAppend_addingToInfinitePath_1_oe() {
        // arrange
        final Vector2D p = Vector2D.Unit.PLUS_X;
        final Builder builder = LinePath.builder(TEST_PRECISION);

        builder.append(Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION).span());

        // act/assert
        try {
    builder.prepend(p);
    org.junit.jupiter.api.Assertions.fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testBuilder_prependAndAppend_addingToInfinitePath_2_oe() {
        // arrange
        final Vector2D p = Vector2D.Unit.PLUS_X;
        final Builder builder = LinePath.builder(TEST_PRECISION);

        builder.append(Lines.fromPointAndAngle(Vector2D.ZERO, 0.0, TEST_PRECISION).span());

        // act/assert
        // removed other assertion
        try {
    builder.append(p);
    org.junit.jupiter.api.Assertions.fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

}
