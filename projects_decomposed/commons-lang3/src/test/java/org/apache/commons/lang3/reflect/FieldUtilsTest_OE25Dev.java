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

    private Field[] sort(final Field[] fields) {
        // Field does not implement Comparable, so we use a KISS solution here.
        return ArraySorter.sort(fields, ObjectToStringComparator.INSTANCE);
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
        // removed other assertion
        final Constructor<?>[] cons = FieldUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
    }

@Test
    public void testConstructor_3_oe() {
        // removed other assertion
        final Constructor<?>[] cons = FieldUtils.class.getDeclaredConstructors();
        // removed other assertion
        assertTrue(Modifier.isPublic(cons[0].getModifiers()));
    }

@Test
    public void testConstructor_4_oe() {
        // removed other assertion
        final Constructor<?>[] cons = FieldUtils.class.getDeclaredConstructors();
        // removed other assertion
        // removed other assertion
        assertTrue(Modifier.isPublic(FieldUtils.class.getModifiers()));
    }

@Test
    public void testConstructor_5_oe() {
        // removed other assertion
        final Constructor<?>[] cons = FieldUtils.class.getDeclaredConstructors();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(Modifier.isFinal(FieldUtils.class.getModifiers()));
    }

@Test
    public void testGetField_1_oe() {
        assertEquals(Foo.class, FieldUtils.getField(PublicChild.class, "VALUE").getDeclaringClass());
    }

@Test
    public void testGetField_2_oe() {
        // removed other assertion
        assertEquals(parentClass, FieldUtils.getField(PublicChild.class, "s").getDeclaringClass());
    }

@Test
    public void testGetField_3_oe() {
        // removed other assertion
        // removed other assertion
        assertNull(FieldUtils.getField(PublicChild.class, "b"));
    }

@Test
    public void testGetField_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(FieldUtils.getField(PublicChild.class, "i"));
    }

@Test
    public void testGetField_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(FieldUtils.getField(PublicChild.class, "d"));
    }

@Test
    public void testGetField_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Foo.class, FieldUtils.getField(PubliclyShadowedChild.class, "VALUE").getDeclaringClass());
    }

@Test
    public void testGetField_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getField(PubliclyShadowedChild.class, "s").getDeclaringClass());
    }

@Test
    public void testGetField_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getField(PubliclyShadowedChild.class, "b").getDeclaringClass());
    }

@Test
    public void testGetField_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getField(PubliclyShadowedChild.class, "i").getDeclaringClass());
    }

@Test
    public void testGetField_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getField(PubliclyShadowedChild.class, "d").getDeclaringClass());
    }

@Test
    public void testGetField_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Foo.class, FieldUtils.getField(PrivatelyShadowedChild.class, "VALUE").getDeclaringClass());
    }

@Test
    public void testGetField_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(parentClass, FieldUtils.getField(PrivatelyShadowedChild.class, "s").getDeclaringClass());
    }

@Test
    public void testGetField_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(FieldUtils.getField(PrivatelyShadowedChild.class, "b"));
    }

@Test
    public void testGetField_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(FieldUtils.getField(PrivatelyShadowedChild.class, "i"));
    }

@Test
    public void testGetField_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(FieldUtils.getField(PrivatelyShadowedChild.class, "d"));
    }

@Test
    public void testGetFieldIllegalArgumentException1_1_oe() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getField(null, "none"));
    }

@Test
    public void testGetFieldIllegalArgumentException2_1_oe() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getField(PublicChild.class, null));
    }

@Test
    public void testGetFieldIllegalArgumentException3_1_oe() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getField(PublicChild.class, ""));
    }

@Test
    public void testGetFieldIllegalArgumentException4_1_oe() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getField(PublicChild.class, " "));
    }

@Test
    public void testGetFieldForceAccess_1_oe() {
        assertEquals(PublicChild.class, FieldUtils.getField(PublicChild.class, "VALUE", true).getDeclaringClass());
    }

@Test
    public void testGetFieldForceAccess_2_oe() {
        // removed other assertion
        assertEquals(parentClass, FieldUtils.getField(PublicChild.class, "s", true).getDeclaringClass());
    }

@Test
    public void testGetFieldForceAccess_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(parentClass, FieldUtils.getField(PublicChild.class, "b", true).getDeclaringClass());
    }

@Test
    public void testGetFieldForceAccess_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(parentClass, FieldUtils.getField(PublicChild.class, "i", true).getDeclaringClass());
    }

@Test
    public void testGetFieldForceAccess_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(parentClass, FieldUtils.getField(PublicChild.class, "d", true).getDeclaringClass());
    }

@Test
    public void testGetFieldForceAccess_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Foo.class, FieldUtils.getField(PubliclyShadowedChild.class, "VALUE", true).getDeclaringClass());
    }

@Test
    public void testGetFieldForceAccess_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getField(PubliclyShadowedChild.class, "s", true).getDeclaringClass());
    }

@Test
    public void testGetFieldForceAccess_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getField(PubliclyShadowedChild.class, "b", true).getDeclaringClass());
    }

@Test
    public void testGetFieldForceAccess_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getField(PubliclyShadowedChild.class, "i", true).getDeclaringClass());
    }

@Test
    public void testGetFieldForceAccess_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getField(PubliclyShadowedChild.class, "d", true).getDeclaringClass());
    }

@Test
    public void testGetFieldForceAccess_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Foo.class, FieldUtils.getField(PrivatelyShadowedChild.class, "VALUE", true).getDeclaringClass());
    }

@Test
    public void testGetFieldForceAccess_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(PrivatelyShadowedChild.class, FieldUtils.getField(PrivatelyShadowedChild.class, "s", true).getDeclaringClass());
    }

@Test
    public void testGetFieldForceAccess_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(PrivatelyShadowedChild.class, FieldUtils.getField(PrivatelyShadowedChild.class, "b", true).getDeclaringClass());
    }

@Test
    public void testGetFieldForceAccess_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(PrivatelyShadowedChild.class, FieldUtils.getField(PrivatelyShadowedChild.class, "i", true).getDeclaringClass());
    }

@Test
    public void testGetFieldForceAccess_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(PrivatelyShadowedChild.class, FieldUtils.getField(PrivatelyShadowedChild.class, "d", true).getDeclaringClass());
    }

@Test
    public void testGetFieldForceAccessIllegalArgumentException1_1_oe() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getField(null, "none", true));
    }

@Test
    public void testGetFieldForceAccessIllegalArgumentException2_1_oe() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getField(PublicChild.class, null, true));
    }

@Test
    public void testGetFieldForceAccessIllegalArgumentException3_1_oe() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getField(PublicChild.class, "", true));
    }

@Test
    public void testGetFieldForceAccessIllegalArgumentException4_1_oe() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getField(PublicChild.class, " ", true));
    }

@Test
    public void testGetAllFields_1_oe() {
        assertArrayEquals(new Field[0], FieldUtils.getAllFields(Object.class));
    }

@Test
    public void testGetAllFields_2_oe() {
        // removed other assertion
        final Field[] fieldsNumber = sort(Number.class.getDeclaredFields());
        assertArrayEquals(fieldsNumber, sort(FieldUtils.getAllFields(Number.class)));
    }

@Test
    public void testGetAllFields_3_oe() {
        // removed other assertion
        final Field[] fieldsNumber = sort(Number.class.getDeclaredFields());
        // removed other assertion
        final Field[] fieldsInteger = Integer.class.getDeclaredFields();
        assertArrayEquals(sort(ArrayUtils.addAll(fieldsInteger, fieldsNumber)), sort(FieldUtils.getAllFields(Integer.class)));
    }

@Test
    public void testGetAllFields_4_oe() {
        // removed other assertion
        final Field[] fieldsNumber = sort(Number.class.getDeclaredFields());
        // removed other assertion
        final Field[] fieldsInteger = Integer.class.getDeclaredFields();
        // removed other assertion
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

@Test
    public void testGetAllFieldsList_1_oe() {
        assertEquals(0, FieldUtils.getAllFieldsList(Object.class).size());
    }

@Test
    public void testGetAllFieldsList_2_oe() {
        // removed other assertion
        final List<Field> fieldsNumber = Arrays.asList(Number.class.getDeclaredFields());
        assertEquals(fieldsNumber, FieldUtils.getAllFieldsList(Number.class));
    }

@Test
    public void testGetAllFieldsList_3_oe() {
        // removed other assertion
        final List<Field> fieldsNumber = Arrays.asList(Number.class.getDeclaredFields());
        // removed other assertion
        final List<Field> fieldsInteger = Arrays.asList(Integer.class.getDeclaredFields());
        final List<Field> allFieldsInteger = new ArrayList<>(fieldsInteger);
        allFieldsInteger.addAll(fieldsNumber);
        assertEquals(new HashSet(allFieldsInteger), new HashSet(FieldUtils.getAllFieldsList(Integer.class)));
    }

@Test
    public void testGetAllFieldsList_4_oe() {
        // removed other assertion
        final List<Field> fieldsNumber = Arrays.asList(Number.class.getDeclaredFields());
        // removed other assertion
        final List<Field> fieldsInteger = Arrays.asList(Integer.class.getDeclaredFields());
        final List<Field> allFieldsInteger = new ArrayList<>(fieldsInteger);
        allFieldsInteger.addAll(fieldsNumber);
        // removed other assertion
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
    public void testGetFieldsWithAnnotation_1_oe() throws NoSuchFieldException {
        assertArrayEquals(new Field[0], FieldUtils.getFieldsWithAnnotation(Object.class, Annotated.class));
    }

@Test
    public void testGetFieldsWithAnnotation_2_oe() throws NoSuchFieldException {
        // removed other assertion
        final Field[] annotatedFields = sort(new Field[] {
                FieldUtilsTest.class.getDeclaredField("publicChild"),
                FieldUtilsTest.class.getDeclaredField("privatelyShadowedChild") });
        assertArrayEquals(annotatedFields,sort(FieldUtils.getFieldsWithAnnotation(FieldUtilsTest.class,Annotated.class)));
    }

@Test
    public void testGetFieldsWithAnnotationIllegalArgumentException1_1_oe() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getFieldsWithAnnotation(FieldUtilsTest.class, null));
    }

