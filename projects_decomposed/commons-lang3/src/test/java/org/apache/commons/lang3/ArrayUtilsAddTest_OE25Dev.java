// /*
//  * Licensed to the Apache Software Foundation (ASF) under one or more
//  * contributor license agreements.  See the NOTICE file distributed with
//  * this work for additional information regarding copyright ownership.
//  * The ASF licenses this file to You under the Apache License, Version 2.0
//  * (the "License"); you may not use this file except in compliance with
//  * the License.  You may obtain a copy of the License at
//  *
//  *      http://www.apache.org/licenses/LICENSE-2.0
//  *
//  * Unless required by applicable law or agreed to in writing, software
//  * distributed under the License is distributed on an "AS IS" BASIS,
//  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  * See the License for the specific language governing permissions and
//  * limitations under the License.
//  */

// package org.apache.commons.lang3;

// import static org.junit.jupiter.api.Assertions.assertArrayEquals;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertNotSame;
// import static org.junit.jupiter.api.Assertions.assertNull;
// import static org.junit.jupiter.api.Assertions.assertThrows;

// import org.junit.jupiter.api.Test;

// /**
//  * Tests ArrayUtils add methods.
//  */
// public class ArrayUtilsAddTest_OE25Dev {

//     @Test
//     public void testAddFirstBoolean_1_oe() {
//         boolean[] newArray;
//         newArray = ArrayUtils.addFirst(null, false);
//         assertArrayEquals(new boolean[]{false}, newArray);
//     }

//     @Test
//     public void testAddFirstBoolean_2_oe() {
//         boolean[] newArray;
//         newArray = ArrayUtils.addFirst(null, false);
//         // removed other assertion
//         assertEquals(Boolean.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstBoolean_3_oe() {
//         boolean[] newArray;
//         newArray = ArrayUtils.addFirst(null, false);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst(null, true);
//         assertArrayEquals(new boolean[]{true}, newArray);
//     }

//     @Test
//     public void testAddFirstBoolean_4_oe() {
//         boolean[] newArray;
//         newArray = ArrayUtils.addFirst(null, false);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst(null, true);
//         // removed other assertion
//         assertEquals(Boolean.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstBoolean_5_oe() {
//         boolean[] newArray;
//         newArray = ArrayUtils.addFirst(null, false);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst(null, true);
//         // removed other assertion
//         // removed other assertion
//         final boolean[] array1 = new boolean[]{true, false, true};
//         newArray = ArrayUtils.addFirst(array1, false);
//         assertArrayEquals(new boolean[]{false, true, false, true}, newArray);
//     }

//     @Test
//     public void testAddFirstBoolean_6_oe() {
//         boolean[] newArray;
//         newArray = ArrayUtils.addFirst(null, false);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst(null, true);
//         // removed other assertion
//         // removed other assertion
//         final boolean[] array1 = new boolean[]{true, false, true};
//         newArray = ArrayUtils.addFirst(array1, false);
//         // removed other assertion
//         assertEquals(Boolean.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstByte_1_oe() {
//         byte[] newArray;
//         newArray = ArrayUtils.addFirst((byte[]) null, (byte) 0);
//         assertArrayEquals(new byte[]{0}, newArray);
//     }

//     @Test
//     public void testAddFirstByte_2_oe() {
//         byte[] newArray;
//         newArray = ArrayUtils.addFirst((byte[]) null, (byte) 0);
//         // removed other assertion
//         assertEquals(Byte.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstByte_3_oe() {
//         byte[] newArray;
//         newArray = ArrayUtils.addFirst((byte[]) null, (byte) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((byte[]) null, (byte) 1);
//         assertArrayEquals(new byte[]{1}, newArray);
//     }

//     @Test
//     public void testAddFirstByte_4_oe() {
//         byte[] newArray;
//         newArray = ArrayUtils.addFirst((byte[]) null, (byte) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((byte[]) null, (byte) 1);
//         // removed other assertion
//         assertEquals(Byte.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstByte_5_oe() {
//         byte[] newArray;
//         newArray = ArrayUtils.addFirst((byte[]) null, (byte) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((byte[]) null, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         final byte[] array1 = new byte[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, (byte) 0);
//         assertArrayEquals(new byte[]{0, 1, 2, 3}, newArray);
//     }

//     @Test
//     public void testAddFirstByte_6_oe() {
//         byte[] newArray;
//         newArray = ArrayUtils.addFirst((byte[]) null, (byte) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((byte[]) null, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         final byte[] array1 = new byte[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, (byte) 0);
//         // removed other assertion
//         assertEquals(Byte.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstByte_7_oe() {
//         byte[] newArray;
//         newArray = ArrayUtils.addFirst((byte[]) null, (byte) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((byte[]) null, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         final byte[] array1 = new byte[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, (byte) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst(array1, (byte) 4);
//         assertArrayEquals(new byte[]{4, 1, 2, 3}, newArray);
//     }

//     @Test
//     public void testAddFirstByte_8_oe() {
//         byte[] newArray;
//         newArray = ArrayUtils.addFirst((byte[]) null, (byte) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((byte[]) null, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         final byte[] array1 = new byte[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, (byte) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst(array1, (byte) 4);
//         // removed other assertion
//         assertEquals(Byte.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstChar_1_oe() {
//         char[] newArray;
//         newArray = ArrayUtils.addFirst((char[]) null, (char) 0);
//         assertArrayEquals(new char[]{0}, newArray);
//     }

//     @Test
//     public void testAddFirstChar_2_oe() {
//         char[] newArray;
//         newArray = ArrayUtils.addFirst((char[]) null, (char) 0);
//         // removed other assertion
//         assertEquals(Character.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstChar_3_oe() {
//         char[] newArray;
//         newArray = ArrayUtils.addFirst((char[]) null, (char) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((char[]) null, (char) 1);
//         assertArrayEquals(new char[]{1}, newArray);
//     }

//     @Test
//     public void testAddFirstChar_4_oe() {
//         char[] newArray;
//         newArray = ArrayUtils.addFirst((char[]) null, (char) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((char[]) null, (char) 1);
//         // removed other assertion
//         assertEquals(Character.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstChar_5_oe() {
//         char[] newArray;
//         newArray = ArrayUtils.addFirst((char[]) null, (char) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((char[]) null, (char) 1);
//         // removed other assertion
//         // removed other assertion
//         final char[] array1 = new char[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, (char) 0);
//         assertArrayEquals(new char[]{0, 1, 2, 3}, newArray);
//     }

//     @Test
//     public void testAddFirstChar_6_oe() {
//         char[] newArray;
//         newArray = ArrayUtils.addFirst((char[]) null, (char) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((char[]) null, (char) 1);
//         // removed other assertion
//         // removed other assertion
//         final char[] array1 = new char[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, (char) 0);
//         // removed other assertion
//         assertEquals(Character.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstChar_7_oe() {
//         char[] newArray;
//         newArray = ArrayUtils.addFirst((char[]) null, (char) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((char[]) null, (char) 1);
//         // removed other assertion
//         // removed other assertion
//         final char[] array1 = new char[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, (char) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst(array1, (char) 4);
//         assertArrayEquals(new char[]{4, 1, 2, 3}, newArray);
//     }

//     @Test
//     public void testAddFirstChar_8_oe() {
//         char[] newArray;
//         newArray = ArrayUtils.addFirst((char[]) null, (char) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((char[]) null, (char) 1);
//         // removed other assertion
//         // removed other assertion
//         final char[] array1 = new char[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, (char) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst(array1, (char) 4);
//         // removed other assertion
//         assertEquals(Character.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstDouble_1_oe() {
//         double[] newArray;
//         newArray = ArrayUtils.addFirst((double[]) null, 0);
//         assertArrayEquals(new double[]{0}, newArray);
//     }

//     @Test
//     public void testAddFirstDouble_2_oe() {
//         double[] newArray;
//         newArray = ArrayUtils.addFirst((double[]) null, 0);
//         // removed other assertion
//         assertEquals(Double.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstDouble_3_oe() {
//         double[] newArray;
//         newArray = ArrayUtils.addFirst((double[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((double[]) null, 1);
//         assertArrayEquals(new double[]{1}, newArray);
//     }

//     @Test
//     public void testAddFirstDouble_4_oe() {
//         double[] newArray;
//         newArray = ArrayUtils.addFirst((double[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((double[]) null, 1);
//         // removed other assertion
//         assertEquals(Double.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstDouble_5_oe() {
//         double[] newArray;
//         newArray = ArrayUtils.addFirst((double[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((double[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final double[] array1 = new double[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, 0);
//         assertArrayEquals(new double[]{0, 1, 2, 3}, newArray);
//     }

//     @Test
//     public void testAddFirstDouble_6_oe() {
//         double[] newArray;
//         newArray = ArrayUtils.addFirst((double[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((double[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final double[] array1 = new double[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, 0);
//         // removed other assertion
//         assertEquals(Double.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstDouble_7_oe() {
//         double[] newArray;
//         newArray = ArrayUtils.addFirst((double[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((double[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final double[] array1 = new double[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst(array1, 4);
//         assertArrayEquals(new double[]{4, 1, 2, 3}, newArray);
//     }

//     @Test
//     public void testAddFirstDouble_8_oe() {
//         double[] newArray;
//         newArray = ArrayUtils.addFirst((double[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((double[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final double[] array1 = new double[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst(array1, 4);
//         // removed other assertion
//         assertEquals(Double.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstFloat_1_oe() {
//         float[] newArray;
//         newArray = ArrayUtils.addFirst((float[]) null, 0);
//         assertArrayEquals(new float[]{0}, newArray);
//     }

//     @Test
//     public void testAddFirstFloat_2_oe() {
//         float[] newArray;
//         newArray = ArrayUtils.addFirst((float[]) null, 0);
//         // removed other assertion
//         assertEquals(Float.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstFloat_3_oe() {
//         float[] newArray;
//         newArray = ArrayUtils.addFirst((float[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((float[]) null, 1);
//         assertArrayEquals(new float[]{1}, newArray);
//     }

