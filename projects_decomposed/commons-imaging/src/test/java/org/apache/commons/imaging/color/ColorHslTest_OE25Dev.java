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
package org.apache.commons.imaging.color;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ColorHslTest_OE25Dev {

    private ColorHsl color;
    private ColorHsl colorCopy;

    @BeforeEach
    public void setUp() {
        color = new ColorHsl(1.0, 2.0, 3.0);
        colorCopy = new ColorHsl(1.0, 2.0, 3.0);
    }

    @Test
    public void testHAssignment_1_oe() {
        assertEquals(1.0, color.H, 0.0);
    }

    @Test
    public void testSAssignment_1_oe() {
        assertEquals(2.0, color.S, 0.0);
    }

    @Test
    public void testLAssignment_1_oe() {
        assertEquals(3.0, color.L, 0.0);
    }

    @Test
    public void testToString_1_oe() {
        assertEquals("{H: 1.0, S: 2.0, L: 3.0}", color.toString());
    }

    @Test
    public void testHashCodeAndEquals_1_oe() {
        assertTrue(color.equals(colorCopy) && colorCopy.equals(color));
    }

    @Test
    public void testHashCodeAndEquals_2_oe() {
        // removed other assertion
        assertThat(color.hashCode(), is(colorCopy.hashCode()));
    }

    @Test
    public void testCreatesColorHslOne_1_oe() {
        final ColorHsl colorHsl = ColorHsl.BLUE;
        final ColorHsl colorHslTwo = new ColorHsl(100.0, 667.226, (-687.72287636));

        assertEquals(667.226, colorHslTwo.S, 0.01);
    }

    @Test
    public void testCreatesColorHslOne_2_oe() {
        final ColorHsl colorHsl = ColorHsl.BLUE;
        final ColorHsl colorHslTwo = new ColorHsl(100.0, 667.226, (-687.72287636));

        // removed other assertion
        assertEquals(100.0, colorHslTwo.H, 0.01);
    }

    @Test
    public void testCreatesColorHslOne_3_oe() {
        final ColorHsl colorHsl = ColorHsl.BLUE;
        final ColorHsl colorHslTwo = new ColorHsl(100.0, 667.226, (-687.72287636));

        // removed other assertion
        // removed other assertion
        assertEquals((-687.72287636), colorHslTwo.L, 0.01);
    }

    @Test
    public void testCreatesColorHslOne_4_oe() {
        final ColorHsl colorHsl = ColorHsl.BLUE;
        final ColorHsl colorHslTwo = new ColorHsl(100.0, 667.226, (-687.72287636));

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals(colorHsl, colorHslTwo);
    }

}