@Test
    public void testGetFieldsWithAnnotationIllegalArgumentException2_1_oe() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getFieldsWithAnnotation(null, Annotated.class));
    }

@Test
    public void testGetFieldsWithAnnotationIllegalArgumentException3_1_oe() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getFieldsWithAnnotation(null, null));
    }

@Test
    public void testGetFieldsListWithAnnotation_1_oe() throws NoSuchFieldException {
        assertEquals(0, FieldUtils.getFieldsListWithAnnotation(Object.class, Annotated.class).size());
    }

@Test
    public void testGetFieldsListWithAnnotation_2_oe() throws NoSuchFieldException {
        // removed other assertion
        final List<Field> annotatedFields = Arrays.asList(
                FieldUtilsTest.class.getDeclaredField("publicChild"),
                FieldUtilsTest.class.getDeclaredField("privatelyShadowedChild")
        );
        final List<Field> fieldUtilsTestAnnotatedFields = FieldUtils.getFieldsListWithAnnotation(FieldUtilsTest.class, Annotated.class);
        assertEquals(annotatedFields.size(), fieldUtilsTestAnnotatedFields.size());
    }

@Test
    public void testGetFieldsListWithAnnotation_3_oe() throws NoSuchFieldException {
        // removed other assertion
        final List<Field> annotatedFields = Arrays.asList(
                FieldUtilsTest.class.getDeclaredField("publicChild"),
                FieldUtilsTest.class.getDeclaredField("privatelyShadowedChild")
        );
        final List<Field> fieldUtilsTestAnnotatedFields = FieldUtils.getFieldsListWithAnnotation(FieldUtilsTest.class, Annotated.class);
        // removed other assertion
        assertTrue(fieldUtilsTestAnnotatedFields.contains(annotatedFields.get(0)));
    }

@Test
    public void testGetFieldsListWithAnnotation_4_oe() throws NoSuchFieldException {
        // removed other assertion
        final List<Field> annotatedFields = Arrays.asList(
                FieldUtilsTest.class.getDeclaredField("publicChild"),
                FieldUtilsTest.class.getDeclaredField("privatelyShadowedChild")
        );
        final List<Field> fieldUtilsTestAnnotatedFields = FieldUtils.getFieldsListWithAnnotation(FieldUtilsTest.class, Annotated.class);
        // removed other assertion
        // removed other assertion
        assertTrue(fieldUtilsTestAnnotatedFields.contains(annotatedFields.get(1)));
    }

@Test
    public void testGetFieldsListWithAnnotationIllegalArgumentException1_1_oe() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getFieldsListWithAnnotation(FieldUtilsTest.class, null));
    }

@Test
    public void testGetFieldsListWithAnnotationIllegalArgumentException2_1_oe() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getFieldsListWithAnnotation(null, Annotated.class));
    }

@Test
    public void testGetFieldsListWithAnnotationIllegalArgumentException3_1_oe() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getFieldsListWithAnnotation(null, null));
    }

@Test
    public void testGetDeclaredField_1_oe() {
        assertNull(FieldUtils.getDeclaredField(PublicChild.class, "VALUE"));
    }

@Test
    public void testGetDeclaredField_2_oe() {
        // removed other assertion
        assertNull(FieldUtils.getDeclaredField(PublicChild.class, "s"));
    }

@Test
    public void testGetDeclaredField_3_oe() {
        // removed other assertion
        // removed other assertion
        assertNull(FieldUtils.getDeclaredField(PublicChild.class, "b"));
    }

@Test
    public void testGetDeclaredField_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(FieldUtils.getDeclaredField(PublicChild.class, "i"));
    }

@Test
    public void testGetDeclaredField_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(FieldUtils.getDeclaredField(PublicChild.class, "d"));
    }

@Test
    public void testGetDeclaredField_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "VALUE"));
    }

@Test
    public void testGetDeclaredField_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "s").getDeclaringClass());
    }

@Test
    public void testGetDeclaredField_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "b").getDeclaringClass());
    }

@Test
    public void testGetDeclaredField_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "i").getDeclaringClass());
    }

@Test
    public void testGetDeclaredField_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "d").getDeclaringClass());
    }

@Test
    public void testGetDeclaredField_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "VALUE"));
    }

@Test
    public void testGetDeclaredField_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "s"));
    }

@Test
    public void testGetDeclaredField_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "b"));
    }

@Test
    public void testGetDeclaredField_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "i"));
    }

@Test
    public void testGetDeclaredField_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "d"));
    }

@Test
    public void testGetDeclaredFieldAccessIllegalArgumentException1_1_oe() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getDeclaredField(null, "none"));
    }

@Test
    public void testGetDeclaredFieldAccessIllegalArgumentException2_1_oe() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getDeclaredField(PublicChild.class, null));
    }

@Test
    public void testGetDeclaredFieldAccessIllegalArgumentException3_1_oe() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getDeclaredField(PublicChild.class, ""));
    }

@Test
    public void testGetDeclaredFieldAccessIllegalArgumentException4_1_oe() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getDeclaredField(PublicChild.class, " "));
    }

@Test
    public void testGetDeclaredFieldForceAccess_1_oe() {
        assertEquals(PublicChild.class, FieldUtils.getDeclaredField(PublicChild.class, "VALUE", true).getDeclaringClass());
    }

@Test
    public void testGetDeclaredFieldForceAccess_2_oe() {
        // removed other assertion
        assertNull(FieldUtils.getDeclaredField(PublicChild.class, "s", true));
    }

@Test
    public void testGetDeclaredFieldForceAccess_3_oe() {
        // removed other assertion
        // removed other assertion
        assertNull(FieldUtils.getDeclaredField(PublicChild.class, "b", true));
    }

@Test
    public void testGetDeclaredFieldForceAccess_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(FieldUtils.getDeclaredField(PublicChild.class, "i", true));
    }

@Test
    public void testGetDeclaredFieldForceAccess_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(FieldUtils.getDeclaredField(PublicChild.class, "d", true));
    }

@Test
    public void testGetDeclaredFieldForceAccess_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "VALUE", true));
    }

@Test
    public void testGetDeclaredFieldForceAccess_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "s", true).getDeclaringClass());
    }

@Test
    public void testGetDeclaredFieldForceAccess_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "b", true).getDeclaringClass());
    }

@Test
    public void testGetDeclaredFieldForceAccess_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "i", true).getDeclaringClass());
    }

@Test
    public void testGetDeclaredFieldForceAccess_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(PubliclyShadowedChild.class, FieldUtils.getDeclaredField(PubliclyShadowedChild.class, "d", true).getDeclaringClass());
    }

@Test
    public void testGetDeclaredFieldForceAccess_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "VALUE", true));
    }

@Test
    public void testGetDeclaredFieldForceAccess_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(PrivatelyShadowedChild.class, FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "s", true).getDeclaringClass());
    }

@Test
    public void testGetDeclaredFieldForceAccess_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(PrivatelyShadowedChild.class, FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "b", true).getDeclaringClass());
    }

@Test
    public void testGetDeclaredFieldForceAccess_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(PrivatelyShadowedChild.class, FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "i", true).getDeclaringClass());
    }

@Test
    public void testGetDeclaredFieldForceAccess_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(PrivatelyShadowedChild.class, FieldUtils.getDeclaredField(PrivatelyShadowedChild.class, "d", true).getDeclaringClass());
    }

@Test
    public void testGetDeclaredFieldForceAccessIllegalArgumentException1_1_oe() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getDeclaredField(null, "none", true));
    }

@Test
    public void testGetDeclaredFieldForceAccessIllegalArgumentException2_1_oe() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getDeclaredField(PublicChild.class, null, true));
    }

