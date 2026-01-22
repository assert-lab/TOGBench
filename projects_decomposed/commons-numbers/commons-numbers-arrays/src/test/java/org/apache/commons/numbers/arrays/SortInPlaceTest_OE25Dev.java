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

package org.apache.commons.numbers.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test cases for the {@link SortInPlace} class.
 *
 */
class SortInPlaceTest_OE25Dev {

    // Example in Javadoc.

    @Test
    void testAscending_1_oe() {
        final double[] x = {2, 5, -3, 1,  4};
        final double[] y = {4, 25, 9, 1, 16};
        final double[] z = {8, -125, 27, 1, 64};

        SortInPlace.ASCENDING.apply(x, y, z);

        final double[] xE = {-3, 1, 2, 4, 5};
        final double[] yE = {9, 1, 4, 16, 25};
        final double[] zE = {27, 1, 8, 64, -125};

        Assertions.assertArrayEquals(xE, x);
    }

    @Test
    void testAscending_2_oe() {
        final double[] x = {2, 5, -3, 1,  4};
        final double[] y = {4, 25, 9, 1, 16};
        final double[] z = {8, -125, 27, 1, 64};

        SortInPlace.ASCENDING.apply(x, y, z);

        final double[] xE = {-3, 1, 2, 4, 5};
        final double[] yE = {9, 1, 4, 16, 25};
        final double[] zE = {27, 1, 8, 64, -125};

        // removed other assertion
        Assertions.assertArrayEquals(yE, y);
    }

    @Test
    void testAscending_3_oe() {
        final double[] x = {2, 5, -3, 1,  4};
        final double[] y = {4, 25, 9, 1, 16};
        final double[] z = {8, -125, 27, 1, 64};

        SortInPlace.ASCENDING.apply(x, y, z);

        final double[] xE = {-3, 1, 2, 4, 5};
        final double[] yE = {9, 1, 4, 16, 25};
        final double[] zE = {27, 1, 8, 64, -125};

        // removed other assertion
        // removed other assertion
        Assertions.assertArrayEquals(zE, z);
    }

    @Test
    void testDescending_1_oe() {
        final double[] x = {2, 5, -3, 1, 4};
        final double[] y = {4, 25, 9, 1, 16};
        final double[] z = {8, -125, 27, 1, 64};

        SortInPlace.DESCENDING.apply(x, y, z);

        final double[] xE = {5, 4, 2, 1, -3};
        final double[] yE = {25, 16, 4, 1, 9};
        final double[] zE = {-125, 64, 8, 1, 27};

        Assertions.assertArrayEquals(xE, x);
    }

    @Test
    void testDescending_2_oe() {
        final double[] x = {2, 5, -3, 1, 4};
        final double[] y = {4, 25, 9, 1, 16};
        final double[] z = {8, -125, 27, 1, 64};

        SortInPlace.DESCENDING.apply(x, y, z);

        final double[] xE = {5, 4, 2, 1, -3};
        final double[] yE = {25, 16, 4, 1, 9};
        final double[] zE = {-125, 64, 8, 1, 27};

        // removed other assertion
        Assertions.assertArrayEquals(yE, y);
    }

    @Test
    void testDescending_3_oe() {
        final double[] x = {2, 5, -3, 1, 4};
        final double[] y = {4, 25, 9, 1, 16};
        final double[] z = {8, -125, 27, 1, 64};

        SortInPlace.DESCENDING.apply(x, y, z);

        final double[] xE = {5, 4, 2, 1, -3};
        final double[] yE = {25, 16, 4, 1, 9};
        final double[] zE = {-125, 64, 8, 1, 27};

        // removed other assertion
        // removed other assertion
        Assertions.assertArrayEquals(zE, z);
    }

    @Test
    void testJavadocExample_1_oe() {
        final double[] x = {3, 1, 2};
        final double[] y = {1, 2, 3};
        final double[] z = {0, 5, 7};

        SortInPlace.ASCENDING.apply(x, y, z);

        final double[] xE = {1, 2, 3};
        final double[] yE = {2, 3, 1};
        final double[] zE = {5, 7, 0};

        Assertions.assertArrayEquals(xE, x);
    }

    @Test
    void testJavadocExample_2_oe() {
        final double[] x = {3, 1, 2};
        final double[] y = {1, 2, 3};
        final double[] z = {0, 5, 7};

        SortInPlace.ASCENDING.apply(x, y, z);

        final double[] xE = {1, 2, 3};
        final double[] yE = {2, 3, 1};
        final double[] zE = {5, 7, 0};

        // removed other assertion
        Assertions.assertArrayEquals(yE, y);
    }

    @Test
    void testJavadocExample_3_oe() {
        final double[] x = {3, 1, 2};
        final double[] y = {1, 2, 3};
        final double[] z = {0, 5, 7};

        SortInPlace.ASCENDING.apply(x, y, z);

        final double[] xE = {1, 2, 3};
        final double[] yE = {2, 3, 1};
        final double[] zE = {5, 7, 0};

        // removed other assertion
        // removed other assertion
        Assertions.assertArrayEquals(zE, z);
    }

    @Test
    void testPreconditions_1_oe() {
        final double[] nullArray = null;
        final double[] one = {1};
        final double[] two = {1, 2};
        final double[] onep = {2};

        Assertions.assertThrows(IllegalArgumentException.class, () -> SortInPlace.ASCENDING.apply(one, two));
    }

    @Test
    void testPreconditions_2_oe() {
        final double[] nullArray = null;
        final double[] one = {1};
        final double[] two = {1, 2};
        final double[] onep = {2};

        // removed other assertion
        Assertions.assertThrows(NullPointerException.class, () -> SortInPlace.ASCENDING.apply(one, nullArray));
    }

    @Test
    void testPreconditions_3_oe() {
        final double[] nullArray = null;
        final double[] one = {1};
        final double[] two = {1, 2};
        final double[] onep = {2};

        // removed other assertion
        // removed other assertion
        Assertions.assertThrows(NullPointerException.class, () -> SortInPlace.ASCENDING.apply(one, onep, nullArray));
    }

}