//     @Test
//     public void testAddFirstFloat_4_oe() {
//         float[] newArray;
//         newArray = ArrayUtils.addFirst((float[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((float[]) null, 1);
//         // removed other assertion
//         assertEquals(Float.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstFloat_5_oe() {
//         float[] newArray;
//         newArray = ArrayUtils.addFirst((float[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((float[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final float[] array1 = new float[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, 0);
//         assertArrayEquals(new float[]{0, 1, 2, 3}, newArray);
//     }

//     @Test
//     public void testAddFirstFloat_6_oe() {
//         float[] newArray;
//         newArray = ArrayUtils.addFirst((float[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((float[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final float[] array1 = new float[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, 0);
//         // removed other assertion
//         assertEquals(Float.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstFloat_7_oe() {
//         float[] newArray;
//         newArray = ArrayUtils.addFirst((float[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((float[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final float[] array1 = new float[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst(array1, 4);
//         assertArrayEquals(new float[]{4, 1, 2, 3}, newArray);
//     }

//     @Test
//     public void testAddFirstFloat_8_oe() {
//         float[] newArray;
//         newArray = ArrayUtils.addFirst((float[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((float[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final float[] array1 = new float[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst(array1, 4);
//         // removed other assertion
//         assertEquals(Float.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstInt_1_oe() {
//         int[] newArray;
//         newArray = ArrayUtils.addFirst((int[]) null, 0);
//         assertArrayEquals(new int[]{0}, newArray);
//     }

//     @Test
//     public void testAddFirstInt_2_oe() {
//         int[] newArray;
//         newArray = ArrayUtils.addFirst((int[]) null, 0);
//         // removed other assertion
//         assertEquals(Integer.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstInt_3_oe() {
//         int[] newArray;
//         newArray = ArrayUtils.addFirst((int[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((int[]) null, 1);
//         assertArrayEquals(new int[]{1}, newArray);
//     }

//     @Test
//     public void testAddFirstInt_4_oe() {
//         int[] newArray;
//         newArray = ArrayUtils.addFirst((int[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((int[]) null, 1);
//         // removed other assertion
//         assertEquals(Integer.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstInt_5_oe() {
//         int[] newArray;
//         newArray = ArrayUtils.addFirst((int[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((int[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final int[] array1 = new int[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, 0);
//         assertArrayEquals(new int[]{0, 1, 2, 3}, newArray);
//     }

//     @Test
//     public void testAddFirstInt_6_oe() {
//         int[] newArray;
//         newArray = ArrayUtils.addFirst((int[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((int[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final int[] array1 = new int[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, 0);
//         // removed other assertion
//         assertEquals(Integer.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstInt_7_oe() {
//         int[] newArray;
//         newArray = ArrayUtils.addFirst((int[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((int[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final int[] array1 = new int[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst(array1, 4);
//         assertArrayEquals(new int[]{4, 1, 2, 3}, newArray);
//     }

//     @Test
//     public void testAddFirstInt_8_oe() {
//         int[] newArray;
//         newArray = ArrayUtils.addFirst((int[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((int[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final int[] array1 = new int[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst(array1, 4);
//         // removed other assertion
//         assertEquals(Integer.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstLong_1_oe() {
//         long[] newArray;
//         newArray = ArrayUtils.addFirst((long[]) null, 0);
//         assertArrayEquals(new long[]{0}, newArray);
//     }

//     @Test
//     public void testAddFirstLong_2_oe() {
//         long[] newArray;
//         newArray = ArrayUtils.addFirst((long[]) null, 0);
//         // removed other assertion
//         assertEquals(Long.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstLong_3_oe() {
//         long[] newArray;
//         newArray = ArrayUtils.addFirst((long[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((long[]) null, 1);
//         assertArrayEquals(new long[]{1}, newArray);
//     }

//     @Test
//     public void testAddFirstLong_4_oe() {
//         long[] newArray;
//         newArray = ArrayUtils.addFirst((long[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((long[]) null, 1);
//         // removed other assertion
//         assertEquals(Long.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstLong_5_oe() {
//         long[] newArray;
//         newArray = ArrayUtils.addFirst((long[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((long[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final long[] array1 = new long[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, 0);
//         assertArrayEquals(new long[]{0, 1, 2, 3}, newArray);
//     }

//     @Test
//     public void testAddFirstLong_6_oe() {
//         long[] newArray;
//         newArray = ArrayUtils.addFirst((long[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((long[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final long[] array1 = new long[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, 0);
//         // removed other assertion
//         assertEquals(Long.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstLong_7_oe() {
//         long[] newArray;
//         newArray = ArrayUtils.addFirst((long[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((long[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final long[] array1 = new long[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst(array1, 4);
//         assertArrayEquals(new long[]{4, 1, 2, 3}, newArray);
//     }

//     @Test
//     public void testAddFirstLong_8_oe() {
//         long[] newArray;
//         newArray = ArrayUtils.addFirst((long[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((long[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final long[] array1 = new long[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst(array1, 4);
//         // removed other assertion
//         assertEquals(Long.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstObject_1_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         assertArrayEquals(new String[]{"a"}, newArray);
//     }

//     @Test
//     public void testAddFirstObject_2_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         // removed other assertion
//         assertArrayEquals(new Object[]{"a"}, newArray);
//     }

//     @Test
//     public void testAddFirstObject_3_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         // removed other assertion
//         // removed other assertion
//         assertEquals(String.class, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstObject_4_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         //show that not casting to Object[] is okay and will assume String based on "a"
//         final String[] newStringArray = ArrayUtils.add(null, "a");
//         assertArrayEquals(new String[]{"a"}, newStringArray);
//     }

//     @Test
//     public void testAddFirstObject_5_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         //show that not casting to Object[] is okay and will assume String based on "a"
//         final String[] newStringArray = ArrayUtils.add(null, "a");
//         // removed other assertion
//         assertArrayEquals(new Object[]{"a"}, newStringArray);
//     }

//     @Test
//     public void testAddFirstObject_6_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         //show that not casting to Object[] is okay and will assume String based on "a"
//         final String[] newStringArray = ArrayUtils.add(null, "a");
//         // removed other assertion
//         // removed other assertion
//         assertEquals(String.class, newStringArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstObject_7_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         //show that not casting to Object[] is okay and will assume String based on "a"
//         final String[] newStringArray = ArrayUtils.add(null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final String[] stringArray1 = new String[] { "a", "b", "c" };
//         newArray = ArrayUtils.addFirst(stringArray1, null);
//         assertArrayEquals(new String[] { null, "a", "b", "c" }, newArray);
//     }

//     @Test
//     public void testAddFirstObject_8_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         //show that not casting to Object[] is okay and will assume String based on "a"
//         final String[] newStringArray = ArrayUtils.add(null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final String[] stringArray1 = new String[] { "a", "b", "c" };
//         newArray = ArrayUtils.addFirst(stringArray1, null);
//         // removed other assertion
//         assertEquals(String.class, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstObject_9_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         //show that not casting to Object[] is okay and will assume String based on "a"
//         final String[] newStringArray = ArrayUtils.add(null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final String[] stringArray1 = new String[] { "a", "b", "c" };
//         newArray = ArrayUtils.addFirst(stringArray1, null);
//         // removed other assertion
//         // removed other assertion

//         newArray = ArrayUtils.addFirst(stringArray1, "d");
//         assertArrayEquals(new String[] { "d", "a", "b", "c" }, newArray);
//     }

//     @Test
//     public void testAddFirstObject_10_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         //show that not casting to Object[] is okay and will assume String based on "a"
//         final String[] newStringArray = ArrayUtils.add(null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final String[] stringArray1 = new String[] { "a", "b", "c" };
//         newArray = ArrayUtils.addFirst(stringArray1, null);
//         // removed other assertion
//         // removed other assertion

//         newArray = ArrayUtils.addFirst(stringArray1, "d");
//         // removed other assertion
//         assertEquals(String.class, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstObject_11_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         //show that not casting to Object[] is okay and will assume String based on "a"
//         final String[] newStringArray = ArrayUtils.add(null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final String[] stringArray1 = new String[] { "a", "b", "c" };
//         newArray = ArrayUtils.addFirst(stringArray1, null);
//         // removed other assertion
//         // removed other assertion

//         newArray = ArrayUtils.addFirst(stringArray1, "d");
//         // removed other assertion
//         // removed other assertion

//         Number[] numberArray1 = new Number[] { Integer.valueOf(1), Double.valueOf(2) };
//         newArray = ArrayUtils.addFirst(numberArray1, Float.valueOf(3));
//         assertArrayEquals(new Number[] { Float.valueOf(3), Integer.valueOf(1), Double.valueOf(2) }, newArray);
//     }

//     @Test
//     public void testAddFirstObject_12_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         //show that not casting to Object[] is okay and will assume String based on "a"
//         final String[] newStringArray = ArrayUtils.add(null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final String[] stringArray1 = new String[] { "a", "b", "c" };
//         newArray = ArrayUtils.addFirst(stringArray1, null);
//         // removed other assertion
//         // removed other assertion

//         newArray = ArrayUtils.addFirst(stringArray1, "d");
//         // removed other assertion
//         // removed other assertion

//         Number[] numberArray1 = new Number[] { Integer.valueOf(1), Double.valueOf(2) };
//         newArray = ArrayUtils.addFirst(numberArray1, Float.valueOf(3));
//         // removed other assertion
//         assertEquals(Number.class, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstObject_13_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         //show that not casting to Object[] is okay and will assume String based on "a"
//         final String[] newStringArray = ArrayUtils.add(null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final String[] stringArray1 = new String[] { "a", "b", "c" };
//         newArray = ArrayUtils.addFirst(stringArray1, null);
//         // removed other assertion
//         // removed other assertion

