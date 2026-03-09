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
import java.util.List;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LinecastPoint2DTest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Precision.DoubleEquivalence TEST_PRECISION =
            Precision.doubleEquivalenceOfEpsilon(TEST_EPS);

    private static final Line X_AXIS =
            Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_X, TEST_PRECISION);

    private static final Line Y_AXIS =
            Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_Y, TEST_PRECISION);

    @Test
    void testProperties() {
        // arrange
        final Vector2D pt = Vector2D.of(1, 1);
        final Vector2D normal = Vector2D.Unit.PLUS_X;

        final LinecastPoint2D it = new LinecastPoint2D(pt, normal, X_AXIS);

        // act
        Assertions.assertSame(pt, it.getPoint());
        Assertions.assertSame(normal, it.getNormal());
        Assertions.assertSame(X_AXIS, it.getLine());
        Assertions.assertEquals(1.0, it.getAbscissa(), TEST_EPS);
    }

    @Test
    void testAbscissaOrder() {
        // arrange
        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);

        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.of(2, 2), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(-3, 3), Vector2D.Unit.PLUS_Y, X_AXIS);
        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 4), Vector2D.Unit.PLUS_Y, X_AXIS);
        final LinecastPoint2D e = new LinecastPoint2D(Vector2D.of(1, 4), Vector2D.Unit.PLUS_X, X_AXIS);

        // act/assert
        Assertions.assertEquals(-1, LinecastPoint2D.ABSCISSA_ORDER.compare(a, b));
        Assertions.assertEquals(1, LinecastPoint2D.ABSCISSA_ORDER.compare(a, c));
        Assertions.assertEquals(1, LinecastPoint2D.ABSCISSA_ORDER.compare(a, d));
        Assertions.assertEquals(0, LinecastPoint2D.ABSCISSA_ORDER.compare(a, e));
    }

    @Test
    void testHashCode() {
        // arrange
        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.of(2, 2), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_Y, X_AXIS);
        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, Y_AXIS);
        final LinecastPoint2D e = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);

        // act
        final int hash = a.hashCode();

        // assert
        Assertions.assertEquals(hash, a.hashCode());

        Assertions.assertNotEquals(hash, b.hashCode());
        Assertions.assertNotEquals(hash, c.hashCode());
        Assertions.assertNotEquals(hash, d.hashCode());

        Assertions.assertEquals(hash, e.hashCode());
    }

    @Test
    void testEquals() {
        // arrange
        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.of(2, 2), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_Y, X_AXIS);
        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, Y_AXIS);
        final LinecastPoint2D e = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);

        // act/assert
        GeometryTestUtils.assertSimpleEqualsCases(a);

        Assertions.assertNotEquals(a, b);
        Assertions.assertNotEquals(a, c);
        Assertions.assertNotEquals(a, d);

        Assertions.assertEquals(a, e);
        Assertions.assertEquals(e, a);
    }

    @Test
    void testEq() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final Line line = Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_X, precision);
        final Line otherLine = Lines.fromPointAndDirection(Vector2D.of(1e-4, 1e-4), Vector2D.Unit.PLUS_X, precision);

        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, line);

        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.of(2, 2), Vector2D.Unit.PLUS_X, line);
        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_Y, line);

        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, line);
        final LinecastPoint2D e = new LinecastPoint2D(
                Vector2D.of(1 + 1e-3, 1 + 1e-3), Vector2D.Unit.from(1 + 1e-3, 1e-3), otherLine);

        // act/assert
        Assertions.assertTrue(a.eq(a, precision));

        Assertions.assertFalse(a.eq(b, precision));
        Assertions.assertFalse(a.eq(c, precision));

        Assertions.assertTrue(a.eq(d, precision));
        Assertions.assertTrue(a.eq(e, precision));
    }

    @Test
    void testToString() {
        // arrange
        final LinecastPoint2D it = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);

        // act
        final String str = it.toString();

        // assert
        GeometryTestUtils.assertContains("LinecastPoint2D[point= (1.0, 1.0), normal= (1.0, 0.0)", str);
    }

    @Test
    void testSortAndFilter_empty() {
        // arrange
        final List<LinecastPoint2D> pts = new ArrayList<>();

        // act
        LinecastPoint2D.sortAndFilter(pts);

        // assert
        Assertions.assertEquals(0, pts.size());
    }

    @Test
    void testSortAndFilter() {
        // arrange
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final Line line = Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_X, precision);
        final Line eqLine = Lines.fromPointAndDirection(Vector2D.of(1e-3, 1e-3), Vector2D.Unit.PLUS_X, precision);
        final Line diffLine = Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_Y, precision);

        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.ZERO, Vector2D.Unit.MINUS_Y, line);
        final LinecastPoint2D aDup1 = new LinecastPoint2D(Vector2D.of(1e-3, 0), Vector2D.Unit.MINUS_Y, line);
        final LinecastPoint2D aDup2 = new LinecastPoint2D(Vector2D.of(1e-3, 1e-3), Vector2D.of(1e-3, -1), eqLine);

        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.ZERO, Vector2D.Unit.MINUS_X, diffLine);
        final LinecastPoint2D bDup = new LinecastPoint2D(Vector2D.of(-1e-3, 1e-4), Vector2D.Unit.MINUS_X, diffLine);

        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(0.5, 0), Vector2D.Unit.MINUS_Y, line);

        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 0), Vector2D.Unit.MINUS_Y, line);

        final List<LinecastPoint2D> list = new ArrayList<>(Arrays.asList(d, aDup1, bDup, b, c, a, aDup2));

        // act
        LinecastPoint2D.sortAndFilter(list);

        // assert
        Assertions.assertEquals(4, list.size());

        Assertions.assertSame(b, list.get(0));
        Assertions.assertSame(a, list.get(1));
        Assertions.assertSame(c, list.get(2));
        Assertions.assertSame(d, list.get(3));
    }

    @Test
    void testProperties_1_oe() {
        final Vector2D pt = Vector2D.of(1, 1);
        final Vector2D normal = Vector2D.Unit.PLUS_X;

        final LinecastPoint2D it = new LinecastPoint2D(pt, normal, X_AXIS);

        Assertions.assertSame(pt, it.getPoint());
    }

    @Test
    void testProperties_2_oe() {
        final Vector2D pt = Vector2D.of(1, 1);
        final Vector2D normal = Vector2D.Unit.PLUS_X;

        final LinecastPoint2D it = new LinecastPoint2D(pt, normal, X_AXIS);

        Assertions.assertSame(normal, it.getNormal());
    }

    @Test
    void testProperties_3_oe() {
        final Vector2D pt = Vector2D.of(1, 1);
        final Vector2D normal = Vector2D.Unit.PLUS_X;

        final LinecastPoint2D it = new LinecastPoint2D(pt, normal, X_AXIS);

        Assertions.assertSame(X_AXIS, it.getLine());
    }

    @Test
    void testProperties_4_oe() {
        final Vector2D pt = Vector2D.of(1, 1);
        final Vector2D normal = Vector2D.Unit.PLUS_X;

        final LinecastPoint2D it = new LinecastPoint2D(pt, normal, X_AXIS);

        Assertions.assertEquals(1.0, it.getAbscissa(), TEST_EPS);
    }

    @Test
    void testAbscissaOrder_1_oe() {
        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);

        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.of(2, 2), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(-3, 3), Vector2D.Unit.PLUS_Y, X_AXIS);
        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 4), Vector2D.Unit.PLUS_Y, X_AXIS);
        final LinecastPoint2D e = new LinecastPoint2D(Vector2D.of(1, 4), Vector2D.Unit.PLUS_X, X_AXIS);

        Assertions.assertEquals(-1, LinecastPoint2D.ABSCISSA_ORDER.compare(a, b));
    }

    @Test
    void testAbscissaOrder_2_oe() {
        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);

        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.of(2, 2), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(-3, 3), Vector2D.Unit.PLUS_Y, X_AXIS);
        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 4), Vector2D.Unit.PLUS_Y, X_AXIS);
        final LinecastPoint2D e = new LinecastPoint2D(Vector2D.of(1, 4), Vector2D.Unit.PLUS_X, X_AXIS);

        Assertions.assertEquals(1, LinecastPoint2D.ABSCISSA_ORDER.compare(a, c));
    }

    @Test
    void testAbscissaOrder_3_oe() {
        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);

        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.of(2, 2), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(-3, 3), Vector2D.Unit.PLUS_Y, X_AXIS);
        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 4), Vector2D.Unit.PLUS_Y, X_AXIS);
        final LinecastPoint2D e = new LinecastPoint2D(Vector2D.of(1, 4), Vector2D.Unit.PLUS_X, X_AXIS);

        Assertions.assertEquals(1, LinecastPoint2D.ABSCISSA_ORDER.compare(a, d));
    }

    @Test
    void testAbscissaOrder_4_oe() {
        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);

        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.of(2, 2), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(-3, 3), Vector2D.Unit.PLUS_Y, X_AXIS);
        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 4), Vector2D.Unit.PLUS_Y, X_AXIS);
        final LinecastPoint2D e = new LinecastPoint2D(Vector2D.of(1, 4), Vector2D.Unit.PLUS_X, X_AXIS);

        Assertions.assertEquals(0, LinecastPoint2D.ABSCISSA_ORDER.compare(a, e));
    }

    @Test
    void testHashCode_1_oe() {
        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.of(2, 2), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_Y, X_AXIS);
        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, Y_AXIS);
        final LinecastPoint2D e = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);

        final int hash = a.hashCode();

        Assertions.assertEquals(hash, a.hashCode());
    }

    @Test
    void testHashCode_2_oe() {
        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.of(2, 2), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_Y, X_AXIS);
        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, Y_AXIS);
        final LinecastPoint2D e = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);

        final int hash = a.hashCode();


        Assertions.assertNotEquals(hash, b.hashCode());
    }

    @Test
    void testHashCode_3_oe() {
        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.of(2, 2), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_Y, X_AXIS);
        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, Y_AXIS);
        final LinecastPoint2D e = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);

        final int hash = a.hashCode();


        Assertions.assertNotEquals(hash, c.hashCode());
    }

    @Test
    void testHashCode_4_oe() {
        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.of(2, 2), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_Y, X_AXIS);
        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, Y_AXIS);
        final LinecastPoint2D e = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);

        final int hash = a.hashCode();


        Assertions.assertNotEquals(hash, d.hashCode());
    }

    @Test
    void testHashCode_5_oe() {
        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.of(2, 2), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_Y, X_AXIS);
        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, Y_AXIS);
        final LinecastPoint2D e = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);

        final int hash = a.hashCode();



        Assertions.assertEquals(hash, e.hashCode());
    }

    @Test
    void testEquals_2_oe() {
        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.of(2, 2), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_Y, X_AXIS);
        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, Y_AXIS);
        final LinecastPoint2D e = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);


        Assertions.assertNotEquals(a, b);
    }

    @Test
    void testEquals_3_oe() {
        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.of(2, 2), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_Y, X_AXIS);
        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, Y_AXIS);
        final LinecastPoint2D e = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);


        Assertions.assertNotEquals(a, c);
    }

    @Test
    void testEquals_4_oe() {
        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.of(2, 2), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_Y, X_AXIS);
        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, Y_AXIS);
        final LinecastPoint2D e = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);


        Assertions.assertNotEquals(a, d);
    }

    @Test
    void testEquals_5_oe() {
        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.of(2, 2), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_Y, X_AXIS);
        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, Y_AXIS);
        final LinecastPoint2D e = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);



        Assertions.assertEquals(a, e);
    }

    @Test
    void testEquals_6_oe() {
        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.of(2, 2), Vector2D.Unit.PLUS_X, X_AXIS);
        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_Y, X_AXIS);
        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, Y_AXIS);
        final LinecastPoint2D e = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, X_AXIS);



        Assertions.assertEquals(e, a);
    }

    @Test
    void testEq_1_oe() {
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final Line line = Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_X, precision);
        final Line otherLine = Lines.fromPointAndDirection(Vector2D.of(1e-4, 1e-4), Vector2D.Unit.PLUS_X, precision);

        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, line);

        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.of(2, 2), Vector2D.Unit.PLUS_X, line);
        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_Y, line);

        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, line);
        final LinecastPoint2D e = new LinecastPoint2D(
                Vector2D.of(1 + 1e-3, 1 + 1e-3), Vector2D.Unit.from(1 + 1e-3, 1e-3), otherLine);

        Assertions.assertTrue(a.eq(a, precision));
    }

    @Test
    void testEq_2_oe() {
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final Line line = Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_X, precision);
        final Line otherLine = Lines.fromPointAndDirection(Vector2D.of(1e-4, 1e-4), Vector2D.Unit.PLUS_X, precision);

        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, line);

        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.of(2, 2), Vector2D.Unit.PLUS_X, line);
        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_Y, line);

        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, line);
        final LinecastPoint2D e = new LinecastPoint2D(
                Vector2D.of(1 + 1e-3, 1 + 1e-3), Vector2D.Unit.from(1 + 1e-3, 1e-3), otherLine);


        Assertions.assertFalse(a.eq(b, precision));
    }

    @Test
    void testEq_3_oe() {
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final Line line = Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_X, precision);
        final Line otherLine = Lines.fromPointAndDirection(Vector2D.of(1e-4, 1e-4), Vector2D.Unit.PLUS_X, precision);

        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, line);

        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.of(2, 2), Vector2D.Unit.PLUS_X, line);
        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_Y, line);

        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, line);
        final LinecastPoint2D e = new LinecastPoint2D(
                Vector2D.of(1 + 1e-3, 1 + 1e-3), Vector2D.Unit.from(1 + 1e-3, 1e-3), otherLine);


        Assertions.assertFalse(a.eq(c, precision));
    }

    @Test
    void testEq_4_oe() {
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final Line line = Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_X, precision);
        final Line otherLine = Lines.fromPointAndDirection(Vector2D.of(1e-4, 1e-4), Vector2D.Unit.PLUS_X, precision);

        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, line);

        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.of(2, 2), Vector2D.Unit.PLUS_X, line);
        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_Y, line);

        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, line);
        final LinecastPoint2D e = new LinecastPoint2D(
                Vector2D.of(1 + 1e-3, 1 + 1e-3), Vector2D.Unit.from(1 + 1e-3, 1e-3), otherLine);



        Assertions.assertTrue(a.eq(d, precision));
    }

    @Test
    void testEq_5_oe() {
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final Line line = Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_X, precision);
        final Line otherLine = Lines.fromPointAndDirection(Vector2D.of(1e-4, 1e-4), Vector2D.Unit.PLUS_X, precision);

        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, line);

        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.of(2, 2), Vector2D.Unit.PLUS_X, line);
        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_Y, line);

        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 1), Vector2D.Unit.PLUS_X, line);
        final LinecastPoint2D e = new LinecastPoint2D(
                Vector2D.of(1 + 1e-3, 1 + 1e-3), Vector2D.Unit.from(1 + 1e-3, 1e-3), otherLine);



        Assertions.assertTrue(a.eq(e, precision));
    }

    @Test
    void testSortAndFilter_empty_1_oe() {
        final List<LinecastPoint2D> pts = new ArrayList<>();

        LinecastPoint2D.sortAndFilter(pts);

        Assertions.assertEquals(0, pts.size());
    }

    @Test
    void testSortAndFilter_1_oe() {
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final Line line = Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_X, precision);
        final Line eqLine = Lines.fromPointAndDirection(Vector2D.of(1e-3, 1e-3), Vector2D.Unit.PLUS_X, precision);
        final Line diffLine = Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_Y, precision);

        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.ZERO, Vector2D.Unit.MINUS_Y, line);
        final LinecastPoint2D aDup1 = new LinecastPoint2D(Vector2D.of(1e-3, 0), Vector2D.Unit.MINUS_Y, line);
        final LinecastPoint2D aDup2 = new LinecastPoint2D(Vector2D.of(1e-3, 1e-3), Vector2D.of(1e-3, -1), eqLine);

        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.ZERO, Vector2D.Unit.MINUS_X, diffLine);
        final LinecastPoint2D bDup = new LinecastPoint2D(Vector2D.of(-1e-3, 1e-4), Vector2D.Unit.MINUS_X, diffLine);

        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(0.5, 0), Vector2D.Unit.MINUS_Y, line);

        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 0), Vector2D.Unit.MINUS_Y, line);

        final List<LinecastPoint2D> list = new ArrayList<>(Arrays.asList(d, aDup1, bDup, b, c, a, aDup2));

        LinecastPoint2D.sortAndFilter(list);

        Assertions.assertEquals(4, list.size());
    }

    @Test
    void testSortAndFilter_2_oe() {
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final Line line = Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_X, precision);
        final Line eqLine = Lines.fromPointAndDirection(Vector2D.of(1e-3, 1e-3), Vector2D.Unit.PLUS_X, precision);
        final Line diffLine = Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_Y, precision);

        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.ZERO, Vector2D.Unit.MINUS_Y, line);
        final LinecastPoint2D aDup1 = new LinecastPoint2D(Vector2D.of(1e-3, 0), Vector2D.Unit.MINUS_Y, line);
        final LinecastPoint2D aDup2 = new LinecastPoint2D(Vector2D.of(1e-3, 1e-3), Vector2D.of(1e-3, -1), eqLine);

        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.ZERO, Vector2D.Unit.MINUS_X, diffLine);
        final LinecastPoint2D bDup = new LinecastPoint2D(Vector2D.of(-1e-3, 1e-4), Vector2D.Unit.MINUS_X, diffLine);

        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(0.5, 0), Vector2D.Unit.MINUS_Y, line);

        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 0), Vector2D.Unit.MINUS_Y, line);

        final List<LinecastPoint2D> list = new ArrayList<>(Arrays.asList(d, aDup1, bDup, b, c, a, aDup2));

        LinecastPoint2D.sortAndFilter(list);


        Assertions.assertSame(b, list.get(0));
    }

    @Test
    void testSortAndFilter_3_oe() {
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final Line line = Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_X, precision);
        final Line eqLine = Lines.fromPointAndDirection(Vector2D.of(1e-3, 1e-3), Vector2D.Unit.PLUS_X, precision);
        final Line diffLine = Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_Y, precision);

        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.ZERO, Vector2D.Unit.MINUS_Y, line);
        final LinecastPoint2D aDup1 = new LinecastPoint2D(Vector2D.of(1e-3, 0), Vector2D.Unit.MINUS_Y, line);
        final LinecastPoint2D aDup2 = new LinecastPoint2D(Vector2D.of(1e-3, 1e-3), Vector2D.of(1e-3, -1), eqLine);

        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.ZERO, Vector2D.Unit.MINUS_X, diffLine);
        final LinecastPoint2D bDup = new LinecastPoint2D(Vector2D.of(-1e-3, 1e-4), Vector2D.Unit.MINUS_X, diffLine);

        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(0.5, 0), Vector2D.Unit.MINUS_Y, line);

        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 0), Vector2D.Unit.MINUS_Y, line);

        final List<LinecastPoint2D> list = new ArrayList<>(Arrays.asList(d, aDup1, bDup, b, c, a, aDup2));

        LinecastPoint2D.sortAndFilter(list);


        Assertions.assertSame(a, list.get(1));
    }

    @Test
    void testSortAndFilter_4_oe() {
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final Line line = Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_X, precision);
        final Line eqLine = Lines.fromPointAndDirection(Vector2D.of(1e-3, 1e-3), Vector2D.Unit.PLUS_X, precision);
        final Line diffLine = Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_Y, precision);

        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.ZERO, Vector2D.Unit.MINUS_Y, line);
        final LinecastPoint2D aDup1 = new LinecastPoint2D(Vector2D.of(1e-3, 0), Vector2D.Unit.MINUS_Y, line);
        final LinecastPoint2D aDup2 = new LinecastPoint2D(Vector2D.of(1e-3, 1e-3), Vector2D.of(1e-3, -1), eqLine);

        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.ZERO, Vector2D.Unit.MINUS_X, diffLine);
        final LinecastPoint2D bDup = new LinecastPoint2D(Vector2D.of(-1e-3, 1e-4), Vector2D.Unit.MINUS_X, diffLine);

        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(0.5, 0), Vector2D.Unit.MINUS_Y, line);

        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 0), Vector2D.Unit.MINUS_Y, line);

        final List<LinecastPoint2D> list = new ArrayList<>(Arrays.asList(d, aDup1, bDup, b, c, a, aDup2));

        LinecastPoint2D.sortAndFilter(list);


        Assertions.assertSame(c, list.get(2));
    }

    @Test
    void testSortAndFilter_5_oe() {
        final Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-2);

        final Line line = Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_X, precision);
        final Line eqLine = Lines.fromPointAndDirection(Vector2D.of(1e-3, 1e-3), Vector2D.Unit.PLUS_X, precision);
        final Line diffLine = Lines.fromPointAndDirection(Vector2D.ZERO, Vector2D.Unit.PLUS_Y, precision);

        final LinecastPoint2D a = new LinecastPoint2D(Vector2D.ZERO, Vector2D.Unit.MINUS_Y, line);
        final LinecastPoint2D aDup1 = new LinecastPoint2D(Vector2D.of(1e-3, 0), Vector2D.Unit.MINUS_Y, line);
        final LinecastPoint2D aDup2 = new LinecastPoint2D(Vector2D.of(1e-3, 1e-3), Vector2D.of(1e-3, -1), eqLine);

        final LinecastPoint2D b = new LinecastPoint2D(Vector2D.ZERO, Vector2D.Unit.MINUS_X, diffLine);
        final LinecastPoint2D bDup = new LinecastPoint2D(Vector2D.of(-1e-3, 1e-4), Vector2D.Unit.MINUS_X, diffLine);

        final LinecastPoint2D c = new LinecastPoint2D(Vector2D.of(0.5, 0), Vector2D.Unit.MINUS_Y, line);

        final LinecastPoint2D d = new LinecastPoint2D(Vector2D.of(1, 0), Vector2D.Unit.MINUS_Y, line);

        final List<LinecastPoint2D> list = new ArrayList<>(Arrays.asList(d, aDup1, bDup, b, c, a, aDup2));

        LinecastPoint2D.sortAndFilter(list);


        Assertions.assertSame(d, list.get(3));
    }

}
