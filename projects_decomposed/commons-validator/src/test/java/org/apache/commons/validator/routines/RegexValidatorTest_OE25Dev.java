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
package org.apache.commons.validator.routines;

import java.util.regex.PatternSyntaxException;

import junit.framework.TestCase;

/**
 * Test Case for RegexValidatorTest_OE25Dev.
 *
 * @version $Revision$
 * @since Validator 1.4
 */
public class RegexValidatorTest_OE25Dev extends TestCase {

    private static final String REGEX         = "^([abc]*)(?:\\-)([DEF]*)(?:\\-)([123]*)$";

    private static final String COMPONENT_1 = "([abc]{3})";
    private static final String COMPONENT_2 = "([DEF]{3})";
    private static final String COMPONENT_3 = "([123]{3})";
    private static final String SEPARATOR_1  = "(?:\\-)";
    private static final String SEPARATOR_2  = "(?:\\s)";
    private static final String REGEX_1 = "^" + COMPONENT_1 + SEPARATOR_1 + COMPONENT_2 + SEPARATOR_1 + COMPONENT_3 + "$";
    private static final String REGEX_2 = "^" + COMPONENT_1 + SEPARATOR_2 + COMPONENT_2 + SEPARATOR_2 + COMPONENT_3 + "$";
    private static final String REGEX_3 = "^" + COMPONENT_1 + COMPONENT_2 + COMPONENT_3 + "$";
    private static final String[] MULTIPLE_REGEX = new String[] {REGEX_1, REGEX_2, REGEX_3};

    /**
     * Constrct a new test case.
     * @param name The name of the test
     */
    public RegexValidatorTest_OE25Dev(String name) {
        super(name);
    }

    /**
     * Set Up.
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Tear Down.
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test instance methods with single regular expression.
     */

    /**
     * Test with multiple regular expressions (case sensitive).
     */

    /**
     * Test with multiple regular expressions (case in-sensitive).
     */

    /**
     * Test Null value
     */

    /**
     * Test exceptions
     */

    /**
     * Test exceptions
     */
    public void testExceptions() {
        String invalidRegex = "^([abCD12]*$";
        try {
            new RegexValidator(invalidRegex);
        } catch (PatternSyntaxException e) {
            // expected
        }
    }

    /**
     * Test toString() method
     */

