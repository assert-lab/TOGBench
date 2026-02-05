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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests ArrayUtils remove and removeElement methods.
 */
public class ArrayUtilsRemoveTest_OE25Dev {

@Test
    public void testRemoveAllBooleanOccurences_1_oe() {
        boolean[] a = null;
        assertNull(ArrayUtils.removeAllOccurences(a, true));
    }

@Test
    public void testRemoveAllBooleanOccurences_2_oe() {
        boolean[] a = null;
        // removed other assertion

        a = new boolean[0];
        assertArrayEquals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, ArrayUtils.removeAllOccurences(a, true));
    }

@Test
    public void testRemoveAllBooleanOccurences_3_oe() {
        boolean[] a = null;
        // removed other assertion

        a = new boolean[0];
        // removed other assertion

        a = new boolean[] { true };
        assertArrayEquals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, ArrayUtils.removeAllOccurences(a, true));
    }

@Test
    public void testRemoveAllBooleanOccurences_4_oe() {
        boolean[] a = null;
        // removed other assertion

        a = new boolean[0];
        // removed other assertion

        a = new boolean[] { true };
        // removed other assertion

        a = new boolean[] { true, true };
        assertArrayEquals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, ArrayUtils.removeAllOccurences(a, true));
    }

@Test
    public void testRemoveAllBooleanOccurences_5_oe() {
        boolean[] a = null;
        // removed other assertion

        a = new boolean[0];
        // removed other assertion

        a = new boolean[] { true };
        // removed other assertion

        a = new boolean[] { true, true };
        // removed other assertion

        a = new boolean[] { false, true, true, false, true };
        assertArrayEquals(new boolean[]{false, false}, ArrayUtils.removeAllOccurences(a, true));
    }

@Test
    public void testRemoveAllBooleanOccurences_6_oe() {
        boolean[] a = null;
        // removed other assertion

        a = new boolean[0];
        // removed other assertion

        a = new boolean[] { true };
        // removed other assertion

        a = new boolean[] { true, true };
        // removed other assertion

        a = new boolean[] { false, true, true, false, true };
        // removed other assertion

        a = new boolean[] { false, true, true, false, true };
        assertArrayEquals(new boolean[]{true, true, true}, ArrayUtils.removeAllOccurences(a, false));
    }

@Test
    public void testRemoveAllBooleanOccurrences_1_oe() {
        boolean[] a = null;
        assertNull(ArrayUtils.removeAllOccurrences(a, true));
    }

@Test
    public void testRemoveAllBooleanOccurrences_2_oe() {
        boolean[] a = null;
        // removed other assertion

        a = new boolean[0];
        assertArrayEquals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, ArrayUtils.removeAllOccurrences(a, true));
    }

@Test
    public void testRemoveAllBooleanOccurrences_3_oe() {
        boolean[] a = null;
        // removed other assertion

        a = new boolean[0];
        // removed other assertion

        a = new boolean[] { true };
        assertArrayEquals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, ArrayUtils.removeAllOccurrences(a, true));
    }

@Test
    public void testRemoveAllBooleanOccurrences_4_oe() {
        boolean[] a = null;
        // removed other assertion

        a = new boolean[0];
        // removed other assertion

        a = new boolean[] { true };
        // removed other assertion

        a = new boolean[] { true, true };
        assertArrayEquals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, ArrayUtils.removeAllOccurrences(a, true));
    }

@Test
    public void testRemoveAllBooleanOccurrences_5_oe() {
        boolean[] a = null;
        // removed other assertion

        a = new boolean[0];
        // removed other assertion

        a = new boolean[] { true };
        // removed other assertion

        a = new boolean[] { true, true };
        // removed other assertion

        a = new boolean[] { false, true, true, false, true };
        assertArrayEquals(new boolean[]{false, false}, ArrayUtils.removeAllOccurrences(a, true));
    }

@Test
    public void testRemoveAllBooleanOccurrences_6_oe() {
        boolean[] a = null;
        // removed other assertion

        a = new boolean[0];
        // removed other assertion

        a = new boolean[] { true };
        // removed other assertion

        a = new boolean[] { true, true };
        // removed other assertion

        a = new boolean[] { false, true, true, false, true };
        // removed other assertion

        a = new boolean[] { false, true, true, false, true };
        assertArrayEquals(new boolean[]{true, true, true}, ArrayUtils.removeAllOccurrences(a, false));
    }

@Test
    public void testRemoveAllByteOccurences_1_oe() {
        byte[] a = null;
        assertNull(ArrayUtils.removeAllOccurences(a, (byte) 2));
    }

@Test
    public void testRemoveAllByteOccurences_2_oe() {
        byte[] a = null;
        // removed other assertion

        a = new byte[0];
        assertArrayEquals(ArrayUtils.EMPTY_BYTE_ARRAY, ArrayUtils.removeAllOccurences(a, (byte) 2));
    }

@Test
    public void testRemoveAllByteOccurences_3_oe() {
        byte[] a = null;
        // removed other assertion

        a = new byte[0];
        // removed other assertion

        a = new byte[] { 2 };
        assertArrayEquals(ArrayUtils.EMPTY_BYTE_ARRAY, ArrayUtils.removeAllOccurences(a, (byte) 2));
    }

@Test
    public void testRemoveAllByteOccurences_4_oe() {
        byte[] a = null;
        // removed other assertion

        a = new byte[0];
        // removed other assertion

        a = new byte[] { 2 };
        // removed other assertion

        a = new byte[] { 2, 2 };
        assertArrayEquals(ArrayUtils.EMPTY_BYTE_ARRAY, ArrayUtils.removeAllOccurences(a, (byte) 2));
    }

@Test
    public void testRemoveAllByteOccurences_5_oe() {
        byte[] a = null;
        // removed other assertion

        a = new byte[0];
        // removed other assertion

        a = new byte[] { 2 };
        // removed other assertion

        a = new byte[] { 2, 2 };
        // removed other assertion

        a = new byte[] { 1, 2, 2, 3, 2 };
        assertArrayEquals(new byte[]{1, 3}, ArrayUtils.removeAllOccurences(a, (byte) 2));
    }

@Test
    public void testRemoveAllByteOccurences_6_oe() {
        byte[] a = null;
        // removed other assertion

        a = new byte[0];
        // removed other assertion

        a = new byte[] { 2 };
        // removed other assertion

        a = new byte[] { 2, 2 };
        // removed other assertion

        a = new byte[] { 1, 2, 2, 3, 2 };
        // removed other assertion

        a = new byte[] { 1, 2, 2, 3, 2 };
        assertArrayEquals(new byte[]{1, 2, 2, 3, 2}, ArrayUtils.removeAllOccurences(a, (byte) 4));
    }

@Test
    public void testRemoveAllByteOccurrences_1_oe() {
        byte[] a = null;
        assertNull(ArrayUtils.removeAllOccurrences(a, (byte) 2));
    }

@Test
    public void testRemoveAllByteOccurrences_2_oe() {
        byte[] a = null;
        // removed other assertion

        a = new byte[0];
        assertArrayEquals(ArrayUtils.EMPTY_BYTE_ARRAY, ArrayUtils.removeAllOccurrences(a, (byte) 2));
    }

@Test
    public void testRemoveAllByteOccurrences_3_oe() {
        byte[] a = null;
        // removed other assertion

        a = new byte[0];
        // removed other assertion

        a = new byte[] { 2 };
        assertArrayEquals(ArrayUtils.EMPTY_BYTE_ARRAY, ArrayUtils.removeAllOccurrences(a, (byte) 2));
    }

@Test
    public void testRemoveAllByteOccurrences_4_oe() {
        byte[] a = null;
        // removed other assertion

        a = new byte[0];
        // removed other assertion

        a = new byte[] { 2 };
        // removed other assertion

        a = new byte[] { 2, 2 };
        assertArrayEquals(ArrayUtils.EMPTY_BYTE_ARRAY, ArrayUtils.removeAllOccurrences(a, (byte) 2));
    }

@Test
    public void testRemoveAllByteOccurrences_5_oe() {
        byte[] a = null;
        // removed other assertion

        a = new byte[0];
        // removed other assertion

        a = new byte[] { 2 };
        // removed other assertion

        a = new byte[] { 2, 2 };
        // removed other assertion

        a = new byte[] { 1, 2, 2, 3, 2 };
        assertArrayEquals(new byte[]{1, 3}, ArrayUtils.removeAllOccurrences(a, (byte) 2));
    }

@Test
    public void testRemoveAllByteOccurrences_6_oe() {
        byte[] a = null;
        // removed other assertion

        a = new byte[0];
        // removed other assertion

        a = new byte[] { 2 };
        // removed other assertion

        a = new byte[] { 2, 2 };
        // removed other assertion

        a = new byte[] { 1, 2, 2, 3, 2 };
        // removed other assertion

        a = new byte[] { 1, 2, 2, 3, 2 };
        assertArrayEquals(new byte[]{1, 2, 2, 3, 2}, ArrayUtils.removeAllOccurrences(a, (byte) 4));
    }

@Test
    public void testRemoveAllCharOccurences_1_oe() {
        char[] a = null;
        assertNull(ArrayUtils.removeAllOccurences(a, '2'));
    }

@Test
    public void testRemoveAllCharOccurences_2_oe() {
        char[] a = null;
        // removed other assertion

        a = new char[0];
        assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, ArrayUtils.removeAllOccurences(a, '2'));
    }

@Test
    public void testRemoveAllCharOccurences_3_oe() {
        char[] a = null;
        // removed other assertion

        a = new char[0];
        // removed other assertion

        a = new char[] { '2' };
        assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, ArrayUtils.removeAllOccurences(a, '2'));
    }

@Test
    public void testRemoveAllCharOccurences_4_oe() {
        char[] a = null;
        // removed other assertion

        a = new char[0];
        // removed other assertion

        a = new char[] { '2' };
        // removed other assertion

        a = new char[] { '2', '2' };
        assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, ArrayUtils.removeAllOccurences(a, '2'));
    }

@Test
    public void testRemoveAllCharOccurences_5_oe() {
        char[] a = null;
        // removed other assertion

        a = new char[0];
        // removed other assertion

        a = new char[] { '2' };
        // removed other assertion

        a = new char[] { '2', '2' };
        // removed other assertion

        a = new char[] { '1', '2', '2', '3', '2' };
        assertArrayEquals(new char[]{'1', '3'}, ArrayUtils.removeAllOccurences(a, '2'));
    }

