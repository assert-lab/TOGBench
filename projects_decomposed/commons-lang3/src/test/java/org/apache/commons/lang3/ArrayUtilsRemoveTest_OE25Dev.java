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
    public void testRemoveBooleanArray_9_oe() throws Exception {
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
        try {
    ArrayUtils.remove(new boolean[] {true, false}, -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveBooleanArray_10_oe() throws Exception {
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
        try {
    ArrayUtils.remove(new boolean[] {true, false}, 2);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveBooleanArray_11_oe() throws Exception {
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
        try {
    ArrayUtils.remove((boolean[]) null, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveByteArray_9_oe() throws Exception {
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
        try {
    ArrayUtils.remove(new byte[] {1, 2}, -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveByteArray_10_oe() throws Exception {
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
        try {
    ArrayUtils.remove(new byte[] {1, 2}, 2);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveByteArray_11_oe() throws Exception {
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
        try {
    ArrayUtils.remove((byte[]) null, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveCharArray_9_oe() throws Exception {
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
        try {
    ArrayUtils.remove(new char[] {'a', 'b'}, -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveCharArray_10_oe() throws Exception {
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
        try {
    ArrayUtils.remove(new char[] {'a', 'b'}, 2);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveCharArray_11_oe() throws Exception {
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
        try {
    ArrayUtils.remove((char[]) null, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveDoubleArray_9_oe() throws Exception {
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
        try {
    ArrayUtils.remove(new double[] {1, 2}, -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveDoubleArray_10_oe() throws Exception {
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
        try {
    ArrayUtils.remove(new double[] {1, 2}, 2);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveDoubleArray_11_oe() throws Exception {
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
        try {
    ArrayUtils.remove((double[]) null, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveFloatArray_9_oe() throws Exception {
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
        try {
    ArrayUtils.remove(new float[] {1, 2}, -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveFloatArray_10_oe() throws Exception {
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
        try {
    ArrayUtils.remove(new float[] {1, 2}, 2);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveFloatArray_11_oe() throws Exception {
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
        try {
    ArrayUtils.remove((float[]) null, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveIntArray_9_oe() throws Exception {
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
        try {
    ArrayUtils.remove(new int[] {1, 2}, -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveIntArray_10_oe() throws Exception {
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
        try {
    ArrayUtils.remove(new int[] {1, 2}, 2);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveIntArray_11_oe() throws Exception {
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
        try {
    ArrayUtils.remove((int[]) null, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveLongArray_9_oe() throws Exception {
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
        try {
    ArrayUtils.remove(new long[] {1, 2}, -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveLongArray_10_oe() throws Exception {
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
        try {
    ArrayUtils.remove(new long[] {1, 2}, 2);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveLongArray_11_oe() throws Exception {
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
        try {
    ArrayUtils.remove((long[]) null, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveObjectArray_9_oe() throws Exception {
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
        try {
    ArrayUtils.remove(new Object[] {"a", "b"}, -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveObjectArray_10_oe() throws Exception {
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
        try {
    ArrayUtils.remove(new Object[] {"a", "b"}, 2);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveObjectArray_11_oe() throws Exception {
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
        try {
    ArrayUtils.remove((Object[]) null, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveShortArray_9_oe() throws Exception {
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
        try {
    ArrayUtils.remove(new short[] {1, 2}, -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveShortArray_10_oe() throws Exception {
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
        try {
    ArrayUtils.remove(new short[] {1, 2}, 2);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveShortArray_11_oe() throws Exception {
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
        try {
    ArrayUtils.remove((short[]) null, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

}