    /**
     * Compare two arrays
     * @param label Label for the test
     * @param expect Expected array
     * @param result Actual array
     */
    private void checkArray(String label, String[] expect, String[] result) {

        // Handle nulls
        if (expect == null || result == null) {
            if (expect == null && result == null) {
                return; // valid, both null
            } else {
                fail(label + " Null expect=" + expect + " result=" + result);
            }
            return; // not strictly necessary, but prevents possible NPE below
        }

        // Check Length
        if (expect.length != result.length) {
            fail(label + " Length expect=" + expect.length + " result=" + result.length);
        }

        // Check Values
        for (int i = 0; i < expect.length; i++) {
            assertEquals(label +" value[" + i + "]", expect[i], result[i]);
        }
    }

public void testSingle_1_oe() {
        RegexValidator sensitive   = new RegexValidator(REGEX);
        RegexValidator insensitive = new RegexValidator(REGEX, false);

        // isValid()
        assertEquals("Sensitive isValid() valid",     true,   sensitive.isValid("ac-DE-1"));
    }

public void testSingle_2_oe() {
        RegexValidator sensitive   = new RegexValidator(REGEX);
        RegexValidator insensitive = new RegexValidator(REGEX, false);

        // isValid()
        // removed other assertion
        assertEquals("Sensitive isValid() invalid",   false,  sensitive.isValid("AB-de-1"));
    }

public void testSingle_3_oe() {
        RegexValidator sensitive   = new RegexValidator(REGEX);
        RegexValidator insensitive = new RegexValidator(REGEX, false);

        // isValid()
        // removed other assertion
        // removed other assertion
        assertEquals("Insensitive isValid() valid",   true,   insensitive.isValid("AB-de-1"));
    }

public void testSingle_4_oe() {
        RegexValidator sensitive   = new RegexValidator(REGEX);
        RegexValidator insensitive = new RegexValidator(REGEX, false);

        // isValid()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Insensitive isValid() invalid", false,  insensitive.isValid("ABd-de-1"));
    }

public void testSingle_5_oe() {
        RegexValidator sensitive   = new RegexValidator(REGEX);
        RegexValidator insensitive = new RegexValidator(REGEX, false);

        // isValid()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // validate()
        assertEquals("Sensitive validate() valid",     "acDE1", sensitive.validate("ac-DE-1"));
    }

public void testSingle_6_oe() {
        RegexValidator sensitive   = new RegexValidator(REGEX);
        RegexValidator insensitive = new RegexValidator(REGEX, false);

        // isValid()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // validate()
        // removed other assertion
        assertEquals("Sensitive validate() invalid",   null,    sensitive.validate("AB-de-1"));
    }

public void testSingle_7_oe() {
        RegexValidator sensitive   = new RegexValidator(REGEX);
        RegexValidator insensitive = new RegexValidator(REGEX, false);

        // isValid()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // validate()
        // removed other assertion
        // removed other assertion
        assertEquals("Insensitive validate() valid",   "ABde1", insensitive.validate("AB-de-1"));
    }

public void testSingle_8_oe() {
        RegexValidator sensitive   = new RegexValidator(REGEX);
        RegexValidator insensitive = new RegexValidator(REGEX, false);

        // isValid()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // validate()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Insensitive validate() invalid", null,    insensitive.validate("ABd-de-1"));
    }

public void testSingle_9_oe() {
        RegexValidator sensitive   = new RegexValidator(REGEX);
        RegexValidator insensitive = new RegexValidator(REGEX, false);

        // isValid()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // validate()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // match()
        checkArray("Sensitive match() valid",     new String[] {"ac", "DE", "1"}, sensitive.match("ac-DE-1"));
        checkArray("Sensitive match() invalid",   null,                           sensitive.match("AB-de-1"));
        checkArray("Insensitive match() valid",   new String[] {"AB", "de", "1"}, insensitive.match("AB-de-1"));
        checkArray("Insensitive match() invalid", null,                           insensitive.match("ABd-de-1"));
        assertEquals("validate one", "ABC", (new RegexValidator("^([A-Z]*)$")).validate("ABC"));
    }

public void testMultipleSensitive_1_oe() {

        // ------------ Set up Sensitive Validators
        RegexValidator multiple   = new RegexValidator(MULTIPLE_REGEX);
        RegexValidator single1   = new RegexValidator(REGEX_1);
        RegexValidator single2   = new RegexValidator(REGEX_2);
        RegexValidator single3   = new RegexValidator(REGEX_3);

        // ------------ Set up test values
        String value = "aac FDE 321";
        String expect = "aacFDE321";
        String[] array = new String[] {"aac", "FDE", "321"};

        // isValid()
        assertEquals("Sensitive isValid() Multiple", true,  multiple.isValid(value));
    }

public void testMultipleSensitive_2_oe() {

        // ------------ Set up Sensitive Validators
        RegexValidator multiple   = new RegexValidator(MULTIPLE_REGEX);
        RegexValidator single1   = new RegexValidator(REGEX_1);
        RegexValidator single2   = new RegexValidator(REGEX_2);
        RegexValidator single3   = new RegexValidator(REGEX_3);

        // ------------ Set up test values
        String value = "aac FDE 321";
        String expect = "aacFDE321";
        String[] array = new String[] {"aac", "FDE", "321"};

        // isValid()
        // removed other assertion
        assertEquals("Sensitive isValid() 1st",      false, single1.isValid(value));
    }

public void testMultipleSensitive_3_oe() {

        // ------------ Set up Sensitive Validators
        RegexValidator multiple   = new RegexValidator(MULTIPLE_REGEX);
        RegexValidator single1   = new RegexValidator(REGEX_1);
        RegexValidator single2   = new RegexValidator(REGEX_2);
        RegexValidator single3   = new RegexValidator(REGEX_3);

        // ------------ Set up test values
        String value = "aac FDE 321";
        String expect = "aacFDE321";
        String[] array = new String[] {"aac", "FDE", "321"};

        // isValid()
        // removed other assertion
        // removed other assertion
        assertEquals("Sensitive isValid() 2nd",      true,  single2.isValid(value));
    }

public void testMultipleSensitive_4_oe() {

        // ------------ Set up Sensitive Validators
        RegexValidator multiple   = new RegexValidator(MULTIPLE_REGEX);
        RegexValidator single1   = new RegexValidator(REGEX_1);
        RegexValidator single2   = new RegexValidator(REGEX_2);
        RegexValidator single3   = new RegexValidator(REGEX_3);

        // ------------ Set up test values
        String value = "aac FDE 321";
        String expect = "aacFDE321";
        String[] array = new String[] {"aac", "FDE", "321"};

        // isValid()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Sensitive isValid() 3rd",      false, single3.isValid(value));
    }

public void testMultipleSensitive_5_oe() {

        // ------------ Set up Sensitive Validators
        RegexValidator multiple   = new RegexValidator(MULTIPLE_REGEX);
        RegexValidator single1   = new RegexValidator(REGEX_1);
        RegexValidator single2   = new RegexValidator(REGEX_2);
        RegexValidator single3   = new RegexValidator(REGEX_3);

        // ------------ Set up test values
        String value = "aac FDE 321";
        String expect = "aacFDE321";
        String[] array = new String[] {"aac", "FDE", "321"};

        // isValid()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // validate()
        assertEquals("Sensitive validate() Multiple", expect, multiple.validate(value));
    }

public void testMultipleSensitive_6_oe() {

        // ------------ Set up Sensitive Validators
        RegexValidator multiple   = new RegexValidator(MULTIPLE_REGEX);
        RegexValidator single1   = new RegexValidator(REGEX_1);
        RegexValidator single2   = new RegexValidator(REGEX_2);
        RegexValidator single3   = new RegexValidator(REGEX_3);

        // ------------ Set up test values
        String value = "aac FDE 321";
        String expect = "aacFDE321";
        String[] array = new String[] {"aac", "FDE", "321"};

        // isValid()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // validate()
        // removed other assertion
        assertEquals("Sensitive validate() 1st",      null,   single1.validate(value));
    }

public void testMultipleSensitive_7_oe() {

        // ------------ Set up Sensitive Validators
        RegexValidator multiple   = new RegexValidator(MULTIPLE_REGEX);
        RegexValidator single1   = new RegexValidator(REGEX_1);
        RegexValidator single2   = new RegexValidator(REGEX_2);
        RegexValidator single3   = new RegexValidator(REGEX_3);

        // ------------ Set up test values
        String value = "aac FDE 321";
        String expect = "aacFDE321";
        String[] array = new String[] {"aac", "FDE", "321"};

        // isValid()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // validate()
        // removed other assertion
        // removed other assertion
        assertEquals("Sensitive validate() 2nd",      expect, single2.validate(value));
    }

public void testMultipleSensitive_8_oe() {

        // ------------ Set up Sensitive Validators
        RegexValidator multiple   = new RegexValidator(MULTIPLE_REGEX);
        RegexValidator single1   = new RegexValidator(REGEX_1);
        RegexValidator single2   = new RegexValidator(REGEX_2);
        RegexValidator single3   = new RegexValidator(REGEX_3);

        // ------------ Set up test values
        String value = "aac FDE 321";
        String expect = "aacFDE321";
        String[] array = new String[] {"aac", "FDE", "321"};

        // isValid()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // validate()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Sensitive validate() 3rd",      null,   single3.validate(value));
    }

public void testMultipleSensitive_9_oe() {

        // ------------ Set up Sensitive Validators
        RegexValidator multiple   = new RegexValidator(MULTIPLE_REGEX);
        RegexValidator single1   = new RegexValidator(REGEX_1);
        RegexValidator single2   = new RegexValidator(REGEX_2);
        RegexValidator single3   = new RegexValidator(REGEX_3);

        // ------------ Set up test values
        String value = "aac FDE 321";
        String expect = "aacFDE321";
        String[] array = new String[] {"aac", "FDE", "321"};

        // isValid()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // validate()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // match()
        checkArray("Sensitive match() Multiple", array, multiple.match(value));
        checkArray("Sensitive match() 1st",      null,  single1.match(value));
        checkArray("Sensitive match() 2nd",      array, single2.match(value));
        checkArray("Sensitive match() 3rd",      null,  single3.match(value));

        // All invalid
        value = "AAC*FDE*321";
        assertEquals("isValid() Invalid",  false, multiple.isValid(value));
    }

public void testMultipleSensitive_10_oe() {

        // ------------ Set up Sensitive Validators
        RegexValidator multiple   = new RegexValidator(MULTIPLE_REGEX);
        RegexValidator single1   = new RegexValidator(REGEX_1);
        RegexValidator single2   = new RegexValidator(REGEX_2);
        RegexValidator single3   = new RegexValidator(REGEX_3);

        // ------------ Set up test values
        String value = "aac FDE 321";
        String expect = "aacFDE321";
        String[] array = new String[] {"aac", "FDE", "321"};

        // isValid()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // validate()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // match()
        checkArray("Sensitive match() Multiple", array, multiple.match(value));
        checkArray("Sensitive match() 1st",      null,  single1.match(value));
        checkArray("Sensitive match() 2nd",      array, single2.match(value));
        checkArray("Sensitive match() 3rd",      null,  single3.match(value));

        // All invalid
        value = "AAC*FDE*321";
        // removed other assertion
        assertEquals("validate() Invalid", null,  multiple.validate(value));
    }

public void testMultipleSensitive_11_oe() {

        // ------------ Set up Sensitive Validators
        RegexValidator multiple   = new RegexValidator(MULTIPLE_REGEX);
        RegexValidator single1   = new RegexValidator(REGEX_1);
        RegexValidator single2   = new RegexValidator(REGEX_2);
        RegexValidator single3   = new RegexValidator(REGEX_3);

        // ------------ Set up test values
        String value = "aac FDE 321";
        String expect = "aacFDE321";
        String[] array = new String[] {"aac", "FDE", "321"};

        // isValid()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // validate()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // match()
        checkArray("Sensitive match() Multiple", array, multiple.match(value));
        checkArray("Sensitive match() 1st",      null,  single1.match(value));
        checkArray("Sensitive match() 2nd",      array, single2.match(value));
        checkArray("Sensitive match() 3rd",      null,  single3.match(value));

        // All invalid
        value = "AAC*FDE*321";
        // removed other assertion
        // removed other assertion
        assertEquals("match() Multiple",   null,  multiple.match(value));
    }

public void testMultipleInsensitive_1_oe() {

        // ------------ Set up In-sensitive Validators
        RegexValidator multiple = new RegexValidator(MULTIPLE_REGEX, false);
        RegexValidator single1   = new RegexValidator(REGEX_1, false);
        RegexValidator single2   = new RegexValidator(REGEX_2, false);
        RegexValidator single3   = new RegexValidator(REGEX_3, false);

        // ------------ Set up test values
        String value = "AAC FDE 321";
        String expect = "AACFDE321";
        String[] array = new String[] {"AAC", "FDE", "321"};

        // isValid()
        assertEquals("isValid() Multiple", true,  multiple.isValid(value));
    }

public void testMultipleInsensitive_2_oe() {

        // ------------ Set up In-sensitive Validators
        RegexValidator multiple = new RegexValidator(MULTIPLE_REGEX, false);
        RegexValidator single1   = new RegexValidator(REGEX_1, false);
        RegexValidator single2   = new RegexValidator(REGEX_2, false);
        RegexValidator single3   = new RegexValidator(REGEX_3, false);

        // ------------ Set up test values
        String value = "AAC FDE 321";
        String expect = "AACFDE321";
        String[] array = new String[] {"AAC", "FDE", "321"};

        // isValid()
        // removed other assertion
        assertEquals("isValid() 1st",      false, single1.isValid(value));
    }

public void testMultipleInsensitive_3_oe() {

        // ------------ Set up In-sensitive Validators
        RegexValidator multiple = new RegexValidator(MULTIPLE_REGEX, false);
        RegexValidator single1   = new RegexValidator(REGEX_1, false);
        RegexValidator single2   = new RegexValidator(REGEX_2, false);
        RegexValidator single3   = new RegexValidator(REGEX_3, false);

        // ------------ Set up test values
        String value = "AAC FDE 321";
        String expect = "AACFDE321";
        String[] array = new String[] {"AAC", "FDE", "321"};

        // isValid()
        // removed other assertion
        // removed other assertion
        assertEquals("isValid() 2nd",      true,  single2.isValid(value));
    }

public void testMultipleInsensitive_4_oe() {

        // ------------ Set up In-sensitive Validators
        RegexValidator multiple = new RegexValidator(MULTIPLE_REGEX, false);
        RegexValidator single1   = new RegexValidator(REGEX_1, false);
        RegexValidator single2   = new RegexValidator(REGEX_2, false);
        RegexValidator single3   = new RegexValidator(REGEX_3, false);

        // ------------ Set up test values
        String value = "AAC FDE 321";
        String expect = "AACFDE321";
        String[] array = new String[] {"AAC", "FDE", "321"};

        // isValid()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("isValid() 3rd",      false, single3.isValid(value));
    }

public void testMultipleInsensitive_5_oe() {

        // ------------ Set up In-sensitive Validators
        RegexValidator multiple = new RegexValidator(MULTIPLE_REGEX, false);
        RegexValidator single1   = new RegexValidator(REGEX_1, false);
        RegexValidator single2   = new RegexValidator(REGEX_2, false);
        RegexValidator single3   = new RegexValidator(REGEX_3, false);

        // ------------ Set up test values
        String value = "AAC FDE 321";
        String expect = "AACFDE321";
        String[] array = new String[] {"AAC", "FDE", "321"};

        // isValid()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // validate()
        assertEquals("validate() Multiple", expect, multiple.validate(value));
    }

public void testMultipleInsensitive_6_oe() {

        // ------------ Set up In-sensitive Validators
        RegexValidator multiple = new RegexValidator(MULTIPLE_REGEX, false);
        RegexValidator single1   = new RegexValidator(REGEX_1, false);
        RegexValidator single2   = new RegexValidator(REGEX_2, false);
        RegexValidator single3   = new RegexValidator(REGEX_3, false);

        // ------------ Set up test values
        String value = "AAC FDE 321";
        String expect = "AACFDE321";
        String[] array = new String[] {"AAC", "FDE", "321"};

        // isValid()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // validate()
        // removed other assertion
        assertEquals("validate() 1st",      null,   single1.validate(value));
    }

public void testMultipleInsensitive_7_oe() {

        // ------------ Set up In-sensitive Validators
        RegexValidator multiple = new RegexValidator(MULTIPLE_REGEX, false);
        RegexValidator single1   = new RegexValidator(REGEX_1, false);
        RegexValidator single2   = new RegexValidator(REGEX_2, false);
        RegexValidator single3   = new RegexValidator(REGEX_3, false);

        // ------------ Set up test values
        String value = "AAC FDE 321";
        String expect = "AACFDE321";
        String[] array = new String[] {"AAC", "FDE", "321"};

        // isValid()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // validate()
        // removed other assertion
        // removed other assertion
        assertEquals("validate() 2nd",      expect, single2.validate(value));
    }

public void testMultipleInsensitive_8_oe() {

        // ------------ Set up In-sensitive Validators
        RegexValidator multiple = new RegexValidator(MULTIPLE_REGEX, false);
        RegexValidator single1   = new RegexValidator(REGEX_1, false);
        RegexValidator single2   = new RegexValidator(REGEX_2, false);
        RegexValidator single3   = new RegexValidator(REGEX_3, false);

        // ------------ Set up test values
        String value = "AAC FDE 321";
        String expect = "AACFDE321";
        String[] array = new String[] {"AAC", "FDE", "321"};

        // isValid()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // validate()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("validate() 3rd",      null,   single3.validate(value));
    }

public void testMultipleInsensitive_9_oe() {

        // ------------ Set up In-sensitive Validators
        RegexValidator multiple = new RegexValidator(MULTIPLE_REGEX, false);
        RegexValidator single1   = new RegexValidator(REGEX_1, false);
        RegexValidator single2   = new RegexValidator(REGEX_2, false);
        RegexValidator single3   = new RegexValidator(REGEX_3, false);

        // ------------ Set up test values
        String value = "AAC FDE 321";
        String expect = "AACFDE321";
        String[] array = new String[] {"AAC", "FDE", "321"};

        // isValid()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // validate()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // match()
        checkArray("match() Multiple", array, multiple.match(value));
        checkArray("match() 1st",      null,  single1.match(value));
        checkArray("match() 2nd",      array, single2.match(value));
        checkArray("match() 3rd",      null,  single3.match(value));

        // All invalid
        value = "AAC*FDE*321";
        assertEquals("isValid() Invalid",  false, multiple.isValid(value));
    }

public void testMultipleInsensitive_10_oe() {

        // ------------ Set up In-sensitive Validators
        RegexValidator multiple = new RegexValidator(MULTIPLE_REGEX, false);
        RegexValidator single1   = new RegexValidator(REGEX_1, false);
        RegexValidator single2   = new RegexValidator(REGEX_2, false);
        RegexValidator single3   = new RegexValidator(REGEX_3, false);

        // ------------ Set up test values
        String value = "AAC FDE 321";
        String expect = "AACFDE321";
        String[] array = new String[] {"AAC", "FDE", "321"};

        // isValid()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // validate()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // match()
        checkArray("match() Multiple", array, multiple.match(value));
        checkArray("match() 1st",      null,  single1.match(value));
        checkArray("match() 2nd",      array, single2.match(value));
        checkArray("match() 3rd",      null,  single3.match(value));

        // All invalid
        value = "AAC*FDE*321";
        // removed other assertion
        assertEquals("validate() Invalid", null,  multiple.validate(value));
    }

public void testMultipleInsensitive_11_oe() {

        // ------------ Set up In-sensitive Validators
        RegexValidator multiple = new RegexValidator(MULTIPLE_REGEX, false);
        RegexValidator single1   = new RegexValidator(REGEX_1, false);
        RegexValidator single2   = new RegexValidator(REGEX_2, false);
        RegexValidator single3   = new RegexValidator(REGEX_3, false);

        // ------------ Set up test values
        String value = "AAC FDE 321";
        String expect = "AACFDE321";
        String[] array = new String[] {"AAC", "FDE", "321"};

        // isValid()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // validate()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // match()
        checkArray("match() Multiple", array, multiple.match(value));
        checkArray("match() 1st",      null,  single1.match(value));
        checkArray("match() 2nd",      array, single2.match(value));
        checkArray("match() 3rd",      null,  single3.match(value));

        // All invalid
        value = "AAC*FDE*321";
        // removed other assertion
        // removed other assertion
        assertEquals("match() Multiple",   null,  multiple.match(value));
    }

public void testNullValue_1_oe() {

        RegexValidator validator = new RegexValidator(REGEX);
        assertEquals("Instance isValid()",  false, validator.isValid(null));
    }

public void testNullValue_2_oe() {

        RegexValidator validator = new RegexValidator(REGEX);
        // removed other assertion
        assertEquals("Instance validate()", null,  validator.validate(null));
    }

public void testNullValue_3_oe() {

        RegexValidator validator = new RegexValidator(REGEX);
        // removed other assertion
        // removed other assertion
        assertEquals("Instance match()",    null,  validator.match(null));
    }

public void testMissingRegex_2_oe() {

        // Single Regular Expression - null
        try {
            new RegexValidator((String)null);
            // removed other assertion
        } catch (IllegalArgumentException e) {
            assertEquals("Single Null", "Regular expression[0] is missing", e.getMessage());
    }
    }

public void testMissingRegex_4_oe() {

        // Single Regular Expression - null
        try {
            new RegexValidator((String)null);
            // removed other assertion
        } catch (IllegalArgumentException e) {
            // removed other assertion
        }

        // Single Regular Expression - Zero Length
        try {
            new RegexValidator("");
            // removed other assertion
        } catch (IllegalArgumentException e) {
            assertEquals("Single Zero Length", "Regular expression[0] is missing", e.getMessage());
    }
    }

public void testMissingRegex_6_oe() {

        // Single Regular Expression - null
        try {
            new RegexValidator((String)null);
            // removed other assertion
        } catch (IllegalArgumentException e) {
            // removed other assertion
        }

        // Single Regular Expression - Zero Length
        try {
            new RegexValidator("");
            // removed other assertion
        } catch (IllegalArgumentException e) {
            // removed other assertion
        }

        // Multiple Regular Expression - Null array
        try {
            new RegexValidator((String[])null);
            // removed other assertion
        } catch (IllegalArgumentException e) {
            assertEquals("Null Array", "Regular expressions are missing", e.getMessage());
    }
    }

public void testMissingRegex_8_oe() {

        // Single Regular Expression - null
        try {
            new RegexValidator((String)null);
            // removed other assertion
        } catch (IllegalArgumentException e) {
            // removed other assertion
        }

        // Single Regular Expression - Zero Length
        try {
            new RegexValidator("");
            // removed other assertion
        } catch (IllegalArgumentException e) {
            // removed other assertion
        }

        // Multiple Regular Expression - Null array
        try {
            new RegexValidator((String[])null);
            // removed other assertion
        } catch (IllegalArgumentException e) {
            // removed other assertion
        }

        // Multiple Regular Expression - Zero Length array
        try {
            new RegexValidator(new String[0]);
            // removed other assertion
        } catch (IllegalArgumentException e) {
            assertEquals("Zero Length Array", "Regular expressions are missing", e.getMessage());
    }
    }

public void testMissingRegex_10_oe() {

        // Single Regular Expression - null
        try {
            new RegexValidator((String)null);
            // removed other assertion
        } catch (IllegalArgumentException e) {
            // removed other assertion
        }

        // Single Regular Expression - Zero Length
        try {
            new RegexValidator("");
            // removed other assertion
        } catch (IllegalArgumentException e) {
            // removed other assertion
        }

        // Multiple Regular Expression - Null array
        try {
            new RegexValidator((String[])null);
            // removed other assertion
        } catch (IllegalArgumentException e) {
            // removed other assertion
        }

        // Multiple Regular Expression - Zero Length array
        try {
            new RegexValidator(new String[0]);
            // removed other assertion
        } catch (IllegalArgumentException e) {
            // removed other assertion
        }

        // Multiple Regular Expression - Array has Null
        String[] expressions = new String[] {"ABC", null};
        try {
            new RegexValidator(expressions);
            // removed other assertion
        } catch (IllegalArgumentException e) {
            assertEquals("Array has Null", "Regular expression[1] is missing", e.getMessage());
    }
    }

public void testMissingRegex_12_oe() {

        // Single Regular Expression - null
        try {
            new RegexValidator((String)null);
            // removed other assertion
        } catch (IllegalArgumentException e) {
            // removed other assertion
        }

        // Single Regular Expression - Zero Length
        try {
            new RegexValidator("");
            // removed other assertion
        } catch (IllegalArgumentException e) {
            // removed other assertion
        }

        // Multiple Regular Expression - Null array
        try {
            new RegexValidator((String[])null);
            // removed other assertion
        } catch (IllegalArgumentException e) {
            // removed other assertion
        }

        // Multiple Regular Expression - Zero Length array
        try {
            new RegexValidator(new String[0]);
            // removed other assertion
        } catch (IllegalArgumentException e) {
            // removed other assertion
        }

        // Multiple Regular Expression - Array has Null
        String[] expressions = new String[] {"ABC", null};
        try {
            new RegexValidator(expressions);
            // removed other assertion
        } catch (IllegalArgumentException e) {
            // removed other assertion
        }

        // Multiple Regular Expression - Array has Zero Length
        expressions = new String[] {"", "ABC"};
        try {
            new RegexValidator(expressions);
            // removed other assertion
        } catch (IllegalArgumentException e) {
            assertEquals("Array has Zero Length", "Regular expression[0] is missing", e.getMessage());
    }
    }

public void testToString_1_oe() {
        RegexValidator single = new RegexValidator(REGEX);
        assertEquals("Single", "RegexValidator{" + REGEX + "}", single.toString());
    }

public void testToString_2_oe() {
        RegexValidator single = new RegexValidator(REGEX);
        // removed other assertion

        RegexValidator multiple = new RegexValidator(new String[] {REGEX, REGEX});
        assertEquals("Multiple", "RegexValidator{" + REGEX + "," + REGEX + "}", multiple.toString());
    }

}
