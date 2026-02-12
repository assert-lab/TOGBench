/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 */
public class EnumUtilsTest_OE25Dev {

    @Test
    public void testConstructable() {
        // enforce public constructor
        new EnumUtils();
    }

    private void assertArrayEquals(final long[] actual, final long... expected) {
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void test_isValidEnum_nullClass_1_oe() throws Exception {
        try {
    EnumUtils.isValidEnum(null, "PURPLE");
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_isValidEnumIgnoreCase_nullClass_1_oe() throws Exception {
        try {
    EnumUtils.isValidEnumIgnoreCase(null, "PURPLE");
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_getEnum_nullClass_1_oe() throws Exception {
        try {
    EnumUtils.getEnum((Class<Traffic>) null, "PURPLE");
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_getEnumIgnoreCase_nullClass_1_oe() throws Exception {
        try {
    EnumUtils.getEnumIgnoreCase((Class<Traffic>) null, "PURPLE");
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_generateBitVector_nullClass_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVector(null, EnumSet.of(Traffic.RED));
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_generateBitVectors_nullClass_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVectors(null, EnumSet.of(Traffic.RED));
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_generateBitVector_nullIterable_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVector(Traffic.class, (Iterable<Traffic>) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_generateBitVectors_nullIterable_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVectors(null, (Iterable<Traffic>) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_generateBitVector_nullElement_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVector(Traffic.class, Arrays.asList(Traffic.RED, null));
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_generateBitVectors_nullElement_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVectors(Traffic.class, Arrays.asList(Traffic.RED, null));
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_generateBitVector_nullClassWithArray_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVector(null, Traffic.RED);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_generateBitVectors_nullClassWithArray_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVectors(null, Traffic.RED);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_generateBitVector_nullArray_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVector(Traffic.class, (Traffic[]) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_generateBitVectors_nullArray_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVectors(Traffic.class, (Traffic[]) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_generateBitVector_nullArrayElement_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVector(Traffic.class, Traffic.RED, null);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_generateBitVectors_nullArrayElement_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVectors(Traffic.class, Traffic.RED, null);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_generateBitVector_longClass_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVector(TooMany.class, EnumSet.of(TooMany.A1));
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_generateBitVector_longClassWithArray_1_oe() throws Exception {
        try {
    EnumUtils.generateBitVector(TooMany.class, TooMany.A1);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_generateBitVector_nonEnumClass_1_oe() throws Exception {
        @SuppressWarnings("rawtypes")
        final
        Class rawType = Object.class;
        @SuppressWarnings("rawtypes")
        final
        List rawList = new ArrayList();
        try {
    EnumUtils.generateBitVector(rawType, rawList);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_generateBitVectors_nonEnumClass_1_oe() throws Exception {
        @SuppressWarnings("rawtypes")
        final
        Class rawType = Object.class;
        @SuppressWarnings("rawtypes")
        final
        List rawList = new ArrayList();
        try {
    EnumUtils.generateBitVectors(rawType, rawList);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_generateBitVector_nonEnumClassWithArray_1_oe() throws Exception {
        @SuppressWarnings("rawtypes")
        final
        Class rawType = Object.class;
        try {
    EnumUtils.generateBitVector(rawType);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_generateBitVectors_nonEnumClassWithArray_1_oe() throws Exception {
        @SuppressWarnings("rawtypes")
        final
        Class rawType = Object.class;
        try {
    EnumUtils.generateBitVectors(rawType);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void test_processBitVector_nullClass_1_oe() throws Exception {
        final Class<Traffic> empty = null;
        try {
    EnumUtils.processBitVector(empty, 0L);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_processBitVectors_nullClass_1_oe() throws Exception {
        final Class<Traffic> empty = null;
        try {
    EnumUtils.processBitVectors(empty, 0L);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void test_processBitVector_longClass_1_oe() throws Exception {
        try {
    EnumUtils.processBitVector(TooMany.class, 0L);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}


