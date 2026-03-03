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
import java.util.Random;

import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.twod.Line;
import org.apache.commons.geometry.euclidean.twod.LineConvexSubset;
import org.apache.commons.geometry.euclidean.twod.Lines;
import org.apache.commons.geometry.euclidean.twod.Segment;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.geometry.euclidean.twod.path.AbstractLinePathConnector.ConnectableLineSubset;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AbstractLinePathConnectorTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    private static final Line Y_AXIS = Lines.fromPointAndAngle(Vector2D.ZERO, Angle.PI_OVER_TWO,
            TEST_PRECISION);

    private final TestConnector connector = new TestConnector();

    private static List<LineConvexSubset> shuffle(final List<LineConvexSubset> segments) {
        return shuffle(segments, 1);
    }

    private static List<LineConvexSubset> shuffle(final List<LineConvexSubset> segments, final int seed) {
        Collections.shuffle(segments, new Random(seed));

        return segments;
    }

    private static void assertFinitePath(final LinePath path, final Vector2D... vertices) {
        Assertions.assertFalse(path.isInfinite());
        Assertions.assertTrue(path.isFinite());

        assertPathVertices(path, vertices);
    }

    private static void assertPathVertices(final LinePath path, final Vector2D... vertices) {
        final List<Vector2D> expectedVertices = Arrays.asList(vertices);
        final List<Vector2D> actualVertices = path.getVertexSequence();

        final String msg = "Expected path vertices to equal " + expectedVertices + " but was " + actualVertices;
        Assertions.assertEquals(expectedVertices.size(), actualVertices.size(), msg);

        for (int i = 0; i < expectedVertices.size(); ++i) {
            EuclideanTestUtils.assertCoordinatesEqual(expectedVertices.get(i), actualVertices.get(i), TEST_EPS);
        }
    }

    private static class TestConnector extends AbstractLinePathConnector {

        @Override
        protected ConnectableLineSubset selectConnection(final ConnectableLineSubset incoming, final List<ConnectableLineSubset> outgoing) {
            // just choose the first element
            return outgoing.get(0);
        }
    }

    @Test
    void testConnectAll_emptyCollection_1_oe() {
        final List<LinePath> paths = connector.connectAll(Collections.emptyList());

        Assertions.assertEquals(0, paths.size());
    }

    @Test
    void testConnectAll_singleInfiniteLine_1_oe() {
        final LineConvexSubset segment = Y_AXIS.span();

        final List<LinePath> paths = connector.connectAll(Collections.singletonList(segment));

        Assertions.assertEquals(1, paths.size());
    }

    @Test
    void testConnectAll_singleInfiniteLine_2_oe() {
        final LineConvexSubset segment = Y_AXIS.span();

        final List<LinePath> paths = connector.connectAll(Collections.singletonList(segment));


        final LinePath path = paths.get(0);
        Assertions.assertEquals(1, path.getElements().size());
    }

    @Test
    void testConnectAll_singleInfiniteLine_3_oe() {
        final LineConvexSubset segment = Y_AXIS.span();

        final List<LinePath> paths = connector.connectAll(Collections.singletonList(segment));


        final LinePath path = paths.get(0);
        Assertions.assertSame(segment, path.getStart());
    }

    @Test
    void testConnectAll_singleHalfInfiniteLine_noEndPoint_1_oe() {
        final LineConvexSubset segment = Y_AXIS.rayFrom(Vector2D.ZERO);

        final List<LinePath> paths = connector.connectAll(Collections.singletonList(segment));

        Assertions.assertEquals(1, paths.size());
    }

    @Test
    void testConnectAll_singleHalfInfiniteLine_noEndPoint_2_oe() {
        final LineConvexSubset segment = Y_AXIS.rayFrom(Vector2D.ZERO);

        final List<LinePath> paths = connector.connectAll(Collections.singletonList(segment));


        final LinePath path = paths.get(0);
        Assertions.assertEquals(1, path.getElements().size());
    }

    @Test
    void testConnectAll_singleHalfInfiniteLine_noEndPoint_3_oe() {
        final LineConvexSubset segment = Y_AXIS.rayFrom(Vector2D.ZERO);

        final List<LinePath> paths = connector.connectAll(Collections.singletonList(segment));


        final LinePath path = paths.get(0);
        Assertions.assertSame(segment, path.getStart());
    }

    @Test
    void testConnectAll_singleHalfInfiniteLine_noStartPoint_1_oe() {
        final LineConvexSubset segment = Y_AXIS.reverseRayTo(Vector2D.ZERO);

        final List<LinePath> paths = connector.connectAll(Collections.singletonList(segment));

        Assertions.assertEquals(1, paths.size());
    }

    @Test
    void testConnectAll_singleHalfInfiniteLine_noStartPoint_2_oe() {
        final LineConvexSubset segment = Y_AXIS.reverseRayTo(Vector2D.ZERO);

        final List<LinePath> paths = connector.connectAll(Collections.singletonList(segment));


        final LinePath path = paths.get(0);
        Assertions.assertEquals(1, path.getElements().size());
    }

    @Test
    void testConnectAll_singleHalfInfiniteLine_noStartPoint_3_oe() {
        final LineConvexSubset segment = Y_AXIS.reverseRayTo(Vector2D.ZERO);

        final List<LinePath> paths = connector.connectAll(Collections.singletonList(segment));


        final LinePath path = paths.get(0);
        Assertions.assertSame(segment, path.getStart());
    }

    @Test
    void testConnectAll_disjointSegments_1_oe() {
        final LineConvexSubset a = Y_AXIS.segment(Vector2D.of(0, 1), Vector2D.of(0, 2));
        final LineConvexSubset b = Y_AXIS.segment(Vector2D.of(0, -1), Vector2D.ZERO);

        final List<LineConvexSubset> segments = Arrays.asList(a, b);

        final List<LinePath> paths = connector.connectAll(segments);

        Assertions.assertEquals(2, paths.size());
    }

    @Test
    void testConnectAll_singleClosedPath_1_oe() {
        final LinePath input = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(1, 1), Vector2D.ZERO, Vector2D.of(1, 0))
                .close();

        final List<LineConvexSubset> segments = new ArrayList<>(input.getElements());
        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);

        Assertions.assertEquals(1, paths.size());
    }

    @Test
    void testConnectAll_multipleClosedPaths_1_oe() {
        final LinePath a = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(1, 1), Vector2D.ZERO, Vector2D.of(1, 0))
                .close();

        final LinePath b = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(0, 1), Vector2D.of(-1, 0), Vector2D.of(-0.5, 0))
                .close();

        final LinePath c = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(1, 3), Vector2D.of(0, 2), Vector2D.of(1, 2))
                .close();

        final List<LineConvexSubset> segments = new ArrayList<>();
        segments.addAll(a.getElements());
        segments.addAll(b.getElements());
        segments.addAll(c.getElements());

        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);

        Assertions.assertEquals(3, paths.size());
    }

    @Test
    void testConnectAll_singleOpenPath_1_oe() {
        final LinePath input = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(1, 1), Vector2D.ZERO, Vector2D.of(1, 0))
                .build();

        final List<LineConvexSubset> segments = new ArrayList<>(input.getElements());
        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);

        Assertions.assertEquals(1, paths.size());
    }

    @Test
    void testConnectAll_mixOfOpenConnectedAndInfinite_1_oe() {
        final LineConvexSubset inputYInf = Y_AXIS.reverseRayTo(Vector2D.ZERO);
        final LineConvexSubset inputXInf = Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.MINUS_X, TEST_PRECISION)
                .rayFrom(Vector2D.ZERO);

        final LinePath closedPath = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(0, 2), Vector2D.of(1, 2), Vector2D.of(1, 3))
                .close();

        final LinePath openPath = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(-1, 3), Vector2D.of(0, 1), Vector2D.of(1, 1))
                .build();

        final List<LineConvexSubset> segments = new ArrayList<>();
        segments.add(inputYInf);
        segments.add(inputXInf);
        segments.addAll(closedPath.getElements());
        segments.addAll(openPath.getElements());

        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);

        Assertions.assertEquals(3, paths.size());
    }

    @Test
    void testConnectAll_mixOfOpenConnectedAndInfinite_3_oe() {
        final LineConvexSubset inputYInf = Y_AXIS.reverseRayTo(Vector2D.ZERO);
        final LineConvexSubset inputXInf = Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.MINUS_X, TEST_PRECISION)
                .rayFrom(Vector2D.ZERO);

        final LinePath closedPath = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(0, 2), Vector2D.of(1, 2), Vector2D.of(1, 3))
                .close();

        final LinePath openPath = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(-1, 3), Vector2D.of(0, 1), Vector2D.of(1, 1))
                .build();

        final List<LineConvexSubset> segments = new ArrayList<>();
        segments.add(inputYInf);
        segments.add(inputXInf);
        segments.addAll(closedPath.getElements());
        segments.addAll(openPath.getElements());

        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);



        final LinePath infPath = paths.get(1);
        Assertions.assertTrue(infPath.isInfinite());
    }

    @Test
    void testConnectAll_mixOfOpenConnectedAndInfinite_4_oe() {
        final LineConvexSubset inputYInf = Y_AXIS.reverseRayTo(Vector2D.ZERO);
        final LineConvexSubset inputXInf = Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.MINUS_X, TEST_PRECISION)
                .rayFrom(Vector2D.ZERO);

        final LinePath closedPath = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(0, 2), Vector2D.of(1, 2), Vector2D.of(1, 3))
                .close();

        final LinePath openPath = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(-1, 3), Vector2D.of(0, 1), Vector2D.of(1, 1))
                .build();

        final List<LineConvexSubset> segments = new ArrayList<>();
        segments.add(inputYInf);
        segments.add(inputXInf);
        segments.addAll(closedPath.getElements());
        segments.addAll(openPath.getElements());

        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);



        final LinePath infPath = paths.get(1);
        Assertions.assertEquals(2, infPath.getElements().size());
    }

    @Test
    void testConnectAll_mixOfOpenConnectedAndInfinite_5_oe() {
        final LineConvexSubset inputYInf = Y_AXIS.reverseRayTo(Vector2D.ZERO);
        final LineConvexSubset inputXInf = Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.MINUS_X, TEST_PRECISION)
                .rayFrom(Vector2D.ZERO);

        final LinePath closedPath = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(0, 2), Vector2D.of(1, 2), Vector2D.of(1, 3))
                .close();

        final LinePath openPath = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(-1, 3), Vector2D.of(0, 1), Vector2D.of(1, 1))
                .build();

        final List<LineConvexSubset> segments = new ArrayList<>();
        segments.add(inputYInf);
        segments.add(inputXInf);
        segments.addAll(closedPath.getElements());
        segments.addAll(openPath.getElements());

        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);



        final LinePath infPath = paths.get(1);
        Assertions.assertSame(inputYInf, infPath.getElements().get(0));
    }

    @Test
    void testConnectAll_mixOfOpenConnectedAndInfinite_6_oe() {
        final LineConvexSubset inputYInf = Y_AXIS.reverseRayTo(Vector2D.ZERO);
        final LineConvexSubset inputXInf = Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.MINUS_X, TEST_PRECISION)
                .rayFrom(Vector2D.ZERO);

        final LinePath closedPath = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(0, 2), Vector2D.of(1, 2), Vector2D.of(1, 3))
                .close();

        final LinePath openPath = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(-1, 3), Vector2D.of(0, 1), Vector2D.of(1, 1))
                .build();

        final List<LineConvexSubset> segments = new ArrayList<>();
        segments.add(inputYInf);
        segments.add(inputXInf);
        segments.addAll(closedPath.getElements());
        segments.addAll(openPath.getElements());

        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);



        final LinePath infPath = paths.get(1);
        Assertions.assertSame(inputXInf, infPath.getElements().get(1));
    }

    @Test
    void testConnectAll_pathWithSinglePoint_1_oe() {
        final Vector2D p0 = Vector2D.ZERO;

        final List<LineConvexSubset> segments = Collections.singletonList(Lines.fromPointAndAngle(p0, 0, TEST_PRECISION).segment(p0, p0));

        final List<LinePath> paths = connector.connectAll(segments);

        Assertions.assertEquals(1, paths.size());
    }

    @Test
    void testConnectAll_pathWithPointLikeConnectedSegments_1_oe() {
        final Vector2D p0 = Vector2D.ZERO;
        final Vector2D p1 = Vector2D.of(1, 0);
        final Vector2D p2 = Vector2D.of(1, 1);

        final Vector2D almostP0 = Vector2D.of(-1e-20, -1e-20);
        final Vector2D almostP1 = Vector2D.of(1 - 1e-15, 0);

        final LinePath input = LinePath.builder(TEST_PRECISION)
                .appendVertices(p0, p1)
                .append(Lines.fromPointAndAngle(p1, 0.25 * Math.PI, TEST_PRECISION).segment(p1, p1))
                .append(Lines.fromPointAndAngle(p1, -0.25 * Math.PI, TEST_PRECISION).segment(almostP1, almostP1))
                .append(p2)
                .append(p0)
                .append(Lines.fromPointAndAngle(Vector2D.ZERO, -Angle.PI_OVER_TWO, TEST_PRECISION)
                        .segment(almostP0, almostP0))
                .build();

        final List<LineConvexSubset> segments = new ArrayList<>(input.getElements());
        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);

        Assertions.assertEquals(1, paths.size());
    }

    @Test
    void testConnectAll_flatLineRegion_1_oe() {
        final Vector2D p0 = Vector2D.ZERO;
        final Vector2D p1 = Vector2D.of(1, 0);

        final Segment seg0 = Lines.segmentFromPoints(p0, p1, TEST_PRECISION);
        final Segment seg1 = Lines.segmentFromPoints(p1, p0, TEST_PRECISION);
        final LineConvexSubset seg2 = Lines.fromPointAndAngle(p1, Angle.PI_OVER_TWO, TEST_PRECISION).segment(p1, p1);
        final LineConvexSubset seg3 = Lines.fromPointAndAngle(p0, -Angle.PI_OVER_TWO, TEST_PRECISION).segment(p0, p0);

        final List<LineConvexSubset> segments = new ArrayList<>(Arrays.asList(seg0, seg1, seg2, seg3));
        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);

        Assertions.assertEquals(1, paths.size());
    }

    @Test
    void testConnectAll_flatLineRegion_2_oe() {
        final Vector2D p0 = Vector2D.ZERO;
        final Vector2D p1 = Vector2D.of(1, 0);

        final Segment seg0 = Lines.segmentFromPoints(p0, p1, TEST_PRECISION);
        final Segment seg1 = Lines.segmentFromPoints(p1, p0, TEST_PRECISION);
        final LineConvexSubset seg2 = Lines.fromPointAndAngle(p1, Angle.PI_OVER_TWO, TEST_PRECISION).segment(p1, p1);
        final LineConvexSubset seg3 = Lines.fromPointAndAngle(p0, -Angle.PI_OVER_TWO, TEST_PRECISION).segment(p0, p0);

        final List<LineConvexSubset> segments = new ArrayList<>(Arrays.asList(seg0, seg1, seg2, seg3));
        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);


        final LinePath path = paths.get(0);
        Assertions.assertSame(seg0, path.getElements().get(0));
    }

    @Test
    void testConnectAll_flatLineRegion_3_oe() {
        final Vector2D p0 = Vector2D.ZERO;
        final Vector2D p1 = Vector2D.of(1, 0);

        final Segment seg0 = Lines.segmentFromPoints(p0, p1, TEST_PRECISION);
        final Segment seg1 = Lines.segmentFromPoints(p1, p0, TEST_PRECISION);
        final LineConvexSubset seg2 = Lines.fromPointAndAngle(p1, Angle.PI_OVER_TWO, TEST_PRECISION).segment(p1, p1);
        final LineConvexSubset seg3 = Lines.fromPointAndAngle(p0, -Angle.PI_OVER_TWO, TEST_PRECISION).segment(p0, p0);

        final List<LineConvexSubset> segments = new ArrayList<>(Arrays.asList(seg0, seg1, seg2, seg3));
        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);


        final LinePath path = paths.get(0);
        Assertions.assertSame(seg2, path.getElements().get(1));
    }

    @Test
    void testConnectAll_flatLineRegion_4_oe() {
        final Vector2D p0 = Vector2D.ZERO;
        final Vector2D p1 = Vector2D.of(1, 0);

        final Segment seg0 = Lines.segmentFromPoints(p0, p1, TEST_PRECISION);
        final Segment seg1 = Lines.segmentFromPoints(p1, p0, TEST_PRECISION);
        final LineConvexSubset seg2 = Lines.fromPointAndAngle(p1, Angle.PI_OVER_TWO, TEST_PRECISION).segment(p1, p1);
        final LineConvexSubset seg3 = Lines.fromPointAndAngle(p0, -Angle.PI_OVER_TWO, TEST_PRECISION).segment(p0, p0);

        final List<LineConvexSubset> segments = new ArrayList<>(Arrays.asList(seg0, seg1, seg2, seg3));
        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);


        final LinePath path = paths.get(0);
        Assertions.assertSame(seg1, path.getElements().get(2));
    }

    @Test
    void testConnectAll_flatLineRegion_5_oe() {
        final Vector2D p0 = Vector2D.ZERO;
        final Vector2D p1 = Vector2D.of(1, 0);

        final Segment seg0 = Lines.segmentFromPoints(p0, p1, TEST_PRECISION);
        final Segment seg1 = Lines.segmentFromPoints(p1, p0, TEST_PRECISION);
        final LineConvexSubset seg2 = Lines.fromPointAndAngle(p1, Angle.PI_OVER_TWO, TEST_PRECISION).segment(p1, p1);
        final LineConvexSubset seg3 = Lines.fromPointAndAngle(p0, -Angle.PI_OVER_TWO, TEST_PRECISION).segment(p0, p0);

        final List<LineConvexSubset> segments = new ArrayList<>(Arrays.asList(seg0, seg1, seg2, seg3));
        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);


        final LinePath path = paths.get(0);
        Assertions.assertSame(seg3, path.getElements().get(3));
    }

    @Test
    void testConnectAll_singlePointRegion_1_oe() {
        final Vector2D p0 = Vector2D.of(1, 0);

        final LineConvexSubset seg0 = Lines.fromPointAndAngle(p0, 0.0, TEST_PRECISION).segment(p0, p0);
        final LineConvexSubset seg1 = Lines.fromPointAndAngle(p0, Angle.PI_OVER_TWO, TEST_PRECISION).segment(p0, p0);
        final LineConvexSubset seg2 = Lines.fromPointAndAngle(p0, Math.PI, TEST_PRECISION).segment(p0, p0);
        final LineConvexSubset seg3 = Lines.fromPointAndAngle(p0, -Angle.PI_OVER_TWO, TEST_PRECISION).segment(p0, p0);

        final List<LineConvexSubset> segments = new ArrayList<>(Arrays.asList(seg0, seg1, seg2, seg3));
        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);

        Assertions.assertEquals(1, paths.size());
    }

    @Test
    void testConnectAll_singlePointRegion_2_oe() {
        final Vector2D p0 = Vector2D.of(1, 0);

        final LineConvexSubset seg0 = Lines.fromPointAndAngle(p0, 0.0, TEST_PRECISION).segment(p0, p0);
        final LineConvexSubset seg1 = Lines.fromPointAndAngle(p0, Angle.PI_OVER_TWO, TEST_PRECISION).segment(p0, p0);
        final LineConvexSubset seg2 = Lines.fromPointAndAngle(p0, Math.PI, TEST_PRECISION).segment(p0, p0);
        final LineConvexSubset seg3 = Lines.fromPointAndAngle(p0, -Angle.PI_OVER_TWO, TEST_PRECISION).segment(p0, p0);

        final List<LineConvexSubset> segments = new ArrayList<>(Arrays.asList(seg0, seg1, seg2, seg3));
        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);


        final LinePath path = paths.get(0);
        Assertions.assertSame(seg2, path.getElements().get(0));
    }

    @Test
    void testConnectAll_singlePointRegion_3_oe() {
        final Vector2D p0 = Vector2D.of(1, 0);

        final LineConvexSubset seg0 = Lines.fromPointAndAngle(p0, 0.0, TEST_PRECISION).segment(p0, p0);
        final LineConvexSubset seg1 = Lines.fromPointAndAngle(p0, Angle.PI_OVER_TWO, TEST_PRECISION).segment(p0, p0);
        final LineConvexSubset seg2 = Lines.fromPointAndAngle(p0, Math.PI, TEST_PRECISION).segment(p0, p0);
        final LineConvexSubset seg3 = Lines.fromPointAndAngle(p0, -Angle.PI_OVER_TWO, TEST_PRECISION).segment(p0, p0);

        final List<LineConvexSubset> segments = new ArrayList<>(Arrays.asList(seg0, seg1, seg2, seg3));
        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);


        final LinePath path = paths.get(0);
        Assertions.assertSame(seg3, path.getElements().get(1));
    }

    @Test
    void testConnectAll_singlePointRegion_4_oe() {
        final Vector2D p0 = Vector2D.of(1, 0);

        final LineConvexSubset seg0 = Lines.fromPointAndAngle(p0, 0.0, TEST_PRECISION).segment(p0, p0);
        final LineConvexSubset seg1 = Lines.fromPointAndAngle(p0, Angle.PI_OVER_TWO, TEST_PRECISION).segment(p0, p0);
        final LineConvexSubset seg2 = Lines.fromPointAndAngle(p0, Math.PI, TEST_PRECISION).segment(p0, p0);
        final LineConvexSubset seg3 = Lines.fromPointAndAngle(p0, -Angle.PI_OVER_TWO, TEST_PRECISION).segment(p0, p0);

        final List<LineConvexSubset> segments = new ArrayList<>(Arrays.asList(seg0, seg1, seg2, seg3));
        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);


        final LinePath path = paths.get(0);
        Assertions.assertSame(seg0, path.getElements().get(2));
    }

    @Test
    void testConnectAll_singlePointRegion_5_oe() {
        final Vector2D p0 = Vector2D.of(1, 0);

        final LineConvexSubset seg0 = Lines.fromPointAndAngle(p0, 0.0, TEST_PRECISION).segment(p0, p0);
        final LineConvexSubset seg1 = Lines.fromPointAndAngle(p0, Angle.PI_OVER_TWO, TEST_PRECISION).segment(p0, p0);
        final LineConvexSubset seg2 = Lines.fromPointAndAngle(p0, Math.PI, TEST_PRECISION).segment(p0, p0);
        final LineConvexSubset seg3 = Lines.fromPointAndAngle(p0, -Angle.PI_OVER_TWO, TEST_PRECISION).segment(p0, p0);

        final List<LineConvexSubset> segments = new ArrayList<>(Arrays.asList(seg0, seg1, seg2, seg3));
        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);


        final LinePath path = paths.get(0);
        Assertions.assertSame(seg1, path.getElements().get(3));
    }

    @Test
    void testConnectAll_pathWithPointLikeUnconnectedSegments_1_oe() {
        final Vector2D p0 = Vector2D.ZERO;
        final Vector2D p1 = Vector2D.of(1, 0);

        final LineConvexSubset seg0 = Lines.fromPointAndAngle(p1, 0.0, TEST_PRECISION).segment(p1, p1);
        final LineConvexSubset seg1 = Lines.fromPointAndAngle(p1, 0.25 * Math.PI, TEST_PRECISION).segment(p1, p1);
        final LineConvexSubset seg2 = Lines.fromPointAndAngle(p0, 0, TEST_PRECISION).segment(p0, p0);

        final List<LineConvexSubset> segments = new ArrayList<>(Arrays.asList(seg0, seg1, seg2));

        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);

        Assertions.assertEquals(2, paths.size());
    }

    @Test
    void testConnectAll_pathWithPointLikeUnconnectedSegments_2_oe() {
        final Vector2D p0 = Vector2D.ZERO;
        final Vector2D p1 = Vector2D.of(1, 0);

        final LineConvexSubset seg0 = Lines.fromPointAndAngle(p1, 0.0, TEST_PRECISION).segment(p1, p1);
        final LineConvexSubset seg1 = Lines.fromPointAndAngle(p1, 0.25 * Math.PI, TEST_PRECISION).segment(p1, p1);
        final LineConvexSubset seg2 = Lines.fromPointAndAngle(p0, 0, TEST_PRECISION).segment(p0, p0);

        final List<LineConvexSubset> segments = new ArrayList<>(Arrays.asList(seg0, seg1, seg2));

        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);


        final LinePath path0 = paths.get(0);
        Assertions.assertEquals(1, path0.getElements().size());
    }

    @Test
    void testConnectAll_pathWithPointLikeUnconnectedSegments_3_oe() {
        final Vector2D p0 = Vector2D.ZERO;
        final Vector2D p1 = Vector2D.of(1, 0);

        final LineConvexSubset seg0 = Lines.fromPointAndAngle(p1, 0.0, TEST_PRECISION).segment(p1, p1);
        final LineConvexSubset seg1 = Lines.fromPointAndAngle(p1, 0.25 * Math.PI, TEST_PRECISION).segment(p1, p1);
        final LineConvexSubset seg2 = Lines.fromPointAndAngle(p0, 0, TEST_PRECISION).segment(p0, p0);

        final List<LineConvexSubset> segments = new ArrayList<>(Arrays.asList(seg0, seg1, seg2));

        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);


        final LinePath path0 = paths.get(0);
        Assertions.assertSame(seg2, path0.getElements().get(0));
    }

    @Test
    void testConnectAll_pathWithPointLikeUnconnectedSegments_4_oe() {
        final Vector2D p0 = Vector2D.ZERO;
        final Vector2D p1 = Vector2D.of(1, 0);

        final LineConvexSubset seg0 = Lines.fromPointAndAngle(p1, 0.0, TEST_PRECISION).segment(p1, p1);
        final LineConvexSubset seg1 = Lines.fromPointAndAngle(p1, 0.25 * Math.PI, TEST_PRECISION).segment(p1, p1);
        final LineConvexSubset seg2 = Lines.fromPointAndAngle(p0, 0, TEST_PRECISION).segment(p0, p0);

        final List<LineConvexSubset> segments = new ArrayList<>(Arrays.asList(seg0, seg1, seg2));

        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);


        final LinePath path0 = paths.get(0);

        final LinePath path1 = paths.get(1);
        Assertions.assertEquals(2, path1.getElements().size());
    }

    @Test
    void testConnectAll_pathWithPointLikeUnconnectedSegments_5_oe() {
        final Vector2D p0 = Vector2D.ZERO;
        final Vector2D p1 = Vector2D.of(1, 0);

        final LineConvexSubset seg0 = Lines.fromPointAndAngle(p1, 0.0, TEST_PRECISION).segment(p1, p1);
        final LineConvexSubset seg1 = Lines.fromPointAndAngle(p1, 0.25 * Math.PI, TEST_PRECISION).segment(p1, p1);
        final LineConvexSubset seg2 = Lines.fromPointAndAngle(p0, 0, TEST_PRECISION).segment(p0, p0);

        final List<LineConvexSubset> segments = new ArrayList<>(Arrays.asList(seg0, seg1, seg2));

        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);


        final LinePath path0 = paths.get(0);

        final LinePath path1 = paths.get(1);
        Assertions.assertSame(seg0, path1.getElements().get(0));
    }

    @Test
    void testConnectAll_pathWithPointLikeUnconnectedSegments_6_oe() {
        final Vector2D p0 = Vector2D.ZERO;
        final Vector2D p1 = Vector2D.of(1, 0);

        final LineConvexSubset seg0 = Lines.fromPointAndAngle(p1, 0.0, TEST_PRECISION).segment(p1, p1);
        final LineConvexSubset seg1 = Lines.fromPointAndAngle(p1, 0.25 * Math.PI, TEST_PRECISION).segment(p1, p1);
        final LineConvexSubset seg2 = Lines.fromPointAndAngle(p0, 0, TEST_PRECISION).segment(p0, p0);

        final List<LineConvexSubset> segments = new ArrayList<>(Arrays.asList(seg0, seg1, seg2));

        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);


        final LinePath path0 = paths.get(0);

        final LinePath path1 = paths.get(1);
        Assertions.assertSame(seg1, path1.getElements().get(1));
    }

    @Test
    void testConnectAll_pathStartingWithPoint_1_oe() {
        final Vector2D p0 = Vector2D.ZERO;
        final Vector2D p1 = Vector2D.of(1, 0);
        final Vector2D p2 = Vector2D.of(1, 1);

        final LineConvexSubset seg0 = Lines.fromPointAndAngle(p0, Math.PI, TEST_PRECISION).segment(p0, p0);
        final LineConvexSubset seg1 = Lines.segmentFromPoints(p0, p1, TEST_PRECISION);
        final LineConvexSubset seg2 = Lines.segmentFromPoints(p1, p2, TEST_PRECISION);

        final List<LineConvexSubset> segments = new ArrayList<>(Arrays.asList(seg0, seg1, seg2));

        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);

        Assertions.assertEquals(1, paths.size());
    }

    @Test
    void testConnectAll_pathStartingWithPoint_2_oe() {
        final Vector2D p0 = Vector2D.ZERO;
        final Vector2D p1 = Vector2D.of(1, 0);
        final Vector2D p2 = Vector2D.of(1, 1);

        final LineConvexSubset seg0 = Lines.fromPointAndAngle(p0, Math.PI, TEST_PRECISION).segment(p0, p0);
        final LineConvexSubset seg1 = Lines.segmentFromPoints(p0, p1, TEST_PRECISION);
        final LineConvexSubset seg2 = Lines.segmentFromPoints(p1, p2, TEST_PRECISION);

        final List<LineConvexSubset> segments = new ArrayList<>(Arrays.asList(seg0, seg1, seg2));

        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);


        final LinePath path = paths.get(0);
        Assertions.assertSame(seg0, path.getElements().get(0));
    }

    @Test
    void testConnectAll_pathStartingWithPoint_3_oe() {
        final Vector2D p0 = Vector2D.ZERO;
        final Vector2D p1 = Vector2D.of(1, 0);
        final Vector2D p2 = Vector2D.of(1, 1);

        final LineConvexSubset seg0 = Lines.fromPointAndAngle(p0, Math.PI, TEST_PRECISION).segment(p0, p0);
        final LineConvexSubset seg1 = Lines.segmentFromPoints(p0, p1, TEST_PRECISION);
        final LineConvexSubset seg2 = Lines.segmentFromPoints(p1, p2, TEST_PRECISION);

        final List<LineConvexSubset> segments = new ArrayList<>(Arrays.asList(seg0, seg1, seg2));

        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);


        final LinePath path = paths.get(0);
        Assertions.assertSame(seg1, path.getElements().get(1));
    }

    @Test
    void testConnectAll_pathStartingWithPoint_4_oe() {
        final Vector2D p0 = Vector2D.ZERO;
        final Vector2D p1 = Vector2D.of(1, 0);
        final Vector2D p2 = Vector2D.of(1, 1);

        final LineConvexSubset seg0 = Lines.fromPointAndAngle(p0, Math.PI, TEST_PRECISION).segment(p0, p0);
        final LineConvexSubset seg1 = Lines.segmentFromPoints(p0, p1, TEST_PRECISION);
        final LineConvexSubset seg2 = Lines.segmentFromPoints(p1, p2, TEST_PRECISION);

        final List<LineConvexSubset> segments = new ArrayList<>(Arrays.asList(seg0, seg1, seg2));

        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);


        final LinePath path = paths.get(0);
        Assertions.assertSame(seg2, path.getElements().get(2));
    }

    @Test
    void testConnectAll_intersectingPaths_1_oe() {
        final LinePath a = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(-1, 1), Vector2D.of(0.5, 0), Vector2D.of(-1, -1))
                .build();

        final LinePath b = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(1, 1), Vector2D.of(-0.5, 0), Vector2D.of(1, -1))
                .build();

        final List<LineConvexSubset> segments = new ArrayList<>();
        segments.addAll(a.getElements());
        segments.addAll(b.getElements());

        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);

        Assertions.assertEquals(2, paths.size());
    }

    @Test
    void testInstancesCanBeReused_1_oe() {
        final LineConvexSubset a = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);
        final LineConvexSubset b = Lines.segmentFromPoints(Vector2D.Unit.PLUS_X, Vector2D.Unit.PLUS_Y, TEST_PRECISION);

        final List<LinePath> firstPaths = connector.connectAll(Collections.singletonList(a));
        final List<LinePath> secondPaths = connector.connectAll(Collections.singletonList(b));

        Assertions.assertEquals(1, firstPaths.size());
    }

    @Test
    void testInstancesCanBeReused_2_oe() {
        final LineConvexSubset a = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);
        final LineConvexSubset b = Lines.segmentFromPoints(Vector2D.Unit.PLUS_X, Vector2D.Unit.PLUS_Y, TEST_PRECISION);

        final List<LinePath> firstPaths = connector.connectAll(Collections.singletonList(a));
        final List<LinePath> secondPaths = connector.connectAll(Collections.singletonList(b));

        Assertions.assertEquals(1, secondPaths.size());
    }

    @Test
    void testInstancesCanBeReused_3_oe() {
        final LineConvexSubset a = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);
        final LineConvexSubset b = Lines.segmentFromPoints(Vector2D.Unit.PLUS_X, Vector2D.Unit.PLUS_Y, TEST_PRECISION);

        final List<LinePath> firstPaths = connector.connectAll(Collections.singletonList(a));
        final List<LinePath> secondPaths = connector.connectAll(Collections.singletonList(b));


        Assertions.assertSame(a, firstPaths.get(0).getElements().get(0));
    }

    @Test
    void testInstancesCanBeReused_4_oe() {
        final LineConvexSubset a = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);
        final LineConvexSubset b = Lines.segmentFromPoints(Vector2D.Unit.PLUS_X, Vector2D.Unit.PLUS_Y, TEST_PRECISION);

        final List<LinePath> firstPaths = connector.connectAll(Collections.singletonList(a));
        final List<LinePath> secondPaths = connector.connectAll(Collections.singletonList(b));


        Assertions.assertSame(b, secondPaths.get(0).getElements().get(0));
    }

    @Test
    void testAdd_1_oe() {
        final LineConvexSubset a = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);
        final LineConvexSubset b = Lines.segmentFromPoints(Vector2D.Unit.PLUS_X, Vector2D.of(1, 1), TEST_PRECISION);
        final LineConvexSubset c = Lines.segmentFromPoints(Vector2D.Unit.PLUS_X, Vector2D.of(2, 0), TEST_PRECISION);

        connector.add(Arrays.asList(a, b));
        connector.add(Collections.singletonList(c));

        final List<LinePath> paths = connector.connectAll();

        Assertions.assertEquals(2, paths.size());
    }

    @Test
    void testConnect_1_oe() {
        final LineConvexSubset a = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);
        final LineConvexSubset b = Lines.segmentFromPoints(Vector2D.Unit.PLUS_X, Vector2D.of(1, 1), TEST_PRECISION);
        final LineConvexSubset c = Lines.segmentFromPoints(Vector2D.Unit.PLUS_X, Vector2D.of(2, 0), TEST_PRECISION);

        connector.connect(Arrays.asList(a, b));
        connector.connect(Collections.singletonList(c));

        final List<LinePath> paths = connector.connectAll();

        Assertions.assertEquals(2, paths.size());
    }

    @Test
    void testConnectableSegment_hashCode_1_oe() {
        final LineConvexSubset segA = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);
        final LineConvexSubset segB = Lines.segmentFromPoints(Vector2D.Unit.PLUS_X, Vector2D.of(1, 1), TEST_PRECISION);

        final ConnectableLineSubset a = new ConnectableLineSubset(segA);

        final int hash = a.hashCode();

        Assertions.assertEquals(hash, a.hashCode());
    }

    @Test
    void testConnectableSegment_hashCode_2_oe() {
        final LineConvexSubset segA = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);
        final LineConvexSubset segB = Lines.segmentFromPoints(Vector2D.Unit.PLUS_X, Vector2D.of(1, 1), TEST_PRECISION);

        final ConnectableLineSubset a = new ConnectableLineSubset(segA);

        final int hash = a.hashCode();


        Assertions.assertNotEquals(hash, new ConnectableLineSubset(segB).hashCode());
    }

    @Test
    void testConnectableSegment_hashCode_3_oe() {
        final LineConvexSubset segA = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);
        final LineConvexSubset segB = Lines.segmentFromPoints(Vector2D.Unit.PLUS_X, Vector2D.of(1, 1), TEST_PRECISION);

        final ConnectableLineSubset a = new ConnectableLineSubset(segA);

        final int hash = a.hashCode();


        Assertions.assertNotEquals(hash, new ConnectableLineSubset(Vector2D.Unit.PLUS_X).hashCode());
    }

    @Test
    void testConnectableSegment_hashCode_4_oe() {
        final LineConvexSubset segA = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);
        final LineConvexSubset segB = Lines.segmentFromPoints(Vector2D.Unit.PLUS_X, Vector2D.of(1, 1), TEST_PRECISION);

        final ConnectableLineSubset a = new ConnectableLineSubset(segA);

        final int hash = a.hashCode();



        Assertions.assertEquals(hash, new ConnectableLineSubset(segA).hashCode());
    }

    @Test
    void testConnectableSegment_equals_1_oe() {
        final LineConvexSubset segA = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);
        final LineConvexSubset segB = Lines.segmentFromPoints(Vector2D.Unit.PLUS_X, Vector2D.of(1, 1), TEST_PRECISION);

        final ConnectableLineSubset a = new ConnectableLineSubset(segA);

        Assertions.assertEquals(a, a);
    }

    @Test
    void testConnectableSegment_equals_2_oe() {
        final LineConvexSubset segA = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);
        final LineConvexSubset segB = Lines.segmentFromPoints(Vector2D.Unit.PLUS_X, Vector2D.of(1, 1), TEST_PRECISION);

        final ConnectableLineSubset a = new ConnectableLineSubset(segA);


        Assertions.assertFalse(a.equals(null));
    }

    @Test
    void testConnectableSegment_equals_3_oe() {
        final LineConvexSubset segA = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);
        final LineConvexSubset segB = Lines.segmentFromPoints(Vector2D.Unit.PLUS_X, Vector2D.of(1, 1), TEST_PRECISION);

        final ConnectableLineSubset a = new ConnectableLineSubset(segA);


        Assertions.assertFalse(a.equals(new Object()));
    }

    @Test
    void testConnectableSegment_equals_4_oe() {
        final LineConvexSubset segA = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);
        final LineConvexSubset segB = Lines.segmentFromPoints(Vector2D.Unit.PLUS_X, Vector2D.of(1, 1), TEST_PRECISION);

        final ConnectableLineSubset a = new ConnectableLineSubset(segA);



        Assertions.assertNotEquals(a, new ConnectableLineSubset(segB));
    }

    @Test
    void testConnectableSegment_equals_5_oe() {
        final LineConvexSubset segA = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);
        final LineConvexSubset segB = Lines.segmentFromPoints(Vector2D.Unit.PLUS_X, Vector2D.of(1, 1), TEST_PRECISION);

        final ConnectableLineSubset a = new ConnectableLineSubset(segA);



        Assertions.assertNotEquals(a, new ConnectableLineSubset(Vector2D.Unit.PLUS_X));
    }

    @Test
    void testConnectableSegment_equals_6_oe() {
        final LineConvexSubset segA = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);
        final LineConvexSubset segB = Lines.segmentFromPoints(Vector2D.Unit.PLUS_X, Vector2D.of(1, 1), TEST_PRECISION);

        final ConnectableLineSubset a = new ConnectableLineSubset(segA);




        Assertions.assertEquals(a, new ConnectableLineSubset(segA));
    }

