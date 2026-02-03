/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.commons.weaver;

import static org.apache.commons.weaver.test.beans.ComplexAnnotations.Stooge.CURLY;
import static org.apache.commons.weaver.test.beans.ComplexAnnotations.Stooge.LARRY;
import static org.apache.commons.weaver.test.beans.ComplexAnnotations.Stooge.MOE;
import static org.apache.commons.weaver.test.beans.ComplexAnnotations.Stooge.SHEMP;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.apache.commons.weaver.test.WeaverTestBase;
import org.apache.commons.weaver.test.beans.AbstractTestBean;
import org.apache.commons.weaver.test.beans.ComplexAnnotations;
import org.apache.commons.weaver.test.beans.ComplexAnnotations.NestAnnotation;
import org.apache.commons.weaver.test.beans.ComplexAnnotations.Stooge;
import org.apache.commons.weaver.test.beans.ComplexAnnotations.TestAnnotation;
import org.apache.commons.weaver.test.beans.TestBeanInterface;
import org.apache.commons.weaver.test.beans.TestBeanWithClassAnnotation;
import org.apache.commons.weaver.test.beans.TestBeanWithMethodAnnotation;
import org.apache.commons.weaver.utils.URLArray;
import org.apache.xbean.finder.Annotated;
import org.apache.xbean.finder.archive.FileArchive;
import org.hamcrest.Matchers;
import org.junit.Test;

public class FinderTest_OE25Dev extends WeaverTestBase {

    private Finder finder() {
        final ClassLoader classLoader = new URLClassLoader(URLArray.fromPaths(getClassPathEntries()));
        return new Finder(new FileArchive(classLoader, getTargetFolder()));
    }

    /**
     * The point of this is to prove that we can correctly hydate instances of
     * annotations with class retention.
     * 
     * @throws IOException
     */

    private List<Class<?>> extract(List<Annotated<Class<?>>> input) {
        Validate.noNullElements(input);
        if (input.isEmpty()) {
            return Collections.emptyList();
        }
        final List<Class<?>> result = new ArrayList<Class<?>>(input.size());
        for (Annotated<Class<?>> c : input) {
            result.add(c.get());
        }
        return result;
    }

