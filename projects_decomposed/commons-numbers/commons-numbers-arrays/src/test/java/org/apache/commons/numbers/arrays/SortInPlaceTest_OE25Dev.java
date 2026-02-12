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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test cases for the {@link SortInPlace} class.
 *
 */
class SortInPlaceTest_OE25Dev {

    // Example in Javadoc.

    @Test
    void testPreconditions_1_oe() {
        final double[] nullArray = null;
        final double[] one = {1};
        final double[] two = {1, 2};
        final double[] onep = {2};

        try {
    SortInPlace.ASCENDING.apply(one, two);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testPreconditions_2_oe() {
        final double[] nullArray = null;
        final double[] one = {1};
        final double[] two = {1, 2};
        final double[] onep = {2};

        // removed other assertion
        try {
    SortInPlace.ASCENDING.apply(one, nullArray);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    void testPreconditions_3_oe() {
        final double[] nullArray = null;
        final double[] one = {1};
        final double[] two = {1, 2};
        final double[] onep = {2};

        // removed other assertion
        // removed other assertion
        try {
    SortInPlace.ASCENDING.apply(one, onep, nullArray);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

}
