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
    public void testReflectionCompareEx1_1_oe() throws Exception {
        final TestObject o1 = new TestObject(4);
        try {
    CompareToBuilder.reflectionCompare(o1, null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReflectionCompareEx2_1_oe() throws Exception {
        final TestObject o1 = new TestObject(4);
        final Object o2 = new Object();
        try {
    CompareToBuilder.reflectionCompare(o1, o2);
    org.junit.jupiter.api.Assertions.fail("ClassCastException");
} catch (ClassCastException e) {
}
    }

    @Test
    public void testObjectEx2_1_oe() throws Exception {
        final TestObject o1 = new TestObject(4);
        final Object o2 = new Object();
        try {
    new CompareToBuilder().append(o1, o2);
    org.junit.jupiter.api.Assertions.fail("ClassCastException");
} catch (ClassCastException e) {
}
    }

 }
