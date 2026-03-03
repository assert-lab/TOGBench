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
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ColorHsvTest_OE25Dev {

    private ColorHsv color;
    private ColorHsv colorCopy;

    @BeforeEach
    public void setUp() {
        color = new ColorHsv(1.0, 2.0, 3.0);
        colorCopy = new ColorHsv(1.0, 2.0, 3.0);
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
    public void testVAssignment_1_oe() {
        assertEquals(3.0, color.V, 0.0);
    }

    @Test
    public void testToString_1_oe() {
        assertEquals("{H: 1.0, S: 2.0, V: 3.0}", color.toString());
    }

    @Test
    public void testHashCodeAndEquals_1_oe() {
        assertTrue(color.equals(colorCopy) && colorCopy.equals(color));
    }

    @Test
    public void testHashCodeAndEquals_2_oe() {
        assertThat(color.hashCode(), is(colorCopy.hashCode()));
    }

}