@Test
    public void testGetDeclaredFieldForceAccessIllegalArgumentException3_1_oe() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getDeclaredField(PublicChild.class, "", true));
    }

@Test
    public void testGetDeclaredFieldForceAccessIllegalArgumentException4_1_oe() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getDeclaredField(PublicChild.class, " ", true));
    }

@Test
    public void testReadStaticField_1_oe() throws Exception {
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(FieldUtils.getField(Foo.class, "VALUE")));
    }

@Test
    public void testReadStaticFieldIllegalArgumentException1_1_oe() {
        assertThrows(NullPointerException.class, () -> FieldUtils.readStaticField(null));
    }

@Test
    public void testReadStaticFieldIllegalArgumentException2_1_oe() throws Exception {
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(FieldUtils.getField(Foo.class, "VALUE")));
    }

@Test
    public void testReadStaticFieldIllegalArgumentException2_2_oe() throws Exception {
        // removed other assertion
        final Field nonStaticField = FieldUtils.getField(PublicChild.class, "s");
        assumeTrue(nonStaticField != null);
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readStaticField(nonStaticField));
    }

@Test
    public void testReadStaticFieldForceAccess_1_oe() throws Exception {
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(FieldUtils.getField(Foo.class, "VALUE")));
    }

@Test
    public void testReadStaticFieldForceAccess_2_oe() throws Exception {
        // removed other assertion
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(FieldUtils.getField(PublicChild.class, "VALUE")));
    }

@Test
    public void testReadStaticFieldForceAccessIllegalArgumentException1_1_oe() {
        assertThrows(NullPointerException.class, () -> FieldUtils.readStaticField(null, true));
    }

@Test
    public void testReadStaticFieldForceAccessIllegalArgumentException2_1_oe() {
        final Field nonStaticField = FieldUtils.getField(PublicChild.class, "s", true);
        assumeTrue(nonStaticField != null);
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readStaticField(nonStaticField));
    }

@Test
    public void testReadNamedStaticField_1_oe() throws Exception {
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(Foo.class, "VALUE"));
    }

@Test
    public void testReadNamedStaticField_2_oe() throws Exception {
        // removed other assertion
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(PubliclyShadowedChild.class, "VALUE"));
    }

@Test
    public void testReadNamedStaticField_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(PrivatelyShadowedChild.class, "VALUE"));
    }

@Test
    public void testReadNamedStaticField_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(PublicChild.class, "VALUE"));
    }

@Test
    public void testReadNamedStaticField_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertThrows( NullPointerException.class, () -> FieldUtils.readStaticField(null, "none"), "null class should cause an IllegalArgumentException");
    }

@Test
    public void testReadNamedStaticField_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        assertThrows( IllegalArgumentException.class, () -> FieldUtils.readStaticField(Foo.class, null), "null field name should cause an IllegalArgumentException");
    }

@Test
    public void testReadNamedStaticField_7_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( IllegalArgumentException.class, () -> FieldUtils.readStaticField(Foo.class, ""), "empty field name should cause an IllegalArgumentException");
    }

@Test
    public void testReadNamedStaticField_8_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( IllegalArgumentException.class, () -> FieldUtils.readStaticField(Foo.class, " "), "blank field name should cause an IllegalArgumentException");
    }

@Test
    public void testReadNamedStaticField_9_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( NullPointerException.class, () -> FieldUtils.readStaticField(Foo.class, "does_not_exist"), "a field that doesn't exist should cause an IllegalArgumentException");
    }

@Test
    public void testReadNamedStaticField_10_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( IllegalArgumentException.class, () -> FieldUtils.readStaticField(PublicChild.class, "s"), "non-static field should cause an IllegalArgumentException");
    }

@Test
    public void testReadNamedStaticFieldForceAccess_1_oe() throws Exception {
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(Foo.class, "VALUE", true));
    }

@Test
    public void testReadNamedStaticFieldForceAccess_2_oe() throws Exception {
        // removed other assertion
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(PubliclyShadowedChild.class, "VALUE", true));
    }

@Test
    public void testReadNamedStaticFieldForceAccess_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals(Foo.VALUE, FieldUtils.readStaticField(PrivatelyShadowedChild.class, "VALUE", true));
    }

@Test
    public void testReadNamedStaticFieldForceAccess_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("child", FieldUtils.readStaticField(PublicChild.class, "VALUE", true));
    }

@Test
    public void testReadNamedStaticFieldForceAccess_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertThrows( NullPointerException.class, () -> FieldUtils.readStaticField(null, "none", true), "null class should cause an IllegalArgumentException");
    }

@Test
    public void testReadNamedStaticFieldForceAccess_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        assertThrows( IllegalArgumentException.class, () -> FieldUtils.readStaticField(Foo.class, null, true), "null field name should cause an IllegalArgumentException");
    }

@Test
    public void testReadNamedStaticFieldForceAccess_7_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( IllegalArgumentException.class, () -> FieldUtils.readStaticField(Foo.class, "", true), "empty field name should cause an IllegalArgumentException");
    }

@Test
    public void testReadNamedStaticFieldForceAccess_8_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( IllegalArgumentException.class, () -> FieldUtils.readStaticField(Foo.class, " ", true), "blank field name should cause an IllegalArgumentException");
    }

@Test
    public void testReadNamedStaticFieldForceAccess_9_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( NullPointerException.class, () -> FieldUtils.readStaticField(Foo.class, "does_not_exist", true), "a field that doesn't exist should cause an IllegalArgumentException");
    }

@Test
    public void testReadNamedStaticFieldForceAccess_10_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( IllegalArgumentException.class, () -> FieldUtils.readStaticField(PublicChild.class, "s", false), "non-static field should cause an IllegalArgumentException");
    }

@Test
    public void testReadDeclaredNamedStaticField_1_oe() throws Exception {
        assertEquals(Foo.VALUE, FieldUtils.readDeclaredStaticField(Foo.class, "VALUE"));
    }

@Test
    public void testReadDeclaredNamedStaticField_2_oe() throws Exception {
        // removed other assertion
        assertThrows( NullPointerException.class, () -> FieldUtils.readDeclaredStaticField(PublicChild.class, "VALUE"));
    }

@Test
    public void testReadDeclaredNamedStaticField_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertThrows( NullPointerException.class, () -> FieldUtils.readDeclaredStaticField(PubliclyShadowedChild.class, "VALUE"));
    }

@Test
    public void testReadDeclaredNamedStaticField_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( NullPointerException.class, () -> FieldUtils.readDeclaredStaticField(PrivatelyShadowedChild.class, "VALUE"));
    }

@Test
    public void testReadDeclaredNamedStaticFieldForceAccess_1_oe() throws Exception {
        assertEquals(Foo.VALUE, FieldUtils.readDeclaredStaticField(Foo.class, "VALUE", true));
    }

@Test
    public void testReadDeclaredNamedStaticFieldForceAccess_2_oe() throws Exception {
        // removed other assertion
        assertEquals("child", FieldUtils.readDeclaredStaticField(PublicChild.class, "VALUE", true));
    }

@Test
    public void testReadDeclaredNamedStaticFieldForceAccess_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertThrows( NullPointerException.class, () -> FieldUtils.readDeclaredStaticField(PubliclyShadowedChild.class, "VALUE", true));
    }

@Test
    public void testReadDeclaredNamedStaticFieldForceAccess_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( NullPointerException.class, () -> FieldUtils.readDeclaredStaticField(PrivatelyShadowedChild.class, "VALUE", true));
    }

@Test
    public void testReadField_1_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        assertEquals("s", FieldUtils.readField(parentS, publicChild));
    }

@Test
    public void testReadField_2_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        // removed other assertion
        assertEquals("s", FieldUtils.readField(parentS, publiclyShadowedChild));
    }

@Test
    public void testReadField_3_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        // removed other assertion
        // removed other assertion
        assertEquals("s", FieldUtils.readField(parentS, privatelyShadowedChild));
    }

@Test
    public void testReadField_4_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        assertEquals(Boolean.FALSE, FieldUtils.readField(parentB, publicChild));
    }

@Test
    public void testReadField_5_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        // removed other assertion
        assertEquals(Boolean.FALSE, FieldUtils.readField(parentB, publiclyShadowedChild));
    }

@Test
    public void testReadField_6_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.FALSE, FieldUtils.readField(parentB, privatelyShadowedChild));
    }

@Test
    public void testReadField_7_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        assertEquals(I0, FieldUtils.readField(parentI, publicChild));
    }

@Test
    public void testReadField_8_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        // removed other assertion
        assertEquals(I0, FieldUtils.readField(parentI, publiclyShadowedChild));
    }

@Test
    public void testReadField_9_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        // removed other assertion
        // removed other assertion
        assertEquals(I0, FieldUtils.readField(parentI, privatelyShadowedChild));
    }

