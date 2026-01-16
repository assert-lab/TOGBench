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
package org.apache.commons.lang3.text;

import static java.util.FormattableFlags.LEFT_JUSTIFY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Formatter;

import org.junit.jupiter.api.Test;

/**
 * Unit tests {@link FormattableUtils}.
 */
@Deprecated
public class FormattableUtilsTest_OE25Dev {

    @Test
    public void testDefaultAppend_1_oe() {
        assertEquals("foo", FormattableUtils.append("foo", new Formatter(), 0, -1, -1).toString());
    }

    @Test
    public void testDefaultAppend_2_oe() {
        // removed other assertion
        assertEquals("fo", FormattableUtils.append("foo", new Formatter(), 0, -1, 2).toString());
    }

    @Test
    public void testDefaultAppend_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(" foo", FormattableUtils.append("foo", new Formatter(), 0, 4, -1).toString());
    }

    @Test
    public void testDefaultAppend_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("   foo", FormattableUtils.append("foo", new Formatter(), 0, 6, -1).toString());
    }

    @Test
    public void testDefaultAppend_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(" fo", FormattableUtils.append("foo", new Formatter(), 0, 3, 2).toString());
    }

    @Test
    public void testDefaultAppend_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("   fo", FormattableUtils.append("foo", new Formatter(), 0, 5, 2).toString());
    }

    @Test
    public void testDefaultAppend_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("foo ", FormattableUtils.append("foo", new Formatter(), LEFT_JUSTIFY, 4, -1).toString());
    }

    @Test
    public void testDefaultAppend_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("foo   ", FormattableUtils.append("foo", new Formatter(), LEFT_JUSTIFY, 6, -1).toString());
    }

    @Test
    public void testDefaultAppend_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("fo ", FormattableUtils.append("foo", new Formatter(), LEFT_JUSTIFY, 3, 2).toString());
    }

    @Test
    public void testDefaultAppend_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("fo   ", FormattableUtils.append("foo", new Formatter(), LEFT_JUSTIFY, 5, 2).toString());
    }

    @Test
    public void testAlternatePadCharacter_1_oe() {
        final char pad='_';
        assertEquals("foo", FormattableUtils.append("foo", new Formatter(), 0, -1, -1, pad).toString());
    }

    @Test
    public void testAlternatePadCharacter_2_oe() {
        final char pad='_';
        // removed other assertion
        assertEquals("fo", FormattableUtils.append("foo", new Formatter(), 0, -1, 2, pad).toString());
    }

    @Test
    public void testAlternatePadCharacter_3_oe() {
        final char pad='_';
        // removed other assertion
        // removed other assertion
        assertEquals("_foo", FormattableUtils.append("foo", new Formatter(), 0, 4, -1, pad).toString());
    }

    @Test
    public void testAlternatePadCharacter_4_oe() {
        final char pad='_';
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("___foo", FormattableUtils.append("foo", new Formatter(), 0, 6, -1, pad).toString());
    }

    @Test
    public void testAlternatePadCharacter_5_oe() {
        final char pad='_';
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("_fo", FormattableUtils.append("foo", new Formatter(), 0, 3, 2, pad).toString());
    }

    @Test
    public void testAlternatePadCharacter_6_oe() {
        final char pad='_';
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("___fo", FormattableUtils.append("foo", new Formatter(), 0, 5, 2, pad).toString());
    }

    @Test
    public void testAlternatePadCharacter_7_oe() {
        final char pad='_';
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("foo_", FormattableUtils.append("foo", new Formatter(), LEFT_JUSTIFY, 4, -1, pad).toString());
    }

    @Test
    public void testAlternatePadCharacter_8_oe() {
        final char pad='_';
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("foo___", FormattableUtils.append("foo", new Formatter(), LEFT_JUSTIFY, 6, -1, pad).toString());
    }

    @Test
    public void testAlternatePadCharacter_9_oe() {
        final char pad='_';
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("fo_", FormattableUtils.append("foo", new Formatter(), LEFT_JUSTIFY, 3, 2, pad).toString());
    }

    @Test
    public void testAlternatePadCharacter_10_oe() {
        final char pad='_';
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("fo___", FormattableUtils.append("foo", new Formatter(), LEFT_JUSTIFY, 5, 2, pad).toString());
    }

    @Test
    public void testEllipsis_1_oe() {
        assertEquals("foo", FormattableUtils.append("foo", new Formatter(), 0, -1, -1, "*").toString());
    }

    @Test
    public void testEllipsis_2_oe() {
        // removed other assertion
        assertEquals("f*", FormattableUtils.append("foo", new Formatter(), 0, -1, 2, "*").toString());
    }

    @Test
    public void testEllipsis_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(" foo", FormattableUtils.append("foo", new Formatter(), 0, 4, -1, "*").toString());
    }

    @Test
    public void testEllipsis_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("   foo", FormattableUtils.append("foo", new Formatter(), 0, 6, -1, "*").toString());
    }

    @Test
    public void testEllipsis_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(" f*", FormattableUtils.append("foo", new Formatter(), 0, 3, 2, "*").toString());
    }

    @Test
    public void testEllipsis_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("   f*", FormattableUtils.append("foo", new Formatter(), 0, 5, 2, "*").toString());
    }

    @Test
    public void testEllipsis_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("foo ", FormattableUtils.append("foo", new Formatter(), LEFT_JUSTIFY, 4, -1, "*").toString());
    }

    @Test
    public void testEllipsis_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("foo   ", FormattableUtils.append("foo", new Formatter(), LEFT_JUSTIFY, 6, -1, "*").toString());
    }

    @Test
    public void testEllipsis_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("f* ", FormattableUtils.append("foo", new Formatter(), LEFT_JUSTIFY, 3, 2, "*").toString());
    }

    @Test
    public void testEllipsis_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("f*   ", FormattableUtils.append("foo", new Formatter(), LEFT_JUSTIFY, 5, 2, "*").toString());
    }

    @Test
    public void testEllipsis_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("foo", FormattableUtils.append("foo", new Formatter(), 0, -1, -1, "+*").toString());
    }

    @Test
    public void testEllipsis_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("+*", FormattableUtils.append("foo", new Formatter(), 0, -1, 2, "+*").toString());
    }

    @Test
    public void testEllipsis_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(" foo", FormattableUtils.append("foo", new Formatter(), 0, 4, -1, "+*").toString());
    }

    @Test
    public void testEllipsis_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("   foo", FormattableUtils.append("foo", new Formatter(), 0, 6, -1, "+*").toString());
    }

    @Test
    public void testEllipsis_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(" +*", FormattableUtils.append("foo", new Formatter(), 0, 3, 2, "+*").toString());
    }

    @Test
    public void testEllipsis_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("   +*", FormattableUtils.append("foo", new Formatter(), 0, 5, 2, "+*").toString());
    }

    @Test
    public void testEllipsis_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("foo ", FormattableUtils.append("foo", new Formatter(), LEFT_JUSTIFY, 4, -1, "+*").toString());
    }

    @Test
    public void testEllipsis_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("foo   ", FormattableUtils.append("foo", new Formatter(), LEFT_JUSTIFY, 6, -1, "+*").toString());
    }

    @Test
    public void testEllipsis_19_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("+* ", FormattableUtils.append("foo", new Formatter(), LEFT_JUSTIFY, 3, 2, "+*").toString());
    }

    @Test
    public void testEllipsis_20_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("+*   ", FormattableUtils.append("foo", new Formatter(), LEFT_JUSTIFY, 5, 2, "+*").toString());
    }

    @Test
    public void testIllegalEllipsis_1_oe() {
        assertThrows(IllegalArgumentException.class, () -> FormattableUtils.append("foo", new Formatter(), 0, -1, 1, "xx"));
    }

    @Test
    public void testAlternatePadCharAndEllipsis_1_oe() {
        assertEquals("foo", FormattableUtils.append("foo", new Formatter(), 0, -1, -1, '_', "*").toString());
    }

    @Test
    public void testAlternatePadCharAndEllipsis_2_oe() {
        // removed other assertion
        assertEquals("f*", FormattableUtils.append("foo", new Formatter(), 0, -1, 2, '_', "*").toString());
    }

    @Test
    public void testAlternatePadCharAndEllipsis_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("_foo", FormattableUtils.append("foo", new Formatter(), 0, 4, -1, '_', "*").toString());
    }

    @Test
    public void testAlternatePadCharAndEllipsis_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("___foo", FormattableUtils.append("foo", new Formatter(), 0, 6, -1, '_', "*").toString());
    }

    @Test
    public void testAlternatePadCharAndEllipsis_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("_f*", FormattableUtils.append("foo", new Formatter(), 0, 3, 2, '_', "*").toString());
    }

    @Test
    public void testAlternatePadCharAndEllipsis_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("___f*", FormattableUtils.append("foo", new Formatter(), 0, 5, 2, '_', "*").toString());
    }

    @Test
    public void testAlternatePadCharAndEllipsis_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("foo_", FormattableUtils.append("foo", new Formatter(), LEFT_JUSTIFY, 4, -1, '_', "*").toString());
    }

    @Test
    public void testAlternatePadCharAndEllipsis_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("foo___", FormattableUtils.append("foo", new Formatter(), LEFT_JUSTIFY, 6, -1, '_', "*").toString());
    }

    @Test
    public void testAlternatePadCharAndEllipsis_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("f*_", FormattableUtils.append("foo", new Formatter(), LEFT_JUSTIFY, 3, 2, '_', "*").toString());
    }

    @Test
    public void testAlternatePadCharAndEllipsis_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("f*___", FormattableUtils.append("foo", new Formatter(), LEFT_JUSTIFY, 5, 2, '_', "*").toString());
    }

    @Test
    public void testAlternatePadCharAndEllipsis_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("foo", FormattableUtils.append("foo", new Formatter(), 0, -1, -1, '_', "+*").toString());
    }

    @Test
    public void testAlternatePadCharAndEllipsis_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("+*", FormattableUtils.append("foo", new Formatter(), 0, -1, 2, '_', "+*").toString());
    }

    @Test
    public void testAlternatePadCharAndEllipsis_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("_foo", FormattableUtils.append("foo", new Formatter(), 0, 4, -1, '_', "+*").toString());
    }

    @Test
    public void testAlternatePadCharAndEllipsis_14_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("___foo", FormattableUtils.append("foo", new Formatter(), 0, 6, -1, '_', "+*").toString());
    }

    @Test
    public void testAlternatePadCharAndEllipsis_15_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("_+*", FormattableUtils.append("foo", new Formatter(), 0, 3, 2, '_', "+*").toString());
    }

    @Test
    public void testAlternatePadCharAndEllipsis_16_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("___+*", FormattableUtils.append("foo", new Formatter(), 0, 5, 2, '_', "+*").toString());
    }

    @Test
    public void testAlternatePadCharAndEllipsis_17_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("foo_", FormattableUtils.append("foo", new Formatter(), LEFT_JUSTIFY, 4, -1, '_', "+*").toString());
    }

    @Test
    public void testAlternatePadCharAndEllipsis_18_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("foo___", FormattableUtils.append("foo", new Formatter(), LEFT_JUSTIFY, 6, -1, '_', "+*").toString());
    }

    @Test
    public void testAlternatePadCharAndEllipsis_19_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("+*_", FormattableUtils.append("foo", new Formatter(), LEFT_JUSTIFY, 3, 2, '_', "+*").toString());
    }

    @Test
    public void testAlternatePadCharAndEllipsis_20_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("+*___", FormattableUtils.append("foo", new Formatter(), LEFT_JUSTIFY, 5, 2, '_', "+*").toString());
    }

}
