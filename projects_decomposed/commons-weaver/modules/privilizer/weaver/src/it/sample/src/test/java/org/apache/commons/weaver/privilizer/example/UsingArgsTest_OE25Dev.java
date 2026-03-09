/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.commons.weaver.privilizer.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.apache.commons.weaver.privilizer.example.UsingArgs.CheckedException1;
import org.apache.commons.weaver.privilizer.example.UsingArgs.CheckedException2;
import org.junit.Before;
import org.junit.Test;

public class UsingArgsTest_OE25Dev {
    private UsingArgs usingArgs;

    @Before
    public void setUp() throws Exception {
        Setup.setProperty("foo", "foo-value");
        Setup.setProperty("bar", "bar-value");
        Setup.setProperty("baz", "baz-value");
        usingArgs = new UsingArgs();
    }

    @Test
    public void testGetProperty() {
        assertEquals("foo-value", usingArgs.getProperty("foo"));
        assertEquals("bar-value", usingArgs.getProperty("bar"));
        assertEquals("baz-value", usingArgs.getProperty("baz"));
    }

    @Test
    public void testGetProperties() {
        assertTrue(Arrays.equals(new String[] { "foo-value", "bar-value", "baz-value" },
            usingArgs.getProperties("foo", "bar", "baz")));
        assertEquals(0, usingArgs.getProperties().length);
        assertNull(usingArgs.getProperties((String[]) null));
    }

    @Test
    public void testThrowAwayProperty() {
        usingArgs.throwAwayProperty('f', "o", 'o');
    }

    @Test
    public void testAssembleAndGetProperty() {
        assertEquals("foo-value", usingArgs.assembleAndGetProperty('f', new StringBuilder().append('o'), 'o'));
        assertEquals("bar-value", usingArgs.assembleAndGetProperty('b', new StringBuilder().append('a'), 'r'));
        assertEquals("baz-value", usingArgs.assembleAndGetProperty('b', new StringBuilder().append('a'), 'z'));
    }

    @Test
    public void testThrowingCheckedException() throws CheckedException1, CheckedException2 {
        assertEquals(0, usingArgs.throwingCheckedException(0, "foo"));
        try {
            usingArgs.throwingCheckedException(1, "bar");
        } catch (CheckedException1 e) {
        }
        try {
            usingArgs.throwingCheckedException(2, "baz");
        } catch (CheckedException2 e) {
        }
    }

    @Test
    public void testGetProperty_1_oe() {
        assertEquals("foo-value", usingArgs.getProperty("foo"));
    }

    @Test
    public void testGetProperty_2_oe() {
        assertEquals("bar-value", usingArgs.getProperty("bar"));
    }

    @Test
    public void testGetProperty_3_oe() {
        assertEquals("baz-value", usingArgs.getProperty("baz"));
    }

    @Test
    public void testGetProperties_1_oe() {
        assertTrue(Arrays.equals(new String[] { "foo-value", "bar-value", "baz-value" }, usingArgs.getProperties("foo", "bar", "baz")));
    }

    @Test
    public void testGetProperties_2_oe() {
        assertEquals(0, usingArgs.getProperties().length);
    }

    @Test
    public void testGetProperties_3_oe() {
        assertNull(usingArgs.getProperties((String[]) null));
    }

    @Test
    public void testAssembleAndGetProperty_1_oe() {
        assertEquals("foo-value", usingArgs.assembleAndGetProperty('f', new StringBuilder().append('o'), 'o'));
    }

    @Test
    public void testAssembleAndGetProperty_2_oe() {
        assertEquals("bar-value", usingArgs.assembleAndGetProperty('b', new StringBuilder().append('a'), 'r'));
    }

    @Test
    public void testAssembleAndGetProperty_3_oe() {
        assertEquals("baz-value", usingArgs.assembleAndGetProperty('b', new StringBuilder().append('a'), 'z'));
    }

    @Test
    public void testThrowingCheckedException_1_oe() throws CheckedException1, CheckedException2 {
        assertEquals(0, usingArgs.throwingCheckedException(0, "foo"));
    }

}
