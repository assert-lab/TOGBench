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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.jupiter.api.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.builder.EqualsBuilder}.
 */
public class EqualsBuilderTest_OE25Dev {

    //-----------------------------------------------------------------------

    static class TestObject {
        private int a;

        TestObject() {
        }

        TestObject(final int a) {
            this.a = a;
        }

        @Override
        public boolean equals(final Object o) {
            if (o == null) {
                return false;
            }
            if (o == this) {
                return true;
            }
            if (o.getClass() != getClass()) {
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
            if (o == null) {
                return false;
            }
            if (o == this) {
                return true;
            }
            if (o.getClass() != getClass()) {
                return false;
            }

            final TestSubObject rhs = (TestSubObject) o;
            return super.equals(o) && b == rhs.b;
        }

        @Override
        public int hashCode() {
            return b * 17 + super.hashCode();
        }

        public void setB(final int b) {
            this.b = b;
        }

        public int getB() {
            return b;
        }
    }

    static class TestEmptySubObject extends TestObject {
        TestEmptySubObject(final int a) {
            super(a);
        }
    }

    static class TestTSubObject extends TestObject {
        @SuppressWarnings("unused")
        private final transient int t;

        TestTSubObject(final int a, final int t) {
            super(a);
            this.t = t;
        }
    }

    static class TestTTSubObject extends TestTSubObject {
        @SuppressWarnings("unused")
        private final transient int tt;

        TestTTSubObject(final int a, final int t, final int tt) {
            super(a, t);
            this.tt = tt;
        }
    }

    static class TestTTLeafObject extends TestTTSubObject {
        @SuppressWarnings("unused")
        private final int leafValue;

        TestTTLeafObject(final int a, final int t, final int tt, final int leafValue) {
            super(a, t, tt);
            this.leafValue = leafValue;
        }
    }

    static class TestTSubObject2 extends TestObject {
        private transient int t;

        TestTSubObject2(final int a, final int t) {
            super(a);
        }

        public int getT() {
            return t;
        }

        public void setT(final int t) {
            this.t = t;
        }
    }

    static class TestRecursiveGenericObject<T> {

        private final T a;

        TestRecursiveGenericObject(final T a) {
            this.a = a;
        }

        public T getA() {
            return a;
        }
    }

    static class TestRecursiveObject {
        private final TestRecursiveInnerObject a;
        private final TestRecursiveInnerObject b;
        private int z;

        TestRecursiveObject(final TestRecursiveInnerObject a,
                            final TestRecursiveInnerObject b, final int z) {
            this.a = a;
            this.b = b;
        }

        public TestRecursiveInnerObject getA() {
            return a;
        }

        public TestRecursiveInnerObject getB() {
            return b;
        }

        public int getZ() {
            return z;
        }

    }

    static class TestRecursiveInnerObject {
        private final int n;

        TestRecursiveInnerObject(final int n) {
            this.n = n;
        }

        public int getN() {
            return n;
        }
    }

    static class TestRecursiveCycleObject {
        private TestRecursiveCycleObject cycle;
        private final int n;

        TestRecursiveCycleObject(final int n) {
            this.n = n;
            this.cycle = this;
        }

        TestRecursiveCycleObject(final TestRecursiveCycleObject cycle, final int n) {
            this.n = n;
            this.cycle = cycle;
        }

        public int getN() {
            return n;
        }

        public TestRecursiveCycleObject getCycle() {
            return cycle;
        }

        public void setCycle(final TestRecursiveCycleObject cycle) {
            this.cycle = cycle;
        }
    }

    @Test
    public void testReflectionHierarchyEquals() {
        testReflectionHierarchyEquals(false);
        testReflectionHierarchyEquals(true);
        // Transients
        assertTrue(EqualsBuilder.reflectionEquals(new TestTTLeafObject(1, 2, 3, 4), new TestTTLeafObject(1, 2, 3, 4), true));
        assertTrue(EqualsBuilder.reflectionEquals(new TestTTLeafObject(1, 2, 3, 4), new TestTTLeafObject(1, 2, 3, 4), false));
        assertFalse(EqualsBuilder.reflectionEquals(new TestTTLeafObject(1, 0, 0, 4), new TestTTLeafObject(1, 2, 3, 4), true));
        assertFalse(EqualsBuilder.reflectionEquals(new TestTTLeafObject(1, 2, 3, 4), new TestTTLeafObject(1, 2, 3, 0), true));
        assertFalse(EqualsBuilder.reflectionEquals(new TestTTLeafObject(0, 2, 3, 4), new TestTTLeafObject(1, 2, 3, 4), true));
    }

