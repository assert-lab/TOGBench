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

/**
 * Tests ArrayUtils remove and removeElement methods.
 */
public class ArrayUtilsRemoveMultipleTest_OE25Dev {

    @Test
    public void testRemoveAllBooleanArrayNegativeIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new boolean[] { true, false }, -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllBooleanArrayOutOfBoundsIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new boolean[] { true, false }, 2);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllByteArrayNegativeIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new byte[] { 1, 2 }, -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllByteArrayOutOfBoundsIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new byte[] { 1, 2 }, 2);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllCharArrayNegativeIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new char[] { 'a', 'b' }, -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllCharArrayOutOfBoundsIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new char[] { 'a', 'b' }, 2);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllDoubleArrayNegativeIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new double[] { 1, 2 }, -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllDoubleArrayOutOfBoundsIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new double[] { 1, 2 }, 2);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllFloatArrayNegativeIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new float[] { 1, 2 }, -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllFloatArrayOutOfBoundsIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new float[] { 1, 2 }, 2);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllIntArrayNegativeIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new int[] { 1, 2 }, -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllIntArrayOutOfBoundsIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new int[] { 1, 2 }, 2);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllLongArrayNegativeIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new long[] { 1, 2 }, -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllLongArrayOutOfBoundsIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new long[] { 1, 2 }, 2);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllNullBooleanArray_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll((boolean[]) null, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllNullByteArray_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll((byte[]) null, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllNullCharArray_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll((char[]) null, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllNullDoubleArray_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll((double[]) null, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllNullFloatArray_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll((float[]) null, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllNullIntArray_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll((int[]) null, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllNullLongArray_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll((long[]) null, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllNullObjectArray_1_oe() throws Exception {
        try {
    ArrayUtils.remove((Object[]) null, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllNullShortArray_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll((short[]) null, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllObjectArrayNegativeIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new Object[] { "a", "b" }, -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllObjectArrayOutOfBoundsIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new Object[] { "a", "b" }, 2);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllShortArrayNegativeIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new short[] { 1, 2 }, -1, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testRemoveAllShortArrayOutOfBoundsIndex_1_oe() throws Exception {
        try {
    ArrayUtils.removeAll(new short[] { 1, 2 }, 2, 0);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

}
