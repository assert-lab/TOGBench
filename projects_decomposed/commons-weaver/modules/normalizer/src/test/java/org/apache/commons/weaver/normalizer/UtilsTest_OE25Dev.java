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
package org.apache.commons.weaver.normalizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

public class UtilsTest_OE25Dev {

    @Test(expected = IllegalArgumentException.class)
    public void testValidatePackageNameStartsWithDigit() {
        Utils.validatePackageName("2foo");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidatePackageNameEmbeddedWhitespace() {
        Utils.validatePackageName("foo bar");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseUnknownType() {
        Utils.parseTypes("gobbledygook", getClass().getClassLoader());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseMissingFirstType() {
        Utils.parseTypes(",java.lang.Object", getClass().getClassLoader());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParseMissingLastType() {
        Utils.parseTypes("java.lang.Object,", getClass().getClassLoader());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseMissingType() {
        Utils.parseTypes("java.lang.Object,,java.lang.Iterable", getClass().getClassLoader());
    }
    
    <E> void assertContainsInOrder(Iterable<E> iterable, E... expectedElements) {
        final Iterator<E> iterator = iterable.iterator();
        for (E e : expectedElements) {
            assertTrue(iterator.hasNext());
            assertEquals(e, iterator.next());
        }
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testValidatePackageName_1_oe() {
        assertEquals("", Utils.validatePackageName(""));
    }

    @Test
    public void testValidatePackageName_2_oe() {
        assertEquals("", Utils.validatePackageName("    "));
    }

    @Test
    public void testValidatePackageName_3_oe() {
        assertEquals("foo", Utils.validatePackageName("foo"));
    }

    @Test
    public void testValidatePackageName_4_oe() {
        assertEquals("foo/bar", Utils.validatePackageName("foo.bar"));
    }

    @Test
    public void testValidatePackageName_5_oe() {
        assertEquals("foo/bar", Utils.validatePackageName("foo/bar"));
    }

    @Test
    public void testValidatePackageName_6_oe() {
        assertEquals("foo/bar/baz", Utils.validatePackageName("foo.bar.baz"));
    }

    @Test
    public void testValidatePackageName_7_oe() {
        assertEquals("foo/bar/baz", Utils.validatePackageName("foo.bar/baz"));
    }

    @Test
    public void testValidatePackageName_8_oe() {
        assertEquals("foo/bar/baz", Utils.validatePackageName("foo/bar.baz"));
    }

    @Test
    public void testValidatePackageName_9_oe() {
        assertEquals("foo/bar/baz", Utils.validatePackageName("foo/bar/baz"));
    }

    @Test
    public void testValidatePackageName_10_oe() {
        assertEquals("$foo", Utils.validatePackageName("$foo"));
    }

    @Test
    public void testValidatePackageName_11_oe() {
        assertEquals("_foo", Utils.validatePackageName("_foo"));
    }

    @Test
    public void testValidatePackageName_12_oe() {
        assertEquals("foo2", Utils.validatePackageName("foo2"));
    }

@Test
    public void testParseTypes_1_oe() {
        assertContainsInOrder(Utils.parseTypes(" java.lang.Number ", getClass().getClassLoader()), Number.class);
    }

@Test
    public void testParseTypes_2_oe() {
        assertContainsInOrder( Utils.parseTypes("java.lang.Number,java.lang.String,java.util.Map", getClass().getClassLoader()), Number.class, String.class, Map.class);
    }

@Test
    public void testParseTypes_3_oe() {
        assertContainsInOrder( Utils.parseTypes("java.lang.Number, java.lang.String, java.util.Map", getClass().getClassLoader()), Number.class, String.class, Map.class);
    }

@Test
    public void testParseTypes_4_oe() {
        assertContainsInOrder( Utils.parseTypes("java/lang/Number, java/lang/String, java/util/Map", getClass().getClassLoader()), Number.class, String.class, Map.class);
    }

@Test
    public void testParseTypes_5_oe() {
        assertContainsInOrder( Utils.parseTypes("java.lang.Number,\njava.lang.String,\njava.util.Map", getClass().getClassLoader()), Number.class, String.class, Map.class);
    }

}
