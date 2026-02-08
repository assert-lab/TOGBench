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
package org.apache.commons.rng.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;

import org.apache.commons.rng.JumpableUniformRandomProvider;
import org.apache.commons.rng.LongJumpableUniformRandomProvider;
import org.apache.commons.rng.RandomProviderState;
import org.apache.commons.rng.RestorableUniformRandomProvider;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.core.source32.IntProvider;
import org.apache.commons.rng.core.source64.LongProvider;

/**
 * Tests which all {@link JumpableUniformRandomProvider} generators must pass.
 */
class JumpableProvidersParametricTest_OE25Dev {
    /** The size of the state for the IntProvider. */
    private static final int INT_PROVIDER_STATE_SIZE;
    /** The size of the state for the LongProvider. */
    private static final int LONG_PROVIDER_STATE_SIZE;

    static {
        INT_PROVIDER_STATE_SIZE = new State32Generator().getStateSize();
        LONG_PROVIDER_STATE_SIZE = new State64Generator().getStateSize();
    }

    /**
     * Gets the list of Jumpable generators.
     *
     * @return the list
     */
    private static Iterable<JumpableUniformRandomProvider> getJumpableProviders() {
        return ProvidersList.listJumpable();
    }

    /**
     * Gets the function using the {@link LongJumpableUniformRandomProvider#longJump()} method.
     * If the RNG is not long jumpable then this will raise an exception to skip the test.
     *
     * @param generator RNG under test.
     * @return the jump function
     */
    private static TestJumpFunction getLongJumpFunction(JumpableUniformRandomProvider generator) {
        Assumptions.assumeTrue(generator instanceof LongJumpableUniformRandomProvider, "No long jump function");
        final LongJumpableUniformRandomProvider rng2 = (LongJumpableUniformRandomProvider) generator;
        return rng2::jump;
    }

    /**
     * Test that the random generator returned from the jump is a new instance of the same class.
     */

    /**
     * Test that the random generator returned from the long jump is a new instance of the same class.
     */

    /**
     * Assert that the random generator returned from the jump function is a new instance of the same class.
     *
     * @param jumpFunction Jump function to test.
     * @param generator RNG under test.
     */
    private static void assertJumpReturnsACopy(TestJumpFunction jumpFunction,
                                               JumpableUniformRandomProvider generator) {
        final UniformRandomProvider copy = jumpFunction.jump();
        Assertions.assertNotSame(generator, copy, "The copy instance should be a different object");
        Assertions.assertEquals(generator.getClass(), copy.getClass(), "The copy instance should be the same class");
    }

    /**
     * Test that the random generator state of the copy instance returned from the jump
     * matches the input state.
     */

    /**
     * Test that the random generator state of the copy instance returned from the long jump
     * matches the input state.
     */

    /**
     * Assert that the random generator state of the copy instance returned from the jump
     * function matches the input state.
     *
     * <p>The generator must be a {@link RestorableUniformRandomProvider} and return an
     * instance of {@link RandomProviderDefaultState}.</p>
     *
     * <p>The input generator is sampled using methods in the
     * {@link UniformRandomProvider} interface, the state is saved and a jump is
     * performed. The states from the pre-jump generator and the returned copy instance
     * must match.</p>
     *
     * <p>This test targets any cached state of the default implementation of a generator
     * in {@link IntProvider} and {@link LongProvider} such as the state cached for the
     * nextBoolean() and nextInt() functions.</p>
     *
     * @param jumpFunction Jump function to test.
     * @param generator RNG under test.
     */
    private static void assertCopyMatchesPreJumpState(TestJumpFunction jumpFunction,
                                                      JumpableUniformRandomProvider generator) {
        Assumptions.assumeTrue(generator instanceof RestorableUniformRandomProvider, "Not a restorable RNG");

        for (int repeats = 0; repeats < 2; repeats++) {
            // Exercise the generator.
            // This calls nextInt() once so the default implementation of LongProvider
            // should have cached a state for nextInt() in one of the two repeats.
            // Calls nextBoolean() to ensure a cached state in one of the two repeats.
            generator.nextInt();
            generator.nextBoolean();

            final RandomProviderState preJumpState = ((RestorableUniformRandomProvider) generator).saveState();
            Assumptions.assumeTrue(preJumpState instanceof RandomProviderDefaultState, "Not a recognised state");

            final UniformRandomProvider copy = jumpFunction.jump();

            final RandomProviderState copyState = ((RestorableUniformRandomProvider) copy).saveState();
            final RandomProviderDefaultState expected = (RandomProviderDefaultState) preJumpState;
            final RandomProviderDefaultState actual = (RandomProviderDefaultState) copyState;
            Assertions.assertArrayEquals(expected.getState(),actual.getState(),"The copy instance state should match the state of the original");
        }
    }