@Test
    public void testRemoveAllCharOccurences_6_oe() {
        char[] a = null;
        // removed other assertion

        a = new char[0];
        // removed other assertion

        a = new char[] { '2' };
        // removed other assertion

        a = new char[] { '2', '2' };
        // removed other assertion

        a = new char[] { '1', '2', '2', '3', '2' };
        // removed other assertion

        a = new char[] { '1', '2', '2', '3', '2' };
        assertArrayEquals(new char[]{'1', '2', '2', '3', '2'}, ArrayUtils.removeAllOccurences(a, '4'));
    }

@Test
    public void testRemoveAllCharOccurrences_1_oe() {
        char[] a = null;
        assertNull(ArrayUtils.removeAllOccurrences(a, '2'));
    }

@Test
    public void testRemoveAllCharOccurrences_2_oe() {
        char[] a = null;
        // removed other assertion

        a = new char[0];
        assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, ArrayUtils.removeAllOccurrences(a, '2'));
    }

@Test
    public void testRemoveAllCharOccurrences_3_oe() {
        char[] a = null;
        // removed other assertion

        a = new char[0];
        // removed other assertion

        a = new char[] { '2' };
        assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, ArrayUtils.removeAllOccurrences(a, '2'));
    }

@Test
    public void testRemoveAllCharOccurrences_4_oe() {
        char[] a = null;
        // removed other assertion

        a = new char[0];
        // removed other assertion

        a = new char[] { '2' };
        // removed other assertion

        a = new char[] { '2', '2' };
        assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, ArrayUtils.removeAllOccurrences(a, '2'));
    }

@Test
    public void testRemoveAllCharOccurrences_5_oe() {
        char[] a = null;
        // removed other assertion

        a = new char[0];
        // removed other assertion

        a = new char[] { '2' };
        // removed other assertion

        a = new char[] { '2', '2' };
        // removed other assertion

        a = new char[] { '1', '2', '2', '3', '2' };
        assertArrayEquals(new char[]{'1', '3'}, ArrayUtils.removeAllOccurrences(a, '2'));
    }

@Test
    public void testRemoveAllCharOccurrences_6_oe() {
        char[] a = null;
        // removed other assertion

        a = new char[0];
        // removed other assertion

        a = new char[] { '2' };
        // removed other assertion

        a = new char[] { '2', '2' };
        // removed other assertion

        a = new char[] { '1', '2', '2', '3', '2' };
        // removed other assertion

        a = new char[] { '1', '2', '2', '3', '2' };
        assertArrayEquals(new char[]{'1', '2', '2', '3', '2'}, ArrayUtils.removeAllOccurrences(a, '4'));
    }

@Test
    public void testRemoveAllDoubleOccurences_1_oe() {
        double[] a = null;
        assertNull(ArrayUtils.removeAllOccurences(a, 2));
    }

@Test
    public void testRemoveAllDoubleOccurences_2_oe() {
        double[] a = null;
        // removed other assertion

        a = new double[0];
        assertArrayEquals(ArrayUtils.EMPTY_DOUBLE_ARRAY, ArrayUtils.removeAllOccurences(a, 2));
    }

@Test
    public void testRemoveAllDoubleOccurences_3_oe() {
        double[] a = null;
        // removed other assertion

        a = new double[0];
        // removed other assertion

        a = new double[] { 2 };
        assertArrayEquals(ArrayUtils.EMPTY_DOUBLE_ARRAY, ArrayUtils.removeAllOccurences(a, 2));
    }

@Test
    public void testRemoveAllDoubleOccurences_4_oe() {
        double[] a = null;
        // removed other assertion

        a = new double[0];
        // removed other assertion

        a = new double[] { 2 };
        // removed other assertion

        a = new double[] { 2, 2 };
        assertArrayEquals(ArrayUtils.EMPTY_DOUBLE_ARRAY, ArrayUtils.removeAllOccurences(a, 2));
    }

@Test
    public void testRemoveAllDoubleOccurences_5_oe() {
        double[] a = null;
        // removed other assertion

        a = new double[0];
        // removed other assertion

        a = new double[] { 2 };
        // removed other assertion

        a = new double[] { 2, 2 };
        // removed other assertion

        a = new double[] { 1, 2, 2, 3, 2 };
        assertArrayEquals(new double[]{1, 3}, ArrayUtils.removeAllOccurences(a, 2));
    }

@Test
    public void testRemoveAllDoubleOccurences_6_oe() {
        double[] a = null;
        // removed other assertion

        a = new double[0];
        // removed other assertion

        a = new double[] { 2 };
        // removed other assertion

        a = new double[] { 2, 2 };
        // removed other assertion

        a = new double[] { 1, 2, 2, 3, 2 };
        // removed other assertion

        a = new double[] { 1, 2, 2, 3, 2 };
        assertArrayEquals(new double[]{1, 2, 2, 3, 2}, ArrayUtils.removeAllOccurences(a, 4));
    }

@Test
    public void testRemoveAllDoubleOccurrences_1_oe() {
        double[] a = null;
        assertNull(ArrayUtils.removeAllOccurrences(a, 2));
    }

@Test
    public void testRemoveAllDoubleOccurrences_2_oe() {
        double[] a = null;
        // removed other assertion

        a = new double[0];
        assertArrayEquals(ArrayUtils.EMPTY_DOUBLE_ARRAY, ArrayUtils.removeAllOccurrences(a, 2));
    }

@Test
    public void testRemoveAllDoubleOccurrences_3_oe() {
        double[] a = null;
        // removed other assertion

        a = new double[0];
        // removed other assertion

        a = new double[] { 2 };
        assertArrayEquals(ArrayUtils.EMPTY_DOUBLE_ARRAY, ArrayUtils.removeAllOccurrences(a, 2));
    }

@Test
    public void testRemoveAllDoubleOccurrences_4_oe() {
        double[] a = null;
        // removed other assertion

        a = new double[0];
        // removed other assertion

        a = new double[] { 2 };
        // removed other assertion

        a = new double[] { 2, 2 };
        assertArrayEquals(ArrayUtils.EMPTY_DOUBLE_ARRAY, ArrayUtils.removeAllOccurrences(a, 2));
    }

@Test
    public void testRemoveAllDoubleOccurrences_5_oe() {
        double[] a = null;
        // removed other assertion

        a = new double[0];
        // removed other assertion

        a = new double[] { 2 };
        // removed other assertion

        a = new double[] { 2, 2 };
        // removed other assertion

        a = new double[] { 1, 2, 2, 3, 2 };
        assertArrayEquals(new double[]{1, 3}, ArrayUtils.removeAllOccurrences(a, 2));
    }

@Test
    public void testRemoveAllDoubleOccurrences_6_oe() {
        double[] a = null;
        // removed other assertion

        a = new double[0];
        // removed other assertion

        a = new double[] { 2 };
        // removed other assertion

        a = new double[] { 2, 2 };
        // removed other assertion

        a = new double[] { 1, 2, 2, 3, 2 };
        // removed other assertion

        a = new double[] { 1, 2, 2, 3, 2 };
        assertArrayEquals(new double[]{1, 2, 2, 3, 2}, ArrayUtils.removeAllOccurrences(a, 4));
    }

@Test
    public void testRemoveAllFloatOccurences_1_oe() {
        float[] a = null;
        assertNull(ArrayUtils.removeAllOccurences(a, 2));
    }

@Test
    public void testRemoveAllFloatOccurences_2_oe() {
        float[] a = null;
        // removed other assertion

        a = new float[0];
        assertArrayEquals(ArrayUtils.EMPTY_FLOAT_ARRAY, ArrayUtils.removeAllOccurences(a, 2));
    }

@Test
    public void testRemoveAllFloatOccurences_3_oe() {
        float[] a = null;
        // removed other assertion

        a = new float[0];
        // removed other assertion

        a = new float[] { 2 };
        assertArrayEquals(ArrayUtils.EMPTY_FLOAT_ARRAY, ArrayUtils.removeAllOccurences(a, 2));
    }

@Test
    public void testRemoveAllFloatOccurences_4_oe() {
        float[] a = null;
        // removed other assertion

        a = new float[0];
        // removed other assertion

        a = new float[] { 2 };
        // removed other assertion

        a = new float[] { 2, 2 };
        assertArrayEquals(ArrayUtils.EMPTY_FLOAT_ARRAY, ArrayUtils.removeAllOccurences(a, 2));
    }

@Test
    public void testRemoveAllFloatOccurences_5_oe() {
        float[] a = null;
        // removed other assertion

        a = new float[0];
        // removed other assertion

        a = new float[] { 2 };
        // removed other assertion

        a = new float[] { 2, 2 };
        // removed other assertion

        a = new float[] { 1, 2, 2, 3, 2 };
        assertArrayEquals(new float[]{1, 3}, ArrayUtils.removeAllOccurences(a, 2));
    }

@Test
    public void testRemoveAllFloatOccurences_6_oe() {
        float[] a = null;
        // removed other assertion

        a = new float[0];
        // removed other assertion

        a = new float[] { 2 };
        // removed other assertion

        a = new float[] { 2, 2 };
        // removed other assertion

        a = new float[] { 1, 2, 2, 3, 2 };
        // removed other assertion

        a = new float[] { 1, 2, 2, 3, 2 };
        assertArrayEquals(new float[]{1, 2, 2, 3, 2}, ArrayUtils.removeAllOccurences(a, 4));
    }

@Test
    public void testRemoveAllFloatOccurrences_1_oe() {
        float[] a = null;
        assertNull(ArrayUtils.removeAllOccurrences(a, 2));
    }

@Test
    public void testRemoveAllFloatOccurrences_2_oe() {
        float[] a = null;
        // removed other assertion

        a = new float[0];
        assertArrayEquals(ArrayUtils.EMPTY_FLOAT_ARRAY, ArrayUtils.removeAllOccurrences(a, 2));
    }

@Test
    public void testRemoveAllFloatOccurrences_3_oe() {
        float[] a = null;
        // removed other assertion

        a = new float[0];
        // removed other assertion

        a = new float[] { 2 };
        assertArrayEquals(ArrayUtils.EMPTY_FLOAT_ARRAY, ArrayUtils.removeAllOccurrences(a, 2));
    }

@Test
    public void testRemoveAllFloatOccurrences_4_oe() {
        float[] a = null;
        // removed other assertion

        a = new float[0];
        // removed other assertion

        a = new float[] { 2 };
        // removed other assertion

        a = new float[] { 2, 2 };
        assertArrayEquals(ArrayUtils.EMPTY_FLOAT_ARRAY, ArrayUtils.removeAllOccurrences(a, 2));
    }