@Test
    void testConnectAll_disjointSegments_2_oe() {
        final LineConvexSubset a = Y_AXIS.segment(Vector2D.of(0, 1), Vector2D.of(0, 2));
        final LineConvexSubset b = Y_AXIS.segment(Vector2D.of(0, -1), Vector2D.ZERO);

        final List<LineConvexSubset> segments = Arrays.asList(a, b);

        final List<LinePath> paths = connector.connectAll(segments);


        assertFinitePath(paths.get(0), Vector2D.of(0, -1), Vector2D.ZERO);
    }

@Test
    void testConnectAll_disjointSegments_3_oe() {
        final LineConvexSubset a = Y_AXIS.segment(Vector2D.of(0, 1), Vector2D.of(0, 2));
        final LineConvexSubset b = Y_AXIS.segment(Vector2D.of(0, -1), Vector2D.ZERO);

        final List<LineConvexSubset> segments = Arrays.asList(a, b);

        final List<LinePath> paths = connector.connectAll(segments);


        assertFinitePath(paths.get(1), Vector2D.of(0, 1), Vector2D.of(0, 2));
    }

@Test
    void testConnectAll_singleClosedPath_2_oe() {
        final LinePath input = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(1, 1), Vector2D.ZERO, Vector2D.of(1, 0))
                .close();

        final List<LineConvexSubset> segments = new ArrayList<>(input.getElements());
        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);


        assertFinitePath(paths.get(0), Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(1, 1), Vector2D.ZERO);
    }