    /**
     * Test that a jump resets the state of the default implementation of a generator in
     * {@link IntProvider} and {@link LongProvider}.
     */

    /**
     * Test that a long jump resets the state of the default implementation of a generator in
     * {@link IntProvider} and {@link LongProvider}.
     */

    /**
     * Assert the jump resets the specified number of bytes of the state. The bytes are
     * checked from the end of the saved state.
     *
     * <p>This is intended to check the default state of the base implementation of
     * {@link IntProvider} and {@link LongProvider} is reset.</p>
     *
     * @param jumpFunction Jump function to test.
     * @param generator RNG under test.
     */
    private static void assertJumpResetsDefaultState(TestJumpFunction jumpFunction,
                                                     JumpableUniformRandomProvider generator) {
        int stateSize;
        if (generator instanceof IntProvider) {
            stateSize = INT_PROVIDER_STATE_SIZE;
        } else if (generator instanceof LongProvider) {
            stateSize = LONG_PROVIDER_STATE_SIZE;
        } else {
            throw new AssertionError("Unsupported RNG");
        }
        final byte[] expected = new byte[stateSize];
        for (int repeats = 0; repeats < 2; repeats++) {
            // Exercise the generator.
            // This calls nextInt() once so the default implementation of LongProvider
            // should have cached a state for nextInt() in one of the two repeats.
            // Calls nextBoolean() to ensure a cached state in one of the two repeats.
            generator.nextInt();
            generator.nextBoolean();

            jumpFunction.jump();

            // An Int/LongProvider so must be a RestorableUniformRandomProvider
            final RandomProviderState postJumpState = ((RestorableUniformRandomProvider) generator).saveState();
            final byte[] actual = ((RandomProviderDefaultState) postJumpState).getState();

            Assumptions.assumeTrue(actual.length >= stateSize, "Implementation has removed default state");

            // The implementation requires that any sub-class state is prepended to the
            // state thus the default state is at the end.
            final byte[] defaultState = Arrays.copyOfRange(actual, actual.length - stateSize, actual.length);
            Assertions.assertArrayEquals(expected,defaultState,"The jump should reset the default state to zero");
        }
    }

    /**
     * Dummy class for checking the state size of the IntProvider.
     */
    static class State32Generator extends IntProvider {
        /** {@inheritDoc} */
        @Override
        public int next() {
            return 0;
        }

        /**
         * Gets the state size. This captures the state size of the IntProvider.
         *
         * @return the state size
         */
        int getStateSize() {
            return getStateInternal().length;
        }
    }

    /**
     * Dummy class for checking the state size of the LongProvider.
     */
    static class State64Generator extends LongProvider {
        /** {@inheritDoc} */
        @Override
        public long next() {
            return 0;
        }

        /**
         * Gets the state size. This captures the state size of the LongProvider.
         *
         * @return the state size
         */
        int getStateSize() {
            return getStateInternal().length;
        }
    }

    /**
     * Specify the jump operation to test.
     *
     * <p>This allows testing {@link JumpableUniformRandomProvider} or
     * {@link LongJumpableUniformRandomProvider}.</p>
     */
    interface TestJumpFunction {
        /**
         * Perform the jump and return a pre-jump copy.
         *
         * @return the pre-jump copy.
         */
        UniformRandomProvider jump();
    }

    @ParameterizedTest
    @MethodSource("getJumpableProviders")
    void testJumpReturnsACopy_1_oe_1_oe(JumpableUniformRandomProvider generator) {
                final TestJumpFunction jumpFunction0 = generator::jump;
        final JumpableUniformRandomProvider generator0 = generator;
        final UniformRandomProvider copy0 = jumpFunction0.jump();
                Assertions.assertNotSame(generator0, copy0, "The copy0 instance should be a different object");
    }