//         newArray = ArrayUtils.addFirst(stringArray1, "d");
//         // removed other assertion
//         // removed other assertion

//         Number[] numberArray1 = new Number[] { Integer.valueOf(1), Double.valueOf(2) };
//         newArray = ArrayUtils.addFirst(numberArray1, Float.valueOf(3));
//         // removed other assertion
//         // removed other assertion

//         numberArray1 = null;
//         newArray = ArrayUtils.addFirst(numberArray1, Float.valueOf(3));
//         assertArrayEquals(new Float[] { Float.valueOf(3) }, newArray);
//     }

//     @Test
//     public void testAddFirstObject_14_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         //show that not casting to Object[] is okay and will assume String based on "a"
//         final String[] newStringArray = ArrayUtils.add(null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final String[] stringArray1 = new String[] { "a", "b", "c" };
//         newArray = ArrayUtils.addFirst(stringArray1, null);
//         // removed other assertion
//         // removed other assertion

//         newArray = ArrayUtils.addFirst(stringArray1, "d");
//         // removed other assertion
//         // removed other assertion

//         Number[] numberArray1 = new Number[] { Integer.valueOf(1), Double.valueOf(2) };
//         newArray = ArrayUtils.addFirst(numberArray1, Float.valueOf(3));
//         // removed other assertion
//         // removed other assertion

//         numberArray1 = null;
//         newArray = ArrayUtils.addFirst(numberArray1, Float.valueOf(3));
//         // removed other assertion
//         assertEquals(Float.class, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstShort_1_oe() {
//         short[] newArray;
//         newArray = ArrayUtils.addFirst((short[]) null, (short) 0);
//         assertArrayEquals(new short[]{0}, newArray);
//     }

//     @Test
//     public void testAddFirstShort_2_oe() {
//         short[] newArray;
//         newArray = ArrayUtils.addFirst((short[]) null, (short) 0);
//         // removed other assertion
//         assertEquals(Short.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstShort_3_oe() {
//         short[] newArray;
//         newArray = ArrayUtils.addFirst((short[]) null, (short) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((short[]) null, (short) 1);
//         assertArrayEquals(new short[]{1}, newArray);
//     }

//     @Test
//     public void testAddFirstShort_4_oe() {
//         short[] newArray;
//         newArray = ArrayUtils.addFirst((short[]) null, (short) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((short[]) null, (short) 1);
//         // removed other assertion
//         assertEquals(Short.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstShort_5_oe() {
//         short[] newArray;
//         newArray = ArrayUtils.addFirst((short[]) null, (short) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((short[]) null, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         final short[] array1 = new short[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, (short) 0);
//         assertArrayEquals(new short[]{0, 1, 2, 3}, newArray);
//     }

//     @Test
//     public void testAddFirstShort_6_oe() {
//         short[] newArray;
//         newArray = ArrayUtils.addFirst((short[]) null, (short) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((short[]) null, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         final short[] array1 = new short[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, (short) 0);
//         // removed other assertion
//         assertEquals(Short.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddFirstShort_7_oe() {
//         short[] newArray;
//         newArray = ArrayUtils.addFirst((short[]) null, (short) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((short[]) null, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         final short[] array1 = new short[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, (short) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst(array1, (short) 4);
//         assertArrayEquals(new short[]{4, 1, 2, 3}, newArray);
//     }

//     @Test
//     public void testAddFirstShort_8_oe() {
//         short[] newArray;
//         newArray = ArrayUtils.addFirst((short[]) null, (short) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst((short[]) null, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         final short[] array1 = new short[]{1, 2, 3};
//         newArray = ArrayUtils.addFirst(array1, (short) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addFirst(array1, (short) 4);
//         // removed other assertion
//         assertEquals(Short.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayBoolean_1_oe() {
//         boolean[] newArray;
//         newArray = ArrayUtils.add(null, false);
//         assertArrayEquals(new boolean[]{false}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayBoolean_2_oe() {
//         boolean[] newArray;
//         newArray = ArrayUtils.add(null, false);
//         // removed other assertion
//         assertEquals(Boolean.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayBoolean_3_oe() {
//         boolean[] newArray;
//         newArray = ArrayUtils.add(null, false);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(null, true);
//         assertArrayEquals(new boolean[]{true}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayBoolean_4_oe() {
//         boolean[] newArray;
//         newArray = ArrayUtils.add(null, false);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(null, true);
//         // removed other assertion
//         assertEquals(Boolean.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayBoolean_5_oe() {
//         boolean[] newArray;
//         newArray = ArrayUtils.add(null, false);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(null, true);
//         // removed other assertion
//         // removed other assertion
//         final boolean[] array1 = new boolean[]{true, false, true};
//         newArray = ArrayUtils.add(array1, false);
//         assertArrayEquals(new boolean[]{true, false, true, false}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayBoolean_6_oe() {
//         boolean[] newArray;
//         newArray = ArrayUtils.add(null, false);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(null, true);
//         // removed other assertion
//         // removed other assertion
//         final boolean[] array1 = new boolean[]{true, false, true};
//         newArray = ArrayUtils.add(array1, false);
//         // removed other assertion
//         assertEquals(Boolean.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayByte_1_oe() {
//         byte[] newArray;
//         newArray = ArrayUtils.add((byte[]) null, (byte) 0);
//         assertArrayEquals(new byte[]{0}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayByte_2_oe() {
//         byte[] newArray;
//         newArray = ArrayUtils.add((byte[]) null, (byte) 0);
//         // removed other assertion
//         assertEquals(Byte.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayByte_3_oe() {
//         byte[] newArray;
//         newArray = ArrayUtils.add((byte[]) null, (byte) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((byte[]) null, (byte) 1);
//         assertArrayEquals(new byte[]{1}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayByte_4_oe() {
//         byte[] newArray;
//         newArray = ArrayUtils.add((byte[]) null, (byte) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((byte[]) null, (byte) 1);
//         // removed other assertion
//         assertEquals(Byte.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayByte_5_oe() {
//         byte[] newArray;
//         newArray = ArrayUtils.add((byte[]) null, (byte) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((byte[]) null, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         final byte[] array1 = new byte[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, (byte) 0);
//         assertArrayEquals(new byte[]{1, 2, 3, 0}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayByte_6_oe() {
//         byte[] newArray;
//         newArray = ArrayUtils.add((byte[]) null, (byte) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((byte[]) null, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         final byte[] array1 = new byte[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, (byte) 0);
//         // removed other assertion
//         assertEquals(Byte.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayByte_7_oe() {
//         byte[] newArray;
//         newArray = ArrayUtils.add((byte[]) null, (byte) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((byte[]) null, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         final byte[] array1 = new byte[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, (byte) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(array1, (byte) 4);
//         assertArrayEquals(new byte[]{1, 2, 3, 4}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayByte_8_oe() {
//         byte[] newArray;
//         newArray = ArrayUtils.add((byte[]) null, (byte) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((byte[]) null, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         final byte[] array1 = new byte[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, (byte) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(array1, (byte) 4);
//         // removed other assertion
//         assertEquals(Byte.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayChar_1_oe() {
//         char[] newArray;
//         newArray = ArrayUtils.add((char[]) null, (char) 0);
//         assertArrayEquals(new char[]{0}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayChar_2_oe() {
//         char[] newArray;
//         newArray = ArrayUtils.add((char[]) null, (char) 0);
//         // removed other assertion
//         assertEquals(Character.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayChar_3_oe() {
//         char[] newArray;
//         newArray = ArrayUtils.add((char[]) null, (char) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((char[]) null, (char) 1);
//         assertArrayEquals(new char[]{1}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayChar_4_oe() {
//         char[] newArray;
//         newArray = ArrayUtils.add((char[]) null, (char) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((char[]) null, (char) 1);
//         // removed other assertion
//         assertEquals(Character.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayChar_5_oe() {
//         char[] newArray;
//         newArray = ArrayUtils.add((char[]) null, (char) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((char[]) null, (char) 1);
//         // removed other assertion
//         // removed other assertion
//         final char[] array1 = new char[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, (char) 0);
//         assertArrayEquals(new char[]{1, 2, 3, 0}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayChar_6_oe() {
//         char[] newArray;
//         newArray = ArrayUtils.add((char[]) null, (char) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((char[]) null, (char) 1);
//         // removed other assertion
//         // removed other assertion
//         final char[] array1 = new char[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, (char) 0);
//         // removed other assertion
//         assertEquals(Character.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayChar_7_oe() {
//         char[] newArray;
//         newArray = ArrayUtils.add((char[]) null, (char) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((char[]) null, (char) 1);
//         // removed other assertion
//         // removed other assertion
//         final char[] array1 = new char[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, (char) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(array1, (char) 4);
//         assertArrayEquals(new char[]{1, 2, 3, 4}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayChar_8_oe() {
//         char[] newArray;
//         newArray = ArrayUtils.add((char[]) null, (char) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((char[]) null, (char) 1);
//         // removed other assertion
//         // removed other assertion
//         final char[] array1 = new char[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, (char) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(array1, (char) 4);
//         // removed other assertion
//         assertEquals(Character.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayDouble_1_oe() {
//         double[] newArray;
//         newArray = ArrayUtils.add((double[]) null, 0);
//         assertArrayEquals(new double[]{0}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayDouble_2_oe() {
//         double[] newArray;
//         newArray = ArrayUtils.add((double[]) null, 0);
//         // removed other assertion
//         assertEquals(Double.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayDouble_3_oe() {
//         double[] newArray;
//         newArray = ArrayUtils.add((double[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((double[]) null, 1);
//         assertArrayEquals(new double[]{1}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayDouble_4_oe() {
//         double[] newArray;
//         newArray = ArrayUtils.add((double[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((double[]) null, 1);
//         // removed other assertion
//         assertEquals(Double.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayDouble_5_oe() {
//         double[] newArray;
//         newArray = ArrayUtils.add((double[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((double[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final double[] array1 = new double[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, 0);
//         assertArrayEquals(new double[]{1, 2, 3, 0}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayDouble_6_oe() {
//         double[] newArray;
//         newArray = ArrayUtils.add((double[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((double[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final double[] array1 = new double[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, 0);
//         // removed other assertion
//         assertEquals(Double.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayDouble_7_oe() {
//         double[] newArray;
//         newArray = ArrayUtils.add((double[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((double[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final double[] array1 = new double[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(array1, 4);
//         assertArrayEquals(new double[]{1, 2, 3, 4}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayDouble_8_oe() {
//         double[] newArray;
//         newArray = ArrayUtils.add((double[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((double[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final double[] array1 = new double[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(array1, 4);
//         // removed other assertion
//         assertEquals(Double.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayFloat_1_oe() {
//         float[] newArray;
//         newArray = ArrayUtils.add((float[]) null, 0);
//         assertArrayEquals(new float[]{0}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayFloat_2_oe() {
//         float[] newArray;
//         newArray = ArrayUtils.add((float[]) null, 0);
//         // removed other assertion
//         assertEquals(Float.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayFloat_3_oe() {
//         float[] newArray;
//         newArray = ArrayUtils.add((float[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((float[]) null, 1);
//         assertArrayEquals(new float[]{1}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayFloat_4_oe() {
//         float[] newArray;
//         newArray = ArrayUtils.add((float[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((float[]) null, 1);
//         // removed other assertion
//         assertEquals(Float.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayFloat_5_oe() {
//         float[] newArray;
//         newArray = ArrayUtils.add((float[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((float[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final float[] array1 = new float[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, 0);
//         assertArrayEquals(new float[]{1, 2, 3, 0}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayFloat_6_oe() {
//         float[] newArray;
//         newArray = ArrayUtils.add((float[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((float[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final float[] array1 = new float[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, 0);
//         // removed other assertion
//         assertEquals(Float.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayFloat_7_oe() {
//         float[] newArray;
//         newArray = ArrayUtils.add((float[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((float[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final float[] array1 = new float[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(array1, 4);
//         assertArrayEquals(new float[]{1, 2, 3, 4}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayFloat_8_oe() {
//         float[] newArray;
//         newArray = ArrayUtils.add((float[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((float[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final float[] array1 = new float[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(array1, 4);
//         // removed other assertion
//         assertEquals(Float.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayInt_1_oe() {
//         int[] newArray;
//         newArray = ArrayUtils.add((int[]) null, 0);
//         assertArrayEquals(new int[]{0}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayInt_2_oe() {
//         int[] newArray;
//         newArray = ArrayUtils.add((int[]) null, 0);
//         // removed other assertion
//         assertEquals(Integer.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayInt_3_oe() {
//         int[] newArray;
//         newArray = ArrayUtils.add((int[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((int[]) null, 1);
//         assertArrayEquals(new int[]{1}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayInt_4_oe() {
//         int[] newArray;
//         newArray = ArrayUtils.add((int[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((int[]) null, 1);
//         // removed other assertion
//         assertEquals(Integer.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayInt_5_oe() {
//         int[] newArray;
//         newArray = ArrayUtils.add((int[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((int[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final int[] array1 = new int[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, 0);
//         assertArrayEquals(new int[]{1, 2, 3, 0}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayInt_6_oe() {
//         int[] newArray;
//         newArray = ArrayUtils.add((int[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((int[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final int[] array1 = new int[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, 0);
//         // removed other assertion
//         assertEquals(Integer.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayInt_7_oe() {
//         int[] newArray;
//         newArray = ArrayUtils.add((int[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((int[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final int[] array1 = new int[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(array1, 4);
//         assertArrayEquals(new int[]{1, 2, 3, 4}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayInt_8_oe() {
//         int[] newArray;
//         newArray = ArrayUtils.add((int[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((int[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final int[] array1 = new int[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(array1, 4);
//         // removed other assertion
//         assertEquals(Integer.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayLong_1_oe() {
//         long[] newArray;
//         newArray = ArrayUtils.add((long[]) null, 0);
//         assertArrayEquals(new long[]{0}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayLong_2_oe() {
//         long[] newArray;
//         newArray = ArrayUtils.add((long[]) null, 0);
//         // removed other assertion
//         assertEquals(Long.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayLong_3_oe() {
//         long[] newArray;
//         newArray = ArrayUtils.add((long[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((long[]) null, 1);
//         assertArrayEquals(new long[]{1}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayLong_4_oe() {
//         long[] newArray;
//         newArray = ArrayUtils.add((long[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((long[]) null, 1);
//         // removed other assertion
//         assertEquals(Long.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayLong_5_oe() {
//         long[] newArray;
//         newArray = ArrayUtils.add((long[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((long[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final long[] array1 = new long[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, 0);
//         assertArrayEquals(new long[]{1, 2, 3, 0}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayLong_6_oe() {
//         long[] newArray;
//         newArray = ArrayUtils.add((long[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((long[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final long[] array1 = new long[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, 0);
//         // removed other assertion
//         assertEquals(Long.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayLong_7_oe() {
//         long[] newArray;
//         newArray = ArrayUtils.add((long[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((long[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final long[] array1 = new long[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(array1, 4);
//         assertArrayEquals(new long[]{1, 2, 3, 4}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayLong_8_oe() {
//         long[] newArray;
//         newArray = ArrayUtils.add((long[]) null, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((long[]) null, 1);
//         // removed other assertion
//         // removed other assertion
//         final long[] array1 = new long[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(array1, 4);
//         // removed other assertion
//         assertEquals(Long.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayObject_1_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         assertArrayEquals(new String[]{"a"}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayObject_2_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         // removed other assertion
//         assertArrayEquals(new Object[]{"a"}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayObject_3_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         // removed other assertion
//         // removed other assertion
//         assertEquals(String.class, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayObject_4_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         //show that not casting to Object[] is okay and will assume String based on "a"
//         final String[] newStringArray = ArrayUtils.add(null, "a");
//         assertArrayEquals(new String[]{"a"}, newStringArray);
//     }