@Test
    void testConnectAll_multipleClosedPaths_2_oe() {
        final LinePath a = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(1, 1), Vector2D.ZERO, Vector2D.of(1, 0))
                .close();

        final LinePath b = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(0, 1), Vector2D.of(-1, 0), Vector2D.of(-0.5, 0))
                .close();

        final LinePath c = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(1, 3), Vector2D.of(0, 2), Vector2D.of(1, 2))
                .close();

        final List<LineConvexSubset> segments = new ArrayList<>();
        segments.addAll(a.getElements());
        segments.addAll(b.getElements());
        segments.addAll(c.getElements());

        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);


        assertFinitePath(paths.get(0), Vector2D.of(-1, 0), Vector2D.of(-0.5, 0), Vector2D.of(0, 1), Vector2D.of(-1, 0));
    }

@Test
    void testConnectAll_multipleClosedPaths_3_oe() {
        final LinePath a = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(1, 1), Vector2D.ZERO, Vector2D.of(1, 0))
                .close();

        final LinePath b = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(0, 1), Vector2D.of(-1, 0), Vector2D.of(-0.5, 0))
                .close();

        final LinePath c = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(1, 3), Vector2D.of(0, 2), Vector2D.of(1, 2))
                .close();

        final List<LineConvexSubset> segments = new ArrayList<>();
        segments.addAll(a.getElements());
        segments.addAll(b.getElements());
        segments.addAll(c.getElements());

        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);



        assertFinitePath(paths.get(1), Vector2D.ZERO, Vector2D.of(1, 0), Vector2D.of(1, 1), Vector2D.ZERO);
    }

