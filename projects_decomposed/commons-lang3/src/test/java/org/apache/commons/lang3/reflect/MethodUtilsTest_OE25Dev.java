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
package org.apache.commons.lang3.reflect;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ClassUtils.Interfaces;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableObject;
import org.apache.commons.lang3.reflect.testbed.Annotated;
import org.apache.commons.lang3.reflect.testbed.GenericConsumer;
import org.apache.commons.lang3.reflect.testbed.GenericParent;
import org.apache.commons.lang3.reflect.testbed.PublicChild;
import org.apache.commons.lang3.reflect.testbed.StringParameterizedChild;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests MethodUtils
 */
public class MethodUtilsTest_OE25Dev {

    private interface PrivateInterface {
    }

    static class TestBeanWithInterfaces implements PrivateInterface {
        public String foo() {
            return "foo()";
        }
    }

    public static class TestBean {

        public static String bar() {
            return "bar()";
        }

        public static String bar(final int i) {
            return "bar(int)";
        }

        public static String bar(final Integer i) {
            return "bar(Integer)";
        }

        public static String bar(final double d) {
            return "bar(double)";
        }

        public static String bar(final String s) {
            return "bar(String)";
        }

        public static String bar(final Object o) {
            return "bar(Object)";
        }

        public static String bar(final String... s) {
            return "bar(String...)";
        }

        public static String bar(final long... s) {
            return "bar(long...)";
        }

        public static String bar(final Integer i, final String... s) {
            return "bar(int, String...)";
        }

        public static void oneParameterStatic(final String s) {
            // empty
        }

        @SuppressWarnings("unused")
        private void privateStuff() {
        }

        @SuppressWarnings("unused")
        private String privateStringStuff() {
            return "privateStringStuff()";
        }

        @SuppressWarnings("unused")
        private String privateStringStuff(final int i) {
            return "privateStringStuff(int)";
        }

        @SuppressWarnings("unused")
        private String privateStringStuff(final Integer i) {
            return "privateStringStuff(Integer)";
        }

        @SuppressWarnings("unused")
        private String privateStringStuff(final double d) {
            return "privateStringStuff(double)";
        }

        @SuppressWarnings("unused")
        private String privateStringStuff(final String s) {
            return "privateStringStuff(String)";
        }

        @SuppressWarnings("unused")
        private String privateStringStuff(final Object s) {
            return "privateStringStuff(Object)";
        }

        public String foo() {
            return "foo()";
        }

        public String foo(final int i) {
            return "foo(int)";
        }

        public String foo(final Integer i) {
            return "foo(Integer)";
        }

        public String foo(final double d) {
            return "foo(double)";
        }

        public String foo(final long l) {
            return "foo(long)";
        }

        public String foo(final String s) {
            return "foo(String)";
        }

        public String foo(final Object o) {
            return "foo(Object)";
        }

        public String foo(final String... s) {
            return "foo(String...)";
        }

        public String foo(final long... l) {
            return "foo(long...)";
        }

        public String foo(final Integer i, final String... s) {
            return "foo(int, String...)";
        }

        public void oneParameter(final String s) {
            // empty
        }

        public String foo(final Object... s) {
            return "foo(Object...)";
        }

        public int[] unboxing(final int... values) {
            return values;
        }

        // This method is overloaded for the wrapper class for every primitive type, plus the common supertypes
        // Number and Object. This is an acid test since it easily leads to ambiguous methods.
        public static String varOverload(final Byte... args) {
            return "Byte...";
        }

        public static String varOverload(final Character... args) {
            return "Character...";
        }

        public static String varOverload(final Short... args) {
            return "Short...";
        }

        public static String varOverload(final Boolean... args) {
            return "Boolean...";
        }

        public static String varOverload(final Float... args) {
            return "Float...";
        }

        public static String varOverload(final Double... args) {
            return "Double...";
        }

        public static String varOverload(final Integer... args) {
            return "Integer...";
        }

        public static String varOverload(final Long... args) {
            return "Long...";
        }

        public static String varOverload(final Number... args) {
            return "Number...";
        }

        public static String varOverload(final Object... args) {
            return "Object...";
        }

        public static String varOverload(final String... args) {
            return "String...";
        }

        // This method is overloaded for the wrapper class for every numeric primitive type, plus the common
        // supertype Number
        public static String numOverload(final Byte... args) {
            return "Byte...";
        }

        public static String numOverload(final Short... args) {
            return "Short...";
        }

        public static String numOverload(final Float... args) {
            return "Float...";
        }

        public static String numOverload(final Double... args) {
            return "Double...";
        }

        public static String numOverload(final Integer... args) {
            return "Integer...";
        }

        public static String numOverload(final Long... args) {
            return "Long...";
        }

