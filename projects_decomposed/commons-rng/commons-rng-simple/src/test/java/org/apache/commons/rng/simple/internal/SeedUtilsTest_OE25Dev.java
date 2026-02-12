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

package org.apache.commons.rng.simple.internal;

import org.apache.commons.math3.stat.inference.ChiSquareTest;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.core.source64.SplitMix64;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Tests for the {@link SeedUtils}.
 */
class SeedUtilsTest_OE25Dev {
    /**
     * Test the int hex permutation has 8 unique hex digits per permutation.
     * A uniformity test is performed on to check each hex digits is used evenly at each
     * character position.
     */

    /**
     * Test the long hex permutation has 8 unique hex digits per permutation in the upper and
     * lower 32-bits.
     * A uniformity test is performed on to check each hex digits is used evenly at each
     * character position.
     */


}