@Test
    public void testRemoveAllFloatOccurrences_5_oe() {
        float[] a = null;
        // removed other assertion

        a = new float[0];
        // removed other assertion

        a = new float[] { 2 };
        // removed other assertion

        a = new float[] { 2, 2 };
        // removed other assertion

        a = new float[] { 1, 2, 2, 3, 2 };
        assertArrayEquals(new float[]{1, 3}, ArrayUtils.removeAllOccurrences(a, 2));
    }

@Test
    public void testRemoveAllFloatOccurrences_6_oe() {
        float[] a = null;
        // removed other assertion

        a = new float[0];
        // removed other assertion

        a = new float[] { 2 };
        // removed other assertion

        a = new float[] { 2, 2 };
        // removed other assertion

        a = new float[] { 1, 2, 2, 3, 2 };
        // removed other assertion

        a = new float[] { 1, 2, 2, 3, 2 };
        assertArrayEquals(new float[]{1, 2, 2, 3, 2}, ArrayUtils.removeAllOccurrences(a, 4));
    }

@Test
    public void testRemoveAllIntOccurences_1_oe() {
        int[] a = null;
        assertNull(ArrayUtils.removeAllOccurences(a, 2));
    }

@Test
    public void testRemoveAllIntOccurences_2_oe() {
        int[] a = null;
        // removed other assertion

        a = new int[0];
        assertArrayEquals(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.removeAllOccurences(a, 2));
    }

@Test
    public void testRemoveAllIntOccurences_3_oe() {
        int[] a = null;
        // removed other assertion

        a = new int[0];
        // removed other assertion

        a = new int[] { 2 };
        assertArrayEquals(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.removeAllOccurences(a, 2));
    }

@Test
    public void testRemoveAllIntOccurences_4_oe() {
        int[] a = null;
        // removed other assertion

        a = new int[0];
        // removed other assertion

        a = new int[] { 2 };
        // removed other assertion

        a = new int[] { 2, 2 };
        assertArrayEquals(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.removeAllOccurences(a, 2));
    }

@Test
    public void testRemoveAllIntOccurences_5_oe() {
        int[] a = null;
        // removed other assertion

        a = new int[0];
        // removed other assertion

        a = new int[] { 2 };
        // removed other assertion

        a = new int[] { 2, 2 };
        // removed other assertion

        a = new int[] { 1, 2, 2, 3, 2 };
        assertArrayEquals(new int[]{1, 3}, ArrayUtils.removeAllOccurences(a, 2));
    }

@Test
    public void testRemoveAllIntOccurences_6_oe() {
        int[] a = null;
        // removed other assertion

        a = new int[0];
        // removed other assertion

        a = new int[] { 2 };
        // removed other assertion

        a = new int[] { 2, 2 };
        // removed other assertion

        a = new int[] { 1, 2, 2, 3, 2 };
        // removed other assertion

        a = new int[] { 1, 2, 2, 3, 2 };
        assertArrayEquals(new int[]{1, 2, 2, 3, 2}, ArrayUtils.removeAllOccurences(a, 4));
    }

@Test
    public void testRemoveAllIntOccurrences_1_oe() {
        int[] a = null;
        assertNull(ArrayUtils.removeAllOccurrences(a, 2));
    }

@Test
    public void testRemoveAllIntOccurrences_2_oe() {
        int[] a = null;
        // removed other assertion

        a = new int[0];
        assertArrayEquals(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.removeAllOccurrences(a, 2));
    }

@Test
    public void testRemoveAllIntOccurrences_3_oe() {
        int[] a = null;
        // removed other assertion

        a = new int[0];
        // removed other assertion

        a = new int[] { 2 };
        assertArrayEquals(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.removeAllOccurrences(a, 2));
    }

@Test
    public void testRemoveAllIntOccurrences_4_oe() {
        int[] a = null;
        // removed other assertion

        a = new int[0];
        // removed other assertion

        a = new int[] { 2 };
        // removed other assertion

        a = new int[] { 2, 2 };
        assertArrayEquals(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.removeAllOccurrences(a, 2));
    }

@Test
    public void testRemoveAllIntOccurrences_5_oe() {
        int[] a = null;
        // removed other assertion

        a = new int[0];
        // removed other assertion

        a = new int[] { 2 };
        // removed other assertion

        a = new int[] { 2, 2 };
        // removed other assertion

        a = new int[] { 1, 2, 2, 3, 2 };
        assertArrayEquals(new int[]{1, 3}, ArrayUtils.removeAllOccurrences(a, 2));
    }

@Test
    public void testRemoveAllIntOccurrences_6_oe() {
        int[] a = null;
        // removed other assertion

        a = new int[0];
        // removed other assertion

        a = new int[] { 2 };
        // removed other assertion

        a = new int[] { 2, 2 };
        // removed other assertion

        a = new int[] { 1, 2, 2, 3, 2 };
        // removed other assertion

        a = new int[] { 1, 2, 2, 3, 2 };
        assertArrayEquals(new int[]{1, 2, 2, 3, 2}, ArrayUtils.removeAllOccurrences(a, 4));
    }

@Test
    public void testRemoveAllLongOccurences_1_oe() {
        long[] a = null;
        assertNull(ArrayUtils.removeAllOccurences(a, 2));
    }

@Test
    public void testRemoveAllLongOccurences_2_oe() {
        long[] a = null;
        // removed other assertion

        a = new long[0];
        assertArrayEquals(ArrayUtils.EMPTY_LONG_ARRAY, ArrayUtils.removeAllOccurences(a, 2));
    }

@Test
    public void testRemoveAllLongOccurences_3_oe() {
        long[] a = null;
        // removed other assertion

        a = new long[0];
        // removed other assertion

        a = new long[] { 2 };
        assertArrayEquals(ArrayUtils.EMPTY_LONG_ARRAY, ArrayUtils.removeAllOccurences(a, 2));
    }

@Test
    public void testRemoveAllLongOccurences_4_oe() {
        long[] a = null;
        // removed other assertion

        a = new long[0];
        // removed other assertion

        a = new long[] { 2 };
        // removed other assertion

        a = new long[] { 2, 2 };
        assertArrayEquals(ArrayUtils.EMPTY_LONG_ARRAY, ArrayUtils.removeAllOccurences(a, 2));
    }

@Test
    public void testRemoveAllLongOccurences_5_oe() {
        long[] a = null;
        // removed other assertion

        a = new long[0];
        // removed other assertion

        a = new long[] { 2 };
        // removed other assertion

        a = new long[] { 2, 2 };
        // removed other assertion

        a = new long[] { 1, 2, 2, 3, 2 };
        assertArrayEquals(new long[]{1, 3}, ArrayUtils.removeAllOccurences(a, 2));
    }

@Test
    public void testRemoveAllLongOccurences_6_oe() {
        long[] a = null;
        // removed other assertion

        a = new long[0];
        // removed other assertion

        a = new long[] { 2 };
        // removed other assertion

        a = new long[] { 2, 2 };
        // removed other assertion

        a = new long[] { 1, 2, 2, 3, 2 };
        // removed other assertion

        a = new long[] { 1, 2, 2, 3, 2 };
        assertArrayEquals(new long[]{1, 2, 2, 3, 2}, ArrayUtils.removeAllOccurences(a, 4));
    }

@Test
    public void testRemoveAllLongOccurrences_1_oe() {
        long[] a = null;
        assertNull(ArrayUtils.removeAllOccurrences(a, 2));
    }

@Test
    public void testRemoveAllLongOccurrences_2_oe() {
        long[] a = null;
        // removed other assertion

        a = new long[0];
        assertArrayEquals(ArrayUtils.EMPTY_LONG_ARRAY, ArrayUtils.removeAllOccurrences(a, 2));
    }

@Test
    public void testRemoveAllLongOccurrences_3_oe() {
        long[] a = null;
        // removed other assertion

        a = new long[0];
        // removed other assertion

        a = new long[] { 2 };
        assertArrayEquals(ArrayUtils.EMPTY_LONG_ARRAY, ArrayUtils.removeAllOccurrences(a, 2));
    }

@Test
    public void testRemoveAllLongOccurrences_4_oe() {
        long[] a = null;
        // removed other assertion

        a = new long[0];
        // removed other assertion

        a = new long[] { 2 };
        // removed other assertion

        a = new long[] { 2, 2 };
        assertArrayEquals(ArrayUtils.EMPTY_LONG_ARRAY, ArrayUtils.removeAllOccurrences(a, 2));
    }

@Test
    public void testRemoveAllLongOccurrences_5_oe() {
        long[] a = null;
        // removed other assertion

        a = new long[0];
        // removed other assertion

        a = new long[] { 2 };
        // removed other assertion

        a = new long[] { 2, 2 };
        // removed other assertion

        a = new long[] { 1, 2, 2, 3, 2 };
        assertArrayEquals(new long[]{1, 3}, ArrayUtils.removeAllOccurrences(a, 2));
    }

@Test
    public void testRemoveAllLongOccurrences_6_oe() {
        long[] a = null;
        // removed other assertion

        a = new long[0];
        // removed other assertion

        a = new long[] { 2 };
        // removed other assertion

        a = new long[] { 2, 2 };
        // removed other assertion

        a = new long[] { 1, 2, 2, 3, 2 };
        // removed other assertion

        a = new long[] { 1, 2, 2, 3, 2 };
        assertArrayEquals(new long[]{1, 2, 2, 3, 2}, ArrayUtils.removeAllOccurrences(a, 4));
    }

@Test
    public void testRemoveAllObjectOccurences_1_oe() {
        String[] a = null;
        assertNull(ArrayUtils.removeAllOccurences(a, "2"));
    }

@Test
    public void testRemoveAllObjectOccurences_2_oe() {
        String[] a = null;
        // removed other assertion

        a = new String[0];
        assertArrayEquals(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.removeAllOccurences(a, "2"));
    }

@Test
    public void testRemoveAllObjectOccurences_3_oe() {
        String[] a = null;
        // removed other assertion

        a = new String[0];
        // removed other assertion

        a = new String[] { "2" };
        assertArrayEquals(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.removeAllOccurences(a, "2"));
    }

@Test
    public void testRemoveAllObjectOccurences_4_oe() {
        String[] a = null;
        // removed other assertion

        a = new String[0];
        // removed other assertion

        a = new String[] { "2" };
        // removed other assertion

        a = new String[] { "2", "2" };
        assertArrayEquals(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.removeAllOccurences(a, "2"));
    }

