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
package org.apache.commons.validator;

import junit.framework.TestCase;

/**
 * ISBNValidator Test Case.
 *
 * @version $Revision$
 * @deprecated to be removed when the org.apache.commons.validator.ISBNValidator class is removed
 */
@Deprecated
public class ISBNValidatorTest_OE25Dev extends TestCase {

    private static final String VALID_ISBN_RAW = "1930110995";
    private static final String VALID_ISBN_DASHES = "1-930110-99-5";
    private static final String VALID_ISBN_SPACES = "1 930110 99 5";
    private static final String VALID_ISBN_X = "0-201-63385-X";
    private static final String INVALID_ISBN = "068-556-98-45";

    public ISBNValidatorTest_OE25Dev(String name) {
        super(name);
    }

public void testIsValid_1_oe() throws Exception {
        ISBNValidator validator = new ISBNValidator();
        assertFalse(validator.isValid(null));
    }

public void testIsValid_2_oe() throws Exception {
        ISBNValidator validator = new ISBNValidator();
        // removed other assertion
        assertFalse(validator.isValid(""));
    }

public void testIsValid_3_oe() throws Exception {
        ISBNValidator validator = new ISBNValidator();
        // removed other assertion
        // removed other assertion
        assertFalse(validator.isValid("1"));
    }

public void testIsValid_4_oe() throws Exception {
        ISBNValidator validator = new ISBNValidator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(validator.isValid("12345678901234"));
    }

public void testIsValid_5_oe() throws Exception {
        ISBNValidator validator = new ISBNValidator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(validator.isValid("dsasdsadsads"));
    }

public void testIsValid_6_oe() throws Exception {
        ISBNValidator validator = new ISBNValidator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(validator.isValid("535365"));
    }

public void testIsValid_7_oe() throws Exception {
        ISBNValidator validator = new ISBNValidator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(validator.isValid("I love sparrows!"));
    }

public void testIsValid_8_oe() throws Exception {
        ISBNValidator validator = new ISBNValidator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(validator.isValid("--1 930110 99 5"));
    }

public void testIsValid_9_oe() throws Exception {
        ISBNValidator validator = new ISBNValidator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(validator.isValid("1 930110 99 5--"));
    }

public void testIsValid_10_oe() throws Exception {
        ISBNValidator validator = new ISBNValidator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(validator.isValid("1 930110-99 5-"));
    }

public void testIsValid_11_oe() throws Exception {
        ISBNValidator validator = new ISBNValidator();
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

        assertTrue(validator.isValid(VALID_ISBN_RAW));
    }

public void testIsValid_12_oe() throws Exception {
        ISBNValidator validator = new ISBNValidator();
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
        assertTrue(validator.isValid(VALID_ISBN_DASHES));
    }

public void testIsValid_13_oe() throws Exception {
        ISBNValidator validator = new ISBNValidator();
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
        assertTrue(validator.isValid(VALID_ISBN_SPACES));
    }

public void testIsValid_14_oe() throws Exception {
        ISBNValidator validator = new ISBNValidator();
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
        assertTrue(validator.isValid(VALID_ISBN_X));
    }

public void testIsValid_15_oe() throws Exception {
        ISBNValidator validator = new ISBNValidator();
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
        assertFalse(validator.isValid(INVALID_ISBN));
    }

}
