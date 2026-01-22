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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.commons.lang3.ArrayUtils;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;


/**
 * Unit tests {@link DiffBuilder}.
 */
public class DiffBuilderTest_OE25Dev {

    private static class TypeTestClass implements Diffable<TypeTestClass> {
        private ToStringStyle style = SHORT_STYLE;
        private boolean booleanField = true;
        private boolean[] booleanArrayField = {true};
        private byte byteField = (byte) 0xFF;
        private byte[] byteArrayField = {(byte) 0xFF};
        private char charField = 'a';
        private char[] charArrayField = {'a'};
        private double doubleField = 1.0;
        private double[] doubleArrayField = {1.0};
        private float floatField = 1.0f;
        private float[] floatArrayField = {1.0f};
        private int intField = 1;
        private int[] intArrayField = {1};
        private long longField = 1L;
        private long[] longArrayField = {1L};
        private short shortField = 1;
        private short[] shortArrayField = {1};
        private Object objectField = null;
        private Object[] objectArrayField = {null};

        @Override
        public DiffResult<TypeTestClass> diff(final TypeTestClass obj) {
            return new DiffBuilder<>(this, obj, style)
                .append("boolean", booleanField, obj.booleanField)
                .append("booleanArray", booleanArrayField, obj.booleanArrayField)
                .append("byte", byteField, obj.byteField)
                .append("byteArray", byteArrayField, obj.byteArrayField)
                .append("char", charField, obj.charField)
                .append("charArray", charArrayField, obj.charArrayField)
                .append("double", doubleField, obj.doubleField)
                .append("doubleArray", doubleArrayField, obj.doubleArrayField)
                .append("float", floatField, obj.floatField)
                .append("floatArray", floatArrayField, obj.floatArrayField)
                .append("int", intField, obj.intField)
                .append("intArray", intArrayField, obj.intArrayField)
                .append("long", longField, obj.longField)
                .append("longArray", longArrayField, obj.longArrayField)
                .append("short", shortField, obj.shortField)
                .append("shortArray", shortArrayField, obj.shortArrayField)
                .append("objectField", objectField, obj.objectField)
                .append("objectArrayField", objectArrayField, obj.objectArrayField)
                .build();
        }

        @Override
        public boolean equals(final Object obj) {
            return EqualsBuilder.reflectionEquals(this, obj, false);
        }

        @Override
        public int hashCode() {
            return HashCodeBuilder.reflectionHashCode(this, false);
        }
    }

    private static final ToStringStyle SHORT_STYLE = ToStringStyle.SHORT_PREFIX_STYLE;

    /**
     * Test that "left" and "right" are the same instance but are equal.
     */

    /**
     * Test that "left" and "right" are not the same instance and are not equal.
     */

    /**
     * Test that "left" and "right" are the same instance and are equal.
     */

