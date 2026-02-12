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
package org.apache.commons.geometry.spherical.twod;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.core.RegionLocation;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.geometry.spherical.SphericalTestUtils;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GreatArcPathTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    @Test
    void testBuilder_points_noPrecisionGiven() {
        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> GreatArcPath.builder(null)
            .append(Point2S.PLUS_I)
            .append(Point2S.PLUS_J), IllegalStateException.class, "Unable to create arc: no point precision specified");

        GeometryTestUtils.assertThrowsWithMessage(() -> GreatArcPath.builder(null)
            .prepend(Point2S.PLUS_I)
            .prepend(Point2S.PLUS_J), IllegalStateException.class, "Unable to create arc: no point precision specified");
    }

    @Test
    void testBuilder_arcsNotConnected() {
        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> GreatArcPath.builder(TEST_PRECISION)
            .append(Point2S.PLUS_I)
            .append(Point2S.PLUS_J)
            .append(GreatCircles.arcFromPoints(Point2S.PLUS_K, Point2S.MINUS_J, TEST_PRECISION)), IllegalStateException.class, Pattern.compile("^Path arcs are not connected.*"));

        GeometryTestUtils.assertThrowsWithMessage(() -> GreatArcPath.builder(TEST_PRECISION)
            .prepend(Point2S.PLUS_I)
            .prepend(Point2S.PLUS_J)
            .prepend(GreatCircles.arcFromPoints(Point2S.PLUS_K, Point2S.MINUS_J, TEST_PRECISION)), IllegalStateException.class, Pattern.compile("^Path arcs are not connected.*"));
    }

    @Test
    void testBuilder_addToFullArc() {
        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> GreatArcPath.builder(TEST_PRECISION)
            .append(GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION).span())
            .append(Point2S.PLUS_J), IllegalStateException.class, Pattern.compile("^Cannot add point .* after full arc.*"));

        GeometryTestUtils.assertThrowsWithMessage(() -> GreatArcPath.builder(TEST_PRECISION)
            .prepend(GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION).span())
            .prepend(Point2S.PLUS_J), IllegalStateException.class, Pattern.compile("^Cannot add point .* before full arc.*"));
    }

    @Test
    void testBuilder_onlySinglePointGiven() {
        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> GreatArcPath.builder(TEST_PRECISION)
            .append(Point2S.PLUS_J)
            .build(), IllegalStateException.class, Pattern.compile("^Unable to create path; only a single point provided.*"));

        GeometryTestUtils.assertThrowsWithMessage(() -> GreatArcPath.builder(TEST_PRECISION)
            .prepend(Point2S.PLUS_J)
            .build(), IllegalStateException.class,  Pattern.compile("^Unable to create path; only a single point provided.*"));
    }

    @Test
    void testBuilder_cannotClose() {
        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> GreatArcPath.builder(TEST_PRECISION)
            .append(GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION).span())
            .close(), IllegalStateException.class, "Unable to close path: path is full");
    }

    @Test
    void testToString_singleFullArc() {
        // arrange
        final GreatArcPath path = GreatArcPath.fromArcs(GreatCircles.fromPole(Vector3D.Unit.PLUS_Z, TEST_PRECISION).span());

        // act
        final String str = path.toString();

        // assert
        GeometryTestUtils.assertContains("GreatArcPath[full= true, circle= GreatCircle[", str);
    }

    @Test
    void testToString_nonFullArcs() {
        // arrange
        final GreatArcPath path = GreatArcPath.builder(TEST_PRECISION)
                .append(Point2S.PLUS_I)
                .append(Point2S.PLUS_J)
                .build();

        // act
        final String str = path.toString();

        // assert
        GeometryTestUtils.assertContains("ArcPath[vertices= [", str);
    }

    private static void assertArc(final GreatArc arc, final Point2S start, final Point2S end) {
        SphericalTestUtils.assertPointsEq(start, arc.getStartPoint(), TEST_EPS);
        SphericalTestUtils.assertPointsEq(end, arc.getEndPoint(), TEST_EPS);
    }

    private static void assertPoints(final Collection<Point2S> expected, final Collection<Point2S> actual) {
        Assertions.assertEquals(expected.size(), actual.size());

        final Iterator<Point2S> expIt = expected.iterator();
        final Iterator<Point2S> actIt = actual.iterator();

        while (expIt.hasNext() && actIt.hasNext()) {
            SphericalTestUtils.assertPointsEq(expIt.next(), actIt.next(), TEST_EPS);
        }
    }

    @Test
    void testEmpty_1_oe() {
        // act
        final GreatArcPath path = GreatArcPath.empty();

        // assert
        Assertions.assertTrue(path.isEmpty());
    }

    @Test
    void testEmpty_2_oe() {
        // act
        final GreatArcPath path = GreatArcPath.empty();

        // assert
        // removed other assertion
        Assertions.assertFalse(path.isClosed());
    }

    @Test
    void testEmpty_3_oe() {
        // act
        final GreatArcPath path = GreatArcPath.empty();

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertNull(path.getStartVertex());
    }

    @Test
    void testEmpty_4_oe() {
        // act
        final GreatArcPath path = GreatArcPath.empty();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(path.getEndVertex());
    }

    @Test
    void testEmpty_5_oe() {
        // act
        final GreatArcPath path = GreatArcPath.empty();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertNull(path.getStartArc());
    }

    @Test
    void testEmpty_6_oe() {
        // act
        final GreatArcPath path = GreatArcPath.empty();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(path.getEndArc());
    }

    @Test
    void testEmpty_7_oe() {
        // act
        final GreatArcPath path = GreatArcPath.empty();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0, path.getArcs().size());
    }

    @Test
    void testEmpty_8_oe() {
        // act
        final GreatArcPath path = GreatArcPath.empty();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0, path.getVertices().size());
    }

    @Test
    void testFromVertices_boolean_empty_1_oe() {
        // act
        final GreatArcPath path = GreatArcPath.fromVertices(Collections.emptyList(), true, TEST_PRECISION);

        // assert
        Assertions.assertTrue(path.isEmpty());
    }

    @Test
    void testFromVertices_boolean_empty_2_oe() {
        // act
        final GreatArcPath path = GreatArcPath.fromVertices(Collections.emptyList(), true, TEST_PRECISION);

        // assert
        // removed other assertion

        Assertions.assertNull(path.getStartVertex());
    }

    @Test
    void testFromVertices_boolean_empty_3_oe() {
        // act
        final GreatArcPath path = GreatArcPath.fromVertices(Collections.emptyList(), true, TEST_PRECISION);

        // assert
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(path.getEndVertex());
    }

    @Test
    void testFromVertices_boolean_empty_4_oe() {
        // act
        final GreatArcPath path = GreatArcPath.fromVertices(Collections.emptyList(), true, TEST_PRECISION);

        // assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertNull(path.getStartArc());
    }

    @Test
    void testFromVertices_boolean_empty_5_oe() {
        // act
        final GreatArcPath path = GreatArcPath.fromVertices(Collections.emptyList(), true, TEST_PRECISION);

        // assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(path.getEndArc());
    }

    @Test
    void testFromVertices_boolean_empty_6_oe() {
        // act
        final GreatArcPath path = GreatArcPath.fromVertices(Collections.emptyList(), true, TEST_PRECISION);

        // assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0, path.getArcs().size());
    }

    @Test
    void testFromVertices_boolean_empty_7_oe() {
        // act
        final GreatArcPath path = GreatArcPath.fromVertices(Collections.emptyList(), true, TEST_PRECISION);

        // assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0, path.getVertices().size());
    }

    @Test
    void testFromVertices_boolean_notClosed_1_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, false, TEST_PRECISION);

        // assert
        Assertions.assertFalse(path.isEmpty());
    }

    @Test
    void testFromVertices_boolean_notClosed_2_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, false, TEST_PRECISION);

        // assert
        // removed other assertion
        Assertions.assertFalse(path.isClosed());
    }

    @Test
    void testFromVertices_boolean_notClosed_5_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, false, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        Assertions.assertEquals(2, arcs.size());
    }

    @Test
    void testFromVertices_boolean_closed_1_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, true, TEST_PRECISION);

        // assert
        Assertions.assertFalse(path.isEmpty());
    }

    @Test
    void testFromVertices_boolean_closed_2_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, true, TEST_PRECISION);

        // assert
        // removed other assertion
        Assertions.assertTrue(path.isClosed());
    }

    @Test
    void testFromVertices_boolean_closed_5_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, true, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        Assertions.assertEquals(3, arcs.size());
    }

    @Test
    void testFromVertices_boolean_closed_pointsConsideredEqual_1_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final Point2S almostPlusI = Point2S.of(1e-4, Angle.PI_OVER_TWO);

        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J,
                almostPlusI);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, true, precision);

        // assert
        Assertions.assertFalse(path.isEmpty());
    }

    @Test
    void testFromVertices_boolean_closed_pointsConsideredEqual_2_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final Point2S almostPlusI = Point2S.of(1e-4, Angle.PI_OVER_TWO);

        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J,
                almostPlusI);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, true, precision);

        // assert
        // removed other assertion
        Assertions.assertTrue(path.isClosed());
    }

    @Test
    void testFromVertices_boolean_closed_pointsConsideredEqual_5_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final Point2S almostPlusI = Point2S.of(1e-4, Angle.PI_OVER_TWO);

        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J,
                almostPlusI);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, true, precision);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        Assertions.assertEquals(3, arcs.size());
    }

    @Test
    void testFromVertices_1_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.MINUS_I,
                Point2S.MINUS_J,
                Point2S.PLUS_I);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, TEST_PRECISION);

        // assert
        Assertions.assertFalse(path.isEmpty());
    }

    @Test
    void testFromVertices_2_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.MINUS_I,
                Point2S.MINUS_J,
                Point2S.PLUS_I);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, TEST_PRECISION);

        // assert
        // removed other assertion
        Assertions.assertFalse(path.isClosed());
    }

    @Test
    void testFromVertices_5_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.MINUS_I,
                Point2S.MINUS_J,
                Point2S.PLUS_I);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        Assertions.assertEquals(2, arcs.size());
    }

    @Test
    void testFromVertexLoop_1_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.MINUS_I,
                Point2S.MINUS_J,
                Point2S.MINUS_K);

        // act
        final GreatArcPath path = GreatArcPath.fromVertexLoop(points, TEST_PRECISION);

        // assert
        Assertions.assertFalse(path.isEmpty());
    }

    @Test
    void testFromVertexLoop_2_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.MINUS_I,
                Point2S.MINUS_J,
                Point2S.MINUS_K);

        // act
        final GreatArcPath path = GreatArcPath.fromVertexLoop(points, TEST_PRECISION);

        // assert
        // removed other assertion
        Assertions.assertTrue(path.isClosed());
    }

    @Test
    void testFromVertexLoop_5_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.MINUS_I,
                Point2S.MINUS_J,
                Point2S.MINUS_K);

        // act
        final GreatArcPath path = GreatArcPath.fromVertexLoop(points, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        Assertions.assertEquals(3, arcs.size());
    }

    @Test
    void testFromArcs_1_oe() {
        // arrange
        final Point2S ptA = Point2S.PLUS_I;
        final Point2S ptB = Point2S.of(1, Angle.PI_OVER_TWO);
        final Point2S ptC = Point2S.of(1, Angle.PI_OVER_TWO - 1);
        final Point2S ptD = Point2S.of(2, Angle.PI_OVER_TWO - 1);

        final GreatArc a = GreatCircles.arcFromPoints(ptA, ptB, TEST_PRECISION);
        final GreatArc b = GreatCircles.arcFromPoints(ptB, ptC, TEST_PRECISION);
        final GreatArc c = GreatCircles.arcFromPoints(ptC, ptD, TEST_PRECISION);

        // act
        final GreatArcPath path = GreatArcPath.fromArcs(a, b, c);

        // assert
        Assertions.assertFalse(path.isEmpty());
    }

    @Test
    void testFromArcs_2_oe() {
        // arrange
        final Point2S ptA = Point2S.PLUS_I;
        final Point2S ptB = Point2S.of(1, Angle.PI_OVER_TWO);
        final Point2S ptC = Point2S.of(1, Angle.PI_OVER_TWO - 1);
        final Point2S ptD = Point2S.of(2, Angle.PI_OVER_TWO - 1);

        final GreatArc a = GreatCircles.arcFromPoints(ptA, ptB, TEST_PRECISION);
        final GreatArc b = GreatCircles.arcFromPoints(ptB, ptC, TEST_PRECISION);
        final GreatArc c = GreatCircles.arcFromPoints(ptC, ptD, TEST_PRECISION);

        // act
        final GreatArcPath path = GreatArcPath.fromArcs(a, b, c);

        // assert
        // removed other assertion
        Assertions.assertFalse(path.isClosed());
    }

    @Test
    void testFromArcs_5_oe() {
        // arrange
        final Point2S ptA = Point2S.PLUS_I;
        final Point2S ptB = Point2S.of(1, Angle.PI_OVER_TWO);
        final Point2S ptC = Point2S.of(1, Angle.PI_OVER_TWO - 1);
        final Point2S ptD = Point2S.of(2, Angle.PI_OVER_TWO - 1);

        final GreatArc a = GreatCircles.arcFromPoints(ptA, ptB, TEST_PRECISION);
        final GreatArc b = GreatCircles.arcFromPoints(ptB, ptC, TEST_PRECISION);
        final GreatArc c = GreatCircles.arcFromPoints(ptC, ptD, TEST_PRECISION);

        // act
        final GreatArcPath path = GreatArcPath.fromArcs(a, b, c);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        Assertions.assertEquals(3, arcs.size());
    }

    @Test
    void testFromArcs_full_1_oe() {
        // arrange
        final GreatArc fullArc = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, TEST_PRECISION).span();

        // act
        final GreatArcPath path = GreatArcPath.fromArcs(fullArc);

        // assert
        Assertions.assertFalse(path.isEmpty());
    }

    @Test
    void testFromArcs_full_2_oe() {
        // arrange
        final GreatArc fullArc = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, TEST_PRECISION).span();

        // act
        final GreatArcPath path = GreatArcPath.fromArcs(fullArc);

        // assert
        // removed other assertion
        Assertions.assertFalse(path.isClosed());
    }

    @Test
    void testFromArcs_full_3_oe() {
        // arrange
        final GreatArc fullArc = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, TEST_PRECISION).span();

        // act
        final GreatArcPath path = GreatArcPath.fromArcs(fullArc);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertSame(fullArc, path.getStartArc());
    }

    @Test
    void testFromArcs_full_4_oe() {
        // arrange
        final GreatArc fullArc = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, TEST_PRECISION).span();

        // act
        final GreatArcPath path = GreatArcPath.fromArcs(fullArc);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertSame(fullArc, path.getEndArc());
    }

    @Test
    void testFromArcs_full_5_oe() {
        // arrange
        final GreatArc fullArc = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, TEST_PRECISION).span();

        // act
        final GreatArcPath path = GreatArcPath.fromArcs(fullArc);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertNull(path.getStartVertex());
    }

    @Test
    void testFromArcs_full_6_oe() {
        // arrange
        final GreatArc fullArc = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, TEST_PRECISION).span();

        // act
        final GreatArcPath path = GreatArcPath.fromArcs(fullArc);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(path.getEndVertex());
    }

    @Test
    void testFromArcs_full_7_oe() {
        // arrange
        final GreatArc fullArc = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, TEST_PRECISION).span();

        // act
        final GreatArcPath path = GreatArcPath.fromArcs(fullArc);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        Assertions.assertEquals(1, arcs.size());
    }

    @Test
    void testFromArcs_full_8_oe() {
        // arrange
        final GreatArc fullArc = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, TEST_PRECISION).span();

        // act
        final GreatArcPath path = GreatArcPath.fromArcs(fullArc);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion

        Assertions.assertSame(fullArc, arcs.get(0));
    }

    @Test
    void testBoundaryStream_1_oe() {
        // arrange
        final GreatArc fullArc = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, TEST_PRECISION).span();
        final GreatArcPath path = GreatArcPath.fromArcs(fullArc);

        // act
        final List<GreatArc> arcs = path.boundaryStream().collect(Collectors.toList());

        // assert
        Assertions.assertEquals(1, arcs.size());
    }

    @Test
    void testBoundaryStream_2_oe() {
        // arrange
        final GreatArc fullArc = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, TEST_PRECISION).span();
        final GreatArcPath path = GreatArcPath.fromArcs(fullArc);

        // act
        final List<GreatArc> arcs = path.boundaryStream().collect(Collectors.toList());

        // assert
        // removed other assertion
        Assertions.assertSame(fullArc, arcs.get(0));
    }

    @Test
    void testBoundaryStream_noBoundaries_1_oe() {
        // arrange
        final GreatArcPath path = GreatArcPath.empty();

        // act
        final List<GreatArc> arcs = path.boundaryStream().collect(Collectors.toList());

        // assert
        Assertions.assertEquals(0, arcs.size());
    }

    @Test
    void testToTree_empty_1_oe() {
        // act
        final RegionBSPTree2S tree = GreatArcPath.empty().toTree();

        // assert
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testToTree_empty_2_oe() {
        // act
        final RegionBSPTree2S tree = GreatArcPath.empty().toTree();

        // assert
        // removed other assertion
        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void testToTree_halfSpace_1_oe() {
        // arrange
        final GreatArcPath path = GreatArcPath.builder(TEST_PRECISION)
                .append(Point2S.PLUS_I)
                .append(Point2S.PLUS_J)
                .build();

        // act
        final RegionBSPTree2S tree = path.toTree();

        // assert
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testToTree_halfSpace_2_oe() {
        // arrange
        final GreatArcPath path = GreatArcPath.builder(TEST_PRECISION)
                .append(Point2S.PLUS_I)
                .append(Point2S.PLUS_J)
                .build();

        // act
        final RegionBSPTree2S tree = path.toTree();

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testToTree_halfSpace_3_oe() {
        // arrange
        final GreatArcPath path = GreatArcPath.builder(TEST_PRECISION)
                .append(Point2S.PLUS_I)
                .append(Point2S.PLUS_J)
                .build();

        // act
        final RegionBSPTree2S tree = path.toTree();

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(Angle.TWO_PI, tree.getSize(), TEST_EPS);
    }

    @Test
    void testToTree_triangle_1_oe() {
        // arrange
        final GreatArcPath path = GreatArcPath.builder(TEST_PRECISION)
                .append(Point2S.PLUS_I)
                .append(Point2S.PLUS_J)
                .append(Point2S.PLUS_K)
                .close();

        // act
        final RegionBSPTree2S tree = path.toTree();

        // assert
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testToTree_triangle_2_oe() {
        // arrange
        final GreatArcPath path = GreatArcPath.builder(TEST_PRECISION)
                .append(Point2S.PLUS_I)
                .append(Point2S.PLUS_J)
                .append(Point2S.PLUS_K)
                .close();

        // act
        final RegionBSPTree2S tree = path.toTree();

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testToTree_triangle_3_oe() {
        // arrange
        final GreatArcPath path = GreatArcPath.builder(TEST_PRECISION)
                .append(Point2S.PLUS_I)
                .append(Point2S.PLUS_J)
                .append(Point2S.PLUS_K)
                .close();

        // act
        final RegionBSPTree2S tree = path.toTree();

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(Angle.PI_OVER_TWO, tree.getSize(), TEST_EPS);
    }

    @Test
    void testBuilder_append_1_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .appendVertices(c, d)
            .append(e)
            .append(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .build();

        // assert
        Assertions.assertFalse(path.isEmpty());
    }

    @Test
    void testBuilder_append_2_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .appendVertices(c, d)
            .append(e)
            .append(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        Assertions.assertTrue(path.isClosed());
    }

    @Test
    void testBuilder_append_5_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .appendVertices(c, d)
            .append(e)
            .append(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        Assertions.assertEquals(5, arcs.size());
    }

    @Test
    void testBuilder_prepend_1_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .prependPoints(Arrays.asList(c, d))
            .prepend(b)
            .prepend(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .build();

        // assert
        Assertions.assertFalse(path.isEmpty());
    }

    @Test
    void testBuilder_prepend_2_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .prependPoints(Arrays.asList(c, d))
            .prepend(b)
            .prepend(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        Assertions.assertTrue(path.isClosed());
    }

    @Test
    void testBuilder_prepend_5_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .prependPoints(Arrays.asList(c, d))
            .prepend(b)
            .prepend(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        Assertions.assertEquals(5, arcs.size());
    }

    @Test
    void testBuilder_appendAndPrepend_points_1_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(a)
                .append(b)
                .prepend(e)
                .append(c)
                .prepend(d)
                .build();

        // assert
        Assertions.assertFalse(path.isEmpty());
    }

    @Test
    void testBuilder_appendAndPrepend_points_2_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(a)
                .append(b)
                .prepend(e)
                .append(c)
                .prepend(d)
                .build();

        // assert
        // removed other assertion
        Assertions.assertFalse(path.isClosed());
    }

    @Test
    void testBuilder_appendAndPrepend_points_5_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(a)
                .append(b)
                .prepend(e)
                .append(c)
                .prepend(d)
                .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        Assertions.assertEquals(4, arcs.size());
    }

    @Test
    void testBuilder_appendAndPrepend_mixedArguments_1_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
                .prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
                .append(c)
                .prepend(d)
                .append(GreatCircles.arcFromPoints(c, d, TEST_PRECISION))
                .build();

        // assert
        Assertions.assertFalse(path.isEmpty());
    }

    @Test
    void testBuilder_appendAndPrepend_mixedArguments_2_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
                .prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
                .append(c)
                .prepend(d)
                .append(GreatCircles.arcFromPoints(c, d, TEST_PRECISION))
                .build();

        // assert
        // removed other assertion
        Assertions.assertTrue(path.isClosed());
    }

    @Test
    void testBuilder_appendAndPrepend_mixedArguments_5_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
                .prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
                .append(c)
                .prepend(d)
                .append(GreatCircles.arcFromPoints(c, d, TEST_PRECISION))
                .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        Assertions.assertEquals(5, arcs.size());
    }

    @Test
    void testToString_empty_1_oe() {
        // arrange
        final GreatArcPath path = GreatArcPath.empty();

        // act
        final String str = path.toString();

        // assert
        Assertions.assertEquals("GreatArcPath[empty= true]", str);
    }

    @Test
    void testFromVertices_boolean_notClosed_6_oe_1_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, false, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
                final GreatArc arc0 = arcs.get(0);
        final Point2S start0 = Point2S.PLUS_I;
        final Point2S end0 = Point2S.PLUS_K;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_boolean_notClosed_6_oe_2_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, false, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
                final GreatArc arc0 = arcs.get(0);
        final Point2S start0 = Point2S.PLUS_I;
        final Point2S end0 = Point2S.PLUS_K;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_boolean_notClosed_7_oe_1_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, false, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(1);
        final Point2S start0 = Point2S.PLUS_K;
        final Point2S end0 = Point2S.PLUS_J;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_boolean_notClosed_7_oe_2_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, false, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(1);
        final Point2S start0 = Point2S.PLUS_K;
        final Point2S end0 = Point2S.PLUS_J;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_boolean_notClosed_8_oe_1_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, false, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final Collection<Point2S> expected0 = points;
        final Collection<Point2S> actual0 = path.getVertices();
        Assertions.assertEquals(expected0.size(), actual0.size());
    }

    @Test
    void testFromVertices_boolean_notClosed_8_oe_2_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, false, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final Collection<Point2S> expected0 = points;
        final Collection<Point2S> actual0 = path.getVertices();
        // removed other assertion
        
                final Iterator<Point2S> expIt0 = expected0.iterator();
                final Iterator<Point2S> actIt0 = actual0.iterator();
        
                while (expIt0.hasNext() && actIt0.hasNext()) {
                    SphericalTestUtils.assertPointsEq(expIt0.next(), actIt0.next(), TEST_EPS);
    }
    }

    @Test
    void testFromVertices_boolean_closed_6_oe_1_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, true, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
                final GreatArc arc0 = arcs.get(0);
        final Point2S start0 = Point2S.PLUS_I;
        final Point2S end0 = Point2S.PLUS_K;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_boolean_closed_6_oe_2_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, true, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
                final GreatArc arc0 = arcs.get(0);
        final Point2S start0 = Point2S.PLUS_I;
        final Point2S end0 = Point2S.PLUS_K;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_boolean_closed_7_oe_1_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, true, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(1);
        final Point2S start0 = Point2S.PLUS_K;
        final Point2S end0 = Point2S.PLUS_J;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_boolean_closed_7_oe_2_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, true, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(1);
        final Point2S start0 = Point2S.PLUS_K;
        final Point2S end0 = Point2S.PLUS_J;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_boolean_closed_8_oe_1_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, true, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(2);
        final Point2S start0 = Point2S.PLUS_J;
        final Point2S end0 = Point2S.PLUS_I;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_boolean_closed_8_oe_2_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, true, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(2);
        final Point2S start0 = Point2S.PLUS_J;
        final Point2S end0 = Point2S.PLUS_I;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_boolean_closed_9_oe_1_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, true, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final Collection<Point2S> expected0 = Arrays.asList( Point2S.PLUS_I, Point2S.PLUS_K, Point2S.PLUS_J, Point2S.PLUS_I);
        final Collection<Point2S> actual0 = path.getVertices();
        Assertions.assertEquals(expected0.size(), actual0.size());
    }

    @Test
    void testFromVertices_boolean_closed_9_oe_2_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, true, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final Collection<Point2S> expected0 = Arrays.asList( Point2S.PLUS_I, Point2S.PLUS_K, Point2S.PLUS_J, Point2S.PLUS_I);
        final Collection<Point2S> actual0 = path.getVertices();
        // removed other assertion
        
                final Iterator<Point2S> expIt0 = expected0.iterator();
                final Iterator<Point2S> actIt0 = actual0.iterator();
        
                while (expIt0.hasNext() && actIt0.hasNext()) {
                    SphericalTestUtils.assertPointsEq(expIt0.next(), actIt0.next(), TEST_EPS);
    }
    }

    @Test
    void testFromVertices_boolean_closed_pointsConsideredEqual_6_oe_1_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final Point2S almostPlusI = Point2S.of(1e-4, Angle.PI_OVER_TWO);

        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J,
                almostPlusI);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, true, precision);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
                final GreatArc arc0 = arcs.get(0);
        final Point2S start0 = Point2S.PLUS_I;
        final Point2S end0 = Point2S.PLUS_K;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_boolean_closed_pointsConsideredEqual_6_oe_2_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final Point2S almostPlusI = Point2S.of(1e-4, Angle.PI_OVER_TWO);

        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J,
                almostPlusI);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, true, precision);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
                final GreatArc arc0 = arcs.get(0);
        final Point2S start0 = Point2S.PLUS_I;
        final Point2S end0 = Point2S.PLUS_K;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_boolean_closed_pointsConsideredEqual_7_oe_1_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final Point2S almostPlusI = Point2S.of(1e-4, Angle.PI_OVER_TWO);

        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J,
                almostPlusI);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, true, precision);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(1);
        final Point2S start0 = Point2S.PLUS_K;
        final Point2S end0 = Point2S.PLUS_J;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_boolean_closed_pointsConsideredEqual_7_oe_2_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final Point2S almostPlusI = Point2S.of(1e-4, Angle.PI_OVER_TWO);

        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J,
                almostPlusI);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, true, precision);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(1);
        final Point2S start0 = Point2S.PLUS_K;
        final Point2S end0 = Point2S.PLUS_J;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_boolean_closed_pointsConsideredEqual_8_oe_1_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final Point2S almostPlusI = Point2S.of(1e-4, Angle.PI_OVER_TWO);

        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J,
                almostPlusI);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, true, precision);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(2);
        final Point2S start0 = Point2S.PLUS_J;
        final Point2S end0 = almostPlusI;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_boolean_closed_pointsConsideredEqual_8_oe_2_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final Point2S almostPlusI = Point2S.of(1e-4, Angle.PI_OVER_TWO);

        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J,
                almostPlusI);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, true, precision);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(2);
        final Point2S start0 = Point2S.PLUS_J;
        final Point2S end0 = almostPlusI;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_boolean_closed_pointsConsideredEqual_9_oe_1_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final Point2S almostPlusI = Point2S.of(1e-4, Angle.PI_OVER_TWO);

        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J,
                almostPlusI);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, true, precision);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final Collection<Point2S> expected0 = Arrays.asList( Point2S.PLUS_I, Point2S.PLUS_K, Point2S.PLUS_J, almostPlusI);
        final Collection<Point2S> actual0 = path.getVertices();
        Assertions.assertEquals(expected0.size(), actual0.size());
    }

    @Test
    void testFromVertices_boolean_closed_pointsConsideredEqual_9_oe_2_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final Point2S almostPlusI = Point2S.of(1e-4, Angle.PI_OVER_TWO);

        final List<Point2S> points = Arrays.asList(
                Point2S.PLUS_I,
                Point2S.PLUS_K,
                Point2S.PLUS_J,
                almostPlusI);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, true, precision);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final Collection<Point2S> expected0 = Arrays.asList( Point2S.PLUS_I, Point2S.PLUS_K, Point2S.PLUS_J, almostPlusI);
        final Collection<Point2S> actual0 = path.getVertices();
        // removed other assertion
        
                final Iterator<Point2S> expIt0 = expected0.iterator();
                final Iterator<Point2S> actIt0 = actual0.iterator();
        
                while (expIt0.hasNext() && actIt0.hasNext()) {
                    SphericalTestUtils.assertPointsEq(expIt0.next(), actIt0.next(), TEST_EPS);
    }
    }

    @Test
    void testFromVertices_6_oe_1_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.MINUS_I,
                Point2S.MINUS_J,
                Point2S.PLUS_I);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
                final GreatArc arc0 = arcs.get(0);
        final Point2S start0 = Point2S.MINUS_I;
        final Point2S end0 = Point2S.MINUS_J;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_6_oe_2_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.MINUS_I,
                Point2S.MINUS_J,
                Point2S.PLUS_I);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
                final GreatArc arc0 = arcs.get(0);
        final Point2S start0 = Point2S.MINUS_I;
        final Point2S end0 = Point2S.MINUS_J;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_7_oe_1_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.MINUS_I,
                Point2S.MINUS_J,
                Point2S.PLUS_I);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(1);
        final Point2S start0 = Point2S.MINUS_J;
        final Point2S end0 = Point2S.PLUS_I;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_7_oe_2_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.MINUS_I,
                Point2S.MINUS_J,
                Point2S.PLUS_I);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(1);
        final Point2S start0 = Point2S.MINUS_J;
        final Point2S end0 = Point2S.PLUS_I;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertices_8_oe_1_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.MINUS_I,
                Point2S.MINUS_J,
                Point2S.PLUS_I);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final Collection<Point2S> expected0 = points;
        final Collection<Point2S> actual0 = path.getVertices();
        Assertions.assertEquals(expected0.size(), actual0.size());
    }

    @Test
    void testFromVertices_8_oe_2_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.MINUS_I,
                Point2S.MINUS_J,
                Point2S.PLUS_I);

        // act
        final GreatArcPath path = GreatArcPath.fromVertices(points, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final Collection<Point2S> expected0 = points;
        final Collection<Point2S> actual0 = path.getVertices();
        // removed other assertion
        
                final Iterator<Point2S> expIt0 = expected0.iterator();
                final Iterator<Point2S> actIt0 = actual0.iterator();
        
                while (expIt0.hasNext() && actIt0.hasNext()) {
                    SphericalTestUtils.assertPointsEq(expIt0.next(), actIt0.next(), TEST_EPS);
    }
    }

    @Test
    void testFromVertexLoop_6_oe_1_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.MINUS_I,
                Point2S.MINUS_J,
                Point2S.MINUS_K);

        // act
        final GreatArcPath path = GreatArcPath.fromVertexLoop(points, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
                final GreatArc arc0 = arcs.get(0);
        final Point2S start0 = Point2S.MINUS_I;
        final Point2S end0 = Point2S.MINUS_J;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertexLoop_6_oe_2_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.MINUS_I,
                Point2S.MINUS_J,
                Point2S.MINUS_K);

        // act
        final GreatArcPath path = GreatArcPath.fromVertexLoop(points, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
                final GreatArc arc0 = arcs.get(0);
        final Point2S start0 = Point2S.MINUS_I;
        final Point2S end0 = Point2S.MINUS_J;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertexLoop_7_oe_1_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.MINUS_I,
                Point2S.MINUS_J,
                Point2S.MINUS_K);

        // act
        final GreatArcPath path = GreatArcPath.fromVertexLoop(points, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(1);
        final Point2S start0 = Point2S.MINUS_J;
        final Point2S end0 = Point2S.MINUS_K;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertexLoop_7_oe_2_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.MINUS_I,
                Point2S.MINUS_J,
                Point2S.MINUS_K);

        // act
        final GreatArcPath path = GreatArcPath.fromVertexLoop(points, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(1);
        final Point2S start0 = Point2S.MINUS_J;
        final Point2S end0 = Point2S.MINUS_K;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertexLoop_8_oe_1_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.MINUS_I,
                Point2S.MINUS_J,
                Point2S.MINUS_K);

        // act
        final GreatArcPath path = GreatArcPath.fromVertexLoop(points, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(2);
        final Point2S start0 = Point2S.MINUS_K;
        final Point2S end0 = Point2S.MINUS_I;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromVertexLoop_8_oe_2_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.MINUS_I,
                Point2S.MINUS_J,
                Point2S.MINUS_K);

        // act
        final GreatArcPath path = GreatArcPath.fromVertexLoop(points, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(2);
        final Point2S start0 = Point2S.MINUS_K;
        final Point2S end0 = Point2S.MINUS_I;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromVertexLoop_9_oe_1_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.MINUS_I,
                Point2S.MINUS_J,
                Point2S.MINUS_K);

        // act
        final GreatArcPath path = GreatArcPath.fromVertexLoop(points, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final Collection<Point2S> expected0 = Arrays.asList( Point2S.MINUS_I, Point2S.MINUS_J, Point2S.MINUS_K, Point2S.MINUS_I);
        final Collection<Point2S> actual0 = path.getVertices();
        Assertions.assertEquals(expected0.size(), actual0.size());
    }

    @Test
    void testFromVertexLoop_9_oe_2_oe() {
        // arrange
        final List<Point2S> points = Arrays.asList(
                Point2S.MINUS_I,
                Point2S.MINUS_J,
                Point2S.MINUS_K);

        // act
        final GreatArcPath path = GreatArcPath.fromVertexLoop(points, TEST_PRECISION);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final Collection<Point2S> expected0 = Arrays.asList( Point2S.MINUS_I, Point2S.MINUS_J, Point2S.MINUS_K, Point2S.MINUS_I);
        final Collection<Point2S> actual0 = path.getVertices();
        // removed other assertion
        
                final Iterator<Point2S> expIt0 = expected0.iterator();
                final Iterator<Point2S> actIt0 = actual0.iterator();
        
                while (expIt0.hasNext() && actIt0.hasNext()) {
                    SphericalTestUtils.assertPointsEq(expIt0.next(), actIt0.next(), TEST_EPS);
    }
    }

    @Test
    void testFromArcs_6_oe_1_oe() {
        // arrange
        final Point2S ptA = Point2S.PLUS_I;
        final Point2S ptB = Point2S.of(1, Angle.PI_OVER_TWO);
        final Point2S ptC = Point2S.of(1, Angle.PI_OVER_TWO - 1);
        final Point2S ptD = Point2S.of(2, Angle.PI_OVER_TWO - 1);

        final GreatArc a = GreatCircles.arcFromPoints(ptA, ptB, TEST_PRECISION);
        final GreatArc b = GreatCircles.arcFromPoints(ptB, ptC, TEST_PRECISION);
        final GreatArc c = GreatCircles.arcFromPoints(ptC, ptD, TEST_PRECISION);

        // act
        final GreatArcPath path = GreatArcPath.fromArcs(a, b, c);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
                final GreatArc arc0 = arcs.get(0);
        final Point2S start0 = ptA;
        final Point2S end0 = ptB;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromArcs_6_oe_2_oe() {
        // arrange
        final Point2S ptA = Point2S.PLUS_I;
        final Point2S ptB = Point2S.of(1, Angle.PI_OVER_TWO);
        final Point2S ptC = Point2S.of(1, Angle.PI_OVER_TWO - 1);
        final Point2S ptD = Point2S.of(2, Angle.PI_OVER_TWO - 1);

        final GreatArc a = GreatCircles.arcFromPoints(ptA, ptB, TEST_PRECISION);
        final GreatArc b = GreatCircles.arcFromPoints(ptB, ptC, TEST_PRECISION);
        final GreatArc c = GreatCircles.arcFromPoints(ptC, ptD, TEST_PRECISION);

        // act
        final GreatArcPath path = GreatArcPath.fromArcs(a, b, c);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
                final GreatArc arc0 = arcs.get(0);
        final Point2S start0 = ptA;
        final Point2S end0 = ptB;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromArcs_7_oe_1_oe() {
        // arrange
        final Point2S ptA = Point2S.PLUS_I;
        final Point2S ptB = Point2S.of(1, Angle.PI_OVER_TWO);
        final Point2S ptC = Point2S.of(1, Angle.PI_OVER_TWO - 1);
        final Point2S ptD = Point2S.of(2, Angle.PI_OVER_TWO - 1);

        final GreatArc a = GreatCircles.arcFromPoints(ptA, ptB, TEST_PRECISION);
        final GreatArc b = GreatCircles.arcFromPoints(ptB, ptC, TEST_PRECISION);
        final GreatArc c = GreatCircles.arcFromPoints(ptC, ptD, TEST_PRECISION);

        // act
        final GreatArcPath path = GreatArcPath.fromArcs(a, b, c);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(1);
        final Point2S start0 = ptB;
        final Point2S end0 = ptC;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromArcs_7_oe_2_oe() {
        // arrange
        final Point2S ptA = Point2S.PLUS_I;
        final Point2S ptB = Point2S.of(1, Angle.PI_OVER_TWO);
        final Point2S ptC = Point2S.of(1, Angle.PI_OVER_TWO - 1);
        final Point2S ptD = Point2S.of(2, Angle.PI_OVER_TWO - 1);

        final GreatArc a = GreatCircles.arcFromPoints(ptA, ptB, TEST_PRECISION);
        final GreatArc b = GreatCircles.arcFromPoints(ptB, ptC, TEST_PRECISION);
        final GreatArc c = GreatCircles.arcFromPoints(ptC, ptD, TEST_PRECISION);

        // act
        final GreatArcPath path = GreatArcPath.fromArcs(a, b, c);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(1);
        final Point2S start0 = ptB;
        final Point2S end0 = ptC;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromArcs_8_oe_1_oe() {
        // arrange
        final Point2S ptA = Point2S.PLUS_I;
        final Point2S ptB = Point2S.of(1, Angle.PI_OVER_TWO);
        final Point2S ptC = Point2S.of(1, Angle.PI_OVER_TWO - 1);
        final Point2S ptD = Point2S.of(2, Angle.PI_OVER_TWO - 1);

        final GreatArc a = GreatCircles.arcFromPoints(ptA, ptB, TEST_PRECISION);
        final GreatArc b = GreatCircles.arcFromPoints(ptB, ptC, TEST_PRECISION);
        final GreatArc c = GreatCircles.arcFromPoints(ptC, ptD, TEST_PRECISION);

        // act
        final GreatArcPath path = GreatArcPath.fromArcs(a, b, c);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(2);
        final Point2S start0 = ptC;
        final Point2S end0 = ptD;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testFromArcs_8_oe_2_oe() {
        // arrange
        final Point2S ptA = Point2S.PLUS_I;
        final Point2S ptB = Point2S.of(1, Angle.PI_OVER_TWO);
        final Point2S ptC = Point2S.of(1, Angle.PI_OVER_TWO - 1);
        final Point2S ptD = Point2S.of(2, Angle.PI_OVER_TWO - 1);

        final GreatArc a = GreatCircles.arcFromPoints(ptA, ptB, TEST_PRECISION);
        final GreatArc b = GreatCircles.arcFromPoints(ptB, ptC, TEST_PRECISION);
        final GreatArc c = GreatCircles.arcFromPoints(ptC, ptD, TEST_PRECISION);

        // act
        final GreatArcPath path = GreatArcPath.fromArcs(a, b, c);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(2);
        final Point2S start0 = ptC;
        final Point2S end0 = ptD;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testFromArcs_9_oe_1_oe() {
        // arrange
        final Point2S ptA = Point2S.PLUS_I;
        final Point2S ptB = Point2S.of(1, Angle.PI_OVER_TWO);
        final Point2S ptC = Point2S.of(1, Angle.PI_OVER_TWO - 1);
        final Point2S ptD = Point2S.of(2, Angle.PI_OVER_TWO - 1);

        final GreatArc a = GreatCircles.arcFromPoints(ptA, ptB, TEST_PRECISION);
        final GreatArc b = GreatCircles.arcFromPoints(ptB, ptC, TEST_PRECISION);
        final GreatArc c = GreatCircles.arcFromPoints(ptC, ptD, TEST_PRECISION);

        // act
        final GreatArcPath path = GreatArcPath.fromArcs(a, b, c);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final Collection<Point2S> expected0 = Arrays.asList(ptA, ptB, ptC, ptD);
        final Collection<Point2S> actual0 = path.getVertices();
        Assertions.assertEquals(expected0.size(), actual0.size());
    }

    @Test
    void testFromArcs_9_oe_2_oe() {
        // arrange
        final Point2S ptA = Point2S.PLUS_I;
        final Point2S ptB = Point2S.of(1, Angle.PI_OVER_TWO);
        final Point2S ptC = Point2S.of(1, Angle.PI_OVER_TWO - 1);
        final Point2S ptD = Point2S.of(2, Angle.PI_OVER_TWO - 1);

        final GreatArc a = GreatCircles.arcFromPoints(ptA, ptB, TEST_PRECISION);
        final GreatArc b = GreatCircles.arcFromPoints(ptB, ptC, TEST_PRECISION);
        final GreatArc c = GreatCircles.arcFromPoints(ptC, ptD, TEST_PRECISION);

        // act
        final GreatArcPath path = GreatArcPath.fromArcs(a, b, c);

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final Collection<Point2S> expected0 = Arrays.asList(ptA, ptB, ptC, ptD);
        final Collection<Point2S> actual0 = path.getVertices();
        // removed other assertion
        
                final Iterator<Point2S> expIt0 = expected0.iterator();
                final Iterator<Point2S> actIt0 = actual0.iterator();
        
                while (expIt0.hasNext() && actIt0.hasNext()) {
                    SphericalTestUtils.assertPointsEq(expIt0.next(), actIt0.next(), TEST_EPS);
    }
    }

    @Test
    void testBuilder_append_6_oe_1_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .appendVertices(c, d)
            .append(e)
            .append(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
                final GreatArc arc0 = arcs.get(0);
        final Point2S start0 = a;
        final Point2S end0 = b;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_append_6_oe_2_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .appendVertices(c, d)
            .append(e)
            .append(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
                final GreatArc arc0 = arcs.get(0);
        final Point2S start0 = a;
        final Point2S end0 = b;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_append_7_oe_1_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .appendVertices(c, d)
            .append(e)
            .append(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(1);
        final Point2S start0 = b;
        final Point2S end0 = c;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_append_7_oe_2_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .appendVertices(c, d)
            .append(e)
            .append(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(1);
        final Point2S start0 = b;
        final Point2S end0 = c;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_append_8_oe_1_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .appendVertices(c, d)
            .append(e)
            .append(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(2);
        final Point2S start0 = c;
        final Point2S end0 = d;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_append_8_oe_2_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .appendVertices(c, d)
            .append(e)
            .append(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(2);
        final Point2S start0 = c;
        final Point2S end0 = d;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_append_9_oe_1_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .appendVertices(c, d)
            .append(e)
            .append(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(3);
        final Point2S start0 = d;
        final Point2S end0 = e;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_append_9_oe_2_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .appendVertices(c, d)
            .append(e)
            .append(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(3);
        final Point2S start0 = d;
        final Point2S end0 = e;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_append_10_oe_1_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .appendVertices(c, d)
            .append(e)
            .append(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(4);
        final Point2S start0 = e;
        final Point2S end0 = a;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_append_10_oe_2_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .appendVertices(c, d)
            .append(e)
            .append(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(4);
        final Point2S start0 = e;
        final Point2S end0 = a;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_append_11_oe_1_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .appendVertices(c, d)
            .append(e)
            .append(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final Collection<Point2S> expected0 = Arrays.asList(a, b, c, d, e, a);
        final Collection<Point2S> actual0 = path.getVertices();
        Assertions.assertEquals(expected0.size(), actual0.size());
    }

    @Test
    void testBuilder_append_11_oe_2_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .appendVertices(c, d)
            .append(e)
            .append(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final Collection<Point2S> expected0 = Arrays.asList(a, b, c, d, e, a);
        final Collection<Point2S> actual0 = path.getVertices();
        // removed other assertion
        
                final Iterator<Point2S> expIt0 = expected0.iterator();
                final Iterator<Point2S> actIt0 = actual0.iterator();
        
                while (expIt0.hasNext() && actIt0.hasNext()) {
                    SphericalTestUtils.assertPointsEq(expIt0.next(), actIt0.next(), TEST_EPS);
    }
    }

    @Test
    void testBuilder_prepend_6_oe_1_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .prependPoints(Arrays.asList(c, d))
            .prepend(b)
            .prepend(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
                final GreatArc arc0 = arcs.get(0);
        final Point2S start0 = a;
        final Point2S end0 = b;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prepend_6_oe_2_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .prependPoints(Arrays.asList(c, d))
            .prepend(b)
            .prepend(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
                final GreatArc arc0 = arcs.get(0);
        final Point2S start0 = a;
        final Point2S end0 = b;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prepend_7_oe_1_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .prependPoints(Arrays.asList(c, d))
            .prepend(b)
            .prepend(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(1);
        final Point2S start0 = b;
        final Point2S end0 = c;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prepend_7_oe_2_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .prependPoints(Arrays.asList(c, d))
            .prepend(b)
            .prepend(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(1);
        final Point2S start0 = b;
        final Point2S end0 = c;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prepend_8_oe_1_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .prependPoints(Arrays.asList(c, d))
            .prepend(b)
            .prepend(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(2);
        final Point2S start0 = c;
        final Point2S end0 = d;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prepend_8_oe_2_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .prependPoints(Arrays.asList(c, d))
            .prepend(b)
            .prepend(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(2);
        final Point2S start0 = c;
        final Point2S end0 = d;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prepend_9_oe_1_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .prependPoints(Arrays.asList(c, d))
            .prepend(b)
            .prepend(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(3);
        final Point2S start0 = d;
        final Point2S end0 = e;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prepend_9_oe_2_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .prependPoints(Arrays.asList(c, d))
            .prepend(b)
            .prepend(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(3);
        final Point2S start0 = d;
        final Point2S end0 = e;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prepend_10_oe_1_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .prependPoints(Arrays.asList(c, d))
            .prepend(b)
            .prepend(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(4);
        final Point2S start0 = e;
        final Point2S end0 = a;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prepend_10_oe_2_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .prependPoints(Arrays.asList(c, d))
            .prepend(b)
            .prepend(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(4);
        final Point2S start0 = e;
        final Point2S end0 = a;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_prepend_11_oe_1_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .prependPoints(Arrays.asList(c, d))
            .prepend(b)
            .prepend(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final Collection<Point2S> expected0 = Arrays.asList(a, b, c, d, e, a);
        final Collection<Point2S> actual0 = path.getVertices();
        Assertions.assertEquals(expected0.size(), actual0.size());
    }

    @Test
    void testBuilder_prepend_11_oe_2_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
            .prependPoints(Arrays.asList(c, d))
            .prepend(b)
            .prepend(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
            .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final Collection<Point2S> expected0 = Arrays.asList(a, b, c, d, e, a);
        final Collection<Point2S> actual0 = path.getVertices();
        // removed other assertion
        
                final Iterator<Point2S> expIt0 = expected0.iterator();
                final Iterator<Point2S> actIt0 = actual0.iterator();
        
                while (expIt0.hasNext() && actIt0.hasNext()) {
                    SphericalTestUtils.assertPointsEq(expIt0.next(), actIt0.next(), TEST_EPS);
    }
    }

    @Test
    void testBuilder_appendAndPrepend_points_6_oe_1_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(a)
                .append(b)
                .prepend(e)
                .append(c)
                .prepend(d)
                .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
                final GreatArc arc0 = arcs.get(0);
        final Point2S start0 = d;
        final Point2S end0 = e;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_appendAndPrepend_points_6_oe_2_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(a)
                .append(b)
                .prepend(e)
                .append(c)
                .prepend(d)
                .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
                final GreatArc arc0 = arcs.get(0);
        final Point2S start0 = d;
        final Point2S end0 = e;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_appendAndPrepend_points_7_oe_1_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(a)
                .append(b)
                .prepend(e)
                .append(c)
                .prepend(d)
                .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(1);
        final Point2S start0 = e;
        final Point2S end0 = a;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_appendAndPrepend_points_7_oe_2_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(a)
                .append(b)
                .prepend(e)
                .append(c)
                .prepend(d)
                .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(1);
        final Point2S start0 = e;
        final Point2S end0 = a;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_appendAndPrepend_points_8_oe_1_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(a)
                .append(b)
                .prepend(e)
                .append(c)
                .prepend(d)
                .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(2);
        final Point2S start0 = a;
        final Point2S end0 = b;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_appendAndPrepend_points_8_oe_2_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(a)
                .append(b)
                .prepend(e)
                .append(c)
                .prepend(d)
                .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(2);
        final Point2S start0 = a;
        final Point2S end0 = b;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_appendAndPrepend_points_9_oe_1_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(a)
                .append(b)
                .prepend(e)
                .append(c)
                .prepend(d)
                .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(3);
        final Point2S start0 = b;
        final Point2S end0 = c;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_appendAndPrepend_points_9_oe_2_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(a)
                .append(b)
                .prepend(e)
                .append(c)
                .prepend(d)
                .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(3);
        final Point2S start0 = b;
        final Point2S end0 = c;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_appendAndPrepend_points_10_oe_1_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(a)
                .append(b)
                .prepend(e)
                .append(c)
                .prepend(d)
                .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final Collection<Point2S> expected0 = Arrays.asList(d, e, a, b, c);
        final Collection<Point2S> actual0 = path.getVertices();
        Assertions.assertEquals(expected0.size(), actual0.size());
    }

    @Test
    void testBuilder_appendAndPrepend_points_10_oe_2_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.prepend(a)
                .append(b)
                .prepend(e)
                .append(c)
                .prepend(d)
                .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final Collection<Point2S> expected0 = Arrays.asList(d, e, a, b, c);
        final Collection<Point2S> actual0 = path.getVertices();
        // removed other assertion
        
                final Iterator<Point2S> expIt0 = expected0.iterator();
                final Iterator<Point2S> actIt0 = actual0.iterator();
        
                while (expIt0.hasNext() && actIt0.hasNext()) {
                    SphericalTestUtils.assertPointsEq(expIt0.next(), actIt0.next(), TEST_EPS);
    }
    }

    @Test
    void testBuilder_appendAndPrepend_mixedArguments_6_oe_1_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
                .prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
                .append(c)
                .prepend(d)
                .append(GreatCircles.arcFromPoints(c, d, TEST_PRECISION))
                .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
                final GreatArc arc0 = arcs.get(0);
        final Point2S start0 = d;
        final Point2S end0 = e;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_appendAndPrepend_mixedArguments_6_oe_2_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
                .prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
                .append(c)
                .prepend(d)
                .append(GreatCircles.arcFromPoints(c, d, TEST_PRECISION))
                .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
                final GreatArc arc0 = arcs.get(0);
        final Point2S start0 = d;
        final Point2S end0 = e;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_appendAndPrepend_mixedArguments_7_oe_1_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
                .prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
                .append(c)
                .prepend(d)
                .append(GreatCircles.arcFromPoints(c, d, TEST_PRECISION))
                .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(1);
        final Point2S start0 = e;
        final Point2S end0 = a;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_appendAndPrepend_mixedArguments_7_oe_2_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
                .prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
                .append(c)
                .prepend(d)
                .append(GreatCircles.arcFromPoints(c, d, TEST_PRECISION))
                .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(1);
        final Point2S start0 = e;
        final Point2S end0 = a;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_appendAndPrepend_mixedArguments_8_oe_1_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
                .prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
                .append(c)
                .prepend(d)
                .append(GreatCircles.arcFromPoints(c, d, TEST_PRECISION))
                .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(2);
        final Point2S start0 = a;
        final Point2S end0 = b;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_appendAndPrepend_mixedArguments_8_oe_2_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
                .prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
                .append(c)
                .prepend(d)
                .append(GreatCircles.arcFromPoints(c, d, TEST_PRECISION))
                .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(2);
        final Point2S start0 = a;
        final Point2S end0 = b;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_appendAndPrepend_mixedArguments_9_oe_1_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
                .prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
                .append(c)
                .prepend(d)
                .append(GreatCircles.arcFromPoints(c, d, TEST_PRECISION))
                .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(3);
        final Point2S start0 = b;
        final Point2S end0 = c;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_appendAndPrepend_mixedArguments_9_oe_2_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
                .prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
                .append(c)
                .prepend(d)
                .append(GreatCircles.arcFromPoints(c, d, TEST_PRECISION))
                .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(3);
        final Point2S start0 = b;
        final Point2S end0 = c;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_appendAndPrepend_mixedArguments_10_oe_1_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
                .prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
                .append(c)
                .prepend(d)
                .append(GreatCircles.arcFromPoints(c, d, TEST_PRECISION))
                .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(4);
        final Point2S start0 = c;
        final Point2S end0 = d;
        SphericalTestUtils.assertPointsEq(start0, arc0.getStartPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_appendAndPrepend_mixedArguments_10_oe_2_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
                .prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
                .append(c)
                .prepend(d)
                .append(GreatCircles.arcFromPoints(c, d, TEST_PRECISION))
                .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final GreatArc arc0 = arcs.get(4);
        final Point2S start0 = c;
        final Point2S end0 = d;
        // removed other assertion
                SphericalTestUtils.assertPointsEq(end0, arc0.getEndPoint(), TEST_EPS);
    }

    @Test
    void testBuilder_appendAndPrepend_mixedArguments_11_oe_1_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
                .prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
                .append(c)
                .prepend(d)
                .append(GreatCircles.arcFromPoints(c, d, TEST_PRECISION))
                .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final Collection<Point2S> expected0 = Arrays.asList(d, e, a, b, c, d);
        final Collection<Point2S> actual0 = path.getVertices();
        Assertions.assertEquals(expected0.size(), actual0.size());
    }

    @Test
    void testBuilder_appendAndPrepend_mixedArguments_11_oe_2_oe() {
        // arrange
        final Point2S a = Point2S.PLUS_I;
        final Point2S b = Point2S.PLUS_J;
        final Point2S c = Point2S.PLUS_K;
        final Point2S d = Point2S.of(-1, Angle.PI_OVER_TWO);
        final Point2S e = Point2S.of(0, 0.6 * Math.PI);

        final GreatArcPath.Builder builder = GreatArcPath.builder(TEST_PRECISION);

        // act
        final GreatArcPath path = builder.append(GreatCircles.arcFromPoints(a, b, TEST_PRECISION))
                .prepend(GreatCircles.arcFromPoints(e, a, TEST_PRECISION))
                .append(c)
                .prepend(d)
                .append(GreatCircles.arcFromPoints(c, d, TEST_PRECISION))
                .build();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        final List<GreatArc> arcs = path.getArcs();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

                final Collection<Point2S> expected0 = Arrays.asList(d, e, a, b, c, d);
        final Collection<Point2S> actual0 = path.getVertices();
        // removed other assertion
        
                final Iterator<Point2S> expIt0 = expected0.iterator();
                final Iterator<Point2S> actIt0 = actual0.iterator();
        
                while (expIt0.hasNext() && actIt0.hasNext()) {
                    SphericalTestUtils.assertPointsEq(expIt0.next(), actIt0.next(), TEST_EPS);
    }
    }

}