@Test
    void testConnectAll_multipleClosedPaths_4_oe() {
        final LinePath a = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(1, 1), Vector2D.ZERO, Vector2D.of(1, 0))
                .close();

        final LinePath b = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(0, 1), Vector2D.of(-1, 0), Vector2D.of(-0.5, 0))
                .close();

        final LinePath c = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(1, 3), Vector2D.of(0, 2), Vector2D.of(1, 2))
                .close();

        final List<LineConvexSubset> segments = new ArrayList<>();
        segments.addAll(a.getElements());
        segments.addAll(b.getElements());
        segments.addAll(c.getElements());

        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);




        assertFinitePath(paths.get(2), Vector2D.of(0, 2), Vector2D.of(1, 2), Vector2D.of(1, 3), Vector2D.of(0, 2));
    }

@Test
    void testConnectAll_singleOpenPath_2_oe() {
        final LinePath input = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(1, 1), Vector2D.ZERO, Vector2D.of(1, 0))
                .build();

        final List<LineConvexSubset> segments = new ArrayList<>(input.getElements());
        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);


        assertFinitePath(paths.get(0), Vector2D.of(1, 1), Vector2D.ZERO, Vector2D.of(1, 0));
    }

@Test
    void testConnectAll_mixOfOpenConnectedAndInfinite_2_oe() {
        final LineConvexSubset inputYInf = Y_AXIS.reverseRayTo(Vector2D.ZERO);
        final LineConvexSubset inputXInf = Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.MINUS_X, TEST_PRECISION)
                .rayFrom(Vector2D.ZERO);

        final LinePath closedPath = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(0, 2), Vector2D.of(1, 2), Vector2D.of(1, 3))
                .close();

        final LinePath openPath = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(-1, 3), Vector2D.of(0, 1), Vector2D.of(1, 1))
                .build();

        final List<LineConvexSubset> segments = new ArrayList<>();
        segments.add(inputYInf);
        segments.add(inputXInf);
        segments.addAll(closedPath.getElements());
        segments.addAll(openPath.getElements());

        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);


        assertFinitePath(paths.get(0), Vector2D.of(-1, 3), Vector2D.of(0, 1), Vector2D.of(1, 1));
    }