@Test
    public void testReadField_10_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentD = FieldUtils.getDeclaredField(parentClass, "d", true);
        assertEquals(D0, FieldUtils.readField(parentD, publicChild));
    }

@Test
    public void testReadField_11_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentD = FieldUtils.getDeclaredField(parentClass, "d", true);
        // removed other assertion
        assertEquals(D0, FieldUtils.readField(parentD, publiclyShadowedChild));
    }

@Test
    public void testReadField_12_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentD = FieldUtils.getDeclaredField(parentClass, "d", true);
        // removed other assertion
        // removed other assertion
        assertEquals(D0, FieldUtils.readField(parentD, privatelyShadowedChild));
    }

@Test
    public void testReadField_13_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentD = FieldUtils.getDeclaredField(parentClass, "d", true);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertThrows( NullPointerException.class, () -> FieldUtils.readField(null, publicChild), "a null field should cause an IllegalArgumentException");
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
        // removed other assertion
        assertEquals("s", FieldUtils.readField(parentS, publiclyShadowedChild, true));
    }

@Test
    public void testReadFieldForceAccess_3_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        parentS.setAccessible(false);
        // removed other assertion
        // removed other assertion
        assertEquals("s", FieldUtils.readField(parentS, privatelyShadowedChild, true));
    }

@Test
    public void testReadFieldForceAccess_4_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        parentS.setAccessible(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        parentB.setAccessible(false);
        assertEquals(Boolean.FALSE, FieldUtils.readField(parentB, publicChild, true));
    }

@Test
    public void testReadFieldForceAccess_5_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        parentS.setAccessible(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        parentB.setAccessible(false);
        // removed other assertion
        assertEquals(Boolean.FALSE, FieldUtils.readField(parentB, publiclyShadowedChild, true));
    }

@Test
    public void testReadFieldForceAccess_6_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        parentS.setAccessible(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        parentB.setAccessible(false);
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.FALSE, FieldUtils.readField(parentB, privatelyShadowedChild, true));
    }

@Test
    public void testReadFieldForceAccess_7_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        parentS.setAccessible(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        parentB.setAccessible(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        parentI.setAccessible(false);
        assertEquals(I0, FieldUtils.readField(parentI, publicChild, true));
    }

@Test
    public void testReadFieldForceAccess_8_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        parentS.setAccessible(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        parentB.setAccessible(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        parentI.setAccessible(false);
        // removed other assertion
        assertEquals(I0, FieldUtils.readField(parentI, publiclyShadowedChild, true));
    }

@Test
    public void testReadFieldForceAccess_9_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        parentS.setAccessible(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        parentB.setAccessible(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        parentI.setAccessible(false);
        // removed other assertion
        // removed other assertion
        assertEquals(I0, FieldUtils.readField(parentI, privatelyShadowedChild, true));
    }

@Test
    public void testReadFieldForceAccess_10_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        parentS.setAccessible(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        parentB.setAccessible(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        parentI.setAccessible(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentD = FieldUtils.getDeclaredField(parentClass, "d", true);
        parentD.setAccessible(false);
        assertEquals(D0, FieldUtils.readField(parentD, publicChild, true));
    }

@Test
    public void testReadFieldForceAccess_11_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        parentS.setAccessible(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        parentB.setAccessible(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        parentI.setAccessible(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentD = FieldUtils.getDeclaredField(parentClass, "d", true);
        parentD.setAccessible(false);
        // removed other assertion
        assertEquals(D0, FieldUtils.readField(parentD, publiclyShadowedChild, true));
    }

@Test
    public void testReadFieldForceAccess_12_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        parentS.setAccessible(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        parentB.setAccessible(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        parentI.setAccessible(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentD = FieldUtils.getDeclaredField(parentClass, "d", true);
        parentD.setAccessible(false);
        // removed other assertion
        // removed other assertion
        assertEquals(D0, FieldUtils.readField(parentD, privatelyShadowedChild, true));
    }

@Test
    public void testReadFieldForceAccess_13_oe() throws Exception {
        final Field parentS = FieldUtils.getDeclaredField(parentClass, "s");
        parentS.setAccessible(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentB = FieldUtils.getDeclaredField(parentClass, "b", true);
        parentB.setAccessible(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentI = FieldUtils.getDeclaredField(parentClass, "i", true);
        parentI.setAccessible(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final Field parentD = FieldUtils.getDeclaredField(parentClass, "d", true);
        parentD.setAccessible(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertThrows( NullPointerException.class, () -> FieldUtils.readField(null, publicChild, true), "a null field should cause an IllegalArgumentException");
    }

@Test
    public void testReadNamedField_1_oe() throws Exception {
        assertEquals("s", FieldUtils.readField(publicChild, "s"));
    }

@Test
    public void testReadNamedField_2_oe() throws Exception {
        // removed other assertion
        assertEquals("ss", FieldUtils.readField(publiclyShadowedChild, "s"));
    }

@Test
    public void testReadNamedField_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals("s", FieldUtils.readField(privatelyShadowedChild, "s"));
    }

@Test
    public void testReadNamedField_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertThrows( IllegalArgumentException.class, () -> FieldUtils.readField(publicChild, null), "a null field name should cause an IllegalArgumentException");
    }

@Test
    public void testReadNamedField_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        assertThrows( IllegalArgumentException.class, () -> FieldUtils.readField(publicChild, ""), "an empty field name should cause an IllegalArgumentException");
    }

@Test
    public void testReadNamedField_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( IllegalArgumentException.class, () -> FieldUtils.readField(publicChild, " "), "a blank field name should cause an IllegalArgumentException");
    }

@Test
    public void testReadNamedField_7_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( NullPointerException.class, () -> FieldUtils.readField((Object) null, "none"), "a null target should cause an IllegalArgumentException");
    }

@Test
    public void testReadNamedField_8_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readField(publicChild, "b"));
    }

@Test
    public void testReadNamedField_9_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertEquals(Boolean.TRUE, FieldUtils.readField(publiclyShadowedChild, "b"));
    }

@Test
    public void testReadNamedField_10_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        assertThrows( IllegalArgumentException.class, () -> FieldUtils.readField(privatelyShadowedChild, "b"));
    }

@Test
    public void testReadNamedField_11_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readField(publicChild, "i"));
    }

@Test
    public void testReadNamedField_12_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(I1, FieldUtils.readField(publiclyShadowedChild, "i"));
    }

@Test
    public void testReadNamedField_13_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readField(privatelyShadowedChild, "i"));
    }

@Test
    public void testReadNamedField_14_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readField(publicChild, "d"));
    }

@Test
    public void testReadNamedField_15_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(D1, FieldUtils.readField(publiclyShadowedChild, "d"));
    }

@Test
    public void testReadNamedField_16_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readField(privatelyShadowedChild, "d"));
    }

@Test
    public void testReadNamedFieldForceAccess_1_oe() throws Exception {
        assertEquals("s", FieldUtils.readField(publicChild, "s", true));
    }

@Test
    public void testReadNamedFieldForceAccess_2_oe() throws Exception {
        // removed other assertion
        assertEquals("ss", FieldUtils.readField(publiclyShadowedChild, "s", true));
    }

@Test
    public void testReadNamedFieldForceAccess_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertEquals("ss", FieldUtils.readField(privatelyShadowedChild, "s", true));
    }

@Test
    public void testReadNamedFieldForceAccess_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.FALSE, FieldUtils.readField(publicChild, "b", true));
    }

@Test
    public void testReadNamedFieldForceAccess_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.TRUE, FieldUtils.readField(publiclyShadowedChild, "b", true));
    }

@Test
    public void testReadNamedFieldForceAccess_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.TRUE, FieldUtils.readField(privatelyShadowedChild, "b", true));
    }

@Test
    public void testReadNamedFieldForceAccess_7_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(I0, FieldUtils.readField(publicChild, "i", true));
    }

@Test
    public void testReadNamedFieldForceAccess_8_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(I1, FieldUtils.readField(publiclyShadowedChild, "i", true));
    }

@Test
    public void testReadNamedFieldForceAccess_9_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(I1, FieldUtils.readField(privatelyShadowedChild, "i", true));
    }

@Test
    public void testReadNamedFieldForceAccess_10_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(D0, FieldUtils.readField(publicChild, "d", true));
    }

@Test
    public void testReadNamedFieldForceAccess_11_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(D1, FieldUtils.readField(publiclyShadowedChild, "d", true));
    }

@Test
    public void testReadNamedFieldForceAccess_12_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(D1, FieldUtils.readField(privatelyShadowedChild, "d", true));
    }

@Test
    public void testReadNamedFieldForceAccess_13_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertThrows( IllegalArgumentException.class, () -> FieldUtils.readField(publicChild, null, true), "a null field name should cause an IllegalArgumentException");
    }

@Test
    public void testReadNamedFieldForceAccess_14_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        assertThrows( IllegalArgumentException.class, () -> FieldUtils.readField(publicChild, "", true), "an empty field name should cause an IllegalArgumentException");
    }