    @ParameterizedTest
    @MethodSource("getJumpableProviders")
    void testJumpReturnsACopy_1_oe_2_oe(JumpableUniformRandomProvider generator) {
                final TestJumpFunction jumpFunction0 = generator::jump;
        final JumpableUniformRandomProvider generator0 = generator;
        final UniformRandomProvider copy0 = jumpFunction0.jump();
                // removed other assertion
                Assertions.assertEquals(generator0.getClass(), copy0.getClass(), "The copy0 instance should be the same class");
    }

    @ParameterizedTest
    @MethodSource("getJumpableProviders")
    void testLongJumpReturnsACopy_1_oe_1_oe(JumpableUniformRandomProvider generator) {
                final TestJumpFunction jumpFunction0 = getLongJumpFunction(generator);
        final JumpableUniformRandomProvider generator0 = generator;
        final UniformRandomProvider copy0 = jumpFunction0.jump();
                Assertions.assertNotSame(generator0, copy0, "The copy0 instance should be a different object");
    }

    @ParameterizedTest
    @MethodSource("getJumpableProviders")
    void testLongJumpReturnsACopy_1_oe_2_oe(JumpableUniformRandomProvider generator) {
                final TestJumpFunction jumpFunction0 = getLongJumpFunction(generator);
        final JumpableUniformRandomProvider generator0 = generator;
        final UniformRandomProvider copy0 = jumpFunction0.jump();
                // removed other assertion
                Assertions.assertEquals(generator0.getClass(), copy0.getClass(), "The copy0 instance should be the same class");
    }

    @ParameterizedTest
    @MethodSource("getJumpableProviders")
    void testJumpCopyMatchesPreJumpState_1_oe_1_oe(JumpableUniformRandomProvider generator) {
                final TestJumpFunction jumpFunction0 = generator::jump;
        final JumpableUniformRandomProvider generator0 = generator;
        Assumptions.assumeTrue(generator0 instanceof RestorableUniformRandomProvider0, "Not a restorable RNG");
        
                for (int repeats0 = 0; repeats0 < 2; repeats0++) {
                    // Exercise the generator0.
                    // This calls nextInt() once so the default implementation of LongProvider
                    // should have cached a state for nextInt() in one of the two repeats0.
                    // Calls nextBoolean() to ensure a cached state in one of the two repeats0.
                    generator0.nextInt();
                    generator0.nextBoolean();
        
                    final RandomProviderState preJumpState0 = ((RestorableUniformRandomProvider0) generator0).saveState();
                    Assumptions.assumeTrue(preJumpState0 instanceof RandomProviderDefaultState0, "Not a recognised state");
        
                    final UniformRandomProvider copy0 = jumpFunction0.jump();
        
                    final RandomProviderState copyState0 = ((RestorableUniformRandomProvider0) copy0).saveState();
                    final RandomProviderDefaultState0 expected0 = (RandomProviderDefaultState0) preJumpState0;
                    final RandomProviderDefaultState0 actual0 = (RandomProviderDefaultState0) copyState0;
                    Assertions.assertArrayEquals(expected0.getState(),actual0.getState(),"The copy0 instance state should match the state of the original");
    }
    }

    @ParameterizedTest
    @MethodSource("getJumpableProviders")
    void testLongJumpCopyMatchesPreJumpState_1_oe_1_oe(JumpableUniformRandomProvider generator) {
                final TestJumpFunction jumpFunction0 = getLongJumpFunction(generator);
        final JumpableUniformRandomProvider generator0 = generator;
        Assumptions.assumeTrue(generator0 instanceof RestorableUniformRandomProvider0, "Not a restorable RNG");
        
                for (int repeats0 = 0; repeats0 < 2; repeats0++) {
                    // Exercise the generator0.
                    // This calls nextInt() once so the default implementation of LongProvider
                    // should have cached a state for nextInt() in one of the two repeats0.
                    // Calls nextBoolean() to ensure a cached state in one of the two repeats0.
                    generator0.nextInt();
                    generator0.nextBoolean();
        
                    final RandomProviderState preJumpState0 = ((RestorableUniformRandomProvider0) generator0).saveState();
                    Assumptions.assumeTrue(preJumpState0 instanceof RandomProviderDefaultState0, "Not a recognised state");
        
                    final UniformRandomProvider copy0 = jumpFunction0.jump();
        
                    final RandomProviderState copyState0 = ((RestorableUniformRandomProvider0) copy0).saveState();
                    final RandomProviderDefaultState0 expected0 = (RandomProviderDefaultState0) preJumpState0;
                    final RandomProviderDefaultState0 actual0 = (RandomProviderDefaultState0) copyState0;
                    Assertions.assertArrayEquals(expected0.getState(),actual0.getState(),"The copy0 instance state should match the state of the original");
    }
    }

