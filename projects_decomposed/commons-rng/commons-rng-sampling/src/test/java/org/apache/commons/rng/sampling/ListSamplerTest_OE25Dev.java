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

import static org.junit.jupiter.api.Assertions.fail;

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

    /**
     * This test hits the edge case when a LinkedList is small enough that the algorithm
     * using a RandomAccess list is faster than the one with an iterator.
     */

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
    void testSample_1_oe() {
         String[][] c = {{"0", "1"}, {"0", "2"}, {"0", "3"}, {"0", "4"},
                              {"1", "2"}, {"1", "3"}, {"1", "4"},
                              {"2", "3"}, {"2", "4"},
                              {"3", "4"}};
         long[] observed = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
         double[] expected = {100, 100, 100, 100, 100, 100, 100, 100, 100, 100};

         HashSet<String> cPop = new HashSet<>(); // {0, 1, 2, 3, 4}.
        for (int i = 0; i < 5; i++) {
            cPop.add(Integer.toString(i));
        }

         List<Set<String>> sets = new ArrayList<>(); // 2-sets from 5.
        for (int i = 0; i < 10; i++) {
             HashSet<String> hs = new HashSet<>();
            hs.add(c[i][0]);
            hs.add(c[i][1]);
            sets.add(hs);
        }

        for (int i = 0; i < 1000; i++) {
            observed[findSample(sets, ListSampler.sample(rng, new ArrayList<>(cPop), 2))]++;
        }

        Assertions.assertFalse(chiSquareTest.chiSquareTest(expected, observed, 0.001));
    }

    @Test
    void testSampleWhole_1_oe() {
         List<String> list = new ArrayList<>();
        list.add("one");

         List<String> one = ListSampler.sample(rng, list, 1);
        Assertions.assertEquals(1, one.size());
    }

    @Test
    void testSampleWhole_2_oe() {
         List<String> list = new ArrayList<>();
        list.add("one");

         List<String> one = ListSampler.sample(rng, list, 1);
        Assertions.assertTrue(one.contains("one"));
    }

    @Test
    void testSamplePrecondition1_1_oe() {
         List<String> list = new ArrayList<>();
        list.add("one");
        try {
    ListSampler.sample(rng, list, 2);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testSamplePrecondition2_1_oe() {
         List<String> list = new ArrayList<>();
        try {
    ListSampler.sample(rng, list, 1);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testShuffle_1_oe() {
         List<Integer> orig = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            orig.add((i + 1) * rng.nextInt());
        }

         List<Integer> arrayList = new ArrayList<>(orig);

        ListSampler.shuffle(rng, arrayList);
        Assertions.assertTrue(compare(orig, arrayList, 0, orig.size(), false), "ArrayList");
    }

    @Test
    void testShuffle_2_oe() {
         List<Integer> orig = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            orig.add((i + 1) * rng.nextInt());
        }

         List<Integer> arrayList = new ArrayList<>(orig);

        ListSampler.shuffle(rng, arrayList);

         List<Integer> linkedList = new LinkedList<>(orig);

        ListSampler.shuffle(rng, linkedList);
        Assertions.assertTrue(compare(orig, linkedList, 0, orig.size(), false), "LinkedList");
    }

    @Test
    void testShuffleTail_1_oe() {
         List<Integer> orig = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            orig.add((i + 1) * rng.nextInt());
        }
         List<Integer> list = new ArrayList<>(orig);

         int start = 4;
        ListSampler.shuffle(rng, list, start, false);

        Assertions.assertTrue(compare(orig, list, 0, start, true));
    }

    @Test
    void testShuffleTail_2_oe() {
         List<Integer> orig = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            orig.add((i + 1) * rng.nextInt());
        }
         List<Integer> list = new ArrayList<>(orig);

         int start = 4;
        ListSampler.shuffle(rng, list, start, false);


        Assertions.assertTrue(compare(orig, list, start, orig.size(), false));
    }

    @Test
    void testShuffleHead_1_oe() {
         List<Integer> orig = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            orig.add((i + 1) * rng.nextInt());
        }
         List<Integer> list = new ArrayList<>(orig);

         int start = 4;
        ListSampler.shuffle(rng, list, start, true);

        Assertions.assertTrue(compare(orig, list, start + 1, orig.size(), true));
    }

    @Test
    void testShuffleHead_2_oe() {
         List<Integer> orig = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            orig.add((i + 1) * rng.nextInt());
        }
         List<Integer> list = new ArrayList<>(orig);

         int start = 4;
        ListSampler.shuffle(rng, list, start, true);


        Assertions.assertTrue(compare(orig, list, 0, start + 1, false));
    }

    @Test
    void testShuffleMatchesPermutationSamplerShuffle_1_oe_1_oe() {
         List<Integer> orig = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            orig.add((i + 1) * rng.nextInt());
        }

                 List<Integer> list0 = new ArrayList<>(orig);
         int[] array0 = new int[list0.size()];
                ListIterator<Integer> it0 = list0.listIterator();
                for (int i0 = 0; i0 < array0.length; i0++) {
                    array0[i0] = it0.next();
                }
        
                 long seed0 = RandomSource.createLong();
                 UniformRandomProvider rng10 = RandomSource.SPLIT_MIX_64.create(seed0);
                 UniformRandomProvider rng20 = RandomSource.SPLIT_MIX_64.create(seed0);
        
                ListSampler.shuffle(rng10, list0);
                PermutationSampler.shuffle(rng20, array0);
        
                 Supplier<String> msg0 = () -> "Type=" + list0.getClass().getSimpleName();
                it0 = list0.listIterator();
                for (int i0 = 0; i0 < array0.length; i0++) {
                    Assertions.assertEquals(array0[i0], it0.next().intValue(), msg0);
    }
    }

    @Test
    void testShuffleMatchesPermutationSamplerShuffle_2_oe_1_oe() {
         List<Integer> orig = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            orig.add((i + 1) * rng.nextInt());
        }

                 List<Integer> list0 = new LinkedList<>(orig);
         int[] array0 = new int[list0.size()];
                ListIterator<Integer> it0 = list0.listIterator();
                for (int i0 = 0; i0 < array0.length; i0++) {
                    array0[i0] = it0.next();
                }
        
                 long seed0 = RandomSource.createLong();
                 UniformRandomProvider rng10 = RandomSource.SPLIT_MIX_64.create(seed0);
                 UniformRandomProvider rng20 = RandomSource.SPLIT_MIX_64.create(seed0);
        
                ListSampler.shuffle(rng10, list0);
                PermutationSampler.shuffle(rng20, array0);
        
                 Supplier<String> msg0 = () -> "Type=" + list0.getClass().getSimpleName();
                it0 = list0.listIterator();
                for (int i0 = 0; i0 < array0.length; i0++) {
                    Assertions.assertEquals(array0[i0], it0.next().intValue(), msg0);
    }
    }