@Test
    public void testReadNamedFieldForceAccess_15_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( IllegalArgumentException.class, () -> FieldUtils.readField(publicChild, " ", true), "a blank field name should cause an IllegalArgumentException");
    }

@Test
    public void testReadNamedFieldForceAccess_16_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( NullPointerException.class, () -> FieldUtils.readField((Object) null, "none", true), "a null target should cause an IllegalArgumentException");
    }

@Test
    public void testReadDeclaredNamedField_1_oe() throws Exception {
        assertThrows( IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(publicChild, null), "a null field name should cause an IllegalArgumentException");
    }

@Test
    public void testReadDeclaredNamedField_2_oe() throws Exception {
        // removed other assertion

        assertThrows( IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(publicChild, ""), "an empty field name should cause an IllegalArgumentException");
    }

@Test
    public void testReadDeclaredNamedField_3_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        assertThrows( IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(publicChild, " "), "a blank field name should cause an IllegalArgumentException");
    }

@Test
    public void testReadDeclaredNamedField_4_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( NullPointerException.class, () -> FieldUtils.readDeclaredField(null, "none"), "a null target should cause an IllegalArgumentException");
    }

@Test
    public void testReadDeclaredNamedField_5_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(publicChild, "s"));
    }

@Test
    public void testReadDeclaredNamedField_6_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        assertEquals("ss", FieldUtils.readDeclaredField(publiclyShadowedChild, "s"));
    }

@Test
    public void testReadDeclaredNamedField_7_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(privatelyShadowedChild, "s"));
    }

@Test
    public void testReadDeclaredNamedField_8_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(publicChild, "b"));
    }

@Test
    public void testReadDeclaredNamedField_9_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.TRUE, FieldUtils.readDeclaredField(publiclyShadowedChild, "b"));
    }

@Test
    public void testReadDeclaredNamedField_10_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(privatelyShadowedChild, "b"));
    }

@Test
    public void testReadDeclaredNamedField_11_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(publicChild, "i"));
    }

@Test
    public void testReadDeclaredNamedField_12_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(I1, FieldUtils.readDeclaredField(publiclyShadowedChild, "i"));
    }

@Test
    public void testReadDeclaredNamedField_13_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(privatelyShadowedChild, "i"));
    }

@Test
    public void testReadDeclaredNamedField_14_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(publicChild, "d"));
    }

@Test
    public void testReadDeclaredNamedField_15_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(D1, FieldUtils.readDeclaredField(publiclyShadowedChild, "d"));
    }

@Test
    public void testReadDeclaredNamedField_16_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(privatelyShadowedChild, "d"));
    }

@Test
    public void testReadDeclaredNamedFieldForceAccess_1_oe() throws Exception {
        assertThrows( IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(publicChild, null, true), "a null field name should cause an IllegalArgumentException");
    }

@Test
    public void testReadDeclaredNamedFieldForceAccess_2_oe() throws Exception {
        // removed other assertion

        assertThrows( IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(publicChild, "", true), "an empty field name should cause an IllegalArgumentException");
    }

@Test
    public void testReadDeclaredNamedFieldForceAccess_3_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        assertThrows( IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(publicChild, " ", true), "a blank field name should cause an IllegalArgumentException");
    }

@Test
    public void testReadDeclaredNamedFieldForceAccess_4_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( NullPointerException.class, () -> FieldUtils.readDeclaredField(null, "none", true), "a null target should cause an IllegalArgumentException");
    }

@Test
    public void testReadDeclaredNamedFieldForceAccess_5_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(publicChild, "s", true));
    }

@Test
    public void testReadDeclaredNamedFieldForceAccess_6_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        assertEquals("ss", FieldUtils.readDeclaredField(publiclyShadowedChild, "s", true));
    }

@Test
    public void testReadDeclaredNamedFieldForceAccess_7_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("ss", FieldUtils.readDeclaredField(privatelyShadowedChild, "s", true));
    }

@Test
    public void testReadDeclaredNamedFieldForceAccess_8_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(publicChild, "b", true));
    }

@Test
    public void testReadDeclaredNamedFieldForceAccess_9_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.TRUE, FieldUtils.readDeclaredField(publiclyShadowedChild, "b", true));
    }

@Test
    public void testReadDeclaredNamedFieldForceAccess_10_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.TRUE, FieldUtils.readDeclaredField(privatelyShadowedChild, "b", true));
    }

@Test
    public void testReadDeclaredNamedFieldForceAccess_11_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(publicChild, "i", true));
    }

@Test
    public void testReadDeclaredNamedFieldForceAccess_12_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(I1, FieldUtils.readDeclaredField(publiclyShadowedChild, "i", true));
    }

@Test
    public void testReadDeclaredNamedFieldForceAccess_13_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(I1, FieldUtils.readDeclaredField(privatelyShadowedChild, "i", true));
    }

@Test
    public void testReadDeclaredNamedFieldForceAccess_14_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.readDeclaredField(publicChild, "d", true));
    }

@Test
    public void testReadDeclaredNamedFieldForceAccess_15_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(D1, FieldUtils.readDeclaredField(publiclyShadowedChild, "d", true));
    }

@Test
    public void testReadDeclaredNamedFieldForceAccess_16_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // removed other assertion
        assertThrows( IllegalAccessException.class, () -> FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("mutableProtected"), "new"));
    }

@Test
    public void testWriteStaticField_3_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new");
        // removed other assertion
        // removed other assertion
        assertThrows( IllegalAccessException.class, () -> FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("mutablePackage"), "new"));
    }

@Test
    public void testWriteStaticField_4_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( IllegalAccessException.class, () -> FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("mutablePrivate"), "new"));
    }

@Test
    public void testWriteStaticField_5_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( IllegalAccessException.class, () -> FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PUBLIC"), "new"));
    }

@Test
    public void testWriteStaticField_6_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( IllegalAccessException.class, () -> FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PROTECTED"), "new"));
    }

@Test
    public void testWriteStaticField_7_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( IllegalAccessException.class, () -> FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PACKAGE"), "new"));
    }

@Test
    public void testWriteStaticField_8_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( IllegalAccessException.class, () -> FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE"), "new"));
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
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutableProtected");
        FieldUtils.writeStaticField(field, "new", true);
        assertEquals("new", StaticContainer.getMutableProtected());
    }

@Test
    public void testWriteStaticFieldForceAccess_3_oe() throws Exception {
        Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutableProtected");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutablePackage");
        FieldUtils.writeStaticField(field, "new", true);
        assertEquals("new", StaticContainer.getMutablePackage());
    }

@Test
    public void testWriteStaticFieldForceAccess_4_oe() throws Exception {
        Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutableProtected");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutablePackage");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutablePrivate");
        FieldUtils.writeStaticField(field, "new", true);
        assertEquals("new", StaticContainer.getMutablePrivate());
    }

@Test
    public void testWriteStaticFieldForceAccess_5_oe() throws Exception {
        Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutableProtected");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutablePackage");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutablePrivate");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        assertThrows( IllegalAccessException.class, () -> FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PUBLIC"), "new", true));
    }

@Test
    public void testWriteStaticFieldForceAccess_6_oe() throws Exception {
        Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutableProtected");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutablePackage");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutablePrivate");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        // removed other assertion
        assertThrows( IllegalAccessException.class, () -> FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PROTECTED"), "new", true));
    }

@Test
    public void testWriteStaticFieldForceAccess_7_oe() throws Exception {
        Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutableProtected");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutablePackage");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutablePrivate");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( IllegalAccessException.class, () -> FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PACKAGE"), "new", true));
    }

@Test
    public void testWriteStaticFieldForceAccess_8_oe() throws Exception {
        Field field = StaticContainer.class.getDeclaredField("mutablePublic");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutableProtected");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutablePackage");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        field = StaticContainer.class.getDeclaredField("mutablePrivate");
        FieldUtils.writeStaticField(field, "new", true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( IllegalAccessException.class, () -> FieldUtils.writeStaticField(StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE"), "new", true));
    }

@Test
    public void testWriteNamedStaticField_1_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new");
        assertEquals("new", StaticContainer.mutablePublic);
    }

@Test
    public void testWriteNamedStaticField_2_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new");
        // removed other assertion
        assertThrows( NullPointerException.class, () -> FieldUtils.writeStaticField(StaticContainerChild.class, "mutableProtected", "new"));
    }

@Test
    public void testWriteNamedStaticField_3_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new");
        // removed other assertion
        // removed other assertion
        assertThrows( NullPointerException.class, () -> FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePackage", "new"));
    }

@Test
    public void testWriteNamedStaticField_4_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( NullPointerException.class, () -> FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePrivate", "new"));
    }

@Test
    public void testWriteNamedStaticField_5_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( IllegalAccessException.class, () -> FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PUBLIC", "new"));
    }

@Test
    public void testWriteNamedStaticField_6_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( NullPointerException.class, () -> FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PROTECTED", "new"));
    }

