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
package org.apache.commons.rng.core.source32;

import org.apache.commons.rng.core.RandomAssert;
import org.apache.commons.rng.core.util.NumberFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MiddleSquareWeylSequenceTest_OE25Dev {

    /**
     * Test the self-seeding functionality outputs non-zero output from the initial state.
     *
     * <p>Note: The generator quality is dependent on a high complexity Weyl increment. This
     * test passes a seed without the increment set and expects the generator to work. The
     * statistical quality of the output is not tested.</p>
     */
    @Test
    void testSelfSeeding() {
        final int warmupCycles = 0;
        final int testCycles = 3;
        // Do not pass the Weyl increment (the 3rd value in the seed array)
        RandomAssert.assertNextLongNonZeroOutput(new MiddleSquareWeylSequence(new long[2]),
                warmupCycles, testCycles);
    }

    /**
     * Test nextLong() returns two nextInt() values joined together. This tests the custom
     * nextLong() routine in the implementation that overrides the default.
     */


}
