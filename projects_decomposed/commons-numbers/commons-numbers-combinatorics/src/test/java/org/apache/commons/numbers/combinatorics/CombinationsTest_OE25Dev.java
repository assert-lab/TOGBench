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
package org.apache.commons.numbers.combinatorics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Comparator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests for the {@link Combinations} class.
 */
class CombinationsTest_OE25Dev {
    @Test
    void testGetN() {
        final int n = 5;
        final int k = 3;
        Assertions.assertEquals(n, Combinations.of(n, k).getN());
    }

    @Test
    void testGetK() {
        final int n = 5;
        final int k = 3;
        Assertions.assertEquals(k, Combinations.of(n, k).getK());
    }

    @Test
    void testLexicographicIterator() {
        checkLexicographicIterator(5, 3);
        checkLexicographicIterator(6, 4);
        checkLexicographicIterator(8, 2);
        checkLexicographicIterator(6, 1);
        checkLexicographicIterator(3, 3);
        checkLexicographicIterator(1, 1);
        checkLexicographicIterator(2, 0);
        checkLexicographicIterator(1, 0);
        checkLexicographicIterator(0, 0);
        checkLexicographicIterator(4, 2);
        checkLexicographicIterator(123, 2);
    }

    @Test
    void testLexicographicIteratorThrows() {
        checkLexicographicIteratorThrows(2, 1);
        // Only 1 combination
        checkLexicographicIteratorThrows(1, 1);
    }

