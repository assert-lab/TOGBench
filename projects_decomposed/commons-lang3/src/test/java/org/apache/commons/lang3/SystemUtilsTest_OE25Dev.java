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

import static org.apache.commons.lang3.JavaVersion.JAVA_10;
import static org.apache.commons.lang3.JavaVersion.JAVA_11;
import static org.apache.commons.lang3.JavaVersion.JAVA_12;
import static org.apache.commons.lang3.JavaVersion.JAVA_13;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_1;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_2;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_3;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_4;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_5;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_6;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_7;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_8;
import static org.apache.commons.lang3.JavaVersion.JAVA_9;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Locale;

import org.junit.jupiter.api.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.SystemUtils}.
 *
 * Only limited testing can be performed.
 */
public class SystemUtilsTest_OE25Dev {

    /**
     * Assumes no security manager exists.
     */

    /**
     * Assumes no security manager exists.
     */

    /**
     * Assumes no security manager exists.
     */

    /**
     * Assumes no security manager exists.
     */

    /**
     * Assumes no security manager exists.
     */

    @Test
    public void testConstructor_1_oe() {
        assertNotNull(new SystemUtils());
    }

    @Test
    public void testConstructor_2_oe() {
        final Constructor<?>[] cons = SystemUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
    }

    @Test
    public void testConstructor_3_oe() {
        final Constructor<?>[] cons = SystemUtils.class.getDeclaredConstructors();
        assertTrue(Modifier.isPublic(cons[0].getModifiers()));
    }

    @Test
    public void testConstructor_4_oe() {
        final Constructor<?>[] cons = SystemUtils.class.getDeclaredConstructors();
        assertTrue(Modifier.isPublic(SystemUtils.class.getModifiers()));
    }

    @Test
    public void testConstructor_5_oe() {
        final Constructor<?>[] cons = SystemUtils.class.getDeclaredConstructors();
        assertFalse(Modifier.isFinal(SystemUtils.class.getModifiers()));
    }

    @Test
    public void testGetEnvironmentVariableAbsent_1_oe() {
        final String name = "THIS_ENV_VAR_SHOULD_NOT_EXIST_FOR_THIS_TEST_TO_PASS";
        final String expected = System.getenv(name);
        assertNull(expected);
    }

    @Test
    public void testGetEnvironmentVariableAbsent_2_oe() {
        final String name = "THIS_ENV_VAR_SHOULD_NOT_EXIST_FOR_THIS_TEST_TO_PASS";
        final String expected = System.getenv(name);
        final String value = SystemUtils.getEnvironmentVariable(name, "DEFAULT");
        assertEquals("DEFAULT", value);
    }

    @Test
    public void testGetEnvironmentVariablePresent_1_oe() {
        final String name = "PATH";
        final String expected = System.getenv(name);
        final String value = SystemUtils.getEnvironmentVariable(name, null);
        assertEquals(expected, value);
    }

    @Test
    public void testGetHostName_1_oe() {
        final String hostName = SystemUtils.getHostName();
        final String expected = SystemUtils.IS_OS_WINDOWS ? System.getenv("COMPUTERNAME") : System.getenv("HOSTNAME");
        assertEquals(expected, hostName);
    }

    @Test
    public void testGetJavaHome_1_oe() {
        final File dir = SystemUtils.getJavaHome();
        assertNotNull(dir);
    }

    @Test
    public void testGetJavaHome_2_oe() {
        final File dir = SystemUtils.getJavaHome();
        assertTrue(dir.exists());
    }

    @Test
    public void testGetJavaIoTmpDir_1_oe() {
        final File dir = SystemUtils.getJavaIoTmpDir();
        assertNotNull(dir);
    }

    @Test
    public void testGetJavaIoTmpDir_2_oe() {
        final File dir = SystemUtils.getJavaIoTmpDir();
        assertTrue(dir.exists());
    }

    @Test
    public void testGetUserDir_1_oe() {
        final File dir = SystemUtils.getUserDir();
        assertNotNull(dir);
    }

    @Test
    public void testGetUserDir_2_oe() {
        final File dir = SystemUtils.getUserDir();
        assertTrue(dir.exists());
    }

    @Test
    public void testGetUserHome_1_oe() {
        final File dir = SystemUtils.getUserHome();
        assertNotNull(dir);
    }

    @Test
    public void testGetUserHome_2_oe() {
        final File dir = SystemUtils.getUserHome();
        assertTrue(dir.exists());
    }

    @Test
    public void testGetUserName_1_oe() {
        assertEquals(System.getProperty("user.name"), SystemUtils.getUserName());
    }