        public static String numOverload(final Number... args) {
            return "Number...";
        }

        // These varOverloadEcho and varOverloadEchoStatic methods are designed to verify that
        // not only is the correct overloaded variant invoked, but that the varags arguments
        // are also delivered correctly to the method.
        public ImmutablePair<String, Object[]> varOverloadEcho(final String... args) {
            return new ImmutablePair<>("String...", args);
        }

        public ImmutablePair<String, Object[]> varOverloadEcho(final Number... args) {
            return new ImmutablePair<>("Number...", args);
        }

        public static ImmutablePair<String, Object[]> varOverloadEchoStatic(final String... args) {
            return new ImmutablePair<>("String...", args);
        }

        public static ImmutablePair<String, Object[]> varOverloadEchoStatic(final Number... args) {
            return new ImmutablePair<>("Number...", args);
        }

        static void verify(final ImmutablePair<String, Object[]> a, final ImmutablePair<String, Object[]> b) {
            assertEquals(a.getLeft(), b.getLeft());
            assertArrayEquals(a.getRight(), b.getRight());
        }

        static void verify(final ImmutablePair<String, Object[]> a, final Object _b) {
            @SuppressWarnings("unchecked") final ImmutablePair<String, Object[]> b = (ImmutablePair<String, Object[]>) _b;
            verify(a, b);
        }

    }

    private static class TestMutable implements Mutable<Object> {
        @Override
        public Object getValue() {
            return null;
        }

        @Override
        public void setValue(final Object value) {
        }
    }

    private TestBean testBean;
    private final Map<Class<?>, Class<?>[]> classCache = new HashMap<>();

    @BeforeEach
    public void setUp() {
        testBean = new TestBean();
        classCache.clear();
    }

    @Test
    public void testGetMatchingAccessibleMethod() {
        expectMatchingAccessibleMethodParameterTypes(TestBean.class, "foo",
                ArrayUtils.EMPTY_CLASS_ARRAY, ArrayUtils.EMPTY_CLASS_ARRAY);
        expectMatchingAccessibleMethodParameterTypes(TestBean.class, "foo",
                null, ArrayUtils.EMPTY_CLASS_ARRAY);
        expectMatchingAccessibleMethodParameterTypes(TestBean.class, "foo",
                singletonArray(String.class), singletonArray(String.class));
        expectMatchingAccessibleMethodParameterTypes(TestBean.class, "foo",
                singletonArray(Object.class), singletonArray(Object.class));
        expectMatchingAccessibleMethodParameterTypes(TestBean.class, "foo",
                singletonArray(Boolean.class), singletonArray(Object.class));
        expectMatchingAccessibleMethodParameterTypes(TestBean.class, "foo",
                singletonArray(Byte.class), singletonArray(Integer.TYPE));
        expectMatchingAccessibleMethodParameterTypes(TestBean.class, "foo",
                singletonArray(Byte.TYPE), singletonArray(Integer.TYPE));
        expectMatchingAccessibleMethodParameterTypes(TestBean.class, "foo",
                singletonArray(Short.class), singletonArray(Integer.TYPE));
        expectMatchingAccessibleMethodParameterTypes(TestBean.class, "foo",
                singletonArray(Short.TYPE), singletonArray(Integer.TYPE));
        expectMatchingAccessibleMethodParameterTypes(TestBean.class, "foo",
                singletonArray(Character.class), singletonArray(Integer.TYPE));
        expectMatchingAccessibleMethodParameterTypes(TestBean.class, "foo",
                singletonArray(Character.TYPE), singletonArray(Integer.TYPE));
        expectMatchingAccessibleMethodParameterTypes(TestBean.class, "foo",
                singletonArray(Integer.class), singletonArray(Integer.class));
        expectMatchingAccessibleMethodParameterTypes(TestBean.class, "foo",
                singletonArray(Integer.TYPE), singletonArray(Integer.TYPE));
        expectMatchingAccessibleMethodParameterTypes(TestBean.class, "foo",
                singletonArray(Long.class), singletonArray(Long.TYPE));
        expectMatchingAccessibleMethodParameterTypes(TestBean.class, "foo",
                singletonArray(Long.TYPE), singletonArray(Long.TYPE));
        expectMatchingAccessibleMethodParameterTypes(TestBean.class, "foo",
                singletonArray(Float.class), singletonArray(Double.TYPE));
        expectMatchingAccessibleMethodParameterTypes(TestBean.class, "foo",
                singletonArray(Float.TYPE), singletonArray(Double.TYPE));
        expectMatchingAccessibleMethodParameterTypes(TestBean.class, "foo",
                singletonArray(Double.class), singletonArray(Double.TYPE));
        expectMatchingAccessibleMethodParameterTypes(TestBean.class, "foo",
                singletonArray(Double.TYPE), singletonArray(Double.TYPE));
        expectMatchingAccessibleMethodParameterTypes(TestBean.class, "foo",
                singletonArray(Double.TYPE), singletonArray(Double.TYPE));
        expectMatchingAccessibleMethodParameterTypes(TestBean.class, "foo",
                new Class[]{String.class, String.class}, new Class[]{String[].class});
        expectMatchingAccessibleMethodParameterTypes(TestBean.class, "foo",
                new Class[]{Integer.TYPE, String.class, String.class}, new Class[]{Integer.class, String[].class});
        expectMatchingAccessibleMethodParameterTypes(InheritanceBean.class, "testOne",
                singletonArray(ParentObject.class), singletonArray(ParentObject.class));
        expectMatchingAccessibleMethodParameterTypes(InheritanceBean.class, "testOne",
                singletonArray(ChildObject.class), singletonArray(ParentObject.class));
        expectMatchingAccessibleMethodParameterTypes(InheritanceBean.class, "testTwo",
                singletonArray(ParentObject.class), singletonArray(GrandParentObject.class));
        expectMatchingAccessibleMethodParameterTypes(InheritanceBean.class, "testTwo",
                singletonArray(ChildObject.class), singletonArray(ChildInterface.class));
    }

