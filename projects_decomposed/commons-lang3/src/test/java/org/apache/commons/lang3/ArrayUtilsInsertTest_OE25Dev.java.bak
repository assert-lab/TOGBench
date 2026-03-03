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
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests ArrayUtils insert methods.
 */
public class ArrayUtilsInsertTest_OE25Dev {

    @Test
    public void testInsertBooleans_1_oe() {
        final boolean[] array = {true, false, true};
        final boolean[] values = {false, true, false};

        final boolean[] result = ArrayUtils.insert(42, array, null);
        assertArrayEquals(array, result);
    }

    @Test
    public void testInsertBooleans_2_oe() {
        final boolean[] array = {true, false, true};
        final boolean[] values = {false, true, false};

        final boolean[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        assertNotSame(array, result);
    }

    @Test
    public void testInsertBooleans_3_oe() {
        final boolean[] array = {true, false, true};
        final boolean[] values = {false, true, false};

        final boolean[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        assertNull(ArrayUtils.insert(42, null, array));
    }

    @Test
    public void testInsertBooleans_4_oe() {
        final boolean[] array = {true, false, true};
        final boolean[] values = {false, true, false};

        final boolean[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertArrayEquals(new boolean[0], ArrayUtils.insert(0, new boolean[0], null));
    }

    @Test
    public void testInsertBooleans_5_oe() {
        final boolean[] array = {true, false, true};
        final boolean[] values = {false, true, false};

        final boolean[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertNull(ArrayUtils.insert(42, (boolean[]) null, null));
    }

    @Test
    public void testInsertBooleans_6_oe() throws Exception {
        final boolean[] array = {true, false, true};
        final boolean[] values = {false, true, false};

        final boolean[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    ArrayUtils.insert(-1, array, array);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsertBooleans_7_oe() throws Exception {
        final boolean[] array = {true, false, true};
        final boolean[] values = {false, true, false};

        final boolean[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        try {
    ArrayUtils.insert(array.length + 1, array, array);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsertBooleans_8_oe() {
        final boolean[] array = {true, false, true};
        final boolean[] values = {false, true, false};

        final boolean[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertArrayEquals(new boolean[]{false, true, false, true}, ArrayUtils.insert(0, array, false));
    }

    @Test
    public void testInsertBooleans_9_oe() {
        final boolean[] array = {true, false, true};
        final boolean[] values = {false, true, false};

        final boolean[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertArrayEquals(new boolean[]{true, false, false, true}, ArrayUtils.insert(1, array, false));
    }

    @Test
    public void testInsertBooleans_10_oe() {
        final boolean[] array = {true, false, true};
        final boolean[] values = {false, true, false};

        final boolean[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertArrayEquals(new boolean[]{true, false, true, false}, ArrayUtils.insert(array.length, array, false));
    }

    @Test
    public void testInsertBooleans_11_oe() {
        final boolean[] array = {true, false, true};
        final boolean[] values = {false, true, false};

        final boolean[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new boolean[]{false, true, false, true, false, true}, ArrayUtils.insert(0, array, values));
    }

    @Test
    public void testInsertBooleans_12_oe() {
        final boolean[] array = {true, false, true};
        final boolean[] values = {false, true, false};

        final boolean[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new boolean[]{true, false, true, false, false, true}, ArrayUtils.insert(1, array, values));
    }

    @Test
    public void testInsertBooleans_13_oe() {
        final boolean[] array = {true, false, true};
        final boolean[] values = {false, true, false};

        final boolean[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new boolean[]{true, false, true, false, true, false}, ArrayUtils.insert(array.length, array, values));
    }

    @Test
    public void testInsertBytes_1_oe() {
        final byte[] array = {1, 2, 3};
        final byte[] values = {4, 5, 6};

        final byte[] result = ArrayUtils.insert(42, array, null);
        assertArrayEquals(array, result);
    }

    @Test
    public void testInsertBytes_2_oe() {
        final byte[] array = {1, 2, 3};
        final byte[] values = {4, 5, 6};

        final byte[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        assertNotSame(array, result);
    }

    @Test
    public void testInsertBytes_3_oe() {
        final byte[] array = {1, 2, 3};
        final byte[] values = {4, 5, 6};

        final byte[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        assertNull(ArrayUtils.insert(42, null, array));
    }

    @Test
    public void testInsertBytes_4_oe() {
        final byte[] array = {1, 2, 3};
        final byte[] values = {4, 5, 6};

        final byte[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertArrayEquals(new byte[0], ArrayUtils.insert(0, new byte[0], null));
    }

    @Test
    public void testInsertBytes_5_oe() {
        final byte[] array = {1, 2, 3};
        final byte[] values = {4, 5, 6};

        final byte[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertNull(ArrayUtils.insert(42, (byte[]) null, null));
    }

    @Test
    public void testInsertBytes_6_oe() throws Exception {
        final byte[] array = {1, 2, 3};
        final byte[] values = {4, 5, 6};

        final byte[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    ArrayUtils.insert(-1, array, array);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsertBytes_7_oe() throws Exception {
        final byte[] array = {1, 2, 3};
        final byte[] values = {4, 5, 6};

        final byte[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        try {
    ArrayUtils.insert(array.length + 1, array, array);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsertBytes_8_oe() {
        final byte[] array = {1, 2, 3};
        final byte[] values = {4, 5, 6};

        final byte[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertArrayEquals(new byte[]{0, 1, 2, 3}, ArrayUtils.insert(0, array, (byte) 0));
    }

    @Test
    public void testInsertBytes_9_oe() {
        final byte[] array = {1, 2, 3};
        final byte[] values = {4, 5, 6};

        final byte[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertArrayEquals(new byte[]{1, 0, 2, 3}, ArrayUtils.insert(1, array, (byte) 0));
    }

    @Test
    public void testInsertBytes_10_oe() {
        final byte[] array = {1, 2, 3};
        final byte[] values = {4, 5, 6};

        final byte[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertArrayEquals(new byte[]{1, 2, 3, 0}, ArrayUtils.insert(array.length, array, (byte) 0));
    }

    @Test
    public void testInsertBytes_11_oe() {
        final byte[] array = {1, 2, 3};
        final byte[] values = {4, 5, 6};

        final byte[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new byte[]{4, 5, 6, 1, 2, 3}, ArrayUtils.insert(0, array, values));
    }

    @Test
    public void testInsertBytes_12_oe() {
        final byte[] array = {1, 2, 3};
        final byte[] values = {4, 5, 6};

        final byte[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new byte[]{1, 4, 5, 6, 2, 3}, ArrayUtils.insert(1, array, values));
    }

    @Test
    public void testInsertBytes_13_oe() {
        final byte[] array = {1, 2, 3};
        final byte[] values = {4, 5, 6};

        final byte[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new byte[]{1, 2, 3, 4, 5, 6}, ArrayUtils.insert(array.length, array, values));
    }

    @Test
    public void testInsertChars_1_oe() {
        final char[] array = {'a', 'b', 'c'};
        final char[] values = {'d', 'e', 'f'};

        final char[] result = ArrayUtils.insert(42, array, null);
        assertArrayEquals(array, result);
    }

    @Test
    public void testInsertChars_2_oe() {
        final char[] array = {'a', 'b', 'c'};
        final char[] values = {'d', 'e', 'f'};

        final char[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        assertNotSame(array, result);
    }

    @Test
    public void testInsertChars_3_oe() {
        final char[] array = {'a', 'b', 'c'};
        final char[] values = {'d', 'e', 'f'};

        final char[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        assertNull(ArrayUtils.insert(42, null, array));
    }

    @Test
    public void testInsertChars_4_oe() {
        final char[] array = {'a', 'b', 'c'};
        final char[] values = {'d', 'e', 'f'};

        final char[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertArrayEquals(new char[0], ArrayUtils.insert(0, new char[0], null));
    }

    @Test
    public void testInsertChars_5_oe() {
        final char[] array = {'a', 'b', 'c'};
        final char[] values = {'d', 'e', 'f'};

        final char[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertNull(ArrayUtils.insert(42, (char[]) null, null));
    }

    @Test
    public void testInsertChars_6_oe() throws Exception {
        final char[] array = {'a', 'b', 'c'};
        final char[] values = {'d', 'e', 'f'};

        final char[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    ArrayUtils.insert(-1, array, array);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsertChars_7_oe() throws Exception {
        final char[] array = {'a', 'b', 'c'};
        final char[] values = {'d', 'e', 'f'};

        final char[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        try {
    ArrayUtils.insert(array.length + 1, array, array);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsertChars_8_oe() {
        final char[] array = {'a', 'b', 'c'};
        final char[] values = {'d', 'e', 'f'};

        final char[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertArrayEquals(new char[]{'z', 'a', 'b', 'c'}, ArrayUtils.insert(0, array, 'z'));
    }

    @Test
    public void testInsertChars_9_oe() {
        final char[] array = {'a', 'b', 'c'};
        final char[] values = {'d', 'e', 'f'};

        final char[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertArrayEquals(new char[]{'a', 'z', 'b', 'c'}, ArrayUtils.insert(1, array, 'z'));
    }

    @Test
    public void testInsertChars_10_oe() {
        final char[] array = {'a', 'b', 'c'};
        final char[] values = {'d', 'e', 'f'};

        final char[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertArrayEquals(new char[]{'a', 'b', 'c', 'z'}, ArrayUtils.insert(array.length, array, 'z'));
    }

    @Test
    public void testInsertChars_11_oe() {
        final char[] array = {'a', 'b', 'c'};
        final char[] values = {'d', 'e', 'f'};

        final char[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new char[]{'d', 'e', 'f', 'a', 'b', 'c'}, ArrayUtils.insert(0, array, values));
    }

    @Test
    public void testInsertChars_12_oe() {
        final char[] array = {'a', 'b', 'c'};
        final char[] values = {'d', 'e', 'f'};

        final char[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new char[]{'a', 'd', 'e', 'f', 'b', 'c'}, ArrayUtils.insert(1, array, values));
    }

    @Test
    public void testInsertChars_13_oe() {
        final char[] array = {'a', 'b', 'c'};
        final char[] values = {'d', 'e', 'f'};

        final char[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new char[]{'a', 'b', 'c', 'd', 'e', 'f'}, ArrayUtils.insert(array.length, array, values));
    }

    @Test
    public void testInsertDoubles_1_oe() {
        final double[] array = {1, 2, 3};
        final double[] values = {4, 5, 6};
        final double delta = 0.000001;

        final double[] result = ArrayUtils.insert(42, array, null);
        assertArrayEquals(array, result, delta);
    }

    @Test
    public void testInsertDoubles_2_oe() {
        final double[] array = {1, 2, 3};
        final double[] values = {4, 5, 6};
        final double delta = 0.000001;

        final double[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        assertNotSame(array, result);
    }

    @Test
    public void testInsertDoubles_3_oe() {
        final double[] array = {1, 2, 3};
        final double[] values = {4, 5, 6};
        final double delta = 0.000001;

        final double[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        assertNull(ArrayUtils.insert(42, null, array));
    }

    @Test
    public void testInsertDoubles_4_oe() {
        final double[] array = {1, 2, 3};
        final double[] values = {4, 5, 6};
        final double delta = 0.000001;

        final double[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertArrayEquals(new double[0], ArrayUtils.insert(0, new double[0], null), delta);
    }

    @Test
    public void testInsertDoubles_5_oe() {
        final double[] array = {1, 2, 3};
        final double[] values = {4, 5, 6};
        final double delta = 0.000001;

        final double[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertNull(ArrayUtils.insert(42, (double[]) null, null));
    }

    @Test
    public void testInsertDoubles_6_oe() throws Exception {
        final double[] array = {1, 2, 3};
        final double[] values = {4, 5, 6};
        final double delta = 0.000001;

        final double[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    ArrayUtils.insert(-1, array, array);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsertDoubles_7_oe() throws Exception {
        final double[] array = {1, 2, 3};
        final double[] values = {4, 5, 6};
        final double delta = 0.000001;

        final double[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        try {
    ArrayUtils.insert(array.length + 1, array, array);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsertDoubles_8_oe() {
        final double[] array = {1, 2, 3};
        final double[] values = {4, 5, 6};
        final double delta = 0.000001;

        final double[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertArrayEquals(new double[]{0, 1, 2, 3}, ArrayUtils.insert(0, array, 0), delta);
    }

    @Test
    public void testInsertDoubles_9_oe() {
        final double[] array = {1, 2, 3};
        final double[] values = {4, 5, 6};
        final double delta = 0.000001;

        final double[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertArrayEquals(new double[]{1, 0, 2, 3}, ArrayUtils.insert(1, array, 0), delta);
    }

    @Test
    public void testInsertDoubles_10_oe() {
        final double[] array = {1, 2, 3};
        final double[] values = {4, 5, 6};
        final double delta = 0.000001;

        final double[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertArrayEquals(new double[]{1, 2, 3, 0}, ArrayUtils.insert(array.length, array, 0), delta);
    }

    @Test
    public void testInsertDoubles_11_oe() {
        final double[] array = {1, 2, 3};
        final double[] values = {4, 5, 6};
        final double delta = 0.000001;

        final double[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new double[]{4, 5, 6, 1, 2, 3}, ArrayUtils.insert(0, array, values), delta);
    }

    @Test
    public void testInsertDoubles_12_oe() {
        final double[] array = {1, 2, 3};
        final double[] values = {4, 5, 6};
        final double delta = 0.000001;

        final double[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new double[]{1, 4, 5, 6, 2, 3}, ArrayUtils.insert(1, array, values), delta);
    }

    @Test
    public void testInsertDoubles_13_oe() {
        final double[] array = {1, 2, 3};
        final double[] values = {4, 5, 6};
        final double delta = 0.000001;

        final double[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new double[]{1, 2, 3, 4, 5, 6}, ArrayUtils.insert(array.length, array, values), delta);
    }

    @Test
    public void testInsertFloats_1_oe() {
        final float[] array = {1, 2, 3};
        final float[] values = {4, 5, 6};
        final float delta = 0.000001f;

        final float[] result = ArrayUtils.insert(42, array, null);
        assertArrayEquals(array, result, delta);
    }

    @Test
    public void testInsertFloats_2_oe() {
        final float[] array = {1, 2, 3};
        final float[] values = {4, 5, 6};
        final float delta = 0.000001f;

        final float[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        assertNotSame(array, result);
    }

    @Test
    public void testInsertFloats_3_oe() {
        final float[] array = {1, 2, 3};
        final float[] values = {4, 5, 6};
        final float delta = 0.000001f;

        final float[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        assertNull(ArrayUtils.insert(42, null, array));
    }

    @Test
    public void testInsertFloats_4_oe() {
        final float[] array = {1, 2, 3};
        final float[] values = {4, 5, 6};
        final float delta = 0.000001f;

        final float[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertArrayEquals(new float[0], ArrayUtils.insert(0, new float[0], null), delta);
    }

    @Test
    public void testInsertFloats_5_oe() {
        final float[] array = {1, 2, 3};
        final float[] values = {4, 5, 6};
        final float delta = 0.000001f;

        final float[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertNull(ArrayUtils.insert(42, (float[]) null, null));
    }

    @Test
    public void testInsertFloats_6_oe() throws Exception {
        final float[] array = {1, 2, 3};
        final float[] values = {4, 5, 6};
        final float delta = 0.000001f;

        final float[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    ArrayUtils.insert(-1, array, array);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsertFloats_7_oe() throws Exception {
        final float[] array = {1, 2, 3};
        final float[] values = {4, 5, 6};
        final float delta = 0.000001f;

        final float[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        try {
    ArrayUtils.insert(array.length + 1, array, array);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsertFloats_8_oe() {
        final float[] array = {1, 2, 3};
        final float[] values = {4, 5, 6};
        final float delta = 0.000001f;

        final float[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertArrayEquals(new float[]{0, 1, 2, 3}, ArrayUtils.insert(0, array, 0), delta);
    }

    @Test
    public void testInsertFloats_9_oe() {
        final float[] array = {1, 2, 3};
        final float[] values = {4, 5, 6};
        final float delta = 0.000001f;

        final float[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertArrayEquals(new float[]{1, 0, 2, 3}, ArrayUtils.insert(1, array, 0), delta);
    }

    @Test
    public void testInsertFloats_10_oe() {
        final float[] array = {1, 2, 3};
        final float[] values = {4, 5, 6};
        final float delta = 0.000001f;

        final float[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertArrayEquals(new float[]{1, 2, 3, 0}, ArrayUtils.insert(array.length, array, 0), delta);
    }

    @Test
    public void testInsertFloats_11_oe() {
        final float[] array = {1, 2, 3};
        final float[] values = {4, 5, 6};
        final float delta = 0.000001f;

        final float[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new float[]{4, 5, 6, 1, 2, 3}, ArrayUtils.insert(0, array, values), delta);
    }

    @Test
    public void testInsertFloats_12_oe() {
        final float[] array = {1, 2, 3};
        final float[] values = {4, 5, 6};
        final float delta = 0.000001f;

        final float[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new float[]{1, 4, 5, 6, 2, 3}, ArrayUtils.insert(1, array, values), delta);
    }

    @Test
    public void testInsertFloats_13_oe() {
        final float[] array = {1, 2, 3};
        final float[] values = {4, 5, 6};
        final float delta = 0.000001f;

        final float[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new float[]{1, 2, 3, 4, 5, 6}, ArrayUtils.insert(array.length, array, values), delta);
    }

    @Test
    public void testInsertGenericArray_1_oe() {
        final String[] array = {"a", "b", "c"};
        final String[] values = {"d", "e", "f"};

        final String[] result = ArrayUtils.insert(42, array, (String[]) null);
        assertArrayEquals(array, result);
    }

    @Test
    public void testInsertGenericArray_2_oe() {
        final String[] array = {"a", "b", "c"};
        final String[] values = {"d", "e", "f"};

        final String[] result = ArrayUtils.insert(42, array, (String[]) null);
        // removed other assertion
        assertNotSame(array, result);
    }

    @Test
    public void testInsertGenericArray_3_oe() {
        final String[] array = {"a", "b", "c"};
        final String[] values = {"d", "e", "f"};

        final String[] result = ArrayUtils.insert(42, array, (String[]) null);
        // removed other assertion
        // removed other assertion

        assertNull(ArrayUtils.insert(42, null, array));
    }

    @Test
    public void testInsertGenericArray_4_oe() {
        final String[] array = {"a", "b", "c"};
        final String[] values = {"d", "e", "f"};

        final String[] result = ArrayUtils.insert(42, array, (String[]) null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertArrayEquals(new String[0], ArrayUtils.insert(0, new String[0], (String[]) null));
    }

    @Test
    public void testInsertGenericArray_5_oe() {
        final String[] array = {"a", "b", "c"};
        final String[] values = {"d", "e", "f"};

        final String[] result = ArrayUtils.insert(42, array, (String[]) null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertNull(ArrayUtils.insert(42, null, (String[]) null));
    }

    @Test
    public void testInsertGenericArray_6_oe() throws Exception {
        final String[] array = {"a", "b", "c"};
        final String[] values = {"d", "e", "f"};

        final String[] result = ArrayUtils.insert(42, array, (String[]) null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    ArrayUtils.insert(-1, array, array);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsertGenericArray_7_oe() throws Exception {
        final String[] array = {"a", "b", "c"};
        final String[] values = {"d", "e", "f"};

        final String[] result = ArrayUtils.insert(42, array, (String[]) null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        try {
    ArrayUtils.insert(array.length + 1, array, array);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsertGenericArray_8_oe() {
        final String[] array = {"a", "b", "c"};
        final String[] values = {"d", "e", "f"};

        final String[] result = ArrayUtils.insert(42, array, (String[]) null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertArrayEquals(new String[]{"z", "a", "b", "c"}, ArrayUtils.insert(0, array, "z"));
    }

    @Test
    public void testInsertGenericArray_9_oe() {
        final String[] array = {"a", "b", "c"};
        final String[] values = {"d", "e", "f"};

        final String[] result = ArrayUtils.insert(42, array, (String[]) null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertArrayEquals(new String[]{"a", "z", "b", "c"}, ArrayUtils.insert(1, array, "z"));
    }

    @Test
    public void testInsertGenericArray_10_oe() {
        final String[] array = {"a", "b", "c"};
        final String[] values = {"d", "e", "f"};

        final String[] result = ArrayUtils.insert(42, array, (String[]) null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertArrayEquals(new String[]{"a", "b", "c", "z"}, ArrayUtils.insert(array.length, array, "z"));
    }

    @Test
    public void testInsertGenericArray_11_oe() {
        final String[] array = {"a", "b", "c"};
        final String[] values = {"d", "e", "f"};

        final String[] result = ArrayUtils.insert(42, array, (String[]) null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new String[]{"d", "e", "f", "a", "b", "c"}, ArrayUtils.insert(0, array, values));
    }

    @Test
    public void testInsertGenericArray_12_oe() {
        final String[] array = {"a", "b", "c"};
        final String[] values = {"d", "e", "f"};

        final String[] result = ArrayUtils.insert(42, array, (String[]) null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new String[]{"a", "d", "e", "f", "b", "c"}, ArrayUtils.insert(1, array, values));
    }

    @Test
    public void testInsertGenericArray_13_oe() {
        final String[] array = {"a", "b", "c"};
        final String[] values = {"d", "e", "f"};

        final String[] result = ArrayUtils.insert(42, array, (String[]) null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new String[]{"a", "b", "c", "d", "e", "f"}, ArrayUtils.insert(array.length, array, values));
    }

    @Test
    public void testInsertInts_1_oe() {
        final int[] array = {1, 2, 3};
        final int[] values = {4, 5, 6};

        final int[] result = ArrayUtils.insert(42, array, null);
        assertArrayEquals(array, result);
    }

    @Test
    public void testInsertInts_2_oe() {
        final int[] array = {1, 2, 3};
        final int[] values = {4, 5, 6};

        final int[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        assertNotSame(array, result);
    }

    @Test
    public void testInsertInts_3_oe() {
        final int[] array = {1, 2, 3};
        final int[] values = {4, 5, 6};

        final int[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        assertNull(ArrayUtils.insert(42, null, array));
    }

    @Test
    public void testInsertInts_4_oe() {
        final int[] array = {1, 2, 3};
        final int[] values = {4, 5, 6};

        final int[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertArrayEquals(new int[0], ArrayUtils.insert(0, new int[0], null));
    }

    @Test
    public void testInsertInts_5_oe() {
        final int[] array = {1, 2, 3};
        final int[] values = {4, 5, 6};

        final int[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertNull(ArrayUtils.insert(42, (int[]) null, null));
    }

    @Test
    public void testInsertInts_6_oe() throws Exception {
        final int[] array = {1, 2, 3};
        final int[] values = {4, 5, 6};

        final int[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    ArrayUtils.insert(-1, array, array);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsertInts_7_oe() throws Exception {
        final int[] array = {1, 2, 3};
        final int[] values = {4, 5, 6};

        final int[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        try {
    ArrayUtils.insert(array.length + 1, array, array);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsertInts_8_oe() {
        final int[] array = {1, 2, 3};
        final int[] values = {4, 5, 6};

        final int[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertArrayEquals(new int[]{0, 1, 2, 3}, ArrayUtils.insert(0, array, 0));
    }

    @Test
    public void testInsertInts_9_oe() {
        final int[] array = {1, 2, 3};
        final int[] values = {4, 5, 6};

        final int[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertArrayEquals(new int[]{1, 0, 2, 3}, ArrayUtils.insert(1, array, 0));
    }

    @Test
    public void testInsertInts_10_oe() {
        final int[] array = {1, 2, 3};
        final int[] values = {4, 5, 6};

        final int[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertArrayEquals(new int[]{1, 2, 3, 0}, ArrayUtils.insert(array.length, array, 0));
    }

    @Test
    public void testInsertInts_11_oe() {
        final int[] array = {1, 2, 3};
        final int[] values = {4, 5, 6};

        final int[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new int[]{4, 5, 6, 1, 2, 3}, ArrayUtils.insert(0, array, values));
    }

    @Test
    public void testInsertInts_12_oe() {
        final int[] array = {1, 2, 3};
        final int[] values = {4, 5, 6};

        final int[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new int[]{1, 4, 5, 6, 2, 3}, ArrayUtils.insert(1, array, values));
    }

    @Test
    public void testInsertInts_13_oe() {
        final int[] array = {1, 2, 3};
        final int[] values = {4, 5, 6};

        final int[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, ArrayUtils.insert(array.length, array, values));
    }

    @Test
    public void testInsertLongs_1_oe() {
        final long[] array = {1, 2, 3};
        final long[] values = {4, 5, 6};

        final long[] result = ArrayUtils.insert(42, array, null);
        assertArrayEquals(array, result);
    }

    @Test
    public void testInsertLongs_2_oe() {
        final long[] array = {1, 2, 3};
        final long[] values = {4, 5, 6};

        final long[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        assertNotSame(array, result);
    }

    @Test
    public void testInsertLongs_3_oe() {
        final long[] array = {1, 2, 3};
        final long[] values = {4, 5, 6};

        final long[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        assertNull(ArrayUtils.insert(42, null, array));
    }

    @Test
    public void testInsertLongs_4_oe() {
        final long[] array = {1, 2, 3};
        final long[] values = {4, 5, 6};

        final long[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertArrayEquals(new long[0], ArrayUtils.insert(0, new long[0], null));
    }

    @Test
    public void testInsertLongs_5_oe() {
        final long[] array = {1, 2, 3};
        final long[] values = {4, 5, 6};

        final long[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertNull(ArrayUtils.insert(42, (long[]) null, null));
    }

    @Test
    public void testInsertLongs_6_oe() throws Exception {
        final long[] array = {1, 2, 3};
        final long[] values = {4, 5, 6};

        final long[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    ArrayUtils.insert(-1, array, array);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsertLongs_7_oe() throws Exception {
        final long[] array = {1, 2, 3};
        final long[] values = {4, 5, 6};

        final long[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        try {
    ArrayUtils.insert(array.length + 1, array, array);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsertLongs_8_oe() {
        final long[] array = {1, 2, 3};
        final long[] values = {4, 5, 6};

        final long[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertArrayEquals(new long[]{0, 1, 2, 3}, ArrayUtils.insert(0, array, 0));
    }

    @Test
    public void testInsertLongs_9_oe() {
        final long[] array = {1, 2, 3};
        final long[] values = {4, 5, 6};

        final long[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertArrayEquals(new long[]{1, 0, 2, 3}, ArrayUtils.insert(1, array, 0));
    }

    @Test
    public void testInsertLongs_10_oe() {
        final long[] array = {1, 2, 3};
        final long[] values = {4, 5, 6};

        final long[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertArrayEquals(new long[]{1, 2, 3, 0}, ArrayUtils.insert(array.length, array, 0));
    }

    @Test
    public void testInsertLongs_11_oe() {
        final long[] array = {1, 2, 3};
        final long[] values = {4, 5, 6};

        final long[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new long[]{4, 5, 6, 1, 2, 3}, ArrayUtils.insert(0, array, values));
    }

    @Test
    public void testInsertLongs_12_oe() {
        final long[] array = {1, 2, 3};
        final long[] values = {4, 5, 6};

        final long[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new long[]{1, 4, 5, 6, 2, 3}, ArrayUtils.insert(1, array, values));
    }

    @Test
    public void testInsertLongs_13_oe() {
        final long[] array = {1, 2, 3};
        final long[] values = {4, 5, 6};

        final long[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new long[]{1, 2, 3, 4, 5, 6}, ArrayUtils.insert(array.length, array, values));
    }

    @Test
    public void testInsertShorts_1_oe() {
        final short[] array = {1, 2, 3};
        final short[] values = {4, 5, 6};

        final short[] result = ArrayUtils.insert(42, array, null);
        assertArrayEquals(array, result);
    }

    @Test
    public void testInsertShorts_2_oe() {
        final short[] array = {1, 2, 3};
        final short[] values = {4, 5, 6};

        final short[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        assertNotSame(array, result);
    }

    @Test
    public void testInsertShorts_3_oe() {
        final short[] array = {1, 2, 3};
        final short[] values = {4, 5, 6};

        final short[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        assertNull(ArrayUtils.insert(42, null, array));
    }

    @Test
    public void testInsertShorts_4_oe() {
        final short[] array = {1, 2, 3};
        final short[] values = {4, 5, 6};

        final short[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertArrayEquals(new short[0], ArrayUtils.insert(0, new short[0], null));
    }

    @Test
    public void testInsertShorts_5_oe() {
        final short[] array = {1, 2, 3};
        final short[] values = {4, 5, 6};

        final short[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertNull(ArrayUtils.insert(42, (short[]) null, null));
    }

    @Test
    public void testInsertShorts_6_oe() throws Exception {
        final short[] array = {1, 2, 3};
        final short[] values = {4, 5, 6};

        final short[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    ArrayUtils.insert(-1, array, array);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsertShorts_7_oe() throws Exception {
        final short[] array = {1, 2, 3};
        final short[] values = {4, 5, 6};

        final short[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        try {
    ArrayUtils.insert(array.length + 1, array, array);
    fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    public void testInsertShorts_8_oe() {
        final short[] array = {1, 2, 3};
        final short[] values = {4, 5, 6};

        final short[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertArrayEquals(new short[]{0, 1, 2, 3}, ArrayUtils.insert(0, array, (short) 0));
    }

    @Test
    public void testInsertShorts_9_oe() {
        final short[] array = {1, 2, 3};
        final short[] values = {4, 5, 6};

        final short[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertArrayEquals(new short[]{1, 0, 2, 3}, ArrayUtils.insert(1, array, (short) 0));
    }

    @Test
    public void testInsertShorts_10_oe() {
        final short[] array = {1, 2, 3};
        final short[] values = {4, 5, 6};

        final short[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertArrayEquals(new short[]{1, 2, 3, 0}, ArrayUtils.insert(array.length, array, (short) 0));
    }

    @Test
    public void testInsertShorts_11_oe() {
        final short[] array = {1, 2, 3};
        final short[] values = {4, 5, 6};

        final short[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new short[]{4, 5, 6, 1, 2, 3}, ArrayUtils.insert(0, array, values));
    }

    @Test
    public void testInsertShorts_12_oe() {
        final short[] array = {1, 2, 3};
        final short[] values = {4, 5, 6};

        final short[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new short[]{1, 4, 5, 6, 2, 3}, ArrayUtils.insert(1, array, values));
    }

    @Test
    public void testInsertShorts_13_oe() {
        final short[] array = {1, 2, 3};
        final short[] values = {4, 5, 6};

        final short[] result = ArrayUtils.insert(42, array, null);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(new short[]{1, 2, 3, 4, 5, 6}, ArrayUtils.insert(array.length, array, values));
    }

}