@Test
    void testConnectAll_mixOfOpenConnectedAndInfinite_7_oe() {
        final LineConvexSubset inputYInf = Y_AXIS.reverseRayTo(Vector2D.ZERO);
        final LineConvexSubset inputXInf = Lines.fromPoints(Vector2D.ZERO, Vector2D.Unit.MINUS_X, TEST_PRECISION)
                .rayFrom(Vector2D.ZERO);

        final LinePath closedPath = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(0, 2), Vector2D.of(1, 2), Vector2D.of(1, 3))
                .close();

        final LinePath openPath = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(-1, 3), Vector2D.of(0, 1), Vector2D.of(1, 1))
                .build();

        final List<LineConvexSubset> segments = new ArrayList<>();
        segments.add(inputYInf);
        segments.add(inputXInf);
        segments.addAll(closedPath.getElements());
        segments.addAll(openPath.getElements());

        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);



        final LinePath infPath = paths.get(1);

        assertFinitePath(paths.get(2), Vector2D.of(0, 2), Vector2D.of(1, 2), Vector2D.of(1, 3), Vector2D.of(0, 2));
    }

@Test
    void testConnectAll_pathWithSinglePoint_2_oe() {
        final Vector2D p0 = Vector2D.ZERO;

        final List<LineConvexSubset> segments = Collections.singletonList(Lines.fromPointAndAngle(p0, 0, TEST_PRECISION).segment(p0, p0));

        final List<LinePath> paths = connector.connectAll(segments);


        assertFinitePath(paths.get(0), p0, p0);
    }

