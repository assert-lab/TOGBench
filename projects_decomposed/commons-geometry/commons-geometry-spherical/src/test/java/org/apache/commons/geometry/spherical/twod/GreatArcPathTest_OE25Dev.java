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


}
