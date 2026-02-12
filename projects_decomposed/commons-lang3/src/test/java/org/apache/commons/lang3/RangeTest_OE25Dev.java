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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>
 * Tests the methods in the {@link org.apache.commons.lang3.Range} class.
 * </p>
 */
@SuppressWarnings("boxing")
public class RangeTest_OE25Dev {

    private Range<Byte> byteRange;
    private Range<Byte> byteRange2;
    private Range<Byte> byteRange3;

    private Range<Double> doubleRange;
    private Range<Float> floatRange;
    private Range<Integer> intRange;
    private Range<Long> longRange;

    @BeforeEach
    public void setUp() {
        byteRange = Range.between((byte) 0, (byte) 5);
        byteRange2 = Range.between((byte) 0, (byte) 5);
        byteRange3 = Range.between((byte) 0, (byte) 10);

        intRange = Range.between(10, 20);
        longRange = Range.between(10L, 20L);
        floatRange = Range.between((float) 10, (float) 20);
        doubleRange = Range.between((double) 10, (double) 20);
    }

    // -----------------------------------------------------------------------

    // -----------------------------------------------------------------------

    // -----------------------------------------------------------------------

    // -----------------------------------------------------------------------

    // -----------------------------------------------------------------------

    // -----------------------------------------------------------------------
    @Test
    public void testSerializing() {
        SerializationUtils.clone(intRange);
    }

    @Test
    public void testElementCompareTo_1_oe() throws Exception {
        try {
    intRange.elementCompareTo(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testFitNull_1_oe() throws Exception {
        try {
     intRange.fit(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testIntersectionWithNonOverlapping_1_oe() throws Exception {
        try {
    intRange.intersectionWith(Range.between(0, 9));
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testIntersectionWithNull_1_oe() throws Exception {
        try {
    intRange.intersectionWith(null);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