@Test
    void testConnectAll_pathWithPointLikeConnectedSegments_2_oe() {
        final Vector2D p0 = Vector2D.ZERO;
        final Vector2D p1 = Vector2D.of(1, 0);
        final Vector2D p2 = Vector2D.of(1, 1);

        final Vector2D almostP0 = Vector2D.of(-1e-20, -1e-20);
        final Vector2D almostP1 = Vector2D.of(1 - 1e-15, 0);

        final LinePath input = LinePath.builder(TEST_PRECISION)
                .appendVertices(p0, p1)
                .append(Lines.fromPointAndAngle(p1, 0.25 * Math.PI, TEST_PRECISION).segment(p1, p1))
                .append(Lines.fromPointAndAngle(p1, -0.25 * Math.PI, TEST_PRECISION).segment(almostP1, almostP1))
                .append(p2)
                .append(p0)
                .append(Lines.fromPointAndAngle(Vector2D.ZERO, -Angle.PI_OVER_TWO, TEST_PRECISION)
                        .segment(almostP0, almostP0))
                .build();

        final List<LineConvexSubset> segments = new ArrayList<>(input.getElements());
        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);


        assertFinitePath(paths.get(0), p0, p1, almostP1, p1, p2, p0, almostP0);
    }

@Test
    void testConnectAll_intersectingPaths_2_oe() {
        final LinePath a = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(-1, 1), Vector2D.of(0.5, 0), Vector2D.of(-1, -1))
                .build();

        final LinePath b = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(1, 1), Vector2D.of(-0.5, 0), Vector2D.of(1, -1))
                .build();

        final List<LineConvexSubset> segments = new ArrayList<>();
        segments.addAll(a.getElements());
        segments.addAll(b.getElements());

        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);


        assertFinitePath(paths.get(0), Vector2D.of(-1, 1), Vector2D.of(0.5, 0), Vector2D.of(-1, -1));
    }

