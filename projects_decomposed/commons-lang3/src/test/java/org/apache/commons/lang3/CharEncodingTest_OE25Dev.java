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

package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

/**
 * Tests CharEncoding.
 *
 * @see CharEncoding
 */
@SuppressWarnings("deprecation")
public class CharEncodingTest_OE25Dev  {

    private void assertSupportedEncoding(final String name) {
        assertTrue(CharEncoding.isSupported(name), "Encoding should be supported: " + name);
    }

    /**
     * The class can be instantiated.
     */
    @Test
    public void testConstructor() {
        new CharEncoding();
    }

    @Test
    public void testSupported() {
        assertTrue(CharEncoding.isSupported("UTF8"));
        assertTrue(CharEncoding.isSupported("UTF-8"));
        assertTrue(CharEncoding.isSupported("ASCII"));
    }

    @Test
    public void testNotSupported() {
        assertFalse(CharEncoding.isSupported(null));
        assertFalse(CharEncoding.isSupported(""));
        assertFalse(CharEncoding.isSupported(" "));
        assertFalse(CharEncoding.isSupported("\t\r\n"));
        assertFalse(CharEncoding.isSupported("DOESNOTEXIST"));
        assertFalse(CharEncoding.isSupported("this is not a valid encoding name"));
    }

    @Test
    public void testStandardCharsetsEquality() {
        assertEquals(StandardCharsets.ISO_8859_1.name(), CharEncoding.ISO_8859_1);
        assertEquals(StandardCharsets.US_ASCII.name(), CharEncoding.US_ASCII);
        assertEquals(StandardCharsets.UTF_8.name(), CharEncoding.UTF_8);
        assertEquals(StandardCharsets.UTF_16.name(), CharEncoding.UTF_16);
        assertEquals(StandardCharsets.UTF_16BE.name(), CharEncoding.UTF_16BE);
        assertEquals(StandardCharsets.UTF_16LE.name(), CharEncoding.UTF_16LE);
    }

@Test
    public void testMustBeSupportedJava1_3_1_and_above_1_oe_1() {
        final String name = CharEncoding.ISO_8859_1;
        assertTrue(CharEncoding.isSupported(name), "Encoding should be supported: " + name);
    }

@Test
    public void testMustBeSupportedJava1_3_1_and_above_2_oe_1() {
        // removed other assertion
        final String name = CharEncoding.US_ASCII;
        assertTrue(CharEncoding.isSupported(name), "Encoding should be supported: " + name);
    }

@Test
    public void testMustBeSupportedJava1_3_1_and_above_3_oe_1() {
        // removed other assertion
        // removed other assertion
        final String name = CharEncoding.UTF_16;
        assertTrue(CharEncoding.isSupported(name), "Encoding should be supported: " + name);
    }

@Test
    public void testMustBeSupportedJava1_3_1_and_above_4_oe_1() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final String name = CharEncoding.UTF_16BE;
        assertTrue(CharEncoding.isSupported(name), "Encoding should be supported: " + name);
    }

@Test
    public void testMustBeSupportedJava1_3_1_and_above_5_oe_1() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final String name = CharEncoding.UTF_16LE;
        assertTrue(CharEncoding.isSupported(name), "Encoding should be supported: " + name);
    }

@Test
    public void testMustBeSupportedJava1_3_1_and_above_6_oe_1() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final String name = CharEncoding.UTF_8;
        assertTrue(CharEncoding.isSupported(name), "Encoding should be supported: " + name);
    }

}
