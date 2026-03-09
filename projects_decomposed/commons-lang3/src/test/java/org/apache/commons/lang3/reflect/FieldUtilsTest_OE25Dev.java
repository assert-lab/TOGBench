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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.ArraySorter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.JavaVersion;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.compare.ObjectToStringComparator;
import org.apache.commons.lang3.reflect.testbed.Ambig;
import org.apache.commons.lang3.reflect.testbed.Annotated;
import org.apache.commons.lang3.reflect.testbed.Foo;
import org.apache.commons.lang3.reflect.testbed.PrivatelyShadowedChild;
import org.apache.commons.lang3.reflect.testbed.PublicChild;
import org.apache.commons.lang3.reflect.testbed.PubliclyShadowedChild;
import org.apache.commons.lang3.reflect.testbed.StaticContainer;
import org.apache.commons.lang3.reflect.testbed.StaticContainerChild;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests FieldUtils
 */
public class FieldUtilsTest_OE25Dev {

    private static final String JACOCO_DATA_FIELD_NAME = "$jacocoData";
    static final Integer I0 = Integer.valueOf(0);
    static final Integer I1 = Integer.valueOf(1);
    static final Double D0 = Double.valueOf(0.0);
    static final Double D1 = Double.valueOf(1.0);

    @Annotated
    private PublicChild publicChild;
    private PubliclyShadowedChild publiclyShadowedChild;
    @Annotated
    private PrivatelyShadowedChild privatelyShadowedChild;
    private final Class<? super PublicChild> parentClass = PublicChild.class.getSuperclass();

    @BeforeEach
    public void setUp() {
        StaticContainer.reset();
        publicChild = new PublicChild();
        publiclyShadowedChild = new PubliclyShadowedChild();
        privatelyShadowedChild = new PrivatelyShadowedChild();
    }

    @Test
    public void testConstructor() {
        assertNotNull(new FieldUtils());
        final Constructor<?>[] cons = FieldUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
        assertTrue(Modifier.isPublic(cons[0].getModifiers()));
        assertTrue(Modifier.isPublic(FieldUtils.class.getModifiers()));
        assertFalse(Modifier.isFinal(FieldUtils.class.getModifiers()));
    }