@Test
    public void testWriteNamedStaticField_7_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( NullPointerException.class, () -> FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PACKAGE", "new"));
    }

@Test
    public void testWriteNamedStaticField_8_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( NullPointerException.class, () -> FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PRIVATE", "new"));
    }

@Test
    public void testWriteNamedStaticFieldForceAccess_1_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new", true);
        assertEquals("new", StaticContainer.mutablePublic);
    }

@Test
    public void testWriteNamedStaticFieldForceAccess_2_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutableProtected", "new", true);
        assertEquals("new", StaticContainer.getMutableProtected());
    }

@Test
    public void testWriteNamedStaticFieldForceAccess_3_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutableProtected", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePackage", "new", true);
        assertEquals("new", StaticContainer.getMutablePackage());
    }

@Test
    public void testWriteNamedStaticFieldForceAccess_4_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutableProtected", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePackage", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePrivate", "new", true);
        assertEquals("new", StaticContainer.getMutablePrivate());
    }

@Test
    public void testWriteNamedStaticFieldForceAccess_5_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutableProtected", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePackage", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePrivate", "new", true);
        // removed other assertion
        assertThrows( IllegalAccessException.class, () -> FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PUBLIC", "new", true));
    }

@Test
    public void testWriteNamedStaticFieldForceAccess_6_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutableProtected", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePackage", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePrivate", "new", true);
        // removed other assertion
        // removed other assertion
        assertThrows( IllegalAccessException.class, () -> FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PROTECTED", "new", true));
    }

@Test
    public void testWriteNamedStaticFieldForceAccess_7_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutableProtected", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePackage", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePrivate", "new", true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( IllegalAccessException.class, () -> FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PACKAGE", "new", true));
    }

@Test
    public void testWriteNamedStaticFieldForceAccess_8_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePublic", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutableProtected", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePackage", "new", true);
        // removed other assertion
        FieldUtils.writeStaticField(StaticContainerChild.class, "mutablePrivate", "new", true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( IllegalAccessException.class, () -> FieldUtils.writeStaticField(StaticContainerChild.class, "IMMUTABLE_PRIVATE", "new", true));
    }

@Test
    public void testWriteDeclaredNamedStaticField_1_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainer.class, "mutablePublic", "new");
        assertEquals("new", StaticContainer.mutablePublic);
    }

@Test
    public void testWriteDeclaredNamedStaticField_2_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainer.class, "mutablePublic", "new");
        // removed other assertion
        assertThrows( NullPointerException.class, () -> FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutableProtected", "new"));
    }

@Test
    public void testWriteDeclaredNamedStaticField_3_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainer.class, "mutablePublic", "new");
        // removed other assertion
        // removed other assertion
        assertThrows( NullPointerException.class, () -> FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePackage", "new"));
    }

@Test
    public void testWriteDeclaredNamedStaticField_4_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainer.class, "mutablePublic", "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( NullPointerException.class, () -> FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePrivate", "new"));
    }

@Test
    public void testWriteDeclaredNamedStaticField_5_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainer.class, "mutablePublic", "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( IllegalAccessException.class, () -> FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PUBLIC", "new"));
    }

@Test
    public void testWriteDeclaredNamedStaticField_6_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainer.class, "mutablePublic", "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( NullPointerException.class, () -> FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PROTECTED", "new"));
    }

@Test
    public void testWriteDeclaredNamedStaticField_7_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainer.class, "mutablePublic", "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( NullPointerException.class, () -> FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PACKAGE", "new"));
    }

@Test
    public void testWriteDeclaredNamedStaticField_8_oe() throws Exception {
        FieldUtils.writeStaticField(StaticContainer.class, "mutablePublic", "new");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( NullPointerException.class, () -> FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PRIVATE", "new"));
    }

@Test
    public void testWriteDeclaredNamedStaticFieldForceAccess_1_oe() throws Exception {
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePublic", "new", true);
        assertEquals("new", StaticContainer.mutablePublic);
    }

@Test
    public void testWriteDeclaredNamedStaticFieldForceAccess_2_oe() throws Exception {
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePublic", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutableProtected", "new", true);
        assertEquals("new", StaticContainer.getMutableProtected());
    }

@Test
    public void testWriteDeclaredNamedStaticFieldForceAccess_3_oe() throws Exception {
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePublic", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutableProtected", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePackage", "new", true);
        assertEquals("new", StaticContainer.getMutablePackage());
    }

@Test
    public void testWriteDeclaredNamedStaticFieldForceAccess_4_oe() throws Exception {
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePublic", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutableProtected", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePackage", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePrivate", "new", true);
        assertEquals("new", StaticContainer.getMutablePrivate());
    }

@Test
    public void testWriteDeclaredNamedStaticFieldForceAccess_5_oe() throws Exception {
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePublic", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutableProtected", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePackage", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePrivate", "new", true);
        // removed other assertion
        assertThrows( IllegalAccessException.class, () -> FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PUBLIC", "new", true));
    }

@Test
    public void testWriteDeclaredNamedStaticFieldForceAccess_6_oe() throws Exception {
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePublic", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutableProtected", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePackage", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePrivate", "new", true);
        // removed other assertion
        // removed other assertion
        assertThrows( IllegalAccessException.class, () -> FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PROTECTED", "new", true));
    }

@Test
    public void testWriteDeclaredNamedStaticFieldForceAccess_7_oe() throws Exception {
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePublic", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutableProtected", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePackage", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePrivate", "new", true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( IllegalAccessException.class, () -> FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PACKAGE", "new", true));
    }

@Test
    public void testWriteDeclaredNamedStaticFieldForceAccess_8_oe() throws Exception {
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePublic", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutableProtected", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePackage", "new", true);
        // removed other assertion
        FieldUtils.writeDeclaredStaticField(StaticContainer.class, "mutablePrivate", "new", true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( IllegalAccessException.class, () -> FieldUtils.writeDeclaredStaticField(StaticContainer.class, "IMMUTABLE_PRIVATE", "new", true));
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
        // removed other assertion
        assertThrows( IllegalAccessException.class, () -> FieldUtils.writeField(parentClass.getDeclaredField("b"), publicChild, Boolean.TRUE));
    }

@Test
    public void testWriteField_3_oe() throws Exception {
        final Field field = parentClass.getDeclaredField("s");
        FieldUtils.writeField(field, publicChild, "S");
        // removed other assertion
        // removed other assertion
        assertThrows( IllegalAccessException.class, () -> FieldUtils.writeField(parentClass.getDeclaredField("i"), publicChild, Integer.valueOf(Integer.MAX_VALUE)));
    }

@Test
    public void testWriteField_4_oe() throws Exception {
        final Field field = parentClass.getDeclaredField("s");
        FieldUtils.writeField(field, publicChild, "S");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( IllegalAccessException.class, () -> FieldUtils.writeField(parentClass.getDeclaredField("d"), publicChild, Double.valueOf(Double.MAX_VALUE)));
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
        // removed other assertion
        field = parentClass.getDeclaredField("b");
        FieldUtils.writeField(field, publicChild, Boolean.TRUE, true);
        assertEquals(Boolean.TRUE, field.get(publicChild));
    }

@Test
    public void testWriteFieldForceAccess_3_oe() throws Exception {
        Field field = parentClass.getDeclaredField("s");
        FieldUtils.writeField(field, publicChild, "S", true);
        // removed other assertion
        field = parentClass.getDeclaredField("b");
        FieldUtils.writeField(field, publicChild, Boolean.TRUE, true);
        // removed other assertion
        field = parentClass.getDeclaredField("i");
        FieldUtils.writeField(field, publicChild, Integer.valueOf(Integer.MAX_VALUE), true);
        assertEquals(Integer.valueOf(Integer.MAX_VALUE), field.get(publicChild));
    }

@Test
    public void testWriteFieldForceAccess_4_oe() throws Exception {
        Field field = parentClass.getDeclaredField("s");
        FieldUtils.writeField(field, publicChild, "S", true);
        // removed other assertion
        field = parentClass.getDeclaredField("b");
        FieldUtils.writeField(field, publicChild, Boolean.TRUE, true);
        // removed other assertion
        field = parentClass.getDeclaredField("i");
        FieldUtils.writeField(field, publicChild, Integer.valueOf(Integer.MAX_VALUE), true);
        // removed other assertion
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
        // removed other assertion
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.writeField(publicChild, "b", Boolean.TRUE));
    }

@Test
    public void testWriteNamedField_3_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");
        // removed other assertion
        // removed other assertion
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.writeField(publicChild, "i", Integer.valueOf(1)));
    }

@Test
    public void testWriteNamedField_4_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( IllegalArgumentException.class, () -> FieldUtils.writeField(publicChild, "d", Double.valueOf(1.0)));
    }

