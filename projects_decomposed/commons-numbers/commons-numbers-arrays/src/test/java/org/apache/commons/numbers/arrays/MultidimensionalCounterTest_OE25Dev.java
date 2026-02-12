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

package org.apache.commons.numbers.arrays;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test cases for the {@link MultidimensionalCounter} class.
 *
 */
class MultidimensionalCounterTest_OE25Dev {

    // Illustrates how to recover the iterator functionality that existed
    // in Commons Math (v3.6.1) but was not ported to "Commons Numbers".

    @Test
    void testPreconditions_1_oe() {
        try {
    MultidimensionalCounter.of(0, 1);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testPreconditions_2_oe() {
        // removed other assertion
        try {
    MultidimensionalCounter.of(2, 0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testPreconditions_3_oe() {
        // removed other assertion
        // removed other assertion
        try {
    MultidimensionalCounter.of(-1, 1);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testPreconditions_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    MultidimensionalCounter.of(-1, -1);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testPreconditions_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    MultidimensionalCounter.of(Integer.MAX_VALUE, 2, Integer.MAX_VALUE);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testPreconditions_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MultidimensionalCounter c = MultidimensionalCounter.of(2, 3);
        try {
    c.toUni(1, 1, 1);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testPreconditions_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MultidimensionalCounter c = MultidimensionalCounter.of(2, 3);
        // removed other assertion
        try {
    c.toUni(3, 1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    void testPreconditions_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MultidimensionalCounter c = MultidimensionalCounter.of(2, 3);
        // removed other assertion
        // removed other assertion
        try {
    c.toUni(0, -1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    void testPreconditions_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MultidimensionalCounter c = MultidimensionalCounter.of(2, 3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    c.toMulti(-1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

    @Test
    void testPreconditions_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final MultidimensionalCounter c = MultidimensionalCounter.of(2, 3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    c.toMulti(6);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException");
} catch (IndexOutOfBoundsException e) {
}
    }

}
