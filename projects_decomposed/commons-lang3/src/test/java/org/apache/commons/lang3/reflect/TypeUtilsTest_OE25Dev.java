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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Insets;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeSet;

import org.apache.commons.lang3.reflect.testbed.Foo;
import org.apache.commons.lang3.reflect.testbed.GenericParent;
import org.apache.commons.lang3.reflect.testbed.GenericTypeHolder;
import org.apache.commons.lang3.reflect.testbed.StringParameterizedChild;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


/**
 * Test TypeUtils
 */
@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
//raw types, where used, are used purposely
public class TypeUtilsTest_OE25Dev<B> {

    public interface And<K, V> extends This<Number, Number> {
    }

    public static class ClassWithSuperClassWithGenericType extends ArrayList<Object> {
        private static final long serialVersionUID = 1L;

        public static <U> Iterable<U> methodWithGenericReturnType() {
            return null;
        }
    }

    public class Other<T> implements This<String, T> {
    }

    public class Tester implements This<String, B> {
    }

    public class That<K, V> implements This<K, V> {
    }

    public class The<K, V> extends That<Number, Number> implements And<String, String> {
    }

    public class Thing<Q> extends Other<B> {
    }

    public interface This<K, V> {
    }

    public static Comparable<String> stringComparable;

    public static Comparable<URI> uriComparable;

    public static Comparable<Integer> intComparable;

    public static Comparable<Long> longComparable;

    public static Comparable<?> wildcardComparable;

    public static URI uri;

    public static List<String>[] stringListArray;

    public static <G extends Comparable<G>> G stub() {
        return null;
    }

    public static <G extends Comparable<? super G>> G stub2() {
        return null;
    }

    public static <T extends Comparable<? extends T>> T stub3() {
        return null;
    }

    public This<String, String> dis;

    public That<String, String> dat;

    public The<String, String> da;

    public Other<String> uhder;

    public Thing ding;

    public TypeUtilsTest_OE25Dev<String>.Tester tester;

    public Tester tester2;

    public TypeUtilsTest_OE25Dev<String>.That<String, String> dat2;

    public TypeUtilsTest_OE25Dev<Number>.That<String, String> dat3;

    public Comparable<? extends Integer>[] intWildcardComparable;

    public Iterable<? extends Map<Integer, ? extends Collection<?>>> iterable;

    public void delegateBooleanAssertion(final Type[] types, final int i2, final int i1, final boolean expected) {
        final Type type1 = types[i1];
        final Type type2 = types[i2];
        final boolean isAssignable = TypeUtils.isAssignable(type2, type1);

        if (expected) {
            assertTrue(isAssignable,"[" + i1 + "," + i2 + "]: From " + String.valueOf(type2)+ " to " + String.valueOf(type1));
        } else {
            assertFalse(isAssignable,"[" + i1 + "," + i2 + "]: From " + String.valueOf(type2)+ " to " + String.valueOf(type1));
        }
    }

    public void dummyMethod(final List list0, final List<Object> list1, final List<?> list2,
            final List<? super Object> list3, final List<String> list4, final List<? extends String> list5,
            final List<? super String> list6, final List[] list7, final List<Object>[] list8, final List<?>[] list9,
            final List<? super Object>[] list10, final List<String>[] list11, final List<? extends String>[] list12,
            final List<? super String>[] list13) {
    }

    @Test
    public void testParameterizeWithOwner() throws Exception {
        final Type owner = TypeUtils.parameterize(TypeUtilsTest_OE25Dev.class, String.class);
        final ParameterizedType dat2Type = TypeUtils.parameterizeWithOwner(owner, That.class, String.class, String.class);
        assertTrue(TypeUtils.equals(getClass().getField("dat2").getGenericType(), dat2Type));
    }


}