@Test
    void testConnectAll_intersectingPaths_3_oe() {
        final LinePath a = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(-1, 1), Vector2D.of(0.5, 0), Vector2D.of(-1, -1))
                .build();

        final LinePath b = LinePath.builder(TEST_PRECISION)
                .appendVertices(Vector2D.of(1, 1), Vector2D.of(-0.5, 0), Vector2D.of(1, -1))
                .build();

        final List<LineConvexSubset> segments = new ArrayList<>();
        segments.addAll(a.getElements());
        segments.addAll(b.getElements());

        shuffle(segments);

        final List<LinePath> paths = connector.connectAll(segments);



        assertFinitePath(paths.get(1), Vector2D.of(1, 1), Vector2D.of(-0.5, 0), Vector2D.of(1, -1));
    }

@Test
    void testAdd_2_oe() {
        final LineConvexSubset a = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);
        final LineConvexSubset b = Lines.segmentFromPoints(Vector2D.Unit.PLUS_X, Vector2D.of(1, 1), TEST_PRECISION);
        final LineConvexSubset c = Lines.segmentFromPoints(Vector2D.Unit.PLUS_X, Vector2D.of(2, 0), TEST_PRECISION);

        connector.add(Arrays.asList(a, b));
        connector.add(Collections.singletonList(c));

        final List<LinePath> paths = connector.connectAll();


        assertFinitePath(paths.get(0), Vector2D.ZERO, Vector2D.Unit.PLUS_X, Vector2D.of(2, 0));
    }

