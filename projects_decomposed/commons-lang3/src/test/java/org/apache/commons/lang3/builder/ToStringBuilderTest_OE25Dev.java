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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for {@link org.apache.commons.lang3.builder.ToStringBuilder}.
 */
public class ToStringBuilderTest_OE25Dev {

    // See LANG-1337 for more.
    private static final int ARRAYLIST_INITIAL_CAPACITY = 10;
    private final Integer base = Integer.valueOf(5);
    private final String baseStr = base.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(base));

    /*
     * All tests should leave the registry empty.
     */
    @AfterEach
    public void after() {
        validateNullToStringStyleRegistry();
    }

    //-----------------------------------------------------------------------

    @Test
    public void testGetSetDefault() {
        try {
            ToStringBuilder.setDefaultStyle(ToStringStyle.NO_FIELD_NAMES_STYLE);
            assertSame(ToStringStyle.NO_FIELD_NAMES_STYLE, ToStringBuilder.getDefaultStyle());
        } finally {
            // reset for other tests
            ToStringBuilder.setDefaultStyle(ToStringStyle.DEFAULT_STYLE);
        }
    }

    /**
     * Test wrapper for int primitive.
     */

    /**
     * Test wrapper for char primitive.
     */

    /**
     * Test wrapper for char boolean.
     */

    /**
     * Create the same toString() as Object.toString().
     * @param o the object to create the string for.
     * @return a String in the Object.toString format.
     */
    private String toBaseString(final Object o) {
        return o.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(o));
    }

    // Reflection Array tests

    //
    // Note on the following line of code repeated in the reflection array tests.
    //
    // assertReflectionArray("<null>", array);
    //
    // The expected value is not baseStr + "[<null>]" since array==null and is typed as Object.
    // The null array does not carry array type information.
    // If we added a primitive array type constructor and pile of associated methods,
    // then type declaring type information could be carried forward. IMHO, null is null.
    //
    // Gary Gregory - 2003-03-12 - ggregory@seagullsw.com
    //

    public void assertReflectionArray(final String expected, final Object actual) {
        if (actual == null) {
            // Until ToStringBuilder supports null objects.
            return;
        }
        assertEquals(expected, ToStringBuilder.reflectionToString(actual));
        assertEquals(expected, ToStringBuilder.reflectionToString(actual, null));
        assertEquals(expected, ToStringBuilder.reflectionToString(actual, null, true));
        assertEquals(expected, ToStringBuilder.reflectionToString(actual, null, false));
    }

    // Reflection Array Array tests

    // Reflection hierarchy tests
    @Test
    public void testReflectionHierarchyArrayList() {
        // LANG-1337 without this, the generated string can differ depending on the JVM version/vendor
        final List<Object> list = new ArrayList<>(ARRAYLIST_INITIAL_CAPACITY);
        final String baseString = this.toBaseString(list);
        final String expectedWithTransients = baseString + "[elementData={<null>,<null>,<null>,<null>,<null>,<null>,<null>,<null>,<null>,<null>},size=0,modCount=0]";
        final String toStringWithTransients = ToStringBuilder.reflectionToString(list, null, true);
        if (!expectedWithTransients.equals(toStringWithTransients)) {
            assertEquals(expectedWithTransients, toStringWithTransients);
        }
        final String expectedWithoutTransients = baseString + "[size=0]";
        final String toStringWithoutTransients = ToStringBuilder.reflectionToString(list, null, false);
        if (!expectedWithoutTransients.equals(toStringWithoutTransients)) {
            assertEquals(expectedWithoutTransients, toStringWithoutTransients);
        }
    }

    static class ReflectionTestFixtureA {
        @SuppressWarnings("unused")
        private final char a='a';
        @SuppressWarnings("unused")
        private final transient char transientA='t';
    }

    static class ReflectionTestFixtureB extends ReflectionTestFixtureA {
        @SuppressWarnings("unused")
        private final char b='b';
        @SuppressWarnings("unused")
        private final transient char transientB='t';
    }

    static class Outer {
        Inner inner = new Inner();
        class Inner {
            @Override
            public String toString() {
                return ToStringBuilder.reflectionToString(this);
            }
        }
        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }

    // Reflection cycle tests

    /**
     * Test an array element pointing to its container.
     */

    /**
     * Test an array element pointing to its container.
     */

    /**
     * A reflection test fixture.
     */
    static class ReflectionTestCycleA {
        ReflectionTestCycleB b;

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }

    /**
     * A reflection test fixture.
     */
    static class ReflectionTestCycleB {
        ReflectionTestCycleA a;

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }

    /**
     * A reflection test fixture.
     */
    static class SimpleReflectionTestFixture {
        Object o;

        SimpleReflectionTestFixture() {
        }

        SimpleReflectionTestFixture(final Object o) {
            this.o = o;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }

    private static class SelfInstanceVarReflectionTestFixture {
        @SuppressWarnings("unused")
        private final SelfInstanceVarReflectionTestFixture typeIsSelf;

        SelfInstanceVarReflectionTestFixture() {
            this.typeIsSelf = this;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
      }

    private static class SelfInstanceTwoVarsReflectionTestFixture {
        @SuppressWarnings("unused")
        private final SelfInstanceTwoVarsReflectionTestFixture typeIsSelf;
        private final String otherType = "The Other Type";

        SelfInstanceTwoVarsReflectionTestFixture() {
            this.typeIsSelf = this;
        }

        public String getOtherType() {
            return this.otherType;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
      }


    /**
     * Test an Object pointing to itself, the simplest test.
     */

    /**
     * Test a class that defines an ivar pointing to itself.
     */

    /**
     * Test a class that defines an ivar pointing to itself.  This test was
     * created to show that handling cyclical object resulted in a missing endFieldSeparator call.
     */


    /**
     * Test Objects pointing to each other.
     */

    /**
     * Test a nasty combination of arrays and Objects pointing to each other.
     * objects[0] -&gt; SimpleReflectionTestFixture[ o -&gt; objects ]
     */

    void validateNullToStringStyleRegistry() {
        final Map<Object, Object> registry = ToStringStyle.getRegistry();
        assertNull(registry, "Expected null, actual: " + registry);
    }
    //  End: Reflection cycle tests

    static class ObjectCycle {
        Object obj;

        @Override
        public String toString() {
            return new ToStringBuilder(this).append(obj).toString();
        }
    }

    /**
     * Tests ReflectionToStringBuilder.toString() for statics.
     */

    /**
     * Tests ReflectionToStringBuilder.toString() for statics.
     */

    /**
     * <p>This method uses reflection to build a suitable
     * {@code toString} value which includes static fields.</p>
     *
     * <p>It uses {@code AccessibleObject.setAccessible} to gain access to private
     * fields. This means that it will throw a security exception if run
     * under a security manager, if the permissions are not set up correctly.
     * It is also not as efficient as testing explicitly. </p>
     *
     * <p>Transient fields are not output.</p>
     *
     * <p>Superclass fields will be appended up to and including the specified superclass.
     * A null superclass is treated as {@code java.lang.Object}.</p>
     *
     * <p>If the style is {@code null}, the default
     * {@code ToStringStyle} is used.</p>
     *
     * @param <T> the type of the output object
     * @param object  the Object to be output
     * @param style  the style of the {@code toString} to create,
     *  may be {@code null}
     * @param reflectUpToClass  the superclass to reflect up to (inclusive),
     *  may be {@code null}
     * @return the String result
     * @throws IllegalArgumentException if the Object is {@code null}
     */
    public <T> String toStringWithStatics(final T object, final ToStringStyle style, final Class<? super T> reflectUpToClass) {
        return ReflectionToStringBuilder.toString(object, style, false, true, reflectUpToClass);
    }

    /**
     * Tests ReflectionToStringBuilder setUpToClass().
     */
    @Test
    public void test_setUpToClass_valid() {
        final Integer val = Integer.valueOf(5);
        final ReflectionToStringBuilder test = new ReflectionToStringBuilder(val);
        test.setUpToClass(Number.class);
        test.toString();
    }

    /**
     * Tests ReflectionToStringBuilder setUpToClass().
     */
    @Test
    public void test_setUpToClass_invalid() {
        final Integer val = Integer.valueOf(5);
        final ReflectionToStringBuilder test = new ReflectionToStringBuilder(val);
        assertThrows(IllegalArgumentException.class, () -> test.setUpToClass(String.class));
        test.toString();
    }

    /**
     * Tests ReflectionToStringBuilder.toString() for statics.
     */
    class ReflectionStaticFieldsFixture {
        static final String staticString = "staticString";
        static final int staticInt = 12345;
        static final transient String staticTransientString = "staticTransientString";
        static final transient int staticTransientInt = 54321;
        String instanceString = "instanceString";
        int instanceInt = 67890;
        transient String transientString = "transientString";
        transient int transientInt = 98765;
    }

    /**
     * Test fixture for ReflectionToStringBuilder.toString() for statics.
     */
    class SimpleReflectionStaticFieldsFixture {
        static final String staticString = "staticString";
        static final int staticInt = 12345;
    }

    /**
     * Test fixture for ReflectionToStringBuilder.toString() for statics.
     */
    @SuppressWarnings("unused")
    class InheritedReflectionStaticFieldsFixture extends SimpleReflectionStaticFieldsFixture {
        static final String staticString2 = "staticString2";
        static final int staticInt2 = 67890;
    }

    /**
     * Points out failure to print anything from appendToString methods using MULTI_LINE_STYLE.
     * See issue LANG-372.
     */
    class MultiLineTestObject {
        Integer i = Integer.valueOf(31337);
        @Override
        public String toString() {
            return new ToStringBuilder(this).append("testInt", i).toString();
        }
    }

    @Test
    public void testConstructorEx1_1_oe() {
        assertEquals("<null>", new ToStringBuilder(null).toString());
    }

    @Test
    public void testConstructorEx2_1_oe() {
        assertEquals("<null>", new ToStringBuilder(null, null).toString());
    }

    @Test
    public void testConstructorEx3_1_oe() {
        assertEquals("<null>", new ToStringBuilder(null, null, null).toString());
    }

    @Test
    public void testSetDefaultEx_1_oe() throws Exception {
        try {
    ToStringBuilder.setDefaultStyle(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testBlank_1_oe() {
        assertEquals(baseStr + "[]", new ToStringBuilder(base).toString());
    }

    @Test
    public void testReflectionInteger_1_oe() {
        assertEquals(baseStr + "[value=5]", ToStringBuilder.reflectionToString(base));
    }

    @Test
    public void testReflectionCharacter_1_oe() {
        final Character c = 'A';
        assertEquals(this.toBaseString(c) + "[value=A]", ToStringBuilder.reflectionToString(c));
    }

    @Test
    public void testReflectionBoolean_1_oe() {
        Boolean b;
        b = Boolean.TRUE;
        assertEquals(this.toBaseString(b) + "[value=true]", ToStringBuilder.reflectionToString(b));
    }

    @Test
    public void testReflectionBoolean_2_oe() {
        Boolean b;
        b = Boolean.TRUE;
        // removed other assertion
        b = Boolean.FALSE;
        assertEquals(this.toBaseString(b) + "[value=false]", ToStringBuilder.reflectionToString(b));
    }

    @Test
    public void testReflectionObjectArray_1_oe() {
        Object[] array = new Object[] { null, base, new int[] { 3, 6 } };
        final String baseString = this.toBaseString(array);
        assertEquals(baseString + "[{<null>,5,{3,6}}]", ToStringBuilder.reflectionToString(array));
    }

    @Test
    public void testReflectionLongArray_1_oe() {
        long[] array = new long[] { 1, 2, -3, 4 };
        final String baseString = this.toBaseString(array);
        assertEquals(baseString + "[{1,2,-3,4}]", ToStringBuilder.reflectionToString(array));
    }

    @Test
    public void testReflectionIntArray_1_oe() {
        int[] array = new int[] { 1, 2, -3, 4 };
        final String baseString = this.toBaseString(array);
        assertEquals(baseString + "[{1,2,-3,4}]", ToStringBuilder.reflectionToString(array));
    }

    @Test
    public void testReflectionShortArray_1_oe() {
        short[] array = new short[] { 1, 2, -3, 4 };
        final String baseString = this.toBaseString(array);
        assertEquals(baseString + "[{1,2,-3,4}]", ToStringBuilder.reflectionToString(array));
    }

    @Test
    public void testReflectionyteArray_1_oe() {
        byte[] array = new byte[] { 1, 2, -3, 4 };
        final String baseString = this.toBaseString(array);
        assertEquals(baseString + "[{1,2,-3,4}]", ToStringBuilder.reflectionToString(array));
    }

    @Test
    public void testReflectionCharArray_1_oe() {
        char[] array = new char[] { 'A', '2', '_', 'D' };
        final String baseString = this.toBaseString(array);
        assertEquals(baseString + "[{A,2,_,D}]", ToStringBuilder.reflectionToString(array));
    }

    @Test
    public void testReflectionDoubleArray_1_oe() {
        double[] array = new double[] { 1.0, 2.9876, -3.00001, 4.3 };
        final String baseString = this.toBaseString(array);
        assertEquals(baseString + "[{1.0,2.9876,-3.00001,4.3}]", ToStringBuilder.reflectionToString(array));
    }

    @Test
    public void testReflectionFloatArray_1_oe() {
        float[] array = new float[] { 1.0f, 2.9876f, -3.00001f, 4.3f };
        final String baseString = this.toBaseString(array);
        assertEquals(baseString + "[{1.0,2.9876,-3.00001,4.3}]", ToStringBuilder.reflectionToString(array));
    }

    @Test
    public void testReflectionBooleanArray_1_oe() {
        boolean[] array = new boolean[] { true, false, false };
        final String baseString = this.toBaseString(array);
        assertEquals(baseString + "[{true,false,false}]", ToStringBuilder.reflectionToString(array));
    }

    @Test
    public void testReflectionFloatArrayArray_1_oe() {
        float[][] array = new float[][] { { 1.0f, 2.29686f }, null, { Float.NaN } };
        final String baseString = this.toBaseString(array);
        assertEquals(baseString + "[{{1.0,2.29686},<null>,{NaN}}]", ToStringBuilder.reflectionToString(array));
    }

    @Test
    public void testReflectionLongArrayArray_1_oe() {
        long[][] array = new long[][] { { 1, 2 }, null, { 5 } };
        final String baseString = this.toBaseString(array);
        assertEquals(baseString + "[{{1,2},<null>,{5}}]", ToStringBuilder.reflectionToString(array));
    }

    @Test
    public void testReflectionIntArrayArray_1_oe() {
        int[][] array = new int[][] { { 1, 2 }, null, { 5 } };
        final String baseString = this.toBaseString(array);
        assertEquals(baseString + "[{{1,2},<null>,{5}}]", ToStringBuilder.reflectionToString(array));
    }

    @Test
    public void testReflectionhortArrayArray_1_oe() {
        short[][] array = new short[][] { { 1, 2 }, null, { 5 } };
        final String baseString = this.toBaseString(array);
        assertEquals(baseString + "[{{1,2},<null>,{5}}]", ToStringBuilder.reflectionToString(array));
    }

    @Test
    public void testReflectionByteArrayArray_1_oe() {
        byte[][] array = new byte[][] { { 1, 2 }, null, { 5 } };
        final String baseString = this.toBaseString(array);
        assertEquals(baseString + "[{{1,2},<null>,{5}}]", ToStringBuilder.reflectionToString(array));
    }

    @Test
    public void testReflectionCharArrayArray_1_oe() {
        char[][] array = new char[][] { { 'A', 'B' }, null, { 'p' } };
        final String baseString = this.toBaseString(array);
        assertEquals(baseString + "[{{A,B},<null>,{p}}]", ToStringBuilder.reflectionToString(array));
    }

    @Test
    public void testReflectionBooleanArrayArray_1_oe() {
        boolean[][] array = new boolean[][] { { true, false }, null, { false } };
        final String baseString = this.toBaseString(array);
        assertEquals(baseString + "[{{true,false},<null>,{false}}]", ToStringBuilder.reflectionToString(array));
    }

    @Test
    public void testReflectionBooleanArrayArray_2_oe() {
        boolean[][] array = new boolean[][] { { true, false }, null, { false } };
        final String baseString = this.toBaseString(array);
        // removed other assertion
        assertEquals(baseString + "[{{true,false},<null>,{false}}]", ToStringBuilder.reflectionToString(array));
    }

    @Test
    public void testReflectionHierarchy_1_oe() {
        final ReflectionTestFixtureA baseA = new ReflectionTestFixtureA();
        String baseString = this.toBaseString(baseA);
        assertEquals(baseString + "[a=a]", ToStringBuilder.reflectionToString(baseA));
    }

    @Test
    public void testReflectionHierarchy_2_oe() {
        final ReflectionTestFixtureA baseA = new ReflectionTestFixtureA();
        String baseString = this.toBaseString(baseA);
        // removed other assertion
        assertEquals(baseString + "[a=a]", ToStringBuilder.reflectionToString(baseA, null));
    }

    @Test
    public void testReflectionHierarchy_3_oe() {
        final ReflectionTestFixtureA baseA = new ReflectionTestFixtureA();
        String baseString = this.toBaseString(baseA);
        // removed other assertion
        // removed other assertion
        assertEquals(baseString + "[a=a]", ToStringBuilder.reflectionToString(baseA, null, false));
    }

    @Test
    public void testReflectionHierarchy_4_oe() {
        final ReflectionTestFixtureA baseA = new ReflectionTestFixtureA();
        String baseString = this.toBaseString(baseA);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseString + "[a=a,transientA=t]", ToStringBuilder.reflectionToString(baseA, null, true));
    }

    @Test
    public void testReflectionHierarchy_5_oe() {
        final ReflectionTestFixtureA baseA = new ReflectionTestFixtureA();
        String baseString = this.toBaseString(baseA);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseString + "[a=a]", ToStringBuilder.reflectionToString(baseA, null, false, null));
    }

    @Test
    public void testReflectionHierarchy_6_oe() {
        final ReflectionTestFixtureA baseA = new ReflectionTestFixtureA();
        String baseString = this.toBaseString(baseA);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseString + "[a=a]", ToStringBuilder.reflectionToString(baseA, null, false, Object.class));
    }

    @Test
    public void testReflectionHierarchy_7_oe() {
        final ReflectionTestFixtureA baseA = new ReflectionTestFixtureA();
        String baseString = this.toBaseString(baseA);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseString + "[a=a]", ToStringBuilder.reflectionToString(baseA, null, false, ReflectionTestFixtureA.class));
    }

    @Test
    public void testReflectionHierarchy_8_oe() {
        final ReflectionTestFixtureA baseA = new ReflectionTestFixtureA();
        String baseString = this.toBaseString(baseA);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final ReflectionTestFixtureB baseB = new ReflectionTestFixtureB();
        baseString = this.toBaseString(baseB);
        assertEquals(baseString + "[b=b,a=a]", ToStringBuilder.reflectionToString(baseB));
    }

    @Test
    public void testReflectionHierarchy_9_oe() {
        final ReflectionTestFixtureA baseA = new ReflectionTestFixtureA();
        String baseString = this.toBaseString(baseA);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final ReflectionTestFixtureB baseB = new ReflectionTestFixtureB();
        baseString = this.toBaseString(baseB);
        // removed other assertion
        assertEquals(baseString + "[b=b,a=a]", ToStringBuilder.reflectionToString(baseB));
    }

    @Test
    public void testReflectionHierarchy_10_oe() {
        final ReflectionTestFixtureA baseA = new ReflectionTestFixtureA();
        String baseString = this.toBaseString(baseA);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final ReflectionTestFixtureB baseB = new ReflectionTestFixtureB();
        baseString = this.toBaseString(baseB);
        // removed other assertion
        // removed other assertion
        assertEquals(baseString + "[b=b,a=a]", ToStringBuilder.reflectionToString(baseB, null));
    }

    @Test
    public void testReflectionHierarchy_11_oe() {
        final ReflectionTestFixtureA baseA = new ReflectionTestFixtureA();
        String baseString = this.toBaseString(baseA);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final ReflectionTestFixtureB baseB = new ReflectionTestFixtureB();
        baseString = this.toBaseString(baseB);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseString + "[b=b,a=a]", ToStringBuilder.reflectionToString(baseB, null, false));
    }

    @Test
    public void testReflectionHierarchy_12_oe() {
        final ReflectionTestFixtureA baseA = new ReflectionTestFixtureA();
        String baseString = this.toBaseString(baseA);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final ReflectionTestFixtureB baseB = new ReflectionTestFixtureB();
        baseString = this.toBaseString(baseB);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseString + "[b=b,transientB=t,a=a,transientA=t]", ToStringBuilder.reflectionToString(baseB, null, true));
    }

    @Test
    public void testReflectionHierarchy_13_oe() {
        final ReflectionTestFixtureA baseA = new ReflectionTestFixtureA();
        String baseString = this.toBaseString(baseA);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final ReflectionTestFixtureB baseB = new ReflectionTestFixtureB();
        baseString = this.toBaseString(baseB);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseString + "[b=b,a=a]", ToStringBuilder.reflectionToString(baseB, null, false, null));
    }

    @Test
    public void testReflectionHierarchy_14_oe() {
        final ReflectionTestFixtureA baseA = new ReflectionTestFixtureA();
        String baseString = this.toBaseString(baseA);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final ReflectionTestFixtureB baseB = new ReflectionTestFixtureB();
        baseString = this.toBaseString(baseB);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseString + "[b=b,a=a]", ToStringBuilder.reflectionToString(baseB, null, false, Object.class));
    }

    @Test
    public void testReflectionHierarchy_15_oe() {
        final ReflectionTestFixtureA baseA = new ReflectionTestFixtureA();
        String baseString = this.toBaseString(baseA);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final ReflectionTestFixtureB baseB = new ReflectionTestFixtureB();
        baseString = this.toBaseString(baseB);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseString + "[b=b,a=a]", ToStringBuilder.reflectionToString(baseB, null, false, ReflectionTestFixtureA.class));
    }

    @Test
    public void testReflectionHierarchy_16_oe() {
        final ReflectionTestFixtureA baseA = new ReflectionTestFixtureA();
        String baseString = this.toBaseString(baseA);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final ReflectionTestFixtureB baseB = new ReflectionTestFixtureB();
        baseString = this.toBaseString(baseB);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseString + "[b=b]", ToStringBuilder.reflectionToString(baseB, null, false, ReflectionTestFixtureB.class));
    }

    @Test
    public void testInnerClassReflection_1_oe() {
        final Outer outer = new Outer();
        assertEquals(toBaseString(outer) + "[inner=" + toBaseString(outer.inner) + "[]]", outer.toString());
    }

    @Test
    public void testReflectionArrayCycle_1_oe() {
        final Object[] objects = new Object[1];
        objects[0] = objects;
        assertEquals( this.toBaseString(objects) + "[{" + this.toBaseString(objects) + "}]", ToStringBuilder.reflectionToString(objects));
    }

    @Test
    public void testReflectionArrayCycleLevel2_1_oe() {
        final Object[] objects = new Object[1];
        final Object[] objectsLevel2 = new Object[1];
        objects[0] = objectsLevel2;
        objectsLevel2[0] = objects;
        assertEquals( this.toBaseString(objects) + "[{{" + this.toBaseString(objects) + "}}]", ToStringBuilder.reflectionToString(objects));
    }

    @Test
    public void testReflectionArrayCycleLevel2_2_oe() {
        final Object[] objects = new Object[1];
        final Object[] objectsLevel2 = new Object[1];
        objects[0] = objectsLevel2;
        objectsLevel2[0] = objects;
        // removed other assertion
        assertEquals( this.toBaseString(objectsLevel2) + "[{{" + this.toBaseString(objectsLevel2) + "}}]", ToStringBuilder.reflectionToString(objectsLevel2));
    }

    @Test
    public void testReflectionArrayArrayCycle_1_oe() {
        final Object[][] objects = new Object[2][2];
        objects[0][0] = objects;
        objects[0][1] = objects;
        objects[1][0] = objects;
        objects[1][1] = objects;
        final String basicToString = this.toBaseString(objects);
        assertEquals( basicToString + "[{{" + basicToString + "," + basicToString + "},{" + basicToString + "," + basicToString + "}}]", ToStringBuilder.reflectionToString(objects));
    }

    @Test
    public void testSimpleReflectionObjectCycle_1_oe() {
        final SimpleReflectionTestFixture simple = new SimpleReflectionTestFixture();
        simple.o = simple;
        assertEquals(this.toBaseString(simple) + "[o=" + this.toBaseString(simple) + "]", simple.toString());
    }

    @Test
    public void testSelfInstanceVarReflectionObjectCycle_1_oe() {
        final SelfInstanceVarReflectionTestFixture test = new SelfInstanceVarReflectionTestFixture();
        assertEquals(this.toBaseString(test) + "[typeIsSelf=" + this.toBaseString(test) + "]", test.toString());
    }

    @Test
    public void testSelfInstanceTwoVarsReflectionObjectCycle_1_oe() {
        final SelfInstanceTwoVarsReflectionTestFixture test = new SelfInstanceTwoVarsReflectionTestFixture();
        assertEquals(this.toBaseString(test) + "[otherType=" + test.getOtherType().toString() + ",typeIsSelf=" + this.toBaseString(test)  + "]", test.toString());
    }

    @Test
    public void testReflectionObjectCycle_1_oe() {
        final ReflectionTestCycleA a = new ReflectionTestCycleA();
        final ReflectionTestCycleB b = new ReflectionTestCycleB();
        a.b = b;
        b.a = a;
        assertEquals(this.toBaseString(a)+ "[b=" + this.toBaseString(b)+ "[a=" + this.toBaseString(a)+ "]]",a.toString());
    }

    @Test
    public void testReflectionArrayAndObjectCycle_1_oe() {
        final Object[] objects = new Object[1];
        final SimpleReflectionTestFixture simple = new SimpleReflectionTestFixture(objects);
        objects[0] = simple;
        assertEquals( this.toBaseString(objects) + "[{" + this.toBaseString(simple) + "[o=" + this.toBaseString(objects) + "]" + "}]", ToStringBuilder.reflectionToString(objects));
    }

    @Test
    public void testReflectionArrayAndObjectCycle_2_oe() {
        final Object[] objects = new Object[1];
        final SimpleReflectionTestFixture simple = new SimpleReflectionTestFixture(objects);
        objects[0] = simple;
        // removed other assertion
        assertEquals( this.toBaseString(simple) + "[o={" + this.toBaseString(simple) + "}]", ToStringBuilder.reflectionToString(simple));
    }

    @Test
    public void testAppendSuper_1_oe() {
        assertEquals(baseStr + "[]", new ToStringBuilder(base).appendSuper("Integer@8888[]").toString());
    }

    @Test
    public void testAppendSuper_2_oe() {
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).appendSuper("Integer@8888[<null>]").toString());
    }

    @Test
    public void testAppendSuper_3_oe() {
        // removed other assertion
        // removed other assertion

        assertEquals(baseStr + "[a=hello]", new ToStringBuilder(base).appendSuper("Integer@8888[]").append("a", "hello").toString());
    }

    @Test
    public void testAppendSuper_4_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(baseStr + "[<null>,a=hello]", new ToStringBuilder(base).appendSuper("Integer@8888[<null>]").append("a", "hello").toString());
    }

    @Test
    public void testAppendSuper_5_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=hello]", new ToStringBuilder(base).appendSuper(null).append("a", "hello").toString());
    }

    @Test
    public void testAppendToString_1_oe() {
        assertEquals(baseStr + "[]", new ToStringBuilder(base).appendToString("Integer@8888[]").toString());
    }

    @Test
    public void testAppendToString_2_oe() {
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).appendToString("Integer@8888[<null>]").toString());
    }

    @Test
    public void testAppendToString_3_oe() {
        // removed other assertion
        // removed other assertion

        assertEquals(baseStr + "[a=hello]", new ToStringBuilder(base).appendToString("Integer@8888[]").append("a", "hello").toString());
    }

    @Test
    public void testAppendToString_4_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(baseStr + "[<null>,a=hello]", new ToStringBuilder(base).appendToString("Integer@8888[<null>]").append("a", "hello").toString());
    }

    @Test
    public void testAppendToString_5_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=hello]", new ToStringBuilder(base).appendToString(null).append("a", "hello").toString());
    }

    @Test
    public void testAppendAsObjectToString_1_oe() {
        final String objectToAppend1 = "";
        final Boolean objectToAppend2 = Boolean.TRUE;
        final Object objectToAppend3 = new Object();

        assertEquals(baseStr + "[" + toBaseString(objectToAppend1)+ "]",new ToStringBuilder(base).appendAsObjectToString(objectToAppend1).toString());
    }

    @Test
    public void testAppendAsObjectToString_2_oe() {
        final String objectToAppend1 = "";
        final Boolean objectToAppend2 = Boolean.TRUE;
        final Object objectToAppend3 = new Object();

        // removed other assertion
        assertEquals(baseStr + "[" + toBaseString(objectToAppend2)+ "]",new ToStringBuilder(base).appendAsObjectToString(objectToAppend2).toString());
    }

    @Test
    public void testAppendAsObjectToString_3_oe() {
        final String objectToAppend1 = "";
        final Boolean objectToAppend2 = Boolean.TRUE;
        final Object objectToAppend3 = new Object();

        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[" + toBaseString(objectToAppend3)+ "]",new ToStringBuilder(base).appendAsObjectToString(objectToAppend3).toString());
    }

    @Test
    public void testAppendBooleanArrayWithFieldName_1_oe() {
        final boolean[] array = new boolean[] { true, false, false };
        assertEquals(baseStr + "[flags={true,false,false}]", new ToStringBuilder(base).append("flags", array).toString());
    }

    @Test
    public void testAppendBooleanArrayWithFieldName_2_oe() {
        final boolean[] array = new boolean[] { true, false, false };
        // removed other assertion
        assertEquals(baseStr + "[flags=<null>]",new ToStringBuilder(base).append("flags",(boolean[])null).toString());
    }

    @Test
    public void testAppendBooleanArrayWithFieldName_3_oe() {
        final boolean[] array = new boolean[] { true, false, false };
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(null, (boolean[]) null).toString());
    }

    @Test
    public void testAppendBooleanArrayWithFieldName_4_oe() {
        final boolean[] array = new boolean[] { true, false, false };
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[{true,false,false}]", new ToStringBuilder(base).append(null, array).toString());
    }

    @Test
    public void testAppendBooleanArrayWithFieldNameAndFullDetatil_1_oe() {
        final boolean[] array = new boolean[] { true, false, false };
        assertEquals(baseStr + "[flags={true,false,false}]", new ToStringBuilder(base).append("flags", array, true).toString());
    }

    @Test
    public void testAppendBooleanArrayWithFieldNameAndFullDetatil_2_oe() {
        final boolean[] array = new boolean[] { true, false, false };
        // removed other assertion
        assertEquals(baseStr + "[length=<size=3>]",new ToStringBuilder(base).append("length",array,false).toString());
    }

    @Test
    public void testAppendBooleanArrayWithFieldNameAndFullDetatil_3_oe() {
        final boolean[] array = new boolean[] { true, false, false };
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[flags=<null>]",new ToStringBuilder(base).append("flags",(boolean[])null,true).toString());
    }

    @Test
    public void testAppendBooleanArrayWithFieldNameAndFullDetatil_4_oe() {
        final boolean[] array = new boolean[] { true, false, false };
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(null, (boolean[]) null, false).toString());
    }

    @Test
    public void testAppendBooleanArrayWithFieldNameAndFullDetatil_5_oe() {
        final boolean[] array = new boolean[] { true, false, false };
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[<size=3>]", new ToStringBuilder(base).append(null, array, false).toString());
    }

    @Test
    public void testAppendCharArrayWithFieldName_1_oe() {
        final char[] array = new char[] { 'A', '2', '_', 'D' };
        assertEquals(baseStr + "[chars={A,2,_,D}]", new ToStringBuilder(base).append("chars", array).toString());
    }

    @Test
    public void testAppendCharArrayWithFieldName_2_oe() {
        final char[] array = new char[] { 'A', '2', '_', 'D' };
        // removed other assertion
        assertEquals(baseStr + "[letters={A,2,_,D}]", new ToStringBuilder(base).append("letters", array).toString());
    }

    @Test
    public void testAppendCharArrayWithFieldName_3_oe() {
        final char[] array = new char[] { 'A', '2', '_', 'D' };
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[flags=<null>]",new ToStringBuilder(base).append("flags",(boolean[])null).toString());
    }

    @Test
    public void testAppendCharArrayWithFieldName_4_oe() {
        final char[] array = new char[] { 'A', '2', '_', 'D' };
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(null, (boolean[]) null).toString());
    }

    @Test
    public void testAppendCharArrayWithFieldName_5_oe() {
        final char[] array = new char[] { 'A', '2', '_', 'D' };
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[{A,2,_,D}]", new ToStringBuilder(base).append(null, array).toString());
    }

    @Test
    public void testAppendCharArrayWithFieldNameAndFullDetatil_1_oe() {
        final char[] array = new char[] { 'A', '2', '_', 'D' };
        assertEquals(baseStr + "[chars={A,2,_,D}]", new ToStringBuilder(base).append("chars", array, true).toString());
    }

    @Test
    public void testAppendCharArrayWithFieldNameAndFullDetatil_2_oe() {
        final char[] array = new char[] { 'A', '2', '_', 'D' };
        // removed other assertion
        assertEquals(baseStr + "[letters=<size=4>]",new ToStringBuilder(base).append("letters",array,false).toString());
    }

    @Test
    public void testAppendCharArrayWithFieldNameAndFullDetatil_3_oe() {
        final char[] array = new char[] { 'A', '2', '_', 'D' };
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[flags=<null>]",new ToStringBuilder(base).append("flags",(boolean[])null,true).toString());
    }

    @Test
    public void testAppendCharArrayWithFieldNameAndFullDetatil_4_oe() {
        final char[] array = new char[] { 'A', '2', '_', 'D' };
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(null, (boolean[]) null, false).toString());
    }

    @Test
    public void testAppendCharArrayWithFieldNameAndFullDetatil_5_oe() {
        final char[] array = new char[] { 'A', '2', '_', 'D' };
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[<size=4>]", new ToStringBuilder(base).append(null, array, false).toString());
    }

    @Test
    public void testAppendDoubleArrayWithFieldName_1_oe() {
        final double[] array = new double[] { 1.0, 2.9876, -3.00001, 4.3 };
        assertEquals(baseStr + "[values={1.0,2.9876,-3.00001,4.3}]", new ToStringBuilder(base).append("values", array).toString());
    }

    @Test
    public void testAppendDoubleArrayWithFieldName_2_oe() {
        final double[] array = new double[] { 1.0, 2.9876, -3.00001, 4.3 };
        // removed other assertion
        assertEquals(baseStr + "[values=<null>]",new ToStringBuilder(base).append("values",(boolean[])null).toString());
    }

    @Test
    public void testAppendDoubleArrayWithFieldName_3_oe() {
        final double[] array = new double[] { 1.0, 2.9876, -3.00001, 4.3 };
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(null, (boolean[]) null).toString());
    }

    @Test
    public void testAppendDoubleArrayWithFieldName_4_oe() {
        final double[] array = new double[] { 1.0, 2.9876, -3.00001, 4.3 };
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[{1.0,2.9876,-3.00001,4.3}]", new ToStringBuilder(base).append(null, array).toString());
    }

    @Test
    public void testAppendDoubleArrayWithFieldNameAndFullDetatil_1_oe() {
        final double[] array = new double[] { 1.0, 2.9876, -3.00001, 4.3 };
        assertEquals(baseStr + "[values={1.0,2.9876,-3.00001,4.3}]", new ToStringBuilder(base).append("values", array, true).toString());
    }

    @Test
    public void testAppendDoubleArrayWithFieldNameAndFullDetatil_2_oe() {
        final double[] array = new double[] { 1.0, 2.9876, -3.00001, 4.3 };
        // removed other assertion
        assertEquals(baseStr + "[length=<size=4>]",new ToStringBuilder(base).append("length",array,false).toString());
    }

    @Test
    public void testAppendDoubleArrayWithFieldNameAndFullDetatil_3_oe() {
        final double[] array = new double[] { 1.0, 2.9876, -3.00001, 4.3 };
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[values=<null>]",new ToStringBuilder(base).append("values",(boolean[])null,true).toString());
    }

    @Test
    public void testAppendDoubleArrayWithFieldNameAndFullDetatil_4_oe() {
        final double[] array = new double[] { 1.0, 2.9876, -3.00001, 4.3 };
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(null, (boolean[]) null, false).toString());
    }

    @Test
    public void testAppendDoubleArrayWithFieldNameAndFullDetatil_5_oe() {
        final double[] array = new double[] { 1.0, 2.9876, -3.00001, 4.3 };
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[<size=4>]", new ToStringBuilder(base).append(null, array, false).toString());
    }

    @Test
    public void testAppendObjectArrayWithFieldName_1_oe() {
        final Object[] array = new Object[] { null, base, new int[] { 3, 6 } };
        assertEquals(baseStr + "[values={<null>,5,{3,6}}]", new ToStringBuilder(base).append("values", array).toString());
    }

    @Test
    public void testAppendObjectArrayWithFieldName_2_oe() {
        final Object[] array = new Object[] { null, base, new int[] { 3, 6 } };
        // removed other assertion
        assertEquals(baseStr + "[values=<null>]",new ToStringBuilder(base).append("values",(boolean[])null).toString());
    }

    @Test
    public void testAppendObjectArrayWithFieldName_3_oe() {
        final Object[] array = new Object[] { null, base, new int[] { 3, 6 } };
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(null, (boolean[]) null).toString());
    }

    @Test
    public void testAppendObjectArrayWithFieldName_4_oe() {
        final Object[] array = new Object[] { null, base, new int[] { 3, 6 } };
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[{<null>,5,{3,6}}]", new ToStringBuilder(base).append(null, array).toString());
    }

    @Test
    public void testAppendObjectArrayWithFieldNameAndFullDetatil_1_oe() {
       final Object[] array = new Object[] { null, base, new int[] { 3, 6 } };
       assertEquals(baseStr + "[values={<null>,5,{3,6}}]", new ToStringBuilder(base).append("values", array, true).toString());
    }

    @Test
    public void testAppendObjectArrayWithFieldNameAndFullDetatil_2_oe() {
       final Object[] array = new Object[] { null, base, new int[] { 3, 6 } };
       // removed other assertion
       assertEquals(baseStr + "[length=<size=3>]",new ToStringBuilder(base).append("length",array,false).toString());
    }

    @Test
    public void testAppendObjectArrayWithFieldNameAndFullDetatil_3_oe() {
       final Object[] array = new Object[] { null, base, new int[] { 3, 6 } };
       // removed other assertion
       // removed other assertion
       assertEquals(baseStr + "[values=<null>]",new ToStringBuilder(base).append("values",(boolean[])null,true).toString());
    }

    @Test
    public void testAppendObjectArrayWithFieldNameAndFullDetatil_4_oe() {
       final Object[] array = new Object[] { null, base, new int[] { 3, 6 } };
       // removed other assertion
       // removed other assertion
       // removed other assertion
       assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(null, (boolean[]) null, false).toString());
    }

    @Test
    public void testAppendObjectArrayWithFieldNameAndFullDetatil_5_oe() {
       final Object[] array = new Object[] { null, base, new int[] { 3, 6 } };
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       assertEquals(baseStr + "[<size=3>]", new ToStringBuilder(base).append(null, array, false).toString());
    }

    @Test
    public void testAppendLongArrayWithFieldName_1_oe() {
       final long[] array = new long[] { 1, 2, -3, 4 };
       assertEquals(baseStr + "[values={1,2,-3,4}]", new ToStringBuilder(base).append("values", array).toString());
    }

    @Test
    public void testAppendLongArrayWithFieldName_2_oe() {
       final long[] array = new long[] { 1, 2, -3, 4 };
       // removed other assertion
       assertEquals(baseStr + "[values=<null>]",new ToStringBuilder(base).append("values",(boolean[])null).toString());
    }

    @Test
    public void testAppendLongArrayWithFieldName_3_oe() {
       final long[] array = new long[] { 1, 2, -3, 4 };
       // removed other assertion
       // removed other assertion
       assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(null, (boolean[]) null).toString());
    }

    @Test
    public void testAppendLongArrayWithFieldName_4_oe() {
       final long[] array = new long[] { 1, 2, -3, 4 };
       // removed other assertion
       // removed other assertion
       // removed other assertion
       assertEquals(baseStr + "[{1,2,-3,4}]", new ToStringBuilder(base).append(null, array).toString());
    }

    @Test
    public void testAppendLongArrayWithFieldNameAndFullDetatil_1_oe() {
        final long[] array = new long[] { 1, 2, -3, 4 };
        assertEquals(baseStr + "[values={1,2,-3,4}]", new ToStringBuilder(base).append("values", array, true).toString());
    }

    @Test
    public void testAppendLongArrayWithFieldNameAndFullDetatil_2_oe() {
        final long[] array = new long[] { 1, 2, -3, 4 };
        // removed other assertion
        assertEquals(baseStr + "[length=<size=4>]",new ToStringBuilder(base).append("length",array,false).toString());
    }

    @Test
    public void testAppendLongArrayWithFieldNameAndFullDetatil_3_oe() {
        final long[] array = new long[] { 1, 2, -3, 4 };
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[values=<null>]",new ToStringBuilder(base).append("values",(boolean[])null,true).toString());
    }

    @Test
    public void testAppendLongArrayWithFieldNameAndFullDetatil_4_oe() {
        final long[] array = new long[] { 1, 2, -3, 4 };
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(null, (boolean[]) null, false).toString());
    }

    @Test
    public void testAppendLongArrayWithFieldNameAndFullDetatil_5_oe() {
        final long[] array = new long[] { 1, 2, -3, 4 };
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[<size=4>]", new ToStringBuilder(base).append(null, array, false).toString());
    }

    @Test
    public void testAppendIntArrayWithFieldName_1_oe() {
        final int[] array = new int[] { 1, 2, -3, 4 };
        assertEquals(baseStr + "[values={1,2,-3,4}]", new ToStringBuilder(base).append("values", array).toString());
    }

    @Test
    public void testAppendIntArrayWithFieldName_2_oe() {
        final int[] array = new int[] { 1, 2, -3, 4 };
        // removed other assertion
        assertEquals(baseStr + "[values=<null>]",new ToStringBuilder(base).append("values",(boolean[])null).toString());
    }

    @Test
    public void testAppendIntArrayWithFieldName_3_oe() {
        final int[] array = new int[] { 1, 2, -3, 4 };
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(null, (boolean[]) null).toString());
    }

    @Test
    public void testAppendIntArrayWithFieldName_4_oe() {
        final int[] array = new int[] { 1, 2, -3, 4 };
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[{1,2,-3,4}]", new ToStringBuilder(base).append(null, array).toString());
    }

    @Test
    public void testAppendIntArrayWithFieldNameAndFullDetatil_1_oe() {
        final int[] array = new int[] { 1, 2, -3, 4 };
        assertEquals(baseStr + "[values={1,2,-3,4}]", new ToStringBuilder(base).append("values", array, true).toString());
    }

    @Test
    public void testAppendIntArrayWithFieldNameAndFullDetatil_2_oe() {
        final int[] array = new int[] { 1, 2, -3, 4 };
        // removed other assertion
        assertEquals(baseStr + "[length=<size=4>]",new ToStringBuilder(base).append("length",array,false).toString());
    }

    @Test
    public void testAppendIntArrayWithFieldNameAndFullDetatil_3_oe() {
        final int[] array = new int[] { 1, 2, -3, 4 };
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[values=<null>]",new ToStringBuilder(base).append("values",(boolean[])null,true).toString());
    }

    @Test
    public void testAppendIntArrayWithFieldNameAndFullDetatil_4_oe() {
        final int[] array = new int[] { 1, 2, -3, 4 };
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(null, (boolean[]) null, false).toString());
    }

    @Test
    public void testAppendIntArrayWithFieldNameAndFullDetatil_5_oe() {
        final int[] array = new int[] { 1, 2, -3, 4 };
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[<size=4>]", new ToStringBuilder(base).append(null, array, false).toString());
    }

    @Test
    public void testAppendShortArrayWithFieldName_1_oe() {
        final short[] array = new short[] { 1, 2, -3, 4 };
        assertEquals(baseStr + "[values={1,2,-3,4}]", new ToStringBuilder(base).append("values", array).toString());
    }

    @Test
    public void testAppendShortArrayWithFieldName_2_oe() {
        final short[] array = new short[] { 1, 2, -3, 4 };
        // removed other assertion
        assertEquals(baseStr + "[values=<null>]",new ToStringBuilder(base).append("values",(boolean[])null).toString());
    }

    @Test
    public void testAppendShortArrayWithFieldName_3_oe() {
        final short[] array = new short[] { 1, 2, -3, 4 };
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(null, (boolean[]) null).toString());
    }

    @Test
    public void testAppendShortArrayWithFieldName_4_oe() {
        final short[] array = new short[] { 1, 2, -3, 4 };
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[{1,2,-3,4}]", new ToStringBuilder(base).append(null, array).toString());
    }

    @Test
    public void testAppendShortArrayWithFieldNameAndFullDetatil_1_oe() {
        final short[] array = new short[] { 1, 2, -3, 4 };
        assertEquals(baseStr + "[values={1,2,-3,4}]", new ToStringBuilder(base).append("values", array, true).toString());
    }

    @Test
    public void testAppendShortArrayWithFieldNameAndFullDetatil_2_oe() {
        final short[] array = new short[] { 1, 2, -3, 4 };
        // removed other assertion
        assertEquals(baseStr + "[length=<size=4>]",new ToStringBuilder(base).append("length",array,false).toString());
    }

    @Test
    public void testAppendShortArrayWithFieldNameAndFullDetatil_3_oe() {
        final short[] array = new short[] { 1, 2, -3, 4 };
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[values=<null>]",new ToStringBuilder(base).append("values",(boolean[])null,true).toString());
    }

    @Test
    public void testAppendShortArrayWithFieldNameAndFullDetatil_4_oe() {
        final short[] array = new short[] { 1, 2, -3, 4 };
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(null, (boolean[]) null, false).toString());
    }

    @Test
    public void testAppendShortArrayWithFieldNameAndFullDetatil_5_oe() {
        final short[] array = new short[] { 1, 2, -3, 4 };
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[<size=4>]", new ToStringBuilder(base).append(null, array, false).toString());
    }

    @Test
    public void testAppendByteArrayWithFieldName_1_oe() {
        final byte[] array = new byte[] { 1, 2, -3, 4 };
        assertEquals(baseStr + "[values={1,2,-3,4}]", new ToStringBuilder(base).append("values", array).toString());
    }

    @Test
    public void testAppendByteArrayWithFieldName_2_oe() {
        final byte[] array = new byte[] { 1, 2, -3, 4 };
        // removed other assertion
        assertEquals(baseStr + "[values=<null>]",new ToStringBuilder(base).append("values",(boolean[])null).toString());
    }

    @Test
    public void testAppendByteArrayWithFieldName_3_oe() {
        final byte[] array = new byte[] { 1, 2, -3, 4 };
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(null, (boolean[]) null).toString());
    }

    @Test
    public void testAppendByteArrayWithFieldName_4_oe() {
        final byte[] array = new byte[] { 1, 2, -3, 4 };
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[{1,2,-3,4}]", new ToStringBuilder(base).append(null, array).toString());
    }

    @Test
    public void testAppendByteArrayWithFieldNameAndFullDetatil_1_oe() {
        final byte[] array = new byte[] { 1, 2, -3, 4 };
        assertEquals(baseStr + "[values={1,2,-3,4}]", new ToStringBuilder(base).append("values", array, true).toString());
    }

    @Test
    public void testAppendByteArrayWithFieldNameAndFullDetatil_2_oe() {
        final byte[] array = new byte[] { 1, 2, -3, 4 };
        // removed other assertion
        assertEquals(baseStr + "[length=<size=4>]",new ToStringBuilder(base).append("length",array,false).toString());
    }

    @Test
    public void testAppendByteArrayWithFieldNameAndFullDetatil_3_oe() {
        final byte[] array = new byte[] { 1, 2, -3, 4 };
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[values=<null>]",new ToStringBuilder(base).append("values",(boolean[])null,true).toString());
    }

    @Test
    public void testAppendByteArrayWithFieldNameAndFullDetatil_4_oe() {
        final byte[] array = new byte[] { 1, 2, -3, 4 };
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(null, (boolean[]) null, false).toString());
    }

    @Test
    public void testAppendByteArrayWithFieldNameAndFullDetatil_5_oe() {
        final byte[] array = new byte[] { 1, 2, -3, 4 };
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[<size=4>]", new ToStringBuilder(base).append(null, array, false).toString());
    }

    @Test
    public void testAppendFloatArrayWithFieldName_1_oe() {
        final float[] array = new float[] { 1.0f, 2.9876f, -3.00001f, 4.3f };
        assertEquals(baseStr + "[values={1.0,2.9876,-3.00001,4.3}]", new ToStringBuilder(base).append("values", array).toString());
    }

    @Test
    public void testAppendFloatArrayWithFieldName_2_oe() {
        final float[] array = new float[] { 1.0f, 2.9876f, -3.00001f, 4.3f };
        // removed other assertion
        assertEquals(baseStr + "[values=<null>]",new ToStringBuilder(base).append("values",(boolean[])null).toString());
    }

    @Test
    public void testAppendFloatArrayWithFieldName_3_oe() {
        final float[] array = new float[] { 1.0f, 2.9876f, -3.00001f, 4.3f };
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(null, (boolean[]) null).toString());
    }

    @Test
    public void testAppendFloatArrayWithFieldName_4_oe() {
        final float[] array = new float[] { 1.0f, 2.9876f, -3.00001f, 4.3f };
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[{1.0,2.9876,-3.00001,4.3}]", new ToStringBuilder(base).append(null, array).toString());
    }

    @Test
    public void testAppendFloatArrayWithFieldNameAndFullDetatil_1_oe() {
        final float[] array = new float[] { 1.0f, 2.9876f, -3.00001f, 4.3f };
        assertEquals(baseStr + "[values={1.0,2.9876,-3.00001,4.3}]", new ToStringBuilder(base).append("values", array, true).toString());
    }

    @Test
    public void testAppendFloatArrayWithFieldNameAndFullDetatil_2_oe() {
        final float[] array = new float[] { 1.0f, 2.9876f, -3.00001f, 4.3f };
        // removed other assertion
        assertEquals(baseStr + "[length=<size=4>]",new ToStringBuilder(base).append("length",array,false).toString());
    }

    @Test
    public void testAppendFloatArrayWithFieldNameAndFullDetatil_3_oe() {
        final float[] array = new float[] { 1.0f, 2.9876f, -3.00001f, 4.3f };
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[values=<null>]",new ToStringBuilder(base).append("values",(boolean[])null,true).toString());
    }

    @Test
    public void testAppendFloatArrayWithFieldNameAndFullDetatil_4_oe() {
        final float[] array = new float[] { 1.0f, 2.9876f, -3.00001f, 4.3f };
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(null, (boolean[]) null, false).toString());
    }

    @Test
    public void testAppendFloatArrayWithFieldNameAndFullDetatil_5_oe() {
        final float[] array = new float[] { 1.0f, 2.9876f, -3.00001f, 4.3f };
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[<size=4>]", new ToStringBuilder(base).append(null, array, false).toString());
    }

    @Test
    public void testConstructToStringBuilder_3_oe() {
        final ToStringBuilder stringBuilder1 = new ToStringBuilder(base, null, null);
        final ToStringBuilder stringBuilder2 = new ToStringBuilder(base, ToStringStyle.DEFAULT_STYLE, new StringBuffer(1024));
        // removed other assertion
        // removed other assertion
        assertNotNull(stringBuilder1.toString());
    }

    @Test
    public void testConstructToStringBuilder_6_oe() {
        final ToStringBuilder stringBuilder1 = new ToStringBuilder(base, null, null);
        final ToStringBuilder stringBuilder2 = new ToStringBuilder(base, ToStringStyle.DEFAULT_STYLE, new StringBuffer(1024));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotNull(stringBuilder2.toString());
    }

    @Test
    public void testObject_1_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append((Object) null).toString());
    }

    @Test
    public void testObject_2_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        assertEquals(baseStr + "[3]", new ToStringBuilder(base).append(i3).toString());
    }

    @Test
    public void testObject_3_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=<null>]", new ToStringBuilder(base).append("a", (Object) null).toString());
    }

    @Test
    public void testObject_4_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=3]", new ToStringBuilder(base).append("a", i3).toString());
    }

    @Test
    public void testObject_5_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=3,b=4]", new ToStringBuilder(base).append("a", i3).append("b", i4).toString());
    }

    @Test
    public void testObject_6_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=<Integer>]", new ToStringBuilder(base).append("a", i3, false).toString());
    }

    @Test
    public void testObject_7_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=<size=0>]", new ToStringBuilder(base).append("a", new ArrayList<>(), false).toString());
    }

    @Test
    public void testObject_8_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=[]]", new ToStringBuilder(base).append("a", new ArrayList<>(), true).toString());
    }

    @Test
    public void testObject_9_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=<size=0>]", new ToStringBuilder(base).append("a", new HashMap<>(), false).toString());
    }

    @Test
    public void testObject_10_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a={}]", new ToStringBuilder(base).append("a", new HashMap<>(), true).toString());
    }

    @Test
    public void testObject_11_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
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
        assertEquals(baseStr + "[a=<size=0>]", new ToStringBuilder(base).append("a", (Object) new String[0], false).toString());
    }

    @Test
    public void testObject_12_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
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
        assertEquals(baseStr + "[a={}]", new ToStringBuilder(base).append("a", (Object) new String[0], true).toString());
    }

    @Test
    public void testObjectBuild_1_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append((Object) null).build());
    }

    @Test
    public void testObjectBuild_2_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        assertEquals(baseStr + "[3]", new ToStringBuilder(base).append(i3).build());
    }

    @Test
    public void testObjectBuild_3_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=<null>]", new ToStringBuilder(base).append("a", (Object) null).build());
    }

    @Test
    public void testObjectBuild_4_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=3]", new ToStringBuilder(base).append("a", i3).build());
    }

    @Test
    public void testObjectBuild_5_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=3,b=4]", new ToStringBuilder(base).append("a", i3).append("b", i4).build());
    }

    @Test
    public void testObjectBuild_6_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=<Integer>]", new ToStringBuilder(base).append("a", i3, false).build());
    }

    @Test
    public void testObjectBuild_9_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=<size=0>]", new ToStringBuilder(base).append("a", new HashMap<>(), false).build());
    }

    @Test
    public void testObjectBuild_10_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a={}]", new ToStringBuilder(base).append("a", new HashMap<>(), true).build());
    }

    @Test
    public void testObjectBuild_11_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
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
        assertEquals(baseStr + "[a=<size=0>]", new ToStringBuilder(base).append("a", (Object) new String[0], false).build());
    }

    @Test
    public void testObjectBuild_12_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
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
        assertEquals(baseStr + "[a={}]", new ToStringBuilder(base).append("a", (Object) new String[0], true).build());
    }

    @Test
    public void testLong_1_oe() {
        assertEquals(baseStr + "[3]", new ToStringBuilder(base).append(3L).toString());
    }

    @Test
    public void testLong_2_oe() {
        // removed other assertion
        assertEquals(baseStr + "[a=3]", new ToStringBuilder(base).append("a", 3L).toString());
    }

    @Test
    public void testLong_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=3,b=4]", new ToStringBuilder(base).append("a", 3L).append("b", 4L).toString());
    }

    @Test
    public void testInt_1_oe() {
        assertEquals(baseStr + "[3]", new ToStringBuilder(base).append(3).toString());
    }

    @Test
    public void testInt_2_oe() {
        // removed other assertion
        assertEquals(baseStr + "[a=3]", new ToStringBuilder(base).append("a", 3).toString());
    }

    @Test
    public void testInt_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=3,b=4]", new ToStringBuilder(base).append("a", 3).append("b", 4).toString());
    }

    @Test
    public void testShort_1_oe() {
        assertEquals(baseStr + "[3]", new ToStringBuilder(base).append((short) 3).toString());
    }

    @Test
    public void testShort_2_oe() {
        // removed other assertion
        assertEquals(baseStr + "[a=3]", new ToStringBuilder(base).append("a", (short) 3).toString());
    }

    @Test
    public void testShort_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=3,b=4]", new ToStringBuilder(base).append("a", (short) 3).append("b", (short) 4).toString());
    }

    @Test
    public void testChar_1_oe() {
        assertEquals(baseStr + "[A]", new ToStringBuilder(base).append((char) 65).toString());
    }

    @Test
    public void testChar_2_oe() {
        // removed other assertion
        assertEquals(baseStr + "[a=A]", new ToStringBuilder(base).append("a", (char) 65).toString());
    }

    @Test
    public void testChar_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=A,b=B]", new ToStringBuilder(base).append("a", (char) 65).append("b", (char) 66).toString());
    }

    @Test
    public void testByte_1_oe() {
        assertEquals(baseStr + "[3]", new ToStringBuilder(base).append((byte) 3).toString());
    }

    @Test
    public void testByte_2_oe() {
        // removed other assertion
        assertEquals(baseStr + "[a=3]", new ToStringBuilder(base).append("a", (byte) 3).toString());
    }

    @Test
    public void testByte_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=3,b=4]", new ToStringBuilder(base).append("a", (byte) 3).append("b", (byte) 4).toString());
    }

    @Test
    public void testDouble_1_oe() {
        assertEquals(baseStr + "[3.2]", new ToStringBuilder(base).append(3.2).toString());
    }

    @Test
    public void testDouble_2_oe() {
        // removed other assertion
        assertEquals(baseStr + "[a=3.2]", new ToStringBuilder(base).append("a", 3.2).toString());
    }

    @Test
    public void testDouble_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=3.2,b=4.3]", new ToStringBuilder(base).append("a", 3.2).append("b", 4.3).toString());
    }

    @Test
    public void testFloat_1_oe() {
        assertEquals(baseStr + "[3.2]", new ToStringBuilder(base).append((float) 3.2).toString());
    }

    @Test
    public void testFloat_2_oe() {
        // removed other assertion
        assertEquals(baseStr + "[a=3.2]", new ToStringBuilder(base).append("a", (float) 3.2).toString());
    }

    @Test
    public void testFloat_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=3.2,b=4.3]", new ToStringBuilder(base).append("a", (float) 3.2).append("b", (float) 4.3).toString());
    }

    @Test
    public void testBoolean_1_oe() {
        assertEquals(baseStr + "[true]", new ToStringBuilder(base).append(true).toString());
    }

    @Test
    public void testBoolean_2_oe() {
        // removed other assertion
        assertEquals(baseStr + "[a=true]", new ToStringBuilder(base).append("a", true).toString());
    }

    @Test
    public void testBoolean_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=true,b=false]", new ToStringBuilder(base).append("a", true).append("b", false).toString());
    }

    @Test
    public void testObjectArray_1_oe() {
        Object[] array = new Object[] {null, base, new int[] {3, 6}};
        assertEquals(baseStr + "[{<null>,5,{3,6}}]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testObjectArray_2_oe() {
        Object[] array = new Object[] {null, base, new int[] {3, 6}};
        // removed other assertion
        assertEquals(baseStr + "[{<null>,5,{3,6}}]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testObjectArray_3_oe() {
        Object[] array = new Object[] {null, base, new int[] {3, 6}};
        // removed other assertion
        // removed other assertion
        array = null;
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testObjectArray_4_oe() {
        Object[] array = new Object[] {null, base, new int[] {3, 6}};
        // removed other assertion
        // removed other assertion
        array = null;
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testLongArray_1_oe() {
        long[] array = new long[] {1, 2, -3, 4};
        assertEquals(baseStr + "[{1,2,-3,4}]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testLongArray_2_oe() {
        long[] array = new long[] {1, 2, -3, 4};
        // removed other assertion
        assertEquals(baseStr + "[{1,2,-3,4}]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testLongArray_3_oe() {
        long[] array = new long[] {1, 2, -3, 4};
        // removed other assertion
        // removed other assertion
        array = null;
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testLongArray_4_oe() {
        long[] array = new long[] {1, 2, -3, 4};
        // removed other assertion
        // removed other assertion
        array = null;
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testIntArray_1_oe() {
        int[] array = new int[] {1, 2, -3, 4};
        assertEquals(baseStr + "[{1,2,-3,4}]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testIntArray_2_oe() {
        int[] array = new int[] {1, 2, -3, 4};
        // removed other assertion
        assertEquals(baseStr + "[{1,2,-3,4}]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testIntArray_3_oe() {
        int[] array = new int[] {1, 2, -3, 4};
        // removed other assertion
        // removed other assertion
        array = null;
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testIntArray_4_oe() {
        int[] array = new int[] {1, 2, -3, 4};
        // removed other assertion
        // removed other assertion
        array = null;
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testShortArray_1_oe() {
        short[] array = new short[] {1, 2, -3, 4};
        assertEquals(baseStr + "[{1,2,-3,4}]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testShortArray_2_oe() {
        short[] array = new short[] {1, 2, -3, 4};
        // removed other assertion
        assertEquals(baseStr + "[{1,2,-3,4}]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testShortArray_3_oe() {
        short[] array = new short[] {1, 2, -3, 4};
        // removed other assertion
        // removed other assertion
        array = null;
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testShortArray_4_oe() {
        short[] array = new short[] {1, 2, -3, 4};
        // removed other assertion
        // removed other assertion
        array = null;
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testByteArray_1_oe() {
        byte[] array = new byte[] {1, 2, -3, 4};
        assertEquals(baseStr + "[{1,2,-3,4}]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testByteArray_2_oe() {
        byte[] array = new byte[] {1, 2, -3, 4};
        // removed other assertion
        assertEquals(baseStr + "[{1,2,-3,4}]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testByteArray_3_oe() {
        byte[] array = new byte[] {1, 2, -3, 4};
        // removed other assertion
        // removed other assertion
        array = null;
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testByteArray_4_oe() {
        byte[] array = new byte[] {1, 2, -3, 4};
        // removed other assertion
        // removed other assertion
        array = null;
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testCharArray_1_oe() {
        char[] array = new char[] {'A', '2', '_', 'D'};
        assertEquals(baseStr + "[{A,2,_,D}]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testCharArray_2_oe() {
        char[] array = new char[] {'A', '2', '_', 'D'};
        // removed other assertion
        assertEquals(baseStr + "[{A,2,_,D}]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testCharArray_3_oe() {
        char[] array = new char[] {'A', '2', '_', 'D'};
        // removed other assertion
        // removed other assertion
        array = null;
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testCharArray_4_oe() {
        char[] array = new char[] {'A', '2', '_', 'D'};
        // removed other assertion
        // removed other assertion
        array = null;
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testDoubleArray_1_oe() {
        double[] array = new double[] {1.0, 2.9876, -3.00001, 4.3};
        assertEquals(baseStr + "[{1.0,2.9876,-3.00001,4.3}]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testDoubleArray_2_oe() {
        double[] array = new double[] {1.0, 2.9876, -3.00001, 4.3};
        // removed other assertion
        assertEquals(baseStr + "[{1.0,2.9876,-3.00001,4.3}]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testDoubleArray_3_oe() {
        double[] array = new double[] {1.0, 2.9876, -3.00001, 4.3};
        // removed other assertion
        // removed other assertion
        array = null;
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testDoubleArray_4_oe() {
        double[] array = new double[] {1.0, 2.9876, -3.00001, 4.3};
        // removed other assertion
        // removed other assertion
        array = null;
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testFloatArray_1_oe() {
        float[] array = new float[] {1.0f, 2.9876f, -3.00001f, 4.3f};
        assertEquals(baseStr + "[{1.0,2.9876,-3.00001,4.3}]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testFloatArray_2_oe() {
        float[] array = new float[] {1.0f, 2.9876f, -3.00001f, 4.3f};
        // removed other assertion
        assertEquals(baseStr + "[{1.0,2.9876,-3.00001,4.3}]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testFloatArray_3_oe() {
        float[] array = new float[] {1.0f, 2.9876f, -3.00001f, 4.3f};
        // removed other assertion
        // removed other assertion
        array = null;
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testFloatArray_4_oe() {
        float[] array = new float[] {1.0f, 2.9876f, -3.00001f, 4.3f};
        // removed other assertion
        // removed other assertion
        array = null;
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testBooleanArray_1_oe() {
        boolean[] array = new boolean[] {true, false, false};
        assertEquals(baseStr + "[{true,false,false}]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testBooleanArray_2_oe() {
        boolean[] array = new boolean[] {true, false, false};
        // removed other assertion
        assertEquals(baseStr + "[{true,false,false}]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testBooleanArray_3_oe() {
        boolean[] array = new boolean[] {true, false, false};
        // removed other assertion
        // removed other assertion
        array = null;
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testBooleanArray_4_oe() {
        boolean[] array = new boolean[] {true, false, false};
        // removed other assertion
        // removed other assertion
        array = null;
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testLongArrayArray_1_oe() {
        long[][] array = new long[][] {{1, 2}, null, {5}};
        assertEquals(baseStr + "[{{1,2},<null>,{5}}]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testLongArrayArray_2_oe() {
        long[][] array = new long[][] {{1, 2}, null, {5}};
        // removed other assertion
        assertEquals(baseStr + "[{{1,2},<null>,{5}}]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testLongArrayArray_3_oe() {
        long[][] array = new long[][] {{1, 2}, null, {5}};
        // removed other assertion
        // removed other assertion
        array = null;
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testLongArrayArray_4_oe() {
        long[][] array = new long[][] {{1, 2}, null, {5}};
        // removed other assertion
        // removed other assertion
        array = null;
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testIntArrayArray_1_oe() {
        int[][] array = new int[][] {{1, 2}, null, {5}};
        assertEquals(baseStr + "[{{1,2},<null>,{5}}]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testIntArrayArray_2_oe() {
        int[][] array = new int[][] {{1, 2}, null, {5}};
        // removed other assertion
        assertEquals(baseStr + "[{{1,2},<null>,{5}}]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testIntArrayArray_3_oe() {
        int[][] array = new int[][] {{1, 2}, null, {5}};
        // removed other assertion
        // removed other assertion
        array = null;
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testIntArrayArray_4_oe() {
        int[][] array = new int[][] {{1, 2}, null, {5}};
        // removed other assertion
        // removed other assertion
        array = null;
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testShortArrayArray_1_oe() {
        short[][] array = new short[][] {{1, 2}, null, {5}};
        assertEquals(baseStr + "[{{1,2},<null>,{5}}]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testShortArrayArray_2_oe() {
        short[][] array = new short[][] {{1, 2}, null, {5}};
        // removed other assertion
        assertEquals(baseStr + "[{{1,2},<null>,{5}}]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testShortArrayArray_3_oe() {
        short[][] array = new short[][] {{1, 2}, null, {5}};
        // removed other assertion
        // removed other assertion
        array = null;
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testShortArrayArray_4_oe() {
        short[][] array = new short[][] {{1, 2}, null, {5}};
        // removed other assertion
        // removed other assertion
        array = null;
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testByteArrayArray_1_oe() {
        byte[][] array = new byte[][] {{1, 2}, null, {5}};
        assertEquals(baseStr + "[{{1,2},<null>,{5}}]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testByteArrayArray_2_oe() {
        byte[][] array = new byte[][] {{1, 2}, null, {5}};
        // removed other assertion
        assertEquals(baseStr + "[{{1,2},<null>,{5}}]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testByteArrayArray_3_oe() {
        byte[][] array = new byte[][] {{1, 2}, null, {5}};
        // removed other assertion
        // removed other assertion
        array = null;
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testByteArrayArray_4_oe() {
        byte[][] array = new byte[][] {{1, 2}, null, {5}};
        // removed other assertion
        // removed other assertion
        array = null;
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testCharArrayArray_1_oe() {
        char[][] array = new char[][] {{'A', 'B'}, null, {'p'}};
        assertEquals(baseStr + "[{{A,B},<null>,{p}}]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testCharArrayArray_2_oe() {
        char[][] array = new char[][] {{'A', 'B'}, null, {'p'}};
        // removed other assertion
        assertEquals(baseStr + "[{{A,B},<null>,{p}}]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testCharArrayArray_3_oe() {
        char[][] array = new char[][] {{'A', 'B'}, null, {'p'}};
        // removed other assertion
        // removed other assertion
        array = null;
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testCharArrayArray_4_oe() {
        char[][] array = new char[][] {{'A', 'B'}, null, {'p'}};
        // removed other assertion
        // removed other assertion
        array = null;
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testDoubleArrayArray_1_oe() {
        double[][] array = new double[][] {{1.0, 2.29686}, null, {Double.NaN}};
        assertEquals(baseStr + "[{{1.0,2.29686},<null>,{NaN}}]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testDoubleArrayArray_2_oe() {
        double[][] array = new double[][] {{1.0, 2.29686}, null, {Double.NaN}};
        // removed other assertion
        assertEquals(baseStr + "[{{1.0,2.29686},<null>,{NaN}}]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testDoubleArrayArray_3_oe() {
        double[][] array = new double[][] {{1.0, 2.29686}, null, {Double.NaN}};
        // removed other assertion
        // removed other assertion
        array = null;
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testDoubleArrayArray_4_oe() {
        double[][] array = new double[][] {{1.0, 2.29686}, null, {Double.NaN}};
        // removed other assertion
        // removed other assertion
        array = null;
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testFloatArrayArray_1_oe() {
        float[][] array = new float[][] {{1.0f, 2.29686f}, null, {Float.NaN}};
        assertEquals(baseStr + "[{{1.0,2.29686},<null>,{NaN}}]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testFloatArrayArray_2_oe() {
        float[][] array = new float[][] {{1.0f, 2.29686f}, null, {Float.NaN}};
        // removed other assertion
        assertEquals(baseStr + "[{{1.0,2.29686},<null>,{NaN}}]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testFloatArrayArray_3_oe() {
        float[][] array = new float[][] {{1.0f, 2.29686f}, null, {Float.NaN}};
        // removed other assertion
        // removed other assertion
        array = null;
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testFloatArrayArray_4_oe() {
        float[][] array = new float[][] {{1.0f, 2.29686f}, null, {Float.NaN}};
        // removed other assertion
        // removed other assertion
        array = null;
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testBooleanArrayArray_1_oe() {
        boolean[][] array = new boolean[][] {{true, false}, null, {false}};
        assertEquals(baseStr + "[{{true,false},<null>,{false}}]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testBooleanArrayArray_2_oe() {
        boolean[][] array = new boolean[][] {{true, false}, null, {false}};
        // removed other assertion
        assertEquals(baseStr + "[{{true,false},<null>,{false}}]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testBooleanArrayArray_3_oe() {
        boolean[][] array = new boolean[][] {{true, false}, null, {false}};
        // removed other assertion
        // removed other assertion
        array = null;
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testBooleanArrayArray_4_oe() {
        boolean[][] array = new boolean[][] {{true, false}, null, {false}};
        // removed other assertion
        // removed other assertion
        array = null;
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testObjectCycle_1_oe() {
        final ObjectCycle a = new ObjectCycle();
        final ObjectCycle b = new ObjectCycle();
        a.obj = b;
        b.obj = a;

        final String expected = toBaseString(a) + "[" + toBaseString(b) + "[" + toBaseString(a) + "]]";
        assertEquals(expected, a.toString());
    }

    @Test
    public void testSimpleReflectionStatics_1_oe() {
        final SimpleReflectionStaticFieldsFixture instance1 = new SimpleReflectionStaticFieldsFixture();
        assertEquals(this.toBaseString(instance1)+ "[staticInt=12345,staticString=staticString]",ReflectionToStringBuilder.toString(instance1,null,false,true,SimpleReflectionStaticFieldsFixture.class));
    }

    @Test
    public void testSimpleReflectionStatics_2_oe() {
        final SimpleReflectionStaticFieldsFixture instance1 = new SimpleReflectionStaticFieldsFixture();
        // removed other assertion
        assertEquals(this.toBaseString(instance1)+ "[staticInt=12345,staticString=staticString]",ReflectionToStringBuilder.toString(instance1,null,true,true,SimpleReflectionStaticFieldsFixture.class));
    }

    @Test
    public void testSimpleReflectionStatics_3_oe() {
        final SimpleReflectionStaticFieldsFixture instance1 = new SimpleReflectionStaticFieldsFixture();
        // removed other assertion
        // removed other assertion
        assertEquals(this.toBaseString(instance1)+ "[staticInt=12345,staticString=staticString]",this.toStringWithStatics(instance1,null,SimpleReflectionStaticFieldsFixture.class));
    }

    @Test
    public void testSimpleReflectionStatics_4_oe() {
        final SimpleReflectionStaticFieldsFixture instance1 = new SimpleReflectionStaticFieldsFixture();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(this.toBaseString(instance1)+ "[staticInt=12345,staticString=staticString]",this.toStringWithStatics(instance1,null,SimpleReflectionStaticFieldsFixture.class));
    }

    @Test
    public void testReflectionStatics_1_oe() {
        final ReflectionStaticFieldsFixture instance1 = new ReflectionStaticFieldsFixture();
        assertEquals(this.toBaseString(instance1)+ "[instanceInt=67890,instanceString=instanceString,staticInt=12345,staticString=staticString]",ReflectionToStringBuilder.toString(instance1,null,false,true,ReflectionStaticFieldsFixture.class));
    }

    @Test
    public void testReflectionStatics_2_oe() {
        final ReflectionStaticFieldsFixture instance1 = new ReflectionStaticFieldsFixture();
        // removed other assertion
        assertEquals(this.toBaseString(instance1)+ "[instanceInt=67890,instanceString=instanceString,staticInt=12345,staticString=staticString,staticTransientInt=54321,staticTransientString=staticTransientString,transientInt=98765,transientString=transientString]",ReflectionToStringBuilder.toString(instance1,null,true,true,ReflectionStaticFieldsFixture.class));
    }

    @Test
    public void testReflectionStatics_3_oe() {
        final ReflectionStaticFieldsFixture instance1 = new ReflectionStaticFieldsFixture();
        // removed other assertion
        // removed other assertion
        assertEquals(this.toBaseString(instance1)+ "[instanceInt=67890,instanceString=instanceString,staticInt=12345,staticString=staticString]",this.toStringWithStatics(instance1,null,ReflectionStaticFieldsFixture.class));
    }

    @Test
    public void testReflectionStatics_4_oe() {
        final ReflectionStaticFieldsFixture instance1 = new ReflectionStaticFieldsFixture();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(this.toBaseString(instance1)+ "[instanceInt=67890,instanceString=instanceString,staticInt=12345,staticString=staticString]",this.toStringWithStatics(instance1,null,ReflectionStaticFieldsFixture.class));
    }

    @Test
    public void testInheritedReflectionStatics_1_oe() {
        final InheritedReflectionStaticFieldsFixture instance1 = new InheritedReflectionStaticFieldsFixture();
        assertEquals(this.toBaseString(instance1)+ "[staticInt2=67890,staticString2=staticString2]",ReflectionToStringBuilder.toString(instance1,null,false,true,InheritedReflectionStaticFieldsFixture.class));
    }

    @Test
    public void testInheritedReflectionStatics_2_oe() {
        final InheritedReflectionStaticFieldsFixture instance1 = new InheritedReflectionStaticFieldsFixture();
        // removed other assertion
        assertEquals(this.toBaseString(instance1)+ "[staticInt2=67890,staticString2=staticString2,staticInt=12345,staticString=staticString]",ReflectionToStringBuilder.toString(instance1,null,false,true,SimpleReflectionStaticFieldsFixture.class));
    }

    @Test
    public void testInheritedReflectionStatics_3_oe() {
        final InheritedReflectionStaticFieldsFixture instance1 = new InheritedReflectionStaticFieldsFixture();
        // removed other assertion
        // removed other assertion
        assertEquals(this.toBaseString(instance1)+ "[staticInt2=67890,staticString2=staticString2,staticInt=12345,staticString=staticString]",this.toStringWithStatics(instance1,null,SimpleReflectionStaticFieldsFixture.class));
    }

    @Test
    public void testInheritedReflectionStatics_4_oe() {
        final InheritedReflectionStaticFieldsFixture instance1 = new InheritedReflectionStaticFieldsFixture();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(this.toBaseString(instance1)+ "[staticInt2=67890,staticString2=staticString2,staticInt=12345,staticString=staticString]",this.toStringWithStatics(instance1,null,SimpleReflectionStaticFieldsFixture.class));
    }

    @Test
    public void testReflectionNull_1_oe() throws Exception {
        try {
    ReflectionToStringBuilder.toString(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReflectionDoubleArrayArray_1_oe() {
        double[][] array = new double[][] { { 1.0, 2.29686 }, null, { Double.NaN } };
        final String baseString = this.toBaseString(array);
        assertEquals(baseString + "[{{1.0,2.29686},<null>,{NaN}}]", ToStringBuilder.reflectionToString(array));
    }

    @Test
    public void testObjectBuild_7_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=<size=0>]", new ToStringBuilder(base).append("a", new ArrayList<>(), false).build());
    }

    @Test
    public void testObjectBuild_8_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=[]]", new ToStringBuilder(base).append("a", new ArrayList<>(), true).build());
    }

    @Test
    public void testAppendToStringUsingMultiLineStyle_1_oe() {
        final MultiLineTestObject obj = new MultiLineTestObject();
        final ToStringBuilder testBuilder = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                                          .appendToString(obj.toString());
        assertEquals(-1, testBuilder.toString().indexOf("testInt=31337"));
    }

}
