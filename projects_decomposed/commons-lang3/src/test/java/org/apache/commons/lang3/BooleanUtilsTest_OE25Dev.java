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

import static org.apache.commons.lang3.ArraySorter.sort;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.BooleanUtils}.
 */
public class BooleanUtilsTest_OE25Dev {

    @Test
    public void test_toBoolean_int_int_int_noMatch_1_oe() throws Exception {
        try {
    BooleanUtils.toBoolean(8, 6, 7);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_toBoolean_Integer_Integer_Integer_noMatch_1_oe() throws Exception {
        try {
    BooleanUtils.toBoolean(Integer.valueOf(8), Integer.valueOf(6), Integer.valueOf(7));
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_toBoolean_Integer_Integer_Integer_nullValue_1_oe() throws Exception {
        try {
    BooleanUtils.toBoolean(null, Integer.valueOf(6), Integer.valueOf(7));
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_toBoolean_String_String_String_noMatch_1_oe() throws Exception {
        try {
    BooleanUtils.toBoolean("X", "Y", "N");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_toBoolean_String_String_String_nullValue_1_oe() throws Exception {
        try {
    BooleanUtils.toBoolean(null, "Y", "N");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_toBooleanObject_int_int_int_noMatch_1_oe() throws Exception {
        try {
    BooleanUtils.toBooleanObject(9, 6, 7, 8);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_toBooleanObject_Integer_Integer_Integer_Integer_noMatch_1_oe() throws Exception {
        try {
    BooleanUtils.toBooleanObject(Integer.valueOf(9), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8));
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_toBooleanObject_Integer_Integer_Integer_Integer_nullValue_1_oe() throws Exception {
        try {
    BooleanUtils.toBooleanObject(null, Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8));
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_toBooleanObject_String_String_String_String_noMatch_1_oe() throws Exception {
        try {
    BooleanUtils.toBooleanObject("X", "Y", "N", "U");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_toBooleanObject_String_String_String_String_nullValue_1_oe() throws Exception {
        try {
    BooleanUtils.toBooleanObject(null, "Y", "N", "U");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testAnd_object_emptyInput_1_oe() throws Exception {
        try {
    BooleanUtils.and(new Boolean[] {});
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testAnd_object_nullElementInput_1_oe() throws Exception {
        try {
    BooleanUtils.and(new Boolean[] {null});
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testAnd_object_nullInput_1_oe() throws Exception {
        try {
    BooleanUtils.and((Boolean[]) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testAnd_primitive_emptyInput_1_oe() throws Exception {
        try {
    BooleanUtils.and(new boolean[] {});
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testAnd_primitive_nullInput_1_oe() throws Exception {
        try {
    BooleanUtils.and((boolean[]) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testOr_object_emptyInput_1_oe() throws Exception {
        try {
    BooleanUtils.or(new Boolean[] {});
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testOr_object_nullElementInput_1_oe() throws Exception {
        try {
    BooleanUtils.or(new Boolean[] {null});
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testOr_object_nullInput_1_oe() throws Exception {
        try {
    BooleanUtils.or((Boolean[]) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testOr_primitive_emptyInput_1_oe() throws Exception {
        try {
    BooleanUtils.or(new boolean[] {});
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testOr_primitive_nullInput_1_oe() throws Exception {
        try {
    BooleanUtils.or((boolean[]) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testXor_object_emptyInput_1_oe() throws Exception {
        try {
    BooleanUtils.xor(new Boolean[] {});
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testXor_object_nullElementInput_1_oe() throws Exception {
        try {
    BooleanUtils.xor(new Boolean[] {null});
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testXor_object_nullInput_1_oe() throws Exception {
        try {
    BooleanUtils.xor((Boolean[]) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testXor_primitive_emptyInput_1_oe() throws Exception {
        try {
    BooleanUtils.xor(new boolean[] {});
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testXor_primitive_nullInput_1_oe() throws Exception {
        try {
    BooleanUtils.xor((boolean[]) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

}
