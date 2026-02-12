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

/**
 * Tests ArrayUtils insert methods.
 */
public class ArrayUtilsInsertTest_OE25Dev {

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
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
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
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
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
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
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
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
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
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
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
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
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
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
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
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
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
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
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
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
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
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
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
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
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
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
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
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
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
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
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
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
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
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
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
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

}
