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
import org.junit.jupiter.api.Test;

class XoRoShiRo64StarStarTest_OE25Dev {
    /** The size of the array seed. */
    private static final int SEED_SIZE = 2;

    @Test
    void testConstructorWithZeroSeedIsNonFunctional() {
        RandomAssert.assertNextIntZeroOutput(new XoRoShiRo64StarStar(new int[SEED_SIZE]), 2 * SEED_SIZE);
    }

    @Test
    void testConstructorWithSingleBitSeedIsFunctional() {
        RandomAssert.assertIntArrayConstructorWithSingleBitSeedIsFunctional(XoRoShiRo64StarStar.class, SEED_SIZE);
    }

    @Test
    void testConstructorWithoutFullLengthSeed() {
        // Hit the case when the input seed is self-seeded when not full length
        RandomAssert.assertNextLongNonZeroOutput(new XoRoShiRo64StarStar(new int[] {0x012de1ba}),
                SEED_SIZE, SEED_SIZE);
    }

    @Test
    void testElementConstructor() {
        final int[] seed = {
            0x012de1ba, 0xa5a818b8,
        };
        final XoRoShiRo64StarStar rng1 = new XoRoShiRo64StarStar(seed);
        final XoRoShiRo64StarStar rng2 = new XoRoShiRo64StarStar(seed[0], seed[1]);
        RandomAssert.assertNextIntEquals(seed.length * 2, rng1, rng2);
    }


}
