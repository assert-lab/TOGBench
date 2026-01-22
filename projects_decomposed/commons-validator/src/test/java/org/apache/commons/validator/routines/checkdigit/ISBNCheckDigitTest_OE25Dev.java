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
package org.apache.commons.validator.routines.checkdigit;


/**
 * ISBN-10/ISBN-13 Check Digit Test.
 *
 * @version $Revision$
 * @since Validator 1.4
 */
public class ISBNCheckDigitTest_OE25Dev extends AbstractCheckDigitTest {
    
    /**
     * Constructor
     * @param name test name
     */
    public ISBNCheckDigitTest_OE25Dev(String name) {
        super(name);
    }

    /**
     * Set up routine & valid codes.
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        routine = ISBNCheckDigit.ISBN_CHECK_DIGIT;
        valid = new String[] {
                "9780072129519",
                "9780764558313",
                "1930110995",
                "020163385X",
                "1590596277",    // ISBN-10 Ubuntu Book
                "9781590596272"  // ISBN-13 Ubuntu Book
                };
        missingMessage = "ISBN Code is missing";
        zeroSum = "000000000000";
    }

    /**
     * Set up routine & valid codes.
     */
    

    public void testInvalidLength_1_oe() {
        assertFalse("isValid() Lth 9 ", routine.isValid("123456789"));
    }

    public void testInvalidLength_2_oe() {
        // removed other assertion
        assertFalse("isValid() Lth 11", routine.isValid("12345678901"));
    }

    public void testInvalidLength_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse("isValid() Lth 12", routine.isValid("123456789012"));
    }

    public void testInvalidLength_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("isValid() Lth 14", routine.isValid("12345678901234"));
    }

    public void testInvalidLength_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            routine.calculate("12345678");
            // removed other assertion
        } catch (Exception e) {
            assertEquals("calculate() Lth 8", "Invalid ISBN Length = 8", e.getMessage());
    }
    }

    public void testInvalidLength_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            routine.calculate("12345678");
            // removed other assertion
        } catch (Exception e) {
            // removed other assertion
        }

        try {
            routine.calculate("1234567890");
            // removed other assertion
        } catch (Exception e) {
            assertEquals("calculate() Lth 10", "Invalid ISBN Length = 10", e.getMessage());
    }
    }

    public void testInvalidLength_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            routine.calculate("12345678");
            // removed other assertion
        } catch (Exception e) {
            // removed other assertion
        }

        try {
            routine.calculate("1234567890");
            // removed other assertion
        } catch (Exception e) {
            // removed other assertion
        }

        try {
            routine.calculate("12345678901");
            // removed other assertion
        } catch (Exception e) {
            assertEquals("calculate() Lth 11", "Invalid ISBN Length = 11", e.getMessage());
    }
    }

    public void testInvalidLength_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
            routine.calculate("12345678");
            // removed other assertion
        } catch (Exception e) {
            // removed other assertion
        }

        try {
            routine.calculate("1234567890");
            // removed other assertion
        } catch (Exception e) {
            // removed other assertion
        }

        try {
            routine.calculate("12345678901");
            // removed other assertion
        } catch (Exception e) {
            // removed other assertion
        }

        try {
            routine.calculate("1234567890123");
            // removed other assertion
        } catch (Exception e) {
            assertEquals("calculate() Lth 13", "Invalid ISBN Length = 13", e.getMessage());
    }
    }

}