//     @Test
//     public void testAddObjectArrayObject_5_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         //show that not casting to Object[] is okay and will assume String based on "a"
//         final String[] newStringArray = ArrayUtils.add(null, "a");
//         // removed other assertion
//         assertArrayEquals(new Object[]{"a"}, newStringArray);
//     }

//     @Test
//     public void testAddObjectArrayObject_6_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         //show that not casting to Object[] is okay and will assume String based on "a"
//         final String[] newStringArray = ArrayUtils.add(null, "a");
//         // removed other assertion
//         // removed other assertion
//         assertEquals(String.class, newStringArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayObject_7_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         //show that not casting to Object[] is okay and will assume String based on "a"
//         final String[] newStringArray = ArrayUtils.add(null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, null);
//         assertArrayEquals(new String[]{"a", "b", "c", null}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayObject_8_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         //show that not casting to Object[] is okay and will assume String based on "a"
//         final String[] newStringArray = ArrayUtils.add(null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, null);
//         // removed other assertion
//         assertEquals(String.class, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayObject_9_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         //show that not casting to Object[] is okay and will assume String based on "a"
//         final String[] newStringArray = ArrayUtils.add(null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, null);
//         // removed other assertion
//         // removed other assertion

//         newArray = ArrayUtils.add(stringArray1, "d");
//         assertArrayEquals(new String[]{"a", "b", "c", "d"}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayObject_10_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         //show that not casting to Object[] is okay and will assume String based on "a"
//         final String[] newStringArray = ArrayUtils.add(null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, null);
//         // removed other assertion
//         // removed other assertion

//         newArray = ArrayUtils.add(stringArray1, "d");
//         // removed other assertion
//         assertEquals(String.class, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayObject_11_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         //show that not casting to Object[] is okay and will assume String based on "a"
//         final String[] newStringArray = ArrayUtils.add(null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, null);
//         // removed other assertion
//         // removed other assertion

//         newArray = ArrayUtils.add(stringArray1, "d");
//         // removed other assertion
//         // removed other assertion

//         Number[] numberArray1 = new Number[]{Integer.valueOf(1), Double.valueOf(2)};
//         newArray = ArrayUtils.add(numberArray1, Float.valueOf(3));
//         assertArrayEquals(new Number[]{Integer.valueOf(1), Double.valueOf(2), Float.valueOf(3)}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayObject_12_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         //show that not casting to Object[] is okay and will assume String based on "a"
//         final String[] newStringArray = ArrayUtils.add(null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, null);
//         // removed other assertion
//         // removed other assertion

//         newArray = ArrayUtils.add(stringArray1, "d");
//         // removed other assertion
//         // removed other assertion

//         Number[] numberArray1 = new Number[]{Integer.valueOf(1), Double.valueOf(2)};
//         newArray = ArrayUtils.add(numberArray1, Float.valueOf(3));
//         // removed other assertion
//         assertEquals(Number.class, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayObject_13_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         //show that not casting to Object[] is okay and will assume String based on "a"
//         final String[] newStringArray = ArrayUtils.add(null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, null);
//         // removed other assertion
//         // removed other assertion

//         newArray = ArrayUtils.add(stringArray1, "d");
//         // removed other assertion
//         // removed other assertion

//         Number[] numberArray1 = new Number[]{Integer.valueOf(1), Double.valueOf(2)};
//         newArray = ArrayUtils.add(numberArray1, Float.valueOf(3));
//         // removed other assertion
//         // removed other assertion

//         numberArray1 = null;
//         newArray = ArrayUtils.add(numberArray1, Float.valueOf(3));
//         assertArrayEquals(new Float[]{Float.valueOf(3)}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayObject_14_oe() {
//         Object[] newArray;