@Test
    public void testWriteNamedField_5_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeField(publiclyShadowedChild, "s", "S");
        assertEquals("S", FieldUtils.readField(publiclyShadowedChild, "s"));
    }

@Test
    public void testWriteNamedField_6_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeField(publiclyShadowedChild, "s", "S");
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE);
        assertEquals(Boolean.FALSE, FieldUtils.readField(publiclyShadowedChild, "b"));
    }

@Test
    public void testWriteNamedField_7_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeField(publiclyShadowedChild, "s", "S");
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE);
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0));
        assertEquals(Integer.valueOf(0), FieldUtils.readField(publiclyShadowedChild, "i"));
    }

@Test
    public void testWriteNamedField_8_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeField(publiclyShadowedChild, "s", "S");
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE);
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0));
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "d", Double.valueOf(0.0));
        assertEquals(Double.valueOf(0.0), FieldUtils.readField(publiclyShadowedChild, "d"));
    }

@Test
    public void testWriteNamedField_9_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeField(publiclyShadowedChild, "s", "S");
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE);
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0));
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "d", Double.valueOf(0.0));
        // removed other assertion

        FieldUtils.writeField(privatelyShadowedChild, "s", "S");
        assertEquals("S", FieldUtils.readField(privatelyShadowedChild, "s"));
    }

@Test
    public void testWriteNamedField_10_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeField(publiclyShadowedChild, "s", "S");
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE);
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0));
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "d", Double.valueOf(0.0));
        // removed other assertion

        FieldUtils.writeField(privatelyShadowedChild, "s", "S");
        // removed other assertion
        assertThrows( IllegalArgumentException.class, () -> FieldUtils.writeField(privatelyShadowedChild, "b", Boolean.TRUE));
    }

@Test
    public void testWriteNamedField_11_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeField(publiclyShadowedChild, "s", "S");
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE);
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0));
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "d", Double.valueOf(0.0));
        // removed other assertion

        FieldUtils.writeField(privatelyShadowedChild, "s", "S");
        // removed other assertion
        // removed other assertion
        assertThrows( IllegalArgumentException.class, () -> FieldUtils.writeField(privatelyShadowedChild, "i", Integer.valueOf(1)));
    }

@Test
    public void testWriteNamedField_12_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeField(publiclyShadowedChild, "s", "S");
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE);
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0));
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "d", Double.valueOf(0.0));
        // removed other assertion

        FieldUtils.writeField(privatelyShadowedChild, "s", "S");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( IllegalArgumentException.class, () -> FieldUtils.writeField(privatelyShadowedChild, "d", Double.valueOf(1.0)));
    }

@Test
    public void testWriteNamedFieldForceAccess_1_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S", true);
        assertEquals("S", FieldUtils.readField(publicChild, "s", true));
    }

@Test
    public void testWriteNamedFieldForceAccess_2_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "b", Boolean.TRUE, true);
        assertEquals(Boolean.TRUE, FieldUtils.readField(publicChild, "b", true));
    }

@Test
    public void testWriteNamedFieldForceAccess_3_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "b", Boolean.TRUE, true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "i", Integer.valueOf(1), true);
        assertEquals(Integer.valueOf(1), FieldUtils.readField(publicChild, "i", true));
    }

@Test
    public void testWriteNamedFieldForceAccess_4_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "b", Boolean.TRUE, true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "i", Integer.valueOf(1), true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "d", Double.valueOf(1.0), true);
        assertEquals(Double.valueOf(1.0), FieldUtils.readField(publicChild, "d", true));
    }

@Test
    public void testWriteNamedFieldForceAccess_5_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "b", Boolean.TRUE, true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "i", Integer.valueOf(1), true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "d", Double.valueOf(1.0), true);
        // removed other assertion

        FieldUtils.writeField(publiclyShadowedChild, "s", "S", true);
        assertEquals("S", FieldUtils.readField(publiclyShadowedChild, "s", true));
    }

@Test
    public void testWriteNamedFieldForceAccess_6_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "b", Boolean.TRUE, true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "i", Integer.valueOf(1), true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "d", Double.valueOf(1.0), true);
        // removed other assertion

        FieldUtils.writeField(publiclyShadowedChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        assertEquals(Boolean.FALSE, FieldUtils.readField(publiclyShadowedChild, "b", true));
    }

@Test
    public void testWriteNamedFieldForceAccess_7_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "b", Boolean.TRUE, true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "i", Integer.valueOf(1), true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "d", Double.valueOf(1.0), true);
        // removed other assertion

        FieldUtils.writeField(publiclyShadowedChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0), true);
        assertEquals(Integer.valueOf(0), FieldUtils.readField(publiclyShadowedChild, "i", true));
    }

@Test
    public void testWriteNamedFieldForceAccess_8_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "b", Boolean.TRUE, true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "i", Integer.valueOf(1), true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "d", Double.valueOf(1.0), true);
        // removed other assertion

        FieldUtils.writeField(publiclyShadowedChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0), true);
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "d", Double.valueOf(0.0), true);
        assertEquals(Double.valueOf(0.0), FieldUtils.readField(publiclyShadowedChild, "d", true));
    }

@Test
    public void testWriteNamedFieldForceAccess_9_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "b", Boolean.TRUE, true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "i", Integer.valueOf(1), true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "d", Double.valueOf(1.0), true);
        // removed other assertion

        FieldUtils.writeField(publiclyShadowedChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0), true);
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "d", Double.valueOf(0.0), true);
        // removed other assertion

        FieldUtils.writeField(privatelyShadowedChild, "s", "S", true);
        assertEquals("S", FieldUtils.readField(privatelyShadowedChild, "s", true));
    }

@Test
    public void testWriteNamedFieldForceAccess_10_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "b", Boolean.TRUE, true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "i", Integer.valueOf(1), true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "d", Double.valueOf(1.0), true);
        // removed other assertion

        FieldUtils.writeField(publiclyShadowedChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0), true);
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "d", Double.valueOf(0.0), true);
        // removed other assertion

        FieldUtils.writeField(privatelyShadowedChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeField(privatelyShadowedChild, "b", Boolean.FALSE, true);
        assertEquals(Boolean.FALSE, FieldUtils.readField(privatelyShadowedChild, "b", true));
    }

@Test
    public void testWriteNamedFieldForceAccess_11_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "b", Boolean.TRUE, true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "i", Integer.valueOf(1), true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "d", Double.valueOf(1.0), true);
        // removed other assertion

        FieldUtils.writeField(publiclyShadowedChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0), true);
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "d", Double.valueOf(0.0), true);
        // removed other assertion

        FieldUtils.writeField(privatelyShadowedChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeField(privatelyShadowedChild, "b", Boolean.FALSE, true);
        // removed other assertion
        FieldUtils.writeField(privatelyShadowedChild, "i", Integer.valueOf(0), true);
        assertEquals(Integer.valueOf(0), FieldUtils.readField(privatelyShadowedChild, "i", true));
    }

@Test
    public void testWriteNamedFieldForceAccess_12_oe() throws Exception {
        FieldUtils.writeField(publicChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "b", Boolean.TRUE, true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "i", Integer.valueOf(1), true);
        // removed other assertion
        FieldUtils.writeField(publicChild, "d", Double.valueOf(1.0), true);
        // removed other assertion

        FieldUtils.writeField(publiclyShadowedChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "i", Integer.valueOf(0), true);
        // removed other assertion
        FieldUtils.writeField(publiclyShadowedChild, "d", Double.valueOf(0.0), true);
        // removed other assertion

        FieldUtils.writeField(privatelyShadowedChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeField(privatelyShadowedChild, "b", Boolean.FALSE, true);
        // removed other assertion
        FieldUtils.writeField(privatelyShadowedChild, "i", Integer.valueOf(0), true);
        // removed other assertion
        FieldUtils.writeField(privatelyShadowedChild, "d", Double.valueOf(0.0), true);
        assertEquals(Double.valueOf(0.0), FieldUtils.readField(privatelyShadowedChild, "d", true));
    }

@Test
    public void testWriteDeclaredNamedField_1_oe() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.writeDeclaredField(publicChild, "s", "S"));
    }

@Test
    public void testWriteDeclaredNamedField_2_oe() throws Exception {
        // removed other assertion
        assertThrows( IllegalArgumentException.class, () -> FieldUtils.writeDeclaredField(publicChild, "b", Boolean.TRUE));
    }

@Test
    public void testWriteDeclaredNamedField_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertThrows( IllegalArgumentException.class, () -> FieldUtils.writeDeclaredField(publicChild, "i", Integer.valueOf(1)));
    }

@Test
    public void testWriteDeclaredNamedField_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( IllegalArgumentException.class, () -> FieldUtils.writeDeclaredField(publicChild, "d", Double.valueOf(1.0)));
    }

@Test
    public void testWriteDeclaredNamedField_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S");
        assertEquals("S", FieldUtils.readDeclaredField(publiclyShadowedChild, "s"));
    }

