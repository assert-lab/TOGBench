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
package org.apache.commons.configuration2.interpol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.awt.event.KeyEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ConstantLookup.
 *
 */
public class TestConstantLookup_OE25Dev {
    /** A public field that can be read by the lookup. */
    public static final String FIELD = "Field that can be read";

    /** A private field that cannot be read by the lookup. */
    @SuppressWarnings("unused")
    private static final String PRIVATE_FIELD = "PRIVATE";

    /** The lookup object to be tested. */
    private ConstantLookup lookup;

    @Before
    public void setUp() throws Exception {
        lookup = new ConstantLookup();
    }

    /**
     * Clears the test environment. Here the static cache of the constant lookup class is wiped out.
     */
    @After
    public void tearDown() {
        ConstantLookup.clear();
    }

    /**
     * Tests accessing the cache by querying a variable twice.
     */
    @Test
    public void testLookupCache() {
        testLookupConstant();
        testLookupConstant();
    }

    /**
     * Tests resolving a valid constant.
     */

    /**
     * Tries to resolve a variable with an invalid syntax: The name does not contain a dot as a field separator.
     */

    /**
     * Tests resolving a non existing constant. Result should be null.
     */

    /**
     * Tests resolving a non string constant. Then looks the same variable up from the cache.
     */

    /**
     * Tests looking up a null variable.
     */

    /**
     * Tests resolving a private constant. Because a private field cannot be accessed this should again yield null.
     */

    /**
     * Tests resolving a field from an unknown class.
     */

    /**
     * Generates the name of a variable for a lookup operation based on the given field name of this class.
     *
     * @param field the field name
     * @return the variable for looking up this field
     */
    private String variable(final String field) {
        return getClass().getName() + '.' + field;
    }

    @Test
    public void testLookupConstant_1_oe() {
        assertEquals("Wrong value of constant", FIELD, lookup.lookup(variable("FIELD")));
    }

    @Test
    public void testLookupInvalidSyntax_1_oe() {
        assertNull("Non null return value for invalid variable name", lookup.lookup("InvalidVariableName"));
    }

    @Test
    public void testLookupNonExisting_1_oe() {
        assertNull("Non null return value for non existing constant", lookup.lookup(variable("NO_FIELD")));
    }

    @Test
    public void testLookupNonStringFromCache_1_oe() {
        final String var = KeyEvent.class.getName() + ".VK_ESCAPE";
        final Object expected = KeyEvent.VK_ESCAPE;
        assertEquals("Wrong result of first lookup", expected, lookup.lookup(var));
    }

    @Test
    public void testLookupNonStringFromCache_2_oe() {
        final String var = KeyEvent.class.getName() + ".VK_ESCAPE";
        final Object expected = KeyEvent.VK_ESCAPE;
        // removed other assertion
        assertEquals("Wrong result of 2nd lookup", expected, lookup.lookup(var));
    }

    @Test
    public void testLookupNull_1_oe() {
        assertNull("Non null return value for null variable", lookup.lookup(null));
    }

    @Test
    public void testLookupPrivate_1_oe() {
        assertNull("Non null return value for non accessible field", lookup.lookup(variable("PRIVATE_FIELD")));
    }

    @Test
    public void testLookupUnknownClass_1_oe() {
        assertNull("Non null return value for unknown class", lookup.lookup("org.apache.commons.configuration.NonExistingConfig." + FIELD));
    }

}
