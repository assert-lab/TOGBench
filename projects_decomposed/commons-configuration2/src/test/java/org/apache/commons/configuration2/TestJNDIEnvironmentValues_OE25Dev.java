/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.configuration2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class TestJNDIEnvironmentValues_OE25Dev {
    private JNDIConfiguration conf;

    @Before
    public void setUp() throws Exception {
        System.setProperty("java.naming.factory.initial", TestJNDIConfiguration.CONTEXT_FACTORY);

        conf = new JNDIConfiguration();
        conf.setThrowExceptionOnMissing(true);
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetMissingKey() throws Exception {
        conf.getString("test.imaginarykey");
    }

    @Test
    public void testClearProperty_1_oe() {
        assertNotNull("null short for the 'test.short' key", conf.getShort("test.short", null));
    }

    @Test
    public void testClearProperty_2_oe() {
        conf.clearProperty("test.short");
        assertNull("'test.short' property not cleared", conf.getShort("test.short", null));
    }

    @Test
    public void testContainsKey_1_oe() throws Exception {
        assertTrue(conf.containsKey("test.key"));
    }

    @Test
    public void testContainsKey_2_oe() throws Exception {
        assertFalse(conf.containsKey("test.imaginarykey"));
    }

    @Test
    public void testGetKeys_1_oe() throws Exception {
        boolean found = false;
        final Iterator<String> it = conf.getKeys();

        assertTrue("no key found", it.hasNext());
    }

    @Test
    public void testGetKeys_2_oe() throws Exception {
        boolean found = false;
        final Iterator<String> it = conf.getKeys();


        while (it.hasNext() && !found) {
            found = "test.boolean".equals(it.next());
        }

        assertTrue("'test.boolean' key not found", found);
    }

    @Test
    public void testGetKeysWithExistingPrefix_1_oe() {
        final Iterator<String> it = conf.getKeys("test");
        boolean found = false;
        while (it.hasNext() && !found) {
            found = "test.boolean".equals(it.next());
        }

        assertTrue("'test.boolean' key not found", found);
    }

    @Test
    public void testGetKeysWithKeyAsPrefix_1_oe() {
        final Iterator<String> it = conf.getKeys("test.boolean");
        boolean found = false;
        while (it.hasNext() && !found) {
            found = "test.boolean".equals(it.next());
        }

        assertTrue("'test.boolean' key not found", found);
    }

    @Test
    public void testGetKeysWithUnknownPrefix_1_oe() {
        final Iterator<String> it = conf.getKeys("foo.bar");
        assertFalse("no key should be found", it.hasNext());
    }

    @Test
    public void testGetMissingKeyWithDefault_1_oe() throws Exception {
        final String result = conf.getString("test.imaginarykey", "bob");
        assertEquals("bob", result);
    }

    @Test
    public void testIsEmpty_1_oe() {
        assertFalse("the configuration shouldn't be empty", conf.isEmpty());
    }

    @Test
    public void testMoreGets_1_oe() throws Exception {
        final String s = conf.getString("test.key");
        assertEquals("jndivalue", s);
    }

    @Test
    public void testMoreGets_2_oe() throws Exception {
        final String s = conf.getString("test.key");
        assertEquals("jndivalue2", conf.getString("test.key2"));
    }

    @Test
    public void testMoreGets_3_oe() throws Exception {
        final String s = conf.getString("test.key");
        assertEquals(1, conf.getShort("test.short"));
    }

    @Test
    public void testSimpleGet_1_oe() throws Exception {
        final String s = conf.getString("test.key");
        assertEquals("jndivalue", s);
    }

    @Test
    public void testThrowExceptionOnMissing_1_oe() {
        assertTrue("Throw Exception Property is not set!", conf.isThrowExceptionOnMissing());
    }

}