@Test
    public void testRemoveAllObjectOccurences_5_oe() {
        String[] a = null;
        // removed other assertion

        a = new String[0];
        // removed other assertion

        a = new String[] { "2" };
        // removed other assertion

        a = new String[] { "2", "2" };
        // removed other assertion

        a = new String[] { "1", "2", "2", "3", "2" };
        assertArrayEquals(new String[]{"1", "3"}, ArrayUtils.removeAllOccurences(a, "2"));
    }

@Test
    public void testRemoveAllObjectOccurences_6_oe() {
        String[] a = null;
        // removed other assertion

        a = new String[0];
        // removed other assertion

        a = new String[] { "2" };
        // removed other assertion

        a = new String[] { "2", "2" };
        // removed other assertion

        a = new String[] { "1", "2", "2", "3", "2" };
        // removed other assertion

        a = new String[] { "1", "2", "2", "3", "2" };
        assertArrayEquals(new String[]{"1", "2", "2", "3", "2"}, ArrayUtils.removeAllOccurences(a, "4"));
    }

@Test
    public void testRemoveAllObjectOccurrences_1_oe() {
        String[] a = null;
        assertNull(ArrayUtils.removeAllOccurrences(a, "2"));
    }

@Test
    public void testRemoveAllObjectOccurrences_2_oe() {
        String[] a = null;
        // removed other assertion

        a = new String[0];
        assertArrayEquals(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.removeAllOccurrences(a, "2"));
    }

@Test
    public void testRemoveAllObjectOccurrences_3_oe() {
        String[] a = null;
        // removed other assertion

        a = new String[0];
        // removed other assertion

        a = new String[] { "2" };
        assertArrayEquals(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.removeAllOccurrences(a, "2"));
    }

@Test
    public void testRemoveAllObjectOccurrences_4_oe() {
        String[] a = null;
        // removed other assertion

        a = new String[0];
        // removed other assertion

        a = new String[] { "2" };
        // removed other assertion

        a = new String[] { "2", "2" };
        assertArrayEquals(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.removeAllOccurrences(a, "2"));
    }

@Test
    public void testRemoveAllObjectOccurrences_5_oe() {
        String[] a = null;
        // removed other assertion

        a = new String[0];
        // removed other assertion

        a = new String[] { "2" };
        // removed other assertion

        a = new String[] { "2", "2" };
        // removed other assertion

        a = new String[] { "1", "2", "2", "3", "2" };
        assertArrayEquals(new String[]{"1", "3"}, ArrayUtils.removeAllOccurrences(a, "2"));
    }

@Test
    public void testRemoveAllObjectOccurrences_6_oe() {
        String[] a = null;
        // removed other assertion

        a = new String[0];
        // removed other assertion

        a = new String[] { "2" };
        // removed other assertion

        a = new String[] { "2", "2" };
        // removed other assertion

        a = new String[] { "1", "2", "2", "3", "2" };
        // removed other assertion

        a = new String[] { "1", "2", "2", "3", "2" };
        assertArrayEquals(new String[]{"1", "2", "2", "3", "2"}, ArrayUtils.removeAllOccurrences(a, "4"));
    }

@Test
    public void testRemoveAllShortOccurences_1_oe() {
        short[] a = null;
        assertNull(ArrayUtils.removeAllOccurences(a, (short) 2));
    }

@Test
    public void testRemoveAllShortOccurences_2_oe() {
        short[] a = null;
        // removed other assertion

        a = new short[0];
        assertArrayEquals(ArrayUtils.EMPTY_SHORT_ARRAY, ArrayUtils.removeAllOccurences(a, (short) 2));
    }

@Test
    public void testRemoveAllShortOccurences_3_oe() {
        short[] a = null;
        // removed other assertion

        a = new short[0];
        // removed other assertion

        a = new short[] { 2 };
        assertArrayEquals(ArrayUtils.EMPTY_SHORT_ARRAY, ArrayUtils.removeAllOccurences(a, (short) 2));
    }

@Test
    public void testRemoveAllShortOccurences_4_oe() {
        short[] a = null;
        // removed other assertion

        a = new short[0];
        // removed other assertion

        a = new short[] { 2 };
        // removed other assertion

        a = new short[] { 2, 2 };
        assertArrayEquals(ArrayUtils.EMPTY_SHORT_ARRAY, ArrayUtils.removeAllOccurences(a, (short) 2));
    }

@Test
    public void testRemoveAllShortOccurences_5_oe() {
        short[] a = null;
        // removed other assertion

        a = new short[0];
        // removed other assertion

        a = new short[] { 2 };
        // removed other assertion

        a = new short[] { 2, 2 };
        // removed other assertion

        a = new short[] { 1, 2, 2, 3, 2 };
        assertArrayEquals(new short[]{1, 3}, ArrayUtils.removeAllOccurences(a, (short) 2));
    }

@Test
    public void testRemoveAllShortOccurences_6_oe() {
        short[] a = null;
        // removed other assertion

        a = new short[0];
        // removed other assertion

        a = new short[] { 2 };
        // removed other assertion

        a = new short[] { 2, 2 };
        // removed other assertion

        a = new short[] { 1, 2, 2, 3, 2 };
        // removed other assertion

        a = new short[] { 1, 2, 2, 3, 2 };
        assertArrayEquals(new short[]{1, 2, 2, 3, 2}, ArrayUtils.removeAllOccurences(a, (short) 4));
    }

@Test
    public void testRemoveAllShortOccurrences_1_oe() {
        short[] a = null;
        assertNull(ArrayUtils.removeAllOccurrences(a, (short) 2));
    }

@Test
    public void testRemoveAllShortOccurrences_2_oe() {
        short[] a = null;
        // removed other assertion

        a = new short[0];
        assertArrayEquals(ArrayUtils.EMPTY_SHORT_ARRAY, ArrayUtils.removeAllOccurrences(a, (short) 2));
    }

@Test
    public void testRemoveAllShortOccurrences_3_oe() {
        short[] a = null;
        // removed other assertion

        a = new short[0];
        // removed other assertion

        a = new short[] { 2 };
        assertArrayEquals(ArrayUtils.EMPTY_SHORT_ARRAY, ArrayUtils.removeAllOccurrences(a, (short) 2));
    }

@Test
    public void testRemoveAllShortOccurrences_4_oe() {
        short[] a = null;
        // removed other assertion

        a = new short[0];
        // removed other assertion

        a = new short[] { 2 };
        // removed other assertion

        a = new short[] { 2, 2 };
        assertArrayEquals(ArrayUtils.EMPTY_SHORT_ARRAY, ArrayUtils.removeAllOccurrences(a, (short) 2));
    }

@Test
    public void testRemoveAllShortOccurrences_5_oe() {
        short[] a = null;
        // removed other assertion

        a = new short[0];
        // removed other assertion

        a = new short[] { 2 };
        // removed other assertion

        a = new short[] { 2, 2 };
        // removed other assertion

        a = new short[] { 1, 2, 2, 3, 2 };
        assertArrayEquals(new short[]{1, 3}, ArrayUtils.removeAllOccurrences(a, (short) 2));
    }

@Test
    public void testRemoveAllShortOccurrences_6_oe() {
        short[] a = null;
        // removed other assertion

        a = new short[0];
        // removed other assertion

        a = new short[] { 2 };
        // removed other assertion

        a = new short[] { 2, 2 };
        // removed other assertion

        a = new short[] { 1, 2, 2, 3, 2 };
        // removed other assertion

        a = new short[] { 1, 2, 2, 3, 2 };
        assertArrayEquals(new short[]{1, 2, 2, 3, 2}, ArrayUtils.removeAllOccurrences(a, (short) 4));
    }

@Test
    public void testRemoveBooleanArray_1_oe() {
        boolean[] array;
        array = ArrayUtils.remove(new boolean[] {true}, 0);
        assertArrayEquals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, array);
    }

