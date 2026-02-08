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

package org.apache.commons.beanutils.converters;

import junit.framework.TestCase;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;

/**
 * Test conversions of String[]->boolean[] and String->boolean[].
 *
 * <p>Note that the tests here don't rigorously test conversions of individual
 * strings to booleans, as the BooleanArrayConverter class uses a
 * BooleanConverter instance to do those conversions, and the BooleanConverter
 * class has its own unit tests. Here, the tests focus on the array-related
 * behaviour.</p>
 *
 * @version $Id$
 */
public class BooleanArrayConverterTestCase_OE25Dev extends TestCase {

    public static final String[] STANDARD_TRUES = new String[] {
            "yes", "y", "true", "on", "1"
        };

    public static final String[] STANDARD_FALSES = new String[] {
            "no", "n", "false", "off", "0"
        };


    public BooleanArrayConverterTestCase_OE25Dev(final String name) {
        super(name);
    }

    /**
     * Check that an object of type String[] with valid boolean string
     * values gets converted nicely.
     */

    /**
     * Check that an object whose toString method returns a list of boolean
     * values gets converted nicely.
     */

    /**
     * Check that the user can specify non-standard true/false values by
     * providing a customised BooleanConverter.
     */

    /**
     * Check that when the input string cannot be split into a String[], and
     * there is no default value then an exception is thrown.
     */
    public void testInvalidStringWithoutDefault() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();
        try {
            converter.convert(null, "true!");
            fail("Converting invalid string should have generated an exception");
        } catch (final ConversionException expected) {
            // Exception is successful test
        }
    }

    /**
     * Check that when the input string cannot be split into a String[], and
     * there is a default value then that default is returned.
     */

    /**
     * Check that when one of the elements in a comma-separated string is not
     * a valid boolean, and there is no default value then an exception is thrown.
     */
    public void testInvalidElementWithoutDefault() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();
        try {
            converter.convert(null, "true,bogus");
            fail("Converting invalid string should have generated an exception");
        } catch (final ConversionException expected) {
            // Exception is successful test
        }
    }

    /**
     * Check that when one of the elements in a comma-separated string is not
     * a valid boolean, and there is a default value then the default value
     * is returned.
     * <p>
     * Note that the default value is for the complete array object returned,
     * not for the failed element.
     */

    /**
     * Check that when a custom BooleanConverter is used, and that converter
     * has a (per-element) default, then that element (and just that element)
     * is assigned the default value.
     * <p>
     * With the standard BooleanArrayConverter, if <i>any</i> of the elements
     * in the array are bad, then the array-wide default value is returned.
     * However by specifying a custom BooleanConverter which has a per-element
     * default, the unrecognized elements get that per-element default but the
     * others are converted as expected.
     */

    /**
     * Check that registration of a custom converter works.
     */

    public void testStandardStringArrayConversion_1_oe() {
        final String[] values = {
            "true", "false",
            "yes", "no",
            "y", "n",
            "1", "0",
        };

        final BooleanArrayConverter converter = new BooleanArrayConverter();
        final boolean[] results = (boolean[]) converter.convert(null, values);

        assertNotNull(results);
    }

    public void testStandardStringArrayConversion_2_oe() {
        final String[] values = {
            "true", "false",
            "yes", "no",
            "y", "n",
            "1", "0",
        };

        final BooleanArrayConverter converter = new BooleanArrayConverter();
        final boolean[] results = (boolean[]) converter.convert(null, values);

        // removed other assertion
        assertEquals(8, results.length);
    }

    public void testStandardStringArrayConversion_3_oe() {
        final String[] values = {
            "true", "false",
            "yes", "no",
            "y", "n",
            "1", "0",
        };

        final BooleanArrayConverter converter = new BooleanArrayConverter();
        final boolean[] results = (boolean[]) converter.convert(null, values);

        // removed other assertion
        // removed other assertion
        assertTrue(results[0]);
    }

    public void testStandardStringArrayConversion_4_oe() {
        final String[] values = {
            "true", "false",
            "yes", "no",
            "y", "n",
            "1", "0",
        };

        final BooleanArrayConverter converter = new BooleanArrayConverter();
        final boolean[] results = (boolean[]) converter.convert(null, values);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(results[1]);
    }

    public void testStandardStringArrayConversion_5_oe() {
        final String[] values = {
            "true", "false",
            "yes", "no",
            "y", "n",
            "1", "0",
        };

        final BooleanArrayConverter converter = new BooleanArrayConverter();
        final boolean[] results = (boolean[]) converter.convert(null, values);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(results[2]);
    }

    public void testStandardStringArrayConversion_6_oe() {
        final String[] values = {
            "true", "false",
            "yes", "no",
            "y", "n",
            "1", "0",
        };

        final BooleanArrayConverter converter = new BooleanArrayConverter();
        final boolean[] results = (boolean[]) converter.convert(null, values);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(results[3]);
    }

    public void testStandardStringArrayConversion_7_oe() {
        final String[] values = {
            "true", "false",
            "yes", "no",
            "y", "n",
            "1", "0",
        };

        final BooleanArrayConverter converter = new BooleanArrayConverter();
        final boolean[] results = (boolean[]) converter.convert(null, values);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(results[4]);
    }

    public void testStandardStringArrayConversion_8_oe() {
        final String[] values = {
            "true", "false",
            "yes", "no",
            "y", "n",
            "1", "0",
        };

        final BooleanArrayConverter converter = new BooleanArrayConverter();
        final boolean[] results = (boolean[]) converter.convert(null, values);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(results[5]);
    }

    public void testStandardStringArrayConversion_9_oe() {
        final String[] values = {
            "true", "false",
            "yes", "no",
            "y", "n",
            "1", "0",
        };

        final BooleanArrayConverter converter = new BooleanArrayConverter();
        final boolean[] results = (boolean[]) converter.convert(null, values);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(results[6]);
    }

    public void testStandardStringArrayConversion_10_oe() {
        final String[] values = {
            "true", "false",
            "yes", "no",
            "y", "n",
            "1", "0",
        };

        final BooleanArrayConverter converter = new BooleanArrayConverter();
        final boolean[] results = (boolean[]) converter.convert(null, values);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(results[7]);
    }

    public void testStandardStringConversion_1_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

        assertNotNull(results);
    }

    public void testStandardStringConversion_2_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        assertEquals(8, results.length);
    }

    public void testStandardStringConversion_3_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        // removed other assertion
        assertTrue(results[0]);
    }

    public void testStandardStringConversion_4_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(results[1]);
    }

    public void testStandardStringConversion_5_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(results[2]);
    }

    public void testStandardStringConversion_6_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(results[3]);
    }

    public void testStandardStringConversion_7_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(results[4]);
    }

    public void testStandardStringConversion_8_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(results[5]);
    }

    public void testStandardStringConversion_9_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(results[6]);
    }

    public void testStandardStringConversion_10_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(results[7]);
    }

    public void testStandardStringConversion_11_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

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

        // string 
        input.setLength(0);
        input.append("'falsE', 'no', 'N', 0, \"truE\", yeS, 'y', '1'");
        results = (boolean[]) converter.convert(null, input);

        assertNotNull(results);
    }

    public void testStandardStringConversion_12_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

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

        // string 
        input.setLength(0);
        input.append("'falsE', 'no', 'N', 0, \"truE\", yeS, 'y', '1'");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        assertEquals(8, results.length);
    }

    public void testStandardStringConversion_13_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

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

        // string 
        input.setLength(0);
        input.append("'falsE', 'no', 'N', 0, \"truE\", yeS, 'y', '1'");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        // removed other assertion
        assertFalse(results[0]);
    }

    public void testStandardStringConversion_14_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

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

        // string 
        input.setLength(0);
        input.append("'falsE', 'no', 'N', 0, \"truE\", yeS, 'y', '1'");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(results[1]);
    }

    public void testStandardStringConversion_15_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

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

        // string 
        input.setLength(0);
        input.append("'falsE', 'no', 'N', 0, \"truE\", yeS, 'y', '1'");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(results[2]);
    }

    public void testStandardStringConversion_16_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

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

        // string 
        input.setLength(0);
        input.append("'falsE', 'no', 'N', 0, \"truE\", yeS, 'y', '1'");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(results[3]);
    }

    public void testStandardStringConversion_17_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

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

        // string 
        input.setLength(0);
        input.append("'falsE', 'no', 'N', 0, \"truE\", yeS, 'y', '1'");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(results[4]);
    }

    public void testStandardStringConversion_18_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

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

        // string 
        input.setLength(0);
        input.append("'falsE', 'no', 'N', 0, \"truE\", yeS, 'y', '1'");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(results[5]);
    }

    public void testStandardStringConversion_19_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

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

        // string 
        input.setLength(0);
        input.append("'falsE', 'no', 'N', 0, \"truE\", yeS, 'y', '1'");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(results[6]);
    }

    public void testStandardStringConversion_20_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

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

        // string 
        input.setLength(0);
        input.append("'falsE', 'no', 'N', 0, \"truE\", yeS, 'y', '1'");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(results[7]);
    }

    public void testStandardStringConversion_21_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

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

        // string 
        input.setLength(0);
        input.append("'falsE', 'no', 'N', 0, \"truE\", yeS, 'y', '1'");
        results = (boolean[]) converter.convert(null, input);

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

        // string has only one element, non-quoted
        input.setLength(0);
        input.append("y");
        results = (boolean[]) converter.convert(null, input);

        assertNotNull(results);
    }

    public void testStandardStringConversion_22_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

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

        // string 
        input.setLength(0);
        input.append("'falsE', 'no', 'N', 0, \"truE\", yeS, 'y', '1'");
        results = (boolean[]) converter.convert(null, input);

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

        // string has only one element, non-quoted
        input.setLength(0);
        input.append("y");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        assertEquals(1, results.length);
    }

    public void testStandardStringConversion_23_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

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

        // string 
        input.setLength(0);
        input.append("'falsE', 'no', 'N', 0, \"truE\", yeS, 'y', '1'");
        results = (boolean[]) converter.convert(null, input);

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

        // string has only one element, non-quoted
        input.setLength(0);
        input.append("y");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        // removed other assertion
        assertTrue(results[0]);
    }

    public void testStandardStringConversion_24_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

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

        // string 
        input.setLength(0);
        input.append("'falsE', 'no', 'N', 0, \"truE\", yeS, 'y', '1'");
        results = (boolean[]) converter.convert(null, input);

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

        // string has only one element, non-quoted
        input.setLength(0);
        input.append("y");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // string has only one element, quoted with ".
        input.setLength(0);
        input.append("\"1\"");
        results = (boolean[]) converter.convert(null, input);

        assertNotNull(results);
    }

    public void testStandardStringConversion_25_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

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

        // string 
        input.setLength(0);
        input.append("'falsE', 'no', 'N', 0, \"truE\", yeS, 'y', '1'");
        results = (boolean[]) converter.convert(null, input);

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

        // string has only one element, non-quoted
        input.setLength(0);
        input.append("y");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // string has only one element, quoted with ".
        input.setLength(0);
        input.append("\"1\"");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        assertEquals(1, results.length);
    }

    public void testStandardStringConversion_26_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

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

        // string 
        input.setLength(0);
        input.append("'falsE', 'no', 'N', 0, \"truE\", yeS, 'y', '1'");
        results = (boolean[]) converter.convert(null, input);

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

        // string has only one element, non-quoted
        input.setLength(0);
        input.append("y");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // string has only one element, quoted with ".
        input.setLength(0);
        input.append("\"1\"");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        // removed other assertion
        assertTrue(results[0]);
    }

    public void testStandardStringConversion_27_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

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

        // string 
        input.setLength(0);
        input.append("'falsE', 'no', 'N', 0, \"truE\", yeS, 'y', '1'");
        results = (boolean[]) converter.convert(null, input);

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

        // string has only one element, non-quoted
        input.setLength(0);
        input.append("y");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // string has only one element, quoted with ".
        input.setLength(0);
        input.append("\"1\"");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // string has only one element, quoted with '
        // Here we also pass an object of type String rather than the
        // StringBuilder
        results = (boolean[]) converter.convert(null, "'yes'");

        assertNotNull(results);
    }

    public void testStandardStringConversion_28_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

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

        // string 
        input.setLength(0);
        input.append("'falsE', 'no', 'N', 0, \"truE\", yeS, 'y', '1'");
        results = (boolean[]) converter.convert(null, input);

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

        // string has only one element, non-quoted
        input.setLength(0);
        input.append("y");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // string has only one element, quoted with ".
        input.setLength(0);
        input.append("\"1\"");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // string has only one element, quoted with '
        // Here we also pass an object of type String rather than the
        // StringBuilder
        results = (boolean[]) converter.convert(null, "'yes'");

        // removed other assertion
        assertEquals(1, results.length);
    }

    public void testStandardStringConversion_29_oe() {
        final BooleanArrayConverter converter = new BooleanArrayConverter();

        final StringBuilder input = new StringBuilder();
        boolean[] results;

        // string has {}
        input.setLength(0);
        input.append("{true, 'yes', Y, 1, 'FALSE', \"no\", 'n', 0}");
        results = (boolean[]) converter.convert(null, input);

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

        // string 
        input.setLength(0);
        input.append("'falsE', 'no', 'N', 0, \"truE\", yeS, 'y', '1'");
        results = (boolean[]) converter.convert(null, input);

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

        // string has only one element, non-quoted
        input.setLength(0);
        input.append("y");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // string has only one element, quoted with ".
        input.setLength(0);
        input.append("\"1\"");
        results = (boolean[]) converter.convert(null, input);

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // string has only one element, quoted with '
        // Here we also pass an object of type String rather than the
        // StringBuilder
        results = (boolean[]) converter.convert(null, "'yes'");

        // removed other assertion
        // removed other assertion
        assertTrue(results[0]);
    }

    public void testAdditionalStrings_1_oe() {
        final String[] trueStrings = {"sure"};
        final String[] falseStrings = {"nope"};
        final BooleanConverter bc = new BooleanConverter(
            trueStrings, falseStrings, BooleanConverter.NO_DEFAULT);
        final BooleanArrayConverter converter = new BooleanArrayConverter(
            bc, BooleanArrayConverter.NO_DEFAULT);

        final boolean[] results = (boolean[]) converter.convert(null, "NOPE, sure, sure");
        assertNotNull(results);
    }

    public void testAdditionalStrings_2_oe() {
        final String[] trueStrings = {"sure"};
        final String[] falseStrings = {"nope"};
        final BooleanConverter bc = new BooleanConverter(
            trueStrings, falseStrings, BooleanConverter.NO_DEFAULT);
        final BooleanArrayConverter converter = new BooleanArrayConverter(
            bc, BooleanArrayConverter.NO_DEFAULT);

        final boolean[] results = (boolean[]) converter.convert(null, "NOPE, sure, sure");
        // removed other assertion
        assertEquals(3, results.length);
    }

    public void testAdditionalStrings_3_oe() {
        final String[] trueStrings = {"sure"};
        final String[] falseStrings = {"nope"};
        final BooleanConverter bc = new BooleanConverter(
            trueStrings, falseStrings, BooleanConverter.NO_DEFAULT);
        final BooleanArrayConverter converter = new BooleanArrayConverter(
            bc, BooleanArrayConverter.NO_DEFAULT);

        final boolean[] results = (boolean[]) converter.convert(null, "NOPE, sure, sure");
        // removed other assertion
        // removed other assertion
        assertFalse(results[0]);
    }

    public void testAdditionalStrings_4_oe() {
        final String[] trueStrings = {"sure"};
        final String[] falseStrings = {"nope"};
        final BooleanConverter bc = new BooleanConverter(
            trueStrings, falseStrings, BooleanConverter.NO_DEFAULT);
        final BooleanArrayConverter converter = new BooleanArrayConverter(
            bc, BooleanArrayConverter.NO_DEFAULT);

        final boolean[] results = (boolean[]) converter.convert(null, "NOPE, sure, sure");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(results[1]);
    }

    public void testAdditionalStrings_5_oe() {
        final String[] trueStrings = {"sure"};
        final String[] falseStrings = {"nope"};
        final BooleanConverter bc = new BooleanConverter(
            trueStrings, falseStrings, BooleanConverter.NO_DEFAULT);
        final BooleanArrayConverter converter = new BooleanArrayConverter(
            bc, BooleanArrayConverter.NO_DEFAULT);

        final boolean[] results = (boolean[]) converter.convert(null, "NOPE, sure, sure");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(results[2]);
    }

    public void testInvalidStringWithDefault_1_oe() {
        final boolean[] defaults = new boolean[1];
        final BooleanArrayConverter converter = new BooleanArrayConverter(defaults);
        final Object o = converter.convert(null, "true!");
        assertSame("Unexpected object returned for failed conversion", o, defaults);
    }

    public void testInvalidElementWithDefault_1_oe() {
        final boolean[] defaults = new boolean[1];
        final BooleanArrayConverter converter = new BooleanArrayConverter(defaults);
        final Object o = converter.convert(null, "true,bogus");
        assertSame("Unexpected object returned for failed conversion", o, defaults);
    }

    public void testElementDefault_1_oe() {
        final boolean[] defaults = new boolean[1];
        final BooleanConverter bc = new BooleanConverter(Boolean.TRUE);
        final BooleanArrayConverter converter = new BooleanArrayConverter(bc, defaults);
        final boolean[] results = (boolean[]) converter.convert(null, "true,bogus");

        assertEquals(2, results.length);
    }

    public void testElementDefault_2_oe() {
        final boolean[] defaults = new boolean[1];
        final BooleanConverter bc = new BooleanConverter(Boolean.TRUE);
        final BooleanArrayConverter converter = new BooleanArrayConverter(bc, defaults);
        final boolean[] results = (boolean[]) converter.convert(null, "true,bogus");

        // removed other assertion
        assertTrue(results[0]);
    }

    public void testElementDefault_3_oe() {
        final boolean[] defaults = new boolean[1];
        final BooleanConverter bc = new BooleanConverter(Boolean.TRUE);
        final BooleanArrayConverter converter = new BooleanArrayConverter(bc, defaults);
        final boolean[] results = (boolean[]) converter.convert(null, "true,bogus");

        // removed other assertion
        // removed other assertion
        assertTrue(results[1]);
    }

    public void testRegistration_1_oe() {
        final String[] trueStrings = {"sure"};
        final String[] falseStrings = {"nope"};
        final BooleanConverter bc = new BooleanConverter(
            trueStrings, falseStrings, BooleanConverter.NO_DEFAULT);

        final BooleanArrayConverter converter = new BooleanArrayConverter(
            bc, BooleanArrayConverter.NO_DEFAULT);

        ConvertUtils.register(converter, BooleanArrayConverter.MODEL);
        final boolean[] sample = new boolean[0];
        final boolean[] results = (boolean[]) ConvertUtils.convert("sure,nope", sample.getClass());

        assertEquals(2, results.length);
    }

    public void testRegistration_2_oe() {
        final String[] trueStrings = {"sure"};
        final String[] falseStrings = {"nope"};
        final BooleanConverter bc = new BooleanConverter(
            trueStrings, falseStrings, BooleanConverter.NO_DEFAULT);

        final BooleanArrayConverter converter = new BooleanArrayConverter(
            bc, BooleanArrayConverter.NO_DEFAULT);

        ConvertUtils.register(converter, BooleanArrayConverter.MODEL);
        final boolean[] sample = new boolean[0];
        final boolean[] results = (boolean[]) ConvertUtils.convert("sure,nope", sample.getClass());

        // removed other assertion
        assertTrue(results[0]);
    }

    public void testRegistration_3_oe() {
        final String[] trueStrings = {"sure"};
        final String[] falseStrings = {"nope"};
        final BooleanConverter bc = new BooleanConverter(
            trueStrings, falseStrings, BooleanConverter.NO_DEFAULT);

        final BooleanArrayConverter converter = new BooleanArrayConverter(
            bc, BooleanArrayConverter.NO_DEFAULT);

        ConvertUtils.register(converter, BooleanArrayConverter.MODEL);
        final boolean[] sample = new boolean[0];
        final boolean[] results = (boolean[]) ConvertUtils.convert("sure,nope", sample.getClass());

        // removed other assertion
        // removed other assertion
        assertFalse(results[1]);
    }

}