    @Test
    public void testBoolean_1_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.booleanField = false;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
    }

    @Test
    public void testBoolean_2_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.booleanField = false;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(Boolean.class, diff.getType());
    }

    @Test
    public void testBoolean_3_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.booleanField = false;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        // removed other assertion
        assertEquals(Boolean.TRUE, diff.getLeft());
    }

    @Test
    public void testBoolean_4_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.booleanField = false;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        // removed other assertion
        // removed other assertion
        assertEquals(Boolean.FALSE, diff.getRight());
    }

    @Test
    public void testBooleanArray_1_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.booleanArrayField = new boolean[] {false, false};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
    }

    @Test
    public void testBooleanArray_2_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.booleanArrayField = new boolean[] {false, false};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        assertArrayEquals(ArrayUtils.toObject(class1.booleanArrayField), (Object[]) diff.getLeft());
    }

    @Test
    public void testBooleanArray_3_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.booleanArrayField = new boolean[] {false, false};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        // removed other assertion
        assertArrayEquals(ArrayUtils.toObject(class2.booleanArrayField), (Object[]) diff.getRight());
    }

    @Test
    public void testByte_1_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.byteField = 0x01;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
    }

    @Test
    public void testByte_2_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.byteField = 0x01;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(Byte.valueOf(class1.byteField), diff.getLeft());
    }

    @Test
    public void testByte_3_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.byteField = 0x01;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        // removed other assertion
        assertEquals(Byte.valueOf(class2.byteField), diff.getRight());
    }

    @Test
    public void testByteArray_1_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.byteArrayField= new byte[] {0x01, 0x02};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
    }

    @Test
    public void testByteArray_2_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.byteArrayField= new byte[] {0x01, 0x02};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        assertArrayEquals(ArrayUtils.toObject(class1.byteArrayField), (Object[]) diff.getLeft());
    }

    @Test
    public void testByteArray_3_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.byteArrayField= new byte[] {0x01, 0x02};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        // removed other assertion
        assertArrayEquals(ArrayUtils.toObject(class2.byteArrayField), (Object[]) diff.getRight());
    }

    @Test
    public void testByteArrayEqualAsObject_1_oe() {
        final DiffResult<String> list = new DiffBuilder<>("String1", "String2", SHORT_STYLE)
            .append("foo", new boolean[] {false}, new boolean[] {false})
            .append("foo", new byte[] {0x01}, new byte[] {0x01})
            .append("foo", new char[] {'a'}, new char[] {'a'})
            .append("foo", new double[] {1.0}, new double[] {1.0})
            .append("foo", new float[] {1.0F}, new float[] {1.0F})
            .append("foo", new int[] {1}, new int[] {1})
            .append("foo", new long[] {1L}, new long[] {1L})
            .append("foo", new short[] {1}, new short[] {1})
            .append("foo", new Object[] {1, "two"}, new Object[] {1, "two"})
            .build();

        assertEquals(0, list.getNumberOfDiffs());
    }

    @Test
    public void testChar_1_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.charField = 'z';
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
    }

    @Test
    public void testChar_2_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.charField = 'z';
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(Character.valueOf(class1.charField), diff.getLeft());
    }

    @Test
    public void testChar_3_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.charField = 'z';
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        // removed other assertion
        assertEquals(Character.valueOf(class2.charField), diff.getRight());
    }

    @Test
    public void testCharArray_1_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.charArrayField = new char[] {'f', 'o', 'o'};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
    }

    @Test
    public void testCharArray_2_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.charArrayField = new char[] {'f', 'o', 'o'};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        assertArrayEquals(ArrayUtils.toObject(class1.charArrayField), (Object[]) diff.getLeft());
    }

    @Test
    public void testCharArray_3_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.charArrayField = new char[] {'f', 'o', 'o'};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        // removed other assertion
        assertArrayEquals(ArrayUtils.toObject(class2.charArrayField), (Object[]) diff.getRight());
    }

    @Test
    public void testDiffResult_1_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.intField = 2;

        final DiffResult<TypeTestClass> list = new DiffBuilder<>(class1, class2, SHORT_STYLE)
            .append("prop1", class1.diff(class2))
            .build();
        assertEquals(1, list.getNumberOfDiffs());
    }

    @Test
    public void testDiffResult_2_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.intField = 2;

        final DiffResult<TypeTestClass> list = new DiffBuilder<>(class1, class2, SHORT_STYLE)
            .append("prop1", class1.diff(class2))
            .build();
        // removed other assertion
        assertEquals("prop1.int", list.getDiffs().get(0).getFieldName());
    }

    @Test
    public void testDouble_1_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.doubleField = 99.99;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
    }

    @Test
    public void testDouble_2_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.doubleField = 99.99;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(Double.valueOf(class1.doubleField), diff.getLeft());
    }

    @Test
    public void testDouble_3_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.doubleField = 99.99;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        // removed other assertion
        assertEquals(Double.valueOf(class2.doubleField), diff.getRight());
    }

    @Test
    public void testDoubleArray_1_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.doubleArrayField = new double[] {3.0, 2.9, 2.8};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
    }

    @Test
    public void testDoubleArray_2_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.doubleArrayField = new double[] {3.0, 2.9, 2.8};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        assertArrayEquals(ArrayUtils.toObject(class1.doubleArrayField), (Object[]) diff.getLeft());
    }

    @Test
    public void testDoubleArray_3_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.doubleArrayField = new double[] {3.0, 2.9, 2.8};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        // removed other assertion
        assertArrayEquals(ArrayUtils.toObject(class2.doubleArrayField), (Object[]) diff.getRight());
    }

    @Test
    public void testFloat_1_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.floatField = 99.99F;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
    }

    @Test
    public void testFloat_2_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.floatField = 99.99F;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(Float.valueOf(class1.floatField), diff.getLeft());
    }

    @Test
    public void testFloat_3_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.floatField = 99.99F;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        // removed other assertion
        assertEquals(Float.valueOf(class2.floatField), diff.getRight());
    }

    @Test
    public void testFloatArray_1_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.floatArrayField = new float[] {3.0F, 2.9F, 2.8F};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
    }

    @Test
    public void testFloatArray_2_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.floatArrayField = new float[] {3.0F, 2.9F, 2.8F};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        assertArrayEquals(ArrayUtils.toObject(class1.floatArrayField), (Object[]) diff.getLeft());
    }

    @Test
    public void testFloatArray_3_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.floatArrayField = new float[] {3.0F, 2.9F, 2.8F};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        // removed other assertion
        assertArrayEquals(ArrayUtils.toObject(class2.floatArrayField), (Object[]) diff.getRight());
    }

    @Test
    public void testInt_1_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.intField = 42;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
    }

    @Test
    public void testInt_2_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.intField = 42;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(Integer.valueOf(class1.intField), diff.getLeft());
    }

    @Test
    public void testInt_3_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.intField = 42;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        // removed other assertion
        assertEquals(Integer.valueOf(class2.intField), diff.getRight());
    }

    @Test
    public void testIntArray_1_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.intArrayField = new int[] {3, 2, 1};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
    }

    @Test
    public void testIntArray_2_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.intArrayField = new int[] {3, 2, 1};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        assertArrayEquals(ArrayUtils.toObject(class1.intArrayField), (Object[]) diff.getLeft());
    }

    @Test
    public void testIntArray_3_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.intArrayField = new int[] {3, 2, 1};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        // removed other assertion
        assertArrayEquals(ArrayUtils.toObject(class2.intArrayField), (Object[]) diff.getRight());
    }

    @Test
    public void testLong_1_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.longField = 42L;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
    }

    @Test
    public void testLong_2_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.longField = 42L;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(Long.valueOf(class1.longField), diff.getLeft());
    }

    @Test
    public void testLong_3_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.longField = 42L;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        // removed other assertion
        assertEquals(Long.valueOf(class2.longField), diff.getRight());
    }

    @Test
    public void testLongArray_1_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.longArrayField = new long[] {3L, 2L, 1L};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
    }

    @Test
    public void testLongArray_2_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.longArrayField = new long[] {3L, 2L, 1L};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        assertArrayEquals(ArrayUtils.toObject(class1.longArrayField), (Object[]) diff.getLeft());
    }

    @Test
    public void testLongArray_3_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.longArrayField = new long[] {3L, 2L, 1L};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        // removed other assertion
        assertArrayEquals(ArrayUtils.toObject(class2.longArrayField), (Object[]) diff.getRight());
    }

    @Test
    public void testNullLhs_1_oe() {
        assertThrows(NullPointerException.class, () -> new DiffBuilder<>(null, this, ToStringStyle.DEFAULT_STYLE));
    }

    @Test
    public void testNullRhs_1_oe() {
        assertThrows(NullPointerException.class, () -> new DiffBuilder<>(this, null, ToStringStyle.DEFAULT_STYLE));
    }

    @Test
    public void testObject_1_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.objectField = "Some string";
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
    }

    @Test
    public void testObject_2_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.objectField = "Some string";
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(class1.objectField, diff.getLeft());
    }

    @Test
    public void testObject_3_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.objectField = "Some string";
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        // removed other assertion
        assertEquals(class2.objectField, diff.getRight());
    }

    @Test
    public void testObjectArray_1_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.objectArrayField = new Object[] {"string", 1, 2};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
    }

    @Test
    public void testObjectArray_2_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.objectArrayField = new Object[] {"string", 1, 2};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        assertArrayEquals(class1.objectArrayField, (Object[]) diff.getLeft());
    }

    @Test
    public void testObjectArray_3_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.objectArrayField = new Object[] {"string", 1, 2};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        // removed other assertion
        assertArrayEquals(class2.objectArrayField, (Object[]) diff.getRight());
    }

    @Test
    public void testObjectArrayEqual_1_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class1.objectArrayField = new Object[] {"string", 1, 2};
        class2.objectArrayField = new Object[] {"string", 1, 2};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(0, list.getNumberOfDiffs());
    }

    @Test
    public void testObjectsNotSameButEqual_1_oe() {
        final TypeTestClass left = new TypeTestClass();
        left.objectField = new Integer(1);
        final TypeTestClass right = new TypeTestClass();
        right.objectField = new Integer(1);
        assertNotSame(left.objectField, right.objectField);
    }

    @Test
    public void testObjectsNotSameButEqual_2_oe() {
        final TypeTestClass left = new TypeTestClass();
        left.objectField = new Integer(1);
        final TypeTestClass right = new TypeTestClass();
        right.objectField = new Integer(1);
        // removed other assertion
        assertEquals(left.objectField, right.objectField);
    }

    @Test
    public void testObjectsNotSameButEqual_3_oe() {
        final TypeTestClass left = new TypeTestClass();
        left.objectField = new Integer(1);
        final TypeTestClass right = new TypeTestClass();
        right.objectField = new Integer(1);
        // removed other assertion
        // removed other assertion

        final DiffResult<TypeTestClass> list = left.diff(right);
        assertEquals(0, list.getNumberOfDiffs());
    }

    @Test
    public void testObjectsNotSameNorEqual_1_oe() {
        final TypeTestClass left = new TypeTestClass();
        left.objectField = 4;
        final TypeTestClass right = new TypeTestClass();
        right.objectField = 100;
        assertNotSame(left.objectField, right.objectField);
    }

    @Test
    public void testObjectsNotSameNorEqual_2_oe() {
        final TypeTestClass left = new TypeTestClass();
        left.objectField = 4;
        final TypeTestClass right = new TypeTestClass();
        right.objectField = 100;
        // removed other assertion
        assertNotEquals(left.objectField, right.objectField);
    }

    @Test
    public void testObjectsNotSameNorEqual_3_oe() {
        final TypeTestClass left = new TypeTestClass();
        left.objectField = 4;
        final TypeTestClass right = new TypeTestClass();
        right.objectField = 100;
        // removed other assertion
        // removed other assertion

        final DiffResult<TypeTestClass> list = left.diff(right);
        assertEquals(1, list.getNumberOfDiffs());
    }

    @Test
    public void testObjectsSameAndEqual_1_oe() {
        final Integer sameObject = 1;
        final TypeTestClass left = new TypeTestClass();
        left.objectField = sameObject;
        final TypeTestClass right = new TypeTestClass();
        right.objectField = sameObject;
        assertSame(left.objectField, right.objectField);
    }

    @Test
    public void testObjectsSameAndEqual_2_oe() {
        final Integer sameObject = 1;
        final TypeTestClass left = new TypeTestClass();
        left.objectField = sameObject;
        final TypeTestClass right = new TypeTestClass();
        right.objectField = sameObject;
        // removed other assertion
        assertEquals(left.objectField, right.objectField);
    }

    @Test
    public void testObjectsSameAndEqual_3_oe() {
        final Integer sameObject = 1;
        final TypeTestClass left = new TypeTestClass();
        left.objectField = sameObject;
        final TypeTestClass right = new TypeTestClass();
        right.objectField = sameObject;
        // removed other assertion
        // removed other assertion

        final DiffResult<TypeTestClass> list = left.diff(right);
        assertEquals(0, list.getNumberOfDiffs());
    }

    @Test
    public void testSameObjectIgnoresAppends_1_oe() {
        final TypeTestClass testClass = new TypeTestClass();
        final DiffResult<TypeTestClass> list = new DiffBuilder<>(testClass, testClass, SHORT_STYLE)
            .append("ignored", false, true)
            .build();
        assertEquals(0, list.getNumberOfDiffs());
    }

    @Test
    public void testShort_1_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.shortField = 42;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
    }

    @Test
    public void testShort_2_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.shortField = 42;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(Short.valueOf(class1.shortField), diff.getLeft());
    }

    @Test
    public void testShort_3_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.shortField = 42;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        // removed other assertion
        assertEquals(Short.valueOf(class2.shortField), diff.getRight());
    }

    @Test
    public void testShortArray_1_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.shortArrayField = new short[] {3, 2, 1};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
    }

    @Test
    public void testShortArray_2_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.shortArrayField = new short[] {3, 2, 1};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        assertArrayEquals(ArrayUtils.toObject(class1.shortArrayField), (Object[]) diff.getLeft());
    }

    @Test
    public void testShortArray_3_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.shortArrayField = new short[] {3, 2, 1};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        // removed other assertion
        final Diff<?> diff = list.getDiffs().get(0);
        // removed other assertion
        assertArrayEquals(ArrayUtils.toObject(class2.shortArrayField), (Object[]) diff.getRight());
    }

    @Test
    public void testSimilarObjectIgnoresAppends_1_oe() {
        final TypeTestClass testClass1 = new TypeTestClass();
        final TypeTestClass testClass2 = new TypeTestClass();
        final DiffResult<TypeTestClass> list = new DiffBuilder<>(testClass1, testClass2, SHORT_STYLE)
            .append("ignored", false, true)
            .build();
        assertEquals(0, list.getNumberOfDiffs());
    }

    @Test
    public void testStylePassedToDiffResult_1_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        DiffResult<TypeTestClass> list = class1.diff(class1);
        assertEquals(SHORT_STYLE, list.getToStringStyle());
    }

    @Test
    public void testStylePassedToDiffResult_2_oe() {
        final TypeTestClass class1 = new TypeTestClass();
        DiffResult<TypeTestClass> list = class1.diff(class1);
        // removed other assertion

        class1.style = ToStringStyle.MULTI_LINE_STYLE;
        list = class1.diff(class1);
        assertEquals(ToStringStyle.MULTI_LINE_STYLE, list.getToStringStyle());
    }

    @Test
    public void testTriviallyEqualTestDisabled_1_oe() {
        final Matcher<Integer> equalToOne = equalTo(1);

        // Constructor's arguments are not trivially equal, but not testing for that.
        final DiffBuilder<Integer> explicitTestAndNotEqual1 = new DiffBuilder<>(1, 2, null, false);
        explicitTestAndNotEqual1.append("letter", "X", "Y");
        assertThat(explicitTestAndNotEqual1.build().getNumberOfDiffs(), equalToOne);
    }

    @Test
    public void testTriviallyEqualTestDisabled_2_oe() {
        final Matcher<Integer> equalToOne = equalTo(1);

        // Constructor's arguments are not trivially equal, but not testing for that.
        final DiffBuilder<Integer> explicitTestAndNotEqual1 = new DiffBuilder<>(1, 2, null, false);
        explicitTestAndNotEqual1.append("letter", "X", "Y");
        // removed other assertion

        // Constructor's arguments are trivially equal, but not testing for that.
        final DiffBuilder<Integer> explicitTestAndNotEqual2 = new DiffBuilder<>(1, 1, null, false);
        // This append(f, l, r) will not abort early.
        explicitTestAndNotEqual2.append("letter", "X", "Y");
        assertThat(explicitTestAndNotEqual2.build().getNumberOfDiffs(), equalToOne);
    }

    @Test
    public void testTriviallyEqualTestEnabled_1_oe() {
        final Matcher<Integer> equalToZero = equalTo(0);
        final Matcher<Integer> equalToOne = equalTo(1);

        // The option to test if trivially equal is enabled by default.
        final DiffBuilder<Integer> implicitTestAndEqual = new DiffBuilder<>(1, 1, null);
        // This append(f, l, r) will abort without creating a Diff for letter.
        implicitTestAndEqual.append("letter", "X", "Y");
        assertThat(implicitTestAndEqual.build().getNumberOfDiffs(), equalToZero);
    }

    @Test
    public void testTriviallyEqualTestEnabled_2_oe() {
        final Matcher<Integer> equalToZero = equalTo(0);
        final Matcher<Integer> equalToOne = equalTo(1);

        // The option to test if trivially equal is enabled by default.
        final DiffBuilder<Integer> implicitTestAndEqual = new DiffBuilder<>(1, 1, null);
        // This append(f, l, r) will abort without creating a Diff for letter.
        implicitTestAndEqual.append("letter", "X", "Y");
        // removed other assertion

        final DiffBuilder<Integer> implicitTestAndNotEqual = new DiffBuilder<>(1, 2, null);
        // This append(f, l, r) will not abort early
        // because the constructor's arguments were not trivially equal.
        implicitTestAndNotEqual.append("letter", "X", "Y");
        assertThat(implicitTestAndNotEqual.build().getNumberOfDiffs(), equalToOne);
    }

    @Test
    public void testTriviallyEqualTestEnabled_3_oe() {
        final Matcher<Integer> equalToZero = equalTo(0);
        final Matcher<Integer> equalToOne = equalTo(1);

        // The option to test if trivially equal is enabled by default.
        final DiffBuilder<Integer> implicitTestAndEqual = new DiffBuilder<>(1, 1, null);
        // This append(f, l, r) will abort without creating a Diff for letter.
        implicitTestAndEqual.append("letter", "X", "Y");
        // removed other assertion

        final DiffBuilder<Integer> implicitTestAndNotEqual = new DiffBuilder<>(1, 2, null);
        // This append(f, l, r) will not abort early
        // because the constructor's arguments were not trivially equal.
        implicitTestAndNotEqual.append("letter", "X", "Y");
        // removed other assertion

        // This is explicitly enabling the trivially equal test.
        final DiffBuilder<Integer> explicitTestAndEqual = new DiffBuilder<>(1, 1, null, true);
        explicitTestAndEqual.append("letter", "X", "Y");
        assertThat(explicitTestAndEqual.build().getNumberOfDiffs(), equalToZero);
    }

}
