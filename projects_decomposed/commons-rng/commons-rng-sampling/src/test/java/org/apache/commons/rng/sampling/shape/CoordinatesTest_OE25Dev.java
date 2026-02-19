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
package org.apache.commons.rng.sampling.shape;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test for {@link Coordinates} utility class.
 */
class CoordinatesTest_OE25Dev {
    /**
     * Test {@link Coordinates#requireFinite(double[], String)} detects infinite and NaN.
     */

    /**
     * Test {@link Coordinates#requireLength(double[], int, String)} detects invalid lengths.
     */

    @Test
    void testRequireFiniteWithMessageThrows_1_oe() {
         double[] c = {0, 1, 2};
         String message = "This should be prepended";
        Assertions.assertSame(c, Coordinates.requireFinite(c, message));
    }

    @Test
    void testRequireFiniteWithMessageThrows_2_oe() {
         double[] c = {0, 1, 2};
         String message = "This should be prepended";
        // removed other assertion
         double[] bad = {Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NaN};
        for (int i = 0; i < c.length; i++) {
             int ii = i;
            for ( double d : bad) {
                 double value = c[i];
                c[i] = d;
                try {
    Coordinates.requireFinite(c, message);
    fail("IllegalArgumentException: () -> String.format(\"Did not detect non-finite coordinate: %d = %s\", ii, d)");
} catch (IllegalArgumentException e) {
}
    }
    }
    }

    @Test
    void testRequireLengthWithMessageThrows_1_oe() {
         String message = "This should be prepended";
        for ( double[] c : new double[][] {{0, 1}, {0, 1, 2}}) {
             int length = c.length;
            Assertions.assertSame(c, Coordinates.requireLength(c, length, message));
    }
    }

    @Test
    void testRequireLengthWithMessageThrows_2_oe() {
         String message = "This should be prepended";
        for ( double[] c : new double[][] {{0, 1}, {0, 1, 2}}) {
             int length = c.length;
            // removed other assertion
            try {
    Coordinates.requireLength(c, length - 1, message);
    fail("IllegalArgumentException: () -> \"Did not detect length was too long: \" + (length - 1)");
} catch (IllegalArgumentException e) {
}
    }
    }

}
