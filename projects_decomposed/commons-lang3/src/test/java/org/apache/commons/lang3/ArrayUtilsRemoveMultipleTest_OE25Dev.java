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
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests ArrayUtils remove and removeElement methods.
 */
public class ArrayUtilsRemoveMultipleTest_OE25Dev {

    @Test
    public void testRemoveAllBooleanArray() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);
        assertArrayEquals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, array);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);
        assertArrayEquals(new boolean[]{false}, array);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 1);
        assertArrayEquals(new boolean[]{true}, array);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new boolean[] { true, false, true }, 1);
        assertArrayEquals(new boolean[]{true, true}, array);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0, 1);
        assertArrayEquals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, array);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 1);
        assertArrayEquals(new boolean[]{false}, array);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 2);
        assertArrayEquals(new boolean[]{false}, array);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 1, 2);
        assertArrayEquals(new boolean[]{true}, array);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 0, 2, 4);
        assertArrayEquals(new boolean[]{false, false}, array);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 1, 3);
        assertArrayEquals(new boolean[]{true, true, true}, array);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 1, 3, 4);
        assertArrayEquals(new boolean[]{true, true}, array);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true, false, true }, 0, 2, 4, 6);
        assertArrayEquals(new boolean[]{false, false, false}, array);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true, false, true }, 1, 3, 5);
        assertArrayEquals(new boolean[]{true, true, true, true}, array);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true, false, true }, 0, 1, 2);
        assertArrayEquals(new boolean[]{false, true, false, true}, array);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllBooleanArrayNegativeIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.removeAll(new boolean[] { true, false }, -1));
    }

    @Test
    public void testRemoveAllBooleanArrayOutOfBoundsIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.removeAll(new boolean[] { true, false }, 2));
    }

    @Test
    public void testRemoveAllBooleanArrayRemoveNone() {
        final boolean[] array1 = new boolean[] { true, false };
        final boolean[] array2 = ArrayUtils.removeAll(array1);
        assertNotSame(array1, array2);
        assertArrayEquals(array1, array2);
        assertEquals(boolean.class, array2.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllByteArray() {
        byte[] array;

        array = ArrayUtils.removeAll(new byte[] { 1 }, 0);
        assertArrayEquals(ArrayUtils.EMPTY_BYTE_ARRAY, array);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0);
        assertArrayEquals(new byte[]{2}, array);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 1);
        assertArrayEquals(new byte[]{1}, array);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 1 }, 1);
        assertArrayEquals(new byte[]{1, 1}, array);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0, 1);
        assertArrayEquals(ArrayUtils.EMPTY_BYTE_ARRAY, array);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 1);
        assertArrayEquals(new byte[]{3}, array);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 1, 2);
        assertArrayEquals(new byte[]{1}, array);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 2);
        assertArrayEquals(new byte[]{2}, array);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5 }, 1, 3);
        assertArrayEquals(new byte[]{1, 3, 5}, array);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5 }, 0, 2, 4);
        assertArrayEquals(new byte[]{2, 4}, array);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);
        assertArrayEquals(new byte[]{1, 3, 5, 7}, array);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5, 6, 7 }, 0, 2, 4, 6);
        assertArrayEquals(new byte[]{2, 4, 6}, array);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllByteArrayNegativeIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.removeAll(new byte[] { 1, 2 }, -1));
    }

    @Test
    public void testRemoveAllByteArrayOutOfBoundsIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.removeAll(new byte[] { 1, 2 }, 2));
    }

    @Test
    public void testRemoveAllByteArrayRemoveNone() {
        final byte[] array1 = new byte[] { 1, 2 };
        final byte[] array2 = ArrayUtils.removeAll(array1);
        assertNotSame(array1, array2);
        assertArrayEquals(array1, array2);
        assertEquals(byte.class, array2.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllCharArray() {
        char[] array;

        array = ArrayUtils.removeAll(new char[] { 'a' }, 0);
        assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, array);
        assertEquals(Character.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0);
        assertArrayEquals(new char[]{'b'}, array);
        assertEquals(Character.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 1);
        assertArrayEquals(new char[]{'a'}, array);
        assertEquals(Character.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1);
        assertArrayEquals(new char[]{'a', 'c'}, array);
        assertEquals(Character.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0, 1);
        assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, array);
        assertEquals(Character.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 1);
        assertArrayEquals(new char[]{'c'}, array);
        assertEquals(Character.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1, 2);
        assertArrayEquals(new char[]{'a'}, array);
        assertEquals(Character.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 2);
        assertArrayEquals(new char[]{'b'}, array);
        assertEquals(Character.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e' }, 1, 3);
        assertArrayEquals(new char[]{'a', 'c', 'e'}, array);
        assertEquals(Character.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e' }, 0, 2, 4);
        assertArrayEquals(new char[]{'b', 'd'}, array);
        assertEquals(Character.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g' }, 1, 3, 5);
        assertArrayEquals(new char[]{'a', 'c', 'e', 'g'}, array);
        assertEquals(Character.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g' }, 0, 2, 4, 6);
        assertArrayEquals(new char[]{'b', 'd', 'f'}, array);
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllCharArrayNegativeIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.removeAll(new char[] { 'a', 'b' }, -1));
    }

    @Test
    public void testRemoveAllCharArrayOutOfBoundsIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.removeAll(new char[] { 'a', 'b' }, 2));
    }

    @Test
    public void testRemoveAllCharArrayRemoveNone() {
        final char[] array1 = new char[] { 'a', 'b' };
        final char[] array2 = ArrayUtils.removeAll(array1);
        assertNotSame(array1, array2);
        assertArrayEquals(array1, array2);
        assertEquals(char.class, array2.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllDoubleArray() {
        double[] array;

        array = ArrayUtils.removeAll(new double[] { 1 }, 0);
        assertArrayEquals(ArrayUtils.EMPTY_DOUBLE_ARRAY, array);
        assertEquals(Double.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0);
        assertArrayEquals(new double[]{2}, array);
        assertEquals(Double.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 1);
        assertArrayEquals(new double[]{1}, array);
        assertEquals(Double.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new double[] { 1, 2, 1 }, 1);
        assertArrayEquals(new double[]{1, 1}, array);
        assertEquals(Double.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0, 1);
        assertArrayEquals(ArrayUtils.EMPTY_DOUBLE_ARRAY, array);
        assertEquals(Double.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 1);
        assertArrayEquals(new double[]{3}, array);
        assertEquals(Double.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 1, 2);
        assertArrayEquals(new double[]{1}, array);
        assertEquals(Double.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 2);
        assertArrayEquals(new double[]{2}, array);
        assertEquals(Double.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5 }, 1, 3);
        assertArrayEquals(new double[]{1, 3, 5}, array);
        assertEquals(Double.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5 }, 0, 2, 4);
        assertArrayEquals(new double[]{2, 4}, array);
        assertEquals(Double.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);
        assertArrayEquals(new double[]{1, 3, 5, 7}, array);
        assertEquals(Double.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5, 6, 7 }, 0, 2, 4, 6);
        assertArrayEquals(new double[]{2, 4, 6}, array);
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllDoubleArrayNegativeIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.removeAll(new double[] { 1, 2 }, -1));
    }

    @Test
    public void testRemoveAllDoubleArrayOutOfBoundsIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.removeAll(new double[] { 1, 2 }, 2));
    }

    @Test
    public void testRemoveAllDoubleArrayRemoveNone() {
        final double[] array1 = new double[] { 1, 2 };
        final double[] array2 = ArrayUtils.removeAll(array1);
        assertNotSame(array1, array2);
        assertArrayEquals(array1, array2);
        assertEquals(double.class, array2.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllFloatArray() {
        float[] array;

        array = ArrayUtils.removeAll(new float[] { 1 }, 0);
        assertArrayEquals(ArrayUtils.EMPTY_FLOAT_ARRAY, array);
        assertEquals(Float.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0);
        assertArrayEquals(new float[]{2}, array);
        assertEquals(Float.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 1);
        assertArrayEquals(new float[]{1}, array);
        assertEquals(Float.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new float[] { 1, 2, 1 }, 1);
        assertArrayEquals(new float[]{1, 1}, array);
        assertEquals(Float.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0, 1);
        assertArrayEquals(ArrayUtils.EMPTY_FLOAT_ARRAY, array);
        assertEquals(Float.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 1);
        assertArrayEquals(new float[]{3}, array);
        assertEquals(Float.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 1, 2);
        assertArrayEquals(new float[]{1}, array);
        assertEquals(Float.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 2);
        assertArrayEquals(new float[]{2}, array);
        assertEquals(Float.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5 }, 1, 3);
        assertArrayEquals(new float[]{1, 3, 5}, array);
        assertEquals(Float.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5 }, 0, 2, 4);
        assertArrayEquals(new float[]{2, 4}, array);
        assertEquals(Float.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);
        assertArrayEquals(new float[]{1, 3, 5, 7}, array);
        assertEquals(Float.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5, 6, 7 }, 0, 2, 4, 6);
        assertArrayEquals(new float[]{2, 4, 6}, array);
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllFloatArrayNegativeIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.removeAll(new float[] { 1, 2 }, -1));
    }

    @Test
    public void testRemoveAllFloatArrayOutOfBoundsIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.removeAll(new float[] { 1, 2 }, 2));
    }

    @Test
    public void testRemoveAllFloatArrayRemoveNone() {
        final float[] array1 = new float[] { 1, 2 };
        final float[] array2 = ArrayUtils.removeAll(array1);
        assertNotSame(array1, array2);
        assertArrayEquals(array1, array2);
        assertEquals(float.class, array2.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllIntArray() {
        int[] array;

        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);
        assertArrayEquals(ArrayUtils.EMPTY_INT_ARRAY, array);

        array = ArrayUtils.removeAll(new int[] { 1 }, ArrayUtils.EMPTY_INT_ARRAY);
        assertArrayEquals(new int[]{1}, array);

        array = ArrayUtils.removeAll(new int[] { 1 }, 0);
        assertArrayEquals(ArrayUtils.EMPTY_INT_ARRAY, array);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0);
        assertArrayEquals(new int[]{2}, array);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 1);
        assertArrayEquals(new int[]{1}, array);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new int[] { 1, 2, 1 }, 1);
        assertArrayEquals(new int[]{1, 1}, array);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0, 1);
        assertArrayEquals(ArrayUtils.EMPTY_INT_ARRAY, array);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 1);
        assertArrayEquals(new int[]{3}, array);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 1, 2);
        assertArrayEquals(new int[]{1}, array);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 2);
        assertArrayEquals(new int[]{2}, array);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5 }, 1, 3);
        assertArrayEquals(new int[]{1, 3, 5}, array);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5 }, 0, 2, 4);
        assertArrayEquals(new int[]{2, 4}, array);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);
        assertArrayEquals(new int[]{1, 3, 5, 7}, array);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 0, 2, 4, 6);
        assertArrayEquals(new int[]{2, 4, 6}, array);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllIntArrayNegativeIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.removeAll(new int[] { 1, 2 }, -1));
    }

    @Test
    public void testRemoveAllIntArrayOutOfBoundsIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.removeAll(new int[] { 1, 2 }, 2));
    }

    @Test
    public void testRemoveAllIntArrayRemoveNone() {
        final int[] array1 = new int[] { 1, 2 };
        final int[] array2 = ArrayUtils.removeAll(array1);
        assertNotSame(array1, array2);
        assertArrayEquals(array1, array2);
        assertEquals(int.class, array2.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllLongArray() {
        long[] array;

        array = ArrayUtils.removeAll(new long[] { 1 }, 0);
        assertArrayEquals(ArrayUtils.EMPTY_LONG_ARRAY, array);
        assertEquals(Long.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0);
        assertArrayEquals(new long[]{2}, array);
        assertEquals(Long.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 1);
        assertArrayEquals(new long[]{1}, array);
        assertEquals(Long.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new long[] { 1, 2, 1 }, 1);
        assertArrayEquals(new long[]{1, 1}, array);
        assertEquals(Long.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0, 1);
        assertArrayEquals(ArrayUtils.EMPTY_LONG_ARRAY, array);
        assertEquals(Long.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 1);
        assertArrayEquals(new long[]{3}, array);
        assertEquals(Long.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 1, 2);
        assertArrayEquals(new long[]{1}, array);
        assertEquals(Long.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 2);
        assertArrayEquals(new long[]{2}, array);
        assertEquals(Long.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5 }, 1, 3);
        assertArrayEquals(new long[]{1, 3, 5}, array);
        assertEquals(Long.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5 }, 0, 2, 4);
        assertArrayEquals(new long[]{2, 4}, array);
        assertEquals(Long.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);
        assertArrayEquals(new long[]{1, 3, 5, 7}, array);
        assertEquals(Long.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5, 6, 7 }, 0, 2, 4, 6);
        assertArrayEquals(new long[]{2, 4, 6}, array);
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllLongArrayNegativeIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.removeAll(new long[] { 1, 2 }, -1));
    }

    @Test
    public void testRemoveAllLongArrayOutOfBoundsIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.removeAll(new long[] { 1, 2 }, 2));
    }

    @Test
    public void testRemoveAllLongArrayRemoveNone() {
        final long[] array1 = new long[] { 1, 2 };
        final long[] array2 = ArrayUtils.removeAll(array1);
        assertNotSame(array1, array2);
        assertArrayEquals(array1, array2);
        assertEquals(long.class, array2.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllNullBooleanArray() {
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.removeAll((boolean[]) null, 0));
    }

    @Test
    public void testRemoveAllNullByteArray() {
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.removeAll((byte[]) null, 0));
    }

    @Test
    public void testRemoveAllNullCharArray() {
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.removeAll((char[]) null, 0));
    }

    @Test
    public void testRemoveAllNullDoubleArray() {
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.removeAll((double[]) null, 0));
    }

    @Test
    public void testRemoveAllNullFloatArray() {
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.removeAll((float[]) null, 0));
    }

    @Test
    public void testRemoveAllNullIntArray() {
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.removeAll((int[]) null, 0));
    }

    @Test
    public void testRemoveAllNullLongArray() {
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.removeAll((long[]) null, 0));
    }

    @Test
    public void testRemoveAllNullObjectArray() {
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove((Object[]) null, 0));
    }

    @Test
    public void testRemoveAllNullShortArray() {
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.removeAll((short[]) null, 0));
    }

    @Test
    public void testRemoveAllNumberArray() {
        final Number[] inarray = { Integer.valueOf(1), Long.valueOf(2L), Byte.valueOf((byte) 3) };
        assertEquals(3, inarray.length);
        Number[] outarray;

        outarray = ArrayUtils.removeAll(inarray, 1);
        assertArrayEquals(new Number[] { Integer.valueOf(1), Byte.valueOf((byte) 3) }, outarray);
        assertEquals(Number.class, outarray.getClass().getComponentType());

        outarray = ArrayUtils.removeAll(outarray, 1);
        assertArrayEquals(new Number[] { Integer.valueOf(1) }, outarray);
        assertEquals(Number.class, outarray.getClass().getComponentType());

        outarray = ArrayUtils.removeAll(outarray, 0);
        assertEquals(0, outarray.length);
        assertEquals(Number.class, outarray.getClass().getComponentType());

        outarray = ArrayUtils.removeAll(inarray, 0, 1);
        assertArrayEquals(new Number[] { Byte.valueOf((byte) 3) }, outarray);
        assertEquals(Number.class, outarray.getClass().getComponentType());

        outarray = ArrayUtils.removeAll(inarray, 0, 2);
        assertArrayEquals(new Number[] { Long.valueOf(2L) }, outarray);
        assertEquals(Number.class, outarray.getClass().getComponentType());

        outarray = ArrayUtils.removeAll(inarray, 1, 2);
        assertArrayEquals(new Number[] { Integer.valueOf(1) }, outarray);
        assertEquals(Number.class, outarray.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllObjectArray() {
        Object[] array;

        array = ArrayUtils.removeAll(new Object[] { "a" }, 0);
        assertArrayEquals(ArrayUtils.EMPTY_OBJECT_ARRAY, array);
        assertEquals(Object.class, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new Object[] { "a", "b" }, 0, 1);
        assertArrayEquals(ArrayUtils.EMPTY_OBJECT_ARRAY, array);
        assertEquals(Object.class, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c" }, 1, 2);
        assertArrayEquals(new Object[] { "a" }, array);
        assertEquals(Object.class, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 1, 2);
        assertArrayEquals(new Object[] { "a", "d" }, array);
        assertEquals(Object.class, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 3);
        assertArrayEquals(new Object[] { "b", "c" }, array);
        assertEquals(Object.class, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 1, 3);
        assertArrayEquals(new Object[] { "c" }, array);
        assertEquals(Object.class, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d", "e" }, 0, 1, 3);
        assertArrayEquals(new Object[] { "c", "e" }, array);
        assertEquals(Object.class, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d", "e" }, 0, 2, 4);
        assertArrayEquals(new Object[] { "b", "d" }, array);
        assertEquals(Object.class, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 1, 3, 0, 1, 3);
        assertArrayEquals(new Object[] { "c" }, array);
        assertEquals(Object.class, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 2, 1, 0, 3);
        assertArrayEquals(ArrayUtils.EMPTY_OBJECT_ARRAY, array);
        assertEquals(Object.class, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 2, 0, 1, 3, 0, 2, 1, 3);
        assertArrayEquals(ArrayUtils.EMPTY_OBJECT_ARRAY, array);
        assertEquals(Object.class, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllObjectArrayNegativeIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.removeAll(new Object[] { "a", "b" }, -1));
    }

    @Test
    public void testRemoveAllObjectArrayOutOfBoundsIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.removeAll(new Object[] { "a", "b" }, 2));
    }

    @Test
    public void testRemoveAllObjectArrayRemoveNone() {
        final Object[] array1 = new Object[] { "foo", "bar", "baz" };
        final Object[] array2 = ArrayUtils.removeAll(array1);
        assertNotSame(array1, array2);
        assertArrayEquals(array1, array2);
        assertEquals(Object.class, array2.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllShortArray() {
        short[] array;

        array = ArrayUtils.removeAll(new short[] { 1 }, 0);
        assertArrayEquals(ArrayUtils.EMPTY_SHORT_ARRAY, array);
        assertEquals(Short.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0);
        assertArrayEquals(new short[]{2}, array);
        assertEquals(Short.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 1);
        assertArrayEquals(new short[]{1}, array);
        assertEquals(Short.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new short[] { 1, 2, 1 }, 1);
        assertArrayEquals(new short[]{1, 1}, array);
        assertEquals(Short.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0, 1);
        assertArrayEquals(ArrayUtils.EMPTY_SHORT_ARRAY, array);
        assertEquals(Short.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 1);
        assertArrayEquals(new short[]{3}, array);
        assertEquals(Short.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 1, 2);
        assertArrayEquals(new short[]{1}, array);
        assertEquals(Short.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 2);
        assertArrayEquals(new short[]{2}, array);
        assertEquals(Short.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5 }, 1, 3);
        assertArrayEquals(new short[]{1, 3, 5}, array);
        assertEquals(Short.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5 }, 0, 2, 4);
        assertArrayEquals(new short[]{2, 4}, array);
        assertEquals(Short.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);
        assertArrayEquals(new short[]{1, 3, 5, 7}, array);
        assertEquals(Short.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5, 6, 7 }, 0, 2, 4, 6);
        assertArrayEquals(new short[]{2, 4, 6}, array);
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllShortArrayNegativeIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.removeAll(new short[] { 1, 2 }, -1, 0));
    }

    @Test
    public void testRemoveAllShortArrayOutOfBoundsIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.removeAll(new short[] { 1, 2 }, 2, 0));
    }

    @Test
    public void testRemoveAllShortArrayRemoveNone() {
        final short[] array1 = new short[] { 1, 2 };
        final short[] array2 = ArrayUtils.removeAll(array1);
        assertNotSame(array1, array2);
        assertArrayEquals(array1, array2);
        assertEquals(short.class, array2.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementBooleanArray() {
        boolean[] array;

        array = ArrayUtils.removeElements((boolean[]) null, true);
        assertNull(array);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);
        assertArrayEquals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, array);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new boolean[] { true }, true);
        assertArrayEquals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, array);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true);
        assertArrayEquals(new boolean[]{false}, array);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true);
        assertArrayEquals(new boolean[]{false, true}, array);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements((boolean[]) null, true, false);
        assertNull(array);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true, false);
        assertArrayEquals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, array);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new boolean[] { true }, true, false);
        assertArrayEquals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, array);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true, false);
        assertArrayEquals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, array);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true, true);
        assertArrayEquals(new boolean[]{false}, array);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true, false);
        assertArrayEquals(new boolean[]{true}, array);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true, true);
        assertArrayEquals(new boolean[]{false}, array);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true, true, true, true);
        assertArrayEquals(new boolean[]{false}, array);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementByteArray() {
        byte[] array;

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1);
        assertNull(array);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);
        assertArrayEquals(ArrayUtils.EMPTY_BYTE_ARRAY, array);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1);
        assertArrayEquals(ArrayUtils.EMPTY_BYTE_ARRAY, array);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1);
        assertArrayEquals(new byte[]{2}, array);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1);
        assertArrayEquals(new byte[]{2, 1}, array);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1, (byte) 2);
        assertNull(array);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1, (byte) 2);
        assertArrayEquals(ArrayUtils.EMPTY_BYTE_ARRAY, array);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1, (byte) 2);
        assertArrayEquals(ArrayUtils.EMPTY_BYTE_ARRAY, array);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1, (byte) 2);
        assertArrayEquals(ArrayUtils.EMPTY_BYTE_ARRAY, array);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1, (byte) 1);
        assertArrayEquals(new byte[]{2}, array);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1, (byte) 2);
        assertArrayEquals(new byte[]{1}, array);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1, (byte) 1);
        assertArrayEquals(new byte[]{2}, array);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1, (byte) 1, (byte) 1, (byte) 1);
        assertArrayEquals(new byte[]{2}, array);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementCharArray() {
        char[] array;

        array = ArrayUtils.removeElements((char[]) null, 'a');
        assertNull(array);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');
        assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, array);
        assertEquals(Character.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a');
        assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, array);
        assertEquals(Character.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a');
        assertArrayEquals(new char[]{'b'}, array);
        assertEquals(Character.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a');
        assertArrayEquals(new char[]{'b', 'a'}, array);
        assertEquals(Character.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements((char[]) null, 'a', 'b');
        assertNull(array);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a', 'b');
        assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, array);
        assertEquals(Character.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a', 'b');
        assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, array);
        assertEquals(Character.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a', 'b');
        assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, array);
        assertEquals(Character.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a', 'a');
        assertArrayEquals(new char[]{'b'}, array);
        assertEquals(Character.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a', 'b');
        assertArrayEquals(new char[]{'a'}, array);
        assertEquals(Character.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a', 'a');
        assertArrayEquals(new char[]{'b'}, array);
        assertEquals(Character.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a', 'a', 'a', 'a');
        assertArrayEquals(new char[]{'b'}, array);
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray() {
        double[] array;

        array = ArrayUtils.removeElements((double[]) null, (double) 1);
        assertNull(array);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);
        assertArrayEquals(ArrayUtils.EMPTY_DOUBLE_ARRAY, array);
        assertEquals(Double.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1);
        assertArrayEquals(ArrayUtils.EMPTY_DOUBLE_ARRAY, array);
        assertEquals(Double.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1);
        assertArrayEquals(new double[]{2}, array);
        assertEquals(Double.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1);
        assertArrayEquals(new double[]{2, 1}, array);
        assertEquals(Double.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements((double[]) null, (double) 1, (double) 2);
        assertNull(array);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1, (double) 2);
        assertArrayEquals(ArrayUtils.EMPTY_DOUBLE_ARRAY, array);
        assertEquals(Double.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1, (double) 2);
        assertArrayEquals(ArrayUtils.EMPTY_DOUBLE_ARRAY, array);
        assertEquals(Double.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1, (double) 2);
        assertArrayEquals(ArrayUtils.EMPTY_DOUBLE_ARRAY, array);
        assertEquals(Double.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1, (double) 1);
        assertArrayEquals(new double[]{2}, array);
        assertEquals(Double.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1, (double) 2);
        assertArrayEquals(new double[]{1}, array);
        assertEquals(Double.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1, (double) 1);
        assertArrayEquals(new double[]{2}, array);
        assertEquals(Double.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1, (double) 1, (double) 1, (double) 1);
        assertArrayEquals(new double[]{2}, array);
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray() {
        float[] array;

        array = ArrayUtils.removeElements((float[]) null, (float) 1);
        assertNull(array);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);
        assertArrayEquals(ArrayUtils.EMPTY_FLOAT_ARRAY, array);
        assertEquals(Float.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1);
        assertArrayEquals(ArrayUtils.EMPTY_FLOAT_ARRAY, array);
        assertEquals(Float.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1);
        assertArrayEquals(new float[]{2}, array);
        assertEquals(Float.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1);
        assertArrayEquals(new float[]{2, 1}, array);
        assertEquals(Float.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements((float[]) null, (float) 1, (float) 1);
        assertNull(array);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1, (float) 1);
        assertArrayEquals(ArrayUtils.EMPTY_FLOAT_ARRAY, array);
        assertEquals(Float.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1, (float) 1);
        assertArrayEquals(ArrayUtils.EMPTY_FLOAT_ARRAY, array);
        assertEquals(Float.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1, (float) 2);
        assertArrayEquals(ArrayUtils.EMPTY_FLOAT_ARRAY, array);
        assertEquals(Float.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1, (float) 1);
        assertArrayEquals(new float[]{2}, array);
        assertEquals(Float.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1, (float) 1);
        assertArrayEquals(new float[]{2}, array);
        assertEquals(Float.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1, (float) 2);
        assertArrayEquals(new float[]{1}, array);
        assertEquals(Float.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1, (float) 1, (float) 1, (float) 1);
        assertArrayEquals(new float[]{2}, array);
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementIntArray() {
        int[] array;

        array = ArrayUtils.removeElements((int[]) null, 1);
        assertNull(array);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1);
        assertArrayEquals(ArrayUtils.EMPTY_INT_ARRAY, array);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new int[] { 1 }, 1);
        assertArrayEquals(ArrayUtils.EMPTY_INT_ARRAY, array);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1);
        assertArrayEquals(new int[]{2}, array);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1);
        assertArrayEquals(new int[]{2, 1}, array);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements((int[]) null, 1);
        assertNull(array);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1, 1);
        assertArrayEquals(ArrayUtils.EMPTY_INT_ARRAY, array);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new int[] { 1 }, 1, 1);
        assertArrayEquals(ArrayUtils.EMPTY_INT_ARRAY, array);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1, 2);
        assertArrayEquals(ArrayUtils.EMPTY_INT_ARRAY, array);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1, 1);
        assertArrayEquals(new int[]{2}, array);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1, 2);
        assertArrayEquals(new int[]{1}, array);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1, 1);
        assertArrayEquals(new int[]{2}, array);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1, 1, 1, 1);
        assertArrayEquals(new int[]{2}, array);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray() {
        long[] array;

        array = ArrayUtils.removeElements((long[]) null, 1L);
        assertNull(array);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L);
        assertArrayEquals(ArrayUtils.EMPTY_LONG_ARRAY, array);
        assertEquals(Long.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L);
        assertArrayEquals(ArrayUtils.EMPTY_LONG_ARRAY, array);
        assertEquals(Long.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L);
        assertArrayEquals(new long[]{2}, array);
        assertEquals(Long.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L);
        assertArrayEquals(new long[]{2, 1}, array);
        assertEquals(Long.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements((long[]) null, 1L, 1L);
        assertNull(array);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L, 1L);
        assertArrayEquals(ArrayUtils.EMPTY_LONG_ARRAY, array);
        assertEquals(Long.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L, 1L);
        assertArrayEquals(ArrayUtils.EMPTY_LONG_ARRAY, array);
        assertEquals(Long.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L, 2L);
        assertArrayEquals(ArrayUtils.EMPTY_LONG_ARRAY, array);
        assertEquals(Long.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L, 1L);
        assertArrayEquals(new long[]{2}, array);
        assertEquals(Long.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L, 1L);
        assertArrayEquals(new long[]{2}, array);
        assertEquals(Long.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L, 2L);
        assertArrayEquals(new long[]{1}, array);
        assertEquals(Long.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L, 1L, 1L, 1L);
        assertArrayEquals(new long[]{2}, array);
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementShortArray() {
        short[] array;

        array = ArrayUtils.removeElements((short[]) null, (short) 1);
        assertNull(array);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);
        assertArrayEquals(ArrayUtils.EMPTY_SHORT_ARRAY, array);
        assertEquals(Short.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1);
        assertArrayEquals(ArrayUtils.EMPTY_SHORT_ARRAY, array);
        assertEquals(Short.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1);
        assertArrayEquals(new short[]{2}, array);
        assertEquals(Short.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1);
        assertArrayEquals(new short[]{2, 1}, array);
        assertEquals(Short.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements((short[]) null, (short) 1, (short) 1);
        assertNull(array);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1, (short) 1);
        assertArrayEquals(ArrayUtils.EMPTY_SHORT_ARRAY, array);
        assertEquals(Short.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1, (short) 1);
        assertArrayEquals(ArrayUtils.EMPTY_SHORT_ARRAY, array);
        assertEquals(Short.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1, (short) 2);
        assertArrayEquals(ArrayUtils.EMPTY_SHORT_ARRAY, array);
        assertEquals(Short.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1, (short) 1);
        assertArrayEquals(new short[]{2}, array);
        assertEquals(Short.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1, (short) 1);
        assertArrayEquals(new short[]{2}, array);
        assertEquals(Short.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1, (short) 2);
        assertArrayEquals(new short[]{1}, array);
        assertEquals(Short.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1, (short) 1, (short) 1, (short) 1);
        assertArrayEquals(new short[]{2}, array);
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementsObjectArray() {
        Object[] array;

        array = ArrayUtils.removeElements((Object[]) null, "a");
        assertNull(array);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");
        assertArrayEquals(ArrayUtils.EMPTY_OBJECT_ARRAY, array);
        assertEquals(Object.class, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a");
        assertArrayEquals(ArrayUtils.EMPTY_OBJECT_ARRAY, array);
        assertEquals(Object.class, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a");
        assertArrayEquals(new Object[]{"b"}, array);
        assertEquals(Object.class, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");
        assertArrayEquals(new Object[]{"b", "a"}, array);
        assertEquals(Object.class, array.getClass().getComponentType());

        array = ArrayUtils.removeElements((Object[]) null, "a", "b");
        assertNull(array);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a", "b");
        assertArrayEquals(ArrayUtils.EMPTY_OBJECT_ARRAY, array);
        assertEquals(Object.class, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a", "b");
        assertArrayEquals(ArrayUtils.EMPTY_OBJECT_ARRAY, array);
        assertEquals(Object.class, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a", "c");
        assertArrayEquals(new Object[]{"b"}, array);
        assertEquals(Object.class, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");
        assertArrayEquals(new Object[]{"b", "a"}, array);
        assertEquals(Object.class, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a", "b");
        assertArrayEquals(new Object[]{"a"}, array);
        assertEquals(Object.class, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a", "a");
        assertArrayEquals(new Object[]{"b"}, array);
        assertEquals(Object.class, array.getClass().getComponentType());

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a", "a", "a", "a");
        assertArrayEquals(new Object[]{"b"}, array);
        assertEquals(Object.class, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllBooleanArray_1_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);
        assertArrayEquals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, array);
    }

    @Test
    public void testRemoveAllBooleanArray_2_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllBooleanArray_3_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);
        assertArrayEquals(new boolean[]{false}, array);
    }

    @Test
    public void testRemoveAllBooleanArray_4_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllBooleanArray_5_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 1);
        assertArrayEquals(new boolean[]{true}, array);
    }

    @Test
    public void testRemoveAllBooleanArray_6_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 1);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllBooleanArray_7_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true }, 1);
        assertArrayEquals(new boolean[]{true, true}, array);
    }

    @Test
    public void testRemoveAllBooleanArray_8_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true }, 1);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllBooleanArray_9_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0, 1);
        assertArrayEquals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, array);
    }

    @Test
    public void testRemoveAllBooleanArray_10_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0, 1);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllBooleanArray_11_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 1);
        assertArrayEquals(new boolean[]{false}, array);
    }

    @Test
    public void testRemoveAllBooleanArray_12_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 1);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllBooleanArray_13_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 2);
        assertArrayEquals(new boolean[]{false}, array);
    }

    @Test
    public void testRemoveAllBooleanArray_14_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 2);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllBooleanArray_15_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 2);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 1, 2);
        assertArrayEquals(new boolean[]{true}, array);
    }

    @Test
    public void testRemoveAllBooleanArray_16_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 2);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 1, 2);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllBooleanArray_17_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 2);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 1, 2);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 0, 2, 4);
        assertArrayEquals(new boolean[]{false, false}, array);
    }

    @Test
    public void testRemoveAllBooleanArray_18_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 2);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 1, 2);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 0, 2, 4);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllBooleanArray_19_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 2);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 1, 2);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 0, 2, 4);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 1, 3);
        assertArrayEquals(new boolean[]{true, true, true}, array);
    }

    @Test
    public void testRemoveAllBooleanArray_20_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 2);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 1, 2);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 0, 2, 4);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 1, 3);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllBooleanArray_21_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 2);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 1, 2);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 0, 2, 4);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 1, 3);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 1, 3, 4);
        assertArrayEquals(new boolean[]{true, true}, array);
    }

    @Test
    public void testRemoveAllBooleanArray_22_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 2);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 1, 2);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 0, 2, 4);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 1, 3);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 1, 3, 4);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllBooleanArray_23_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 2);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 1, 2);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 0, 2, 4);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 1, 3);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 1, 3, 4);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true, false, true }, 0, 2, 4, 6);
        assertArrayEquals(new boolean[]{false, false, false}, array);
    }

    @Test
    public void testRemoveAllBooleanArray_24_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 2);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 1, 2);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 0, 2, 4);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 1, 3);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 1, 3, 4);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true, false, true }, 0, 2, 4, 6);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllBooleanArray_25_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 2);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 1, 2);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 0, 2, 4);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 1, 3);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 1, 3, 4);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true, false, true }, 0, 2, 4, 6);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true, false, true }, 1, 3, 5);
        assertArrayEquals(new boolean[]{true, true, true, true}, array);
    }

    @Test
    public void testRemoveAllBooleanArray_26_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 2);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 1, 2);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 0, 2, 4);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 1, 3);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 1, 3, 4);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true, false, true }, 0, 2, 4, 6);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true, false, true }, 1, 3, 5);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllBooleanArray_27_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 2);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 1, 2);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 0, 2, 4);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 1, 3);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 1, 3, 4);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true, false, true }, 0, 2, 4, 6);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true, false, true }, 1, 3, 5);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true, false, true }, 0, 1, 2);
        assertArrayEquals(new boolean[]{false, true, false, true}, array);
    }

    @Test
    public void testRemoveAllBooleanArray_28_oe() {
        boolean[] array;

        array = ArrayUtils.removeAll(new boolean[] { true }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true }, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 1);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 2);

        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 1, 2);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 0, 2, 4);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 1, 3);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 1, 3, 4);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true, false, true }, 0, 2, 4, 6);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true, false, true }, 1, 3, 5);

        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true, false, true }, 0, 1, 2);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllBooleanArrayNegativeIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new boolean[] { true, false }, -1);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllBooleanArrayOutOfBoundsIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new boolean[] { true, false }, 2);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllBooleanArrayRemoveNone_1_oe() {
        final boolean[] array1 = new boolean[] { true, false };
        final boolean[] array2 = ArrayUtils.removeAll(array1);
        assertNotSame(array1, array2);
    }

    @Test
    public void testRemoveAllBooleanArrayRemoveNone_2_oe() {
        final boolean[] array1 = new boolean[] { true, false };
        final boolean[] array2 = ArrayUtils.removeAll(array1);
        assertArrayEquals(array1, array2);
    }

    @Test
    public void testRemoveAllBooleanArrayRemoveNone_3_oe() {
        final boolean[] array1 = new boolean[] { true, false };
        final boolean[] array2 = ArrayUtils.removeAll(array1);
        assertEquals(boolean.class, array2.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllByteArray_1_oe() {
        byte[] array;

        array = ArrayUtils.removeAll(new byte[] { 1 }, 0);
        assertArrayEquals(ArrayUtils.EMPTY_BYTE_ARRAY, array);
    }

    @Test
    public void testRemoveAllByteArray_2_oe() {
        byte[] array;

        array = ArrayUtils.removeAll(new byte[] { 1 }, 0);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllByteArray_3_oe() {
        byte[] array;

        array = ArrayUtils.removeAll(new byte[] { 1 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0);
        assertArrayEquals(new byte[]{2}, array);
    }

    @Test
    public void testRemoveAllByteArray_4_oe() {
        byte[] array;

        array = ArrayUtils.removeAll(new byte[] { 1 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllByteArray_5_oe() {
        byte[] array;

        array = ArrayUtils.removeAll(new byte[] { 1 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 1);
        assertArrayEquals(new byte[]{1}, array);
    }

    @Test
    public void testRemoveAllByteArray_6_oe() {
        byte[] array;

        array = ArrayUtils.removeAll(new byte[] { 1 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 1);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllByteArray_7_oe() {
        byte[] array;

        array = ArrayUtils.removeAll(new byte[] { 1 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 1 }, 1);
        assertArrayEquals(new byte[]{1, 1}, array);
    }

    @Test
    public void testRemoveAllByteArray_8_oe() {
        byte[] array;

        array = ArrayUtils.removeAll(new byte[] { 1 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 1 }, 1);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllByteArray_9_oe() {
        byte[] array;

        array = ArrayUtils.removeAll(new byte[] { 1 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0, 1);
        assertArrayEquals(ArrayUtils.EMPTY_BYTE_ARRAY, array);
    }

    @Test
    public void testRemoveAllByteArray_10_oe() {
        byte[] array;

        array = ArrayUtils.removeAll(new byte[] { 1 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0, 1);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllByteArray_11_oe() {
        byte[] array;

        array = ArrayUtils.removeAll(new byte[] { 1 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 1);
        assertArrayEquals(new byte[]{3}, array);
    }

    @Test
    public void testRemoveAllByteArray_12_oe() {
        byte[] array;

        array = ArrayUtils.removeAll(new byte[] { 1 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 1);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllByteArray_13_oe() {
        byte[] array;

        array = ArrayUtils.removeAll(new byte[] { 1 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 1, 2);
        assertArrayEquals(new byte[]{1}, array);
    }

    @Test
    public void testRemoveAllByteArray_14_oe() {
        byte[] array;

        array = ArrayUtils.removeAll(new byte[] { 1 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 1, 2);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllByteArray_15_oe() {
        byte[] array;

        array = ArrayUtils.removeAll(new byte[] { 1 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 2);
        assertArrayEquals(new byte[]{2}, array);
    }

    @Test
    public void testRemoveAllByteArray_16_oe() {
        byte[] array;

        array = ArrayUtils.removeAll(new byte[] { 1 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 2);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllByteArray_17_oe() {
        byte[] array;

        array = ArrayUtils.removeAll(new byte[] { 1 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5 }, 1, 3);
        assertArrayEquals(new byte[]{1, 3, 5}, array);
    }

    @Test
    public void testRemoveAllByteArray_18_oe() {
        byte[] array;

        array = ArrayUtils.removeAll(new byte[] { 1 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5 }, 1, 3);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllByteArray_19_oe() {
        byte[] array;

        array = ArrayUtils.removeAll(new byte[] { 1 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5 }, 0, 2, 4);
        assertArrayEquals(new byte[]{2, 4}, array);
    }

    @Test
    public void testRemoveAllByteArray_20_oe() {
        byte[] array;

        array = ArrayUtils.removeAll(new byte[] { 1 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5 }, 0, 2, 4);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllByteArray_21_oe() {
        byte[] array;

        array = ArrayUtils.removeAll(new byte[] { 1 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5 }, 0, 2, 4);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);
        assertArrayEquals(new byte[]{1, 3, 5, 7}, array);
    }

    @Test
    public void testRemoveAllByteArray_22_oe() {
        byte[] array;

        array = ArrayUtils.removeAll(new byte[] { 1 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5 }, 0, 2, 4);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllByteArray_23_oe() {
        byte[] array;

        array = ArrayUtils.removeAll(new byte[] { 1 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5 }, 0, 2, 4);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5, 6, 7 }, 0, 2, 4, 6);
        assertArrayEquals(new byte[]{2, 4, 6}, array);
    }

    @Test
    public void testRemoveAllByteArray_24_oe() {
        byte[] array;

        array = ArrayUtils.removeAll(new byte[] { 1 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5 }, 0, 2, 4);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);

        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5, 6, 7 }, 0, 2, 4, 6);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllByteArrayNegativeIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new byte[] { 1, 2 }, -1);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllByteArrayOutOfBoundsIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new byte[] { 1, 2 }, 2);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllByteArrayRemoveNone_1_oe() {
        final byte[] array1 = new byte[] { 1, 2 };
        final byte[] array2 = ArrayUtils.removeAll(array1);
        assertNotSame(array1, array2);
    }

    @Test
    public void testRemoveAllByteArrayRemoveNone_2_oe() {
        final byte[] array1 = new byte[] { 1, 2 };
        final byte[] array2 = ArrayUtils.removeAll(array1);
        assertArrayEquals(array1, array2);
    }

    @Test
    public void testRemoveAllByteArrayRemoveNone_3_oe() {
        final byte[] array1 = new byte[] { 1, 2 };
        final byte[] array2 = ArrayUtils.removeAll(array1);
        assertEquals(byte.class, array2.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllCharArray_1_oe() {
        char[] array;

        array = ArrayUtils.removeAll(new char[] { 'a' }, 0);
        assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, array);
    }

    @Test
    public void testRemoveAllCharArray_2_oe() {
        char[] array;

        array = ArrayUtils.removeAll(new char[] { 'a' }, 0);
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllCharArray_3_oe() {
        char[] array;

        array = ArrayUtils.removeAll(new char[] { 'a' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0);
        assertArrayEquals(new char[]{'b'}, array);
    }

    @Test
    public void testRemoveAllCharArray_4_oe() {
        char[] array;

        array = ArrayUtils.removeAll(new char[] { 'a' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0);
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllCharArray_5_oe() {
        char[] array;

        array = ArrayUtils.removeAll(new char[] { 'a' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 1);
        assertArrayEquals(new char[]{'a'}, array);
    }

    @Test
    public void testRemoveAllCharArray_6_oe() {
        char[] array;

        array = ArrayUtils.removeAll(new char[] { 'a' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 1);
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllCharArray_7_oe() {
        char[] array;

        array = ArrayUtils.removeAll(new char[] { 'a' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1);
        assertArrayEquals(new char[]{'a', 'c'}, array);
    }

    @Test
    public void testRemoveAllCharArray_8_oe() {
        char[] array;

        array = ArrayUtils.removeAll(new char[] { 'a' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1);
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllCharArray_9_oe() {
        char[] array;

        array = ArrayUtils.removeAll(new char[] { 'a' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0, 1);
        assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, array);
    }

    @Test
    public void testRemoveAllCharArray_10_oe() {
        char[] array;

        array = ArrayUtils.removeAll(new char[] { 'a' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0, 1);
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllCharArray_11_oe() {
        char[] array;

        array = ArrayUtils.removeAll(new char[] { 'a' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 1);
        assertArrayEquals(new char[]{'c'}, array);
    }

    @Test
    public void testRemoveAllCharArray_12_oe() {
        char[] array;

        array = ArrayUtils.removeAll(new char[] { 'a' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 1);
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllCharArray_13_oe() {
        char[] array;

        array = ArrayUtils.removeAll(new char[] { 'a' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1, 2);
        assertArrayEquals(new char[]{'a'}, array);
    }

    @Test
    public void testRemoveAllCharArray_14_oe() {
        char[] array;

        array = ArrayUtils.removeAll(new char[] { 'a' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1, 2);
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllCharArray_15_oe() {
        char[] array;

        array = ArrayUtils.removeAll(new char[] { 'a' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1, 2);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 2);
        assertArrayEquals(new char[]{'b'}, array);
    }

    @Test
    public void testRemoveAllCharArray_16_oe() {
        char[] array;

        array = ArrayUtils.removeAll(new char[] { 'a' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1, 2);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 2);
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllCharArray_17_oe() {
        char[] array;

        array = ArrayUtils.removeAll(new char[] { 'a' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1, 2);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 2);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e' }, 1, 3);
        assertArrayEquals(new char[]{'a', 'c', 'e'}, array);
    }

    @Test
    public void testRemoveAllCharArray_18_oe() {
        char[] array;

        array = ArrayUtils.removeAll(new char[] { 'a' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1, 2);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 2);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e' }, 1, 3);
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllCharArray_19_oe() {
        char[] array;

        array = ArrayUtils.removeAll(new char[] { 'a' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1, 2);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 2);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e' }, 1, 3);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e' }, 0, 2, 4);
        assertArrayEquals(new char[]{'b', 'd'}, array);
    }

    @Test
    public void testRemoveAllCharArray_20_oe() {
        char[] array;

        array = ArrayUtils.removeAll(new char[] { 'a' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1, 2);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 2);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e' }, 1, 3);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e' }, 0, 2, 4);
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllCharArray_21_oe() {
        char[] array;

        array = ArrayUtils.removeAll(new char[] { 'a' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1, 2);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 2);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e' }, 1, 3);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e' }, 0, 2, 4);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g' }, 1, 3, 5);
        assertArrayEquals(new char[]{'a', 'c', 'e', 'g'}, array);
    }

    @Test
    public void testRemoveAllCharArray_22_oe() {
        char[] array;

        array = ArrayUtils.removeAll(new char[] { 'a' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1, 2);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 2);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e' }, 1, 3);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e' }, 0, 2, 4);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g' }, 1, 3, 5);
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllCharArray_23_oe() {
        char[] array;

        array = ArrayUtils.removeAll(new char[] { 'a' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1, 2);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 2);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e' }, 1, 3);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e' }, 0, 2, 4);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g' }, 1, 3, 5);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g' }, 0, 2, 4, 6);
        assertArrayEquals(new char[]{'b', 'd', 'f'}, array);
    }

    @Test
    public void testRemoveAllCharArray_24_oe() {
        char[] array;

        array = ArrayUtils.removeAll(new char[] { 'a' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 1);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1, 2);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 2);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e' }, 1, 3);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e' }, 0, 2, 4);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g' }, 1, 3, 5);

        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g' }, 0, 2, 4, 6);
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllCharArrayNegativeIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new char[] { 'a', 'b' }, -1);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllCharArrayOutOfBoundsIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new char[] { 'a', 'b' }, 2);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllCharArrayRemoveNone_1_oe() {
        final char[] array1 = new char[] { 'a', 'b' };
        final char[] array2 = ArrayUtils.removeAll(array1);
        assertNotSame(array1, array2);
    }

    @Test
    public void testRemoveAllCharArrayRemoveNone_2_oe() {
        final char[] array1 = new char[] { 'a', 'b' };
        final char[] array2 = ArrayUtils.removeAll(array1);
        assertArrayEquals(array1, array2);
    }

    @Test
    public void testRemoveAllCharArrayRemoveNone_3_oe() {
        final char[] array1 = new char[] { 'a', 'b' };
        final char[] array2 = ArrayUtils.removeAll(array1);
        assertEquals(char.class, array2.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllDoubleArray_1_oe() {
        double[] array;

        array = ArrayUtils.removeAll(new double[] { 1 }, 0);
        assertArrayEquals(ArrayUtils.EMPTY_DOUBLE_ARRAY, array);
    }

    @Test
    public void testRemoveAllDoubleArray_2_oe() {
        double[] array;

        array = ArrayUtils.removeAll(new double[] { 1 }, 0);
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllDoubleArray_3_oe() {
        double[] array;

        array = ArrayUtils.removeAll(new double[] { 1 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0);
        assertArrayEquals(new double[]{2}, array);
    }

    @Test
    public void testRemoveAllDoubleArray_4_oe() {
        double[] array;

        array = ArrayUtils.removeAll(new double[] { 1 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0);
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllDoubleArray_5_oe() {
        double[] array;

        array = ArrayUtils.removeAll(new double[] { 1 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 1);
        assertArrayEquals(new double[]{1}, array);
    }

    @Test
    public void testRemoveAllDoubleArray_6_oe() {
        double[] array;

        array = ArrayUtils.removeAll(new double[] { 1 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 1);
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllDoubleArray_7_oe() {
        double[] array;

        array = ArrayUtils.removeAll(new double[] { 1 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 1 }, 1);
        assertArrayEquals(new double[]{1, 1}, array);
    }

    @Test
    public void testRemoveAllDoubleArray_8_oe() {
        double[] array;

        array = ArrayUtils.removeAll(new double[] { 1 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 1 }, 1);
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllDoubleArray_9_oe() {
        double[] array;

        array = ArrayUtils.removeAll(new double[] { 1 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0, 1);
        assertArrayEquals(ArrayUtils.EMPTY_DOUBLE_ARRAY, array);
    }

    @Test
    public void testRemoveAllDoubleArray_10_oe() {
        double[] array;

        array = ArrayUtils.removeAll(new double[] { 1 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0, 1);
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllDoubleArray_11_oe() {
        double[] array;

        array = ArrayUtils.removeAll(new double[] { 1 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 1);
        assertArrayEquals(new double[]{3}, array);
    }

    @Test
    public void testRemoveAllDoubleArray_12_oe() {
        double[] array;

        array = ArrayUtils.removeAll(new double[] { 1 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 1);
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllDoubleArray_13_oe() {
        double[] array;

        array = ArrayUtils.removeAll(new double[] { 1 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 1, 2);
        assertArrayEquals(new double[]{1}, array);
    }

    @Test
    public void testRemoveAllDoubleArray_14_oe() {
        double[] array;

        array = ArrayUtils.removeAll(new double[] { 1 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 1, 2);
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllDoubleArray_15_oe() {
        double[] array;

        array = ArrayUtils.removeAll(new double[] { 1 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 2);
        assertArrayEquals(new double[]{2}, array);
    }

    @Test
    public void testRemoveAllDoubleArray_16_oe() {
        double[] array;

        array = ArrayUtils.removeAll(new double[] { 1 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 2);
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllDoubleArray_17_oe() {
        double[] array;

        array = ArrayUtils.removeAll(new double[] { 1 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5 }, 1, 3);
        assertArrayEquals(new double[]{1, 3, 5}, array);
    }

    @Test
    public void testRemoveAllDoubleArray_18_oe() {
        double[] array;

        array = ArrayUtils.removeAll(new double[] { 1 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5 }, 1, 3);
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllDoubleArray_19_oe() {
        double[] array;

        array = ArrayUtils.removeAll(new double[] { 1 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5 }, 0, 2, 4);
        assertArrayEquals(new double[]{2, 4}, array);
    }

    @Test
    public void testRemoveAllDoubleArray_20_oe() {
        double[] array;

        array = ArrayUtils.removeAll(new double[] { 1 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5 }, 0, 2, 4);
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllDoubleArray_21_oe() {
        double[] array;

        array = ArrayUtils.removeAll(new double[] { 1 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5 }, 0, 2, 4);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);
        assertArrayEquals(new double[]{1, 3, 5, 7}, array);
    }

    @Test
    public void testRemoveAllDoubleArray_22_oe() {
        double[] array;

        array = ArrayUtils.removeAll(new double[] { 1 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5 }, 0, 2, 4);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllDoubleArray_23_oe() {
        double[] array;

        array = ArrayUtils.removeAll(new double[] { 1 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5 }, 0, 2, 4);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5, 6, 7 }, 0, 2, 4, 6);
        assertArrayEquals(new double[]{2, 4, 6}, array);
    }

    @Test
    public void testRemoveAllDoubleArray_24_oe() {
        double[] array;

        array = ArrayUtils.removeAll(new double[] { 1 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5 }, 0, 2, 4);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);

        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5, 6, 7 }, 0, 2, 4, 6);
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllDoubleArrayNegativeIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new double[] { 1, 2 }, -1);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllDoubleArrayOutOfBoundsIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new double[] { 1, 2 }, 2);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllDoubleArrayRemoveNone_1_oe() {
        final double[] array1 = new double[] { 1, 2 };
        final double[] array2 = ArrayUtils.removeAll(array1);
        assertNotSame(array1, array2);
    }

    @Test
    public void testRemoveAllDoubleArrayRemoveNone_2_oe() {
        final double[] array1 = new double[] { 1, 2 };
        final double[] array2 = ArrayUtils.removeAll(array1);
        assertArrayEquals(array1, array2);
    }

    @Test
    public void testRemoveAllDoubleArrayRemoveNone_3_oe() {
        final double[] array1 = new double[] { 1, 2 };
        final double[] array2 = ArrayUtils.removeAll(array1);
        assertEquals(double.class, array2.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllFloatArray_1_oe() {
        float[] array;

        array = ArrayUtils.removeAll(new float[] { 1 }, 0);
        assertArrayEquals(ArrayUtils.EMPTY_FLOAT_ARRAY, array);
    }

    @Test
    public void testRemoveAllFloatArray_2_oe() {
        float[] array;

        array = ArrayUtils.removeAll(new float[] { 1 }, 0);
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllFloatArray_3_oe() {
        float[] array;

        array = ArrayUtils.removeAll(new float[] { 1 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0);
        assertArrayEquals(new float[]{2}, array);
    }

    @Test
    public void testRemoveAllFloatArray_4_oe() {
        float[] array;

        array = ArrayUtils.removeAll(new float[] { 1 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0);
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllFloatArray_5_oe() {
        float[] array;

        array = ArrayUtils.removeAll(new float[] { 1 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 1);
        assertArrayEquals(new float[]{1}, array);
    }

    @Test
    public void testRemoveAllFloatArray_6_oe() {
        float[] array;

        array = ArrayUtils.removeAll(new float[] { 1 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 1);
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllFloatArray_7_oe() {
        float[] array;

        array = ArrayUtils.removeAll(new float[] { 1 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 1 }, 1);
        assertArrayEquals(new float[]{1, 1}, array);
    }

    @Test
    public void testRemoveAllFloatArray_8_oe() {
        float[] array;

        array = ArrayUtils.removeAll(new float[] { 1 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 1 }, 1);
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllFloatArray_9_oe() {
        float[] array;

        array = ArrayUtils.removeAll(new float[] { 1 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0, 1);
        assertArrayEquals(ArrayUtils.EMPTY_FLOAT_ARRAY, array);
    }

    @Test
    public void testRemoveAllFloatArray_10_oe() {
        float[] array;

        array = ArrayUtils.removeAll(new float[] { 1 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0, 1);
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllFloatArray_11_oe() {
        float[] array;

        array = ArrayUtils.removeAll(new float[] { 1 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 1);
        assertArrayEquals(new float[]{3}, array);
    }

    @Test
    public void testRemoveAllFloatArray_12_oe() {
        float[] array;

        array = ArrayUtils.removeAll(new float[] { 1 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 1);
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllFloatArray_13_oe() {
        float[] array;

        array = ArrayUtils.removeAll(new float[] { 1 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 1, 2);
        assertArrayEquals(new float[]{1}, array);
    }

    @Test
    public void testRemoveAllFloatArray_14_oe() {
        float[] array;

        array = ArrayUtils.removeAll(new float[] { 1 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 1, 2);
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllFloatArray_15_oe() {
        float[] array;

        array = ArrayUtils.removeAll(new float[] { 1 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 2);
        assertArrayEquals(new float[]{2}, array);
    }

    @Test
    public void testRemoveAllFloatArray_16_oe() {
        float[] array;

        array = ArrayUtils.removeAll(new float[] { 1 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 2);
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllFloatArray_17_oe() {
        float[] array;

        array = ArrayUtils.removeAll(new float[] { 1 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5 }, 1, 3);
        assertArrayEquals(new float[]{1, 3, 5}, array);
    }

    @Test
    public void testRemoveAllFloatArray_18_oe() {
        float[] array;

        array = ArrayUtils.removeAll(new float[] { 1 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5 }, 1, 3);
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllFloatArray_19_oe() {
        float[] array;

        array = ArrayUtils.removeAll(new float[] { 1 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5 }, 0, 2, 4);
        assertArrayEquals(new float[]{2, 4}, array);
    }

    @Test
    public void testRemoveAllFloatArray_20_oe() {
        float[] array;

        array = ArrayUtils.removeAll(new float[] { 1 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5 }, 0, 2, 4);
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllFloatArray_21_oe() {
        float[] array;

        array = ArrayUtils.removeAll(new float[] { 1 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5 }, 0, 2, 4);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);
        assertArrayEquals(new float[]{1, 3, 5, 7}, array);
    }

    @Test
    public void testRemoveAllFloatArray_22_oe() {
        float[] array;

        array = ArrayUtils.removeAll(new float[] { 1 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5 }, 0, 2, 4);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllFloatArray_23_oe() {
        float[] array;

        array = ArrayUtils.removeAll(new float[] { 1 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5 }, 0, 2, 4);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5, 6, 7 }, 0, 2, 4, 6);
        assertArrayEquals(new float[]{2, 4, 6}, array);
    }

    @Test
    public void testRemoveAllFloatArray_24_oe() {
        float[] array;

        array = ArrayUtils.removeAll(new float[] { 1 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5 }, 0, 2, 4);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);

        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5, 6, 7 }, 0, 2, 4, 6);
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllFloatArrayNegativeIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new float[] { 1, 2 }, -1);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllFloatArrayOutOfBoundsIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new float[] { 1, 2 }, 2);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllFloatArrayRemoveNone_1_oe() {
        final float[] array1 = new float[] { 1, 2 };
        final float[] array2 = ArrayUtils.removeAll(array1);
        assertNotSame(array1, array2);
    }

    @Test
    public void testRemoveAllFloatArrayRemoveNone_2_oe() {
        final float[] array1 = new float[] { 1, 2 };
        final float[] array2 = ArrayUtils.removeAll(array1);
        assertArrayEquals(array1, array2);
    }

    @Test
    public void testRemoveAllFloatArrayRemoveNone_3_oe() {
        final float[] array1 = new float[] { 1, 2 };
        final float[] array2 = ArrayUtils.removeAll(array1);
        assertEquals(float.class, array2.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllIntArray_1_oe() {
        int[] array;

        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);
        assertArrayEquals(ArrayUtils.EMPTY_INT_ARRAY, array);
    }

    @Test
    public void testRemoveAllIntArray_2_oe() {
        int[] array;

        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, ArrayUtils.EMPTY_INT_ARRAY);
        assertArrayEquals(new int[]{1}, array);
    }

    @Test
    public void testRemoveAllIntArray_3_oe() {
        int[] array;

        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, 0);
        assertArrayEquals(ArrayUtils.EMPTY_INT_ARRAY, array);
    }

    @Test
    public void testRemoveAllIntArray_4_oe() {
        int[] array;

        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, 0);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllIntArray_5_oe() {
        int[] array;

        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0);
        assertArrayEquals(new int[]{2}, array);
    }

    @Test
    public void testRemoveAllIntArray_6_oe() {
        int[] array;

        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllIntArray_7_oe() {
        int[] array;

        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 1);
        assertArrayEquals(new int[]{1}, array);
    }

    @Test
    public void testRemoveAllIntArray_8_oe() {
        int[] array;

        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 1);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllIntArray_9_oe() {
        int[] array;

        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 1 }, 1);
        assertArrayEquals(new int[]{1, 1}, array);
    }

    @Test
    public void testRemoveAllIntArray_10_oe() {
        int[] array;

        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 1 }, 1);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllIntArray_11_oe() {
        int[] array;

        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0, 1);
        assertArrayEquals(ArrayUtils.EMPTY_INT_ARRAY, array);
    }

    @Test
    public void testRemoveAllIntArray_12_oe() {
        int[] array;

        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0, 1);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllIntArray_13_oe() {
        int[] array;

        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 1);
        assertArrayEquals(new int[]{3}, array);
    }

    @Test
    public void testRemoveAllIntArray_14_oe() {
        int[] array;

        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 1);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllIntArray_15_oe() {
        int[] array;

        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 1, 2);
        assertArrayEquals(new int[]{1}, array);
    }

    @Test
    public void testRemoveAllIntArray_16_oe() {
        int[] array;

        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 1, 2);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllIntArray_17_oe() {
        int[] array;

        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 2);
        assertArrayEquals(new int[]{2}, array);
    }

    @Test
    public void testRemoveAllIntArray_18_oe() {
        int[] array;

        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 2);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllIntArray_19_oe() {
        int[] array;

        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5 }, 1, 3);
        assertArrayEquals(new int[]{1, 3, 5}, array);
    }

    @Test
    public void testRemoveAllIntArray_20_oe() {
        int[] array;

        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5 }, 1, 3);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllIntArray_21_oe() {
        int[] array;

        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5 }, 0, 2, 4);
        assertArrayEquals(new int[]{2, 4}, array);
    }

    @Test
    public void testRemoveAllIntArray_22_oe() {
        int[] array;

        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5 }, 0, 2, 4);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllIntArray_23_oe() {
        int[] array;

        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5 }, 0, 2, 4);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);
        assertArrayEquals(new int[]{1, 3, 5, 7}, array);
    }

    @Test
    public void testRemoveAllIntArray_24_oe() {
        int[] array;

        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5 }, 0, 2, 4);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllIntArray_25_oe() {
        int[] array;

        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5 }, 0, 2, 4);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 0, 2, 4, 6);
        assertArrayEquals(new int[]{2, 4, 6}, array);
    }

    @Test
    public void testRemoveAllIntArray_26_oe() {
        int[] array;

        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, ArrayUtils.EMPTY_INT_ARRAY);

        array = ArrayUtils.removeAll(new int[] { 1 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5 }, 0, 2, 4);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);

        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 0, 2, 4, 6);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllIntArrayNegativeIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new int[] { 1, 2 }, -1);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllIntArrayOutOfBoundsIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new int[] { 1, 2 }, 2);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllIntArrayRemoveNone_1_oe() {
        final int[] array1 = new int[] { 1, 2 };
        final int[] array2 = ArrayUtils.removeAll(array1);
        assertNotSame(array1, array2);
    }

    @Test
    public void testRemoveAllIntArrayRemoveNone_2_oe() {
        final int[] array1 = new int[] { 1, 2 };
        final int[] array2 = ArrayUtils.removeAll(array1);
        assertArrayEquals(array1, array2);
    }

    @Test
    public void testRemoveAllIntArrayRemoveNone_3_oe() {
        final int[] array1 = new int[] { 1, 2 };
        final int[] array2 = ArrayUtils.removeAll(array1);
        assertEquals(int.class, array2.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllLongArray_1_oe() {
        long[] array;

        array = ArrayUtils.removeAll(new long[] { 1 }, 0);
        assertArrayEquals(ArrayUtils.EMPTY_LONG_ARRAY, array);
    }

    @Test
    public void testRemoveAllLongArray_2_oe() {
        long[] array;

        array = ArrayUtils.removeAll(new long[] { 1 }, 0);
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllLongArray_3_oe() {
        long[] array;

        array = ArrayUtils.removeAll(new long[] { 1 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0);
        assertArrayEquals(new long[]{2}, array);
    }

    @Test
    public void testRemoveAllLongArray_4_oe() {
        long[] array;

        array = ArrayUtils.removeAll(new long[] { 1 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0);
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllLongArray_5_oe() {
        long[] array;

        array = ArrayUtils.removeAll(new long[] { 1 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 1);
        assertArrayEquals(new long[]{1}, array);
    }

    @Test
    public void testRemoveAllLongArray_6_oe() {
        long[] array;

        array = ArrayUtils.removeAll(new long[] { 1 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 1);
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllLongArray_7_oe() {
        long[] array;

        array = ArrayUtils.removeAll(new long[] { 1 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 1 }, 1);
        assertArrayEquals(new long[]{1, 1}, array);
    }

    @Test
    public void testRemoveAllLongArray_8_oe() {
        long[] array;

        array = ArrayUtils.removeAll(new long[] { 1 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 1 }, 1);
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllLongArray_9_oe() {
        long[] array;

        array = ArrayUtils.removeAll(new long[] { 1 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0, 1);
        assertArrayEquals(ArrayUtils.EMPTY_LONG_ARRAY, array);
    }

    @Test
    public void testRemoveAllLongArray_10_oe() {
        long[] array;

        array = ArrayUtils.removeAll(new long[] { 1 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0, 1);
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllLongArray_11_oe() {
        long[] array;

        array = ArrayUtils.removeAll(new long[] { 1 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 1);
        assertArrayEquals(new long[]{3}, array);
    }

    @Test
    public void testRemoveAllLongArray_12_oe() {
        long[] array;

        array = ArrayUtils.removeAll(new long[] { 1 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 1);
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllLongArray_13_oe() {
        long[] array;

        array = ArrayUtils.removeAll(new long[] { 1 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 1, 2);
        assertArrayEquals(new long[]{1}, array);
    }

    @Test
    public void testRemoveAllLongArray_14_oe() {
        long[] array;

        array = ArrayUtils.removeAll(new long[] { 1 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 1, 2);
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllLongArray_15_oe() {
        long[] array;

        array = ArrayUtils.removeAll(new long[] { 1 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 2);
        assertArrayEquals(new long[]{2}, array);
    }

    @Test
    public void testRemoveAllLongArray_16_oe() {
        long[] array;

        array = ArrayUtils.removeAll(new long[] { 1 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 2);
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllLongArray_17_oe() {
        long[] array;

        array = ArrayUtils.removeAll(new long[] { 1 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5 }, 1, 3);
        assertArrayEquals(new long[]{1, 3, 5}, array);
    }

    @Test
    public void testRemoveAllLongArray_18_oe() {
        long[] array;

        array = ArrayUtils.removeAll(new long[] { 1 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5 }, 1, 3);
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllLongArray_19_oe() {
        long[] array;

        array = ArrayUtils.removeAll(new long[] { 1 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5 }, 0, 2, 4);
        assertArrayEquals(new long[]{2, 4}, array);
    }

    @Test
    public void testRemoveAllLongArray_20_oe() {
        long[] array;

        array = ArrayUtils.removeAll(new long[] { 1 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5 }, 0, 2, 4);
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllLongArray_21_oe() {
        long[] array;

        array = ArrayUtils.removeAll(new long[] { 1 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5 }, 0, 2, 4);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);
        assertArrayEquals(new long[]{1, 3, 5, 7}, array);
    }

    @Test
    public void testRemoveAllLongArray_22_oe() {
        long[] array;

        array = ArrayUtils.removeAll(new long[] { 1 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5 }, 0, 2, 4);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllLongArray_23_oe() {
        long[] array;

        array = ArrayUtils.removeAll(new long[] { 1 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5 }, 0, 2, 4);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5, 6, 7 }, 0, 2, 4, 6);
        assertArrayEquals(new long[]{2, 4, 6}, array);
    }

    @Test
    public void testRemoveAllLongArray_24_oe() {
        long[] array;

        array = ArrayUtils.removeAll(new long[] { 1 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5 }, 0, 2, 4);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);

        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5, 6, 7 }, 0, 2, 4, 6);
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllLongArrayNegativeIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new long[] { 1, 2 }, -1);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllLongArrayOutOfBoundsIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new long[] { 1, 2 }, 2);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllLongArrayRemoveNone_1_oe() {
        final long[] array1 = new long[] { 1, 2 };
        final long[] array2 = ArrayUtils.removeAll(array1);
        assertNotSame(array1, array2);
    }

    @Test
    public void testRemoveAllLongArrayRemoveNone_2_oe() {
        final long[] array1 = new long[] { 1, 2 };
        final long[] array2 = ArrayUtils.removeAll(array1);
        assertArrayEquals(array1, array2);
    }

    @Test
    public void testRemoveAllLongArrayRemoveNone_3_oe() {
        final long[] array1 = new long[] { 1, 2 };
        final long[] array2 = ArrayUtils.removeAll(array1);
        assertEquals(long.class, array2.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllNullBooleanArray_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll((boolean[]) null, 0);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllNullByteArray_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll((byte[]) null, 0);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllNullCharArray_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll((char[]) null, 0);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllNullDoubleArray_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll((double[]) null, 0);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllNullFloatArray_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll((float[]) null, 0);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllNullIntArray_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll((int[]) null, 0);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllNullLongArray_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll((long[]) null, 0);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllNullObjectArray_1_oe() throws Exception {
        try {
    ArrayUtils.remove((Object[]) null, 0);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllNullShortArray_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll((short[]) null, 0);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllNumberArray_1_oe() {
        final Number[] inarray = { Integer.valueOf(1), Long.valueOf(2L), Byte.valueOf((byte) 3) };
        assertEquals(3, inarray.length);
    }

    @Test
    public void testRemoveAllNumberArray_2_oe() {
        final Number[] inarray = { Integer.valueOf(1), Long.valueOf(2L), Byte.valueOf((byte) 3) };
        Number[] outarray;

        outarray = ArrayUtils.removeAll(inarray, 1);
        assertArrayEquals(new Number[] { Integer.valueOf(1), Byte.valueOf((byte) 3) }, outarray);
    }

    @Test
    public void testRemoveAllNumberArray_3_oe() {
        final Number[] inarray = { Integer.valueOf(1), Long.valueOf(2L), Byte.valueOf((byte) 3) };
        Number[] outarray;

        outarray = ArrayUtils.removeAll(inarray, 1);
        assertEquals(Number.class, outarray.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllNumberArray_4_oe() {
        final Number[] inarray = { Integer.valueOf(1), Long.valueOf(2L), Byte.valueOf((byte) 3) };
        Number[] outarray;

        outarray = ArrayUtils.removeAll(inarray, 1);

        outarray = ArrayUtils.removeAll(outarray, 1);
        assertArrayEquals(new Number[] { Integer.valueOf(1) }, outarray);
    }

    @Test
    public void testRemoveAllNumberArray_5_oe() {
        final Number[] inarray = { Integer.valueOf(1), Long.valueOf(2L), Byte.valueOf((byte) 3) };
        Number[] outarray;

        outarray = ArrayUtils.removeAll(inarray, 1);

        outarray = ArrayUtils.removeAll(outarray, 1);
        assertEquals(Number.class, outarray.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllNumberArray_6_oe() {
        final Number[] inarray = { Integer.valueOf(1), Long.valueOf(2L), Byte.valueOf((byte) 3) };
        Number[] outarray;

        outarray = ArrayUtils.removeAll(inarray, 1);

        outarray = ArrayUtils.removeAll(outarray, 1);

        outarray = ArrayUtils.removeAll(outarray, 0);
        assertEquals(0, outarray.length);
    }

    @Test
    public void testRemoveAllNumberArray_7_oe() {
        final Number[] inarray = { Integer.valueOf(1), Long.valueOf(2L), Byte.valueOf((byte) 3) };
        Number[] outarray;

        outarray = ArrayUtils.removeAll(inarray, 1);

        outarray = ArrayUtils.removeAll(outarray, 1);

        outarray = ArrayUtils.removeAll(outarray, 0);
        assertEquals(Number.class, outarray.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllNumberArray_8_oe() {
        final Number[] inarray = { Integer.valueOf(1), Long.valueOf(2L), Byte.valueOf((byte) 3) };
        Number[] outarray;

        outarray = ArrayUtils.removeAll(inarray, 1);

        outarray = ArrayUtils.removeAll(outarray, 1);

        outarray = ArrayUtils.removeAll(outarray, 0);

        outarray = ArrayUtils.removeAll(inarray, 0, 1);
        assertArrayEquals(new Number[] { Byte.valueOf((byte) 3) }, outarray);
    }

    @Test
    public void testRemoveAllNumberArray_9_oe() {
        final Number[] inarray = { Integer.valueOf(1), Long.valueOf(2L), Byte.valueOf((byte) 3) };
        Number[] outarray;

        outarray = ArrayUtils.removeAll(inarray, 1);

        outarray = ArrayUtils.removeAll(outarray, 1);

        outarray = ArrayUtils.removeAll(outarray, 0);

        outarray = ArrayUtils.removeAll(inarray, 0, 1);
        assertEquals(Number.class, outarray.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllNumberArray_10_oe() {
        final Number[] inarray = { Integer.valueOf(1), Long.valueOf(2L), Byte.valueOf((byte) 3) };
        Number[] outarray;

        outarray = ArrayUtils.removeAll(inarray, 1);

        outarray = ArrayUtils.removeAll(outarray, 1);

        outarray = ArrayUtils.removeAll(outarray, 0);

        outarray = ArrayUtils.removeAll(inarray, 0, 1);

        outarray = ArrayUtils.removeAll(inarray, 0, 2);
        assertArrayEquals(new Number[] { Long.valueOf(2L) }, outarray);
    }

    @Test
    public void testRemoveAllNumberArray_11_oe() {
        final Number[] inarray = { Integer.valueOf(1), Long.valueOf(2L), Byte.valueOf((byte) 3) };
        Number[] outarray;

        outarray = ArrayUtils.removeAll(inarray, 1);

        outarray = ArrayUtils.removeAll(outarray, 1);

        outarray = ArrayUtils.removeAll(outarray, 0);

        outarray = ArrayUtils.removeAll(inarray, 0, 1);

        outarray = ArrayUtils.removeAll(inarray, 0, 2);
        assertEquals(Number.class, outarray.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllNumberArray_12_oe() {
        final Number[] inarray = { Integer.valueOf(1), Long.valueOf(2L), Byte.valueOf((byte) 3) };
        Number[] outarray;

        outarray = ArrayUtils.removeAll(inarray, 1);

        outarray = ArrayUtils.removeAll(outarray, 1);

        outarray = ArrayUtils.removeAll(outarray, 0);

        outarray = ArrayUtils.removeAll(inarray, 0, 1);

        outarray = ArrayUtils.removeAll(inarray, 0, 2);

        outarray = ArrayUtils.removeAll(inarray, 1, 2);
        assertArrayEquals(new Number[] { Integer.valueOf(1) }, outarray);
    }

    @Test
    public void testRemoveAllNumberArray_13_oe() {
        final Number[] inarray = { Integer.valueOf(1), Long.valueOf(2L), Byte.valueOf((byte) 3) };
        Number[] outarray;

        outarray = ArrayUtils.removeAll(inarray, 1);

        outarray = ArrayUtils.removeAll(outarray, 1);

        outarray = ArrayUtils.removeAll(outarray, 0);

        outarray = ArrayUtils.removeAll(inarray, 0, 1);

        outarray = ArrayUtils.removeAll(inarray, 0, 2);

        outarray = ArrayUtils.removeAll(inarray, 1, 2);
        assertEquals(Number.class, outarray.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllObjectArray_1_oe() {
        Object[] array;

        array = ArrayUtils.removeAll(new Object[] { "a" }, 0);
        assertArrayEquals(ArrayUtils.EMPTY_OBJECT_ARRAY, array);
    }

    @Test
    public void testRemoveAllObjectArray_2_oe() {
        Object[] array;

        array = ArrayUtils.removeAll(new Object[] { "a" }, 0);
        assertEquals(Object.class, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllObjectArray_3_oe() {
        Object[] array;

        array = ArrayUtils.removeAll(new Object[] { "a" }, 0);

        array = ArrayUtils.removeAll(new Object[] { "a", "b" }, 0, 1);
        assertArrayEquals(ArrayUtils.EMPTY_OBJECT_ARRAY, array);
    }

    @Test
    public void testRemoveAllObjectArray_4_oe() {
        Object[] array;

        array = ArrayUtils.removeAll(new Object[] { "a" }, 0);

        array = ArrayUtils.removeAll(new Object[] { "a", "b" }, 0, 1);
        assertEquals(Object.class, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllObjectArray_5_oe() {
        Object[] array;

        array = ArrayUtils.removeAll(new Object[] { "a" }, 0);

        array = ArrayUtils.removeAll(new Object[] { "a", "b" }, 0, 1);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c" }, 1, 2);
        assertArrayEquals(new Object[] { "a" }, array);
    }

    @Test
    public void testRemoveAllObjectArray_6_oe() {
        Object[] array;

        array = ArrayUtils.removeAll(new Object[] { "a" }, 0);

        array = ArrayUtils.removeAll(new Object[] { "a", "b" }, 0, 1);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c" }, 1, 2);
        assertEquals(Object.class, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllObjectArray_7_oe() {
        Object[] array;

        array = ArrayUtils.removeAll(new Object[] { "a" }, 0);

        array = ArrayUtils.removeAll(new Object[] { "a", "b" }, 0, 1);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 1, 2);
        assertArrayEquals(new Object[] { "a", "d" }, array);
    }

    @Test
    public void testRemoveAllObjectArray_8_oe() {
        Object[] array;

        array = ArrayUtils.removeAll(new Object[] { "a" }, 0);

        array = ArrayUtils.removeAll(new Object[] { "a", "b" }, 0, 1);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 1, 2);
        assertEquals(Object.class, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllObjectArray_9_oe() {
        Object[] array;

        array = ArrayUtils.removeAll(new Object[] { "a" }, 0);

        array = ArrayUtils.removeAll(new Object[] { "a", "b" }, 0, 1);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 3);
        assertArrayEquals(new Object[] { "b", "c" }, array);
    }

    @Test
    public void testRemoveAllObjectArray_10_oe() {
        Object[] array;

        array = ArrayUtils.removeAll(new Object[] { "a" }, 0);

        array = ArrayUtils.removeAll(new Object[] { "a", "b" }, 0, 1);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 3);
        assertEquals(Object.class, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllObjectArray_11_oe() {
        Object[] array;

        array = ArrayUtils.removeAll(new Object[] { "a" }, 0);

        array = ArrayUtils.removeAll(new Object[] { "a", "b" }, 0, 1);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 1, 3);
        assertArrayEquals(new Object[] { "c" }, array);
    }

    @Test
    public void testRemoveAllObjectArray_12_oe() {
        Object[] array;

        array = ArrayUtils.removeAll(new Object[] { "a" }, 0);

        array = ArrayUtils.removeAll(new Object[] { "a", "b" }, 0, 1);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 1, 3);
        assertEquals(Object.class, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllObjectArray_13_oe() {
        Object[] array;

        array = ArrayUtils.removeAll(new Object[] { "a" }, 0);

        array = ArrayUtils.removeAll(new Object[] { "a", "b" }, 0, 1);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 1, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d", "e" }, 0, 1, 3);
        assertArrayEquals(new Object[] { "c", "e" }, array);
    }

    @Test
    public void testRemoveAllObjectArray_14_oe() {
        Object[] array;

        array = ArrayUtils.removeAll(new Object[] { "a" }, 0);

        array = ArrayUtils.removeAll(new Object[] { "a", "b" }, 0, 1);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 1, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d", "e" }, 0, 1, 3);
        assertEquals(Object.class, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllObjectArray_15_oe() {
        Object[] array;

        array = ArrayUtils.removeAll(new Object[] { "a" }, 0);

        array = ArrayUtils.removeAll(new Object[] { "a", "b" }, 0, 1);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 1, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d", "e" }, 0, 1, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d", "e" }, 0, 2, 4);
        assertArrayEquals(new Object[] { "b", "d" }, array);
    }

    @Test
    public void testRemoveAllObjectArray_16_oe() {
        Object[] array;

        array = ArrayUtils.removeAll(new Object[] { "a" }, 0);

        array = ArrayUtils.removeAll(new Object[] { "a", "b" }, 0, 1);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 1, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d", "e" }, 0, 1, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d", "e" }, 0, 2, 4);
        assertEquals(Object.class, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllObjectArray_17_oe() {
        Object[] array;

        array = ArrayUtils.removeAll(new Object[] { "a" }, 0);

        array = ArrayUtils.removeAll(new Object[] { "a", "b" }, 0, 1);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 1, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d", "e" }, 0, 1, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d", "e" }, 0, 2, 4);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 1, 3, 0, 1, 3);
        assertArrayEquals(new Object[] { "c" }, array);
    }

    @Test
    public void testRemoveAllObjectArray_18_oe() {
        Object[] array;

        array = ArrayUtils.removeAll(new Object[] { "a" }, 0);

        array = ArrayUtils.removeAll(new Object[] { "a", "b" }, 0, 1);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 1, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d", "e" }, 0, 1, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d", "e" }, 0, 2, 4);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 1, 3, 0, 1, 3);
        assertEquals(Object.class, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllObjectArray_19_oe() {
        Object[] array;

        array = ArrayUtils.removeAll(new Object[] { "a" }, 0);

        array = ArrayUtils.removeAll(new Object[] { "a", "b" }, 0, 1);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 1, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d", "e" }, 0, 1, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d", "e" }, 0, 2, 4);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 1, 3, 0, 1, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 2, 1, 0, 3);
        assertArrayEquals(ArrayUtils.EMPTY_OBJECT_ARRAY, array);
    }

    @Test
    public void testRemoveAllObjectArray_20_oe() {
        Object[] array;

        array = ArrayUtils.removeAll(new Object[] { "a" }, 0);

        array = ArrayUtils.removeAll(new Object[] { "a", "b" }, 0, 1);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 1, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d", "e" }, 0, 1, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d", "e" }, 0, 2, 4);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 1, 3, 0, 1, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 2, 1, 0, 3);
        assertEquals(Object.class, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllObjectArray_21_oe() {
        Object[] array;

        array = ArrayUtils.removeAll(new Object[] { "a" }, 0);

        array = ArrayUtils.removeAll(new Object[] { "a", "b" }, 0, 1);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 1, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d", "e" }, 0, 1, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d", "e" }, 0, 2, 4);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 1, 3, 0, 1, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 2, 1, 0, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 2, 0, 1, 3, 0, 2, 1, 3);
        assertArrayEquals(ArrayUtils.EMPTY_OBJECT_ARRAY, array);
    }

    @Test
    public void testRemoveAllObjectArray_22_oe() {
        Object[] array;

        array = ArrayUtils.removeAll(new Object[] { "a" }, 0);

        array = ArrayUtils.removeAll(new Object[] { "a", "b" }, 0, 1);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 1, 2);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 1, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d", "e" }, 0, 1, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d", "e" }, 0, 2, 4);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 1, 3, 0, 1, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 2, 1, 0, 3);

        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 2, 0, 1, 3, 0, 2, 1, 3);
        assertEquals(Object.class, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllObjectArrayNegativeIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new Object[] { "a", "b" }, -1);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllObjectArrayOutOfBoundsIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new Object[] { "a", "b" }, 2);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllObjectArrayRemoveNone_1_oe() {
        final Object[] array1 = new Object[] { "foo", "bar", "baz" };
        final Object[] array2 = ArrayUtils.removeAll(array1);
        assertNotSame(array1, array2);
    }

    @Test
    public void testRemoveAllObjectArrayRemoveNone_2_oe() {
        final Object[] array1 = new Object[] { "foo", "bar", "baz" };
        final Object[] array2 = ArrayUtils.removeAll(array1);
        assertArrayEquals(array1, array2);
    }

    @Test
    public void testRemoveAllObjectArrayRemoveNone_3_oe() {
        final Object[] array1 = new Object[] { "foo", "bar", "baz" };
        final Object[] array2 = ArrayUtils.removeAll(array1);
        assertEquals(Object.class, array2.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllShortArray_1_oe() {
        short[] array;

        array = ArrayUtils.removeAll(new short[] { 1 }, 0);
        assertArrayEquals(ArrayUtils.EMPTY_SHORT_ARRAY, array);
    }

    @Test
    public void testRemoveAllShortArray_2_oe() {
        short[] array;

        array = ArrayUtils.removeAll(new short[] { 1 }, 0);
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllShortArray_3_oe() {
        short[] array;

        array = ArrayUtils.removeAll(new short[] { 1 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0);
        assertArrayEquals(new short[]{2}, array);
    }

    @Test
    public void testRemoveAllShortArray_4_oe() {
        short[] array;

        array = ArrayUtils.removeAll(new short[] { 1 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0);
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllShortArray_5_oe() {
        short[] array;

        array = ArrayUtils.removeAll(new short[] { 1 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 1);
        assertArrayEquals(new short[]{1}, array);
    }

    @Test
    public void testRemoveAllShortArray_6_oe() {
        short[] array;

        array = ArrayUtils.removeAll(new short[] { 1 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 1);
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllShortArray_7_oe() {
        short[] array;

        array = ArrayUtils.removeAll(new short[] { 1 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 1 }, 1);
        assertArrayEquals(new short[]{1, 1}, array);
    }

    @Test
    public void testRemoveAllShortArray_8_oe() {
        short[] array;

        array = ArrayUtils.removeAll(new short[] { 1 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 1 }, 1);
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllShortArray_9_oe() {
        short[] array;

        array = ArrayUtils.removeAll(new short[] { 1 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0, 1);
        assertArrayEquals(ArrayUtils.EMPTY_SHORT_ARRAY, array);
    }

    @Test
    public void testRemoveAllShortArray_10_oe() {
        short[] array;

        array = ArrayUtils.removeAll(new short[] { 1 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0, 1);
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllShortArray_11_oe() {
        short[] array;

        array = ArrayUtils.removeAll(new short[] { 1 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 1);
        assertArrayEquals(new short[]{3}, array);
    }

    @Test
    public void testRemoveAllShortArray_12_oe() {
        short[] array;

        array = ArrayUtils.removeAll(new short[] { 1 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 1);
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllShortArray_13_oe() {
        short[] array;

        array = ArrayUtils.removeAll(new short[] { 1 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 1, 2);
        assertArrayEquals(new short[]{1}, array);
    }

    @Test
    public void testRemoveAllShortArray_14_oe() {
        short[] array;

        array = ArrayUtils.removeAll(new short[] { 1 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 1, 2);
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllShortArray_15_oe() {
        short[] array;

        array = ArrayUtils.removeAll(new short[] { 1 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 2);
        assertArrayEquals(new short[]{2}, array);
    }

    @Test
    public void testRemoveAllShortArray_16_oe() {
        short[] array;

        array = ArrayUtils.removeAll(new short[] { 1 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 2);
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllShortArray_17_oe() {
        short[] array;

        array = ArrayUtils.removeAll(new short[] { 1 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5 }, 1, 3);
        assertArrayEquals(new short[]{1, 3, 5}, array);
    }

    @Test
    public void testRemoveAllShortArray_18_oe() {
        short[] array;

        array = ArrayUtils.removeAll(new short[] { 1 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5 }, 1, 3);
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllShortArray_19_oe() {
        short[] array;

        array = ArrayUtils.removeAll(new short[] { 1 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5 }, 0, 2, 4);
        assertArrayEquals(new short[]{2, 4}, array);
    }

    @Test
    public void testRemoveAllShortArray_20_oe() {
        short[] array;

        array = ArrayUtils.removeAll(new short[] { 1 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5 }, 0, 2, 4);
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllShortArray_21_oe() {
        short[] array;

        array = ArrayUtils.removeAll(new short[] { 1 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5 }, 0, 2, 4);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);
        assertArrayEquals(new short[]{1, 3, 5, 7}, array);
    }

    @Test
    public void testRemoveAllShortArray_22_oe() {
        short[] array;

        array = ArrayUtils.removeAll(new short[] { 1 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5 }, 0, 2, 4);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllShortArray_23_oe() {
        short[] array;

        array = ArrayUtils.removeAll(new short[] { 1 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5 }, 0, 2, 4);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5, 6, 7 }, 0, 2, 4, 6);
        assertArrayEquals(new short[]{2, 4, 6}, array);
    }

    @Test
    public void testRemoveAllShortArray_24_oe() {
        short[] array;

        array = ArrayUtils.removeAll(new short[] { 1 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 1);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 1, 2);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 2);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5 }, 1, 3);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5 }, 0, 2, 4);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);

        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5, 6, 7 }, 0, 2, 4, 6);
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllShortArrayNegativeIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new short[] { 1, 2 }, -1, 0);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllShortArrayOutOfBoundsIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new short[] { 1, 2 }, 2, 0);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllShortArrayRemoveNone_1_oe() {
        final short[] array1 = new short[] { 1, 2 };
        final short[] array2 = ArrayUtils.removeAll(array1);
        assertNotSame(array1, array2);
    }

    @Test
    public void testRemoveAllShortArrayRemoveNone_2_oe() {
        final short[] array1 = new short[] { 1, 2 };
        final short[] array2 = ArrayUtils.removeAll(array1);
        assertArrayEquals(array1, array2);
    }

    @Test
    public void testRemoveAllShortArrayRemoveNone_3_oe() {
        final short[] array1 = new short[] { 1, 2 };
        final short[] array2 = ArrayUtils.removeAll(array1);
        assertEquals(short.class, array2.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementBooleanArray_1_oe() {
        boolean[] array;

        array = ArrayUtils.removeElements((boolean[]) null, true);
        assertNull(array);
    }

    @Test
    public void testRemoveElementBooleanArray_2_oe() {
        boolean[] array;

        array = ArrayUtils.removeElements((boolean[]) null, true);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);
        assertArrayEquals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, array);
    }

    @Test
    public void testRemoveElementBooleanArray_3_oe() {
        boolean[] array;

        array = ArrayUtils.removeElements((boolean[]) null, true);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementBooleanArray_4_oe() {
        boolean[] array;

        array = ArrayUtils.removeElements((boolean[]) null, true);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);

        array = ArrayUtils.removeElements(new boolean[] { true }, true);
        assertArrayEquals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, array);
    }

    @Test
    public void testRemoveElementBooleanArray_5_oe() {
        boolean[] array;

        array = ArrayUtils.removeElements((boolean[]) null, true);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);

        array = ArrayUtils.removeElements(new boolean[] { true }, true);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementBooleanArray_6_oe() {
        boolean[] array;

        array = ArrayUtils.removeElements((boolean[]) null, true);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);

        array = ArrayUtils.removeElements(new boolean[] { true }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true);
        assertArrayEquals(new boolean[]{false}, array);
    }

    @Test
    public void testRemoveElementBooleanArray_7_oe() {
        boolean[] array;

        array = ArrayUtils.removeElements((boolean[]) null, true);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);

        array = ArrayUtils.removeElements(new boolean[] { true }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementBooleanArray_8_oe() {
        boolean[] array;

        array = ArrayUtils.removeElements((boolean[]) null, true);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);

        array = ArrayUtils.removeElements(new boolean[] { true }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true);
        assertArrayEquals(new boolean[]{false, true}, array);
    }

    @Test
    public void testRemoveElementBooleanArray_9_oe() {
        boolean[] array;

        array = ArrayUtils.removeElements((boolean[]) null, true);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);

        array = ArrayUtils.removeElements(new boolean[] { true }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementBooleanArray_10_oe() {
        boolean[] array;

        array = ArrayUtils.removeElements((boolean[]) null, true);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);

        array = ArrayUtils.removeElements(new boolean[] { true }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true);

        array = ArrayUtils.removeElements((boolean[]) null, true, false);
        assertNull(array);
    }

    @Test
    public void testRemoveElementBooleanArray_11_oe() {
        boolean[] array;

        array = ArrayUtils.removeElements((boolean[]) null, true);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);

        array = ArrayUtils.removeElements(new boolean[] { true }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true);

        array = ArrayUtils.removeElements((boolean[]) null, true, false);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true, false);
        assertArrayEquals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, array);
    }

    @Test
    public void testRemoveElementBooleanArray_12_oe() {
        boolean[] array;

        array = ArrayUtils.removeElements((boolean[]) null, true);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);

        array = ArrayUtils.removeElements(new boolean[] { true }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true);

        array = ArrayUtils.removeElements((boolean[]) null, true, false);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true, false);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementBooleanArray_13_oe() {
        boolean[] array;

        array = ArrayUtils.removeElements((boolean[]) null, true);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);

        array = ArrayUtils.removeElements(new boolean[] { true }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true);

        array = ArrayUtils.removeElements((boolean[]) null, true, false);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true }, true, false);
        assertArrayEquals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, array);
    }

    @Test
    public void testRemoveElementBooleanArray_14_oe() {
        boolean[] array;

        array = ArrayUtils.removeElements((boolean[]) null, true);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);

        array = ArrayUtils.removeElements(new boolean[] { true }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true);

        array = ArrayUtils.removeElements((boolean[]) null, true, false);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true }, true, false);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementBooleanArray_15_oe() {
        boolean[] array;

        array = ArrayUtils.removeElements((boolean[]) null, true);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);

        array = ArrayUtils.removeElements(new boolean[] { true }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true);

        array = ArrayUtils.removeElements((boolean[]) null, true, false);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true }, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true, false);
        assertArrayEquals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, array);
    }

    @Test
    public void testRemoveElementBooleanArray_16_oe() {
        boolean[] array;

        array = ArrayUtils.removeElements((boolean[]) null, true);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);

        array = ArrayUtils.removeElements(new boolean[] { true }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true);

        array = ArrayUtils.removeElements((boolean[]) null, true, false);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true }, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true, false);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementBooleanArray_17_oe() {
        boolean[] array;

        array = ArrayUtils.removeElements((boolean[]) null, true);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);

        array = ArrayUtils.removeElements(new boolean[] { true }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true);

        array = ArrayUtils.removeElements((boolean[]) null, true, false);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true }, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true, true);
        assertArrayEquals(new boolean[]{false}, array);
    }

    @Test
    public void testRemoveElementBooleanArray_18_oe() {
        boolean[] array;

        array = ArrayUtils.removeElements((boolean[]) null, true);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);

        array = ArrayUtils.removeElements(new boolean[] { true }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true);

        array = ArrayUtils.removeElements((boolean[]) null, true, false);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true }, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true, true);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementBooleanArray_19_oe() {
        boolean[] array;

        array = ArrayUtils.removeElements((boolean[]) null, true);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);

        array = ArrayUtils.removeElements(new boolean[] { true }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true);

        array = ArrayUtils.removeElements((boolean[]) null, true, false);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true }, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true, false);
        assertArrayEquals(new boolean[]{true}, array);
    }

    @Test
    public void testRemoveElementBooleanArray_20_oe() {
        boolean[] array;

        array = ArrayUtils.removeElements((boolean[]) null, true);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);

        array = ArrayUtils.removeElements(new boolean[] { true }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true);

        array = ArrayUtils.removeElements((boolean[]) null, true, false);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true }, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true, false);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementBooleanArray_21_oe() {
        boolean[] array;

        array = ArrayUtils.removeElements((boolean[]) null, true);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);

        array = ArrayUtils.removeElements(new boolean[] { true }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true);

        array = ArrayUtils.removeElements((boolean[]) null, true, false);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true }, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true, true);
        assertArrayEquals(new boolean[]{false}, array);
    }

    @Test
    public void testRemoveElementBooleanArray_22_oe() {
        boolean[] array;

        array = ArrayUtils.removeElements((boolean[]) null, true);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);

        array = ArrayUtils.removeElements(new boolean[] { true }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true);

        array = ArrayUtils.removeElements((boolean[]) null, true, false);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true }, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true, true);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementBooleanArray_23_oe() {
        boolean[] array;

        array = ArrayUtils.removeElements((boolean[]) null, true);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);

        array = ArrayUtils.removeElements(new boolean[] { true }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true);

        array = ArrayUtils.removeElements((boolean[]) null, true, false);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true }, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true, true, true, true);
        assertArrayEquals(new boolean[]{false}, array);
    }

    @Test
    public void testRemoveElementBooleanArray_24_oe() {
        boolean[] array;

        array = ArrayUtils.removeElements((boolean[]) null, true);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);

        array = ArrayUtils.removeElements(new boolean[] { true }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true);

        array = ArrayUtils.removeElements((boolean[]) null, true, false);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true }, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true, false }, true, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true, false);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true, true);

        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true, true, true, true);
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementByteArray_1_oe() {
        byte[] array;

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1);
        assertNull(array);
    }

    @Test
    public void testRemoveElementByteArray_2_oe() {
        byte[] array;

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);
        assertArrayEquals(ArrayUtils.EMPTY_BYTE_ARRAY, array);
    }

    @Test
    public void testRemoveElementByteArray_3_oe() {
        byte[] array;

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementByteArray_4_oe() {
        byte[] array;

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1);
        assertArrayEquals(ArrayUtils.EMPTY_BYTE_ARRAY, array);
    }

    @Test
    public void testRemoveElementByteArray_5_oe() {
        byte[] array;

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementByteArray_6_oe() {
        byte[] array;

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1);
        assertArrayEquals(new byte[]{2}, array);
    }

    @Test
    public void testRemoveElementByteArray_7_oe() {
        byte[] array;

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementByteArray_8_oe() {
        byte[] array;

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1);
        assertArrayEquals(new byte[]{2, 1}, array);
    }

    @Test
    public void testRemoveElementByteArray_9_oe() {
        byte[] array;

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementByteArray_10_oe() {
        byte[] array;

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1);

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1, (byte) 2);
        assertNull(array);
    }

    @Test
    public void testRemoveElementByteArray_11_oe() {
        byte[] array;

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1);

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1, (byte) 2);
        assertArrayEquals(ArrayUtils.EMPTY_BYTE_ARRAY, array);
    }

    @Test
    public void testRemoveElementByteArray_12_oe() {
        byte[] array;

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1);

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1, (byte) 2);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementByteArray_13_oe() {
        byte[] array;

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1);

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1, (byte) 2);
        assertArrayEquals(ArrayUtils.EMPTY_BYTE_ARRAY, array);
    }

    @Test
    public void testRemoveElementByteArray_14_oe() {
        byte[] array;

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1);

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1, (byte) 2);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementByteArray_15_oe() {
        byte[] array;

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1);

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1, (byte) 2);
        assertArrayEquals(ArrayUtils.EMPTY_BYTE_ARRAY, array);
    }

    @Test
    public void testRemoveElementByteArray_16_oe() {
        byte[] array;

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1);

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1, (byte) 2);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementByteArray_17_oe() {
        byte[] array;

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1);

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1, (byte) 1);
        assertArrayEquals(new byte[]{2}, array);
    }

    @Test
    public void testRemoveElementByteArray_18_oe() {
        byte[] array;

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1);

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1, (byte) 1);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementByteArray_19_oe() {
        byte[] array;

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1);

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1, (byte) 2);
        assertArrayEquals(new byte[]{1}, array);
    }

    @Test
    public void testRemoveElementByteArray_20_oe() {
        byte[] array;

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1);

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1, (byte) 2);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementByteArray_21_oe() {
        byte[] array;

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1);

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1, (byte) 1);
        assertArrayEquals(new byte[]{2}, array);
    }

    @Test
    public void testRemoveElementByteArray_22_oe() {
        byte[] array;

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1);

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1, (byte) 1);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementByteArray_23_oe() {
        byte[] array;

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1);

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1, (byte) 1, (byte) 1, (byte) 1);
        assertArrayEquals(new byte[]{2}, array);
    }

    @Test
    public void testRemoveElementByteArray_24_oe() {
        byte[] array;

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1);

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1, (byte) 2);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1, (byte) 1);

        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1, (byte) 1, (byte) 1, (byte) 1);
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementCharArray_1_oe() {
        char[] array;

        array = ArrayUtils.removeElements((char[]) null, 'a');
        assertNull(array);
    }

    @Test
    public void testRemoveElementCharArray_2_oe() {
        char[] array;

        array = ArrayUtils.removeElements((char[]) null, 'a');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');
        assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, array);
    }

    @Test
    public void testRemoveElementCharArray_3_oe() {
        char[] array;

        array = ArrayUtils.removeElements((char[]) null, 'a');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementCharArray_4_oe() {
        char[] array;

        array = ArrayUtils.removeElements((char[]) null, 'a');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a');
        assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, array);
    }

    @Test
    public void testRemoveElementCharArray_5_oe() {
        char[] array;

        array = ArrayUtils.removeElements((char[]) null, 'a');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a');
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementCharArray_6_oe() {
        char[] array;

        array = ArrayUtils.removeElements((char[]) null, 'a');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a');
        assertArrayEquals(new char[]{'b'}, array);
    }

    @Test
    public void testRemoveElementCharArray_7_oe() {
        char[] array;

        array = ArrayUtils.removeElements((char[]) null, 'a');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a');
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementCharArray_8_oe() {
        char[] array;

        array = ArrayUtils.removeElements((char[]) null, 'a');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a');
        assertArrayEquals(new char[]{'b', 'a'}, array);
    }

    @Test
    public void testRemoveElementCharArray_9_oe() {
        char[] array;

        array = ArrayUtils.removeElements((char[]) null, 'a');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a');
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementCharArray_10_oe() {
        char[] array;

        array = ArrayUtils.removeElements((char[]) null, 'a');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a');

        array = ArrayUtils.removeElements((char[]) null, 'a', 'b');
        assertNull(array);
    }

    @Test
    public void testRemoveElementCharArray_11_oe() {
        char[] array;

        array = ArrayUtils.removeElements((char[]) null, 'a');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a');

        array = ArrayUtils.removeElements((char[]) null, 'a', 'b');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a', 'b');
        assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, array);
    }

    @Test
    public void testRemoveElementCharArray_12_oe() {
        char[] array;

        array = ArrayUtils.removeElements((char[]) null, 'a');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a');

        array = ArrayUtils.removeElements((char[]) null, 'a', 'b');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a', 'b');
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementCharArray_13_oe() {
        char[] array;

        array = ArrayUtils.removeElements((char[]) null, 'a');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a');

        array = ArrayUtils.removeElements((char[]) null, 'a', 'b');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a', 'b');
        assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, array);
    }

    @Test
    public void testRemoveElementCharArray_14_oe() {
        char[] array;

        array = ArrayUtils.removeElements((char[]) null, 'a');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a');

        array = ArrayUtils.removeElements((char[]) null, 'a', 'b');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a', 'b');
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementCharArray_15_oe() {
        char[] array;

        array = ArrayUtils.removeElements((char[]) null, 'a');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a');

        array = ArrayUtils.removeElements((char[]) null, 'a', 'b');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a', 'b');
        assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, array);
    }

    @Test
    public void testRemoveElementCharArray_16_oe() {
        char[] array;

        array = ArrayUtils.removeElements((char[]) null, 'a');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a');

        array = ArrayUtils.removeElements((char[]) null, 'a', 'b');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a', 'b');
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementCharArray_17_oe() {
        char[] array;

        array = ArrayUtils.removeElements((char[]) null, 'a');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a');

        array = ArrayUtils.removeElements((char[]) null, 'a', 'b');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a', 'a');
        assertArrayEquals(new char[]{'b'}, array);
    }

    @Test
    public void testRemoveElementCharArray_18_oe() {
        char[] array;

        array = ArrayUtils.removeElements((char[]) null, 'a');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a');

        array = ArrayUtils.removeElements((char[]) null, 'a', 'b');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a', 'a');
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementCharArray_19_oe() {
        char[] array;

        array = ArrayUtils.removeElements((char[]) null, 'a');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a');

        array = ArrayUtils.removeElements((char[]) null, 'a', 'b');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a', 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a', 'b');
        assertArrayEquals(new char[]{'a'}, array);
    }

    @Test
    public void testRemoveElementCharArray_20_oe() {
        char[] array;

        array = ArrayUtils.removeElements((char[]) null, 'a');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a');

        array = ArrayUtils.removeElements((char[]) null, 'a', 'b');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a', 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a', 'b');
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementCharArray_21_oe() {
        char[] array;

        array = ArrayUtils.removeElements((char[]) null, 'a');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a');

        array = ArrayUtils.removeElements((char[]) null, 'a', 'b');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a', 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a', 'a');
        assertArrayEquals(new char[]{'b'}, array);
    }

    @Test
    public void testRemoveElementCharArray_22_oe() {
        char[] array;

        array = ArrayUtils.removeElements((char[]) null, 'a');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a');

        array = ArrayUtils.removeElements((char[]) null, 'a', 'b');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a', 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a', 'a');
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementCharArray_23_oe() {
        char[] array;

        array = ArrayUtils.removeElements((char[]) null, 'a');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a');

        array = ArrayUtils.removeElements((char[]) null, 'a', 'b');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a', 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a', 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a', 'a', 'a', 'a');
        assertArrayEquals(new char[]{'b'}, array);
    }

    @Test
    public void testRemoveElementCharArray_24_oe() {
        char[] array;

        array = ArrayUtils.removeElements((char[]) null, 'a');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a');

        array = ArrayUtils.removeElements((char[]) null, 'a', 'b');

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a', 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a', 'b');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a', 'a');

        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a', 'a', 'a', 'a');
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_1_oe() {
        double[] array;

        array = ArrayUtils.removeElements((double[]) null, (double) 1);
        assertNull(array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_2_oe() {
        double[] array;

        array = ArrayUtils.removeElements((double[]) null, (double) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);
        assertArrayEquals(ArrayUtils.EMPTY_DOUBLE_ARRAY, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_3_oe() {
        double[] array;

        array = ArrayUtils.removeElements((double[]) null, (double) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_4_oe() {
        double[] array;

        array = ArrayUtils.removeElements((double[]) null, (double) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1);
        assertArrayEquals(ArrayUtils.EMPTY_DOUBLE_ARRAY, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_5_oe() {
        double[] array;

        array = ArrayUtils.removeElements((double[]) null, (double) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1);
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_6_oe() {
        double[] array;

        array = ArrayUtils.removeElements((double[]) null, (double) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1);
        assertArrayEquals(new double[]{2}, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_7_oe() {
        double[] array;

        array = ArrayUtils.removeElements((double[]) null, (double) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1);
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_8_oe() {
        double[] array;

        array = ArrayUtils.removeElements((double[]) null, (double) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1);
        assertArrayEquals(new double[]{2, 1}, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_9_oe() {
        double[] array;

        array = ArrayUtils.removeElements((double[]) null, (double) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1);
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_10_oe() {
        double[] array;

        array = ArrayUtils.removeElements((double[]) null, (double) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1);

        array = ArrayUtils.removeElements((double[]) null, (double) 1, (double) 2);
        assertNull(array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_11_oe() {
        double[] array;

        array = ArrayUtils.removeElements((double[]) null, (double) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1);

        array = ArrayUtils.removeElements((double[]) null, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1, (double) 2);
        assertArrayEquals(ArrayUtils.EMPTY_DOUBLE_ARRAY, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_12_oe() {
        double[] array;

        array = ArrayUtils.removeElements((double[]) null, (double) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1);

        array = ArrayUtils.removeElements((double[]) null, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1, (double) 2);
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_13_oe() {
        double[] array;

        array = ArrayUtils.removeElements((double[]) null, (double) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1);

        array = ArrayUtils.removeElements((double[]) null, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1, (double) 2);
        assertArrayEquals(ArrayUtils.EMPTY_DOUBLE_ARRAY, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_14_oe() {
        double[] array;

        array = ArrayUtils.removeElements((double[]) null, (double) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1);

        array = ArrayUtils.removeElements((double[]) null, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1, (double) 2);
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_15_oe() {
        double[] array;

        array = ArrayUtils.removeElements((double[]) null, (double) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1);

        array = ArrayUtils.removeElements((double[]) null, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1, (double) 2);
        assertArrayEquals(ArrayUtils.EMPTY_DOUBLE_ARRAY, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_16_oe() {
        double[] array;

        array = ArrayUtils.removeElements((double[]) null, (double) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1);

        array = ArrayUtils.removeElements((double[]) null, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1, (double) 2);
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_17_oe() {
        double[] array;

        array = ArrayUtils.removeElements((double[]) null, (double) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1);

        array = ArrayUtils.removeElements((double[]) null, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1, (double) 1);
        assertArrayEquals(new double[]{2}, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_18_oe() {
        double[] array;

        array = ArrayUtils.removeElements((double[]) null, (double) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1);

        array = ArrayUtils.removeElements((double[]) null, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1, (double) 1);
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_19_oe() {
        double[] array;

        array = ArrayUtils.removeElements((double[]) null, (double) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1);

        array = ArrayUtils.removeElements((double[]) null, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1, (double) 2);
        assertArrayEquals(new double[]{1}, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_20_oe() {
        double[] array;

        array = ArrayUtils.removeElements((double[]) null, (double) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1);

        array = ArrayUtils.removeElements((double[]) null, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1, (double) 2);
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_21_oe() {
        double[] array;

        array = ArrayUtils.removeElements((double[]) null, (double) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1);

        array = ArrayUtils.removeElements((double[]) null, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1, (double) 1);
        assertArrayEquals(new double[]{2}, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_22_oe() {
        double[] array;

        array = ArrayUtils.removeElements((double[]) null, (double) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1);

        array = ArrayUtils.removeElements((double[]) null, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1, (double) 1);
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_23_oe() {
        double[] array;

        array = ArrayUtils.removeElements((double[]) null, (double) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1);

        array = ArrayUtils.removeElements((double[]) null, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1, (double) 1, (double) 1, (double) 1);
        assertArrayEquals(new double[]{2}, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_24_oe() {
        double[] array;

        array = ArrayUtils.removeElements((double[]) null, (double) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1);

        array = ArrayUtils.removeElements((double[]) null, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1, (double) 2);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1, (double) 1);

        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1, (double) 1, (double) 1, (double) 1);
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_1_oe() {
        float[] array;

        array = ArrayUtils.removeElements((float[]) null, (float) 1);
        assertNull(array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_2_oe() {
        float[] array;

        array = ArrayUtils.removeElements((float[]) null, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);
        assertArrayEquals(ArrayUtils.EMPTY_FLOAT_ARRAY, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_3_oe() {
        float[] array;

        array = ArrayUtils.removeElements((float[]) null, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_4_oe() {
        float[] array;

        array = ArrayUtils.removeElements((float[]) null, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1);
        assertArrayEquals(ArrayUtils.EMPTY_FLOAT_ARRAY, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_5_oe() {
        float[] array;

        array = ArrayUtils.removeElements((float[]) null, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1);
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_6_oe() {
        float[] array;

        array = ArrayUtils.removeElements((float[]) null, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1);
        assertArrayEquals(new float[]{2}, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_7_oe() {
        float[] array;

        array = ArrayUtils.removeElements((float[]) null, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1);
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_8_oe() {
        float[] array;

        array = ArrayUtils.removeElements((float[]) null, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1);
        assertArrayEquals(new float[]{2, 1}, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_9_oe() {
        float[] array;

        array = ArrayUtils.removeElements((float[]) null, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1);
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_10_oe() {
        float[] array;

        array = ArrayUtils.removeElements((float[]) null, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1);

        array = ArrayUtils.removeElements((float[]) null, (float) 1, (float) 1);
        assertNull(array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_11_oe() {
        float[] array;

        array = ArrayUtils.removeElements((float[]) null, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1);

        array = ArrayUtils.removeElements((float[]) null, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1, (float) 1);
        assertArrayEquals(ArrayUtils.EMPTY_FLOAT_ARRAY, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_12_oe() {
        float[] array;

        array = ArrayUtils.removeElements((float[]) null, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1);

        array = ArrayUtils.removeElements((float[]) null, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1, (float) 1);
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_13_oe() {
        float[] array;

        array = ArrayUtils.removeElements((float[]) null, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1);

        array = ArrayUtils.removeElements((float[]) null, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1, (float) 1);
        assertArrayEquals(ArrayUtils.EMPTY_FLOAT_ARRAY, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_14_oe() {
        float[] array;

        array = ArrayUtils.removeElements((float[]) null, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1);

        array = ArrayUtils.removeElements((float[]) null, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1, (float) 1);
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_15_oe() {
        float[] array;

        array = ArrayUtils.removeElements((float[]) null, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1);

        array = ArrayUtils.removeElements((float[]) null, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1, (float) 2);
        assertArrayEquals(ArrayUtils.EMPTY_FLOAT_ARRAY, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_16_oe() {
        float[] array;

        array = ArrayUtils.removeElements((float[]) null, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1);

        array = ArrayUtils.removeElements((float[]) null, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1, (float) 2);
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_17_oe() {
        float[] array;

        array = ArrayUtils.removeElements((float[]) null, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1);

        array = ArrayUtils.removeElements((float[]) null, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1, (float) 2);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1, (float) 1);
        assertArrayEquals(new float[]{2}, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_18_oe() {
        float[] array;

        array = ArrayUtils.removeElements((float[]) null, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1);

        array = ArrayUtils.removeElements((float[]) null, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1, (float) 2);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1, (float) 1);
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_19_oe() {
        float[] array;

        array = ArrayUtils.removeElements((float[]) null, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1);

        array = ArrayUtils.removeElements((float[]) null, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1, (float) 2);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1, (float) 1);
        assertArrayEquals(new float[]{2}, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_20_oe() {
        float[] array;

        array = ArrayUtils.removeElements((float[]) null, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1);

        array = ArrayUtils.removeElements((float[]) null, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1, (float) 2);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1, (float) 1);
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_21_oe() {
        float[] array;

        array = ArrayUtils.removeElements((float[]) null, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1);

        array = ArrayUtils.removeElements((float[]) null, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1, (float) 2);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1, (float) 2);
        assertArrayEquals(new float[]{1}, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_22_oe() {
        float[] array;

        array = ArrayUtils.removeElements((float[]) null, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1);

        array = ArrayUtils.removeElements((float[]) null, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1, (float) 2);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1, (float) 2);
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_23_oe() {
        float[] array;

        array = ArrayUtils.removeElements((float[]) null, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1);

        array = ArrayUtils.removeElements((float[]) null, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1, (float) 2);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1, (float) 2);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1, (float) 1, (float) 1, (float) 1);
        assertArrayEquals(new float[]{2}, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_24_oe() {
        float[] array;

        array = ArrayUtils.removeElements((float[]) null, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1);

        array = ArrayUtils.removeElements((float[]) null, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1, (float) 2);

        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1, (float) 1);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1, (float) 2);

        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1, (float) 1, (float) 1, (float) 1);
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementIntArray_1_oe() {
        int[] array;

        array = ArrayUtils.removeElements((int[]) null, 1);
        assertNull(array);
    }

    @Test
    public void testRemoveElementIntArray_2_oe() {
        int[] array;

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1);
        assertArrayEquals(ArrayUtils.EMPTY_INT_ARRAY, array);
    }

    @Test
    public void testRemoveElementIntArray_3_oe() {
        int[] array;

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementIntArray_4_oe() {
        int[] array;

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1);
        assertArrayEquals(ArrayUtils.EMPTY_INT_ARRAY, array);
    }

    @Test
    public void testRemoveElementIntArray_5_oe() {
        int[] array;

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementIntArray_6_oe() {
        int[] array;

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1);
        assertArrayEquals(new int[]{2}, array);
    }

    @Test
    public void testRemoveElementIntArray_7_oe() {
        int[] array;

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementIntArray_8_oe() {
        int[] array;

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1);
        assertArrayEquals(new int[]{2, 1}, array);
    }

    @Test
    public void testRemoveElementIntArray_9_oe() {
        int[] array;

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementIntArray_10_oe() {
        int[] array;

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeElements((int[]) null, 1);
        assertNull(array);
    }

    @Test
    public void testRemoveElementIntArray_11_oe() {
        int[] array;

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1, 1);
        assertArrayEquals(ArrayUtils.EMPTY_INT_ARRAY, array);
    }

    @Test
    public void testRemoveElementIntArray_12_oe() {
        int[] array;

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1, 1);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementIntArray_13_oe() {
        int[] array;

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1, 1);
        assertArrayEquals(ArrayUtils.EMPTY_INT_ARRAY, array);
    }

    @Test
    public void testRemoveElementIntArray_14_oe() {
        int[] array;

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1, 1);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementIntArray_15_oe() {
        int[] array;

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1, 2);
        assertArrayEquals(ArrayUtils.EMPTY_INT_ARRAY, array);
    }

    @Test
    public void testRemoveElementIntArray_16_oe() {
        int[] array;

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1, 2);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementIntArray_17_oe() {
        int[] array;

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1, 2);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1, 1);
        assertArrayEquals(new int[]{2}, array);
    }

    @Test
    public void testRemoveElementIntArray_18_oe() {
        int[] array;

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1, 2);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1, 1);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementIntArray_19_oe() {
        int[] array;

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1, 2);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1, 2);
        assertArrayEquals(new int[]{1}, array);
    }

    @Test
    public void testRemoveElementIntArray_20_oe() {
        int[] array;

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1, 2);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1, 2);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementIntArray_21_oe() {
        int[] array;

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1, 2);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1, 2);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1, 1);
        assertArrayEquals(new int[]{2}, array);
    }

    @Test
    public void testRemoveElementIntArray_22_oe() {
        int[] array;

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1, 2);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1, 2);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1, 1);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementIntArray_23_oe() {
        int[] array;

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1, 2);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1, 2);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1, 1, 1, 1);
        assertArrayEquals(new int[]{2}, array);
    }

    @Test
    public void testRemoveElementIntArray_24_oe() {
        int[] array;

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1);

        array = ArrayUtils.removeElements((int[]) null, 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1 }, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1, 2);

        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1, 2);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1, 1);

        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1, 1, 1, 1);
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_1_oe() {
        long[] array;

        array = ArrayUtils.removeElements((long[]) null, 1L);
        assertNull(array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_2_oe() {
        long[] array;

        array = ArrayUtils.removeElements((long[]) null, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L);
        assertArrayEquals(ArrayUtils.EMPTY_LONG_ARRAY, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_3_oe() {
        long[] array;

        array = ArrayUtils.removeElements((long[]) null, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L);
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_4_oe() {
        long[] array;

        array = ArrayUtils.removeElements((long[]) null, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L);
        assertArrayEquals(ArrayUtils.EMPTY_LONG_ARRAY, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_5_oe() {
        long[] array;

        array = ArrayUtils.removeElements((long[]) null, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L);
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_6_oe() {
        long[] array;

        array = ArrayUtils.removeElements((long[]) null, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L);
        assertArrayEquals(new long[]{2}, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_7_oe() {
        long[] array;

        array = ArrayUtils.removeElements((long[]) null, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L);
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_8_oe() {
        long[] array;

        array = ArrayUtils.removeElements((long[]) null, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L);
        assertArrayEquals(new long[]{2, 1}, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_9_oe() {
        long[] array;

        array = ArrayUtils.removeElements((long[]) null, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L);
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_10_oe() {
        long[] array;

        array = ArrayUtils.removeElements((long[]) null, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L);

        array = ArrayUtils.removeElements((long[]) null, 1L, 1L);
        assertNull(array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_11_oe() {
        long[] array;

        array = ArrayUtils.removeElements((long[]) null, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L);

        array = ArrayUtils.removeElements((long[]) null, 1L, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L, 1L);
        assertArrayEquals(ArrayUtils.EMPTY_LONG_ARRAY, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_12_oe() {
        long[] array;

        array = ArrayUtils.removeElements((long[]) null, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L);

        array = ArrayUtils.removeElements((long[]) null, 1L, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L, 1L);
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_13_oe() {
        long[] array;

        array = ArrayUtils.removeElements((long[]) null, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L);

        array = ArrayUtils.removeElements((long[]) null, 1L, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L, 1L);
        assertArrayEquals(ArrayUtils.EMPTY_LONG_ARRAY, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_14_oe() {
        long[] array;

        array = ArrayUtils.removeElements((long[]) null, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L);

        array = ArrayUtils.removeElements((long[]) null, 1L, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L, 1L);
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_15_oe() {
        long[] array;

        array = ArrayUtils.removeElements((long[]) null, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L);

        array = ArrayUtils.removeElements((long[]) null, 1L, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L, 2L);
        assertArrayEquals(ArrayUtils.EMPTY_LONG_ARRAY, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_16_oe() {
        long[] array;

        array = ArrayUtils.removeElements((long[]) null, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L);

        array = ArrayUtils.removeElements((long[]) null, 1L, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L, 2L);
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_17_oe() {
        long[] array;

        array = ArrayUtils.removeElements((long[]) null, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L);

        array = ArrayUtils.removeElements((long[]) null, 1L, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L, 2L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L, 1L);
        assertArrayEquals(new long[]{2}, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_18_oe() {
        long[] array;

        array = ArrayUtils.removeElements((long[]) null, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L);

        array = ArrayUtils.removeElements((long[]) null, 1L, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L, 2L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L, 1L);
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_19_oe() {
        long[] array;

        array = ArrayUtils.removeElements((long[]) null, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L);

        array = ArrayUtils.removeElements((long[]) null, 1L, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L, 2L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L, 1L);
        assertArrayEquals(new long[]{2}, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_20_oe() {
        long[] array;

        array = ArrayUtils.removeElements((long[]) null, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L);

        array = ArrayUtils.removeElements((long[]) null, 1L, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L, 2L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L, 1L);
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_21_oe() {
        long[] array;

        array = ArrayUtils.removeElements((long[]) null, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L);

        array = ArrayUtils.removeElements((long[]) null, 1L, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L, 2L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L, 2L);
        assertArrayEquals(new long[]{1}, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_22_oe() {
        long[] array;

        array = ArrayUtils.removeElements((long[]) null, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L);

        array = ArrayUtils.removeElements((long[]) null, 1L, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L, 2L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L, 2L);
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_23_oe() {
        long[] array;

        array = ArrayUtils.removeElements((long[]) null, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L);

        array = ArrayUtils.removeElements((long[]) null, 1L, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L, 2L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L, 2L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L, 1L, 1L, 1L);
        assertArrayEquals(new long[]{2}, array);
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_24_oe() {
        long[] array;

        array = ArrayUtils.removeElements((long[]) null, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L);

        array = ArrayUtils.removeElements((long[]) null, 1L, 1L);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1 }, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L, 2L);

        array = ArrayUtils.removeElements(new long[] { 1, 2 }, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L, 1L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L, 2L);

        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, 1L, 1L, 1L, 1L);
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementShortArray_1_oe() {
        short[] array;

        array = ArrayUtils.removeElements((short[]) null, (short) 1);
        assertNull(array);
    }

    @Test
    public void testRemoveElementShortArray_2_oe() {
        short[] array;

        array = ArrayUtils.removeElements((short[]) null, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);
        assertArrayEquals(ArrayUtils.EMPTY_SHORT_ARRAY, array);
    }

    @Test
    public void testRemoveElementShortArray_3_oe() {
        short[] array;

        array = ArrayUtils.removeElements((short[]) null, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementShortArray_4_oe() {
        short[] array;

        array = ArrayUtils.removeElements((short[]) null, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1);
        assertArrayEquals(ArrayUtils.EMPTY_SHORT_ARRAY, array);
    }

    @Test
    public void testRemoveElementShortArray_5_oe() {
        short[] array;

        array = ArrayUtils.removeElements((short[]) null, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1);
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementShortArray_6_oe() {
        short[] array;

        array = ArrayUtils.removeElements((short[]) null, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1);
        assertArrayEquals(new short[]{2}, array);
    }

    @Test
    public void testRemoveElementShortArray_7_oe() {
        short[] array;

        array = ArrayUtils.removeElements((short[]) null, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1);
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementShortArray_8_oe() {
        short[] array;

        array = ArrayUtils.removeElements((short[]) null, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1);
        assertArrayEquals(new short[]{2, 1}, array);
    }

    @Test
    public void testRemoveElementShortArray_9_oe() {
        short[] array;

        array = ArrayUtils.removeElements((short[]) null, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1);
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementShortArray_10_oe() {
        short[] array;

        array = ArrayUtils.removeElements((short[]) null, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1);

        array = ArrayUtils.removeElements((short[]) null, (short) 1, (short) 1);
        assertNull(array);
    }

    @Test
    public void testRemoveElementShortArray_11_oe() {
        short[] array;

        array = ArrayUtils.removeElements((short[]) null, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1);

        array = ArrayUtils.removeElements((short[]) null, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1, (short) 1);
        assertArrayEquals(ArrayUtils.EMPTY_SHORT_ARRAY, array);
    }

    @Test
    public void testRemoveElementShortArray_12_oe() {
        short[] array;

        array = ArrayUtils.removeElements((short[]) null, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1);

        array = ArrayUtils.removeElements((short[]) null, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1, (short) 1);
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementShortArray_13_oe() {
        short[] array;

        array = ArrayUtils.removeElements((short[]) null, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1);

        array = ArrayUtils.removeElements((short[]) null, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1, (short) 1);
        assertArrayEquals(ArrayUtils.EMPTY_SHORT_ARRAY, array);
    }

    @Test
    public void testRemoveElementShortArray_14_oe() {
        short[] array;

        array = ArrayUtils.removeElements((short[]) null, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1);

        array = ArrayUtils.removeElements((short[]) null, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1, (short) 1);
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementShortArray_15_oe() {
        short[] array;

        array = ArrayUtils.removeElements((short[]) null, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1);

        array = ArrayUtils.removeElements((short[]) null, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1, (short) 2);
        assertArrayEquals(ArrayUtils.EMPTY_SHORT_ARRAY, array);
    }

    @Test
    public void testRemoveElementShortArray_16_oe() {
        short[] array;

        array = ArrayUtils.removeElements((short[]) null, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1);

        array = ArrayUtils.removeElements((short[]) null, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1, (short) 2);
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementShortArray_17_oe() {
        short[] array;

        array = ArrayUtils.removeElements((short[]) null, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1);

        array = ArrayUtils.removeElements((short[]) null, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1, (short) 2);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1, (short) 1);
        assertArrayEquals(new short[]{2}, array);
    }

    @Test
    public void testRemoveElementShortArray_18_oe() {
        short[] array;

        array = ArrayUtils.removeElements((short[]) null, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1);

        array = ArrayUtils.removeElements((short[]) null, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1, (short) 2);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1, (short) 1);
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementShortArray_19_oe() {
        short[] array;

        array = ArrayUtils.removeElements((short[]) null, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1);

        array = ArrayUtils.removeElements((short[]) null, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1, (short) 2);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1, (short) 1);
        assertArrayEquals(new short[]{2}, array);
    }

    @Test
    public void testRemoveElementShortArray_20_oe() {
        short[] array;

        array = ArrayUtils.removeElements((short[]) null, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1);

        array = ArrayUtils.removeElements((short[]) null, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1, (short) 2);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1, (short) 1);
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementShortArray_21_oe() {
        short[] array;

        array = ArrayUtils.removeElements((short[]) null, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1);

        array = ArrayUtils.removeElements((short[]) null, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1, (short) 2);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1, (short) 2);
        assertArrayEquals(new short[]{1}, array);
    }

    @Test
    public void testRemoveElementShortArray_22_oe() {
        short[] array;

        array = ArrayUtils.removeElements((short[]) null, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1);

        array = ArrayUtils.removeElements((short[]) null, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1, (short) 2);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1, (short) 2);
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementShortArray_23_oe() {
        short[] array;

        array = ArrayUtils.removeElements((short[]) null, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1);

        array = ArrayUtils.removeElements((short[]) null, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1, (short) 2);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1, (short) 2);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1, (short) 1, (short) 1, (short) 1);
        assertArrayEquals(new short[]{2}, array);
    }

    @Test
    public void testRemoveElementShortArray_24_oe() {
        short[] array;

        array = ArrayUtils.removeElements((short[]) null, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1);

        array = ArrayUtils.removeElements((short[]) null, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1, (short) 2);

        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1, (short) 1);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1, (short) 2);

        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1, (short) 1, (short) 1, (short) 1);
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementsObjectArray_1_oe() {
        Object[] array;

        array = ArrayUtils.removeElements((Object[]) null, "a");
        assertNull(array);
    }

    @Test
    public void testRemoveElementsObjectArray_2_oe() {
        Object[] array;

        array = ArrayUtils.removeElements((Object[]) null, "a");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");
        assertArrayEquals(ArrayUtils.EMPTY_OBJECT_ARRAY, array);
    }

    @Test
    public void testRemoveElementsObjectArray_3_oe() {
        Object[] array;

        array = ArrayUtils.removeElements((Object[]) null, "a");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");
        assertEquals(Object.class, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementsObjectArray_4_oe() {
        Object[] array;

        array = ArrayUtils.removeElements((Object[]) null, "a");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a");
        assertArrayEquals(ArrayUtils.EMPTY_OBJECT_ARRAY, array);
    }

    @Test
    public void testRemoveElementsObjectArray_5_oe() {
        Object[] array;

        array = ArrayUtils.removeElements((Object[]) null, "a");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a");
        assertEquals(Object.class, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementsObjectArray_6_oe() {
        Object[] array;

        array = ArrayUtils.removeElements((Object[]) null, "a");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a");
        assertArrayEquals(new Object[]{"b"}, array);
    }

    @Test
    public void testRemoveElementsObjectArray_7_oe() {
        Object[] array;

        array = ArrayUtils.removeElements((Object[]) null, "a");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a");
        assertEquals(Object.class, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementsObjectArray_8_oe() {
        Object[] array;

        array = ArrayUtils.removeElements((Object[]) null, "a");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");
        assertArrayEquals(new Object[]{"b", "a"}, array);
    }

    @Test
    public void testRemoveElementsObjectArray_9_oe() {
        Object[] array;

        array = ArrayUtils.removeElements((Object[]) null, "a");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");
        assertEquals(Object.class, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementsObjectArray_10_oe() {
        Object[] array;

        array = ArrayUtils.removeElements((Object[]) null, "a");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");

        array = ArrayUtils.removeElements((Object[]) null, "a", "b");
        assertNull(array);
    }

    @Test
    public void testRemoveElementsObjectArray_11_oe() {
        Object[] array;

        array = ArrayUtils.removeElements((Object[]) null, "a");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");

        array = ArrayUtils.removeElements((Object[]) null, "a", "b");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a", "b");
        assertArrayEquals(ArrayUtils.EMPTY_OBJECT_ARRAY, array);
    }

    @Test
    public void testRemoveElementsObjectArray_12_oe() {
        Object[] array;

        array = ArrayUtils.removeElements((Object[]) null, "a");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");

        array = ArrayUtils.removeElements((Object[]) null, "a", "b");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a", "b");
        assertEquals(Object.class, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementsObjectArray_13_oe() {
        Object[] array;

        array = ArrayUtils.removeElements((Object[]) null, "a");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");

        array = ArrayUtils.removeElements((Object[]) null, "a", "b");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a", "b");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a", "b");
        assertArrayEquals(ArrayUtils.EMPTY_OBJECT_ARRAY, array);
    }

    @Test
    public void testRemoveElementsObjectArray_14_oe() {
        Object[] array;

        array = ArrayUtils.removeElements((Object[]) null, "a");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");

        array = ArrayUtils.removeElements((Object[]) null, "a", "b");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a", "b");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a", "b");
        assertEquals(Object.class, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementsObjectArray_15_oe() {
        Object[] array;

        array = ArrayUtils.removeElements((Object[]) null, "a");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");

        array = ArrayUtils.removeElements((Object[]) null, "a", "b");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a", "b");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a", "b");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a", "c");
        assertArrayEquals(new Object[]{"b"}, array);
    }

    @Test
    public void testRemoveElementsObjectArray_16_oe() {
        Object[] array;

        array = ArrayUtils.removeElements((Object[]) null, "a");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");

        array = ArrayUtils.removeElements((Object[]) null, "a", "b");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a", "b");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a", "b");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a", "c");
        assertEquals(Object.class, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementsObjectArray_17_oe() {
        Object[] array;

        array = ArrayUtils.removeElements((Object[]) null, "a");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");

        array = ArrayUtils.removeElements((Object[]) null, "a", "b");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a", "b");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a", "b");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a", "c");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");
        assertArrayEquals(new Object[]{"b", "a"}, array);
    }

    @Test
    public void testRemoveElementsObjectArray_18_oe() {
        Object[] array;

        array = ArrayUtils.removeElements((Object[]) null, "a");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");

        array = ArrayUtils.removeElements((Object[]) null, "a", "b");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a", "b");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a", "b");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a", "c");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");
        assertEquals(Object.class, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementsObjectArray_19_oe() {
        Object[] array;

        array = ArrayUtils.removeElements((Object[]) null, "a");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");

        array = ArrayUtils.removeElements((Object[]) null, "a", "b");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a", "b");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a", "b");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a", "c");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a", "b");
        assertArrayEquals(new Object[]{"a"}, array);
    }

    @Test
    public void testRemoveElementsObjectArray_20_oe() {
        Object[] array;

        array = ArrayUtils.removeElements((Object[]) null, "a");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");

        array = ArrayUtils.removeElements((Object[]) null, "a", "b");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a", "b");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a", "b");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a", "c");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a", "b");
        assertEquals(Object.class, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementsObjectArray_21_oe() {
        Object[] array;

        array = ArrayUtils.removeElements((Object[]) null, "a");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");

        array = ArrayUtils.removeElements((Object[]) null, "a", "b");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a", "b");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a", "b");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a", "c");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a", "b");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a", "a");
        assertArrayEquals(new Object[]{"b"}, array);
    }

    @Test
    public void testRemoveElementsObjectArray_22_oe() {
        Object[] array;

        array = ArrayUtils.removeElements((Object[]) null, "a");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");

        array = ArrayUtils.removeElements((Object[]) null, "a", "b");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a", "b");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a", "b");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a", "c");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a", "b");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a", "a");
        assertEquals(Object.class, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementsObjectArray_23_oe() {
        Object[] array;

        array = ArrayUtils.removeElements((Object[]) null, "a");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");

        array = ArrayUtils.removeElements((Object[]) null, "a", "b");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a", "b");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a", "b");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a", "c");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a", "b");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a", "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a", "a", "a", "a");
        assertArrayEquals(new Object[]{"b"}, array);
    }

    @Test
    public void testRemoveElementsObjectArray_24_oe() {
        Object[] array;

        array = ArrayUtils.removeElements((Object[]) null, "a");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");

        array = ArrayUtils.removeElements((Object[]) null, "a", "b");

        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a", "b");

        array = ArrayUtils.removeElements(new Object[] { "a" }, "a", "b");

        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a", "c");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a", "b");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a", "a");

        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a", "a", "a", "a");
        assertEquals(Object.class, array.getClass().getComponentType());
    }

}
