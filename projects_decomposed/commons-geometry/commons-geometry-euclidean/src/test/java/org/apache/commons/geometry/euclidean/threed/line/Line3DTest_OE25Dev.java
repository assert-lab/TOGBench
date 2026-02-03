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
package org.apache.commons.geometry.euclidean.threed.line;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.core.Transform;
import org.apache.commons.geometry.euclidean.EuclideanTestUtils;
import org.apache.commons.geometry.euclidean.oned.Vector1D;
import org.apache.commons.geometry.euclidean.threed.AffineTransformMatrix3D;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.geometry.euclidean.threed.rotation.QuaternionRotation;
import org.apache.commons.numbers.angle.Angle;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Line3DTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    @Test
    void testFromPointAndDirection_illegalDirectionNorm() {
        // act/assert
        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.ZERO, TEST_PRECISION);
        }, IllegalArgumentException.class, "Line direction cannot be zero");

        GeometryTestUtils.assertThrowsWithMessage(() -> {
            Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.of(1e-12, 1e-12, 1e-12), TEST_PRECISION);
        }, IllegalArgumentException.class, "Line direction cannot be zero");
    }

    @Test
    void testPointAt() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(0, 0, -1), Vector3D.of(4, 3, 0), TEST_PRECISION);

        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(line.getOrigin(), line.pointAt(0.0), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(4, 3, -1), line.pointAt(5.0), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(-4, -3, -1), line.pointAt(-5.0), TEST_EPS);
    }

    @Test
    void testToSpace() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(0, 0, -1), Vector3D.of(4, 3, 0), TEST_PRECISION);

        // act/assert
        EuclideanTestUtils.assertCoordinatesEqual(line.getOrigin(), line.toSpace(Vector1D.of(0.0)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(4, 3, -1), line.toSpace(Vector1D.of(5.0)), TEST_EPS);
        EuclideanTestUtils.assertCoordinatesEqual(Vector3D.of(-4, -3, -1), line.toSpace(Vector1D.of(-5.0)), TEST_EPS);
    }

    @Test
    void testReverse() {
        // arrange
        final Line3D line = Lines3D.fromPoints(Vector3D.of(1653345.6696423641, 6170370.041579291, 90000),
                             Vector3D.of(1650757.5050732433, 6160710.879908984, 0.9),
                             TEST_PRECISION);
        final Vector3D expected = line.getDirection().negate();

        // act
        final Line3D reversed = line.reverse();

        // assert
        EuclideanTestUtils.assertCoordinatesEqual(expected, reversed.getDirection(), TEST_EPS);
    }

    @Test
    void testSpan_toString() {
        // arrange
        final LineConvexSubset3D span = Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION)
                .span();

        // act
        final String str = span.toString();

        // assert
        GeometryTestUtils.assertContains("LineSpanningSubset3D[origin= (0", str);
        GeometryTestUtils.assertContains(", direction= (1", str);
    }

    @Test
    void testFromPointAndDirection_3_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(-1, 1, 0), Vector3D.Unit.PLUS_Y, TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(TEST_PRECISION, line.getPrecision());
    }

    @Test
    void testFromPointAndDirection_normalizesDirection_3_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.of(1, 1, 1), TEST_PRECISION);

        // act/assert
        // removed other assertion

        final double invSqrt3 = 1.0 / Math.sqrt(3);
        // removed other assertion
        Assertions.assertSame(TEST_PRECISION, line.getPrecision());
    }

    @Test
    void testFromPoints_3_oe() {
        // arrange
        final Line3D line = Lines3D.fromPoints(Vector3D.of(-1, 1, 0), Vector3D.of(-1, 7, 0), TEST_PRECISION);

        // act/assert
        // removed other assertion
        // removed other assertion
        Assertions.assertSame(TEST_PRECISION, line.getPrecision());
    }

    @Test
    void testFromPoints_pointsTooClose_1_oe() {
        // act/assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> Lines3D.fromPoints(Vector3D.of(1, 1, 1), Vector3D.of(1, 1, 1 + 1e-16), TEST_PRECISION));
    }

    @Test
    void testTransform_1_oe() {
        // arrange
        final Vector3D pt = Vector3D.of(1, 2, 3);
        final Line3D line = Lines3D.fromPointAndDirection(pt, Vector3D.of(1, 1, 1), TEST_PRECISION);

        final AffineTransformMatrix3D mat = AffineTransformMatrix3D.createRotation(pt,
                QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, Angle.PI_OVER_TWO));

        // act
        final Line3D result = line.transform(mat);

        // assert
        Assertions.assertTrue(result.contains(pt));
    }

    @Test
    void testTransform_reflectionInOneAxis_1_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 0), Vector3D.of(1, 1, 1), TEST_PRECISION);

        final AffineTransformMatrix3D transform = AffineTransformMatrix3D.from(v -> Vector3D.of(v.getX(), v.getY(), -v.getZ()));

        // act
        final Line3D result = line.transform(transform);

        // assert
        Assertions.assertTrue(result.contains(Vector3D.of(1, 0, 0)));
    }

    @Test
    void testTransform_reflectionInTwoAxes_1_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 0), Vector3D.of(1, 1, 1), TEST_PRECISION);

        final AffineTransformMatrix3D transform = AffineTransformMatrix3D.from(v -> Vector3D.of(v.getX(), -v.getY(), -v.getZ()));

        // act
        final Line3D result = line.transform(transform);

        // assert
        Assertions.assertTrue(result.contains(Vector3D.of(1, 0, 0)));
    }

    @Test
    void testTransform_reflectionInThreeAxes_1_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 0), Vector3D.of(1, 1, 1), TEST_PRECISION);

        final AffineTransformMatrix3D transform = AffineTransformMatrix3D.from(Vector3D::negate);

        // act
        final Line3D result = line.transform(transform);

        // assert
        Assertions.assertTrue(result.contains(Vector3D.of(-1, 0, 0)));
    }

    @Test
    void testSubspaceTransform_3_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(0, 0, 1), Vector3D.of(1, 0, 0), TEST_PRECISION);

        final Transform<Vector3D> transform = AffineTransformMatrix3D.identity()
                .scale(2, 1, 1)
                .translate(0.5, 1, 0)
                .rotate(QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, Angle.PI_OVER_TWO));

        // act
        final Line3D.SubspaceTransform result = line.subspaceTransform(transform);

        // assert
        final Line3D tLine = result.getLine();
        final Transform<Vector1D> tSub = result.getTransform();

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(0.5, tSub.apply(Vector1D.ZERO).getX(), TEST_EPS);
    }

    @Test
    void testSubspaceTransform_4_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(0, 0, 1), Vector3D.of(1, 0, 0), TEST_PRECISION);

        final Transform<Vector3D> transform = AffineTransformMatrix3D.identity()
                .scale(2, 1, 1)
                .translate(0.5, 1, 0)
                .rotate(QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, Angle.PI_OVER_TWO));

        // act
        final Line3D.SubspaceTransform result = line.subspaceTransform(transform);

        // assert
        final Line3D tLine = result.getLine();
        final Transform<Vector1D> tSub = result.getTransform();

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(4.5, tSub.apply(Vector1D.of(2)).getX(), TEST_EPS);
    }

    @Test
    void testAbscissa_1_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(0, 0, -1), Vector3D.of(4, 3, 0), TEST_PRECISION);

        // act/assert
        Assertions.assertEquals(0.0, line.abscissa(line.getOrigin()), TEST_EPS);
    }

    @Test
    void testAbscissa_2_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(0, 0, -1), Vector3D.of(4, 3, 0), TEST_PRECISION);

        // act/assert
        // removed other assertion

        Assertions.assertEquals(5.0, line.abscissa(Vector3D.of(4, 3, 0)), TEST_EPS);
    }

    @Test
    void testAbscissa_3_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(0, 0, -1), Vector3D.of(4, 3, 0), TEST_PRECISION);

        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(5.0, line.abscissa(Vector3D.of(4, 3, 10)), TEST_EPS);
    }

    @Test
    void testAbscissa_4_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(0, 0, -1), Vector3D.of(4, 3, 0), TEST_PRECISION);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(-5.0, line.abscissa(Vector3D.of(-4, -3, 0)), TEST_EPS);
    }

    @Test
    void testAbscissa_5_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(0, 0, -1), Vector3D.of(4, 3, 0), TEST_PRECISION);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(-5.0, line.abscissa(Vector3D.of(-4, -3, -10)), TEST_EPS);
    }

    @Test
    void testToSubspace_1_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(0, 0, -1), Vector3D.of(4, 3, 0), TEST_PRECISION);

        // act/assert
        Assertions.assertEquals(0.0, line.toSubspace(line.getOrigin()).getX(), TEST_EPS);
    }

    @Test
    void testToSubspace_2_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(0, 0, -1), Vector3D.of(4, 3, 0), TEST_PRECISION);

        // act/assert
        // removed other assertion

        Assertions.assertEquals(5.0, line.toSubspace(Vector3D.of(4, 3, -1)).getX(), TEST_EPS);
    }

    @Test
    void testToSubspace_3_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(0, 0, -1), Vector3D.of(4, 3, 0), TEST_PRECISION);

        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(5.0, line.toSubspace(Vector3D.of(4, 3, 10)).getX(), TEST_EPS);
    }

    @Test
    void testToSubspace_4_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(0, 0, -1), Vector3D.of(4, 3, 0), TEST_PRECISION);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(-5.0, line.toSubspace(Vector3D.of(-4, -3, -1)).getX(), TEST_EPS);
    }

    @Test
    void testToSubspace_5_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.of(0, 0, -1), Vector3D.of(4, 3, 0), TEST_PRECISION);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(-5.0, line.toSubspace(Vector3D.of(-4, -3, -10)).getX(), TEST_EPS);
    }

    @Test
    void testContains_1_oe() {
        final Vector3D p1 = Vector3D.of(0, 0, 1);
        final Line3D l = Lines3D.fromPoints(p1, Vector3D.of(0, 0, 2), TEST_PRECISION);
        Assertions.assertTrue(l.contains(p1));
    }

    @Test
    void testContains_2_oe() {
        final Vector3D p1 = Vector3D.of(0, 0, 1);
        final Line3D l = Lines3D.fromPoints(p1, Vector3D.of(0, 0, 2), TEST_PRECISION);
        // removed other assertion
        Assertions.assertTrue(l.contains(Vector3D.Sum.of(p1).addScaled(0.3, l.getDirection()).get()));
    }

    @Test
    void testContains_3_oe() {
        final Vector3D p1 = Vector3D.of(0, 0, 1);
        final Line3D l = Lines3D.fromPoints(p1, Vector3D.of(0, 0, 2), TEST_PRECISION);
        // removed other assertion
        // removed other assertion
        final Vector3D u = l.getDirection().orthogonal();
        final Vector3D v = l.getDirection().cross(u);
        for (double alpha = 0; alpha < 2 * Math.PI; alpha += 0.3) {
            Assertions.assertFalse(l.contains(p1.add(Vector3D.Sum.create().addScaled(Math.cos(alpha),u).addScaled(Math.sin(alpha),v).get())));
    }
    }

    @Test
    void testSimilar_1_oe() {
        final Vector3D p1  = Vector3D.of(1.2, 3.4, -5.8);
        final Vector3D p2  = Vector3D.of(3.4, -5.8, 1.2);
        final Line3D lA  = Lines3D.fromPoints(p1, p2, TEST_PRECISION);
        final Line3D lB  = Lines3D.fromPoints(p2, p1, TEST_PRECISION);
        Assertions.assertTrue(lA.isSimilarTo(lB));
    }

    @Test
    void testSimilar_2_oe() {
        final Vector3D p1  = Vector3D.of(1.2, 3.4, -5.8);
        final Vector3D p2  = Vector3D.of(3.4, -5.8, 1.2);
        final Line3D lA  = Lines3D.fromPoints(p1, p2, TEST_PRECISION);
        final Line3D lB  = Lines3D.fromPoints(p2, p1, TEST_PRECISION);
        // removed other assertion
        Assertions.assertFalse(lA.isSimilarTo(Lines3D.fromPoints(p1, p1.add(lA.getDirection().orthogonal()), TEST_PRECISION)));
    }

    @Test
    void testPointDistance_1_oe() {
        final Line3D l = Lines3D.fromPoints(Vector3D.of(0, 1, 1), Vector3D.of(0, 2, 2), TEST_PRECISION);
        Assertions.assertEquals(Math.sqrt(3.0 / 2.0), l.distance(Vector3D.of(1, 0, 1)), TEST_EPS);
    }

    @Test
    void testPointDistance_2_oe() {
        final Line3D l = Lines3D.fromPoints(Vector3D.of(0, 1, 1), Vector3D.of(0, 2, 2), TEST_PRECISION);
        // removed other assertion
        Assertions.assertEquals(0, l.distance(Vector3D.of(0, -4, -4)), TEST_EPS);
    }

    @Test
    void testLineDistance_1_oe() {
        final Line3D l = Lines3D.fromPoints(Vector3D.of(0, 1, 1), Vector3D.of(0, 2, 2), TEST_PRECISION);
        Assertions.assertEquals(1.0,l.distance(Lines3D.fromPoints(Vector3D.of(1,0,1),Vector3D.of(1,0,2),TEST_PRECISION)),1.0e-10);
    }

    @Test
    void testLineDistance_2_oe() {
        final Line3D l = Lines3D.fromPoints(Vector3D.of(0, 1, 1), Vector3D.of(0, 2, 2), TEST_PRECISION);
        // removed other assertion
        Assertions.assertEquals(0.5,l.distance(Lines3D.fromPoints(Vector3D.of(-0.5,0,0),Vector3D.of(-0.5,-1,-1),TEST_PRECISION)),1.0e-10);
    }

    @Test
    void testLineDistance_3_oe() {
        final Line3D l = Lines3D.fromPoints(Vector3D.of(0, 1, 1), Vector3D.of(0, 2, 2), TEST_PRECISION);
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0.0,l.distance(l),1.0e-10);
    }

    @Test
    void testLineDistance_4_oe() {
        final Line3D l = Lines3D.fromPoints(Vector3D.of(0, 1, 1), Vector3D.of(0, 2, 2), TEST_PRECISION);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0.0,l.distance(Lines3D.fromPoints(Vector3D.of(0,-4,-4),Vector3D.of(0,-5,-5),TEST_PRECISION)),1.0e-10);
    }

    @Test
    void testLineDistance_5_oe() {
        final Line3D l = Lines3D.fromPoints(Vector3D.of(0, 1, 1), Vector3D.of(0, 2, 2), TEST_PRECISION);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0.0,l.distance(Lines3D.fromPoints(Vector3D.of(0,-4,-4),Vector3D.of(0,-3,-4),TEST_PRECISION)),1.0e-10);
    }

    @Test
    void testLineDistance_6_oe() {
        final Line3D l = Lines3D.fromPoints(Vector3D.of(0, 1, 1), Vector3D.of(0, 2, 2), TEST_PRECISION);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0.0,l.distance(Lines3D.fromPoints(Vector3D.of(0,-4,-4),Vector3D.of(1,-4,-4),TEST_PRECISION)),1.0e-10);
    }

    @Test
    void testLineDistance_7_oe() {
        final Line3D l = Lines3D.fromPoints(Vector3D.of(0, 1, 1), Vector3D.of(0, 2, 2), TEST_PRECISION);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(Math.sqrt(8),l.distance(Lines3D.fromPoints(Vector3D.of(0,-4,0),Vector3D.of(1,-4,0),TEST_PRECISION)),1.0e-10);
    }

    @Test
    void testClosest_1_oe() {
        final Line3D l = Lines3D.fromPoints(Vector3D.of(0, 1, 1), Vector3D.of(0, 2, 2), TEST_PRECISION);
        Assertions.assertEquals(0.0,l.closest(Lines3D.fromPoints(Vector3D.of(1,0,1),Vector3D.of(1,0,2),TEST_PRECISION)).distance(Vector3D.of(0,0,0)),1.0e-10);
    }

    @Test
    void testClosest_2_oe() {
        final Line3D l = Lines3D.fromPoints(Vector3D.of(0, 1, 1), Vector3D.of(0, 2, 2), TEST_PRECISION);
        // removed other assertion
        Assertions.assertEquals(0.5,l.closest(Lines3D.fromPoints(Vector3D.of(-0.5,0,0),Vector3D.of(-0.5,-1,-1),TEST_PRECISION)).distance(Vector3D.of(-0.5,0,0)),1.0e-10);
    }

    @Test
    void testClosest_3_oe() {
        final Line3D l = Lines3D.fromPoints(Vector3D.of(0, 1, 1), Vector3D.of(0, 2, 2), TEST_PRECISION);
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0.0,l.closest(l).distance(Vector3D.of(0,0,0)),1.0e-10);
    }

    @Test
    void testClosest_4_oe() {
        final Line3D l = Lines3D.fromPoints(Vector3D.of(0, 1, 1), Vector3D.of(0, 2, 2), TEST_PRECISION);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0.0,l.closest(Lines3D.fromPoints(Vector3D.of(0,-4,-4),Vector3D.of(0,-5,-5),TEST_PRECISION)).distance(Vector3D.of(0,0,0)),1.0e-10);
    }

    @Test
    void testClosest_5_oe() {
        final Line3D l = Lines3D.fromPoints(Vector3D.of(0, 1, 1), Vector3D.of(0, 2, 2), TEST_PRECISION);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0.0,l.closest(Lines3D.fromPoints(Vector3D.of(0,-4,-4),Vector3D.of(0,-3,-4),TEST_PRECISION)).distance(Vector3D.of(0,-4,-4)),1.0e-10);
    }

    @Test
    void testClosest_6_oe() {
        final Line3D l = Lines3D.fromPoints(Vector3D.of(0, 1, 1), Vector3D.of(0, 2, 2), TEST_PRECISION);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0.0,l.closest(Lines3D.fromPoints(Vector3D.of(0,-4,-4),Vector3D.of(1,-4,-4),TEST_PRECISION)).distance(Vector3D.of(0,-4,-4)),1.0e-10);
    }

    @Test
    void testClosest_7_oe() {
        final Line3D l = Lines3D.fromPoints(Vector3D.of(0, 1, 1), Vector3D.of(0, 2, 2), TEST_PRECISION);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0.0,l.closest(Lines3D.fromPoints(Vector3D.of(0,-4,0),Vector3D.of(1,-4,0),TEST_PRECISION)).distance(Vector3D.of(0,-2,-2)),1.0e-10);
    }

    @Test
    void testIntersection_1_oe() {
        final Line3D l = Lines3D.fromPoints(Vector3D.of(0, 1, 1), Vector3D.of(0, 2, 2), TEST_PRECISION);
        Assertions.assertNull(l.intersection(Lines3D.fromPoints(Vector3D.of(1, 0, 1), Vector3D.of(1, 0, 2), TEST_PRECISION)));
    }

    @Test
    void testIntersection_2_oe() {
        final Line3D l = Lines3D.fromPoints(Vector3D.of(0, 1, 1), Vector3D.of(0, 2, 2), TEST_PRECISION);
        // removed other assertion
        Assertions.assertNull(l.intersection(Lines3D.fromPoints(Vector3D.of(-0.5, 0, 0), Vector3D.of(-0.5, -1, -1), TEST_PRECISION)));
    }

    @Test
    void testIntersection_3_oe() {
        final Line3D l = Lines3D.fromPoints(Vector3D.of(0, 1, 1), Vector3D.of(0, 2, 2), TEST_PRECISION);
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0.0,l.intersection(l).distance(Vector3D.of(0,0,0)),1.0e-10);
    }

    @Test
    void testIntersection_4_oe() {
        final Line3D l = Lines3D.fromPoints(Vector3D.of(0, 1, 1), Vector3D.of(0, 2, 2), TEST_PRECISION);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0.0,l.intersection(Lines3D.fromPoints(Vector3D.of(0,-4,-4),Vector3D.of(0,-5,-5),TEST_PRECISION)).distance(Vector3D.of(0,0,0)),1.0e-10);
    }

    @Test
    void testIntersection_5_oe() {
        final Line3D l = Lines3D.fromPoints(Vector3D.of(0, 1, 1), Vector3D.of(0, 2, 2), TEST_PRECISION);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0.0,l.intersection(Lines3D.fromPoints(Vector3D.of(0,-4,-4),Vector3D.of(0,-3,-4),TEST_PRECISION)).distance(Vector3D.of(0,-4,-4)),1.0e-10);
    }

    @Test
    void testIntersection_6_oe() {
        final Line3D l = Lines3D.fromPoints(Vector3D.of(0, 1, 1), Vector3D.of(0, 2, 2), TEST_PRECISION);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertEquals(0.0,l.intersection(Lines3D.fromPoints(Vector3D.of(0,-4,-4),Vector3D.of(1,-4,-4),TEST_PRECISION)).distance(Vector3D.of(0,-4,-4)),1.0e-10);
    }

    @Test
    void testIntersection_7_oe() {
        final Line3D l = Lines3D.fromPoints(Vector3D.of(0, 1, 1), Vector3D.of(0, 2, 2), TEST_PRECISION);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(l.intersection(Lines3D.fromPoints(Vector3D.of(0, -4, 0), Vector3D.of(1, -4, 0), TEST_PRECISION)));
    }

    @Test
    void testSpan_1_oe() {
        // arrange
        final Line3D line = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final LineConvexSubset3D span = line.span();

        // assert
        Assertions.assertTrue(span.isInfinite());
    }

    @Test
    void testSpan_2_oe() {
        // arrange
        final Line3D line = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final LineConvexSubset3D span = line.span();

        // assert
        // removed other assertion
        Assertions.assertFalse(span.isFinite());
    }

    @Test
    void testSpan_3_oe() {
        // arrange
        final Line3D line = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final LineConvexSubset3D span = line.span();

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertNull(span.getStartPoint());
    }

    @Test
    void testSpan_4_oe() {
        // arrange
        final Line3D line = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final LineConvexSubset3D span = line.span();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(span.getEndPoint());
    }

    @Test
    void testSpan_5_oe() {
        // arrange
        final Line3D line = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final LineConvexSubset3D span = line.span();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertNull(span.getCentroid());
    }

    @Test
    void testSpan_6_oe() {
        // arrange
        final Line3D line = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final LineConvexSubset3D span = line.span();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertNull(span.getBounds());
    }

    @Test
    void testSpan_10_oe() {
        // arrange
        final Line3D line = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final LineConvexSubset3D span = line.span();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        Assertions.assertSame(line, span.getLine());
    }

    @Test
    void testSpan_11_oe() {
        // arrange
        final Line3D line = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final LineConvexSubset3D span = line.span();

        // assert
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        Assertions.assertTrue(span.getInterval().isFull());
    }

    @Test
    void testSpan_contains_1_oe() {
        // arrange
        final double delta = 1e-12;

        final LineConvexSubset3D span = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION).span();

        for (double x = -10; x <= 10; x += 0.5) {

            // act/assert
            Assertions.assertFalse(span.contains(Vector3D.of(0, 1, 0)));
    }
    }

    @Test
    void testSpan_contains_2_oe() {
        // arrange
        final double delta = 1e-12;

        final LineConvexSubset3D span = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION).span();

        for (double x = -10; x <= 10; x += 0.5) {

            // act/assert
            // removed other assertion
            Assertions.assertFalse(span.contains(Vector3D.of(0, 0, 1)));
    }
    }

    @Test
    void testSpan_contains_3_oe() {
        // arrange
        final double delta = 1e-12;

        final LineConvexSubset3D span = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION).span();

        for (double x = -10; x <= 10; x += 0.5) {

            // act/assert
            // removed other assertion
            // removed other assertion

            Assertions.assertTrue(span.contains(Vector3D.of(x, 0, 0)));
    }
    }

    @Test
    void testSpan_contains_4_oe() {
        // arrange
        final double delta = 1e-12;

        final LineConvexSubset3D span = Lines3D.fromPoints(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION).span();

        for (double x = -10; x <= 10; x += 0.5) {

            // act/assert
            // removed other assertion
            // removed other assertion

            // removed other assertion
            Assertions.assertTrue(span.contains(Vector3D.of(x + delta, delta, delta)));
    }
    }

    @Test
    void testSpan_transform_1_oe() {
        // arrange
        final AffineTransformMatrix3D t = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, 0.5 * Math.PI)
                .toMatrix()
                .translate(Vector3D.Unit.PLUS_Y);

        final LineConvexSubset3D span = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 0), Vector3D.Unit.PLUS_X, TEST_PRECISION)
                .span();

        // act
        final LineConvexSubset3D result = span.transform(t);

        // assert
        Assertions.assertNull(result.getStartPoint());
    }

    @Test
    void testSpan_transform_2_oe() {
        // arrange
        final AffineTransformMatrix3D t = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, 0.5 * Math.PI)
                .toMatrix()
                .translate(Vector3D.Unit.PLUS_Y);

        final LineConvexSubset3D span = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 0), Vector3D.Unit.PLUS_X, TEST_PRECISION)
                .span();

        // act
        final LineConvexSubset3D result = span.transform(t);

        // assert
        // removed other assertion
        Assertions.assertNull(result.getEndPoint());
    }

    @Test
    void testSpan_transform_3_oe() {
        // arrange
        final AffineTransformMatrix3D t = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, 0.5 * Math.PI)
                .toMatrix()
                .translate(Vector3D.Unit.PLUS_Y);

        final LineConvexSubset3D span = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 0), Vector3D.Unit.PLUS_X, TEST_PRECISION)
                .span();

        // act
        final LineConvexSubset3D result = span.transform(t);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(result.contains(Vector3D.of(0, 1, -1)));
    }

    @Test
    void testSpan_transform_reflection_1_oe() {
        // arrange
        final AffineTransformMatrix3D t = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, 0.5 * Math.PI)
                .toMatrix()
                .translate(Vector3D.Unit.PLUS_Y)
                .scale(1, 1, -2);

        final LineConvexSubset3D span = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 0),
                Vector3D.Unit.PLUS_X, TEST_PRECISION).span();

        // act
        final LineConvexSubset3D result = span.transform(t);

        // assert
        Assertions.assertNull(result.getStartPoint());
    }

    @Test
    void testSpan_transform_reflection_2_oe() {
        // arrange
        final AffineTransformMatrix3D t = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, 0.5 * Math.PI)
                .toMatrix()
                .translate(Vector3D.Unit.PLUS_Y)
                .scale(1, 1, -2);

        final LineConvexSubset3D span = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 0),
                Vector3D.Unit.PLUS_X, TEST_PRECISION).span();

        // act
        final LineConvexSubset3D result = span.transform(t);

        // assert
        // removed other assertion
        Assertions.assertNull(result.getEndPoint());
    }

    @Test
    void testSpan_transform_reflection_3_oe() {
        // arrange
        final AffineTransformMatrix3D t = QuaternionRotation.fromAxisAngle(Vector3D.Unit.PLUS_Y, 0.5 * Math.PI)
                .toMatrix()
                .translate(Vector3D.Unit.PLUS_Y)
                .scale(1, 1, -2);

        final LineConvexSubset3D span = Lines3D.fromPointAndDirection(Vector3D.of(1, 0, 0),
                Vector3D.Unit.PLUS_X, TEST_PRECISION).span();

        // act
        final LineConvexSubset3D result = span.transform(t);

        // assert
        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(result.contains(Vector3D.of(0, 1, 2)));
    }

    @Test
    void testSubsetMethods_1_oe() {
        // arrange
        final Line3D line = Lines3D.fromPoints(Vector3D.of(0, 3, 0), Vector3D.of(1, 3, 0), TEST_PRECISION);

        // act/assert
        final Segment3D doubleArgResult = line.segment(3, 4);
        Assertions.assertSame(line, doubleArgResult.getLine());
    }

    @Test
    void testSubsetMethods_4_oe() {
        // arrange
        final Line3D line = Lines3D.fromPoints(Vector3D.of(0, 3, 0), Vector3D.of(1, 3, 0), TEST_PRECISION);

        // act/assert
        final Segment3D doubleArgResult = line.segment(3, 4);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Segment3D ptArgResult = line.segment(Vector3D.of(0, 4, 0), Vector3D.of(2, 5, 1));
        Assertions.assertSame(line, ptArgResult.getLine());
    }

    @Test
    void testSubsetMethods_7_oe() {
        // arrange
        final Line3D line = Lines3D.fromPoints(Vector3D.of(0, 3, 0), Vector3D.of(1, 3, 0), TEST_PRECISION);

        // act/assert
        final Segment3D doubleArgResult = line.segment(3, 4);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Segment3D ptArgResult = line.segment(Vector3D.of(0, 4, 0), Vector3D.of(2, 5, 1));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Ray3D rayDoubleResult = line.rayFrom(2);
        Assertions.assertSame(line, rayDoubleResult.getLine());
    }

    @Test
    void testSubsetMethods_9_oe() {
        // arrange
        final Line3D line = Lines3D.fromPoints(Vector3D.of(0, 3, 0), Vector3D.of(1, 3, 0), TEST_PRECISION);

        // act/assert
        final Segment3D doubleArgResult = line.segment(3, 4);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Segment3D ptArgResult = line.segment(Vector3D.of(0, 4, 0), Vector3D.of(2, 5, 1));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Ray3D rayDoubleResult = line.rayFrom(2);
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(rayDoubleResult.getEndPoint());
    }

    @Test
    void testSubsetMethods_10_oe() {
        // arrange
        final Line3D line = Lines3D.fromPoints(Vector3D.of(0, 3, 0), Vector3D.of(1, 3, 0), TEST_PRECISION);

        // act/assert
        final Segment3D doubleArgResult = line.segment(3, 4);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Segment3D ptArgResult = line.segment(Vector3D.of(0, 4, 0), Vector3D.of(2, 5, 1));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Ray3D rayDoubleResult = line.rayFrom(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Ray3D rayPtResult = line.rayFrom(Vector3D.of(1, 4, 0));
        Assertions.assertSame(line, rayPtResult.getLine());
    }

    @Test
    void testSubsetMethods_12_oe() {
        // arrange
        final Line3D line = Lines3D.fromPoints(Vector3D.of(0, 3, 0), Vector3D.of(1, 3, 0), TEST_PRECISION);

        // act/assert
        final Segment3D doubleArgResult = line.segment(3, 4);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Segment3D ptArgResult = line.segment(Vector3D.of(0, 4, 0), Vector3D.of(2, 5, 1));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Ray3D rayDoubleResult = line.rayFrom(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Ray3D rayPtResult = line.rayFrom(Vector3D.of(1, 4, 0));
        // removed other assertion
        // removed other assertion
        Assertions.assertNull(rayPtResult.getEndPoint());
    }

    @Test
    void testSubsetMethods_13_oe() {
        // arrange
        final Line3D line = Lines3D.fromPoints(Vector3D.of(0, 3, 0), Vector3D.of(1, 3, 0), TEST_PRECISION);

        // act/assert
        final Segment3D doubleArgResult = line.segment(3, 4);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Segment3D ptArgResult = line.segment(Vector3D.of(0, 4, 0), Vector3D.of(2, 5, 1));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Ray3D rayDoubleResult = line.rayFrom(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Ray3D rayPtResult = line.rayFrom(Vector3D.of(1, 4, 0));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final ReverseRay3D toDoubleResult = line.reverseRayTo(-1);
        Assertions.assertSame(line, toDoubleResult.getLine());
    }

    @Test
    void testSubsetMethods_14_oe() {
        // arrange
        final Line3D line = Lines3D.fromPoints(Vector3D.of(0, 3, 0), Vector3D.of(1, 3, 0), TEST_PRECISION);

        // act/assert
        final Segment3D doubleArgResult = line.segment(3, 4);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Segment3D ptArgResult = line.segment(Vector3D.of(0, 4, 0), Vector3D.of(2, 5, 1));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Ray3D rayDoubleResult = line.rayFrom(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Ray3D rayPtResult = line.rayFrom(Vector3D.of(1, 4, 0));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final ReverseRay3D toDoubleResult = line.reverseRayTo(-1);
        // removed other assertion
        Assertions.assertNull(toDoubleResult.getStartPoint());
    }

    @Test
    void testSubsetMethods_16_oe() {
        // arrange
        final Line3D line = Lines3D.fromPoints(Vector3D.of(0, 3, 0), Vector3D.of(1, 3, 0), TEST_PRECISION);

        // act/assert
        final Segment3D doubleArgResult = line.segment(3, 4);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Segment3D ptArgResult = line.segment(Vector3D.of(0, 4, 0), Vector3D.of(2, 5, 1));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Ray3D rayDoubleResult = line.rayFrom(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Ray3D rayPtResult = line.rayFrom(Vector3D.of(1, 4, 0));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final ReverseRay3D toDoubleResult = line.reverseRayTo(-1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final ReverseRay3D toPtResult = line.reverseRayTo(Vector3D.of(1, 4, 0));
        Assertions.assertSame(line, toPtResult.getLine());
    }

    @Test
    void testSubsetMethods_17_oe() {
        // arrange
        final Line3D line = Lines3D.fromPoints(Vector3D.of(0, 3, 0), Vector3D.of(1, 3, 0), TEST_PRECISION);

        // act/assert
        final Segment3D doubleArgResult = line.segment(3, 4);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Segment3D ptArgResult = line.segment(Vector3D.of(0, 4, 0), Vector3D.of(2, 5, 1));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Ray3D rayDoubleResult = line.rayFrom(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Ray3D rayPtResult = line.rayFrom(Vector3D.of(1, 4, 0));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final ReverseRay3D toDoubleResult = line.reverseRayTo(-1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final ReverseRay3D toPtResult = line.reverseRayTo(Vector3D.of(1, 4, 0));
        // removed other assertion
        Assertions.assertNull(toPtResult.getStartPoint());
    }

    @Test
    void testEq_1_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final Vector3D p = Vector3D.of(1, 2, 3);
        final Vector3D dir = Vector3D.of(1, 0, 0);

        final Line3D a = Lines3D.fromPointAndDirection(p, dir, precision);
        final Line3D b = Lines3D.fromPointAndDirection(Vector3D.ZERO, dir, precision);
        final Line3D c = Lines3D.fromPointAndDirection(p, Vector3D.of(1, 1, 0), precision);

        final Line3D d = Lines3D.fromPointAndDirection(p, dir, precision);
        final Line3D e = Lines3D.fromPointAndDirection(p.add(Vector3D.of(1e-4, 1e-4, 1e-4)), dir, precision);
        final Line3D f = Lines3D.fromPointAndDirection(p, Vector3D.of(1 + 1e-4, 1e-4, 1e-4), precision);

        // act/assert
        Assertions.assertTrue(a.eq(a, precision));
    }

    @Test
    void testEq_2_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final Vector3D p = Vector3D.of(1, 2, 3);
        final Vector3D dir = Vector3D.of(1, 0, 0);

        final Line3D a = Lines3D.fromPointAndDirection(p, dir, precision);
        final Line3D b = Lines3D.fromPointAndDirection(Vector3D.ZERO, dir, precision);
        final Line3D c = Lines3D.fromPointAndDirection(p, Vector3D.of(1, 1, 0), precision);

        final Line3D d = Lines3D.fromPointAndDirection(p, dir, precision);
        final Line3D e = Lines3D.fromPointAndDirection(p.add(Vector3D.of(1e-4, 1e-4, 1e-4)), dir, precision);
        final Line3D f = Lines3D.fromPointAndDirection(p, Vector3D.of(1 + 1e-4, 1e-4, 1e-4), precision);

        // act/assert
        // removed other assertion

        Assertions.assertTrue(a.eq(d, precision));
    }

    @Test
    void testEq_3_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final Vector3D p = Vector3D.of(1, 2, 3);
        final Vector3D dir = Vector3D.of(1, 0, 0);

        final Line3D a = Lines3D.fromPointAndDirection(p, dir, precision);
        final Line3D b = Lines3D.fromPointAndDirection(Vector3D.ZERO, dir, precision);
        final Line3D c = Lines3D.fromPointAndDirection(p, Vector3D.of(1, 1, 0), precision);

        final Line3D d = Lines3D.fromPointAndDirection(p, dir, precision);
        final Line3D e = Lines3D.fromPointAndDirection(p.add(Vector3D.of(1e-4, 1e-4, 1e-4)), dir, precision);
        final Line3D f = Lines3D.fromPointAndDirection(p, Vector3D.of(1 + 1e-4, 1e-4, 1e-4), precision);

        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertTrue(d.eq(a, precision));
    }

    @Test
    void testEq_4_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final Vector3D p = Vector3D.of(1, 2, 3);
        final Vector3D dir = Vector3D.of(1, 0, 0);

        final Line3D a = Lines3D.fromPointAndDirection(p, dir, precision);
        final Line3D b = Lines3D.fromPointAndDirection(Vector3D.ZERO, dir, precision);
        final Line3D c = Lines3D.fromPointAndDirection(p, Vector3D.of(1, 1, 0), precision);

        final Line3D d = Lines3D.fromPointAndDirection(p, dir, precision);
        final Line3D e = Lines3D.fromPointAndDirection(p.add(Vector3D.of(1e-4, 1e-4, 1e-4)), dir, precision);
        final Line3D f = Lines3D.fromPointAndDirection(p, Vector3D.of(1 + 1e-4, 1e-4, 1e-4), precision);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(a.eq(e, precision));
    }

    @Test
    void testEq_5_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final Vector3D p = Vector3D.of(1, 2, 3);
        final Vector3D dir = Vector3D.of(1, 0, 0);

        final Line3D a = Lines3D.fromPointAndDirection(p, dir, precision);
        final Line3D b = Lines3D.fromPointAndDirection(Vector3D.ZERO, dir, precision);
        final Line3D c = Lines3D.fromPointAndDirection(p, Vector3D.of(1, 1, 0), precision);

        final Line3D d = Lines3D.fromPointAndDirection(p, dir, precision);
        final Line3D e = Lines3D.fromPointAndDirection(p.add(Vector3D.of(1e-4, 1e-4, 1e-4)), dir, precision);
        final Line3D f = Lines3D.fromPointAndDirection(p, Vector3D.of(1 + 1e-4, 1e-4, 1e-4), precision);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertTrue(e.eq(a, precision));
    }

    @Test
    void testEq_6_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final Vector3D p = Vector3D.of(1, 2, 3);
        final Vector3D dir = Vector3D.of(1, 0, 0);

        final Line3D a = Lines3D.fromPointAndDirection(p, dir, precision);
        final Line3D b = Lines3D.fromPointAndDirection(Vector3D.ZERO, dir, precision);
        final Line3D c = Lines3D.fromPointAndDirection(p, Vector3D.of(1, 1, 0), precision);

        final Line3D d = Lines3D.fromPointAndDirection(p, dir, precision);
        final Line3D e = Lines3D.fromPointAndDirection(p.add(Vector3D.of(1e-4, 1e-4, 1e-4)), dir, precision);
        final Line3D f = Lines3D.fromPointAndDirection(p, Vector3D.of(1 + 1e-4, 1e-4, 1e-4), precision);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertTrue(a.eq(f, precision));
    }

    @Test
    void testEq_7_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final Vector3D p = Vector3D.of(1, 2, 3);
        final Vector3D dir = Vector3D.of(1, 0, 0);

        final Line3D a = Lines3D.fromPointAndDirection(p, dir, precision);
        final Line3D b = Lines3D.fromPointAndDirection(Vector3D.ZERO, dir, precision);
        final Line3D c = Lines3D.fromPointAndDirection(p, Vector3D.of(1, 1, 0), precision);

        final Line3D d = Lines3D.fromPointAndDirection(p, dir, precision);
        final Line3D e = Lines3D.fromPointAndDirection(p.add(Vector3D.of(1e-4, 1e-4, 1e-4)), dir, precision);
        final Line3D f = Lines3D.fromPointAndDirection(p, Vector3D.of(1 + 1e-4, 1e-4, 1e-4), precision);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertTrue(f.eq(a, precision));
    }

    @Test
    void testEq_8_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final Vector3D p = Vector3D.of(1, 2, 3);
        final Vector3D dir = Vector3D.of(1, 0, 0);

        final Line3D a = Lines3D.fromPointAndDirection(p, dir, precision);
        final Line3D b = Lines3D.fromPointAndDirection(Vector3D.ZERO, dir, precision);
        final Line3D c = Lines3D.fromPointAndDirection(p, Vector3D.of(1, 1, 0), precision);

        final Line3D d = Lines3D.fromPointAndDirection(p, dir, precision);
        final Line3D e = Lines3D.fromPointAndDirection(p.add(Vector3D.of(1e-4, 1e-4, 1e-4)), dir, precision);
        final Line3D f = Lines3D.fromPointAndDirection(p, Vector3D.of(1 + 1e-4, 1e-4, 1e-4), precision);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        Assertions.assertFalse(a.eq(b, precision));
    }

    @Test
    void testEq_9_oe() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-3);

        final Vector3D p = Vector3D.of(1, 2, 3);
        final Vector3D dir = Vector3D.of(1, 0, 0);

        final Line3D a = Lines3D.fromPointAndDirection(p, dir, precision);
        final Line3D b = Lines3D.fromPointAndDirection(Vector3D.ZERO, dir, precision);
        final Line3D c = Lines3D.fromPointAndDirection(p, Vector3D.of(1, 1, 0), precision);

        final Line3D d = Lines3D.fromPointAndDirection(p, dir, precision);
        final Line3D e = Lines3D.fromPointAndDirection(p.add(Vector3D.of(1e-4, 1e-4, 1e-4)), dir, precision);
        final Line3D f = Lines3D.fromPointAndDirection(p, Vector3D.of(1 + 1e-4, 1e-4, 1e-4), precision);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertFalse(a.eq(c, precision));
    }

    @Test
    void testHashCode_1_oe() {
        // arrange
        final Line3D a = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), TEST_PRECISION);
        final Line3D b = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, -1), Vector3D.of(4, 5, 6), TEST_PRECISION);
        final Line3D c = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, -1), TEST_PRECISION);
        final Line3D d = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), Precision.doubleEquivalenceOfEpsilon(TEST_EPS + 1e-3));

        final Line3D e = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), TEST_PRECISION);

        final int hash = a.hashCode();

        // act/assert
        Assertions.assertEquals(hash, a.hashCode());
    }

    @Test
    void testHashCode_2_oe() {
        // arrange
        final Line3D a = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), TEST_PRECISION);
        final Line3D b = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, -1), Vector3D.of(4, 5, 6), TEST_PRECISION);
        final Line3D c = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, -1), TEST_PRECISION);
        final Line3D d = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), Precision.doubleEquivalenceOfEpsilon(TEST_EPS + 1e-3));

        final Line3D e = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), TEST_PRECISION);

        final int hash = a.hashCode();

        // act/assert
        // removed other assertion

        Assertions.assertNotEquals(hash, b.hashCode());
    }

    @Test
    void testHashCode_3_oe() {
        // arrange
        final Line3D a = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), TEST_PRECISION);
        final Line3D b = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, -1), Vector3D.of(4, 5, 6), TEST_PRECISION);
        final Line3D c = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, -1), TEST_PRECISION);
        final Line3D d = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), Precision.doubleEquivalenceOfEpsilon(TEST_EPS + 1e-3));

        final Line3D e = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), TEST_PRECISION);

        final int hash = a.hashCode();

        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertNotEquals(hash, c.hashCode());
    }

    @Test
    void testHashCode_4_oe() {
        // arrange
        final Line3D a = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), TEST_PRECISION);
        final Line3D b = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, -1), Vector3D.of(4, 5, 6), TEST_PRECISION);
        final Line3D c = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, -1), TEST_PRECISION);
        final Line3D d = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), Precision.doubleEquivalenceOfEpsilon(TEST_EPS + 1e-3));

        final Line3D e = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), TEST_PRECISION);

        final int hash = a.hashCode();

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertNotEquals(hash, d.hashCode());
    }

    @Test
    void testHashCode_5_oe() {
        // arrange
        final Line3D a = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), TEST_PRECISION);
        final Line3D b = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, -1), Vector3D.of(4, 5, 6), TEST_PRECISION);
        final Line3D c = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, -1), TEST_PRECISION);
        final Line3D d = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), Precision.doubleEquivalenceOfEpsilon(TEST_EPS + 1e-3));

        final Line3D e = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), TEST_PRECISION);

        final int hash = a.hashCode();

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(hash, e.hashCode());
    }

    @Test
    void testEquals_2_oe() {
        // arrange
        final Line3D a = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), TEST_PRECISION);
        final Line3D b = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, -1), Vector3D.of(4, 5, 6), TEST_PRECISION);
        final Line3D c = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, -1), TEST_PRECISION);
        final Line3D d = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), Precision.doubleEquivalenceOfEpsilon(TEST_EPS + 1e-3));

        final Line3D e = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), TEST_PRECISION);

        // act/assert
        // removed other assertion

        Assertions.assertNotEquals(a, b);
    }

    @Test
    void testEquals_3_oe() {
        // arrange
        final Line3D a = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), TEST_PRECISION);
        final Line3D b = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, -1), Vector3D.of(4, 5, 6), TEST_PRECISION);
        final Line3D c = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, -1), TEST_PRECISION);
        final Line3D d = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), Precision.doubleEquivalenceOfEpsilon(TEST_EPS + 1e-3));

        final Line3D e = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), TEST_PRECISION);

        // act/assert
        // removed other assertion

        // removed other assertion
        Assertions.assertNotEquals(a, c);
    }

    @Test
    void testEquals_4_oe() {
        // arrange
        final Line3D a = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), TEST_PRECISION);
        final Line3D b = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, -1), Vector3D.of(4, 5, 6), TEST_PRECISION);
        final Line3D c = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, -1), TEST_PRECISION);
        final Line3D d = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), Precision.doubleEquivalenceOfEpsilon(TEST_EPS + 1e-3));

        final Line3D e = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), TEST_PRECISION);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        Assertions.assertNotEquals(a, d);
    }

    @Test
    void testEquals_5_oe() {
        // arrange
        final Line3D a = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), TEST_PRECISION);
        final Line3D b = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, -1), Vector3D.of(4, 5, 6), TEST_PRECISION);
        final Line3D c = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, -1), TEST_PRECISION);
        final Line3D d = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), Precision.doubleEquivalenceOfEpsilon(TEST_EPS + 1e-3));

        final Line3D e = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), TEST_PRECISION);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        Assertions.assertEquals(a, e);
    }

    @Test
    void testEquals_6_oe() {
        // arrange
        final Line3D a = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), TEST_PRECISION);
        final Line3D b = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, -1), Vector3D.of(4, 5, 6), TEST_PRECISION);
        final Line3D c = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, -1), TEST_PRECISION);
        final Line3D d = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), Precision.doubleEquivalenceOfEpsilon(TEST_EPS + 1e-3));

        final Line3D e = Lines3D.fromPointAndDirection(Vector3D.of(1, 2, 3), Vector3D.of(4, 5, 6), TEST_PRECISION);

        // act/assert
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        Assertions.assertEquals(e, a);
    }

    @Test
    void testToString_1_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final String str = line.toString();

        // assert
        Assertions.assertTrue(str.contains("Line3D"));
    }

    @Test
    void testToString_2_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final String str = line.toString();

        // assert
        // removed other assertion
        Assertions.assertTrue(str.matches(".*origin= \\(0(\\.0)?, 0(\\.0)?, 0(\\.0)?\\).*"));
    }

    @Test
    void testToString_3_oe() {
        // arrange
        final Line3D line = Lines3D.fromPointAndDirection(Vector3D.ZERO, Vector3D.Unit.PLUS_X, TEST_PRECISION);

        // act
        final String str = line.toString();

        // assert
        // removed other assertion
        // removed other assertion
        Assertions.assertTrue(str.matches(".*direction= \\(1(\\.0)?, 0(\\.0)?, 0(\\.0)?\\).*"));
    }

}
