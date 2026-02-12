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
package org.apache.commons.rng.simple.internal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Tests for the {@link NativeSeedType} seed conversions. This test
 * ensures that a seed can be created or converted from any supported input seed to each
 * supported native seed type.
 */
class NativeSeedTypeParametricTest_OE25Dev {
    /** This is a list of the class types that are supported native seeds. */
    private static final Object[] SUPPORTED_NATIVE_TYPES = {
        Integer.class,
        Long.class,
        int[].class,
        long[].class
    };
    /** Example supported seeds for conversion to a native seed type. */
    private static final Object[] SUPPORTED_SEEDS = {
        Integer.valueOf(1),
        Long.valueOf(2),
        new int[] {3, 4, 5},
        new long[] {6, 7, 8},
        new byte[] {9, 10, 11},
    };
    /** Example unsupported seeds for conversion to a native seed type. */
    private static final Object[] UNSUPPORTED_SEEDS = {
        null,
        Double.valueOf(Math.PI),
    };

    /**
     * Check that there are enum values for all supported types.
     * This ensures the test is maintained to correspond to the enum.
     */

    /**
     * Test the seed can be created as the correct type.
     *
     * @param nativeSeedType Native seed type.
     */

    /**
     * Test the seed can be created, converted to a byte[] and then back to the native type.
     *
     * @param nativeSeedType Native seed type.
     */

    /**
     * Test the seed can be converted to the correct type from any of the supported input types.
     *
     * @param nativeSeedType The native seed type enum instance.
     */

    /**
     * Test unsupported input seed types are rejected.
     *
     * @param nativeSeedType The native seed type enum instance.
     */

    @ParameterizedTest
    @EnumSource
    void testCannotConvertUnsupportedSeed_1_oe(NativeSeedType nativeSeedType) {
         int size = 3;
        for ( Object input : UNSUPPORTED_SEEDS) {
            try {
    nativeSeedType.convertSeed(input, size);
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }
    }

}