@Test
    void testShuffleMatchesPermutationSamplerShuffleDirectional_1_oe() {
         List<Integer> orig = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            orig.add((i + 1) * rng.nextInt());
        }

        assertShuffleMatchesPermutationSamplerShuffle(new ArrayList<>(orig), 4, true);
    }

@Test
    void testShuffleMatchesPermutationSamplerShuffleDirectional_2_oe() {
         List<Integer> orig = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            orig.add((i + 1) * rng.nextInt());
        }

        assertShuffleMatchesPermutationSamplerShuffle(new ArrayList<>(orig), 4, false);
    }

@Test
    void testShuffleMatchesPermutationSamplerShuffleDirectional_3_oe() {
         List<Integer> orig = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            orig.add((i + 1) * rng.nextInt());
        }

        assertShuffleMatchesPermutationSamplerShuffle(new LinkedList<>(orig), 4, true);
    }

@Test
    void testShuffleMatchesPermutationSamplerShuffleDirectional_4_oe() {
         List<Integer> orig = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            orig.add((i + 1) * rng.nextInt());
        }

        assertShuffleMatchesPermutationSamplerShuffle(new LinkedList<>(orig), 4, false);
    }

@Test
    void testShuffleWithSmallLinkedList_1_oe() {
         int size = 3;
         List<Integer> orig = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            orig.add((i + 1) * rng.nextInt());
        }

        for (int i = 0; i < 10; i++) {
            assertShuffleMatchesPermutationSamplerShuffle(new LinkedList<>(orig), size - 1, true);
    }
    }

}
