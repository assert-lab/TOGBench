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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.geometry.core.RegionLocation;
import org.apache.commons.geometry.core.partitioning.Split;
import org.apache.commons.geometry.core.partitioning.SplitLocation;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.geometry.spherical.SphericalTestUtils;
import org.apache.commons.geometry.spherical.oned.Point1S;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class ConvexArea2STest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    private static List<GreatArc> sortArcs(final List<GreatArc> arcs) {
        final List<GreatArc> result = new ArrayList<>(arcs);

        result.sort((a, b) ->
                Point2S.POLAR_AZIMUTH_ASCENDING_ORDER.compare(a.getStartPoint(), b.getStartPoint()));

        return result;
    }

    private static Point2S triangleCentroid(final Point2S p1, final Point2S p2, final Point2S p3) {
        // compute the centroid as the sum of the cross product of each point pair weighted by
        // the angle between the points
        final Vector3D v1 = p1.getVector();
        final Vector3D v2 = p2.getVector();
        final Vector3D v3 = p3.getVector();

        Vector3D sum = Vector3D.ZERO;
        sum = sum.add(v1.cross(v2).withNorm(v1.angle(v2)));
        sum = sum.add(v2.cross(v3).withNorm(v2.angle(v3)));
        sum = sum.add(v3.cross(v1).withNorm(v3.angle(v1)));

        return Point2S.from(sum);
    }

    private static void checkArc(final GreatArc arc, final Point2S start, final Point2S end) {
        SphericalTestUtils.assertPointsEq(start, arc.getStartPoint(), TEST_EPS);
        SphericalTestUtils.assertPointsEq(end, arc.getEndPoint(), TEST_EPS);
    }

    private static void assertPath(final GreatArcPath path, final Point2S... expectedVertices) {
        final List<Point2S> vertices = path.getVertices();

        Assertions.assertEquals(expectedVertices.length, vertices.size());
        for (int i = 0; i < expectedVertices.length; ++i) {

            if (!expectedVertices[i].eq(vertices.get(i), TEST_PRECISION)) {
                final String msg = "Unexpected point in path at index " + i + ". Expected " +
                        Arrays.toString(expectedVertices) + " but received " + vertices;
                Assertions.fail(msg);
            }
        }
    }

    private static void checkCentroidConsistency(final ConvexArea2S area) {
        final Point2S centroid = area.getCentroid();
        final double size = area.getSize();

        SphericalTestUtils.checkClassify(area, RegionLocation.INSIDE, centroid);

        final GreatCircle circle = GreatCircles.fromPole(centroid.getVector(), TEST_PRECISION);
        for (double az = 0; az <= Angle.TWO_PI; az += 0.2) {
            final Point2S pt = circle.toSpace(Point1S.of(az));
            final GreatCircle splitter = GreatCircles.fromPoints(centroid, pt, TEST_PRECISION);

            final Split<ConvexArea2S> split = area.split(splitter);

            Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());

            final ConvexArea2S minus = split.getMinus();
            final double minusSize = minus.getSize();

            final ConvexArea2S plus = split.getPlus();
            final double plusSize = plus.getSize();

            final Vector3D minusWeightedCentroid = minus.getWeightedCentroidVector();
            final Vector3D plusWeightedCentroid = plus.getWeightedCentroidVector();

            final Point2S computedCentroid = Point2S.from(minusWeightedCentroid.add(plusWeightedCentroid));

            Assertions.assertEquals(size, minusSize + plusSize, TEST_EPS);
            SphericalTestUtils.assertPointsEq(centroid, computedCentroid, TEST_EPS);
        }
    }

    @Test
    void testFull_1_oe() {
        final ConvexArea2S area = ConvexArea2S.full();

        Assertions.assertTrue(area.isFull());
    }

    @Test
    void testFull_2_oe() {
        final ConvexArea2S area = ConvexArea2S.full();

        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFull_3_oe() {
        final ConvexArea2S area = ConvexArea2S.full();

        Assertions.assertEquals(0, area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFull_4_oe() {
        final ConvexArea2S area = ConvexArea2S.full();

        Assertions.assertEquals(4 * Math.PI, area.getSize(), TEST_EPS);
    }

    @Test
    void testFull_5_oe() {
        final ConvexArea2S area = ConvexArea2S.full();

        Assertions.assertNull(area.getCentroid());
    }

    @Test
    void testFull_6_oe() {
        final ConvexArea2S area = ConvexArea2S.full();


        Assertions.assertEquals(0, area.getBoundaries().size());
    }

    @Test
    void testFromBounds_empty_1_oe() {
        final ConvexArea2S area = ConvexArea2S.fromBounds();

        Assertions.assertTrue(area.isFull());
    }

    @Test
    void testFromBounds_empty_2_oe() {
        final ConvexArea2S area = ConvexArea2S.fromBounds();

        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFromBounds_empty_3_oe() {
        final ConvexArea2S area = ConvexArea2S.fromBounds();

        Assertions.assertEquals(0, area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFromBounds_empty_4_oe() {
        final ConvexArea2S area = ConvexArea2S.fromBounds();

        Assertions.assertEquals(4 * Math.PI, area.getSize(), TEST_EPS);
    }

    @Test
    void testFromBounds_empty_5_oe() {
        final ConvexArea2S area = ConvexArea2S.fromBounds();

        Assertions.assertNull(area.getCentroid());
    }

    @Test
    void testFromBounds_empty_6_oe() {
        final ConvexArea2S area = ConvexArea2S.fromBounds();


        Assertions.assertEquals(0, area.getBoundaries().size());
    }

    @Test
    void testFromBounds_singleBound_1_oe() {
        final GreatCircle circle = GreatCircles.fromPoints(Point2S.PLUS_K, Point2S.PLUS_I, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(circle);

        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testFromBounds_singleBound_2_oe() {
        final GreatCircle circle = GreatCircles.fromPoints(Point2S.PLUS_K, Point2S.PLUS_I, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(circle);

        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFromBounds_singleBound_3_oe() {
        final GreatCircle circle = GreatCircles.fromPoints(Point2S.PLUS_K, Point2S.PLUS_I, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(circle);

        Assertions.assertEquals(2 * Math.PI, area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFromBounds_singleBound_4_oe() {
        final GreatCircle circle = GreatCircles.fromPoints(Point2S.PLUS_K, Point2S.PLUS_I, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(circle);

        Assertions.assertEquals(2 * Math.PI, area.getSize(), TEST_EPS);
    }

    @Test
    void testFromBounds_singleBound_6_oe() {
        final GreatCircle circle = GreatCircles.fromPoints(Point2S.PLUS_K, Point2S.PLUS_I, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(circle);

        checkCentroidConsistency(area);

        Assertions.assertEquals(1, area.getBoundaries().size());
    }

    @Test
    void testFromBounds_singleBound_7_oe() {
        final GreatCircle circle = GreatCircles.fromPoints(Point2S.PLUS_K, Point2S.PLUS_I, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(circle);

        checkCentroidConsistency(area);

        final GreatArc arc = area.getBoundaries().get(0);
        Assertions.assertTrue(arc.isFull());
    }

    @Test
    void testFromBounds_lune_intersectionAtPoles_1_oe() {
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_K, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(
                Point2S.of(0.25 * Math.PI, Angle.PI_OVER_TWO), Point2S.PLUS_K, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(a, b);

        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testFromBounds_lune_intersectionAtPoles_2_oe() {
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_K, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(
                Point2S.of(0.25 * Math.PI, Angle.PI_OVER_TWO), Point2S.PLUS_K, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(a, b);

        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFromBounds_lune_intersectionAtPoles_3_oe() {
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_K, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(
                Point2S.of(0.25 * Math.PI, Angle.PI_OVER_TWO), Point2S.PLUS_K, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(a, b);

        Assertions.assertEquals(2 * Math.PI, area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFromBounds_lune_intersectionAtPoles_4_oe() {
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_K, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(
                Point2S.of(0.25 * Math.PI, Angle.PI_OVER_TWO), Point2S.PLUS_K, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(a, b);

        Assertions.assertEquals(Angle.PI_OVER_TWO, area.getSize(), TEST_EPS);
    }

    @Test
    void testFromBounds_lune_intersectionAtPoles_6_oe() {
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_K, Point2S.PLUS_I, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(
                Point2S.of(0.25 * Math.PI, Angle.PI_OVER_TWO), Point2S.PLUS_K, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(a, b);

        checkCentroidConsistency(area);

        final List<GreatArc> arcs = sortArcs(area.getBoundaries());
        Assertions.assertEquals(2, arcs.size());
    }

    @Test
    void testFromBounds_lune_intersectionAtEquator_1_oe() {
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(a, b);

        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testFromBounds_lune_intersectionAtEquator_2_oe() {
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(a, b);

        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFromBounds_lune_intersectionAtEquator_3_oe() {
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(a, b);

        Assertions.assertEquals(2 * Math.PI, area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFromBounds_lune_intersectionAtEquator_4_oe() {
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(a, b);

        Assertions.assertEquals(Math.PI, area.getSize(), TEST_EPS);
    }

    @Test
    void testFromBounds_lune_intersectionAtEquator_6_oe() {
        final GreatCircle a = GreatCircles.fromPoints(Point2S.PLUS_I, Point2S.PLUS_J, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(Point2S.PLUS_J, Point2S.PLUS_K, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(a, b);

        checkCentroidConsistency(area);

        final List<GreatArc> arcs = sortArcs(area.getBoundaries());
        Assertions.assertEquals(2, arcs.size());
    }

    @Test
    void testFromBounds_triangle_large_1_oe() {
        final GreatCircle a = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPole(Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPole(Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(Arrays.asList(a, b, c));

        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testFromBounds_triangle_large_2_oe() {
        final GreatCircle a = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPole(Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPole(Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(Arrays.asList(a, b, c));

        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFromBounds_triangle_large_3_oe() {
        final GreatCircle a = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPole(Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPole(Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(Arrays.asList(a, b, c));

        Assertions.assertEquals(1.5 * Math.PI, area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFromBounds_triangle_large_4_oe() {
        final GreatCircle a = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPole(Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPole(Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(Arrays.asList(a, b, c));

        Assertions.assertEquals(Angle.PI_OVER_TWO, area.getSize(), TEST_EPS);
    }

    @Test
    void testFromBounds_triangle_large_6_oe() {
        final GreatCircle a = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPole(Vector3D.Unit.PLUS_Y, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPole(Vector3D.Unit.PLUS_Z, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(Arrays.asList(a, b, c));


        final Point2S expectedCentroid = triangleCentroid(Point2S.PLUS_I, Point2S.PLUS_J, Point2S.PLUS_K);

        checkCentroidConsistency(area);

        final List<GreatArc> arcs = sortArcs(area.getBoundaries());
        Assertions.assertEquals(3, arcs.size());
    }

    @Test
    void testFromBounds_triangle_small_1_oe() {
        final double azMin = 1.12 * Math.PI;
        final double azMax = 1.375 * Math.PI;
        final double azMid = 0.5 * (azMin + azMax);
        final double polarTop = 0.1;
        final double polarBottom = 0.25 * Math.PI;

        final Point2S p1 = Point2S.of(azMin, polarBottom);
        final Point2S p2 = Point2S.of(azMax, polarBottom);
        final Point2S p3 = Point2S.of(azMid, polarTop);

        final GreatCircle a = GreatCircles.fromPoints(p1, p2, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(p2, p3, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(p3, p1, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(Arrays.asList(a, b, c));

        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testFromBounds_triangle_small_2_oe() {
        final double azMin = 1.12 * Math.PI;
        final double azMax = 1.375 * Math.PI;
        final double azMid = 0.5 * (azMin + azMax);
        final double polarTop = 0.1;
        final double polarBottom = 0.25 * Math.PI;

        final Point2S p1 = Point2S.of(azMin, polarBottom);
        final Point2S p2 = Point2S.of(azMax, polarBottom);
        final Point2S p3 = Point2S.of(azMid, polarTop);

        final GreatCircle a = GreatCircles.fromPoints(p1, p2, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(p2, p3, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(p3, p1, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(Arrays.asList(a, b, c));

        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFromBounds_triangle_small_3_oe() {
        final double azMin = 1.12 * Math.PI;
        final double azMax = 1.375 * Math.PI;
        final double azMid = 0.5 * (azMin + azMax);
        final double polarTop = 0.1;
        final double polarBottom = 0.25 * Math.PI;

        final Point2S p1 = Point2S.of(azMin, polarBottom);
        final Point2S p2 = Point2S.of(azMax, polarBottom);
        final Point2S p3 = Point2S.of(azMid, polarTop);

        final GreatCircle a = GreatCircles.fromPoints(p1, p2, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(p2, p3, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(p3, p1, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(Arrays.asList(a, b, c));

        Assertions.assertEquals(p1.distance(p2)+ p2.distance(p3)+ p3.distance(p1),area.getBoundarySize(),TEST_EPS);
    }

    @Test
    void testFromBounds_triangle_small_4_oe() {
        final double azMin = 1.12 * Math.PI;
        final double azMax = 1.375 * Math.PI;
        final double azMid = 0.5 * (azMin + azMax);
        final double polarTop = 0.1;
        final double polarBottom = 0.25 * Math.PI;

        final Point2S p1 = Point2S.of(azMin, polarBottom);
        final Point2S p2 = Point2S.of(azMax, polarBottom);
        final Point2S p3 = Point2S.of(azMid, polarTop);

        final GreatCircle a = GreatCircles.fromPoints(p1, p2, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(p2, p3, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(p3, p1, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(Arrays.asList(a, b, c));

        final double size = Angle.TWO_PI - a.angle(b) - b.angle(c) - c.angle(a);
        Assertions.assertEquals(size, area.getSize(), TEST_EPS);
    }

    @Test
    void testFromBounds_triangle_small_6_oe() {
        final double azMin = 1.12 * Math.PI;
        final double azMax = 1.375 * Math.PI;
        final double azMid = 0.5 * (azMin + azMax);
        final double polarTop = 0.1;
        final double polarBottom = 0.25 * Math.PI;

        final Point2S p1 = Point2S.of(azMin, polarBottom);
        final Point2S p2 = Point2S.of(azMax, polarBottom);
        final Point2S p3 = Point2S.of(azMid, polarTop);

        final GreatCircle a = GreatCircles.fromPoints(p1, p2, TEST_PRECISION);
        final GreatCircle b = GreatCircles.fromPoints(p2, p3, TEST_PRECISION);
        final GreatCircle c = GreatCircles.fromPoints(p3, p1, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(Arrays.asList(a, b, c));

        final double size = Angle.TWO_PI - a.angle(b) - b.angle(c) - c.angle(a);

        final Point2S expectedCentroid = triangleCentroid(p1, p2, p3);

        checkCentroidConsistency(area);

        final List<GreatArc> arcs = sortArcs(area.getBoundaries());
        Assertions.assertEquals(3, arcs.size());
    }

    @Test
    void testFromBounds_quad_1_oe() {
        final Point2S p1 = Point2S.of(0.2, 0.1);
        final Point2S p2 = Point2S.of(0.1, 0.2);
        final Point2S p3 = Point2S.of(0.2, 0.5);
        final Point2S p4 = Point2S.of(0.3, 0.2);

        final GreatCircle c1 = GreatCircles.fromPoints(p1, p2, TEST_PRECISION);
        final GreatCircle c2 = GreatCircles.fromPoints(p2, p3, TEST_PRECISION);
        final GreatCircle c3 = GreatCircles.fromPoints(p3, p4, TEST_PRECISION);
        final GreatCircle c4 = GreatCircles.fromPoints(p4, p1, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(c1, c2, c3, c4);

        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testFromBounds_quad_2_oe() {
        final Point2S p1 = Point2S.of(0.2, 0.1);
        final Point2S p2 = Point2S.of(0.1, 0.2);
        final Point2S p3 = Point2S.of(0.2, 0.5);
        final Point2S p4 = Point2S.of(0.3, 0.2);

        final GreatCircle c1 = GreatCircles.fromPoints(p1, p2, TEST_PRECISION);
        final GreatCircle c2 = GreatCircles.fromPoints(p2, p3, TEST_PRECISION);
        final GreatCircle c3 = GreatCircles.fromPoints(p3, p4, TEST_PRECISION);
        final GreatCircle c4 = GreatCircles.fromPoints(p4, p1, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(c1, c2, c3, c4);

        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFromBounds_quad_3_oe() {
        final Point2S p1 = Point2S.of(0.2, 0.1);
        final Point2S p2 = Point2S.of(0.1, 0.2);
        final Point2S p3 = Point2S.of(0.2, 0.5);
        final Point2S p4 = Point2S.of(0.3, 0.2);

        final GreatCircle c1 = GreatCircles.fromPoints(p1, p2, TEST_PRECISION);
        final GreatCircle c2 = GreatCircles.fromPoints(p2, p3, TEST_PRECISION);
        final GreatCircle c3 = GreatCircles.fromPoints(p3, p4, TEST_PRECISION);
        final GreatCircle c4 = GreatCircles.fromPoints(p4, p1, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(c1, c2, c3, c4);

        Assertions.assertEquals(p1.distance(p2)+ p2.distance(p3)+ p3.distance(p4)+ p4.distance(p1),area.getBoundarySize(),TEST_EPS);
    }

    @Test
    void testFromBounds_quad_4_oe() {
        final Point2S p1 = Point2S.of(0.2, 0.1);
        final Point2S p2 = Point2S.of(0.1, 0.2);
        final Point2S p3 = Point2S.of(0.2, 0.5);
        final Point2S p4 = Point2S.of(0.3, 0.2);

        final GreatCircle c1 = GreatCircles.fromPoints(p1, p2, TEST_PRECISION);
        final GreatCircle c2 = GreatCircles.fromPoints(p2, p3, TEST_PRECISION);
        final GreatCircle c3 = GreatCircles.fromPoints(p3, p4, TEST_PRECISION);
        final GreatCircle c4 = GreatCircles.fromPoints(p4, p1, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(c1, c2, c3, c4);


        final double size = 2 * Math.PI - c1.angle(c2) - c2.angle(c3) - c3.angle(c4) - c4.angle(c1);
        Assertions.assertEquals(size, area.getSize(), TEST_EPS);
    }

    @Test
    void testFromBounds_quad_5_oe() {
        final Point2S p1 = Point2S.of(0.2, 0.1);
        final Point2S p2 = Point2S.of(0.1, 0.2);
        final Point2S p3 = Point2S.of(0.2, 0.5);
        final Point2S p4 = Point2S.of(0.3, 0.2);

        final GreatCircle c1 = GreatCircles.fromPoints(p1, p2, TEST_PRECISION);
        final GreatCircle c2 = GreatCircles.fromPoints(p2, p3, TEST_PRECISION);
        final GreatCircle c3 = GreatCircles.fromPoints(p3, p4, TEST_PRECISION);
        final GreatCircle c4 = GreatCircles.fromPoints(p4, p1, TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(c1, c2, c3, c4);


        final double size = 2 * Math.PI - c1.angle(c2) - c2.angle(c3) - c3.angle(c4) - c4.angle(c1);

        checkCentroidConsistency(area);

        final List<GreatArc> arcs = sortArcs(area.getBoundaries());
        Assertions.assertEquals(4, arcs.size());
    }

    @Test
    void testFromPath_empty_1_oe() {
        final ConvexArea2S area = ConvexArea2S.fromPath(GreatArcPath.empty());

        Assertions.assertSame(ConvexArea2S.full(), area);
    }

    @Test
    void testFromPath_1_oe() {
        final GreatArcPath path = GreatArcPath.builder(TEST_PRECISION)
                .append(Point2S.MINUS_I)
                .append(Point2S.MINUS_K)
                .append(Point2S.MINUS_J)
                .close();

        final ConvexArea2S area = ConvexArea2S.fromPath(path);

        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testFromPath_2_oe() {
        final GreatArcPath path = GreatArcPath.builder(TEST_PRECISION)
                .append(Point2S.MINUS_I)
                .append(Point2S.MINUS_K)
                .append(Point2S.MINUS_J)
                .close();

        final ConvexArea2S area = ConvexArea2S.fromPath(path);

        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFromPath_3_oe() {
        final GreatArcPath path = GreatArcPath.builder(TEST_PRECISION)
                .append(Point2S.MINUS_I)
                .append(Point2S.MINUS_K)
                .append(Point2S.MINUS_J)
                .close();

        final ConvexArea2S area = ConvexArea2S.fromPath(path);

        Assertions.assertEquals(1.5 * Math.PI, area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFromPath_4_oe() {
        final GreatArcPath path = GreatArcPath.builder(TEST_PRECISION)
                .append(Point2S.MINUS_I)
                .append(Point2S.MINUS_K)
                .append(Point2S.MINUS_J)
                .close();

        final ConvexArea2S area = ConvexArea2S.fromPath(path);

        Assertions.assertEquals(Angle.PI_OVER_TWO, area.getSize(), TEST_EPS);
    }

    @Test
    void testFromPath_6_oe() {
        final GreatArcPath path = GreatArcPath.builder(TEST_PRECISION)
                .append(Point2S.MINUS_I)
                .append(Point2S.MINUS_K)
                .append(Point2S.MINUS_J)
                .close();

        final ConvexArea2S area = ConvexArea2S.fromPath(path);


        final Point2S expectedCentroid = triangleCentroid(Point2S.MINUS_I, Point2S.MINUS_K, Point2S.MINUS_J);

        checkCentroidConsistency(area);

        final List<GreatArc> arcs = sortArcs(area.getBoundaries());
        Assertions.assertEquals(3, arcs.size());
    }

    @Test
    void testFromVertices_empty_1_oe() {
        final ConvexArea2S area = ConvexArea2S.fromVertices(Collections.emptyList(), TEST_PRECISION);

        Assertions.assertSame(ConvexArea2S.full(), area);
    }

    @Test
    void testFromVertices_1_oe() {
        final Point2S p1 = Point2S.PLUS_I;
        final Point2S p2 = Point2S.PLUS_J;
        final Point2S p3 = Point2S.PLUS_K;

        final ConvexArea2S area = ConvexArea2S.fromVertices(Arrays.asList(p1, p2, p3), TEST_PRECISION);

        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testFromVertices_2_oe() {
        final Point2S p1 = Point2S.PLUS_I;
        final Point2S p2 = Point2S.PLUS_J;
        final Point2S p3 = Point2S.PLUS_K;

        final ConvexArea2S area = ConvexArea2S.fromVertices(Arrays.asList(p1, p2, p3), TEST_PRECISION);

        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFromVertices_3_oe() {
        final Point2S p1 = Point2S.PLUS_I;
        final Point2S p2 = Point2S.PLUS_J;
        final Point2S p3 = Point2S.PLUS_K;

        final ConvexArea2S area = ConvexArea2S.fromVertices(Arrays.asList(p1, p2, p3), TEST_PRECISION);

        Assertions.assertEquals(2 * Math.PI, area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFromVertices_4_oe() {
        final Point2S p1 = Point2S.PLUS_I;
        final Point2S p2 = Point2S.PLUS_J;
        final Point2S p3 = Point2S.PLUS_K;

        final ConvexArea2S area = ConvexArea2S.fromVertices(Arrays.asList(p1, p2, p3), TEST_PRECISION);

        Assertions.assertEquals(Math.PI, area.getSize(), TEST_EPS);
    }

    @Test
    void testFromVertices_6_oe() {
        final Point2S p1 = Point2S.PLUS_I;
        final Point2S p2 = Point2S.PLUS_J;
        final Point2S p3 = Point2S.PLUS_K;

        final ConvexArea2S area = ConvexArea2S.fromVertices(Arrays.asList(p1, p2, p3), TEST_PRECISION);

        checkCentroidConsistency(area);

        final List<GreatArc> arcs = sortArcs(area.getBoundaries());
        Assertions.assertEquals(2, arcs.size());
    }

    @Test
    void testFromVertices_lastVertexRepeated_1_oe() {
        final Point2S p1 = Point2S.PLUS_I;
        final Point2S p2 = Point2S.PLUS_J;
        final Point2S p3 = Point2S.PLUS_K;

        final ConvexArea2S area = ConvexArea2S.fromVertices(Arrays.asList(p1, p2, p3, p1), TEST_PRECISION);

        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testFromVertices_lastVertexRepeated_2_oe() {
        final Point2S p1 = Point2S.PLUS_I;
        final Point2S p2 = Point2S.PLUS_J;
        final Point2S p3 = Point2S.PLUS_K;

        final ConvexArea2S area = ConvexArea2S.fromVertices(Arrays.asList(p1, p2, p3, p1), TEST_PRECISION);

        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFromVertices_lastVertexRepeated_3_oe() {
        final Point2S p1 = Point2S.PLUS_I;
        final Point2S p2 = Point2S.PLUS_J;
        final Point2S p3 = Point2S.PLUS_K;

        final ConvexArea2S area = ConvexArea2S.fromVertices(Arrays.asList(p1, p2, p3, p1), TEST_PRECISION);

        Assertions.assertEquals(1.5 * Math.PI, area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFromVertices_lastVertexRepeated_4_oe() {
        final Point2S p1 = Point2S.PLUS_I;
        final Point2S p2 = Point2S.PLUS_J;
        final Point2S p3 = Point2S.PLUS_K;

        final ConvexArea2S area = ConvexArea2S.fromVertices(Arrays.asList(p1, p2, p3, p1), TEST_PRECISION);

        Assertions.assertEquals(Angle.PI_OVER_TWO, area.getSize(), TEST_EPS);
    }

    @Test
    void testFromVertices_lastVertexRepeated_6_oe() {
        final Point2S p1 = Point2S.PLUS_I;
        final Point2S p2 = Point2S.PLUS_J;
        final Point2S p3 = Point2S.PLUS_K;

        final ConvexArea2S area = ConvexArea2S.fromVertices(Arrays.asList(p1, p2, p3, p1), TEST_PRECISION);


        final Point2S expectedCentroid = triangleCentroid(Point2S.PLUS_I, Point2S.PLUS_J, Point2S.PLUS_K);

        checkCentroidConsistency(area);

        final List<GreatArc> arcs = sortArcs(area.getBoundaries());
        Assertions.assertEquals(3, arcs.size());
    }

    @Test
    void testFromVertices_verticesRepeated_1_oe() {
        final Point2S p1 = Point2S.PLUS_I;
        final Point2S p2 = Point2S.PLUS_J;
        final Point2S p3 = Point2S.PLUS_K;

        final ConvexArea2S area = ConvexArea2S.fromVertices(Arrays.asList(
                p1, Point2S.of(1e-17, Angle.PI_OVER_TWO), p2, p3, p3, p1), true, TEST_PRECISION);

        Assertions.assertEquals(Angle.PI_OVER_TWO, area.getSize(), TEST_EPS);
    }

    @Test
    void testFromVertices_verticesRepeated_3_oe() {
        final Point2S p1 = Point2S.PLUS_I;
        final Point2S p2 = Point2S.PLUS_J;
        final Point2S p3 = Point2S.PLUS_K;

        final ConvexArea2S area = ConvexArea2S.fromVertices(Arrays.asList(
                p1, Point2S.of(1e-17, Angle.PI_OVER_TWO), p2, p3, p3, p1), true, TEST_PRECISION);


        final Point2S expectedCentroid = triangleCentroid(Point2S.PLUS_I, Point2S.PLUS_J, Point2S.PLUS_K);

        final List<Point2S> vertices = area.getBoundaryPath().getVertices();
        Assertions.assertEquals(4, vertices.size());
    }

    @Test
    void testFromVertices_invalidArguments_1_oe() {
        try {
    ConvexArea2S.fromVertices(Collections.singletonList(Point2S.PLUS_I), TEST_PRECISION);
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testFromVertices_invalidArguments_2_oe() {
        try {
    ConvexArea2S.fromVertices(Arrays.asList(Point2S.PLUS_I, Point2S.of(1e-16, Angle.PI_OVER_TWO)), TEST_PRECISION);
    fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    void testFromVertexLoop_1_oe() {
        final Point2S p1 = Point2S.PLUS_I;
        final Point2S p2 = Point2S.PLUS_J;
        final Point2S p3 = Point2S.PLUS_K;

        final ConvexArea2S area = ConvexArea2S.fromVertexLoop(Arrays.asList(p1, p2, p3), TEST_PRECISION);

        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testFromVertexLoop_2_oe() {
        final Point2S p1 = Point2S.PLUS_I;
        final Point2S p2 = Point2S.PLUS_J;
        final Point2S p3 = Point2S.PLUS_K;

        final ConvexArea2S area = ConvexArea2S.fromVertexLoop(Arrays.asList(p1, p2, p3), TEST_PRECISION);

        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testFromVertexLoop_3_oe() {
        final Point2S p1 = Point2S.PLUS_I;
        final Point2S p2 = Point2S.PLUS_J;
        final Point2S p3 = Point2S.PLUS_K;

        final ConvexArea2S area = ConvexArea2S.fromVertexLoop(Arrays.asList(p1, p2, p3), TEST_PRECISION);

        Assertions.assertEquals(1.5 * Math.PI, area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testFromVertexLoop_4_oe() {
        final Point2S p1 = Point2S.PLUS_I;
        final Point2S p2 = Point2S.PLUS_J;
        final Point2S p3 = Point2S.PLUS_K;

        final ConvexArea2S area = ConvexArea2S.fromVertexLoop(Arrays.asList(p1, p2, p3), TEST_PRECISION);

        Assertions.assertEquals(Angle.PI_OVER_TWO, area.getSize(), TEST_EPS);
    }

    @Test
    void testFromVertexLoop_6_oe() {
        final Point2S p1 = Point2S.PLUS_I;
        final Point2S p2 = Point2S.PLUS_J;
        final Point2S p3 = Point2S.PLUS_K;

        final ConvexArea2S area = ConvexArea2S.fromVertexLoop(Arrays.asList(p1, p2, p3), TEST_PRECISION);


        final Point2S expectedCentroid = triangleCentroid(Point2S.PLUS_I, Point2S.PLUS_J, Point2S.PLUS_K);

        checkCentroidConsistency(area);

        final List<GreatArc> arcs = sortArcs(area.getBoundaries());
        Assertions.assertEquals(3, arcs.size());
    }

    @Test
    void testFromVertexLoop_empty_1_oe() {
        final ConvexArea2S area = ConvexArea2S.fromVertexLoop(Collections.emptyList(), TEST_PRECISION);

        Assertions.assertSame(ConvexArea2S.full(), area);
    }

    @Test
    void testGetCentroid_diminishingLunes_1_oe() {
        final double eps = 1e-14;
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(eps);

        final double centerAz = 1;
        final double centerPolar = 0.5 * Math.PI;
        final Point2S center = Point2S.of(centerAz, centerPolar);
        final Point2S pole = Point2S.PLUS_K;

        final double startOffset = Angle.PI_OVER_TWO;
        final double minOffset = 1e-14;

        ConvexArea2S area;
        Point2S p1;
        Point2S p2;
        Point2S centroid;
        for (double offset = startOffset; offset > minOffset; offset *= 0.5) {
            p1 = Point2S.of(centerAz - offset, centerPolar);
            p2 = Point2S.of(centerAz + offset, centerPolar);

            area = ConvexArea2S.fromBounds(
                    GreatCircles.fromPoints(pole, p1, precision),
                    GreatCircles.fromPoints(p2, pole, precision));

            centroid = area.getCentroid();

            Assertions.assertTrue(area.contains(centroid));
    }
    }

    @Test
    void testGetCentroid_diminishingSquares_1_oe() {
        final double eps = 1e-14;
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(eps);

        final double centerAz = 1;
        final double centerPolar = 0.5 * Math.PI;
        final Point2S center = Point2S.of(centerAz, centerPolar);

        final double minOffset = 1e-14;

        ConvexArea2S area;
        Point2S p1;
        Point2S p2;
        Point2S p3;
        Point2S p4;
        Point2S centroid;
        for (double offset = 0.5; offset > minOffset; offset *= 0.5) {
            p1 = Point2S.of(centerAz, centerPolar - offset);
            p2 = Point2S.of(centerAz - offset, centerPolar);
            p3 = Point2S.of(centerAz, centerPolar + offset);
            p4 = Point2S.of(centerAz + offset, centerPolar);

            area = ConvexArea2S.fromVertexLoop(Arrays.asList(p1, p2, p3, p4), precision);

            centroid = area.getCentroid();

            Assertions.assertTrue(area.contains(centroid));
    }
    }

    @Test
    void testBoundaryStream_1_oe() {
        final GreatCircle circle = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final ConvexArea2S area = ConvexArea2S.fromBounds(circle);

        final List<GreatArc> arcs = area.boundaryStream().collect(Collectors.toList());

        Assertions.assertEquals(1, arcs.size());
    }

    @Test
    void testBoundaryStream_2_oe() {
        final GreatCircle circle = GreatCircles.fromPole(Vector3D.Unit.PLUS_X, TEST_PRECISION);
        final ConvexArea2S area = ConvexArea2S.fromBounds(circle);

        final List<GreatArc> arcs = area.boundaryStream().collect(Collectors.toList());

        Assertions.assertSame(circle, arcs.get(0).getCircle());
    }

    @Test
    void testBoundaryStream_noBoundaries_1_oe() {
        final ConvexArea2S area = ConvexArea2S.full();

        final List<GreatArc> arcs = area.boundaryStream().collect(Collectors.toList());

        Assertions.assertEquals(0, arcs.size());
    }

    @Test
    void testGetInteriorAngles_noAngles_1_oe() {
        Assertions.assertEquals(0, ConvexArea2S.full().getInteriorAngles().length);
    }

    @Test
    void testGetInteriorAngles_noAngles_2_oe() {
        Assertions.assertEquals(0,ConvexArea2S.fromBounds(GreatCircles.fromPole(Vector3D.Unit.PLUS_X,TEST_PRECISION)).getInteriorAngles().length);
    }

    @Test
    void testGetInteriorAngles_1_oe() {
        final Point2S p1 = Point2S.PLUS_K;
        final Point2S p2 = Point2S.PLUS_I;
        final Point2S p4 = Point2S.PLUS_J;

        final GreatCircle base = GreatCircles.fromPoints(p2, p4, TEST_PRECISION);
        final GreatCircle c1 = base.transform(Transform2S.createRotation(p2, -0.2));
        final GreatCircle c2 = base.transform(Transform2S.createRotation(p4, 0.1));

        final Point2S p3 = c1.intersection(c2);

        final ConvexArea2S area = ConvexArea2S.fromVertexLoop(Arrays.asList(p1, p2, p3, p4), TEST_PRECISION);

        final double[] angles = area.getInteriorAngles();
        Assertions.assertEquals(4, angles.length);
    }

    @Test
    void testGetInteriorAngles_2_oe() {
        final Point2S p1 = Point2S.PLUS_K;
        final Point2S p2 = Point2S.PLUS_I;
        final Point2S p4 = Point2S.PLUS_J;

        final GreatCircle base = GreatCircles.fromPoints(p2, p4, TEST_PRECISION);
        final GreatCircle c1 = base.transform(Transform2S.createRotation(p2, -0.2));
        final GreatCircle c2 = base.transform(Transform2S.createRotation(p4, 0.1));

        final Point2S p3 = c1.intersection(c2);

        final ConvexArea2S area = ConvexArea2S.fromVertexLoop(Arrays.asList(p1, p2, p3, p4), TEST_PRECISION);

        final double[] angles = area.getInteriorAngles();
        Assertions.assertEquals(Angle.PI_OVER_TWO + 0.2, angles[0], TEST_EPS);
    }

    @Test
    void testGetInteriorAngles_3_oe() {
        final Point2S p1 = Point2S.PLUS_K;
        final Point2S p2 = Point2S.PLUS_I;
        final Point2S p4 = Point2S.PLUS_J;

        final GreatCircle base = GreatCircles.fromPoints(p2, p4, TEST_PRECISION);
        final GreatCircle c1 = base.transform(Transform2S.createRotation(p2, -0.2));
        final GreatCircle c2 = base.transform(Transform2S.createRotation(p4, 0.1));

        final Point2S p3 = c1.intersection(c2);

        final ConvexArea2S area = ConvexArea2S.fromVertexLoop(Arrays.asList(p1, p2, p3, p4), TEST_PRECISION);

        final double[] angles = area.getInteriorAngles();
        Assertions.assertEquals(Math.PI - c1.angle(c2), angles[1], TEST_EPS);
    }

    @Test
    void testGetInteriorAngles_4_oe() {
        final Point2S p1 = Point2S.PLUS_K;
        final Point2S p2 = Point2S.PLUS_I;
        final Point2S p4 = Point2S.PLUS_J;

        final GreatCircle base = GreatCircles.fromPoints(p2, p4, TEST_PRECISION);
        final GreatCircle c1 = base.transform(Transform2S.createRotation(p2, -0.2));
        final GreatCircle c2 = base.transform(Transform2S.createRotation(p4, 0.1));

        final Point2S p3 = c1.intersection(c2);

        final ConvexArea2S area = ConvexArea2S.fromVertexLoop(Arrays.asList(p1, p2, p3, p4), TEST_PRECISION);

        final double[] angles = area.getInteriorAngles();
        Assertions.assertEquals(Angle.PI_OVER_TWO + 0.1, angles[2], TEST_EPS);
    }

    @Test
    void testGetInteriorAngles_5_oe() {
        final Point2S p1 = Point2S.PLUS_K;
        final Point2S p2 = Point2S.PLUS_I;
        final Point2S p4 = Point2S.PLUS_J;

        final GreatCircle base = GreatCircles.fromPoints(p2, p4, TEST_PRECISION);
        final GreatCircle c1 = base.transform(Transform2S.createRotation(p2, -0.2));
        final GreatCircle c2 = base.transform(Transform2S.createRotation(p4, 0.1));

        final Point2S p3 = c1.intersection(c2);

        final ConvexArea2S area = ConvexArea2S.fromVertexLoop(Arrays.asList(p1, p2, p3, p4), TEST_PRECISION);

        final double[] angles = area.getInteriorAngles();
        Assertions.assertEquals(Angle.PI_OVER_TWO, angles[3], TEST_EPS);
    }

    @Test
    void testTransform_1_oe() {
        final Transform2S t = Transform2S.createReflection(Point2S.PLUS_J);
        final ConvexArea2S input = ConvexArea2S.fromVertexLoop(
                Arrays.asList(Point2S.PLUS_I, Point2S.PLUS_J, Point2S.PLUS_K), TEST_PRECISION);

        final ConvexArea2S area = input.transform(t);

        Assertions.assertFalse(area.isFull());
    }

    @Test
    void testTransform_2_oe() {
        final Transform2S t = Transform2S.createReflection(Point2S.PLUS_J);
        final ConvexArea2S input = ConvexArea2S.fromVertexLoop(
                Arrays.asList(Point2S.PLUS_I, Point2S.PLUS_J, Point2S.PLUS_K), TEST_PRECISION);

        final ConvexArea2S area = input.transform(t);

        Assertions.assertFalse(area.isEmpty());
    }

    @Test
    void testTransform_3_oe() {
        final Transform2S t = Transform2S.createReflection(Point2S.PLUS_J);
        final ConvexArea2S input = ConvexArea2S.fromVertexLoop(
                Arrays.asList(Point2S.PLUS_I, Point2S.PLUS_J, Point2S.PLUS_K), TEST_PRECISION);

        final ConvexArea2S area = input.transform(t);

        Assertions.assertEquals(1.5 * Math.PI, area.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testTransform_4_oe() {
        final Transform2S t = Transform2S.createReflection(Point2S.PLUS_J);
        final ConvexArea2S input = ConvexArea2S.fromVertexLoop(
                Arrays.asList(Point2S.PLUS_I, Point2S.PLUS_J, Point2S.PLUS_K), TEST_PRECISION);

        final ConvexArea2S area = input.transform(t);

        Assertions.assertEquals(Angle.PI_OVER_TWO, area.getSize(), TEST_EPS);
    }

    @Test
    void testTransform_6_oe() {
        final Transform2S t = Transform2S.createReflection(Point2S.PLUS_J);
        final ConvexArea2S input = ConvexArea2S.fromVertexLoop(
                Arrays.asList(Point2S.PLUS_I, Point2S.PLUS_J, Point2S.PLUS_K), TEST_PRECISION);

        final ConvexArea2S area = input.transform(t);


        final Point2S expectedCentroid = triangleCentroid(Point2S.MINUS_J, Point2S.PLUS_I, Point2S.PLUS_K);

        checkCentroidConsistency(area);

        final List<GreatArc> arcs = sortArcs(area.getBoundaries());
        Assertions.assertEquals(3, arcs.size());
    }

    @Test
    void testTrim_1_oe() {
        final GreatCircle c1 = GreatCircles.fromPole(Vector3D.Unit.MINUS_X, TEST_PRECISION);
        final GreatCircle c2 = GreatCircles.fromPole(Vector3D.of(1, 1, 0), TEST_PRECISION);

        final GreatCircle slanted = GreatCircles.fromPole(Vector3D.of(-1, 0, 1), TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(c1, c2);

        checkArc(area.trim(GreatCircles.arcFromPoints(Point2S.of(0.1, Angle.PI_OVER_TWO), Point2S.MINUS_I, TEST_PRECISION)),
                Point2S.PLUS_J, Point2S.of(0.75 * Math.PI, Angle.PI_OVER_TWO));

        checkArc(area.trim(GreatCircles.arcFromPoints(Point2S.MINUS_I, Point2S.of(0.2, Angle.PI_OVER_TWO), TEST_PRECISION)),
                Point2S.of(0.75 * Math.PI, Angle.PI_OVER_TWO), Point2S.PLUS_J);

        checkArc(area.trim(GreatCircles.arcFromPoints(Point2S.of(0.6 * Math.PI, 0.1), Point2S.of(0.7 * Math.PI, 0.8), TEST_PRECISION)),
                Point2S.of(0.6 * Math.PI, 0.1), Point2S.of(0.7 * Math.PI, 0.8));

        Assertions.assertNull(area.trim(GreatCircles.arcFromPoints(Point2S.MINUS_I, Point2S.MINUS_J, TEST_PRECISION)));
    }

    @Test
    void testSplit_both_1_oe() {
        final GreatCircle c1 = GreatCircles.fromPole(Vector3D.Unit.MINUS_X, TEST_PRECISION);
        final GreatCircle c2 = GreatCircles.fromPole(Vector3D.of(1, 1, 0), TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(c1, c2);

        final GreatCircle splitter = GreatCircles.fromPole(Vector3D.of(-1, 0, 1), TEST_PRECISION);

        final Split<ConvexArea2S> split = area.split(splitter);

        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_both_4_oe() {
        final GreatCircle c1 = GreatCircles.fromPole(Vector3D.Unit.MINUS_X, TEST_PRECISION);
        final GreatCircle c2 = GreatCircles.fromPole(Vector3D.of(1, 1, 0), TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(c1, c2);

        final GreatCircle splitter = GreatCircles.fromPole(Vector3D.of(-1, 0, 1), TEST_PRECISION);

        final Split<ConvexArea2S> split = area.split(splitter);


        final Point2S p1 = c1.intersection(splitter);
        final Point2S p2 = splitter.intersection(c2);

        final ConvexArea2S minus = split.getMinus();

        final ConvexArea2S plus = split.getPlus();

        Assertions.assertEquals(area.getSize(), minus.getSize() + plus.getSize(), TEST_EPS);
    }

    @Test
    void testSplit_minus_1_oe() {
        final ConvexArea2S area = ConvexArea2S.fromVertexLoop(Arrays.asList(
                    Point2S.PLUS_I, Point2S.PLUS_K, Point2S.MINUS_J
                ), TEST_PRECISION);

        final GreatCircle splitter = GreatCircles.fromPole(Vector3D.of(0, -1, 1), TEST_PRECISION);

        final Split<ConvexArea2S> split = area.split(splitter);

        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());
    }

    @Test
    void testSplit_minus_2_oe() {
        final ConvexArea2S area = ConvexArea2S.fromVertexLoop(Arrays.asList(
                    Point2S.PLUS_I, Point2S.PLUS_K, Point2S.MINUS_J
                ), TEST_PRECISION);

        final GreatCircle splitter = GreatCircles.fromPole(Vector3D.of(0, -1, 1), TEST_PRECISION);

        final Split<ConvexArea2S> split = area.split(splitter);


        Assertions.assertSame(area, split.getMinus());
    }

    @Test
    void testSplit_minus_3_oe() {
        final ConvexArea2S area = ConvexArea2S.fromVertexLoop(Arrays.asList(
                    Point2S.PLUS_I, Point2S.PLUS_K, Point2S.MINUS_J
                ), TEST_PRECISION);

        final GreatCircle splitter = GreatCircles.fromPole(Vector3D.of(0, -1, 1), TEST_PRECISION);

        final Split<ConvexArea2S> split = area.split(splitter);


        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplit_plus_1_oe() {
        final ConvexArea2S area = ConvexArea2S.fromVertexLoop(Arrays.asList(
                    Point2S.PLUS_I, Point2S.PLUS_K, Point2S.MINUS_J
                ), TEST_PRECISION);

        final GreatCircle splitter = GreatCircles.fromPole(Vector3D.of(0, 1, -1), TEST_PRECISION);

        final Split<ConvexArea2S> split = area.split(splitter);

        Assertions.assertEquals(SplitLocation.PLUS, split.getLocation());
    }

    @Test
    void testSplit_plus_2_oe() {
        final ConvexArea2S area = ConvexArea2S.fromVertexLoop(Arrays.asList(
                    Point2S.PLUS_I, Point2S.PLUS_K, Point2S.MINUS_J
                ), TEST_PRECISION);

        final GreatCircle splitter = GreatCircles.fromPole(Vector3D.of(0, 1, -1), TEST_PRECISION);

        final Split<ConvexArea2S> split = area.split(splitter);


        Assertions.assertNull(split.getMinus());
    }

    @Test
    void testSplit_plus_3_oe() {
        final ConvexArea2S area = ConvexArea2S.fromVertexLoop(Arrays.asList(
                    Point2S.PLUS_I, Point2S.PLUS_K, Point2S.MINUS_J
                ), TEST_PRECISION);

        final GreatCircle splitter = GreatCircles.fromPole(Vector3D.of(0, 1, -1), TEST_PRECISION);

        final Split<ConvexArea2S> split = area.split(splitter);


        Assertions.assertSame(area, split.getPlus());
    }

    @Test
    void testToList_full_1_oe() {
        final ConvexArea2S area = ConvexArea2S.full();

        final BoundaryList2S list = area.toList();

        Assertions.assertEquals(0, list.count());
    }

    @Test
    void testToList_1_oe() {
        final ConvexArea2S area = ConvexArea2S.fromVertexLoop(Arrays.asList(
                    Point2S.of(0.1, 0.1), Point2S.of(-0.4, 1),
                    Point2S.of(0.15, 1.5), Point2S.of(0.3, 1.2),
                    Point2S.of(0.1, 0.1)
                ), TEST_PRECISION);

        final BoundaryList2S list = area.toList();

        Assertions.assertEquals(4, list.count());
    }

    @Test
    void testToList_2_oe() {
        final ConvexArea2S area = ConvexArea2S.fromVertexLoop(Arrays.asList(
                    Point2S.of(0.1, 0.1), Point2S.of(-0.4, 1),
                    Point2S.of(0.15, 1.5), Point2S.of(0.3, 1.2),
                    Point2S.of(0.1, 0.1)
                ), TEST_PRECISION);

        final BoundaryList2S list = area.toList();

        Assertions.assertEquals(area.getSize(), list.toTree().getSize(), TEST_EPS);
    }

    @Test
    void testToTree_full_1_oe() {
        final ConvexArea2S area = ConvexArea2S.full();

        final RegionBSPTree2S tree = area.toTree();

        Assertions.assertTrue(tree.isFull());
    }

    @Test
    void testToTree_full_2_oe() {
        final ConvexArea2S area = ConvexArea2S.full();

        final RegionBSPTree2S tree = area.toTree();

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testToTree_1_oe() {
        final ConvexArea2S area = ConvexArea2S.fromVertexLoop(Arrays.asList(
                    Point2S.of(0.1, 0.1), Point2S.of(-0.4, 1),
                    Point2S.of(0.15, 1.5), Point2S.of(0.3, 1.2),
                    Point2S.of(0.1, 0.1)
                ), TEST_PRECISION);

        final RegionBSPTree2S tree = area.toTree();

        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testToTree_2_oe() {
        final ConvexArea2S area = ConvexArea2S.fromVertexLoop(Arrays.asList(
                    Point2S.of(0.1, 0.1), Point2S.of(-0.4, 1),
                    Point2S.of(0.15, 1.5), Point2S.of(0.3, 1.2),
                    Point2S.of(0.1, 0.1)
                ), TEST_PRECISION);

        final RegionBSPTree2S tree = area.toTree();

        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testToTree_3_oe() {
        final ConvexArea2S area = ConvexArea2S.fromVertexLoop(Arrays.asList(
                    Point2S.of(0.1, 0.1), Point2S.of(-0.4, 1),
                    Point2S.of(0.15, 1.5), Point2S.of(0.3, 1.2),
                    Point2S.of(0.1, 0.1)
                ), TEST_PRECISION);

        final RegionBSPTree2S tree = area.toTree();


        Assertions.assertEquals(area.getSize(), tree.getSize(), TEST_EPS);
    }

@Test
    void testSplit_both_2_oe() {
        final GreatCircle c1 = GreatCircles.fromPole(Vector3D.Unit.MINUS_X, TEST_PRECISION);
        final GreatCircle c2 = GreatCircles.fromPole(Vector3D.of(1, 1, 0), TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(c1, c2);

        final GreatCircle splitter = GreatCircles.fromPole(Vector3D.of(-1, 0, 1), TEST_PRECISION);

        final Split<ConvexArea2S> split = area.split(splitter);


        final Point2S p1 = c1.intersection(splitter);
        final Point2S p2 = splitter.intersection(c2);

        final ConvexArea2S minus = split.getMinus();
        assertPath(minus.getBoundaryPath(), Point2S.PLUS_K, p1, p2, Point2S.PLUS_K);
    }

@Test
    void testSplit_both_3_oe() {
        final GreatCircle c1 = GreatCircles.fromPole(Vector3D.Unit.MINUS_X, TEST_PRECISION);
        final GreatCircle c2 = GreatCircles.fromPole(Vector3D.of(1, 1, 0), TEST_PRECISION);

        final ConvexArea2S area = ConvexArea2S.fromBounds(c1, c2);

        final GreatCircle splitter = GreatCircles.fromPole(Vector3D.of(-1, 0, 1), TEST_PRECISION);

        final Split<ConvexArea2S> split = area.split(splitter);


        final Point2S p1 = c1.intersection(splitter);
        final Point2S p2 = splitter.intersection(c2);

        final ConvexArea2S minus = split.getMinus();

        final ConvexArea2S plus = split.getPlus();
        assertPath(plus.getBoundaryPath(), p1, Point2S.MINUS_K, p2, p1);
    }

}
