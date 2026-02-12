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
import java.util.stream.Stream;

import org.apache.commons.geometry.core.RegionLocation;
import org.apache.commons.geometry.core.partitioning.Split;
import org.apache.commons.geometry.core.partitioning.SplitLocation;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.geometry.spherical.SphericalTestUtils;
import org.apache.commons.geometry.spherical.oned.Point1S;
import org.apache.commons.geometry.spherical.twod.RegionBSPTree2S.RegionNode2S;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RegionBSPTree2STest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    // alternative epsilon value for checking the centroids of complex
    // or very small regions
    private static final double CENTROID_EPS = 1e-5;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    private static final GreatCircle EQUATOR = GreatCircles.fromPoleAndU(
            Vector3D.Unit.PLUS_Z, Vector3D.Unit.PLUS_X, TEST_PRECISION);

    private static final GreatCircle X_MERIDIAN = GreatCircles.fromPoleAndU(
            Vector3D.Unit.PLUS_Y, Vector3D.Unit.PLUS_X, TEST_PRECISION);

    private static final GreatCircle Y_MERIDIAN = GreatCircles.fromPoleAndU(
            Vector3D.Unit.PLUS_X, Vector3D.Unit.PLUS_Y, TEST_PRECISION);

    @Test
    void testProject() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();
        tree.insert(EQUATOR.arc(0, Math.PI));
        tree.insert(X_MERIDIAN.arc(Math.PI, 0));

        // act/assert
        SphericalTestUtils.assertPointsEq(Point2S.of(Angle.PI_OVER_TWO, Angle.PI_OVER_TWO),
                tree.project(Point2S.of(Angle.PI_OVER_TWO, Angle.PI_OVER_TWO + 0.2)), TEST_EPS);
        SphericalTestUtils.assertPointsEq(Point2S.PLUS_K,
                tree.project(Point2S.of(-Angle.PI_OVER_TWO, 0.2)), TEST_EPS);

        SphericalTestUtils.assertPointsEq(Point2S.PLUS_I,
                tree.project(Point2S.of(-0.5, Angle.PI_OVER_TWO)), TEST_EPS);
        SphericalTestUtils.assertPointsEq(Point2S.MINUS_I,
                tree.project(Point2S.of(Math.PI + 0.5, Angle.PI_OVER_TWO)), TEST_EPS);

        final Point2S centroid = tree.getCentroid();
        SphericalTestUtils.assertPointsEq(Point2S.PLUS_K,
                tree.project(centroid.slerp(Point2S.PLUS_K, 1e-10)), TEST_EPS);
        SphericalTestUtils.assertPointsEq(Point2S.PLUS_J,
                tree.project(centroid.slerp(Point2S.PLUS_J, 1e-10)), TEST_EPS);
    }

    @Test
    void testCircleToPolygonCentroid() {
        final double radius = 0.0001;
        final Point2S center = Point2S.of(1.0, 1.0);
        final int numPts = 200;

        // counterclockwise
        final RegionBSPTree2S ccw = circleToPolygon(center, radius, numPts, false, TEST_PRECISION);
        SphericalTestUtils.assertPointsEq(center, ccw.getCentroid(), TEST_EPS);

        // clockwise; centroid should just be antipodal for the circle center
        final RegionBSPTree2S cw = circleToPolygon(center, radius, numPts, true, TEST_PRECISION);

        SphericalTestUtils.assertPointsEq(center.antipodal(), cw.getCentroid(), CENTROID_EPS);
    }

    /**
     * Insert hyperplane convex subsets defining the positive quadrant area.
     * @param tree
     */
    private static void insertPositiveQuadrant(final RegionBSPTree2S tree) {
        tree.insert(Arrays.asList(
                EQUATOR.arc(Point2S.PLUS_I, Point2S.PLUS_J),
                X_MERIDIAN.arc(Point2S.PLUS_K, Point2S.PLUS_I),
                Y_MERIDIAN.arc(Point2S.PLUS_J, Point2S.PLUS_K)
            ));
    }

    private static Point2S triangleCentroid(final Point2S p1, final Point2S p2, final Point2S p3) {
        // compute the centroid using intersection mid point arcs
        final GreatCircle c1 = GreatCircles.fromPoints(p1, p2.slerp(p3, 0.5), TEST_PRECISION);
        final GreatCircle c2 = GreatCircles.fromPoints(p2, p1.slerp(p3, 0.5), TEST_PRECISION);

        return c1.intersection(c2);
    }

    /** Assert that the given path contains {@code vertices} in the exact order given.
     * @param path path to check
     * @param vertices expected vertices
     */
    private static void assertPath(final GreatArcPath path, final Point2S... vertices) {
        final List<Point2S> expected = Arrays.asList(vertices);
        final List<Point2S> actual = path.getVertices();

        if (expected.size() != actual.size()) {
            Assertions.fail("Unexpected path size. Expected path " + expected + " but was " + actual);
        }

        for (int i = 0; i < expected.size(); ++i) {
            if (!expected.get(i).eq(actual.get(i), TEST_PRECISION)) {
                Assertions.fail("Unexpected path vertex at index " + i + ". Expected path " + expected + " but was " + actual);
            }
        }
    }

    /** Assert that the given path contains {@code vertices} in a closed loop sequence. The
     * actual path may start at any point in the sequence.
     * @param path path to check
     * @param vertices expected vertex loop without repeated points
     */
    private static void assertPathLoop(final GreatArcPath path, final Point2S... vertices) {
        final List<Point2S> expected = Arrays.asList(vertices);
        final List<Point2S> actual = path.getVertices();

        Assertions.assertTrue(path.isClosed());
        Assertions.assertFalse(path.isEmpty());
        Assertions.assertTrue(actual.get(0).eq(actual.get(actual.size() - 1), TEST_PRECISION));

        final List<Point2S> actualLoopVertices = actual.subList(0, actual.size() - 1);

        if (expected.size() != actualLoopVertices.size()) {
            Assertions.fail("Unexpected path loop. Expected vertex loop " + expected + " but " + actual);
        }

        int offset = -1;
        final Point2S start = expected.get(0);
        for (int i = 0; i < actualLoopVertices.size(); ++i) {
            if (actualLoopVertices.get(i).eq(start, TEST_PRECISION)) {
                offset = i;
                break;
            }
        }

        if (offset < 0) {
            Assertions.fail("Vertex loops do not share any points: expected vertex loop " + expected + " but was " + actual);
        }

        for (int i = 0; i < expected.size(); ++i) {
            final Point2S expectedVertex = expected.get(i % expected.size());
            final Point2S actualVertex = actualLoopVertices.get((i + offset) % actualLoopVertices.size());

            if (!expectedVertex.eq(actualVertex, TEST_PRECISION)) {
                Assertions.fail("Unexpected vertex at index " + i + ": expected " + expectedVertex + " but was " + actualVertex);
            }
        }
    }

    private static RegionBSPTree2S latLongToTree(final Precision.DoubleEquivalence precision, final double[][] points) {
        final GreatArcPath.Builder pathBuilder = GreatArcPath.builder(precision);

        for (final double[] point : points) {
            pathBuilder.append(latLongToPoint(point[0], point[1]));
        }

        return pathBuilder.close().toTree();
    }

    private static Point2S latLongToPoint(final double latitude, final double longitude) {
        return Point2S.of(Math.toRadians(longitude), Math.toRadians(90.0 - latitude));
    }

    private static void checkCentroidConsistency(final RegionBSPTree2S region) {
        final Point2S centroid = region.getCentroid();
        final double size = region.getSize();

        final GreatCircle circle = GreatCircles.fromPole(centroid.getVector(), TEST_PRECISION);
        for (double az = 0; az <= Angle.TWO_PI; az += 0.2) {
            final Point2S pt = circle.toSpace(Point1S.of(az));
            final GreatCircle splitter = GreatCircles.fromPoints(centroid, pt, TEST_PRECISION);

            final Split<RegionBSPTree2S> split = region.split(splitter);

            Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());

            final RegionBSPTree2S minus = split.getMinus();
            final double minusSize = minus.getSize();

            final RegionBSPTree2S plus = split.getPlus();
            final double plusSize = plus.getSize();

            final Point2S computedCentroid = Point2S.from(weightedCentroidVector(minus)
                    .add(weightedCentroidVector(plus)));

            Assertions.assertEquals(size, minusSize + plusSize, TEST_EPS);
            SphericalTestUtils.assertPointsEq(centroid, computedCentroid, TEST_EPS);
        }
    }

    private static Vector3D weightedCentroidVector(final RegionBSPTree2S tree) {
        Vector3D sum = Vector3D.ZERO;
        for (final ConvexArea2S convex : tree.toConvex()) {
            sum = sum.add(convex.getWeightedCentroidVector());
        }

        return sum;
    }

    private static RegionBSPTree2S buildDiamond(final Point2S center, final double radius) {
        final Vector3D u = center.getVector();
        final Vector3D w = u.orthogonal(Vector3D.Unit.PLUS_Z);
        final Vector3D v = w.cross(u);

        final Transform2S rotV = Transform2S.createRotation(v, radius);
        final Transform2S rotW = Transform2S.createRotation(w, radius);

        final Point2S top = rotV.inverse().apply(center);
        final Point2S bottom = rotV.apply(center);

        final Point2S right = rotW.apply(center);
        final Point2S left = rotW.inverse().apply(center);

        return GreatArcPath.fromVertexLoop(Arrays.asList(top, left, bottom, right), TEST_PRECISION)
                .toTree();
    }

    /** Solve for the hypotenuse of a spherical right triangle, given the lengths of the
     * other two side. The sides must have lengths less than pi/2.
     * @param a first side; must be less than pi/2
     * @param b second side; must be less than pi/2
     * @return the hypotenuse of the spherical right triangle with sides of the given lengths
     */
    private static double sphericalHypot(final double a, final double b) {
        // use the spherical law of cosines and the fact that cos(pi/2) = 0
        // https://en.wikipedia.org/wiki/Spherical_trigonometry#Cosine_rules
        return Math.acos(Math.cos(a) * Math.cos(b));
    }

    /**
     * Compute the area of the spherical right triangle with the given sides. The sides must have lengths
     * less than pi/2.
     * @param a first side; must be less than pi/2
     * @param b second side; must be less than pi/2
     * @return the area of the spherical right triangle
     */
    private static double rightTriangleArea(final double a, final double b) {
        final double c = sphericalHypot(a, b);

        // use the spherical law of sines to determine the interior angles
        // https://en.wikipedia.org/wiki/Spherical_trigonometry#Sine_rules
        final double sinC = Math.sin(c);
        final double angleA = Math.asin(Math.sin(a) / sinC);
        final double angleB = Math.asin(Math.sin(b) / sinC);

        // use Girard's theorem
        return angleA + angleB - Angle.PI_OVER_TWO;
    }

    private static RegionBSPTree2S circleToPolygon(final Point2S center, final double radius, final int numPts,
                                                   final boolean clockwise, final Precision.DoubleEquivalence precision) {
        final List<Point2S> pts = new ArrayList<>(numPts);

        // get an arbitrary point on the circle boundary
        pts.add(Transform2S.createRotation(center.getVector().orthogonal(), radius).apply(center));

        // create the list of boundary points by rotating the previous point around the circle center
        final double span = Angle.TWO_PI / numPts;

        // negate the span for clockwise winding
        final Transform2S rotate = Transform2S.createRotation(center, clockwise ? -span : span);
        for (int i = 1; i < numPts; ++i) {
            pts.add(rotate.apply(pts.get(i - 1)));
        }

        return GreatArcPath.fromVertexLoop(pts, precision).toTree();
    }

    @Test
    void testGetBoundaryPaths_isUnmodifiable_1_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();
        tree.insert(EQUATOR.span());

        // act/assert
        try {
    tree.getBoundaryPaths().add(GreatArcPath.empty());
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

}