    @Test
    public void testElements_1_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        assertEquals(2, fields.size());
    }

    @Test
    public void testElements_2_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        assertFalse(anno1.booleanValue());
    }

    @Test
    public void testElements_3_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        assertTrue(Arrays.equals(new boolean[] { false }, anno1.booleanValues()));
    }

    @Test
    public void testElements_4_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        assertEquals((byte) 0, anno1.byteValue());
    }

    @Test
    public void testElements_5_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new byte[] { 0 }, anno1.byteValues());
    }

    @Test
    public void testElements_6_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((char) 0, anno1.charValue());
    }

    @Test
    public void testElements_7_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new char[] { 0 }, anno1.charValues());
    }

    @Test
    public void testElements_8_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Double.valueOf(0.0), Double.valueOf(anno1.doubleValue()));
    }

    @Test
    public void testElements_9_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(Arrays.equals(new double[] { 0.0 }, anno1.doubleValues()));
    }

    @Test
    public void testElements_10_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.valueOf(0.0f), Float.valueOf(anno1.floatValue()));
    }

    @Test
    public void testElements_11_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(Arrays.equals(new float[] { 0.0f }, anno1.floatValues()));
    }

    @Test
    public void testElements_12_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, anno1.intValue());
    }

    @Test
    public void testElements_13_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new int[] { 0 }, anno1.intValues());
    }

    @Test
    public void testElements_14_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0L, anno1.longValue());
    }

    @Test
    public void testElements_15_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new long[] { 0L }, anno1.longValues());
    }

    @Test
    public void testElements_16_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        assertFalse(nest1.booleanValue());
    }

    @Test
    public void testElements_17_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        assertTrue(Arrays.equals(new boolean[] { false }, nest1.booleanValues()));
    }

    @Test
    public void testElements_18_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        assertEquals((byte) 0, nest1.byteValue());
    }

    @Test
    public void testElements_19_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new byte[] { 0 }, nest1.byteValues());
    }

    @Test
    public void testElements_20_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((char) 0, nest1.charValue());
    }

    @Test
    public void testElements_21_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new char[] { 0 }, nest1.charValues());
    }

    @Test
    public void testElements_22_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Double.valueOf(0.0), Double.valueOf(nest1.doubleValue()));
    }

    @Test
    public void testElements_23_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(Arrays.equals(new double[] { 0.0 }, nest1.doubleValues()));
    }

    @Test
    public void testElements_24_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.valueOf(0.0f), Float.valueOf(nest1.floatValue()));
    }

    @Test
    public void testElements_25_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(Arrays.equals(new float[] { 0.0f }, nest1.floatValues()));
    }

    @Test
    public void testElements_26_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, nest1.intValue());
    }

    @Test
    public void testElements_27_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new int[] { 0 }, nest1.intValues());
    }

    @Test
    public void testElements_28_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0L, nest1.longValue());
    }

    @Test
    public void testElements_29_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new long[] { 0L }, nest1.longValues());
    }

    @Test
    public void testElements_30_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((short) 0, nest1.shortValue());
    }

    @Test
    public void testElements_31_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new short[] { 0 }, nest1.shortValues());
    }

    @Test
    public void testElements_32_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(CURLY, nest1.stooge());
    }

    @Test
    public void testElements_33_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new Stooge[] { MOE, LARRY, SHEMP }, nest1.stooges());
    }

    @Test
    public void testElements_34_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", nest1.string());
    }

    @Test
    public void testElements_35_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new String[] { "" }, nest1.strings());
    }

    @Test
    public void testElements_36_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Object.class, nest1.type());
    }

    @Test
    public void testElements_37_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new Class[] { Object.class }, nest1.types());
    }

    @Test
    public void testElements_38_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(1, anno1.nests().length);
    }

    @Test
    public void testElements_39_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        assertFalse(nest1_0.booleanValue());
    }

    @Test
    public void testElements_40_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        assertTrue(Arrays.equals(new boolean[] { false }, nest1_0.booleanValues()));
    }

    @Test
    public void testElements_41_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        assertEquals((byte) 0, nest1_0.byteValue());
    }

    @Test
    public void testElements_42_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new byte[] { 0 }, nest1_0.byteValues());
    }

    @Test
    public void testElements_43_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((char) 0, nest1_0.charValue());
    }

    @Test
    public void testElements_44_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new char[] { 0 }, nest1_0.charValues());
    }

    @Test
    public void testElements_45_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Double.valueOf(0.0), Double.valueOf(nest1_0.doubleValue()));
    }

    @Test
    public void testElements_46_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(Arrays.equals(new double[] { 0.0 }, nest1_0.doubleValues()));
    }

    @Test
    public void testElements_47_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.valueOf(0.0f), Float.valueOf(nest1_0.floatValue()));
    }

    @Test
    public void testElements_48_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(Arrays.equals(new float[] { 0.0f }, nest1_0.floatValues()));
    }

    @Test
    public void testElements_49_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, nest1_0.intValue());
    }

    @Test
    public void testElements_50_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new int[] { 0 }, nest1_0.intValues());
    }

    @Test
    public void testElements_51_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0L, nest1_0.longValue());
    }

    @Test
    public void testElements_52_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new long[] { 0L }, nest1_0.longValues());
    }

    @Test
    public void testElements_53_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((short) 0, nest1_0.shortValue());
    }

    @Test
    public void testElements_54_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new short[] { 0 }, nest1_0.shortValues());
    }

    @Test
    public void testElements_55_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(CURLY, nest1_0.stooge());
    }

    @Test
    public void testElements_56_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new Stooge[] { MOE, LARRY, SHEMP }, nest1_0.stooges());
    }

    @Test
    public void testElements_57_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", nest1_0.string());
    }

    @Test
    public void testElements_58_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new String[] { "" }, nest1_0.strings());
    }

    @Test
    public void testElements_59_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Object[].class, nest1_0.type());
    }

    @Test
    public void testElements_60_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new Class[] { Object[].class }, nest1_0.types());
    }

    @Test
    public void testElements_61_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals((short) 0, anno1.shortValue());
    }

    @Test
    public void testElements_62_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertArrayEquals(new short[] { 0 }, anno1.shortValues());
    }

    @Test
    public void testElements_63_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertSame(SHEMP, anno1.stooge());
    }

    @Test
    public void testElements_64_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new Stooge[] { MOE, LARRY, CURLY }, anno1.stooges());
    }

    @Test
    public void testElements_65_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", anno1.string());
    }

    @Test
    public void testElements_66_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new String[] { "" }, anno1.strings());
    }

    @Test
    public void testElements_67_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Object.class, anno1.type());
    }

    @Test
    public void testElements_68_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new Class[] { Object.class }, anno1.types());
    }

    @Test
    public void testElements_69_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        assertFalse(anno2.booleanValue());
    }

    @Test
    public void testElements_70_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        assertTrue(Arrays.equals(new boolean[] { false }, anno2.booleanValues()));
    }

    @Test
    public void testElements_71_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        assertEquals((byte) 0, anno2.byteValue());
    }

    @Test
    public void testElements_72_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new byte[] { 0 }, anno2.byteValues());
    }

    @Test
    public void testElements_73_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((char) 0, anno2.charValue());
    }

    @Test
    public void testElements_74_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new char[] { 0 }, anno2.charValues());
    }

    @Test
    public void testElements_75_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Double.valueOf(0.0), Double.valueOf(anno2.doubleValue()));
    }

    @Test
    public void testElements_76_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(Arrays.equals(new double[] { 0.0 }, anno2.doubleValues()));
    }

    @Test
    public void testElements_77_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.valueOf(0.0f), Float.valueOf(anno2.floatValue()));
    }

    @Test
    public void testElements_78_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(Arrays.equals(new float[] { 0.0f }, anno2.floatValues()));
    }

    @Test
    public void testElements_79_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, anno2.intValue());
    }

    @Test
    public void testElements_80_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new int[] { 0 }, anno2.intValues());
    }

    @Test
    public void testElements_81_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0L, anno2.longValue());
    }

    @Test
    public void testElements_82_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new long[] { 0L }, anno2.longValues());
    }

    @Test
    public void testElements_83_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        assertFalse(nest2.booleanValue());
    }

    @Test
    public void testElements_84_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        assertTrue(Arrays.equals(new boolean[] { false }, nest2.booleanValues()));
    }

    @Test
    public void testElements_85_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        assertEquals((byte) 0, nest2.byteValue());
    }

    @Test
    public void testElements_86_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new byte[] { 0 }, nest2.byteValues());
    }

    @Test
    public void testElements_87_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((char) 0, nest2.charValue());
    }

    @Test
    public void testElements_88_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new char[] { 0 }, nest2.charValues());
    }

    @Test
    public void testElements_89_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Double.valueOf(0.0), Double.valueOf(nest2.doubleValue()));
    }

    @Test
    public void testElements_90_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(Arrays.equals(new double[] { 0.0 }, nest2.doubleValues()));
    }

    @Test
    public void testElements_91_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.valueOf(0.0f), Float.valueOf(nest2.floatValue()));
    }

    @Test
    public void testElements_92_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(Arrays.equals(new float[] { 0.0f }, nest2.floatValues()));
    }

    @Test
    public void testElements_93_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, nest2.intValue());
    }

    @Test
    public void testElements_94_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new int[] { 0 }, nest2.intValues());
    }

    @Test
    public void testElements_95_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0L, nest2.longValue());
    }

    @Test
    public void testElements_96_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new long[] { 0L }, nest2.longValues());
    }

    @Test
    public void testElements_97_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((short) 0, nest2.shortValue());
    }

    @Test
    public void testElements_98_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new short[] { 0 }, nest2.shortValues());
    }

    @Test
    public void testElements_99_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(CURLY, nest2.stooge());
    }

    @Test
    public void testElements_100_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new Stooge[] { MOE, LARRY, SHEMP }, nest2.stooges());
    }

    @Test
    public void testElements_101_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", nest2.string());
    }

    @Test
    public void testElements_102_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new String[] { "" }, nest2.strings());
    }

    @Test
    public void testElements_103_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Object.class, nest2.type());
    }

    @Test
    public void testElements_104_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new Class[] { Object.class }, nest2.types());
    }

    @Test
    public void testElements_105_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals(2, anno2.nests().length);
    }

    @Test
    public void testElements_106_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        assertFalse(nest2_0.booleanValue());
    }

    @Test
    public void testElements_107_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        assertTrue(Arrays.equals(new boolean[] { false }, nest2_0.booleanValues()));
    }

    @Test
    public void testElements_108_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        assertEquals((byte) 0, nest2_0.byteValue());
    }

    @Test
    public void testElements_109_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new byte[] { 0 }, nest2_0.byteValues());
    }

    @Test
    public void testElements_110_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((char) 0, nest2_0.charValue());
    }

    @Test
    public void testElements_111_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new char[] { 0 }, nest2_0.charValues());
    }

    @Test
    public void testElements_112_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Double.valueOf(0.0), Double.valueOf(nest2_0.doubleValue()));
    }

    @Test
    public void testElements_113_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(Arrays.equals(new double[] { 0.0 }, nest2_0.doubleValues()));
    }

    @Test
    public void testElements_114_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.valueOf(0.0f), Float.valueOf(nest2_0.floatValue()));
    }

    @Test
    public void testElements_115_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(Arrays.equals(new float[] { 0.0f }, nest2_0.floatValues()));
    }

    @Test
    public void testElements_116_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, nest2_0.intValue());
    }

    @Test
    public void testElements_117_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new int[] { 0 }, nest2_0.intValues());
    }

    @Test
    public void testElements_118_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0L, nest2_0.longValue());
    }

    @Test
    public void testElements_119_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new long[] { 0L }, nest2_0.longValues());
    }

    @Test
    public void testElements_120_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((short) 0, nest2_0.shortValue());
    }

    @Test
    public void testElements_121_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new short[] { 0 }, nest2_0.shortValues());
    }

    @Test
    public void testElements_122_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(CURLY, nest2_0.stooge());
    }

    @Test
    public void testElements_123_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new Stooge[] { MOE, LARRY, SHEMP }, nest2_0.stooges());
    }

    @Test
    public void testElements_124_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", nest2_0.string());
    }

    @Test
    public void testElements_125_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new String[] { "" }, nest2_0.strings());
    }

    @Test
    public void testElements_126_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Object[].class, nest2_0.type());
    }

    @Test
    public void testElements_127_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new Class[] { Object[].class }, nest2_0.types());
    }

    @Test
    public void testElements_128_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        assertFalse(nest2_1.booleanValue());
    }

    @Test
    public void testElements_129_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        assertTrue(Arrays.equals(new boolean[] { false }, nest2_1.booleanValues()));
    }

    @Test
    public void testElements_130_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        assertEquals((byte) 0, nest2_1.byteValue());
    }

    @Test
    public void testElements_131_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new byte[] { 0 }, nest2_1.byteValues());
    }

    @Test
    public void testElements_132_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((char) 0, nest2_1.charValue());
    }

    @Test
    public void testElements_133_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new char[] { 0 }, nest2_1.charValues());
    }

    @Test
    public void testElements_134_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Double.valueOf(0.0), Double.valueOf(nest2_1.doubleValue()));
    }

    @Test
    public void testElements_135_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(Arrays.equals(new double[] { 0.0 }, nest2_1.doubleValues()));
    }

    @Test
    public void testElements_136_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Float.valueOf(0.0f), Float.valueOf(nest2_1.floatValue()));
    }

    @Test
    public void testElements_137_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(Arrays.equals(new float[] { 0.0f }, nest2_1.floatValues()));
    }

    @Test
    public void testElements_138_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, nest2_1.intValue());
    }

    @Test
    public void testElements_139_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new int[] { 0 }, nest2_1.intValues());
    }

    @Test
    public void testElements_140_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0L, nest2_1.longValue());
    }

    @Test
    public void testElements_141_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new long[] { 0L }, nest2_1.longValues());
    }

    @Test
    public void testElements_142_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((short) 0, nest2_1.shortValue());
    }

    @Test
    public void testElements_143_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new short[] { 0 }, nest2_1.shortValues());
    }

    @Test
    public void testElements_144_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame(CURLY, nest2_1.stooge());
    }

    @Test
    public void testElements_145_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new Stooge[] { MOE, LARRY, SHEMP }, nest2_1.stooges());
    }

    @Test
    public void testElements_146_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", nest2_1.string());
    }

    @Test
    public void testElements_147_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new String[] { "" }, nest2_1.strings());
    }

    @Test
    public void testElements_148_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Object[].class, nest2_1.type());
    }

    @Test
    public void testElements_149_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new Class[] { Object[].class }, nest2_1.types());
    }

    @Test
    public void testElements_150_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals((short) 0, anno2.shortValue());
    }

    @Test
    public void testElements_151_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertArrayEquals(new short[] { 0 }, anno2.shortValues());
    }

    @Test
    public void testElements_152_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertSame(SHEMP, anno2.stooge());
    }

    @Test
    public void testElements_153_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new Stooge[] { MOE, LARRY, CURLY }, anno2.stooges());
    }

    @Test
    public void testElements_154_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", anno2.string());
    }

    @Test
    public void testElements_155_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new String[] { "" }, anno2.strings());
    }

    @Test
    public void testElements_156_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Object.class, anno2.type());
    }

    @Test
    public void testElements_157_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        Map<String, Annotated<Field>> fields = new HashMap<String, Annotated<Field>>();
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            fields.put(annotated.get().getName(), annotated);
        }
        // removed other assertion

        TestAnnotation anno1 = fields.get("dummy1").getAnnotation(TestAnnotation.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest1 = anno1.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest1_0 = anno1.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        TestAnnotation anno2 = fields.get("dummy2").getAnnotation(TestAnnotation.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2 = anno2.nest();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        NestAnnotation nest2_0 = anno2.nests()[0];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        NestAnnotation nest2_1 = anno2.nests()[1];
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new Class[] { Object.class }, anno2.types());
    }

    @Test
    public void testObjectMethods_1_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            TestAnnotation anno = annotated.getAnnotation(TestAnnotation.class);
            assertFalse(anno.toString().isEmpty());
    }
    }

    @Test
    public void testObjectMethods_2_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            TestAnnotation anno = annotated.getAnnotation(TestAnnotation.class);
            // removed other assertion
            assertFalse(anno.hashCode() == 0);
    }
    }

    @Test
    public void testObjectMethods_3_oe() throws IOException {
        addClassForScanning(ComplexAnnotations.class);
        for (Annotated<Field> annotated : finder().withAnnotations().findAnnotatedFields(
            ComplexAnnotations.TestAnnotation.class)) {
            TestAnnotation anno = annotated.getAnnotation(TestAnnotation.class);
            // removed other assertion
            // removed other assertion
            assertTrue(anno.equals(anno));
    }
    }

    @Test
    public void testFindAssignableTypes_1_oe() throws IOException {
        addClassForScanning(TestBeanInterface.class);
        addClassForScanning(AbstractTestBean.class);
        addClassForScanning(TestBeanWithClassAnnotation.class);
        addClassForScanning(TestBeanWithMethodAnnotation.class);

        final Set<Class<?>> implementors = new HashSet<Class<?>>();
        for (Annotated<Class<?>> annotated : finder().withAnnotations().findAssignableTypes(TestBeanInterface.class)) {
            implementors.add(annotated.get());
        }
        assertEquals(1, implementors.size());
    }

    @Test
    public void testFindAssignableTypes_2_oe() throws IOException {
        addClassForScanning(TestBeanInterface.class);
        addClassForScanning(AbstractTestBean.class);
        addClassForScanning(TestBeanWithClassAnnotation.class);
        addClassForScanning(TestBeanWithMethodAnnotation.class);

        final Set<Class<?>> implementors = new HashSet<Class<?>>();
        for (Annotated<Class<?>> annotated : finder().withAnnotations().findAssignableTypes(TestBeanInterface.class)) {
            implementors.add(annotated.get());
        }
        // removed other assertion
        assertTrue(implementors.contains(TestBeanWithClassAnnotation.class));
    }

    @Test
    public void testFindAssignableTypes_3_oe() throws IOException {
        addClassForScanning(TestBeanInterface.class);
        addClassForScanning(AbstractTestBean.class);
        addClassForScanning(TestBeanWithClassAnnotation.class);
        addClassForScanning(TestBeanWithMethodAnnotation.class);

        final Set<Class<?>> implementors = new HashSet<Class<?>>();
        for (Annotated<Class<?>> annotated : finder().withAnnotations().findAssignableTypes(TestBeanInterface.class)) {
            implementors.add(annotated.get());
        }
        // removed other assertion
        // removed other assertion

        final Set<Class<?>> subclasses = new HashSet<Class<?>>();
        for (Annotated<Class<?>> annotated : finder().withAnnotations().findAssignableTypes(AbstractTestBean.class)) {
            subclasses.add(annotated.get());
        }
        assertEquals(2, subclasses.size());
    }

    @Test
    public void testFindAssignableTypes_4_oe() throws IOException {
        addClassForScanning(TestBeanInterface.class);
        addClassForScanning(AbstractTestBean.class);
        addClassForScanning(TestBeanWithClassAnnotation.class);
        addClassForScanning(TestBeanWithMethodAnnotation.class);

        final Set<Class<?>> implementors = new HashSet<Class<?>>();
        for (Annotated<Class<?>> annotated : finder().withAnnotations().findAssignableTypes(TestBeanInterface.class)) {
            implementors.add(annotated.get());
        }
        // removed other assertion
        // removed other assertion

        final Set<Class<?>> subclasses = new HashSet<Class<?>>();
        for (Annotated<Class<?>> annotated : finder().withAnnotations().findAssignableTypes(AbstractTestBean.class)) {
            subclasses.add(annotated.get());
        }
        // removed other assertion
        assertTrue(subclasses.contains(TestBeanWithClassAnnotation.class));
    }

    @Test
    public void testFindAssignableTypes_5_oe() throws IOException {
        addClassForScanning(TestBeanInterface.class);
        addClassForScanning(AbstractTestBean.class);
        addClassForScanning(TestBeanWithClassAnnotation.class);
        addClassForScanning(TestBeanWithMethodAnnotation.class);

        final Set<Class<?>> implementors = new HashSet<Class<?>>();
        for (Annotated<Class<?>> annotated : finder().withAnnotations().findAssignableTypes(TestBeanInterface.class)) {
            implementors.add(annotated.get());
        }
        // removed other assertion
        // removed other assertion

        final Set<Class<?>> subclasses = new HashSet<Class<?>>();
        for (Annotated<Class<?>> annotated : finder().withAnnotations().findAssignableTypes(AbstractTestBean.class)) {
            subclasses.add(annotated.get());
        }
        // removed other assertion
        // removed other assertion
        assertTrue(subclasses.contains(TestBeanWithMethodAnnotation.class));
    }

    @Test
    public void testFindAllTypes_1_oe() throws IOException {
        addClassForScanning(TestBeanInterface.class);
        addClassForScanning(AbstractTestBean.class);
        addClassForScanning(TestBeanWithClassAnnotation.class);
        addClassForScanning(TestBeanWithMethodAnnotation.class);

        List<Annotated<Class<?>>> allClasses = finder().withAnnotations().getAllClasses();
        assertThat(extract(allClasses),Matchers.<Class<?>> containsInAnyOrder(TestBeanInterface.class,AbstractTestBean.class,TestBeanWithClassAnnotation.class,TestBeanWithMethodAnnotation.class));
    }

}
