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
package org.apache.commons.rng.simple;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.JumpableUniformRandomProvider;
import org.apache.commons.rng.LongJumpableUniformRandomProvider;
import org.apache.commons.rng.RandomProviderState;
import org.apache.commons.rng.RestorableUniformRandomProvider;
import org.apache.commons.rng.core.RandomProviderDefaultState;
import org.apache.commons.rng.core.source64.SplitMix64;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests which all generators must pass.
 */
class ProvidersCommonParametricTest_OE25Dev {
    private static Iterable<ProvidersList.Data> getProvidersTestData() {
        return ProvidersList.list();
    }

    // Seeding tests.

    /**
     * Test the factory create method returns the same class as the instance create method.
     */

    /**
     * Test the factory create method returns the same class as the instance create method
     * and produces the same output.
     */

    /**
     * Test the create method throws an {@link IllegalArgumentException} if passed the wrong
     * arguments.
     */

    @ParameterizedTest
    @MethodSource("getProvidersTestData")
    void testNullSeed(ProvidersList.Data data) {
        final RandomSource originalSource = data.getSource();
        final Object[] originalArgs = data.getArgs();
        // Note: This is the only test that explicitly calls RandomSource.create() with no other arguments.
        final UniformRandomProvider rng = originalArgs == null ?
            originalSource.create() :
            originalSource.create(null, originalArgs);
        checkNextIntegerInRange(rng, 10, 10000);
    }

    @ParameterizedTest
    @MethodSource("getProvidersTestData")
    void testEmptyIntArraySeed(ProvidersList.Data data) {
        final RandomSource originalSource = data.getSource();
        final Object[] originalArgs = data.getArgs();
        final int[] empty = new int[0];
        Assumptions.assumeTrue(originalSource.isNativeSeed(empty));

        // Exercise the default seeding procedure.
        final UniformRandomProvider rng = originalSource.create(empty, originalArgs);
        checkNextIntegerInRange(rng, 10, 20000);
    }

    @ParameterizedTest
    @MethodSource("getProvidersTestData")
    void testEmptyLongArraySeed(ProvidersList.Data data) {
        final RandomSource originalSource = data.getSource();
        final Object[] originalArgs = data.getArgs();
        final long[] empty = new long[0];
        Assumptions.assumeTrue(originalSource.isNativeSeed(empty));
        // The Middle-Square Weyl Sequence generator cannot self-seed
        Assumptions.assumeFalse(originalSource == RandomSource.MSWS);

        // Exercise the default seeding procedure.
        final UniformRandomProvider rng = originalSource.create(empty, originalArgs);
        checkNextIntegerInRange(rng, 10, 10000);
    }

    @ParameterizedTest
    @MethodSource("getProvidersTestData")
    void testZeroIntArraySeed(ProvidersList.Data data) {
        final RandomSource originalSource = data.getSource();
        final Object[] originalArgs = data.getArgs();
        // Exercise capacity to escape all "zero" state.
        final int[] zero = new int[2000]; // Large enough to fill the entire state with zeroes.
        final UniformRandomProvider rng = originalSource.create(zero, originalArgs);
        Assumptions.assumeTrue(createsNonZeroLongOutput(rng, 2000),
            () -> "RNG is non-functional with an all zero seed: " + originalSource);
        checkNextIntegerInRange(rng, 10, 10000);
    }

    @ParameterizedTest
    @MethodSource("getProvidersTestData")
    void testZeroLongArraySeed(ProvidersList.Data data) {
        final RandomSource originalSource = data.getSource();
        final Object[] originalArgs = data.getArgs();
        // Exercise capacity to escape all "zero" state.
        final long[] zero = new long[2000]; // Large enough to fill the entire state with zeroes.
        final UniformRandomProvider rng = originalSource.create(zero, originalArgs);
        Assumptions.assumeTrue(createsNonZeroLongOutput(rng, 2000),
            () -> "RNG is non-functional with an all zero seed: " + originalSource);
        checkNextIntegerInRange(rng, 10, 10000);
    }

    @ParameterizedTest
    @MethodSource("getProvidersTestData")
    void testRandomSourceCreateSeed(ProvidersList.Data data) {
        final RandomSource originalSource = data.getSource();
        final Object[] originalArgs = data.getArgs();
        final byte[] seed = originalSource.createSeed();
        final UniformRandomProvider rng = originalSource.create(seed, originalArgs);
        checkNextIntegerInRange(rng, 10, 10000);
    }

