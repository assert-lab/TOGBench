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

import static org.junit.jupiter.api.Assertions.fail;

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
    void testCtor_booleanArg_true_1_oe() {
        // act
        final RegionBSPTree2S tree = new RegionBSPTree2S(true);

        // assert
        Assertions.assertTrue(tree.isFull());
    }

    @Test
    void testCtor_booleanArg_true_2_oe() {
        // act
        final RegionBSPTree2S tree = new RegionBSPTree2S(true);

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testCtor_booleanArg_true_3_oe() {
        // act
        final RegionBSPTree2S tree = new RegionBSPTree2S(true);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1, tree.count());
    }

    @Test
    void testCtor_booleanArg_false_1_oe() {
        // act
        final RegionBSPTree2S tree = new RegionBSPTree2S(false);

        // assert
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testCtor_booleanArg_false_2_oe() {
        // act
        final RegionBSPTree2S tree = new RegionBSPTree2S(false);

        // assert
        // removed other assertion
        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void testCtor_booleanArg_false_3_oe() {
        // act
        final RegionBSPTree2S tree = new RegionBSPTree2S(false);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1, tree.count());
    }

    @Test
    void testCtor_default_1_oe() {
        // act
        final RegionBSPTree2S tree = new RegionBSPTree2S();

        // assert
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testCtor_default_2_oe() {
        // act
        final RegionBSPTree2S tree = new RegionBSPTree2S();

        // assert
        // removed other assertion
        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void testCtor_default_3_oe() {
        // act
        final RegionBSPTree2S tree = new RegionBSPTree2S();

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1, tree.count());
    }

    @Test
    void testFull_factoryMethod_1_oe() {
        // act
        final RegionBSPTree2S tree = RegionBSPTree2S.full();

        // assert
        Assertions.assertTrue(tree.isFull());
    }

    @Test
    void testFull_factoryMethod_2_oe() {
        // act
        final RegionBSPTree2S tree = RegionBSPTree2S.full();

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testFull_factoryMethod_3_oe() {
        // act
        final RegionBSPTree2S tree = RegionBSPTree2S.full();

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1, tree.count());
    }

    @Test
    void testEmpty_factoryMethod_1_oe() {
        // act
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();

        // assert
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testEmpty_factoryMethod_2_oe() {
        // act
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();

        // assert
        // removed other assertion
        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void testEmpty_factoryMethod_3_oe() {
        // act
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1, tree.count());
    }

    @Test
    void testFrom_boundaries_noBoundaries_1_oe() {
        // act/assert
        Assertions.assertTrue(RegionBSPTree2S.from(Collections.emptyList()).isEmpty());
    }

    @Test
    void testFrom_boundaries_noBoundaries_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertTrue(RegionBSPTree2S.from(Collections.emptyList(), true).isFull());
    }

    @Test
    void testFrom_boundaries_noBoundaries_3_oe() {
        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(RegionBSPTree2S.from(Collections.emptyList(), false).isEmpty());
    }

    @Test
    void testFrom_boundaries_1_oe() {
        // act
        final RegionBSPTree2S tree = RegionBSPTree2S.from(Arrays.asList(
                    EQUATOR.arc(Point2S.PLUS_I, Point2S.PLUS_J),
                    X_MERIDIAN.arc(Point2S.PLUS_K, Point2S.PLUS_I),
                    Y_MERIDIAN.arc(Point2S.PLUS_J, Point2S.PLUS_K)
                ));

        // assert
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testFrom_boundaries_2_oe() {
        // act
        final RegionBSPTree2S tree = RegionBSPTree2S.from(Arrays.asList(
                    EQUATOR.arc(Point2S.PLUS_I, Point2S.PLUS_J),
                    X_MERIDIAN.arc(Point2S.PLUS_K, Point2S.PLUS_I),
                    Y_MERIDIAN.arc(Point2S.PLUS_J, Point2S.PLUS_K)
                ));

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testFrom_boundaries_3_oe() {
        // act
        final RegionBSPTree2S tree = RegionBSPTree2S.from(Arrays.asList(
                    EQUATOR.arc(Point2S.PLUS_I, Point2S.PLUS_J),
                    X_MERIDIAN.arc(Point2S.PLUS_K, Point2S.PLUS_I),
                    Y_MERIDIAN.arc(Point2S.PLUS_J, Point2S.PLUS_K)
                ));

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.OUTSIDE, tree.getRoot().getLocation());
    }

    @Test
    void testFrom_boundaries_fullIsTrue_1_oe() {
        // act
        final RegionBSPTree2S tree = RegionBSPTree2S.from(Arrays.asList(
                    EQUATOR.arc(Point2S.PLUS_I, Point2S.PLUS_J),
                    X_MERIDIAN.arc(Point2S.PLUS_K, Point2S.PLUS_I),
                    Y_MERIDIAN.arc(Point2S.PLUS_J, Point2S.PLUS_K)
                ), true);

        // assert
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testFrom_boundaries_fullIsTrue_2_oe() {
        // act
        final RegionBSPTree2S tree = RegionBSPTree2S.from(Arrays.asList(
                    EQUATOR.arc(Point2S.PLUS_I, Point2S.PLUS_J),
                    X_MERIDIAN.arc(Point2S.PLUS_K, Point2S.PLUS_I),
                    Y_MERIDIAN.arc(Point2S.PLUS_J, Point2S.PLUS_K)
                ), true);

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testFrom_boundaries_fullIsTrue_3_oe() {
        // act
        final RegionBSPTree2S tree = RegionBSPTree2S.from(Arrays.asList(
                    EQUATOR.arc(Point2S.PLUS_I, Point2S.PLUS_J),
                    X_MERIDIAN.arc(Point2S.PLUS_K, Point2S.PLUS_I),
                    Y_MERIDIAN.arc(Point2S.PLUS_J, Point2S.PLUS_K)
                ), true);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(RegionLocation.INSIDE, tree.getRoot().getLocation());
    }

    @Test
    void testCopy_1_oe() {
        // arrange
        final RegionBSPTree2S tree = new RegionBSPTree2S(true);
        tree.getRoot().cut(EQUATOR);

        // act
        final RegionBSPTree2S copy = tree.copy();

        // assert
        Assertions.assertNotSame(tree, copy);
    }

    @Test
    void testCopy_2_oe() {
        // arrange
        final RegionBSPTree2S tree = new RegionBSPTree2S(true);
        tree.getRoot().cut(EQUATOR);

        // act
        final RegionBSPTree2S copy = tree.copy();

        // assert
        // removed other assertion
        Assertions.assertEquals(3, copy.count());
    }

    @Test
    void testBoundaries_1_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();
        insertPositiveQuadrant(tree);

        // act
        final List<GreatArc> arcs = new ArrayList<>();
        tree.boundaries().forEach(arcs::add);

        // assert
        Assertions.assertEquals(3, arcs.size());
    }

    @Test
    void testGetBoundaries_1_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();
        insertPositiveQuadrant(tree);

        // act
        final List<GreatArc> arcs = tree.getBoundaries();

        // assert
        Assertions.assertEquals(3, arcs.size());
    }

    @Test
    void testBoundaryStream_1_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();
        insertPositiveQuadrant(tree);

        // act
        final List<GreatArc> arcs = tree.boundaryStream().collect(Collectors.toList());

        // assert
        Assertions.assertEquals(3, arcs.size());
    }

    @Test
    void testBoundaryStream_noBoundaries_1_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();

        // act
        final List<GreatArc> arcs = tree.boundaryStream().collect(Collectors.toList());

        // assert
        Assertions.assertEquals(0, arcs.size());
    }

    @Test
    void testToList_fullAndEmpty_1_oe() {
        // act/assert
        Assertions.assertEquals(0, RegionBSPTree2S.full().toList().count());
    }

    @Test
    void testToList_fullAndEmpty_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertEquals(0, RegionBSPTree2S.empty().toList().count());
    }

    @Test
    void testToList_1_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();
        insertPositiveQuadrant(tree);

        // act
        final BoundaryList2S list = tree.toList();

        // assert
        Assertions.assertEquals(3, list.count());
    }

    @Test
    void testToList_2_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();
        insertPositiveQuadrant(tree);

        // act
        final BoundaryList2S list = tree.toList();

        // assert
        // removed other assertion
        Assertions.assertEquals(0.5 * Math.PI, list.toTree().getSize(), TEST_EPS);
    }

    @Test
    void testToTree_returnsSameInstance_1_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();
        insertPositiveQuadrant(tree);

        // act/assert
        Assertions.assertSame(tree, tree.toTree());
    }

    @Test
    void testGetBoundaryPaths_cachesResult_1_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();
        insertPositiveQuadrant(tree);

        // act
        final List<GreatArcPath> a = tree.getBoundaryPaths();
        final List<GreatArcPath> b = tree.getBoundaryPaths();

        // assert
        Assertions.assertSame(a, b);
    }

    @Test
    void testGetBoundaryPaths_recomputesResultOnChange_1_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();
        tree.insert(EQUATOR.span());

        // act
        final List<GreatArcPath> a = tree.getBoundaryPaths();
        tree.insert(X_MERIDIAN.span());
        final List<GreatArcPath> b = tree.getBoundaryPaths();

        // assert
        Assertions.assertNotSame(a, b);
    }

    @Test
    void testGetBoundaryPaths_isUnmodifiable_1_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();
        tree.insert(EQUATOR.span());

        // act/assert
        try {
    tree.getBoundaryPaths().add(GreatArcPath.empty());
    fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    void testToConvex_full_1_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.full();

        // act
        final List<ConvexArea2S> result = tree.toConvex();

        // assert
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void testToConvex_full_2_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.full();

        // act
        final List<ConvexArea2S> result = tree.toConvex();

        // assert
        // removed other assertion
        Assertions.assertTrue(result.get(0).isFull());
    }

    @Test
    void testToConvex_empty_1_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();

        // act
        final List<ConvexArea2S> result = tree.toConvex();

        // assert
        Assertions.assertEquals(0, result.size());
    }

    @Test
    void testToConvex_doubleLune_1_oe() {
        // arrange
        final RegionBSPTree2S tree = GreatArcPath.builder(TEST_PRECISION)
                .append(EQUATOR.arc(0,  Math.PI))
                .append(X_MERIDIAN.arc(Math.PI, 0))
                .append(EQUATOR.reverse().arc(0, Math.PI))
                .append(X_MERIDIAN.reverse().arc(Math.PI, 0))
                .build()
                .toTree();

        // act
        final List<ConvexArea2S> result = tree.toConvex();

        // assert
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testToConvex_doubleLune_2_oe() {
        // arrange
        final RegionBSPTree2S tree = GreatArcPath.builder(TEST_PRECISION)
                .append(EQUATOR.arc(0,  Math.PI))
                .append(X_MERIDIAN.arc(Math.PI, 0))
                .append(EQUATOR.reverse().arc(0, Math.PI))
                .append(X_MERIDIAN.reverse().arc(Math.PI, 0))
                .build()
                .toTree();

        // act
        final List<ConvexArea2S> result = tree.toConvex();

        // assert
        // removed other assertion

        final double size = result.stream().mapToDouble(ConvexArea2S::getSize).sum();
        Assertions.assertEquals(Angle.TWO_PI, size, TEST_EPS);
    }

    @Test
    void testToConvex_doubleLune_complement_1_oe() {
        // arrange
        final RegionBSPTree2S tree = GreatArcPath.builder(TEST_PRECISION)
                .append(EQUATOR.arc(0,  Math.PI))
                .append(X_MERIDIAN.arc(Math.PI, 0))
                .append(EQUATOR.reverse().arc(0, Math.PI))
                .append(X_MERIDIAN.reverse().arc(Math.PI, 0))
                .build()
                .toTree();

        // act
        final List<ConvexArea2S> result = tree.toConvex();

        // assert
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testToConvex_doubleLune_complement_2_oe() {
        // arrange
        final RegionBSPTree2S tree = GreatArcPath.builder(TEST_PRECISION)
                .append(EQUATOR.arc(0,  Math.PI))
                .append(X_MERIDIAN.arc(Math.PI, 0))
                .append(EQUATOR.reverse().arc(0, Math.PI))
                .append(X_MERIDIAN.reverse().arc(Math.PI, 0))
                .build()
                .toTree();

        // act
        final List<ConvexArea2S> result = tree.toConvex();

        // assert
        // removed other assertion

        final double size = result.stream().mapToDouble(ConvexArea2S::getSize).sum();
        Assertions.assertEquals(Angle.TWO_PI, size, TEST_EPS);
    }

    @Test
    void testProject_noBoundaries_1_oe() {
        // act/assert
        Assertions.assertNull(RegionBSPTree2S.empty().project(Point2S.PLUS_I));
    }

    @Test
    void testProject_noBoundaries_2_oe() {
        // act/assert
        // removed other assertion
        Assertions.assertNull(RegionBSPTree2S.full().project(Point2S.PLUS_I));
    }

    @Test
    void testGeometricProperties_full_1_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.full();

        // act/assert
        Assertions.assertEquals(4 * Math.PI, tree.getSize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_full_2_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.full();

        // act/assert
        // removed other assertion
        Assertions.assertNull(tree.getCentroid());
    }

    @Test
    void testGeometricProperties_full_3_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.full();

        // act/assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_full_4_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.full();

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        Assertions.assertEquals(0, tree.getBoundaries().size());
    }

    @Test
    void testGeometricProperties_full_5_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.full();

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0, tree.getBoundaryPaths().size());
    }

    @Test
    void testGeometricProperties_empty_1_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();

        // act/assert
        Assertions.assertEquals(0, tree.getSize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_empty_2_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();

        // act/assert
        // removed other assertion
        Assertions.assertNull(tree.getCentroid());
    }

    @Test
    void testGeometricProperties_empty_3_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();

        // act/assert
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_empty_4_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        Assertions.assertEquals(0, tree.getBoundaries().size());
    }

    @Test
    void testGeometricProperties_empty_5_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();

        // act/assert
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(0, tree.getBoundaryPaths().size());
    }

    @Test
    void testGeometricProperties_halfSpace_1_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.full();
        tree.getRoot().cut(EQUATOR);

        // act/assert
        Assertions.assertEquals(Angle.TWO_PI, tree.getSize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_halfSpace_2_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.full();
        tree.getRoot().cut(EQUATOR);

        // act/assert
        // removed other assertion
        Assertions.assertEquals(Angle.TWO_PI, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_halfSpace_4_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.full();
        tree.getRoot().cut(EQUATOR);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        checkCentroidConsistency(tree);

        final List<GreatArc> arcs = tree.getBoundaries();
        Assertions.assertEquals(1, arcs.size());
    }

    @Test
    void testGeometricProperties_halfSpace_5_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.full();
        tree.getRoot().cut(EQUATOR);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        checkCentroidConsistency(tree);

        final List<GreatArc> arcs = tree.getBoundaries();
        // removed other assertion

        final GreatArc arc = arcs.get(0);
        Assertions.assertSame(EQUATOR, arc.getCircle());
    }

    @Test
    void testGeometricProperties_halfSpace_6_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.full();
        tree.getRoot().cut(EQUATOR);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        checkCentroidConsistency(tree);

        final List<GreatArc> arcs = tree.getBoundaries();
        // removed other assertion

        final GreatArc arc = arcs.get(0);
        // removed other assertion
        Assertions.assertNull(arc.getStartPoint());
    }

    @Test
    void testGeometricProperties_halfSpace_7_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.full();
        tree.getRoot().cut(EQUATOR);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        checkCentroidConsistency(tree);

        final List<GreatArc> arcs = tree.getBoundaries();
        // removed other assertion

        final GreatArc arc = arcs.get(0);
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(arc.getEndPoint());
    }

    @Test
    void testGeometricProperties_halfSpace_8_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.full();
        tree.getRoot().cut(EQUATOR);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        checkCentroidConsistency(tree);

        final List<GreatArc> arcs = tree.getBoundaries();
        // removed other assertion

        final GreatArc arc = arcs.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<GreatArcPath> paths = tree.getBoundaryPaths();
        Assertions.assertEquals(1, paths.size());
    }

    @Test
    void testGeometricProperties_halfSpace_9_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.full();
        tree.getRoot().cut(EQUATOR);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        checkCentroidConsistency(tree);

        final List<GreatArc> arcs = tree.getBoundaries();
        // removed other assertion

        final GreatArc arc = arcs.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<GreatArcPath> paths = tree.getBoundaryPaths();
        // removed other assertion

        final GreatArcPath path = paths.get(0);
        Assertions.assertEquals(1, path.getArcs().size());
    }

    @Test
    void testGeometricProperties_halfSpace_10_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.full();
        tree.getRoot().cut(EQUATOR);

        // act/assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        checkCentroidConsistency(tree);

        final List<GreatArc> arcs = tree.getBoundaries();
        // removed other assertion

        final GreatArc arc = arcs.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<GreatArcPath> paths = tree.getBoundaryPaths();
        // removed other assertion

        final GreatArcPath path = paths.get(0);
        // removed other assertion
        Assertions.assertTrue(path.getArcs().get(0).isFull());
    }

    @Test
    void testGeometricProperties_doubleLune_1_oe() {
        // act
        final RegionBSPTree2S tree = GreatArcPath.builder(TEST_PRECISION)
                .append(EQUATOR.arc(0,  Math.PI))
                .append(X_MERIDIAN.arc(Math.PI, 0))
                .append(EQUATOR.reverse().arc(0, Math.PI))
                .append(X_MERIDIAN.reverse().arc(Math.PI, 0))
                .build()
                .toTree();

        // assert
        Assertions.assertEquals(2 * Math.PI, tree.getSize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_doubleLune_2_oe() {
        // act
        final RegionBSPTree2S tree = GreatArcPath.builder(TEST_PRECISION)
                .append(EQUATOR.arc(0,  Math.PI))
                .append(X_MERIDIAN.arc(Math.PI, 0))
                .append(EQUATOR.reverse().arc(0, Math.PI))
                .append(X_MERIDIAN.reverse().arc(Math.PI, 0))
                .build()
                .toTree();

        // assert
        // removed other assertion
        Assertions.assertEquals(4 * Math.PI, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_doubleLune_3_oe() {
        // act
        final RegionBSPTree2S tree = GreatArcPath.builder(TEST_PRECISION)
                .append(EQUATOR.arc(0,  Math.PI))
                .append(X_MERIDIAN.arc(Math.PI, 0))
                .append(EQUATOR.reverse().arc(0, Math.PI))
                .append(X_MERIDIAN.reverse().arc(Math.PI, 0))
                .build()
                .toTree();

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(tree.getCentroid());
    }

    @Test
    void testGeometricProperties_doubleLune_4_oe() {
        // act
        final RegionBSPTree2S tree = GreatArcPath.builder(TEST_PRECISION)
                .append(EQUATOR.arc(0,  Math.PI))
                .append(X_MERIDIAN.arc(Math.PI, 0))
                .append(EQUATOR.reverse().arc(0, Math.PI))
                .append(X_MERIDIAN.reverse().arc(Math.PI, 0))
                .build()
                .toTree();

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final List<GreatArcPath> paths = tree.getBoundaryPaths();
        Assertions.assertEquals(2, paths.size());
    }

    @Test
    void testGeometricProperties_quadrant_1_oe() {
        // act
        final RegionBSPTree2S tree = GreatArcPath.builder(TEST_PRECISION)
                .appendVertices(Point2S.MINUS_K, Point2S.PLUS_I, Point2S.MINUS_J)
                .close()
                .toTree();

        // assert
        Assertions.assertEquals(0.5 * Math.PI, tree.getSize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_quadrant_2_oe() {
        // act
        final RegionBSPTree2S tree = GreatArcPath.builder(TEST_PRECISION)
                .appendVertices(Point2S.MINUS_K, Point2S.PLUS_I, Point2S.MINUS_J)
                .close()
                .toTree();

        // assert
        // removed other assertion
        Assertions.assertEquals(1.5 * Math.PI, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_quadrant_4_oe() {
        // act
        final RegionBSPTree2S tree = GreatArcPath.builder(TEST_PRECISION)
                .appendVertices(Point2S.MINUS_K, Point2S.PLUS_I, Point2S.MINUS_J)
                .close()
                .toTree();

        // assert
        // removed other assertion
        // removed other assertion

        final Point2S center = Point2S.from(Point2S.MINUS_K.getVector()
                .add(Point2S.PLUS_I.getVector())
                .add(Point2S.MINUS_J.getVector()));
        // removed other assertion

        checkCentroidConsistency(tree);

        final List<GreatArcPath> paths = tree.getBoundaryPaths();
        Assertions.assertEquals(1, paths.size());
    }

    @Test
    void testGeometricProperties_quadrant_complement_1_oe() {
        // arrange
        final RegionBSPTree2S tree = GreatArcPath.builder(TEST_PRECISION)
                .appendVertices(Point2S.MINUS_K, Point2S.PLUS_I, Point2S.MINUS_J)
                .close()
                .toTree();

        // act
        tree.complement();

        // assert
        Assertions.assertEquals(3.5 * Math.PI, tree.getSize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_quadrant_complement_2_oe() {
        // arrange
        final RegionBSPTree2S tree = GreatArcPath.builder(TEST_PRECISION)
                .appendVertices(Point2S.MINUS_K, Point2S.PLUS_I, Point2S.MINUS_J)
                .close()
                .toTree();

        // act
        tree.complement();

        // assert
        // removed other assertion
        Assertions.assertEquals(1.5 * Math.PI, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_quadrant_complement_4_oe() {
        // arrange
        final RegionBSPTree2S tree = GreatArcPath.builder(TEST_PRECISION)
                .appendVertices(Point2S.MINUS_K, Point2S.PLUS_I, Point2S.MINUS_J)
                .close()
                .toTree();

        // act
        tree.complement();

        // assert
        // removed other assertion
        // removed other assertion

        final Point2S center = Point2S.from(Point2S.MINUS_K.getVector()
                .add(Point2S.PLUS_I.getVector())
                .add(Point2S.MINUS_J.getVector()));
        // removed other assertion

        checkCentroidConsistency(tree);

        final List<GreatArcPath> paths = tree.getBoundaryPaths();
        Assertions.assertEquals(1, paths.size());
    }

    @Test
    void testGeometricProperties_polygonWithHole_1_oe() {
        // arrange
        final Point2S center = Point2S.of(0.5, 2);

        final double outerRadius = 1;
        final double innerRadius = 0.5;

        final RegionBSPTree2S outer = buildDiamond(center, outerRadius);
        final RegionBSPTree2S inner = buildDiamond(center, innerRadius);

        // rotate the inner diamond a quarter turn to become a square
        inner.transform(Transform2S.createRotation(center, 0.25 * Math.PI));

        // act
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();
        tree.difference(outer, inner);

        // assert
        final double area = 4 * (rightTriangleArea(outerRadius, outerRadius) - rightTriangleArea(innerRadius, innerRadius));
        Assertions.assertEquals(area, tree.getSize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_polygonWithHole_2_oe() {
        // arrange
        final Point2S center = Point2S.of(0.5, 2);

        final double outerRadius = 1;
        final double innerRadius = 0.5;

        final RegionBSPTree2S outer = buildDiamond(center, outerRadius);
        final RegionBSPTree2S inner = buildDiamond(center, innerRadius);

        // rotate the inner diamond a quarter turn to become a square
        inner.transform(Transform2S.createRotation(center, 0.25 * Math.PI));

        // act
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();
        tree.difference(outer, inner);

        // assert
        final double area = 4 * (rightTriangleArea(outerRadius, outerRadius) - rightTriangleArea(innerRadius, innerRadius));
        // removed other assertion

        final double outerSideLength = sphericalHypot(outerRadius, outerRadius);
        final double innerSideLength = sphericalHypot(innerRadius, innerRadius);
        final double boundarySize = 4 * (outerSideLength + innerSideLength);
        Assertions.assertEquals(boundarySize, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_polygonWithHole_small_1_oe() {
        // arrange
        final Point2S center = Point2S.of(0.5, 2);

        final double outerRadius = 1e-5;
        final double innerRadius = 1e-7;

        final RegionBSPTree2S outer = buildDiamond(center, outerRadius);
        final RegionBSPTree2S inner = buildDiamond(center, innerRadius);

        // rotate the inner diamond a quarter turn to become a square
        inner.transform(Transform2S.createRotation(center, 0.25 * Math.PI));

        // act
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();
        tree.difference(outer, inner);

        // assert

        // use Euclidean approximations of the area and boundary size since those will be more accurate
        // at these sizes
        final double area = (2 * outerRadius * outerRadius) - (2 * innerRadius * innerRadius);
        Assertions.assertEquals(area, tree.getSize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_polygonWithHole_small_2_oe() {
        // arrange
        final Point2S center = Point2S.of(0.5, 2);

        final double outerRadius = 1e-5;
        final double innerRadius = 1e-7;

        final RegionBSPTree2S outer = buildDiamond(center, outerRadius);
        final RegionBSPTree2S inner = buildDiamond(center, innerRadius);

        // rotate the inner diamond a quarter turn to become a square
        inner.transform(Transform2S.createRotation(center, 0.25 * Math.PI));

        // act
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();
        tree.difference(outer, inner);

        // assert

        // use Euclidean approximations of the area and boundary size since those will be more accurate
        // at these sizes
        final double area = (2 * outerRadius * outerRadius) - (2 * innerRadius * innerRadius);
        // removed other assertion

        final double outerSideLength = Math.hypot(outerRadius, outerRadius);
        final double innerSideLength = Math.hypot(innerRadius, innerRadius);
        final double boundarySize = 4 * (outerSideLength + innerSideLength);
        Assertions.assertEquals(boundarySize, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_polygonWithHole_complex_1_oe() {
        // arrange
        final Point2S center = Point2S.of(0.5, 2);

        final double outerRadius = 2;
        final double midRadius = 1;
        final double innerRadius = 0.5;

        final RegionBSPTree2S outer = buildDiamond(center, outerRadius);
        final RegionBSPTree2S mid = buildDiamond(center, midRadius);
        final RegionBSPTree2S inner = buildDiamond(center, innerRadius);

        // rotate the middle diamond a quarter turn to become a square
        mid.transform(Transform2S.createRotation(center, 0.25 * Math.PI));

        // act
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();
        tree.difference(outer, mid);
        tree.union(inner);
        tree.complement();

        // assert
        // compute the area, adjusting the first computation for the fact that the triangles comprising the
        // outer diamond have lengths greater than pi/2
        final double nonComplementedArea = 4 * ((Math.PI - rightTriangleArea(outerRadius, outerRadius) -
                rightTriangleArea(midRadius, midRadius) + rightTriangleArea(innerRadius, innerRadius)));
        final double area = (4 * Math.PI) - nonComplementedArea;
        Assertions.assertEquals(area, tree.getSize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_polygonWithHole_complex_2_oe() {
        // arrange
        final Point2S center = Point2S.of(0.5, 2);

        final double outerRadius = 2;
        final double midRadius = 1;
        final double innerRadius = 0.5;

        final RegionBSPTree2S outer = buildDiamond(center, outerRadius);
        final RegionBSPTree2S mid = buildDiamond(center, midRadius);
        final RegionBSPTree2S inner = buildDiamond(center, innerRadius);

        // rotate the middle diamond a quarter turn to become a square
        mid.transform(Transform2S.createRotation(center, 0.25 * Math.PI));

        // act
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();
        tree.difference(outer, mid);
        tree.union(inner);
        tree.complement();

        // assert
        // compute the area, adjusting the first computation for the fact that the triangles comprising the
        // outer diamond have lengths greater than pi/2
        final double nonComplementedArea = 4 * ((Math.PI - rightTriangleArea(outerRadius, outerRadius) -
                rightTriangleArea(midRadius, midRadius) + rightTriangleArea(innerRadius, innerRadius)));
        final double area = (4 * Math.PI) - nonComplementedArea;
        // removed other assertion

        final double outerSideLength = sphericalHypot(outerRadius, outerRadius);
        final double midSideLength = sphericalHypot(midRadius, midRadius);
        final double innerSideLength = sphericalHypot(innerRadius, innerRadius);
        final double boundarySize = 4 * (outerSideLength + midSideLength + innerSideLength);
        Assertions.assertEquals(boundarySize, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_smallRightTriangle_1_oe() {
        // arrange
        final double azOffset = 1e-5;
        final double polarOffset = 1e-6;

        final double minAz = 0;
        final double maxAz = minAz + azOffset;
        final double maxPolar = Angle.PI_OVER_TWO;
        final double minPolar = maxPolar - polarOffset;

        final Point2S p0 = Point2S.of(minAz, maxPolar);
        final Point2S p1 = Point2S.of(maxAz, maxPolar);
        final Point2S p2 = Point2S.of(maxAz, minPolar);

        // act
        final RegionBSPTree2S tree = GreatArcPath.fromVertexLoop(Arrays.asList(p0, p1, p2), TEST_PRECISION)
                .toTree();

        // assert

        // use Euclidean approximations of the area and boundary size since those will be more accurate
        // at these sizes
        final double expectedArea = 0.5 * azOffset * polarOffset;
        Assertions.assertEquals(expectedArea, tree.getSize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_smallRightTriangle_2_oe() {
        // arrange
        final double azOffset = 1e-5;
        final double polarOffset = 1e-6;

        final double minAz = 0;
        final double maxAz = minAz + azOffset;
        final double maxPolar = Angle.PI_OVER_TWO;
        final double minPolar = maxPolar - polarOffset;

        final Point2S p0 = Point2S.of(minAz, maxPolar);
        final Point2S p1 = Point2S.of(maxAz, maxPolar);
        final Point2S p2 = Point2S.of(maxAz, minPolar);

        // act
        final RegionBSPTree2S tree = GreatArcPath.fromVertexLoop(Arrays.asList(p0, p1, p2), TEST_PRECISION)
                .toTree();

        // assert

        // use Euclidean approximations of the area and boundary size since those will be more accurate
        // at these sizes
        final double expectedArea = 0.5 * azOffset * polarOffset;
        // removed other assertion

        final double expectedBoundarySize = azOffset + polarOffset + Math.hypot(azOffset, polarOffset);
        Assertions.assertEquals(expectedBoundarySize, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_smallRightTriangle_3_oe() {
        // arrange
        final double azOffset = 1e-5;
        final double polarOffset = 1e-6;

        final double minAz = 0;
        final double maxAz = minAz + azOffset;
        final double maxPolar = Angle.PI_OVER_TWO;
        final double minPolar = maxPolar - polarOffset;

        final Point2S p0 = Point2S.of(minAz, maxPolar);
        final Point2S p1 = Point2S.of(maxAz, maxPolar);
        final Point2S p2 = Point2S.of(maxAz, minPolar);

        // act
        final RegionBSPTree2S tree = GreatArcPath.fromVertexLoop(Arrays.asList(p0, p1, p2), TEST_PRECISION)
                .toTree();

        // assert

        // use Euclidean approximations of the area and boundary size since those will be more accurate
        // at these sizes
        final double expectedArea = 0.5 * azOffset * polarOffset;
        // removed other assertion

        final double expectedBoundarySize = azOffset + polarOffset + Math.hypot(azOffset, polarOffset);
        // removed other assertion

        Assertions.assertTrue(tree.contains(tree.getCentroid()));
    }

    @Test
    void testGeometricProperties_equalAndOppositeRegions_1_oe() {
        // arrange
        final Point2S center = Point2S.PLUS_I;
        final double radius = 0.25 * Math.PI;

        final RegionBSPTree2S a = buildDiamond(center, radius);
        final RegionBSPTree2S b = buildDiamond(center.antipodal(), radius);

        // act
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();
        tree.union(a, b);

        // assert
        final double area = 8 * rightTriangleArea(radius, radius);
        Assertions.assertEquals(area, tree.getSize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_equalAndOppositeRegions_2_oe() {
        // arrange
        final Point2S center = Point2S.PLUS_I;
        final double radius = 0.25 * Math.PI;

        final RegionBSPTree2S a = buildDiamond(center, radius);
        final RegionBSPTree2S b = buildDiamond(center.antipodal(), radius);

        // act
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();
        tree.union(a, b);

        // assert
        final double area = 8 * rightTriangleArea(radius, radius);
        // removed other assertion

        final double boundarySize = 8 * sphericalHypot(radius, radius);
        Assertions.assertEquals(boundarySize, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testGeometricProperties_equalAndOppositeRegions_3_oe() {
        // arrange
        final Point2S center = Point2S.PLUS_I;
        final double radius = 0.25 * Math.PI;

        final RegionBSPTree2S a = buildDiamond(center, radius);
        final RegionBSPTree2S b = buildDiamond(center.antipodal(), radius);

        // act
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();
        tree.union(a, b);

        // assert
        final double area = 8 * rightTriangleArea(radius, radius);
        // removed other assertion

        final double boundarySize = 8 * sphericalHypot(radius, radius);
        // removed other assertion

        // should be null since no unique centroid exists
        Assertions.assertNull(tree.getCentroid());
    }

    @Test
    void testSplit_both_1_oe() {
        // arrange
        final GreatCircle c1 = GreatCircles.fromPole(Vector3D.Unit.MINUS_X, TEST_PRECISION);
        final GreatCircle c2 = GreatCircles.fromPole(Vector3D.of(1, 1, 0), TEST_PRECISION);

        final RegionBSPTree2S tree = ConvexArea2S.fromBounds(c1, c2).toTree();

        final GreatCircle splitter = GreatCircles.fromPole(Vector3D.of(-1, 0, 1), TEST_PRECISION);

        // act
        final Split<RegionBSPTree2S> split = tree.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.BOTH, split.getLocation());
    }

    @Test
    void testSplit_both_2_oe() {
        // arrange
        final GreatCircle c1 = GreatCircles.fromPole(Vector3D.Unit.MINUS_X, TEST_PRECISION);
        final GreatCircle c2 = GreatCircles.fromPole(Vector3D.of(1, 1, 0), TEST_PRECISION);

        final RegionBSPTree2S tree = ConvexArea2S.fromBounds(c1, c2).toTree();

        final GreatCircle splitter = GreatCircles.fromPole(Vector3D.of(-1, 0, 1), TEST_PRECISION);

        // act
        final Split<RegionBSPTree2S> split = tree.split(splitter);

        // assert
        // removed other assertion

        final Point2S p1 = c1.intersection(splitter);
        final Point2S p2 = splitter.intersection(c2);

        final RegionBSPTree2S minus = split.getMinus();
        final List<GreatArcPath> minusPaths = minus.getBoundaryPaths();
        Assertions.assertEquals(1, minusPaths.size());
    }

    @Test
    void testSplit_both_4_oe() {
        // arrange
        final GreatCircle c1 = GreatCircles.fromPole(Vector3D.Unit.MINUS_X, TEST_PRECISION);
        final GreatCircle c2 = GreatCircles.fromPole(Vector3D.of(1, 1, 0), TEST_PRECISION);

        final RegionBSPTree2S tree = ConvexArea2S.fromBounds(c1, c2).toTree();

        final GreatCircle splitter = GreatCircles.fromPole(Vector3D.of(-1, 0, 1), TEST_PRECISION);

        // act
        final Split<RegionBSPTree2S> split = tree.split(splitter);

        // assert
        // removed other assertion

        final Point2S p1 = c1.intersection(splitter);
        final Point2S p2 = splitter.intersection(c2);

        final RegionBSPTree2S minus = split.getMinus();
        final List<GreatArcPath> minusPaths = minus.getBoundaryPaths();
        // removed other assertion
        // removed other assertion

        final RegionBSPTree2S plus = split.getPlus();
        final List<GreatArcPath> plusPaths = plus.getBoundaryPaths();
        Assertions.assertEquals(1, plusPaths.size());
    }

    @Test
    void testSplit_both_6_oe() {
        // arrange
        final GreatCircle c1 = GreatCircles.fromPole(Vector3D.Unit.MINUS_X, TEST_PRECISION);
        final GreatCircle c2 = GreatCircles.fromPole(Vector3D.of(1, 1, 0), TEST_PRECISION);

        final RegionBSPTree2S tree = ConvexArea2S.fromBounds(c1, c2).toTree();

        final GreatCircle splitter = GreatCircles.fromPole(Vector3D.of(-1, 0, 1), TEST_PRECISION);

        // act
        final Split<RegionBSPTree2S> split = tree.split(splitter);

        // assert
        // removed other assertion

        final Point2S p1 = c1.intersection(splitter);
        final Point2S p2 = splitter.intersection(c2);

        final RegionBSPTree2S minus = split.getMinus();
        final List<GreatArcPath> minusPaths = minus.getBoundaryPaths();
        // removed other assertion
        // removed other assertion

        final RegionBSPTree2S plus = split.getPlus();
        final List<GreatArcPath> plusPaths = plus.getBoundaryPaths();
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(tree.getSize(), minus.getSize() + plus.getSize(), TEST_EPS);
    }

    @Test
    void testSplit_minus_1_oe() {
        // arrange
        final RegionBSPTree2S tree = ConvexArea2S.fromVertexLoop(Arrays.asList(
                    Point2S.PLUS_I, Point2S.PLUS_K, Point2S.MINUS_J
                ), TEST_PRECISION).toTree();

        final GreatCircle splitter = GreatCircles.fromPole(Vector3D.of(0, -1, 1), TEST_PRECISION);

        // act
        final Split<RegionBSPTree2S> split = tree.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.MINUS, split.getLocation());
    }

    @Test
    void testSplit_minus_2_oe() {
        // arrange
        final RegionBSPTree2S tree = ConvexArea2S.fromVertexLoop(Arrays.asList(
                    Point2S.PLUS_I, Point2S.PLUS_K, Point2S.MINUS_J
                ), TEST_PRECISION).toTree();

        final GreatCircle splitter = GreatCircles.fromPole(Vector3D.of(0, -1, 1), TEST_PRECISION);

        // act
        final Split<RegionBSPTree2S> split = tree.split(splitter);

        // assert
        // removed other assertion

        final RegionBSPTree2S minus = split.getMinus();
        Assertions.assertNotSame(tree, minus);
    }

    @Test
    void testSplit_minus_3_oe() {
        // arrange
        final RegionBSPTree2S tree = ConvexArea2S.fromVertexLoop(Arrays.asList(
                    Point2S.PLUS_I, Point2S.PLUS_K, Point2S.MINUS_J
                ), TEST_PRECISION).toTree();

        final GreatCircle splitter = GreatCircles.fromPole(Vector3D.of(0, -1, 1), TEST_PRECISION);

        // act
        final Split<RegionBSPTree2S> split = tree.split(splitter);

        // assert
        // removed other assertion

        final RegionBSPTree2S minus = split.getMinus();
        // removed other assertion
        Assertions.assertEquals(tree.getSize(), minus.getSize(), TEST_EPS);
    }

    @Test
    void testSplit_minus_4_oe() {
        // arrange
        final RegionBSPTree2S tree = ConvexArea2S.fromVertexLoop(Arrays.asList(
                    Point2S.PLUS_I, Point2S.PLUS_K, Point2S.MINUS_J
                ), TEST_PRECISION).toTree();

        final GreatCircle splitter = GreatCircles.fromPole(Vector3D.of(0, -1, 1), TEST_PRECISION);

        // act
        final Split<RegionBSPTree2S> split = tree.split(splitter);

        // assert
        // removed other assertion

        final RegionBSPTree2S minus = split.getMinus();
        // removed other assertion
        // removed other assertion

        Assertions.assertNull(split.getPlus());
    }

    @Test
    void testSplit_plus_1_oe() {
        // arrange
        final RegionBSPTree2S tree = ConvexArea2S.fromVertexLoop(Arrays.asList(
                    Point2S.PLUS_I, Point2S.PLUS_K, Point2S.MINUS_J
                ), TEST_PRECISION).toTree();

        final GreatCircle splitter = GreatCircles.fromPole(Vector3D.of(0, 1, -1), TEST_PRECISION);

        // act
        final Split<RegionBSPTree2S> split = tree.split(splitter);

        // assert
        Assertions.assertEquals(SplitLocation.PLUS, split.getLocation());
    }

    @Test
    void testSplit_plus_2_oe() {
        // arrange
        final RegionBSPTree2S tree = ConvexArea2S.fromVertexLoop(Arrays.asList(
                    Point2S.PLUS_I, Point2S.PLUS_K, Point2S.MINUS_J
                ), TEST_PRECISION).toTree();

        final GreatCircle splitter = GreatCircles.fromPole(Vector3D.of(0, 1, -1), TEST_PRECISION);

        // act
        final Split<RegionBSPTree2S> split = tree.split(splitter);

        // assert
        // removed other assertion

        Assertions.assertNull(split.getMinus());
    }

    @Test
    void testSplit_plus_3_oe() {
        // arrange
        final RegionBSPTree2S tree = ConvexArea2S.fromVertexLoop(Arrays.asList(
                    Point2S.PLUS_I, Point2S.PLUS_K, Point2S.MINUS_J
                ), TEST_PRECISION).toTree();

        final GreatCircle splitter = GreatCircles.fromPole(Vector3D.of(0, 1, -1), TEST_PRECISION);

        // act
        final Split<RegionBSPTree2S> split = tree.split(splitter);

        // assert
        // removed other assertion

        // removed other assertion

        final RegionBSPTree2S plus = split.getPlus();
        Assertions.assertNotSame(tree, plus);
    }

    @Test
    void testSplit_plus_4_oe() {
        // arrange
        final RegionBSPTree2S tree = ConvexArea2S.fromVertexLoop(Arrays.asList(
                    Point2S.PLUS_I, Point2S.PLUS_K, Point2S.MINUS_J
                ), TEST_PRECISION).toTree();

        final GreatCircle splitter = GreatCircles.fromPole(Vector3D.of(0, 1, -1), TEST_PRECISION);

        // act
        final Split<RegionBSPTree2S> split = tree.split(splitter);

        // assert
        // removed other assertion

        // removed other assertion

        final RegionBSPTree2S plus = split.getPlus();
        // removed other assertion
        Assertions.assertEquals(tree.getSize(), plus.getSize(), TEST_EPS);
    }

    @Test
    void testTransform_1_oe() {
        // arrange
        final Transform2S t = Transform2S.createReflection(Point2S.PLUS_J);
        final RegionBSPTree2S tree = ConvexArea2S.fromVertexLoop(
                Arrays.asList(Point2S.PLUS_I, Point2S.PLUS_J, Point2S.PLUS_K), TEST_PRECISION).toTree();

        // act
        tree.transform(t);

        // assert
        Assertions.assertFalse(tree.isFull());
    }

    @Test
    void testTransform_2_oe() {
        // arrange
        final Transform2S t = Transform2S.createReflection(Point2S.PLUS_J);
        final RegionBSPTree2S tree = ConvexArea2S.fromVertexLoop(
                Arrays.asList(Point2S.PLUS_I, Point2S.PLUS_J, Point2S.PLUS_K), TEST_PRECISION).toTree();

        // act
        tree.transform(t);

        // assert
        // removed other assertion
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void testTransform_3_oe() {
        // arrange
        final Transform2S t = Transform2S.createReflection(Point2S.PLUS_J);
        final RegionBSPTree2S tree = ConvexArea2S.fromVertexLoop(
                Arrays.asList(Point2S.PLUS_I, Point2S.PLUS_J, Point2S.PLUS_K), TEST_PRECISION).toTree();

        // act
        tree.transform(t);

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(1.5 * Math.PI, tree.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testTransform_4_oe() {
        // arrange
        final Transform2S t = Transform2S.createReflection(Point2S.PLUS_J);
        final RegionBSPTree2S tree = ConvexArea2S.fromVertexLoop(
                Arrays.asList(Point2S.PLUS_I, Point2S.PLUS_J, Point2S.PLUS_K), TEST_PRECISION).toTree();

        // act
        tree.transform(t);

        // assert
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Angle.PI_OVER_TWO, tree.getSize(), TEST_EPS);
    }

    @Test
    void testRegionNode_getNodeRegion_1_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();

        final RegionNode2S root = tree.getRoot();
        final RegionNode2S minus = root.cut(EQUATOR).getMinus();
        final RegionNode2S minusPlus = minus.cut(X_MERIDIAN).getPlus();

        // act/assert
        final ConvexArea2S rootRegion = root.getNodeRegion();
        Assertions.assertEquals(4 * Math.PI, rootRegion.getSize(), TEST_EPS);
    }

    @Test
    void testRegionNode_getNodeRegion_2_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();

        final RegionNode2S root = tree.getRoot();
        final RegionNode2S minus = root.cut(EQUATOR).getMinus();
        final RegionNode2S minusPlus = minus.cut(X_MERIDIAN).getPlus();

        // act/assert
        final ConvexArea2S rootRegion = root.getNodeRegion();
        // removed other assertion
        Assertions.assertNull(rootRegion.getCentroid());
    }

    @Test
    void testRegionNode_getNodeRegion_3_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();

        final RegionNode2S root = tree.getRoot();
        final RegionNode2S minus = root.cut(EQUATOR).getMinus();
        final RegionNode2S minusPlus = minus.cut(X_MERIDIAN).getPlus();

        // act/assert
        final ConvexArea2S rootRegion = root.getNodeRegion();
        // removed other assertion
        // removed other assertion

        final ConvexArea2S minusRegion = minus.getNodeRegion();
        Assertions.assertEquals(2 * Math.PI, minusRegion.getSize(), TEST_EPS);
    }

    @Test
    void testRegionNode_getNodeRegion_5_oe() {
        // arrange
        final RegionBSPTree2S tree = RegionBSPTree2S.empty();

        final RegionNode2S root = tree.getRoot();
        final RegionNode2S minus = root.cut(EQUATOR).getMinus();
        final RegionNode2S minusPlus = minus.cut(X_MERIDIAN).getPlus();

        // act/assert
        final ConvexArea2S rootRegion = root.getNodeRegion();
        // removed other assertion
        // removed other assertion

        final ConvexArea2S minusRegion = minus.getNodeRegion();
        // removed other assertion
        // removed other assertion

        final ConvexArea2S minusPlusRegion = minusPlus.getNodeRegion();
        Assertions.assertEquals(Math.PI, minusPlusRegion.getSize(), TEST_EPS);
    }

    @Test
    void testGeographicMap_1_oe() {
        // arrange
        final RegionBSPTree2S continental = latLongToTree(TEST_PRECISION, new double[][] {
                {51.14850,  2.51357}, {50.94660,  1.63900}, {50.12717,  1.33876}, {49.34737, -0.98946},
                {49.77634, -1.93349}, {48.64442, -1.61651}, {48.90169, -3.29581}, {48.68416, -4.59234},
                {47.95495, -4.49155}, {47.57032, -2.96327}, {46.01491, -1.19379}, {44.02261, -1.38422},
                {43.42280, -1.90135}, {43.03401, -1.50277}, {42.34338,  1.82679}, {42.47301,  2.98599},
                {43.07520,  3.10041}, {43.39965,  4.55696}, {43.12889,  6.52924}, {43.69384,  7.43518},
                {44.12790,  7.54959}, {45.02851,  6.74995}, {45.33309,  7.09665}, {46.42967,  6.50009},
                {46.27298,  6.02260}, {46.72577,  6.03738}, {47.62058,  7.46675}, {49.01778,  8.09927},
                {49.20195,  6.65822}, {49.44266,  5.89775}, {49.98537,  4.79922}
            });
        final RegionBSPTree2S corsica = latLongToTree(TEST_PRECISION, new double[][] {
                {42.15249,  9.56001}, {43.00998,  9.39000}, {42.62812,  8.74600}, {42.25651,  8.54421},
                {41.58361,  8.77572}, {41.38000,  9.22975}
            });

        // act
        final RegionBSPTree2S france = RegionBSPTree2S.empty();
        france.union(continental, corsica);

        // assert
        Assertions.assertEquals(0.6316801448267251, france.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testGeographicMap_2_oe() {
        // arrange
        final RegionBSPTree2S continental = latLongToTree(TEST_PRECISION, new double[][] {
                {51.14850,  2.51357}, {50.94660,  1.63900}, {50.12717,  1.33876}, {49.34737, -0.98946},
                {49.77634, -1.93349}, {48.64442, -1.61651}, {48.90169, -3.29581}, {48.68416, -4.59234},
                {47.95495, -4.49155}, {47.57032, -2.96327}, {46.01491, -1.19379}, {44.02261, -1.38422},
                {43.42280, -1.90135}, {43.03401, -1.50277}, {42.34338,  1.82679}, {42.47301,  2.98599},
                {43.07520,  3.10041}, {43.39965,  4.55696}, {43.12889,  6.52924}, {43.69384,  7.43518},
                {44.12790,  7.54959}, {45.02851,  6.74995}, {45.33309,  7.09665}, {46.42967,  6.50009},
                {46.27298,  6.02260}, {46.72577,  6.03738}, {47.62058,  7.46675}, {49.01778,  8.09927},
                {49.20195,  6.65822}, {49.44266,  5.89775}, {49.98537,  4.79922}
            });
        final RegionBSPTree2S corsica = latLongToTree(TEST_PRECISION, new double[][] {
                {42.15249,  9.56001}, {43.00998,  9.39000}, {42.62812,  8.74600}, {42.25651,  8.54421},
                {41.58361,  8.77572}, {41.38000,  9.22975}
            });

        // act
        final RegionBSPTree2S france = RegionBSPTree2S.empty();
        france.union(continental, corsica);

        // assert
        // removed other assertion
        Assertions.assertEquals(0.013964220234478741, france.getSize(), TEST_EPS);
    }

    @Test
    void testCircleToPolygonSize_1_oe() {
        final double radius = 0.0001;
        final Point2S center = Point2S.of(1.0, 1.0);
        final int numPts = 200;

        // https://en.wikipedia.org/wiki/Spherical_cap
        final double ccwArea = 4.0 * Math.PI * Math.pow(Math.sin(radius / 2.0), 2.0);
        final double cwArea = 4.0 * Math.PI - ccwArea;

        final RegionBSPTree2S ccw = circleToPolygon(center, radius, numPts, false, TEST_PRECISION);
        Assertions.assertEquals(ccwArea, ccw.getSize(), TEST_EPS, "Counterclockwise size");
    }

    @Test
    void testCircleToPolygonSize_2_oe() {
        final double radius = 0.0001;
        final Point2S center = Point2S.of(1.0, 1.0);
        final int numPts = 200;

        // https://en.wikipedia.org/wiki/Spherical_cap
        final double ccwArea = 4.0 * Math.PI * Math.pow(Math.sin(radius / 2.0), 2.0);
        final double cwArea = 4.0 * Math.PI - ccwArea;

        final RegionBSPTree2S ccw = circleToPolygon(center, radius, numPts, false, TEST_PRECISION);
        // removed other assertion

        final RegionBSPTree2S cw = circleToPolygon(center, radius, numPts, true, TEST_PRECISION);
        Assertions.assertEquals(cwArea, cw.getSize(), TEST_EPS, "Clockwise size");
    }

    @Test
    void testCircleToPolygonBoundarySize_1_oe() {
        final double radius = 0.0001;
        final Point2S center = Point2S.of(1.0, 1.0);
        final int numPts = 200;

        // boundary size is independent from winding
        final double boundary = Angle.TWO_PI * Math.sin(radius);

        final RegionBSPTree2S ccw = circleToPolygon(center, radius, numPts, false, TEST_PRECISION);
        Assertions.assertEquals(boundary, ccw.getBoundarySize(), 1.0e-7, "Counterclockwise boundary size");
    }

    @Test
    void testCircleToPolygonBoundarySize_2_oe() {
        final double radius = 0.0001;
        final Point2S center = Point2S.of(1.0, 1.0);
        final int numPts = 200;

        // boundary size is independent from winding
        final double boundary = Angle.TWO_PI * Math.sin(radius);

        final RegionBSPTree2S ccw = circleToPolygon(center, radius, numPts, false, TEST_PRECISION);
        // removed other assertion

        final RegionBSPTree2S cw = circleToPolygon(center, radius, numPts, true, TEST_PRECISION);
        Assertions.assertEquals(boundary, cw.getBoundarySize(), 1.0e-7, "Clockwise boundary size");
    }

    @Test
    void testSmallCircleToPolygon_2_oe() {
        // arrange
        final double radius = 5.0e-8;
        final Point2S center = Point2S.of(0.5, 1.5);
        final int numPts = 100;

        // act
        final RegionBSPTree2S circle = circleToPolygon(center, radius, numPts, false, TEST_PRECISION);

        // assert
        // https://en.wikipedia.org/wiki/Spherical_cap
        final double area = 4.0 * Math.PI * Math.pow(Math.sin(radius / 2.0), 2.0);
        final double boundary = Angle.TWO_PI * Math.sin(radius);

        // removed other assertion
        Assertions.assertEquals(area, circle.getSize(), TEST_EPS);
    }

    @Test
    void testSmallCircleToPolygon_3_oe() {
        // arrange
        final double radius = 5.0e-8;
        final Point2S center = Point2S.of(0.5, 1.5);
        final int numPts = 100;

        // act
        final RegionBSPTree2S circle = circleToPolygon(center, radius, numPts, false, TEST_PRECISION);

        // assert
        // https://en.wikipedia.org/wiki/Spherical_cap
        final double area = 4.0 * Math.PI * Math.pow(Math.sin(radius / 2.0), 2.0);
        final double boundary = Angle.TWO_PI * Math.sin(radius);

        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(boundary, circle.getBoundarySize(), TEST_EPS);
    }

    @Test
    void testSmallGeographicalRectangle_2_oe() {
        // arrange
        final double[][] vertices = {
            {42.656216727628696, -70.61919768884546},
            {42.65612858998112, -70.61938607250165},
            {42.65579098923594, -70.61909615581666},
            {42.655879126692355, -70.61890777301083}
        };

        // act
        final RegionBSPTree2S rectangle = latLongToTree(TEST_PRECISION, vertices);

        // assert
        // approximate the centroid as average of vertices
        final double avgLat = Stream.of(vertices).mapToDouble(v -> v[0]).average().getAsDouble();
        final double avgLon = Stream.of(vertices).mapToDouble(v -> v[1]).average().getAsDouble();
        final Point2S expectedCentroid = latLongToPoint(avgLat, avgLon);

        // removed other assertion

        // expected results computed using GeographicLib (https://geographiclib.sourceforge.io/)
        Assertions.assertEquals(1.997213869978027E-11, rectangle.getSize(), TEST_EPS);
    }

    @Test
    void testSmallGeographicalRectangle_3_oe() {
        // arrange
        final double[][] vertices = {
            {42.656216727628696, -70.61919768884546},
            {42.65612858998112, -70.61938607250165},
            {42.65579098923594, -70.61909615581666},
            {42.655879126692355, -70.61890777301083}
        };

        // act
        final RegionBSPTree2S rectangle = latLongToTree(TEST_PRECISION, vertices);

        // assert
        // approximate the centroid as average of vertices
        final double avgLat = Stream.of(vertices).mapToDouble(v -> v[0]).average().getAsDouble();
        final double avgLon = Stream.of(vertices).mapToDouble(v -> v[1]).average().getAsDouble();
        final Point2S expectedCentroid = latLongToPoint(avgLat, avgLon);

        // removed other assertion

        // expected results computed using GeographicLib (https://geographiclib.sourceforge.io/)
        // removed other assertion
        Assertions.assertEquals(1.9669710464585642E-5, rectangle.getBoundarySize(), TEST_EPS);
    }

}
