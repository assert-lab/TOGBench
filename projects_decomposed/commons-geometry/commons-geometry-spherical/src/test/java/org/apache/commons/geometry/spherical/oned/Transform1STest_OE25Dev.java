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
package org.apache.commons.geometry.spherical.oned;

import org.apache.commons.geometry.core.GeometryTestUtils;
import org.apache.commons.geometry.spherical.SphericalTestUtils;
import org.apache.commons.numbers.angle.Angle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Transform1STest_OE25Dev {

    private static final double TEST_EPS = 1e-10;

    private static final Point1S ZERO = Point1S.ZERO;

    private static final Point1S HALF_PI = Point1S.of(Angle.PI_OVER_TWO);

    private static final Point1S PI = Point1S.of(Math.PI);

    private static final Point1S MINUS_HALF_PI = Point1S.of(-Angle.PI_OVER_TWO);

    @Test
    void testToString() {
        // arrange
        final Transform1S t = Transform1S.identity().negate().rotate(1);

        // act
        final String str = t.toString();

        // assert
        GeometryTestUtils.assertContains("Transform1S", str);
        GeometryTestUtils.assertContains("negate= true", str);
        GeometryTestUtils.assertContains("rotate= 1", str);
    }

    private static void checkInverse(final Transform1S t) {
        final Transform1S inv = t.inverse();

        for (double x = -Angle.TWO_PI; x <= 2 * Angle.TWO_PI; x += 0.2) {
            final Point1S pt = Point1S.of(x);

            SphericalTestUtils.assertPointsEqual(pt, inv.apply(t.apply(pt)), TEST_EPS);
            SphericalTestUtils.assertPointsEqual(pt, t.apply(inv.apply(pt)), TEST_EPS);
        }
    }


}