    @Test
    void testLexicographicComparatorWrongIterate1() {
        final int n = 5;
        final int k = 3;
        final Comparator<int[]> comp = Combinations.of(n, k).comparator();
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> comp.compare(new int[] {1},
                               new int[] {0, 1, 2}));
    }

    @Test
    void testLexicographicComparatorWrongIterate2() {
        final int n = 5;
        final int k = 3;
        final Comparator<int[]> comp = Combinations.of(n, k).comparator();
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> comp.compare(new int[] {0, 1, 2},
                               new int[] {0, 1, 2, 3}));
    }

    @Test
    void testLexicographicComparatorWrongIterate3() {
        final int n = 5;
        final int k = 3;
        final Comparator<int[]> comp = Combinations.of(n, k).comparator();
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> comp.compare(new int[] {1, 2, 5},
                               new int[] {0, 1, 2}));
    }

    @Test
    void testLexicographicComparatorWrongIterate4() {
        final int n = 5;
        final int k = 3;
        final Comparator<int[]> comp = Combinations.of(n, k).comparator();
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> comp.compare(new int[] {1, 2, 4},
                               new int[] {-1, 1, 2}));
    }

    @Test
    void testLexicographicComparator() {
        final int n = 5;
        final int k = 3;
        final Comparator<int[]> comp = Combinations.of(n, k).comparator();
        Assertions.assertEquals(1, comp.compare(new int[] {1, 2, 4},
                                                new int[] {1, 2, 3}));
        Assertions.assertEquals(-1, comp.compare(new int[] {0, 1, 4},
                                                 new int[] {0, 2, 4}));
        Assertions.assertEquals(0, comp.compare(new int[] {1, 3, 4},
                                                new int[] {1, 3, 4}));
    }

    /**
     * Check that iterates can be passed unsorted.
     */
    @Test
    void testLexicographicComparatorUnsorted() {
        final int n = 5;
        final int k = 3;
        final Comparator<int[]> comp = Combinations.of(n, k).comparator();
        Assertions.assertEquals(1, comp.compare(new int[] {1, 4, 2},
                                                new int[] {1, 3, 2}));
        Assertions.assertEquals(-1, comp.compare(new int[] {0, 4, 1},
                                                 new int[] {0, 4, 2}));
        Assertions.assertEquals(0, comp.compare(new int[] {1, 4, 3},
                                                new int[] {1, 3, 4}));
    }

    @Test
    void testEmptyCombination() {
        final Iterator<int[]> iter = Combinations.of(12345, 0).iterator();
        Assertions.assertTrue(iter.hasNext());
        final int[] c = iter.next();
        Assertions.assertEquals(0, c.length);
        Assertions.assertFalse(iter.hasNext());
    }

    @Test
    void testFullSetCombination() {
        final int n = 67;
        final Iterator<int[]> iter = Combinations.of(n, n).iterator();
        Assertions.assertTrue(iter.hasNext());
        final int[] c = iter.next();
        Assertions.assertEquals(n, c.length);

        for (int i = 0; i < n; i++) {
            Assertions.assertEquals(i, c[i]);
        }

        Assertions.assertFalse(iter.hasNext());
    }

    /**
     * Verifies that the iterator generates a lexicographically
     * increasing sequence of b(n,k) arrays, each having length k
     * and each array itself increasing.
     *
     * @param n Size of the set from which subsets are selected.
     * @param k Size of the subsets to be enumerated.
     */
    private static void checkLexicographicIterator(int n,
                                                   int k) {
        int[] lastIterate = null;

        long numIterates = 0;
        final Comparator<int[]> comp = Combinations.of(n, k).comparator();
        for (int[] iterate : Combinations.of(n, k)) {
            Assertions.assertEquals(k, iterate.length);

            // Check that the sequence of iterates is ordered.
            if (lastIterate != null) {
                Assertions.assertEquals(1, comp.compare(iterate, lastIterate));
            }

            // Check that each iterate is ordered.
            for (int i = 1; i < iterate.length; i++) {
                Assertions.assertTrue(iterate[i] > iterate[i - 1]);
            }

            lastIterate = iterate;
            ++numIterates;
        }

        // Check the number of iterates.
        Assertions.assertEquals(BinomialCoefficient.value(n, k), numIterates);
    }

    /**
     * Verifies that the iterator throws exceptions when misused.
     *
     * @param n Size of the set from which subsets are selected.
     * @param k Size of the subsets to be enumerated.
     */
    private static void checkLexicographicIteratorThrows(int n,
                                                         int k) {
        Iterator<int[]> iter = Combinations.of(n, k).iterator();

        // First call
        iter.next();
        // Check remove is not supported
        Assertions.assertThrows(UnsupportedOperationException.class, () -> iter.remove());

        // Consume the rest
        final long numIterates = BinomialCoefficient.value(n, k);
        for (long i = 1; i < numIterates; i++) {
            iter.next();
        }
        Assertions.assertThrows(NoSuchElementException.class, () -> iter.next());
    }

    @Test
    void testBinomialCoefficientKLargerThanN() {
        Assertions.assertThrows(CombinatoricsException.class,
            () -> Combinations.of(4, 5)
        );
    }

    @Test
    void testBinomialCoefficientNegativeN() {
        Assertions.assertThrows(CombinatoricsException.class,
            () -> Combinations.of(-1, 1)
        );
    }

    @Test
    void testBinomialCoefficientNegativeK() {
        Assertions.assertThrows(CombinatoricsException.class,
            () -> Combinations.of(10, -1)
        );
    }

    @Test
    void testBinomialCoefficientKAboveN() {
        Assertions.assertThrows(CombinatoricsException.class,
            () -> Combinations.of(10, 20)
        );
    }

    @Test
    void testGetN_1_oe() {
        final int n = 5;
        final int k = 3;
        Assertions.assertEquals(n, Combinations.of(n, k).getN());
    }

    @Test
    void testGetK_1_oe() {
        final int n = 5;
        final int k = 3;
        Assertions.assertEquals(k, Combinations.of(n, k).getK());
    }

    @Test
    void testLexicographicComparatorWrongIterate1_1_oe() {
        final int n = 5;
        final int k = 3;
        final Comparator<int[]> comp = Combinations.of(n, k).comparator();
        try {
    comp.compare(new int[] {1}, new int[] {0, 1, 2});
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testLexicographicComparatorWrongIterate2_1_oe() {
        final int n = 5;
        final int k = 3;
        final Comparator<int[]> comp = Combinations.of(n, k).comparator();
        try {
    comp.compare(new int[] {0, 1, 2}, new int[] {0, 1, 2, 3});
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testLexicographicComparatorWrongIterate3_1_oe() {
        final int n = 5;
        final int k = 3;
        final Comparator<int[]> comp = Combinations.of(n, k).comparator();
        try {
    comp.compare(new int[] {1, 2, 5}, new int[] {0, 1, 2});
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testLexicographicComparatorWrongIterate4_1_oe() {
        final int n = 5;
        final int k = 3;
        final Comparator<int[]> comp = Combinations.of(n, k).comparator();
        try {
    comp.compare(new int[] {1, 2, 4}, new int[] {-1, 1, 2});
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testLexicographicComparator_1_oe() {
        final int n = 5;
        final int k = 3;
        final Comparator<int[]> comp = Combinations.of(n, k).comparator();
        Assertions.assertEquals(1, comp.compare(new int[] {1, 2, 4}, new int[] {1, 2, 3}));
    }

    @Test
    void testLexicographicComparator_2_oe() {
        final int n = 5;
        final int k = 3;
        final Comparator<int[]> comp = Combinations.of(n, k).comparator();
        Assertions.assertEquals(-1, comp.compare(new int[] {0, 1, 4}, new int[] {0, 2, 4}));
    }

    @Test
    void testLexicographicComparator_3_oe() {
        final int n = 5;
        final int k = 3;
        final Comparator<int[]> comp = Combinations.of(n, k).comparator();
        Assertions.assertEquals(0, comp.compare(new int[] {1, 3, 4}, new int[] {1, 3, 4}));
    }

    @Test
    void testLexicographicComparatorUnsorted_1_oe() {
        final int n = 5;
        final int k = 3;
        final Comparator<int[]> comp = Combinations.of(n, k).comparator();
        Assertions.assertEquals(1, comp.compare(new int[] {1, 4, 2}, new int[] {1, 3, 2}));
    }

    @Test
    void testLexicographicComparatorUnsorted_2_oe() {
        final int n = 5;
        final int k = 3;
        final Comparator<int[]> comp = Combinations.of(n, k).comparator();
        Assertions.assertEquals(-1, comp.compare(new int[] {0, 4, 1}, new int[] {0, 4, 2}));
    }

    @Test
    void testLexicographicComparatorUnsorted_3_oe() {
        final int n = 5;
        final int k = 3;
        final Comparator<int[]> comp = Combinations.of(n, k).comparator();
        Assertions.assertEquals(0, comp.compare(new int[] {1, 4, 3}, new int[] {1, 3, 4}));
    }

    @Test
    void testEmptyCombination_1_oe() {
        final Iterator<int[]> iter = Combinations.of(12345, 0).iterator();
        Assertions.assertTrue(iter.hasNext());
    }

    @Test
    void testEmptyCombination_2_oe() {
        final Iterator<int[]> iter = Combinations.of(12345, 0).iterator();
        final int[] c = iter.next();
        Assertions.assertEquals(0, c.length);
    }

    @Test
    void testEmptyCombination_3_oe() {
        final Iterator<int[]> iter = Combinations.of(12345, 0).iterator();
        final int[] c = iter.next();
        Assertions.assertFalse(iter.hasNext());
    }

    @Test
    void testFullSetCombination_1_oe() {
        final int n = 67;
        final Iterator<int[]> iter = Combinations.of(n, n).iterator();
        Assertions.assertTrue(iter.hasNext());
    }

    @Test
    void testFullSetCombination_2_oe() {
        final int n = 67;
        final Iterator<int[]> iter = Combinations.of(n, n).iterator();
        final int[] c = iter.next();
        Assertions.assertEquals(n, c.length);
    }

    @Test
    void testFullSetCombination_3_oe() {
        final int n = 67;
        final Iterator<int[]> iter = Combinations.of(n, n).iterator();
        final int[] c = iter.next();

        for (int i = 0; i < n; i++) {
            Assertions.assertEquals(i, c[i]);
    }
    }

    @Test
    void testFullSetCombination_4_oe() {
        final int n = 67;
        final Iterator<int[]> iter = Combinations.of(n, n).iterator();
        final int[] c = iter.next();

        for (int i = 0; i < n; i++) {
        }

        Assertions.assertFalse(iter.hasNext());
    }

    @Test
    void testBinomialCoefficientKLargerThanN_1_oe() {
        try {
    Combinations.of(4, 5);
    fail("CombinatoricsException");
} catch (CombinatoricsException e) {
}
    }

    @Test
    void testBinomialCoefficientNegativeN_1_oe() {
        try {
    Combinations.of(-1, 1);
    fail("CombinatoricsException");
} catch (CombinatoricsException e) {
}
    }

    @Test
    void testBinomialCoefficientNegativeK_1_oe() {
        try {
    Combinations.of(10, -1);
    fail("CombinatoricsException");
} catch (CombinatoricsException e) {
}
    }

    @Test
    void testBinomialCoefficientKAboveN_1_oe() {
        try {
    Combinations.of(10, 20);
    fail("CombinatoricsException");
} catch (CombinatoricsException e) {
}
    }

}
