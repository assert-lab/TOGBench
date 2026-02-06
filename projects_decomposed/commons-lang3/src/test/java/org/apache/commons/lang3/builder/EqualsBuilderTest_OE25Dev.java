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

    @Test
    public void testReflectionEquals_1_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        assertTrue(EqualsBuilder.reflectionEquals(o1, o1));
    }

    @Test
    public void testReflectionEquals_2_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        assertFalse(EqualsBuilder.reflectionEquals(o1, o2));
    }

    @Test
    public void testReflectionEquals_3_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        // removed other assertion
        o2.setA(4);
        assertTrue(EqualsBuilder.reflectionEquals(o1, o2));
    }

    @Test
    public void testReflectionEquals_4_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        // removed other assertion
        o2.setA(4);
        // removed other assertion

        assertFalse(EqualsBuilder.reflectionEquals(o1, this));
    }

    @Test
    public void testReflectionEquals_5_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        // removed other assertion
        o2.setA(4);
        // removed other assertion

        // removed other assertion

        assertFalse(EqualsBuilder.reflectionEquals(o1, null));
    }

    @Test
    public void testReflectionEquals_6_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        // removed other assertion
        o2.setA(4);
        // removed other assertion

        // removed other assertion

        // removed other assertion
        assertFalse(EqualsBuilder.reflectionEquals(null, o2));
    }

    @Test
    public void testReflectionEquals_7_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        // removed other assertion
        o2.setA(4);
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(EqualsBuilder.reflectionEquals(null, null));
    }

    // @Test
    // public void testReflectionHierarchyEquals_1_oe() {
    //     testReflectionHierarchyEquals(false);
    //     testReflectionHierarchyEquals(true);
    //     // Transients
    //     assertTrue(EqualsBuilder.reflectionEquals(new TestTTLeafObject(1, 2, 3, 4), new TestTTLeafObject(1, 2, 3, 4), true));
    // }

    // @Test
    // public void testReflectionHierarchyEquals_2_oe() {
    //     testReflectionHierarchyEquals(false);
    //     testReflectionHierarchyEquals(true);
    //     // Transients
    //     // removed other assertion
    //     assertTrue(EqualsBuilder.reflectionEquals(new TestTTLeafObject(1, 2, 3, 4), new TestTTLeafObject(1, 2, 3, 4), false));
    // }

    // @Test
    // public void testReflectionHierarchyEquals_3_oe() {
    //     testReflectionHierarchyEquals(false);
    //     testReflectionHierarchyEquals(true);
    //     // Transients
    //     // removed other assertion
    //     // removed other assertion
    //     assertFalse(EqualsBuilder.reflectionEquals(new TestTTLeafObject(1, 0, 0, 4), new TestTTLeafObject(1, 2, 3, 4), true));
    // }

    // @Test
    // public void testReflectionHierarchyEquals_4_oe() {
    //     testReflectionHierarchyEquals(false);
    //     testReflectionHierarchyEquals(true);
    //     // Transients
    //     // removed other assertion
    //     // removed other assertion
    //     // removed other assertion
    //     assertFalse(EqualsBuilder.reflectionEquals(new TestTTLeafObject(1, 2, 3, 4), new TestTTLeafObject(1, 2, 3, 0), true));
    // }

    // @Test
    // public void testReflectionHierarchyEquals_5_oe() {
    //     testReflectionHierarchyEquals(false);
    //     testReflectionHierarchyEquals(true);
    //     // Transients
    //     // removed other assertion
    //     // removed other assertion
    //     // removed other assertion
    //     // removed other assertion
    //     assertFalse(EqualsBuilder.reflectionEquals(new TestTTLeafObject(0, 2, 3, 4), new TestTTLeafObject(1, 2, 3, 4), true));
    // }

    @Test
    public void testSuper_1_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        assertTrue(new EqualsBuilder().appendSuper(true).append(o1, o1).isEquals());
    }

    @Test
    public void testSuper_2_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        assertFalse(new EqualsBuilder().appendSuper(false).append(o1, o1).isEquals());
    }

    @Test
    public void testSuper_3_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        // removed other assertion
        assertFalse(new EqualsBuilder().appendSuper(true).append(o1, o2).isEquals());
    }

    @Test
    public void testSuper_4_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(new EqualsBuilder().appendSuper(false).append(o1, o2).isEquals());
    }

    @Test
    public void testObject_1_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        assertTrue(new EqualsBuilder().append(o1, o1).isEquals());
    }

    @Test
    public void testObject_2_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        assertFalse(new EqualsBuilder().append(o1, o2).isEquals());
    }

    @Test
    public void testObject_3_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        // removed other assertion
        o2.setA(4);
        assertTrue(new EqualsBuilder().append(o1, o2).isEquals());
    }

    @Test
    public void testObject_4_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        // removed other assertion
        o2.setA(4);
        // removed other assertion

        assertFalse(new EqualsBuilder().append(o1, this).isEquals());
    }

    @Test
    public void testObject_5_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        // removed other assertion
        o2.setA(4);
        // removed other assertion

        // removed other assertion

        assertFalse(new EqualsBuilder().append(o1, null).isEquals());
    }

    @Test
    public void testObject_6_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        // removed other assertion
        o2.setA(4);
        // removed other assertion

        // removed other assertion

        // removed other assertion
        assertFalse(new EqualsBuilder().append(null, o2).isEquals());
    }

    @Test
    public void testObject_7_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        // removed other assertion
        o2.setA(4);
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append((Object) null, null).isEquals());
    }

    @Test
    public void testObjectBuild_1_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        assertEquals(Boolean.TRUE, new EqualsBuilder().append(o1, o1).build());
    }

    @Test
    public void testObjectBuild_2_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        assertEquals(Boolean.FALSE, new EqualsBuilder().append(o1, o2).build());
    }

    @Test
    public void testObjectBuild_3_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        // removed other assertion
        o2.setA(4);
        assertEquals(Boolean.TRUE, new EqualsBuilder().append(o1, o2).build());
    }

    @Test
    public void testObjectBuild_4_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        // removed other assertion
        o2.setA(4);
        // removed other assertion

        assertEquals(Boolean.FALSE, new EqualsBuilder().append(o1, this).build());
    }

    @Test
    public void testObjectBuild_5_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        // removed other assertion
        o2.setA(4);
        // removed other assertion

        // removed other assertion

        assertEquals(Boolean.FALSE, new EqualsBuilder().append(o1, null).build());
    }

    @Test
    public void testObjectBuild_6_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        // removed other assertion
        o2.setA(4);
        // removed other assertion

        // removed other assertion

        // removed other assertion
        assertEquals(Boolean.FALSE, new EqualsBuilder().append(null, o2).build());
    }

    @Test
    public void testObjectBuild_7_oe() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        // removed other assertion
        o2.setA(4);
        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.TRUE, new EqualsBuilder().append((Object) null, null).build());
    }

    @Test
    public void testObjectRecursiveGenericInteger_1_oe() {
        final TestRecursiveGenericObject<Integer> o1_a = new TestRecursiveGenericObject<>(1);
        final TestRecursiveGenericObject<Integer> o1_b = new TestRecursiveGenericObject<>(1);
        final TestRecursiveGenericObject<Integer> o2 = new TestRecursiveGenericObject<>(2);

        assertTrue(new EqualsBuilder().setTestRecursive(true).append(o1_a, o1_b).isEquals());
    }

    @Test
    public void testObjectRecursiveGenericInteger_2_oe() {
        final TestRecursiveGenericObject<Integer> o1_a = new TestRecursiveGenericObject<>(1);
        final TestRecursiveGenericObject<Integer> o1_b = new TestRecursiveGenericObject<>(1);
        final TestRecursiveGenericObject<Integer> o2 = new TestRecursiveGenericObject<>(2);

        // removed other assertion
        assertTrue(new EqualsBuilder().setTestRecursive(true).append(o1_b, o1_a).isEquals());
    }

    @Test
    public void testObjectRecursiveGenericInteger_3_oe() {
        final TestRecursiveGenericObject<Integer> o1_a = new TestRecursiveGenericObject<>(1);
        final TestRecursiveGenericObject<Integer> o1_b = new TestRecursiveGenericObject<>(1);
        final TestRecursiveGenericObject<Integer> o2 = new TestRecursiveGenericObject<>(2);

        // removed other assertion
        // removed other assertion

        assertFalse(new EqualsBuilder().setTestRecursive(true).append(o1_b, o2).isEquals());
    }

    @Test
    public void testObjectRecursiveGenericString_1_oe() {
        // Note: Do not use literals, because string literals are always mapped by same object (internal() of String))!
        final String s1_a = String.valueOf(1);
        final TestRecursiveGenericObject<String> o1_a = new TestRecursiveGenericObject<>(s1_a);
        final TestRecursiveGenericObject<String> o1_b = new TestRecursiveGenericObject<>(String.valueOf(1));
        final TestRecursiveGenericObject<String> o2 = new TestRecursiveGenericObject<>(String.valueOf(2));

        // To trigger bug reported in LANG-1356, call hashCode only on string in instance o1_a
        s1_a.hashCode();

        assertTrue(new EqualsBuilder().setTestRecursive(true).append(o1_a, o1_b).isEquals());
    }

    @Test
    public void testObjectRecursiveGenericString_2_oe() {
        // Note: Do not use literals, because string literals are always mapped by same object (internal() of String))!
        final String s1_a = String.valueOf(1);
        final TestRecursiveGenericObject<String> o1_a = new TestRecursiveGenericObject<>(s1_a);
        final TestRecursiveGenericObject<String> o1_b = new TestRecursiveGenericObject<>(String.valueOf(1));
        final TestRecursiveGenericObject<String> o2 = new TestRecursiveGenericObject<>(String.valueOf(2));

        // To trigger bug reported in LANG-1356, call hashCode only on string in instance o1_a
        s1_a.hashCode();

        // removed other assertion
        assertTrue(new EqualsBuilder().setTestRecursive(true).append(o1_b, o1_a).isEquals());
    }

    @Test
    public void testObjectRecursiveGenericString_3_oe() {
        // Note: Do not use literals, because string literals are always mapped by same object (internal() of String))!
        final String s1_a = String.valueOf(1);
        final TestRecursiveGenericObject<String> o1_a = new TestRecursiveGenericObject<>(s1_a);
        final TestRecursiveGenericObject<String> o1_b = new TestRecursiveGenericObject<>(String.valueOf(1));
        final TestRecursiveGenericObject<String> o2 = new TestRecursiveGenericObject<>(String.valueOf(2));

        // To trigger bug reported in LANG-1356, call hashCode only on string in instance o1_a
        s1_a.hashCode();

        // removed other assertion
        // removed other assertion

        assertFalse(new EqualsBuilder().setTestRecursive(true).append(o1_b, o2).isEquals());
    }

    @Test
    public void testObjectRecursive_1_oe() {
        final TestRecursiveInnerObject i1_1 = new TestRecursiveInnerObject(1);
        final TestRecursiveInnerObject i1_2 = new TestRecursiveInnerObject(1);
        final TestRecursiveInnerObject i2_1 = new TestRecursiveInnerObject(2);
        final TestRecursiveInnerObject i2_2 = new TestRecursiveInnerObject(2);
        final TestRecursiveInnerObject i3 = new TestRecursiveInnerObject(3);
        final TestRecursiveInnerObject i4 = new TestRecursiveInnerObject(4);

        final TestRecursiveObject o1_a = new TestRecursiveObject(i1_1, i2_1, 1);
        final TestRecursiveObject o1_b = new TestRecursiveObject(i1_2, i2_2, 1);
        final TestRecursiveObject o2 = new TestRecursiveObject(i3, i4, 2);
        final TestRecursiveObject oNull = new TestRecursiveObject(null, null, 2);

        assertTrue(new EqualsBuilder().setTestRecursive(true).append(o1_a, o1_a).isEquals());
    }

    @Test
    public void testObjectRecursive_2_oe() {
        final TestRecursiveInnerObject i1_1 = new TestRecursiveInnerObject(1);
        final TestRecursiveInnerObject i1_2 = new TestRecursiveInnerObject(1);
        final TestRecursiveInnerObject i2_1 = new TestRecursiveInnerObject(2);
        final TestRecursiveInnerObject i2_2 = new TestRecursiveInnerObject(2);
        final TestRecursiveInnerObject i3 = new TestRecursiveInnerObject(3);
        final TestRecursiveInnerObject i4 = new TestRecursiveInnerObject(4);

        final TestRecursiveObject o1_a = new TestRecursiveObject(i1_1, i2_1, 1);
        final TestRecursiveObject o1_b = new TestRecursiveObject(i1_2, i2_2, 1);
        final TestRecursiveObject o2 = new TestRecursiveObject(i3, i4, 2);
        final TestRecursiveObject oNull = new TestRecursiveObject(null, null, 2);

        // removed other assertion
        assertTrue(new EqualsBuilder().setTestRecursive(true).append(o1_a, o1_b).isEquals());
    }

    @Test
    public void testObjectRecursive_3_oe() {
        final TestRecursiveInnerObject i1_1 = new TestRecursiveInnerObject(1);
        final TestRecursiveInnerObject i1_2 = new TestRecursiveInnerObject(1);
        final TestRecursiveInnerObject i2_1 = new TestRecursiveInnerObject(2);
        final TestRecursiveInnerObject i2_2 = new TestRecursiveInnerObject(2);
        final TestRecursiveInnerObject i3 = new TestRecursiveInnerObject(3);
        final TestRecursiveInnerObject i4 = new TestRecursiveInnerObject(4);

        final TestRecursiveObject o1_a = new TestRecursiveObject(i1_1, i2_1, 1);
        final TestRecursiveObject o1_b = new TestRecursiveObject(i1_2, i2_2, 1);
        final TestRecursiveObject o2 = new TestRecursiveObject(i3, i4, 2);
        final TestRecursiveObject oNull = new TestRecursiveObject(null, null, 2);

        // removed other assertion
        // removed other assertion

        assertFalse(new EqualsBuilder().setTestRecursive(true).append(o1_a, o2).isEquals());
    }

    @Test
    public void testObjectRecursive_4_oe() {
        final TestRecursiveInnerObject i1_1 = new TestRecursiveInnerObject(1);
        final TestRecursiveInnerObject i1_2 = new TestRecursiveInnerObject(1);
        final TestRecursiveInnerObject i2_1 = new TestRecursiveInnerObject(2);
        final TestRecursiveInnerObject i2_2 = new TestRecursiveInnerObject(2);
        final TestRecursiveInnerObject i3 = new TestRecursiveInnerObject(3);
        final TestRecursiveInnerObject i4 = new TestRecursiveInnerObject(4);

        final TestRecursiveObject o1_a = new TestRecursiveObject(i1_1, i2_1, 1);
        final TestRecursiveObject o1_b = new TestRecursiveObject(i1_2, i2_2, 1);
        final TestRecursiveObject o2 = new TestRecursiveObject(i3, i4, 2);
        final TestRecursiveObject oNull = new TestRecursiveObject(null, null, 2);

        // removed other assertion
        // removed other assertion

        // removed other assertion

        assertTrue(new EqualsBuilder().setTestRecursive(true).append(oNull, oNull).isEquals());
    }

    @Test
    public void testObjectRecursive_5_oe() {
        final TestRecursiveInnerObject i1_1 = new TestRecursiveInnerObject(1);
        final TestRecursiveInnerObject i1_2 = new TestRecursiveInnerObject(1);
        final TestRecursiveInnerObject i2_1 = new TestRecursiveInnerObject(2);
        final TestRecursiveInnerObject i2_2 = new TestRecursiveInnerObject(2);
        final TestRecursiveInnerObject i3 = new TestRecursiveInnerObject(3);
        final TestRecursiveInnerObject i4 = new TestRecursiveInnerObject(4);

        final TestRecursiveObject o1_a = new TestRecursiveObject(i1_1, i2_1, 1);
        final TestRecursiveObject o1_b = new TestRecursiveObject(i1_2, i2_2, 1);
        final TestRecursiveObject o2 = new TestRecursiveObject(i3, i4, 2);
        final TestRecursiveObject oNull = new TestRecursiveObject(null, null, 2);

        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        assertFalse(new EqualsBuilder().setTestRecursive(true).append(o1_a, oNull).isEquals());
    }

    @Test
    public void testObjectRecursiveCycleSelfreference_1_oe() {
        final TestRecursiveCycleObject o1_a = new TestRecursiveCycleObject(1);
        final TestRecursiveCycleObject o1_b = new TestRecursiveCycleObject(1);
        final TestRecursiveCycleObject o2 = new TestRecursiveCycleObject(2);

        assertTrue(new EqualsBuilder().setTestRecursive(true).append(o1_a, o1_a).isEquals());
    }

    @Test
    public void testObjectRecursiveCycleSelfreference_2_oe() {
        final TestRecursiveCycleObject o1_a = new TestRecursiveCycleObject(1);
        final TestRecursiveCycleObject o1_b = new TestRecursiveCycleObject(1);
        final TestRecursiveCycleObject o2 = new TestRecursiveCycleObject(2);

        // removed other assertion
        assertTrue(new EqualsBuilder().setTestRecursive(true).append(o1_a, o1_b).isEquals());
    }

    @Test
    public void testObjectRecursiveCycleSelfreference_3_oe() {
        final TestRecursiveCycleObject o1_a = new TestRecursiveCycleObject(1);
        final TestRecursiveCycleObject o1_b = new TestRecursiveCycleObject(1);
        final TestRecursiveCycleObject o2 = new TestRecursiveCycleObject(2);

        // removed other assertion
        // removed other assertion
        assertFalse(new EqualsBuilder().setTestRecursive(true).append(o1_a, o2).isEquals());
    }

    @Test
    public void testObjectRecursiveCycle_1_oe() {
        final TestRecursiveCycleObject o1_a = new TestRecursiveCycleObject(1);
        final TestRecursiveCycleObject i1_a = new TestRecursiveCycleObject(o1_a, 100);
        o1_a.setCycle(i1_a);

        final TestRecursiveCycleObject o1_b = new TestRecursiveCycleObject(1);
        final TestRecursiveCycleObject i1_b = new TestRecursiveCycleObject(o1_b, 100);
        o1_b.setCycle(i1_b);

        final TestRecursiveCycleObject o2 = new TestRecursiveCycleObject(2);
        final TestRecursiveCycleObject i2 = new TestRecursiveCycleObject(o1_b, 200);
        o2.setCycle(i2);

        assertTrue(new EqualsBuilder().setTestRecursive(true).append(o1_a, o1_a).isEquals());
    }

    @Test
    public void testObjectRecursiveCycle_2_oe() {
        final TestRecursiveCycleObject o1_a = new TestRecursiveCycleObject(1);
        final TestRecursiveCycleObject i1_a = new TestRecursiveCycleObject(o1_a, 100);
        o1_a.setCycle(i1_a);

        final TestRecursiveCycleObject o1_b = new TestRecursiveCycleObject(1);
        final TestRecursiveCycleObject i1_b = new TestRecursiveCycleObject(o1_b, 100);
        o1_b.setCycle(i1_b);

        final TestRecursiveCycleObject o2 = new TestRecursiveCycleObject(2);
        final TestRecursiveCycleObject i2 = new TestRecursiveCycleObject(o1_b, 200);
        o2.setCycle(i2);

        // removed other assertion
        assertTrue(new EqualsBuilder().setTestRecursive(true).append(o1_a, o1_b).isEquals());
    }

    @Test
    public void testObjectRecursiveCycle_3_oe() {
        final TestRecursiveCycleObject o1_a = new TestRecursiveCycleObject(1);
        final TestRecursiveCycleObject i1_a = new TestRecursiveCycleObject(o1_a, 100);
        o1_a.setCycle(i1_a);

        final TestRecursiveCycleObject o1_b = new TestRecursiveCycleObject(1);
        final TestRecursiveCycleObject i1_b = new TestRecursiveCycleObject(o1_b, 100);
        o1_b.setCycle(i1_b);

        final TestRecursiveCycleObject o2 = new TestRecursiveCycleObject(2);
        final TestRecursiveCycleObject i2 = new TestRecursiveCycleObject(o1_b, 200);
        o2.setCycle(i2);

        // removed other assertion
        // removed other assertion
        assertFalse(new EqualsBuilder().setTestRecursive(true).append(o1_a, o2).isEquals());
    }

    @Test
    public void testObjectRecursiveCycle_4_oe() {
        final TestRecursiveCycleObject o1_a = new TestRecursiveCycleObject(1);
        final TestRecursiveCycleObject i1_a = new TestRecursiveCycleObject(o1_a, 100);
        o1_a.setCycle(i1_a);

        final TestRecursiveCycleObject o1_b = new TestRecursiveCycleObject(1);
        final TestRecursiveCycleObject i1_b = new TestRecursiveCycleObject(o1_b, 100);
        o1_b.setCycle(i1_b);

        final TestRecursiveCycleObject o2 = new TestRecursiveCycleObject(2);
        final TestRecursiveCycleObject i2 = new TestRecursiveCycleObject(o1_b, 200);
        o2.setCycle(i2);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue(EqualsBuilder.reflectionEquals(o1_a, o1_b, false, null, true));
    }

    @Test
    public void testObjectRecursiveCycle_5_oe() {
        final TestRecursiveCycleObject o1_a = new TestRecursiveCycleObject(1);
        final TestRecursiveCycleObject i1_a = new TestRecursiveCycleObject(o1_a, 100);
        o1_a.setCycle(i1_a);

        final TestRecursiveCycleObject o1_b = new TestRecursiveCycleObject(1);
        final TestRecursiveCycleObject i1_b = new TestRecursiveCycleObject(o1_b, 100);
        o1_b.setCycle(i1_b);

        final TestRecursiveCycleObject o2 = new TestRecursiveCycleObject(2);
        final TestRecursiveCycleObject i2 = new TestRecursiveCycleObject(o1_b, 200);
        o2.setCycle(i2);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(EqualsBuilder.reflectionEquals(o1_a, o2, false, null, true));
    }

    @Test
    public void testLong_1_oe() {
        final long o1 = 1L;
        final long o2 = 2L;
        assertTrue(new EqualsBuilder().append(o1, o1).isEquals());
    }

    @Test
    public void testLong_2_oe() {
        final long o1 = 1L;
        final long o2 = 2L;
        // removed other assertion
        assertFalse(new EqualsBuilder().append(o1, o2).isEquals());
    }

    @Test
    public void testInt_1_oe() {
        final int o1 = 1;
        final int o2 = 2;
        assertTrue(new EqualsBuilder().append(o1, o1).isEquals());
    }

    @Test
    public void testInt_2_oe() {
        final int o1 = 1;
        final int o2 = 2;
        // removed other assertion
        assertFalse(new EqualsBuilder().append(o1, o2).isEquals());
    }

    @Test
    public void testShort_1_oe() {
        final short o1 = 1;
        final short o2 = 2;
        assertTrue(new EqualsBuilder().append(o1, o1).isEquals());
    }

    @Test
    public void testShort_2_oe() {
        final short o1 = 1;
        final short o2 = 2;
        // removed other assertion
        assertFalse(new EqualsBuilder().append(o1, o2).isEquals());
    }

    @Test
    public void testChar_1_oe() {
        final char o1 = 1;
        final char o2 = 2;
        assertTrue(new EqualsBuilder().append(o1, o1).isEquals());
    }

    @Test
    public void testChar_2_oe() {
        final char o1 = 1;
        final char o2 = 2;
        // removed other assertion
        assertFalse(new EqualsBuilder().append(o1, o2).isEquals());
    }

    @Test
    public void testByte_1_oe() {
        final byte o1 = 1;
        final byte o2 = 2;
        assertTrue(new EqualsBuilder().append(o1, o1).isEquals());
    }

    @Test
    public void testByte_2_oe() {
        final byte o1 = 1;
        final byte o2 = 2;
        // removed other assertion
        assertFalse(new EqualsBuilder().append(o1, o2).isEquals());
    }

    @Test
    public void testDouble_1_oe() {
        final double o1 = 1;
        final double o2 = 2;
        assertTrue(new EqualsBuilder().append(o1, o1).isEquals());
    }

    @Test
    public void testDouble_2_oe() {
        final double o1 = 1;
        final double o2 = 2;
        // removed other assertion
        assertFalse(new EqualsBuilder().append(o1, o2).isEquals());
    }

    @Test
    public void testDouble_3_oe() {
        final double o1 = 1;
        final double o2 = 2;
        // removed other assertion
        // removed other assertion
        assertFalse(new EqualsBuilder().append(o1, Double.NaN).isEquals());
    }

    @Test
    public void testDouble_4_oe() {
        final double o1 = 1;
        final double o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append(Double.NaN, Double.NaN).isEquals());
    }

    @Test
    public void testDouble_5_oe() {
        final double o1 = 1;
        final double o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY).isEquals());
    }

    @Test
    public void testFloat_1_oe() {
        final float o1 = 1;
        final float o2 = 2;
        assertTrue(new EqualsBuilder().append(o1, o1).isEquals());
    }

    @Test
    public void testFloat_2_oe() {
        final float o1 = 1;
        final float o2 = 2;
        // removed other assertion
        assertFalse(new EqualsBuilder().append(o1, o2).isEquals());
    }

    @Test
    public void testFloat_3_oe() {
        final float o1 = 1;
        final float o2 = 2;
        // removed other assertion
        // removed other assertion
        assertFalse(new EqualsBuilder().append(o1, Float.NaN).isEquals());
    }

    @Test
    public void testFloat_4_oe() {
        final float o1 = 1;
        final float o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append(Float.NaN, Float.NaN).isEquals());
    }

    @Test
    public void testFloat_5_oe() {
        final float o1 = 1;
        final float o2 = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY).isEquals());
    }

    @Test
    public void testAccessors_1_oe() {
        final EqualsBuilder equalsBuilder = new EqualsBuilder();
        assertTrue(equalsBuilder.isEquals());
    }

    @Test
    public void testAccessors_2_oe() {
        final EqualsBuilder equalsBuilder = new EqualsBuilder();
        // removed other assertion
        equalsBuilder.setEquals(true);
        assertTrue(equalsBuilder.isEquals());
    }

    @Test
    public void testAccessors_3_oe() {
        final EqualsBuilder equalsBuilder = new EqualsBuilder();
        // removed other assertion
        equalsBuilder.setEquals(true);
        // removed other assertion
        equalsBuilder.setEquals(false);
        assertFalse(equalsBuilder.isEquals());
    }

    @Test
    public void testReset_1_oe() {
        final EqualsBuilder equalsBuilder = new EqualsBuilder();
        assertTrue(equalsBuilder.isEquals());
    }

    @Test
    public void testReset_2_oe() {
        final EqualsBuilder equalsBuilder = new EqualsBuilder();
        // removed other assertion
        equalsBuilder.setEquals(false);
        assertFalse(equalsBuilder.isEquals());
    }

    @Test
    public void testReset_3_oe() {
        final EqualsBuilder equalsBuilder = new EqualsBuilder();
        // removed other assertion
        equalsBuilder.setEquals(false);
        // removed other assertion
        equalsBuilder.reset();
        assertTrue(equalsBuilder.isEquals());
    }

    @Test
    public void testBoolean_1_oe() {
        final boolean o1 = true;
        final boolean o2 = false;
        assertTrue(new EqualsBuilder().append(o1, o1).isEquals());
    }

    @Test
    public void testBoolean_2_oe() {
        final boolean o1 = true;
        final boolean o2 = false;
        // removed other assertion
        assertFalse(new EqualsBuilder().append(o1, o2).isEquals());
    }

    @Test
    public void testObjectArray_1_oe() {
        TestObject[] obj1 = new TestObject[3];
        obj1[0] = new TestObject(4);
        obj1[1] = new TestObject(5);
        obj1[2] = null;
        TestObject[] obj2 = new TestObject[3];
        obj2[0] = new TestObject(4);
        obj2[1] = new TestObject(5);
        obj2[2] = null;

        assertTrue(new EqualsBuilder().append(obj1, obj1).isEquals());
    }

    @Test
    public void testObjectArray_2_oe() {
        TestObject[] obj1 = new TestObject[3];
        obj1[0] = new TestObject(4);
        obj1[1] = new TestObject(5);
        obj1[2] = null;
        TestObject[] obj2 = new TestObject[3];
        obj2[0] = new TestObject(4);
        obj2[1] = new TestObject(5);
        obj2[2] = null;

        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj2, obj2).isEquals());
    }

    @Test
    public void testObjectArray_3_oe() {
        TestObject[] obj1 = new TestObject[3];
        obj1[0] = new TestObject(4);
        obj1[1] = new TestObject(5);
        obj1[2] = null;
        TestObject[] obj2 = new TestObject[3];
        obj2[0] = new TestObject(4);
        obj2[1] = new TestObject(5);
        obj2[2] = null;

        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testObjectArray_4_oe() {
        TestObject[] obj1 = new TestObject[3];
        obj1[0] = new TestObject(4);
        obj1[1] = new TestObject(5);
        obj1[2] = null;
        TestObject[] obj2 = new TestObject[3];
        obj2[0] = new TestObject(4);
        obj2[1] = new TestObject(5);
        obj2[2] = null;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        obj1[1].setA(6);
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testObjectArray_5_oe() {
        TestObject[] obj1 = new TestObject[3];
        obj1[0] = new TestObject(4);
        obj1[1] = new TestObject(5);
        obj1[2] = null;
        TestObject[] obj2 = new TestObject[3];
        obj2[0] = new TestObject(4);
        obj2[1] = new TestObject(5);
        obj2[2] = null;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        obj1[1].setA(6);
        // removed other assertion
        obj1[1].setA(5);
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testObjectArray_6_oe() {
        TestObject[] obj1 = new TestObject[3];
        obj1[0] = new TestObject(4);
        obj1[1] = new TestObject(5);
        obj1[2] = null;
        TestObject[] obj2 = new TestObject[3];
        obj2[0] = new TestObject(4);
        obj2[1] = new TestObject(5);
        obj2[2] = null;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        obj1[1].setA(6);
        // removed other assertion
        obj1[1].setA(5);
        // removed other assertion
        obj1[2] = obj1[1];
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testObjectArray_7_oe() {
        TestObject[] obj1 = new TestObject[3];
        obj1[0] = new TestObject(4);
        obj1[1] = new TestObject(5);
        obj1[2] = null;
        TestObject[] obj2 = new TestObject[3];
        obj2[0] = new TestObject(4);
        obj2[1] = new TestObject(5);
        obj2[2] = null;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        obj1[1].setA(6);
        // removed other assertion
        obj1[1].setA(5);
        // removed other assertion
        obj1[2] = obj1[1];
        // removed other assertion
        obj1[2] = null;
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testObjectArray_8_oe() {
        TestObject[] obj1 = new TestObject[3];
        obj1[0] = new TestObject(4);
        obj1[1] = new TestObject(5);
        obj1[2] = null;
        TestObject[] obj2 = new TestObject[3];
        obj2[0] = new TestObject(4);
        obj2[1] = new TestObject(5);
        obj2[2] = null;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        obj1[1].setA(6);
        // removed other assertion
        obj1[1].setA(5);
        // removed other assertion
        obj1[2] = obj1[1];
        // removed other assertion
        obj1[2] = null;
        // removed other assertion

        obj2 = null;
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testObjectArray_9_oe() {
        TestObject[] obj1 = new TestObject[3];
        obj1[0] = new TestObject(4);
        obj1[1] = new TestObject(5);
        obj1[2] = null;
        TestObject[] obj2 = new TestObject[3];
        obj2[0] = new TestObject(4);
        obj2[1] = new TestObject(5);
        obj2[2] = null;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        obj1[1].setA(6);
        // removed other assertion
        obj1[1].setA(5);
        // removed other assertion
        obj1[2] = obj1[1];
        // removed other assertion
        obj1[2] = null;
        // removed other assertion

        obj2 = null;
        // removed other assertion
        obj1 = null;
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testLongArray_1_oe() {
        long[] obj1 = new long[2];
        obj1[0] = 5L;
        obj1[1] = 6L;
        long[] obj2 = new long[2];
        obj2[0] = 5L;
        obj2[1] = 6L;
        assertTrue(new EqualsBuilder().append(obj1, obj1).isEquals());
    }

    @Test
    public void testLongArray_2_oe() {
        long[] obj1 = new long[2];
        obj1[0] = 5L;
        obj1[1] = 6L;
        long[] obj2 = new long[2];
        obj2[0] = 5L;
        obj2[1] = 6L;
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testLongArray_3_oe() {
        long[] obj1 = new long[2];
        obj1[0] = 5L;
        obj1[1] = 6L;
        long[] obj2 = new long[2];
        obj2[0] = 5L;
        obj2[1] = 6L;
        // removed other assertion
        // removed other assertion
        obj1[1] = 7;
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testLongArray_4_oe() {
        long[] obj1 = new long[2];
        obj1[0] = 5L;
        obj1[1] = 6L;
        long[] obj2 = new long[2];
        obj2[0] = 5L;
        obj2[1] = 6L;
        // removed other assertion
        // removed other assertion
        obj1[1] = 7;
        // removed other assertion

        obj2 = null;
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testLongArray_5_oe() {
        long[] obj1 = new long[2];
        obj1[0] = 5L;
        obj1[1] = 6L;
        long[] obj2 = new long[2];
        obj2[0] = 5L;
        obj2[1] = 6L;
        // removed other assertion
        // removed other assertion
        obj1[1] = 7;
        // removed other assertion

        obj2 = null;
        // removed other assertion
        obj1 = null;
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testIntArray_1_oe() {
        int[] obj1 = new int[2];
        obj1[0] = 5;
        obj1[1] = 6;
        int[] obj2 = new int[2];
        obj2[0] = 5;
        obj2[1] = 6;
        assertTrue(new EqualsBuilder().append(obj1, obj1).isEquals());
    }

    @Test
    public void testIntArray_2_oe() {
        int[] obj1 = new int[2];
        obj1[0] = 5;
        obj1[1] = 6;
        int[] obj2 = new int[2];
        obj2[0] = 5;
        obj2[1] = 6;
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testIntArray_3_oe() {
        int[] obj1 = new int[2];
        obj1[0] = 5;
        obj1[1] = 6;
        int[] obj2 = new int[2];
        obj2[0] = 5;
        obj2[1] = 6;
        // removed other assertion
        // removed other assertion
        obj1[1] = 7;
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testIntArray_4_oe() {
        int[] obj1 = new int[2];
        obj1[0] = 5;
        obj1[1] = 6;
        int[] obj2 = new int[2];
        obj2[0] = 5;
        obj2[1] = 6;
        // removed other assertion
        // removed other assertion
        obj1[1] = 7;
        // removed other assertion

        obj2 = null;
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testIntArray_5_oe() {
        int[] obj1 = new int[2];
        obj1[0] = 5;
        obj1[1] = 6;
        int[] obj2 = new int[2];
        obj2[0] = 5;
        obj2[1] = 6;
        // removed other assertion
        // removed other assertion
        obj1[1] = 7;
        // removed other assertion

        obj2 = null;
        // removed other assertion
        obj1 = null;
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testShortArray_1_oe() {
        short[] obj1 = new short[2];
        obj1[0] = 5;
        obj1[1] = 6;
        short[] obj2 = new short[2];
        obj2[0] = 5;
        obj2[1] = 6;
        assertTrue(new EqualsBuilder().append(obj1, obj1).isEquals());
    }

    @Test
    public void testShortArray_2_oe() {
        short[] obj1 = new short[2];
        obj1[0] = 5;
        obj1[1] = 6;
        short[] obj2 = new short[2];
        obj2[0] = 5;
        obj2[1] = 6;
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testShortArray_3_oe() {
        short[] obj1 = new short[2];
        obj1[0] = 5;
        obj1[1] = 6;
        short[] obj2 = new short[2];
        obj2[0] = 5;
        obj2[1] = 6;
        // removed other assertion
        // removed other assertion
        obj1[1] = 7;
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testShortArray_4_oe() {
        short[] obj1 = new short[2];
        obj1[0] = 5;
        obj1[1] = 6;
        short[] obj2 = new short[2];
        obj2[0] = 5;
        obj2[1] = 6;
        // removed other assertion
        // removed other assertion
        obj1[1] = 7;
        // removed other assertion

        obj2 = null;
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testShortArray_5_oe() {
        short[] obj1 = new short[2];
        obj1[0] = 5;
        obj1[1] = 6;
        short[] obj2 = new short[2];
        obj2[0] = 5;
        obj2[1] = 6;
        // removed other assertion
        // removed other assertion
        obj1[1] = 7;
        // removed other assertion

        obj2 = null;
        // removed other assertion
        obj1 = null;
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testCharArray_1_oe() {
        char[] obj1 = new char[2];
        obj1[0] = 5;
        obj1[1] = 6;
        char[] obj2 = new char[2];
        obj2[0] = 5;
        obj2[1] = 6;
        assertTrue(new EqualsBuilder().append(obj1, obj1).isEquals());
    }

    @Test
    public void testCharArray_2_oe() {
        char[] obj1 = new char[2];
        obj1[0] = 5;
        obj1[1] = 6;
        char[] obj2 = new char[2];
        obj2[0] = 5;
        obj2[1] = 6;
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testCharArray_3_oe() {
        char[] obj1 = new char[2];
        obj1[0] = 5;
        obj1[1] = 6;
        char[] obj2 = new char[2];
        obj2[0] = 5;
        obj2[1] = 6;
        // removed other assertion
        // removed other assertion
        obj1[1] = 7;
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testCharArray_4_oe() {
        char[] obj1 = new char[2];
        obj1[0] = 5;
        obj1[1] = 6;
        char[] obj2 = new char[2];
        obj2[0] = 5;
        obj2[1] = 6;
        // removed other assertion
        // removed other assertion
        obj1[1] = 7;
        // removed other assertion

        obj2 = null;
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testCharArray_5_oe() {
        char[] obj1 = new char[2];
        obj1[0] = 5;
        obj1[1] = 6;
        char[] obj2 = new char[2];
        obj2[0] = 5;
        obj2[1] = 6;
        // removed other assertion
        // removed other assertion
        obj1[1] = 7;
        // removed other assertion

        obj2 = null;
        // removed other assertion
        obj1 = null;
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testByteArray_1_oe() {
        byte[] obj1 = new byte[2];
        obj1[0] = 5;
        obj1[1] = 6;
        byte[] obj2 = new byte[2];
        obj2[0] = 5;
        obj2[1] = 6;
        assertTrue(new EqualsBuilder().append(obj1, obj1).isEquals());
    }

    @Test
    public void testByteArray_2_oe() {
        byte[] obj1 = new byte[2];
        obj1[0] = 5;
        obj1[1] = 6;
        byte[] obj2 = new byte[2];
        obj2[0] = 5;
        obj2[1] = 6;
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testByteArray_3_oe() {
        byte[] obj1 = new byte[2];
        obj1[0] = 5;
        obj1[1] = 6;
        byte[] obj2 = new byte[2];
        obj2[0] = 5;
        obj2[1] = 6;
        // removed other assertion
        // removed other assertion
        obj1[1] = 7;
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testByteArray_4_oe() {
        byte[] obj1 = new byte[2];
        obj1[0] = 5;
        obj1[1] = 6;
        byte[] obj2 = new byte[2];
        obj2[0] = 5;
        obj2[1] = 6;
        // removed other assertion
        // removed other assertion
        obj1[1] = 7;
        // removed other assertion

        obj2 = null;
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testByteArray_5_oe() {
        byte[] obj1 = new byte[2];
        obj1[0] = 5;
        obj1[1] = 6;
        byte[] obj2 = new byte[2];
        obj2[0] = 5;
        obj2[1] = 6;
        // removed other assertion
        // removed other assertion
        obj1[1] = 7;
        // removed other assertion

        obj2 = null;
        // removed other assertion
        obj1 = null;
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testDoubleArray_1_oe() {
        double[] obj1 = new double[2];
        obj1[0] = 5;
        obj1[1] = 6;
        double[] obj2 = new double[2];
        obj2[0] = 5;
        obj2[1] = 6;
        assertTrue(new EqualsBuilder().append(obj1, obj1).isEquals());
    }

    @Test
    public void testDoubleArray_2_oe() {
        double[] obj1 = new double[2];
        obj1[0] = 5;
        obj1[1] = 6;
        double[] obj2 = new double[2];
        obj2[0] = 5;
        obj2[1] = 6;
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testDoubleArray_3_oe() {
        double[] obj1 = new double[2];
        obj1[0] = 5;
        obj1[1] = 6;
        double[] obj2 = new double[2];
        obj2[0] = 5;
        obj2[1] = 6;
        // removed other assertion
        // removed other assertion
        obj1[1] = 7;
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testDoubleArray_4_oe() {
        double[] obj1 = new double[2];
        obj1[0] = 5;
        obj1[1] = 6;
        double[] obj2 = new double[2];
        obj2[0] = 5;
        obj2[1] = 6;
        // removed other assertion
        // removed other assertion
        obj1[1] = 7;
        // removed other assertion

        obj2 = null;
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testDoubleArray_5_oe() {
        double[] obj1 = new double[2];
        obj1[0] = 5;
        obj1[1] = 6;
        double[] obj2 = new double[2];
        obj2[0] = 5;
        obj2[1] = 6;
        // removed other assertion
        // removed other assertion
        obj1[1] = 7;
        // removed other assertion

        obj2 = null;
        // removed other assertion
        obj1 = null;
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testFloatArray_1_oe() {
        float[] obj1 = new float[2];
        obj1[0] = 5;
        obj1[1] = 6;
        float[] obj2 = new float[2];
        obj2[0] = 5;
        obj2[1] = 6;
        assertTrue(new EqualsBuilder().append(obj1, obj1).isEquals());
    }

    @Test
    public void testFloatArray_2_oe() {
        float[] obj1 = new float[2];
        obj1[0] = 5;
        obj1[1] = 6;
        float[] obj2 = new float[2];
        obj2[0] = 5;
        obj2[1] = 6;
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testFloatArray_3_oe() {
        float[] obj1 = new float[2];
        obj1[0] = 5;
        obj1[1] = 6;
        float[] obj2 = new float[2];
        obj2[0] = 5;
        obj2[1] = 6;
        // removed other assertion
        // removed other assertion
        obj1[1] = 7;
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testFloatArray_4_oe() {
        float[] obj1 = new float[2];
        obj1[0] = 5;
        obj1[1] = 6;
        float[] obj2 = new float[2];
        obj2[0] = 5;
        obj2[1] = 6;
        // removed other assertion
        // removed other assertion
        obj1[1] = 7;
        // removed other assertion

        obj2 = null;
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testFloatArray_5_oe() {
        float[] obj1 = new float[2];
        obj1[0] = 5;
        obj1[1] = 6;
        float[] obj2 = new float[2];
        obj2[0] = 5;
        obj2[1] = 6;
        // removed other assertion
        // removed other assertion
        obj1[1] = 7;
        // removed other assertion

        obj2 = null;
        // removed other assertion
        obj1 = null;
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testBooleanArray_1_oe() {
        boolean[] obj1 = new boolean[2];
        obj1[0] = true;
        obj1[1] = false;
        boolean[] obj2 = new boolean[2];
        obj2[0] = true;
        obj2[1] = false;
        assertTrue(new EqualsBuilder().append(obj1, obj1).isEquals());
    }

    @Test
    public void testBooleanArray_2_oe() {
        boolean[] obj1 = new boolean[2];
        obj1[0] = true;
        obj1[1] = false;
        boolean[] obj2 = new boolean[2];
        obj2[0] = true;
        obj2[1] = false;
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testBooleanArray_3_oe() {
        boolean[] obj1 = new boolean[2];
        obj1[0] = true;
        obj1[1] = false;
        boolean[] obj2 = new boolean[2];
        obj2[0] = true;
        obj2[1] = false;
        // removed other assertion
        // removed other assertion
        obj1[1] = true;
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testBooleanArray_4_oe() {
        boolean[] obj1 = new boolean[2];
        obj1[0] = true;
        obj1[1] = false;
        boolean[] obj2 = new boolean[2];
        obj2[0] = true;
        obj2[1] = false;
        // removed other assertion
        // removed other assertion
        obj1[1] = true;
        // removed other assertion

        obj2 = null;
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testBooleanArray_5_oe() {
        boolean[] obj1 = new boolean[2];
        obj1[0] = true;
        obj1[1] = false;
        boolean[] obj2 = new boolean[2];
        obj2[0] = true;
        obj2[1] = false;
        // removed other assertion
        // removed other assertion
        obj1[1] = true;
        // removed other assertion

        obj2 = null;
        // removed other assertion
        obj1 = null;
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testMultiLongArray_1_oe() {
        final long[][] array1 = new long[2][2];
        final long[][] array2 = new long[2][2];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
            }
        }
        assertTrue(new EqualsBuilder().append(array1, array1).isEquals());
    }

    @Test
    public void testMultiLongArray_2_oe() {
        final long[][] array1 = new long[2][2];
        final long[][] array2 = new long[2][2];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
            }
        }
        // removed other assertion
        assertTrue(new EqualsBuilder().append(array1, array2).isEquals());
    }

    @Test
    public void testMultiLongArray_3_oe() {
        final long[][] array1 = new long[2][2];
        final long[][] array2 = new long[2][2];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
            }
        }
        // removed other assertion
        // removed other assertion
        array1[1][1] = 0;
        assertFalse(new EqualsBuilder().append(array1, array2).isEquals());
    }

    @Test
    public void testMultiIntArray_1_oe() {
        final int[][] array1 = new int[2][2];
        final int[][] array2 = new int[2][2];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
            }
        }
        assertTrue(new EqualsBuilder().append(array1, array1).isEquals());
    }

    @Test
    public void testMultiIntArray_2_oe() {
        final int[][] array1 = new int[2][2];
        final int[][] array2 = new int[2][2];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
            }
        }
        // removed other assertion
        assertTrue(new EqualsBuilder().append(array1, array2).isEquals());
    }

    @Test
    public void testMultiIntArray_3_oe() {
        final int[][] array1 = new int[2][2];
        final int[][] array2 = new int[2][2];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
            }
        }
        // removed other assertion
        // removed other assertion
        array1[1][1] = 0;
        assertFalse(new EqualsBuilder().append(array1, array2).isEquals());
    }

    @Test
    public void testMultiShortArray_1_oe() {
        final short[][] array1 = new short[2][2];
        final short[][] array2 = new short[2][2];
        for (short i = 0; i < array1.length; ++i) {
            for (short j = 0; j < array1[0].length; j++) {
                array1[i][j] = i;
                array2[i][j] = i;
            }
        }
        assertTrue(new EqualsBuilder().append(array1, array1).isEquals());
    }

    @Test
    public void testMultiShortArray_2_oe() {
        final short[][] array1 = new short[2][2];
        final short[][] array2 = new short[2][2];
        for (short i = 0; i < array1.length; ++i) {
            for (short j = 0; j < array1[0].length; j++) {
                array1[i][j] = i;
                array2[i][j] = i;
            }
        }
        // removed other assertion
        assertTrue(new EqualsBuilder().append(array1, array2).isEquals());
    }

    @Test
    public void testMultiShortArray_3_oe() {
        final short[][] array1 = new short[2][2];
        final short[][] array2 = new short[2][2];
        for (short i = 0; i < array1.length; ++i) {
            for (short j = 0; j < array1[0].length; j++) {
                array1[i][j] = i;
                array2[i][j] = i;
            }
        }
        // removed other assertion
        // removed other assertion
        array1[1][1] = 0;
        assertFalse(new EqualsBuilder().append(array1, array2).isEquals());
    }

    @Test
    public void testMultiCharArray_1_oe() {
        final char[][] array1 = new char[2][2];
        final char[][] array2 = new char[2][2];
        for (char i = 0; i < array1.length; ++i) {
            for (char j = 0; j < array1[0].length; j++) {
                array1[i][j] = i;
                array2[i][j] = i;
            }
        }
        assertTrue(new EqualsBuilder().append(array1, array1).isEquals());
    }

    @Test
    public void testMultiCharArray_2_oe() {
        final char[][] array1 = new char[2][2];
        final char[][] array2 = new char[2][2];
        for (char i = 0; i < array1.length; ++i) {
            for (char j = 0; j < array1[0].length; j++) {
                array1[i][j] = i;
                array2[i][j] = i;
            }
        }
        // removed other assertion
        assertTrue(new EqualsBuilder().append(array1, array2).isEquals());
    }

    @Test
    public void testMultiCharArray_3_oe() {
        final char[][] array1 = new char[2][2];
        final char[][] array2 = new char[2][2];
        for (char i = 0; i < array1.length; ++i) {
            for (char j = 0; j < array1[0].length; j++) {
                array1[i][j] = i;
                array2[i][j] = i;
            }
        }
        // removed other assertion
        // removed other assertion
        array1[1][1] = 0;
        assertFalse(new EqualsBuilder().append(array1, array2).isEquals());
    }

    @Test
    public void testMultiByteArray_1_oe() {
        final byte[][] array1 = new byte[2][2];
        final byte[][] array2 = new byte[2][2];
        for (byte i = 0; i < array1.length; ++i) {
            for (byte j = 0; j < array1[0].length; j++) {
                array1[i][j] = i;
                array2[i][j] = i;
            }
        }
        assertTrue(new EqualsBuilder().append(array1, array1).isEquals());
    }

    @Test
    public void testMultiByteArray_2_oe() {
        final byte[][] array1 = new byte[2][2];
        final byte[][] array2 = new byte[2][2];
        for (byte i = 0; i < array1.length; ++i) {
            for (byte j = 0; j < array1[0].length; j++) {
                array1[i][j] = i;
                array2[i][j] = i;
            }
        }
        // removed other assertion
        assertTrue(new EqualsBuilder().append(array1, array2).isEquals());
    }

    @Test
    public void testMultiByteArray_3_oe() {
        final byte[][] array1 = new byte[2][2];
        final byte[][] array2 = new byte[2][2];
        for (byte i = 0; i < array1.length; ++i) {
            for (byte j = 0; j < array1[0].length; j++) {
                array1[i][j] = i;
                array2[i][j] = i;
            }
        }
        // removed other assertion
        // removed other assertion
        array1[1][1] = 0;
        assertFalse(new EqualsBuilder().append(array1, array2).isEquals());
    }

    @Test
    public void testMultiFloatArray_1_oe() {
        final float[][] array1 = new float[2][2];
        final float[][] array2 = new float[2][2];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
            }
        }
        assertTrue(new EqualsBuilder().append(array1, array1).isEquals());
    }

    @Test
    public void testMultiFloatArray_2_oe() {
        final float[][] array1 = new float[2][2];
        final float[][] array2 = new float[2][2];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
            }
        }
        // removed other assertion
        assertTrue(new EqualsBuilder().append(array1, array2).isEquals());
    }

    @Test
    public void testMultiFloatArray_3_oe() {
        final float[][] array1 = new float[2][2];
        final float[][] array2 = new float[2][2];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
            }
        }
        // removed other assertion
        // removed other assertion
        array1[1][1] = 0;
        assertFalse(new EqualsBuilder().append(array1, array2).isEquals());
    }

    @Test
    public void testMultiDoubleArray_1_oe() {
        final double[][] array1 = new double[2][2];
        final double[][] array2 = new double[2][2];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
            }
        }
        assertTrue(new EqualsBuilder().append(array1, array1).isEquals());
    }

    @Test
    public void testMultiDoubleArray_2_oe() {
        final double[][] array1 = new double[2][2];
        final double[][] array2 = new double[2][2];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
            }
        }
        // removed other assertion
        assertTrue(new EqualsBuilder().append(array1, array2).isEquals());
    }

    @Test
    public void testMultiDoubleArray_3_oe() {
        final double[][] array1 = new double[2][2];
        final double[][] array2 = new double[2][2];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
            }
        }
        // removed other assertion
        // removed other assertion
        array1[1][1] = 0;
        assertFalse(new EqualsBuilder().append(array1, array2).isEquals());
    }

    @Test
    public void testMultiBooleanArray_1_oe() {
        final boolean[][] array1 = new boolean[2][2];
        final boolean[][] array2 = new boolean[2][2];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = i == 1 || j == 1;
                array2[i][j] = i == 1 || j == 1;
            }
        }
        assertTrue(new EqualsBuilder().append(array1, array1).isEquals());
    }

    @Test
    public void testMultiBooleanArray_2_oe() {
        final boolean[][] array1 = new boolean[2][2];
        final boolean[][] array2 = new boolean[2][2];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = i == 1 || j == 1;
                array2[i][j] = i == 1 || j == 1;
            }
        }
        // removed other assertion
        assertTrue(new EqualsBuilder().append(array1, array2).isEquals());
    }

    @Test
    public void testMultiBooleanArray_3_oe() {
        final boolean[][] array1 = new boolean[2][2];
        final boolean[][] array2 = new boolean[2][2];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = i == 1 || j == 1;
                array2[i][j] = i == 1 || j == 1;
            }
        }
        // removed other assertion
        // removed other assertion
        array1[1][1] = false;
        assertFalse(new EqualsBuilder().append(array1, array2).isEquals());
    }

    @Test
    public void testMultiBooleanArray_4_oe() {
        final boolean[][] array1 = new boolean[2][2];
        final boolean[][] array2 = new boolean[2][2];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = i == 1 || j == 1;
                array2[i][j] = i == 1 || j == 1;
            }
        }
        // removed other assertion
        // removed other assertion
        array1[1][1] = false;
        // removed other assertion

        // compare 1 dim to 2.
        final boolean[] array3 = new boolean[]{true, true};
        assertFalse(new EqualsBuilder().append(array1, array3).isEquals());
    }

    @Test
    public void testMultiBooleanArray_5_oe() {
        final boolean[][] array1 = new boolean[2][2];
        final boolean[][] array2 = new boolean[2][2];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = i == 1 || j == 1;
                array2[i][j] = i == 1 || j == 1;
            }
        }
        // removed other assertion
        // removed other assertion
        array1[1][1] = false;
        // removed other assertion

        // compare 1 dim to 2.
        final boolean[] array3 = new boolean[]{true, true};
        // removed other assertion
        assertFalse(new EqualsBuilder().append(array3, array1).isEquals());
    }

    @Test
    public void testMultiBooleanArray_6_oe() {
        final boolean[][] array1 = new boolean[2][2];
        final boolean[][] array2 = new boolean[2][2];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = i == 1 || j == 1;
                array2[i][j] = i == 1 || j == 1;
            }
        }
        // removed other assertion
        // removed other assertion
        array1[1][1] = false;
        // removed other assertion

        // compare 1 dim to 2.
        final boolean[] array3 = new boolean[]{true, true};
        // removed other assertion
        // removed other assertion
        assertFalse(new EqualsBuilder().append(array2, array3).isEquals());
    }

    @Test
    public void testMultiBooleanArray_7_oe() {
        final boolean[][] array1 = new boolean[2][2];
        final boolean[][] array2 = new boolean[2][2];
        for (int i = 0; i < array1.length; ++i) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = i == 1 || j == 1;
                array2[i][j] = i == 1 || j == 1;
            }
        }
        // removed other assertion
        // removed other assertion
        array1[1][1] = false;
        // removed other assertion

        // compare 1 dim to 2.
        final boolean[] array3 = new boolean[]{true, true};
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(new EqualsBuilder().append(array3, array2).isEquals());
    }

    @Test
    public void testRaggedArray_1_oe() {
        final long[][] array1 = new long[2][];
        final long[][] array2 = new long[2][];
        for (int i = 0; i < array1.length; ++i) {
            array1[i] = new long[2];
            array2[i] = new long[2];
            for (int j = 0; j < array1[i].length; ++j) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
            }
        }
        assertTrue(new EqualsBuilder().append(array1, array1).isEquals());
    }

    @Test
    public void testRaggedArray_2_oe() {
        final long[][] array1 = new long[2][];
        final long[][] array2 = new long[2][];
        for (int i = 0; i < array1.length; ++i) {
            array1[i] = new long[2];
            array2[i] = new long[2];
            for (int j = 0; j < array1[i].length; ++j) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
            }
        }
        // removed other assertion
        assertTrue(new EqualsBuilder().append(array1, array2).isEquals());
    }

    @Test
    public void testRaggedArray_3_oe() {
        final long[][] array1 = new long[2][];
        final long[][] array2 = new long[2][];
        for (int i = 0; i < array1.length; ++i) {
            array1[i] = new long[2];
            array2[i] = new long[2];
            for (int j = 0; j < array1[i].length; ++j) {
                array1[i][j] = (i + 1) * (j + 1);
                array2[i][j] = (i + 1) * (j + 1);
            }
        }
        // removed other assertion
        // removed other assertion
        array1[1][1] = 0;
        assertFalse(new EqualsBuilder().append(array1, array2).isEquals());
    }

    @Test
    public void testMixedArray_1_oe() {
        final Object[] array1 = new Object[2];
        final Object[] array2 = new Object[2];
        for (int i = 0; i < array1.length; ++i) {
            array1[i] = new long[2];
            array2[i] = new long[2];
            for (int j = 0; j < 2; ++j) {
                ((long[]) array1[i])[j] = (i + 1) * (j + 1);
                ((long[]) array2[i])[j] = (i + 1) * (j + 1);
            }
        }
        assertTrue(new EqualsBuilder().append(array1, array1).isEquals());
    }

    @Test
    public void testMixedArray_2_oe() {
        final Object[] array1 = new Object[2];
        final Object[] array2 = new Object[2];
        for (int i = 0; i < array1.length; ++i) {
            array1[i] = new long[2];
            array2[i] = new long[2];
            for (int j = 0; j < 2; ++j) {
                ((long[]) array1[i])[j] = (i + 1) * (j + 1);
                ((long[]) array2[i])[j] = (i + 1) * (j + 1);
            }
        }
        // removed other assertion
        assertTrue(new EqualsBuilder().append(array1, array2).isEquals());
    }

    @Test
    public void testMixedArray_3_oe() {
        final Object[] array1 = new Object[2];
        final Object[] array2 = new Object[2];
        for (int i = 0; i < array1.length; ++i) {
            array1[i] = new long[2];
            array2[i] = new long[2];
            for (int j = 0; j < 2; ++j) {
                ((long[]) array1[i])[j] = (i + 1) * (j + 1);
                ((long[]) array2[i])[j] = (i + 1) * (j + 1);
            }
        }
        // removed other assertion
        // removed other assertion
        ((long[]) array1[1])[1] = 0;
        assertFalse(new EqualsBuilder().append(array1, array2).isEquals());
    }

    @Test
    public void testObjectArrayHiddenByObject_1_oe() {
        final TestObject[] array1 = new TestObject[2];
        array1[0] = new TestObject(4);
        array1[1] = new TestObject(5);
        final TestObject[] array2 = new TestObject[2];
        array2[0] = new TestObject(4);
        array2[1] = new TestObject(5);
        final Object obj1 = array1;
        final Object obj2 = array2;
        assertTrue(new EqualsBuilder().append(obj1, obj1).isEquals());
    }

    @Test
    public void testObjectArrayHiddenByObject_2_oe() {
        final TestObject[] array1 = new TestObject[2];
        array1[0] = new TestObject(4);
        array1[1] = new TestObject(5);
        final TestObject[] array2 = new TestObject[2];
        array2[0] = new TestObject(4);
        array2[1] = new TestObject(5);
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, array1).isEquals());
    }

    @Test
    public void testObjectArrayHiddenByObject_3_oe() {
        final TestObject[] array1 = new TestObject[2];
        array1[0] = new TestObject(4);
        array1[1] = new TestObject(5);
        final TestObject[] array2 = new TestObject[2];
        array2[0] = new TestObject(4);
        array2[1] = new TestObject(5);
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testObjectArrayHiddenByObject_4_oe() {
        final TestObject[] array1 = new TestObject[2];
        array1[0] = new TestObject(4);
        array1[1] = new TestObject(5);
        final TestObject[] array2 = new TestObject[2];
        array2[0] = new TestObject(4);
        array2[1] = new TestObject(5);
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, array2).isEquals());
    }

    @Test
    public void testObjectArrayHiddenByObject_5_oe() {
        final TestObject[] array1 = new TestObject[2];
        array1[0] = new TestObject(4);
        array1[1] = new TestObject(5);
        final TestObject[] array2 = new TestObject[2];
        array2[0] = new TestObject(4);
        array2[1] = new TestObject(5);
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        array1[1].setA(6);
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testLongArrayHiddenByObject_1_oe() {
        final long[] array1 = new long[2];
        array1[0] = 5L;
        array1[1] = 6L;
        final long[] array2 = new long[2];
        array2[0] = 5L;
        array2[1] = 6L;
        final Object obj1 = array1;
        final Object obj2 = array2;
        assertTrue(new EqualsBuilder().append(obj1, obj1).isEquals());
    }

    @Test
    public void testLongArrayHiddenByObject_2_oe() {
        final long[] array1 = new long[2];
        array1[0] = 5L;
        array1[1] = 6L;
        final long[] array2 = new long[2];
        array2[0] = 5L;
        array2[1] = 6L;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, array1).isEquals());
    }

    @Test
    public void testLongArrayHiddenByObject_3_oe() {
        final long[] array1 = new long[2];
        array1[0] = 5L;
        array1[1] = 6L;
        final long[] array2 = new long[2];
        array2[0] = 5L;
        array2[1] = 6L;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testLongArrayHiddenByObject_4_oe() {
        final long[] array1 = new long[2];
        array1[0] = 5L;
        array1[1] = 6L;
        final long[] array2 = new long[2];
        array2[0] = 5L;
        array2[1] = 6L;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, array2).isEquals());
    }

    @Test
    public void testLongArrayHiddenByObject_5_oe() {
        final long[] array1 = new long[2];
        array1[0] = 5L;
        array1[1] = 6L;
        final long[] array2 = new long[2];
        array2[0] = 5L;
        array2[1] = 6L;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        array1[1] = 7;
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testIntArrayHiddenByObject_1_oe() {
        final int[] array1 = new int[2];
        array1[0] = 5;
        array1[1] = 6;
        final int[] array2 = new int[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        assertTrue(new EqualsBuilder().append(obj1, obj1).isEquals());
    }

    @Test
    public void testIntArrayHiddenByObject_2_oe() {
        final int[] array1 = new int[2];
        array1[0] = 5;
        array1[1] = 6;
        final int[] array2 = new int[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, array1).isEquals());
    }

    @Test
    public void testIntArrayHiddenByObject_3_oe() {
        final int[] array1 = new int[2];
        array1[0] = 5;
        array1[1] = 6;
        final int[] array2 = new int[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testIntArrayHiddenByObject_4_oe() {
        final int[] array1 = new int[2];
        array1[0] = 5;
        array1[1] = 6;
        final int[] array2 = new int[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, array2).isEquals());
    }

    @Test
    public void testIntArrayHiddenByObject_5_oe() {
        final int[] array1 = new int[2];
        array1[0] = 5;
        array1[1] = 6;
        final int[] array2 = new int[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        array1[1] = 7;
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testShortArrayHiddenByObject_1_oe() {
        final short[] array1 = new short[2];
        array1[0] = 5;
        array1[1] = 6;
        final short[] array2 = new short[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        assertTrue(new EqualsBuilder().append(obj1, obj1).isEquals());
    }

    @Test
    public void testShortArrayHiddenByObject_2_oe() {
        final short[] array1 = new short[2];
        array1[0] = 5;
        array1[1] = 6;
        final short[] array2 = new short[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, array1).isEquals());
    }

    @Test
    public void testShortArrayHiddenByObject_3_oe() {
        final short[] array1 = new short[2];
        array1[0] = 5;
        array1[1] = 6;
        final short[] array2 = new short[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testShortArrayHiddenByObject_4_oe() {
        final short[] array1 = new short[2];
        array1[0] = 5;
        array1[1] = 6;
        final short[] array2 = new short[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, array2).isEquals());
    }

    @Test
    public void testShortArrayHiddenByObject_5_oe() {
        final short[] array1 = new short[2];
        array1[0] = 5;
        array1[1] = 6;
        final short[] array2 = new short[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        array1[1] = 7;
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testCharArrayHiddenByObject_1_oe() {
        final char[] array1 = new char[2];
        array1[0] = 5;
        array1[1] = 6;
        final char[] array2 = new char[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        assertTrue(new EqualsBuilder().append(obj1, obj1).isEquals());
    }

    @Test
    public void testCharArrayHiddenByObject_2_oe() {
        final char[] array1 = new char[2];
        array1[0] = 5;
        array1[1] = 6;
        final char[] array2 = new char[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, array1).isEquals());
    }

    @Test
    public void testCharArrayHiddenByObject_3_oe() {
        final char[] array1 = new char[2];
        array1[0] = 5;
        array1[1] = 6;
        final char[] array2 = new char[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testCharArrayHiddenByObject_4_oe() {
        final char[] array1 = new char[2];
        array1[0] = 5;
        array1[1] = 6;
        final char[] array2 = new char[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, array2).isEquals());
    }

    @Test
    public void testCharArrayHiddenByObject_5_oe() {
        final char[] array1 = new char[2];
        array1[0] = 5;
        array1[1] = 6;
        final char[] array2 = new char[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        array1[1] = 7;
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testByteArrayHiddenByObject_1_oe() {
        final byte[] array1 = new byte[2];
        array1[0] = 5;
        array1[1] = 6;
        final byte[] array2 = new byte[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        assertTrue(new EqualsBuilder().append(obj1, obj1).isEquals());
    }

    @Test
    public void testByteArrayHiddenByObject_2_oe() {
        final byte[] array1 = new byte[2];
        array1[0] = 5;
        array1[1] = 6;
        final byte[] array2 = new byte[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, array1).isEquals());
    }

    @Test
    public void testByteArrayHiddenByObject_3_oe() {
        final byte[] array1 = new byte[2];
        array1[0] = 5;
        array1[1] = 6;
        final byte[] array2 = new byte[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testByteArrayHiddenByObject_4_oe() {
        final byte[] array1 = new byte[2];
        array1[0] = 5;
        array1[1] = 6;
        final byte[] array2 = new byte[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, array2).isEquals());
    }

    @Test
    public void testByteArrayHiddenByObject_5_oe() {
        final byte[] array1 = new byte[2];
        array1[0] = 5;
        array1[1] = 6;
        final byte[] array2 = new byte[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        array1[1] = 7;
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testDoubleArrayHiddenByObject_1_oe() {
        final double[] array1 = new double[2];
        array1[0] = 5;
        array1[1] = 6;
        final double[] array2 = new double[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        assertTrue(new EqualsBuilder().append(obj1, obj1).isEquals());
    }

    @Test
    public void testDoubleArrayHiddenByObject_2_oe() {
        final double[] array1 = new double[2];
        array1[0] = 5;
        array1[1] = 6;
        final double[] array2 = new double[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, array1).isEquals());
    }

    @Test
    public void testDoubleArrayHiddenByObject_3_oe() {
        final double[] array1 = new double[2];
        array1[0] = 5;
        array1[1] = 6;
        final double[] array2 = new double[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testDoubleArrayHiddenByObject_4_oe() {
        final double[] array1 = new double[2];
        array1[0] = 5;
        array1[1] = 6;
        final double[] array2 = new double[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, array2).isEquals());
    }

    @Test
    public void testDoubleArrayHiddenByObject_5_oe() {
        final double[] array1 = new double[2];
        array1[0] = 5;
        array1[1] = 6;
        final double[] array2 = new double[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        array1[1] = 7;
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testFloatArrayHiddenByObject_1_oe() {
        final float[] array1 = new float[2];
        array1[0] = 5;
        array1[1] = 6;
        final float[] array2 = new float[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        assertTrue(new EqualsBuilder().append(obj1, obj1).isEquals());
    }

    @Test
    public void testFloatArrayHiddenByObject_2_oe() {
        final float[] array1 = new float[2];
        array1[0] = 5;
        array1[1] = 6;
        final float[] array2 = new float[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, array1).isEquals());
    }

    @Test
    public void testFloatArrayHiddenByObject_3_oe() {
        final float[] array1 = new float[2];
        array1[0] = 5;
        array1[1] = 6;
        final float[] array2 = new float[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testFloatArrayHiddenByObject_4_oe() {
        final float[] array1 = new float[2];
        array1[0] = 5;
        array1[1] = 6;
        final float[] array2 = new float[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, array2).isEquals());
    }

    @Test
    public void testFloatArrayHiddenByObject_5_oe() {
        final float[] array1 = new float[2];
        array1[0] = 5;
        array1[1] = 6;
        final float[] array2 = new float[2];
        array2[0] = 5;
        array2[1] = 6;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        array1[1] = 7;
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testBooleanArrayHiddenByObject_1_oe() {
        final boolean[] array1 = new boolean[2];
        array1[0] = true;
        array1[1] = false;
        final boolean[] array2 = new boolean[2];
        array2[0] = true;
        array2[1] = false;
        final Object obj1 = array1;
        final Object obj2 = array2;
        assertTrue(new EqualsBuilder().append(obj1, obj1).isEquals());
    }

    @Test
    public void testBooleanArrayHiddenByObject_2_oe() {
        final boolean[] array1 = new boolean[2];
        array1[0] = true;
        array1[1] = false;
        final boolean[] array2 = new boolean[2];
        array2[0] = true;
        array2[1] = false;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, array1).isEquals());
    }

    @Test
    public void testBooleanArrayHiddenByObject_3_oe() {
        final boolean[] array1 = new boolean[2];
        array1[0] = true;
        array1[1] = false;
        final boolean[] array2 = new boolean[2];
        array2[0] = true;
        array2[1] = false;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testBooleanArrayHiddenByObject_4_oe() {
        final boolean[] array1 = new boolean[2];
        array1[0] = true;
        array1[1] = false;
        final boolean[] array2 = new boolean[2];
        array2[0] = true;
        array2[1] = false;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append(obj1, array2).isEquals());
    }

    @Test
    public void testBooleanArrayHiddenByObject_5_oe() {
        final boolean[] array1 = new boolean[2];
        array1[0] = true;
        array1[1] = false;
        final boolean[] array2 = new boolean[2];
        array2[0] = true;
        array2[1] = false;
        final Object obj1 = array1;
        final Object obj2 = array2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        array1[1] = true;
        assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
    }

    @Test
    public void testUnrelatedClasses_1_oe() {
        final Object[] x = new Object[]{new TestACanEqualB(1)};
        final Object[] y = new Object[]{new TestBCanEqualA(1)};

        // sanity checks:
        assertArrayEquals(x, x);
    }

    @Test
    public void testUnrelatedClasses_2_oe() {
        final Object[] x = new Object[]{new TestACanEqualB(1)};
        final Object[] y = new Object[]{new TestBCanEqualA(1)};

        // sanity checks:
        // removed other assertion
        assertArrayEquals(y, y);
    }

    @Test
    public void testUnrelatedClasses_3_oe() {
        final Object[] x = new Object[]{new TestACanEqualB(1)};
        final Object[] y = new Object[]{new TestBCanEqualA(1)};

        // sanity checks:
        // removed other assertion
        // removed other assertion
        assertArrayEquals(x, y);
    }

    @Test
    public void testUnrelatedClasses_4_oe() {
        final Object[] x = new Object[]{new TestACanEqualB(1)};
        final Object[] y = new Object[]{new TestBCanEqualA(1)};

        // sanity checks:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(y, x);
    }

    @Test
    public void testUnrelatedClasses_5_oe() {
        final Object[] x = new Object[]{new TestACanEqualB(1)};
        final Object[] y = new Object[]{new TestBCanEqualA(1)};

        // sanity checks:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // real tests:
        assertEquals(x[0], x[0]);
    }

    @Test
    public void testUnrelatedClasses_6_oe() {
        final Object[] x = new Object[]{new TestACanEqualB(1)};
        final Object[] y = new Object[]{new TestBCanEqualA(1)};

        // sanity checks:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // real tests:
        // removed other assertion
        assertEquals(y[0], y[0]);
    }

    @Test
    public void testUnrelatedClasses_7_oe() {
        final Object[] x = new Object[]{new TestACanEqualB(1)};
        final Object[] y = new Object[]{new TestBCanEqualA(1)};

        // sanity checks:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // real tests:
        // removed other assertion
        // removed other assertion
        assertEquals(x[0], y[0]);
    }

    @Test
    public void testUnrelatedClasses_8_oe() {
        final Object[] x = new Object[]{new TestACanEqualB(1)};
        final Object[] y = new Object[]{new TestBCanEqualA(1)};

        // sanity checks:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // real tests:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(y[0], x[0]);
    }

    @Test
    public void testUnrelatedClasses_9_oe() {
        final Object[] x = new Object[]{new TestACanEqualB(1)};
        final Object[] y = new Object[]{new TestBCanEqualA(1)};

        // sanity checks:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // real tests:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append(x, x).isEquals());
    }

    @Test
    public void testUnrelatedClasses_10_oe() {
        final Object[] x = new Object[]{new TestACanEqualB(1)};
        final Object[] y = new Object[]{new TestBCanEqualA(1)};

        // sanity checks:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // real tests:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append(y, y).isEquals());
    }

    @Test
    public void testUnrelatedClasses_11_oe() {
        final Object[] x = new Object[]{new TestACanEqualB(1)};
        final Object[] y = new Object[]{new TestBCanEqualA(1)};

        // sanity checks:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // real tests:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append(x, y).isEquals());
    }

    @Test
    public void testUnrelatedClasses_12_oe() {
        final Object[] x = new Object[]{new TestACanEqualB(1)};
        final Object[] y = new Object[]{new TestBCanEqualA(1)};

        // sanity checks:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // real tests:
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(new EqualsBuilder().append(y, x).isEquals());
    }

    @Test
    public void testReflectionEqualsExcludeFields_1_oe() {
        final TestObjectWithMultipleFields x1 = new TestObjectWithMultipleFields(1, 2, 3);
        final TestObjectWithMultipleFields x2 = new TestObjectWithMultipleFields(1, 3, 4);

        // not equal when including all fields
        assertFalse(EqualsBuilder.reflectionEquals(x1, x2));
    }

    @Test
    public void testReflectionEqualsExcludeFields_2_oe() {
        final TestObjectWithMultipleFields x1 = new TestObjectWithMultipleFields(1, 2, 3);
        final TestObjectWithMultipleFields x2 = new TestObjectWithMultipleFields(1, 3, 4);

        // not equal when including all fields
        // removed other assertion

        // doesn't barf on null, empty array, or non-existent field, but still tests as not equal
        assertFalse(EqualsBuilder.reflectionEquals(x1, x2, (String[]) null));
    }

    @Test
    public void testReflectionEqualsExcludeFields_3_oe() {
        final TestObjectWithMultipleFields x1 = new TestObjectWithMultipleFields(1, 2, 3);
        final TestObjectWithMultipleFields x2 = new TestObjectWithMultipleFields(1, 3, 4);

        // not equal when including all fields
        // removed other assertion

        // doesn't barf on null, empty array, or non-existent field, but still tests as not equal
        // removed other assertion
        assertFalse(EqualsBuilder.reflectionEquals(x1, x2));
    }

    @Test
    public void testReflectionEqualsExcludeFields_4_oe() {
        final TestObjectWithMultipleFields x1 = new TestObjectWithMultipleFields(1, 2, 3);
        final TestObjectWithMultipleFields x2 = new TestObjectWithMultipleFields(1, 3, 4);

        // not equal when including all fields
        // removed other assertion

        // doesn't barf on null, empty array, or non-existent field, but still tests as not equal
        // removed other assertion
        // removed other assertion
        assertFalse(EqualsBuilder.reflectionEquals(x1, x2, "xxx"));
    }

    @Test
    public void testReflectionEqualsExcludeFields_5_oe() {
        final TestObjectWithMultipleFields x1 = new TestObjectWithMultipleFields(1, 2, 3);
        final TestObjectWithMultipleFields x2 = new TestObjectWithMultipleFields(1, 3, 4);

        // not equal when including all fields
        // removed other assertion

        // doesn't barf on null, empty array, or non-existent field, but still tests as not equal
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // not equal if only one of the differing fields excluded
        assertFalse(EqualsBuilder.reflectionEquals(x1, x2, "two"));
    }

    @Test
    public void testReflectionEqualsExcludeFields_6_oe() {
        final TestObjectWithMultipleFields x1 = new TestObjectWithMultipleFields(1, 2, 3);
        final TestObjectWithMultipleFields x2 = new TestObjectWithMultipleFields(1, 3, 4);

        // not equal when including all fields
        // removed other assertion

        // doesn't barf on null, empty array, or non-existent field, but still tests as not equal
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // not equal if only one of the differing fields excluded
        // removed other assertion
        assertFalse(EqualsBuilder.reflectionEquals(x1, x2, "three"));
    }

    @Test
    public void testReflectionEqualsExcludeFields_7_oe() {
        final TestObjectWithMultipleFields x1 = new TestObjectWithMultipleFields(1, 2, 3);
        final TestObjectWithMultipleFields x2 = new TestObjectWithMultipleFields(1, 3, 4);

        // not equal when including all fields
        // removed other assertion

        // doesn't barf on null, empty array, or non-existent field, but still tests as not equal
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // not equal if only one of the differing fields excluded
        // removed other assertion
        // removed other assertion

        // equal if both differing fields excluded
        assertTrue(EqualsBuilder.reflectionEquals(x1, x2, "two", "three"));
    }

    @Test
    public void testReflectionEqualsExcludeFields_8_oe() {
        final TestObjectWithMultipleFields x1 = new TestObjectWithMultipleFields(1, 2, 3);
        final TestObjectWithMultipleFields x2 = new TestObjectWithMultipleFields(1, 3, 4);

        // not equal when including all fields
        // removed other assertion

        // doesn't barf on null, empty array, or non-existent field, but still tests as not equal
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // not equal if only one of the differing fields excluded
        // removed other assertion
        // removed other assertion

        // equal if both differing fields excluded
        // removed other assertion

        // still equal as long as both differing fields are among excluded
        assertTrue(EqualsBuilder.reflectionEquals(x1, x2, "one", "two", "three"));
    }

    @Test
    public void testReflectionEqualsExcludeFields_9_oe() {
        final TestObjectWithMultipleFields x1 = new TestObjectWithMultipleFields(1, 2, 3);
        final TestObjectWithMultipleFields x2 = new TestObjectWithMultipleFields(1, 3, 4);

        // not equal when including all fields
        // removed other assertion

        // doesn't barf on null, empty array, or non-existent field, but still tests as not equal
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // not equal if only one of the differing fields excluded
        // removed other assertion
        // removed other assertion

        // equal if both differing fields excluded
        // removed other assertion

        // still equal as long as both differing fields are among excluded
        // removed other assertion
        assertTrue(EqualsBuilder.reflectionEquals(x1, x2, "one", "two", "three", "xxx"));
    }

    @Test
    public void testCyclicalObjectReferences_1_oe() {
        final TestObjectReference refX1 = new TestObjectReference(1);
        final TestObjectReference x1 = new TestObjectReference(1);
        x1.setObjectReference(refX1);
        refX1.setObjectReference(x1);

        final TestObjectReference refX2 = new TestObjectReference(1);
        final TestObjectReference x2 = new TestObjectReference(1);
        x2.setObjectReference(refX2);
        refX2.setObjectReference(x2);

        final TestObjectReference refX3 = new TestObjectReference(2);
        final TestObjectReference x3 = new TestObjectReference(2);
        x3.setObjectReference(refX3);
        refX3.setObjectReference(x3);

        assertEquals(x1, x2);
    }

    @Test
    public void testCyclicalObjectReferences_2_oe() {
        final TestObjectReference refX1 = new TestObjectReference(1);
        final TestObjectReference x1 = new TestObjectReference(1);
        x1.setObjectReference(refX1);
        refX1.setObjectReference(x1);

        final TestObjectReference refX2 = new TestObjectReference(1);
        final TestObjectReference x2 = new TestObjectReference(1);
        x2.setObjectReference(refX2);
        refX2.setObjectReference(x2);

        final TestObjectReference refX3 = new TestObjectReference(2);
        final TestObjectReference x3 = new TestObjectReference(2);
        x3.setObjectReference(refX3);
        refX3.setObjectReference(x3);

        // removed other assertion
        assertNull(EqualsBuilder.getRegistry());
    }

    @Test
    public void testCyclicalObjectReferences_3_oe() {
        final TestObjectReference refX1 = new TestObjectReference(1);
        final TestObjectReference x1 = new TestObjectReference(1);
        x1.setObjectReference(refX1);
        refX1.setObjectReference(x1);

        final TestObjectReference refX2 = new TestObjectReference(1);
        final TestObjectReference x2 = new TestObjectReference(1);
        x2.setObjectReference(refX2);
        refX2.setObjectReference(x2);

        final TestObjectReference refX3 = new TestObjectReference(2);
        final TestObjectReference x3 = new TestObjectReference(2);
        x3.setObjectReference(refX3);
        refX3.setObjectReference(x3);

        // removed other assertion
        // removed other assertion
        assertNotEquals(x1, x3);
    }

    @Test
    public void testCyclicalObjectReferences_4_oe() {
        final TestObjectReference refX1 = new TestObjectReference(1);
        final TestObjectReference x1 = new TestObjectReference(1);
        x1.setObjectReference(refX1);
        refX1.setObjectReference(x1);

        final TestObjectReference refX2 = new TestObjectReference(1);
        final TestObjectReference x2 = new TestObjectReference(1);
        x2.setObjectReference(refX2);
        refX2.setObjectReference(x2);

        final TestObjectReference refX3 = new TestObjectReference(2);
        final TestObjectReference x3 = new TestObjectReference(2);
        x3.setObjectReference(refX3);
        refX3.setObjectReference(x3);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(EqualsBuilder.getRegistry());
    }

    @Test
    public void testCyclicalObjectReferences_5_oe() {
        final TestObjectReference refX1 = new TestObjectReference(1);
        final TestObjectReference x1 = new TestObjectReference(1);
        x1.setObjectReference(refX1);
        refX1.setObjectReference(x1);

        final TestObjectReference refX2 = new TestObjectReference(1);
        final TestObjectReference x2 = new TestObjectReference(1);
        x2.setObjectReference(refX2);
        refX2.setObjectReference(x2);

        final TestObjectReference refX3 = new TestObjectReference(2);
        final TestObjectReference x3 = new TestObjectReference(2);
        x3.setObjectReference(refX3);
        refX3.setObjectReference(x3);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(x2, x3);
    }

    @Test
    public void testCyclicalObjectReferences_6_oe() {
        final TestObjectReference refX1 = new TestObjectReference(1);
        final TestObjectReference x1 = new TestObjectReference(1);
        x1.setObjectReference(refX1);
        refX1.setObjectReference(x1);

        final TestObjectReference refX2 = new TestObjectReference(1);
        final TestObjectReference x2 = new TestObjectReference(1);
        x2.setObjectReference(refX2);
        refX2.setObjectReference(x2);

        final TestObjectReference refX3 = new TestObjectReference(2);
        final TestObjectReference x3 = new TestObjectReference(2);
        x3.setObjectReference(refX3);
        refX3.setObjectReference(x3);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(EqualsBuilder.getRegistry());
    }

    @Test
    public void testReflectionArrays_1_oe() {

        final TestObject one = new TestObject(1);
        final TestObject two = new TestObject(2);

        final Object[] o1 = new Object[]{one};
        final Object[] o2 = new Object[]{two};
        final Object[] o3 = new Object[]{one};

        assertFalse(EqualsBuilder.reflectionEquals(o1, o2));
    }

    @Test
    public void testReflectionArrays_2_oe() {

        final TestObject one = new TestObject(1);
        final TestObject two = new TestObject(2);

        final Object[] o1 = new Object[]{one};
        final Object[] o2 = new Object[]{two};
        final Object[] o3 = new Object[]{one};

        // removed other assertion
        assertTrue(EqualsBuilder.reflectionEquals(o1, o1));
    }

    @Test
    public void testReflectionArrays_3_oe() {

        final TestObject one = new TestObject(1);
        final TestObject two = new TestObject(2);

        final Object[] o1 = new Object[]{one};
        final Object[] o2 = new Object[]{two};
        final Object[] o3 = new Object[]{one};

        // removed other assertion
        // removed other assertion
        assertTrue(EqualsBuilder.reflectionEquals(o1, o3));
    }

    @Test
    public void testReflectionArrays_4_oe() {

        final TestObject one = new TestObject(1);
        final TestObject two = new TestObject(2);

        final Object[] o1 = new Object[]{one};
        final Object[] o2 = new Object[]{two};
        final Object[] o3 = new Object[]{one};

        // removed other assertion
        // removed other assertion
        // removed other assertion

        final double[] d1 = {0, 1};
        final double[] d2 = {2, 3};
        final double[] d3 = {0, 1};

        assertFalse(EqualsBuilder.reflectionEquals(d1, d2));
    }

    @Test
    public void testReflectionArrays_5_oe() {

        final TestObject one = new TestObject(1);
        final TestObject two = new TestObject(2);

        final Object[] o1 = new Object[]{one};
        final Object[] o2 = new Object[]{two};
        final Object[] o3 = new Object[]{one};

        // removed other assertion
        // removed other assertion
        // removed other assertion

        final double[] d1 = {0, 1};
        final double[] d2 = {2, 3};
        final double[] d3 = {0, 1};

        // removed other assertion
        assertTrue(EqualsBuilder.reflectionEquals(d1, d1));
    }

    @Test
    public void testReflectionArrays_6_oe() {

        final TestObject one = new TestObject(1);
        final TestObject two = new TestObject(2);

        final Object[] o1 = new Object[]{one};
        final Object[] o2 = new Object[]{two};
        final Object[] o3 = new Object[]{one};

        // removed other assertion
        // removed other assertion
        // removed other assertion

        final double[] d1 = {0, 1};
        final double[] d2 = {2, 3};
        final double[] d3 = {0, 1};

        // removed other assertion
        // removed other assertion
        assertTrue(EqualsBuilder.reflectionEquals(d1, d3));
    }

    @Test
    public void testToEqualsExclude_1_oe() {
        TestObjectEqualsExclude one = new TestObjectEqualsExclude(1, 2);
        TestObjectEqualsExclude two = new TestObjectEqualsExclude(1, 3);

        assertFalse(EqualsBuilder.reflectionEquals(one, two));
    }

    @Test
    public void testToEqualsExclude_2_oe() {
        TestObjectEqualsExclude one = new TestObjectEqualsExclude(1, 2);
        TestObjectEqualsExclude two = new TestObjectEqualsExclude(1, 3);

        // removed other assertion

        one = new TestObjectEqualsExclude(1, 2);
        two = new TestObjectEqualsExclude(2, 2);

        assertTrue(EqualsBuilder.reflectionEquals(one, two));
    }

    @Test
    public void testReflectionAppend_1_oe() {
        assertTrue(EqualsBuilder.reflectionEquals(null, null));
    }

    @Test
    public void testReflectionAppend_2_oe() {
        // removed other assertion

        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        assertTrue(new EqualsBuilder().reflectionAppend(o1, o1).build());
    }

    @Test
    public void testReflectionAppend_3_oe() {
        // removed other assertion

        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        assertFalse(new EqualsBuilder().reflectionAppend(o1, o2).build());
    }

    @Test
    public void testReflectionAppend_4_oe() {
        // removed other assertion

        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        // removed other assertion

        o2.setA(4);
        assertTrue(new EqualsBuilder().reflectionAppend(o1, o2).build());
    }

    @Test
    public void testReflectionAppend_5_oe() {
        // removed other assertion

        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        // removed other assertion

        o2.setA(4);
        // removed other assertion

        assertFalse(new EqualsBuilder().reflectionAppend(o1, this).build());
    }

    @Test
    public void testReflectionAppend_6_oe() {
        // removed other assertion

        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        // removed other assertion

        o2.setA(4);
        // removed other assertion

        // removed other assertion

        assertFalse(new EqualsBuilder().reflectionAppend(o1, null).build());
    }

    @Test
    public void testReflectionAppend_7_oe() {
        // removed other assertion

        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        // removed other assertion
        // removed other assertion

        o2.setA(4);
        // removed other assertion

        // removed other assertion

        // removed other assertion
        assertFalse(new EqualsBuilder().reflectionAppend(null, o2).build());
    }

}

