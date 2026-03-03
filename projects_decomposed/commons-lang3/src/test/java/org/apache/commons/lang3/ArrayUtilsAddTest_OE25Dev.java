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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests ArrayUtils add methods.
 */
public class ArrayUtilsAddTest_OE25Dev {

    @Test
    public void testAddFirstBoolean_1_oe() {
        boolean[] newArray;
        newArray = ArrayUtils.addFirst(null, false);
        assertArrayEquals(new boolean[]{false}, newArray);
    }

    @Test
    public void testAddFirstBoolean_2_oe() {
        boolean[] newArray;
        newArray = ArrayUtils.addFirst(null, false);
        assertEquals(Boolean.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstBoolean_3_oe() {
        boolean[] newArray;
        newArray = ArrayUtils.addFirst(null, false);
        newArray = ArrayUtils.addFirst(null, true);
        assertArrayEquals(new boolean[]{true}, newArray);
    }

    @Test
    public void testAddFirstBoolean_4_oe() {
        boolean[] newArray;
        newArray = ArrayUtils.addFirst(null, false);
        newArray = ArrayUtils.addFirst(null, true);
        assertEquals(Boolean.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstBoolean_5_oe() {
        boolean[] newArray;
        newArray = ArrayUtils.addFirst(null, false);
        newArray = ArrayUtils.addFirst(null, true);
        final boolean[] array1 = new boolean[]{true, false, true};
        newArray = ArrayUtils.addFirst(array1, false);
        assertArrayEquals(new boolean[]{false, true, false, true}, newArray);
    }

    @Test
    public void testAddFirstBoolean_6_oe() {
        boolean[] newArray;
        newArray = ArrayUtils.addFirst(null, false);
        newArray = ArrayUtils.addFirst(null, true);
        final boolean[] array1 = new boolean[]{true, false, true};
        newArray = ArrayUtils.addFirst(array1, false);
        assertEquals(Boolean.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstByte_1_oe() {
        byte[] newArray;
        newArray = ArrayUtils.addFirst((byte[]) null, (byte) 0);
        assertArrayEquals(new byte[]{0}, newArray);
    }

    @Test
    public void testAddFirstByte_2_oe() {
        byte[] newArray;
        newArray = ArrayUtils.addFirst((byte[]) null, (byte) 0);
        assertEquals(Byte.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstByte_3_oe() {
        byte[] newArray;
        newArray = ArrayUtils.addFirst((byte[]) null, (byte) 0);
        newArray = ArrayUtils.addFirst((byte[]) null, (byte) 1);
        assertArrayEquals(new byte[]{1}, newArray);
    }

    @Test
    public void testAddFirstByte_4_oe() {
        byte[] newArray;
        newArray = ArrayUtils.addFirst((byte[]) null, (byte) 0);
        newArray = ArrayUtils.addFirst((byte[]) null, (byte) 1);
        assertEquals(Byte.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstByte_5_oe() {
        byte[] newArray;
        newArray = ArrayUtils.addFirst((byte[]) null, (byte) 0);
        newArray = ArrayUtils.addFirst((byte[]) null, (byte) 1);
        final byte[] array1 = new byte[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, (byte) 0);
        assertArrayEquals(new byte[]{0, 1, 2, 3}, newArray);
    }

    @Test
    public void testAddFirstByte_6_oe() {
        byte[] newArray;
        newArray = ArrayUtils.addFirst((byte[]) null, (byte) 0);
        newArray = ArrayUtils.addFirst((byte[]) null, (byte) 1);
        final byte[] array1 = new byte[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, (byte) 0);
        assertEquals(Byte.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstByte_7_oe() {
        byte[] newArray;
        newArray = ArrayUtils.addFirst((byte[]) null, (byte) 0);
        newArray = ArrayUtils.addFirst((byte[]) null, (byte) 1);
        final byte[] array1 = new byte[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, (byte) 0);
        newArray = ArrayUtils.addFirst(array1, (byte) 4);
        assertArrayEquals(new byte[]{4, 1, 2, 3}, newArray);
    }

    @Test
    public void testAddFirstByte_8_oe() {
        byte[] newArray;
        newArray = ArrayUtils.addFirst((byte[]) null, (byte) 0);
        newArray = ArrayUtils.addFirst((byte[]) null, (byte) 1);
        final byte[] array1 = new byte[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, (byte) 0);
        newArray = ArrayUtils.addFirst(array1, (byte) 4);
        assertEquals(Byte.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstChar_1_oe() {
        char[] newArray;
        newArray = ArrayUtils.addFirst((char[]) null, (char) 0);
        assertArrayEquals(new char[]{0}, newArray);
    }

    @Test
    public void testAddFirstChar_2_oe() {
        char[] newArray;
        newArray = ArrayUtils.addFirst((char[]) null, (char) 0);
        assertEquals(Character.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstChar_3_oe() {
        char[] newArray;
        newArray = ArrayUtils.addFirst((char[]) null, (char) 0);
        newArray = ArrayUtils.addFirst((char[]) null, (char) 1);
        assertArrayEquals(new char[]{1}, newArray);
    }

    @Test
    public void testAddFirstChar_4_oe() {
        char[] newArray;
        newArray = ArrayUtils.addFirst((char[]) null, (char) 0);
        newArray = ArrayUtils.addFirst((char[]) null, (char) 1);
        assertEquals(Character.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstChar_5_oe() {
        char[] newArray;
        newArray = ArrayUtils.addFirst((char[]) null, (char) 0);
        newArray = ArrayUtils.addFirst((char[]) null, (char) 1);
        final char[] array1 = new char[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, (char) 0);
        assertArrayEquals(new char[]{0, 1, 2, 3}, newArray);
    }

    @Test
    public void testAddFirstChar_6_oe() {
        char[] newArray;
        newArray = ArrayUtils.addFirst((char[]) null, (char) 0);
        newArray = ArrayUtils.addFirst((char[]) null, (char) 1);
        final char[] array1 = new char[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, (char) 0);
        assertEquals(Character.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstChar_7_oe() {
        char[] newArray;
        newArray = ArrayUtils.addFirst((char[]) null, (char) 0);
        newArray = ArrayUtils.addFirst((char[]) null, (char) 1);
        final char[] array1 = new char[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, (char) 0);
        newArray = ArrayUtils.addFirst(array1, (char) 4);
        assertArrayEquals(new char[]{4, 1, 2, 3}, newArray);
    }

    @Test
    public void testAddFirstChar_8_oe() {
        char[] newArray;
        newArray = ArrayUtils.addFirst((char[]) null, (char) 0);
        newArray = ArrayUtils.addFirst((char[]) null, (char) 1);
        final char[] array1 = new char[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, (char) 0);
        newArray = ArrayUtils.addFirst(array1, (char) 4);
        assertEquals(Character.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstDouble_1_oe() {
        double[] newArray;
        newArray = ArrayUtils.addFirst((double[]) null, 0);
        assertArrayEquals(new double[]{0}, newArray);
    }

    @Test
    public void testAddFirstDouble_2_oe() {
        double[] newArray;
        newArray = ArrayUtils.addFirst((double[]) null, 0);
        assertEquals(Double.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstDouble_3_oe() {
        double[] newArray;
        newArray = ArrayUtils.addFirst((double[]) null, 0);
        newArray = ArrayUtils.addFirst((double[]) null, 1);
        assertArrayEquals(new double[]{1}, newArray);
    }

    @Test
    public void testAddFirstDouble_4_oe() {
        double[] newArray;
        newArray = ArrayUtils.addFirst((double[]) null, 0);
        newArray = ArrayUtils.addFirst((double[]) null, 1);
        assertEquals(Double.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstDouble_5_oe() {
        double[] newArray;
        newArray = ArrayUtils.addFirst((double[]) null, 0);
        newArray = ArrayUtils.addFirst((double[]) null, 1);
        final double[] array1 = new double[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, 0);
        assertArrayEquals(new double[]{0, 1, 2, 3}, newArray);
    }

    @Test
    public void testAddFirstDouble_6_oe() {
        double[] newArray;
        newArray = ArrayUtils.addFirst((double[]) null, 0);
        newArray = ArrayUtils.addFirst((double[]) null, 1);
        final double[] array1 = new double[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, 0);
        assertEquals(Double.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstDouble_7_oe() {
        double[] newArray;
        newArray = ArrayUtils.addFirst((double[]) null, 0);
        newArray = ArrayUtils.addFirst((double[]) null, 1);
        final double[] array1 = new double[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, 0);
        newArray = ArrayUtils.addFirst(array1, 4);
        assertArrayEquals(new double[]{4, 1, 2, 3}, newArray);
    }

    @Test
    public void testAddFirstDouble_8_oe() {
        double[] newArray;
        newArray = ArrayUtils.addFirst((double[]) null, 0);
        newArray = ArrayUtils.addFirst((double[]) null, 1);
        final double[] array1 = new double[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, 0);
        newArray = ArrayUtils.addFirst(array1, 4);
        assertEquals(Double.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstFloat_1_oe() {
        float[] newArray;
        newArray = ArrayUtils.addFirst((float[]) null, 0);
        assertArrayEquals(new float[]{0}, newArray);
    }

    @Test
    public void testAddFirstFloat_2_oe() {
        float[] newArray;
        newArray = ArrayUtils.addFirst((float[]) null, 0);
        assertEquals(Float.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstFloat_3_oe() {
        float[] newArray;
        newArray = ArrayUtils.addFirst((float[]) null, 0);
        newArray = ArrayUtils.addFirst((float[]) null, 1);
        assertArrayEquals(new float[]{1}, newArray);
    }

    @Test
    public void testAddFirstFloat_4_oe() {
        float[] newArray;
        newArray = ArrayUtils.addFirst((float[]) null, 0);
        newArray = ArrayUtils.addFirst((float[]) null, 1);
        assertEquals(Float.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstFloat_5_oe() {
        float[] newArray;
        newArray = ArrayUtils.addFirst((float[]) null, 0);
        newArray = ArrayUtils.addFirst((float[]) null, 1);
        final float[] array1 = new float[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, 0);
        assertArrayEquals(new float[]{0, 1, 2, 3}, newArray);
    }

    @Test
    public void testAddFirstFloat_6_oe() {
        float[] newArray;
        newArray = ArrayUtils.addFirst((float[]) null, 0);
        newArray = ArrayUtils.addFirst((float[]) null, 1);
        final float[] array1 = new float[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, 0);
        assertEquals(Float.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstFloat_7_oe() {
        float[] newArray;
        newArray = ArrayUtils.addFirst((float[]) null, 0);
        newArray = ArrayUtils.addFirst((float[]) null, 1);
        final float[] array1 = new float[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, 0);
        newArray = ArrayUtils.addFirst(array1, 4);
        assertArrayEquals(new float[]{4, 1, 2, 3}, newArray);
    }

    @Test
    public void testAddFirstFloat_8_oe() {
        float[] newArray;
        newArray = ArrayUtils.addFirst((float[]) null, 0);
        newArray = ArrayUtils.addFirst((float[]) null, 1);
        final float[] array1 = new float[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, 0);
        newArray = ArrayUtils.addFirst(array1, 4);
        assertEquals(Float.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstInt_1_oe() {
        int[] newArray;
        newArray = ArrayUtils.addFirst((int[]) null, 0);
        assertArrayEquals(new int[]{0}, newArray);
    }

    @Test
    public void testAddFirstInt_2_oe() {
        int[] newArray;
        newArray = ArrayUtils.addFirst((int[]) null, 0);
        assertEquals(Integer.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstInt_3_oe() {
        int[] newArray;
        newArray = ArrayUtils.addFirst((int[]) null, 0);
        newArray = ArrayUtils.addFirst((int[]) null, 1);
        assertArrayEquals(new int[]{1}, newArray);
    }

    @Test
    public void testAddFirstInt_4_oe() {
        int[] newArray;
        newArray = ArrayUtils.addFirst((int[]) null, 0);
        newArray = ArrayUtils.addFirst((int[]) null, 1);
        assertEquals(Integer.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstInt_5_oe() {
        int[] newArray;
        newArray = ArrayUtils.addFirst((int[]) null, 0);
        newArray = ArrayUtils.addFirst((int[]) null, 1);
        final int[] array1 = new int[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, 0);
        assertArrayEquals(new int[]{0, 1, 2, 3}, newArray);
    }

    @Test
    public void testAddFirstInt_6_oe() {
        int[] newArray;
        newArray = ArrayUtils.addFirst((int[]) null, 0);
        newArray = ArrayUtils.addFirst((int[]) null, 1);
        final int[] array1 = new int[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, 0);
        assertEquals(Integer.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstInt_7_oe() {
        int[] newArray;
        newArray = ArrayUtils.addFirst((int[]) null, 0);
        newArray = ArrayUtils.addFirst((int[]) null, 1);
        final int[] array1 = new int[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, 0);
        newArray = ArrayUtils.addFirst(array1, 4);
        assertArrayEquals(new int[]{4, 1, 2, 3}, newArray);
    }

    @Test
    public void testAddFirstInt_8_oe() {
        int[] newArray;
        newArray = ArrayUtils.addFirst((int[]) null, 0);
        newArray = ArrayUtils.addFirst((int[]) null, 1);
        final int[] array1 = new int[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, 0);
        newArray = ArrayUtils.addFirst(array1, 4);
        assertEquals(Integer.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstLong_1_oe() {
        long[] newArray;
        newArray = ArrayUtils.addFirst((long[]) null, 0);
        assertArrayEquals(new long[]{0}, newArray);
    }

    @Test
    public void testAddFirstLong_2_oe() {
        long[] newArray;
        newArray = ArrayUtils.addFirst((long[]) null, 0);
        assertEquals(Long.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstLong_3_oe() {
        long[] newArray;
        newArray = ArrayUtils.addFirst((long[]) null, 0);
        newArray = ArrayUtils.addFirst((long[]) null, 1);
        assertArrayEquals(new long[]{1}, newArray);
    }

    @Test
    public void testAddFirstLong_4_oe() {
        long[] newArray;
        newArray = ArrayUtils.addFirst((long[]) null, 0);
        newArray = ArrayUtils.addFirst((long[]) null, 1);
        assertEquals(Long.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstLong_5_oe() {
        long[] newArray;
        newArray = ArrayUtils.addFirst((long[]) null, 0);
        newArray = ArrayUtils.addFirst((long[]) null, 1);
        final long[] array1 = new long[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, 0);
        assertArrayEquals(new long[]{0, 1, 2, 3}, newArray);
    }

    @Test
    public void testAddFirstLong_6_oe() {
        long[] newArray;
        newArray = ArrayUtils.addFirst((long[]) null, 0);
        newArray = ArrayUtils.addFirst((long[]) null, 1);
        final long[] array1 = new long[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, 0);
        assertEquals(Long.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstLong_7_oe() {
        long[] newArray;
        newArray = ArrayUtils.addFirst((long[]) null, 0);
        newArray = ArrayUtils.addFirst((long[]) null, 1);
        final long[] array1 = new long[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, 0);
        newArray = ArrayUtils.addFirst(array1, 4);
        assertArrayEquals(new long[]{4, 1, 2, 3}, newArray);
    }

    @Test
    public void testAddFirstLong_8_oe() {
        long[] newArray;
        newArray = ArrayUtils.addFirst((long[]) null, 0);
        newArray = ArrayUtils.addFirst((long[]) null, 1);
        final long[] array1 = new long[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, 0);
        newArray = ArrayUtils.addFirst(array1, 4);
        assertEquals(Long.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstObject_1_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");
        assertArrayEquals(new String[]{"a"}, newArray);
    }

    @Test
    public void testAddFirstObject_2_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");
        assertArrayEquals(new Object[]{"a"}, newArray);
    }

    @Test
    public void testAddFirstObject_3_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");
        assertEquals(String.class, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstObject_4_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");

        final String[] newStringArray = ArrayUtils.add(null, "a");
        assertArrayEquals(new String[]{"a"}, newStringArray);
    }

    @Test
    public void testAddFirstObject_5_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");

        final String[] newStringArray = ArrayUtils.add(null, "a");
        assertArrayEquals(new Object[]{"a"}, newStringArray);
    }

    @Test
    public void testAddFirstObject_6_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");

        final String[] newStringArray = ArrayUtils.add(null, "a");
        assertEquals(String.class, newStringArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstObject_7_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");

        final String[] newStringArray = ArrayUtils.add(null, "a");

        final String[] stringArray1 = new String[] { "a", "b", "c" };
        newArray = ArrayUtils.addFirst(stringArray1, null);
        assertArrayEquals(new String[] { null, "a", "b", "c" }, newArray);
    }

    @Test
    public void testAddFirstObject_8_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");

        final String[] newStringArray = ArrayUtils.add(null, "a");

        final String[] stringArray1 = new String[] { "a", "b", "c" };
        newArray = ArrayUtils.addFirst(stringArray1, null);
        assertEquals(String.class, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstObject_9_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");

        final String[] newStringArray = ArrayUtils.add(null, "a");

        final String[] stringArray1 = new String[] { "a", "b", "c" };
        newArray = ArrayUtils.addFirst(stringArray1, null);

        newArray = ArrayUtils.addFirst(stringArray1, "d");
        assertArrayEquals(new String[] { "d", "a", "b", "c" }, newArray);
    }

    @Test
    public void testAddFirstObject_10_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");

        final String[] newStringArray = ArrayUtils.add(null, "a");

        final String[] stringArray1 = new String[] { "a", "b", "c" };
        newArray = ArrayUtils.addFirst(stringArray1, null);

        newArray = ArrayUtils.addFirst(stringArray1, "d");
        assertEquals(String.class, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstObject_11_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");

        final String[] newStringArray = ArrayUtils.add(null, "a");

        final String[] stringArray1 = new String[] { "a", "b", "c" };
        newArray = ArrayUtils.addFirst(stringArray1, null);

        newArray = ArrayUtils.addFirst(stringArray1, "d");

        Number[] numberArray1 = new Number[] { Integer.valueOf(1), Double.valueOf(2) };
        newArray = ArrayUtils.addFirst(numberArray1, Float.valueOf(3));
        assertArrayEquals(new Number[] { Float.valueOf(3), Integer.valueOf(1), Double.valueOf(2) }, newArray);
    }

    @Test
    public void testAddFirstObject_12_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");

        final String[] newStringArray = ArrayUtils.add(null, "a");

        final String[] stringArray1 = new String[] { "a", "b", "c" };
        newArray = ArrayUtils.addFirst(stringArray1, null);

        newArray = ArrayUtils.addFirst(stringArray1, "d");

        Number[] numberArray1 = new Number[] { Integer.valueOf(1), Double.valueOf(2) };
        newArray = ArrayUtils.addFirst(numberArray1, Float.valueOf(3));
        assertEquals(Number.class, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstObject_13_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");

        final String[] newStringArray = ArrayUtils.add(null, "a");

        final String[] stringArray1 = new String[] { "a", "b", "c" };
        newArray = ArrayUtils.addFirst(stringArray1, null);

        newArray = ArrayUtils.addFirst(stringArray1, "d");

        Number[] numberArray1 = new Number[] { Integer.valueOf(1), Double.valueOf(2) };
        newArray = ArrayUtils.addFirst(numberArray1, Float.valueOf(3));

        numberArray1 = null;
        newArray = ArrayUtils.addFirst(numberArray1, Float.valueOf(3));
        assertArrayEquals(new Float[] { Float.valueOf(3) }, newArray);
    }

    @Test
    public void testAddFirstObject_14_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");

        final String[] newStringArray = ArrayUtils.add(null, "a");

        final String[] stringArray1 = new String[] { "a", "b", "c" };
        newArray = ArrayUtils.addFirst(stringArray1, null);

        newArray = ArrayUtils.addFirst(stringArray1, "d");

        Number[] numberArray1 = new Number[] { Integer.valueOf(1), Double.valueOf(2) };
        newArray = ArrayUtils.addFirst(numberArray1, Float.valueOf(3));

        numberArray1 = null;
        newArray = ArrayUtils.addFirst(numberArray1, Float.valueOf(3));
        assertEquals(Float.class, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstShort_1_oe() {
        short[] newArray;
        newArray = ArrayUtils.addFirst((short[]) null, (short) 0);
        assertArrayEquals(new short[]{0}, newArray);
    }

    @Test
    public void testAddFirstShort_2_oe() {
        short[] newArray;
        newArray = ArrayUtils.addFirst((short[]) null, (short) 0);
        assertEquals(Short.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstShort_3_oe() {
        short[] newArray;
        newArray = ArrayUtils.addFirst((short[]) null, (short) 0);
        newArray = ArrayUtils.addFirst((short[]) null, (short) 1);
        assertArrayEquals(new short[]{1}, newArray);
    }

    @Test
    public void testAddFirstShort_4_oe() {
        short[] newArray;
        newArray = ArrayUtils.addFirst((short[]) null, (short) 0);
        newArray = ArrayUtils.addFirst((short[]) null, (short) 1);
        assertEquals(Short.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstShort_5_oe() {
        short[] newArray;
        newArray = ArrayUtils.addFirst((short[]) null, (short) 0);
        newArray = ArrayUtils.addFirst((short[]) null, (short) 1);
        final short[] array1 = new short[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, (short) 0);
        assertArrayEquals(new short[]{0, 1, 2, 3}, newArray);
    }

    @Test
    public void testAddFirstShort_6_oe() {
        short[] newArray;
        newArray = ArrayUtils.addFirst((short[]) null, (short) 0);
        newArray = ArrayUtils.addFirst((short[]) null, (short) 1);
        final short[] array1 = new short[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, (short) 0);
        assertEquals(Short.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddFirstShort_7_oe() {
        short[] newArray;
        newArray = ArrayUtils.addFirst((short[]) null, (short) 0);
        newArray = ArrayUtils.addFirst((short[]) null, (short) 1);
        final short[] array1 = new short[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, (short) 0);
        newArray = ArrayUtils.addFirst(array1, (short) 4);
        assertArrayEquals(new short[]{4, 1, 2, 3}, newArray);
    }

    @Test
    public void testAddFirstShort_8_oe() {
        short[] newArray;
        newArray = ArrayUtils.addFirst((short[]) null, (short) 0);
        newArray = ArrayUtils.addFirst((short[]) null, (short) 1);
        final short[] array1 = new short[]{1, 2, 3};
        newArray = ArrayUtils.addFirst(array1, (short) 0);
        newArray = ArrayUtils.addFirst(array1, (short) 4);
        assertEquals(Short.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayBoolean_1_oe() {
        boolean[] newArray;
        newArray = ArrayUtils.add(null, false);
        assertArrayEquals(new boolean[]{false}, newArray);
    }

    @Test
    public void testAddObjectArrayBoolean_2_oe() {
        boolean[] newArray;
        newArray = ArrayUtils.add(null, false);
        assertEquals(Boolean.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayBoolean_3_oe() {
        boolean[] newArray;
        newArray = ArrayUtils.add(null, false);
        newArray = ArrayUtils.add(null, true);
        assertArrayEquals(new boolean[]{true}, newArray);
    }

    @Test
    public void testAddObjectArrayBoolean_4_oe() {
        boolean[] newArray;
        newArray = ArrayUtils.add(null, false);
        newArray = ArrayUtils.add(null, true);
        assertEquals(Boolean.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayBoolean_5_oe() {
        boolean[] newArray;
        newArray = ArrayUtils.add(null, false);
        newArray = ArrayUtils.add(null, true);
        final boolean[] array1 = new boolean[]{true, false, true};
        newArray = ArrayUtils.add(array1, false);
        assertArrayEquals(new boolean[]{true, false, true, false}, newArray);
    }

    @Test
    public void testAddObjectArrayBoolean_6_oe() {
        boolean[] newArray;
        newArray = ArrayUtils.add(null, false);
        newArray = ArrayUtils.add(null, true);
        final boolean[] array1 = new boolean[]{true, false, true};
        newArray = ArrayUtils.add(array1, false);
        assertEquals(Boolean.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayByte_1_oe() {
        byte[] newArray;
        newArray = ArrayUtils.add((byte[]) null, (byte) 0);
        assertArrayEquals(new byte[]{0}, newArray);
    }

    @Test
    public void testAddObjectArrayByte_2_oe() {
        byte[] newArray;
        newArray = ArrayUtils.add((byte[]) null, (byte) 0);
        assertEquals(Byte.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayByte_3_oe() {
        byte[] newArray;
        newArray = ArrayUtils.add((byte[]) null, (byte) 0);
        newArray = ArrayUtils.add((byte[]) null, (byte) 1);
        assertArrayEquals(new byte[]{1}, newArray);
    }

    @Test
    public void testAddObjectArrayByte_4_oe() {
        byte[] newArray;
        newArray = ArrayUtils.add((byte[]) null, (byte) 0);
        newArray = ArrayUtils.add((byte[]) null, (byte) 1);
        assertEquals(Byte.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayByte_5_oe() {
        byte[] newArray;
        newArray = ArrayUtils.add((byte[]) null, (byte) 0);
        newArray = ArrayUtils.add((byte[]) null, (byte) 1);
        final byte[] array1 = new byte[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, (byte) 0);
        assertArrayEquals(new byte[]{1, 2, 3, 0}, newArray);
    }

    @Test
    public void testAddObjectArrayByte_6_oe() {
        byte[] newArray;
        newArray = ArrayUtils.add((byte[]) null, (byte) 0);
        newArray = ArrayUtils.add((byte[]) null, (byte) 1);
        final byte[] array1 = new byte[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, (byte) 0);
        assertEquals(Byte.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayByte_7_oe() {
        byte[] newArray;
        newArray = ArrayUtils.add((byte[]) null, (byte) 0);
        newArray = ArrayUtils.add((byte[]) null, (byte) 1);
        final byte[] array1 = new byte[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, (byte) 0);
        newArray = ArrayUtils.add(array1, (byte) 4);
        assertArrayEquals(new byte[]{1, 2, 3, 4}, newArray);
    }

    @Test
    public void testAddObjectArrayByte_8_oe() {
        byte[] newArray;
        newArray = ArrayUtils.add((byte[]) null, (byte) 0);
        newArray = ArrayUtils.add((byte[]) null, (byte) 1);
        final byte[] array1 = new byte[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, (byte) 0);
        newArray = ArrayUtils.add(array1, (byte) 4);
        assertEquals(Byte.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayChar_1_oe() {
        char[] newArray;
        newArray = ArrayUtils.add((char[]) null, (char) 0);
        assertArrayEquals(new char[]{0}, newArray);
    }

    @Test
    public void testAddObjectArrayChar_2_oe() {
        char[] newArray;
        newArray = ArrayUtils.add((char[]) null, (char) 0);
        assertEquals(Character.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayChar_3_oe() {
        char[] newArray;
        newArray = ArrayUtils.add((char[]) null, (char) 0);
        newArray = ArrayUtils.add((char[]) null, (char) 1);
        assertArrayEquals(new char[]{1}, newArray);
    }

    @Test
    public void testAddObjectArrayChar_4_oe() {
        char[] newArray;
        newArray = ArrayUtils.add((char[]) null, (char) 0);
        newArray = ArrayUtils.add((char[]) null, (char) 1);
        assertEquals(Character.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayChar_5_oe() {
        char[] newArray;
        newArray = ArrayUtils.add((char[]) null, (char) 0);
        newArray = ArrayUtils.add((char[]) null, (char) 1);
        final char[] array1 = new char[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, (char) 0);
        assertArrayEquals(new char[]{1, 2, 3, 0}, newArray);
    }

    @Test
    public void testAddObjectArrayChar_6_oe() {
        char[] newArray;
        newArray = ArrayUtils.add((char[]) null, (char) 0);
        newArray = ArrayUtils.add((char[]) null, (char) 1);
        final char[] array1 = new char[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, (char) 0);
        assertEquals(Character.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayChar_7_oe() {
        char[] newArray;
        newArray = ArrayUtils.add((char[]) null, (char) 0);
        newArray = ArrayUtils.add((char[]) null, (char) 1);
        final char[] array1 = new char[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, (char) 0);
        newArray = ArrayUtils.add(array1, (char) 4);
        assertArrayEquals(new char[]{1, 2, 3, 4}, newArray);
    }

    @Test
    public void testAddObjectArrayChar_8_oe() {
        char[] newArray;
        newArray = ArrayUtils.add((char[]) null, (char) 0);
        newArray = ArrayUtils.add((char[]) null, (char) 1);
        final char[] array1 = new char[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, (char) 0);
        newArray = ArrayUtils.add(array1, (char) 4);
        assertEquals(Character.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayDouble_1_oe() {
        double[] newArray;
        newArray = ArrayUtils.add((double[]) null, 0);
        assertArrayEquals(new double[]{0}, newArray);
    }

    @Test
    public void testAddObjectArrayDouble_2_oe() {
        double[] newArray;
        newArray = ArrayUtils.add((double[]) null, 0);
        assertEquals(Double.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayDouble_3_oe() {
        double[] newArray;
        newArray = ArrayUtils.add((double[]) null, 0);
        newArray = ArrayUtils.add((double[]) null, 1);
        assertArrayEquals(new double[]{1}, newArray);
    }

    @Test
    public void testAddObjectArrayDouble_4_oe() {
        double[] newArray;
        newArray = ArrayUtils.add((double[]) null, 0);
        newArray = ArrayUtils.add((double[]) null, 1);
        assertEquals(Double.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayDouble_5_oe() {
        double[] newArray;
        newArray = ArrayUtils.add((double[]) null, 0);
        newArray = ArrayUtils.add((double[]) null, 1);
        final double[] array1 = new double[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, 0);
        assertArrayEquals(new double[]{1, 2, 3, 0}, newArray);
    }

    @Test
    public void testAddObjectArrayDouble_6_oe() {
        double[] newArray;
        newArray = ArrayUtils.add((double[]) null, 0);
        newArray = ArrayUtils.add((double[]) null, 1);
        final double[] array1 = new double[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, 0);
        assertEquals(Double.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayDouble_7_oe() {
        double[] newArray;
        newArray = ArrayUtils.add((double[]) null, 0);
        newArray = ArrayUtils.add((double[]) null, 1);
        final double[] array1 = new double[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, 0);
        newArray = ArrayUtils.add(array1, 4);
        assertArrayEquals(new double[]{1, 2, 3, 4}, newArray);
    }

    @Test
    public void testAddObjectArrayDouble_8_oe() {
        double[] newArray;
        newArray = ArrayUtils.add((double[]) null, 0);
        newArray = ArrayUtils.add((double[]) null, 1);
        final double[] array1 = new double[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, 0);
        newArray = ArrayUtils.add(array1, 4);
        assertEquals(Double.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayFloat_1_oe() {
        float[] newArray;
        newArray = ArrayUtils.add((float[]) null, 0);
        assertArrayEquals(new float[]{0}, newArray);
    }

    @Test
    public void testAddObjectArrayFloat_2_oe() {
        float[] newArray;
        newArray = ArrayUtils.add((float[]) null, 0);
        assertEquals(Float.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayFloat_3_oe() {
        float[] newArray;
        newArray = ArrayUtils.add((float[]) null, 0);
        newArray = ArrayUtils.add((float[]) null, 1);
        assertArrayEquals(new float[]{1}, newArray);
    }

    @Test
    public void testAddObjectArrayFloat_4_oe() {
        float[] newArray;
        newArray = ArrayUtils.add((float[]) null, 0);
        newArray = ArrayUtils.add((float[]) null, 1);
        assertEquals(Float.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayFloat_5_oe() {
        float[] newArray;
        newArray = ArrayUtils.add((float[]) null, 0);
        newArray = ArrayUtils.add((float[]) null, 1);
        final float[] array1 = new float[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, 0);
        assertArrayEquals(new float[]{1, 2, 3, 0}, newArray);
    }

    @Test
    public void testAddObjectArrayFloat_6_oe() {
        float[] newArray;
        newArray = ArrayUtils.add((float[]) null, 0);
        newArray = ArrayUtils.add((float[]) null, 1);
        final float[] array1 = new float[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, 0);
        assertEquals(Float.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayFloat_7_oe() {
        float[] newArray;
        newArray = ArrayUtils.add((float[]) null, 0);
        newArray = ArrayUtils.add((float[]) null, 1);
        final float[] array1 = new float[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, 0);
        newArray = ArrayUtils.add(array1, 4);
        assertArrayEquals(new float[]{1, 2, 3, 4}, newArray);
    }

    @Test
    public void testAddObjectArrayFloat_8_oe() {
        float[] newArray;
        newArray = ArrayUtils.add((float[]) null, 0);
        newArray = ArrayUtils.add((float[]) null, 1);
        final float[] array1 = new float[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, 0);
        newArray = ArrayUtils.add(array1, 4);
        assertEquals(Float.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayInt_1_oe() {
        int[] newArray;
        newArray = ArrayUtils.add((int[]) null, 0);
        assertArrayEquals(new int[]{0}, newArray);
    }

    @Test
    public void testAddObjectArrayInt_2_oe() {
        int[] newArray;
        newArray = ArrayUtils.add((int[]) null, 0);
        assertEquals(Integer.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayInt_3_oe() {
        int[] newArray;
        newArray = ArrayUtils.add((int[]) null, 0);
        newArray = ArrayUtils.add((int[]) null, 1);
        assertArrayEquals(new int[]{1}, newArray);
    }

    @Test
    public void testAddObjectArrayInt_4_oe() {
        int[] newArray;
        newArray = ArrayUtils.add((int[]) null, 0);
        newArray = ArrayUtils.add((int[]) null, 1);
        assertEquals(Integer.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayInt_5_oe() {
        int[] newArray;
        newArray = ArrayUtils.add((int[]) null, 0);
        newArray = ArrayUtils.add((int[]) null, 1);
        final int[] array1 = new int[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, 0);
        assertArrayEquals(new int[]{1, 2, 3, 0}, newArray);
    }

    @Test
    public void testAddObjectArrayInt_6_oe() {
        int[] newArray;
        newArray = ArrayUtils.add((int[]) null, 0);
        newArray = ArrayUtils.add((int[]) null, 1);
        final int[] array1 = new int[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, 0);
        assertEquals(Integer.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayInt_7_oe() {
        int[] newArray;
        newArray = ArrayUtils.add((int[]) null, 0);
        newArray = ArrayUtils.add((int[]) null, 1);
        final int[] array1 = new int[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, 0);
        newArray = ArrayUtils.add(array1, 4);
        assertArrayEquals(new int[]{1, 2, 3, 4}, newArray);
    }

    @Test
    public void testAddObjectArrayInt_8_oe() {
        int[] newArray;
        newArray = ArrayUtils.add((int[]) null, 0);
        newArray = ArrayUtils.add((int[]) null, 1);
        final int[] array1 = new int[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, 0);
        newArray = ArrayUtils.add(array1, 4);
        assertEquals(Integer.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayLong_1_oe() {
        long[] newArray;
        newArray = ArrayUtils.add((long[]) null, 0);
        assertArrayEquals(new long[]{0}, newArray);
    }

    @Test
    public void testAddObjectArrayLong_2_oe() {
        long[] newArray;
        newArray = ArrayUtils.add((long[]) null, 0);
        assertEquals(Long.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayLong_3_oe() {
        long[] newArray;
        newArray = ArrayUtils.add((long[]) null, 0);
        newArray = ArrayUtils.add((long[]) null, 1);
        assertArrayEquals(new long[]{1}, newArray);
    }

    @Test
    public void testAddObjectArrayLong_4_oe() {
        long[] newArray;
        newArray = ArrayUtils.add((long[]) null, 0);
        newArray = ArrayUtils.add((long[]) null, 1);
        assertEquals(Long.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayLong_5_oe() {
        long[] newArray;
        newArray = ArrayUtils.add((long[]) null, 0);
        newArray = ArrayUtils.add((long[]) null, 1);
        final long[] array1 = new long[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, 0);
        assertArrayEquals(new long[]{1, 2, 3, 0}, newArray);
    }

    @Test
    public void testAddObjectArrayLong_6_oe() {
        long[] newArray;
        newArray = ArrayUtils.add((long[]) null, 0);
        newArray = ArrayUtils.add((long[]) null, 1);
        final long[] array1 = new long[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, 0);
        assertEquals(Long.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayLong_7_oe() {
        long[] newArray;
        newArray = ArrayUtils.add((long[]) null, 0);
        newArray = ArrayUtils.add((long[]) null, 1);
        final long[] array1 = new long[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, 0);
        newArray = ArrayUtils.add(array1, 4);
        assertArrayEquals(new long[]{1, 2, 3, 4}, newArray);
    }

    @Test
    public void testAddObjectArrayLong_8_oe() {
        long[] newArray;
        newArray = ArrayUtils.add((long[]) null, 0);
        newArray = ArrayUtils.add((long[]) null, 1);
        final long[] array1 = new long[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, 0);
        newArray = ArrayUtils.add(array1, 4);
        assertEquals(Long.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayObject_1_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");
        assertArrayEquals(new String[]{"a"}, newArray);
    }

    @Test
    public void testAddObjectArrayObject_2_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");
        assertArrayEquals(new Object[]{"a"}, newArray);
    }

    @Test
    public void testAddObjectArrayObject_3_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");
        assertEquals(String.class, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayObject_4_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");

        final String[] newStringArray = ArrayUtils.add(null, "a");
        assertArrayEquals(new String[]{"a"}, newStringArray);
    }

    @Test
    public void testAddObjectArrayObject_5_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");

        final String[] newStringArray = ArrayUtils.add(null, "a");
        assertArrayEquals(new Object[]{"a"}, newStringArray);
    }

    @Test
    public void testAddObjectArrayObject_6_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");

        final String[] newStringArray = ArrayUtils.add(null, "a");
        assertEquals(String.class, newStringArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayObject_7_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");

        final String[] newStringArray = ArrayUtils.add(null, "a");

        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, null);
        assertArrayEquals(new String[]{"a", "b", "c", null}, newArray);
    }

    @Test
    public void testAddObjectArrayObject_8_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");

        final String[] newStringArray = ArrayUtils.add(null, "a");

        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, null);
        assertEquals(String.class, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayObject_9_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");

        final String[] newStringArray = ArrayUtils.add(null, "a");

        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, null);

        newArray = ArrayUtils.add(stringArray1, "d");
        assertArrayEquals(new String[]{"a", "b", "c", "d"}, newArray);
    }

    @Test
    public void testAddObjectArrayObject_10_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");

        final String[] newStringArray = ArrayUtils.add(null, "a");

        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, null);

        newArray = ArrayUtils.add(stringArray1, "d");
        assertEquals(String.class, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayObject_11_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");

        final String[] newStringArray = ArrayUtils.add(null, "a");

        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, null);

        newArray = ArrayUtils.add(stringArray1, "d");

        Number[] numberArray1 = new Number[]{Integer.valueOf(1), Double.valueOf(2)};
        newArray = ArrayUtils.add(numberArray1, Float.valueOf(3));
        assertArrayEquals(new Number[]{Integer.valueOf(1), Double.valueOf(2), Float.valueOf(3)}, newArray);
    }

    @Test
    public void testAddObjectArrayObject_12_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");

        final String[] newStringArray = ArrayUtils.add(null, "a");

        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, null);

        newArray = ArrayUtils.add(stringArray1, "d");

        Number[] numberArray1 = new Number[]{Integer.valueOf(1), Double.valueOf(2)};
        newArray = ArrayUtils.add(numberArray1, Float.valueOf(3));
        assertEquals(Number.class, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayObject_13_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");

        final String[] newStringArray = ArrayUtils.add(null, "a");

        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, null);

        newArray = ArrayUtils.add(stringArray1, "d");

        Number[] numberArray1 = new Number[]{Integer.valueOf(1), Double.valueOf(2)};
        newArray = ArrayUtils.add(numberArray1, Float.valueOf(3));

        numberArray1 = null;
        newArray = ArrayUtils.add(numberArray1, Float.valueOf(3));
        assertArrayEquals(new Float[]{Float.valueOf(3)}, newArray);
    }

    @Test
    public void testAddObjectArrayObject_14_oe() {
        Object[] newArray;

        newArray = ArrayUtils.add((Object[]) null, "a");

        final String[] newStringArray = ArrayUtils.add(null, "a");

        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, null);

        newArray = ArrayUtils.add(stringArray1, "d");

        Number[] numberArray1 = new Number[]{Integer.valueOf(1), Double.valueOf(2)};
        newArray = ArrayUtils.add(numberArray1, Float.valueOf(3));

        numberArray1 = null;
        newArray = ArrayUtils.add(numberArray1, Float.valueOf(3));
        assertEquals(Float.class, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayShort_1_oe() {
        short[] newArray;
        newArray = ArrayUtils.add((short[]) null, (short) 0);
        assertArrayEquals(new short[]{0}, newArray);
    }

    @Test
    public void testAddObjectArrayShort_2_oe() {
        short[] newArray;
        newArray = ArrayUtils.add((short[]) null, (short) 0);
        assertEquals(Short.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayShort_3_oe() {
        short[] newArray;
        newArray = ArrayUtils.add((short[]) null, (short) 0);
        newArray = ArrayUtils.add((short[]) null, (short) 1);
        assertArrayEquals(new short[]{1}, newArray);
    }

    @Test
    public void testAddObjectArrayShort_4_oe() {
        short[] newArray;
        newArray = ArrayUtils.add((short[]) null, (short) 0);
        newArray = ArrayUtils.add((short[]) null, (short) 1);
        assertEquals(Short.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayShort_5_oe() {
        short[] newArray;
        newArray = ArrayUtils.add((short[]) null, (short) 0);
        newArray = ArrayUtils.add((short[]) null, (short) 1);
        final short[] array1 = new short[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, (short) 0);
        assertArrayEquals(new short[]{1, 2, 3, 0}, newArray);
    }

    @Test
    public void testAddObjectArrayShort_6_oe() {
        short[] newArray;
        newArray = ArrayUtils.add((short[]) null, (short) 0);
        newArray = ArrayUtils.add((short[]) null, (short) 1);
        final short[] array1 = new short[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, (short) 0);
        assertEquals(Short.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayShort_7_oe() {
        short[] newArray;
        newArray = ArrayUtils.add((short[]) null, (short) 0);
        newArray = ArrayUtils.add((short[]) null, (short) 1);
        final short[] array1 = new short[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, (short) 0);
        newArray = ArrayUtils.add(array1, (short) 4);
        assertArrayEquals(new short[]{1, 2, 3, 4}, newArray);
    }

    @Test
    public void testAddObjectArrayShort_8_oe() {
        short[] newArray;
        newArray = ArrayUtils.add((short[]) null, (short) 0);
        newArray = ArrayUtils.add((short[]) null, (short) 1);
        final short[] array1 = new short[]{1, 2, 3};
        newArray = ArrayUtils.add(array1, (short) 0);
        newArray = ArrayUtils.add(array1, (short) 4);
        assertEquals(Short.TYPE, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayToObjectArray_1_oe() {
        assertNull(ArrayUtils.addAll(null, (Object[]) null));
    }

    @Test
    public void testAddObjectArrayToObjectArray_2_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        assertNotSame(stringArray1, newArray);
    }

    @Test
    public void testAddObjectArrayToObjectArray_3_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        assertArrayEquals(stringArray1, newArray);
    }

    @Test
    public void testAddObjectArrayToObjectArray_4_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        assertArrayEquals(new String[]{"a", "b", "c"}, newArray);
    }

    @Test
    public void testAddObjectArrayToObjectArray_5_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        assertEquals(String.class, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayToObjectArray_6_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        assertNotSame(stringArray2, newArray);
    }

    @Test
    public void testAddObjectArrayToObjectArray_7_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        assertArrayEquals(stringArray2, newArray);
    }

    @Test
    public void testAddObjectArrayToObjectArray_8_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        assertArrayEquals(new String[]{"1", "2", "3"}, newArray);
    }

    @Test
    public void testAddObjectArrayToObjectArray_9_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        assertEquals(String.class, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayToObjectArray_10_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        assertArrayEquals(new String[]{"a", "b", "c", "1", "2", "3"}, newArray);
    }

    @Test
    public void testAddObjectArrayToObjectArray_11_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        assertEquals(String.class, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayToObjectArray_12_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        assertArrayEquals(ArrayUtils.EMPTY_STRING_ARRAY, newArray);
    }

    @Test
    public void testAddObjectArrayToObjectArray_13_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        assertArrayEquals(new String[]{}, newArray);
    }

    @Test
    public void testAddObjectArrayToObjectArray_14_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        assertEquals(String.class, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayToObjectArray_15_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        assertArrayEquals(ArrayUtils.EMPTY_STRING_ARRAY, newArray);
    }

    @Test
    public void testAddObjectArrayToObjectArray_16_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        assertArrayEquals(new String[]{}, newArray);
    }

    @Test
    public void testAddObjectArrayToObjectArray_17_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        assertEquals(String.class, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayToObjectArray_18_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        assertArrayEquals(ArrayUtils.EMPTY_STRING_ARRAY, newArray);
    }

    @Test
    public void testAddObjectArrayToObjectArray_19_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        assertArrayEquals(new String[]{}, newArray);
    }

    @Test
    public void testAddObjectArrayToObjectArray_20_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        assertEquals(String.class, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayToObjectArray_21_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        final String[] stringArrayNull = new String []{null};
        newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
        assertArrayEquals(new String[]{null, null}, newArray);
    }

    @Test
    public void testAddObjectArrayToObjectArray_22_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        final String[] stringArrayNull = new String []{null};
        newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
        assertEquals(String.class, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectArrayToObjectArray_23_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        final String[] stringArrayNull = new String []{null};
        newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);

        assertArrayEquals(new boolean[]{true, false, false, true}, ArrayUtils.addAll(new boolean[]{true, false}, false, true));
    }

    @Test
    public void testAddObjectArrayToObjectArray_24_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        final String[] stringArrayNull = new String []{null};
        newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);


        assertArrayEquals(new boolean[]{false, true}, ArrayUtils.addAll(null, new boolean[]{false, true}));
    }

    @Test
    public void testAddObjectArrayToObjectArray_25_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        final String[] stringArrayNull = new String []{null};
        newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);



        assertArrayEquals(new boolean[]{true, false}, ArrayUtils.addAll(new boolean[]{true, false}, null));
    }

    @Test
    public void testAddObjectArrayToObjectArray_26_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        final String[] stringArrayNull = new String []{null};
        newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);




        assertArrayEquals(new char[]{'a', 'b', 'c', 'd'}, ArrayUtils.addAll(new char[]{'a', 'b'}, 'c', 'd'));
    }

    @Test
    public void testAddObjectArrayToObjectArray_27_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        final String[] stringArrayNull = new String []{null};
        newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);





        assertArrayEquals(new char[]{'c', 'd'}, ArrayUtils.addAll(null, new char[]{'c', 'd'}));
    }

    @Test
    public void testAddObjectArrayToObjectArray_28_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        final String[] stringArrayNull = new String []{null};
        newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);






        assertArrayEquals(new char[]{'a', 'b'}, ArrayUtils.addAll(new char[]{'a', 'b'}, null));
    }

    @Test
    public void testAddObjectArrayToObjectArray_29_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        final String[] stringArrayNull = new String []{null};
        newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);







        assertArrayEquals(new byte[]{(byte) 0, (byte) 1, (byte) 2, (byte) 3}, ArrayUtils.addAll(new byte[]{(byte) 0, (byte) 1}, (byte) 2, (byte) 3));
    }

    @Test
    public void testAddObjectArrayToObjectArray_30_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        final String[] stringArrayNull = new String []{null};
        newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);








        assertArrayEquals(new byte[]{(byte) 2, (byte) 3}, ArrayUtils.addAll(null, new byte[]{(byte) 2, (byte) 3}));
    }

    @Test
    public void testAddObjectArrayToObjectArray_31_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        final String[] stringArrayNull = new String []{null};
        newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);









        assertArrayEquals(new byte[]{(byte) 0, (byte) 1}, ArrayUtils.addAll(new byte[]{(byte) 0, (byte) 1}, null));
    }

    @Test
    public void testAddObjectArrayToObjectArray_32_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        final String[] stringArrayNull = new String []{null};
        newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);










        assertArrayEquals(new short[]{(short) 10, (short) 20, (short) 30, (short) 40}, ArrayUtils.addAll(new short[]{(short) 10, (short) 20}, (short) 30, (short) 40));
    }

    @Test
    public void testAddObjectArrayToObjectArray_33_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        final String[] stringArrayNull = new String []{null};
        newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);











        assertArrayEquals(new short[]{(short) 30, (short) 40}, ArrayUtils.addAll(null, new short[]{(short) 30, (short) 40}));
    }

    @Test
    public void testAddObjectArrayToObjectArray_34_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        final String[] stringArrayNull = new String []{null};
        newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);












        assertArrayEquals(new short[]{(short) 10, (short) 20}, ArrayUtils.addAll(new short[]{(short) 10, (short) 20}, null));
    }

    @Test
    public void testAddObjectArrayToObjectArray_35_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        final String[] stringArrayNull = new String []{null};
        newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);













        assertArrayEquals(new int[]{1, 1000, -1000, -1}, ArrayUtils.addAll(new int[]{1, 1000}, -1000, -1));
    }

    @Test
    public void testAddObjectArrayToObjectArray_36_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        final String[] stringArrayNull = new String []{null};
        newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);














        assertArrayEquals(new int[]{-1000, -1}, ArrayUtils.addAll(null, new int[]{-1000, -1}));
    }

    @Test
    public void testAddObjectArrayToObjectArray_37_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        final String[] stringArrayNull = new String []{null};
        newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);















        assertArrayEquals(new int[]{1, 1000}, ArrayUtils.addAll(new int[]{1, 1000}, null));
    }

    @Test
    public void testAddObjectArrayToObjectArray_38_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        final String[] stringArrayNull = new String []{null};
        newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
















        assertArrayEquals(new long[]{1L, -1L, 1000L, -1000L}, ArrayUtils.addAll(new long[]{1L, -1L}, 1000L, -1000L));
    }

    @Test
    public void testAddObjectArrayToObjectArray_39_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        final String[] stringArrayNull = new String []{null};
        newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);

















        assertArrayEquals(new long[]{1000L, -1000L}, ArrayUtils.addAll(null, new long[]{1000L, -1000L}));
    }

    @Test
    public void testAddObjectArrayToObjectArray_40_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        final String[] stringArrayNull = new String []{null};
        newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);


















        assertArrayEquals(new long[]{1L, -1L}, ArrayUtils.addAll(new long[]{1L, -1L}, null));
    }

    @Test
    public void testAddObjectArrayToObjectArray_41_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        final String[] stringArrayNull = new String []{null};
        newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);



















        assertArrayEquals(new float[]{10.5f, 10.1f, 1.6f, 0.01f}, ArrayUtils.addAll(new float[]{10.5f, 10.1f}, 1.6f, 0.01f));
    }

    @Test
    public void testAddObjectArrayToObjectArray_42_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        final String[] stringArrayNull = new String []{null};
        newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);




















        assertArrayEquals(new float[]{1.6f, 0.01f}, ArrayUtils.addAll(null, new float[]{1.6f, 0.01f}));
    }

    @Test
    public void testAddObjectArrayToObjectArray_43_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        final String[] stringArrayNull = new String []{null};
        newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);





















        assertArrayEquals(new float[]{10.5f, 10.1f}, ArrayUtils.addAll(new float[]{10.5f, 10.1f}, null));
    }

    @Test
    public void testAddObjectArrayToObjectArray_44_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        final String[] stringArrayNull = new String []{null};
        newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);






















        assertArrayEquals(new double[]{Math.PI, -Math.PI, 0, 9.99}, ArrayUtils.addAll(new double[]{Math.PI, -Math.PI}, 0, 9.99));
    }

    @Test
    public void testAddObjectArrayToObjectArray_45_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        final String[] stringArrayNull = new String []{null};
        newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);























        assertArrayEquals(new double[]{0, 9.99}, ArrayUtils.addAll(null, new double[]{0, 9.99}));
    }

    @Test
    public void testAddObjectArrayToObjectArray_46_oe() {
        Object[] newArray;
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        final String[] stringArray2 = new String[]{"1", "2", "3"};
        newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
        newArray = ArrayUtils.addAll(null, stringArray2);
        newArray = ArrayUtils.addAll(stringArray1, stringArray2);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
        newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
        newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
        final String[] stringArrayNull = new String []{null};
        newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
























        assertArrayEquals(new double[]{Math.PI, -Math.PI}, ArrayUtils.addAll(new double[]{Math.PI, -Math.PI}, null));
    }

    @Test
    public void testAddObjectAtIndex_1_oe() {
        Object[] newArray;
        newArray = ArrayUtils.add((Object[]) null, 0, "a");
        assertArrayEquals(new String[]{"a"}, newArray);
    }

    @Test
    public void testAddObjectAtIndex_2_oe() {
        Object[] newArray;
        newArray = ArrayUtils.add((Object[]) null, 0, "a");
        assertArrayEquals(new Object[]{"a"}, newArray);
    }

    @Test
    public void testAddObjectAtIndex_3_oe() {
        Object[] newArray;
        newArray = ArrayUtils.add((Object[]) null, 0, "a");
        assertEquals(String.class, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectAtIndex_4_oe() {
        Object[] newArray;
        newArray = ArrayUtils.add((Object[]) null, 0, "a");
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, 0, null);
        assertArrayEquals(new String[]{null, "a", "b", "c"}, newArray);
    }

    @Test
    public void testAddObjectAtIndex_5_oe() {
        Object[] newArray;
        newArray = ArrayUtils.add((Object[]) null, 0, "a");
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, 0, null);
        assertEquals(String.class, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectAtIndex_6_oe() {
        Object[] newArray;
        newArray = ArrayUtils.add((Object[]) null, 0, "a");
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, 0, null);
        newArray = ArrayUtils.add(stringArray1, 1, null);
        assertArrayEquals(new String[]{"a", null, "b", "c"}, newArray);
    }

    @Test
    public void testAddObjectAtIndex_7_oe() {
        Object[] newArray;
        newArray = ArrayUtils.add((Object[]) null, 0, "a");
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, 0, null);
        newArray = ArrayUtils.add(stringArray1, 1, null);
        assertEquals(String.class, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectAtIndex_8_oe() {
        Object[] newArray;
        newArray = ArrayUtils.add((Object[]) null, 0, "a");
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, 0, null);
        newArray = ArrayUtils.add(stringArray1, 1, null);
        newArray = ArrayUtils.add(stringArray1, 3, null);
        assertArrayEquals(new String[]{"a", "b", "c", null}, newArray);
    }

    @Test
    public void testAddObjectAtIndex_9_oe() {
        Object[] newArray;
        newArray = ArrayUtils.add((Object[]) null, 0, "a");
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, 0, null);
        newArray = ArrayUtils.add(stringArray1, 1, null);
        newArray = ArrayUtils.add(stringArray1, 3, null);
        assertEquals(String.class, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectAtIndex_10_oe() {
        Object[] newArray;
        newArray = ArrayUtils.add((Object[]) null, 0, "a");
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, 0, null);
        newArray = ArrayUtils.add(stringArray1, 1, null);
        newArray = ArrayUtils.add(stringArray1, 3, null);
        newArray = ArrayUtils.add(stringArray1, 3, "d");
        assertArrayEquals(new String[]{"a", "b", "c", "d"}, newArray);
    }

    @Test
    public void testAddObjectAtIndex_11_oe() {
        Object[] newArray;
        newArray = ArrayUtils.add((Object[]) null, 0, "a");
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, 0, null);
        newArray = ArrayUtils.add(stringArray1, 1, null);
        newArray = ArrayUtils.add(stringArray1, 3, null);
        newArray = ArrayUtils.add(stringArray1, 3, "d");
        assertEquals(String.class, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectAtIndex_12_oe() {
        Object[] newArray;
        newArray = ArrayUtils.add((Object[]) null, 0, "a");
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, 0, null);
        newArray = ArrayUtils.add(stringArray1, 1, null);
        newArray = ArrayUtils.add(stringArray1, 3, null);
        newArray = ArrayUtils.add(stringArray1, 3, "d");
        assertEquals(String.class, newArray.getClass().getComponentType());
    }

    @Test
    public void testAddObjectAtIndex_13_oe() {
        Object[] newArray;
        newArray = ArrayUtils.add((Object[]) null, 0, "a");
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, 0, null);
        newArray = ArrayUtils.add(stringArray1, 1, null);
        newArray = ArrayUtils.add(stringArray1, 3, null);
        newArray = ArrayUtils.add(stringArray1, 3, "d");

        final Object[] o = new Object[] {"1", "2", "4"};
        final Object[] result = ArrayUtils.add(o, 2, "3");
        final Object[] result2 = ArrayUtils.add(o, 3, "5");

        assertNotNull(result);
    }

    @Test
    public void testAddObjectAtIndex_14_oe() {
        Object[] newArray;
        newArray = ArrayUtils.add((Object[]) null, 0, "a");
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, 0, null);
        newArray = ArrayUtils.add(stringArray1, 1, null);
        newArray = ArrayUtils.add(stringArray1, 3, null);
        newArray = ArrayUtils.add(stringArray1, 3, "d");

        final Object[] o = new Object[] {"1", "2", "4"};
        final Object[] result = ArrayUtils.add(o, 2, "3");
        final Object[] result2 = ArrayUtils.add(o, 3, "5");

        assertEquals(4, result.length);
    }

    @Test
    public void testAddObjectAtIndex_15_oe() {
        Object[] newArray;
        newArray = ArrayUtils.add((Object[]) null, 0, "a");
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, 0, null);
        newArray = ArrayUtils.add(stringArray1, 1, null);
        newArray = ArrayUtils.add(stringArray1, 3, null);
        newArray = ArrayUtils.add(stringArray1, 3, "d");

        final Object[] o = new Object[] {"1", "2", "4"};
        final Object[] result = ArrayUtils.add(o, 2, "3");
        final Object[] result2 = ArrayUtils.add(o, 3, "5");

        assertEquals("1", result[0]);
    }

    @Test
    public void testAddObjectAtIndex_16_oe() {
        Object[] newArray;
        newArray = ArrayUtils.add((Object[]) null, 0, "a");
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, 0, null);
        newArray = ArrayUtils.add(stringArray1, 1, null);
        newArray = ArrayUtils.add(stringArray1, 3, null);
        newArray = ArrayUtils.add(stringArray1, 3, "d");

        final Object[] o = new Object[] {"1", "2", "4"};
        final Object[] result = ArrayUtils.add(o, 2, "3");
        final Object[] result2 = ArrayUtils.add(o, 3, "5");

        assertEquals("2", result[1]);
    }

    @Test
    public void testAddObjectAtIndex_17_oe() {
        Object[] newArray;
        newArray = ArrayUtils.add((Object[]) null, 0, "a");
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, 0, null);
        newArray = ArrayUtils.add(stringArray1, 1, null);
        newArray = ArrayUtils.add(stringArray1, 3, null);
        newArray = ArrayUtils.add(stringArray1, 3, "d");

        final Object[] o = new Object[] {"1", "2", "4"};
        final Object[] result = ArrayUtils.add(o, 2, "3");
        final Object[] result2 = ArrayUtils.add(o, 3, "5");

        assertEquals("3", result[2]);
    }

    @Test
    public void testAddObjectAtIndex_18_oe() {
        Object[] newArray;
        newArray = ArrayUtils.add((Object[]) null, 0, "a");
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, 0, null);
        newArray = ArrayUtils.add(stringArray1, 1, null);
        newArray = ArrayUtils.add(stringArray1, 3, null);
        newArray = ArrayUtils.add(stringArray1, 3, "d");

        final Object[] o = new Object[] {"1", "2", "4"};
        final Object[] result = ArrayUtils.add(o, 2, "3");
        final Object[] result2 = ArrayUtils.add(o, 3, "5");

        assertEquals("4", result[3]);
    }

    @Test
    public void testAddObjectAtIndex_19_oe() {
        Object[] newArray;
        newArray = ArrayUtils.add((Object[]) null, 0, "a");
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, 0, null);
        newArray = ArrayUtils.add(stringArray1, 1, null);
        newArray = ArrayUtils.add(stringArray1, 3, null);
        newArray = ArrayUtils.add(stringArray1, 3, "d");

        final Object[] o = new Object[] {"1", "2", "4"};
        final Object[] result = ArrayUtils.add(o, 2, "3");
        final Object[] result2 = ArrayUtils.add(o, 3, "5");

        assertNotNull(result2);
    }

    @Test
    public void testAddObjectAtIndex_20_oe() {
        Object[] newArray;
        newArray = ArrayUtils.add((Object[]) null, 0, "a");
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, 0, null);
        newArray = ArrayUtils.add(stringArray1, 1, null);
        newArray = ArrayUtils.add(stringArray1, 3, null);
        newArray = ArrayUtils.add(stringArray1, 3, "d");

        final Object[] o = new Object[] {"1", "2", "4"};
        final Object[] result = ArrayUtils.add(o, 2, "3");
        final Object[] result2 = ArrayUtils.add(o, 3, "5");

        assertEquals(4, result2.length);
    }

    @Test
    public void testAddObjectAtIndex_21_oe() {
        Object[] newArray;
        newArray = ArrayUtils.add((Object[]) null, 0, "a");
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, 0, null);
        newArray = ArrayUtils.add(stringArray1, 1, null);
        newArray = ArrayUtils.add(stringArray1, 3, null);
        newArray = ArrayUtils.add(stringArray1, 3, "d");

        final Object[] o = new Object[] {"1", "2", "4"};
        final Object[] result = ArrayUtils.add(o, 2, "3");
        final Object[] result2 = ArrayUtils.add(o, 3, "5");

        assertEquals("1", result2[0]);
    }

    @Test
    public void testAddObjectAtIndex_22_oe() {
        Object[] newArray;
        newArray = ArrayUtils.add((Object[]) null, 0, "a");
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, 0, null);
        newArray = ArrayUtils.add(stringArray1, 1, null);
        newArray = ArrayUtils.add(stringArray1, 3, null);
        newArray = ArrayUtils.add(stringArray1, 3, "d");

        final Object[] o = new Object[] {"1", "2", "4"};
        final Object[] result = ArrayUtils.add(o, 2, "3");
        final Object[] result2 = ArrayUtils.add(o, 3, "5");

        assertEquals("2", result2[1]);
    }

    @Test
    public void testAddObjectAtIndex_23_oe() {
        Object[] newArray;
        newArray = ArrayUtils.add((Object[]) null, 0, "a");
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, 0, null);
        newArray = ArrayUtils.add(stringArray1, 1, null);
        newArray = ArrayUtils.add(stringArray1, 3, null);
        newArray = ArrayUtils.add(stringArray1, 3, "d");

        final Object[] o = new Object[] {"1", "2", "4"};
        final Object[] result = ArrayUtils.add(o, 2, "3");
        final Object[] result2 = ArrayUtils.add(o, 3, "5");

        assertEquals("4", result2[2]);
    }

    @Test
    public void testAddObjectAtIndex_24_oe() {
        Object[] newArray;
        newArray = ArrayUtils.add((Object[]) null, 0, "a");
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, 0, null);
        newArray = ArrayUtils.add(stringArray1, 1, null);
        newArray = ArrayUtils.add(stringArray1, 3, null);
        newArray = ArrayUtils.add(stringArray1, 3, "d");

        final Object[] o = new Object[] {"1", "2", "4"};
        final Object[] result = ArrayUtils.add(o, 2, "3");
        final Object[] result2 = ArrayUtils.add(o, 3, "5");

        assertEquals("5", result2[3]);
    }

    @Test
    public void testAddObjectAtIndex_25_oe() {
        Object[] newArray;
        newArray = ArrayUtils.add((Object[]) null, 0, "a");
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, 0, null);
        newArray = ArrayUtils.add(stringArray1, 1, null);
        newArray = ArrayUtils.add(stringArray1, 3, null);
        newArray = ArrayUtils.add(stringArray1, 3, "d");

        final Object[] o = new Object[] {"1", "2", "4"};
        final Object[] result = ArrayUtils.add(o, 2, "3");
        final Object[] result2 = ArrayUtils.add(o, 3, "5");


        boolean[] booleanArray = ArrayUtils.add( null, 0, true );
        assertArrayEquals(new boolean[]{true}, booleanArray);
    }

    @Test
    public void testJira567_1_oe() {
        Number[] n;
        n = ArrayUtils.addAll(new Number[]{Integer.valueOf(1)}, new Long[]{Long.valueOf(2)});
        assertEquals(2, n.length);
    }

    @Test
    public void testJira567_2_oe() {
        Number[] n;
        n = ArrayUtils.addAll(new Number[]{Integer.valueOf(1)}, new Long[]{Long.valueOf(2)});
        assertEquals(Number.class, n.getClass().getComponentType());
    }

@SuppressWarnings("deprecation")
    @Test
    public void testAddObjectAtIndex_26_oe() {
        Object[] newArray;
        newArray = ArrayUtils.add((Object[]) null, 0, "a");
        final String[] stringArray1 = new String[]{"a", "b", "c"};
        newArray = ArrayUtils.add(stringArray1, 0, null);
        newArray = ArrayUtils.add(stringArray1, 1, null);
        newArray = ArrayUtils.add(stringArray1, 3, null);
        newArray = ArrayUtils.add(stringArray1, 3, "d");

        final Object[] o = new Object[] {"1", "2", "4"};
        final Object[] result = ArrayUtils.add(o, 2, "3");
        final Object[] result2 = ArrayUtils.add(o, 3, "5");


        boolean[] booleanArray = ArrayUtils.add( null, 0, true );
        IndexOutOfBoundsException e =
                assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add( null, -1, true));
    }

@Test
    public void testJira567_3_oe() {
        Number[] n;
        n = ArrayUtils.addAll(new Number[]{Integer.valueOf(1)}, new Long[]{Long.valueOf(2)});
        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.addAll(new Integer[]{Integer.valueOf(1)}, new Long[]{Long.valueOf(2)}));
    }

@Test
    @SuppressWarnings("deprecation")
    public void testLANG571_1_oe() {
        final String[] stringArray=null;
        final String aString=null;
        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.add(stringArray, aString));
    }

@Test
    @SuppressWarnings("deprecation")
    public void testLANG571_2_oe() {
        final String[] stringArray=null;
        final String aString=null;
        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.add(stringArray, 0, aString));
    }

}
