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
package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ClassUtils.Interfaces;
import org.apache.commons.lang3.reflect.testbed.GenericConsumer;
import org.apache.commons.lang3.reflect.testbed.GenericParent;
import org.apache.commons.lang3.reflect.testbed.StringParameterizedChild;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.ClassUtils}.
 */
@SuppressWarnings("boxing") // JUnit4 does not support primitive equality testing apart from long
public class ClassUtilsTest_OE25Dev  {

    private static class CX implements IB, IA, IE {
        // empty
    }

    private static class CY extends CX implements IB, IC {
        // empty
    }

    private interface IA {
        // empty
    }

    private interface IB {
        // empty
    }

    private interface IC extends ID, IE {
        // empty
    }

    private interface ID {
        // empty
    }

    private interface IE extends IF {
        // empty
    }

    private interface IF {
        // empty
    }

    private static class Inner {
        private class DeeplyNested {
            // empty
        }
    }

    private void assertGetClassReturnsClass( final Class<?> c ) throws Exception {
        assertEquals( c, ClassUtils.getClass( c.getName() ) );
    }

    private void assertGetClassThrowsClassNotFound( final String className ) {
        assertGetClassThrowsException( className, ClassNotFoundException.class );
    }

    private void assertGetClassThrowsException(final String className, final Class<? extends Exception> exceptionType) {
        assertThrows(exceptionType,
                () -> ClassUtils.getClass(className),
                "ClassUtils.getClass() should fail with an exception of type " + exceptionType.getName() + " when given class name \"" + className + "\"." );
    }

    private void assertGetClassThrowsNullPointerException( final String className ) {
        assertGetClassThrowsException( className, NullPointerException.class );
    }

    // -------------------------------------------------------------------------

    // -------------------------------------------------------------------------

    /**
     * Test that in case the required length is larger than the name and thus there is no need for any shortening
     * then the returned string object is the same as the one passed as argument. Note, however, that this is
     * tested as an internal implementation detail, but it is not a guaranteed feature of the implementation.
     */

    // -------------------------------------------------------------------------

    // -------------------------------------------------------------------------

    // -------------------------------------------------------------------------

    // -------------------------------------------------------------------------

    // -------------------------------------------------------------------------

    //-----------------------------------------------------------------------

    @Test
    public void testGetInnerClass() throws ClassNotFoundException {
        assertEquals( Inner.DeeplyNested.class, ClassUtils.getClass( "org.apache.commons.lang3.ClassUtilsTest_OE25Dev.Inner.DeeplyNested" ) );
        assertEquals( Inner.DeeplyNested.class, ClassUtils.getClass( "org.apache.commons.lang3.ClassUtilsTest_OE25Dev.Inner$DeeplyNested" ) );
        assertEquals( Inner.DeeplyNested.class, ClassUtils.getClass( "org.apache.commons.lang3.ClassUtilsTest_OE25Dev$Inner$DeeplyNested" ) );
        assertEquals( Inner.DeeplyNested.class, ClassUtils.getClass( "org.apache.commons.lang3.ClassUtilsTest_OE25Dev$Inner.DeeplyNested" ) );
    }

    // Show the Java bug: http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4071957
    // We may have to delete this if a JDK fixes the bug.

    @Test
    public void test_convertClassesToClassNames_List_6_oe() throws Exception {
        final List<Class<?>> list = new ArrayList<>();
        List<String> result = ClassUtils.convertClassesToClassNames(list);
        // removed other assertion

        list.add(String.class);
        list.add(null);
        list.add(Object.class);
        result = ClassUtils.convertClassesToClassNames(list);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        @SuppressWarnings("unchecked") // test what happens when non-generic code adds wrong type of element
        final List<Object> olist = (List<Object>) (List<?>) list;
        olist.add(new Object());
        try {
    ClassUtils.convertClassesToClassNames(list);
    org.junit.jupiter.api.Assertions.fail("ClassCastException: Should not have been able to convert list");
} catch (ClassCastException e) {
}
    }

    @Test
    public void test_convertClassNamesToClasses_List_6_oe() throws Exception {
        final List<String> list = new ArrayList<>();
        List<Class<?>> result = ClassUtils.convertClassNamesToClasses(list);
        // removed other assertion

        list.add("java.lang.String");
        list.add("java.lang.xxx");
        list.add("java.lang.Object");
        result = ClassUtils.convertClassNamesToClasses(list);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        @SuppressWarnings("unchecked") // test what happens when non-generic code adds wrong type of element
        final List<Object> olist = (List<Object>) (List<?>) list;
        olist.add(new Object());
        try {
    ClassUtils.convertClassNamesToClasses(list);
    org.junit.jupiter.api.Assertions.fail("ClassCastException: Should not have been able to convert list");
} catch (ClassCastException e) {
}
    }

    @Test
    @DisplayName("When the desired length is negative then exception is thrown")
    public void test_getAbbreviatedName_Class_NegativeLen_1_oe() throws Exception {
        try {
    ClassUtils.getAbbreviatedName(String.class, -10);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    @DisplayName("When the desired length is zero then exception is thrown")
    public void test_getAbbreviatedName_Class_ZeroLen_1_oe() throws Exception {
        try {
    ClassUtils.getAbbreviatedName(String.class, 0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testShowJavaBug_1_oe() throws Exception {
        // Tests with Collections$UnmodifiableSet
        final Set<?> set = Collections.unmodifiableSet(new HashSet<>());
        final Method isEmptyMethod = set.getClass().getMethod("isEmpty");
        try {
    isEmptyMethod.invoke(set);
    org.junit.jupiter.api.Assertions.fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

}