    @Test
    public void testGetField() {
        assertEquals(Foo.class, FieldUtils.getField(PublicChild.class, "VALUE").getDeclaringClass());
        assertEquals(parentClass, FieldUtils.getField(PublicChild.class, "s").getDeclaringClass());
        assertNull(FieldUtils.getField(PublicChild.class, "b"));
        assertNull(FieldUtils.getField(PublicChild.class, "i"));
        assertNull(FieldUtils.getField(PublicChild.class, "d"));
        assertEquals(Foo.class, FieldUtils.getField(PubliclyShadowedChild.class, "VALUE").getDeclaringClass());
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getField(PubliclyShadowedChild.class, "s").getDeclaringClass());
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getField(PubliclyShadowedChild.class, "b").getDeclaringClass());
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getField(PubliclyShadowedChild.class, "i").getDeclaringClass());
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getField(PubliclyShadowedChild.class, "d").getDeclaringClass());
        assertEquals(Foo.class, FieldUtils.getField(PrivatelyShadowedChild.class, "VALUE").getDeclaringClass());
        assertEquals(parentClass, FieldUtils.getField(PrivatelyShadowedChild.class, "s").getDeclaringClass());
        assertNull(FieldUtils.getField(PrivatelyShadowedChild.class, "b"));
        assertNull(FieldUtils.getField(PrivatelyShadowedChild.class, "i"));
        assertNull(FieldUtils.getField(PrivatelyShadowedChild.class, "d"));
    }

    @Test
    public void testGetFieldIllegalArgumentException1() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getField(null, "none"));
    }

    @Test
    public void testGetFieldIllegalArgumentException2() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getField(PublicChild.class, null));
    }

    @Test
    public void testGetFieldIllegalArgumentException3() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getField(PublicChild.class, ""));
    }

    @Test
    public void testGetFieldIllegalArgumentException4() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getField(PublicChild.class, " "));
    }

    @Test
    public void testGetFieldForceAccess() {
        assertEquals(PublicChild.class, FieldUtils.getField(PublicChild.class, "VALUE", true).getDeclaringClass());
        assertEquals(parentClass, FieldUtils.getField(PublicChild.class, "s", true).getDeclaringClass());
        assertEquals(parentClass, FieldUtils.getField(PublicChild.class, "b", true).getDeclaringClass());
        assertEquals(parentClass, FieldUtils.getField(PublicChild.class, "i", true).getDeclaringClass());
        assertEquals(parentClass, FieldUtils.getField(PublicChild.class, "d", true).getDeclaringClass());
        assertEquals(Foo.class, FieldUtils.getField(PubliclyShadowedChild.class, "VALUE", true).getDeclaringClass());
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getField(PubliclyShadowedChild.class, "s", true).getDeclaringClass());
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getField(PubliclyShadowedChild.class, "b", true).getDeclaringClass());
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getField(PubliclyShadowedChild.class, "i", true).getDeclaringClass());
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getField(PubliclyShadowedChild.class, "d", true).getDeclaringClass());
        assertEquals(Foo.class, FieldUtils.getField(PrivatelyShadowedChild.class, "VALUE", true).getDeclaringClass());
        assertEquals(PrivatelyShadowedChild.class, FieldUtils.getField(PrivatelyShadowedChild.class, "s", true).getDeclaringClass());
        assertEquals(PrivatelyShadowedChild.class, FieldUtils.getField(PrivatelyShadowedChild.class, "b", true).getDeclaringClass());
        assertEquals(PrivatelyShadowedChild.class, FieldUtils.getField(PrivatelyShadowedChild.class, "i", true).getDeclaringClass());
        assertEquals(PrivatelyShadowedChild.class, FieldUtils.getField(PrivatelyShadowedChild.class, "d", true).getDeclaringClass());
    }

    @Test
    public void testGetFieldForceAccessIllegalArgumentException1() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getField(null, "none", true));
    }

    @Test
    public void testGetFieldForceAccessIllegalArgumentException2() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getField(PublicChild.class, null, true));
    }

    @Test
    public void testGetFieldForceAccessIllegalArgumentException3() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getField(PublicChild.class, "", true));
    }

    @Test
    public void testGetFieldForceAccessIllegalArgumentException4() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getField(PublicChild.class, " ", true));
    }

    @Test
    public void testGetAllFields() {
        assertArrayEquals(new Field[0], FieldUtils.getAllFields(Object.class));
        final Field[] fieldsNumber = sort(Number.class.getDeclaredFields());
        assertArrayEquals(fieldsNumber, sort(FieldUtils.getAllFields(Number.class)));
        final Field[] fieldsInteger = Integer.class.getDeclaredFields();
        assertArrayEquals(sort(ArrayUtils.addAll(fieldsInteger, fieldsNumber)), sort(FieldUtils.getAllFields(Integer.class)));
        final Field[] allFields = FieldUtils.getAllFields(PublicChild.class);
        // Under Jacoco,0.8.1 and Java 10, the field count is 7.
        int expected = 5;
        for (final Field field : allFields) {
            if (field.getName().equals(JACOCO_DATA_FIELD_NAME)) {
                expected++;
            }
        }
        assertEquals(expected, allFields.length, Arrays.toString(allFields));
    }

    private Field[] sort(final Field[] fields) {
        // Field does not implement Comparable, so we use a KISS solution here.
        return ArraySorter.sort(fields, ObjectToStringComparator.INSTANCE);
    }

    @Test
    public void testGetAllFieldsList() {
        assertEquals(0, FieldUtils.getAllFieldsList(Object.class).size());
        final List<Field> fieldsNumber = Arrays.asList(Number.class.getDeclaredFields());
        assertEquals(fieldsNumber, FieldUtils.getAllFieldsList(Number.class));
        final List<Field> fieldsInteger = Arrays.asList(Integer.class.getDeclaredFields());
        final List<Field> allFieldsInteger = new ArrayList<>(fieldsInteger);
        allFieldsInteger.addAll(fieldsNumber);
        assertEquals(new HashSet(allFieldsInteger), new HashSet(FieldUtils.getAllFieldsList(Integer.class)));
        final List<Field> allFields = FieldUtils.getAllFieldsList(PublicChild.class);
        // Under Jacoco,0.8.1 and Java 10, the field count is 7.
        int expected = 5;
        for (final Field field : allFields) {
            if (field.getName().equals(JACOCO_DATA_FIELD_NAME)) {
                expected++;
            }
        }
        assertEquals(expected, allFields.size(), allFields.toString());

    }

    @Test
    public void testGetFieldsWithAnnotation() throws NoSuchFieldException {
        assertArrayEquals(new Field[0], FieldUtils.getFieldsWithAnnotation(Object.class, Annotated.class));
        final Field[] annotatedFields = sort(new Field[] {
                FieldUtilsTest_OE25Dev.class.getDeclaredField("publicChild"),
                FieldUtilsTest_OE25Dev.class.getDeclaredField("privatelyShadowedChild") });
        assertArrayEquals(annotatedFields,sort(FieldUtils.getFieldsWithAnnotation(FieldUtilsTest_OE25Dev.class,Annotated.class)));
    }

    @Test
    public void testGetFieldsWithAnnotationIllegalArgumentException1() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getFieldsWithAnnotation(FieldUtilsTest_OE25Dev.class, null));
    }

    @Test
    public void testGetFieldsWithAnnotationIllegalArgumentException2() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getFieldsWithAnnotation(null, Annotated.class));
    }

    @Test
    public void testGetFieldsWithAnnotationIllegalArgumentException3() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getFieldsWithAnnotation(null, null));
    }

    @Test
    public void testGetFieldsListWithAnnotation() throws NoSuchFieldException {
        assertEquals(0, FieldUtils.getFieldsListWithAnnotation(Object.class, Annotated.class).size());
        final List<Field> annotatedFields = Arrays.asList(
                FieldUtilsTest_OE25Dev.class.getDeclaredField("publicChild"),
                FieldUtilsTest_OE25Dev.class.getDeclaredField("privatelyShadowedChild")
        );
        final List<Field> fieldUtilsTestAnnotatedFields = FieldUtils.getFieldsListWithAnnotation(FieldUtilsTest_OE25Dev.class, Annotated.class);
        assertEquals(annotatedFields.size(), fieldUtilsTestAnnotatedFields.size());
        assertTrue(fieldUtilsTestAnnotatedFields.contains(annotatedFields.get(0)));
        assertTrue(fieldUtilsTestAnnotatedFields.contains(annotatedFields.get(1)));
    }

    @Test
    public void testGetFieldsListWithAnnotationIllegalArgumentException1() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getFieldsListWithAnnotation(FieldUtilsTest_OE25Dev.class, null));
    }

    @Test
    public void testGetFieldsListWithAnnotationIllegalArgumentException2() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getFieldsListWithAnnotation(null, Annotated.class));
    }

    @Test
    public void testGetFieldsListWithAnnotationIllegalArgumentException3() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getFieldsListWithAnnotation(null, null));
    }

    @Test
    public void testGetDeclaredField() {
        assertNull(FieldUtils.getDeclaredField(PublicChild.class, "VALUE"));
        assertNull(FieldUtils.getDeclaredField(PublicChild.class, "s"));
        assertNull(FieldUtils.getDeclaredField(PublicChild.class, "b"));
        assertNull(FieldUtils.getDeclaredField(PublicChild.class, "i"));
        assertNull(FieldUtils.getDeclaredField(PublicChild.class, "d"));
        assertNull(FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "VALUE"));
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "s").getDeclaringClass());
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "b").getDeclaringClass());
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "i").getDeclaringClass());
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "d").getDeclaringClass());
        assertNull(FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "VALUE"));
        assertNull(FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "s"));
        assertNull(FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "b"));
        assertNull(FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "i"));
        assertNull(FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "d"));
    }

    @Test
    public void testGetDeclaredFieldAccessIllegalArgumentException1() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getDeclaredField(null, "none"));
    }

    @Test
    public void testGetDeclaredFieldAccessIllegalArgumentException2() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getDeclaredField(PublicChild.class, null));
    }

    @Test
    public void testGetDeclaredFieldAccessIllegalArgumentException3() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getDeclaredField(PublicChild.class, ""));
    }

    @Test
    public void testGetDeclaredFieldAccessIllegalArgumentException4() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getDeclaredField(PublicChild.class, " "));
    }

    @Test
    public void testGetDeclaredFieldForceAccess() {
        assertEquals(PublicChild.class, FieldUtils.getDeclaredField(PublicChild.class, "VALUE", true).getDeclaringClass());
        assertNull(FieldUtils.getDeclaredField(PublicChild.class, "s", true));
        assertNull(FieldUtils.getDeclaredField(PublicChild.class, "b", true));
        assertNull(FieldUtils.getDeclaredField(PublicChild.class, "i", true));
        assertNull(FieldUtils.getDeclaredField(PublicChild.class, "d", true));
        assertNull(FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "VALUE", true));
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "s", true).getDeclaringClass());
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "b", true).getDeclaringClass());
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "i", true).getDeclaringClass());
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "d", true).getDeclaringClass());
        assertNull(FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "VALUE", true));
        assertEquals(PrivatelyShadowedChild.class, FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "s", true).getDeclaringClass());
        assertEquals(PrivatelyShadowedChild.class, FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "b", true).getDeclaringClass());
        assertEquals(PrivatelyShadowedChild.class, FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "i", true).getDeclaringClass());
        assertEquals(PrivatelyShadowedChild.class, FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "d", true).getDeclaringClass());
    }

    @Test
    public void testGetDeclaredFieldForceAccessIllegalArgumentException1() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getDeclaredField(null, "none", true));
    }

    @Test
    public void testGetDeclaredFieldForceAccessIllegalArgumentException2() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getDeclaredField(PublicChild.class, null, true));
    }

    @Test
    public void testGetDeclaredFieldForceAccessIllegalArgumentException3() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getDeclaredField(PublicChild.class, "", true));
    }

    @Test
    public void testGetDeclaredFieldForceAccessIllegalArgumentException4() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getDeclaredField(PublicChild.class, " ", true));
    }

    @Test
    public void testReadStaticField() throws Exception {
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(FieldUtils.getField(Foo.class, "VALUE")));
    }

    @Test
    public void testReadStaticFieldIllegalArgumentException1() {
        assertThrows(NullPointerException.class, () -> FieldUtils.readStaticField(null));
    }

    @Test
    public void testReadStaticFieldIllegalArgumentException2() throws Exception {
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(FieldUtils.getField(Foo.class, "VALUE")));
        final Field nonStaticField = FieldUtils.getField(PublicChild.class, "s");
        assumeTrue(nonStaticField != null);
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readStaticField(nonStaticField));
    }

    @Test
    public void testReadStaticFieldForceAccess() throws Exception {
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(FieldUtils.getField(Foo.class, "VALUE")));
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(FieldUtils.getField(PublicChild.class, "VALUE")));
    }

    @Test
    public void testReadStaticFieldForceAccessIllegalArgumentException1() {
        assertThrows(NullPointerException.class, () -> FieldUtils.readStaticField(null, true));
    }

    @Test
    public void testReadStaticFieldForceAccessIllegalArgumentException2() {
        final Field nonStaticField = FieldUtils.getField(PublicChild.class, "s", true);
        assumeTrue(nonStaticField != null);
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readStaticField(nonStaticField));
    }

    @Test
    public void testReadNamedStaticField() throws Exception {
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(Foo.class, "VALUE"));
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(PubliclyShadowedChild.class, "VALUE"));
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(PrivatelyShadowedChild.class, "VALUE"));
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(PublicChild.class, "VALUE"));

        assertThrows(
                NullPointerException.class,
                () -> FieldUtils.readStaticField(null, "none"),
                "null class should cause an IllegalArgumentException");

        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.readStaticField(Foo.class, null),
                "null field name should cause an IllegalArgumentException");

        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.readStaticField(Foo.class, ""),
                "empty field name should cause an IllegalArgumentException");

        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.readStaticField(Foo.class, " "),
                "blank field name should cause an IllegalArgumentException");

        assertThrows(
                NullPointerException.class,
                () -> FieldUtils.readStaticField(Foo.class, "does_not_exist"),
                "a field that doesn't exist should cause an IllegalArgumentException");

        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.readStaticField(PublicChild.class, "s"),
                "non-static field should cause an IllegalArgumentException");
    }

    @Test
    public void testReadNamedStaticFieldForceAccess() throws Exception {
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(Foo.class, "VALUE", true));
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(PubliclyShadowedChild.class, "VALUE", true));
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(PrivatelyShadowedChild.class, "VALUE", true));
        assertEquals("child", FieldUtils.readStaticField(PublicChild.class, "VALUE", true));

        assertThrows(
                NullPointerException.class,
                () -> FieldUtils.readStaticField(null, "none", true),
                "null class should cause an IllegalArgumentException");

        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.readStaticField(Foo.class, null, true),
                "null field name should cause an IllegalArgumentException");

        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.readStaticField(Foo.class, "", true),
                "empty field name should cause an IllegalArgumentException");

        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.readStaticField(Foo.class, " ", true),
                "blank field name should cause an IllegalArgumentException");

        assertThrows(
                NullPointerException.class,
                () -> FieldUtils.readStaticField(Foo.class, "does_not_exist", true),
                "a field that doesn't exist should cause an IllegalArgumentException");

        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.readStaticField(PublicChild.class, "s", false),
                "non-static field should cause an IllegalArgumentException");
    }

    @Test
    public void testReadDeclaredNamedStaticField() throws Exception {
        assertEquals(Foo.VALUE, FieldUtils.readDeclaredStaticField(Foo.class, "VALUE"));
        assertThrows(
                NullPointerException.class, () -> FieldUtils.readDeclaredStaticField(PublicChild.class, "VALUE"));
        assertThrows(
                NullPointerException.class,
                () -> FieldUtils.readDeclaredStaticField(PubliclyShadowedChild.class, "VALUE"));
        assertThrows(
                NullPointerException.class,
                () -> FieldUtils.readDeclaredStaticField(PrivatelyShadowedChild.class, "VALUE"));
    }

    @Test
    public void testReadDeclaredNamedStaticFieldForceAccess() throws Exception {
        assertEquals(Foo.VALUE, FieldUtils.readDeclaredStaticField(Foo.class, "VALUE", true));
        assertEquals("child", FieldUtils.readDeclaredStaticField(PublicChild.class, "VALUE", true));
        assertThrows(
                NullPointerException.class,
                () -> FieldUtils.readDeclaredStaticField(PubliclyShadowedChild.class, "VALUE", true));
        assertThrows(
                NullPointerException.class,
                () -> FieldUtils.readDeclaredStaticField(PrivatelyShadowedChild.class, "VALUE", true));
    }

    @Test
    public void testReadField() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        assertEquals("s", FieldUtils.readField(parentS, publicChild));
        assertEquals("s", FieldUtils.readField(parentS, publiclyShadowedChild));
        assertEquals("s", FieldUtils.readField(parentS, privatelyShadowedChild));
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        assertEquals(Boolean.FALSE, FieldUtils.readField(parentB, publicChild));
        assertEquals(Boolean.FALSE, FieldUtils.readField(parentB, publiclyShadowedChild));
        assertEquals(Boolean.FALSE, FieldUtils.readField(parentB, privatelyShadowedChild));
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        assertEquals(I0, FieldUtils.readField(parentI, publicChild));
        assertEquals(I0, FieldUtils.readField(parentI, publiclyShadowedChild));
        assertEquals(I0, FieldUtils.readField(parentI, privatelyShadowedChild));
        final Field parentD = FieldUtils.getDeclaredField(parentClass, "d", true);
        assertEquals(D0, FieldUtils.readField(parentD, publicChild));
        assertEquals(D0, FieldUtils.readField(parentD, publiclyShadowedChild));
        assertEquals(D0, FieldUtils.readField(parentD, privatelyShadowedChild));

        assertThrows(
                NullPointerException.class,
                () -> FieldUtils.readField(null, publicChild),
                "a null field should cause an IllegalArgumentException");
    }

    @Test
    public void testReadFieldForceAccess() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        parentS.setAccessible(false);
        assertEquals("s", FieldUtils.readField(parentS, publicChild, true));
        assertEquals("s", FieldUtils.readField(parentS, publiclyShadowedChild, true));
        assertEquals("s", FieldUtils.readField(parentS, privatelyShadowedChild, true));
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        parentB.setAccessible(false);
        assertEquals(Boolean.FALSE, FieldUtils.readField(parentB, publicChild, true));
        assertEquals(Boolean.FALSE, FieldUtils.readField(parentB, publiclyShadowedChild, true));
        assertEquals(Boolean.FALSE, FieldUtils.readField(parentB, privatelyShadowedChild, true));
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        parentI.setAccessible(false);
        assertEquals(I0, FieldUtils.readField(parentI, publicChild, true));
        assertEquals(I0, FieldUtils.readField(parentI, publiclyShadowedChild, true));
        assertEquals(I0, FieldUtils.readField(parentI, privatelyShadowedChild, true));
        final Field parentD = FieldUtils.getDeclaredField(parentClass, "d", true);
        parentD.setAccessible(false);
        assertEquals(D0, FieldUtils.readField(parentD, publicChild, true));
        assertEquals(D0, FieldUtils.readField(parentD, publiclyShadowedChild, true));
        assertEquals(D0, FieldUtils.readField(parentD, privatelyShadowedChild, true));

        assertThrows(
                NullPointerException.class,
                () -> FieldUtils.readField(null, publicChild, true),
                "a null field should cause an IllegalArgumentException");
    }

    @Test
    public void testReadNamedField() throws Exception {
        assertEquals("s", FieldUtils.readField(publicChild, "s"));
        assertEquals("ss", FieldUtils.readField(publiclyShadowedChild, "s"));
        assertEquals("s", FieldUtils.readField(privatelyShadowedChild, "s"));

        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.readField(publicChild, null),
                "a null field name should cause an IllegalArgumentException");

        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.readField(publicChild, ""),
                "an empty field name should cause an IllegalArgumentException");

        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.readField(publicChild, " "),
                "a blank field name should cause an IllegalArgumentException");

        assertThrows(
                NullPointerException.class,
                () -> FieldUtils.readField((Object) null, "none"),
                "a null target should cause an IllegalArgumentException");

        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readField(publicChild, "b"));

        assertEquals(Boolean.TRUE, FieldUtils.readField(publiclyShadowedChild, "b"));
        assertThrows( IllegalArgumentException.class, () -> FieldUtils.readField(privatelyShadowedChild, "b"));
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readField(publicChild, "i"));
        assertEquals(I1, FieldUtils.readField(publiclyShadowedChild, "i"));
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readField(privatelyShadowedChild, "i"));
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readField(publicChild, "d"));
        assertEquals(D1, FieldUtils.readField(publiclyShadowedChild, "d"));
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readField(privatelyShadowedChild, "d"));
    }

    @Test
    public void testReadNamedFieldForceAccess() throws Exception {
        assertEquals("s", FieldUtils.readField(publicChild, "s", true));
        assertEquals("ss", FieldUtils.readField(publiclyShadowedChild, "s", true));
        assertEquals("ss", FieldUtils.readField(privatelyShadowedChild, "s", true));
        assertEquals(Boolean.FALSE, FieldUtils.readField(publicChild, "b", true));
        assertEquals(Boolean.TRUE, FieldUtils.readField(publiclyShadowedChild, "b", true));
        assertEquals(Boolean.TRUE, FieldUtils.readField(privatelyShadowedChild, "b", true));
        assertEquals(I0, FieldUtils.readField(publicChild, "i", true));
        assertEquals(I1, FieldUtils.readField(publiclyShadowedChild, "i", true));
        assertEquals(I1, FieldUtils.readField(privatelyShadowedChild, "i", true));
        assertEquals(D0, FieldUtils.readField(publicChild, "d", true));
        assertEquals(D1, FieldUtils.readField(publiclyShadowedChild, "d", true));
        assertEquals(D1, FieldUtils.readField(privatelyShadowedChild, "d", true));

        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.readField(publicChild, null, true),
                "a null field name should cause an IllegalArgumentException");

        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.readField(publicChild, "", true),
                "an empty field name should cause an IllegalArgumentException");

        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.readField(publicChild, " ", true),
                "a blank field name should cause an IllegalArgumentException");

        assertThrows(
                NullPointerException.class,
                () -> FieldUtils.readField((Object) null, "none", true),
                "a null target should cause an IllegalArgumentException");
    }

    @Test
    public void testReadDeclaredNamedField() throws Exception {
        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.readDeclaredField(publicChild, null),
                "a null field name should cause an IllegalArgumentException");

        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.readDeclaredField(publicChild, ""),
                "an empty field name should cause an IllegalArgumentException");

        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.readDeclaredField(publicChild, " "),
                "a blank field name should cause an IllegalArgumentException");

        assertThrows(
                NullPointerException.class,
                () -> FieldUtils.readDeclaredField(null, "none"),
                "a null target should cause an IllegalArgumentException");

        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(publicChild, "s"));
        assertEquals("ss", FieldUtils.readDeclaredField(publiclyShadowedChild, "s"));
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(privatelyShadowedChild, "s"));
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(publicChild, "b"));
        assertEquals(Boolean.TRUE, FieldUtils.readDeclaredField(publiclyShadowedChild, "b"));
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(privatelyShadowedChild, "b"));
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(publicChild, "i"));
        assertEquals(I1, FieldUtils.readDeclaredField(publiclyShadowedChild, "i"));
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(privatelyShadowedChild, "i"));
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(publicChild, "d"));
        assertEquals(D1, FieldUtils.readDeclaredField(publiclyShadowedChild, "d"));
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(privatelyShadowedChild, "d"));
    }

    @Test
    public void testReadDeclaredNamedFieldForceAccess() throws Exception {
        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.readDeclaredField(publicChild, null, true),
                "a null field name should cause an IllegalArgumentException");

        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.readDeclaredField(publicChild, "", true),
                "an empty field name should cause an IllegalArgumentException");

        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.readDeclaredField(publicChild, " ", true),
                "a blank field name should cause an IllegalArgumentException");

        assertThrows(
                NullPointerException.class,
                () -> FieldUtils.readDeclaredField(null, "none", true),
                "a null target should cause an IllegalArgumentException");

        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(publicChild, "s", true));
        assertEquals("ss", FieldUtils.readDeclaredField(publiclyShadowedChild, "s", true));
        assertEquals("ss", FieldUtils.readDeclaredField(privatelyShadowedChild, "s", true));
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(publicChild, "b", true));
        assertEquals(Boolean.TRUE, FieldUtils.readDeclaredField(publiclyShadowedChild, "b", true));
        assertEquals(Boolean.TRUE, FieldUtils.readDeclaredField(privatelyShadowedChild, "b", true));
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(publicChild, "i", true));
        assertEquals(I1, FieldUtils.readDeclaredField(publiclyShadowedChild, "i", true));
        assertEquals(I1, FieldUtils.readDeclaredField(privatelyShadowedChild, "i", true));
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(publicChild, "d", true));
        assertEquals(D1, FieldUtils.readDeclaredField(publiclyShadowedChild, "d", true));
        assertEquals(D1, FieldUtils.readDeclaredField(privatelyShadowedChild, "d", true));
    }

    @Test
    public void testWriteStaticField() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new");
        assertEquals("new", StaticContainer.mutablePublic);
        assertThrows(
                IllegalAccessException.class,
                () -> FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("mutableProtected"), "new"));
        assertThrows(
                IllegalAccessException.class,
                () -> FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("mutablePackage"), "new"));
        assertThrows(
                IllegalAccessException.class,
                () -> FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("mutablePrivate"), "new"));
        assertThrows(
                IllegalAccessException.class,
                () -> FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PUBLIC"), "new"));
        assertThrows(
                IllegalAccessException.class,
                () -> FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PROTECTED"), "new"));
        assertThrows(
                IllegalAccessException.class,
                () -> FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PACKAGE"), "new"));
        assertThrows(
                IllegalAccessException.class,
                () -> FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE"), "new"));
    }

    @Test
    public void testWriteStaticFieldForceAccess() throws Exception {
        Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new", true);
        assertEquals("new", StaticContainer.mutablePublic);
        field = StaticContainer.class.getDeclaredField("mutableProtected");
        FieldUtils.writeStaticField(field, "new", true);
        assertEquals("new", StaticContainer.getMutableProtected());
        field = StaticContainer.class.getDeclaredField("mutablePackage");
        FieldUtils.writeStaticField(field, "new", true);
        assertEquals("new", StaticContainer.getMutablePackage());
        field = StaticContainer.class.getDeclaredField("mutablePrivate");
        FieldUtils.writeStaticField(field, "new", true);
        assertEquals("new", StaticContainer.getMutablePrivate());
        assertThrows(
                IllegalAccessException.class,
                () -> FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PUBLIC"), "new", true));
        assertThrows(
                IllegalAccessException.class,
                () -> FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PROTECTED"), "new", true));
        assertThrows(
                IllegalAccessException.class,
                () -> FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PACKAGE"), "new", true));
        assertThrows(
                IllegalAccessException.class,
                () -> FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE"), "new", true));
    }

    @Test
    public void testWriteNamedStaticField() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new");
        assertEquals("new", StaticContainer.mutablePublic);
        assertThrows(
                NullPointerException.class,
                () -> FieldUtils.writeStaticField(StaticContainerChild.class, "mutableProtected", "new"));
        assertThrows(
                NullPointerException.class,
                () -> FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePackage", "new"));
        assertThrows(
                NullPointerException.class,
                () -> FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePrivate", "new"));
        assertThrows(
                IllegalAccessException.class,
                () -> FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PUBLIC", "new"));
        assertThrows(
                NullPointerException.class,
                () -> FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PROTECTED", "new"));
        assertThrows(
                NullPointerException.class,
                () -> FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PACKAGE", "new"));
        assertThrows(
                NullPointerException.class,
                () -> FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PRIVATE", "new"));
    }

    @Test
    public void testWriteNamedStaticFieldForceAccess() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new", true);
        assertEquals("new", StaticContainer.mutablePublic);
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutableProtected", "new", true);
        assertEquals("new", StaticContainer.getMutableProtected());
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePackage", "new", true);
        assertEquals("new", StaticContainer.getMutablePackage());
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePrivate", "new", true);
        assertEquals("new", StaticContainer.getMutablePrivate());
        assertThrows(
                IllegalAccessException.class,
                () -> FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PUBLIC", "new", true));
        assertThrows(
                IllegalAccessException.class,
                () -> FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PROTECTED", "new", true));
        assertThrows(
                IllegalAccessException.class,
                () -> FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PACKAGE", "new", true));
        assertThrows(
                IllegalAccessException.class,
                () -> FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PRIVATE", "new", true));
    }

    @Test
    public void testWriteDeclaredNamedStaticField() throws Exception {
        FieldUtils.writeStaticField(StaticContainer.class, "mutablePublic", "new");
        assertEquals("new", StaticContainer.mutablePublic);
        assertThrows(
                NullPointerException.class,
                () -> FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutableProtected", "new"));
        assertThrows(
                NullPointerException.class,
                () -> FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePackage", "new"));
        assertThrows(
                NullPointerException.class,
                () -> FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePrivate", "new"));
        assertThrows(
                IllegalAccessException.class,
                () -> FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PUBLIC", "new"));
        assertThrows(
                NullPointerException.class,
                () -> FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PROTECTED", "new"));
        assertThrows(
                NullPointerException.class,
                () -> FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PACKAGE", "new"));
        assertThrows(
                NullPointerException.class,
                () -> FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PRIVATE", "new"));
    }

    @Test
    public void testWriteDeclaredNamedStaticFieldForceAccess() throws Exception {
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePublic", "new", true);
        assertEquals("new", StaticContainer.mutablePublic);
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutableProtected", "new", true);
        assertEquals("new", StaticContainer.getMutableProtected());
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePackage", "new", true);
        assertEquals("new", StaticContainer.getMutablePackage());
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePrivate", "new", true);
        assertEquals("new", StaticContainer.getMutablePrivate());
        assertThrows(
                IllegalAccessException.class,
                () -> FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PUBLIC", "new", true));
        assertThrows(
                IllegalAccessException.class,
                () -> FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PROTECTED", "new", true));
        assertThrows(
                IllegalAccessException.class,
                () -> FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PACKAGE", "new", true));
        assertThrows(
                IllegalAccessException.class,
                () -> FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PRIVATE", "new", true));
    }

    @Test
    public void testWriteField() throws Exception {
        final Field field = parentClass.getDeclaredField("s");
        FieldUtils.writeField(field, publicChild, "S");
        assertEquals("S", field.get(publicChild));
        assertThrows(
                IllegalAccessException.class,
                () -> FieldUtils.writeField(parentClass.getDeclaredField("b"), publicChild, Boolean.TRUE));
        assertThrows(
                IllegalAccessException.class,
                () -> FieldUtils.writeField(parentClass.getDeclaredField("i"), publicChild, Integer.valueOf(Integer.MAX_VALUE)));
        assertThrows(
                IllegalAccessException.class,
                () -> FieldUtils.writeField(parentClass.getDeclaredField("d"), publicChild, Double.valueOf(Double.MAX_VALUE)));
    }

    @Test
    public void testWriteFieldForceAccess() throws Exception {
        Field field = parentClass.getDeclaredField("s");
        FieldUtils.writeField(field, publicChild, "S", true);
        assertEquals("S", field.get(publicChild));
        field = parentClass.getDeclaredField("b");
        FieldUtils.writeField(field, publicChild, Boolean.TRUE, true);
        assertEquals(Boolean.TRUE, field.get(publicChild));
        field = parentClass.getDeclaredField("i");
        FieldUtils.writeField(field, publicChild, Integer.valueOf(Integer.MAX_VALUE), true);
        assertEquals(Integer.valueOf(Integer.MAX_VALUE), field.get(publicChild));
        field = parentClass.getDeclaredField("d");
        FieldUtils.writeField(field, publicChild, Double.valueOf(Double.MAX_VALUE), true);
        assertEquals(Double.valueOf(Double.MAX_VALUE), field.get(publicChild));
    }

    @Test
    public void testWriteNamedField() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");
        assertEquals("S", FieldUtils.readField(publicChild, "s"));
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.writeField(publicChild, "b", Boolean.TRUE));
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.writeField(publicChild, "i", Integer.valueOf(1)));
        assertThrows(
                IllegalArgumentException.class, () -> FieldUtils.writeField(publicChild, "d", Double.valueOf(1.0)));

        FieldUtils.writeField(publiclyShadowedChild, "s", "S");
        assertEquals("S", FieldUtils.readField(publiclyShadowedChild, "s"));
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE);
        assertEquals(Boolean.FALSE, FieldUtils.readField(publiclyShadowedChild, "b"));
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0));
        assertEquals(Integer.valueOf(0), FieldUtils.readField(publiclyShadowedChild, "i"));
        FieldUtils.writeField(publiclyShadowedChild, "d", Double.valueOf(0.0));
        assertEquals(Double.valueOf(0.0), FieldUtils.readField(publiclyShadowedChild, "d"));

        FieldUtils.writeField(privatelyShadowedChild, "s", "S");
        assertEquals("S", FieldUtils.readField(privatelyShadowedChild, "s"));
        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.writeField(privatelyShadowedChild, "b", Boolean.TRUE));
        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.writeField(privatelyShadowedChild, "i", Integer.valueOf(1)));
        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.writeField(privatelyShadowedChild, "d", Double.valueOf(1.0)));
    }

    @Test
    public void testWriteNamedFieldForceAccess() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S", true);
        assertEquals("S", FieldUtils.readField(publicChild, "s", true));
        FieldUtils.writeField(publicChild, "b", Boolean.TRUE, true);
        assertEquals(Boolean.TRUE, FieldUtils.readField(publicChild, "b", true));
        FieldUtils.writeField(publicChild, "i", Integer.valueOf(1), true);
        assertEquals(Integer.valueOf(1), FieldUtils.readField(publicChild, "i", true));
        FieldUtils.writeField(publicChild, "d", Double.valueOf(1.0), true);
        assertEquals(Double.valueOf(1.0), FieldUtils.readField(publicChild, "d", true));

        FieldUtils.writeField(publiclyShadowedChild, "s", "S", true);
        assertEquals("S", FieldUtils.readField(publiclyShadowedChild, "s", true));
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        assertEquals(Boolean.FALSE, FieldUtils.readField(publiclyShadowedChild, "b", true));
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0), true);
        assertEquals(Integer.valueOf(0), FieldUtils.readField(publiclyShadowedChild, "i", true));
        FieldUtils.writeField(publiclyShadowedChild, "d", Double.valueOf(0.0), true);
        assertEquals(Double.valueOf(0.0), FieldUtils.readField(publiclyShadowedChild, "d", true));

        FieldUtils.writeField(privatelyShadowedChild, "s", "S", true);
        assertEquals("S", FieldUtils.readField(privatelyShadowedChild, "s", true));
        FieldUtils.writeField(privatelyShadowedChild, "b", Boolean.FALSE, true);
        assertEquals(Boolean.FALSE, FieldUtils.readField(privatelyShadowedChild, "b", true));
        FieldUtils.writeField(privatelyShadowedChild, "i", Integer.valueOf(0), true);
        assertEquals(Integer.valueOf(0), FieldUtils.readField(privatelyShadowedChild, "i", true));
        FieldUtils.writeField(privatelyShadowedChild, "d", Double.valueOf(0.0), true);
        assertEquals(Double.valueOf(0.0), FieldUtils.readField(privatelyShadowedChild, "d", true));
    }

    @Test
    public void testWriteDeclaredNamedField() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.writeDeclaredField(publicChild, "s", "S"));
        assertThrows(
                IllegalArgumentException.class, () -> FieldUtils.writeDeclaredField(publicChild, "b", Boolean.TRUE));
        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.writeDeclaredField(publicChild, "i", Integer.valueOf(1)));
        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.writeDeclaredField(publicChild, "d", Double.valueOf(1.0)));

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S");
        assertEquals("S", FieldUtils.readDeclaredField(publiclyShadowedChild, "s"));
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE);
        assertEquals(Boolean.FALSE, FieldUtils.readDeclaredField(publiclyShadowedChild, "b"));
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0));
        assertEquals(Integer.valueOf(0), FieldUtils.readDeclaredField(publiclyShadowedChild, "i"));
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "d", Double.valueOf(0.0));
        assertEquals(Double.valueOf(0.0), FieldUtils.readDeclaredField(publiclyShadowedChild, "d"));

        assertThrows(
                IllegalArgumentException.class, () -> FieldUtils.writeDeclaredField(privatelyShadowedChild, "s", "S"));
        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.writeDeclaredField(privatelyShadowedChild, "b", Boolean.TRUE));
        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.writeDeclaredField(privatelyShadowedChild, "i", Integer.valueOf(1)));
        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.writeDeclaredField(privatelyShadowedChild, "d", Double.valueOf(1.0)));
    }

    @Test
    public void testWriteDeclaredNamedFieldForceAccess() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.writeDeclaredField(publicChild, "s", "S", true));
        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.writeDeclaredField(publicChild, "b", Boolean.TRUE, true));
        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.writeDeclaredField(publicChild, "i", Integer.valueOf(1), true));
        assertThrows(
                IllegalArgumentException.class,
                () -> FieldUtils.writeDeclaredField(publicChild, "d", Double.valueOf(1.0), true));

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S", true);
        assertEquals("S", FieldUtils.readDeclaredField(publiclyShadowedChild, "s", true));
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        assertEquals(Boolean.FALSE, FieldUtils.readDeclaredField(publiclyShadowedChild, "b", true));
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0), true);
        assertEquals(Integer.valueOf(0), FieldUtils.readDeclaredField(publiclyShadowedChild, "i", true));
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "d", Double.valueOf(0.0), true);
        assertEquals(Double.valueOf(0.0), FieldUtils.readDeclaredField(publiclyShadowedChild, "d", true));

        FieldUtils.writeDeclaredField(privatelyShadowedChild, "s", "S", true);
        assertEquals("S", FieldUtils.readDeclaredField(privatelyShadowedChild, "s", true));
        FieldUtils.writeDeclaredField(privatelyShadowedChild, "b", Boolean.FALSE, true);
        assertEquals(Boolean.FALSE, FieldUtils.readDeclaredField(privatelyShadowedChild, "b", true));
        FieldUtils.writeDeclaredField(privatelyShadowedChild, "i", Integer.valueOf(0), true);
        assertEquals(Integer.valueOf(0), FieldUtils.readDeclaredField(privatelyShadowedChild, "i", true));
        FieldUtils.writeDeclaredField(privatelyShadowedChild, "d", Double.valueOf(0.0), true);
        assertEquals(Double.valueOf(0.0), FieldUtils.readDeclaredField(privatelyShadowedChild, "d", true));
    }

    @Test
    public void testAmbig() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getField(Ambig.class, "VALUE"));
    }

    @Test
    public void testRemoveFinalModifier() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE_2");
        assertFalse(field.isAccessible());
        assertTrue(Modifier.isFinal(field.getModifiers()));
        callRemoveFinalModifierCheckForException(field, true);
        if (SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_11)) {
            assertFalse(Modifier.isFinal(field.getModifiers()));
            assertFalse(field.isAccessible());
        }
    }

    @Test
    public void testRemoveFinalModifierWithAccess() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE_2");
        assertFalse(field.isAccessible());
        assertTrue(Modifier.isFinal(field.getModifiers()));
        callRemoveFinalModifierCheckForException(field, true);
        if (SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_11)) {
            assertFalse(Modifier.isFinal(field.getModifiers()));
            assertFalse(field.isAccessible());
        }
    }

    @Test
    public void testRemoveFinalModifierWithoutAccess() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE_2");
        assertFalse(field.isAccessible());
        assertTrue(Modifier.isFinal(field.getModifiers()));
        callRemoveFinalModifierCheckForException(field, false);
        if (SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_11)) {
            assertTrue(Modifier.isFinal(field.getModifiers()));
            assertFalse(field.isAccessible());
        }
    }

    @Test
    public void testRemoveFinalModifierAccessNotNeeded() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PACKAGE");
        assertFalse(field.isAccessible());
        assertTrue(Modifier.isFinal(field.getModifiers()));
        callRemoveFinalModifierCheckForException(field, false);
        if (SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_11)) {
            assertTrue(Modifier.isFinal(field.getModifiers()));
            assertFalse(field.isAccessible());
        }
    }

    /**
     * Read the {@code @deprecated} notice on
     * {@link FieldUtils#removeFinalModifier(Field, boolean)}.
     *
     * @param field {@link Field} to be curried into
     *              {@link FieldUtils#removeFinalModifier(Field, boolean)}.
     * @param forceAccess {@link Boolean} to be curried into
     *              {@link FieldUtils#removeFinalModifier(Field, boolean)}.
     */
    private void callRemoveFinalModifierCheckForException(final Field field, final Boolean forceAccess) {
        try {
            FieldUtils.removeFinalModifier(field, forceAccess);
        } catch (final UnsupportedOperationException exception) {
            if (SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_12)) {
                assertTrue(exception.getCause() instanceof NoSuchFieldException);
            } else {
                fail("No exception should be thrown for java prior to 12.0");
            }
        }
    }

    @Test
    public void testConstructor_1_oe() {
        assertNotNull(new FieldUtils());
    }

    @Test
    public void testConstructor_2_oe() {
        final Constructor<?>[] cons = FieldUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
    }

    @Test
    public void testConstructor_3_oe() {
        final Constructor<?>[] cons = FieldUtils.class.getDeclaredConstructors();
        assertTrue(Modifier.isPublic(cons[0].getModifiers()));
    }

    @Test
    public void testConstructor_4_oe() {
        final Constructor<?>[] cons = FieldUtils.class.getDeclaredConstructors();
        assertTrue(Modifier.isPublic(FieldUtils.class.getModifiers()));
    }

    @Test
    public void testConstructor_5_oe() {
        final Constructor<?>[] cons = FieldUtils.class.getDeclaredConstructors();
        assertFalse(Modifier.isFinal(FieldUtils.class.getModifiers()));
    }

    @Test
    public void testGetField_1_oe() {
        assertEquals(Foo.class, FieldUtils.getField(PublicChild.class, "VALUE").getDeclaringClass());
    }

    @Test
    public void testGetField_2_oe() {
        assertEquals(parentClass, FieldUtils.getField(PublicChild.class, "s").getDeclaringClass());
    }

    @Test
    public void testGetField_3_oe() {
        assertNull(FieldUtils.getField(PublicChild.class, "b"));
    }

    @Test
    public void testGetField_4_oe() {
        assertNull(FieldUtils.getField(PublicChild.class, "i"));
    }

    @Test
    public void testGetField_5_oe() {
        assertNull(FieldUtils.getField(PublicChild.class, "d"));
    }

    @Test
    public void testGetField_6_oe() {
        assertEquals(Foo.class, FieldUtils.getField(PubliclyShadowedChild.class, "VALUE").getDeclaringClass());
    }

    @Test
    public void testGetField_7_oe() {
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getField(PubliclyShadowedChild.class, "s").getDeclaringClass());
    }

    @Test
    public void testGetField_8_oe() {
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getField(PubliclyShadowedChild.class, "b").getDeclaringClass());
    }

    @Test
    public void testGetField_9_oe() {
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getField(PubliclyShadowedChild.class, "i").getDeclaringClass());
    }

    @Test
    public void testGetField_10_oe() {
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getField(PubliclyShadowedChild.class, "d").getDeclaringClass());
    }

    @Test
    public void testGetField_11_oe() {
        assertEquals(Foo.class, FieldUtils.getField(PrivatelyShadowedChild.class, "VALUE").getDeclaringClass());
    }

    @Test
    public void testGetField_12_oe() {
        assertEquals(parentClass, FieldUtils.getField(PrivatelyShadowedChild.class, "s").getDeclaringClass());
    }

    @Test
    public void testGetField_13_oe() {
        assertNull(FieldUtils.getField(PrivatelyShadowedChild.class, "b"));
    }

    @Test
    public void testGetField_14_oe() {
        assertNull(FieldUtils.getField(PrivatelyShadowedChild.class, "i"));
    }

    @Test
    public void testGetField_15_oe() {
        assertNull(FieldUtils.getField(PrivatelyShadowedChild.class, "d"));
    }

    @Test
    public void testGetFieldIllegalArgumentException1_1_oe() throws Exception {
        try {
    FieldUtils.getField(null, "none");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetFieldIllegalArgumentException2_1_oe() throws Exception {
        try {
    FieldUtils.getField(PublicChild.class, null);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetFieldIllegalArgumentException3_1_oe() throws Exception {
        try {
    FieldUtils.getField(PublicChild.class, "");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetFieldIllegalArgumentException4_1_oe() throws Exception {
        try {
    FieldUtils.getField(PublicChild.class, " ");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetFieldForceAccess_1_oe() {
        assertEquals(PublicChild.class, FieldUtils.getField(PublicChild.class, "VALUE", true).getDeclaringClass());
    }

    @Test
    public void testGetFieldForceAccess_2_oe() {
        assertEquals(parentClass, FieldUtils.getField(PublicChild.class, "s", true).getDeclaringClass());
    }

    @Test
    public void testGetFieldForceAccess_3_oe() {
        assertEquals(parentClass, FieldUtils.getField(PublicChild.class, "b", true).getDeclaringClass());
    }

    @Test
    public void testGetFieldForceAccess_4_oe() {
        assertEquals(parentClass, FieldUtils.getField(PublicChild.class, "i", true).getDeclaringClass());
    }

    @Test
    public void testGetFieldForceAccess_5_oe() {
        assertEquals(parentClass, FieldUtils.getField(PublicChild.class, "d", true).getDeclaringClass());
    }

    @Test
    public void testGetFieldForceAccess_6_oe() {
        assertEquals(Foo.class, FieldUtils.getField(PubliclyShadowedChild.class, "VALUE", true).getDeclaringClass());
    }

    @Test
    public void testGetFieldForceAccess_7_oe() {
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getField(PubliclyShadowedChild.class, "s", true).getDeclaringClass());
    }

    @Test
    public void testGetFieldForceAccess_8_oe() {
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getField(PubliclyShadowedChild.class, "b", true).getDeclaringClass());
    }

    @Test
    public void testGetFieldForceAccess_9_oe() {
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getField(PubliclyShadowedChild.class, "i", true).getDeclaringClass());
    }

    @Test
    public void testGetFieldForceAccess_10_oe() {
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getField(PubliclyShadowedChild.class, "d", true).getDeclaringClass());
    }

    @Test
    public void testGetFieldForceAccess_11_oe() {
        assertEquals(Foo.class, FieldUtils.getField(PrivatelyShadowedChild.class, "VALUE", true).getDeclaringClass());
    }

    @Test
    public void testGetFieldForceAccess_12_oe() {
        assertEquals(PrivatelyShadowedChild.class, FieldUtils.getField(PrivatelyShadowedChild.class, "s", true).getDeclaringClass());
    }

    @Test
    public void testGetFieldForceAccess_13_oe() {
        assertEquals(PrivatelyShadowedChild.class, FieldUtils.getField(PrivatelyShadowedChild.class, "b", true).getDeclaringClass());
    }

    @Test
    public void testGetFieldForceAccess_14_oe() {
        assertEquals(PrivatelyShadowedChild.class, FieldUtils.getField(PrivatelyShadowedChild.class, "i", true).getDeclaringClass());
    }

    @Test
    public void testGetFieldForceAccess_15_oe() {
        assertEquals(PrivatelyShadowedChild.class, FieldUtils.getField(PrivatelyShadowedChild.class, "d", true).getDeclaringClass());
    }

    @Test
    public void testGetFieldForceAccessIllegalArgumentException1_1_oe() throws Exception {
        try {
    FieldUtils.getField(null, "none", true);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetFieldForceAccessIllegalArgumentException2_1_oe() throws Exception {
        try {
    FieldUtils.getField(PublicChild.class, null, true);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetFieldForceAccessIllegalArgumentException3_1_oe() throws Exception {
        try {
    FieldUtils.getField(PublicChild.class, "", true);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetFieldForceAccessIllegalArgumentException4_1_oe() throws Exception {
        try {
    FieldUtils.getField(PublicChild.class, " ", true);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetAllFields_1_oe() {
        assertArrayEquals(new Field[0], FieldUtils.getAllFields(Object.class));
    }

    @Test
    public void testGetAllFields_2_oe() {
        final Field[] fieldsNumber = sort(Number.class.getDeclaredFields());
        assertArrayEquals(fieldsNumber, sort(FieldUtils.getAllFields(Number.class)));
    }

    @Test
    public void testGetAllFields_3_oe() {
        final Field[] fieldsNumber = sort(Number.class.getDeclaredFields());
        final Field[] fieldsInteger = Integer.class.getDeclaredFields();
        assertArrayEquals(sort(ArrayUtils.addAll(fieldsInteger, fieldsNumber)), sort(FieldUtils.getAllFields(Integer.class)));
    }

    @Test
    public void testGetAllFields_4_oe() {
        final Field[] fieldsNumber = sort(Number.class.getDeclaredFields());
        final Field[] fieldsInteger = Integer.class.getDeclaredFields();
        final Field[] allFields = FieldUtils.getAllFields(PublicChild.class);
        int expected = 5;
        for (final Field field : allFields) {
            if (field.getName().equals(JACOCO_DATA_FIELD_NAME)) {
                expected++;
            }
        }
        assertEquals(expected, allFields.length, Arrays.toString(allFields));
    }

    @Test
    public void testGetAllFieldsList_1_oe() {
        assertEquals(0, FieldUtils.getAllFieldsList(Object.class).size());
    }

    @Test
    public void testGetAllFieldsList_2_oe() {
        final List<Field> fieldsNumber = Arrays.asList(Number.class.getDeclaredFields());
        assertEquals(fieldsNumber, FieldUtils.getAllFieldsList(Number.class));
    }

    @Test
    public void testGetAllFieldsList_3_oe() {
        final List<Field> fieldsNumber = Arrays.asList(Number.class.getDeclaredFields());
        final List<Field> fieldsInteger = Arrays.asList(Integer.class.getDeclaredFields());
        final List<Field> allFieldsInteger = new ArrayList<>(fieldsInteger);
        allFieldsInteger.addAll(fieldsNumber);
        assertEquals(new HashSet(allFieldsInteger), new HashSet(FieldUtils.getAllFieldsList(Integer.class)));
    }

    @Test
    public void testGetAllFieldsList_4_oe() {
        final List<Field> fieldsNumber = Arrays.asList(Number.class.getDeclaredFields());
        final List<Field> fieldsInteger = Arrays.asList(Integer.class.getDeclaredFields());
        final List<Field> allFieldsInteger = new ArrayList<>(fieldsInteger);
        allFieldsInteger.addAll(fieldsNumber);
        final List<Field> allFields = FieldUtils.getAllFieldsList(PublicChild.class);
        int expected = 5;
        for (final Field field : allFields) {
            if (field.getName().equals(JACOCO_DATA_FIELD_NAME)) {
                expected++;
            }
        }
        assertEquals(expected, allFields.size(), allFields.toString());
    }

    @Test
    public void testGetFieldsWithAnnotation_1_oe() throws NoSuchFieldException {
        assertArrayEquals(new Field[0], FieldUtils.getFieldsWithAnnotation(Object.class, Annotated.class));
    }

    @Test
    public void testGetFieldsWithAnnotation_2_oe() throws NoSuchFieldException {
        final Field[] annotatedFields = sort(new Field[] {
                FieldUtilsTest.class.getDeclaredField("publicChild"),
                FieldUtilsTest.class.getDeclaredField("privatelyShadowedChild") });
        assertArrayEquals(annotatedFields,sort(FieldUtils.getFieldsWithAnnotation(FieldUtilsTest.class,Annotated.class)));
    }

    @Test
    public void testGetFieldsWithAnnotationIllegalArgumentException1_1_oe() throws Exception {
        try {
    FieldUtils.getFieldsWithAnnotation(FieldUtilsTest.class, null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetFieldsWithAnnotationIllegalArgumentException2_1_oe() throws Exception {
        try {
    FieldUtils.getFieldsWithAnnotation(null, Annotated.class);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetFieldsWithAnnotationIllegalArgumentException3_1_oe() throws Exception {
        try {
    FieldUtils.getFieldsWithAnnotation(null, null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetFieldsListWithAnnotation_1_oe() throws NoSuchFieldException {
        assertEquals(0, FieldUtils.getFieldsListWithAnnotation(Object.class, Annotated.class).size());
    }

    @Test
    public void testGetFieldsListWithAnnotation_2_oe() throws NoSuchFieldException {
        final List<Field> annotatedFields = Arrays.asList(
                FieldUtilsTest.class.getDeclaredField("publicChild"),
                FieldUtilsTest.class.getDeclaredField("privatelyShadowedChild")
        );
        final List<Field> fieldUtilsTestAnnotatedFields = FieldUtils.getFieldsListWithAnnotation(FieldUtilsTest.class, Annotated.class);
        assertEquals(annotatedFields.size(), fieldUtilsTestAnnotatedFields.size());
    }

    @Test
    public void testGetFieldsListWithAnnotation_3_oe() throws NoSuchFieldException {
        final List<Field> annotatedFields = Arrays.asList(
                FieldUtilsTest.class.getDeclaredField("publicChild"),
                FieldUtilsTest.class.getDeclaredField("privatelyShadowedChild")
        );
        final List<Field> fieldUtilsTestAnnotatedFields = FieldUtils.getFieldsListWithAnnotation(FieldUtilsTest.class, Annotated.class);
        assertTrue(fieldUtilsTestAnnotatedFields.contains(annotatedFields.get(0)));
    }

    @Test
    public void testGetFieldsListWithAnnotation_4_oe() throws NoSuchFieldException {
        final List<Field> annotatedFields = Arrays.asList(
                FieldUtilsTest.class.getDeclaredField("publicChild"),
                FieldUtilsTest.class.getDeclaredField("privatelyShadowedChild")
        );
        final List<Field> fieldUtilsTestAnnotatedFields = FieldUtils.getFieldsListWithAnnotation(FieldUtilsTest.class, Annotated.class);
        assertTrue(fieldUtilsTestAnnotatedFields.contains(annotatedFields.get(1)));
    }

    @Test
    public void testGetFieldsListWithAnnotationIllegalArgumentException1_1_oe() throws Exception {
        try {
    FieldUtils.getFieldsListWithAnnotation(FieldUtilsTest.class, null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetFieldsListWithAnnotationIllegalArgumentException2_1_oe() throws Exception {
        try {
    FieldUtils.getFieldsListWithAnnotation(null, Annotated.class);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetFieldsListWithAnnotationIllegalArgumentException3_1_oe() throws Exception {
        try {
    FieldUtils.getFieldsListWithAnnotation(null, null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetDeclaredField_1_oe() {
        assertNull(FieldUtils.getDeclaredField(PublicChild.class, "VALUE"));
    }

    @Test
    public void testGetDeclaredField_2_oe() {
        assertNull(FieldUtils.getDeclaredField(PublicChild.class, "s"));
    }

    @Test
    public void testGetDeclaredField_3_oe() {
        assertNull(FieldUtils.getDeclaredField(PublicChild.class, "b"));
    }

    @Test
    public void testGetDeclaredField_4_oe() {
        assertNull(FieldUtils.getDeclaredField(PublicChild.class, "i"));
    }

    @Test
    public void testGetDeclaredField_5_oe() {
        assertNull(FieldUtils.getDeclaredField(PublicChild.class, "d"));
    }

    @Test
    public void testGetDeclaredField_6_oe() {
        assertNull(FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "VALUE"));
    }

    @Test
    public void testGetDeclaredField_7_oe() {
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "s").getDeclaringClass());
    }

    @Test
    public void testGetDeclaredField_8_oe() {
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "b").getDeclaringClass());
    }

    @Test
    public void testGetDeclaredField_9_oe() {
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "i").getDeclaringClass());
    }

    @Test
    public void testGetDeclaredField_10_oe() {
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "d").getDeclaringClass());
    }

    @Test
    public void testGetDeclaredField_11_oe() {
        assertNull(FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "VALUE"));
    }

    @Test
    public void testGetDeclaredField_12_oe() {
        assertNull(FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "s"));
    }

    @Test
    public void testGetDeclaredField_13_oe() {
        assertNull(FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "b"));
    }

    @Test
    public void testGetDeclaredField_14_oe() {
        assertNull(FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "i"));
    }

    @Test
    public void testGetDeclaredField_15_oe() {
        assertNull(FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "d"));
    }

    @Test
    public void testGetDeclaredFieldAccessIllegalArgumentException1_1_oe() throws Exception {
        try {
    FieldUtils.getDeclaredField(null, "none");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetDeclaredFieldAccessIllegalArgumentException2_1_oe() throws Exception {
        try {
    FieldUtils.getDeclaredField(PublicChild.class, null);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetDeclaredFieldAccessIllegalArgumentException3_1_oe() throws Exception {
        try {
    FieldUtils.getDeclaredField(PublicChild.class, "");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetDeclaredFieldAccessIllegalArgumentException4_1_oe() throws Exception {
        try {
    FieldUtils.getDeclaredField(PublicChild.class, " ");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetDeclaredFieldForceAccess_1_oe() {
        assertEquals(PublicChild.class, FieldUtils.getDeclaredField(PublicChild.class, "VALUE", true).getDeclaringClass());
    }

    @Test
    public void testGetDeclaredFieldForceAccess_2_oe() {
        assertNull(FieldUtils.getDeclaredField(PublicChild.class, "s", true));
    }

    @Test
    public void testGetDeclaredFieldForceAccess_3_oe() {
        assertNull(FieldUtils.getDeclaredField(PublicChild.class, "b", true));
    }

    @Test
    public void testGetDeclaredFieldForceAccess_4_oe() {
        assertNull(FieldUtils.getDeclaredField(PublicChild.class, "i", true));
    }

    @Test
    public void testGetDeclaredFieldForceAccess_5_oe() {
        assertNull(FieldUtils.getDeclaredField(PublicChild.class, "d", true));
    }

    @Test
    public void testGetDeclaredFieldForceAccess_6_oe() {
        assertNull(FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "VALUE", true));
    }

    @Test
    public void testGetDeclaredFieldForceAccess_7_oe() {
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "s", true).getDeclaringClass());
    }

    @Test
    public void testGetDeclaredFieldForceAccess_8_oe() {
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "b", true).getDeclaringClass());
    }

    @Test
    public void testGetDeclaredFieldForceAccess_9_oe() {
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "i", true).getDeclaringClass());
    }

    @Test
    public void testGetDeclaredFieldForceAccess_10_oe() {
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "d", true).getDeclaringClass());
    }

    @Test
    public void testGetDeclaredFieldForceAccess_11_oe() {
        assertNull(FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "VALUE", true));
    }

    @Test
    public void testGetDeclaredFieldForceAccess_12_oe() {
        assertEquals(PrivatelyShadowedChild.class, FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "s", true).getDeclaringClass());
    }

    @Test
    public void testGetDeclaredFieldForceAccess_13_oe() {
        assertEquals(PrivatelyShadowedChild.class, FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "b", true).getDeclaringClass());
    }

    @Test
    public void testGetDeclaredFieldForceAccess_14_oe() {
        assertEquals(PrivatelyShadowedChild.class, FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "i", true).getDeclaringClass());
    }

    @Test
    public void testGetDeclaredFieldForceAccess_15_oe() {
        assertEquals(PrivatelyShadowedChild.class, FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "d", true).getDeclaringClass());
    }

    @Test
    public void testGetDeclaredFieldForceAccessIllegalArgumentException1_1_oe() throws Exception {
        try {
    FieldUtils.getDeclaredField(null, "none", true);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testGetDeclaredFieldForceAccessIllegalArgumentException2_1_oe() throws Exception {
        try {
    FieldUtils.getDeclaredField(PublicChild.class, null, true);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetDeclaredFieldForceAccessIllegalArgumentException3_1_oe() throws Exception {
        try {
    FieldUtils.getDeclaredField(PublicChild.class, "", true);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetDeclaredFieldForceAccessIllegalArgumentException4_1_oe() throws Exception {
        try {
    FieldUtils.getDeclaredField(PublicChild.class, " ", true);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadStaticField_1_oe() throws Exception {
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(FieldUtils.getField(Foo.class, "VALUE")));
    }

    @Test
    public void testReadStaticFieldIllegalArgumentException1_1_oe() throws Exception {
        try {
    FieldUtils.readStaticField(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadStaticFieldIllegalArgumentException2_1_oe() throws Exception {
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(FieldUtils.getField(Foo.class, "VALUE")));
    }

    @Test
    public void testReadStaticFieldIllegalArgumentException2_2_oe() throws Exception {
        final Field nonStaticField = FieldUtils.getField(PublicChild.class, "s");
        assumeTrue(nonStaticField != null);
        try {
    FieldUtils.readStaticField(nonStaticField);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadStaticFieldForceAccess_1_oe() throws Exception {
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(FieldUtils.getField(Foo.class, "VALUE")));
    }

    @Test
    public void testReadStaticFieldForceAccess_2_oe() throws Exception {
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(FieldUtils.getField(PublicChild.class, "VALUE")));
    }

    @Test
    public void testReadStaticFieldForceAccessIllegalArgumentException1_1_oe() throws Exception {
        try {
    FieldUtils.readStaticField(null, true);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadStaticFieldForceAccessIllegalArgumentException2_1_oe() throws Exception {
        final Field nonStaticField = FieldUtils.getField(PublicChild.class, "s", true);
        assumeTrue(nonStaticField != null);
        try {
    FieldUtils.readStaticField(nonStaticField);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedStaticField_1_oe() throws Exception {
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(Foo.class, "VALUE"));
    }

    @Test
    public void testReadNamedStaticField_2_oe() throws Exception {
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(PubliclyShadowedChild.class, "VALUE"));
    }

    @Test
    public void testReadNamedStaticField_3_oe() throws Exception {
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(PrivatelyShadowedChild.class, "VALUE"));
    }

    @Test
    public void testReadNamedStaticField_4_oe() throws Exception {
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(PublicChild.class, "VALUE"));
    }

    @Test
    public void testReadNamedStaticField_5_oe() throws Exception {

        try {
    FieldUtils.readStaticField(null, "none");
    fail("NullPointerException: null class should cause an IllegalArgumentException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadNamedStaticField_6_oe() throws Exception {


        try {
    FieldUtils.readStaticField(Foo.class, null);
    fail("IllegalArgumentException: null field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedStaticField_7_oe() throws Exception {



        try {
    FieldUtils.readStaticField(Foo.class, "");
    fail("IllegalArgumentException: empty field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedStaticField_8_oe() throws Exception {




        try {
    FieldUtils.readStaticField(Foo.class, " ");
    fail("IllegalArgumentException: blank field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedStaticField_9_oe() throws Exception {





        try {
    FieldUtils.readStaticField(Foo.class, "does_not_exist");
    fail("NullPointerException: a field that doesn't exist should cause an IllegalArgumentException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadNamedStaticField_10_oe() throws Exception {






        try {
    FieldUtils.readStaticField(PublicChild.class, "s");
    fail("IllegalArgumentException: non-static field should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedStaticFieldForceAccess_1_oe() throws Exception {
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(Foo.class, "VALUE", true));
    }

    @Test
    public void testReadNamedStaticFieldForceAccess_2_oe() throws Exception {
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(PubliclyShadowedChild.class, "VALUE", true));
    }

    @Test
    public void testReadNamedStaticFieldForceAccess_3_oe() throws Exception {
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(PrivatelyShadowedChild.class, "VALUE", true));
    }

    @Test
    public void testReadNamedStaticFieldForceAccess_4_oe() throws Exception {
        assertEquals("child", FieldUtils.readStaticField(PublicChild.class, "VALUE", true));
    }

    @Test
    public void testReadNamedStaticFieldForceAccess_5_oe() throws Exception {

        try {
    FieldUtils.readStaticField(null, "none", true);
    fail("NullPointerException: null class should cause an IllegalArgumentException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadNamedStaticFieldForceAccess_6_oe() throws Exception {


        try {
    FieldUtils.readStaticField(Foo.class, null, true);
    fail("IllegalArgumentException: null field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedStaticFieldForceAccess_7_oe() throws Exception {



        try {
    FieldUtils.readStaticField(Foo.class, "", true);
    fail("IllegalArgumentException: empty field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedStaticFieldForceAccess_8_oe() throws Exception {




        try {
    FieldUtils.readStaticField(Foo.class, " ", true);
    fail("IllegalArgumentException: blank field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedStaticFieldForceAccess_9_oe() throws Exception {





        try {
    FieldUtils.readStaticField(Foo.class, "does_not_exist", true);
    fail("NullPointerException: a field that doesn't exist should cause an IllegalArgumentException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadNamedStaticFieldForceAccess_10_oe() throws Exception {






        try {
    FieldUtils.readStaticField(PublicChild.class, "s", false);
    fail("IllegalArgumentException: non-static field should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedStaticField_1_oe() throws Exception {
        assertEquals(Foo.VALUE, FieldUtils.readDeclaredStaticField(Foo.class, "VALUE"));
    }

    @Test
    public void testReadDeclaredNamedStaticField_2_oe() throws Exception {
        try {
    FieldUtils.readDeclaredStaticField(PublicChild.class, "VALUE");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadDeclaredNamedStaticField_3_oe() throws Exception {
        try {
    FieldUtils.readDeclaredStaticField(PubliclyShadowedChild.class, "VALUE");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadDeclaredNamedStaticField_4_oe() throws Exception {
        try {
    FieldUtils.readDeclaredStaticField(PrivatelyShadowedChild.class, "VALUE");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadDeclaredNamedStaticFieldForceAccess_1_oe() throws Exception {
        assertEquals(Foo.VALUE, FieldUtils.readDeclaredStaticField(Foo.class, "VALUE", true));
    }

    @Test
    public void testReadDeclaredNamedStaticFieldForceAccess_2_oe() throws Exception {
        assertEquals("child", FieldUtils.readDeclaredStaticField(PublicChild.class, "VALUE", true));
    }

    @Test
    public void testReadDeclaredNamedStaticFieldForceAccess_3_oe() throws Exception {
        try {
    FieldUtils.readDeclaredStaticField(PubliclyShadowedChild.class, "VALUE", true);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadDeclaredNamedStaticFieldForceAccess_4_oe() throws Exception {
        try {
    FieldUtils.readDeclaredStaticField(PrivatelyShadowedChild.class, "VALUE", true);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadField_1_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        assertEquals("s", FieldUtils.readField(parentS, publicChild));
    }

    @Test
    public void testReadField_2_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        assertEquals("s", FieldUtils.readField(parentS, publiclyShadowedChild));
    }

    @Test
    public void testReadField_3_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        assertEquals("s", FieldUtils.readField(parentS, privatelyShadowedChild));
    }

    @Test
    public void testReadField_4_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        assertEquals(Boolean.FALSE, FieldUtils.readField(parentB, publicChild));
    }

    @Test
    public void testReadField_5_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        assertEquals(Boolean.FALSE, FieldUtils.readField(parentB, publiclyShadowedChild));
    }

    @Test
    public void testReadField_6_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        assertEquals(Boolean.FALSE, FieldUtils.readField(parentB, privatelyShadowedChild));
    }

    @Test
    public void testReadField_7_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        assertEquals(I0, FieldUtils.readField(parentI, publicChild));
    }

    @Test
    public void testReadField_8_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        assertEquals(I0, FieldUtils.readField(parentI, publiclyShadowedChild));
    }

    @Test
    public void testReadField_9_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        assertEquals(I0, FieldUtils.readField(parentI, privatelyShadowedChild));
    }

    @Test
    public void testReadField_10_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        final Field parentD = FieldUtils.getDeclaredField(parentClass, "d", true);
        assertEquals(D0, FieldUtils.readField(parentD, publicChild));
    }

    @Test
    public void testReadField_11_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        final Field parentD = FieldUtils.getDeclaredField(parentClass, "d", true);
        assertEquals(D0, FieldUtils.readField(parentD, publiclyShadowedChild));
    }

    @Test
    public void testReadField_12_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        final Field parentD = FieldUtils.getDeclaredField(parentClass, "d", true);
        assertEquals(D0, FieldUtils.readField(parentD, privatelyShadowedChild));
    }

    @Test
    public void testReadField_13_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        final Field parentD = FieldUtils.getDeclaredField(parentClass, "d", true);

        try {
    FieldUtils.readField(null, publicChild);
    fail("NullPointerException: a null field should cause an IllegalArgumentException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadFieldForceAccess_1_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        parentS.setAccessible(false);
        assertEquals("s", FieldUtils.readField(parentS, publicChild, true));
    }

    @Test
    public void testReadFieldForceAccess_2_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        parentS.setAccessible(false);
        assertEquals("s", FieldUtils.readField(parentS, publiclyShadowedChild, true));
    }

    @Test
    public void testReadFieldForceAccess_3_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        parentS.setAccessible(false);
        assertEquals("s", FieldUtils.readField(parentS, privatelyShadowedChild, true));
    }

    @Test
    public void testReadFieldForceAccess_4_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        parentS.setAccessible(false);
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        parentB.setAccessible(false);
        assertEquals(Boolean.FALSE, FieldUtils.readField(parentB, publicChild, true));
    }

    @Test
    public void testReadFieldForceAccess_5_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        parentS.setAccessible(false);
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        parentB.setAccessible(false);
        assertEquals(Boolean.FALSE, FieldUtils.readField(parentB, publiclyShadowedChild, true));
    }

    @Test
    public void testReadFieldForceAccess_6_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        parentS.setAccessible(false);
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        parentB.setAccessible(false);
        assertEquals(Boolean.FALSE, FieldUtils.readField(parentB, privatelyShadowedChild, true));
    }

    @Test
    public void testReadFieldForceAccess_7_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        parentS.setAccessible(false);
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        parentB.setAccessible(false);
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        parentI.setAccessible(false);
        assertEquals(I0, FieldUtils.readField(parentI, publicChild, true));
    }

    @Test
    public void testReadFieldForceAccess_8_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        parentS.setAccessible(false);
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        parentB.setAccessible(false);
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        parentI.setAccessible(false);
        assertEquals(I0, FieldUtils.readField(parentI, publiclyShadowedChild, true));
    }

    @Test
    public void testReadFieldForceAccess_9_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        parentS.setAccessible(false);
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        parentB.setAccessible(false);
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        parentI.setAccessible(false);
        assertEquals(I0, FieldUtils.readField(parentI, privatelyShadowedChild, true));
    }

    @Test
    public void testReadFieldForceAccess_10_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        parentS.setAccessible(false);
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        parentB.setAccessible(false);
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        parentI.setAccessible(false);
        final Field parentD = FieldUtils.getDeclaredField(parentClass, "d", true);
        parentD.setAccessible(false);
        assertEquals(D0, FieldUtils.readField(parentD, publicChild, true));
    }

    @Test
    public void testReadFieldForceAccess_11_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        parentS.setAccessible(false);
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        parentB.setAccessible(false);
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        parentI.setAccessible(false);
        final Field parentD = FieldUtils.getDeclaredField(parentClass, "d", true);
        parentD.setAccessible(false);
        assertEquals(D0, FieldUtils.readField(parentD, publiclyShadowedChild, true));
    }

    @Test
    public void testReadFieldForceAccess_12_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        parentS.setAccessible(false);
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        parentB.setAccessible(false);
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        parentI.setAccessible(false);
        final Field parentD = FieldUtils.getDeclaredField(parentClass, "d", true);
        parentD.setAccessible(false);
        assertEquals(D0, FieldUtils.readField(parentD, privatelyShadowedChild, true));
    }

    @Test
    public void testReadFieldForceAccess_13_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        parentS.setAccessible(false);
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        parentB.setAccessible(false);
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        parentI.setAccessible(false);
        final Field parentD = FieldUtils.getDeclaredField(parentClass, "d", true);
        parentD.setAccessible(false);

        try {
    FieldUtils.readField(null, publicChild, true);
    fail("NullPointerException: a null field should cause an IllegalArgumentException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadNamedField_1_oe() throws Exception {
        assertEquals("s", FieldUtils.readField(publicChild, "s"));
    }

    @Test
    public void testReadNamedField_2_oe() throws Exception {
        assertEquals("ss", FieldUtils.readField(publiclyShadowedChild, "s"));
    }

    @Test
    public void testReadNamedField_3_oe() throws Exception {
        assertEquals("s", FieldUtils.readField(privatelyShadowedChild, "s"));
    }

    @Test
    public void testReadNamedField_4_oe() throws Exception {

        try {
    FieldUtils.readField(publicChild, null);
    fail("IllegalArgumentException: a null field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedField_5_oe() throws Exception {


        try {
    FieldUtils.readField(publicChild, "");
    fail("IllegalArgumentException: an empty field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedField_6_oe() throws Exception {



        try {
    FieldUtils.readField(publicChild, " ");
    fail("IllegalArgumentException: a blank field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedField_7_oe() throws Exception {




        try {
    FieldUtils.readField((Object) null, "none");
    fail("NullPointerException: a null target should cause an IllegalArgumentException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadNamedField_8_oe() throws Exception {





        try {
    FieldUtils.readField(publicChild, "b");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedField_9_oe() throws Exception {






        assertEquals(Boolean.TRUE, FieldUtils.readField(publiclyShadowedChild, "b"));
    }

    @Test
    public void testReadNamedField_10_oe() throws Exception {






        try {
    FieldUtils.readField(privatelyShadowedChild, "b");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedField_11_oe() throws Exception {






        try {
    FieldUtils.readField(publicChild, "i");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedField_12_oe() throws Exception {






        assertEquals(I1, FieldUtils.readField(publiclyShadowedChild, "i"));
    }

    @Test
    public void testReadNamedField_13_oe() throws Exception {






        try {
    FieldUtils.readField(privatelyShadowedChild, "i");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedField_14_oe() throws Exception {






        try {
    FieldUtils.readField(publicChild, "d");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedField_15_oe() throws Exception {






        assertEquals(D1, FieldUtils.readField(publiclyShadowedChild, "d"));
    }

    @Test
    public void testReadNamedField_16_oe() throws Exception {






        try {
    FieldUtils.readField(privatelyShadowedChild, "d");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedFieldForceAccess_1_oe() throws Exception {
        assertEquals("s", FieldUtils.readField(publicChild, "s", true));
    }

    @Test
    public void testReadNamedFieldForceAccess_2_oe() throws Exception {
        assertEquals("ss", FieldUtils.readField(publiclyShadowedChild, "s", true));
    }

    @Test
    public void testReadNamedFieldForceAccess_3_oe() throws Exception {
        assertEquals("ss", FieldUtils.readField(privatelyShadowedChild, "s", true));
    }

    @Test
    public void testReadNamedFieldForceAccess_4_oe() throws Exception {
        assertEquals(Boolean.FALSE, FieldUtils.readField(publicChild, "b", true));
    }

    @Test
    public void testReadNamedFieldForceAccess_5_oe() throws Exception {
        assertEquals(Boolean.TRUE, FieldUtils.readField(publiclyShadowedChild, "b", true));
    }

    @Test
    public void testReadNamedFieldForceAccess_6_oe() throws Exception {
        assertEquals(Boolean.TRUE, FieldUtils.readField(privatelyShadowedChild, "b", true));
    }

    @Test
    public void testReadNamedFieldForceAccess_7_oe() throws Exception {
        assertEquals(I0, FieldUtils.readField(publicChild, "i", true));
    }

    @Test
    public void testReadNamedFieldForceAccess_8_oe() throws Exception {
        assertEquals(I1, FieldUtils.readField(publiclyShadowedChild, "i", true));
    }

    @Test
    public void testReadNamedFieldForceAccess_9_oe() throws Exception {
        assertEquals(I1, FieldUtils.readField(privatelyShadowedChild, "i", true));
    }

    @Test
    public void testReadNamedFieldForceAccess_10_oe() throws Exception {
        assertEquals(D0, FieldUtils.readField(publicChild, "d", true));
    }

    @Test
    public void testReadNamedFieldForceAccess_11_oe() throws Exception {
        assertEquals(D1, FieldUtils.readField(publiclyShadowedChild, "d", true));
    }

    @Test
    public void testReadNamedFieldForceAccess_12_oe() throws Exception {
        assertEquals(D1, FieldUtils.readField(privatelyShadowedChild, "d", true));
    }

    @Test
    public void testReadNamedFieldForceAccess_13_oe() throws Exception {

        try {
    FieldUtils.readField(publicChild, null, true);
    fail("IllegalArgumentException: a null field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedFieldForceAccess_14_oe() throws Exception {


        try {
    FieldUtils.readField(publicChild, "", true);
    fail("IllegalArgumentException: an empty field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedFieldForceAccess_15_oe() throws Exception {



        try {
    FieldUtils.readField(publicChild, " ", true);
    fail("IllegalArgumentException: a blank field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadNamedFieldForceAccess_16_oe() throws Exception {




        try {
    FieldUtils.readField((Object) null, "none", true);
    fail("NullPointerException: a null target should cause an IllegalArgumentException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadDeclaredNamedField_1_oe() throws Exception {
        try {
    FieldUtils.readDeclaredField(publicChild, null);
    fail("IllegalArgumentException: a null field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedField_2_oe() throws Exception {

        try {
    FieldUtils.readDeclaredField(publicChild, "");
    fail("IllegalArgumentException: an empty field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedField_3_oe() throws Exception {


        try {
    FieldUtils.readDeclaredField(publicChild, " ");
    fail("IllegalArgumentException: a blank field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedField_4_oe() throws Exception {



        try {
    FieldUtils.readDeclaredField(null, "none");
    fail("NullPointerException: a null target should cause an IllegalArgumentException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadDeclaredNamedField_5_oe() throws Exception {




        try {
    FieldUtils.readDeclaredField(publicChild, "s");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedField_6_oe() throws Exception {




        assertEquals("ss", FieldUtils.readDeclaredField(publiclyShadowedChild, "s"));
    }

    @Test
    public void testReadDeclaredNamedField_7_oe() throws Exception {




        try {
    FieldUtils.readDeclaredField(privatelyShadowedChild, "s");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedField_8_oe() throws Exception {




        try {
    FieldUtils.readDeclaredField(publicChild, "b");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedField_9_oe() throws Exception {




        assertEquals(Boolean.TRUE, FieldUtils.readDeclaredField(publiclyShadowedChild, "b"));
    }

    @Test
    public void testReadDeclaredNamedField_10_oe() throws Exception {




        try {
    FieldUtils.readDeclaredField(privatelyShadowedChild, "b");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedField_11_oe() throws Exception {




        try {
    FieldUtils.readDeclaredField(publicChild, "i");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedField_12_oe() throws Exception {




        assertEquals(I1, FieldUtils.readDeclaredField(publiclyShadowedChild, "i"));
    }

    @Test
    public void testReadDeclaredNamedField_13_oe() throws Exception {




        try {
    FieldUtils.readDeclaredField(privatelyShadowedChild, "i");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedField_14_oe() throws Exception {




        try {
    FieldUtils.readDeclaredField(publicChild, "d");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedField_15_oe() throws Exception {




        assertEquals(D1, FieldUtils.readDeclaredField(publiclyShadowedChild, "d"));
    }

    @Test
    public void testReadDeclaredNamedField_16_oe() throws Exception {




        try {
    FieldUtils.readDeclaredField(privatelyShadowedChild, "d");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedFieldForceAccess_1_oe() throws Exception {
        try {
    FieldUtils.readDeclaredField(publicChild, null, true);
    fail("IllegalArgumentException: a null field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedFieldForceAccess_2_oe() throws Exception {

        try {
    FieldUtils.readDeclaredField(publicChild, "", true);
    fail("IllegalArgumentException: an empty field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedFieldForceAccess_3_oe() throws Exception {


        try {
    FieldUtils.readDeclaredField(publicChild, " ", true);
    fail("IllegalArgumentException: a blank field name should cause an IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedFieldForceAccess_4_oe() throws Exception {



        try {
    FieldUtils.readDeclaredField(null, "none", true);
    fail("NullPointerException: a null target should cause an IllegalArgumentException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testReadDeclaredNamedFieldForceAccess_5_oe() throws Exception {




        try {
    FieldUtils.readDeclaredField(publicChild, "s", true);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedFieldForceAccess_6_oe() throws Exception {




        assertEquals("ss", FieldUtils.readDeclaredField(publiclyShadowedChild, "s", true));
    }

    @Test
    public void testReadDeclaredNamedFieldForceAccess_7_oe() throws Exception {




        assertEquals("ss", FieldUtils.readDeclaredField(privatelyShadowedChild, "s", true));
    }

    @Test
    public void testReadDeclaredNamedFieldForceAccess_8_oe() throws Exception {




        try {
    FieldUtils.readDeclaredField(publicChild, "b", true);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedFieldForceAccess_9_oe() throws Exception {




        assertEquals(Boolean.TRUE, FieldUtils.readDeclaredField(publiclyShadowedChild, "b", true));
    }

    @Test
    public void testReadDeclaredNamedFieldForceAccess_10_oe() throws Exception {




        assertEquals(Boolean.TRUE, FieldUtils.readDeclaredField(privatelyShadowedChild, "b", true));
    }

    @Test
    public void testReadDeclaredNamedFieldForceAccess_11_oe() throws Exception {




        try {
    FieldUtils.readDeclaredField(publicChild, "i", true);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedFieldForceAccess_12_oe() throws Exception {




        assertEquals(I1, FieldUtils.readDeclaredField(publiclyShadowedChild, "i", true));
    }

    @Test
    public void testReadDeclaredNamedFieldForceAccess_13_oe() throws Exception {




        assertEquals(I1, FieldUtils.readDeclaredField(privatelyShadowedChild, "i", true));
    }

    @Test
    public void testReadDeclaredNamedFieldForceAccess_14_oe() throws Exception {




        try {
    FieldUtils.readDeclaredField(publicChild, "d", true);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReadDeclaredNamedFieldForceAccess_15_oe() throws Exception {




        assertEquals(D1, FieldUtils.readDeclaredField(publiclyShadowedChild, "d", true));
    }

    @Test
    public void testReadDeclaredNamedFieldForceAccess_16_oe() throws Exception {




        assertEquals(D1, FieldUtils.readDeclaredField(privatelyShadowedChild, "d", true));
    }

    @Test
    public void testWriteStaticField_1_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new");
        assertEquals("new", StaticContainer.mutablePublic);
    }

    @Test
    public void testWriteStaticField_2_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new");
        try {
    FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("mutableProtected"), "new");
    fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteStaticField_3_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new");
        try {
    FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("mutablePackage"), "new");
    fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteStaticField_4_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new");
        try {
    FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("mutablePrivate"), "new");
    fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteStaticField_5_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new");
        try {
    FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PUBLIC"), "new");
    fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteStaticField_6_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new");
        try {
    FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PROTECTED"), "new");
    fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteStaticField_7_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new");
        try {
    FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PACKAGE"), "new");
    fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteStaticField_8_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new");
        try {
    FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE"), "new");
    fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteStaticFieldForceAccess_1_oe() throws Exception {
        Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new", true);
        assertEquals("new", StaticContainer.mutablePublic);
    }

    @Test
    public void testWriteStaticFieldForceAccess_2_oe() throws Exception {
        Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new", true);
        field = StaticContainer.class.getDeclaredField("mutableProtected");
        FieldUtils.writeStaticField(field, "new", true);
        assertEquals("new", StaticContainer.getMutableProtected());
    }

    @Test
    public void testWriteStaticFieldForceAccess_3_oe() throws Exception {
        Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new", true);
        field = StaticContainer.class.getDeclaredField("mutableProtected");
        FieldUtils.writeStaticField(field, "new", true);
        field = StaticContainer.class.getDeclaredField("mutablePackage");
        FieldUtils.writeStaticField(field, "new", true);
        assertEquals("new", StaticContainer.getMutablePackage());
    }

    @Test
    public void testWriteStaticFieldForceAccess_4_oe() throws Exception {
        Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new", true);
        field = StaticContainer.class.getDeclaredField("mutableProtected");
        FieldUtils.writeStaticField(field, "new", true);
        field = StaticContainer.class.getDeclaredField("mutablePackage");
        FieldUtils.writeStaticField(field, "new", true);
        field = StaticContainer.class.getDeclaredField("mutablePrivate");
        FieldUtils.writeStaticField(field, "new", true);
        assertEquals("new", StaticContainer.getMutablePrivate());
    }

    @Test
    public void testWriteStaticFieldForceAccess_5_oe() throws Exception {
        Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new", true);
        field = StaticContainer.class.getDeclaredField("mutableProtected");
        FieldUtils.writeStaticField(field, "new", true);
        field = StaticContainer.class.getDeclaredField("mutablePackage");
        FieldUtils.writeStaticField(field, "new", true);
        field = StaticContainer.class.getDeclaredField("mutablePrivate");
        FieldUtils.writeStaticField(field, "new", true);
        try {
    FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PUBLIC"), "new", true);
    fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteStaticFieldForceAccess_6_oe() throws Exception {
        Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new", true);
        field = StaticContainer.class.getDeclaredField("mutableProtected");
        FieldUtils.writeStaticField(field, "new", true);
        field = StaticContainer.class.getDeclaredField("mutablePackage");
        FieldUtils.writeStaticField(field, "new", true);
        field = StaticContainer.class.getDeclaredField("mutablePrivate");
        FieldUtils.writeStaticField(field, "new", true);
        try {
    FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PROTECTED"), "new", true);
    fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteStaticFieldForceAccess_7_oe() throws Exception {
        Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new", true);
        field = StaticContainer.class.getDeclaredField("mutableProtected");
        FieldUtils.writeStaticField(field, "new", true);
        field = StaticContainer.class.getDeclaredField("mutablePackage");
        FieldUtils.writeStaticField(field, "new", true);
        field = StaticContainer.class.getDeclaredField("mutablePrivate");
        FieldUtils.writeStaticField(field, "new", true);
        try {
    FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PACKAGE"), "new", true);
    fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteStaticFieldForceAccess_8_oe() throws Exception {
        Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new", true);
        field = StaticContainer.class.getDeclaredField("mutableProtected");
        FieldUtils.writeStaticField(field, "new", true);
        field = StaticContainer.class.getDeclaredField("mutablePackage");
        FieldUtils.writeStaticField(field, "new", true);
        field = StaticContainer.class.getDeclaredField("mutablePrivate");
        FieldUtils.writeStaticField(field, "new", true);
        try {
    FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE"), "new", true);
    fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteNamedStaticField_1_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new");
        assertEquals("new", StaticContainer.mutablePublic);
    }

    @Test
    public void testWriteNamedStaticField_2_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new");
        try {
    FieldUtils.writeStaticField(StaticContainerChild.class, "mutableProtected", "new");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testWriteNamedStaticField_3_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new");
        try {
    FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePackage", "new");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testWriteNamedStaticField_4_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new");
        try {
    FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePrivate", "new");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testWriteNamedStaticField_5_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new");
        try {
    FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PUBLIC", "new");
    fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteNamedStaticField_6_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new");
        try {
    FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PROTECTED", "new");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testWriteNamedStaticField_7_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new");
        try {
    FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PACKAGE", "new");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testWriteNamedStaticField_8_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new");
        try {
    FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PRIVATE", "new");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testWriteNamedStaticFieldForceAccess_1_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new", true);
        assertEquals("new", StaticContainer.mutablePublic);
    }

    @Test
    public void testWriteNamedStaticFieldForceAccess_2_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new", true);
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutableProtected", "new", true);
        assertEquals("new", StaticContainer.getMutableProtected());
    }

    @Test
    public void testWriteNamedStaticFieldForceAccess_3_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new", true);
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutableProtected", "new", true);
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePackage", "new", true);
        assertEquals("new", StaticContainer.getMutablePackage());
    }

    @Test
    public void testWriteNamedStaticFieldForceAccess_4_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new", true);
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutableProtected", "new", true);
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePackage", "new", true);
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePrivate", "new", true);
        assertEquals("new", StaticContainer.getMutablePrivate());
    }

    @Test
    public void testWriteNamedStaticFieldForceAccess_5_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new", true);
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutableProtected", "new", true);
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePackage", "new", true);
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePrivate", "new", true);
        try {
    FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PUBLIC", "new", true);
    fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteNamedStaticFieldForceAccess_6_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new", true);
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutableProtected", "new", true);
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePackage", "new", true);
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePrivate", "new", true);
        try {
    FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PROTECTED", "new", true);
    fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteNamedStaticFieldForceAccess_7_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new", true);
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutableProtected", "new", true);
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePackage", "new", true);
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePrivate", "new", true);
        try {
    FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PACKAGE", "new", true);
    fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteNamedStaticFieldForceAccess_8_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new", true);
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutableProtected", "new", true);
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePackage", "new", true);
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePrivate", "new", true);
        try {
    FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PRIVATE", "new", true);
    fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedStaticField_1_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainer.class, "mutablePublic", "new");
        assertEquals("new", StaticContainer.mutablePublic);
    }

    @Test
    public void testWriteDeclaredNamedStaticField_2_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainer.class, "mutablePublic", "new");
        try {
    FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutableProtected", "new");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedStaticField_3_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainer.class, "mutablePublic", "new");
        try {
    FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePackage", "new");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedStaticField_4_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainer.class, "mutablePublic", "new");
        try {
    FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePrivate", "new");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedStaticField_5_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainer.class, "mutablePublic", "new");
        try {
    FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PUBLIC", "new");
    fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedStaticField_6_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainer.class, "mutablePublic", "new");
        try {
    FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PROTECTED", "new");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedStaticField_7_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainer.class, "mutablePublic", "new");
        try {
    FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PACKAGE", "new");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedStaticField_8_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainer.class, "mutablePublic", "new");
        try {
    FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PRIVATE", "new");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedStaticFieldForceAccess_1_oe() throws Exception {
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePublic", "new", true);
        assertEquals("new", StaticContainer.mutablePublic);
    }

    @Test
    public void testWriteDeclaredNamedStaticFieldForceAccess_2_oe() throws Exception {
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePublic", "new", true);
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutableProtected", "new", true);
        assertEquals("new", StaticContainer.getMutableProtected());
    }

    @Test
    public void testWriteDeclaredNamedStaticFieldForceAccess_3_oe() throws Exception {
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePublic", "new", true);
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutableProtected", "new", true);
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePackage", "new", true);
        assertEquals("new", StaticContainer.getMutablePackage());
    }

    @Test
    public void testWriteDeclaredNamedStaticFieldForceAccess_4_oe() throws Exception {
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePublic", "new", true);
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutableProtected", "new", true);
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePackage", "new", true);
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePrivate", "new", true);
        assertEquals("new", StaticContainer.getMutablePrivate());
    }

    @Test
    public void testWriteDeclaredNamedStaticFieldForceAccess_5_oe() throws Exception {
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePublic", "new", true);
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutableProtected", "new", true);
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePackage", "new", true);
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePrivate", "new", true);
        try {
    FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PUBLIC", "new", true);
    fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedStaticFieldForceAccess_6_oe() throws Exception {
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePublic", "new", true);
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutableProtected", "new", true);
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePackage", "new", true);
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePrivate", "new", true);
        try {
    FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PROTECTED", "new", true);
    fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedStaticFieldForceAccess_7_oe() throws Exception {
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePublic", "new", true);
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutableProtected", "new", true);
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePackage", "new", true);
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePrivate", "new", true);
        try {
    FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PACKAGE", "new", true);
    fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedStaticFieldForceAccess_8_oe() throws Exception {
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePublic", "new", true);
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutableProtected", "new", true);
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePackage", "new", true);
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePrivate", "new", true);
        try {
    FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PRIVATE", "new", true);
    fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteField_1_oe() throws Exception {
        final Field field = parentClass.getDeclaredField("s");
        FieldUtils.writeField(field, publicChild, "S");
        assertEquals("S", field.get(publicChild));
    }

    @Test
    public void testWriteField_2_oe() throws Exception {
        final Field field = parentClass.getDeclaredField("s");
        FieldUtils.writeField(field, publicChild, "S");
        try {
    FieldUtils.writeField(parentClass.getDeclaredField("b"), publicChild, Boolean.TRUE);
    fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteField_3_oe() throws Exception {
        final Field field = parentClass.getDeclaredField("s");
        FieldUtils.writeField(field, publicChild, "S");
        try {
    FieldUtils.writeField(parentClass.getDeclaredField("i"), publicChild, Integer.valueOf(Integer.MAX_VALUE));
    fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteField_4_oe() throws Exception {
        final Field field = parentClass.getDeclaredField("s");
        FieldUtils.writeField(field, publicChild, "S");
        try {
    FieldUtils.writeField(parentClass.getDeclaredField("d"), publicChild, Double.valueOf(Double.MAX_VALUE));
    fail("IllegalAccessException");
} catch (IllegalAccessException e) {
}
    }

    @Test
    public void testWriteFieldForceAccess_1_oe() throws Exception {
        Field field = parentClass.getDeclaredField("s");
        FieldUtils.writeField(field, publicChild, "S", true);
        assertEquals("S", field.get(publicChild));
    }

    @Test
    public void testWriteFieldForceAccess_2_oe() throws Exception {
        Field field = parentClass.getDeclaredField("s");
        FieldUtils.writeField(field, publicChild, "S", true);
        field = parentClass.getDeclaredField("b");
        FieldUtils.writeField(field, publicChild, Boolean.TRUE, true);
        assertEquals(Boolean.TRUE, field.get(publicChild));
    }

    @Test
    public void testWriteFieldForceAccess_3_oe() throws Exception {
        Field field = parentClass.getDeclaredField("s");
        FieldUtils.writeField(field, publicChild, "S", true);
        field = parentClass.getDeclaredField("b");
        FieldUtils.writeField(field, publicChild, Boolean.TRUE, true);
        field = parentClass.getDeclaredField("i");
        FieldUtils.writeField(field, publicChild, Integer.valueOf(Integer.MAX_VALUE), true);
        assertEquals(Integer.valueOf(Integer.MAX_VALUE), field.get(publicChild));
    }

    @Test
    public void testWriteFieldForceAccess_4_oe() throws Exception {
        Field field = parentClass.getDeclaredField("s");
        FieldUtils.writeField(field, publicChild, "S", true);
        field = parentClass.getDeclaredField("b");
        FieldUtils.writeField(field, publicChild, Boolean.TRUE, true);
        field = parentClass.getDeclaredField("i");
        FieldUtils.writeField(field, publicChild, Integer.valueOf(Integer.MAX_VALUE), true);
        field = parentClass.getDeclaredField("d");
        FieldUtils.writeField(field, publicChild, Double.valueOf(Double.MAX_VALUE), true);
        assertEquals(Double.valueOf(Double.MAX_VALUE), field.get(publicChild));
    }

    @Test
    public void testWriteNamedField_1_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");
        assertEquals("S", FieldUtils.readField(publicChild, "s"));
    }

    @Test
    public void testWriteNamedField_2_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");
        try {
    FieldUtils.writeField(publicChild, "b", Boolean.TRUE);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteNamedField_3_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");
        try {
    FieldUtils.writeField(publicChild, "i", Integer.valueOf(1));
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteNamedField_4_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");
        try {
    FieldUtils.writeField(publicChild, "d", Double.valueOf(1.0));
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteNamedField_5_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");

        FieldUtils.writeField(publiclyShadowedChild, "s", "S");
        assertEquals("S", FieldUtils.readField(publiclyShadowedChild, "s"));
    }

    @Test
    public void testWriteNamedField_6_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");

        FieldUtils.writeField(publiclyShadowedChild, "s", "S");
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE);
        assertEquals(Boolean.FALSE, FieldUtils.readField(publiclyShadowedChild, "b"));
    }

    @Test
    public void testWriteNamedField_7_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");

        FieldUtils.writeField(publiclyShadowedChild, "s", "S");
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE);
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0));
        assertEquals(Integer.valueOf(0), FieldUtils.readField(publiclyShadowedChild, "i"));
    }

    @Test
    public void testWriteNamedField_8_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");

        FieldUtils.writeField(publiclyShadowedChild, "s", "S");
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE);
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0));
        FieldUtils.writeField(publiclyShadowedChild, "d", Double.valueOf(0.0));
        assertEquals(Double.valueOf(0.0), FieldUtils.readField(publiclyShadowedChild, "d"));
    }

    @Test
    public void testWriteNamedField_9_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");

        FieldUtils.writeField(publiclyShadowedChild, "s", "S");
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE);
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0));
        FieldUtils.writeField(publiclyShadowedChild, "d", Double.valueOf(0.0));

        FieldUtils.writeField(privatelyShadowedChild, "s", "S");
        assertEquals("S", FieldUtils.readField(privatelyShadowedChild, "s"));
    }

    @Test
    public void testWriteNamedField_10_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");

        FieldUtils.writeField(publiclyShadowedChild, "s", "S");
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE);
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0));
        FieldUtils.writeField(publiclyShadowedChild, "d", Double.valueOf(0.0));

        FieldUtils.writeField(privatelyShadowedChild, "s", "S");
        try {
    FieldUtils.writeField(privatelyShadowedChild, "b", Boolean.TRUE);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteNamedField_11_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");

        FieldUtils.writeField(publiclyShadowedChild, "s", "S");
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE);
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0));
        FieldUtils.writeField(publiclyShadowedChild, "d", Double.valueOf(0.0));

        FieldUtils.writeField(privatelyShadowedChild, "s", "S");
        try {
    FieldUtils.writeField(privatelyShadowedChild, "i", Integer.valueOf(1));
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteNamedField_12_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");

        FieldUtils.writeField(publiclyShadowedChild, "s", "S");
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE);
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0));
        FieldUtils.writeField(publiclyShadowedChild, "d", Double.valueOf(0.0));

        FieldUtils.writeField(privatelyShadowedChild, "s", "S");
        try {
    FieldUtils.writeField(privatelyShadowedChild, "d", Double.valueOf(1.0));
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteNamedFieldForceAccess_1_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S", true);
        assertEquals("S", FieldUtils.readField(publicChild, "s", true));
    }

    @Test
    public void testWriteNamedFieldForceAccess_2_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S", true);
        FieldUtils.writeField(publicChild, "b", Boolean.TRUE, true);
        assertEquals(Boolean.TRUE, FieldUtils.readField(publicChild, "b", true));
    }

    @Test
    public void testWriteNamedFieldForceAccess_3_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S", true);
        FieldUtils.writeField(publicChild, "b", Boolean.TRUE, true);
        FieldUtils.writeField(publicChild, "i", Integer.valueOf(1), true);
        assertEquals(Integer.valueOf(1), FieldUtils.readField(publicChild, "i", true));
    }

    @Test
    public void testWriteNamedFieldForceAccess_4_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S", true);
        FieldUtils.writeField(publicChild, "b", Boolean.TRUE, true);
        FieldUtils.writeField(publicChild, "i", Integer.valueOf(1), true);
        FieldUtils.writeField(publicChild, "d", Double.valueOf(1.0), true);
        assertEquals(Double.valueOf(1.0), FieldUtils.readField(publicChild, "d", true));
    }

    @Test
    public void testWriteNamedFieldForceAccess_5_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S", true);
        FieldUtils.writeField(publicChild, "b", Boolean.TRUE, true);
        FieldUtils.writeField(publicChild, "i", Integer.valueOf(1), true);
        FieldUtils.writeField(publicChild, "d", Double.valueOf(1.0), true);

        FieldUtils.writeField(publiclyShadowedChild, "s", "S", true);
        assertEquals("S", FieldUtils.readField(publiclyShadowedChild, "s", true));
    }

    @Test
    public void testWriteNamedFieldForceAccess_6_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S", true);
        FieldUtils.writeField(publicChild, "b", Boolean.TRUE, true);
        FieldUtils.writeField(publicChild, "i", Integer.valueOf(1), true);
        FieldUtils.writeField(publicChild, "d", Double.valueOf(1.0), true);

        FieldUtils.writeField(publiclyShadowedChild, "s", "S", true);
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        assertEquals(Boolean.FALSE, FieldUtils.readField(publiclyShadowedChild, "b", true));
    }

    @Test
    public void testWriteNamedFieldForceAccess_7_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S", true);
        FieldUtils.writeField(publicChild, "b", Boolean.TRUE, true);
        FieldUtils.writeField(publicChild, "i", Integer.valueOf(1), true);
        FieldUtils.writeField(publicChild, "d", Double.valueOf(1.0), true);

        FieldUtils.writeField(publiclyShadowedChild, "s", "S", true);
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0), true);
        assertEquals(Integer.valueOf(0), FieldUtils.readField(publiclyShadowedChild, "i", true));
    }

    @Test
    public void testWriteNamedFieldForceAccess_8_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S", true);
        FieldUtils.writeField(publicChild, "b", Boolean.TRUE, true);
        FieldUtils.writeField(publicChild, "i", Integer.valueOf(1), true);
        FieldUtils.writeField(publicChild, "d", Double.valueOf(1.0), true);

        FieldUtils.writeField(publiclyShadowedChild, "s", "S", true);
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0), true);
        FieldUtils.writeField(publiclyShadowedChild, "d", Double.valueOf(0.0), true);
        assertEquals(Double.valueOf(0.0), FieldUtils.readField(publiclyShadowedChild, "d", true));
    }

    @Test
    public void testWriteNamedFieldForceAccess_9_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S", true);
        FieldUtils.writeField(publicChild, "b", Boolean.TRUE, true);
        FieldUtils.writeField(publicChild, "i", Integer.valueOf(1), true);
        FieldUtils.writeField(publicChild, "d", Double.valueOf(1.0), true);

        FieldUtils.writeField(publiclyShadowedChild, "s", "S", true);
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0), true);
        FieldUtils.writeField(publiclyShadowedChild, "d", Double.valueOf(0.0), true);

        FieldUtils.writeField(privatelyShadowedChild, "s", "S", true);
        assertEquals("S", FieldUtils.readField(privatelyShadowedChild, "s", true));
    }

    @Test
    public void testWriteNamedFieldForceAccess_10_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S", true);
        FieldUtils.writeField(publicChild, "b", Boolean.TRUE, true);
        FieldUtils.writeField(publicChild, "i", Integer.valueOf(1), true);
        FieldUtils.writeField(publicChild, "d", Double.valueOf(1.0), true);

        FieldUtils.writeField(publiclyShadowedChild, "s", "S", true);
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0), true);
        FieldUtils.writeField(publiclyShadowedChild, "d", Double.valueOf(0.0), true);

        FieldUtils.writeField(privatelyShadowedChild, "s", "S", true);
        FieldUtils.writeField(privatelyShadowedChild, "b", Boolean.FALSE, true);
        assertEquals(Boolean.FALSE, FieldUtils.readField(privatelyShadowedChild, "b", true));
    }

    @Test
    public void testWriteNamedFieldForceAccess_11_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S", true);
        FieldUtils.writeField(publicChild, "b", Boolean.TRUE, true);
        FieldUtils.writeField(publicChild, "i", Integer.valueOf(1), true);
        FieldUtils.writeField(publicChild, "d", Double.valueOf(1.0), true);

        FieldUtils.writeField(publiclyShadowedChild, "s", "S", true);
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0), true);
        FieldUtils.writeField(publiclyShadowedChild, "d", Double.valueOf(0.0), true);

        FieldUtils.writeField(privatelyShadowedChild, "s", "S", true);
        FieldUtils.writeField(privatelyShadowedChild, "b", Boolean.FALSE, true);
        FieldUtils.writeField(privatelyShadowedChild, "i", Integer.valueOf(0), true);
        assertEquals(Integer.valueOf(0), FieldUtils.readField(privatelyShadowedChild, "i", true));
    }

    @Test
    public void testWriteNamedFieldForceAccess_12_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S", true);
        FieldUtils.writeField(publicChild, "b", Boolean.TRUE, true);
        FieldUtils.writeField(publicChild, "i", Integer.valueOf(1), true);
        FieldUtils.writeField(publicChild, "d", Double.valueOf(1.0), true);

        FieldUtils.writeField(publiclyShadowedChild, "s", "S", true);
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0), true);
        FieldUtils.writeField(publiclyShadowedChild, "d", Double.valueOf(0.0), true);

        FieldUtils.writeField(privatelyShadowedChild, "s", "S", true);
        FieldUtils.writeField(privatelyShadowedChild, "b", Boolean.FALSE, true);
        FieldUtils.writeField(privatelyShadowedChild, "i", Integer.valueOf(0), true);
        FieldUtils.writeField(privatelyShadowedChild, "d", Double.valueOf(0.0), true);
        assertEquals(Double.valueOf(0.0), FieldUtils.readField(privatelyShadowedChild, "d", true));
    }

    @Test
    public void testWriteDeclaredNamedField_1_oe() throws Exception {
        try {
    FieldUtils.writeDeclaredField(publicChild, "s", "S");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedField_2_oe() throws Exception {
        try {
    FieldUtils.writeDeclaredField(publicChild, "b", Boolean.TRUE);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedField_3_oe() throws Exception {
        try {
    FieldUtils.writeDeclaredField(publicChild, "i", Integer.valueOf(1));
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedField_4_oe() throws Exception {
        try {
    FieldUtils.writeDeclaredField(publicChild, "d", Double.valueOf(1.0));
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedField_5_oe() throws Exception {

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S");
        assertEquals("S", FieldUtils.readDeclaredField(publiclyShadowedChild, "s"));
    }

    @Test
    public void testWriteDeclaredNamedField_6_oe() throws Exception {

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S");
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE);
        assertEquals(Boolean.FALSE, FieldUtils.readDeclaredField(publiclyShadowedChild, "b"));
    }

    @Test
    public void testWriteDeclaredNamedField_7_oe() throws Exception {

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S");
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE);
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0));
        assertEquals(Integer.valueOf(0), FieldUtils.readDeclaredField(publiclyShadowedChild, "i"));
    }

    @Test
    public void testWriteDeclaredNamedField_8_oe() throws Exception {

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S");
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE);
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0));
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "d", Double.valueOf(0.0));
        assertEquals(Double.valueOf(0.0), FieldUtils.readDeclaredField(publiclyShadowedChild, "d"));
    }

    @Test
    public void testWriteDeclaredNamedField_9_oe() throws Exception {

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S");
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE);
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0));
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "d", Double.valueOf(0.0));

        try {
    FieldUtils.writeDeclaredField(privatelyShadowedChild, "s", "S");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedField_10_oe() throws Exception {

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S");
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE);
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0));
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "d", Double.valueOf(0.0));

        try {
    FieldUtils.writeDeclaredField(privatelyShadowedChild, "b", Boolean.TRUE);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedField_11_oe() throws Exception {

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S");
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE);
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0));
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "d", Double.valueOf(0.0));

        try {
    FieldUtils.writeDeclaredField(privatelyShadowedChild, "i", Integer.valueOf(1));
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedField_12_oe() throws Exception {

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S");
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE);
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0));
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "d", Double.valueOf(0.0));

        try {
    FieldUtils.writeDeclaredField(privatelyShadowedChild, "d", Double.valueOf(1.0));
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedFieldForceAccess_1_oe() throws Exception {
        try {
    FieldUtils.writeDeclaredField(publicChild, "s", "S", true);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedFieldForceAccess_2_oe() throws Exception {
        try {
    FieldUtils.writeDeclaredField(publicChild, "b", Boolean.TRUE, true);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedFieldForceAccess_3_oe() throws Exception {
        try {
    FieldUtils.writeDeclaredField(publicChild, "i", Integer.valueOf(1), true);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedFieldForceAccess_4_oe() throws Exception {
        try {
    FieldUtils.writeDeclaredField(publicChild, "d", Double.valueOf(1.0), true);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWriteDeclaredNamedFieldForceAccess_5_oe() throws Exception {

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S", true);
        assertEquals("S", FieldUtils.readDeclaredField(publiclyShadowedChild, "s", true));
    }

    @Test
    public void testWriteDeclaredNamedFieldForceAccess_6_oe() throws Exception {

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S", true);
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        assertEquals(Boolean.FALSE, FieldUtils.readDeclaredField(publiclyShadowedChild, "b", true));
    }

    @Test
    public void testWriteDeclaredNamedFieldForceAccess_7_oe() throws Exception {

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S", true);
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0), true);
        assertEquals(Integer.valueOf(0), FieldUtils.readDeclaredField(publiclyShadowedChild, "i", true));
    }

    @Test
    public void testWriteDeclaredNamedFieldForceAccess_8_oe() throws Exception {

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S", true);
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0), true);
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "d", Double.valueOf(0.0), true);
        assertEquals(Double.valueOf(0.0), FieldUtils.readDeclaredField(publiclyShadowedChild, "d", true));
    }

    @Test
    public void testWriteDeclaredNamedFieldForceAccess_9_oe() throws Exception {

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S", true);
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0), true);
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "d", Double.valueOf(0.0), true);

        FieldUtils.writeDeclaredField(privatelyShadowedChild, "s", "S", true);
        assertEquals("S", FieldUtils.readDeclaredField(privatelyShadowedChild, "s", true));
    }

    @Test
    public void testWriteDeclaredNamedFieldForceAccess_10_oe() throws Exception {

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S", true);
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0), true);
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "d", Double.valueOf(0.0), true);

        FieldUtils.writeDeclaredField(privatelyShadowedChild, "s", "S", true);
        FieldUtils.writeDeclaredField(privatelyShadowedChild, "b", Boolean.FALSE, true);
        assertEquals(Boolean.FALSE, FieldUtils.readDeclaredField(privatelyShadowedChild, "b", true));
    }

    @Test
    public void testWriteDeclaredNamedFieldForceAccess_11_oe() throws Exception {

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S", true);
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0), true);
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "d", Double.valueOf(0.0), true);

        FieldUtils.writeDeclaredField(privatelyShadowedChild, "s", "S", true);
        FieldUtils.writeDeclaredField(privatelyShadowedChild, "b", Boolean.FALSE, true);
        FieldUtils.writeDeclaredField(privatelyShadowedChild, "i", Integer.valueOf(0), true);
        assertEquals(Integer.valueOf(0), FieldUtils.readDeclaredField(privatelyShadowedChild, "i", true));
    }

    @Test
    public void testWriteDeclaredNamedFieldForceAccess_12_oe() throws Exception {

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S", true);
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0), true);
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "d", Double.valueOf(0.0), true);

        FieldUtils.writeDeclaredField(privatelyShadowedChild, "s", "S", true);
        FieldUtils.writeDeclaredField(privatelyShadowedChild, "b", Boolean.FALSE, true);
        FieldUtils.writeDeclaredField(privatelyShadowedChild, "i", Integer.valueOf(0), true);
        FieldUtils.writeDeclaredField(privatelyShadowedChild, "d", Double.valueOf(0.0), true);
        assertEquals(Double.valueOf(0.0), FieldUtils.readDeclaredField(privatelyShadowedChild, "d", true));
    }

    @Test
    public void testAmbig_1_oe() throws Exception {
        try {
    FieldUtils.getField(Ambig.class, "VALUE");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testRemoveFinalModifier_1_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE_2");
        assertFalse(field.isAccessible());
    }

    @Test
    public void testRemoveFinalModifier_2_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE_2");
        assertTrue(Modifier.isFinal(field.getModifiers()));
    }

    @Test
    public void testRemoveFinalModifier_3_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE_2");
        callRemoveFinalModifierCheckForException(field, true);
        if (SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_11)) {
            assertFalse(Modifier.isFinal(field.getModifiers()));
    }
    }

    @Test
    public void testRemoveFinalModifier_4_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE_2");
        callRemoveFinalModifierCheckForException(field, true);
        if (SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_11)) {
            assertFalse(field.isAccessible());
    }
    }

    @Test
    public void testRemoveFinalModifierWithAccess_1_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE_2");
        assertFalse(field.isAccessible());
    }

    @Test
    public void testRemoveFinalModifierWithAccess_2_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE_2");
        assertTrue(Modifier.isFinal(field.getModifiers()));
    }

    @Test
    public void testRemoveFinalModifierWithAccess_3_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE_2");
        callRemoveFinalModifierCheckForException(field, true);
        if (SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_11)) {
            assertFalse(Modifier.isFinal(field.getModifiers()));
    }
    }

    @Test
    public void testRemoveFinalModifierWithAccess_4_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE_2");
        callRemoveFinalModifierCheckForException(field, true);
        if (SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_11)) {
            assertFalse(field.isAccessible());
    }
    }

    @Test
    public void testRemoveFinalModifierWithoutAccess_1_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE_2");
        assertFalse(field.isAccessible());
    }

    @Test
    public void testRemoveFinalModifierWithoutAccess_2_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE_2");
        assertTrue(Modifier.isFinal(field.getModifiers()));
    }

    @Test
    public void testRemoveFinalModifierWithoutAccess_3_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE_2");
        callRemoveFinalModifierCheckForException(field, false);
        if (SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_11)) {
            assertTrue(Modifier.isFinal(field.getModifiers()));
    }
    }

    @Test
    public void testRemoveFinalModifierWithoutAccess_4_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE_2");
        callRemoveFinalModifierCheckForException(field, false);
        if (SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_11)) {
            assertFalse(field.isAccessible());
    }
    }

    @Test
    public void testRemoveFinalModifierAccessNotNeeded_1_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PACKAGE");
        assertFalse(field.isAccessible());
    }

    @Test
    public void testRemoveFinalModifierAccessNotNeeded_2_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PACKAGE");
        assertTrue(Modifier.isFinal(field.getModifiers()));
    }

    @Test
    public void testRemoveFinalModifierAccessNotNeeded_3_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PACKAGE");
        callRemoveFinalModifierCheckForException(field, false);
        if (SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_11)) {
            assertTrue(Modifier.isFinal(field.getModifiers()));
    }
    }

    @Test
    public void testRemoveFinalModifierAccessNotNeeded_4_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PACKAGE");
        callRemoveFinalModifierCheckForException(field, false);
        if (SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_11)) {
            assertFalse(field.isAccessible());
    }
    }

}