@Test
    void testAdd_3_oe() {
        final LineConvexSubset a = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);
        final LineConvexSubset b = Lines.segmentFromPoints(Vector2D.Unit.PLUS_X, Vector2D.of(1, 1), TEST_PRECISION);
        final LineConvexSubset c = Lines.segmentFromPoints(Vector2D.Unit.PLUS_X, Vector2D.of(2, 0), TEST_PRECISION);

        connector.add(Arrays.asList(a, b));
        connector.add(Collections.singletonList(c));

        final List<LinePath> paths = connector.connectAll();


        assertFinitePath(paths.get(1), Vector2D.Unit.PLUS_X, Vector2D.of(1, 1));
    }

@Test
    void testConnect_2_oe() {
        final LineConvexSubset a = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);
        final LineConvexSubset b = Lines.segmentFromPoints(Vector2D.Unit.PLUS_X, Vector2D.of(1, 1), TEST_PRECISION);
        final LineConvexSubset c = Lines.segmentFromPoints(Vector2D.Unit.PLUS_X, Vector2D.of(2, 0), TEST_PRECISION);

        connector.connect(Arrays.asList(a, b));
        connector.connect(Collections.singletonList(c));

        final List<LinePath> paths = connector.connectAll();


        assertFinitePath(paths.get(0), Vector2D.ZERO, Vector2D.Unit.PLUS_X, Vector2D.of(1, 1));
    }

@Test
    void testConnect_3_oe() {
        final LineConvexSubset a = Lines.segmentFromPoints(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);
        final LineConvexSubset b = Lines.segmentFromPoints(Vector2D.Unit.PLUS_X, Vector2D.of(1, 1), TEST_PRECISION);
        final LineConvexSubset c = Lines.segmentFromPoints(Vector2D.Unit.PLUS_X, Vector2D.of(2, 0), TEST_PRECISION);

        connector.connect(Arrays.asList(a, b));
        connector.connect(Collections.singletonList(c));

        final List<LinePath> paths = connector.connectAll();


        assertFinitePath(paths.get(1), Vector2D.Unit.PLUS_X, Vector2D.of(2, 0));
    }

}
