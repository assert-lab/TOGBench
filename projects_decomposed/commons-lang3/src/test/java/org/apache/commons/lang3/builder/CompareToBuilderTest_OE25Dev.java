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
package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.builder.CompareToBuilder}.
 */
public class CompareToBuilderTest_OE25Dev {

    //-----------------------------------------------------------------------

    static class TestObject implements Comparable<TestObject> {
        private int a;
        TestObject(final int a) {
            this.a = a;
        }
        @Override
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof TestObject)) {
                return false;
            }
            final TestObject rhs = (TestObject) o;
            return a == rhs.a;
        }

        @Override
        public int hashCode() {
            return a;
        }

        public void setA(final int a) {
            this.a = a;
        }

        public int getA() {
            return a;
        }
        @Override
        public int compareTo(final TestObject rhs) {
            return Integer.compare(a, rhs.a);
        }
    }

    static class TestSubObject extends TestObject {
        private int b;
        TestSubObject() {
            super(0);
        }
        TestSubObject(final int a, final int b) {
            super(a);
            this.b = b;
        }
        @Override
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof TestSubObject)) {
                return false;
            }
            final TestSubObject rhs = (TestSubObject) o;
            return super.equals(o) && b == rhs.b;
        }
    }

    static class TestTransientSubObject extends TestObject {
        @SuppressWarnings("unused")
        private final transient int t;
        TestTransientSubObject(final int a, final int t) {
            super(a);
            this.t = t;
        }
    }

    @Test
    public void testReflectionHierarchyCompare() {
        testReflectionHierarchyCompare(false, null);
    }

    @Test
    public void testReflectionHierarchyCompareExcludeFields() {
        final String[] excludeFields = new String[] { "b" };
        testReflectionHierarchyCompare(true, excludeFields);

        TestSubObject x;
        TestSubObject y;
        TestSubObject z;

        x = new TestSubObject(1, 1);
        y = new TestSubObject(2, 1);
        z = new TestSubObject(3, 1);
        assertXYZCompareOrder(x, y, z, true, excludeFields);

        x = new TestSubObject(1, 3);
        y = new TestSubObject(2, 2);
        z = new TestSubObject(3, 1);
        assertXYZCompareOrder(x, y, z, true, excludeFields);
    }

    @Test
    public void testReflectionHierarchyCompareTransients() {
        testReflectionHierarchyCompare(true, null);

        TestTransientSubObject x;
        TestTransientSubObject y;
        TestTransientSubObject z;

        x = new TestTransientSubObject(1, 1);
        y = new TestTransientSubObject(2, 2);
        z = new TestTransientSubObject(3, 3);
        assertXYZCompareOrder(x, y, z, true, null);

        x = new TestTransientSubObject(1, 1);
        y = new TestTransientSubObject(1, 2);
        z = new TestTransientSubObject(1, 3);
        assertXYZCompareOrder(x, y, z, true, null);
    }

    private void assertXYZCompareOrder(final Object x, final Object y, final Object z, final boolean testTransients, final String[] excludeFields) {
        assertEquals(0, CompareToBuilder.reflectionCompare(x, x, testTransients, null, excludeFields));
        assertEquals(0, CompareToBuilder.reflectionCompare(y, y, testTransients, null, excludeFields));
        assertEquals(0, CompareToBuilder.reflectionCompare(z, z, testTransients, null, excludeFields));

        assertTrue(0 > CompareToBuilder.reflectionCompare(x, y, testTransients, null, excludeFields));
        assertTrue(0 > CompareToBuilder.reflectionCompare(x, z, testTransients, null, excludeFields));
        assertTrue(0 > CompareToBuilder.reflectionCompare(y, z, testTransients, null, excludeFields));

        assertTrue(0 < CompareToBuilder.reflectionCompare(y, x, testTransients, null, excludeFields));
        assertTrue(0 < CompareToBuilder.reflectionCompare(z, x, testTransients, null, excludeFields));
        assertTrue(0 < CompareToBuilder.reflectionCompare(z, y, testTransients, null, excludeFields));
    }

    private void testReflectionHierarchyCompare(final boolean testTransients, final String[] excludeFields) {
        final TestObject to1 = new TestObject(1);
        final TestObject to2 = new TestObject(2);
        final TestObject to3 = new TestObject(3);
        final TestSubObject tso1 = new TestSubObject(1, 1);
        final TestSubObject tso2 = new TestSubObject(2, 2);
        final TestSubObject tso3 = new TestSubObject(3, 3);

        assertReflectionCompareContract(to1, to1, to1, false, excludeFields);
        assertReflectionCompareContract(to1, to2, to3, false, excludeFields);
        assertReflectionCompareContract(tso1, tso1, tso1, false, excludeFields);
        assertReflectionCompareContract(tso1, tso2, tso3, false, excludeFields);
        assertReflectionCompareContract("1", "2", "3", false, excludeFields);

        assertTrue(0 != CompareToBuilder.reflectionCompare(tso1, new TestSubObject(1, 0), testTransients));
        assertTrue(0 != CompareToBuilder.reflectionCompare(tso1, new TestSubObject(0, 1), testTransients));

        // root class
        assertXYZCompareOrder(to1, to2, to3, true, null);
        // subclass
        assertXYZCompareOrder(tso1, tso2, tso3, true, null);
    }

    /**
     * See "Effective Java" under "Consider Implementing Comparable".
     *
     * @param x an object to compare
     * @param y an object to compare
     * @param z an object to compare
     * @param testTransients Whether to include transients in the comparison
     * @param excludeFields fields to exclude
     */
    private void assertReflectionCompareContract(final Object x, final Object y, final Object z, final boolean testTransients, final String[] excludeFields) {

        // signum
        assertEquals(reflectionCompareSignum(x, y, testTransients, excludeFields), -reflectionCompareSignum(y, x, testTransients, excludeFields));

        // transitive
        if (CompareToBuilder.reflectionCompare(x, y, testTransients, null, excludeFields) > 0
                && CompareToBuilder.reflectionCompare(y, z, testTransients, null, excludeFields) > 0) {
            assertTrue(CompareToBuilder.reflectionCompare(x, z, testTransients, null, excludeFields) > 0);
        }

        // un-named
        if (CompareToBuilder.reflectionCompare(x, y, testTransients, null, excludeFields) == 0) {
            assertEquals(reflectionCompareSignum(x, z, testTransients, excludeFields), -reflectionCompareSignum(y, z, testTransients, excludeFields));
        }

        // strongly recommended but not strictly required
        assertTrue(CompareToBuilder.reflectionCompare(x, y, testTransients) ==0 == EqualsBuilder.reflectionEquals(x, y, testTransients));
    }

    /**
     * Returns the signum of the result of comparing x and y with
     * {@code CompareToBuilder.reflectionCompare}
     *
     * @param lhs The "left-hand-side" of the comparison.
     * @param rhs The "right-hand-side" of the comparison.
     * @param testTransients Whether to include transients in the comparison
     * @param excludeFields fields to exclude
     * @return int The signum
     */
    private int reflectionCompareSignum(final Object lhs, final Object rhs, final boolean testTransients, final String[] excludeFields) {
        return BigInteger.valueOf(CompareToBuilder.reflectionCompare(lhs, rhs, testTransients)).signum();
    }

    @Test
    public void testReflectionCompare_1_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(4);
        assertEquals(0, CompareToBuilder.reflectionCompare(o1, o1));
    }

    @Test
    public void testReflectionCompare_2_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(4);
        // removed other assertion
        assertEquals(0, CompareToBuilder.reflectionCompare(o1, o2));
    }

    @Test
    public void testReflectionCompare_3_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(4);
        // removed other assertion
        // removed other assertion
        o2.setA(5);
        assertTrue(CompareToBuilder.reflectionCompare(o1, o2) < 0);
    }

    @Test
    public void testReflectionCompare_4_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(4);
        // removed other assertion
        // removed other assertion
        o2.setA(5);
        // removed other assertion
        assertTrue(CompareToBuilder.reflectionCompare(o2, o1) > 0);
    }

    @Test
    public void testReflectionCompareEx1_1_oe() {
        final TestObject o1 = new TestObject(4);
        assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(o1, null));
    }

    @Test
    public void testReflectionCompareEx2_1_oe() {
        final TestObject o1 = new TestObject(4);
        final Object o2 = new Object();
        assertThrows(ClassCastException.class, () -> CompareToBuilder.reflectionCompare(o1, o2));
    }

    @Test
    public void testAppendSuper_1_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        assertEquals(0, new CompareToBuilder().appendSuper(0).append(o1, o1).toComparison());
    }

    @Test
    public void testAppendSuper_2_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        assertTrue(new CompareToBuilder().appendSuper(0).append(o1, o2).toComparison() < 0);
    }

    @Test
    public void testAppendSuper_3_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().appendSuper(0).append(o2, o1).toComparison() > 0);
    }

    @Test
    public void testAppendSuper_4_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue(new CompareToBuilder().appendSuper(-1).append(o1, o1).toComparison() < 0);
    }

    @Test
    public void testAppendSuper_5_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(new CompareToBuilder().appendSuper(-1).append(o1, o2).toComparison() < 0);
    }

    @Test
    public void testAppendSuper_6_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertTrue(new CompareToBuilder().appendSuper(1).append(o1, o1).toComparison() > 0);
    }

    @Test
    public void testAppendSuper_7_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(new CompareToBuilder().appendSuper(1).append(o1, o2).toComparison() > 0);
    }

    @Test
    public void testObject_1_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(4);
        assertEquals(0, new CompareToBuilder().append(o1, o1).toComparison());
    }

    @Test
    public void testObject_2_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(4);
        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(o1, o2).toComparison());
    }

    @Test
    public void testObject_3_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(4);
        // removed other assertion
        // removed other assertion
        o2.setA(5);
        assertTrue(new CompareToBuilder().append(o1, o2).toComparison() < 0);
    }

    @Test
    public void testObject_4_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(4);
        // removed other assertion
        // removed other assertion
        o2.setA(5);
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o2, o1).toComparison() > 0);
    }

    @Test
    public void testObject_5_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(4);
        // removed other assertion
        // removed other assertion
        o2.setA(5);
        // removed other assertion
        // removed other assertion

        assertTrue(new CompareToBuilder().append(o1, null).toComparison() > 0);
    }

    @Test
    public void testObject_6_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(4);
        // removed other assertion
        // removed other assertion
        o2.setA(5);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append((Object) null, null).toComparison());
    }

    @Test
    public void testObject_7_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(4);
        // removed other assertion
        // removed other assertion
        o2.setA(5);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(null, o1).toComparison() < 0);
    }

    @Test
    public void testObjectBuild_1_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(4);
        assertEquals(Integer.valueOf(0), new CompareToBuilder().append(o1, o1).build());
    }

    @Test
    public void testObjectBuild_2_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(4);
        // removed other assertion
        assertEquals(Integer.valueOf(0), new CompareToBuilder().append(o1, o2).build());
    }

    @Test
    public void testObjectBuild_3_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(4);
        // removed other assertion
        // removed other assertion
        o2.setA(5);
        assertTrue(new CompareToBuilder().append(o1, o2).build().intValue() < 0);
    }

    @Test
    public void testObjectBuild_4_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(4);
        // removed other assertion
        // removed other assertion
        o2.setA(5);
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o2, o1).build().intValue() > 0);
    }

    @Test
    public void testObjectBuild_5_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(4);
        // removed other assertion
        // removed other assertion
        o2.setA(5);
        // removed other assertion
        // removed other assertion

        assertTrue(new CompareToBuilder().append(o1, null).build().intValue() > 0);
    }

    @Test
    public void testObjectBuild_6_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(4);
        // removed other assertion
        // removed other assertion
        o2.setA(5);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(Integer.valueOf(0), new CompareToBuilder().append((Object) null, null).build());
    }

    @Test
    public void testObjectBuild_7_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(4);
        // removed other assertion
        // removed other assertion
        o2.setA(5);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(null, o1).build().intValue() < 0);
    }

    @Test
    public void testObjectEx2_1_oe() {
        final TestObject o1 = new TestObject(4);
        final Object o2 = new Object();
        assertThrows(ClassCastException.class, () -> new CompareToBuilder().append(o1, o2));
    }

    @Test
    public void testObjectComparator_1_oe() {
        final String o1 = "Fred";
        String o2 = "Fred";
        assertEquals(0, new CompareToBuilder().append(o1, o1, String.CASE_INSENSITIVE_ORDER).toComparison());
    }

    @Test
    public void testObjectComparator_2_oe() {
        final String o1 = "Fred";
        String o2 = "Fred";
        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(o1, o2, String.CASE_INSENSITIVE_ORDER).toComparison());
    }

    @Test
    public void testObjectComparator_3_oe() {
        final String o1 = "Fred";
        String o2 = "Fred";
        // removed other assertion
        // removed other assertion
        o2 = "FRED";
        assertEquals(0, new CompareToBuilder().append(o1, o2, String.CASE_INSENSITIVE_ORDER).toComparison());
    }

    @Test
    public void testObjectComparator_4_oe() {
        final String o1 = "Fred";
        String o2 = "Fred";
        // removed other assertion
        // removed other assertion
        o2 = "FRED";
        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(o2, o1, String.CASE_INSENSITIVE_ORDER).toComparison());
    }

    @Test
    public void testObjectComparator_5_oe() {
        final String o1 = "Fred";
        String o2 = "Fred";
        // removed other assertion
        // removed other assertion
        o2 = "FRED";
        // removed other assertion
        // removed other assertion
        o2 = "FREDA";
        assertTrue(new CompareToBuilder().append(o1, o2, String.CASE_INSENSITIVE_ORDER).toComparison() < 0);
    }

    @Test
    public void testObjectComparator_6_oe() {
        final String o1 = "Fred";
        String o2 = "Fred";
        // removed other assertion
        // removed other assertion
        o2 = "FRED";
        // removed other assertion
        // removed other assertion
        o2 = "FREDA";
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o2, o1, String.CASE_INSENSITIVE_ORDER).toComparison() > 0);
    }

    @Test
    public void testObjectComparator_7_oe() {
        final String o1 = "Fred";
        String o2 = "Fred";
        // removed other assertion
        // removed other assertion
        o2 = "FRED";
        // removed other assertion
        // removed other assertion
        o2 = "FREDA";
        // removed other assertion
        // removed other assertion

        assertTrue(new CompareToBuilder().append(o1, null, String.CASE_INSENSITIVE_ORDER).toComparison() > 0);
    }

    @Test
    public void testObjectComparator_8_oe() {
        final String o1 = "Fred";
        String o2 = "Fred";
        // removed other assertion
        // removed other assertion
        o2 = "FRED";
        // removed other assertion
        // removed other assertion
        o2 = "FREDA";
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(null, null, String.CASE_INSENSITIVE_ORDER).toComparison());
    }

    @Test
    public void testObjectComparator_9_oe() {
        final String o1 = "Fred";
        String o2 = "Fred";
        // removed other assertion
        // removed other assertion
        o2 = "FRED";
        // removed other assertion
        // removed other assertion
        o2 = "FREDA";
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(null, o1, String.CASE_INSENSITIVE_ORDER).toComparison() < 0);
    }

    @Test
    public void testObjectComparatorNull_1_oe() {
        final String o1 = "Fred";
        String o2 = "Fred";
        assertEquals(0, new CompareToBuilder().append(o1, o1, null).toComparison());
    }

    @Test
    public void testObjectComparatorNull_2_oe() {
        final String o1 = "Fred";
        String o2 = "Fred";
        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(o1, o2, null).toComparison());
    }

    @Test
    public void testObjectComparatorNull_3_oe() {
        final String o1 = "Fred";
        String o2 = "Fred";
        // removed other assertion
        // removed other assertion
        o2 = "Zebra";
        assertTrue(new CompareToBuilder().append(o1, o2, null).toComparison() < 0);
    }

    @Test
    public void testObjectComparatorNull_4_oe() {
        final String o1 = "Fred";
        String o2 = "Fred";
        // removed other assertion
        // removed other assertion
        o2 = "Zebra";
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o2, o1, null).toComparison() > 0);
    }

    @Test
    public void testObjectComparatorNull_5_oe() {
        final String o1 = "Fred";
        String o2 = "Fred";
        // removed other assertion
        // removed other assertion
        o2 = "Zebra";
        // removed other assertion
        // removed other assertion

        assertTrue(new CompareToBuilder().append(o1, null, null).toComparison() > 0);
    }

    @Test
    public void testObjectComparatorNull_6_oe() {
        final String o1 = "Fred";
        String o2 = "Fred";
        // removed other assertion
        // removed other assertion
        o2 = "Zebra";
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(null, null, null).toComparison());
    }

    @Test
    public void testObjectComparatorNull_7_oe() {
        final String o1 = "Fred";
        String o2 = "Fred";
        // removed other assertion
        // removed other assertion
        o2 = "Zebra";
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(null, o1, null).toComparison() < 0);
    }

    @Test
    public void testLong_1_oe() {
        final long o1 = 1L;
        final long o2 = 2L;
        assertEquals(0, new CompareToBuilder().append(o1, o1).toComparison());
    }

    @Test
    public void testLong_2_oe() {
        final long o1 = 1L;
        final long o2 = 2L;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o1, o2).toComparison() < 0);
    }

    @Test
    public void testLong_3_oe() {
        final long o1 = 1L;
        final long o2 = 2L;
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o2, o1).toComparison() > 0);
    }

    @Test
    public void testLong_4_oe() {
        final long o1 = 1L;
        final long o2 = 2L;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o1, Long.MAX_VALUE).toComparison() < 0);
    }

    @Test
    public void testLong_5_oe() {
        final long o1 = 1L;
        final long o2 = 2L;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(Long.MAX_VALUE, o1).toComparison() > 0);
    }

    @Test
    public void testLong_6_oe() {
        final long o1 = 1L;
        final long o2 = 2L;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o1, Long.MIN_VALUE).toComparison() > 0);
    }

    @Test
    public void testLong_7_oe() {
        final long o1 = 1L;
        final long o2 = 2L;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(Long.MIN_VALUE, o1).toComparison() < 0);
    }

    @Test
    public void testInt_1_oe() {
        final int o1 = 1;
        final int o2 = 2;
        assertEquals(0, new CompareToBuilder().append(o1, o1).toComparison());
    }

    @Test
    public void testInt_2_oe() {
        final int o1 = 1;
        final int o2 = 2;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o1, o2).toComparison() < 0);
    }

    @Test
    public void testInt_3_oe() {
        final int o1 = 1;
        final int o2 = 2;
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o2, o1).toComparison() > 0);
    }

    @Test
    public void testInt_4_oe() {
        final int o1 = 1;
        final int o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o1, Integer.MAX_VALUE).toComparison() < 0);
    }

    @Test
    public void testInt_5_oe() {
        final int o1 = 1;
        final int o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(Integer.MAX_VALUE, o1).toComparison() > 0);
    }

    @Test
    public void testInt_6_oe() {
        final int o1 = 1;
        final int o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o1, Integer.MIN_VALUE).toComparison() > 0);
    }

    @Test
    public void testInt_7_oe() {
        final int o1 = 1;
        final int o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(Integer.MIN_VALUE, o1).toComparison() < 0);
    }

    @Test
    public void testShort_1_oe() {
        final short o1 = 1;
        final short o2 = 2;
        assertEquals(0, new CompareToBuilder().append(o1, o1).toComparison());
    }

    @Test
    public void testShort_2_oe() {
        final short o1 = 1;
        final short o2 = 2;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o1, o2).toComparison() < 0);
    }

    @Test
    public void testShort_3_oe() {
        final short o1 = 1;
        final short o2 = 2;
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o2, o1).toComparison() > 0);
    }

    @Test
    public void testShort_4_oe() {
        final short o1 = 1;
        final short o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o1, Short.MAX_VALUE).toComparison() < 0);
    }

    @Test
    public void testShort_5_oe() {
        final short o1 = 1;
        final short o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(Short.MAX_VALUE, o1).toComparison() > 0);
    }

    @Test
    public void testShort_6_oe() {
        final short o1 = 1;
        final short o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o1, Short.MIN_VALUE).toComparison() > 0);
    }

    @Test
    public void testShort_7_oe() {
        final short o1 = 1;
        final short o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(Short.MIN_VALUE, o1).toComparison() < 0);
    }

    @Test
    public void testChar_1_oe() {
        final char o1 = 1;
        final char o2 = 2;
        assertEquals(0, new CompareToBuilder().append(o1, o1).toComparison());
    }

    @Test
    public void testChar_2_oe() {
        final char o1 = 1;
        final char o2 = 2;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o1, o2).toComparison() < 0);
    }

    @Test
    public void testChar_3_oe() {
        final char o1 = 1;
        final char o2 = 2;
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o2, o1).toComparison() > 0);
    }

    @Test
    public void testChar_4_oe() {
        final char o1 = 1;
        final char o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o1, Character.MAX_VALUE).toComparison() < 0);
    }

    @Test
    public void testChar_5_oe() {
        final char o1 = 1;
        final char o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(Character.MAX_VALUE, o1).toComparison() > 0);
    }

    @Test
    public void testChar_6_oe() {
        final char o1 = 1;
        final char o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o1, Character.MIN_VALUE).toComparison() > 0);
    }

    @Test
    public void testChar_7_oe() {
        final char o1 = 1;
        final char o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(Character.MIN_VALUE, o1).toComparison() < 0);
    }

    @Test
    public void testByte_1_oe() {
        final byte o1 = 1;
        final byte o2 = 2;
        assertEquals(0, new CompareToBuilder().append(o1, o1).toComparison());
    }

    @Test
    public void testByte_2_oe() {
        final byte o1 = 1;
        final byte o2 = 2;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o1, o2).toComparison() < 0);
    }

    @Test
    public void testByte_3_oe() {
        final byte o1 = 1;
        final byte o2 = 2;
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o2, o1).toComparison() > 0);
    }

    @Test
    public void testByte_4_oe() {
        final byte o1 = 1;
        final byte o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o1, Byte.MAX_VALUE).toComparison() < 0);
    }

    @Test
    public void testByte_5_oe() {
        final byte o1 = 1;
        final byte o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(Byte.MAX_VALUE, o1).toComparison() > 0);
    }

    @Test
    public void testByte_6_oe() {
        final byte o1 = 1;
        final byte o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o1, Byte.MIN_VALUE).toComparison() > 0);
    }

    @Test
    public void testByte_7_oe() {
        final byte o1 = 1;
        final byte o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(Byte.MIN_VALUE, o1).toComparison() < 0);
    }

    @Test
    public void testDouble_1_oe() {
        final double o1 = 1;
        final double o2 = 2;
        assertEquals(0, new CompareToBuilder().append(o1, o1).toComparison());
    }

    @Test
    public void testDouble_2_oe() {
        final double o1 = 1;
        final double o2 = 2;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o1, o2).toComparison() < 0);
    }

    @Test
    public void testDouble_3_oe() {
        final double o1 = 1;
        final double o2 = 2;
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o2, o1).toComparison() > 0);
    }

    @Test
    public void testDouble_4_oe() {
        final double o1 = 1;
        final double o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o1, Double.MAX_VALUE).toComparison() < 0);
    }

    @Test
    public void testDouble_5_oe() {
        final double o1 = 1;
        final double o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(Double.MAX_VALUE, o1).toComparison() > 0);
    }

    @Test
    public void testDouble_6_oe() {
        final double o1 = 1;
        final double o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o1, Double.MIN_VALUE).toComparison() > 0);
    }

    @Test
    public void testDouble_7_oe() {
        final double o1 = 1;
        final double o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(Double.MIN_VALUE, o1).toComparison() < 0);
    }

    @Test
    public void testDouble_8_oe() {
        final double o1 = 1;
        final double o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(Double.NaN, Double.NaN).toComparison());
    }

    @Test
    public void testDouble_9_oe() {
        final double o1 = 1;
        final double o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(Double.NaN, Double.MAX_VALUE).toComparison() > 0);
    }

    @Test
    public void testDouble_10_oe() {
        final double o1 = 1;
        final double o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(Double.POSITIVE_INFINITY, Double.MAX_VALUE).toComparison() > 0);
    }

    @Test
    public void testDouble_11_oe() {
        final double o1 = 1;
        final double o2 = 2;
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
        assertTrue(new CompareToBuilder().append(Double.NEGATIVE_INFINITY, Double.MIN_VALUE).toComparison() < 0);
    }

    @Test
    public void testDouble_12_oe() {
        final double o1 = 1;
        final double o2 = 2;
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
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o1, Double.NaN).toComparison() < 0);
    }

    @Test
    public void testDouble_13_oe() {
        final double o1 = 1;
        final double o2 = 2;
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
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(Double.NaN, o1).toComparison() > 0);
    }

    @Test
    public void testDouble_14_oe() {
        final double o1 = 1;
        final double o2 = 2;
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(-0.0, 0.0).toComparison() < 0);
    }

    @Test
    public void testDouble_15_oe() {
        final double o1 = 1;
        final double o2 = 2;
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(0.0, -0.0).toComparison() > 0);
    }

    @Test
    public void testFloat_1_oe() {
        final float o1 = 1;
        final float o2 = 2;
        assertEquals(0, new CompareToBuilder().append(o1, o1).toComparison());
    }

    @Test
    public void testFloat_2_oe() {
        final float o1 = 1;
        final float o2 = 2;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o1, o2).toComparison() < 0);
    }

    @Test
    public void testFloat_3_oe() {
        final float o1 = 1;
        final float o2 = 2;
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o2, o1).toComparison() > 0);
    }

    @Test
    public void testFloat_4_oe() {
        final float o1 = 1;
        final float o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o1, Float.MAX_VALUE).toComparison() < 0);
    }

    @Test
    public void testFloat_5_oe() {
        final float o1 = 1;
        final float o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(Float.MAX_VALUE, o1).toComparison() > 0);
    }

    @Test
    public void testFloat_6_oe() {
        final float o1 = 1;
        final float o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o1, Float.MIN_VALUE).toComparison() > 0);
    }

    @Test
    public void testFloat_7_oe() {
        final float o1 = 1;
        final float o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(Float.MIN_VALUE, o1).toComparison() < 0);
    }

    @Test
    public void testFloat_8_oe() {
        final float o1 = 1;
        final float o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(Float.NaN, Float.NaN).toComparison());
    }

    @Test
    public void testFloat_9_oe() {
        final float o1 = 1;
        final float o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(Float.NaN, Float.MAX_VALUE).toComparison() > 0);
    }

    @Test
    public void testFloat_10_oe() {
        final float o1 = 1;
        final float o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(Float.POSITIVE_INFINITY, Float.MAX_VALUE).toComparison() > 0);
    }

    @Test
    public void testFloat_11_oe() {
        final float o1 = 1;
        final float o2 = 2;
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
        assertTrue(new CompareToBuilder().append(Float.NEGATIVE_INFINITY, Float.MIN_VALUE).toComparison() < 0);
    }

    @Test
    public void testFloat_12_oe() {
        final float o1 = 1;
        final float o2 = 2;
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
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o1, Float.NaN).toComparison() < 0);
    }

    @Test
    public void testFloat_13_oe() {
        final float o1 = 1;
        final float o2 = 2;
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
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(Float.NaN, o1).toComparison() > 0);
    }

    @Test
    public void testFloat_14_oe() {
        final float o1 = 1;
        final float o2 = 2;
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(-0.0, 0.0).toComparison() < 0);
    }

    @Test
    public void testFloat_15_oe() {
        final float o1 = 1;
        final float o2 = 2;
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(0.0, -0.0).toComparison() > 0);
    }

    @Test
    public void testBoolean_1_oe() {
        final boolean o1 = true;
        final boolean o2 = false;
        assertEquals(0, new CompareToBuilder().append(o1, o1).toComparison());
    }

    @Test
    public void testBoolean_2_oe() {
        final boolean o1 = true;
        final boolean o2 = false;
        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(o2, o2).toComparison());
    }

    @Test
    public void testBoolean_3_oe() {
        final boolean o1 = true;
        final boolean o2 = false;
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o1, o2).toComparison() > 0);
    }

    @Test
    public void testBoolean_4_oe() {
        final boolean o1 = true;
        final boolean o2 = false;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(o2, o1).toComparison() < 0);
    }

    @Test
    public void testObjectArray_1_oe() {
        final TestObject[] obj1 = new TestObject[2];
        obj1[0] = new TestObject(4);
        obj1[1] = new TestObject(5);
        final TestObject[] obj2 = new TestObject[2];
        obj2[0] = new TestObject(4);
        obj2[1] = new TestObject(5);
        final TestObject[] obj3 = new TestObject[3];
        obj3[0] = new TestObject(4);
        obj3[1] = new TestObject(5);
        obj3[2] = new TestObject(6);

        assertEquals(0, new CompareToBuilder().append(obj1, obj1).toComparison());
    }

    @Test
    public void testObjectArray_2_oe() {
        final TestObject[] obj1 = new TestObject[2];
        obj1[0] = new TestObject(4);
        obj1[1] = new TestObject(5);
        final TestObject[] obj2 = new TestObject[2];
        obj2[0] = new TestObject(4);
        obj2[1] = new TestObject(5);
        final TestObject[] obj3 = new TestObject[3];
        obj3[0] = new TestObject(4);
        obj3[1] = new TestObject(5);
        obj3[2] = new TestObject(6);

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(obj1, obj2).toComparison());
    }

    @Test
    public void testObjectArray_3_oe() {
        final TestObject[] obj1 = new TestObject[2];
        obj1[0] = new TestObject(4);
        obj1[1] = new TestObject(5);
        final TestObject[] obj2 = new TestObject[2];
        obj2[0] = new TestObject(4);
        obj2[1] = new TestObject(5);
        final TestObject[] obj3 = new TestObject[3];
        obj3[0] = new TestObject(4);
        obj3[1] = new TestObject(5);
        obj3[2] = new TestObject(6);

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj1, obj3).toComparison() < 0);
    }

    @Test
    public void testObjectArray_4_oe() {
        final TestObject[] obj1 = new TestObject[2];
        obj1[0] = new TestObject(4);
        obj1[1] = new TestObject(5);
        final TestObject[] obj2 = new TestObject[2];
        obj2[0] = new TestObject(4);
        obj2[1] = new TestObject(5);
        final TestObject[] obj3 = new TestObject[3];
        obj3[0] = new TestObject(4);
        obj3[1] = new TestObject(5);
        obj3[2] = new TestObject(6);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj3, obj1).toComparison() > 0);
    }

    @Test
    public void testObjectArray_5_oe() {
        final TestObject[] obj1 = new TestObject[2];
        obj1[0] = new TestObject(4);
        obj1[1] = new TestObject(5);
        final TestObject[] obj2 = new TestObject[2];
        obj2[0] = new TestObject(4);
        obj2[1] = new TestObject(5);
        final TestObject[] obj3 = new TestObject[3];
        obj3[0] = new TestObject(4);
        obj3[1] = new TestObject(5);
        obj3[2] = new TestObject(6);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = new TestObject(7);
        assertTrue(new CompareToBuilder().append(obj1, obj2).toComparison() > 0);
    }

    @Test
    public void testObjectArray_6_oe() {
        final TestObject[] obj1 = new TestObject[2];
        obj1[0] = new TestObject(4);
        obj1[1] = new TestObject(5);
        final TestObject[] obj2 = new TestObject[2];
        obj2[0] = new TestObject(4);
        obj2[1] = new TestObject(5);
        final TestObject[] obj3 = new TestObject[3];
        obj3[0] = new TestObject(4);
        obj3[1] = new TestObject(5);
        obj3[2] = new TestObject(6);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = new TestObject(7);
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj2, obj1).toComparison() < 0);
    }

    @Test
    public void testObjectArray_7_oe() {
        final TestObject[] obj1 = new TestObject[2];
        obj1[0] = new TestObject(4);
        obj1[1] = new TestObject(5);
        final TestObject[] obj2 = new TestObject[2];
        obj2[0] = new TestObject(4);
        obj2[1] = new TestObject(5);
        final TestObject[] obj3 = new TestObject[3];
        obj3[0] = new TestObject(4);
        obj3[1] = new TestObject(5);
        obj3[2] = new TestObject(6);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = new TestObject(7);
        // removed other assertion
        // removed other assertion

        assertTrue(new CompareToBuilder().append(obj1, null).toComparison() > 0);
    }

    @Test
    public void testObjectArray_8_oe() {
        final TestObject[] obj1 = new TestObject[2];
        obj1[0] = new TestObject(4);
        obj1[1] = new TestObject(5);
        final TestObject[] obj2 = new TestObject[2];
        obj2[0] = new TestObject(4);
        obj2[1] = new TestObject(5);
        final TestObject[] obj3 = new TestObject[3];
        obj3[0] = new TestObject(4);
        obj3[1] = new TestObject(5);
        obj3[2] = new TestObject(6);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = new TestObject(7);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append((Object[]) null, null).toComparison());
    }

    @Test
    public void testObjectArray_9_oe() {
        final TestObject[] obj1 = new TestObject[2];
        obj1[0] = new TestObject(4);
        obj1[1] = new TestObject(5);
        final TestObject[] obj2 = new TestObject[2];
        obj2[0] = new TestObject(4);
        obj2[1] = new TestObject(5);
        final TestObject[] obj3 = new TestObject[3];
        obj3[0] = new TestObject(4);
        obj3[1] = new TestObject(5);
        obj3[2] = new TestObject(6);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = new TestObject(7);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(null, obj1).toComparison() < 0);
    }

    @Test
    public void testLongArray_1_oe() {
        final long[] obj1 = new long[2];
        obj1[0] = 5L;
        obj1[1] = 6L;
        final long[] obj2 = new long[2];
        obj2[0] = 5L;
        obj2[1] = 6L;
        final long[] obj3 = new long[3];
        obj3[0] = 5L;
        obj3[1] = 6L;
        obj3[2] = 7L;

        assertEquals(0, new CompareToBuilder().append(obj1, obj1).toComparison());
    }

    @Test
    public void testLongArray_2_oe() {
        final long[] obj1 = new long[2];
        obj1[0] = 5L;
        obj1[1] = 6L;
        final long[] obj2 = new long[2];
        obj2[0] = 5L;
        obj2[1] = 6L;
        final long[] obj3 = new long[3];
        obj3[0] = 5L;
        obj3[1] = 6L;
        obj3[2] = 7L;

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(obj1, obj2).toComparison());
    }

    @Test
    public void testLongArray_3_oe() {
        final long[] obj1 = new long[2];
        obj1[0] = 5L;
        obj1[1] = 6L;
        final long[] obj2 = new long[2];
        obj2[0] = 5L;
        obj2[1] = 6L;
        final long[] obj3 = new long[3];
        obj3[0] = 5L;
        obj3[1] = 6L;
        obj3[2] = 7L;

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj1, obj3).toComparison() < 0);
    }

    @Test
    public void testLongArray_4_oe() {
        final long[] obj1 = new long[2];
        obj1[0] = 5L;
        obj1[1] = 6L;
        final long[] obj2 = new long[2];
        obj2[0] = 5L;
        obj2[1] = 6L;
        final long[] obj3 = new long[3];
        obj3[0] = 5L;
        obj3[1] = 6L;
        obj3[2] = 7L;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj3, obj1).toComparison() > 0);
    }

    @Test
    public void testLongArray_5_oe() {
        final long[] obj1 = new long[2];
        obj1[0] = 5L;
        obj1[1] = 6L;
        final long[] obj2 = new long[2];
        obj2[0] = 5L;
        obj2[1] = 6L;
        final long[] obj3 = new long[3];
        obj3[0] = 5L;
        obj3[1] = 6L;
        obj3[2] = 7L;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        assertTrue(new CompareToBuilder().append(obj1, obj2).toComparison() > 0);
    }

    @Test
    public void testLongArray_6_oe() {
        final long[] obj1 = new long[2];
        obj1[0] = 5L;
        obj1[1] = 6L;
        final long[] obj2 = new long[2];
        obj2[0] = 5L;
        obj2[1] = 6L;
        final long[] obj3 = new long[3];
        obj3[0] = 5L;
        obj3[1] = 6L;
        obj3[2] = 7L;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj2, obj1).toComparison() < 0);
    }

    @Test
    public void testLongArray_7_oe() {
        final long[] obj1 = new long[2];
        obj1[0] = 5L;
        obj1[1] = 6L;
        final long[] obj2 = new long[2];
        obj2[0] = 5L;
        obj2[1] = 6L;
        final long[] obj3 = new long[3];
        obj3[0] = 5L;
        obj3[1] = 6L;
        obj3[2] = 7L;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        // removed other assertion

        assertTrue(new CompareToBuilder().append(obj1, null).toComparison() > 0);
    }

    @Test
    public void testLongArray_8_oe() {
        final long[] obj1 = new long[2];
        obj1[0] = 5L;
        obj1[1] = 6L;
        final long[] obj2 = new long[2];
        obj2[0] = 5L;
        obj2[1] = 6L;
        final long[] obj3 = new long[3];
        obj3[0] = 5L;
        obj3[1] = 6L;
        obj3[2] = 7L;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append((long[]) null, null).toComparison());
    }

    @Test
    public void testLongArray_9_oe() {
        final long[] obj1 = new long[2];
        obj1[0] = 5L;
        obj1[1] = 6L;
        final long[] obj2 = new long[2];
        obj2[0] = 5L;
        obj2[1] = 6L;
        final long[] obj3 = new long[3];
        obj3[0] = 5L;
        obj3[1] = 6L;
        obj3[2] = 7L;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(null, obj1).toComparison() < 0);
    }

    @Test
    public void testIntArray_1_oe() {
        final int[] obj1 = new int[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final int[] obj2 = new int[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final int[] obj3 = new int[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        assertEquals(0, new CompareToBuilder().append(obj1, obj1).toComparison());
    }

    @Test
    public void testIntArray_2_oe() {
        final int[] obj1 = new int[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final int[] obj2 = new int[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final int[] obj3 = new int[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(obj1, obj2).toComparison());
    }

    @Test
    public void testIntArray_3_oe() {
        final int[] obj1 = new int[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final int[] obj2 = new int[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final int[] obj3 = new int[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj1, obj3).toComparison() < 0);
    }

    @Test
    public void testIntArray_4_oe() {
        final int[] obj1 = new int[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final int[] obj2 = new int[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final int[] obj3 = new int[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj3, obj1).toComparison() > 0);
    }

    @Test
    public void testIntArray_5_oe() {
        final int[] obj1 = new int[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final int[] obj2 = new int[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final int[] obj3 = new int[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        assertTrue(new CompareToBuilder().append(obj1, obj2).toComparison() > 0);
    }

    @Test
    public void testIntArray_6_oe() {
        final int[] obj1 = new int[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final int[] obj2 = new int[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final int[] obj3 = new int[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj2, obj1).toComparison() < 0);
    }

    @Test
    public void testIntArray_7_oe() {
        final int[] obj1 = new int[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final int[] obj2 = new int[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final int[] obj3 = new int[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        // removed other assertion

        assertTrue(new CompareToBuilder().append(obj1, null).toComparison() > 0);
    }

    @Test
    public void testIntArray_8_oe() {
        final int[] obj1 = new int[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final int[] obj2 = new int[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final int[] obj3 = new int[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append((int[]) null, null).toComparison());
    }

    @Test
    public void testIntArray_9_oe() {
        final int[] obj1 = new int[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final int[] obj2 = new int[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final int[] obj3 = new int[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(null, obj1).toComparison() < 0);
    }

    @Test
    public void testShortArray_1_oe() {
        final short[] obj1 = new short[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final short[] obj2 = new short[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final short[] obj3 = new short[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        assertEquals(0, new CompareToBuilder().append(obj1, obj1).toComparison());
    }

    @Test
    public void testShortArray_2_oe() {
        final short[] obj1 = new short[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final short[] obj2 = new short[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final short[] obj3 = new short[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(obj1, obj2).toComparison());
    }

    @Test
    public void testShortArray_3_oe() {
        final short[] obj1 = new short[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final short[] obj2 = new short[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final short[] obj3 = new short[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj1, obj3).toComparison() < 0);
    }

    @Test
    public void testShortArray_4_oe() {
        final short[] obj1 = new short[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final short[] obj2 = new short[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final short[] obj3 = new short[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj3, obj1).toComparison() > 0);
    }

    @Test
    public void testShortArray_5_oe() {
        final short[] obj1 = new short[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final short[] obj2 = new short[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final short[] obj3 = new short[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        assertTrue(new CompareToBuilder().append(obj1, obj2).toComparison() > 0);
    }

    @Test
    public void testShortArray_6_oe() {
        final short[] obj1 = new short[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final short[] obj2 = new short[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final short[] obj3 = new short[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj2, obj1).toComparison() < 0);
    }

    @Test
    public void testShortArray_7_oe() {
        final short[] obj1 = new short[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final short[] obj2 = new short[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final short[] obj3 = new short[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        // removed other assertion

        assertTrue(new CompareToBuilder().append(obj1, null).toComparison() > 0);
    }

    @Test
    public void testShortArray_8_oe() {
        final short[] obj1 = new short[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final short[] obj2 = new short[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final short[] obj3 = new short[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append((short[]) null, null).toComparison());
    }

    @Test
    public void testShortArray_9_oe() {
        final short[] obj1 = new short[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final short[] obj2 = new short[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final short[] obj3 = new short[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(null, obj1).toComparison() < 0);
    }

    @Test
    public void testCharArray_1_oe() {
        final char[] obj1 = new char[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final char[] obj2 = new char[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final char[] obj3 = new char[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        assertEquals(0, new CompareToBuilder().append(obj1, obj1).toComparison());
    }

    @Test
    public void testCharArray_2_oe() {
        final char[] obj1 = new char[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final char[] obj2 = new char[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final char[] obj3 = new char[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(obj1, obj2).toComparison());
    }

    @Test
    public void testCharArray_3_oe() {
        final char[] obj1 = new char[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final char[] obj2 = new char[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final char[] obj3 = new char[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj1, obj3).toComparison() < 0);
    }

    @Test
    public void testCharArray_4_oe() {
        final char[] obj1 = new char[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final char[] obj2 = new char[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final char[] obj3 = new char[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj3, obj1).toComparison() > 0);
    }

    @Test
    public void testCharArray_5_oe() {
        final char[] obj1 = new char[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final char[] obj2 = new char[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final char[] obj3 = new char[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        assertTrue(new CompareToBuilder().append(obj1, obj2).toComparison() > 0);
    }

    @Test
    public void testCharArray_6_oe() {
        final char[] obj1 = new char[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final char[] obj2 = new char[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final char[] obj3 = new char[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj2, obj1).toComparison() < 0);
    }

    @Test
    public void testCharArray_7_oe() {
        final char[] obj1 = new char[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final char[] obj2 = new char[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final char[] obj3 = new char[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        // removed other assertion

        assertTrue(new CompareToBuilder().append(obj1, null).toComparison() > 0);
    }

    @Test
    public void testCharArray_8_oe() {
        final char[] obj1 = new char[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final char[] obj2 = new char[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final char[] obj3 = new char[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append((char[]) null, null).toComparison());
    }

    @Test
    public void testCharArray_9_oe() {
        final char[] obj1 = new char[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final char[] obj2 = new char[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final char[] obj3 = new char[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(null, obj1).toComparison() < 0);
    }

    @Test
    public void testByteArray_1_oe() {
        final byte[] obj1 = new byte[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final byte[] obj2 = new byte[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final byte[] obj3 = new byte[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        assertEquals(0, new CompareToBuilder().append(obj1, obj1).toComparison());
    }

    @Test
    public void testByteArray_2_oe() {
        final byte[] obj1 = new byte[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final byte[] obj2 = new byte[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final byte[] obj3 = new byte[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(obj1, obj2).toComparison());
    }

    @Test
    public void testByteArray_3_oe() {
        final byte[] obj1 = new byte[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final byte[] obj2 = new byte[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final byte[] obj3 = new byte[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj1, obj3).toComparison() < 0);
    }

    @Test
    public void testByteArray_4_oe() {
        final byte[] obj1 = new byte[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final byte[] obj2 = new byte[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final byte[] obj3 = new byte[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj3, obj1).toComparison() > 0);
    }

    @Test
    public void testByteArray_5_oe() {
        final byte[] obj1 = new byte[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final byte[] obj2 = new byte[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final byte[] obj3 = new byte[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        assertTrue(new CompareToBuilder().append(obj1, obj2).toComparison() > 0);
    }

    @Test
    public void testByteArray_6_oe() {
        final byte[] obj1 = new byte[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final byte[] obj2 = new byte[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final byte[] obj3 = new byte[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj2, obj1).toComparison() < 0);
    }

    @Test
    public void testByteArray_7_oe() {
        final byte[] obj1 = new byte[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final byte[] obj2 = new byte[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final byte[] obj3 = new byte[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        // removed other assertion

        assertTrue(new CompareToBuilder().append(obj1, null).toComparison() > 0);
    }

    @Test
    public void testByteArray_8_oe() {
        final byte[] obj1 = new byte[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final byte[] obj2 = new byte[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final byte[] obj3 = new byte[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append((byte[]) null, null).toComparison());
    }

    @Test
    public void testByteArray_9_oe() {
        final byte[] obj1 = new byte[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final byte[] obj2 = new byte[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final byte[] obj3 = new byte[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(null, obj1).toComparison() < 0);
    }

    @Test
    public void testDoubleArray_1_oe() {
        final double[] obj1 = new double[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final double[] obj2 = new double[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final double[] obj3 = new double[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        assertEquals(0, new CompareToBuilder().append(obj1, obj1).toComparison());
    }

    @Test
    public void testDoubleArray_2_oe() {
        final double[] obj1 = new double[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final double[] obj2 = new double[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final double[] obj3 = new double[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(obj1, obj2).toComparison());
    }

    @Test
    public void testDoubleArray_3_oe() {
        final double[] obj1 = new double[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final double[] obj2 = new double[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final double[] obj3 = new double[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj1, obj3).toComparison() < 0);
    }

    @Test
    public void testDoubleArray_4_oe() {
        final double[] obj1 = new double[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final double[] obj2 = new double[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final double[] obj3 = new double[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj3, obj1).toComparison() > 0);
    }

    @Test
    public void testDoubleArray_5_oe() {
        final double[] obj1 = new double[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final double[] obj2 = new double[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final double[] obj3 = new double[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        assertTrue(new CompareToBuilder().append(obj1, obj2).toComparison() > 0);
    }

    @Test
    public void testDoubleArray_6_oe() {
        final double[] obj1 = new double[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final double[] obj2 = new double[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final double[] obj3 = new double[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj2, obj1).toComparison() < 0);
    }

    @Test
    public void testDoubleArray_7_oe() {
        final double[] obj1 = new double[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final double[] obj2 = new double[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final double[] obj3 = new double[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        // removed other assertion

        assertTrue(new CompareToBuilder().append(obj1, null).toComparison() > 0);
    }

    @Test
    public void testDoubleArray_8_oe() {
        final double[] obj1 = new double[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final double[] obj2 = new double[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final double[] obj3 = new double[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append((double[]) null, null).toComparison());
    }

    @Test
    public void testDoubleArray_9_oe() {
        final double[] obj1 = new double[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final double[] obj2 = new double[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final double[] obj3 = new double[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(null, obj1).toComparison() < 0);
    }

    @Test
    public void testFloatArray_1_oe() {
        final float[] obj1 = new float[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final float[] obj2 = new float[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final float[] obj3 = new float[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        assertEquals(0, new CompareToBuilder().append(obj1, obj1).toComparison());
    }

    @Test
    public void testFloatArray_2_oe() {
        final float[] obj1 = new float[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final float[] obj2 = new float[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final float[] obj3 = new float[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(obj1, obj2).toComparison());
    }

    @Test
    public void testFloatArray_3_oe() {
        final float[] obj1 = new float[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final float[] obj2 = new float[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final float[] obj3 = new float[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj1, obj3).toComparison() < 0);
    }

    @Test
    public void testFloatArray_4_oe() {
        final float[] obj1 = new float[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final float[] obj2 = new float[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final float[] obj3 = new float[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj3, obj1).toComparison() > 0);
    }

    @Test
    public void testFloatArray_5_oe() {
        final float[] obj1 = new float[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final float[] obj2 = new float[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final float[] obj3 = new float[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        assertTrue(new CompareToBuilder().append(obj1, obj2).toComparison() > 0);
    }

    @Test
    public void testFloatArray_6_oe() {
        final float[] obj1 = new float[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final float[] obj2 = new float[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final float[] obj3 = new float[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj2, obj1).toComparison() < 0);
    }

    @Test
    public void testFloatArray_7_oe() {
        final float[] obj1 = new float[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final float[] obj2 = new float[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final float[] obj3 = new float[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        // removed other assertion

        assertTrue(new CompareToBuilder().append(obj1, null).toComparison() > 0);
    }

    @Test
    public void testFloatArray_8_oe() {
        final float[] obj1 = new float[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final float[] obj2 = new float[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final float[] obj3 = new float[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append((float[]) null, null).toComparison());
    }

    @Test
    public void testFloatArray_9_oe() {
        final float[] obj1 = new float[2];
        obj1[0] = 5;
        obj1[1] = 6;
        final float[] obj2 = new float[2];
        obj2[0] = 5;
        obj2[1] = 6;
        final float[] obj3 = new float[3];
        obj3[0] = 5;
        obj3[1] = 6;
        obj3[2] = 7;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = 7;
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(null, obj1).toComparison() < 0);
    }

    @Test
    public void testBooleanArray_1_oe() {
        final boolean[] obj1 = new boolean[2];
        obj1[0] = true;
        obj1[1] = false;
        final boolean[] obj2 = new boolean[2];
        obj2[0] = true;
        obj2[1] = false;
        final boolean[] obj3 = new boolean[3];
        obj3[0] = true;
        obj3[1] = false;
        obj3[2] = true;

        assertEquals(0, new CompareToBuilder().append(obj1, obj1).toComparison());
    }

    @Test
    public void testBooleanArray_2_oe() {
        final boolean[] obj1 = new boolean[2];
        obj1[0] = true;
        obj1[1] = false;
        final boolean[] obj2 = new boolean[2];
        obj2[0] = true;
        obj2[1] = false;
        final boolean[] obj3 = new boolean[3];
        obj3[0] = true;
        obj3[1] = false;
        obj3[2] = true;

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(obj1, obj2).toComparison());
    }

    @Test
    public void testBooleanArray_3_oe() {
        final boolean[] obj1 = new boolean[2];
        obj1[0] = true;
        obj1[1] = false;
        final boolean[] obj2 = new boolean[2];
        obj2[0] = true;
        obj2[1] = false;
        final boolean[] obj3 = new boolean[3];
        obj3[0] = true;
        obj3[1] = false;
        obj3[2] = true;

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj1, obj3).toComparison() < 0);
    }

    @Test
    public void testBooleanArray_4_oe() {
        final boolean[] obj1 = new boolean[2];
        obj1[0] = true;
        obj1[1] = false;
        final boolean[] obj2 = new boolean[2];
        obj2[0] = true;
        obj2[1] = false;
        final boolean[] obj3 = new boolean[3];
        obj3[0] = true;
        obj3[1] = false;
        obj3[2] = true;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj3, obj1).toComparison() > 0);
    }

    @Test
    public void testBooleanArray_5_oe() {
        final boolean[] obj1 = new boolean[2];
        obj1[0] = true;
        obj1[1] = false;
        final boolean[] obj2 = new boolean[2];
        obj2[0] = true;
        obj2[1] = false;
        final boolean[] obj3 = new boolean[3];
        obj3[0] = true;
        obj3[1] = false;
        obj3[2] = true;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = true;
        assertTrue(new CompareToBuilder().append(obj1, obj2).toComparison() > 0);
    }

    @Test
    public void testBooleanArray_6_oe() {
        final boolean[] obj1 = new boolean[2];
        obj1[0] = true;
        obj1[1] = false;
        final boolean[] obj2 = new boolean[2];
        obj2[0] = true;
        obj2[1] = false;
        final boolean[] obj3 = new boolean[3];
        obj3[0] = true;
        obj3[1] = false;
        obj3[2] = true;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = true;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj2, obj1).toComparison() < 0);
    }

    @Test
    public void testBooleanArray_7_oe() {
        final boolean[] obj1 = new boolean[2];
        obj1[0] = true;
        obj1[1] = false;
        final boolean[] obj2 = new boolean[2];
        obj2[0] = true;
        obj2[1] = false;
        final boolean[] obj3 = new boolean[3];
        obj3[0] = true;
        obj3[1] = false;
        obj3[2] = true;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = true;
        // removed other assertion
        // removed other assertion

        assertTrue(new CompareToBuilder().append(obj1, null).toComparison() > 0);
    }

    @Test
    public void testBooleanArray_8_oe() {
        final boolean[] obj1 = new boolean[2];
        obj1[0] = true;
        obj1[1] = false;
        final boolean[] obj2 = new boolean[2];
        obj2[0] = true;
        obj2[1] = false;
        final boolean[] obj3 = new boolean[3];
        obj3[0] = true;
        obj3[1] = false;
        obj3[2] = true;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = true;
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append((boolean[]) null, null).toComparison());
    }

    @Test
    public void testBooleanArray_9_oe() {
        final boolean[] obj1 = new boolean[2];
        obj1[0] = true;
        obj1[1] = false;
        final boolean[] obj2 = new boolean[2];
        obj2[0] = true;
        obj2[1] = false;
        final boolean[] obj3 = new boolean[3];
        obj3[0] = true;
        obj3[1] = false;
        obj3[2] = true;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        obj1[1] = true;
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(null, obj1).toComparison() < 0);
    }

    @Test
    public void testMultiLongArray_1_oe() {
        final long[][] array1 = new long[2][2];
        final long[][] array2 = new long[2][2];
        final long[][] array3 = new long[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        assertEquals(0, new CompareToBuilder().append(array1, array1).toComparison());
    }

    @Test
    public void testMultiLongArray_2_oe() {
        final long[][] array1 = new long[2][2];
        final long[][] array2 = new long[2][2];
        final long[][] array3 = new long[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(array1, array2).toComparison());
    }

    @Test
    public void testMultiLongArray_3_oe() {
        final long[][] array1 = new long[2][2];
        final long[][] array2 = new long[2][2];
        final long[][] array3 = new long[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array1, array3).toComparison() < 0);
    }

    @Test
    public void testMultiLongArray_4_oe() {
        final long[][] array1 = new long[2][2];
        final long[][] array2 = new long[2][2];
        final long[][] array3 = new long[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array3, array1).toComparison() > 0);
    }

    @Test
    public void testMultiLongArray_5_oe() {
        final long[][] array1 = new long[2][2];
        final long[][] array2 = new long[2][2];
        final long[][] array3 = new long[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        array1[1][1] = 200;
        assertTrue(new CompareToBuilder().append(array1, array2).toComparison() > 0);
    }

    @Test
    public void testMultiLongArray_6_oe() {
        final long[][] array1 = new long[2][2];
        final long[][] array2 = new long[2][2];
        final long[][] array3 = new long[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        array1[1][1] = 200;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array2, array1).toComparison() < 0);
    }

    @Test
    public void testMultiIntArray_1_oe() {
        final int[][] array1 = new int[2][2];
        final int[][] array2 = new int[2][2];
        final int[][] array3 = new int[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        assertEquals(0, new CompareToBuilder().append(array1, array1).toComparison());
    }

    @Test
    public void testMultiIntArray_2_oe() {
        final int[][] array1 = new int[2][2];
        final int[][] array2 = new int[2][2];
        final int[][] array3 = new int[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(array1, array2).toComparison());
    }

    @Test
    public void testMultiIntArray_3_oe() {
        final int[][] array1 = new int[2][2];
        final int[][] array2 = new int[2][2];
        final int[][] array3 = new int[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array1, array3).toComparison() < 0);
    }

    @Test
    public void testMultiIntArray_4_oe() {
        final int[][] array1 = new int[2][2];
        final int[][] array2 = new int[2][2];
        final int[][] array3 = new int[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array3, array1).toComparison() > 0);
    }

    @Test
    public void testMultiIntArray_5_oe() {
        final int[][] array1 = new int[2][2];
        final int[][] array2 = new int[2][2];
        final int[][] array3 = new int[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        array1[1][1] = 200;
        assertTrue(new CompareToBuilder().append(array1, array2).toComparison() > 0);
    }

    @Test
    public void testMultiIntArray_6_oe() {
        final int[][] array1 = new int[2][2];
        final int[][] array2 = new int[2][2];
        final int[][] array3 = new int[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        array1[1][1] = 200;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array2, array1).toComparison() < 0);
    }

    @Test
    public void testMultiShortArray_1_oe() {
        final short[][] array1 = new short[2][2];
        final short[][] array2 = new short[2][2];
        final short[][] array3 = new short[2][3];
        for (short i = 0; i < array1.length; ++i) {
            for (short j = 0; j < array1[0].length; j++) {
                array1[i][j] = (short) ((i + 1) * (j + 1));
                array2[i][j] = (short) ((i + 1) * (j + 1));
                array3[i][j] = (short) ((i + 1) * (j + 1));
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        assertEquals(0, new CompareToBuilder().append(array1, array1).toComparison());
    }

    @Test
    public void testMultiShortArray_2_oe() {
        final short[][] array1 = new short[2][2];
        final short[][] array2 = new short[2][2];
        final short[][] array3 = new short[2][3];
        for (short i = 0; i < array1.length; ++i) {
            for (short j = 0; j < array1[0].length; j++) {
                array1[i][j] = (short) ((i + 1) * (j + 1));
                array2[i][j] = (short) ((i + 1) * (j + 1));
                array3[i][j] = (short) ((i + 1) * (j + 1));
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(array1, array2).toComparison());
    }

    @Test
    public void testMultiShortArray_3_oe() {
        final short[][] array1 = new short[2][2];
        final short[][] array2 = new short[2][2];
        final short[][] array3 = new short[2][3];
        for (short i = 0; i < array1.length; ++i) {
            for (short j = 0; j < array1[0].length; j++) {
                array1[i][j] = (short) ((i + 1) * (j + 1));
                array2[i][j] = (short) ((i + 1) * (j + 1));
                array3[i][j] = (short) ((i + 1) * (j + 1));
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array1, array3).toComparison() < 0);
    }

    @Test
    public void testMultiShortArray_4_oe() {
        final short[][] array1 = new short[2][2];
        final short[][] array2 = new short[2][2];
        final short[][] array3 = new short[2][3];
        for (short i = 0; i < array1.length; ++i) {
            for (short j = 0; j < array1[0].length; j++) {
                array1[i][j] = (short) ((i + 1) * (j + 1));
                array2[i][j] = (short) ((i + 1) * (j + 1));
                array3[i][j] = (short) ((i + 1) * (j + 1));
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array3, array1).toComparison() > 0);
    }

    @Test
    public void testMultiShortArray_5_oe() {
        final short[][] array1 = new short[2][2];
        final short[][] array2 = new short[2][2];
        final short[][] array3 = new short[2][3];
        for (short i = 0; i < array1.length; ++i) {
            for (short j = 0; j < array1[0].length; j++) {
                array1[i][j] = (short) ((i + 1) * (j + 1));
                array2[i][j] = (short) ((i + 1) * (j + 1));
                array3[i][j] = (short) ((i + 1) * (j + 1));
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        array1[1][1] = 200;
        assertTrue(new CompareToBuilder().append(array1, array2).toComparison() > 0);
    }

    @Test
    public void testMultiShortArray_6_oe() {
        final short[][] array1 = new short[2][2];
        final short[][] array2 = new short[2][2];
        final short[][] array3 = new short[2][3];
        for (short i = 0; i < array1.length; ++i) {
            for (short j = 0; j < array1[0].length; j++) {
                array1[i][j] = (short) ((i + 1) * (j + 1));
                array2[i][j] = (short) ((i + 1) * (j + 1));
                array3[i][j] = (short) ((i + 1) * (j + 1));
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        array1[1][1] = 200;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array2, array1).toComparison() < 0);
    }

    @Test
    public void testMultiCharArray_1_oe() {
        final char[][] array1 = new char[2][2];
        final char[][] array2 = new char[2][2];
        final char[][] array3 = new char[2][3];
        for (short i = 0; i < array1.length; ++i) {
            for (short j = 0; j < array1[0].length; j++) {
                array1[i][j] = (char) ((i + 1) * (j + 1));
                array2[i][j] = (char) ((i + 1) * (j + 1));
                array3[i][j] = (char) ((i + 1) * (j + 1));
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        assertEquals(0, new CompareToBuilder().append(array1, array1).toComparison());
    }

    @Test
    public void testMultiCharArray_2_oe() {
        final char[][] array1 = new char[2][2];
        final char[][] array2 = new char[2][2];
        final char[][] array3 = new char[2][3];
        for (short i = 0; i < array1.length; ++i) {
            for (short j = 0; j < array1[0].length; j++) {
                array1[i][j] = (char) ((i + 1) * (j + 1));
                array2[i][j] = (char) ((i + 1) * (j + 1));
                array3[i][j] = (char) ((i + 1) * (j + 1));
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(array1, array2).toComparison());
    }

    @Test
    public void testMultiCharArray_3_oe() {
        final char[][] array1 = new char[2][2];
        final char[][] array2 = new char[2][2];
        final char[][] array3 = new char[2][3];
        for (short i = 0; i < array1.length; ++i) {
            for (short j = 0; j < array1[0].length; j++) {
                array1[i][j] = (char) ((i + 1) * (j + 1));
                array2[i][j] = (char) ((i + 1) * (j + 1));
                array3[i][j] = (char) ((i + 1) * (j + 1));
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array1, array3).toComparison() < 0);
    }

    @Test
    public void testMultiCharArray_4_oe() {
        final char[][] array1 = new char[2][2];
        final char[][] array2 = new char[2][2];
        final char[][] array3 = new char[2][3];
        for (short i = 0; i < array1.length; ++i) {
            for (short j = 0; j < array1[0].length; j++) {
                array1[i][j] = (char) ((i + 1) * (j + 1));
                array2[i][j] = (char) ((i + 1) * (j + 1));
                array3[i][j] = (char) ((i + 1) * (j + 1));
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array3, array1).toComparison() > 0);
    }

    @Test
    public void testMultiCharArray_5_oe() {
        final char[][] array1 = new char[2][2];
        final char[][] array2 = new char[2][2];
        final char[][] array3 = new char[2][3];
        for (short i = 0; i < array1.length; ++i) {
            for (short j = 0; j < array1[0].length; j++) {
                array1[i][j] = (char) ((i + 1) * (j + 1));
                array2[i][j] = (char) ((i + 1) * (j + 1));
                array3[i][j] = (char) ((i + 1) * (j + 1));
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        array1[1][1] = 200;
        assertTrue(new CompareToBuilder().append(array1, array2).toComparison() > 0);
    }

    @Test
    public void testMultiCharArray_6_oe() {
        final char[][] array1 = new char[2][2];
        final char[][] array2 = new char[2][2];
        final char[][] array3 = new char[2][3];
        for (short i = 0; i < array1.length; ++i) {
            for (short j = 0; j < array1[0].length; j++) {
                array1[i][j] = (char) ((i + 1) * (j + 1));
                array2[i][j] = (char) ((i + 1) * (j + 1));
                array3[i][j] = (char) ((i + 1) * (j + 1));
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        array1[1][1] = 200;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array2, array1).toComparison() < 0);
    }

    @Test
    public void testMultiByteArray_1_oe() {
        final byte[][] array1 = new byte[2][2];
        final byte[][] array2 = new byte[2][2];
        final byte[][] array3 = new byte[2][3];
        for (byte i = 0; i < array1.length; ++i) {
            for (byte j = 0; j < array1[0].length; j++) {
                array1[i][j] = (byte) ((i + 1) * (j + 1));
                array2[i][j] = (byte) ((i + 1) * (j + 1));
                array3[i][j] = (byte) ((i + 1) * (j + 1));
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        assertEquals(0, new CompareToBuilder().append(array1, array1).toComparison());
    }

    @Test
    public void testMultiByteArray_2_oe() {
        final byte[][] array1 = new byte[2][2];
        final byte[][] array2 = new byte[2][2];
        final byte[][] array3 = new byte[2][3];
        for (byte i = 0; i < array1.length; ++i) {
            for (byte j = 0; j < array1[0].length; j++) {
                array1[i][j] = (byte) ((i + 1) * (j + 1));
                array2[i][j] = (byte) ((i + 1) * (j + 1));
                array3[i][j] = (byte) ((i + 1) * (j + 1));
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(array1, array2).toComparison());
    }

    @Test
    public void testMultiByteArray_3_oe() {
        final byte[][] array1 = new byte[2][2];
        final byte[][] array2 = new byte[2][2];
        final byte[][] array3 = new byte[2][3];
        for (byte i = 0; i < array1.length; ++i) {
            for (byte j = 0; j < array1[0].length; j++) {
                array1[i][j] = (byte) ((i + 1) * (j + 1));
                array2[i][j] = (byte) ((i + 1) * (j + 1));
                array3[i][j] = (byte) ((i + 1) * (j + 1));
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array1, array3).toComparison() < 0);
    }

    @Test
    public void testMultiByteArray_4_oe() {
        final byte[][] array1 = new byte[2][2];
        final byte[][] array2 = new byte[2][2];
        final byte[][] array3 = new byte[2][3];
        for (byte i = 0; i < array1.length; ++i) {
            for (byte j = 0; j < array1[0].length; j++) {
                array1[i][j] = (byte) ((i + 1) * (j + 1));
                array2[i][j] = (byte) ((i + 1) * (j + 1));
                array3[i][j] = (byte) ((i + 1) * (j + 1));
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array3, array1).toComparison() > 0);
    }

    @Test
    public void testMultiByteArray_5_oe() {
        final byte[][] array1 = new byte[2][2];
        final byte[][] array2 = new byte[2][2];
        final byte[][] array3 = new byte[2][3];
        for (byte i = 0; i < array1.length; ++i) {
            for (byte j = 0; j < array1[0].length; j++) {
                array1[i][j] = (byte) ((i + 1) * (j + 1));
                array2[i][j] = (byte) ((i + 1) * (j + 1));
                array3[i][j] = (byte) ((i + 1) * (j + 1));
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        array1[1][1] = 127;
        assertTrue(new CompareToBuilder().append(array1, array2).toComparison() > 0);
    }

    @Test
    public void testMultiByteArray_6_oe() {
        final byte[][] array1 = new byte[2][2];
        final byte[][] array2 = new byte[2][2];
        final byte[][] array3 = new byte[2][3];
        for (byte i = 0; i < array1.length; ++i) {
            for (byte j = 0; j < array1[0].length; j++) {
                array1[i][j] = (byte) ((i + 1) * (j + 1));
                array2[i][j] = (byte) ((i + 1) * (j + 1));
                array3[i][j] = (byte) ((i + 1) * (j + 1));
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        array1[1][1] = 127;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array2, array1).toComparison() < 0);
    }

    @Test
    public void testMultiFloatArray_1_oe() {
        final float[][] array1 = new float[2][2];
        final float[][] array2 = new float[2][2];
        final float[][] array3 = new float[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        assertEquals(0, new CompareToBuilder().append(array1, array1).toComparison());
    }

    @Test
    public void testMultiFloatArray_2_oe() {
        final float[][] array1 = new float[2][2];
        final float[][] array2 = new float[2][2];
        final float[][] array3 = new float[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(array1, array2).toComparison());
    }

    @Test
    public void testMultiFloatArray_3_oe() {
        final float[][] array1 = new float[2][2];
        final float[][] array2 = new float[2][2];
        final float[][] array3 = new float[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array1, array3).toComparison() < 0);
    }

    @Test
    public void testMultiFloatArray_4_oe() {
        final float[][] array1 = new float[2][2];
        final float[][] array2 = new float[2][2];
        final float[][] array3 = new float[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array3, array1).toComparison() > 0);
    }

    @Test
    public void testMultiFloatArray_5_oe() {
        final float[][] array1 = new float[2][2];
        final float[][] array2 = new float[2][2];
        final float[][] array3 = new float[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        array1[1][1] = 127;
        assertTrue(new CompareToBuilder().append(array1, array2).toComparison() > 0);
    }

    @Test
    public void testMultiFloatArray_6_oe() {
        final float[][] array1 = new float[2][2];
        final float[][] array2 = new float[2][2];
        final float[][] array3 = new float[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        array1[1][1] = 127;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array2, array1).toComparison() < 0);
    }

    @Test
    public void testMultiDoubleArray_1_oe() {
        final double[][] array1 = new double[2][2];
        final double[][] array2 = new double[2][2];
        final double[][] array3 = new double[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        assertEquals(0, new CompareToBuilder().append(array1, array1).toComparison());
    }

    @Test
    public void testMultiDoubleArray_2_oe() {
        final double[][] array1 = new double[2][2];
        final double[][] array2 = new double[2][2];
        final double[][] array3 = new double[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(array1, array2).toComparison());
    }

    @Test
    public void testMultiDoubleArray_3_oe() {
        final double[][] array1 = new double[2][2];
        final double[][] array2 = new double[2][2];
        final double[][] array3 = new double[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array1, array3).toComparison() < 0);
    }

    @Test
    public void testMultiDoubleArray_4_oe() {
        final double[][] array1 = new double[2][2];
        final double[][] array2 = new double[2][2];
        final double[][] array3 = new double[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array3, array1).toComparison() > 0);
    }

    @Test
    public void testMultiDoubleArray_5_oe() {
        final double[][] array1 = new double[2][2];
        final double[][] array2 = new double[2][2];
        final double[][] array3 = new double[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        array1[1][1] = 127;
        assertTrue(new CompareToBuilder().append(array1, array2).toComparison() > 0);
    }

    @Test
    public void testMultiDoubleArray_6_oe() {
        final double[][] array1 = new double[2][2];
        final double[][] array2 = new double[2][2];
        final double[][] array3 = new double[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        array1[1][1] = 127;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array2, array1).toComparison() < 0);
    }

    @Test
    public void testMultiBooleanArray_1_oe() {
        final boolean[][] array1 = new boolean[2][2];
        final boolean[][] array2 = new boolean[2][2];
        final boolean[][] array3 = new boolean[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = i == 1 ^ j == 1;
                array2[i][j] = i == 1 ^ j == 1;
                array3[i][j] = i == 1 ^ j == 1;
            }
        }
        array3[1][2] = false;
        array3[1][2] = false;

        assertEquals(0, new CompareToBuilder().append(array1, array1).toComparison());
    }

    @Test
    public void testMultiBooleanArray_2_oe() {
        final boolean[][] array1 = new boolean[2][2];
        final boolean[][] array2 = new boolean[2][2];
        final boolean[][] array3 = new boolean[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = i == 1 ^ j == 1;
                array2[i][j] = i == 1 ^ j == 1;
                array3[i][j] = i == 1 ^ j == 1;
            }
        }
        array3[1][2] = false;
        array3[1][2] = false;

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(array1, array2).toComparison());
    }

    @Test
    public void testMultiBooleanArray_3_oe() {
        final boolean[][] array1 = new boolean[2][2];
        final boolean[][] array2 = new boolean[2][2];
        final boolean[][] array3 = new boolean[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = i == 1 ^ j == 1;
                array2[i][j] = i == 1 ^ j == 1;
                array3[i][j] = i == 1 ^ j == 1;
            }
        }
        array3[1][2] = false;
        array3[1][2] = false;

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array1, array3).toComparison() < 0);
    }

    @Test
    public void testMultiBooleanArray_4_oe() {
        final boolean[][] array1 = new boolean[2][2];
        final boolean[][] array2 = new boolean[2][2];
        final boolean[][] array3 = new boolean[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = i == 1 ^ j == 1;
                array2[i][j] = i == 1 ^ j == 1;
                array3[i][j] = i == 1 ^ j == 1;
            }
        }
        array3[1][2] = false;
        array3[1][2] = false;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array3, array1).toComparison() > 0);
    }

    @Test
    public void testMultiBooleanArray_5_oe() {
        final boolean[][] array1 = new boolean[2][2];
        final boolean[][] array2 = new boolean[2][2];
        final boolean[][] array3 = new boolean[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = i == 1 ^ j == 1;
                array2[i][j] = i == 1 ^ j == 1;
                array3[i][j] = i == 1 ^ j == 1;
            }
        }
        array3[1][2] = false;
        array3[1][2] = false;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        array1[1][1] = true;
        assertTrue(new CompareToBuilder().append(array1, array2).toComparison() > 0);
    }

    @Test
    public void testMultiBooleanArray_6_oe() {
        final boolean[][] array1 = new boolean[2][2];
        final boolean[][] array2 = new boolean[2][2];
        final boolean[][] array3 = new boolean[2][3];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = i == 1 ^ j == 1;
                array2[i][j] = i == 1 ^ j == 1;
                array3[i][j] = i == 1 ^ j == 1;
            }
        }
        array3[1][2] = false;
        array3[1][2] = false;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        array1[1][1] = true;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array2, array1).toComparison() < 0);
    }

    @Test
    public void testRaggedArray_1_oe() {
        final long[][] array1 = new long[2][];
        final long[][] array2 = new long[2][];
        final long[][] array3 = new long[3][];
        for (int i = 0; i < array1.length; ++i) {
            array1[i] = new long[2];
            array2[i] = new long[2];
            array3[i] = new long[3];
            for (int j = 0; j < array1[i].length; ++j) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;


        assertEquals(0, new CompareToBuilder().append(array1, array1).toComparison());
    }

    @Test
    public void testRaggedArray_2_oe() {
        final long[][] array1 = new long[2][];
        final long[][] array2 = new long[2][];
        final long[][] array3 = new long[3][];
        for (int i = 0; i < array1.length; ++i) {
            array1[i] = new long[2];
            array2[i] = new long[2];
            array3[i] = new long[3];
            for (int j = 0; j < array1[i].length; ++j) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;


        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(array1, array2).toComparison());
    }

    @Test
    public void testRaggedArray_3_oe() {
        final long[][] array1 = new long[2][];
        final long[][] array2 = new long[2][];
        final long[][] array3 = new long[3][];
        for (int i = 0; i < array1.length; ++i) {
            array1[i] = new long[2];
            array2[i] = new long[2];
            array3[i] = new long[3];
            for (int j = 0; j < array1[i].length; ++j) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;


        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array1, array3).toComparison() < 0);
    }

    @Test
    public void testRaggedArray_4_oe() {
        final long[][] array1 = new long[2][];
        final long[][] array2 = new long[2][];
        final long[][] array3 = new long[3][];
        for (int i = 0; i < array1.length; ++i) {
            array1[i] = new long[2];
            array2[i] = new long[2];
            array3[i] = new long[3];
            for (int j = 0; j < array1[i].length; ++j) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;


        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array3, array1).toComparison() > 0);
    }

    @Test
    public void testRaggedArray_5_oe() {
        final long[][] array1 = new long[2][];
        final long[][] array2 = new long[2][];
        final long[][] array3 = new long[3][];
        for (int i = 0; i < array1.length; ++i) {
            array1[i] = new long[2];
            array2[i] = new long[2];
            array3[i] = new long[3];
            for (int j = 0; j < array1[i].length; ++j) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;


        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        array1[1][1] = 200;
        assertTrue(new CompareToBuilder().append(array1, array2).toComparison() > 0);
    }

    @Test
    public void testRaggedArray_6_oe() {
        final long[][] array1 = new long[2][];
        final long[][] array2 = new long[2][];
        final long[][] array3 = new long[3][];
        for (int i = 0; i < array1.length; ++i) {
            array1[i] = new long[2];
            array2[i] = new long[2];
            array3[i] = new long[3];
            for (int j = 0; j < array1[i].length; ++j) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
                array3[i][j] = (i + 1) * (j + 1);
            }
        }
        array3[1][2] = 100;
        array3[1][2] = 100;


        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        array1[1][1] = 200;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array2, array1).toComparison() < 0);
    }

    @Test
    public void testMixedArray_1_oe() {
        final Object[] array1 = new Object[2];
        final Object[] array2 = new Object[2];
        final Object[] array3 = new Object[2];
        for (int i = 0; i < array1.length; ++i) {
            array1[i] = new long[2];
            array2[i] = new long[2];
            array3[i] = new long[3];
            for (int j = 0; j < 2; ++j) {
                ((long[]) array1[i])[j] = (i + 1) * (j + 1);
                ((long[]) array2[i])[j] = (i + 1) * (j + 1);
                ((long[]) array3[i])[j] = (i + 1) * (j + 1);
            }
        }
        ((long[]) array3[0])[2] = 1;
        ((long[]) array3[1])[2] = 1;
        assertEquals(0, new CompareToBuilder().append(array1, array1).toComparison());
    }

    @Test
    public void testMixedArray_2_oe() {
        final Object[] array1 = new Object[2];
        final Object[] array2 = new Object[2];
        final Object[] array3 = new Object[2];
        for (int i = 0; i < array1.length; ++i) {
            array1[i] = new long[2];
            array2[i] = new long[2];
            array3[i] = new long[3];
            for (int j = 0; j < 2; ++j) {
                ((long[]) array1[i])[j] = (i + 1) * (j + 1);
                ((long[]) array2[i])[j] = (i + 1) * (j + 1);
                ((long[]) array3[i])[j] = (i + 1) * (j + 1);
            }
        }
        ((long[]) array3[0])[2] = 1;
        ((long[]) array3[1])[2] = 1;
        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(array1, array2).toComparison());
    }

    @Test
    public void testMixedArray_3_oe() {
        final Object[] array1 = new Object[2];
        final Object[] array2 = new Object[2];
        final Object[] array3 = new Object[2];
        for (int i = 0; i < array1.length; ++i) {
            array1[i] = new long[2];
            array2[i] = new long[2];
            array3[i] = new long[3];
            for (int j = 0; j < 2; ++j) {
                ((long[]) array1[i])[j] = (i + 1) * (j + 1);
                ((long[]) array2[i])[j] = (i + 1) * (j + 1);
                ((long[]) array3[i])[j] = (i + 1) * (j + 1);
            }
        }
        ((long[]) array3[0])[2] = 1;
        ((long[]) array3[1])[2] = 1;
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array1, array3).toComparison() < 0);
    }

    @Test
    public void testMixedArray_4_oe() {
        final Object[] array1 = new Object[2];
        final Object[] array2 = new Object[2];
        final Object[] array3 = new Object[2];
        for (int i = 0; i < array1.length; ++i) {
            array1[i] = new long[2];
            array2[i] = new long[2];
            array3[i] = new long[3];
            for (int j = 0; j < 2; ++j) {
                ((long[]) array1[i])[j] = (i + 1) * (j + 1);
                ((long[]) array2[i])[j] = (i + 1) * (j + 1);
                ((long[]) array3[i])[j] = (i + 1) * (j + 1);
            }
        }
        ((long[]) array3[0])[2] = 1;
        ((long[]) array3[1])[2] = 1;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array3, array1).toComparison() > 0);
    }

    @Test
    public void testMixedArray_5_oe() {
        final Object[] array1 = new Object[2];
        final Object[] array2 = new Object[2];
        final Object[] array3 = new Object[2];
        for (int i = 0; i < array1.length; ++i) {
            array1[i] = new long[2];
            array2[i] = new long[2];
            array3[i] = new long[3];
            for (int j = 0; j < 2; ++j) {
                ((long[]) array1[i])[j] = (i + 1) * (j + 1);
                ((long[]) array2[i])[j] = (i + 1) * (j + 1);
                ((long[]) array3[i])[j] = (i + 1) * (j + 1);
            }
        }
        ((long[]) array3[0])[2] = 1;
        ((long[]) array3[1])[2] = 1;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        ((long[]) array1[1])[1] = 200;
        assertTrue(new CompareToBuilder().append(array1, array2).toComparison() > 0);
    }

    @Test
    public void testMixedArray_6_oe() {
        final Object[] array1 = new Object[2];
        final Object[] array2 = new Object[2];
        final Object[] array3 = new Object[2];
        for (int i = 0; i < array1.length; ++i) {
            array1[i] = new long[2];
            array2[i] = new long[2];
            array3[i] = new long[3];
            for (int j = 0; j < 2; ++j) {
                ((long[]) array1[i])[j] = (i + 1) * (j + 1);
                ((long[]) array2[i])[j] = (i + 1) * (j + 1);
                ((long[]) array3[i])[j] = (i + 1) * (j + 1);
            }
        }
        ((long[]) array3[0])[2] = 1;
        ((long[]) array3[1])[2] = 1;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        ((long[]) array1[1])[1] = 200;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(array2, array1).toComparison() < 0);
    }

    @Test
    public void testObjectArrayHiddenByObject_1_oe() {
        final TestObject[] array1 = new TestObject[2];
        array1[0] = new TestObject(4);
        array1[1] = new TestObject(5);
        final TestObject[] array2 = new TestObject[2];
        array2[0] = new TestObject(4);
        array2[1] = new TestObject(5);
        final TestObject[] array3 = new TestObject[3];
        array3[0] = new TestObject(4);
        array3[1] = new TestObject(5);
        array3[2] = new TestObject(6);

        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;

        assertEquals(0, new CompareToBuilder().append(obj1, obj1).toComparison());
    }

    @Test
    public void testObjectArrayHiddenByObject_2_oe() {
        final TestObject[] array1 = new TestObject[2];
        array1[0] = new TestObject(4);
        array1[1] = new TestObject(5);
        final TestObject[] array2 = new TestObject[2];
        array2[0] = new TestObject(4);
        array2[1] = new TestObject(5);
        final TestObject[] array3 = new TestObject[3];
        array3[0] = new TestObject(4);
        array3[1] = new TestObject(5);
        array3[2] = new TestObject(6);

        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;

        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(obj1, obj2).toComparison());
    }

    @Test
    public void testObjectArrayHiddenByObject_3_oe() {
        final TestObject[] array1 = new TestObject[2];
        array1[0] = new TestObject(4);
        array1[1] = new TestObject(5);
        final TestObject[] array2 = new TestObject[2];
        array2[0] = new TestObject(4);
        array2[1] = new TestObject(5);
        final TestObject[] array3 = new TestObject[3];
        array3[0] = new TestObject(4);
        array3[1] = new TestObject(5);
        array3[2] = new TestObject(6);

        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;

        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj1, obj3).toComparison() < 0);
    }

    @Test
    public void testObjectArrayHiddenByObject_4_oe() {
        final TestObject[] array1 = new TestObject[2];
        array1[0] = new TestObject(4);
        array1[1] = new TestObject(5);
        final TestObject[] array2 = new TestObject[2];
        array2[0] = new TestObject(4);
        array2[1] = new TestObject(5);
        final TestObject[] array3 = new TestObject[3];
        array3[0] = new TestObject(4);
        array3[1] = new TestObject(5);
        array3[2] = new TestObject(6);

        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj3, obj1).toComparison() > 0);
    }

    @Test
    public void testObjectArrayHiddenByObject_5_oe() {
        final TestObject[] array1 = new TestObject[2];
        array1[0] = new TestObject(4);
        array1[1] = new TestObject(5);
        final TestObject[] array2 = new TestObject[2];
        array2[0] = new TestObject(4);
        array2[1] = new TestObject(5);
        final TestObject[] array3 = new TestObject[3];
        array3[0] = new TestObject(4);
        array3[1] = new TestObject(5);
        array3[2] = new TestObject(6);

        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        array1[1] = new TestObject(7);
        assertTrue(new CompareToBuilder().append(obj1, obj2).toComparison() > 0);
    }

    @Test
    public void testObjectArrayHiddenByObject_6_oe() {
        final TestObject[] array1 = new TestObject[2];
        array1[0] = new TestObject(4);
        array1[1] = new TestObject(5);
        final TestObject[] array2 = new TestObject[2];
        array2[0] = new TestObject(4);
        array2[1] = new TestObject(5);
        final TestObject[] array3 = new TestObject[3];
        array3[0] = new TestObject(4);
        array3[1] = new TestObject(5);
        array3[2] = new TestObject(6);

        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        array1[1] = new TestObject(7);
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj2, obj1).toComparison() < 0);
    }

    @Test
    public void testLongArrayHiddenByObject_1_oe() {
        final long[] array1 = new long[2];
        array1[0] = 5L;
        array1[1] = 6L;
        final long[] array2 = new long[2];
        array2[0] = 5L;
        array2[1] = 6L;
        final long[] array3 = new long[3];
        array3[0] = 5L;
        array3[1] = 6L;
        array3[2] = 7L;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        assertEquals(0, new CompareToBuilder().append(obj1, obj1).toComparison());
    }

    @Test
    public void testLongArrayHiddenByObject_2_oe() {
        final long[] array1 = new long[2];
        array1[0] = 5L;
        array1[1] = 6L;
        final long[] array2 = new long[2];
        array2[0] = 5L;
        array2[1] = 6L;
        final long[] array3 = new long[3];
        array3[0] = 5L;
        array3[1] = 6L;
        array3[2] = 7L;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(obj1, obj2).toComparison());
    }

    @Test
    public void testLongArrayHiddenByObject_3_oe() {
        final long[] array1 = new long[2];
        array1[0] = 5L;
        array1[1] = 6L;
        final long[] array2 = new long[2];
        array2[0] = 5L;
        array2[1] = 6L;
        final long[] array3 = new long[3];
        array3[0] = 5L;
        array3[1] = 6L;
        array3[2] = 7L;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj1, obj3).toComparison() < 0);
    }

    @Test
    public void testLongArrayHiddenByObject_4_oe() {
        final long[] array1 = new long[2];
        array1[0] = 5L;
        array1[1] = 6L;
        final long[] array2 = new long[2];
        array2[0] = 5L;
        array2[1] = 6L;
        final long[] array3 = new long[3];
        array3[0] = 5L;
        array3[1] = 6L;
        array3[2] = 7L;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj3, obj1).toComparison() > 0);
    }

    @Test
    public void testLongArrayHiddenByObject_5_oe() {
        final long[] array1 = new long[2];
        array1[0] = 5L;
        array1[1] = 6L;
        final long[] array2 = new long[2];
        array2[0] = 5L;
        array2[1] = 6L;
        final long[] array3 = new long[3];
        array3[0] = 5L;
        array3[1] = 6L;
        array3[2] = 7L;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        array1[1] = 7;
        assertTrue(new CompareToBuilder().append(obj1, obj2).toComparison() > 0);
    }

    @Test
    public void testLongArrayHiddenByObject_6_oe() {
        final long[] array1 = new long[2];
        array1[0] = 5L;
        array1[1] = 6L;
        final long[] array2 = new long[2];
        array2[0] = 5L;
        array2[1] = 6L;
        final long[] array3 = new long[3];
        array3[0] = 5L;
        array3[1] = 6L;
        array3[2] = 7L;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        array1[1] = 7;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj2, obj1).toComparison() < 0);
    }

    @Test
    public void testIntArrayHiddenByObject_1_oe() {
        final int[] array1 = new int[2];
        array1[0] = 5;
        array1[1] = 6;
        final int[] array2 = new int[2];
        array2[0] = 5;
        array2[1] = 6;
        final int[] array3 = new int[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        assertEquals(0, new CompareToBuilder().append(obj1, obj1).toComparison());
    }

    @Test
    public void testIntArrayHiddenByObject_2_oe() {
        final int[] array1 = new int[2];
        array1[0] = 5;
        array1[1] = 6;
        final int[] array2 = new int[2];
        array2[0] = 5;
        array2[1] = 6;
        final int[] array3 = new int[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(obj1, obj2).toComparison());
    }

    @Test
    public void testIntArrayHiddenByObject_3_oe() {
        final int[] array1 = new int[2];
        array1[0] = 5;
        array1[1] = 6;
        final int[] array2 = new int[2];
        array2[0] = 5;
        array2[1] = 6;
        final int[] array3 = new int[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj1, obj3).toComparison() < 0);
    }

    @Test
    public void testIntArrayHiddenByObject_4_oe() {
        final int[] array1 = new int[2];
        array1[0] = 5;
        array1[1] = 6;
        final int[] array2 = new int[2];
        array2[0] = 5;
        array2[1] = 6;
        final int[] array3 = new int[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj3, obj1).toComparison() > 0);
    }

    @Test
    public void testIntArrayHiddenByObject_5_oe() {
        final int[] array1 = new int[2];
        array1[0] = 5;
        array1[1] = 6;
        final int[] array2 = new int[2];
        array2[0] = 5;
        array2[1] = 6;
        final int[] array3 = new int[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        array1[1] = 7;
        assertTrue(new CompareToBuilder().append(obj1, obj2).toComparison() > 0);
    }

    @Test
    public void testIntArrayHiddenByObject_6_oe() {
        final int[] array1 = new int[2];
        array1[0] = 5;
        array1[1] = 6;
        final int[] array2 = new int[2];
        array2[0] = 5;
        array2[1] = 6;
        final int[] array3 = new int[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        array1[1] = 7;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj2, obj1).toComparison() < 0);
    }

    @Test
    public void testShortArrayHiddenByObject_1_oe() {
        final short[] array1 = new short[2];
        array1[0] = 5;
        array1[1] = 6;
        final short[] array2 = new short[2];
        array2[0] = 5;
        array2[1] = 6;
        final short[] array3 = new short[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        assertEquals(0, new CompareToBuilder().append(obj1, obj1).toComparison());
    }

    @Test
    public void testShortArrayHiddenByObject_2_oe() {
        final short[] array1 = new short[2];
        array1[0] = 5;
        array1[1] = 6;
        final short[] array2 = new short[2];
        array2[0] = 5;
        array2[1] = 6;
        final short[] array3 = new short[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(obj1, obj2).toComparison());
    }

    @Test
    public void testShortArrayHiddenByObject_3_oe() {
        final short[] array1 = new short[2];
        array1[0] = 5;
        array1[1] = 6;
        final short[] array2 = new short[2];
        array2[0] = 5;
        array2[1] = 6;
        final short[] array3 = new short[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj1, obj3).toComparison() < 0);
    }

    @Test
    public void testShortArrayHiddenByObject_4_oe() {
        final short[] array1 = new short[2];
        array1[0] = 5;
        array1[1] = 6;
        final short[] array2 = new short[2];
        array2[0] = 5;
        array2[1] = 6;
        final short[] array3 = new short[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj3, obj1).toComparison() > 0);
    }

    @Test
    public void testShortArrayHiddenByObject_5_oe() {
        final short[] array1 = new short[2];
        array1[0] = 5;
        array1[1] = 6;
        final short[] array2 = new short[2];
        array2[0] = 5;
        array2[1] = 6;
        final short[] array3 = new short[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        array1[1] = 7;
        assertTrue(new CompareToBuilder().append(obj1, obj2).toComparison() > 0);
    }

    @Test
    public void testShortArrayHiddenByObject_6_oe() {
        final short[] array1 = new short[2];
        array1[0] = 5;
        array1[1] = 6;
        final short[] array2 = new short[2];
        array2[0] = 5;
        array2[1] = 6;
        final short[] array3 = new short[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        array1[1] = 7;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj2, obj1).toComparison() < 0);
    }

    @Test
    public void testCharArrayHiddenByObject_1_oe() {
        final char[] array1 = new char[2];
        array1[0] = 5;
        array1[1] = 6;
        final char[] array2 = new char[2];
        array2[0] = 5;
        array2[1] = 6;
        final char[] array3 = new char[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        assertEquals(0, new CompareToBuilder().append(obj1, obj1).toComparison());
    }

    @Test
    public void testCharArrayHiddenByObject_2_oe() {
        final char[] array1 = new char[2];
        array1[0] = 5;
        array1[1] = 6;
        final char[] array2 = new char[2];
        array2[0] = 5;
        array2[1] = 6;
        final char[] array3 = new char[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(obj1, obj2).toComparison());
    }

    @Test
    public void testCharArrayHiddenByObject_3_oe() {
        final char[] array1 = new char[2];
        array1[0] = 5;
        array1[1] = 6;
        final char[] array2 = new char[2];
        array2[0] = 5;
        array2[1] = 6;
        final char[] array3 = new char[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj1, obj3).toComparison() < 0);
    }

    @Test
    public void testCharArrayHiddenByObject_4_oe() {
        final char[] array1 = new char[2];
        array1[0] = 5;
        array1[1] = 6;
        final char[] array2 = new char[2];
        array2[0] = 5;
        array2[1] = 6;
        final char[] array3 = new char[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj3, obj1).toComparison() > 0);
    }

    @Test
    public void testCharArrayHiddenByObject_5_oe() {
        final char[] array1 = new char[2];
        array1[0] = 5;
        array1[1] = 6;
        final char[] array2 = new char[2];
        array2[0] = 5;
        array2[1] = 6;
        final char[] array3 = new char[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        array1[1] = 7;
        assertTrue(new CompareToBuilder().append(obj1, obj2).toComparison() > 0);
    }

    @Test
    public void testCharArrayHiddenByObject_6_oe() {
        final char[] array1 = new char[2];
        array1[0] = 5;
        array1[1] = 6;
        final char[] array2 = new char[2];
        array2[0] = 5;
        array2[1] = 6;
        final char[] array3 = new char[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        array1[1] = 7;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj2, obj1).toComparison() < 0);
    }

    @Test
    public void testByteArrayHiddenByObject_1_oe() {
        final byte[] array1 = new byte[2];
        array1[0] = 5;
        array1[1] = 6;
        final byte[] array2 = new byte[2];
        array2[0] = 5;
        array2[1] = 6;
        final byte[] array3 = new byte[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        assertEquals(0, new CompareToBuilder().append(obj1, obj1).toComparison());
    }

    @Test
    public void testByteArrayHiddenByObject_2_oe() {
        final byte[] array1 = new byte[2];
        array1[0] = 5;
        array1[1] = 6;
        final byte[] array2 = new byte[2];
        array2[0] = 5;
        array2[1] = 6;
        final byte[] array3 = new byte[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(obj1, obj2).toComparison());
    }

    @Test
    public void testByteArrayHiddenByObject_3_oe() {
        final byte[] array1 = new byte[2];
        array1[0] = 5;
        array1[1] = 6;
        final byte[] array2 = new byte[2];
        array2[0] = 5;
        array2[1] = 6;
        final byte[] array3 = new byte[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj1, obj3).toComparison() < 0);
    }

    @Test
    public void testByteArrayHiddenByObject_4_oe() {
        final byte[] array1 = new byte[2];
        array1[0] = 5;
        array1[1] = 6;
        final byte[] array2 = new byte[2];
        array2[0] = 5;
        array2[1] = 6;
        final byte[] array3 = new byte[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj3, obj1).toComparison() > 0);
    }

    @Test
    public void testByteArrayHiddenByObject_5_oe() {
        final byte[] array1 = new byte[2];
        array1[0] = 5;
        array1[1] = 6;
        final byte[] array2 = new byte[2];
        array2[0] = 5;
        array2[1] = 6;
        final byte[] array3 = new byte[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        array1[1] = 7;
        assertTrue(new CompareToBuilder().append(obj1, obj2).toComparison() > 0);
    }

    @Test
    public void testByteArrayHiddenByObject_6_oe() {
        final byte[] array1 = new byte[2];
        array1[0] = 5;
        array1[1] = 6;
        final byte[] array2 = new byte[2];
        array2[0] = 5;
        array2[1] = 6;
        final byte[] array3 = new byte[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        array1[1] = 7;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj2, obj1).toComparison() < 0);
    }

    @Test
    public void testDoubleArrayHiddenByObject_1_oe() {
        final double[] array1 = new double[2];
        array1[0] = 5;
        array1[1] = 6;
        final double[] array2 = new double[2];
        array2[0] = 5;
        array2[1] = 6;
        final double[] array3 = new double[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        assertEquals(0, new CompareToBuilder().append(obj1, obj1).toComparison());
    }

    @Test
    public void testDoubleArrayHiddenByObject_2_oe() {
        final double[] array1 = new double[2];
        array1[0] = 5;
        array1[1] = 6;
        final double[] array2 = new double[2];
        array2[0] = 5;
        array2[1] = 6;
        final double[] array3 = new double[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(obj1, obj2).toComparison());
    }

    @Test
    public void testDoubleArrayHiddenByObject_3_oe() {
        final double[] array1 = new double[2];
        array1[0] = 5;
        array1[1] = 6;
        final double[] array2 = new double[2];
        array2[0] = 5;
        array2[1] = 6;
        final double[] array3 = new double[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj1, obj3).toComparison() < 0);
    }

    @Test
    public void testDoubleArrayHiddenByObject_4_oe() {
        final double[] array1 = new double[2];
        array1[0] = 5;
        array1[1] = 6;
        final double[] array2 = new double[2];
        array2[0] = 5;
        array2[1] = 6;
        final double[] array3 = new double[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj3, obj1).toComparison() > 0);
    }

    @Test
    public void testDoubleArrayHiddenByObject_5_oe() {
        final double[] array1 = new double[2];
        array1[0] = 5;
        array1[1] = 6;
        final double[] array2 = new double[2];
        array2[0] = 5;
        array2[1] = 6;
        final double[] array3 = new double[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        array1[1] = 7;
        assertTrue(new CompareToBuilder().append(obj1, obj2).toComparison() > 0);
    }

    @Test
    public void testDoubleArrayHiddenByObject_6_oe() {
        final double[] array1 = new double[2];
        array1[0] = 5;
        array1[1] = 6;
        final double[] array2 = new double[2];
        array2[0] = 5;
        array2[1] = 6;
        final double[] array3 = new double[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        array1[1] = 7;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj2, obj1).toComparison() < 0);
    }

    @Test
    public void testFloatArrayHiddenByObject_1_oe() {
        final float[] array1 = new float[2];
        array1[0] = 5;
        array1[1] = 6;
        final float[] array2 = new float[2];
        array2[0] = 5;
        array2[1] = 6;
        final float[] array3 = new float[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        assertEquals(0, new CompareToBuilder().append(obj1, obj1).toComparison());
    }

    @Test
    public void testFloatArrayHiddenByObject_2_oe() {
        final float[] array1 = new float[2];
        array1[0] = 5;
        array1[1] = 6;
        final float[] array2 = new float[2];
        array2[0] = 5;
        array2[1] = 6;
        final float[] array3 = new float[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(obj1, obj2).toComparison());
    }

    @Test
    public void testFloatArrayHiddenByObject_3_oe() {
        final float[] array1 = new float[2];
        array1[0] = 5;
        array1[1] = 6;
        final float[] array2 = new float[2];
        array2[0] = 5;
        array2[1] = 6;
        final float[] array3 = new float[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj1, obj3).toComparison() < 0);
    }

    @Test
    public void testFloatArrayHiddenByObject_4_oe() {
        final float[] array1 = new float[2];
        array1[0] = 5;
        array1[1] = 6;
        final float[] array2 = new float[2];
        array2[0] = 5;
        array2[1] = 6;
        final float[] array3 = new float[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj3, obj1).toComparison() > 0);
    }

    @Test
    public void testFloatArrayHiddenByObject_5_oe() {
        final float[] array1 = new float[2];
        array1[0] = 5;
        array1[1] = 6;
        final float[] array2 = new float[2];
        array2[0] = 5;
        array2[1] = 6;
        final float[] array3 = new float[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        array1[1] = 7;
        assertTrue(new CompareToBuilder().append(obj1, obj2).toComparison() > 0);
    }

    @Test
    public void testFloatArrayHiddenByObject_6_oe() {
        final float[] array1 = new float[2];
        array1[0] = 5;
        array1[1] = 6;
        final float[] array2 = new float[2];
        array2[0] = 5;
        array2[1] = 6;
        final float[] array3 = new float[3];
        array3[0] = 5;
        array3[1] = 6;
        array3[2] = 7;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        array1[1] = 7;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj2, obj1).toComparison() < 0);
    }

    @Test
    public void testBooleanArrayHiddenByObject_1_oe() {
        final boolean[] array1 = new boolean[2];
        array1[0] = true;
        array1[1] = false;
        final boolean[] array2 = new boolean[2];
        array2[0] = true;
        array2[1] = false;
        final boolean[] array3 = new boolean[3];
        array3[0] = true;
        array3[1] = false;
        array3[2] = true;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        assertEquals(0, new CompareToBuilder().append(obj1, obj1).toComparison());
    }

    @Test
    public void testBooleanArrayHiddenByObject_2_oe() {
        final boolean[] array1 = new boolean[2];
        array1[0] = true;
        array1[1] = false;
        final boolean[] array2 = new boolean[2];
        array2[0] = true;
        array2[1] = false;
        final boolean[] array3 = new boolean[3];
        array3[0] = true;
        array3[1] = false;
        array3[2] = true;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        assertEquals(0, new CompareToBuilder().append(obj1, obj2).toComparison());
    }

    @Test
    public void testBooleanArrayHiddenByObject_3_oe() {
        final boolean[] array1 = new boolean[2];
        array1[0] = true;
        array1[1] = false;
        final boolean[] array2 = new boolean[2];
        array2[0] = true;
        array2[1] = false;
        final boolean[] array3 = new boolean[3];
        array3[0] = true;
        array3[1] = false;
        array3[2] = true;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj1, obj3).toComparison() < 0);
    }

    @Test
    public void testBooleanArrayHiddenByObject_4_oe() {
        final boolean[] array1 = new boolean[2];
        array1[0] = true;
        array1[1] = false;
        final boolean[] array2 = new boolean[2];
        array2[0] = true;
        array2[1] = false;
        final boolean[] array3 = new boolean[3];
        array3[0] = true;
        array3[1] = false;
        array3[2] = true;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj3, obj1).toComparison() > 0);
    }

    @Test
    public void testBooleanArrayHiddenByObject_5_oe() {
        final boolean[] array1 = new boolean[2];
        array1[0] = true;
        array1[1] = false;
        final boolean[] array2 = new boolean[2];
        array2[0] = true;
        array2[1] = false;
        final boolean[] array3 = new boolean[3];
        array3[0] = true;
        array3[1] = false;
        array3[2] = true;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        array1[1] = true;
        assertTrue(new CompareToBuilder().append(obj1, obj2).toComparison() > 0);
    }

    @Test
    public void testBooleanArrayHiddenByObject_6_oe() {
        final boolean[] array1 = new boolean[2];
        array1[0] = true;
        array1[1] = false;
        final boolean[] array2 = new boolean[2];
        array2[0] = true;
        array2[1] = false;
        final boolean[] array3 = new boolean[3];
        array3[0] = true;
        array3[1] = false;
        array3[2] = true;
        final Object obj1 = array1;
        final Object obj2 = array2;
        final Object obj3 = array3;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        array1[1] = true;
        // removed other assertion
        assertTrue(new CompareToBuilder().append(obj2, obj1).toComparison() < 0);
    }

 }