    @ParameterizedTest
    @MethodSource("getProvidersTestData")
    void testRandomSourceCreateSeedFromRNG(ProvidersList.Data data) {
        final RandomSource originalSource = data.getSource();
        final Object[] originalArgs = data.getArgs();
        final byte[] seed = originalSource.createSeed(new SplitMix64(RandomSource.createLong()));
        final UniformRandomProvider rng = originalSource.create(seed, originalArgs);
        checkNextIntegerInRange(rng, 10, 10000);
    }

    // State save and restore tests.

    ///// Support methods below.


    // The methods
    //   * makeList
    //   * checkNextIntegerInRange
    //   * checkNextInRange
    // have been copied from "src/test" in module "commons-rng-core".
    // TODO: check whether it is possible to have a single implementation.

    /**
     * Populates a list with random numbers.
     *
     * @param n Loop counter.
     * @param generator Random generator.
     * @return a list containing {@code 11 * n} random numbers.
     */
    private static List<Number> makeList(int n, UniformRandomProvider generator) {
        final List<Number> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            // Append 11 values.
            list.add(generator.nextInt());
            list.add(generator.nextInt(21));
            list.add(generator.nextInt(436));
            list.add(generator.nextLong());
            list.add(generator.nextLong(157894));
            list.add(generator.nextLong(5745833));
            list.add(generator.nextFloat());
            list.add(generator.nextFloat());
            list.add(generator.nextDouble());
            list.add(generator.nextDouble());
            list.add(generator.nextBoolean() ? 1 : 0);
        }

