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

    @Test
    public void testReflectionDoubleArrayArray() {
        double[][] array = new double[][] { { 1.0, 2.29686 }, null, { Double.NaN } };
        final String baseString = this.toBaseString(array);
        assertEquals(baseString + "[{{1.0,2.29686},<null>,{NaN}}]", ToStringBuilder.reflectionToString(array));
        array = null;
        assertReflectionArray("<null>", array);
    }

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
    public void testAppendToStringUsingMultiLineStyle() {
        final MultiLineTestObject obj = new MultiLineTestObject();
        final ToStringBuilder testBuilder = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                                          .appendToString(obj.toString());
        assertEquals(-1, testBuilder.toString().indexOf("testInt=31337"));
    }

    @Test
    public void testSetDefaultEx_1_oe() throws Exception {
        try {
    ToStringBuilder.setDefaultStyle(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReflectionNull_1_oe() throws Exception {
        try {
    ReflectionToStringBuilder.toString(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

}
