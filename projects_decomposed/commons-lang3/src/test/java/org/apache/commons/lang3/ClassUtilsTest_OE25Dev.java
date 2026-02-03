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
    public void testGetClassClassNotFound() throws Exception {
        assertGetClassThrowsClassNotFound( "bool" );
        assertGetClassThrowsClassNotFound( "bool[]" );
        assertGetClassThrowsClassNotFound( "integer[]" );
    }

    @Test
    public void testGetClassInvalidArguments() throws Exception {
        assertGetClassThrowsNullPointerException( null );
        assertGetClassThrowsClassNotFound( "[][][]" );
        assertGetClassThrowsClassNotFound( "[[]" );
        assertGetClassThrowsClassNotFound( "[" );
        assertGetClassThrowsClassNotFound( "java.lang.String][" );
        assertGetClassThrowsClassNotFound( ".hello.world" );
        assertGetClassThrowsClassNotFound( "hello..world" );
    }

    @Test
    public void testGetClassWithArrayClasses() throws Exception {
        assertGetClassReturnsClass( String[].class );
        assertGetClassReturnsClass( int[].class );
        assertGetClassReturnsClass( long[].class );
        assertGetClassReturnsClass( short[].class );
        assertGetClassReturnsClass( byte[].class );
        assertGetClassReturnsClass( char[].class );
        assertGetClassReturnsClass( float[].class );
        assertGetClassReturnsClass( double[].class );
        assertGetClassReturnsClass( boolean[].class );
    }

    @Test
    public void testGetClassWithArrayClasses2D() throws Exception {
        assertGetClassReturnsClass( String[][].class );
        assertGetClassReturnsClass( int[][].class );
        assertGetClassReturnsClass( long[][].class );
        assertGetClassReturnsClass( short[][].class );
        assertGetClassReturnsClass( byte[][].class );
        assertGetClassReturnsClass( char[][].class );
        assertGetClassReturnsClass( float[][].class );
        assertGetClassReturnsClass( double[][].class );
        assertGetClassReturnsClass( boolean[][].class );
    }

    // Show the Java bug: http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4071957
    // We may have to delete this if a JDK fixes the bug.

    @Test
    public void test_convertClassesToClassNames_List_1_oe() {
        final List<Class<?>> list = new ArrayList<>();
        List<String> result = ClassUtils.convertClassesToClassNames(list);
        assertEquals(0, result.size());
    }

    @Test
    public void test_convertClassesToClassNames_List_2_oe() {
        final List<Class<?>> list = new ArrayList<>();
        List<String> result = ClassUtils.convertClassesToClassNames(list);
        // removed other assertion

        list.add(String.class);
        list.add(null);
        list.add(Object.class);
        result = ClassUtils.convertClassesToClassNames(list);
        assertEquals(3, result.size());
    }

    @Test
    public void test_convertClassesToClassNames_List_3_oe() {
        final List<Class<?>> list = new ArrayList<>();
        List<String> result = ClassUtils.convertClassesToClassNames(list);
        // removed other assertion

        list.add(String.class);
        list.add(null);
        list.add(Object.class);
        result = ClassUtils.convertClassesToClassNames(list);
        // removed other assertion
        assertEquals("java.lang.String", result.get(0));
    }

    @Test
    public void test_convertClassesToClassNames_List_4_oe() {
        final List<Class<?>> list = new ArrayList<>();
        List<String> result = ClassUtils.convertClassesToClassNames(list);
        // removed other assertion

        list.add(String.class);
        list.add(null);
        list.add(Object.class);
        result = ClassUtils.convertClassesToClassNames(list);
        // removed other assertion
        // removed other assertion
        assertNull(result.get(1));
    }

    @Test
    public void test_convertClassesToClassNames_List_5_oe() {
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
        assertEquals("java.lang.Object", result.get(2));
    }

    @Test
    public void test_convertClassesToClassNames_List_6_oe() {
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
        assertThrows(ClassCastException.class, () -> ClassUtils.convertClassesToClassNames(list), "Should not have been able to convert list");
    }

    @Test
    public void test_convertClassesToClassNames_List_7_oe() {
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
        // removed other assertion
        assertNull(ClassUtils.convertClassesToClassNames(null));
    }

    @Test
    public void test_convertClassNamesToClasses_List_1_oe() {
        final List<String> list = new ArrayList<>();
        List<Class<?>> result = ClassUtils.convertClassNamesToClasses(list);
        assertEquals(0, result.size());
    }

    @Test
    public void test_convertClassNamesToClasses_List_2_oe() {
        final List<String> list = new ArrayList<>();
        List<Class<?>> result = ClassUtils.convertClassNamesToClasses(list);
        // removed other assertion

        list.add("java.lang.String");
        list.add("java.lang.xxx");
        list.add("java.lang.Object");
        result = ClassUtils.convertClassNamesToClasses(list);
        assertEquals(3, result.size());
    }

    @Test
    public void test_convertClassNamesToClasses_List_3_oe() {
        final List<String> list = new ArrayList<>();
        List<Class<?>> result = ClassUtils.convertClassNamesToClasses(list);
        // removed other assertion

        list.add("java.lang.String");
        list.add("java.lang.xxx");
        list.add("java.lang.Object");
        result = ClassUtils.convertClassNamesToClasses(list);
        // removed other assertion
        assertEquals(String.class, result.get(0));
    }

    @Test
    public void test_convertClassNamesToClasses_List_4_oe() {
        final List<String> list = new ArrayList<>();
        List<Class<?>> result = ClassUtils.convertClassNamesToClasses(list);
        // removed other assertion

        list.add("java.lang.String");
        list.add("java.lang.xxx");
        list.add("java.lang.Object");
        result = ClassUtils.convertClassNamesToClasses(list);
        // removed other assertion
        // removed other assertion
        assertNull(result.get(1));
    }

    @Test
    public void test_convertClassNamesToClasses_List_5_oe() {
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
        assertEquals(Object.class, result.get(2));
    }

    @Test
    public void test_convertClassNamesToClasses_List_6_oe() {
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
        assertThrows(ClassCastException.class, () -> ClassUtils.convertClassNamesToClasses(list), "Should not have been able to convert list");
    }

    @Test
    public void test_convertClassNamesToClasses_List_7_oe() {
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
        // removed other assertion
        assertNull(ClassUtils.convertClassNamesToClasses(null));
    }

    @Test
    public void test_getAbbreviatedName_Class_1_oe() {
        assertEquals("", ClassUtils.getAbbreviatedName((Class<?>) null, 1));
    }

    @Test
    public void test_getAbbreviatedName_Class_2_oe() {
        // removed other assertion
        assertEquals("j.l.String", ClassUtils.getAbbreviatedName(String.class, 1));
    }

    @Test
    public void test_getAbbreviatedName_Class_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("j.l.String", ClassUtils.getAbbreviatedName(String.class, 5));
    }

    @Test
    public void test_getAbbreviatedName_Class_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("o.a.c.l.ClassUtils", ClassUtils.getAbbreviatedName(ClassUtils.class, 18));
    }

    @Test
    public void test_getAbbreviatedName_Class_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("j.lang.String", ClassUtils.getAbbreviatedName(String.class, 13));
    }

    @Test
    public void test_getAbbreviatedName_Class_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("j.lang.String", ClassUtils.getAbbreviatedName(String.class, 15));
    }

    @Test
    public void test_getAbbreviatedName_Class_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("java.lang.String", ClassUtils.getAbbreviatedName(String.class, 20));
    }

    @Test
    @DisplayName("When the length hint is longer than the actual length then the same String object is returned")
    public void test_getAbbreviatedName_TooLongHint_1_oe(){
        final String className = "java.lang.String";
        Assertions.assertSame(className, ClassUtils.getAbbreviatedName(className, className.length()+1));
    }

    @Test
    @DisplayName("When the length hint is longer than the actual length then the same String object is returned")
    public void test_getAbbreviatedName_TooLongHint_2_oe(){
        final String className = "java.lang.String";
        // removed other assertion
        Assertions.assertSame(className, ClassUtils.getAbbreviatedName(className, className.length()));
    }

    @Test
    @DisplayName("When the desired length is negative then exception is thrown")
    public void test_getAbbreviatedName_Class_NegativeLen_1_oe() {
        assertThrows(IllegalArgumentException.class, () -> ClassUtils.getAbbreviatedName(String.class, -10));
    }

    @Test
    @DisplayName("When the desired length is zero then exception is thrown")
    public void test_getAbbreviatedName_Class_ZeroLen_1_oe() {
        assertThrows(IllegalArgumentException.class, () -> ClassUtils.getAbbreviatedName(String.class, 0));
    }

    @Test
    public void test_getAbbreviatedName_String_1_oe() {
        assertEquals("", ClassUtils.getAbbreviatedName((String) null, 1));
    }

    @Test
    public void test_getAbbreviatedName_String_2_oe() {
        // removed other assertion
        assertEquals("", ClassUtils.getAbbreviatedName("", 1));
    }

    @Test
    public void test_getAbbreviatedName_String_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("WithoutPackage", ClassUtils.getAbbreviatedName("WithoutPackage", 1));
    }

    @Test
    public void test_getAbbreviatedName_String_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("j.l.String", ClassUtils.getAbbreviatedName("java.lang.String", 1));
    }

    @Test
    public void test_getAbbreviatedName_String_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("o.a.c.l.ClassUtils", ClassUtils.getAbbreviatedName("org.apache.commons.lang3.ClassUtils", 18));
    }

    @Test
    public void test_getAbbreviatedName_String_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("org.apache.commons.lang3.ClassUtils",ClassUtils.getAbbreviatedName("org.apache.commons.lang3.ClassUtils","org.apache.commons.lang3.ClassUtils".length()));
    }

    @Test
    public void test_getAbbreviatedName_String_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("o.a.c.l.ClassUtils", ClassUtils.getAbbreviatedName("o.a.c.l.ClassUtils", 18));
    }

    @Test
    public void test_getAbbreviatedName_String_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("o..c.l.ClassUtils", ClassUtils.getAbbreviatedName("o..c.l.ClassUtils", 18));
    }

    @Test
    public void test_getAbbreviatedName_String_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(".", ClassUtils.getAbbreviatedName(".", 18));
    }

    @Test
    public void test_getAbbreviatedName_String_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(".", ClassUtils.getAbbreviatedName(".", 1));
    }

    @Test
    public void test_getAbbreviatedName_String_11_oe() {
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
        assertEquals("..", ClassUtils.getAbbreviatedName("..", 1));
    }

    @Test
    public void test_getAbbreviatedName_String_12_oe() {
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
        assertEquals("...", ClassUtils.getAbbreviatedName("...", 2));
    }

    @Test
    public void test_getAbbreviatedName_String_13_oe() {
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
        assertEquals("...", ClassUtils.getAbbreviatedName("...", 3));
    }

    @Test
    public void test_getAbbreviatedName_String_14_oe() {
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
        assertEquals("java.lang.String", ClassUtils.getAbbreviatedName("java.lang.String", Integer.MAX_VALUE));
    }

    @Test
    public void test_getAbbreviatedName_String_15_oe() {
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
        assertEquals("j.lang.String", ClassUtils.getAbbreviatedName("java.lang.String", "j.lang.String".length()));
    }

    @Test
    public void test_getAbbreviatedName_String_16_oe() {
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
        assertEquals("j.l.String", ClassUtils.getAbbreviatedName("java.lang.String", "j.lang.String".length() - 1));
    }

    @Test
    public void test_getAbbreviatedName_String_17_oe() {
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
        // removed other assertion
        assertEquals("j.l.String", ClassUtils.getAbbreviatedName("java.lang.String", "j.l.String".length()));
    }

    @Test
    public void test_getAbbreviatedName_String_18_oe() {
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
        // removed other assertion
        // removed other assertion
        assertEquals("j.l.String", ClassUtils.getAbbreviatedName("java.lang.String", "j.l.String".length() - 1));
    }

    @Test
    public void test_getAllInterfaces_Class_1_oe() {
        final List<?> list = ClassUtils.getAllInterfaces(CY.class);
        assertEquals(6, list.size());
    }

    @Test
    public void test_getAllInterfaces_Class_2_oe() {
        final List<?> list = ClassUtils.getAllInterfaces(CY.class);
        // removed other assertion
        assertEquals(IB.class, list.get(0));
    }

    @Test
    public void test_getAllInterfaces_Class_3_oe() {
        final List<?> list = ClassUtils.getAllInterfaces(CY.class);
        // removed other assertion
        // removed other assertion
        assertEquals(IC.class, list.get(1));
    }

    @Test
    public void test_getAllInterfaces_Class_4_oe() {
        final List<?> list = ClassUtils.getAllInterfaces(CY.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ID.class, list.get(2));
    }

    @Test
    public void test_getAllInterfaces_Class_5_oe() {
        final List<?> list = ClassUtils.getAllInterfaces(CY.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(IE.class, list.get(3));
    }

    @Test
    public void test_getAllInterfaces_Class_6_oe() {
        final List<?> list = ClassUtils.getAllInterfaces(CY.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(IF.class, list.get(4));
    }

    @Test
    public void test_getAllInterfaces_Class_7_oe() {
        final List<?> list = ClassUtils.getAllInterfaces(CY.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(IA.class, list.get(5));
    }

    @Test
    public void test_getAllInterfaces_Class_8_oe() {
        final List<?> list = ClassUtils.getAllInterfaces(CY.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertNull(ClassUtils.getAllInterfaces(null));
    }

    @Test
    public void test_getAllSuperclasses_Class_1_oe() {
        final List<?> list = ClassUtils.getAllSuperclasses(CY.class);
        assertEquals(2, list.size());
    }

    @Test
    public void test_getAllSuperclasses_Class_2_oe() {
        final List<?> list = ClassUtils.getAllSuperclasses(CY.class);
        // removed other assertion
        assertEquals(CX.class, list.get(0));
    }

    @Test
    public void test_getAllSuperclasses_Class_3_oe() {
        final List<?> list = ClassUtils.getAllSuperclasses(CY.class);
        // removed other assertion
        // removed other assertion
        assertEquals(Object.class, list.get(1));
    }

    @Test
    public void test_getAllSuperclasses_Class_4_oe() {
        final List<?> list = ClassUtils.getAllSuperclasses(CY.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertNull(ClassUtils.getAllSuperclasses(null));
    }

    @Test
    public void test_getCanonicalName_Class_1_oe() {
        assertEquals("org.apache.commons.lang3.ClassUtils", ClassUtils.getCanonicalName(ClassUtils.class));
    }

    @Test
    public void test_getCanonicalName_Class_2_oe() {
        // removed other assertion
        assertEquals("java.util.Map.Entry", ClassUtils.getCanonicalName(Map.Entry.class));
    }

    @Test
    public void test_getCanonicalName_Class_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("", ClassUtils.getCanonicalName((Class<?>) null));
    }

    @Test
    public void test_getCanonicalName_Class_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("java.lang.String[]", ClassUtils.getCanonicalName(String[].class));
    }

    @Test
    public void test_getCanonicalName_Class_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("java.util.Map.Entry[]", ClassUtils.getCanonicalName(Map.Entry[].class));
    }

    @Test
    public void test_getCanonicalName_Class_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        assertEquals("boolean", ClassUtils.getCanonicalName(boolean.class));
    }

    @Test
    public void test_getCanonicalName_Class_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        assertEquals("byte", ClassUtils.getCanonicalName(byte.class));
    }

    @Test
    public void test_getCanonicalName_Class_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        assertEquals("char", ClassUtils.getCanonicalName(char.class));
    }

    @Test
    public void test_getCanonicalName_Class_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("short", ClassUtils.getCanonicalName(short.class));
    }

    @Test
    public void test_getCanonicalName_Class_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("int", ClassUtils.getCanonicalName(int.class));
    }

    @Test
    public void test_getCanonicalName_Class_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("long", ClassUtils.getCanonicalName(long.class));
    }

    @Test
    public void test_getCanonicalName_Class_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("float", ClassUtils.getCanonicalName(float.class));
    }

    @Test
    public void test_getCanonicalName_Class_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("double", ClassUtils.getCanonicalName(double.class));
    }

    @Test
    public void test_getCanonicalName_Class_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        assertEquals("boolean[]", ClassUtils.getCanonicalName(boolean[].class));
    }

    @Test
    public void test_getCanonicalName_Class_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        assertEquals("byte[]", ClassUtils.getCanonicalName(byte[].class));
    }

    @Test
    public void test_getCanonicalName_Class_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        assertEquals("char[]", ClassUtils.getCanonicalName(char[].class));
    }

    @Test
    public void test_getCanonicalName_Class_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("short[]", ClassUtils.getCanonicalName(short[].class));
    }

    @Test
    public void test_getCanonicalName_Class_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("int[]", ClassUtils.getCanonicalName(int[].class));
    }

    @Test
    public void test_getCanonicalName_Class_19_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("long[]", ClassUtils.getCanonicalName(long[].class));
    }

    @Test
    public void test_getCanonicalName_Class_20_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("float[]", ClassUtils.getCanonicalName(float[].class));
    }

    @Test
    public void test_getCanonicalName_Class_21_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("double[]", ClassUtils.getCanonicalName(double[].class));
    }

    @Test
    public void test_getCanonicalName_Class_22_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        assertEquals("java.lang.String[][]", ClassUtils.getCanonicalName(String[][].class));
    }

    @Test
    public void test_getCanonicalName_Class_23_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        // removed other assertion
        assertEquals("java.lang.String[][][]", ClassUtils.getCanonicalName(String[][][].class));
    }

    @Test
    public void test_getCanonicalName_Class_24_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        // removed other assertion
        // removed other assertion
        assertEquals("java.lang.String[][][][]", ClassUtils.getCanonicalName(String[][][][].class));
    }

    @Test
    public void test_getCanonicalName_Class_26_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Inner types
        class Named {
            // empty
        }
        // removed other assertion
        assertEquals(StringUtils.EMPTY, ClassUtils.getCanonicalName(Named.class));
    }

    @Test
    public void test_getCanonicalName_Class_27_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Inner types
        class Named {
            // empty
        }
        // removed other assertion
        // removed other assertion
        assertEquals("org.apache.commons.lang3.ClassUtilsTest.Inner", ClassUtils.getCanonicalName(Inner.class));
    }

    @Test
    public void test_getCanonicalName_Class_String_1_oe() {
        assertEquals("org.apache.commons.lang3.ClassUtils", ClassUtils.getCanonicalName(ClassUtils.class, "X"));
    }

    @Test
    public void test_getCanonicalName_Class_String_2_oe() {
        // removed other assertion
        assertEquals("java.util.Map.Entry", ClassUtils.getCanonicalName(Map.Entry.class, "X"));
    }

    @Test
    public void test_getCanonicalName_Class_String_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("X", ClassUtils.getCanonicalName((Class<?>) null, "X"));
    }

    @Test
    public void test_getCanonicalName_Class_String_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("java.lang.String[]", ClassUtils.getCanonicalName(String[].class, "X"));
    }

    @Test
    public void test_getCanonicalName_Class_String_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("java.util.Map.Entry[]", ClassUtils.getCanonicalName(Map.Entry[].class, "X"));
    }

    @Test
    public void test_getCanonicalName_Class_String_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        assertEquals("boolean", ClassUtils.getCanonicalName(boolean.class, "X"));
    }

    @Test
    public void test_getCanonicalName_Class_String_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        assertEquals("byte", ClassUtils.getCanonicalName(byte.class, "X"));
    }

    @Test
    public void test_getCanonicalName_Class_String_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        assertEquals("char", ClassUtils.getCanonicalName(char.class, "X"));
    }

    @Test
    public void test_getCanonicalName_Class_String_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("short", ClassUtils.getCanonicalName(short.class, "X"));
    }

    @Test
    public void test_getCanonicalName_Class_String_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("int", ClassUtils.getCanonicalName(int.class, "X"));
    }

    @Test
    public void test_getCanonicalName_Class_String_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("long", ClassUtils.getCanonicalName(long.class, "X"));
    }

    @Test
    public void test_getCanonicalName_Class_String_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("float", ClassUtils.getCanonicalName(float.class, "X"));
    }

    @Test
    public void test_getCanonicalName_Class_String_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("double", ClassUtils.getCanonicalName(double.class, "X"));
    }

    @Test
    public void test_getCanonicalName_Class_String_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        assertEquals("boolean[]", ClassUtils.getCanonicalName(boolean[].class, "X"));
    }

    @Test
    public void test_getCanonicalName_Class_String_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        assertEquals("byte[]", ClassUtils.getCanonicalName(byte[].class, "X"));
    }

    @Test
    public void test_getCanonicalName_Class_String_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        assertEquals("char[]", ClassUtils.getCanonicalName(char[].class, "X"));
    }

    @Test
    public void test_getCanonicalName_Class_String_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("short[]", ClassUtils.getCanonicalName(short[].class, "X"));
    }

    @Test
    public void test_getCanonicalName_Class_String_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("int[]", ClassUtils.getCanonicalName(int[].class, "X"));
    }

    @Test
    public void test_getCanonicalName_Class_String_19_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("long[]", ClassUtils.getCanonicalName(long[].class, "X"));
    }

    @Test
    public void test_getCanonicalName_Class_String_20_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("float[]", ClassUtils.getCanonicalName(float[].class, "X"));
    }

    @Test
    public void test_getCanonicalName_Class_String_21_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("double[]", ClassUtils.getCanonicalName(double[].class, "X"));
    }

    @Test
    public void test_getCanonicalName_Class_String_22_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        assertEquals("java.lang.String[][]", ClassUtils.getCanonicalName(String[][].class, "X"));
    }

    @Test
    public void test_getCanonicalName_Class_String_23_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        // removed other assertion
        assertEquals("java.lang.String[][][]", ClassUtils.getCanonicalName(String[][][].class, "X"));
    }

    @Test
    public void test_getCanonicalName_Class_String_24_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        // removed other assertion
        // removed other assertion
        assertEquals("java.lang.String[][][][]", ClassUtils.getCanonicalName(String[][][][].class, "X"));
    }

    @Test
    public void test_getCanonicalName_Class_String_26_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Inner types
        class Named {
            // empty
        }
        // removed other assertion
        assertEquals("X", ClassUtils.getCanonicalName(Named.class, "X"));
    }

    @Test
    public void test_getCanonicalName_Class_String_27_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Inner types
        class Named {
            // empty
        }
        // removed other assertion
        // removed other assertion
        assertEquals("org.apache.commons.lang3.ClassUtilsTest.Inner", ClassUtils.getCanonicalName(Inner.class, "X"));
    }

    @Test
    public void test_getName_Class_1_oe() {
        assertEquals("org.apache.commons.lang3.ClassUtils", ClassUtils.getName(ClassUtils.class));
    }

    @Test
    public void test_getName_Class_2_oe() {
        // removed other assertion
        assertEquals("java.util.Map$Entry", ClassUtils.getName(Map.Entry.class));
    }

    @Test
    public void test_getName_Class_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("", ClassUtils.getName((Class<?>) null));
    }

    @Test
    public void test_getName_Class_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("[Ljava.lang.String;", ClassUtils.getName(String[].class));
    }

    @Test
    public void test_getName_Class_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("[Ljava.util.Map$Entry;", ClassUtils.getName(Map.Entry[].class));
    }

    @Test
    public void test_getName_Class_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        assertEquals("boolean", ClassUtils.getName(boolean.class));
    }

    @Test
    public void test_getName_Class_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        assertEquals("byte", ClassUtils.getName(byte.class));
    }

    @Test
    public void test_getName_Class_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        assertEquals("char", ClassUtils.getName(char.class));
    }

    @Test
    public void test_getName_Class_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("short", ClassUtils.getName(short.class));
    }

    @Test
    public void test_getName_Class_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("int", ClassUtils.getName(int.class));
    }

    @Test
    public void test_getName_Class_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("long", ClassUtils.getName(long.class));
    }

    @Test
    public void test_getName_Class_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("float", ClassUtils.getName(float.class));
    }

    @Test
    public void test_getName_Class_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("double", ClassUtils.getName(double.class));
    }

    @Test
    public void test_getName_Class_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        assertEquals("[Z", ClassUtils.getName(boolean[].class));
    }

    @Test
    public void test_getName_Class_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        assertEquals("[B", ClassUtils.getName(byte[].class));
    }

    @Test
    public void test_getName_Class_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        assertEquals("[C", ClassUtils.getName(char[].class));
    }

    @Test
    public void test_getName_Class_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[S", ClassUtils.getName(short[].class));
    }

    @Test
    public void test_getName_Class_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[I", ClassUtils.getName(int[].class));
    }

    @Test
    public void test_getName_Class_19_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[J", ClassUtils.getName(long[].class));
    }

    @Test
    public void test_getName_Class_20_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[F", ClassUtils.getName(float[].class));
    }

    @Test
    public void test_getName_Class_21_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[D", ClassUtils.getName(double[].class));
    }

    @Test
    public void test_getName_Class_22_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        assertEquals("[[Ljava.lang.String;", ClassUtils.getName(String[][].class));
    }

    @Test
    public void test_getName_Class_23_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        // removed other assertion
        assertEquals("[[[Ljava.lang.String;", ClassUtils.getName(String[][][].class));
    }

    @Test
    public void test_getName_Class_24_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        // removed other assertion
        // removed other assertion
        assertEquals("[[[[Ljava.lang.String;", ClassUtils.getName(String[][][][].class));
    }

    @Test
    public void test_getName_Class_26_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Inner types
        class Named {
            // empty
        }
        // removed other assertion
        assertEquals("org.apache.commons.lang3.ClassUtilsTest$3Named", ClassUtils.getName(Named.class));
    }

    @Test
    public void test_getName_Class_27_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Inner types
        class Named {
            // empty
        }
        // removed other assertion
        // removed other assertion
        assertEquals("org.apache.commons.lang3.ClassUtilsTest$Inner", ClassUtils.getName(Inner.class));
    }

    @Test
    public void test_getName_Object_1_oe() {
        assertEquals("org.apache.commons.lang3.ClassUtils", ClassUtils.getName(new ClassUtils(), "<null>"));
    }

    @Test
    public void test_getName_Object_2_oe() {
        // removed other assertion
        assertEquals("org.apache.commons.lang3.ClassUtilsTest$Inner", ClassUtils.getName(new Inner(), "<null>"));
    }

    @Test
    public void test_getName_Object_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("java.lang.String", ClassUtils.getName("hello", "<null>"));
    }

    @Test
    public void test_getName_Object_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("<null>", ClassUtils.getName(null, "<null>"));
    }

    @Test
    public void test_getName_Object_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Inner types
        class Named {
            // empty
        }
        // removed other assertion
        assertEquals("org.apache.commons.lang3.ClassUtilsTest$4Named", ClassUtils.getName(new Named(), "<null>"));
    }

    @Test
    public void test_getName_Object_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Inner types
        class Named {
            // empty
        }
        // removed other assertion
        // removed other assertion
        assertEquals("org.apache.commons.lang3.ClassUtilsTest$Inner", ClassUtils.getName(new Inner(), "<null>"));
    }

    @Test
    public void test_getPackageCanonicalName_Class_1_oe() {
        assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName(ClassUtils.class));
    }

    @Test
    public void test_getPackageCanonicalName_Class_2_oe() {
        // removed other assertion
        assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName(ClassUtils[].class));
    }

    @Test
    public void test_getPackageCanonicalName_Class_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName(ClassUtils[][].class));
    }

    @Test
    public void test_getPackageCanonicalName_Class_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", ClassUtils.getPackageCanonicalName(int[].class));
    }

    @Test
    public void test_getPackageCanonicalName_Class_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", ClassUtils.getPackageCanonicalName(int[][].class));
    }

    @Test
    public void test_getPackageCanonicalName_Class_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Inner types
        class Named {
            // empty
        }
        // removed other assertion
        assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName(Named.class));
    }

    @Test
    public void test_getPackageCanonicalName_Class_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Inner types
        class Named {
            // empty
        }
        // removed other assertion
        // removed other assertion
        assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName(Inner.class));
    }

    @Test
    public void test_getPackageCanonicalName_Object_1_oe() {
        assertEquals("<null>", ClassUtils.getPackageCanonicalName(null, "<null>"));
    }

    @Test
    public void test_getPackageCanonicalName_Object_2_oe() {
        // removed other assertion
        assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName(new ClassUtils(), "<null>"));
    }

    @Test
    public void test_getPackageCanonicalName_Object_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName(new ClassUtils[0], "<null>"));
    }

    @Test
    public void test_getPackageCanonicalName_Object_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName(new ClassUtils[0][0], "<null>"));
    }

    @Test
    public void test_getPackageCanonicalName_Object_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", ClassUtils.getPackageCanonicalName(new int[0], "<null>"));
    }

    @Test
    public void test_getPackageCanonicalName_Object_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", ClassUtils.getPackageCanonicalName(new int[0][0], "<null>"));
    }

    @Test
    public void test_getPackageCanonicalName_Object_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Inner types
        class Named {
            // empty
        }
        // removed other assertion
        assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName(new Named(), "<null>"));
    }

    @Test
    public void test_getPackageCanonicalName_Object_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Inner types
        class Named {
            // empty
        }
        // removed other assertion
        // removed other assertion
        assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName(new Inner(), "<null>"));
    }

    @Test
    public void test_getPackageCanonicalName_String_1_oe() {
        assertEquals("org.apache.commons.lang3",ClassUtils.getPackageCanonicalName("org.apache.commons.lang3.ClassUtils"));
    }

    @Test
    public void test_getPackageCanonicalName_String_2_oe() {
        // removed other assertion
        assertEquals("org.apache.commons.lang3",ClassUtils.getPackageCanonicalName("[Lorg.apache.commons.lang3.ClassUtils;"));
    }

    @Test
    public void test_getPackageCanonicalName_String_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("org.apache.commons.lang3",ClassUtils.getPackageCanonicalName("[[Lorg.apache.commons.lang3.ClassUtils;"));
    }

    @Test
    public void test_getPackageCanonicalName_String_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("org.apache.commons.lang3",ClassUtils.getPackageCanonicalName("org.apache.commons.lang3.ClassUtils[]"));
    }

    @Test
    public void test_getPackageCanonicalName_String_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("org.apache.commons.lang3",ClassUtils.getPackageCanonicalName("org.apache.commons.lang3.ClassUtils[][]"));
    }

    @Test
    public void test_getPackageCanonicalName_String_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", ClassUtils.getPackageCanonicalName("[I"));
    }

    @Test
    public void test_getPackageCanonicalName_String_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", ClassUtils.getPackageCanonicalName("[[I"));
    }

    @Test
    public void test_getPackageCanonicalName_String_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", ClassUtils.getPackageCanonicalName("int[]"));
    }

    @Test
    public void test_getPackageCanonicalName_String_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", ClassUtils.getPackageCanonicalName("int[][]"));
    }

    @Test
    public void test_getPackageCanonicalName_String_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Inner types
        assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName("org.apache.commons.lang3.ClassUtilsTest$6"));
    }

    @Test
    public void test_getPackageCanonicalName_String_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Inner types
        // removed other assertion
        assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName("org.apache.commons.lang3.ClassUtilsTest$5Named"));
    }

    @Test
    public void test_getPackageCanonicalName_String_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Inner types
        // removed other assertion
        // removed other assertion
        assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName("org.apache.commons.lang3.ClassUtilsTest$Inner"));
    }

    @Test
    public void test_getPackageName_Class_1_oe() {
        assertEquals("java.lang", ClassUtils.getPackageName(String.class));
    }

    @Test
    public void test_getPackageName_Class_2_oe() {
        // removed other assertion
        assertEquals("java.util", ClassUtils.getPackageName(Map.Entry.class));
    }

    @Test
    public void test_getPackageName_Class_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("", ClassUtils.getPackageName((Class<?>) null));
    }

    @Test
    public void test_getPackageName_Class_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        assertEquals("java.lang", ClassUtils.getPackageName(String[].class));
    }

    @Test
    public void test_getPackageName_Class_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion

        // Primitive Arrays
        assertEquals("", ClassUtils.getPackageName(boolean[].class));
    }

    @Test
    public void test_getPackageName_Class_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        assertEquals("", ClassUtils.getPackageName(byte[].class));
    }

    @Test
    public void test_getPackageName_Class_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        assertEquals("", ClassUtils.getPackageName(char[].class));
    }

    @Test
    public void test_getPackageName_Class_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", ClassUtils.getPackageName(short[].class));
    }

    @Test
    public void test_getPackageName_Class_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", ClassUtils.getPackageName(int[].class));
    }

    @Test
    public void test_getPackageName_Class_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", ClassUtils.getPackageName(long[].class));
    }

    @Test
    public void test_getPackageName_Class_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", ClassUtils.getPackageName(float[].class));
    }

    @Test
    public void test_getPackageName_Class_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", ClassUtils.getPackageName(double[].class));
    }

    @Test
    public void test_getPackageName_Class_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        assertEquals("java.lang", ClassUtils.getPackageName(String[][].class));
    }

    @Test
    public void test_getPackageName_Class_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        // removed other assertion
        assertEquals("java.lang", ClassUtils.getPackageName(String[][][].class));
    }

    @Test
    public void test_getPackageName_Class_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        // removed other assertion
        // removed other assertion
        assertEquals("java.lang", ClassUtils.getPackageName(String[][][][].class));
    }

    @Test
    public void test_getPackageName_Class_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // On-the-fly types
        class Named {
            // empty
        }
        // removed other assertion
        assertEquals("org.apache.commons.lang3", ClassUtils.getPackageName(Named.class));
    }

    @Test
    public void test_getPackageName_Object_1_oe() {
        assertEquals("org.apache.commons.lang3", ClassUtils.getPackageName(new ClassUtils(), "<null>"));
    }

    @Test
    public void test_getPackageName_Object_2_oe() {
        // removed other assertion
        assertEquals("org.apache.commons.lang3", ClassUtils.getPackageName(new Inner(), "<null>"));
    }

    @Test
    public void test_getPackageName_Object_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("<null>", ClassUtils.getPackageName(null, "<null>"));
    }

    @Test
    public void test_getPackageName_String_1_oe() {
        assertEquals("org.apache.commons.lang3", ClassUtils.getPackageName(ClassUtils.class.getName()));
    }

    @Test
    public void test_getPackageName_String_2_oe() {
        // removed other assertion
        assertEquals("java.util", ClassUtils.getPackageName(Map.Entry.class.getName()));
    }

    @Test
    public void test_getPackageName_String_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("", ClassUtils.getPackageName((String) null));
    }

    @Test
    public void test_getPackageName_String_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", ClassUtils.getPackageName(""));
    }

    @Test
    public void test_getShortCanonicalName_Class_1_oe() {
        assertEquals("ClassUtils", ClassUtils.getShortCanonicalName(ClassUtils.class));
    }

    @Test
    public void test_getShortCanonicalName_Class_2_oe() {
        // removed other assertion
        assertEquals("ClassUtils[]", ClassUtils.getShortCanonicalName(ClassUtils[].class));
    }

    @Test
    public void test_getShortCanonicalName_Class_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("ClassUtils[][]", ClassUtils.getShortCanonicalName(ClassUtils[][].class));
    }

    @Test
    public void test_getShortCanonicalName_Class_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("int[]", ClassUtils.getShortCanonicalName(int[].class));
    }

    @Test
    public void test_getShortCanonicalName_Class_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("int[][]", ClassUtils.getShortCanonicalName(int[][].class));
    }

    @Test
    public void test_getShortCanonicalName_Class_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Inner types
        class Named {
            // empty
        }
        // WARNING: this is fragile, implementation may change, naming is not guaranteed
        // removed other assertion
        // WARNING: this is fragile, implementation may change, naming is not guaranteed
        assertEquals("ClassUtilsTest.8Named", ClassUtils.getShortCanonicalName(Named.class));
    }

    @Test
    public void test_getShortCanonicalName_Class_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Inner types
        class Named {
            // empty
        }
        // WARNING: this is fragile, implementation may change, naming is not guaranteed
        // removed other assertion
        // WARNING: this is fragile, implementation may change, naming is not guaranteed
        // removed other assertion
        assertEquals("ClassUtilsTest.Inner", ClassUtils.getShortCanonicalName(Inner.class));
    }

    @Test
    public void test_getShortCanonicalName_Object_1_oe() {
        assertEquals("<null>", ClassUtils.getShortCanonicalName(null, "<null>"));
    }

    @Test
    public void test_getShortCanonicalName_Object_2_oe() {
        // removed other assertion
        assertEquals("ClassUtils", ClassUtils.getShortCanonicalName(new ClassUtils(), "<null>"));
    }

    @Test
    public void test_getShortCanonicalName_Object_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("ClassUtils[]", ClassUtils.getShortCanonicalName(new ClassUtils[0], "<null>"));
    }

    @Test
    public void test_getShortCanonicalName_Object_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ClassUtils[][]", ClassUtils.getShortCanonicalName(new ClassUtils[0][0], "<null>"));
    }

    @Test
    public void test_getShortCanonicalName_Object_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("int[]", ClassUtils.getShortCanonicalName(new int[0], "<null>"));
    }

    @Test
    public void test_getShortCanonicalName_Object_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("int[][]", ClassUtils.getShortCanonicalName(new int[0][0], "<null>"));
    }

    @Test
    public void test_getShortCanonicalName_Object_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Inner types
        class Named {
            // empty
        }
        // WARNING: this is fragile, implementation may change, naming is not guaranteed
        // removed other assertion
        assertEquals("ClassUtilsTest.9Named", ClassUtils.getShortCanonicalName(new Named(), "<null>"));
    }

    @Test
    public void test_getShortCanonicalName_Object_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Inner types
        class Named {
            // empty
        }
        // WARNING: this is fragile, implementation may change, naming is not guaranteed
        // removed other assertion
        // removed other assertion
        assertEquals("ClassUtilsTest.Inner", ClassUtils.getShortCanonicalName(new Inner(), "<null>"));
    }

    @Test
    public void test_getShortCanonicalName_String_1_oe() {
        assertEquals("", ClassUtils.getShortCanonicalName((String) null));
    }

    @Test
    public void test_getShortCanonicalName_String_2_oe() {
        // removed other assertion
        assertEquals("Map.Entry", ClassUtils.getShortCanonicalName(java.util.Map.Entry.class.getName()));
    }

    @Test
    public void test_getShortCanonicalName_String_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("Entry", ClassUtils.getShortCanonicalName(java.util.Map.Entry.class.getCanonicalName()));
    }

    @Test
    public void test_getShortCanonicalName_String_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ClassUtils", ClassUtils.getShortCanonicalName("org.apache.commons.lang3.ClassUtils"));
    }

    @Test
    public void test_getShortCanonicalName_String_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ClassUtils[]", ClassUtils.getShortCanonicalName("[Lorg.apache.commons.lang3.ClassUtils;"));
    }

    @Test
    public void test_getShortCanonicalName_String_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ClassUtils[][]", ClassUtils.getShortCanonicalName("[[Lorg.apache.commons.lang3.ClassUtils;"));
    }

    @Test
    public void test_getShortCanonicalName_String_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ClassUtils[]", ClassUtils.getShortCanonicalName("org.apache.commons.lang3.ClassUtils[]"));
    }

    @Test
    public void test_getShortCanonicalName_String_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ClassUtils[][]", ClassUtils.getShortCanonicalName("org.apache.commons.lang3.ClassUtils[][]"));
    }

    @Test
    public void test_getShortCanonicalName_String_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("int[]", ClassUtils.getShortCanonicalName("[I"));
    }

    @Test
    public void test_getShortCanonicalName_String_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("int[]", ClassUtils.getShortCanonicalName(int[].class.getCanonicalName()));
    }

    @Test
    public void test_getShortCanonicalName_String_11_oe() {
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
        assertEquals("int[]", ClassUtils.getShortCanonicalName(int[].class.getName()));
    }

    @Test
    public void test_getShortCanonicalName_String_12_oe() {
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
        assertEquals("int[][]", ClassUtils.getShortCanonicalName("[[I"));
    }

    @Test
    public void test_getShortCanonicalName_String_13_oe() {
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
        assertEquals("int[]", ClassUtils.getShortCanonicalName("int[]"));
    }

    @Test
    public void test_getShortCanonicalName_String_14_oe() {
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
        assertEquals("int[][]", ClassUtils.getShortCanonicalName("int[][]"));
    }

    @Test
    public void test_getShortCanonicalName_String_15_oe() {
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

        // this is to demonstrate that the documentation and the naming of the methods
        // uses the class name and canonical name totally mixed up, which cannot be
        // fixed without backward compatibility break
        assertEquals("int[]", int[].class.getCanonicalName());
    }

    @Test
    public void test_getShortCanonicalName_String_16_oe() {
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

        // this is to demonstrate that the documentation and the naming of the methods
        // uses the class name and canonical name totally mixed up, which cannot be
        // fixed without backward compatibility break
        // removed other assertion
        assertEquals("[I", int[].class.getName());
    }

    @Test
    public void test_getShortCanonicalName_String_17_oe() {
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

        // this is to demonstrate that the documentation and the naming of the methods
        // uses the class name and canonical name totally mixed up, which cannot be
        // fixed without backward compatibility break
        // removed other assertion
        // removed other assertion

        // Inner types... the problem is that these are not canonical names, classes with this name do not even have canonical name
        // WARNING: this is fragile, implementation may change, naming is not guaranteed
        assertEquals("ClassUtilsTest.6", ClassUtils.getShortCanonicalName("org.apache.commons.lang3.ClassUtilsTest$6"));
    }

    @Test
    public void test_getShortCanonicalName_String_18_oe() {
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

        // this is to demonstrate that the documentation and the naming of the methods
        // uses the class name and canonical name totally mixed up, which cannot be
        // fixed without backward compatibility break
        // removed other assertion
        // removed other assertion

        // Inner types... the problem is that these are not canonical names, classes with this name do not even have canonical name
        // WARNING: this is fragile, implementation may change, naming is not guaranteed
        // removed other assertion
      // WARNING: this is fragile, implementation may change, naming is not guaranteed
        assertEquals("ClassUtilsTest.5Named", ClassUtils.getShortCanonicalName("org.apache.commons.lang3.ClassUtilsTest$5Named"));
    }

    @Test
    public void test_getShortCanonicalName_String_19_oe() {
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

        // this is to demonstrate that the documentation and the naming of the methods
        // uses the class name and canonical name totally mixed up, which cannot be
        // fixed without backward compatibility break
        // removed other assertion
        // removed other assertion

        // Inner types... the problem is that these are not canonical names, classes with this name do not even have canonical name
        // WARNING: this is fragile, implementation may change, naming is not guaranteed
        // removed other assertion
      // WARNING: this is fragile, implementation may change, naming is not guaranteed
        // removed other assertion
        assertEquals("ClassUtilsTest.Inner", ClassUtils.getShortCanonicalName("org.apache.commons.lang3.ClassUtilsTest$Inner"));
    }

    @Test
    public void test_getShortCanonicalName_String_20_oe() {
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

        // this is to demonstrate that the .getClass().getName());
    }

    @Test
    public void test_getShortCanonicalName_String_21_oe() {
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

        // this is to demonstrate that the .getClass().getCanonicalName());
    }

    @Test
    public void test_getShortClassName_Class_1_oe() {
        assertEquals("ClassUtils", ClassUtils.getShortClassName(ClassUtils.class));
    }

    @Test
    public void test_getShortClassName_Class_2_oe() {
        // removed other assertion
        assertEquals("Map.Entry", ClassUtils.getShortClassName(Map.Entry.class));
    }

    @Test
    public void test_getShortClassName_Class_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("", ClassUtils.getShortClassName((Class<?>) null));
    }

    @Test
    public void test_getShortClassName_Class_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        assertEquals("String[]", ClassUtils.getShortClassName(String[].class));
    }

    @Test
    public void test_getShortClassName_Class_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        assertEquals("Map.Entry[]", ClassUtils.getShortClassName(Map.Entry[].class));
    }

    @Test
    public void test_getShortClassName_Class_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        assertEquals("boolean", ClassUtils.getShortClassName(boolean.class));
    }

    @Test
    public void test_getShortClassName_Class_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        assertEquals("byte", ClassUtils.getShortClassName(byte.class));
    }

    @Test
    public void test_getShortClassName_Class_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        assertEquals("char", ClassUtils.getShortClassName(char.class));
    }

    @Test
    public void test_getShortClassName_Class_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("short", ClassUtils.getShortClassName(short.class));
    }

    @Test
    public void test_getShortClassName_Class_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("int", ClassUtils.getShortClassName(int.class));
    }

    @Test
    public void test_getShortClassName_Class_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("long", ClassUtils.getShortClassName(long.class));
    }

    @Test
    public void test_getShortClassName_Class_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("float", ClassUtils.getShortClassName(float.class));
    }

    @Test
    public void test_getShortClassName_Class_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("double", ClassUtils.getShortClassName(double.class));
    }

    @Test
    public void test_getShortClassName_Class_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        assertEquals("boolean[]", ClassUtils.getShortClassName(boolean[].class));
    }

    @Test
    public void test_getShortClassName_Class_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        assertEquals("byte[]", ClassUtils.getShortClassName(byte[].class));
    }

    @Test
    public void test_getShortClassName_Class_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        assertEquals("char[]", ClassUtils.getShortClassName(char[].class));
    }

    @Test
    public void test_getShortClassName_Class_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("short[]", ClassUtils.getShortClassName(short[].class));
    }

    @Test
    public void test_getShortClassName_Class_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("int[]", ClassUtils.getShortClassName(int[].class));
    }

    @Test
    public void test_getShortClassName_Class_19_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("long[]", ClassUtils.getShortClassName(long[].class));
    }

    @Test
    public void test_getShortClassName_Class_20_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("float[]", ClassUtils.getShortClassName(float[].class));
    }

    @Test
    public void test_getShortClassName_Class_21_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("double[]", ClassUtils.getShortClassName(double[].class));
    }

    @Test
    public void test_getShortClassName_Class_22_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        assertEquals("String[][]", ClassUtils.getShortClassName(String[][].class));
    }

    @Test
    public void test_getShortClassName_Class_23_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        // removed other assertion
        assertEquals("String[][][]", ClassUtils.getShortClassName(String[][][].class));
    }

    @Test
    public void test_getShortClassName_Class_24_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        // removed other assertion
        // removed other assertion
        assertEquals("String[][][][]", ClassUtils.getShortClassName(String[][][][].class));
    }

    @Test
    public void test_getShortClassName_Class_26_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Inner types
        class Named {
            // empty
        }
      // WARNING: this is fragile, implementation may change, naming is not guaranteed
        // removed other assertion
        // WARNING: this is fragile, implementation may change, naming is not guaranteed
        assertEquals("ClassUtilsTest.10Named", ClassUtils.getShortClassName(Named.class));
    }

    @Test
    public void test_getShortClassName_Class_27_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Inner types
        class Named {
            // empty
        }
      // WARNING: this is fragile, implementation may change, naming is not guaranteed
        // removed other assertion
        // WARNING: this is fragile, implementation may change, naming is not guaranteed
        // removed other assertion
        assertEquals("ClassUtilsTest.Inner", ClassUtils.getShortClassName(Inner.class));
    }

    @Test
    public void test_getShortClassName_Object_1_oe() {
        assertEquals("ClassUtils", ClassUtils.getShortClassName(new ClassUtils(), "<null>"));
    }

    @Test
    public void test_getShortClassName_Object_2_oe() {
        // removed other assertion
        assertEquals("ClassUtilsTest.Inner", ClassUtils.getShortClassName(new Inner(), "<null>"));
    }

    @Test
    public void test_getShortClassName_Object_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("String", ClassUtils.getShortClassName("hello", "<null>"));
    }

    @Test
    public void test_getShortClassName_Object_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("<null>", ClassUtils.getShortClassName(null, "<null>"));
    }

    @Test
    public void test_getShortClassName_Object_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Inner types
        class Named {
            // empty
        }
      // WARNING: this is fragile, implementation may change, naming is not guaranteed
        // removed other assertion
        // WARNING: this is fragile, implementation may change, naming is not guaranteed
        assertEquals("ClassUtilsTest.11Named", ClassUtils.getShortClassName(new Named(), "<null>"));
    }

    @Test
    public void test_getShortClassName_Object_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Inner types
        class Named {
            // empty
        }
      // WARNING: this is fragile, implementation may change, naming is not guaranteed
        // removed other assertion
        // WARNING: this is fragile, implementation may change, naming is not guaranteed
        // removed other assertion
        assertEquals("ClassUtilsTest.Inner", ClassUtils.getShortClassName(new Inner(), "<null>"));
    }

    @Test
    public void test_getShortClassName_String_1_oe() {
        assertEquals("ClassUtils", ClassUtils.getShortClassName(ClassUtils.class.getName()));
    }

    @Test
    public void test_getShortClassName_String_2_oe() {
        // removed other assertion
        assertEquals("Map.Entry", ClassUtils.getShortClassName(Map.Entry.class.getName()));
    }

    @Test
    public void test_getShortClassName_String_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("", ClassUtils.getShortClassName((String) null));
    }

    @Test
    public void test_getShortClassName_String_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", ClassUtils.getShortClassName(""));
    }

    @Test
    public void test_getSimpleName_Class_1_oe() {
        assertEquals("ClassUtils", ClassUtils.getSimpleName(ClassUtils.class));
    }

    @Test
    public void test_getSimpleName_Class_2_oe() {
        // removed other assertion
        assertEquals("Entry", ClassUtils.getSimpleName(Map.Entry.class));
    }

    @Test
    public void test_getSimpleName_Class_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("", ClassUtils.getSimpleName(null));
    }

    @Test
    public void test_getSimpleName_Class_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        assertEquals("String[]", ClassUtils.getSimpleName(String[].class));
    }

    @Test
    public void test_getSimpleName_Class_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        assertEquals("Entry[]", ClassUtils.getSimpleName(Map.Entry[].class));
    }

    @Test
    public void test_getSimpleName_Class_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        assertEquals("boolean", ClassUtils.getSimpleName(boolean.class));
    }

    @Test
    public void test_getSimpleName_Class_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        assertEquals("byte", ClassUtils.getSimpleName(byte.class));
    }

    @Test
    public void test_getSimpleName_Class_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        assertEquals("char", ClassUtils.getSimpleName(char.class));
    }

    @Test
    public void test_getSimpleName_Class_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("short", ClassUtils.getSimpleName(short.class));
    }

    @Test
    public void test_getSimpleName_Class_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("int", ClassUtils.getSimpleName(int.class));
    }

    @Test
    public void test_getSimpleName_Class_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("long", ClassUtils.getSimpleName(long.class));
    }

    @Test
    public void test_getSimpleName_Class_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("float", ClassUtils.getSimpleName(float.class));
    }

    @Test
    public void test_getSimpleName_Class_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("double", ClassUtils.getSimpleName(double.class));
    }

    @Test
    public void test_getSimpleName_Class_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        assertEquals("boolean[]", ClassUtils.getSimpleName(boolean[].class));
    }

    @Test
    public void test_getSimpleName_Class_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        assertEquals("byte[]", ClassUtils.getSimpleName(byte[].class));
    }

    @Test
    public void test_getSimpleName_Class_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        assertEquals("char[]", ClassUtils.getSimpleName(char[].class));
    }

    @Test
    public void test_getSimpleName_Class_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("short[]", ClassUtils.getSimpleName(short[].class));
    }

    @Test
    public void test_getSimpleName_Class_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("int[]", ClassUtils.getSimpleName(int[].class));
    }

    @Test
    public void test_getSimpleName_Class_19_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("long[]", ClassUtils.getSimpleName(long[].class));
    }

    @Test
    public void test_getSimpleName_Class_20_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("float[]", ClassUtils.getSimpleName(float[].class));
    }

    @Test
    public void test_getSimpleName_Class_21_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("double[]", ClassUtils.getSimpleName(double[].class));
    }

    @Test
    public void test_getSimpleName_Class_22_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        assertEquals("String[][]", ClassUtils.getSimpleName(String[][].class));
    }

    @Test
    public void test_getSimpleName_Class_23_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        // removed other assertion
        assertEquals("String[][][]", ClassUtils.getSimpleName(String[][][].class));
    }

    @Test
    public void test_getSimpleName_Class_24_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        // removed other assertion
        // removed other assertion
        assertEquals("String[][][][]", ClassUtils.getSimpleName(String[][][][].class));
    }

    @Test
    public void test_getSimpleName_Class_26_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // LANG-535
        // removed other assertion
        // removed other assertion

        // Primitives
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Primitive Arrays
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Arrays of arrays of ...
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // On-the-fly types
        class Named {
            // empty
        }
        // removed other assertion
        assertEquals("Named", ClassUtils.getSimpleName(Named.class));
    }

    @Test
    public void test_getSimpleName_Object_1_oe() {
        assertEquals("ClassUtils", ClassUtils.getSimpleName(new ClassUtils()));
    }

    @Test
    public void test_getSimpleName_Object_2_oe() {
        // removed other assertion
        assertEquals("Inner", ClassUtils.getSimpleName(new Inner()));
    }

    @Test
    public void test_getSimpleName_Object_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("String", ClassUtils.getSimpleName("hello"));
    }

    @Test
    public void test_getSimpleName_Object_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(StringUtils.EMPTY, ClassUtils.getSimpleName(null));
    }

    @Test
    public void test_getSimpleName_Object_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(StringUtils.EMPTY, ClassUtils.getSimpleName(null));
    }

    @Test
    public void test_getSimpleName_Object_String_1_oe() {
        assertEquals("ClassUtils", ClassUtils.getSimpleName(new ClassUtils(), "<null>"));
    }

    @Test
    public void test_getSimpleName_Object_String_2_oe() {
        // removed other assertion
        assertEquals("Inner", ClassUtils.getSimpleName(new Inner(), "<null>"));
    }

    @Test
    public void test_getSimpleName_Object_String_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("String", ClassUtils.getSimpleName("hello", "<null>"));
    }

    @Test
    public void test_getSimpleName_Object_String_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("<null>", ClassUtils.getSimpleName(null, "<null>"));
    }

    @Test
    public void test_getSimpleName_Object_String_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(ClassUtils.getSimpleName(null, null));
    }

    @Test
    public void test_isAssignable_1_oe() {
        assertFalse(ClassUtils.isAssignable((Class<?>) null, null));
    }

    @Test
    public void test_isAssignable_2_oe() {
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(String.class, null));
    }

    @Test
    public void test_isAssignable_3_oe() {
        // removed other assertion
        // removed other assertion

        assertTrue(ClassUtils.isAssignable(null, Object.class));
    }

    @Test
    public void test_isAssignable_4_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(ClassUtils.isAssignable(null, Integer.class));
    }

    @Test
    public void test_isAssignable_5_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(null, Integer.TYPE));
    }

    @Test
    public void test_isAssignable_6_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(String.class, Object.class));
    }

    @Test
    public void test_isAssignable_7_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(String.class, String.class));
    }

    @Test
    public void test_isAssignable_8_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Object.class, String.class));
    }

    @Test
    public void test_isAssignable_9_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue(ClassUtils.isAssignable(Integer.TYPE, Integer.class));
    }

    @Test
    public void test_isAssignable_10_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Integer.TYPE, Object.class));
    }

    @Test
    public void test_isAssignable_11_oe() {
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
        assertTrue(ClassUtils.isAssignable(Integer.class, Integer.TYPE));
    }

    @Test
    public void test_isAssignable_12_oe() {
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
        assertTrue(ClassUtils.isAssignable(Integer.class, Object.class));
    }

    @Test
    public void test_isAssignable_13_oe() {
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
        assertTrue(ClassUtils.isAssignable(Integer.TYPE, Integer.TYPE));
    }

    @Test
    public void test_isAssignable_14_oe() {
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
        assertTrue(ClassUtils.isAssignable(Integer.class, Integer.class));
    }

    @Test
    public void test_isAssignable_15_oe() {
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
        assertTrue(ClassUtils.isAssignable(Boolean.TYPE, Boolean.class));
    }

    @Test
    public void test_isAssignable_16_oe() {
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
        assertTrue(ClassUtils.isAssignable(Boolean.TYPE, Object.class));
    }

    @Test
    public void test_isAssignable_17_oe() {
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
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Boolean.class, Boolean.TYPE));
    }

    @Test
    public void test_isAssignable_18_oe() {
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
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Boolean.class, Object.class));
    }

    @Test
    public void test_isAssignable_19_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Boolean.TYPE, Boolean.TYPE));
    }

    @Test
    public void test_isAssignable_20_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Boolean.class, Boolean.class));
    }

    @Test
    public void test_isAssignable_Autoboxing_1_oe() {
        assertFalse(ClassUtils.isAssignable((Class<?>) null, null, true));
    }

    @Test
    public void test_isAssignable_Autoboxing_2_oe() {
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(String.class, null, true));
    }

    @Test
    public void test_isAssignable_Autoboxing_3_oe() {
        // removed other assertion
        // removed other assertion

        assertTrue(ClassUtils.isAssignable(null, Object.class, true));
    }

    @Test
    public void test_isAssignable_Autoboxing_4_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(ClassUtils.isAssignable(null, Integer.class, true));
    }

    @Test
    public void test_isAssignable_Autoboxing_5_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(null, Integer.TYPE, true));
    }

    @Test
    public void test_isAssignable_Autoboxing_6_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(String.class, Object.class, true));
    }

    @Test
    public void test_isAssignable_Autoboxing_7_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(String.class, String.class, true));
    }

    @Test
    public void test_isAssignable_Autoboxing_8_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Object.class, String.class, true));
    }

    @Test
    public void test_isAssignable_Autoboxing_9_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Integer.TYPE, Integer.class, true));
    }

    @Test
    public void test_isAssignable_Autoboxing_10_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Integer.TYPE, Object.class, true));
    }

    @Test
    public void test_isAssignable_Autoboxing_11_oe() {
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
        assertTrue(ClassUtils.isAssignable(Integer.class, Integer.TYPE, true));
    }

    @Test
    public void test_isAssignable_Autoboxing_12_oe() {
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
        assertTrue(ClassUtils.isAssignable(Integer.class, Object.class, true));
    }

    @Test
    public void test_isAssignable_Autoboxing_13_oe() {
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
        assertTrue(ClassUtils.isAssignable(Integer.TYPE, Integer.TYPE, true));
    }

    @Test
    public void test_isAssignable_Autoboxing_14_oe() {
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
        assertTrue(ClassUtils.isAssignable(Integer.class, Integer.class, true));
    }

    @Test
    public void test_isAssignable_Autoboxing_15_oe() {
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
        assertTrue(ClassUtils.isAssignable(Boolean.TYPE, Boolean.class, true));
    }

    @Test
    public void test_isAssignable_Autoboxing_16_oe() {
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
        assertTrue(ClassUtils.isAssignable(Boolean.class, Boolean.TYPE, true));
    }

    @Test
    public void test_isAssignable_Autoboxing_17_oe() {
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
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Boolean.class, Object.class, true));
    }

    @Test
    public void test_isAssignable_Autoboxing_18_oe() {
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
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Boolean.TYPE, Boolean.TYPE, true));
    }

    @Test
    public void test_isAssignable_Autoboxing_19_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Boolean.class, Boolean.class, true));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_1_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        assertFalse(ClassUtils.isAssignable(array1, array2));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_2_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        // removed other assertion
        assertFalse(ClassUtils.isAssignable(null, array2));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_3_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(null, array0));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_4_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(array0, array0));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_5_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(array0,(Class<?>[])null));// explicit cast to avoid warning assertTrue(ClassUtils.isAssignable(null,(Class<?>[])null));// explicit cast to avoid warning assertFalse(ClassUtils.isAssignable(array1,array1s));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_6_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(array1s, array1s));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_7_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(array1s, array1));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_8_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue(ClassUtils.isAssignable(arrayPrimitives, arrayWrappers));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_9_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(ClassUtils.isAssignable(arrayWrappers, arrayPrimitives));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_10_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(arrayPrimitives, array1));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_11_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

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
        assertFalse(ClassUtils.isAssignable(arrayWrappers, array1));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_12_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

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
        assertTrue(ClassUtils.isAssignable(arrayPrimitives, array2));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_13_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

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
        assertTrue(ClassUtils.isAssignable(arrayWrappers, array2));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_Autoboxing_1_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        assertFalse(ClassUtils.isAssignable(array1, array2, true));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_Autoboxing_2_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        // removed other assertion
        assertFalse(ClassUtils.isAssignable(null, array2, true));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_Autoboxing_3_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(null, array0, true));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_Autoboxing_4_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(array0, array0, true));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_Autoboxing_5_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(array0, null, true));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_Autoboxing_6_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable((Class[]) null, null, true));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_Autoboxing_7_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(ClassUtils.isAssignable(array1, array1s, true));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_Autoboxing_8_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(ClassUtils.isAssignable(array1s, array1s, true));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_Autoboxing_9_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(array1s, array1, true));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_Autoboxing_10_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue(ClassUtils.isAssignable(arrayPrimitives, arrayWrappers, true));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_Autoboxing_11_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

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
        assertTrue(ClassUtils.isAssignable(arrayWrappers, arrayPrimitives, true));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_Autoboxing_12_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

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
        assertFalse(ClassUtils.isAssignable(arrayPrimitives, array1, true));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_Autoboxing_13_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

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
        assertFalse(ClassUtils.isAssignable(arrayWrappers, array1, true));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_Autoboxing_14_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

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
        assertTrue(ClassUtils.isAssignable(arrayPrimitives, array2, true));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_Autoboxing_15_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

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
        assertTrue(ClassUtils.isAssignable(arrayWrappers, array2, true));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_NoAutoboxing_1_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        assertFalse(ClassUtils.isAssignable(array1, array2, false));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_NoAutoboxing_2_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        // removed other assertion
        assertFalse(ClassUtils.isAssignable(null, array2, false));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_NoAutoboxing_3_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(null, array0, false));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_NoAutoboxing_4_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(array0, array0, false));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_NoAutoboxing_5_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(array0, null, false));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_NoAutoboxing_6_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable((Class[]) null, null, false));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_NoAutoboxing_7_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(ClassUtils.isAssignable(array1, array1s, false));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_NoAutoboxing_8_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(ClassUtils.isAssignable(array1s, array1s, false));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_NoAutoboxing_9_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(array1s, array1, false));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_NoAutoboxing_10_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(ClassUtils.isAssignable(arrayPrimitives, arrayWrappers, false));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_NoAutoboxing_11_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

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
        assertFalse(ClassUtils.isAssignable(arrayWrappers, arrayPrimitives, false));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_NoAutoboxing_12_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

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
        assertFalse(ClassUtils.isAssignable(arrayPrimitives, array1, false));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_NoAutoboxing_13_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

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
        assertFalse(ClassUtils.isAssignable(arrayWrappers, array1, false));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_NoAutoboxing_14_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

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
        assertTrue(ClassUtils.isAssignable(arrayWrappers, array2, false));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_NoAutoboxing_15_oe() {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

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
        assertFalse(ClassUtils.isAssignable(arrayPrimitives, array2, false));
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_1_oe() {
        // test byte conversions
        assertFalse(ClassUtils.isAssignable(Byte.class, Character.TYPE), "byte -> char");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_2_oe() {
        // test byte conversions
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Byte.class, Byte.TYPE), "byte -> byte");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_3_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Byte.class, Short.TYPE), "byte -> short");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_4_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Byte.class, Integer.TYPE), "byte -> int");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_5_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Byte.class, Long.TYPE), "byte -> long");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_6_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Byte.class, Float.TYPE), "byte -> float");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_7_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Byte.class, Double.TYPE), "byte -> double");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_8_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Byte.class, Boolean.TYPE), "byte -> boolean");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_9_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        assertFalse(ClassUtils.isAssignable(Short.class, Character.TYPE), "short -> char");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_10_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Short.class, Byte.TYPE), "short -> byte");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_11_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Short.class, Short.TYPE), "short -> short");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_12_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Short.class, Integer.TYPE), "short -> int");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_13_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Short.class, Long.TYPE), "short -> long");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_14_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Short.class, Float.TYPE), "short -> float");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_15_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Short.class, Double.TYPE), "short -> double");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_16_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Short.class, Boolean.TYPE), "short -> boolean");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_17_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        assertTrue(ClassUtils.isAssignable(Character.class, Character.TYPE), "char -> char");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_18_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Character.class, Byte.TYPE), "char -> byte");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_19_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Character.class, Short.TYPE), "char -> short");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_20_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Character.class, Integer.TYPE), "char -> int");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_21_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Character.class, Long.TYPE), "char -> long");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_22_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Character.class, Float.TYPE), "char -> float");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_23_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Character.class, Double.TYPE), "char -> double");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_24_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Character.class, Boolean.TYPE), "char -> boolean");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_25_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        assertFalse(ClassUtils.isAssignable(Integer.class, Character.TYPE), "int -> char");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_26_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Integer.class, Byte.TYPE), "int -> byte");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_27_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Integer.class, Short.TYPE), "int -> short");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_28_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Integer.class, Integer.TYPE), "int -> int");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_29_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Integer.class, Long.TYPE), "int -> long");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_30_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Integer.class, Float.TYPE), "int -> float");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_31_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Integer.class, Double.TYPE), "int -> double");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_32_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Integer.class, Boolean.TYPE), "int -> boolean");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_33_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        assertFalse(ClassUtils.isAssignable(Long.class, Character.TYPE), "long -> char");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_34_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Long.class, Byte.TYPE), "long -> byte");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_35_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Long.class, Short.TYPE), "long -> short");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_36_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Long.class, Integer.TYPE), "long -> int");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_37_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Long.class, Long.TYPE), "long -> long");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_38_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Long.class, Float.TYPE), "long -> float");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_39_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Long.class, Double.TYPE), "long -> double");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_40_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Long.class, Boolean.TYPE), "long -> boolean");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_41_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        assertFalse(ClassUtils.isAssignable(Float.class, Character.TYPE), "float -> char");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_42_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Float.class, Byte.TYPE), "float -> byte");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_43_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Float.class, Short.TYPE), "float -> short");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_44_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Float.class, Integer.TYPE), "float -> int");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_45_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Float.class, Long.TYPE), "float -> long");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_46_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Float.class, Float.TYPE), "float -> float");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_47_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Float.class, Double.TYPE), "float -> double");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_48_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Float.class, Boolean.TYPE), "float -> boolean");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_49_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        assertFalse(ClassUtils.isAssignable(Double.class, Character.TYPE), "double -> char");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_50_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Double.class, Byte.TYPE), "double -> byte");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_51_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Double.class, Short.TYPE), "double -> short");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_52_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Double.class, Integer.TYPE), "double -> int");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_53_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Double.class, Long.TYPE), "double -> long");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_54_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Double.class, Float.TYPE), "double -> float");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_55_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Double.class, Double.TYPE), "double -> double");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_56_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Double.class, Boolean.TYPE), "double -> boolean");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_57_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test boolean conversions
        assertFalse(ClassUtils.isAssignable(Boolean.class, Character.TYPE), "boolean -> char");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_58_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test boolean conversions
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Boolean.class, Byte.TYPE), "boolean -> byte");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_59_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test boolean conversions
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Boolean.class, Short.TYPE), "boolean -> short");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_60_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test boolean conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Boolean.class, Integer.TYPE), "boolean -> int");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_61_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test boolean conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Boolean.class, Long.TYPE), "boolean -> long");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_62_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test boolean conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Boolean.class, Float.TYPE), "boolean -> float");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_63_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test boolean conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Boolean.class, Double.TYPE), "boolean -> double");
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening_64_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test boolean conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Boolean.class, Boolean.TYPE), "boolean -> boolean");
    }

    @Test
    public void test_isAssignable_NoAutoboxing_1_oe() {
        assertFalse(ClassUtils.isAssignable((Class<?>) null, null, false));
    }

    @Test
    public void test_isAssignable_NoAutoboxing_2_oe() {
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(String.class, null, false));
    }

    @Test
    public void test_isAssignable_NoAutoboxing_3_oe() {
        // removed other assertion
        // removed other assertion

        assertTrue(ClassUtils.isAssignable(null, Object.class, false));
    }

    @Test
    public void test_isAssignable_NoAutoboxing_4_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(ClassUtils.isAssignable(null, Integer.class, false));
    }

    @Test
    public void test_isAssignable_NoAutoboxing_5_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(null, Integer.TYPE, false));
    }

    @Test
    public void test_isAssignable_NoAutoboxing_6_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(String.class, Object.class, false));
    }

    @Test
    public void test_isAssignable_NoAutoboxing_7_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(String.class, String.class, false));
    }

    @Test
    public void test_isAssignable_NoAutoboxing_8_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Object.class, String.class, false));
    }

    @Test
    public void test_isAssignable_NoAutoboxing_9_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Integer.TYPE, Integer.class, false));
    }

    @Test
    public void test_isAssignable_NoAutoboxing_10_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Integer.TYPE, Object.class, false));
    }

    @Test
    public void test_isAssignable_NoAutoboxing_11_oe() {
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
        assertFalse(ClassUtils.isAssignable(Integer.class, Integer.TYPE, false));
    }

    @Test
    public void test_isAssignable_NoAutoboxing_12_oe() {
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
        assertTrue(ClassUtils.isAssignable(Integer.TYPE, Integer.TYPE, false));
    }

    @Test
    public void test_isAssignable_NoAutoboxing_13_oe() {
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
        assertTrue(ClassUtils.isAssignable(Integer.class, Integer.class, false));
    }

    @Test
    public void test_isAssignable_NoAutoboxing_14_oe() {
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
        assertFalse(ClassUtils.isAssignable(Boolean.TYPE, Boolean.class, false));
    }

    @Test
    public void test_isAssignable_NoAutoboxing_15_oe() {
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
        assertFalse(ClassUtils.isAssignable(Boolean.TYPE, Object.class, false));
    }

    @Test
    public void test_isAssignable_NoAutoboxing_16_oe() {
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
        assertFalse(ClassUtils.isAssignable(Boolean.class, Boolean.TYPE, false));
    }

    @Test
    public void test_isAssignable_NoAutoboxing_17_oe() {
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
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Boolean.class, Object.class, false));
    }

    @Test
    public void test_isAssignable_NoAutoboxing_18_oe() {
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
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Boolean.TYPE, Boolean.TYPE, false));
    }

    @Test
    public void test_isAssignable_NoAutoboxing_19_oe() {
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Boolean.class, Boolean.class, false));
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_1_oe() {
        // test byte conversions
        assertFalse(ClassUtils.isAssignable(Byte.class, Character.TYPE, true), "byte -> char");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_2_oe() {
        // test byte conversions
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Byte.class, Byte.TYPE, true), "byte -> byte");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_3_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Byte.class, Short.TYPE, true), "byte -> short");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_4_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Byte.class, Integer.TYPE, true), "byte -> int");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_5_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Byte.class, Long.TYPE, true), "byte -> long");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_6_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Byte.class, Float.TYPE, true), "byte -> float");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_7_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Byte.class, Double.TYPE, true), "byte -> double");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_8_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Byte.class, Boolean.TYPE, true), "byte -> boolean");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_9_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        assertFalse(ClassUtils.isAssignable(Short.class, Character.TYPE, true), "short -> char");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_10_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Short.class, Byte.TYPE, true), "short -> byte");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_11_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Short.class, Short.TYPE, true), "short -> short");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_12_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Short.class, Integer.TYPE, true), "short -> int");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_13_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Short.class, Long.TYPE, true), "short -> long");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_14_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Short.class, Float.TYPE, true), "short -> float");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_15_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Short.class, Double.TYPE, true), "short -> double");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_16_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Short.class, Boolean.TYPE, true), "short -> boolean");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_17_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        assertTrue(ClassUtils.isAssignable(Character.class, Character.TYPE, true), "char -> char");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_18_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Character.class, Byte.TYPE, true), "char -> byte");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_19_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Character.class, Short.TYPE, true), "char -> short");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_20_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Character.class, Integer.TYPE, true), "char -> int");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_21_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Character.class, Long.TYPE, true), "char -> long");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_22_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Character.class, Float.TYPE, true), "char -> float");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_23_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Character.class, Double.TYPE, true), "char -> double");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_24_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Character.class, Boolean.TYPE, true), "char -> boolean");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_25_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        assertFalse(ClassUtils.isAssignable(Integer.class, Character.TYPE, true), "int -> char");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_26_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Integer.class, Byte.TYPE, true), "int -> byte");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_27_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Integer.class, Short.TYPE, true), "int -> short");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_28_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Integer.class, Integer.TYPE, true), "int -> int");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_29_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Integer.class, Long.TYPE, true), "int -> long");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_30_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Integer.class, Float.TYPE, true), "int -> float");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_31_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Integer.class, Double.TYPE, true), "int -> double");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_32_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Integer.class, Boolean.TYPE, true), "int -> boolean");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_33_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        assertFalse(ClassUtils.isAssignable(Long.class, Character.TYPE, true), "long -> char");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_34_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Long.class, Byte.TYPE, true), "long -> byte");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_35_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Long.class, Short.TYPE, true), "long -> short");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_36_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Long.class, Integer.TYPE, true), "long -> int");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_37_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Long.class, Long.TYPE, true), "long -> long");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_38_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Long.class, Float.TYPE, true), "long -> float");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_39_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Long.class, Double.TYPE, true), "long -> double");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_40_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Long.class, Boolean.TYPE, true), "long -> boolean");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_41_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        assertFalse(ClassUtils.isAssignable(Float.class, Character.TYPE, true), "float -> char");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_42_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Float.class, Byte.TYPE, true), "float -> byte");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_43_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Float.class, Short.TYPE, true), "float -> short");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_44_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Float.class, Integer.TYPE, true), "float -> int");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_45_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Float.class, Long.TYPE, true), "float -> long");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_46_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Float.class, Float.TYPE, true), "float -> float");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_47_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Float.class, Double.TYPE, true), "float -> double");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_48_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Float.class, Boolean.TYPE, true), "float -> boolean");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_49_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        assertFalse(ClassUtils.isAssignable(Double.class, Character.TYPE, true), "double -> char");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_50_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Double.class, Byte.TYPE, true), "double -> byte");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_51_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Double.class, Short.TYPE, true), "double -> short");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_52_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Double.class, Integer.TYPE, true), "double -> int");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_53_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Double.class, Long.TYPE, true), "double -> long");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_54_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Double.class, Float.TYPE, true), "double -> float");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_55_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Double.class, Double.TYPE, true), "double -> double");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_56_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Double.class, Boolean.TYPE, true), "double -> boolean");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_57_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test boolean conversions
        assertFalse(ClassUtils.isAssignable(Boolean.class, Character.TYPE, true), "boolean -> char");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_58_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test boolean conversions
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Boolean.class, Byte.TYPE, true), "boolean -> byte");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_59_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test boolean conversions
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Boolean.class, Short.TYPE, true), "boolean -> short");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_60_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test boolean conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Boolean.class, Integer.TYPE, true), "boolean -> int");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_61_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test boolean conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Boolean.class, Long.TYPE, true), "boolean -> long");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_62_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test boolean conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Boolean.class, Float.TYPE, true), "boolean -> float");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_63_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test boolean conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Boolean.class, Double.TYPE, true), "boolean -> double");
    }

    @Test
    public void test_isAssignable_Unboxing_Widening_64_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test boolean conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Boolean.class, Boolean.TYPE, true), "boolean -> boolean");
    }

    @Test
    public void test_isAssignable_Widening_1_oe() {
        // test byte conversions
        assertFalse(ClassUtils.isAssignable(Byte.TYPE, Character.TYPE), "byte -> char");
    }

    @Test
    public void test_isAssignable_Widening_2_oe() {
        // test byte conversions
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Byte.TYPE, Byte.TYPE), "byte -> byte");
    }

    @Test
    public void test_isAssignable_Widening_3_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Byte.TYPE, Short.TYPE), "byte -> short");
    }

    @Test
    public void test_isAssignable_Widening_4_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Byte.TYPE, Integer.TYPE), "byte -> int");
    }

    @Test
    public void test_isAssignable_Widening_5_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Byte.TYPE, Long.TYPE), "byte -> long");
    }

    @Test
    public void test_isAssignable_Widening_6_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Byte.TYPE, Float.TYPE), "byte -> float");
    }

    @Test
    public void test_isAssignable_Widening_7_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Byte.TYPE, Double.TYPE), "byte -> double");
    }

    @Test
    public void test_isAssignable_Widening_8_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Byte.TYPE, Boolean.TYPE), "byte -> boolean");
    }

    @Test
    public void test_isAssignable_Widening_9_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        assertFalse(ClassUtils.isAssignable(Short.TYPE, Character.TYPE), "short -> char");
    }

    @Test
    public void test_isAssignable_Widening_10_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Short.TYPE, Byte.TYPE), "short -> byte");
    }

    @Test
    public void test_isAssignable_Widening_11_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Short.TYPE, Short.TYPE), "short -> short");
    }

    @Test
    public void test_isAssignable_Widening_12_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Short.TYPE, Integer.TYPE), "short -> int");
    }

    @Test
    public void test_isAssignable_Widening_13_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Short.TYPE, Long.TYPE), "short -> long");
    }

    @Test
    public void test_isAssignable_Widening_14_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Short.TYPE, Float.TYPE), "short -> float");
    }

    @Test
    public void test_isAssignable_Widening_15_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Short.TYPE, Double.TYPE), "short -> double");
    }

    @Test
    public void test_isAssignable_Widening_16_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Short.TYPE, Boolean.TYPE), "short -> boolean");
    }

    @Test
    public void test_isAssignable_Widening_17_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        assertTrue(ClassUtils.isAssignable(Character.TYPE, Character.TYPE), "char -> char");
    }

    @Test
    public void test_isAssignable_Widening_18_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Character.TYPE, Byte.TYPE), "char -> byte");
    }

    @Test
    public void test_isAssignable_Widening_19_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Character.TYPE, Short.TYPE), "char -> short");
    }

    @Test
    public void test_isAssignable_Widening_20_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Character.TYPE, Integer.TYPE), "char -> int");
    }

    @Test
    public void test_isAssignable_Widening_21_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Character.TYPE, Long.TYPE), "char -> long");
    }

    @Test
    public void test_isAssignable_Widening_22_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Character.TYPE, Float.TYPE), "char -> float");
    }

    @Test
    public void test_isAssignable_Widening_23_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Character.TYPE, Double.TYPE), "char -> double");
    }

    @Test
    public void test_isAssignable_Widening_24_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Character.TYPE, Boolean.TYPE), "char -> boolean");
    }

    @Test
    public void test_isAssignable_Widening_25_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        assertFalse(ClassUtils.isAssignable(Integer.TYPE, Character.TYPE), "int -> char");
    }

    @Test
    public void test_isAssignable_Widening_26_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Integer.TYPE, Byte.TYPE), "int -> byte");
    }

    @Test
    public void test_isAssignable_Widening_27_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Integer.TYPE, Short.TYPE), "int -> short");
    }

    @Test
    public void test_isAssignable_Widening_28_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Integer.TYPE, Integer.TYPE), "int -> int");
    }

    @Test
    public void test_isAssignable_Widening_29_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Integer.TYPE, Long.TYPE), "int -> long");
    }

    @Test
    public void test_isAssignable_Widening_30_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Integer.TYPE, Float.TYPE), "int -> float");
    }

    @Test
    public void test_isAssignable_Widening_31_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Integer.TYPE, Double.TYPE), "int -> double");
    }

    @Test
    public void test_isAssignable_Widening_32_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Integer.TYPE, Boolean.TYPE), "int -> boolean");
    }

    @Test
    public void test_isAssignable_Widening_33_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        assertFalse(ClassUtils.isAssignable(Long.TYPE, Character.TYPE), "long -> char");
    }

    @Test
    public void test_isAssignable_Widening_34_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Long.TYPE, Byte.TYPE), "long -> byte");
    }

    @Test
    public void test_isAssignable_Widening_35_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Long.TYPE, Short.TYPE), "long -> short");
    }

    @Test
    public void test_isAssignable_Widening_36_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Long.TYPE, Integer.TYPE), "long -> int");
    }

    @Test
    public void test_isAssignable_Widening_37_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Long.TYPE, Long.TYPE), "long -> long");
    }

    @Test
    public void test_isAssignable_Widening_38_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Long.TYPE, Float.TYPE), "long -> float");
    }

    @Test
    public void test_isAssignable_Widening_39_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Long.TYPE, Double.TYPE), "long -> double");
    }

    @Test
    public void test_isAssignable_Widening_40_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Long.TYPE, Boolean.TYPE), "long -> boolean");
    }

    @Test
    public void test_isAssignable_Widening_41_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        assertFalse(ClassUtils.isAssignable(Float.TYPE, Character.TYPE), "float -> char");
    }

    @Test
    public void test_isAssignable_Widening_42_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Float.TYPE, Byte.TYPE), "float -> byte");
    }

    @Test
    public void test_isAssignable_Widening_43_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Float.TYPE, Short.TYPE), "float -> short");
    }

    @Test
    public void test_isAssignable_Widening_44_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Float.TYPE, Integer.TYPE), "float -> int");
    }

    @Test
    public void test_isAssignable_Widening_45_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Float.TYPE, Long.TYPE), "float -> long");
    }

    @Test
    public void test_isAssignable_Widening_46_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Float.TYPE, Float.TYPE), "float -> float");
    }

    @Test
    public void test_isAssignable_Widening_47_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Float.TYPE, Double.TYPE), "float -> double");
    }

    @Test
    public void test_isAssignable_Widening_48_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Float.TYPE, Boolean.TYPE), "float -> boolean");
    }

    @Test
    public void test_isAssignable_Widening_49_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        assertFalse(ClassUtils.isAssignable(Double.TYPE, Character.TYPE), "double -> char");
    }

    @Test
    public void test_isAssignable_Widening_50_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Double.TYPE, Byte.TYPE), "double -> byte");
    }

    @Test
    public void test_isAssignable_Widening_51_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Double.TYPE, Short.TYPE), "double -> short");
    }

    @Test
    public void test_isAssignable_Widening_52_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Double.TYPE, Integer.TYPE), "double -> int");
    }

    @Test
    public void test_isAssignable_Widening_53_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Double.TYPE, Long.TYPE), "double -> long");
    }

    @Test
    public void test_isAssignable_Widening_54_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Double.TYPE, Float.TYPE), "double -> float");
    }

    @Test
    public void test_isAssignable_Widening_55_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Double.TYPE, Double.TYPE), "double -> double");
    }

    @Test
    public void test_isAssignable_Widening_56_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Double.TYPE, Boolean.TYPE), "double -> boolean");
    }

    @Test
    public void test_isAssignable_Widening_57_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test boolean conversions
        assertFalse(ClassUtils.isAssignable(Boolean.TYPE, Character.TYPE), "boolean -> char");
    }

    @Test
    public void test_isAssignable_Widening_58_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test boolean conversions
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Boolean.TYPE, Byte.TYPE), "boolean -> byte");
    }

    @Test
    public void test_isAssignable_Widening_59_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test boolean conversions
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Boolean.TYPE, Short.TYPE), "boolean -> short");
    }

    @Test
    public void test_isAssignable_Widening_60_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test boolean conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Boolean.TYPE, Integer.TYPE), "boolean -> int");
    }

    @Test
    public void test_isAssignable_Widening_61_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test boolean conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Boolean.TYPE, Long.TYPE), "boolean -> long");
    }

    @Test
    public void test_isAssignable_Widening_62_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test boolean conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Boolean.TYPE, Float.TYPE), "boolean -> float");
    }

    @Test
    public void test_isAssignable_Widening_63_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test boolean conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isAssignable(Boolean.TYPE, Double.TYPE), "boolean -> double");
    }

    @Test
    public void test_isAssignable_Widening_64_oe() {
        // test byte conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test short conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test char conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test int conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test long conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test float conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test double conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test boolean conversions
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isAssignable(Boolean.TYPE, Boolean.TYPE), "boolean -> boolean");
    }

    @Test
    public void test_isInnerClass_Class_1_oe() {
        assertTrue(ClassUtils.isInnerClass(Inner.class));
    }

    @Test
    public void test_isInnerClass_Class_2_oe() {
        // removed other assertion
        assertTrue(ClassUtils.isInnerClass(Map.Entry.class));
    }

    @Test
    public void test_isInnerClass_Class_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isInnerClass(this.getClass()));
    }

    @Test
    public void test_isInnerClass_Class_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isInnerClass(String.class));
    }

    @Test
    public void test_isInnerClass_Class_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isInnerClass(null));
    }

    @Test
    public void testConstructor_1_oe() {
        assertNotNull(new ClassUtils());
    }

    @Test
    public void testConstructor_2_oe() {
        // removed other assertion
        final Constructor<?>[] cons = ClassUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
    }

    @Test
    public void testConstructor_3_oe() {
        // removed other assertion
        final Constructor<?>[] cons = ClassUtils.class.getDeclaredConstructors();
        // removed other assertion
        assertTrue(Modifier.isPublic(cons[0].getModifiers()));
    }

    @Test
    public void testConstructor_4_oe() {
        // removed other assertion
        final Constructor<?>[] cons = ClassUtils.class.getDeclaredConstructors();
        // removed other assertion
        // removed other assertion
        assertTrue(Modifier.isPublic(ClassUtils.class.getModifiers()));
    }

    @Test
    public void testConstructor_5_oe() {
        // removed other assertion
        final Constructor<?>[] cons = ClassUtils.class.getDeclaredConstructors();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(Modifier.isFinal(ClassUtils.class.getModifiers()));
    }

    @Test
    public void testGetClassByNormalNameArrays_1_oe() throws ClassNotFoundException {
        assertEquals( int[].class, ClassUtils.getClass( "int[]" ) );
    }

    @Test
    public void testGetClassByNormalNameArrays_2_oe() throws ClassNotFoundException {
        // removed other assertion
        assertEquals( long[].class, ClassUtils.getClass( "long[]" ) );
    }

    @Test
    public void testGetClassByNormalNameArrays_3_oe() throws ClassNotFoundException {
        // removed other assertion
        // removed other assertion
        assertEquals( short[].class, ClassUtils.getClass( "short[]" ) );
    }

    @Test
    public void testGetClassByNormalNameArrays_4_oe() throws ClassNotFoundException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( byte[].class, ClassUtils.getClass( "byte[]" ) );
    }

    @Test
    public void testGetClassByNormalNameArrays_5_oe() throws ClassNotFoundException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( char[].class, ClassUtils.getClass( "char[]" ) );
    }

    @Test
    public void testGetClassByNormalNameArrays_6_oe() throws ClassNotFoundException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( float[].class, ClassUtils.getClass( "float[]" ) );
    }

    @Test
    public void testGetClassByNormalNameArrays_7_oe() throws ClassNotFoundException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( double[].class, ClassUtils.getClass( "double[]" ) );
    }

    @Test
    public void testGetClassByNormalNameArrays_8_oe() throws ClassNotFoundException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( boolean[].class, ClassUtils.getClass( "boolean[]" ) );
    }

    @Test
    public void testGetClassByNormalNameArrays_9_oe() throws ClassNotFoundException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( String[].class, ClassUtils.getClass( "java.lang.String[]" ) );
    }

    @Test
    public void testGetClassByNormalNameArrays_10_oe() throws ClassNotFoundException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( java.util.Map.Entry[].class, ClassUtils.getClass( "java.util.Map.Entry[]" ) );
    }

    @Test
    public void testGetClassByNormalNameArrays_11_oe() throws ClassNotFoundException {
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
        assertEquals( java.util.Map.Entry[].class, ClassUtils.getClass( "java.util.Map$Entry[]" ) );
    }

    @Test
    public void testGetClassByNormalNameArrays_12_oe() throws ClassNotFoundException {
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
        assertEquals( java.util.Map.Entry[].class, ClassUtils.getClass( "[Ljava.util.Map.Entry;" ) );
    }

    @Test
    public void testGetClassByNormalNameArrays_13_oe() throws ClassNotFoundException {
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
        assertEquals( java.util.Map.Entry[].class, ClassUtils.getClass( "[Ljava.util.Map$Entry;" ) );
    }

    @Test
    public void testGetClassByNormalNameArrays2D_1_oe() throws ClassNotFoundException {
        assertEquals( int[][].class, ClassUtils.getClass( "int[][]" ) );
    }

    @Test
    public void testGetClassByNormalNameArrays2D_2_oe() throws ClassNotFoundException {
        // removed other assertion
        assertEquals( long[][].class, ClassUtils.getClass( "long[][]" ) );
    }

    @Test
    public void testGetClassByNormalNameArrays2D_3_oe() throws ClassNotFoundException {
        // removed other assertion
        // removed other assertion
        assertEquals( short[][].class, ClassUtils.getClass( "short[][]" ) );
    }

    @Test
    public void testGetClassByNormalNameArrays2D_4_oe() throws ClassNotFoundException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( byte[][].class, ClassUtils.getClass( "byte[][]" ) );
    }

    @Test
    public void testGetClassByNormalNameArrays2D_5_oe() throws ClassNotFoundException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( char[][].class, ClassUtils.getClass( "char[][]" ) );
    }

    @Test
    public void testGetClassByNormalNameArrays2D_6_oe() throws ClassNotFoundException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( float[][].class, ClassUtils.getClass( "float[][]" ) );
    }

    @Test
    public void testGetClassByNormalNameArrays2D_7_oe() throws ClassNotFoundException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( double[][].class, ClassUtils.getClass( "double[][]" ) );
    }

    @Test
    public void testGetClassByNormalNameArrays2D_8_oe() throws ClassNotFoundException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( boolean[][].class, ClassUtils.getClass( "boolean[][]" ) );
    }

    @Test
    public void testGetClassByNormalNameArrays2D_9_oe() throws ClassNotFoundException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( String[][].class, ClassUtils.getClass( "java.lang.String[][]" ) );
    }

    @Test
    public void testGetClassRawPrimitives_1_oe() throws ClassNotFoundException {
        assertEquals( int.class, ClassUtils.getClass( "int" ) );
    }

    @Test
    public void testGetClassRawPrimitives_2_oe() throws ClassNotFoundException {
        // removed other assertion
        assertEquals( long.class, ClassUtils.getClass( "long" ) );
    }

    @Test
    public void testGetClassRawPrimitives_3_oe() throws ClassNotFoundException {
        // removed other assertion
        // removed other assertion
        assertEquals( short.class, ClassUtils.getClass( "short" ) );
    }

    @Test
    public void testGetClassRawPrimitives_4_oe() throws ClassNotFoundException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( byte.class, ClassUtils.getClass( "byte" ) );
    }

    @Test
    public void testGetClassRawPrimitives_5_oe() throws ClassNotFoundException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( char.class, ClassUtils.getClass( "char" ) );
    }

    @Test
    public void testGetClassRawPrimitives_6_oe() throws ClassNotFoundException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( float.class, ClassUtils.getClass( "float" ) );
    }

    @Test
    public void testGetClassRawPrimitives_7_oe() throws ClassNotFoundException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( double.class, ClassUtils.getClass( "double" ) );
    }

    @Test
    public void testGetClassRawPrimitives_8_oe() throws ClassNotFoundException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( boolean.class, ClassUtils.getClass( "boolean" ) );
    }

    @Test
    public void testGetClassRawPrimitives_9_oe() throws ClassNotFoundException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( void.class, ClassUtils.getClass( "void" ) );
    }

    @Test
    public void testGetInnerClass_1_oe() throws ClassNotFoundException {
        assertEquals( Inner.DeeplyNested.class, ClassUtils.getClass( "org.apache.commons.lang3.ClassUtilsTest.Inner.DeeplyNested" ) );
    }

    @Test
    public void testGetInnerClass_2_oe() throws ClassNotFoundException {
        // removed other assertion
        assertEquals( Inner.DeeplyNested.class, ClassUtils.getClass( "org.apache.commons.lang3.ClassUtilsTest.Inner$DeeplyNested" ) );
    }

    @Test
    public void testGetInnerClass_3_oe() throws ClassNotFoundException {
        // removed other assertion
        // removed other assertion
        assertEquals( Inner.DeeplyNested.class, ClassUtils.getClass( "org.apache.commons.lang3.ClassUtilsTest$Inner$DeeplyNested" ) );
    }

    @Test
    public void testGetInnerClass_4_oe() throws ClassNotFoundException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( Inner.DeeplyNested.class, ClassUtils.getClass( "org.apache.commons.lang3.ClassUtilsTest$Inner.DeeplyNested" ) );
    }

    @Test
    public void testGetPublicMethod_1_oe() throws Exception {
        // Tests with Collections$UnmodifiableSet
        final Set<?> set = Collections.unmodifiableSet(new HashSet<>());
        final Method isEmptyMethod = ClassUtils.getPublicMethod(set.getClass(), "isEmpty");
        assertTrue(Modifier.isPublic(isEmptyMethod.getDeclaringClass().getModifiers()));
    }

    @Test
    public void testGetPublicMethod_2_oe() throws Exception {
        // Tests with Collections$UnmodifiableSet
        final Set<?> set = Collections.unmodifiableSet(new HashSet<>());
        final Method isEmptyMethod = ClassUtils.getPublicMethod(set.getClass(), "isEmpty");
        // removed other assertion
        assertTrue((Boolean) isEmptyMethod.invoke(set));
    }

    @Test
    public void testGetPublicMethod_3_oe() throws Exception {
        // Tests with Collections$UnmodifiableSet
        final Set<?> set = Collections.unmodifiableSet(new HashSet<>());
        final Method isEmptyMethod = ClassUtils.getPublicMethod(set.getClass(), "isEmpty");
        // removed other assertion
        // removed other assertion

        // Tests with a public Class
        final Method toStringMethod = ClassUtils.getPublicMethod(Object.class, "toString");
        assertEquals(Object.class.getMethod("toString"), toStringMethod);
    }

    @Test
    public void testHierarchyExcludingInterfaces_1_oe() {
        final Iterator<Class<?>> iter = ClassUtils.hierarchy(StringParameterizedChild.class).iterator();
        assertEquals(StringParameterizedChild.class, iter.next());
    }

    @Test
    public void testHierarchyExcludingInterfaces_2_oe() {
        final Iterator<Class<?>> iter = ClassUtils.hierarchy(StringParameterizedChild.class).iterator();
        // removed other assertion
        assertEquals(GenericParent.class, iter.next());
    }

    @Test
    public void testHierarchyExcludingInterfaces_3_oe() {
        final Iterator<Class<?>> iter = ClassUtils.hierarchy(StringParameterizedChild.class).iterator();
        // removed other assertion
        // removed other assertion
        assertEquals(Object.class, iter.next());
    }

    @Test
    public void testHierarchyExcludingInterfaces_4_oe() {
        final Iterator<Class<?>> iter = ClassUtils.hierarchy(StringParameterizedChild.class).iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(iter.hasNext());
    }

    @Test
    public void testHierarchyIncludingInterfaces_1_oe() {
        final Iterator<Class<?>> iter =
            ClassUtils.hierarchy(StringParameterizedChild.class, Interfaces.INCLUDE).iterator();
        assertEquals(StringParameterizedChild.class, iter.next());
    }

    @Test
    public void testHierarchyIncludingInterfaces_2_oe() {
        final Iterator<Class<?>> iter =
            ClassUtils.hierarchy(StringParameterizedChild.class, Interfaces.INCLUDE).iterator();
        // removed other assertion
        assertEquals(GenericParent.class, iter.next());
    }

    @Test
    public void testHierarchyIncludingInterfaces_3_oe() {
        final Iterator<Class<?>> iter =
            ClassUtils.hierarchy(StringParameterizedChild.class, Interfaces.INCLUDE).iterator();
        // removed other assertion
        // removed other assertion
        assertEquals(GenericConsumer.class, iter.next());
    }

    @Test
    public void testHierarchyIncludingInterfaces_4_oe() {
        final Iterator<Class<?>> iter =
            ClassUtils.hierarchy(StringParameterizedChild.class, Interfaces.INCLUDE).iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Object.class, iter.next());
    }

    @Test
    public void testHierarchyIncludingInterfaces_5_oe() {
        final Iterator<Class<?>> iter =
            ClassUtils.hierarchy(StringParameterizedChild.class, Interfaces.INCLUDE).iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(iter.hasNext());
    }

    @Test
    public void testIsPrimitiveOrWrapper_1_oe() {

        // test primitive wrapper classes
        assertTrue(ClassUtils.isPrimitiveOrWrapper(Boolean.class), "Boolean.class");
    }

    @Test
    public void testIsPrimitiveOrWrapper_2_oe() {

        // test primitive wrapper classes
        // removed other assertion
        assertTrue(ClassUtils.isPrimitiveOrWrapper(Byte.class), "Byte.class");
    }

    @Test
    public void testIsPrimitiveOrWrapper_3_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isPrimitiveOrWrapper(Character.class), "Character.class");
    }

    @Test
    public void testIsPrimitiveOrWrapper_4_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isPrimitiveOrWrapper(Short.class), "Short.class");
    }

    @Test
    public void testIsPrimitiveOrWrapper_5_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isPrimitiveOrWrapper(Integer.class), "Integer.class");
    }

    @Test
    public void testIsPrimitiveOrWrapper_6_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isPrimitiveOrWrapper(Long.class), "Long.class");
    }

    @Test
    public void testIsPrimitiveOrWrapper_7_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isPrimitiveOrWrapper(Double.class), "Double.class");
    }

    @Test
    public void testIsPrimitiveOrWrapper_8_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isPrimitiveOrWrapper(Float.class), "Float.class");
    }

    @Test
    public void testIsPrimitiveOrWrapper_9_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test primitive classes
        assertTrue(ClassUtils.isPrimitiveOrWrapper(Boolean.TYPE), "boolean");
    }

    @Test
    public void testIsPrimitiveOrWrapper_10_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test primitive classes
        // removed other assertion
        assertTrue(ClassUtils.isPrimitiveOrWrapper(Byte.TYPE), "byte");
    }

    @Test
    public void testIsPrimitiveOrWrapper_11_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test primitive classes
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isPrimitiveOrWrapper(Character.TYPE), "char");
    }

    @Test
    public void testIsPrimitiveOrWrapper_12_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isPrimitiveOrWrapper(Short.TYPE), "short");
    }

    @Test
    public void testIsPrimitiveOrWrapper_13_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isPrimitiveOrWrapper(Integer.TYPE), "int");
    }

    @Test
    public void testIsPrimitiveOrWrapper_14_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isPrimitiveOrWrapper(Long.TYPE), "long");
    }

    @Test
    public void testIsPrimitiveOrWrapper_15_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isPrimitiveOrWrapper(Double.TYPE), "double");
    }

    @Test
    public void testIsPrimitiveOrWrapper_16_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isPrimitiveOrWrapper(Float.TYPE), "float");
    }

    @Test
    public void testIsPrimitiveOrWrapper_17_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isPrimitiveOrWrapper(Void.TYPE), "Void.TYPE");
    }

    @Test
    public void testIsPrimitiveOrWrapper_18_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // others
        assertFalse(ClassUtils.isPrimitiveOrWrapper(null), "null");
    }

    @Test
    public void testIsPrimitiveOrWrapper_19_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // others
        // removed other assertion
        assertFalse(ClassUtils.isPrimitiveOrWrapper(Void.class), "Void.class");
    }

    @Test
    public void testIsPrimitiveOrWrapper_20_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // others
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isPrimitiveOrWrapper(String.class), "String.class");
    }

    @Test
    public void testIsPrimitiveOrWrapper_21_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // others
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isPrimitiveOrWrapper(this.getClass()), "this.getClass()");
    }

    @Test
    public void testIsPrimitiveWrapper_1_oe() {

        // test primitive wrapper classes
        assertTrue(ClassUtils.isPrimitiveWrapper(Boolean.class), "Boolean.class");
    }

    @Test
    public void testIsPrimitiveWrapper_2_oe() {

        // test primitive wrapper classes
        // removed other assertion
        assertTrue(ClassUtils.isPrimitiveWrapper(Byte.class), "Byte.class");
    }

    @Test
    public void testIsPrimitiveWrapper_3_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isPrimitiveWrapper(Character.class), "Character.class");
    }

    @Test
    public void testIsPrimitiveWrapper_4_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isPrimitiveWrapper(Short.class), "Short.class");
    }

    @Test
    public void testIsPrimitiveWrapper_5_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isPrimitiveWrapper(Integer.class), "Integer.class");
    }

    @Test
    public void testIsPrimitiveWrapper_6_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isPrimitiveWrapper(Long.class), "Long.class");
    }

    @Test
    public void testIsPrimitiveWrapper_7_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isPrimitiveWrapper(Double.class), "Double.class");
    }

    @Test
    public void testIsPrimitiveWrapper_8_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ClassUtils.isPrimitiveWrapper(Float.class), "Float.class");
    }

    @Test
    public void testIsPrimitiveWrapper_9_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test primitive classes
        assertFalse(ClassUtils.isPrimitiveWrapper(Boolean.TYPE), "boolean");
    }

    @Test
    public void testIsPrimitiveWrapper_10_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test primitive classes
        // removed other assertion
        assertFalse(ClassUtils.isPrimitiveWrapper(Byte.TYPE), "byte");
    }

    @Test
    public void testIsPrimitiveWrapper_11_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test primitive classes
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isPrimitiveWrapper(Character.TYPE), "char");
    }

    @Test
    public void testIsPrimitiveWrapper_12_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isPrimitiveWrapper(Short.TYPE), "short");
    }

    @Test
    public void testIsPrimitiveWrapper_13_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isPrimitiveWrapper(Integer.TYPE), "int");
    }

    @Test
    public void testIsPrimitiveWrapper_14_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isPrimitiveWrapper(Long.TYPE), "long");
    }

    @Test
    public void testIsPrimitiveWrapper_15_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isPrimitiveWrapper(Double.TYPE), "double");
    }

    @Test
    public void testIsPrimitiveWrapper_16_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isPrimitiveWrapper(Float.TYPE), "float");
    }

    @Test
    public void testIsPrimitiveWrapper_17_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // others
        assertFalse(ClassUtils.isPrimitiveWrapper(null), "null");
    }

    @Test
    public void testIsPrimitiveWrapper_18_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // others
        // removed other assertion
        assertFalse(ClassUtils.isPrimitiveWrapper(Void.class), "Void.class");
    }

    @Test
    public void testIsPrimitiveWrapper_19_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // others
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isPrimitiveWrapper(Void.TYPE), "Void.TYPE");
    }

    @Test
    public void testIsPrimitiveWrapper_20_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // others
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isPrimitiveWrapper(String.class), "String.class");
    }

    @Test
    public void testIsPrimitiveWrapper_21_oe() {

        // test primitive wrapper classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // others
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ClassUtils.isPrimitiveWrapper(this.getClass()), "this.getClass()");
    }

    @Test
    public void testPrimitivesToWrappers_1_oe() {
        // test null
//        assertNull("null -> null", ClassUtils.primitivesToWrappers(null)); // generates warning
        assertNull(ClassUtils.primitivesToWrappers((Class<?>[])null),"null -> null");// equivalent cast to avoid warning assertArrayEquals(ArrayUtils.EMPTY_CLASS_ARRAY,ClassUtils.primitivesToWrappers(),"empty -> empty");
    }

    @Test
    public void testPrimitivesToWrappers_2_oe() {
        // test null
//        assertNull("null -> null", ClassUtils.primitivesToWrappers(null)); // generates warning
        // removed other assertion
        final Class<?>[] castNull = ClassUtils.primitivesToWrappers((Class<?>) null); // == new Class<?>[]{null}
        assertArrayEquals(new Class<?>[]{null}, castNull, "(Class<?>) null -> [null]");
    }

    @Test
    public void testPrimitivesToWrappers_3_oe() {
        // test null
//        assertNull("null -> null", ClassUtils.primitivesToWrappers(null)); // generates warning
        // removed other assertion
        final Class<?>[] castNull = ClassUtils.primitivesToWrappers((Class<?>) null); // == new Class<?>[]{null}
        // removed other assertion
        // test empty array is returned unchanged
        assertArrayEquals(ArrayUtils.EMPTY_CLASS_ARRAY,ClassUtils.primitivesToWrappers(ArrayUtils.EMPTY_CLASS_ARRAY),"empty -> empty");
    }

    @Test
    public void testPrimitivesToWrappers_4_oe() {
        // test null
//        assertNull("null -> null", ClassUtils.primitivesToWrappers(null)); // generates warning
        // removed other assertion
        final Class<?>[] castNull = ClassUtils.primitivesToWrappers((Class<?>) null); // == new Class<?>[]{null}
        // removed other assertion
        // test empty array is returned unchanged
        // removed other assertion

        // test an array of various classes
        final Class<?>[] primitives = new Class[] {
                Boolean.TYPE, Byte.TYPE, Character.TYPE, Short.TYPE,
                Integer.TYPE, Long.TYPE, Double.TYPE, Float.TYPE,
                String.class, ClassUtils.class
        };
        final Class<?>[] wrappers= ClassUtils.primitivesToWrappers(primitives);

        for (int i=0; i < primitives.length; i++) {
            // test each returned wrapper
            final Class<?> primitive = primitives[i];
            final Class<?> expectedWrapper = ClassUtils.primitiveToWrapper(primitive);

            assertEquals(expectedWrapper, wrappers[i], primitive + " -> " + expectedWrapper);
    }
    }

    @Test
    public void testPrimitivesToWrappers_5_oe() {
        // test null
//        assertNull("null -> null", ClassUtils.primitivesToWrappers(null)); // generates warning
        // removed other assertion
        final Class<?>[] castNull = ClassUtils.primitivesToWrappers((Class<?>) null); // == new Class<?>[]{null}
        // removed other assertion
        // test empty array is returned unchanged
        // removed other assertion

        // test an array of various classes
        final Class<?>[] primitives = new Class[] {
                Boolean.TYPE, Byte.TYPE, Character.TYPE, Short.TYPE,
                Integer.TYPE, Long.TYPE, Double.TYPE, Float.TYPE,
                String.class, ClassUtils.class
        };
        final Class<?>[] wrappers= ClassUtils.primitivesToWrappers(primitives);

        for (int i=0; i < primitives.length; i++) {
            // test each returned wrapper
            final Class<?> primitive = primitives[i];
            final Class<?> expectedWrapper = ClassUtils.primitiveToWrapper(primitive);

            // removed other assertion
        }

        // test an array of no primitive classes
        final Class<?>[] noPrimitives = new Class[] {
                String.class, ClassUtils.class, Void.TYPE
        };
        // This used to return the exact same array, but no longer does.
        assertNotSame(noPrimitives, ClassUtils.primitivesToWrappers(noPrimitives), "unmodified");
    }

    @Test
    public void testPrimitiveToWrapper_1_oe() {

        // test primitive classes
        assertEquals(Boolean.class, ClassUtils.primitiveToWrapper(Boolean.TYPE), "boolean -> Boolean.class");
    }

    @Test
    public void testPrimitiveToWrapper_2_oe() {

        // test primitive classes
        // removed other assertion
        assertEquals(Byte.class, ClassUtils.primitiveToWrapper(Byte.TYPE), "byte -> Byte.class");
    }

    @Test
    public void testPrimitiveToWrapper_3_oe() {

        // test primitive classes
        // removed other assertion
        // removed other assertion
        assertEquals(Character.class, ClassUtils.primitiveToWrapper(Character.TYPE), "char -> Character.class");
    }

    @Test
    public void testPrimitiveToWrapper_4_oe() {

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Short.class, ClassUtils.primitiveToWrapper(Short.TYPE), "short -> Short.class");
    }

    @Test
    public void testPrimitiveToWrapper_5_oe() {

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.class, ClassUtils.primitiveToWrapper(Integer.TYPE), "int -> Integer.class");
    }

    @Test
    public void testPrimitiveToWrapper_6_oe() {

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Long.class, ClassUtils.primitiveToWrapper(Long.TYPE), "long -> Long.class");
    }

    @Test
    public void testPrimitiveToWrapper_7_oe() {

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Double.class, ClassUtils.primitiveToWrapper(Double.TYPE), "double -> Double.class");
    }

    @Test
    public void testPrimitiveToWrapper_8_oe() {

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.class, ClassUtils.primitiveToWrapper(Float.TYPE), "float -> Float.class");
    }

    @Test
    public void testPrimitiveToWrapper_9_oe() {

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test a few other classes
        assertEquals(String.class, ClassUtils.primitiveToWrapper(String.class), "String.class -> String.class");
    }

    @Test
    public void testPrimitiveToWrapper_10_oe() {

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test a few other classes
        // removed other assertion
        assertEquals(ClassUtils.class,ClassUtils.primitiveToWrapper(ClassUtils.class),"ClassUtils.class -> ClassUtils.class");
    }

    @Test
    public void testPrimitiveToWrapper_11_oe() {

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test a few other classes
        // removed other assertion
        // removed other assertion
        assertEquals(Void.TYPE, ClassUtils.primitiveToWrapper(Void.TYPE), "Void.TYPE -> Void.TYPE");
    }

    @Test
    public void testPrimitiveToWrapper_12_oe() {

        // test primitive classes
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test a few other classes
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // test null
        assertNull(ClassUtils.primitiveToWrapper(null), "null -> null");
    }

    @Test
    public void testShowJavaBug_1_oe() throws Exception {
        // Tests with Collections$UnmodifiableSet
        final Set<?> set = Collections.unmodifiableSet(new HashSet<>());
        final Method isEmptyMethod = set.getClass().getMethod("isEmpty");
        assertThrows(IllegalAccessException.class, () -> isEmptyMethod.invoke(set));
    }

    @Test
    public void testToClass_object_1_oe() {
//        assertNull(ClassUtils.toClass(null)); // generates warning
        assertNull(ClassUtils.toClass((Object[])null));// equivalent explicit cast assertArrayEquals(ArrayUtils.EMPTY_CLASS_ARRAY,ClassUtils.toClass(),"empty -> empty");
    }

    @Test
    public void testToClass_object_2_oe() {
//        assertNull(ClassUtils.toClass(null)); // generates warning
        // removed other assertion
        final Class<?>[] castNull = ClassUtils.toClass((Object) null); // == new Object[]{null}
        assertArrayEquals(new Object[]{null}, castNull, "(Object) null -> [null]");
    }

    @Test
    public void testToClass_object_3_oe() {
//        assertNull(ClassUtils.toClass(null)); // generates warning
        // removed other assertion
        final Class<?>[] castNull = ClassUtils.toClass((Object) null); // == new Object[]{null}
        // removed other assertion

        assertSame(ArrayUtils.EMPTY_CLASS_ARRAY, ClassUtils.toClass(ArrayUtils.EMPTY_OBJECT_ARRAY));
    }

    @Test
    public void testToClass_object_4_oe() {
//        assertNull(ClassUtils.toClass(null)); // generates warning
        // removed other assertion
        final Class<?>[] castNull = ClassUtils.toClass((Object) null); // == new Object[]{null}
        // removed other assertion

        // removed other assertion

        assertArrayEquals(new Class[]{String.class, Integer.class, Double.class}, ClassUtils.toClass("Test", Integer.valueOf(1), Double.valueOf(99d)));
    }

    @Test
    public void testToClass_object_5_oe() {
//        assertNull(ClassUtils.toClass(null)); // generates warning
        // removed other assertion
        final Class<?>[] castNull = ClassUtils.toClass((Object) null); // == new Object[]{null}
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertArrayEquals(new Class[]{String.class, null, Double.class}, ClassUtils.toClass("Test", null, Double.valueOf(99d)));
    }

    @Test
    public void testWithInterleavingWhitespace_1_oe() throws ClassNotFoundException {
        assertEquals( int[].class, ClassUtils.getClass( " int [ ] " ) );
    }

    @Test
    public void testWithInterleavingWhitespace_2_oe() throws ClassNotFoundException {
        // removed other assertion
        assertEquals( long[].class, ClassUtils.getClass( "\rlong\t[\n]\r" ) );
    }

    @Test
    public void testWithInterleavingWhitespace_3_oe() throws ClassNotFoundException {
        // removed other assertion
        // removed other assertion
        assertEquals( short[].class, ClassUtils.getClass( "\tshort                \t\t[]" ) );
    }

    @Test
    public void testWithInterleavingWhitespace_4_oe() throws ClassNotFoundException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals( byte[].class, ClassUtils.getClass( "byte[\t\t\n\r]   " ) );
    }

    @Test
    public void testWrappersToPrimitives_1_oe() {
        // an array with classes to test
        final Class<?>[] classes = {
                Boolean.class, Byte.class, Character.class, Short.class,
                Integer.class, Long.class, Float.class, Double.class,
                String.class, ClassUtils.class, null
        };

        final Class<?>[] primitives = ClassUtils.wrappersToPrimitives(classes);
        // now test the result
        assertEquals(classes.length, primitives.length, "Wrong length of result array");
    }

    @Test
    public void testWrappersToPrimitives_2_oe() {
        // an array with classes to test
        final Class<?>[] classes = {
                Boolean.class, Byte.class, Character.class, Short.class,
                Integer.class, Long.class, Float.class, Double.class,
                String.class, ClassUtils.class, null
        };

        final Class<?>[] primitives = ClassUtils.wrappersToPrimitives(classes);
        // now test the result
        // removed other assertion
        for (int i = 0; i < classes.length; i++) {
            final Class<?> expectedPrimitive = ClassUtils.wrapperToPrimitive(classes[i]);
            assertEquals(expectedPrimitive, primitives[i], classes[i] + " -> " + expectedPrimitive);
    }
    }

    @Test
    public void testWrappersToPrimitivesEmpty_1_oe() {
        final Class<?>[] empty = new Class[0];
        assertArrayEquals(empty, ClassUtils.wrappersToPrimitives(empty), "Wrong result for empty input");
    }

    @Test
    public void testWrappersToPrimitivesNull_1_oe() {
//        assertNull("Wrong result for null input", ClassUtils.wrappersToPrimitives(null)); // generates warning
        assertNull(ClassUtils.wrappersToPrimitives((Class<?>[])null),"Wrong result for null input");// equivalent cast assertArrayEquals(ArrayUtils.EMPTY_CLASS_ARRAY,ClassUtils.wrappersToPrimitives(),"empty -> empty");
    }

    @Test
    public void testWrappersToPrimitivesNull_2_oe() {
//        assertNull("Wrong result for null input", ClassUtils.wrappersToPrimitives(null)); // generates warning
        // removed other assertion
        final Class<?>[] castNull = ClassUtils.wrappersToPrimitives((Class<?>) null); // == new Class<?>[]{null}
        assertArrayEquals(new Class<?>[]{null}, castNull, "(Class<?>) null -> [null]");
    }

    @Test
    public void testWrapperToPrimitive_1_oe() {
        // an array with classes to convert
        final Class<?>[] primitives = {
                Boolean.TYPE, Byte.TYPE, Character.TYPE, Short.TYPE,
                Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE
        };
        for (final Class<?> primitive : primitives) {
            final Class<?> wrapperCls = ClassUtils.primitiveToWrapper(primitive);
            assertFalse(wrapperCls.isPrimitive(), "Still primitive");
    }
    }

    @Test
    public void testWrapperToPrimitive_2_oe() {
        // an array with classes to convert
        final Class<?>[] primitives = {
                Boolean.TYPE, Byte.TYPE, Character.TYPE, Short.TYPE,
                Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE
        };
        for (final Class<?> primitive : primitives) {
            final Class<?> wrapperCls = ClassUtils.primitiveToWrapper(primitive);
            // removed other assertion
            assertEquals(primitive, ClassUtils.wrapperToPrimitive(wrapperCls), wrapperCls + " -> " + primitive);
    }
    }

    @Test
    public void testWrapperToPrimitiveNoWrapper_1_oe() {
        assertNull(ClassUtils.wrapperToPrimitive(String.class), "Wrong result for non wrapper class");
    }

    @Test
    public void testWrapperToPrimitiveNull_1_oe() {
        assertNull(ClassUtils.wrapperToPrimitive(null), "Wrong result for null class");
    }

}