    @Test
    public void testNullArgument() {
        expectMatchingAccessibleMethodParameterTypes(TestBean.class, "oneParameter",
                singletonArray(null), singletonArray(String.class));
    }

    private void expectMatchingAccessibleMethodParameterTypes(final Class<?> cls,
                                                              final String methodName, final Class<?>[] requestTypes, final Class<?>[] actualTypes) {
        final Method m = MethodUtils.getMatchingAccessibleMethod(cls, methodName,
                requestTypes);
        assertNotNull(m,"could not find any matches for " + methodName + "(" +(requestTypes == null ? null : toString(requestTypes))+ ")");
        assertArrayEquals(actualTypes, m.getParameterTypes(), toString(m.getParameterTypes()) + " not equals " + toString(actualTypes));
    }

    private String toString(final Class<?>[] c) {
        return Arrays.asList(c).toString();
    }

    private Class<?>[] singletonArray(final Class<?> c) {
        Class<?>[] result = classCache.get(c);
        if (result == null) {
            result = new Class[]{c};
            classCache.put(c, result);
        }
        return result;
    }

    public static class InheritanceBean {
        public void testOne(final Object obj) {
        }

        public void testOne(final GrandParentObject obj) {
        }

        public void testOne(final ParentObject obj) {
        }

        public void testTwo(final Object obj) {
        }

        public void testTwo(final GrandParentObject obj) {
        }

        public void testTwo(final ChildInterface obj) {
        }
    }

    interface ChildInterface {
    }

    public static class GrandParentObject {
    }

    public static class ParentObject extends GrandParentObject {
    }

    public static class ChildObject extends ParentObject implements ChildInterface {
    }

    private static class MethodDescriptor {
        final Class<?> declaringClass;
        final String name;
        final Type[] parameterTypes;

        MethodDescriptor(final Class<?> declaringClass, final String name, final Type... parameterTypes) {
            this.declaringClass = declaringClass;
            this.name = name;
            this.parameterTypes = parameterTypes;
        }
    }

    private static final class GetMatchingMethodClass {
        public void testMethod() {
        }

        public void testMethod(final Long aLong) {
        }

        public void testMethod(final long aLong) {
        }

        public void testMethod2(final Long aLong) {
        }

        public void testMethod2(final Color aColor) {
        }

        public void testMethod2(final long aLong) {
        }

        public void testMethod3(final long aLong, final Long anotherLong) {
        }

        public void testMethod3(final Long aLong, final long anotherLong) {
        }

        public void testMethod3(final Long aLong, final Long anotherLong) {
        }

        public void testMethod4(final Long aLong, final Long anotherLong) {
        }

        public void testMethod4(final Color aColor1, final Color aColor2) {
        }
    }

    @Test
    public void testInvokeMethod_16_oe() throws Exception {
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
        // removed other assertion

        try {
    MethodUtils.invokeMethod(testBean, "foo", 1, 2);
    org.junit.jupiter.api.Assertions.fail("NoSuchMethodException");
} catch (NoSuchMethodException e) {
}
    }

