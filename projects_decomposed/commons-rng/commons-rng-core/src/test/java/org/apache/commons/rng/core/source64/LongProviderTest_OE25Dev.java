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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The tests the caching of calls to {@link LongProvider#nextLong()} are used as
 * the source for {@link LongProvider#nextInt()} and
 * {@link LongProvider#nextBoolean()}.
 */
class LongProviderTest_OE25Dev {
    /**
     * A simple class to return a fixed value as the source for
     * {@link LongProvider#next()}.
     */
    static final class FixedLongProvider extends LongProvider {
        /** The value. */
        private long value;

        /**
         * @param value the value
         */
        FixedLongProvider(long value) {
            this.value = value;
        }

        @Override
        public long next() {
            return value;
        }
    }

    /**
     * A simple class to flip the bits in a number as the source for
     * {@link LongProvider#next()}.
     */
    static final class FlipLongProvider extends LongProvider {
        /** The value. */
        private long value;

        /**
         * @param value the value
         */
        FlipLongProvider(long value) {
            // Flip the bits so the first call to next() returns to the same state
            this.value = ~value;
        }

        @Override
        public long next() {
            // Flip the bits
            value = ~value;
            return value;
        }
    }

    /**
     * This test ensures that the call to {@link LongProvider#nextInt()} returns the
     * upper and then lower 32-bits from {@link LongProvider#nextLong()}.
     */

    /**
     * This test ensures that the call to {@link LongProvider#nextBoolean()} returns
     * each of the bits from a call to {@link LongProvider#nextLong()}.
     *
     * <p>The order should be from the least-significant bit.
     */


}