@Test
    public void testWriteDeclaredNamedField_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S");
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE);
        assertEquals(Boolean.FALSE, FieldUtils.readDeclaredField(publiclyShadowedChild, "b"));
    }

@Test
    public void testWriteDeclaredNamedField_7_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S");
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0));
        assertEquals(Integer.valueOf(0), FieldUtils.readDeclaredField(publiclyShadowedChild, "i"));
    }

@Test
    public void testWriteDeclaredNamedField_8_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S");
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0));
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "d", Double.valueOf(0.0));
        assertEquals(Double.valueOf(0.0), FieldUtils.readDeclaredField(publiclyShadowedChild, "d"));
    }

@Test
    public void testWriteDeclaredNamedField_9_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S");
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0));
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "d", Double.valueOf(0.0));
        // removed other assertion

        assertThrows( IllegalArgumentException.class, () -> FieldUtils.writeDeclaredField(privatelyShadowedChild, "s", "S"));
    }

@Test
    public void testWriteDeclaredNamedField_10_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S");
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0));
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "d", Double.valueOf(0.0));
        // removed other assertion

        // removed other assertion
        assertThrows( IllegalArgumentException.class, () -> FieldUtils.writeDeclaredField(privatelyShadowedChild, "b", Boolean.TRUE));
    }

@Test
    public void testWriteDeclaredNamedField_11_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S");
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0));
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "d", Double.valueOf(0.0));
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertThrows( IllegalArgumentException.class, () -> FieldUtils.writeDeclaredField(privatelyShadowedChild, "i", Integer.valueOf(1)));
    }

@Test
    public void testWriteDeclaredNamedField_12_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S");
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0));
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "d", Double.valueOf(0.0));
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( IllegalArgumentException.class, () -> FieldUtils.writeDeclaredField(privatelyShadowedChild, "d", Double.valueOf(1.0)));
    }

@Test
    public void testWriteDeclaredNamedFieldForceAccess_1_oe() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.writeDeclaredField(publicChild, "s", "S", true));
    }

@Test
    public void testWriteDeclaredNamedFieldForceAccess_2_oe() throws Exception {
        // removed other assertion
        assertThrows( IllegalArgumentException.class, () -> FieldUtils.writeDeclaredField(publicChild, "b", Boolean.TRUE, true));
    }

@Test
    public void testWriteDeclaredNamedFieldForceAccess_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        assertThrows( IllegalArgumentException.class, () -> FieldUtils.writeDeclaredField(publicChild, "i", Integer.valueOf(1), true));
    }

@Test
    public void testWriteDeclaredNamedFieldForceAccess_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows( IllegalArgumentException.class, () -> FieldUtils.writeDeclaredField(publicChild, "d", Double.valueOf(1.0), true));
    }

@Test
    public void testWriteDeclaredNamedFieldForceAccess_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S", true);
        assertEquals("S", FieldUtils.readDeclaredField(publiclyShadowedChild, "s", true));
    }

@Test
    public void testWriteDeclaredNamedFieldForceAccess_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        assertEquals(Boolean.FALSE, FieldUtils.readDeclaredField(publiclyShadowedChild, "b", true));
    }

@Test
    public void testWriteDeclaredNamedFieldForceAccess_7_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0), true);
        assertEquals(Integer.valueOf(0), FieldUtils.readDeclaredField(publiclyShadowedChild, "i", true));
    }

@Test
    public void testWriteDeclaredNamedFieldForceAccess_8_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0), true);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "d", Double.valueOf(0.0), true);
        assertEquals(Double.valueOf(0.0), FieldUtils.readDeclaredField(publiclyShadowedChild, "d", true));
    }

@Test
    public void testWriteDeclaredNamedFieldForceAccess_9_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0), true);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "d", Double.valueOf(0.0), true);
        // removed other assertion

        FieldUtils.writeDeclaredField(privatelyShadowedChild, "s", "S", true);
        assertEquals("S", FieldUtils.readDeclaredField(privatelyShadowedChild, "s", true));
    }

@Test
    public void testWriteDeclaredNamedFieldForceAccess_10_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0), true);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "d", Double.valueOf(0.0), true);
        // removed other assertion

        FieldUtils.writeDeclaredField(privatelyShadowedChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeDeclaredField(privatelyShadowedChild, "b", Boolean.FALSE, true);
        assertEquals(Boolean.FALSE, FieldUtils.readDeclaredField(privatelyShadowedChild, "b", true));
    }

@Test
    public void testWriteDeclaredNamedFieldForceAccess_11_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0), true);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "d", Double.valueOf(0.0), true);
        // removed other assertion

        FieldUtils.writeDeclaredField(privatelyShadowedChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeDeclaredField(privatelyShadowedChild, "b", Boolean.FALSE, true);
        // removed other assertion
        FieldUtils.writeDeclaredField(privatelyShadowedChild, "i", Integer.valueOf(0), true);
        assertEquals(Integer.valueOf(0), FieldUtils.readDeclaredField(privatelyShadowedChild, "i", true));
    }

@Test
    public void testWriteDeclaredNamedFieldForceAccess_12_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        FieldUtils.writeDeclaredField(publiclyShadowedChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "b", Boolean.FALSE, true);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "i", Integer.valueOf(0), true);
        // removed other assertion
        FieldUtils.writeDeclaredField(publiclyShadowedChild, "d", Double.valueOf(0.0), true);
        // removed other assertion

        FieldUtils.writeDeclaredField(privatelyShadowedChild, "s", "S", true);
        // removed other assertion
        FieldUtils.writeDeclaredField(privatelyShadowedChild, "b", Boolean.FALSE, true);
        // removed other assertion
        FieldUtils.writeDeclaredField(privatelyShadowedChild, "i", Integer.valueOf(0), true);
        // removed other assertion
        FieldUtils.writeDeclaredField(privatelyShadowedChild, "d", Double.valueOf(0.0), true);
        assertEquals(Double.valueOf(0.0), FieldUtils.readDeclaredField(privatelyShadowedChild, "d", true));
    }

@Test
    public void testAmbig_1_oe() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getField(Ambig.class, "VALUE"));
    }

@Test
    public void testRemoveFinalModifier_1_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE_2");
        assertFalse(field.isAccessible());
    }

@Test
    public void testRemoveFinalModifier_2_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE_2");
        // removed other assertion
        assertTrue(Modifier.isFinal(field.getModifiers()));
    }

@Test
    public void testRemoveFinalModifier_3_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE_2");
        // removed other assertion
        // removed other assertion
        callRemoveFinalModifierCheckForException(field, true);
        if (SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_11)) {
            assertFalse(Modifier.isFinal(field.getModifiers()));
    }
    }

@Test
    public void testRemoveFinalModifier_4_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE_2");
        // removed other assertion
        // removed other assertion
        callRemoveFinalModifierCheckForException(field, true);
        if (SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_11)) {
            // removed other assertion
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
        // removed other assertion
        assertTrue(Modifier.isFinal(field.getModifiers()));
    }

@Test
    public void testRemoveFinalModifierWithAccess_3_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE_2");
        // removed other assertion
        // removed other assertion
        callRemoveFinalModifierCheckForException(field, true);
        if (SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_11)) {
            assertFalse(Modifier.isFinal(field.getModifiers()));
    }
    }

@Test
    public void testRemoveFinalModifierWithAccess_4_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE_2");
        // removed other assertion
        // removed other assertion
        callRemoveFinalModifierCheckForException(field, true);
        if (SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_11)) {
            // removed other assertion
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
        // removed other assertion
        assertTrue(Modifier.isFinal(field.getModifiers()));
    }

@Test
    public void testRemoveFinalModifierWithoutAccess_3_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE_2");
        // removed other assertion
        // removed other assertion
        callRemoveFinalModifierCheckForException(field, false);
        if (SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_11)) {
            assertTrue(Modifier.isFinal(field.getModifiers()));
    }
    }

@Test
    public void testRemoveFinalModifierWithoutAccess_4_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PRIVATE_2");
        // removed other assertion
        // removed other assertion
        callRemoveFinalModifierCheckForException(field, false);
        if (SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_11)) {
            // removed other assertion
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
        // removed other assertion
        assertTrue(Modifier.isFinal(field.getModifiers()));
    }

@Test
    public void testRemoveFinalModifierAccessNotNeeded_3_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PACKAGE");
        // removed other assertion
        // removed other assertion
        callRemoveFinalModifierCheckForException(field, false);
        if (SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_11)) {
            assertTrue(Modifier.isFinal(field.getModifiers()));
    }
    }

@Test
    public void testRemoveFinalModifierAccessNotNeeded_4_oe() throws Exception {
        final Field field = StaticContainer.class.getDeclaredField("IMMUTABLE_PACKAGE");
        // removed other assertion
        // removed other assertion
        callRemoveFinalModifierCheckForException(field, false);
        if (SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_11)) {
            // removed other assertion
            assertFalse(field.isAccessible());
    }
    }

}
