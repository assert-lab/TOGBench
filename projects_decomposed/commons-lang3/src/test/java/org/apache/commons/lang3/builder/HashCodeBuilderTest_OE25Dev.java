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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests {@link org.apache.commons.lang3.builder.HashCodeBuilder}.
 */
public class HashCodeBuilderTest_OE25Dev {

    /**
     * A reflection test fixture.
     */
    static class ReflectionTestCycleA {
        ReflectionTestCycleB b;

        @Override
        public int hashCode() {
            return HashCodeBuilder.reflectionHashCode(this);
        }
    }

    /**
     * A reflection test fixture.
     */
    static class ReflectionTestCycleB {
        ReflectionTestCycleA a;

        @Override
        public int hashCode() {
            return HashCodeBuilder.reflectionHashCode(this);
        }
    }

    // -----------------------------------------------------------------------

    static class TestObject {
        private int a;

        TestObject(final int a) {
            this.a = a;
        }

        @Override
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof TestObject)) {
                return false;
            }
            final TestObject rhs = (TestObject) o;
            return a == rhs.a;
        }

        @Override
        public int hashCode() {
            return a;
        }

        public void setA(final int a) {
            this.a = a;
        }

        public int getA() {
            return a;
        }
    }

    static class TestSubObject extends TestObject {
        private int b;

        @SuppressWarnings("unused")
        private transient int t;

        TestSubObject() {
            super(0);
        }

        TestSubObject(final int a, final int b, final int t) {
            super(a);
            this.b = b;
            this.t = t;
        }

        @Override
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof TestSubObject)) {
                return false;
            }
            final TestSubObject rhs = (TestSubObject) o;
            return super.equals(o) && b == rhs.b;
        }

        @Override
        public int hashCode() {
            return b*17 + super.hashCode();
        }

    }

    static class TestObjectWithMultipleFields {
        @SuppressWarnings("unused")
        private int one = 0;

        @SuppressWarnings("unused")
        private int two = 0;

        @SuppressWarnings("unused")
        private int three = 0;

        TestObjectWithMultipleFields(final int one, final int two, final int three) {
            this.one = one;
            this.two = two;
            this.three = three;
        }
    }

    /**
     * Test Objects pointing to each other.
     */

    /**
     * Ensures LANG-520 remains true
     */

    static class TestObjectHashCodeExclude {
        @HashCodeExclude
        private final int a;
        private final int b;

        TestObjectHashCodeExclude(final int a, final int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }
    }

    static class TestObjectHashCodeExclude2 {
        @HashCodeExclude
        private final int a;
        @HashCodeExclude
        private final int b;

        TestObjectHashCodeExclude2(final int a, final int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }
    }

    @Test
    public void testConstructorExZero_1_oe() throws Exception {
        try {
    new HashCodeBuilder(0, 0);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testConstructorExEvenFirst_1_oe() throws Exception {
        try {
    new HashCodeBuilder(2, 3);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testConstructorExEvenSecond_1_oe() throws Exception {
        try {
    new HashCodeBuilder(3, 2);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testConstructorExEvenNegative_1_oe() throws Exception {
        try {
    new HashCodeBuilder(-2, -2);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReflectionHashCode_1_oe() {
        assertEquals(17 * 37, HashCodeBuilder.reflectionHashCode(new TestObject(0)));
    }

    @Test
    public void testReflectionHashCode_2_oe() {
        // removed other assertion
        assertEquals(17 * 37 + 123456, HashCodeBuilder.reflectionHashCode(new TestObject(123456)));
    }

    @Test
    public void testReflectionHierarchyHashCode_1_oe() {
        assertEquals(17 * 37 * 37, HashCodeBuilder.reflectionHashCode(new TestSubObject(0, 0, 0)));
    }

    @Test
    public void testReflectionHierarchyHashCode_2_oe() {
        // removed other assertion
        assertEquals(17 * 37 * 37 * 37, HashCodeBuilder.reflectionHashCode(new TestSubObject(0, 0, 0), true));
    }

    @Test
    public void testReflectionHierarchyHashCode_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals((17 * 37 + 7890)* 37 + 123456,HashCodeBuilder.reflectionHashCode(new TestSubObject(123456,7890,0)));
    }

    @Test
    public void testReflectionHierarchyHashCode_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(((17 * 37 + 7890)* 37 + 0)* 37 + 123456,HashCodeBuilder.reflectionHashCode(new TestSubObject(123456,7890,0),true));
    }

    @Test
    public void testReflectionHierarchyHashCodeEx1_1_oe() throws Exception {
        try {
    HashCodeBuilder.reflectionHashCode(0, 0, new TestSubObject(0, 0, 0), true);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReflectionHierarchyHashCodeEx2_1_oe() throws Exception {
        try {
    HashCodeBuilder.reflectionHashCode(2, 2, new TestSubObject(0, 0, 0), true);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReflectionHashCodeEx1_1_oe() throws Exception {
        try {
    HashCodeBuilder.reflectionHashCode(0, 0, new TestObject(0), true);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReflectionHashCodeEx2_1_oe() throws Exception {
        try {
    HashCodeBuilder.reflectionHashCode(2, 2, new TestObject(0), true);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReflectionHashCodeEx3_1_oe() throws Exception {
        try {
    HashCodeBuilder.reflectionHashCode(13, 19, null, true);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testSuper_1_oe() {
        final Object obj = new Object();
        assertEquals(17 * 37 + 19 * 41 + obj.hashCode(),new HashCodeBuilder(17,37).appendSuper(new HashCodeBuilder(19,41).append(obj).toHashCode()).toHashCode());
    }

    @Test
    public void testObject_1_oe() {
        Object obj = null;
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testObject_2_oe() {
        Object obj = null;
        // removed other assertion
        obj = new Object();
        assertEquals(17 * 37 + obj.hashCode(), new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testObjectBuild_1_oe() {
        Object obj = null;
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append(obj).build().intValue());
    }

    @Test
    public void testObjectBuild_2_oe() {
        Object obj = null;
        // removed other assertion
        obj = new Object();
        assertEquals(17 * 37 + obj.hashCode(), new HashCodeBuilder(17, 37).append(obj).build().intValue());
    }

    @Test
    @SuppressWarnings("cast") // cast is not really needed, keep for consistency
    public void testLong_1_oe() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append(0L).toHashCode());
    }

    @Test
    @SuppressWarnings("cast") // cast is not really needed, keep for consistency
    public void testLong_2_oe() {
        // removed other assertion
        assertEquals(17 * 37 +(int)(123456789L ^ 123456789L >> 32),new HashCodeBuilder(17,37).append(123456789L).toHashCode());
    }

    @Test
    @SuppressWarnings("cast") // cast is not really needed, keep for consistency
    public void testInt_1_oe() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append(0).toHashCode());
    }

    @Test
    @SuppressWarnings("cast") // cast is not really needed, keep for consistency
    public void testInt_2_oe() {
        // removed other assertion
        assertEquals(17 * 37 + 123456, new HashCodeBuilder(17, 37).append(123456).toHashCode());
    }

    @Test
    public void testShort_1_oe() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((short) 0).toHashCode());
    }

    @Test
    public void testShort_2_oe() {
        // removed other assertion
        assertEquals(17 * 37 + 12345, new HashCodeBuilder(17, 37).append((short) 12345).toHashCode());
    }

    @Test
    public void testChar_1_oe() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((char) 0).toHashCode());
    }

    @Test
    public void testChar_2_oe() {
        // removed other assertion
        assertEquals(17 * 37 + 1234, new HashCodeBuilder(17, 37).append((char) 1234).toHashCode());
    }

    @Test
    public void testByte_1_oe() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((byte) 0).toHashCode());
    }

    @Test
    public void testByte_2_oe() {
        // removed other assertion
        assertEquals(17 * 37 + 123, new HashCodeBuilder(17, 37).append((byte) 123).toHashCode());
    }

    @Test
    @SuppressWarnings("cast") // cast is not really needed, keep for consistency
    public void testDouble_1_oe() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append(0d).toHashCode());
    }

    @Test
    @SuppressWarnings("cast") // cast is not really needed, keep for consistency
    public void testDouble_2_oe() {
        // removed other assertion
        final double d = 1234567.89;
        final long l = Double.doubleToLongBits(d);
        assertEquals(17 * 37 + (int) (l ^ l >> 32), new HashCodeBuilder(17, 37).append(d).toHashCode());
    }

    @Test
    @SuppressWarnings("cast") // cast is not really needed, keep for consistency
    public void testFloat_1_oe() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append(0f).toHashCode());
    }

    @Test
    @SuppressWarnings("cast") // cast is not really needed, keep for consistency
    public void testFloat_2_oe() {
        // removed other assertion
        final float f = 1234.89f;
        final int i = Float.floatToIntBits(f);
        assertEquals(17 * 37 + i, new HashCodeBuilder(17, 37).append(f).toHashCode());
    }

    @Test
    public void testBoolean_1_oe() {
        assertEquals(17 * 37 + 0, new HashCodeBuilder(17, 37).append(true).toHashCode());
    }

    @Test
    public void testBoolean_2_oe() {
        // removed other assertion
        assertEquals(17 * 37 + 1, new HashCodeBuilder(17, 37).append(false).toHashCode());
    }

    @Test
    public void testObjectArray_1_oe() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((Object[]) null).toHashCode());
    }

    @Test
    public void testObjectArray_2_oe() {
        // removed other assertion
        final Object[] obj = new Object[2];
        assertEquals(17 * 37 * 37, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testObjectArray_3_oe() {
        // removed other assertion
        final Object[] obj = new Object[2];
        // removed other assertion
        obj[0] = new Object();
        assertEquals((17 * 37 + obj[0].hashCode()) * 37, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testObjectArray_4_oe() {
        // removed other assertion
        final Object[] obj = new Object[2];
        // removed other assertion
        obj[0] = new Object();
        // removed other assertion
        obj[1] = new Object();
        assertEquals((17 * 37 + obj[0].hashCode())* 37 + obj[1].hashCode(),new HashCodeBuilder(17,37).append(obj).toHashCode());
    }

    @Test
    public void testObjectArrayAsObject_1_oe() {
        final Object[] obj = new Object[2];
        assertEquals(17 * 37 * 37, new HashCodeBuilder(17, 37).append((Object) obj).toHashCode());
    }

    @Test
    public void testObjectArrayAsObject_2_oe() {
        final Object[] obj = new Object[2];
        // removed other assertion
        obj[0] = new Object();
        assertEquals((17 * 37 + obj[0].hashCode()) * 37, new HashCodeBuilder(17, 37).append((Object) obj).toHashCode());
    }

    @Test
    public void testObjectArrayAsObject_3_oe() {
        final Object[] obj = new Object[2];
        // removed other assertion
        obj[0] = new Object();
        // removed other assertion
        obj[1] = new Object();
        assertEquals((17 * 37 + obj[0].hashCode())* 37 + obj[1].hashCode(),new HashCodeBuilder(17,37).append((Object)obj).toHashCode());
    }

    @Test
    public void testLongArray_1_oe() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((long[]) null).toHashCode());
    }

    @Test
    public void testLongArray_2_oe() {
        // removed other assertion
        final long[] obj = new long[2];
        assertEquals(17 * 37 * 37, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testLongArray_3_oe() {
        // removed other assertion
        final long[] obj = new long[2];
        // removed other assertion
        obj[0] = 5L;
        final int h1 = (int) (5L ^ 5L >> 32);
        assertEquals((17 * 37 + h1) * 37, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testLongArray_4_oe() {
        // removed other assertion
        final long[] obj = new long[2];
        // removed other assertion
        obj[0] = 5L;
        final int h1 = (int) (5L ^ 5L >> 32);
        // removed other assertion
        obj[1] = 6L;
        final int h2 = (int) (6L ^ 6L >> 32);
        assertEquals((17 * 37 + h1) * 37 + h2, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testLongArrayAsObject_1_oe() {
        final long[] obj = new long[2];
        assertEquals(17 * 37 * 37, new HashCodeBuilder(17, 37).append((Object) obj).toHashCode());
    }

    @Test
    public void testLongArrayAsObject_2_oe() {
        final long[] obj = new long[2];
        // removed other assertion
        obj[0] = 5L;
        final int h1 = (int) (5L ^ 5L >> 32);
        assertEquals((17 * 37 + h1) * 37, new HashCodeBuilder(17, 37).append((Object) obj).toHashCode());
    }

    @Test
    public void testLongArrayAsObject_3_oe() {
        final long[] obj = new long[2];
        // removed other assertion
        obj[0] = 5L;
        final int h1 = (int) (5L ^ 5L >> 32);
        // removed other assertion
        obj[1] = 6L;
        final int h2 = (int) (6L ^ 6L >> 32);
        assertEquals((17 * 37 + h1) * 37 + h2, new HashCodeBuilder(17, 37).append((Object) obj).toHashCode());
    }

    @Test
    public void testIntArray_1_oe() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((int[]) null).toHashCode());
    }

    @Test
    public void testIntArray_2_oe() {
        // removed other assertion
        final int[] obj = new int[2];
        assertEquals(17 * 37 * 37, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testIntArray_3_oe() {
        // removed other assertion
        final int[] obj = new int[2];
        // removed other assertion
        obj[0] = 5;
        assertEquals((17 * 37 + 5) * 37, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testIntArray_4_oe() {
        // removed other assertion
        final int[] obj = new int[2];
        // removed other assertion
        obj[0] = 5;
        // removed other assertion
        obj[1] = 6;
        assertEquals((17 * 37 + 5) * 37 + 6, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testIntArrayAsObject_1_oe() {
        final int[] obj = new int[2];
        assertEquals(17 * 37 * 37, new HashCodeBuilder(17, 37).append((Object) obj).toHashCode());
    }

    @Test
    public void testIntArrayAsObject_2_oe() {
        final int[] obj = new int[2];
        // removed other assertion
        obj[0] = 5;
        assertEquals((17 * 37 + 5) * 37, new HashCodeBuilder(17, 37).append((Object) obj).toHashCode());
    }

    @Test
    public void testIntArrayAsObject_3_oe() {
        final int[] obj = new int[2];
        // removed other assertion
        obj[0] = 5;
        // removed other assertion
        obj[1] = 6;
        assertEquals((17 * 37 + 5) * 37 + 6, new HashCodeBuilder(17, 37).append((Object) obj).toHashCode());
    }

    @Test
    public void testShortArray_1_oe() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((short[]) null).toHashCode());
    }

    @Test
    public void testShortArray_2_oe() {
        // removed other assertion
        final short[] obj = new short[2];
        assertEquals(17 * 37 * 37, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testShortArray_3_oe() {
        // removed other assertion
        final short[] obj = new short[2];
        // removed other assertion
        obj[0] = (short) 5;
        assertEquals((17 * 37 + 5) * 37, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testShortArray_4_oe() {
        // removed other assertion
        final short[] obj = new short[2];
        // removed other assertion
        obj[0] = (short) 5;
        // removed other assertion
        obj[1] = (short) 6;
        assertEquals((17 * 37 + 5) * 37 + 6, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testShortArrayAsObject_1_oe() {
        final short[] obj = new short[2];
        assertEquals(17 * 37 * 37, new HashCodeBuilder(17, 37).append((Object) obj).toHashCode());
    }

    @Test
    public void testShortArrayAsObject_2_oe() {
        final short[] obj = new short[2];
        // removed other assertion
        obj[0] = (short) 5;
        assertEquals((17 * 37 + 5) * 37, new HashCodeBuilder(17, 37).append((Object) obj).toHashCode());
    }

    @Test
    public void testShortArrayAsObject_3_oe() {
        final short[] obj = new short[2];
        // removed other assertion
        obj[0] = (short) 5;
        // removed other assertion
        obj[1] = (short) 6;
        assertEquals((17 * 37 + 5) * 37 + 6, new HashCodeBuilder(17, 37).append((Object) obj).toHashCode());
    }

    @Test
    public void testCharArray_1_oe() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((char[]) null).toHashCode());
    }

    @Test
    public void testCharArray_2_oe() {
        // removed other assertion
        final char[] obj = new char[2];
        assertEquals(17 * 37 * 37, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testCharArray_3_oe() {
        // removed other assertion
        final char[] obj = new char[2];
        // removed other assertion
        obj[0] = (char) 5;
        assertEquals((17 * 37 + 5) * 37, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testCharArray_4_oe() {
        // removed other assertion
        final char[] obj = new char[2];
        // removed other assertion
        obj[0] = (char) 5;
        // removed other assertion
        obj[1] = (char) 6;
        assertEquals((17 * 37 + 5) * 37 + 6, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testCharArrayAsObject_1_oe() {
        final char[] obj = new char[2];
        assertEquals(17 * 37 * 37, new HashCodeBuilder(17, 37).append((Object) obj).toHashCode());
    }

    @Test
    public void testCharArrayAsObject_2_oe() {
        final char[] obj = new char[2];
        // removed other assertion
        obj[0] = (char) 5;
        assertEquals((17 * 37 + 5) * 37, new HashCodeBuilder(17, 37).append((Object) obj).toHashCode());
    }

    @Test
    public void testCharArrayAsObject_3_oe() {
        final char[] obj = new char[2];
        // removed other assertion
        obj[0] = (char) 5;
        // removed other assertion
        obj[1] = (char) 6;
        assertEquals((17 * 37 + 5) * 37 + 6, new HashCodeBuilder(17, 37).append((Object) obj).toHashCode());
    }

    @Test
    public void testByteArray_1_oe() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((byte[]) null).toHashCode());
    }

    @Test
    public void testByteArray_2_oe() {
        // removed other assertion
        final byte[] obj = new byte[2];
        assertEquals(17 * 37 * 37, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testByteArray_3_oe() {
        // removed other assertion
        final byte[] obj = new byte[2];
        // removed other assertion
        obj[0] = (byte) 5;
        assertEquals((17 * 37 + 5) * 37, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testByteArray_4_oe() {
        // removed other assertion
        final byte[] obj = new byte[2];
        // removed other assertion
        obj[0] = (byte) 5;
        // removed other assertion
        obj[1] = (byte) 6;
        assertEquals((17 * 37 + 5) * 37 + 6, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testByteArrayAsObject_1_oe() {
        final byte[] obj = new byte[2];
        assertEquals(17 * 37 * 37, new HashCodeBuilder(17, 37).append((Object) obj).toHashCode());
    }

    @Test
    public void testByteArrayAsObject_2_oe() {
        final byte[] obj = new byte[2];
        // removed other assertion
        obj[0] = (byte) 5;
        assertEquals((17 * 37 + 5) * 37, new HashCodeBuilder(17, 37).append((Object) obj).toHashCode());
    }

    @Test
    public void testByteArrayAsObject_3_oe() {
        final byte[] obj = new byte[2];
        // removed other assertion
        obj[0] = (byte) 5;
        // removed other assertion
        obj[1] = (byte) 6;
        assertEquals((17 * 37 + 5) * 37 + 6, new HashCodeBuilder(17, 37).append((Object) obj).toHashCode());
    }

    @Test
    public void testDoubleArray_1_oe() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((double[]) null).toHashCode());
    }

    @Test
    public void testDoubleArray_2_oe() {
        // removed other assertion
        final double[] obj = new double[2];
        assertEquals(17 * 37 * 37, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testDoubleArray_3_oe() {
        // removed other assertion
        final double[] obj = new double[2];
        // removed other assertion
        obj[0] = 5.4d;
        final long l1 = Double.doubleToLongBits(5.4d);
        final int h1 = (int) (l1 ^ l1 >> 32);
        assertEquals((17 * 37 + h1) * 37, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testDoubleArray_4_oe() {
        // removed other assertion
        final double[] obj = new double[2];
        // removed other assertion
        obj[0] = 5.4d;
        final long l1 = Double.doubleToLongBits(5.4d);
        final int h1 = (int) (l1 ^ l1 >> 32);
        // removed other assertion
        obj[1] = 6.3d;
        final long l2 = Double.doubleToLongBits(6.3d);
        final int h2 = (int) (l2 ^ l2 >> 32);
        assertEquals((17 * 37 + h1) * 37 + h2, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testDoubleArrayAsObject_1_oe() {
        final double[] obj = new double[2];
        assertEquals(17 * 37 * 37, new HashCodeBuilder(17, 37).append((Object) obj).toHashCode());
    }

    @Test
    public void testDoubleArrayAsObject_2_oe() {
        final double[] obj = new double[2];
        // removed other assertion
        obj[0] = 5.4d;
        final long l1 = Double.doubleToLongBits(5.4d);
        final int h1 = (int) (l1 ^ l1 >> 32);
        assertEquals((17 * 37 + h1) * 37, new HashCodeBuilder(17, 37).append((Object) obj).toHashCode());
    }

    @Test
    public void testDoubleArrayAsObject_3_oe() {
        final double[] obj = new double[2];
        // removed other assertion
        obj[0] = 5.4d;
        final long l1 = Double.doubleToLongBits(5.4d);
        final int h1 = (int) (l1 ^ l1 >> 32);
        // removed other assertion
        obj[1] = 6.3d;
        final long l2 = Double.doubleToLongBits(6.3d);
        final int h2 = (int) (l2 ^ l2 >> 32);
        assertEquals((17 * 37 + h1) * 37 + h2, new HashCodeBuilder(17, 37).append((Object) obj).toHashCode());
    }

    @Test
    public void testFloatArray_1_oe() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((float[]) null).toHashCode());
    }

    @Test
    public void testFloatArray_2_oe() {
        // removed other assertion
        final float[] obj = new float[2];
        assertEquals(17 * 37 * 37, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testFloatArray_3_oe() {
        // removed other assertion
        final float[] obj = new float[2];
        // removed other assertion
        obj[0] = 5.4f;
        final int h1 = Float.floatToIntBits(5.4f);
        assertEquals((17 * 37 + h1) * 37, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testFloatArray_4_oe() {
        // removed other assertion
        final float[] obj = new float[2];
        // removed other assertion
        obj[0] = 5.4f;
        final int h1 = Float.floatToIntBits(5.4f);
        // removed other assertion
        obj[1] = 6.3f;
        final int h2 = Float.floatToIntBits(6.3f);
        assertEquals((17 * 37 + h1) * 37 + h2, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testFloatArrayAsObject_1_oe() {
        final float[] obj = new float[2];
        assertEquals(17 * 37 * 37, new HashCodeBuilder(17, 37).append((Object) obj).toHashCode());
    }

    @Test
    public void testFloatArrayAsObject_2_oe() {
        final float[] obj = new float[2];
        // removed other assertion
        obj[0] = 5.4f;
        final int h1 = Float.floatToIntBits(5.4f);
        assertEquals((17 * 37 + h1) * 37, new HashCodeBuilder(17, 37).append((Object) obj).toHashCode());
    }

    @Test
    public void testFloatArrayAsObject_3_oe() {
        final float[] obj = new float[2];
        // removed other assertion
        obj[0] = 5.4f;
        final int h1 = Float.floatToIntBits(5.4f);
        // removed other assertion
        obj[1] = 6.3f;
        final int h2 = Float.floatToIntBits(6.3f);
        assertEquals((17 * 37 + h1) * 37 + h2, new HashCodeBuilder(17, 37).append((Object) obj).toHashCode());
    }

    @Test
    public void testBooleanArray_1_oe() {
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append((boolean[]) null).toHashCode());
    }

    @Test
    public void testBooleanArray_2_oe() {
        // removed other assertion
        final boolean[] obj = new boolean[2];
        assertEquals((17 * 37 + 1) * 37 + 1, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testBooleanArray_3_oe() {
        // removed other assertion
        final boolean[] obj = new boolean[2];
        // removed other assertion
        obj[0] = true;
        assertEquals((17 * 37 + 0) * 37 + 1, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testBooleanArray_4_oe() {
        // removed other assertion
        final boolean[] obj = new boolean[2];
        // removed other assertion
        obj[0] = true;
        // removed other assertion
        obj[1] = false;
        assertEquals((17 * 37 + 0) * 37 + 1, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testBooleanArrayAsObject_1_oe() {
        final boolean[] obj = new boolean[2];
        assertEquals((17 * 37 + 1) * 37 + 1, new HashCodeBuilder(17, 37).append((Object) obj).toHashCode());
    }

    @Test
    public void testBooleanArrayAsObject_2_oe() {
        final boolean[] obj = new boolean[2];
        // removed other assertion
        obj[0] = true;
        assertEquals((17 * 37 + 0) * 37 + 1, new HashCodeBuilder(17, 37).append((Object) obj).toHashCode());
    }

    @Test
    public void testBooleanArrayAsObject_3_oe() {
        final boolean[] obj = new boolean[2];
        // removed other assertion
        obj[0] = true;
        // removed other assertion
        obj[1] = false;
        assertEquals((17 * 37 + 0) * 37 + 1, new HashCodeBuilder(17, 37).append((Object) obj).toHashCode());
    }

    @Test
    public void testBooleanMultiArray_1_oe() {
        final boolean[][] obj = new boolean[2][];
        assertEquals(17 * 37 * 37, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testBooleanMultiArray_2_oe() {
        final boolean[][] obj = new boolean[2][];
        // removed other assertion
        obj[0] = new boolean[0];
        assertEquals(17 * 37, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testBooleanMultiArray_3_oe() {
        final boolean[][] obj = new boolean[2][];
        // removed other assertion
        obj[0] = new boolean[0];
        // removed other assertion
        obj[0] = new boolean[1];
        assertEquals((17 * 37 + 1) * 37, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testBooleanMultiArray_4_oe() {
        final boolean[][] obj = new boolean[2][];
        // removed other assertion
        obj[0] = new boolean[0];
        // removed other assertion
        obj[0] = new boolean[1];
        // removed other assertion
        obj[0] = new boolean[2];
        assertEquals(((17 * 37 + 1) * 37 + 1) * 37, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testBooleanMultiArray_5_oe() {
        final boolean[][] obj = new boolean[2][];
        // removed other assertion
        obj[0] = new boolean[0];
        // removed other assertion
        obj[0] = new boolean[1];
        // removed other assertion
        obj[0] = new boolean[2];
        // removed other assertion
        obj[0][0] = true;
        assertEquals(((17 * 37 + 0) * 37 + 1) * 37, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testBooleanMultiArray_6_oe() {
        final boolean[][] obj = new boolean[2][];
        // removed other assertion
        obj[0] = new boolean[0];
        // removed other assertion
        obj[0] = new boolean[1];
        // removed other assertion
        obj[0] = new boolean[2];
        // removed other assertion
        obj[0][0] = true;
        // removed other assertion
        obj[1] = new boolean[1];
        assertEquals(((17 * 37 + 0) * 37 + 1) * 37 + 1, new HashCodeBuilder(17, 37).append(obj).toHashCode());
    }

    @Test
    public void testReflectionHashCodeExcludeFields_1_oe() {
        final TestObjectWithMultipleFields x = new TestObjectWithMultipleFields(1, 2, 3);

        assertEquals(((17 * 37 + 1) * 37 + 3) * 37 + 2, HashCodeBuilder.reflectionHashCode(x));
    }

    @Test
    public void testReflectionHashCodeExcludeFields_2_oe() {
        final TestObjectWithMultipleFields x = new TestObjectWithMultipleFields(1, 2, 3);

        // removed other assertion

        assertEquals(((17 * 37 + 1) * 37 + 3) * 37 + 2, HashCodeBuilder.reflectionHashCode(x, (String[]) null));
    }

    @Test
    public void testReflectionHashCodeExcludeFields_3_oe() {
        final TestObjectWithMultipleFields x = new TestObjectWithMultipleFields(1, 2, 3);

        // removed other assertion

        // removed other assertion
        assertEquals(((17 * 37 + 1) * 37 + 3) * 37 + 2, HashCodeBuilder.reflectionHashCode(x));
    }

    @Test
    public void testReflectionHashCodeExcludeFields_4_oe() {
        final TestObjectWithMultipleFields x = new TestObjectWithMultipleFields(1, 2, 3);

        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(((17 * 37 + 1) * 37 + 3) * 37 + 2, HashCodeBuilder.reflectionHashCode(x, "xxx"));
    }

    @Test
    public void testReflectionHashCodeExcludeFields_5_oe() {
        final TestObjectWithMultipleFields x = new TestObjectWithMultipleFields(1, 2, 3);

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals((17 * 37 + 1) * 37 + 3, HashCodeBuilder.reflectionHashCode(x, "two"));
    }

    @Test
    public void testReflectionHashCodeExcludeFields_6_oe() {
        final TestObjectWithMultipleFields x = new TestObjectWithMultipleFields(1, 2, 3);

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals((17 * 37 + 1) * 37 + 2, HashCodeBuilder.reflectionHashCode(x, "three"));
    }

    @Test
    public void testReflectionHashCodeExcludeFields_7_oe() {
        final TestObjectWithMultipleFields x = new TestObjectWithMultipleFields(1, 2, 3);

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals(17 * 37 + 1, HashCodeBuilder.reflectionHashCode(x, "two", "three"));
    }

    @Test
    public void testReflectionHashCodeExcludeFields_8_oe() {
        final TestObjectWithMultipleFields x = new TestObjectWithMultipleFields(1, 2, 3);

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        assertEquals(17, HashCodeBuilder.reflectionHashCode(x, "one", "two", "three"));
    }

    @Test
    public void testReflectionHashCodeExcludeFields_9_oe() {
        final TestObjectWithMultipleFields x = new TestObjectWithMultipleFields(1, 2, 3);

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion
        assertEquals(17, HashCodeBuilder.reflectionHashCode(x, "one", "two", "three", "xxx"));
    }

    @Test
    public void testReflectionObjectCycle_1_oe() {
        final ReflectionTestCycleA a = new ReflectionTestCycleA();
        final ReflectionTestCycleB b = new ReflectionTestCycleB();
        a.b = b;
        b.a = a;

        // Used to caused:
        // java.lang.StackOverflowError
        // at java.lang.ClassLoader.getCallerClassLoader(Native Method)
        // at java.lang.Class.getDeclaredFields(Class.java:992)
        // at org.apache.commons.lang.builder.HashCodeBuilder.reflectionAppend(HashCodeBuilder.java:373)
        // at org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode(HashCodeBuilder.java:349)
        // at org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode(HashCodeBuilder.java:155)
        // at
        // org.apache.commons.lang.builder.HashCodeBuilderTest$ReflectionTestCycleB.hashCode(HashCodeBuilderTest.java:53)
        // at org.apache.commons.lang.builder.HashCodeBuilder.append(HashCodeBuilder.java:422)
        // at org.apache.commons.lang.builder.HashCodeBuilder.reflectionAppend(HashCodeBuilder.java:383)
        // at org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode(HashCodeBuilder.java:349)
        // at org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode(HashCodeBuilder.java:155)
        // at
        // org.apache.commons.lang.builder.HashCodeBuilderTest$ReflectionTestCycleA.hashCode(HashCodeBuilderTest.java:42)
        // at org.apache.commons.lang.builder.HashCodeBuilder.append(HashCodeBuilder.java:422)

        a.hashCode();
        assertNull(HashCodeBuilder.getRegistry());
    }

    @Test
    public void testReflectionObjectCycle_2_oe() {
        final ReflectionTestCycleA a = new ReflectionTestCycleA();
        final ReflectionTestCycleB b = new ReflectionTestCycleB();
        a.b = b;
        b.a = a;

        // Used to caused:
        // java.lang.StackOverflowError
        // at java.lang.ClassLoader.getCallerClassLoader(Native Method)
        // at java.lang.Class.getDeclaredFields(Class.java:992)
        // at org.apache.commons.lang.builder.HashCodeBuilder.reflectionAppend(HashCodeBuilder.java:373)
        // at org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode(HashCodeBuilder.java:349)
        // at org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode(HashCodeBuilder.java:155)
        // at
        // org.apache.commons.lang.builder.HashCodeBuilderTest$ReflectionTestCycleB.hashCode(HashCodeBuilderTest.java:53)
        // at org.apache.commons.lang.builder.HashCodeBuilder.append(HashCodeBuilder.java:422)
        // at org.apache.commons.lang.builder.HashCodeBuilder.reflectionAppend(HashCodeBuilder.java:383)
        // at org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode(HashCodeBuilder.java:349)
        // at org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode(HashCodeBuilder.java:155)
        // at
        // org.apache.commons.lang.builder.HashCodeBuilderTest$ReflectionTestCycleA.hashCode(HashCodeBuilderTest.java:42)
        // at org.apache.commons.lang.builder.HashCodeBuilder.append(HashCodeBuilder.java:422)

        a.hashCode();
        // removed other assertion
        b.hashCode();
        assertNull(HashCodeBuilder.getRegistry());
    }

    @Test
    public void testToHashCodeEqualsHashCode_1_oe() {
        final HashCodeBuilder hcb = new HashCodeBuilder(17, 37).append(new Object()).append('a');
        assertEquals(hcb.toHashCode(),hcb.hashCode(),"hashCode()is no longer returning the same value as toHashCode()- see LANG-520");
    }

    @Test
    public void testToHashCodeExclude_1_oe() {
        final TestObjectHashCodeExclude one = new TestObjectHashCodeExclude(1, 2);
        final TestObjectHashCodeExclude2 two = new TestObjectHashCodeExclude2(1, 2);
        assertEquals(17 * 37 + 2, HashCodeBuilder.reflectionHashCode(one));
    }

    @Test
    public void testToHashCodeExclude_2_oe() {
        final TestObjectHashCodeExclude one = new TestObjectHashCodeExclude(1, 2);
        final TestObjectHashCodeExclude2 two = new TestObjectHashCodeExclude2(1, 2);
        // removed other assertion
        assertEquals(17, HashCodeBuilder.reflectionHashCode(two));
    }

}
