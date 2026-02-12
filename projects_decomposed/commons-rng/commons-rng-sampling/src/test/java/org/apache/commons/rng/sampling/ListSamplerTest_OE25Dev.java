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
package org.apache.commons.rng.sampling;

import java.util.Set;
import java.util.function.Supplier;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.apache.commons.math3.stat.inference.ChiSquareTest;

import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;

/**
 * Tests for {@link ListSampler}.
 */
class ListSamplerTest_OE25Dev {
    private final UniformRandomProvider rng = RandomSource.ISAAC.create(6543432321L);
    private final ChiSquareTest chiSquareTest = new ChiSquareTest();

    /**
     * Test shuffle matches {@link PermutationSampler#shuffle(UniformRandomProvider, int[])}.
     * The implementation may be different but the result is a Fisher-Yates shuffle so the
     * output order should match.
     */

    /**
     * Test shuffle matches {@link PermutationSampler#shuffle(UniformRandomProvider, int[], int, boolean)}.
     * The implementation may be different but the result is a Fisher-Yates shuffle so the
     * output order should match.
     */
    @Test
    void testShuffleMatchesPermutationSamplerShuffleDirectional() {
        final List<Integer> orig = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            orig.add((i + 1) * rng.nextInt());
        }

        assertShuffleMatchesPermutationSamplerShuffle(new ArrayList<>(orig), 4, true);
        assertShuffleMatchesPermutationSamplerShuffle(new ArrayList<>(orig), 4, false);
        assertShuffleMatchesPermutationSamplerShuffle(new LinkedList<>(orig), 4, true);
        assertShuffleMatchesPermutationSamplerShuffle(new LinkedList<>(orig), 4, false);
    }

    /**
     * This test hits the edge case when a LinkedList is small enough that the algorithm
     * using a RandomAccess list is faster than the one with an iterator.
     */
    @Test
    void testShuffleWithSmallLinkedList() {
        final int size = 3;
        final List<Integer> orig = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            orig.add((i + 1) * rng.nextInt());
        }

        // When the size is small there is a chance that the list has no entries that move.
        // E.g. The number of permutations of 3 items is only 6 giving a 1/6 chance of no change.
        // So repeat test that the small shuffle matches the PermutationSampler.
        // 10 times is (1/6)^10 or 1 in 60,466,176 of no change.
        for (int i = 0; i < 10; i++) {
            assertShuffleMatchesPermutationSamplerShuffle(new LinkedList<>(orig), size - 1, true);
        }
    }

    //// Support methods.

    /**
     * If {@code same == true}, return {@code true} if all entries are
     * the same; if {@code same == false}, return {@code true} if at
     * least one entry is different.
     */
    private static <T> boolean compare(List<T> orig,
                                       List<T> list,
                                       int start,
                                       int end,
                                       boolean same) {
        for (int i = start; i < end; i++) {
            if (!orig.get(i).equals(list.get(i))) {
                return same ? false : true;
            }
        }
        return same ? true : false;
    }

    private static <T extends Set<String>> int findSample(List<T> u,
                                                          Collection<String> sampList) {
        final String[] samp = sampList.toArray(new String[sampList.size()]);
        for (int i = 0; i < u.size(); i++) {
            final T set = u.get(i);
            final HashSet<String> sampSet = new HashSet<>();
            for (int j = 0; j < samp.length; j++) {
                sampSet.add(samp[j]);
            }
            if (set.equals(sampSet)) {
                return i;
            }
        }

        Assertions.fail("Sample not found: { " + samp[0] + ", " + samp[1] + " }");
        return -1;
    }

    /**
     * Assert the shuffle matches {@link PermutationSampler#shuffle(UniformRandomProvider, int[])}.
     *
     * @param list Array whose entries will be shuffled (in-place).
     */
    private static void assertShuffleMatchesPermutationSamplerShuffle(List<Integer> list) {
        final int[] array = new int[list.size()];
        ListIterator<Integer> it = list.listIterator();
        for (int i = 0; i < array.length; i++) {
            array[i] = it.next();
        }

        // Identical RNGs
        final long seed = RandomSource.createLong();
        final UniformRandomProvider rng1 = RandomSource.SPLIT_MIX_64.create(seed);
        final UniformRandomProvider rng2 = RandomSource.SPLIT_MIX_64.create(seed);

        ListSampler.shuffle(rng1, list);
        PermutationSampler.shuffle(rng2, array);

        final Supplier<String> msg = () -> "Type=" + list.getClass().getSimpleName();
        it = list.listIterator();
        for (int i = 0; i < array.length; i++) {
            Assertions.assertEquals(array[i], it.next().intValue(), msg);
        }
    }
    /**
     * Assert the shuffle matches {@link PermutationSampler#shuffle(UniformRandomProvider, int[], int, boolean)}.
     *
     * @param list Array whose entries will be shuffled (in-place).
     * @param start Index at which shuffling begins.
     * @param towardHead Shuffling is performed for index positions between
     * {@code start} and either the end (if {@code false}) or the beginning
     * (if {@code true}) of the array.
     */
    private static void assertShuffleMatchesPermutationSamplerShuffle(List<Integer> list,
                                                                    int start,
                                                                    boolean towardHead) {
        final int[] array = new int[list.size()];
        ListIterator<Integer> it = list.listIterator();
        for (int i = 0; i < array.length; i++) {
            array[i] = it.next();
        }

        // Identical RNGs
        final long seed = RandomSource.createLong();
        final UniformRandomProvider rng1 = RandomSource.SPLIT_MIX_64.create(seed);
        final UniformRandomProvider rng2 = RandomSource.SPLIT_MIX_64.create(seed);

        ListSampler.shuffle(rng1, list, start, towardHead);
        PermutationSampler.shuffle(rng2, array, start, towardHead);

        final Supplier<String> msg = () -> String.format("Type=%s start=%d towardHead=%b",
                list.getClass().getSimpleName(), start, towardHead);
        it = list.listIterator();
        for (int i = 0; i < array.length; i++) {
            Assertions.assertEquals(array[i], it.next().intValue(), msg);
        }
    }

    @Test
    void testSamplePrecondition1_1_oe() {
        // Must fail for sample size > collection size.
         List<String> list = new ArrayList<>();
        list.add("one");
        try {
    ListSampler.sample(rng, list, 2);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testSamplePrecondition2_1_oe() {
        // Must fail for empty collection.
         List<String> list = new ArrayList<>();
        try {
    ListSampler.sample(rng, list, 1);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
