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

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test class for {@link DiscreteProbabilityCollectionSampler}.
 */
class DiscreteProbabilityCollectionSamplerTest_OE25Dev {
    /** RNG. */
    private final UniformRandomProvider rng = RandomSource.XO_SHI_RO_128_PP.create();

    @Test
    void testPrecondition1() {
        // Size mismatch
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> new DiscreteProbabilityCollectionSampler<>(rng,
                 Arrays.asList(1d, 2d),
                 new double[] {0d}));
    }

    @Test
    void testPrecondition2() {
        // Negative probability
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> new DiscreteProbabilityCollectionSampler<>(rng,
                 Arrays.asList(1d, 2d),
                 new double[] {0d, -1d}));
    }

    @Test
    void testPrecondition3() {
        // Probabilities do not sum above 0
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> new DiscreteProbabilityCollectionSampler<>(rng,
                 Arrays.asList(1d, 2d),
                 new double[] {0d, 0d}));
    }

    @Test
    void testPrecondition4() {
        // NaN probability
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> new DiscreteProbabilityCollectionSampler<>(rng,
                 Arrays.asList(1d, 2d),
                 new double[] {0d, Double.NaN}));
    }

    @Test
    void testPrecondition5() {
        // Infinite probability
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> new DiscreteProbabilityCollectionSampler<>(rng,
                 Arrays.asList(1d, 2d),
                 new double[] {0d, Double.POSITIVE_INFINITY}));
    }

    @Test
    void testPrecondition6() {
        // Empty Map<T, Double> not allowed
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> new DiscreteProbabilityCollectionSampler<>(rng,
                 new HashMap<>()));
    }

    @Test
    void testPrecondition7() {
        // Empty List<T> not allowed
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> new DiscreteProbabilityCollectionSampler<>(rng,
                 Collections.<Double>emptyList(),
                 new double[0]));
    }

    @Test
    void testSample() {
        final DiscreteProbabilityCollectionSampler<Double> sampler =
            new DiscreteProbabilityCollectionSampler<>(rng,
                                                       Arrays.asList(3d, -1d, 3d, 7d, -2d, 8d),
                                                       new double[] {0.2, 0.2, 0.3, 0.3, 0, 0});
        final double expectedMean = 3.4;
        final double expectedVariance = 7.84;

        final int n = 100000000;
        double sum = 0;
        double sumOfSquares = 0;
        for (int i = 0; i < n; i++) {
            final double rand = sampler.sample();
            sum += rand;
            sumOfSquares += rand * rand;
        }

        final double mean = sum / n;
        Assertions.assertEquals(expectedMean, mean, 1e-3);
        final double variance = sumOfSquares / n - mean * mean;
        Assertions.assertEquals(expectedVariance, variance, 2e-3);
    }


    @Test
    void testSampleUsingMap() {
        final UniformRandomProvider rng1 = RandomSource.SPLIT_MIX_64.create(0L);
        final UniformRandomProvider rng2 = RandomSource.SPLIT_MIX_64.create(0L);
        final List<Integer> items = Arrays.asList(1, 3, 4, 6, 9);
        final double[] probabilities = {0.1, 0.2, 0.3, 0.4, 0.5};
        final DiscreteProbabilityCollectionSampler<Integer> sampler1 =
            new DiscreteProbabilityCollectionSampler<>(rng1, items, probabilities);

        // Create a map version. The map iterator must be ordered so use a TreeMap.
        final Map<Integer, Double> map = new TreeMap<>();
        for (int i = 0; i < probabilities.length; i++) {
            map.put(items.get(i), probabilities[i]);
        }
        final DiscreteProbabilityCollectionSampler<Integer> sampler2 =
            new DiscreteProbabilityCollectionSampler<>(rng2, map);

        for (int i = 0; i < 50; i++) {
            Assertions.assertEquals(sampler1.sample(), sampler2.sample());
        }
    }

    /**
     * Edge-case test:
     * Create a sampler that will return 1 for nextDouble() forcing the search to
     * identify the end item of the cumulative probability array.
     */
    @Test
    void testSampleWithProbabilityAtLastItem() {
        // Ensure the samples pick probability 0 (the first item) and then
        // a probability (for the second item) that hits an edge case.
        final UniformRandomProvider dummyRng = new UniformRandomProvider() {
            private int count;
            // CHECKSTYLE: stop all
            public long nextLong(long n) { return 0; }
            public long nextLong() { return 0; }
            public int nextInt(int n) { return 0; }
            public int nextInt() { return 0; }
            public float nextFloat() { return 0; }
            // Return 0 then the given probability
            public double nextDouble() { return (count++ == 0) ? 0 : 1.0; }
            public void nextBytes(byte[] bytes, int start, int len) {}
            public void nextBytes(byte[] bytes) {}
            public boolean nextBoolean() { return false; }
            // CHECKSTYLE: resume all
        };

        final List<Double> items = Arrays.asList(1d, 2d);
        final DiscreteProbabilityCollectionSampler<Double> sampler =
            new DiscreteProbabilityCollectionSampler<>(dummyRng,
                                                       items,
                                                       new double[] {0.5, 0.5});
        final Double item1 = sampler.sample();
        final Double item2 = sampler.sample();
        // Check they are in the list
        Assertions.assertTrue(items.contains(item1), "Sample item1 is not from the list");
        Assertions.assertTrue(items.contains(item2), "Sample item2 is not from the list");
        // Test the two samples are different items
        Assertions.assertNotSame(item1, item2, "Item1 and 2 should be different");
    }

    /**
     * Test the SharedStateSampler implementation.
     */
    @Test
    void testSharedStateSampler() {
        final UniformRandomProvider rng1 = RandomSource.SPLIT_MIX_64.create(0L);
        final UniformRandomProvider rng2 = RandomSource.SPLIT_MIX_64.create(0L);
        final List<Double> items = Arrays.asList(1d, 2d, 3d, 4d);
        final DiscreteProbabilityCollectionSampler<Double> sampler1 =
            new DiscreteProbabilityCollectionSampler<>(rng1,
                                                       items,
                                                       new double[] {0.1, 0.2, 0.3, 0.4});
        final DiscreteProbabilityCollectionSampler<Double> sampler2 = sampler1.withUniformRandomProvider(rng2);
        RandomAssert.assertProduceSameSequence(sampler1, sampler2);
    }

    @Test
    void testPrecondition1_1_oe() {
        // Size mismatch
        try {
    new DiscreteProbabilityCollectionSampler<>(rng, Arrays.asList(1d, 2d), new double[] {0d});
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testPrecondition2_1_oe() {
        // Negative probability
        try {
    new DiscreteProbabilityCollectionSampler<>(rng, Arrays.asList(1d, 2d), new double[] {0d, -1d});
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testPrecondition3_1_oe() {
        // Probabilities do not sum above 0
        try {
    new DiscreteProbabilityCollectionSampler<>(rng, Arrays.asList(1d, 2d), new double[] {0d, 0d});
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testPrecondition4_1_oe() {
        // NaN probability
        try {
    new DiscreteProbabilityCollectionSampler<>(rng, Arrays.asList(1d, 2d), new double[] {0d, Double.NaN});
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testPrecondition5_1_oe() {
        // Infinite probability
        try {
    new DiscreteProbabilityCollectionSampler<>(rng, Arrays.asList(1d, 2d), new double[] {0d, Double.POSITIVE_INFINITY});
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testPrecondition6_1_oe() {
        // Empty Map<T, Double> not allowed
        try {
    new DiscreteProbabilityCollectionSampler<>(rng, new HashMap<>());
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testPrecondition7_1_oe() {
        // Empty List<T> not allowed
        try {
    new DiscreteProbabilityCollectionSampler<>(rng, Collections.<Double>emptyList(), new double[0]);
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    void testSample_1_oe() {
         DiscreteProbabilityCollectionSampler<Double> sampler =
            new DiscreteProbabilityCollectionSampler<>(rng,
                                                       Arrays.asList(3d, -1d, 3d, 7d, -2d, 8d),
                                                       new double[] {0.2, 0.2, 0.3, 0.3, 0, 0});
         double expectedMean = 3.4;
         double expectedVariance = 7.84;

         int n = 100000000;
        double sum = 0;
        double sumOfSquares = 0;
        for (int i = 0; i < n; i++) {
             double rand = sampler.sample();
            sum += rand;
            sumOfSquares += rand * rand;
        }

         double mean = sum / n;
        Assertions.assertEquals(expectedMean, mean, 1e-3);
    }

    @Test
    void testSample_2_oe() {
         DiscreteProbabilityCollectionSampler<Double> sampler =
            new DiscreteProbabilityCollectionSampler<>(rng,
                                                       Arrays.asList(3d, -1d, 3d, 7d, -2d, 8d),
                                                       new double[] {0.2, 0.2, 0.3, 0.3, 0, 0});
         double expectedMean = 3.4;
         double expectedVariance = 7.84;

         int n = 100000000;
        double sum = 0;
        double sumOfSquares = 0;
        for (int i = 0; i < n; i++) {
             double rand = sampler.sample();
            sum += rand;
            sumOfSquares += rand * rand;
        }

         double mean = sum / n;
        // removed other assertion
         double variance = sumOfSquares / n - mean * mean;
        Assertions.assertEquals(expectedVariance, variance, 2e-3);
    }

    @Test
    void testSampleUsingMap_1_oe() {
         UniformRandomProvider rng1 = RandomSource.SPLIT_MIX_64.create(0L);
         UniformRandomProvider rng2 = RandomSource.SPLIT_MIX_64.create(0L);
         List<Integer> items = Arrays.asList(1, 3, 4, 6, 9);
         double[] probabilities = {0.1, 0.2, 0.3, 0.4, 0.5};
         DiscreteProbabilityCollectionSampler<Integer> sampler1 =
            new DiscreteProbabilityCollectionSampler<>(rng1, items, probabilities);

        // Create a map version. The map iterator must be ordered so use a TreeMap.
         Map<Integer, Double> map = new TreeMap<>();
        for (int i = 0; i < probabilities.length; i++) {
            map.put(items.get(i), probabilities[i]);
        }
         DiscreteProbabilityCollectionSampler<Integer> sampler2 =
            new DiscreteProbabilityCollectionSampler<>(rng2, map);

        for (int i = 0; i < 50; i++) {
            Assertions.assertEquals(sampler1.sample(), sampler2.sample());
    }
    }

    @Test
    void testSampleWithProbabilityAtLastItem_1_oe() {
        // Ensure the samples pick probability 0 (the first item) and then
        // a probability (for the second item) that hits an edge case.
         UniformRandomProvider dummyRng = new UniformRandomProvider() {
            private int count;
            // CHECKSTYLE: stop all
            public long nextLong(long n) { return 0; }
            public long nextLong() { return 0; }
            public int nextInt(int n) { return 0; }
            public int nextInt() { return 0; }
            public float nextFloat() { return 0; }
            // Return 0 then the given probability
            public double nextDouble() { return (count++ == 0) ? 0 : 1.0; }
            public void nextBytes(byte[] bytes, int start, int len) {}
            public void nextBytes(byte[] bytes) {}
            public boolean nextBoolean() { return false; }
            // CHECKSTYLE: resume all
        };

         List<Double> items = Arrays.asList(1d, 2d);
         DiscreteProbabilityCollectionSampler<Double> sampler =
            new DiscreteProbabilityCollectionSampler<>(dummyRng,
                                                       items,
                                                       new double[] {0.5, 0.5});
         Double item1 = sampler.sample();
         Double item2 = sampler.sample();
        // Check they are in the list
        Assertions.assertTrue(items.contains(item1), "Sample item1 is not from the list");
    }

    @Test
    void testSampleWithProbabilityAtLastItem_2_oe() {
        // Ensure the samples pick probability 0 (the first item) and then
        // a probability (for the second item) that hits an edge case.
         UniformRandomProvider dummyRng = new UniformRandomProvider() {
            private int count;
            // CHECKSTYLE: stop all
            public long nextLong(long n) { return 0; }
            public long nextLong() { return 0; }
            public int nextInt(int n) { return 0; }
            public int nextInt() { return 0; }
            public float nextFloat() { return 0; }
            // Return 0 then the given probability
            public double nextDouble() { return (count++ == 0) ? 0 : 1.0; }
            public void nextBytes(byte[] bytes, int start, int len) {}
            public void nextBytes(byte[] bytes) {}
            public boolean nextBoolean() { return false; }
            // CHECKSTYLE: resume all
        };

         List<Double> items = Arrays.asList(1d, 2d);
         DiscreteProbabilityCollectionSampler<Double> sampler =
            new DiscreteProbabilityCollectionSampler<>(dummyRng,
                                                       items,
                                                       new double[] {0.5, 0.5});
         Double item1 = sampler.sample();
         Double item2 = sampler.sample();
        // Check they are in the list
        // removed other assertion
        Assertions.assertTrue(items.contains(item2), "Sample item2 is not from the list");
    }

    @Test
    void testSampleWithProbabilityAtLastItem_3_oe() {
        // Ensure the samples pick probability 0 (the first item) and then
        // a probability (for the second item) that hits an edge case.
         UniformRandomProvider dummyRng = new UniformRandomProvider() {
            private int count;
            // CHECKSTYLE: stop all
            public long nextLong(long n) { return 0; }
            public long nextLong() { return 0; }
            public int nextInt(int n) { return 0; }
            public int nextInt() { return 0; }
            public float nextFloat() { return 0; }
            // Return 0 then the given probability
            public double nextDouble() { return (count++ == 0) ? 0 : 1.0; }
            public void nextBytes(byte[] bytes, int start, int len) {}
            public void nextBytes(byte[] bytes) {}
            public boolean nextBoolean() { return false; }
            // CHECKSTYLE: resume all
        };

         List<Double> items = Arrays.asList(1d, 2d);
         DiscreteProbabilityCollectionSampler<Double> sampler =
            new DiscreteProbabilityCollectionSampler<>(dummyRng,
                                                       items,
                                                       new double[] {0.5, 0.5});
         Double item1 = sampler.sample();
         Double item2 = sampler.sample();
        // Check they are in the list
        // removed other assertion
        // removed other assertion
        // Test the two samples are different items
        Assertions.assertNotSame(item1, item2, "Item1 and 2 should be different");
    }

}