    private void testReflectionHierarchyEquals(final boolean testTransients) {
        final TestObject to1 = new TestObject(4);
        final TestObject to1Bis = new TestObject(4);
        final TestObject to1Ter = new TestObject(4);
        final TestObject to2 = new TestObject(5);
        final TestEmptySubObject teso = new TestEmptySubObject(4);
        final TestTSubObject ttso = new TestTSubObject(4, 1);
        final TestTTSubObject tttso = new TestTTSubObject(4, 1, 2);
        final TestTTLeafObject ttlo = new TestTTLeafObject(4, 1, 2, 3);
        final TestSubObject tso1 = new TestSubObject(1, 4);
        final TestSubObject tso1bis = new TestSubObject(1, 4);
        final TestSubObject tso1ter = new TestSubObject(1, 4);
        final TestSubObject tso2 = new TestSubObject(2, 5);

        testReflectionEqualsEquivalenceRelationship(to1, to1Bis, to1Ter, to2, new TestObject(), testTransients);
        testReflectionEqualsEquivalenceRelationship(tso1, tso1bis, tso1ter, tso2, new TestSubObject(), testTransients);

        // More sanity checks:

        // same values
        assertTrue(EqualsBuilder.reflectionEquals(ttlo, ttlo, testTransients));
        assertTrue(EqualsBuilder.reflectionEquals(new TestSubObject(1, 10), new TestSubObject(1, 10), testTransients));
        // same super values, diff sub values
        assertFalse(EqualsBuilder.reflectionEquals(new TestSubObject(1, 10), new TestSubObject(1, 11), testTransients));
        assertFalse(EqualsBuilder.reflectionEquals(new TestSubObject(1, 11), new TestSubObject(1, 10), testTransients));
        // diff super values, same sub values
        assertFalse(EqualsBuilder.reflectionEquals(new TestSubObject(0, 10), new TestSubObject(1, 10), testTransients));
        assertFalse(EqualsBuilder.reflectionEquals(new TestSubObject(1, 10), new TestSubObject(0, 10), testTransients));

        // mix super and sub types: equals
        assertTrue(EqualsBuilder.reflectionEquals(to1, teso, testTransients));
        assertTrue(EqualsBuilder.reflectionEquals(teso, to1, testTransients));

        assertTrue(EqualsBuilder.reflectionEquals(to1,ttso,false));// Force testTransients = false for this assert assertTrue(EqualsBuilder.reflectionEquals(ttso,to1,false));// Force testTransients = false for this assert assertTrue(EqualsBuilder.reflectionEquals(to1,tttso,false));// Force testTransients = false for this assert assertTrue(EqualsBuilder.reflectionEquals(tttso,to1,false));// Force testTransients = false for this assert assertTrue(EqualsBuilder.reflectionEquals(ttso,tttso,false));// Force testTransients = false for this assert assertTrue(EqualsBuilder.reflectionEquals(tttso,ttso,false));// Force testTransients = false for this assert assertFalse(EqualsBuilder.reflectionEquals(new TestObject(0),new TestEmptySubObject(1),testTransients));
        assertFalse(EqualsBuilder.reflectionEquals(new TestEmptySubObject(1), new TestObject(0), testTransients));

        assertFalse(EqualsBuilder.reflectionEquals(new TestObject(0), new TestTSubObject(1, 1), testTransients));
        assertFalse(EqualsBuilder.reflectionEquals(new TestTSubObject(1, 1), new TestObject(0), testTransients));

        assertFalse(EqualsBuilder.reflectionEquals(new TestObject(1), new TestSubObject(0, 10), testTransients));
        assertFalse(EqualsBuilder.reflectionEquals(new TestSubObject(0, 10), new TestObject(1), testTransients));

        assertFalse(EqualsBuilder.reflectionEquals(to1, ttlo));
        assertFalse(EqualsBuilder.reflectionEquals(tso1, this));
    }

    /**
     * Equivalence relationship tests inspired by "Effective Java":
     * <ul>
     * <li>reflection</li>
     * <li>symmetry</li>
     * <li>transitive</li>
     * <li>consistency</li>
     * <li>non-null reference</li>
     * </ul>
     *
     * @param to             a TestObject
     * @param toBis          a TestObject, equal to to and toTer
     * @param toTer          Left hand side, equal to to and toBis
     * @param to2            a different TestObject
     * @param oToChange      a TestObject that will be changed
     * @param testTransients whether to test transient instance variables
     */
    private void testReflectionEqualsEquivalenceRelationship(
            final TestObject to,
            final TestObject toBis,
            final TestObject toTer,
            final TestObject to2,
            final TestObject oToChange,
            final boolean testTransients) {

        // reflection test
        assertTrue(EqualsBuilder.reflectionEquals(to, to, testTransients));
        assertTrue(EqualsBuilder.reflectionEquals(to2, to2, testTransients));

        // symmetry test
        assertTrue(EqualsBuilder.reflectionEquals(to, toBis, testTransients) && EqualsBuilder.reflectionEquals(toBis, to, testTransients));

        // transitive test
        assertTrue(EqualsBuilder.reflectionEquals(to,toBis,testTransients)&& EqualsBuilder.reflectionEquals(toBis,toTer,testTransients)&& EqualsBuilder.reflectionEquals(to,toTer,testTransients));

        // consistency test
        oToChange.setA(to.getA());
        if (oToChange instanceof TestSubObject) {
            ((TestSubObject) oToChange).setB(((TestSubObject) to).getB());
        }
        assertTrue(EqualsBuilder.reflectionEquals(oToChange, to, testTransients));
        assertTrue(EqualsBuilder.reflectionEquals(oToChange, to, testTransients));
        oToChange.setA(to.getA() + 1);
        if (oToChange instanceof TestSubObject) {
            ((TestSubObject) oToChange).setB(((TestSubObject) to).getB() + 1);
        }
        assertFalse(EqualsBuilder.reflectionEquals(oToChange, to, testTransients));
        assertFalse(EqualsBuilder.reflectionEquals(oToChange, to, testTransients));

        // non-null reference test
        assertFalse(EqualsBuilder.reflectionEquals(to, null, testTransients));
        assertFalse(EqualsBuilder.reflectionEquals(to2, null, testTransients));
        assertFalse(EqualsBuilder.reflectionEquals(null, to, testTransients));
        assertFalse(EqualsBuilder.reflectionEquals(null, to2, testTransients));
        assertTrue(EqualsBuilder.reflectionEquals(null, null, testTransients));
    }