@Test
    public void testRemoveBooleanArray_2_oe() {
        boolean[] array;
        array = ArrayUtils.remove(new boolean[] {true}, 0);
        // removed other assertion
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveBooleanArray_3_oe() {
        boolean[] array;
        array = ArrayUtils.remove(new boolean[] {true}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new boolean[] {true, false}, 0);
        assertArrayEquals(new boolean[]{false}, array);
    }

@Test
    public void testRemoveBooleanArray_4_oe() {
        boolean[] array;
        array = ArrayUtils.remove(new boolean[] {true}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new boolean[] {true, false}, 0);
        // removed other assertion
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveBooleanArray_5_oe() {
        boolean[] array;
        array = ArrayUtils.remove(new boolean[] {true}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new boolean[] {true, false}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new boolean[] {true, false}, 1);
        assertArrayEquals(new boolean[]{true}, array);
    }

@Test
    public void testRemoveBooleanArray_6_oe() {
        boolean[] array;
        array = ArrayUtils.remove(new boolean[] {true}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new boolean[] {true, false}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new boolean[] {true, false}, 1);
        // removed other assertion
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveBooleanArray_7_oe() {
        boolean[] array;
        array = ArrayUtils.remove(new boolean[] {true}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new boolean[] {true, false}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new boolean[] {true, false}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new boolean[] {true, false, true}, 1);
        assertArrayEquals(new boolean[]{true, true}, array);
    }

@Test
    public void testRemoveBooleanArray_8_oe() {
        boolean[] array;
        array = ArrayUtils.remove(new boolean[] {true}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new boolean[] {true, false}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new boolean[] {true, false}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new boolean[] {true, false, true}, 1);
        // removed other assertion
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveBooleanArray_9_oe() {
        boolean[] array;
        array = ArrayUtils.remove(new boolean[] {true}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new boolean[] {true, false}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new boolean[] {true, false}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new boolean[] {true, false, true}, 1);
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove(new boolean[] {true, false}, -1));
    }

@Test
    public void testRemoveBooleanArray_10_oe() {
        boolean[] array;
        array = ArrayUtils.remove(new boolean[] {true}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new boolean[] {true, false}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new boolean[] {true, false}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new boolean[] {true, false, true}, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove(new boolean[] {true, false}, 2));
    }

@Test
    public void testRemoveBooleanArray_11_oe() {
        boolean[] array;
        array = ArrayUtils.remove(new boolean[] {true}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new boolean[] {true, false}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new boolean[] {true, false}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new boolean[] {true, false, true}, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove((boolean[]) null, 0));
    }

@Test
    public void testRemoveByteArray_1_oe() {
        byte[] array;
        array = ArrayUtils.remove(new byte[] {1}, 0);
        assertArrayEquals(ArrayUtils.EMPTY_BYTE_ARRAY, array);
    }

@Test
    public void testRemoveByteArray_2_oe() {
        byte[] array;
        array = ArrayUtils.remove(new byte[] {1}, 0);
        // removed other assertion
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveByteArray_3_oe() {
        byte[] array;
        array = ArrayUtils.remove(new byte[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new byte[] {1, 2}, 0);
        assertArrayEquals(new byte[]{2}, array);
    }

@Test
    public void testRemoveByteArray_4_oe() {
        byte[] array;
        array = ArrayUtils.remove(new byte[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new byte[] {1, 2}, 0);
        // removed other assertion
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveByteArray_5_oe() {
        byte[] array;
        array = ArrayUtils.remove(new byte[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new byte[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new byte[] {1, 2}, 1);
        assertArrayEquals(new byte[]{1}, array);
    }

@Test
    public void testRemoveByteArray_6_oe() {
        byte[] array;
        array = ArrayUtils.remove(new byte[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new byte[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new byte[] {1, 2}, 1);
        // removed other assertion
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveByteArray_7_oe() {
        byte[] array;
        array = ArrayUtils.remove(new byte[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new byte[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new byte[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new byte[] {1, 2, 1}, 1);
        assertArrayEquals(new byte[]{1, 1}, array);
    }

@Test
    public void testRemoveByteArray_8_oe() {
        byte[] array;
        array = ArrayUtils.remove(new byte[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new byte[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new byte[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new byte[] {1, 2, 1}, 1);
        // removed other assertion
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveByteArray_9_oe() {
        byte[] array;
        array = ArrayUtils.remove(new byte[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new byte[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new byte[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new byte[] {1, 2, 1}, 1);
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove(new byte[] {1, 2}, -1));
    }

@Test
    public void testRemoveByteArray_10_oe() {
        byte[] array;
        array = ArrayUtils.remove(new byte[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new byte[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new byte[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new byte[] {1, 2, 1}, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove(new byte[] {1, 2}, 2));
    }

@Test
    public void testRemoveByteArray_11_oe() {
        byte[] array;
        array = ArrayUtils.remove(new byte[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new byte[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new byte[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new byte[] {1, 2, 1}, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove((byte[]) null, 0));
    }

@Test
    public void testRemoveCharArray_1_oe() {
        char[] array;
        array = ArrayUtils.remove(new char[] {'a'}, 0);
        assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, array);
    }

@Test
    public void testRemoveCharArray_2_oe() {
        char[] array;
        array = ArrayUtils.remove(new char[] {'a'}, 0);
        // removed other assertion
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveCharArray_3_oe() {
        char[] array;
        array = ArrayUtils.remove(new char[] {'a'}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new char[] {'a', 'b'}, 0);
        assertArrayEquals(new char[]{'b'}, array);
    }

@Test
    public void testRemoveCharArray_4_oe() {
        char[] array;
        array = ArrayUtils.remove(new char[] {'a'}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new char[] {'a', 'b'}, 0);
        // removed other assertion
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveCharArray_5_oe() {
        char[] array;
        array = ArrayUtils.remove(new char[] {'a'}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new char[] {'a', 'b'}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new char[] {'a', 'b'}, 1);
        assertArrayEquals(new char[]{'a'}, array);
    }

@Test
    public void testRemoveCharArray_6_oe() {
        char[] array;
        array = ArrayUtils.remove(new char[] {'a'}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new char[] {'a', 'b'}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new char[] {'a', 'b'}, 1);
        // removed other assertion
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveCharArray_7_oe() {
        char[] array;
        array = ArrayUtils.remove(new char[] {'a'}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new char[] {'a', 'b'}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new char[] {'a', 'b'}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new char[] {'a', 'b', 'c'}, 1);
        assertArrayEquals(new char[]{'a', 'c'}, array);
    }

@Test
    public void testRemoveCharArray_8_oe() {
        char[] array;
        array = ArrayUtils.remove(new char[] {'a'}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new char[] {'a', 'b'}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new char[] {'a', 'b'}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new char[] {'a', 'b', 'c'}, 1);
        // removed other assertion
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveCharArray_9_oe() {
        char[] array;
        array = ArrayUtils.remove(new char[] {'a'}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new char[] {'a', 'b'}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new char[] {'a', 'b'}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new char[] {'a', 'b', 'c'}, 1);
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove(new char[] {'a', 'b'}, -1));
    }

@Test
    public void testRemoveCharArray_10_oe() {
        char[] array;
        array = ArrayUtils.remove(new char[] {'a'}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new char[] {'a', 'b'}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new char[] {'a', 'b'}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new char[] {'a', 'b', 'c'}, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove(new char[] {'a', 'b'}, 2));
    }

@Test
    public void testRemoveCharArray_11_oe() {
        char[] array;
        array = ArrayUtils.remove(new char[] {'a'}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new char[] {'a', 'b'}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new char[] {'a', 'b'}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new char[] {'a', 'b', 'c'}, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove((char[]) null, 0));
    }

@Test
    public void testRemoveDoubleArray_1_oe() {
        double[] array;
        array = ArrayUtils.remove(new double[] {1}, 0);
        assertArrayEquals(ArrayUtils.EMPTY_DOUBLE_ARRAY, array);
    }

@Test
    public void testRemoveDoubleArray_2_oe() {
        double[] array;
        array = ArrayUtils.remove(new double[] {1}, 0);
        // removed other assertion
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveDoubleArray_3_oe() {
        double[] array;
        array = ArrayUtils.remove(new double[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new double[] {1, 2}, 0);
        assertArrayEquals(new double[]{2}, array);
    }

@Test
    public void testRemoveDoubleArray_4_oe() {
        double[] array;
        array = ArrayUtils.remove(new double[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new double[] {1, 2}, 0);
        // removed other assertion
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveDoubleArray_5_oe() {
        double[] array;
        array = ArrayUtils.remove(new double[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new double[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new double[] {1, 2}, 1);
        assertArrayEquals(new double[]{1}, array);
    }

@Test
    public void testRemoveDoubleArray_6_oe() {
        double[] array;
        array = ArrayUtils.remove(new double[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new double[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new double[] {1, 2}, 1);
        // removed other assertion
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveDoubleArray_7_oe() {
        double[] array;
        array = ArrayUtils.remove(new double[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new double[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new double[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new double[] {1, 2, 1}, 1);
        assertArrayEquals(new double[]{1, 1}, array);
    }

@Test
    public void testRemoveDoubleArray_8_oe() {
        double[] array;
        array = ArrayUtils.remove(new double[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new double[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new double[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new double[] {1, 2, 1}, 1);
        // removed other assertion
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveDoubleArray_9_oe() {
        double[] array;
        array = ArrayUtils.remove(new double[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new double[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new double[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new double[] {1, 2, 1}, 1);
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove(new double[] {1, 2}, -1));
    }

@Test
    public void testRemoveDoubleArray_10_oe() {
        double[] array;
        array = ArrayUtils.remove(new double[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new double[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new double[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new double[] {1, 2, 1}, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove(new double[] {1, 2}, 2));
    }

@Test
    public void testRemoveDoubleArray_11_oe() {
        double[] array;
        array = ArrayUtils.remove(new double[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new double[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new double[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new double[] {1, 2, 1}, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove((double[]) null, 0));
    }

@Test
    public void testRemoveElementBooleanArray_1_oe() {
        boolean[] array;
        array = ArrayUtils.removeElement(null, true);
        assertNull(array);
    }

@Test
    public void testRemoveElementBooleanArray_2_oe() {
        boolean[] array;
        array = ArrayUtils.removeElement(null, true);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);
        assertArrayEquals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, array);
    }

@Test
    public void testRemoveElementBooleanArray_3_oe() {
        boolean[] array;
        array = ArrayUtils.removeElement(null, true);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);
        // removed other assertion
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveElementBooleanArray_4_oe() {
        boolean[] array;
        array = ArrayUtils.removeElement(null, true);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new boolean[] {true}, true);
        assertArrayEquals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, array);
    }

@Test
    public void testRemoveElementBooleanArray_5_oe() {
        boolean[] array;
        array = ArrayUtils.removeElement(null, true);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new boolean[] {true}, true);
        // removed other assertion
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveElementBooleanArray_6_oe() {
        boolean[] array;
        array = ArrayUtils.removeElement(null, true);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new boolean[] {true}, true);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new boolean[] {true, false}, true);
        assertArrayEquals(new boolean[]{false}, array);
    }

@Test
    public void testRemoveElementBooleanArray_7_oe() {
        boolean[] array;
        array = ArrayUtils.removeElement(null, true);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new boolean[] {true}, true);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new boolean[] {true, false}, true);
        // removed other assertion
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveElementBooleanArray_8_oe() {
        boolean[] array;
        array = ArrayUtils.removeElement(null, true);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new boolean[] {true}, true);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new boolean[] {true, false}, true);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new boolean[] {true, false, true}, true);
        assertArrayEquals(new boolean[]{false, true}, array);
    }

@Test
    public void testRemoveElementBooleanArray_9_oe() {
        boolean[] array;
        array = ArrayUtils.removeElement(null, true);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new boolean[] {true}, true);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new boolean[] {true, false}, true);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new boolean[] {true, false, true}, true);
        // removed other assertion
        assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveElementByteArray_1_oe() {
        byte[] array;
        array = ArrayUtils.removeElement((byte[]) null, (byte) 1);
        assertNull(array);
    }

@Test
    public void testRemoveElementByteArray_2_oe() {
        byte[] array;
        array = ArrayUtils.removeElement((byte[]) null, (byte) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);
        assertArrayEquals(ArrayUtils.EMPTY_BYTE_ARRAY, array);
    }

@Test
    public void testRemoveElementByteArray_3_oe() {
        byte[] array;
        array = ArrayUtils.removeElement((byte[]) null, (byte) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);
        // removed other assertion
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveElementByteArray_4_oe() {
        byte[] array;
        array = ArrayUtils.removeElement((byte[]) null, (byte) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new byte[] {1}, (byte) 1);
        assertArrayEquals(ArrayUtils.EMPTY_BYTE_ARRAY, array);
    }

@Test
    public void testRemoveElementByteArray_5_oe() {
        byte[] array;
        array = ArrayUtils.removeElement((byte[]) null, (byte) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new byte[] {1}, (byte) 1);
        // removed other assertion
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveElementByteArray_6_oe() {
        byte[] array;
        array = ArrayUtils.removeElement((byte[]) null, (byte) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new byte[] {1}, (byte) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new byte[] {1, 2}, (byte) 1);
        assertArrayEquals(new byte[]{2}, array);
    }

@Test
    public void testRemoveElementByteArray_7_oe() {
        byte[] array;
        array = ArrayUtils.removeElement((byte[]) null, (byte) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new byte[] {1}, (byte) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new byte[] {1, 2}, (byte) 1);
        // removed other assertion
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveElementByteArray_8_oe() {
        byte[] array;
        array = ArrayUtils.removeElement((byte[]) null, (byte) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new byte[] {1}, (byte) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new byte[] {1, 2}, (byte) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new byte[] {1, 2, 1}, (byte) 1);
        assertArrayEquals(new byte[]{2, 1}, array);
    }

@Test
    public void testRemoveElementByteArray_9_oe() {
        byte[] array;
        array = ArrayUtils.removeElement((byte[]) null, (byte) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new byte[] {1}, (byte) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new byte[] {1, 2}, (byte) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new byte[] {1, 2, 1}, (byte) 1);
        // removed other assertion
        assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveElementCharArray_1_oe() {
        char[] array;
        array = ArrayUtils.removeElement((char[]) null, 'a');
        assertNull(array);
    }

@Test
    public void testRemoveElementCharArray_2_oe() {
        char[] array;
        array = ArrayUtils.removeElement((char[]) null, 'a');
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');
        assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, array);
    }

@Test
    public void testRemoveElementCharArray_3_oe() {
        char[] array;
        array = ArrayUtils.removeElement((char[]) null, 'a');
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');
        // removed other assertion
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveElementCharArray_4_oe() {
        char[] array;
        array = ArrayUtils.removeElement((char[]) null, 'a');
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new char[] {'a'}, 'a');
        assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, array);
    }

@Test
    public void testRemoveElementCharArray_5_oe() {
        char[] array;
        array = ArrayUtils.removeElement((char[]) null, 'a');
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new char[] {'a'}, 'a');
        // removed other assertion
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveElementCharArray_6_oe() {
        char[] array;
        array = ArrayUtils.removeElement((char[]) null, 'a');
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new char[] {'a'}, 'a');
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new char[] {'a', 'b'}, 'a');
        assertArrayEquals(new char[]{'b'}, array);
    }

@Test
    public void testRemoveElementCharArray_7_oe() {
        char[] array;
        array = ArrayUtils.removeElement((char[]) null, 'a');
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new char[] {'a'}, 'a');
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new char[] {'a', 'b'}, 'a');
        // removed other assertion
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveElementCharArray_8_oe() {
        char[] array;
        array = ArrayUtils.removeElement((char[]) null, 'a');
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new char[] {'a'}, 'a');
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new char[] {'a', 'b'}, 'a');
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new char[] {'a', 'b', 'a'}, 'a');
        assertArrayEquals(new char[]{'b', 'a'}, array);
    }

@Test
    public void testRemoveElementCharArray_9_oe() {
        char[] array;
        array = ArrayUtils.removeElement((char[]) null, 'a');
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new char[] {'a'}, 'a');
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new char[] {'a', 'b'}, 'a');
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new char[] {'a', 'b', 'a'}, 'a');
        // removed other assertion
        assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

@Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_1_oe() {
        double[] array;
        array = ArrayUtils.removeElement(null, (double) 1);
        assertNull(array);
    }

@Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_2_oe() {
        double[] array;
        array = ArrayUtils.removeElement(null, (double) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);
        assertArrayEquals(ArrayUtils.EMPTY_DOUBLE_ARRAY, array);
    }

@Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_3_oe() {
        double[] array;
        array = ArrayUtils.removeElement(null, (double) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);
        // removed other assertion
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

@Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_4_oe() {
        double[] array;
        array = ArrayUtils.removeElement(null, (double) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new double[] {1}, (double) 1);
        assertArrayEquals(ArrayUtils.EMPTY_DOUBLE_ARRAY, array);
    }

@Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_5_oe() {
        double[] array;
        array = ArrayUtils.removeElement(null, (double) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new double[] {1}, (double) 1);
        // removed other assertion
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

@Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_6_oe() {
        double[] array;
        array = ArrayUtils.removeElement(null, (double) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new double[] {1}, (double) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new double[] {1, 2}, (double) 1);
        assertArrayEquals(new double[]{2}, array);
    }

@Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_7_oe() {
        double[] array;
        array = ArrayUtils.removeElement(null, (double) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new double[] {1}, (double) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new double[] {1, 2}, (double) 1);
        // removed other assertion
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

@Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_8_oe() {
        double[] array;
        array = ArrayUtils.removeElement(null, (double) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new double[] {1}, (double) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new double[] {1, 2}, (double) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new double[] {1, 2, 1}, (double) 1);
        assertArrayEquals(new double[]{2, 1}, array);
    }

@Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray_9_oe() {
        double[] array;
        array = ArrayUtils.removeElement(null, (double) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new double[] {1}, (double) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new double[] {1, 2}, (double) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new double[] {1, 2, 1}, (double) 1);
        // removed other assertion
        assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

@Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_1_oe() {
        float[] array;
        array = ArrayUtils.removeElement((float[]) null, (float) 1);
        assertNull(array);
    }

@Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_2_oe() {
        float[] array;
        array = ArrayUtils.removeElement((float[]) null, (float) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);
        assertArrayEquals(ArrayUtils.EMPTY_FLOAT_ARRAY, array);
    }

@Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_3_oe() {
        float[] array;
        array = ArrayUtils.removeElement((float[]) null, (float) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);
        // removed other assertion
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

@Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_4_oe() {
        float[] array;
        array = ArrayUtils.removeElement((float[]) null, (float) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new float[] {1}, (float) 1);
        assertArrayEquals(ArrayUtils.EMPTY_FLOAT_ARRAY, array);
    }

@Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_5_oe() {
        float[] array;
        array = ArrayUtils.removeElement((float[]) null, (float) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new float[] {1}, (float) 1);
        // removed other assertion
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

@Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_6_oe() {
        float[] array;
        array = ArrayUtils.removeElement((float[]) null, (float) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new float[] {1}, (float) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new float[] {1, 2}, (float) 1);
        assertArrayEquals(new float[]{2}, array);
    }

@Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_7_oe() {
        float[] array;
        array = ArrayUtils.removeElement((float[]) null, (float) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new float[] {1}, (float) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new float[] {1, 2}, (float) 1);
        // removed other assertion
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

@Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_8_oe() {
        float[] array;
        array = ArrayUtils.removeElement((float[]) null, (float) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new float[] {1}, (float) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new float[] {1, 2}, (float) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new float[] {1, 2, 1}, (float) 1);
        assertArrayEquals(new float[]{2, 1}, array);
    }

@Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray_9_oe() {
        float[] array;
        array = ArrayUtils.removeElement((float[]) null, (float) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new float[] {1}, (float) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new float[] {1, 2}, (float) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new float[] {1, 2, 1}, (float) 1);
        // removed other assertion
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveElementIntArray_1_oe() {
        int[] array;
        array = ArrayUtils.removeElement((int[]) null, 1);
        assertNull(array);
    }

@Test
    public void testRemoveElementIntArray_2_oe() {
        int[] array;
        array = ArrayUtils.removeElement((int[]) null, 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_INT_ARRAY, 1);
        assertArrayEquals(ArrayUtils.EMPTY_INT_ARRAY, array);
    }

@Test
    public void testRemoveElementIntArray_3_oe() {
        int[] array;
        array = ArrayUtils.removeElement((int[]) null, 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_INT_ARRAY, 1);
        // removed other assertion
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveElementIntArray_4_oe() {
        int[] array;
        array = ArrayUtils.removeElement((int[]) null, 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_INT_ARRAY, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new int[] {1}, 1);
        assertArrayEquals(ArrayUtils.EMPTY_INT_ARRAY, array);
    }

@Test
    public void testRemoveElementIntArray_5_oe() {
        int[] array;
        array = ArrayUtils.removeElement((int[]) null, 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_INT_ARRAY, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new int[] {1}, 1);
        // removed other assertion
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveElementIntArray_6_oe() {
        int[] array;
        array = ArrayUtils.removeElement((int[]) null, 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_INT_ARRAY, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new int[] {1}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new int[] {1, 2}, 1);
        assertArrayEquals(new int[]{2}, array);
    }

@Test
    public void testRemoveElementIntArray_7_oe() {
        int[] array;
        array = ArrayUtils.removeElement((int[]) null, 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_INT_ARRAY, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new int[] {1}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new int[] {1, 2}, 1);
        // removed other assertion
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveElementIntArray_8_oe() {
        int[] array;
        array = ArrayUtils.removeElement((int[]) null, 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_INT_ARRAY, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new int[] {1}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new int[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new int[] {1, 2, 1}, 1);
        assertArrayEquals(new int[]{2, 1}, array);
    }

@Test
    public void testRemoveElementIntArray_9_oe() {
        int[] array;
        array = ArrayUtils.removeElement((int[]) null, 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_INT_ARRAY, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new int[] {1}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new int[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new int[] {1, 2, 1}, 1);
        // removed other assertion
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

@Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_1_oe() {
        long[] array;
        array = ArrayUtils.removeElement((long[]) null, 1L);
        assertNull(array);
    }

@Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_2_oe() {
        long[] array;
        array = ArrayUtils.removeElement((long[]) null, 1L);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_LONG_ARRAY, 1L);
        assertArrayEquals(ArrayUtils.EMPTY_LONG_ARRAY, array);
    }

@Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_3_oe() {
        long[] array;
        array = ArrayUtils.removeElement((long[]) null, 1L);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_LONG_ARRAY, 1L);
        // removed other assertion
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

@Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_4_oe() {
        long[] array;
        array = ArrayUtils.removeElement((long[]) null, 1L);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_LONG_ARRAY, 1L);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new long[] {1}, 1L);
        assertArrayEquals(ArrayUtils.EMPTY_LONG_ARRAY, array);
    }

@Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_5_oe() {
        long[] array;
        array = ArrayUtils.removeElement((long[]) null, 1L);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_LONG_ARRAY, 1L);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new long[] {1}, 1L);
        // removed other assertion
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

@Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_6_oe() {
        long[] array;
        array = ArrayUtils.removeElement((long[]) null, 1L);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_LONG_ARRAY, 1L);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new long[] {1}, 1L);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new long[] {1, 2}, 1L);
        assertArrayEquals(new long[]{2}, array);
    }

@Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_7_oe() {
        long[] array;
        array = ArrayUtils.removeElement((long[]) null, 1L);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_LONG_ARRAY, 1L);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new long[] {1}, 1L);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new long[] {1, 2}, 1L);
        // removed other assertion
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

@Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_8_oe() {
        long[] array;
        array = ArrayUtils.removeElement((long[]) null, 1L);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_LONG_ARRAY, 1L);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new long[] {1}, 1L);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new long[] {1, 2}, 1L);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new long[] {1, 2, 1}, 1L);
        assertArrayEquals(new long[]{2, 1}, array);
    }

@Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray_9_oe() {
        long[] array;
        array = ArrayUtils.removeElement((long[]) null, 1L);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_LONG_ARRAY, 1L);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new long[] {1}, 1L);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new long[] {1, 2}, 1L);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new long[] {1, 2, 1}, 1L);
        // removed other assertion
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveElementObjectArray_1_oe() {
        Object[] array;
        array = ArrayUtils.removeElement(null, "a");
        assertNull(array);
    }

@Test
    public void testRemoveElementObjectArray_2_oe() {
        Object[] array;
        array = ArrayUtils.removeElement(null, "a");
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");
        assertArrayEquals(ArrayUtils.EMPTY_OBJECT_ARRAY, array);
    }

@Test
    public void testRemoveElementObjectArray_3_oe() {
        Object[] array;
        array = ArrayUtils.removeElement(null, "a");
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");
        // removed other assertion
        assertEquals(Object.class, array.getClass().getComponentType());
    }

@Test
    public void testRemoveElementObjectArray_4_oe() {
        Object[] array;
        array = ArrayUtils.removeElement(null, "a");
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new Object[] {"a"}, "a");
        assertArrayEquals(ArrayUtils.EMPTY_OBJECT_ARRAY, array);
    }

@Test
    public void testRemoveElementObjectArray_5_oe() {
        Object[] array;
        array = ArrayUtils.removeElement(null, "a");
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new Object[] {"a"}, "a");
        // removed other assertion
        assertEquals(Object.class, array.getClass().getComponentType());
    }

@Test
    public void testRemoveElementObjectArray_6_oe() {
        Object[] array;
        array = ArrayUtils.removeElement(null, "a");
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new Object[] {"a"}, "a");
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new Object[] {"a", "b"}, "a");
        assertArrayEquals(new Object[]{"b"}, array);
    }

@Test
    public void testRemoveElementObjectArray_7_oe() {
        Object[] array;
        array = ArrayUtils.removeElement(null, "a");
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new Object[] {"a"}, "a");
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new Object[] {"a", "b"}, "a");
        // removed other assertion
        assertEquals(Object.class, array.getClass().getComponentType());
    }

@Test
    public void testRemoveElementObjectArray_8_oe() {
        Object[] array;
        array = ArrayUtils.removeElement(null, "a");
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new Object[] {"a"}, "a");
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new Object[] {"a", "b"}, "a");
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new Object[] {"a", "b", "a"}, "a");
        assertArrayEquals(new Object[]{"b", "a"}, array);
    }

@Test
    public void testRemoveElementObjectArray_9_oe() {
        Object[] array;
        array = ArrayUtils.removeElement(null, "a");
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new Object[] {"a"}, "a");
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new Object[] {"a", "b"}, "a");
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new Object[] {"a", "b", "a"}, "a");
        // removed other assertion
        assertEquals(Object.class, array.getClass().getComponentType());
    }

@Test
    public void testRemoveElementShortArray_1_oe() {
        short[] array;
        array = ArrayUtils.removeElement((short[]) null, (short) 1);
        assertNull(array);
    }

@Test
    public void testRemoveElementShortArray_2_oe() {
        short[] array;
        array = ArrayUtils.removeElement((short[]) null, (short) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);
        assertArrayEquals(ArrayUtils.EMPTY_SHORT_ARRAY, array);
    }

@Test
    public void testRemoveElementShortArray_3_oe() {
        short[] array;
        array = ArrayUtils.removeElement((short[]) null, (short) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);
        // removed other assertion
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveElementShortArray_4_oe() {
        short[] array;
        array = ArrayUtils.removeElement((short[]) null, (short) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new short[] {1}, (short) 1);
        assertArrayEquals(ArrayUtils.EMPTY_SHORT_ARRAY, array);
    }

@Test
    public void testRemoveElementShortArray_5_oe() {
        short[] array;
        array = ArrayUtils.removeElement((short[]) null, (short) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new short[] {1}, (short) 1);
        // removed other assertion
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveElementShortArray_6_oe() {
        short[] array;
        array = ArrayUtils.removeElement((short[]) null, (short) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new short[] {1}, (short) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new short[] {1, 2}, (short) 1);
        assertArrayEquals(new short[]{2}, array);
    }

@Test
    public void testRemoveElementShortArray_7_oe() {
        short[] array;
        array = ArrayUtils.removeElement((short[]) null, (short) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new short[] {1}, (short) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new short[] {1, 2}, (short) 1);
        // removed other assertion
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveElementShortArray_8_oe() {
        short[] array;
        array = ArrayUtils.removeElement((short[]) null, (short) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new short[] {1}, (short) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new short[] {1, 2}, (short) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new short[] {1, 2, 1}, (short) 1);
        assertArrayEquals(new short[]{2, 1}, array);
    }

@Test
    public void testRemoveElementShortArray_9_oe() {
        short[] array;
        array = ArrayUtils.removeElement((short[]) null, (short) 1);
        // removed other assertion
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new short[] {1}, (short) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new short[] {1, 2}, (short) 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.removeElement(new short[] {1, 2, 1}, (short) 1);
        // removed other assertion
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveFloatArray_1_oe() {
        float[] array;
        array = ArrayUtils.remove(new float[] {1}, 0);
        assertArrayEquals(ArrayUtils.EMPTY_FLOAT_ARRAY, array);
    }

@Test
    public void testRemoveFloatArray_2_oe() {
        float[] array;
        array = ArrayUtils.remove(new float[] {1}, 0);
        // removed other assertion
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveFloatArray_3_oe() {
        float[] array;
        array = ArrayUtils.remove(new float[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new float[] {1, 2}, 0);
        assertArrayEquals(new float[]{2}, array);
    }

@Test
    public void testRemoveFloatArray_4_oe() {
        float[] array;
        array = ArrayUtils.remove(new float[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new float[] {1, 2}, 0);
        // removed other assertion
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveFloatArray_5_oe() {
        float[] array;
        array = ArrayUtils.remove(new float[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new float[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new float[] {1, 2}, 1);
        assertArrayEquals(new float[]{1}, array);
    }

@Test
    public void testRemoveFloatArray_6_oe() {
        float[] array;
        array = ArrayUtils.remove(new float[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new float[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new float[] {1, 2}, 1);
        // removed other assertion
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveFloatArray_7_oe() {
        float[] array;
        array = ArrayUtils.remove(new float[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new float[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new float[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new float[] {1, 2, 1}, 1);
        assertArrayEquals(new float[]{1, 1}, array);
    }

@Test
    public void testRemoveFloatArray_8_oe() {
        float[] array;
        array = ArrayUtils.remove(new float[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new float[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new float[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new float[] {1, 2, 1}, 1);
        // removed other assertion
        assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveFloatArray_9_oe() {
        float[] array;
        array = ArrayUtils.remove(new float[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new float[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new float[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new float[] {1, 2, 1}, 1);
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove(new float[] {1, 2}, -1));
    }

@Test
    public void testRemoveFloatArray_10_oe() {
        float[] array;
        array = ArrayUtils.remove(new float[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new float[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new float[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new float[] {1, 2, 1}, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove(new float[] {1, 2}, 2));
    }

@Test
    public void testRemoveFloatArray_11_oe() {
        float[] array;
        array = ArrayUtils.remove(new float[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new float[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new float[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new float[] {1, 2, 1}, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove((float[]) null, 0));
    }

@Test
    public void testRemoveIntArray_1_oe() {
        int[] array;
        array = ArrayUtils.remove(new int[] {1}, 0);
        assertArrayEquals(ArrayUtils.EMPTY_INT_ARRAY, array);
    }

@Test
    public void testRemoveIntArray_2_oe() {
        int[] array;
        array = ArrayUtils.remove(new int[] {1}, 0);
        // removed other assertion
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveIntArray_3_oe() {
        int[] array;
        array = ArrayUtils.remove(new int[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new int[] {1, 2}, 0);
        assertArrayEquals(new int[]{2}, array);
    }

@Test
    public void testRemoveIntArray_4_oe() {
        int[] array;
        array = ArrayUtils.remove(new int[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new int[] {1, 2}, 0);
        // removed other assertion
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveIntArray_5_oe() {
        int[] array;
        array = ArrayUtils.remove(new int[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new int[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new int[] {1, 2}, 1);
        assertArrayEquals(new int[]{1}, array);
    }

@Test
    public void testRemoveIntArray_6_oe() {
        int[] array;
        array = ArrayUtils.remove(new int[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new int[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new int[] {1, 2}, 1);
        // removed other assertion
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveIntArray_7_oe() {
        int[] array;
        array = ArrayUtils.remove(new int[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new int[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new int[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new int[] {1, 2, 1}, 1);
        assertArrayEquals(new int[]{1, 1}, array);
    }

@Test
    public void testRemoveIntArray_8_oe() {
        int[] array;
        array = ArrayUtils.remove(new int[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new int[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new int[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new int[] {1, 2, 1}, 1);
        // removed other assertion
        assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveIntArray_9_oe() {
        int[] array;
        array = ArrayUtils.remove(new int[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new int[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new int[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new int[] {1, 2, 1}, 1);
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove(new int[] {1, 2}, -1));
    }

@Test
    public void testRemoveIntArray_10_oe() {
        int[] array;
        array = ArrayUtils.remove(new int[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new int[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new int[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new int[] {1, 2, 1}, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove(new int[] {1, 2}, 2));
    }

@Test
    public void testRemoveIntArray_11_oe() {
        int[] array;
        array = ArrayUtils.remove(new int[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new int[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new int[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new int[] {1, 2, 1}, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove((int[]) null, 0));
    }

@Test
    public void testRemoveLongArray_1_oe() {
        long[] array;
        array = ArrayUtils.remove(new long[] {1}, 0);
        assertArrayEquals(ArrayUtils.EMPTY_LONG_ARRAY, array);
    }

@Test
    public void testRemoveLongArray_2_oe() {
        long[] array;
        array = ArrayUtils.remove(new long[] {1}, 0);
        // removed other assertion
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveLongArray_3_oe() {
        long[] array;
        array = ArrayUtils.remove(new long[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new long[] {1, 2}, 0);
        assertArrayEquals(new long[]{2}, array);
    }

@Test
    public void testRemoveLongArray_4_oe() {
        long[] array;
        array = ArrayUtils.remove(new long[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new long[] {1, 2}, 0);
        // removed other assertion
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveLongArray_5_oe() {
        long[] array;
        array = ArrayUtils.remove(new long[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new long[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new long[] {1, 2}, 1);
        assertArrayEquals(new long[]{1}, array);
    }

@Test
    public void testRemoveLongArray_6_oe() {
        long[] array;
        array = ArrayUtils.remove(new long[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new long[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new long[] {1, 2}, 1);
        // removed other assertion
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveLongArray_7_oe() {
        long[] array;
        array = ArrayUtils.remove(new long[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new long[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new long[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new long[] {1, 2, 1}, 1);
        assertArrayEquals(new long[]{1, 1}, array);
    }

@Test
    public void testRemoveLongArray_8_oe() {
        long[] array;
        array = ArrayUtils.remove(new long[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new long[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new long[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new long[] {1, 2, 1}, 1);
        // removed other assertion
        assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveLongArray_9_oe() {
        long[] array;
        array = ArrayUtils.remove(new long[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new long[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new long[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new long[] {1, 2, 1}, 1);
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove(new long[] {1, 2}, -1));
    }

@Test
    public void testRemoveLongArray_10_oe() {
        long[] array;
        array = ArrayUtils.remove(new long[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new long[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new long[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new long[] {1, 2, 1}, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove(new long[] {1, 2}, 2));
    }

@Test
    public void testRemoveLongArray_11_oe() {
        long[] array;
        array = ArrayUtils.remove(new long[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new long[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new long[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new long[] {1, 2, 1}, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove((long[]) null, 0));
    }

@Test
    public void testRemoveNumberArray_1_oe() {
        final Number[] inarray = {Integer.valueOf(1), Long.valueOf(2), Byte.valueOf((byte) 3)};
        assertEquals(3, inarray.length);
    }

@Test
    public void testRemoveNumberArray_2_oe() {
        final Number[] inarray = {Integer.valueOf(1), Long.valueOf(2), Byte.valueOf((byte) 3)};
        // removed other assertion
        Number[] outarray;
        outarray = ArrayUtils.remove(inarray, 1);
        assertEquals(2, outarray.length);
    }

@Test
    public void testRemoveNumberArray_3_oe() {
        final Number[] inarray = {Integer.valueOf(1), Long.valueOf(2), Byte.valueOf((byte) 3)};
        // removed other assertion
        Number[] outarray;
        outarray = ArrayUtils.remove(inarray, 1);
        // removed other assertion
        assertEquals(Number.class, outarray.getClass().getComponentType());
    }

@Test
    public void testRemoveNumberArray_4_oe() {
        final Number[] inarray = {Integer.valueOf(1), Long.valueOf(2), Byte.valueOf((byte) 3)};
        // removed other assertion
        Number[] outarray;
        outarray = ArrayUtils.remove(inarray, 1);
        // removed other assertion
        // removed other assertion
        outarray = ArrayUtils.remove(outarray, 1);
        assertEquals(1, outarray.length);
    }

@Test
    public void testRemoveNumberArray_5_oe() {
        final Number[] inarray = {Integer.valueOf(1), Long.valueOf(2), Byte.valueOf((byte) 3)};
        // removed other assertion
        Number[] outarray;
        outarray = ArrayUtils.remove(inarray, 1);
        // removed other assertion
        // removed other assertion
        outarray = ArrayUtils.remove(outarray, 1);
        // removed other assertion
        assertEquals(Number.class, outarray.getClass().getComponentType());
    }

@Test
    public void testRemoveNumberArray_6_oe() {
        final Number[] inarray = {Integer.valueOf(1), Long.valueOf(2), Byte.valueOf((byte) 3)};
        // removed other assertion
        Number[] outarray;
        outarray = ArrayUtils.remove(inarray, 1);
        // removed other assertion
        // removed other assertion
        outarray = ArrayUtils.remove(outarray, 1);
        // removed other assertion
        // removed other assertion
        outarray = ArrayUtils.remove(outarray, 0);
        assertEquals(0, outarray.length);
    }

@Test
    public void testRemoveNumberArray_7_oe() {
        final Number[] inarray = {Integer.valueOf(1), Long.valueOf(2), Byte.valueOf((byte) 3)};
        // removed other assertion
        Number[] outarray;
        outarray = ArrayUtils.remove(inarray, 1);
        // removed other assertion
        // removed other assertion
        outarray = ArrayUtils.remove(outarray, 1);
        // removed other assertion
        // removed other assertion
        outarray = ArrayUtils.remove(outarray, 0);
        // removed other assertion
        assertEquals(Number.class, outarray.getClass().getComponentType());
    }

@Test
    public void testRemoveObjectArray_1_oe() {
        Object[] array;
        array = ArrayUtils.remove(new Object[] {"a"}, 0);
        assertArrayEquals(ArrayUtils.EMPTY_OBJECT_ARRAY, array);
    }

@Test
    public void testRemoveObjectArray_2_oe() {
        Object[] array;
        array = ArrayUtils.remove(new Object[] {"a"}, 0);
        // removed other assertion
        assertEquals(Object.class, array.getClass().getComponentType());
    }

@Test
    public void testRemoveObjectArray_3_oe() {
        Object[] array;
        array = ArrayUtils.remove(new Object[] {"a"}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new Object[] {"a", "b"}, 0);
        assertArrayEquals(new Object[]{"b"}, array);
    }

@Test
    public void testRemoveObjectArray_4_oe() {
        Object[] array;
        array = ArrayUtils.remove(new Object[] {"a"}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new Object[] {"a", "b"}, 0);
        // removed other assertion
        assertEquals(Object.class, array.getClass().getComponentType());
    }

@Test
    public void testRemoveObjectArray_5_oe() {
        Object[] array;
        array = ArrayUtils.remove(new Object[] {"a"}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new Object[] {"a", "b"}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new Object[] {"a", "b"}, 1);
        assertArrayEquals(new Object[]{"a"}, array);
    }

@Test
    public void testRemoveObjectArray_6_oe() {
        Object[] array;
        array = ArrayUtils.remove(new Object[] {"a"}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new Object[] {"a", "b"}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new Object[] {"a", "b"}, 1);
        // removed other assertion
        assertEquals(Object.class, array.getClass().getComponentType());
    }

@Test
    public void testRemoveObjectArray_7_oe() {
        Object[] array;
        array = ArrayUtils.remove(new Object[] {"a"}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new Object[] {"a", "b"}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new Object[] {"a", "b"}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new Object[] {"a", "b", "c"}, 1);
        assertArrayEquals(new Object[]{"a", "c"}, array);
    }

@Test
    public void testRemoveObjectArray_8_oe() {
        Object[] array;
        array = ArrayUtils.remove(new Object[] {"a"}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new Object[] {"a", "b"}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new Object[] {"a", "b"}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new Object[] {"a", "b", "c"}, 1);
        // removed other assertion
        assertEquals(Object.class, array.getClass().getComponentType());
    }

@Test
    public void testRemoveObjectArray_9_oe() {
        Object[] array;
        array = ArrayUtils.remove(new Object[] {"a"}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new Object[] {"a", "b"}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new Object[] {"a", "b"}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new Object[] {"a", "b", "c"}, 1);
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove(new Object[] {"a", "b"}, -1));
    }

@Test
    public void testRemoveObjectArray_10_oe() {
        Object[] array;
        array = ArrayUtils.remove(new Object[] {"a"}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new Object[] {"a", "b"}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new Object[] {"a", "b"}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new Object[] {"a", "b", "c"}, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove(new Object[] {"a", "b"}, 2));
    }

@Test
    public void testRemoveObjectArray_11_oe() {
        Object[] array;
        array = ArrayUtils.remove(new Object[] {"a"}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new Object[] {"a", "b"}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new Object[] {"a", "b"}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new Object[] {"a", "b", "c"}, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove((Object[]) null, 0));
    }

@Test
    public void testRemoveShortArray_1_oe() {
        short[] array;
        array = ArrayUtils.remove(new short[] {1}, 0);
        assertArrayEquals(ArrayUtils.EMPTY_SHORT_ARRAY, array);
    }

@Test
    public void testRemoveShortArray_2_oe() {
        short[] array;
        array = ArrayUtils.remove(new short[] {1}, 0);
        // removed other assertion
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveShortArray_3_oe() {
        short[] array;
        array = ArrayUtils.remove(new short[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new short[] {1, 2}, 0);
        assertArrayEquals(new short[]{2}, array);
    }

@Test
    public void testRemoveShortArray_4_oe() {
        short[] array;
        array = ArrayUtils.remove(new short[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new short[] {1, 2}, 0);
        // removed other assertion
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveShortArray_5_oe() {
        short[] array;
        array = ArrayUtils.remove(new short[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new short[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new short[] {1, 2}, 1);
        assertArrayEquals(new short[]{1}, array);
    }

@Test
    public void testRemoveShortArray_6_oe() {
        short[] array;
        array = ArrayUtils.remove(new short[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new short[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new short[] {1, 2}, 1);
        // removed other assertion
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveShortArray_7_oe() {
        short[] array;
        array = ArrayUtils.remove(new short[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new short[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new short[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new short[] {1, 2, 1}, 1);
        assertArrayEquals(new short[]{1, 1}, array);
    }

@Test
    public void testRemoveShortArray_8_oe() {
        short[] array;
        array = ArrayUtils.remove(new short[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new short[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new short[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new short[] {1, 2, 1}, 1);
        // removed other assertion
        assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

@Test
    public void testRemoveShortArray_9_oe() {
        short[] array;
        array = ArrayUtils.remove(new short[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new short[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new short[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new short[] {1, 2, 1}, 1);
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove(new short[] {1, 2}, -1));
    }

@Test
    public void testRemoveShortArray_10_oe() {
        short[] array;
        array = ArrayUtils.remove(new short[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new short[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new short[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new short[] {1, 2, 1}, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove(new short[] {1, 2}, 2));
    }

@Test
    public void testRemoveShortArray_11_oe() {
        short[] array;
        array = ArrayUtils.remove(new short[] {1}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new short[] {1, 2}, 0);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new short[] {1, 2}, 1);
        // removed other assertion
        // removed other assertion
        array = ArrayUtils.remove(new short[] {1, 2, 1}, 1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.remove((short[]) null, 0));
    }

}
