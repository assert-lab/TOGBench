/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.commons.lang3;

import static org.apache.commons.lang3.JavaVersion.JAVA_0_9;
import static org.apache.commons.lang3.JavaVersion.JAVA_10;
import static org.apache.commons.lang3.JavaVersion.JAVA_11;
import static org.apache.commons.lang3.JavaVersion.JAVA_12;
import static org.apache.commons.lang3.JavaVersion.JAVA_13;
import static org.apache.commons.lang3.JavaVersion.JAVA_14;
import static org.apache.commons.lang3.JavaVersion.JAVA_15;
import static org.apache.commons.lang3.JavaVersion.JAVA_16;
import static org.apache.commons.lang3.JavaVersion.JAVA_17;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_1;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_2;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_3;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_4;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_5;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_6;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_7;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_8;
import static org.apache.commons.lang3.JavaVersion.JAVA_9;
import static org.apache.commons.lang3.JavaVersion.JAVA_RECENT;
import static org.apache.commons.lang3.JavaVersion.get;
import static org.apache.commons.lang3.JavaVersion.getJavaVersion;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.JavaVersion}.
 */
public class JavaVersionTest_OE25Dev {

    @Test
    public void testGetJavaVersion() {
        assertEquals(JAVA_0_9, get("0.9"), "0.9 failed");
        assertEquals(JAVA_1_1, get("1.1"), "1.1 failed");
        assertEquals(JAVA_1_2, get("1.2"), "1.2 failed");
        assertEquals(JAVA_1_3, get("1.3"), "1.3 failed");
        assertEquals(JAVA_1_4, get("1.4"), "1.4 failed");
        assertEquals(JAVA_1_5, get("1.5"), "1.5 failed");
        assertEquals(JAVA_1_6, get("1.6"), "1.6 failed");
        assertEquals(JAVA_1_7, get("1.7"), "1.7 failed");
        assertEquals(JAVA_1_8, get("1.8"), "1.8 failed");
        assertEquals(JAVA_9, get("9"), "9 failed");
        assertEquals(JAVA_10, get("10"), "10 failed");
        assertEquals(JAVA_11, get("11"), "11 failed");
        assertEquals(JAVA_12, get("12"), "12 failed");
        assertEquals(JAVA_13, get("13"), "13 failed");
        assertEquals(JAVA_14, get("14"), "14 failed");
        assertEquals(JAVA_15, get("15"), "15 failed");
        assertEquals(JAVA_16, get("16"), "16 failed");
        assertEquals(JAVA_17, get("17"), "17 failed");
        assertEquals(JAVA_RECENT, get("1.10"), "1.10 failed");
        // assertNull("2.10 unexpectedly worked", get("2.10"));
        assertEquals(get("1.5"), getJavaVersion("1.5"), "Wrapper method failed");
        assertEquals(JAVA_RECENT, get("18"), "Unhandled"); // LANG-1384
    }

    @Test
    public void testAtLeast() {
        assertFalse(JAVA_1_2.atLeast(JAVA_1_5), "1.2 at least 1.5 passed");
        assertTrue(JAVA_1_5.atLeast(JAVA_1_2), "1.5 at least 1.2 failed");
        assertFalse(JAVA_1_6.atLeast(JAVA_1_7), "1.6 at least 1.7 passed");

        assertTrue(JAVA_0_9.atLeast(JAVA_1_5), "0.9 at least 1.5 failed");
        assertFalse(JAVA_0_9.atLeast(JAVA_1_6), "0.9 at least 1.6 passed");
    }

    @Test
    public void testToString() {
        assertEquals("1.2", JAVA_1_2.toString());
    }

    @Test
    public void testGetJavaVersion_1_oe() {
        assertEquals(JAVA_0_9, get("0.9"), "0.9 failed");
    }

    @Test
    public void testGetJavaVersion_2_oe() {
        assertEquals(JAVA_1_1, get("1.1"), "1.1 failed");
    }

    @Test
    public void testGetJavaVersion_3_oe() {
        assertEquals(JAVA_1_2, get("1.2"), "1.2 failed");
    }

    @Test
    public void testGetJavaVersion_4_oe() {
        assertEquals(JAVA_1_3, get("1.3"), "1.3 failed");
    }

    @Test
    public void testGetJavaVersion_5_oe() {
        assertEquals(JAVA_1_4, get("1.4"), "1.4 failed");
    }

    @Test
    public void testGetJavaVersion_6_oe() {
        assertEquals(JAVA_1_5, get("1.5"), "1.5 failed");
    }

    @Test
    public void testGetJavaVersion_7_oe() {
        assertEquals(JAVA_1_6, get("1.6"), "1.6 failed");
    }

    @Test
    public void testGetJavaVersion_8_oe() {
        assertEquals(JAVA_1_7, get("1.7"), "1.7 failed");
    }

    @Test
    public void testGetJavaVersion_9_oe() {
        assertEquals(JAVA_1_8, get("1.8"), "1.8 failed");
    }

    @Test
    public void testGetJavaVersion_10_oe() {
        assertEquals(JAVA_9, get("9"), "9 failed");
    }

    @Test
    public void testGetJavaVersion_11_oe() {
        assertEquals(JAVA_10, get("10"), "10 failed");
    }

    @Test
    public void testGetJavaVersion_12_oe() {
        assertEquals(JAVA_11, get("11"), "11 failed");
    }

    @Test
    public void testGetJavaVersion_13_oe() {
        assertEquals(JAVA_12, get("12"), "12 failed");
    }

    @Test
    public void testGetJavaVersion_14_oe() {
        assertEquals(JAVA_13, get("13"), "13 failed");
    }

    @Test
    public void testGetJavaVersion_15_oe() {
        assertEquals(JAVA_14, get("14"), "14 failed");
    }

    @Test
    public void testGetJavaVersion_16_oe() {
        assertEquals(JAVA_15, get("15"), "15 failed");
    }

    @Test
    public void testGetJavaVersion_17_oe() {
        assertEquals(JAVA_16, get("16"), "16 failed");
    }

    @Test
    public void testGetJavaVersion_18_oe() {
        assertEquals(JAVA_17, get("17"), "17 failed");
    }

    @Test
    public void testGetJavaVersion_19_oe() {
        assertEquals(JAVA_RECENT, get("1.10"), "1.10 failed");
    }

    @Test
    public void testGetJavaVersion_20_oe() {
        assertEquals(get("1.5"), getJavaVersion("1.5"), "Wrapper method failed");
    }

    @Test
    public void testGetJavaVersion_21_oe() {
        assertEquals(JAVA_RECENT, get("18"), "Unhandled"); // LANG-1384;
    }

    @Test
    public void testAtLeast_1_oe() {
        assertFalse(JAVA_1_2.atLeast(JAVA_1_5), "1.2 at least 1.5 passed");
    }

    @Test
    public void testAtLeast_2_oe() {
        assertTrue(JAVA_1_5.atLeast(JAVA_1_2), "1.5 at least 1.2 failed");
    }

    @Test
    public void testAtLeast_3_oe() {
        assertFalse(JAVA_1_6.atLeast(JAVA_1_7), "1.6 at least 1.7 passed");
    }

    @Test
    public void testAtLeast_4_oe() {

        assertTrue(JAVA_0_9.atLeast(JAVA_1_5), "0.9 at least 1.5 failed");
    }

    @Test
    public void testAtLeast_5_oe() {

        assertFalse(JAVA_0_9.atLeast(JAVA_1_6), "0.9 at least 1.6 passed");
    }

    @Test
    public void testToString_1_oe() {
        assertEquals("1.2", JAVA_1_2.toString());
    }

}