    public static class TestACanEqualB {
        private final int a;

        public TestACanEqualB(final int a) {
            this.a = a;
        }

        @Override
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof TestACanEqualB) {
                return this.a == ((TestACanEqualB) o).getA();
            }
            if (o instanceof TestBCanEqualA) {
                return this.a == ((TestBCanEqualA) o).getB();
            }
            return false;
        }

        @Override
        public int hashCode() {
            return a;
        }

        public int getA() {
            return this.a;
        }
    }

    public static class TestBCanEqualA {
        private final int b;

        public TestBCanEqualA(final int b) {
            this.b = b;
        }

        @Override
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof TestACanEqualB) {
                return this.b == ((TestACanEqualB) o).getA();
            }
            if (o instanceof TestBCanEqualA) {
                return this.b == ((TestBCanEqualA) o).getB();
            }
            return false;
        }

        @Override
        public int hashCode() {
            return b;
        }

        public int getB() {
            return this.b;
        }
    }

    /**
     * Tests two instances of classes that can be equal and that are not "related". The two classes are not subclasses
     * of each other and do not share a parent aside from Object.
     * See https://issues.apache.org/bugzilla/show_bug.cgi?id=33069
     */

    /**
     * Test from https://issues.apache.org/bugzilla/show_bug.cgi?id=33067
     */
    @Test
    public void testNpeForNullElement() {
        final Object[] x1 = new Object[]{Integer.valueOf(1), null, Integer.valueOf(3)};
        final Object[] x2 = new Object[]{Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3)};

        // causes an NPE in 2.0 according to:
        // https://issues.apache.org/bugzilla/show_bug.cgi?id=33067
        new EqualsBuilder().append(x1, x2);
    }

    static class TestObjectWithMultipleFields {
        @SuppressWarnings("unused")
        private final TestObject one;
        @SuppressWarnings("unused")
        private final TestObject two;
        @SuppressWarnings("unused")
        private final TestObject three;

        TestObjectWithMultipleFields(final int one, final int two, final int three) {
            this.one = new TestObject(one);
            this.two = new TestObject(two);
            this.three = new TestObject(three);
        }
    }

    /**
     * Test cyclical object references which cause a StackOverflowException if
     * not handled properly. s. LANG-606
     */

    static class TestObjectReference {
        @SuppressWarnings("unused")
        private TestObjectReference reference;
        @SuppressWarnings("unused")
        private final TestObject one;

        TestObjectReference(final int one) {
            this.one = new TestObject(one);
        }

        public void setObjectReference(final TestObjectReference reference) {
            this.reference = reference;
        }

        @Override
        public boolean equals(final Object obj) {
            return EqualsBuilder.reflectionEquals(this, obj);
        }
    }

    static class TestObjectEqualsExclude {
        @EqualsExclude
        private final int a;
        private final int b;

        TestObjectEqualsExclude(final int a, final int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }
    }

    @Test
    public void testIsRegistered() throws Exception {
        final Object firstObject = new Object();
        final Object secondObject = new Object();

        try {
            final Method registerMethod = MethodUtils.getMatchingMethod(EqualsBuilder.class, "register", Object.class, Object.class);
            registerMethod.setAccessible(true);
            registerMethod.invoke(null, firstObject, secondObject);

            assertTrue(EqualsBuilder.isRegistered(firstObject, secondObject));
            assertTrue(EqualsBuilder.isRegistered(secondObject, firstObject)); // LANG-1349
        } finally {
            final Method unregisterMethod = MethodUtils.getMatchingMethod(EqualsBuilder.class, "unregister", Object.class, Object.class);
            unregisterMethod.setAccessible(true);
            unregisterMethod.invoke(null, firstObject, secondObject);
        }
    }


}