//         //show that not casting is okay
//         newArray = ArrayUtils.add((Object[]) null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         //show that not casting to Object[] is okay and will assume String based on "a"
//         final String[] newStringArray = ArrayUtils.add(null, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, null);
//         // removed other assertion
//         // removed other assertion

//         newArray = ArrayUtils.add(stringArray1, "d");
//         // removed other assertion
//         // removed other assertion

//         Number[] numberArray1 = new Number[]{Integer.valueOf(1), Double.valueOf(2)};
//         newArray = ArrayUtils.add(numberArray1, Float.valueOf(3));
//         // removed other assertion
//         // removed other assertion

//         numberArray1 = null;
//         newArray = ArrayUtils.add(numberArray1, Float.valueOf(3));
//         // removed other assertion
//         assertEquals(Float.class, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayShort_1_oe() {
//         short[] newArray;
//         newArray = ArrayUtils.add((short[]) null, (short) 0);
//         assertArrayEquals(new short[]{0}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayShort_2_oe() {
//         short[] newArray;
//         newArray = ArrayUtils.add((short[]) null, (short) 0);
//         // removed other assertion
//         assertEquals(Short.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayShort_3_oe() {
//         short[] newArray;
//         newArray = ArrayUtils.add((short[]) null, (short) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((short[]) null, (short) 1);
//         assertArrayEquals(new short[]{1}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayShort_4_oe() {
//         short[] newArray;
//         newArray = ArrayUtils.add((short[]) null, (short) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((short[]) null, (short) 1);
//         // removed other assertion
//         assertEquals(Short.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayShort_5_oe() {
//         short[] newArray;
//         newArray = ArrayUtils.add((short[]) null, (short) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((short[]) null, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         final short[] array1 = new short[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, (short) 0);
//         assertArrayEquals(new short[]{1, 2, 3, 0}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayShort_6_oe() {
//         short[] newArray;
//         newArray = ArrayUtils.add((short[]) null, (short) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((short[]) null, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         final short[] array1 = new short[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, (short) 0);
//         // removed other assertion
//         assertEquals(Short.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayShort_7_oe() {
//         short[] newArray;
//         newArray = ArrayUtils.add((short[]) null, (short) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((short[]) null, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         final short[] array1 = new short[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, (short) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(array1, (short) 4);
//         assertArrayEquals(new short[]{1, 2, 3, 4}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayShort_8_oe() {
//         short[] newArray;
//         newArray = ArrayUtils.add((short[]) null, (short) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add((short[]) null, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         final short[] array1 = new short[]{1, 2, 3};
//         newArray = ArrayUtils.add(array1, (short) 0);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(array1, (short) 4);
//         // removed other assertion
//         assertEquals(Short.TYPE, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_1_oe() {
//         assertNull(ArrayUtils.addAll(null, (Object[]) null));
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_2_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         assertNotSame(stringArray1, newArray);
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_3_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         assertArrayEquals(stringArray1, newArray);
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_4_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         assertArrayEquals(new String[]{"a", "b", "c"}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_5_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         assertEquals(String.class, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_6_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         assertNotSame(stringArray2, newArray);
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_7_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         assertArrayEquals(stringArray2, newArray);
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_8_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         assertArrayEquals(new String[]{"1", "2", "3"}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_9_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         assertEquals(String.class, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_10_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         assertArrayEquals(new String[]{"a", "b", "c", "1", "2", "3"}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_11_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         assertEquals(String.class, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_12_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         assertArrayEquals(ArrayUtils.EMPTY_STRING_ARRAY, newArray);
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_13_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         assertArrayEquals(new String[]{}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_14_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         assertEquals(String.class, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_15_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         assertArrayEquals(ArrayUtils.EMPTY_STRING_ARRAY, newArray);
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_16_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         assertArrayEquals(new String[]{}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_17_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         assertEquals(String.class, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_18_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         assertArrayEquals(ArrayUtils.EMPTY_STRING_ARRAY, newArray);
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_19_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         assertArrayEquals(new String[]{}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_20_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         assertEquals(String.class, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_21_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArrayNull = new String []{null};
//         newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
//         assertArrayEquals(new String[]{null, null}, newArray);
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_22_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArrayNull = new String []{null};
//         newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
//         // removed other assertion
//         assertEquals(String.class, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_23_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArrayNull = new String []{null};
//         newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
//         // removed other assertion
//         // removed other assertion

//         // boolean
//         assertArrayEquals(new boolean[]{true, false, false, true}, ArrayUtils.addAll(new boolean[]{true, false}, false, true));
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_24_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArrayNull = new String []{null};
//         newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
//         // removed other assertion
//         // removed other assertion

//         // boolean
//         // removed other assertion

//         assertArrayEquals(new boolean[]{false, true}, ArrayUtils.addAll(null, new boolean[]{false, true}));
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_25_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArrayNull = new String []{null};
//         newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
//         // removed other assertion
//         // removed other assertion

//         // boolean
//         // removed other assertion

//         // removed other assertion

//         assertArrayEquals(new boolean[]{true, false}, ArrayUtils.addAll(new boolean[]{true, false}, null));
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_26_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArrayNull = new String []{null};
//         newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
//         // removed other assertion
//         // removed other assertion

//         // boolean
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // char
//         assertArrayEquals(new char[]{'a', 'b', 'c', 'd'}, ArrayUtils.addAll(new char[]{'a', 'b'}, 'c', 'd'));
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_27_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArrayNull = new String []{null};
//         newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
//         // removed other assertion
//         // removed other assertion

//         // boolean
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // char
//         // removed other assertion

//         assertArrayEquals(new char[]{'c', 'd'}, ArrayUtils.addAll(null, new char[]{'c', 'd'}));
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_28_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArrayNull = new String []{null};
//         newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
//         // removed other assertion
//         // removed other assertion

//         // boolean
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // char
//         // removed other assertion

//         // removed other assertion

//         assertArrayEquals(new char[]{'a', 'b'}, ArrayUtils.addAll(new char[]{'a', 'b'}, null));
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_29_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArrayNull = new String []{null};
//         newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
//         // removed other assertion
//         // removed other assertion

//         // boolean
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // char
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // byte
//         assertArrayEquals(new byte[]{(byte) 0, (byte) 1, (byte) 2, (byte) 3}, ArrayUtils.addAll(new byte[]{(byte) 0, (byte) 1}, (byte) 2, (byte) 3));
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_30_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArrayNull = new String []{null};
//         newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
//         // removed other assertion
//         // removed other assertion

//         // boolean
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // char
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // byte
//         // removed other assertion

//         assertArrayEquals(new byte[]{(byte) 2, (byte) 3}, ArrayUtils.addAll(null, new byte[]{(byte) 2, (byte) 3}));
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_31_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArrayNull = new String []{null};
//         newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
//         // removed other assertion
//         // removed other assertion

//         // boolean
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // char
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // byte
//         // removed other assertion

//         // removed other assertion

//         assertArrayEquals(new byte[]{(byte) 0, (byte) 1}, ArrayUtils.addAll(new byte[]{(byte) 0, (byte) 1}, null));
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_32_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArrayNull = new String []{null};
//         newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
//         // removed other assertion
//         // removed other assertion

//         // boolean
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // char
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // byte
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // short
//         assertArrayEquals(new short[]{(short) 10, (short) 20, (short) 30, (short) 40}, ArrayUtils.addAll(new short[]{(short) 10, (short) 20}, (short) 30, (short) 40));
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_33_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArrayNull = new String []{null};
//         newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
//         // removed other assertion
//         // removed other assertion

//         // boolean
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // char
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // byte
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // short
//         // removed other assertion

//         assertArrayEquals(new short[]{(short) 30, (short) 40}, ArrayUtils.addAll(null, new short[]{(short) 30, (short) 40}));
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_34_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArrayNull = new String []{null};
//         newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
//         // removed other assertion
//         // removed other assertion

//         // boolean
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // char
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // byte
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // short
//         // removed other assertion

//         // removed other assertion

//         assertArrayEquals(new short[]{(short) 10, (short) 20}, ArrayUtils.addAll(new short[]{(short) 10, (short) 20}, null));
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_35_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArrayNull = new String []{null};
//         newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
//         // removed other assertion
//         // removed other assertion

//         // boolean
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // char
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // byte
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // short
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // int
//         assertArrayEquals(new int[]{1, 1000, -1000, -1}, ArrayUtils.addAll(new int[]{1, 1000}, -1000, -1));
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_36_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArrayNull = new String []{null};
//         newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
//         // removed other assertion
//         // removed other assertion

//         // boolean
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // char
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // byte
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // short
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // int
//         // removed other assertion

//         assertArrayEquals(new int[]{-1000, -1}, ArrayUtils.addAll(null, new int[]{-1000, -1}));
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_37_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArrayNull = new String []{null};
//         newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
//         // removed other assertion
//         // removed other assertion

//         // boolean
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // char
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // byte
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // short
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // int
//         // removed other assertion

//         // removed other assertion

//         assertArrayEquals(new int[]{1, 1000}, ArrayUtils.addAll(new int[]{1, 1000}, null));
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_38_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArrayNull = new String []{null};
//         newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
//         // removed other assertion
//         // removed other assertion

//         // boolean
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // char
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // byte
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // short
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // int
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // long
//         assertArrayEquals(new long[]{1L, -1L, 1000L, -1000L}, ArrayUtils.addAll(new long[]{1L, -1L}, 1000L, -1000L));
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_39_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArrayNull = new String []{null};
//         newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
//         // removed other assertion
//         // removed other assertion

//         // boolean
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // char
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // byte
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // short
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // int
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // long
//         // removed other assertion

//         assertArrayEquals(new long[]{1000L, -1000L}, ArrayUtils.addAll(null, new long[]{1000L, -1000L}));
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_40_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArrayNull = new String []{null};
//         newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
//         // removed other assertion
//         // removed other assertion

//         // boolean
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // char
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // byte
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // short
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // int
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // long
//         // removed other assertion

//         // removed other assertion

//         assertArrayEquals(new long[]{1L, -1L}, ArrayUtils.addAll(new long[]{1L, -1L}, null));
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_41_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArrayNull = new String []{null};
//         newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
//         // removed other assertion
//         // removed other assertion

//         // boolean
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // char
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // byte
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // short
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // int
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // long
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // float
//         assertArrayEquals(new float[]{10.5f, 10.1f, 1.6f, 0.01f}, ArrayUtils.addAll(new float[]{10.5f, 10.1f}, 1.6f, 0.01f));
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_42_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArrayNull = new String []{null};
//         newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
//         // removed other assertion
//         // removed other assertion

//         // boolean
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // char
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // byte
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // short
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // int
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // long
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // float
//         // removed other assertion

//         assertArrayEquals(new float[]{1.6f, 0.01f}, ArrayUtils.addAll(null, new float[]{1.6f, 0.01f}));
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_43_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArrayNull = new String []{null};
//         newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
//         // removed other assertion
//         // removed other assertion

//         // boolean
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // char
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // byte
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // short
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // int
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // long
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // float
//         // removed other assertion

//         // removed other assertion

//         assertArrayEquals(new float[]{10.5f, 10.1f}, ArrayUtils.addAll(new float[]{10.5f, 10.1f}, null));
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_44_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArrayNull = new String []{null};
//         newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
//         // removed other assertion
//         // removed other assertion

//         // boolean
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // char
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // byte
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // short
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // int
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // long
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // float
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // double
//         assertArrayEquals(new double[]{Math.PI, -Math.PI, 0, 9.99}, ArrayUtils.addAll(new double[]{Math.PI, -Math.PI}, 0, 9.99));
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_45_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArrayNull = new String []{null};
//         newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
//         // removed other assertion
//         // removed other assertion

//         // boolean
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // char
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // byte
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // short
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // int
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // long
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // float
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // double
//         // removed other assertion

//         assertArrayEquals(new double[]{0, 9.99}, ArrayUtils.addAll(null, new double[]{0, 9.99}));
//     }

//     @Test
//     public void testAddObjectArrayToObjectArray_46_oe() {
//         // removed other assertion
//         Object[] newArray;
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         final String[] stringArray2 = new String[]{"1", "2", "3"};
//         newArray = ArrayUtils.addAll(stringArray1, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(stringArray1, stringArray2);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, (String[]) null);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(null, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.addAll(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArrayNull = new String []{null};
//         newArray = ArrayUtils.addAll(stringArrayNull, stringArrayNull);
//         // removed other assertion
//         // removed other assertion

//         // boolean
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // char
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // byte
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // short
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // int
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // long
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // float
//         // removed other assertion

//         // removed other assertion

//         // removed other assertion

//         // double
//         // removed other assertion

//         // removed other assertion

//         assertArrayEquals(new double[]{Math.PI, -Math.PI}, ArrayUtils.addAll(new double[]{Math.PI, -Math.PI}, null));
//     }

//     @Test
//     public void testAddObjectAtIndex_1_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         assertArrayEquals(new String[]{"a"}, newArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_2_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         assertArrayEquals(new Object[]{"a"}, newArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_3_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         assertEquals(String.class, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectAtIndex_4_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         assertArrayEquals(new String[]{null, "a", "b", "c"}, newArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_5_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         assertEquals(String.class, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectAtIndex_6_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         assertArrayEquals(new String[]{"a", null, "b", "c"}, newArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_7_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         assertEquals(String.class, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectAtIndex_8_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         assertArrayEquals(new String[]{"a", "b", "c", null}, newArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_9_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         assertEquals(String.class, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectAtIndex_10_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         assertArrayEquals(new String[]{"a", "b", "c", "d"}, newArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_11_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         assertEquals(String.class, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectAtIndex_12_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         assertEquals(String.class, newArray.getClass().getComponentType());
//     }

//     @Test
//     public void testAddObjectAtIndex_13_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         assertNotNull(result);
//     }

//     @Test
//     public void testAddObjectAtIndex_14_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         assertEquals(4, result.length);
//     }

//     @Test
//     public void testAddObjectAtIndex_15_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         assertEquals("1", result[0]);
//     }

//     @Test
//     public void testAddObjectAtIndex_16_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         assertEquals("2", result[1]);
//     }

//     @Test
//     public void testAddObjectAtIndex_17_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         assertEquals("3", result[2]);
//     }

//     @Test
//     public void testAddObjectAtIndex_18_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         assertEquals("4", result[3]);
//     }

//     @Test
//     public void testAddObjectAtIndex_19_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         assertNotNull(result2);
//     }

//     @Test
//     public void testAddObjectAtIndex_20_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         assertEquals(4, result2.length);
//     }

//     @Test
//     public void testAddObjectAtIndex_21_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         assertEquals("1", result2[0]);
//     }

//     @Test
//     public void testAddObjectAtIndex_22_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         assertEquals("2", result2[1]);
//     }

//     @Test
//     public void testAddObjectAtIndex_23_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         assertEquals("4", result2[2]);
//     }

//     @Test
//     public void testAddObjectAtIndex_24_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         assertEquals("5", result2[3]);
//     }

//     @Test
//     public void testAddObjectAtIndex_25_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         assertArrayEquals(new boolean[]{true}, booleanArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_26_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add( null, -1, true));
//     }

//     @Test
//     public void testAddObjectAtIndex_27_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         assertEquals("Index: -1, Length: 0", e.getMessage());
//     }

//     @Test
//     public void testAddObjectAtIndex_28_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         assertArrayEquals(new boolean[]{false, true}, booleanArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_29_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         assertArrayEquals(new boolean[]{false, true}, booleanArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_30_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         assertArrayEquals(new boolean[]{true, true, false}, booleanArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_31_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         e = assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add(new boolean[] { true, false }, 4, true));
//     }

//     @Test
//     public void testAddObjectAtIndex_32_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         assertEquals("Index: 4, Length: 2", e.getMessage());
//     }

//     @Test
//     public void testAddObjectAtIndex_33_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         e = assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add(new boolean[] { true, false }, -1, true));
//     }

//     @Test
//     public void testAddObjectAtIndex_34_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         assertEquals("Index: -1, Length: 2", e.getMessage());
//     }

//     @Test
//     public void testAddObjectAtIndex_35_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         assertArrayEquals(new char[]{'a'}, charArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_36_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         e = assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add( (char[]) null, -1, 'a' ));
//     }

//     @Test
//     public void testAddObjectAtIndex_37_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         assertEquals("Index: -1, Length: 0", e.getMessage());
//     }

//     @Test
//     public void testAddObjectAtIndex_38_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         assertArrayEquals(new char[]{'b', 'a'}, charArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_39_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         assertArrayEquals(new char[]{'c', 'a', 'b'}, charArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_40_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         assertArrayEquals(new char[]{'a', 'k', 'b'}, charArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_41_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         assertArrayEquals(new char[]{'a', 't', 'b', 'c'}, charArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_42_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         e = assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add( new char[] { 'a', 'b' }, 4, 'c'));
//     }

//     @Test
//     public void testAddObjectAtIndex_43_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         assertEquals("Index: 4, Length: 2", e.getMessage());
//     }

//     @Test
//     public void testAddObjectAtIndex_44_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         e = assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add( new char[] { 'a', 'b' }, -1, 'c'));
//     }

//     @Test
//     public void testAddObjectAtIndex_45_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         assertEquals("Index: -1, Length: 2", e.getMessage());
//     }

//     @Test
//     public void testAddObjectAtIndex_46_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         assertArrayEquals(new short[]{2, 1}, shortArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_47_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         e = assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add( (short[]) null, -1, (short) 2));
//     }

//     @Test
//     public void testAddObjectAtIndex_48_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         assertEquals("Index: -1, Length: 0", e.getMessage());
//     }

//     @Test
//     public void testAddObjectAtIndex_49_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         assertArrayEquals(new short[]{2, 6, 10}, shortArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_50_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         assertArrayEquals(new short[]{-4, 2, 6}, shortArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_51_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         assertArrayEquals(new short[]{2, 6, 1, 3}, shortArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_52_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         e = assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add( new short[] { 2, 6 }, 4, (short) 10));
//     }

//     @Test
//     public void testAddObjectAtIndex_53_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         assertEquals("Index: 4, Length: 2", e.getMessage());
//     }

//     @Test
//     public void testAddObjectAtIndex_54_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         e = assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add( new short[] { 2, 6 }, -1, (short) 10));
//     }

//     @Test
//     public void testAddObjectAtIndex_55_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         assertEquals("Index: -1, Length: 2", e.getMessage());
//     }

//     @Test
//     public void testAddObjectAtIndex_56_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         assertArrayEquals(new byte[]{2, 1}, byteArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_57_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         e = assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add( (byte[]) null, -1, (byte) 2));
//     }

//     @Test
//     public void testAddObjectAtIndex_58_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         assertEquals("Index: -1, Length: 0", e.getMessage());
//     }

//     @Test
//     public void testAddObjectAtIndex_59_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         assertArrayEquals(new byte[]{2, 6, 3}, byteArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_60_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         assertArrayEquals(new byte[]{1, 2, 6}, byteArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_61_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         assertArrayEquals(new byte[]{2, 6, 1, 3}, byteArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_62_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         e = assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add( new byte[] { 2, 6 }, 4, (byte) 3));
//     }

//     @Test
//     public void testAddObjectAtIndex_63_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         assertEquals("Index: 4, Length: 2", e.getMessage());
//     }

//     @Test
//     public void testAddObjectAtIndex_64_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         e = assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add( new byte[] { 2, 6 }, -1, (byte) 3));
//     }

//     @Test
//     public void testAddObjectAtIndex_65_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         assertEquals("Index: -1, Length: 2", e.getMessage());
//     }

//     @Test
//     public void testAddObjectAtIndex_66_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         assertArrayEquals(new int[]{2, 1}, intArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_67_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         e = assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add( (int[]) null, -1, 2));
//     }

//     @Test
//     public void testAddObjectAtIndex_68_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         assertEquals("Index: -1, Length: 0", e.getMessage());
//     }

//     @Test
//     public void testAddObjectAtIndex_69_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         assertArrayEquals(new int[]{2, 6, 10}, intArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_70_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         assertArrayEquals(new int[]{-4, 2, 6}, intArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_71_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         assertArrayEquals(new int[]{2, 6, 1, 3}, intArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_72_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         e = assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add( new int[] { 2, 6 }, 4, 10));
//     }

//     @Test
//     public void testAddObjectAtIndex_73_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         assertEquals("Index: 4, Length: 2", e.getMessage());
//     }

//     @Test
//     public void testAddObjectAtIndex_74_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         e = assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add( new int[] { 2, 6 }, -1, 10));
//     }

//     @Test
//     public void testAddObjectAtIndex_75_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         assertEquals("Index: -1, Length: 2", e.getMessage());
//     }

//     @Test
//     public void testAddObjectAtIndex_76_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         assertArrayEquals(new long[]{2L, 1L}, longArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_77_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         e = assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add( (long[]) null, -1, 2L));
//     }

//     @Test
//     public void testAddObjectAtIndex_78_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         assertEquals("Index: -1, Length: 0", e.getMessage());
//     }

//     @Test
//     public void testAddObjectAtIndex_79_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 2, 10L);
//         assertArrayEquals(new long[]{2L, 6L, 10L}, longArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_80_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 2, 10L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 0, -4L);
//         assertArrayEquals(new long[]{-4L, 2L, 6L}, longArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_81_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 2, 10L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 0, -4L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L, 3L }, 2, 1L);
//         assertArrayEquals(new long[]{2L, 6L, 1L, 3L}, longArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_82_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 2, 10L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 0, -4L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L, 3L }, 2, 1L);
//         // removed other assertion
//         e = assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add( new long[] { 2L, 6L }, 4, 10L));
//     }

//     @Test
//     public void testAddObjectAtIndex_83_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 2, 10L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 0, -4L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L, 3L }, 2, 1L);
//         // removed other assertion
//         // removed other assertion
//         assertEquals("Index: 4, Length: 2", e.getMessage());
//     }

//     @Test
//     public void testAddObjectAtIndex_84_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 2, 10L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 0, -4L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L, 3L }, 2, 1L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         e = assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add( new long[] { 2L, 6L }, -1, 10L));
//     }

//     @Test
//     public void testAddObjectAtIndex_85_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 2, 10L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 0, -4L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L, 3L }, 2, 1L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         assertEquals("Index: -1, Length: 2", e.getMessage());
//     }

//     @Test
//     public void testAddObjectAtIndex_86_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 2, 10L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 0, -4L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L, 3L }, 2, 1L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // float tests
//         float[] floatArray = ArrayUtils.add( new float[] { 1.1f }, 0, 2.2f);
//         assertArrayEquals(new float[]{2.2f, 1.1f}, floatArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_87_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 2, 10L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 0, -4L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L, 3L }, 2, 1L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // float tests
//         float[] floatArray = ArrayUtils.add( new float[] { 1.1f }, 0, 2.2f);
//         // removed other assertion
//         e = assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add( (float[]) null, -1, 2.2f));
//     }

//     @Test
//     public void testAddObjectAtIndex_88_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 2, 10L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 0, -4L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L, 3L }, 2, 1L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // float tests
//         float[] floatArray = ArrayUtils.add( new float[] { 1.1f }, 0, 2.2f);
//         // removed other assertion
//         // removed other assertion
//         assertEquals("Index: -1, Length: 0", e.getMessage());
//     }

//     @Test
//     public void testAddObjectAtIndex_89_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 2, 10L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 0, -4L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L, 3L }, 2, 1L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // float tests
//         float[] floatArray = ArrayUtils.add( new float[] { 1.1f }, 0, 2.2f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.3f, 6.4f }, 2, 10.5f);
//         assertArrayEquals(new float[]{2.3f, 6.4f, 10.5f}, floatArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_90_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 2, 10L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 0, -4L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L, 3L }, 2, 1L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // float tests
//         float[] floatArray = ArrayUtils.add( new float[] { 1.1f }, 0, 2.2f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.3f, 6.4f }, 2, 10.5f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.6f, 6.7f }, 0, -4.8f);
//         assertArrayEquals(new float[]{-4.8f, 2.6f, 6.7f}, floatArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_91_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 2, 10L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 0, -4L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L, 3L }, 2, 1L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // float tests
//         float[] floatArray = ArrayUtils.add( new float[] { 1.1f }, 0, 2.2f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.3f, 6.4f }, 2, 10.5f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.6f, 6.7f }, 0, -4.8f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.9f, 6.0f, 0.3f }, 2, 1.0f);
//         assertArrayEquals(new float[]{2.9f, 6.0f, 1.0f, 0.3f}, floatArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_92_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 2, 10L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 0, -4L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L, 3L }, 2, 1L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // float tests
//         float[] floatArray = ArrayUtils.add( new float[] { 1.1f }, 0, 2.2f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.3f, 6.4f }, 2, 10.5f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.6f, 6.7f }, 0, -4.8f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.9f, 6.0f, 0.3f }, 2, 1.0f);
//         // removed other assertion
//         e = assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add( new float[] { 2.3f, 6.4f }, 4, 10.5f));
//     }

//     @Test
//     public void testAddObjectAtIndex_93_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 2, 10L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 0, -4L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L, 3L }, 2, 1L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // float tests
//         float[] floatArray = ArrayUtils.add( new float[] { 1.1f }, 0, 2.2f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.3f, 6.4f }, 2, 10.5f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.6f, 6.7f }, 0, -4.8f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.9f, 6.0f, 0.3f }, 2, 1.0f);
//         // removed other assertion
//         // removed other assertion
//         assertEquals("Index: 4, Length: 2", e.getMessage());
//     }

//     @Test
//     public void testAddObjectAtIndex_94_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 2, 10L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 0, -4L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L, 3L }, 2, 1L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // float tests
//         float[] floatArray = ArrayUtils.add( new float[] { 1.1f }, 0, 2.2f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.3f, 6.4f }, 2, 10.5f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.6f, 6.7f }, 0, -4.8f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.9f, 6.0f, 0.3f }, 2, 1.0f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         e = assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add( new float[] { 2.3f, 6.4f }, -1, 10.5f));
//     }

//     @Test
//     public void testAddObjectAtIndex_95_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 2, 10L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 0, -4L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L, 3L }, 2, 1L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // float tests
//         float[] floatArray = ArrayUtils.add( new float[] { 1.1f }, 0, 2.2f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.3f, 6.4f }, 2, 10.5f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.6f, 6.7f }, 0, -4.8f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.9f, 6.0f, 0.3f }, 2, 1.0f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         assertEquals("Index: -1, Length: 2", e.getMessage());
//     }

//     @Test
//     public void testAddObjectAtIndex_96_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 2, 10L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 0, -4L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L, 3L }, 2, 1L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // float tests
//         float[] floatArray = ArrayUtils.add( new float[] { 1.1f }, 0, 2.2f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.3f, 6.4f }, 2, 10.5f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.6f, 6.7f }, 0, -4.8f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.9f, 6.0f, 0.3f }, 2, 1.0f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // double tests
//         double[] doubleArray = ArrayUtils.add( new double[] { 1.1 }, 0, 2.2);
//         assertArrayEquals(new double[]{2.2, 1.1}, doubleArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_97_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 2, 10L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 0, -4L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L, 3L }, 2, 1L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // float tests
//         float[] floatArray = ArrayUtils.add( new float[] { 1.1f }, 0, 2.2f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.3f, 6.4f }, 2, 10.5f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.6f, 6.7f }, 0, -4.8f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.9f, 6.0f, 0.3f }, 2, 1.0f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // double tests
//         double[] doubleArray = ArrayUtils.add( new double[] { 1.1 }, 0, 2.2);
//         // removed other assertion
//         e = assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add(null, -1, 2.2));
//     }

//     @Test
//     public void testAddObjectAtIndex_98_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 2, 10L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 0, -4L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L, 3L }, 2, 1L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // float tests
//         float[] floatArray = ArrayUtils.add( new float[] { 1.1f }, 0, 2.2f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.3f, 6.4f }, 2, 10.5f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.6f, 6.7f }, 0, -4.8f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.9f, 6.0f, 0.3f }, 2, 1.0f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // double tests
//         double[] doubleArray = ArrayUtils.add( new double[] { 1.1 }, 0, 2.2);
//         // removed other assertion
//         // removed other assertion
//         assertEquals("Index: -1, Length: 0", e.getMessage());
//     }

//     @Test
//     public void testAddObjectAtIndex_99_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 2, 10L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 0, -4L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L, 3L }, 2, 1L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // float tests
//         float[] floatArray = ArrayUtils.add( new float[] { 1.1f }, 0, 2.2f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.3f, 6.4f }, 2, 10.5f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.6f, 6.7f }, 0, -4.8f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.9f, 6.0f, 0.3f }, 2, 1.0f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // double tests
//         double[] doubleArray = ArrayUtils.add( new double[] { 1.1 }, 0, 2.2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         doubleArray = ArrayUtils.add( new double[] { 2.3, 6.4 }, 2, 10.5);
//         assertArrayEquals(new double[]{2.3, 6.4, 10.5}, doubleArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_100_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 2, 10L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 0, -4L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L, 3L }, 2, 1L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // float tests
//         float[] floatArray = ArrayUtils.add( new float[] { 1.1f }, 0, 2.2f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.3f, 6.4f }, 2, 10.5f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.6f, 6.7f }, 0, -4.8f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.9f, 6.0f, 0.3f }, 2, 1.0f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // double tests
//         double[] doubleArray = ArrayUtils.add( new double[] { 1.1 }, 0, 2.2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         doubleArray = ArrayUtils.add( new double[] { 2.3, 6.4 }, 2, 10.5);
//         // removed other assertion
//         doubleArray = ArrayUtils.add( new double[] { 2.6, 6.7 }, 0, -4.8);
//         assertArrayEquals(new double[]{-4.8, 2.6, 6.7}, doubleArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_101_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 2, 10L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 0, -4L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L, 3L }, 2, 1L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // float tests
//         float[] floatArray = ArrayUtils.add( new float[] { 1.1f }, 0, 2.2f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.3f, 6.4f }, 2, 10.5f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.6f, 6.7f }, 0, -4.8f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.9f, 6.0f, 0.3f }, 2, 1.0f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // double tests
//         double[] doubleArray = ArrayUtils.add( new double[] { 1.1 }, 0, 2.2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         doubleArray = ArrayUtils.add( new double[] { 2.3, 6.4 }, 2, 10.5);
//         // removed other assertion
//         doubleArray = ArrayUtils.add( new double[] { 2.6, 6.7 }, 0, -4.8);
//         // removed other assertion
//         doubleArray = ArrayUtils.add( new double[] { 2.9, 6.0, 0.3 }, 2, 1.0);
//         assertArrayEquals(new double[]{2.9, 6.0, 1.0, 0.3}, doubleArray);
//     }

//     @Test
//     public void testAddObjectAtIndex_102_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 2, 10L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 0, -4L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L, 3L }, 2, 1L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // float tests
//         float[] floatArray = ArrayUtils.add( new float[] { 1.1f }, 0, 2.2f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.3f, 6.4f }, 2, 10.5f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.6f, 6.7f }, 0, -4.8f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.9f, 6.0f, 0.3f }, 2, 1.0f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // double tests
//         double[] doubleArray = ArrayUtils.add( new double[] { 1.1 }, 0, 2.2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         doubleArray = ArrayUtils.add( new double[] { 2.3, 6.4 }, 2, 10.5);
//         // removed other assertion
//         doubleArray = ArrayUtils.add( new double[] { 2.6, 6.7 }, 0, -4.8);
//         // removed other assertion
//         doubleArray = ArrayUtils.add( new double[] { 2.9, 6.0, 0.3 }, 2, 1.0);
//         // removed other assertion
//         e = assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add( new double[] { 2.3, 6.4 }, 4, 10.5));
//     }

//     @Test
//     public void testAddObjectAtIndex_103_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 2, 10L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 0, -4L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L, 3L }, 2, 1L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // float tests
//         float[] floatArray = ArrayUtils.add( new float[] { 1.1f }, 0, 2.2f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.3f, 6.4f }, 2, 10.5f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.6f, 6.7f }, 0, -4.8f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.9f, 6.0f, 0.3f }, 2, 1.0f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // double tests
//         double[] doubleArray = ArrayUtils.add( new double[] { 1.1 }, 0, 2.2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         doubleArray = ArrayUtils.add( new double[] { 2.3, 6.4 }, 2, 10.5);
//         // removed other assertion
//         doubleArray = ArrayUtils.add( new double[] { 2.6, 6.7 }, 0, -4.8);
//         // removed other assertion
//         doubleArray = ArrayUtils.add( new double[] { 2.9, 6.0, 0.3 }, 2, 1.0);
//         // removed other assertion
//         // removed other assertion
//         assertEquals("Index: 4, Length: 2", e.getMessage());
//     }

//     @Test
//     public void testAddObjectAtIndex_104_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 2, 10L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 0, -4L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L, 3L }, 2, 1L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // float tests
//         float[] floatArray = ArrayUtils.add( new float[] { 1.1f }, 0, 2.2f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.3f, 6.4f }, 2, 10.5f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.6f, 6.7f }, 0, -4.8f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.9f, 6.0f, 0.3f }, 2, 1.0f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // double tests
//         double[] doubleArray = ArrayUtils.add( new double[] { 1.1 }, 0, 2.2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         doubleArray = ArrayUtils.add( new double[] { 2.3, 6.4 }, 2, 10.5);
//         // removed other assertion
//         doubleArray = ArrayUtils.add( new double[] { 2.6, 6.7 }, 0, -4.8);
//         // removed other assertion
//         doubleArray = ArrayUtils.add( new double[] { 2.9, 6.0, 0.3 }, 2, 1.0);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         e = assertThrows(IndexOutOfBoundsException.class, () -> ArrayUtils.add( new double[] { 2.3, 6.4 }, -1, 10.5));
//     }

//     @Test
//     public void testAddObjectAtIndex_105_oe() {
//         Object[] newArray;
//         newArray = ArrayUtils.add((Object[]) null, 0, "a");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         final String[] stringArray1 = new String[]{"a", "b", "c"};
//         newArray = ArrayUtils.add(stringArray1, 0, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 1, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, null);
//         // removed other assertion
//         // removed other assertion
//         newArray = ArrayUtils.add(stringArray1, 3, "d");
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         final Object[] o = new Object[] {"1", "2", "4"};
//         final Object[] result = ArrayUtils.add(o, 2, "3");
//         final Object[] result2 = ArrayUtils.add(o, 3, "5");

//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // boolean tests
//         boolean[] booleanArray = ArrayUtils.add( null, 0, true );
//         // removed other assertion
//         IndexOutOfBoundsException e =
//                 // removed other assertion
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true }, 0, false);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { false }, 1, true);
//         // removed other assertion
//         booleanArray = ArrayUtils.add( new boolean[] { true, false }, 1, true);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // char tests
//         char[] charArray = ArrayUtils.add( (char[]) null, 0, 'a' );
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a' }, 0, 'b');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 0, 'c');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b' }, 1, 'k');
//         // removed other assertion
//         charArray = ArrayUtils.add( new char[] { 'a', 'b', 'c' }, 1, 't');
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // short tests
//         short[] shortArray = ArrayUtils.add( new short[] { 1 }, 0, (short) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 2, (short) 10);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6 }, 0, (short) -4);
//         // removed other assertion
//         shortArray = ArrayUtils.add( new short[] { 2, 6, 3 }, 2, (short) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // byte tests
//         byte[] byteArray = ArrayUtils.add( new byte[] { 1 }, 0, (byte) 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 2, (byte) 3);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6 }, 0, (byte) 1);
//         // removed other assertion
//         byteArray = ArrayUtils.add( new byte[] { 2, 6, 3 }, 2, (byte) 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // int tests
//         int[] intArray = ArrayUtils.add( new int[] { 1 }, 0, 2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 2, 10);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6 }, 0, -4);
//         // removed other assertion
//         intArray = ArrayUtils.add( new int[] { 2, 6, 3 }, 2, 1);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // long tests
//         long[] longArray = ArrayUtils.add( new long[] { 1L }, 0, 2L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 2, 10L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L }, 0, -4L);
//         // removed other assertion
//         longArray = ArrayUtils.add( new long[] { 2L, 6L, 3L }, 2, 1L);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // float tests
//         float[] floatArray = ArrayUtils.add( new float[] { 1.1f }, 0, 2.2f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.3f, 6.4f }, 2, 10.5f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.6f, 6.7f }, 0, -4.8f);
//         // removed other assertion
//         floatArray = ArrayUtils.add( new float[] { 2.9f, 6.0f, 0.3f }, 2, 1.0f);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion

//         // double tests
//         double[] doubleArray = ArrayUtils.add( new double[] { 1.1 }, 0, 2.2);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         doubleArray = ArrayUtils.add( new double[] { 2.3, 6.4 }, 2, 10.5);
//         // removed other assertion
//         doubleArray = ArrayUtils.add( new double[] { 2.6, 6.7 }, 0, -4.8);
//         // removed other assertion
//         doubleArray = ArrayUtils.add( new double[] { 2.9, 6.0, 0.3 }, 2, 1.0);
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         // removed other assertion
//         assertEquals("Index: -1, Length: 2", e.getMessage());
//     }

//     @Test
//     public void testJira567_1_oe() {
//         Number[] n;
//         // Valid array construction
//         n = ArrayUtils.addAll(new Number[]{Integer.valueOf(1)}, new Long[]{Long.valueOf(2)});
//         assertEquals(2, n.length);
//     }

//     @Test
//     public void testJira567_2_oe() {
//         Number[] n;
//         // Valid array construction
//         n = ArrayUtils.addAll(new Number[]{Integer.valueOf(1)}, new Long[]{Long.valueOf(2)});
//         // removed other assertion
//         assertEquals(Number.class, n.getClass().getComponentType());
//     }

//     @Test
//     public void testJira567_3_oe() {
//         Number[] n;
//         // Valid array construction
//         n = ArrayUtils.addAll(new Number[]{Integer.valueOf(1)}, new Long[]{Long.valueOf(2)});
//         // removed other assertion
//         // removed other assertion
//         // Invalid - can't store Long in Integer array
//         assertThrows(IllegalArgumentException.class, () -> ArrayUtils.addAll(new Integer[]{Integer.valueOf(1)}, new Long[]{Long.valueOf(2)}));
//     }

//     @Test
//     @SuppressWarnings("deprecation")
//     public void testLANG571_1_oe() {
//         final String[] stringArray=null;
//         final String aString=null;
//         assertThrows(IllegalArgumentException.class, () -> ArrayUtils.add(stringArray, aString));
//     }

//     @Test
//     @SuppressWarnings("deprecation")
//     public void testLANG571_2_oe() {
//         final String[] stringArray=null;
//         final String aString=null;
//         // removed other assertion
//         assertThrows(IllegalArgumentException.class, () -> ArrayUtils.add(stringArray, 0, aString));
//     }

// }
