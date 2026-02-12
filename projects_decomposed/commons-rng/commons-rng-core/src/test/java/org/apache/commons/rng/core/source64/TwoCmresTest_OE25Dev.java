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
package org.apache.commons.rng.core.source64;

import org.apache.commons.rng.core.RandomAssert;
import org.apache.commons.rng.core.source64.TwoCmres.Cmres;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class TwoCmresTest_OE25Dev {

    /**
     * This test targets the seeding procedure to verify any bit of the input seed contributes
     * to the output. Note: The seeding routine creates 2 16-bit integers from the 32-bit seed,
     * thus a change of any single bit should make a different output.
     */

    @Test
    void testSubcycleGeneratorsMustBeDifferent_1_oe() {
         int max = TwoCmres.numberOfSubcycleGenerators();
        for (int i = 0; i < max; i++) {
             int subCycle = i;
            try {
    new TwoCmres(-97845, subCycle, subCycle);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }
    }

    @Test
    void testSubcycleGeneratorsIndex_1_oe() {
         int seed = 246810;

        // Valid indices are between 0 (included) and max (excluded).
         int max = TwoCmres.numberOfSubcycleGenerators();

        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                if (i != j) { // Subcycle generators must be different.
                    // Can be instantiated.
                    new TwoCmres(seed, i, j);
                }
            }
        }

        for (int wrongIndex : new int[] {-1, max}) {
            try {
    new TwoCmres(seed, wrongIndex, 1);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: () -> \"Exception expected for index i = \" + wrongIndex");
} catch (IndexOutOfBoundsException e) {
}
    }
    }

    @Test
    void testSubcycleGeneratorsIndex_2_oe() {
         int seed = 246810;

        // Valid indices are between 0 (included) and max (excluded).
         int max = TwoCmres.numberOfSubcycleGenerators();

        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                if (i != j) { // Subcycle generators must be different.
                    // Can be instantiated.
                    new TwoCmres(seed, i, j);
                }
            }
        }

        for (int wrongIndex : new int[] {-1, max}) {
            // removed other assertion
            try {
    new TwoCmres(seed, 1, wrongIndex);
    org.junit.jupiter.api.Assertions.fail("IndexOutOfBoundsException: () -> \"Exception expected for index j = \" + wrongIndex");
} catch (IndexOutOfBoundsException e) {
}
    }
    }

    @Test
    void testCmresFactoryThrowsWithDuplicateMultiplier_2_oe() {
         ArrayList<Cmres> list = new ArrayList<>();
         long multiply = 0;
         int rotate = 3;
         int start = 5;

        list.add(new Cmres(multiply, rotate, start));

        long nextMultiply = multiply + 1;
        // removed other assertion

        list.add(new Cmres(nextMultiply, rotate, start));
        // This should throw as the list now contains the multiply value
        try {
    Cmres.Factory.checkUnique(list, nextMultiply);
    org.junit.jupiter.api.Assertions.fail("IllegalStateException");
} catch (IllegalStateException e) {
}
    }

}