    @Test
    public void testInvokeExactMethod_9_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    MethodUtils.invokeExactMethod(testBean, "foo", NumberUtils.BYTE_ONE);
    org.junit.jupiter.api.Assertions.fail("NoSuchMethodException");
} catch (NoSuchMethodException e) {
}
    }

    @Test
    public void testInvokeExactMethod_10_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        try {
    MethodUtils.invokeExactMethod(testBean, "foo", NumberUtils.LONG_ONE);
    org.junit.jupiter.api.Assertions.fail("NoSuchMethodException");
} catch (NoSuchMethodException e) {
}
    }

    @Test
    public void testInvokeExactMethod_11_oe() throws Exception {
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
        try {
    MethodUtils.invokeExactMethod(testBean, "foo", Boolean.TRUE);
    org.junit.jupiter.api.Assertions.fail("NoSuchMethodException");
} catch (NoSuchMethodException e) {
}
    }

    @Test
    public void testInvokeStaticMethod_13_oe() throws Exception {
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

        TestBean.verify(new ImmutablePair<>("String...", new String[]{"x", "y"}),
                MethodUtils.invokeStaticMethod(TestBean.class, "varOverloadEchoStatic", "x", "y"));
        TestBean.verify(new ImmutablePair<>("Number...", new Number[]{17, 23, 42}),
                MethodUtils.invokeStaticMethod(TestBean.class, "varOverloadEchoStatic", 17, 23, 42));
        TestBean.verify(new ImmutablePair<>("String...", new String[]{"x", "y"}),
                MethodUtils.invokeStaticMethod(TestBean.class, "varOverloadEchoStatic", "x", "y"));
        TestBean.verify(new ImmutablePair<>("Number...", new Number[]{17, 23, 42}),
                MethodUtils.invokeStaticMethod(TestBean.class, "varOverloadEchoStatic", 17, 23, 42));

        try {
    MethodUtils.invokeStaticMethod(TestBean.class, "does_not_exist");
    org.junit.jupiter.api.Assertions.fail("NoSuchMethodException");
} catch (NoSuchMethodException e) {
}
    }

    @Test
    public void testInvokeExactStaticMethod_8_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    MethodUtils.invokeExactStaticMethod(TestBean.class, "bar", NumberUtils.BYTE_ONE);
    org.junit.jupiter.api.Assertions.fail("NoSuchMethodException");
} catch (NoSuchMethodException e) {
}
    }

    @Test
    public void testInvokeExactStaticMethod_9_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        try {
    MethodUtils.invokeExactStaticMethod(TestBean.class, "bar", NumberUtils.LONG_ONE);
    org.junit.jupiter.api.Assertions.fail("NoSuchMethodException");
} catch (NoSuchMethodException e) {
}
    }

    @Test
    public void testInvokeExactStaticMethod_10_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        try {
    MethodUtils.invokeExactStaticMethod(TestBean.class, "bar", Boolean.TRUE);
    org.junit.jupiter.api.Assertions.fail("NoSuchMethodException");
} catch (NoSuchMethodException e) {
}
    }

    @Test
    public void testGetMethodsWithAnnotationIllegalArgumentException1_1_oe() throws Exception {
        try {
    MethodUtils.getMethodsWithAnnotation(FieldUtilsTest.class, null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetMethodsWithAnnotationIllegalArgumentException2_1_oe() throws Exception {
        try {
    MethodUtils.getMethodsWithAnnotation(null, Annotated.class);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetMethodsWithAnnotationIllegalArgumentException3_1_oe() throws Exception {
        try {
    MethodUtils.getMethodsWithAnnotation(null, null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetMethodsListWithAnnotationIllegalArgumentException1_1_oe() throws Exception {
        try {
    MethodUtils.getMethodsListWithAnnotation(FieldUtilsTest.class, null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetMethodsListWithAnnotationIllegalArgumentException2_1_oe() throws Exception {
        try {
    MethodUtils.getMethodsListWithAnnotation(null, Annotated.class);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetMethodsListWithAnnotationIllegalArgumentException3_1_oe() throws Exception {
        try {
    MethodUtils.getMethodsListWithAnnotation(null, null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetAnnotationIllegalArgumentException1_1_oe() throws Exception {
        try {
    MethodUtils.getAnnotation(FieldUtilsTest.class.getDeclaredMethods()[0], null, true, true);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetAnnotationIllegalArgumentException2_1_oe() throws Exception {
        try {
    MethodUtils.getAnnotation(null, Annotated.class, true, true);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetAnnotationIllegalArgumentException3_1_oe() throws Exception {
        try {
    MethodUtils.getAnnotation(null, null, true, true);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetMatchingMethod_5_oe() throws NoSuchMethodException {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    MethodUtils.getMatchingMethod(GetMatchingMethodClass.class, "testMethod2", (Class<?>) null);
    org.junit.jupiter.api.Assertions.fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

    @Test
    public void testGetMatchingMethod_10_oe() throws NoSuchMethodException {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    MethodUtils.getMatchingMethod(GetMatchingMethodClass.class, "testMethod4", null, null);
    org.junit.jupiter.api.Assertions.fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

}