        return list;
    }

    /**
     * Tests uniformity of the distribution produced by {@code nextInt(int)}.
     *
     * @param rng Generator.
     * @param max Upper bound.
     * @param sampleSize Number of random values generated.
     * @param generator Random generator.
     */
    private void checkNextIntegerInRange(final UniformRandomProvider rng,
                                         final int max,
                                         int sampleSize) {
        final Callable<Integer> nextMethod = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return rng.nextInt(max);
            }
        };

        checkNextInRange(max, sampleSize, nextMethod, rng);
    }

    /**
     * Tests uniformity of the distribution produced by the given
     * {@code nextMethod}.
     * It performs a chi-square test of homogeneity of the observed
     * distribution with the expected uniform distribution.
     * Repeat tests are performed at the 1% level and the total number of failed
     * tests is tested at the 0.5% significance level.
     *
     * @param max Upper bound.
     * @param nextMethod method to call.
     * @param sampleSize Number of random values generated.
     * @param generator Random generator.
     */
    private static <T extends Number> void checkNextInRange(T max,
                                                            int sampleSize,
                                                            Callable<T> nextMethod,
                                                            UniformRandomProvider generator) {
        final int numTests = 500;

        // Do not change (statistical test assumes that dof = 9).
        final int numBins = 10; // dof = numBins - 1

        // Set up bins.
        final long n = max.longValue();
        final long[] binUpperBounds = new long[numBins];
        final double step = n / (double) numBins;
        for (int k = 0; k < numBins; k++) {
            binUpperBounds[k] = (long) ((k + 1) * step);
        }
        // Rounding error occurs on the long value of 2305843009213693951L
        binUpperBounds[numBins - 1] = n;

        // Run the tests.
        int numFailures = 0;

        final double[] expected = new double[numBins];
        long previousUpperBound = 0;
        for (int k = 0; k < numBins; k++) {
            final long range = binUpperBounds[k] - previousUpperBound;
            expected[k] = sampleSize * (range / (double) n);
            previousUpperBound = binUpperBounds[k];
        }

        final int[] observed = new int[numBins];
        // Chi-square critical value with 9 degrees of freedom
        // and 1% significance level.
        final double chi2CriticalValue = 21.67;

        try {
            for (int i = 0; i < numTests; i++) {
                Arrays.fill(observed, 0);
                for (int j = 0; j < sampleSize; j++) {
                    final long value = nextMethod.call().longValue();
                    Assertions.assertTrue(value >= 0 && value < n, "Range");

                    for (int k = 0; k < numBins; k++) {
                        if (value < binUpperBounds[k]) {
                            ++observed[k];
                            break;
                        }
                    }
                }

                // Compute chi-square.
                double chi2 = 0;
                for (int k = 0; k < numBins; k++) {
                    final double diff = observed[k] - expected[k];
                    chi2 += diff * diff / expected[k];
                }

                // Statistics check.
                if (chi2 > chi2CriticalValue) {
                    ++numFailures;
                }
            }
        } catch (Exception e) {
            // Should never happen.
            throw new RuntimeException("Unexpected", e);
        }

        // The expected number of failed tests can be modelled as a Binomial distribution
        // B(n, p) with n=500, p=0.01 (500 tests with a 1% significance level).
        // The cumulative probability of the number of failed tests (X) is:
        // x     P(X>x)
        // 10    0.0132
        // 11    0.00521
        // 12    0.00190

        if (numFailures > 11) { // Test will fail with 0.5% probability
            Assertions.fail(generator + ": Too many failures for n = " + n + "(" + numFailures + " out of " + numTests + " tests failed)");
        }
    }

    /**
     * Return true if the generator creates non-zero output from
     * {@link UniformRandomProvider#nextLong()} within the given number of cycles.
     *
     * @param rng Random generator.
     * @param cycles Number of cycles.
     * @return true if non-zero output
     */
    private static boolean createsNonZeroLongOutput(UniformRandomProvider rng,
                                                    int cycles) {
        boolean nonZero = false;
        for (int i = 0; i < cycles; i++) {
            if (rng.nextLong() != 0) {
                nonZero = true;
            }
        }
        return nonZero;
    }

    @ParameterizedTest
    @MethodSource("getProvidersTestData")
    void testUnsupportedSeedType_1_oe(ProvidersList.Data data) {
         byte seed = 123;
        try {
    data.getSource().create(seed, data.getArgs());
    fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @ParameterizedTest
    @MethodSource("getProvidersTestData")
    void testFactoryCreateMethod_1_oe(ProvidersList.Data data) {
         RandomSource originalSource = data.getSource();
         Object originalSeed = data.getSeed();
         Object[] originalArgs = data.getArgs();
        // Cannot test providers that require arguments
        Assumptions.assumeTrue(originalArgs == null);
        @SuppressWarnings("deprecation")
         UniformRandomProvider rng = RandomSource.create(data.getSource());
         UniformRandomProvider generator = originalSource.create(originalSeed, originalArgs);
        Assertions.assertEquals(generator.getClass(), rng.getClass());
    }

    @ParameterizedTest
    @MethodSource("getProvidersTestData")
    void testFactoryCreateMethodWithSeed_1_oe(ProvidersList.Data data) {
         RandomSource originalSource = data.getSource();
         Object originalSeed = data.getSeed();
         Object[] originalArgs = data.getArgs();
         UniformRandomProvider generator = originalSource.create(originalSeed, originalArgs);
        @SuppressWarnings("deprecation")
         UniformRandomProvider rng1 = RandomSource.create(originalSource, originalSeed, originalArgs);
        Assertions.assertEquals(rng1.getClass(), generator.getClass());
    }

    @ParameterizedTest
    @MethodSource("getProvidersTestData")
    void testFactoryCreateMethodWithSeed_2_oe(ProvidersList.Data data) {
         RandomSource originalSource = data.getSource();
         Object originalSeed = data.getSeed();
         Object[] originalArgs = data.getArgs();
         UniformRandomProvider generator = originalSource.create(originalSeed, originalArgs);
        @SuppressWarnings("deprecation")
         UniformRandomProvider rng1 = RandomSource.create(originalSource, originalSeed, originalArgs);
        // removed other assertion
        // Check the output
         UniformRandomProvider rng2 = originalSource.create(originalSeed, originalArgs);
        for (int i = 0; i < 10; i++) {
            Assertions.assertEquals(rng2.nextLong(), rng1.nextLong());
    }
    }

    @ParameterizedTest
    @MethodSource("getProvidersTestData")
    void testCreateMethodThrowsWithIncorrectArguments_1_oe(ProvidersList.Data data) {
         RandomSource originalSource = data.getSource();
         Object[] originalArgs = data.getArgs();
        if (originalArgs == null) {
            // Try passing arguments to a provider that does not require them
            int arg1 = 123;
            double arg2 = 456.0;
            try {
    originalSource.create(arg1, arg2);
    fail("IllegalArgumentException: () -> \"Source does not require arguments: \" + originalSource");
} catch (IllegalArgumentException e) {
}
    }
    }

    @ParameterizedTest
    @MethodSource("getProvidersTestData")
    void testCreateMethodThrowsWithIncorrectArguments_2_oe(ProvidersList.Data data) {
         RandomSource originalSource = data.getSource();
         Object[] originalArgs = data.getArgs();
        if (originalArgs == null) {
            // Try passing arguments to a provider that does not require them
            int arg1 = 123;
            double arg2 = 456.0;
            // removed other assertion
        } else {
            // Try no arguments for a provider that does require them
            try {
    originalSource.create();
    fail("IllegalArgumentException: () -> \"Source requires arguments: \" + originalSource");
} catch (IllegalArgumentException e) {
}
    }
    }

    @ParameterizedTest
    @MethodSource("getProvidersTestData")
    void testAllSeedTypes_1_oe(ProvidersList.Data data) {
         RandomSource originalSource = data.getSource();
         Object originalSeed = data.getSeed();
         Object[] originalArgs = data.getArgs();
         Integer intSeed = -12131415;
         Long longSeed = -1213141516171819L;
         int[] intArraySeed = new int[] {0, 11, -22, 33, -44, 55, -66, 77, -88, 99};
         long[] longArraySeed = new long[] {11111L, -222222L, 3333333L, -44444444L};
         byte[] byteArraySeed = new byte[] {-128, -91, -45, -32, -1, 0, 11, 23, 54, 88, 127};

         Object[] seeds = new Object[] {null,
                                             intSeed,
                                             longSeed,
                                             intArraySeed,
                                             longArraySeed,
                                             byteArraySeed};

        int nonNativeSeedCount = 0;
        int seedCount = 0;
        for (Object s : seeds) {
            ++seedCount;
            if (originalSource.isNativeSeed(s)) {
                Assertions.assertNotNull(s, "Identified native seed is null");
    }
    }
    }

    @ParameterizedTest
    @MethodSource("getProvidersTestData")
    void testAllSeedTypes_2_oe(ProvidersList.Data data) {
         RandomSource originalSource = data.getSource();
         Object originalSeed = data.getSeed();
         Object[] originalArgs = data.getArgs();
         Integer intSeed = -12131415;
         Long longSeed = -1213141516171819L;
         int[] intArraySeed = new int[] {0, 11, -22, 33, -44, 55, -66, 77, -88, 99};
         long[] longArraySeed = new long[] {11111L, -222222L, 3333333L, -44444444L};
         byte[] byteArraySeed = new byte[] {-128, -91, -45, -32, -1, 0, 11, 23, 54, 88, 127};

         Object[] seeds = new Object[] {null,
                                             intSeed,
                                             longSeed,
                                             intArraySeed,
                                             longArraySeed,
                                             byteArraySeed};

        int nonNativeSeedCount = 0;
        int seedCount = 0;
        for (Object s : seeds) {
            ++seedCount;
            if (originalSource.isNativeSeed(s)) {
                // removed other assertion
                Assertions.assertEquals(s.getClass(),originalSeed.getClass(),"Incorrect identification of native seed type");
    }
    }
    }

    @ParameterizedTest
    @MethodSource("getProvidersTestData")
    void testAllSeedTypes_3_oe(ProvidersList.Data data) {
         RandomSource originalSource = data.getSource();
         Object originalSeed = data.getSeed();
         Object[] originalArgs = data.getArgs();
         Integer intSeed = -12131415;
         Long longSeed = -1213141516171819L;
         int[] intArraySeed = new int[] {0, 11, -22, 33, -44, 55, -66, 77, -88, 99};
         long[] longArraySeed = new long[] {11111L, -222222L, 3333333L, -44444444L};
         byte[] byteArraySeed = new byte[] {-128, -91, -45, -32, -1, 0, 11, 23, 54, 88, 127};

         Object[] seeds = new Object[] {null,
                                             intSeed,
                                             longSeed,
                                             intArraySeed,
                                             longArraySeed,
                                             byteArraySeed};

        int nonNativeSeedCount = 0;
        int seedCount = 0;
        for (Object s : seeds) {
            ++seedCount;
            if (originalSource.isNativeSeed(s)) {
                // removed other assertion
                // removed other assertion
            } else {
                ++nonNativeSeedCount;
            }

            originalSource.create(s, originalArgs);
        }

        Assertions.assertEquals(6, seedCount);
    }

    @ParameterizedTest
    @MethodSource("getProvidersTestData")
    void testAllSeedTypes_4_oe(ProvidersList.Data data) {
         RandomSource originalSource = data.getSource();
         Object originalSeed = data.getSeed();
         Object[] originalArgs = data.getArgs();
         Integer intSeed = -12131415;
         Long longSeed = -1213141516171819L;
         int[] intArraySeed = new int[] {0, 11, -22, 33, -44, 55, -66, 77, -88, 99};
         long[] longArraySeed = new long[] {11111L, -222222L, 3333333L, -44444444L};
         byte[] byteArraySeed = new byte[] {-128, -91, -45, -32, -1, 0, 11, 23, 54, 88, 127};

         Object[] seeds = new Object[] {null,
                                             intSeed,
                                             longSeed,
                                             intArraySeed,
                                             longArraySeed,
                                             byteArraySeed};

        int nonNativeSeedCount = 0;
        int seedCount = 0;
        for (Object s : seeds) {
            ++seedCount;
            if (originalSource.isNativeSeed(s)) {
                // removed other assertion
                // removed other assertion
            } else {
                ++nonNativeSeedCount;
            }

            originalSource.create(s, originalArgs);
        }

        // removed other assertion
        Assertions.assertEquals(5, nonNativeSeedCount);
    }

    @ParameterizedTest
    @MethodSource("getProvidersTestData")
    void testUnrestorable_2_oe(ProvidersList.Data data) {
         RandomSource originalSource = data.getSource();
         Object originalSeed = data.getSeed();
         Object[] originalArgs = data.getArgs();
        // Create two generators of the same type as the one being tested.
         UniformRandomProvider rng1 = originalSource.create(originalSeed, originalArgs);
         UniformRandomProvider rng2 = RandomSource.unrestorable(originalSource.create(originalSeed, originalArgs));

        // Ensure that they generate the same values.
        // removed other assertion

        // Cast must work.
         RestorableUniformRandomProvider restorable = (RestorableUniformRandomProvider) rng1;
        // Cast must fail.
        try {
     RestorableUniformRandomProvider dummy = (RestorableUniformRandomProvider) rng2;
    fail("ClassCastException");
} catch (ClassCastException e) {
}
    }

    @ParameterizedTest
    @MethodSource("getProvidersTestData")
    void testSerializingState_1_oe(ProvidersList.Data data)
        throws IOException,
               ClassNotFoundException {
         UniformRandomProvider generator = data.getSource().create(data.getSeed(), data.getArgs());

        // Large "n" is not necessary here as we only test the serialization.
         int n = 100;

        // Cast is OK: all instances created by this library inherit from "BaseProvider".
         RestorableUniformRandomProvider restorable = (RestorableUniformRandomProvider) generator;

        // Save.
         RandomProviderState stateOrig = restorable.saveState();
        // Serialize.
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(((RandomProviderDefaultState) stateOrig).getState());

        // Store some values.
         List<Number> listOrig = makeList(n, generator);

        // Discard a few more.
         List<Number> listDiscard = makeList(n, generator);
        Assertions.assertNotEquals(0, listDiscard.size());
    }

    @ParameterizedTest
    @MethodSource("getProvidersTestData")
    void testSerializingState_2_oe(ProvidersList.Data data)
        throws IOException,
               ClassNotFoundException {
         UniformRandomProvider generator = data.getSource().create(data.getSeed(), data.getArgs());

        // Large "n" is not necessary here as we only test the serialization.
         int n = 100;

        // Cast is OK: all instances created by this library inherit from "BaseProvider".
         RestorableUniformRandomProvider restorable = (RestorableUniformRandomProvider) generator;

        // Save.
         RandomProviderState stateOrig = restorable.saveState();
        // Serialize.
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(((RandomProviderDefaultState) stateOrig).getState());

        // Store some values.
         List<Number> listOrig = makeList(n, generator);

        // Discard a few more.
         List<Number> listDiscard = makeList(n, generator);
        // removed other assertion
        Assertions.assertNotEquals(listOrig, listDiscard);
    }

    @ParameterizedTest
    @MethodSource("getProvidersTestData")
    void testSerializingState_3_oe(ProvidersList.Data data)
        throws IOException,
               ClassNotFoundException {
         UniformRandomProvider generator = data.getSource().create(data.getSeed(), data.getArgs());

        // Large "n" is not necessary here as we only test the serialization.
         int n = 100;

        // Cast is OK: all instances created by this library inherit from "BaseProvider".
         RestorableUniformRandomProvider restorable = (RestorableUniformRandomProvider) generator;

        // Save.
         RandomProviderState stateOrig = restorable.saveState();
        // Serialize.
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(((RandomProviderDefaultState) stateOrig).getState());

        // Store some values.
         List<Number> listOrig = makeList(n, generator);

        // Discard a few more.
         List<Number> listDiscard = makeList(n, generator);
        // removed other assertion
        // removed other assertion

        // Retrieve from serialized stream.
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
         RandomProviderState stateNew = new RandomProviderDefaultState((byte[]) ois.readObject());

        Assertions.assertNotSame(stateOrig, stateNew);
    }

    @ParameterizedTest
    @MethodSource("getProvidersTestData")
    void testSerializingState_4_oe(ProvidersList.Data data)
        throws IOException,
               ClassNotFoundException {
         UniformRandomProvider generator = data.getSource().create(data.getSeed(), data.getArgs());

        // Large "n" is not necessary here as we only test the serialization.
         int n = 100;

        // Cast is OK: all instances created by this library inherit from "BaseProvider".
         RestorableUniformRandomProvider restorable = (RestorableUniformRandomProvider) generator;

        // Save.
         RandomProviderState stateOrig = restorable.saveState();
        // Serialize.
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(((RandomProviderDefaultState) stateOrig).getState());

        // Store some values.
         List<Number> listOrig = makeList(n, generator);

        // Discard a few more.
         List<Number> listDiscard = makeList(n, generator);
        // removed other assertion
        // removed other assertion

        // Retrieve from serialized stream.
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
         RandomProviderState stateNew = new RandomProviderDefaultState((byte[]) ois.readObject());

        // removed other assertion

        // Reset.
        restorable.restoreState(stateNew);

        // Replay.
         List<Number> listReplay = makeList(n, generator);
        Assertions.assertNotSame(listOrig, listReplay);
    }

    @ParameterizedTest
    @MethodSource("getProvidersTestData")
    void testSerializingState_5_oe(ProvidersList.Data data)
        throws IOException,
               ClassNotFoundException {
         UniformRandomProvider generator = data.getSource().create(data.getSeed(), data.getArgs());

        // Large "n" is not necessary here as we only test the serialization.
         int n = 100;

        // Cast is OK: all instances created by this library inherit from "BaseProvider".
         RestorableUniformRandomProvider restorable = (RestorableUniformRandomProvider) generator;

        // Save.
         RandomProviderState stateOrig = restorable.saveState();
        // Serialize.
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(((RandomProviderDefaultState) stateOrig).getState());

        // Store some values.
         List<Number> listOrig = makeList(n, generator);

        // Discard a few more.
         List<Number> listDiscard = makeList(n, generator);
        // removed other assertion
        // removed other assertion

        // Retrieve from serialized stream.
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
         RandomProviderState stateNew = new RandomProviderDefaultState((byte[]) ois.readObject());

        // removed other assertion

        // Reset.
        restorable.restoreState(stateNew);

        // Replay.
         List<Number> listReplay = makeList(n, generator);
        // removed other assertion

        // Check that the serialized data recreated the orginal state.
        Assertions.assertEquals(listOrig, listReplay);
    }

    @ParameterizedTest
    @MethodSource("getProvidersTestData")
    void testUnrestorableToString_1_oe(ProvidersList.Data data) {
         UniformRandomProvider generator = data.getSource().create(data.getSeed(), data.getArgs());
        Assertions.assertEquals(generator.toString(),RandomSource.unrestorable(generator).toString());
    }

    @ParameterizedTest
    @MethodSource("getProvidersTestData")
    void testSupportedInterfaces_1_oe(ProvidersList.Data data) {
         RandomSource originalSource = data.getSource();
         Object[] originalArgs = data.getArgs();
         UniformRandomProvider rng = originalSource.create(null, originalArgs);
        Assertions.assertEquals(rng instanceof JumpableUniformRandomProvider,originalSource.isJumpable(),"isJumpable");
    }

    @ParameterizedTest
    @MethodSource("getProvidersTestData")
    void testSupportedInterfaces_2_oe(ProvidersList.Data data) {
         RandomSource originalSource = data.getSource();
         Object[] originalArgs = data.getArgs();
         UniformRandomProvider rng = originalSource.create(null, originalArgs);
        // removed other assertion
        Assertions.assertEquals(rng instanceof LongJumpableUniformRandomProvider,originalSource.isLongJumpable(),"isLongJumpable");
    }

}