    @Test
    public void testGetUserName_2_oe() {
        assertEquals(System.getProperty("user.name", "foo"), SystemUtils.getUserName("foo"));
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_1_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
            assertFalse(SystemUtils.IS_JAVA_1_1);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_2_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
            assertFalse(SystemUtils.IS_JAVA_1_2);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_3_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
            assertFalse(SystemUtils.IS_JAVA_1_3);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_4_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
            assertFalse(SystemUtils.IS_JAVA_1_4);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_5_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
            assertFalse(SystemUtils.IS_JAVA_1_5);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_6_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
            assertFalse(SystemUtils.IS_JAVA_1_6);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_7_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
            assertFalse(SystemUtils.IS_JAVA_1_7);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_8_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
            assertFalse(SystemUtils.IS_JAVA_1_8);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_9_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
            assertFalse(SystemUtils.IS_JAVA_1_9);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_10_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
            assertFalse(SystemUtils.IS_JAVA_9);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_11_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
            assertFalse(SystemUtils.IS_JAVA_10);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_12_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
            assertFalse(SystemUtils.IS_JAVA_11);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_13_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
            assertFalse(SystemUtils.IS_JAVA_12);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_14_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
            assertFalse(SystemUtils.IS_JAVA_13);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_15_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
            assertFalse(SystemUtils.IS_JAVA_14);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_16_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
            assertFalse(SystemUtils.IS_JAVA_15);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_17_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
            assertFalse(SystemUtils.IS_JAVA_1_1);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_18_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
            assertFalse(SystemUtils.IS_JAVA_1_2);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_19_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
            assertFalse(SystemUtils.IS_JAVA_1_3);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_20_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
            assertFalse(SystemUtils.IS_JAVA_1_4);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_21_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
            assertFalse(SystemUtils.IS_JAVA_1_5);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_22_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
            assertFalse(SystemUtils.IS_JAVA_1_6);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_23_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
            assertFalse(SystemUtils.IS_JAVA_1_7);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_24_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
            assertTrue(SystemUtils.IS_JAVA_1_8);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_25_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
            assertFalse(SystemUtils.IS_JAVA_1_9);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_26_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
            assertFalse(SystemUtils.IS_JAVA_9);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_27_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
            assertFalse(SystemUtils.IS_JAVA_10);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_28_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
            assertFalse(SystemUtils.IS_JAVA_11);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_29_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
            assertFalse(SystemUtils.IS_JAVA_12);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_30_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
            assertFalse(SystemUtils.IS_JAVA_13);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_31_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
            assertFalse(SystemUtils.IS_JAVA_14);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_32_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
            assertFalse(SystemUtils.IS_JAVA_15);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_33_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
            assertFalse(SystemUtils.IS_JAVA_1_1);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_34_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
            assertFalse(SystemUtils.IS_JAVA_1_2);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_35_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
            assertFalse(SystemUtils.IS_JAVA_1_3);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_36_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
            assertFalse(SystemUtils.IS_JAVA_1_4);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_37_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
            assertFalse(SystemUtils.IS_JAVA_1_5);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_38_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
            assertFalse(SystemUtils.IS_JAVA_1_6);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_39_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
            assertFalse(SystemUtils.IS_JAVA_1_7);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_40_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
            assertFalse(SystemUtils.IS_JAVA_1_8);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_41_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
            assertTrue(SystemUtils.IS_JAVA_1_9);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_42_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
            assertTrue(SystemUtils.IS_JAVA_9);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_43_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
            assertFalse(SystemUtils.IS_JAVA_10);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_44_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
            assertFalse(SystemUtils.IS_JAVA_11);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_45_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
            assertFalse(SystemUtils.IS_JAVA_12);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_46_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
            assertFalse(SystemUtils.IS_JAVA_13);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_47_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
            assertFalse(SystemUtils.IS_JAVA_14);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_48_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
            assertFalse(SystemUtils.IS_JAVA_15);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_49_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
            assertFalse(SystemUtils.IS_JAVA_1_1);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_50_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
            assertFalse(SystemUtils.IS_JAVA_1_2);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_51_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
            assertFalse(SystemUtils.IS_JAVA_1_3);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_52_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
            assertFalse(SystemUtils.IS_JAVA_1_4);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_53_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
            assertFalse(SystemUtils.IS_JAVA_1_5);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_54_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
            assertFalse(SystemUtils.IS_JAVA_1_6);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_55_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
            assertFalse(SystemUtils.IS_JAVA_1_7);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_56_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
            assertFalse(SystemUtils.IS_JAVA_1_8);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_57_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
            assertFalse(SystemUtils.IS_JAVA_1_9);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_58_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
            assertFalse(SystemUtils.IS_JAVA_9);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_59_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
            assertTrue(SystemUtils.IS_JAVA_10);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_60_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
            assertFalse(SystemUtils.IS_JAVA_11);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_61_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
            assertFalse(SystemUtils.IS_JAVA_12);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_62_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
            assertFalse(SystemUtils.IS_JAVA_13);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_63_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
            assertFalse(SystemUtils.IS_JAVA_14);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_64_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
            assertFalse(SystemUtils.IS_JAVA_15);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_65_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
            assertFalse(SystemUtils.IS_JAVA_1_1);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_66_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
            assertFalse(SystemUtils.IS_JAVA_1_2);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_67_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
            assertFalse(SystemUtils.IS_JAVA_1_3);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_68_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
            assertFalse(SystemUtils.IS_JAVA_1_4);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_69_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
            assertFalse(SystemUtils.IS_JAVA_1_5);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_70_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
            assertFalse(SystemUtils.IS_JAVA_1_6);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_71_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
            assertFalse(SystemUtils.IS_JAVA_1_7);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_72_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
            assertFalse(SystemUtils.IS_JAVA_1_8);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_73_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
            assertFalse(SystemUtils.IS_JAVA_1_9);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_74_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
            assertFalse(SystemUtils.IS_JAVA_9);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_75_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
            assertFalse(SystemUtils.IS_JAVA_10);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_76_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
            assertTrue(SystemUtils.IS_JAVA_11);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_77_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
            assertFalse(SystemUtils.IS_JAVA_12);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_78_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
            assertFalse(SystemUtils.IS_JAVA_13);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_79_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
            assertFalse(SystemUtils.IS_JAVA_14);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_80_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
            assertFalse(SystemUtils.IS_JAVA_15);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_81_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
            assertFalse(SystemUtils.IS_JAVA_1_1);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_82_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
            assertFalse(SystemUtils.IS_JAVA_1_2);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_83_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
            assertFalse(SystemUtils.IS_JAVA_1_3);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_84_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
            assertFalse(SystemUtils.IS_JAVA_1_4);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_85_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
            assertFalse(SystemUtils.IS_JAVA_1_5);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_86_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
            assertFalse(SystemUtils.IS_JAVA_1_6);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_87_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
            assertFalse(SystemUtils.IS_JAVA_1_7);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_88_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
            assertFalse(SystemUtils.IS_JAVA_1_8);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_89_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
            assertFalse(SystemUtils.IS_JAVA_1_9);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_90_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
            assertFalse(SystemUtils.IS_JAVA_9);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_91_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
            assertFalse(SystemUtils.IS_JAVA_10);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_92_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
            assertFalse(SystemUtils.IS_JAVA_11);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_93_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
            assertTrue(SystemUtils.IS_JAVA_12);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_94_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
            assertFalse(SystemUtils.IS_JAVA_13);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_95_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
            assertFalse(SystemUtils.IS_JAVA_1_1);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_96_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
            assertFalse(SystemUtils.IS_JAVA_1_2);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_97_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
            assertFalse(SystemUtils.IS_JAVA_1_3);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_98_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
            assertFalse(SystemUtils.IS_JAVA_1_4);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_99_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
            assertFalse(SystemUtils.IS_JAVA_1_5);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_100_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
            assertFalse(SystemUtils.IS_JAVA_1_6);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_101_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
            assertFalse(SystemUtils.IS_JAVA_1_7);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_102_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
            assertFalse(SystemUtils.IS_JAVA_1_8);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_103_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
            assertFalse(SystemUtils.IS_JAVA_1_9);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_104_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
            assertFalse(SystemUtils.IS_JAVA_9);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_105_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
            assertFalse(SystemUtils.IS_JAVA_10);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_106_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
            assertFalse(SystemUtils.IS_JAVA_11);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_107_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
            assertFalse(SystemUtils.IS_JAVA_12);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_108_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
            assertTrue(SystemUtils.IS_JAVA_13);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_109_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
            assertFalse(SystemUtils.IS_JAVA_14);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_110_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
            assertFalse(SystemUtils.IS_JAVA_15);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_111_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
            assertFalse(SystemUtils.IS_JAVA_1_1);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_112_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
            assertFalse(SystemUtils.IS_JAVA_1_2);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_113_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
            assertFalse(SystemUtils.IS_JAVA_1_3);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_114_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
            assertFalse(SystemUtils.IS_JAVA_1_4);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_115_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
            assertFalse(SystemUtils.IS_JAVA_1_5);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_116_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
            assertFalse(SystemUtils.IS_JAVA_1_6);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_117_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
            assertFalse(SystemUtils.IS_JAVA_1_7);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_118_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
            assertFalse(SystemUtils.IS_JAVA_1_8);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_119_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
            assertFalse(SystemUtils.IS_JAVA_1_9);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_120_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
            assertFalse(SystemUtils.IS_JAVA_9);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_121_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
            assertFalse(SystemUtils.IS_JAVA_10);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_122_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
            assertFalse(SystemUtils.IS_JAVA_11);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_123_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
            assertFalse(SystemUtils.IS_JAVA_12);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_124_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
            assertFalse(SystemUtils.IS_JAVA_13);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_125_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
            assertTrue(SystemUtils.IS_JAVA_14);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_126_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
            assertFalse(SystemUtils.IS_JAVA_15);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_127_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
        } else if (javaVersion.startsWith("15")) {
            assertFalse(SystemUtils.IS_JAVA_1_1);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_128_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
        } else if (javaVersion.startsWith("15")) {
            assertFalse(SystemUtils.IS_JAVA_1_2);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_129_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
        } else if (javaVersion.startsWith("15")) {
            assertFalse(SystemUtils.IS_JAVA_1_3);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_130_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
        } else if (javaVersion.startsWith("15")) {
            assertFalse(SystemUtils.IS_JAVA_1_4);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_131_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
        } else if (javaVersion.startsWith("15")) {
            assertFalse(SystemUtils.IS_JAVA_1_5);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_132_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
        } else if (javaVersion.startsWith("15")) {
            assertFalse(SystemUtils.IS_JAVA_1_6);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_133_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
        } else if (javaVersion.startsWith("15")) {
            assertFalse(SystemUtils.IS_JAVA_1_7);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_134_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
        } else if (javaVersion.startsWith("15")) {
            assertFalse(SystemUtils.IS_JAVA_1_8);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_135_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
        } else if (javaVersion.startsWith("15")) {
            assertFalse(SystemUtils.IS_JAVA_1_9);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_136_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
        } else if (javaVersion.startsWith("15")) {
            assertFalse(SystemUtils.IS_JAVA_9);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_137_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
        } else if (javaVersion.startsWith("15")) {
            assertFalse(SystemUtils.IS_JAVA_10);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_138_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
        } else if (javaVersion.startsWith("15")) {
            assertFalse(SystemUtils.IS_JAVA_11);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_139_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
        } else if (javaVersion.startsWith("15")) {
            assertFalse(SystemUtils.IS_JAVA_12);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_140_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
        } else if (javaVersion.startsWith("15")) {
            assertFalse(SystemUtils.IS_JAVA_13);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_141_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
        } else if (javaVersion.startsWith("15")) {
            assertFalse(SystemUtils.IS_JAVA_14);
    }
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA_142_oe() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
        } else if (javaVersion.startsWith("1.8")) {
        } else if (javaVersion.startsWith("9")) {
        } else if (javaVersion.startsWith("10")) {
        } else if (javaVersion.startsWith("11")) {
        } else if (javaVersion.startsWith("12")) {
        } else if (javaVersion.startsWith("13")) {
        } else if (javaVersion.startsWith("14")) {
        } else if (javaVersion.startsWith("15")) {
            assertTrue(SystemUtils.IS_JAVA_15);
    }
    }

    @Test
    public void testIS_OS_1_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
            assertFalse(SystemUtils.IS_OS_WINDOWS);
    }
    }

    @Test
    public void testIS_OS_2_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
            assertFalse(SystemUtils.IS_OS_UNIX);
    }
    }

    @Test
    public void testIS_OS_3_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
            assertFalse(SystemUtils.IS_OS_SOLARIS);
    }
    }

    @Test
    public void testIS_OS_4_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
            assertFalse(SystemUtils.IS_OS_LINUX);
    }
    }

    @Test
    public void testIS_OS_5_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
            assertFalse(SystemUtils.IS_OS_MAC_OSX);
    }
    }

    @Test
    public void testIS_OS_6_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
        } else if (osName.startsWith("Windows")) {
            assertFalse(SystemUtils.IS_OS_UNIX);
    }
    }

    @Test
    public void testIS_OS_7_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
        } else if (osName.startsWith("Windows")) {
            assertTrue(SystemUtils.IS_OS_WINDOWS);
    }
    }

    @Test
    public void testIS_OS_8_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
        } else if (osName.startsWith("Windows")) {
        } else if (osName.startsWith("Solaris")) {
            assertTrue(SystemUtils.IS_OS_SOLARIS);
    }
    }

    @Test
    public void testIS_OS_9_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
        } else if (osName.startsWith("Windows")) {
        } else if (osName.startsWith("Solaris")) {
            assertTrue(SystemUtils.IS_OS_UNIX);
    }
    }

    @Test
    public void testIS_OS_10_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
        } else if (osName.startsWith("Windows")) {
        } else if (osName.startsWith("Solaris")) {
            assertFalse(SystemUtils.IS_OS_WINDOWS);
    }
    }

    @Test
    public void testIS_OS_11_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
        } else if (osName.startsWith("Windows")) {
        } else if (osName.startsWith("Solaris")) {
        } else if (osName.toLowerCase(Locale.ENGLISH).startsWith("linux")) {
            assertTrue(SystemUtils.IS_OS_LINUX);
    }
    }

    @Test
    public void testIS_OS_12_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
        } else if (osName.startsWith("Windows")) {
        } else if (osName.startsWith("Solaris")) {
        } else if (osName.toLowerCase(Locale.ENGLISH).startsWith("linux")) {
            assertTrue(SystemUtils.IS_OS_UNIX);
    }
    }

    @Test
    public void testIS_OS_13_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
        } else if (osName.startsWith("Windows")) {
        } else if (osName.startsWith("Solaris")) {
        } else if (osName.toLowerCase(Locale.ENGLISH).startsWith("linux")) {
            assertFalse(SystemUtils.IS_OS_WINDOWS);
    }
    }

    @Test
    public void testIS_OS_14_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
        } else if (osName.startsWith("Windows")) {
        } else if (osName.startsWith("Solaris")) {
        } else if (osName.toLowerCase(Locale.ENGLISH).startsWith("linux")) {
        } else if (osName.startsWith("Mac OS X")) {
            assertTrue(SystemUtils.IS_OS_MAC_OSX);
    }
    }

    @Test
    public void testIS_OS_15_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
        } else if (osName.startsWith("Windows")) {
        } else if (osName.startsWith("Solaris")) {
        } else if (osName.toLowerCase(Locale.ENGLISH).startsWith("linux")) {
        } else if (osName.startsWith("Mac OS X")) {
            assertTrue(SystemUtils.IS_OS_UNIX);
    }
    }

    @Test
    public void testIS_OS_16_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
        } else if (osName.startsWith("Windows")) {
        } else if (osName.startsWith("Solaris")) {
        } else if (osName.toLowerCase(Locale.ENGLISH).startsWith("linux")) {
        } else if (osName.startsWith("Mac OS X")) {
            assertFalse(SystemUtils.IS_OS_WINDOWS);
    }
    }

    @Test
    public void testIS_OS_17_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
        } else if (osName.startsWith("Windows")) {
        } else if (osName.startsWith("Solaris")) {
        } else if (osName.toLowerCase(Locale.ENGLISH).startsWith("linux")) {
        } else if (osName.startsWith("Mac OS X")) {
        } else if (osName.startsWith("OS/2")) {
            assertTrue(SystemUtils.IS_OS_OS2);
    }
    }

    @Test
    public void testIS_OS_18_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
        } else if (osName.startsWith("Windows")) {
        } else if (osName.startsWith("Solaris")) {
        } else if (osName.toLowerCase(Locale.ENGLISH).startsWith("linux")) {
        } else if (osName.startsWith("Mac OS X")) {
        } else if (osName.startsWith("OS/2")) {
            assertFalse(SystemUtils.IS_OS_UNIX);
    }
    }

    @Test
    public void testIS_OS_19_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
        } else if (osName.startsWith("Windows")) {
        } else if (osName.startsWith("Solaris")) {
        } else if (osName.toLowerCase(Locale.ENGLISH).startsWith("linux")) {
        } else if (osName.startsWith("Mac OS X")) {
        } else if (osName.startsWith("OS/2")) {
            assertFalse(SystemUtils.IS_OS_WINDOWS);
    }
    }

    @Test
    public void testIS_OS_20_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
        } else if (osName.startsWith("Windows")) {
        } else if (osName.startsWith("Solaris")) {
        } else if (osName.toLowerCase(Locale.ENGLISH).startsWith("linux")) {
        } else if (osName.startsWith("Mac OS X")) {
        } else if (osName.startsWith("OS/2")) {
        } else if (osName.startsWith("SunOS")) {
            assertTrue(SystemUtils.IS_OS_SUN_OS);
    }
    }

    @Test
    public void testIS_OS_21_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
        } else if (osName.startsWith("Windows")) {
        } else if (osName.startsWith("Solaris")) {
        } else if (osName.toLowerCase(Locale.ENGLISH).startsWith("linux")) {
        } else if (osName.startsWith("Mac OS X")) {
        } else if (osName.startsWith("OS/2")) {
        } else if (osName.startsWith("SunOS")) {
            assertTrue(SystemUtils.IS_OS_UNIX);
    }
    }

    @Test
    public void testIS_OS_22_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
        } else if (osName.startsWith("Windows")) {
        } else if (osName.startsWith("Solaris")) {
        } else if (osName.toLowerCase(Locale.ENGLISH).startsWith("linux")) {
        } else if (osName.startsWith("Mac OS X")) {
        } else if (osName.startsWith("OS/2")) {
        } else if (osName.startsWith("SunOS")) {
            assertFalse(SystemUtils.IS_OS_WINDOWS);
    }
    }

    @Test
    public void testIS_OS_23_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
        } else if (osName.startsWith("Windows")) {
        } else if (osName.startsWith("Solaris")) {
        } else if (osName.toLowerCase(Locale.ENGLISH).startsWith("linux")) {
        } else if (osName.startsWith("Mac OS X")) {
        } else if (osName.startsWith("OS/2")) {
        } else if (osName.startsWith("SunOS")) {
        } else if (osName.startsWith("FreeBSD")) {
            assertTrue(SystemUtils.IS_OS_FREE_BSD);
    }
    }

    @Test
    public void testIS_OS_24_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
        } else if (osName.startsWith("Windows")) {
        } else if (osName.startsWith("Solaris")) {
        } else if (osName.toLowerCase(Locale.ENGLISH).startsWith("linux")) {
        } else if (osName.startsWith("Mac OS X")) {
        } else if (osName.startsWith("OS/2")) {
        } else if (osName.startsWith("SunOS")) {
        } else if (osName.startsWith("FreeBSD")) {
            assertTrue(SystemUtils.IS_OS_UNIX);
    }
    }

    @Test
    public void testIS_OS_25_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
        } else if (osName.startsWith("Windows")) {
        } else if (osName.startsWith("Solaris")) {
        } else if (osName.toLowerCase(Locale.ENGLISH).startsWith("linux")) {
        } else if (osName.startsWith("Mac OS X")) {
        } else if (osName.startsWith("OS/2")) {
        } else if (osName.startsWith("SunOS")) {
        } else if (osName.startsWith("FreeBSD")) {
            assertFalse(SystemUtils.IS_OS_WINDOWS);
    }
    }

    @Test
    public void testIS_zOS_1_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
            assertFalse(SystemUtils.IS_OS_ZOS);
    }
    }

    @Test
    public void testIS_zOS_2_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
        } else if (osName.contains("z/OS")) {
            assertFalse(SystemUtils.IS_OS_WINDOWS);
    }
    }

    @Test
    public void testIS_zOS_3_oe() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
        } else if (osName.contains("z/OS")) {
            assertTrue(SystemUtils.IS_OS_ZOS);
    }
    }

    @Test
    public void testJavaVersionMatches_1_oe() {
        String javaVersion = null;
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
    }

    @Test
    public void testJavaVersionMatches_2_oe() {
        String javaVersion = null;
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
    }

    @Test
    public void testJavaVersionMatches_3_oe() {
        String javaVersion = null;
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
    }

    @Test
    public void testJavaVersionMatches_4_oe() {
        String javaVersion = null;
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
    }

    @Test
    public void testJavaVersionMatches_5_oe() {
        String javaVersion = null;
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
    }

    @Test
    public void testJavaVersionMatches_6_oe() {
        String javaVersion = null;
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
    }

    @Test
    public void testJavaVersionMatches_7_oe() {
        String javaVersion = null;
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
    }

    @Test
    public void testJavaVersionMatches_8_oe() {
        String javaVersion = null;
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
    }

    @Test
    public void testJavaVersionMatches_9_oe() {
        String javaVersion = null;
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
    }

    @Test
    public void testJavaVersionMatches_10_oe() {
        String javaVersion = null;
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
    }

    @Test
    public void testJavaVersionMatches_11_oe() {
        String javaVersion = null;
        javaVersion = "";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
    }

    @Test
    public void testJavaVersionMatches_12_oe() {
        String javaVersion = null;
        javaVersion = "";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
    }

    @Test
    public void testJavaVersionMatches_13_oe() {
        String javaVersion = null;
        javaVersion = "";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
    }

    @Test
    public void testJavaVersionMatches_14_oe() {
        String javaVersion = null;
        javaVersion = "";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
    }

    @Test
    public void testJavaVersionMatches_15_oe() {
        String javaVersion = null;
        javaVersion = "";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
    }

    @Test
    public void testJavaVersionMatches_16_oe() {
        String javaVersion = null;
        javaVersion = "";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
    }

    @Test
    public void testJavaVersionMatches_17_oe() {
        String javaVersion = null;
        javaVersion = "";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
    }

    @Test
    public void testJavaVersionMatches_18_oe() {
        String javaVersion = null;
        javaVersion = "";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
    }

    @Test
    public void testJavaVersionMatches_19_oe() {
        String javaVersion = null;
        javaVersion = "";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
    }

    @Test
    public void testJavaVersionMatches_20_oe() {
        String javaVersion = null;
        javaVersion = "";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
    }

    @Test
    public void testJavaVersionMatches_21_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        assertTrue(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
    }

    @Test
    public void testJavaVersionMatches_22_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
    }

    @Test
    public void testJavaVersionMatches_23_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
    }

    @Test
    public void testJavaVersionMatches_24_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
    }

    @Test
    public void testJavaVersionMatches_25_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
    }

    @Test
    public void testJavaVersionMatches_26_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
    }

    @Test
    public void testJavaVersionMatches_27_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
    }

    @Test
    public void testJavaVersionMatches_28_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
    }

    @Test
    public void testJavaVersionMatches_29_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
    }

    @Test
    public void testJavaVersionMatches_30_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
    }

    @Test
    public void testJavaVersionMatches_31_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
    }

    @Test
    public void testJavaVersionMatches_32_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        assertTrue(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
    }

    @Test
    public void testJavaVersionMatches_33_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
    }

    @Test
    public void testJavaVersionMatches_34_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
    }

    @Test
    public void testJavaVersionMatches_35_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
    }

    @Test
    public void testJavaVersionMatches_36_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
    }

    @Test
    public void testJavaVersionMatches_37_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
    }

    @Test
    public void testJavaVersionMatches_38_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
    }

    @Test
    public void testJavaVersionMatches_39_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
    }

    @Test
    public void testJavaVersionMatches_40_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
    }

    @Test
    public void testJavaVersionMatches_41_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
    }

    @Test
    public void testJavaVersionMatches_42_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
    }

    @Test
    public void testJavaVersionMatches_43_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        assertTrue(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
    }

    @Test
    public void testJavaVersionMatches_44_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
    }

    @Test
    public void testJavaVersionMatches_45_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
    }

    @Test
    public void testJavaVersionMatches_46_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
    }

    @Test
    public void testJavaVersionMatches_47_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
    }

    @Test
    public void testJavaVersionMatches_48_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
    }

    @Test
    public void testJavaVersionMatches_49_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
    }

    @Test
    public void testJavaVersionMatches_50_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
    }

    @Test
    public void testJavaVersionMatches_51_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
    }

    @Test
    public void testJavaVersionMatches_52_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
    }

    @Test
    public void testJavaVersionMatches_53_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
    }

    @Test
    public void testJavaVersionMatches_54_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        assertTrue(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
    }

    @Test
    public void testJavaVersionMatches_55_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
    }

    @Test
    public void testJavaVersionMatches_56_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
    }

    @Test
    public void testJavaVersionMatches_57_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
    }

    @Test
    public void testJavaVersionMatches_58_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
    }

    @Test
    public void testJavaVersionMatches_59_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
    }

    @Test
    public void testJavaVersionMatches_60_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
    }

    @Test
    public void testJavaVersionMatches_61_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
    }

    @Test
    public void testJavaVersionMatches_62_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
    }

    @Test
    public void testJavaVersionMatches_63_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
    }

    @Test
    public void testJavaVersionMatches_64_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        assertTrue(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
    }

    @Test
    public void testJavaVersionMatches_65_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
    }

    @Test
    public void testJavaVersionMatches_66_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
    }

    @Test
    public void testJavaVersionMatches_67_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
    }

    @Test
    public void testJavaVersionMatches_68_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
    }

    @Test
    public void testJavaVersionMatches_69_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
    }

    @Test
    public void testJavaVersionMatches_70_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
    }

    @Test
    public void testJavaVersionMatches_71_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
    }

    @Test
    public void testJavaVersionMatches_72_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
    }

    @Test
    public void testJavaVersionMatches_73_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
    }

    @Test
    public void testJavaVersionMatches_74_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
    }

    @Test
    public void testJavaVersionMatches_75_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        assertTrue(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
    }

    @Test
    public void testJavaVersionMatches_76_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
    }

    @Test
    public void testJavaVersionMatches_77_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
    }

    @Test
    public void testJavaVersionMatches_78_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
    }

    @Test
    public void testJavaVersionMatches_79_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
    }

    @Test
    public void testJavaVersionMatches_80_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
    }

    @Test
    public void testJavaVersionMatches_81_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
    }

    @Test
    public void testJavaVersionMatches_82_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
    }

    @Test
    public void testJavaVersionMatches_83_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
    }

    @Test
    public void testJavaVersionMatches_84_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
    }

    @Test
    public void testJavaVersionMatches_85_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        assertTrue(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
    }

    @Test
    public void testJavaVersionMatches_86_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
    }

    @Test
    public void testJavaVersionMatches_87_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
    }

    @Test
    public void testJavaVersionMatches_88_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
    }

    @Test
    public void testJavaVersionMatches_89_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
    }

    @Test
    public void testJavaVersionMatches_90_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
    }

    @Test
    public void testJavaVersionMatches_91_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
    }

    @Test
    public void testJavaVersionMatches_92_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
    }

    @Test
    public void testJavaVersionMatches_93_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
    }

    @Test
    public void testJavaVersionMatches_94_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
    }

    @Test
    public void testJavaVersionMatches_95_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        assertTrue(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
    }

    @Test
    public void testJavaVersionMatches_96_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
    }

    @Test
    public void testJavaVersionMatches_97_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
    }

    @Test
    public void testJavaVersionMatches_98_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
    }

    @Test
    public void testJavaVersionMatches_99_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
    }

    @Test
    public void testJavaVersionMatches_100_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
    }

    @Test
    public void testJavaVersionMatches_101_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
    }

    @Test
    public void testJavaVersionMatches_102_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
    }

    @Test
    public void testJavaVersionMatches_103_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
    }

    @Test
    public void testJavaVersionMatches_104_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
    }

    @Test
    public void testJavaVersionMatches_105_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
    }

    @Test
    public void testJavaVersionMatches_106_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        assertTrue(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
    }

    @Test
    public void testJavaVersionMatches_107_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
    }

    @Test
    public void testJavaVersionMatches_108_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
    }

    @Test
    public void testJavaVersionMatches_109_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
    }

    @Test
    public void testJavaVersionMatches_110_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
    }

    @Test
    public void testJavaVersionMatches_111_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
    }

    @Test
    public void testJavaVersionMatches_112_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
    }

    @Test
    public void testJavaVersionMatches_113_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
    }

    @Test
    public void testJavaVersionMatches_114_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
    }

    @Test
    public void testJavaVersionMatches_115_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
    }

    @Test
    public void testJavaVersionMatches_116_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
    }

    @Test
    public void testJavaVersionMatches_117_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        assertTrue(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
    }

    @Test
    public void testJavaVersionMatches_118_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
    }

    @Test
    public void testJavaVersionMatches_119_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
    }

    @Test
    public void testJavaVersionMatches_120_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
    }

    @Test
    public void testJavaVersionMatches_121_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
    }

    @Test
    public void testJavaVersionMatches_122_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
    }

    @Test
    public void testJavaVersionMatches_123_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
    }

    @Test
    public void testJavaVersionMatches_124_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
    }

    @Test
    public void testJavaVersionMatches_125_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
    }

    @Test
    public void testJavaVersionMatches_126_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
    }

    @Test
    public void testJavaVersionMatches_127_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
    }

    @Test
    public void testJavaVersionMatches_128_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        assertTrue(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
    }

    @Test
    public void testJavaVersionMatches_129_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
    }

    @Test
    public void testJavaVersionMatches_130_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
    }

    @Test
    public void testJavaVersionMatches_131_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        javaVersion = "1.8.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
    }

    @Test
    public void testJavaVersionMatches_132_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        javaVersion = "1.8.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
    }

    @Test
    public void testJavaVersionMatches_133_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        javaVersion = "1.8.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
    }

    @Test
    public void testJavaVersionMatches_134_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        javaVersion = "1.8.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
    }

    @Test
    public void testJavaVersionMatches_135_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        javaVersion = "1.8.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
    }

    @Test
    public void testJavaVersionMatches_136_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        javaVersion = "1.8.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
    }

    @Test
    public void testJavaVersionMatches_137_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        javaVersion = "1.8.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
    }

    @Test
    public void testJavaVersionMatches_138_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        javaVersion = "1.8.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
    }

    @Test
    public void testJavaVersionMatches_139_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        javaVersion = "1.8.0";
        assertTrue(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
    }

    @Test
    public void testJavaVersionMatches_140_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        javaVersion = "1.8.0";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
    }

    @Test
    public void testJavaVersionMatches_141_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        javaVersion = "1.8.0";
        javaVersion = "9";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
    }

    @Test
    public void testJavaVersionMatches_142_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        javaVersion = "1.8.0";
        javaVersion = "9";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
    }

    @Test
    public void testJavaVersionMatches_143_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        javaVersion = "1.8.0";
        javaVersion = "9";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
    }

    @Test
    public void testJavaVersionMatches_144_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        javaVersion = "1.8.0";
        javaVersion = "9";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
    }

    @Test
    public void testJavaVersionMatches_145_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        javaVersion = "1.8.0";
        javaVersion = "9";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
    }

    @Test
    public void testJavaVersionMatches_146_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        javaVersion = "1.8.0";
        javaVersion = "9";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
    }

    @Test
    public void testJavaVersionMatches_147_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        javaVersion = "1.8.0";
        javaVersion = "9";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
    }

    @Test
    public void testJavaVersionMatches_148_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        javaVersion = "1.8.0";
        javaVersion = "9";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
    }

    @Test
    public void testJavaVersionMatches_149_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        javaVersion = "1.8.0";
        javaVersion = "9";
        assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
    }

    @Test
    public void testJavaVersionMatches_150_oe() {
        String javaVersion = null;
        javaVersion = "";
        javaVersion = "1.0";
        javaVersion = "1.1";
        javaVersion = "1.2";
        javaVersion = "1.3.0";
        javaVersion = "1.3.1";
        javaVersion = "1.4.0";
        javaVersion = "1.4.1";
        javaVersion = "1.4.2";
        javaVersion = "1.5.0";
        javaVersion = "1.6.0";
        javaVersion = "1.7.0";
        javaVersion = "1.8.0";
        javaVersion = "9";
        assertTrue(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
    }

    @Test
    public void testIsJavaVersionAtLeast_1_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_1));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_2_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_2));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_3_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_3));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_4_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_4));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_5_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_5));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_6_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_6));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_7_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_7));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_8_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_8));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_9_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
            assertFalse(SystemUtils.isJavaVersionAtLeast(JAVA_9));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_10_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
            assertFalse(SystemUtils.isJavaVersionAtLeast(JAVA_10));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_11_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
            assertFalse(SystemUtils.isJavaVersionAtLeast(JAVA_11));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_12_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
            assertFalse(SystemUtils.isJavaVersionAtLeast(JAVA_12));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_13_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
            assertFalse(SystemUtils.isJavaVersionAtLeast(JAVA_13));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_14_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_1));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_15_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_2));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_16_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_3));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_17_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_4));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_18_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_5));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_19_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_6));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_20_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_7));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_21_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_8));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_22_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_9));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_23_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
            assertFalse(SystemUtils.isJavaVersionAtLeast(JAVA_10));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_24_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
            assertFalse(SystemUtils.isJavaVersionAtLeast(JAVA_11));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_25_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
            assertFalse(SystemUtils.isJavaVersionAtLeast(JAVA_12));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_26_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
            assertFalse(SystemUtils.isJavaVersionAtLeast(JAVA_13));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_27_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_1));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_28_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_2));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_29_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_3));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_30_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_4));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_31_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_5));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_32_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_6));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_33_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_7));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_34_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_8));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_35_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_9));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_36_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_10));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_37_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
            assertFalse(SystemUtils.isJavaVersionAtLeast(JAVA_11));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_38_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
            assertFalse(SystemUtils.isJavaVersionAtLeast(JAVA_12));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_39_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
            assertFalse(SystemUtils.isJavaVersionAtLeast(JAVA_13));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_40_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_1));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_41_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_2));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_42_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_3));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_43_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_4));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_44_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_5));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_45_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_6));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_46_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_7));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_47_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_8));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_48_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_9));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_49_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_10));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_50_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_11));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_51_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
            assertFalse(SystemUtils.isJavaVersionAtLeast(JAVA_12));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_52_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
            assertFalse(SystemUtils.isJavaVersionAtLeast(JAVA_13));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_53_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_1));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_54_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_2));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_55_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_3));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_56_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_4));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_57_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_5));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_58_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_6));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_59_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_7));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_60_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_8));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_61_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_9));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_62_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_10));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_63_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_11));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_64_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_12));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_65_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
            assertFalse(SystemUtils.isJavaVersionAtLeast(JAVA_13));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_66_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
        } else if (SystemUtils.IS_JAVA_13) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_1));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_67_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
        } else if (SystemUtils.IS_JAVA_13) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_2));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_68_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
        } else if (SystemUtils.IS_JAVA_13) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_3));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_69_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
        } else if (SystemUtils.IS_JAVA_13) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_4));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_70_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
        } else if (SystemUtils.IS_JAVA_13) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_5));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_71_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
        } else if (SystemUtils.IS_JAVA_13) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_6));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_72_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
        } else if (SystemUtils.IS_JAVA_13) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_7));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_73_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
        } else if (SystemUtils.IS_JAVA_13) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_8));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_74_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
        } else if (SystemUtils.IS_JAVA_13) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_9));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_75_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
        } else if (SystemUtils.IS_JAVA_13) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_10));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_76_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
        } else if (SystemUtils.IS_JAVA_13) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_11));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_77_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
        } else if (SystemUtils.IS_JAVA_13) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_12));
    }
    }

    @Test
    public void testIsJavaVersionAtLeast_78_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
        } else if (SystemUtils.IS_JAVA_13) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_13));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_1_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_1));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_2_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_2));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_3_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_3));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_4_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_4));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_5_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_5));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_6_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_6));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_7_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_7));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_8_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_1_8));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_9_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_9));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_10_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_10));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_11_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_11));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_12_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_12));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_13_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_13));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_14_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_1));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_15_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_2));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_16_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_3));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_17_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_4));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_18_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_5));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_19_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_6));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_20_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_7));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_21_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_8));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_22_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_9));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_23_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_10));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_24_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_11));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_25_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_12));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_26_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_13));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_27_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_1));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_28_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_2));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_29_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_3));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_30_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_4));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_31_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_5));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_32_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_6));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_33_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_7));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_34_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_8));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_35_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_9));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_36_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_10));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_37_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_11));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_38_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_12));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_39_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_13));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_40_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_1));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_41_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_2));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_42_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_3));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_43_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_4));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_44_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_5));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_45_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_6));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_46_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_7));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_47_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_8));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_48_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_9));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_49_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_10));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_50_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_11));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_51_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_12));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_52_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_13));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_53_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_1));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_54_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_2));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_55_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_3));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_56_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_4));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_57_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_5));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_58_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_6));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_59_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_7));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_60_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_8));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_61_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_9));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_62_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_10));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_63_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_11));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_64_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_12));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_65_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_13));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_66_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
        } else if (SystemUtils.IS_JAVA_13) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_1));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_67_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
        } else if (SystemUtils.IS_JAVA_13) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_2));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_68_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
        } else if (SystemUtils.IS_JAVA_13) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_3));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_69_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
        } else if (SystemUtils.IS_JAVA_13) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_4));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_70_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
        } else if (SystemUtils.IS_JAVA_13) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_5));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_71_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
        } else if (SystemUtils.IS_JAVA_13) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_6));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_72_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
        } else if (SystemUtils.IS_JAVA_13) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_7));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_73_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
        } else if (SystemUtils.IS_JAVA_13) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_8));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_74_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
        } else if (SystemUtils.IS_JAVA_13) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_9));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_75_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
        } else if (SystemUtils.IS_JAVA_13) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_10));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_76_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
        } else if (SystemUtils.IS_JAVA_13) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_11));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_77_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
        } else if (SystemUtils.IS_JAVA_13) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_12));
    }
    }

    @Test
    public void testIsJavaVersionAtMost_78_oe() {
        if (SystemUtils.IS_JAVA_1_8) {
        } else if (SystemUtils.IS_JAVA_9) {
        } else if (SystemUtils.IS_JAVA_10) {
        } else if (SystemUtils.IS_JAVA_11) {
        } else if (SystemUtils.IS_JAVA_12) {
        } else if (SystemUtils.IS_JAVA_13) {
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_13));
    }
    }

    @Test
    public void testOSMatchesName_1_oe() {
        String osName = null;
        assertFalse(SystemUtils.isOSNameMatch(osName, "Windows"));
    }

    @Test
    public void testOSMatchesName_2_oe() {
        String osName = null;
        osName = "";
        assertFalse(SystemUtils.isOSNameMatch(osName, "Windows"));
    }

    @Test
    public void testOSMatchesName_3_oe() {
        String osName = null;
        osName = "";
        osName = "Windows 95";
        assertTrue(SystemUtils.isOSNameMatch(osName, "Windows"));
    }

    @Test
    public void testOSMatchesName_4_oe() {
        String osName = null;
        osName = "";
        osName = "Windows 95";
        osName = "Windows NT";
        assertTrue(SystemUtils.isOSNameMatch(osName, "Windows"));
    }

    @Test
    public void testOSMatchesName_5_oe() {
        String osName = null;
        osName = "";
        osName = "Windows 95";
        osName = "Windows NT";
        osName = "OS/2";
        assertFalse(SystemUtils.isOSNameMatch(osName, "Windows"));
    }

    @Test
    public void testOSMatchesNameAndVersion_1_oe() {
        String osName = null;
        String osVersion = null;
        assertFalse(SystemUtils.isOSMatch(osName, osVersion, "Windows 9", "4.1"));
    }

    @Test
    public void testOSMatchesNameAndVersion_2_oe() {
        String osName = null;
        String osVersion = null;
        osName = "";
        osVersion = "";
        assertFalse(SystemUtils.isOSMatch(osName, osVersion, "Windows 9", "4.1"));
    }

    @Test
    public void testOSMatchesNameAndVersion_3_oe() {
        String osName = null;
        String osVersion = null;
        osName = "";
        osVersion = "";
        osName = "Windows 95";
        osVersion = "4.0";
        assertFalse(SystemUtils.isOSMatch(osName, osVersion, "Windows 9", "4.1"));
    }

    @Test
    public void testOSMatchesNameAndVersion_4_oe() {
        String osName = null;
        String osVersion = null;
        osName = "";
        osVersion = "";
        osName = "Windows 95";
        osVersion = "4.0";
        osName = "Windows 95";
        osVersion = "4.1";
        assertTrue(SystemUtils.isOSMatch(osName, osVersion, "Windows 9", "4.1"));
    }

    @Test
    public void testOSMatchesNameAndVersion_5_oe() {
        String osName = null;
        String osVersion = null;
        osName = "";
        osVersion = "";
        osName = "Windows 95";
        osVersion = "4.0";
        osName = "Windows 95";
        osVersion = "4.1";
        osName = "Windows 98";
        osVersion = "4.1";
        assertTrue(SystemUtils.isOSMatch(osName, osVersion, "Windows 9", "4.1"));
    }

    @Test
    public void testOSMatchesNameAndVersion_6_oe() {
        String osName = null;
        String osVersion = null;
        osName = "";
        osVersion = "";
        osName = "Windows 95";
        osVersion = "4.0";
        osName = "Windows 95";
        osVersion = "4.1";
        osName = "Windows 98";
        osVersion = "4.1";
        osName = "Windows NT";
        osVersion = "4.0";
        assertFalse(SystemUtils.isOSMatch(osName, osVersion, "Windows 9", "4.1"));
    }

    @Test
    public void testOSMatchesNameAndVersion_7_oe() {
        String osName = null;
        String osVersion = null;
        osName = "";
        osVersion = "";
        osName = "Windows 95";
        osVersion = "4.0";
        osName = "Windows 95";
        osVersion = "4.1";
        osName = "Windows 98";
        osVersion = "4.1";
        osName = "Windows NT";
        osVersion = "4.0";
        osName = "OS/2";
        osVersion = "4.0";
        assertFalse(SystemUtils.isOSMatch(osName, osVersion, "Windows 9", "4.1"));
    }

    @Test
    public void testOsVersionMatches_1_oe() {
        String osVersion = null;
        assertFalse(SystemUtils.isOSVersionMatch(osVersion, "10.1"));
    }

    @Test
    public void testOsVersionMatches_2_oe() {
        String osVersion = null;

        osVersion = "";
        assertFalse(SystemUtils.isOSVersionMatch(osVersion, "10.1"));
    }

    @Test
    public void testOsVersionMatches_3_oe() {
        String osVersion = null;

        osVersion = "";

        osVersion = "10";
        assertTrue(SystemUtils.isOSVersionMatch(osVersion, "10.1"));
    }

    @Test
    public void testOsVersionMatches_4_oe() {
        String osVersion = null;

        osVersion = "";

        osVersion = "10";
        assertTrue(SystemUtils.isOSVersionMatch(osVersion, "10.1.1"));
    }

    @Test
    public void testOsVersionMatches_5_oe() {
        String osVersion = null;

        osVersion = "";

        osVersion = "10";
        assertTrue(SystemUtils.isOSVersionMatch(osVersion, "10.10"));
    }

    @Test
    public void testOsVersionMatches_6_oe() {
        String osVersion = null;

        osVersion = "";

        osVersion = "10";
        assertTrue(SystemUtils.isOSVersionMatch(osVersion, "10.10.1"));
    }

    @Test
    public void testOsVersionMatches_7_oe() {
        String osVersion = null;

        osVersion = "";

        osVersion = "10";

        osVersion = "10.1";
        assertTrue(SystemUtils.isOSVersionMatch(osVersion, "10.1"));
    }

    @Test
    public void testOsVersionMatches_8_oe() {
        String osVersion = null;

        osVersion = "";

        osVersion = "10";

        osVersion = "10.1";
        assertTrue(SystemUtils.isOSVersionMatch(osVersion, "10.1.1"));
    }

    @Test
    public void testOsVersionMatches_9_oe() {
        String osVersion = null;

        osVersion = "";

        osVersion = "10";

        osVersion = "10.1";
        assertFalse(SystemUtils.isOSVersionMatch(osVersion, "10.10"));
    }

    @Test
    public void testOsVersionMatches_10_oe() {
        String osVersion = null;

        osVersion = "";

        osVersion = "10";

        osVersion = "10.1";
        assertFalse(SystemUtils.isOSVersionMatch(osVersion, "10.10.1"));
    }

    @Test
    public void testOsVersionMatches_11_oe() {
        String osVersion = null;

        osVersion = "";

        osVersion = "10";

        osVersion = "10.1";

        osVersion = "10.1.1";
        assertTrue(SystemUtils.isOSVersionMatch(osVersion, "10.1"));
    }

    @Test
    public void testOsVersionMatches_12_oe() {
        String osVersion = null;

        osVersion = "";

        osVersion = "10";

        osVersion = "10.1";

        osVersion = "10.1.1";
        assertTrue(SystemUtils.isOSVersionMatch(osVersion, "10.1.1"));
    }

    @Test
    public void testOsVersionMatches_13_oe() {
        String osVersion = null;

        osVersion = "";

        osVersion = "10";

        osVersion = "10.1";

        osVersion = "10.1.1";
        assertFalse(SystemUtils.isOSVersionMatch(osVersion, "10.10"));
    }

    @Test
    public void testOsVersionMatches_14_oe() {
        String osVersion = null;

        osVersion = "";

        osVersion = "10";

        osVersion = "10.1";

        osVersion = "10.1.1";
        assertFalse(SystemUtils.isOSVersionMatch(osVersion, "10.10.1"));
    }

    @Test
    public void testOsVersionMatches_15_oe() {
        String osVersion = null;

        osVersion = "";

        osVersion = "10";

        osVersion = "10.1";

        osVersion = "10.1.1";

        osVersion = "10.10";
        assertFalse(SystemUtils.isOSVersionMatch(osVersion, "10.1"));
    }

    @Test
    public void testOsVersionMatches_16_oe() {
        String osVersion = null;

        osVersion = "";

        osVersion = "10";

        osVersion = "10.1";

        osVersion = "10.1.1";

        osVersion = "10.10";
        assertFalse(SystemUtils.isOSVersionMatch(osVersion, "10.1.1"));
    }

    @Test
    public void testOsVersionMatches_17_oe() {
        String osVersion = null;

        osVersion = "";

        osVersion = "10";

        osVersion = "10.1";

        osVersion = "10.1.1";

        osVersion = "10.10";
        assertTrue(SystemUtils.isOSVersionMatch(osVersion, "10.10"));
    }

    @Test
    public void testOsVersionMatches_18_oe() {
        String osVersion = null;

        osVersion = "";

        osVersion = "10";

        osVersion = "10.1";

        osVersion = "10.1.1";

        osVersion = "10.10";
        assertTrue(SystemUtils.isOSVersionMatch(osVersion, "10.10.1"));
    }

    @Test
    public void testOsVersionMatches_19_oe() {
        String osVersion = null;

        osVersion = "";

        osVersion = "10";

        osVersion = "10.1";

        osVersion = "10.1.1";

        osVersion = "10.10";

        osVersion = "10.10.1";
        assertFalse(SystemUtils.isOSVersionMatch(osVersion, "10.1"));
    }

    @Test
    public void testOsVersionMatches_20_oe() {
        String osVersion = null;

        osVersion = "";

        osVersion = "10";

        osVersion = "10.1";

        osVersion = "10.1.1";

        osVersion = "10.10";

        osVersion = "10.10.1";
        assertFalse(SystemUtils.isOSVersionMatch(osVersion, "10.1.1"));
    }

    @Test
    public void testOsVersionMatches_21_oe() {
        String osVersion = null;

        osVersion = "";

        osVersion = "10";

        osVersion = "10.1";

        osVersion = "10.1.1";

        osVersion = "10.10";

        osVersion = "10.10.1";
        assertTrue(SystemUtils.isOSVersionMatch(osVersion, "10.10"));
    }

    @Test
    public void testOsVersionMatches_22_oe() {
        String osVersion = null;

        osVersion = "";

        osVersion = "10";

        osVersion = "10.1";

        osVersion = "10.1.1";

        osVersion = "10.10";

        osVersion = "10.10.1";
        assertTrue(SystemUtils.isOSVersionMatch(osVersion, "10.10.1"));
    }

    @Test
    public void testJavaAwtHeadless_1_oe() {
        final String expectedStringValue = System.getProperty("java.awt.headless");
        final String expectedStringValueWithDefault = System.getProperty("java.awt.headless", "false");
        assertNotNull(expectedStringValueWithDefault);
    }

    @Test
    public void testJavaAwtHeadless_2_oe() {
        final String expectedStringValue = System.getProperty("java.awt.headless");
        final String expectedStringValueWithDefault = System.getProperty("java.awt.headless", "false");
        final boolean expectedValue = Boolean.valueOf(expectedStringValue).booleanValue();
        if (expectedStringValue != null) {
            assertEquals(expectedStringValue, SystemUtils.JAVA_AWT_HEADLESS);
    }
    }

    @Test
    public void testJavaAwtHeadless_3_oe() {
        final String expectedStringValue = System.getProperty("java.awt.headless");
        final String expectedStringValueWithDefault = System.getProperty("java.awt.headless", "false");
        final boolean expectedValue = Boolean.valueOf(expectedStringValue).booleanValue();
        if (expectedStringValue != null) {
        }
        assertEquals(expectedValue, SystemUtils.isJavaAwtHeadless());
    }

    @Test
    public void testJavaAwtHeadless_4_oe() {
        final String expectedStringValue = System.getProperty("java.awt.headless");
        final String expectedStringValueWithDefault = System.getProperty("java.awt.headless", "false");
        final boolean expectedValue = Boolean.valueOf(expectedStringValue).booleanValue();
        if (expectedStringValue != null) {
        }
        assertEquals(expectedStringValueWithDefault, "" + SystemUtils.isJavaAwtHeadless());
    }

}