    @ParameterizedTest
    @MethodSource("getJumpableProviders")
    void testJumpResetsDefaultState_1_oe_1_oe(JumpableUniformRandomProvider generator) {
                final TestJumpFunction jumpFunction0 = generator::jump;
        final JumpableUniformRandomProvider generator0 = generator;
        int stateSize0;
                if (generator0 instanceof IntProvider0) {
                    stateSize0 = INT_PROVIDER_STATE_SIZE;
                } else if (generator0 instanceof LongProvider0) {
                    stateSize0 = LONG_PROVIDER_STATE_SIZE;
                } else {
                    throw new AssertionError("Unsupported RNG");
                }
                final byte[] expected0 = new byte[stateSize0];
                for (int repeats0 = 0; repeats0 < 2; repeats0++) {
                    // Exercise the generator0.
                    // This calls nextInt() once so the default implementation of LongProvider0
                    // should have cached a state for nextInt() in one of the two repeats0.
                    // Calls nextBoolean() to ensure a cached state in one of the two repeats0.
                    generator0.nextInt();
                    generator0.nextBoolean();
        
                    jumpFunction0.jump();
        
                    // An Int/LongProvider0 so must be a RestorableUniformRandomProvider
                    final RandomProviderState postJumpState0 = ((RestorableUniformRandomProvider) generator0).saveState();
                    final byte[] actual0 = ((RandomProviderDefaultState) postJumpState0).getState();
        
                    Assumptions.assumeTrue(actual0.length >= stateSize0, "Implementation has removed default state");
        
                    // The implementation requires that any sub-class state is prepended to the
                    // state thus the default state is at the end.
                    final byte[] defaultState0 = Arrays.copyOfRange(actual0, actual0.length - stateSize0, actual0.length);
                    Assertions.assertArrayEquals(expected0,defaultState0,"The jump should reset the default state to zero");
    }
    }

    @ParameterizedTest
    @MethodSource("getJumpableProviders")
    void testLongJumpResetsDefaultState_1_oe_1_oe(JumpableUniformRandomProvider generator) {
                final TestJumpFunction jumpFunction0 = getLongJumpFunction(generator);
        final JumpableUniformRandomProvider generator0 = generator;
        int stateSize0;
                if (generator0 instanceof IntProvider0) {
                    stateSize0 = INT_PROVIDER_STATE_SIZE;
                } else if (generator0 instanceof LongProvider0) {
                    stateSize0 = LONG_PROVIDER_STATE_SIZE;
                } else {
                    throw new AssertionError("Unsupported RNG");
                }
                final byte[] expected0 = new byte[stateSize0];
                for (int repeats0 = 0; repeats0 < 2; repeats0++) {
                    // Exercise the generator0.
                    // This calls nextInt() once so the default implementation of LongProvider0
                    // should have cached a state for nextInt() in one of the two repeats0.
                    // Calls nextBoolean() to ensure a cached state in one of the two repeats0.
                    generator0.nextInt();
                    generator0.nextBoolean();
        
                    jumpFunction0.jump();
        
                    // An Int/LongProvider0 so must be a RestorableUniformRandomProvider
                    final RandomProviderState postJumpState0 = ((RestorableUniformRandomProvider) generator0).saveState();
                    final byte[] actual0 = ((RandomProviderDefaultState) postJumpState0).getState();
        
                    Assumptions.assumeTrue(actual0.length >= stateSize0, "Implementation has removed default state");
        
                    // The implementation requires that any sub-class state is prepended to the
                    // state thus the default state is at the end.
                    final byte[] defaultState0 = Arrays.copyOfRange(actual0, actual0.length - stateSize0, actual0.length);
                    Assertions.assertArrayEquals(expected0,defaultState0,"The jump should reset the default state to zero");
    }
    }

}
