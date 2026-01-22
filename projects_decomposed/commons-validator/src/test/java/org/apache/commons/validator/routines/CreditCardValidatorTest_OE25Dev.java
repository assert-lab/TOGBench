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

import junit.framework.TestCase;
import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;
import org.apache.commons.validator.routines.CreditCardValidator.CreditCardRange;

/**
 * Test the CreditCardValidator class.
 *
 * @version $Revision$
 */
public class CreditCardValidatorTest_OE25Dev extends TestCase {
    
    private static final String VALID_VISA       = "4417123456789113"; // 16
    private static final String ERROR_VISA       = "4417123456789112"; 
    private static final String VALID_SHORT_VISA = "4222222222222"; // 13
    private static final String ERROR_SHORT_VISA = "4222222222229";
    private static final String VALID_AMEX       = "378282246310005"; // 15
    private static final String ERROR_AMEX       = "378282246310001";
    private static final String VALID_MASTERCARD = "5105105105105100";
    private static final String ERROR_MASTERCARD = "5105105105105105";
    private static final String VALID_DISCOVER   = "6011000990139424";
    private static final String ERROR_DISCOVER   = "6011000990139421";
    private static final String VALID_DISCOVER65 = "6534567890123458"; // FIXME need verified test data for Discover with "65" prefix
    private static final String ERROR_DISCOVER65 = "6534567890123450"; // FIXME need verified test data for Discover with "65" prefix
    private static final String VALID_DINERS     = "30569309025904"; // 14
    private static final String ERROR_DINERS     = "30569309025901";
    private static final String VALID_VPAY       = "4370000000000061"; // 16
    private static final String VALID_VPAY2      = "4370000000000012";
    private static final String ERROR_VPAY       = "4370000000000069";

    private static final String [] VALID_CARDS = {
        VALID_VISA,
        VALID_SHORT_VISA,
        VALID_AMEX,
        VALID_MASTERCARD,
        VALID_DISCOVER,
        VALID_DISCOVER65,
        VALID_DINERS,
        VALID_VPAY,
        VALID_VPAY2,
        "60115564485789458", // VALIDATOR-403
    };

    private static final String [] ERROR_CARDS = {
        ERROR_VISA,
        ERROR_SHORT_VISA,
        ERROR_AMEX,
        ERROR_MASTERCARD,
        ERROR_DISCOVER,
        ERROR_DISCOVER65,
        ERROR_DINERS,
        ERROR_VPAY,
//        ERROR_VPAY2,
        "",
        "12345678901", // too short (11)
        "12345678901234567890", // too long (20)
        "4417123456789112", // invalid check digit
    };

    /**
     * Constructor for CreditCardValidatorTest_OE25Dev.
     */
    public CreditCardValidatorTest_OE25Dev(String name) {
        super(name);
    }

    /**
     * Test the CodeValidator array constructor
     */    

    /**
     * Test the Amex Card validator
     */    

    /**
     * Test the Amex Card option
     */    

    /**
     * Test the Diners Card validator
     */    

    /**
     * Test the Diners Card option
     */    

    /**
     * Test the Discover Card validator
     */    

    /**
     * Test the Discover Card option
     */    

    /**
     * Test the Mastercard Card validator
     */    

    /**
     * Test the Mastercard Card option
     */    

    /**
     * Test the Visa Card validator
     */    

    /**
     * Test the Visa Card option
     */    

    /**
     * Test using separators
     */    

    public void testIsValid_1_oe() {
        CreditCardValidator ccv = new CreditCardValidator();
        
        assertNull(ccv.validate(null));
    }

    public void testIsValid_2_oe() {
        CreditCardValidator ccv = new CreditCardValidator();
        
        // removed other assertion

        assertFalse(ccv.isValid(null));
    }

    public void testIsValid_3_oe() {
        CreditCardValidator ccv = new CreditCardValidator();
        
        // removed other assertion

        // removed other assertion
        assertFalse(ccv.isValid(""));
    }

    public void testIsValid_4_oe() {
        CreditCardValidator ccv = new CreditCardValidator();
        
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse(ccv.isValid("123456789012"));   // too short;
    }

    public void testIsValid_5_oe() {
        CreditCardValidator ccv = new CreditCardValidator();
        
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ccv.isValid("12345678901234567890"));   // too long;
    }

    public void testIsValid_6_oe() {
        CreditCardValidator ccv = new CreditCardValidator();
        
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ccv.isValid("4417123456789112"));
    }

    public void testIsValid_7_oe() {
        CreditCardValidator ccv = new CreditCardValidator();
        
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ccv.isValid("4417q23456w89113"));
    }

    public void testIsValid_8_oe() {
        CreditCardValidator ccv = new CreditCardValidator();
        
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ccv.isValid(VALID_VISA));
    }

    public void testIsValid_9_oe() {
        CreditCardValidator ccv = new CreditCardValidator();
        
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ccv.isValid(VALID_SHORT_VISA));
    }

    public void testIsValid_10_oe() {
        CreditCardValidator ccv = new CreditCardValidator();
        
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ccv.isValid(VALID_AMEX));
    }

    public void testIsValid_11_oe() {
        CreditCardValidator ccv = new CreditCardValidator();
        
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
        assertTrue(ccv.isValid(VALID_MASTERCARD));
    }

    public void testIsValid_12_oe() {
        CreditCardValidator ccv = new CreditCardValidator();
        
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
        assertTrue(ccv.isValid(VALID_DISCOVER));
    }

    public void testIsValid_13_oe() {
        CreditCardValidator ccv = new CreditCardValidator();
        
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
        assertTrue(ccv.isValid(VALID_DISCOVER65));
    }

    public void testIsValid_14_oe() {
        CreditCardValidator ccv = new CreditCardValidator();
        
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

        assertFalse(ccv.isValid(ERROR_VISA));
    }

    public void testIsValid_15_oe() {
        CreditCardValidator ccv = new CreditCardValidator();
        
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
        assertFalse(ccv.isValid(ERROR_SHORT_VISA));
    }

    public void testIsValid_16_oe() {
        CreditCardValidator ccv = new CreditCardValidator();
        
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
        // removed other assertion
        assertFalse(ccv.isValid(ERROR_AMEX));
    }

    public void testIsValid_17_oe() {
        CreditCardValidator ccv = new CreditCardValidator();
        
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
        // removed other assertion
        // removed other assertion
        assertFalse(ccv.isValid(ERROR_MASTERCARD));
    }

    public void testIsValid_18_oe() {
        CreditCardValidator ccv = new CreditCardValidator();
        
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ccv.isValid(ERROR_DISCOVER));
    }

    public void testIsValid_19_oe() {
        CreditCardValidator ccv = new CreditCardValidator();
        
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ccv.isValid(ERROR_DISCOVER65));
    }

    public void testIsValid_20_oe() {
        CreditCardValidator ccv = new CreditCardValidator();
        
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // disallow Visa so it should fail even with good number
        ccv = new CreditCardValidator(CreditCardValidator.AMEX);
        assertFalse(ccv.isValid("4417123456789113"));
    }

    public void testAddAllowedCardType_1_oe() {
        CreditCardValidator ccv = new CreditCardValidator(CreditCardValidator.NONE);
        // Turned off all cards so even valid numbers should fail
        assertFalse(ccv.isValid(VALID_VISA));
    }

    public void testAddAllowedCardType_2_oe() {
        CreditCardValidator ccv = new CreditCardValidator(CreditCardValidator.NONE);
        // Turned off all cards so even valid numbers should fail
        // removed other assertion
        assertFalse(ccv.isValid(VALID_AMEX));
    }

    public void testAddAllowedCardType_3_oe() {
        CreditCardValidator ccv = new CreditCardValidator(CreditCardValidator.NONE);
        // Turned off all cards so even valid numbers should fail
        // removed other assertion
        // removed other assertion
        assertFalse(ccv.isValid(VALID_MASTERCARD));
    }

    public void testAddAllowedCardType_4_oe() {
        CreditCardValidator ccv = new CreditCardValidator(CreditCardValidator.NONE);
        // Turned off all cards so even valid numbers should fail
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ccv.isValid(VALID_DISCOVER));
    }

    public void testAddAllowedCardType_5_oe() {
        CreditCardValidator ccv = new CreditCardValidator(CreditCardValidator.NONE);
        // Turned off all cards so even valid numbers should fail
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ccv.isValid(VALID_DINERS));
    }

    public void testArrayConstructor_1_oe() {
        CreditCardValidator ccv = new CreditCardValidator(new CodeValidator[]
               {CreditCardValidator.VISA_VALIDATOR, CreditCardValidator.AMEX_VALIDATOR});
        
        assertTrue(ccv.isValid(VALID_VISA));
    }

    public void testArrayConstructor_2_oe() {
        CreditCardValidator ccv = new CreditCardValidator(new CodeValidator[]
               {CreditCardValidator.VISA_VALIDATOR, CreditCardValidator.AMEX_VALIDATOR});
        
        // removed other assertion
        assertTrue(ccv.isValid(VALID_SHORT_VISA));
    }

    public void testArrayConstructor_3_oe() {
        CreditCardValidator ccv = new CreditCardValidator(new CodeValidator[]
               {CreditCardValidator.VISA_VALIDATOR, CreditCardValidator.AMEX_VALIDATOR});
        
        // removed other assertion
        // removed other assertion
        assertTrue(ccv.isValid(VALID_AMEX));
    }

    public void testArrayConstructor_4_oe() {
        CreditCardValidator ccv = new CreditCardValidator(new CodeValidator[]
               {CreditCardValidator.VISA_VALIDATOR, CreditCardValidator.AMEX_VALIDATOR});
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ccv.isValid(VALID_MASTERCARD));
    }

    public void testArrayConstructor_5_oe() {
        CreditCardValidator ccv = new CreditCardValidator(new CodeValidator[]
               {CreditCardValidator.VISA_VALIDATOR, CreditCardValidator.AMEX_VALIDATOR});
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ccv.isValid(VALID_DISCOVER));
    }

    public void testArrayConstructor_6_oe() {
        CreditCardValidator ccv = new CreditCardValidator(new CodeValidator[]
               {CreditCardValidator.VISA_VALIDATOR, CreditCardValidator.AMEX_VALIDATOR});
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(ccv.isValid(ERROR_VISA));
    }

    public void testArrayConstructor_7_oe() {
        CreditCardValidator ccv = new CreditCardValidator(new CodeValidator[]
               {CreditCardValidator.VISA_VALIDATOR, CreditCardValidator.AMEX_VALIDATOR});
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse(ccv.isValid(ERROR_SHORT_VISA));
    }

    public void testArrayConstructor_8_oe() {
        CreditCardValidator ccv = new CreditCardValidator(new CodeValidator[]
               {CreditCardValidator.VISA_VALIDATOR, CreditCardValidator.AMEX_VALIDATOR});
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse(ccv.isValid(ERROR_AMEX));
    }

    public void testArrayConstructor_9_oe() {
        CreditCardValidator ccv = new CreditCardValidator(new CodeValidator[]
               {CreditCardValidator.VISA_VALIDATOR, CreditCardValidator.AMEX_VALIDATOR});
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ccv.isValid(ERROR_MASTERCARD));
    }

    public void testArrayConstructor_10_oe() {
        CreditCardValidator ccv = new CreditCardValidator(new CodeValidator[]
               {CreditCardValidator.VISA_VALIDATOR, CreditCardValidator.AMEX_VALIDATOR});
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ccv.isValid(ERROR_DISCOVER));
    }

    public void testAmexValidator_1_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
        assertFalse("Length 12",      regex.isValid("343456789012"));
    }

    public void testAmexValidator_2_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
        // removed other assertion
        assertFalse("Length 13",      regex.isValid("3434567890123"));
    }

    public void testAmexValidator_3_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
        // removed other assertion
        // removed other assertion
        assertFalse("Length 14",      regex.isValid("34345678901234"));
    }

    public void testAmexValidator_4_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Length 15",       regex.isValid("343456789012345"));
    }

    public void testAmexValidator_5_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Length 16",      regex.isValid("3434567890123456"));
    }

    public void testAmexValidator_6_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Length 17",      regex.isValid("34345678901234567"));
    }

    public void testAmexValidator_7_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Length 18",      regex.isValid("343456789012345678"));
    }

    public void testAmexValidator_8_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Prefix 33",      regex.isValid("333456789012345"));
    }

    public void testAmexValidator_9_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Prefix 34",       regex.isValid("343456789012345"));
    }

    public void testAmexValidator_10_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Prefix 35",      regex.isValid("353456789012345"));
    }

    public void testAmexValidator_11_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
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
        assertFalse("Prefix 36",      regex.isValid("363456789012345"));
    }

    public void testAmexValidator_12_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
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
        assertTrue("Prefix 37",       regex.isValid("373456789012345"));
    }

    public void testAmexValidator_13_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
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
        assertFalse("Prefix 38",      regex.isValid("383456789012345"));
    }

    public void testAmexValidator_14_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
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
        assertFalse("Prefix 41",      regex.isValid("413456789012345"));
    }

    public void testAmexValidator_15_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
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
        assertFalse("Invalid Char",   regex.isValid("3434567x9012345"));
    }

    public void testAmexValidator_16_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
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
        // removed other assertion

        // *********** Test Validator **********
        assertTrue("Valid regex",     regex.isValid(ERROR_AMEX));
    }

    public void testAmexValidator_17_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
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
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        assertFalse("Invalid",        validator.isValid(ERROR_AMEX));
    }

    public void testAmexValidator_18_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
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
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        assertNull("validate()",      validator.validate(ERROR_AMEX));
    }

    public void testAmexValidator_19_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
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
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(VALID_AMEX,      validator.validate(VALID_AMEX));
    }

    public void testAmexValidator_20_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
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
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue("Amex",            validator.isValid(VALID_AMEX));
    }

    public void testAmexValidator_21_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
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
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse("Diners",         validator.isValid(VALID_DINERS));
    }

    public void testAmexValidator_22_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
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
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse("Discover",       validator.isValid(VALID_DISCOVER));
    }

    public void testAmexValidator_23_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
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
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Mastercard",     validator.isValid(VALID_MASTERCARD));
    }

    public void testAmexValidator_24_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
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
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Visa",           validator.isValid(VALID_VISA));
    }

    public void testAmexValidator_25_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
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
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Visa Short",     validator.isValid(VALID_SHORT_VISA));
    }

    public void testAmexValidator_26_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
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
        // removed other assertion

        // *********** Test Validator **********
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
        
        assertTrue("Valid-A",         validator.isValid("371449635398431"));
    }

    public void testAmexValidator_27_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
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
        // removed other assertion

        // *********** Test Validator **********
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
        assertTrue("Valid-B",         validator.isValid("340000000000009"));
    }

    public void testAmexValidator_28_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
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
        // removed other assertion

        // *********** Test Validator **********
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
        assertTrue("Valid-C",         validator.isValid("370000000000002"));
    }

    public void testAmexValidator_29_oe() {

        CodeValidator validator = CreditCardValidator.AMEX_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 15 and start with a "34" or "37"
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
        // removed other assertion

        // *********** Test Validator **********
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
        assertTrue("Valid-D",         validator.isValid("378734493671000"));
    }

    public void testAmexOption_1_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.AMEX);
        assertFalse("Invalid",        validator.isValid(ERROR_AMEX));
    }

    public void testAmexOption_2_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.AMEX);
        // removed other assertion
        assertNull("validate()",      validator.validate(ERROR_AMEX));
    }

    public void testAmexOption_3_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.AMEX);
        // removed other assertion
        // removed other assertion
        assertEquals(VALID_AMEX,      validator.validate(VALID_AMEX));
    }

    public void testAmexOption_4_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.AMEX);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue("Amex",            validator.isValid(VALID_AMEX));
    }

    public void testAmexOption_5_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.AMEX);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse("Diners",         validator.isValid(VALID_DINERS));
    }

    public void testAmexOption_6_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.AMEX);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse("Discover",       validator.isValid(VALID_DISCOVER));
    }

    public void testAmexOption_7_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.AMEX);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Mastercard",     validator.isValid(VALID_MASTERCARD));
    }

    public void testAmexOption_8_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.AMEX);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Visa",           validator.isValid(VALID_VISA));
    }

    public void testAmexOption_9_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.AMEX);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Visa Short",     validator.isValid(VALID_SHORT_VISA));
    }

    public void testDinersValidator_1_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
        assertFalse("Length 12-300",  regex.isValid("300456789012"));
    }

    public void testDinersValidator_2_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
        // removed other assertion
        assertFalse("Length 12-36",   regex.isValid("363456789012"));
    }

    public void testDinersValidator_3_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
        // removed other assertion
        // removed other assertion
        assertFalse("Length 13-300",  regex.isValid("3004567890123"));
    }

    public void testDinersValidator_4_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Length 13-36",   regex.isValid("3634567890123"));
    }

    public void testDinersValidator_5_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Length 14-300",   regex.isValid("30045678901234"));
    }

    public void testDinersValidator_6_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Length 14-36",    regex.isValid("36345678901234"));
    }

    public void testDinersValidator_7_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Length 15-300",  regex.isValid("300456789012345"));
    }

    public void testDinersValidator_8_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Length 15-36",   regex.isValid("363456789012345"));
    }

    public void testDinersValidator_9_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Length 16-300",  regex.isValid("3004567890123456"));
    }

    public void testDinersValidator_10_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Length 16-36",   regex.isValid("3634567890123456"));
    }

    public void testDinersValidator_11_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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
        assertFalse("Length 17-300",  regex.isValid("30045678901234567"));
    }

    public void testDinersValidator_12_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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
        assertFalse("Length 17-36",   regex.isValid("36345678901234567"));
    }

    public void testDinersValidator_13_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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
        assertFalse("Length 18-300",  regex.isValid("300456789012345678"));
    }

    public void testDinersValidator_14_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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
        assertFalse("Length 18-36",   regex.isValid("363456789012345678"));
    }

    public void testDinersValidator_15_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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

        assertTrue("Prefix 300",      regex.isValid("30045678901234"));
    }

    public void testDinersValidator_16_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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

        // removed other assertion
        assertTrue("Prefix 301",      regex.isValid("30145678901234"));
    }

    public void testDinersValidator_17_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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

        // removed other assertion
        // removed other assertion
        assertTrue("Prefix 302",      regex.isValid("30245678901234"));
    }

    public void testDinersValidator_18_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Prefix 303",      regex.isValid("30345678901234"));
    }

    public void testDinersValidator_19_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Prefix 304",      regex.isValid("30445678901234"));
    }

    public void testDinersValidator_20_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Prefix 305",      regex.isValid("30545678901234"));
    }

    public void testDinersValidator_21_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Prefix 306",     regex.isValid("30645678901234"));
    }

    public void testDinersValidator_22_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Prefix 3094",    regex.isValid("30945678901234"));
    }

    public void testDinersValidator_23_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue( "Prefix 3095",    regex.isValid("30955678901234"));
    }

    public void testDinersValidator_24_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Prefix 3096",    regex.isValid("30965678901234"));
    }

    public void testDinersValidator_25_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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
        assertFalse("Prefix 35",      regex.isValid("35345678901234"));
    }

    public void testDinersValidator_26_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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
        assertTrue("Prefix 36",       regex.isValid("36345678901234"));
    }

    public void testDinersValidator_27_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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
        assertFalse("Prefix 37",      regex.isValid("37345678901234"));
    }

    public void testDinersValidator_28_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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
        assertTrue("Prefix 38",       regex.isValid("38345678901234"));
    }

    public void testDinersValidator_29_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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
        assertTrue("Prefix 39",       regex.isValid("39345678901234"));
    }

    public void testDinersValidator_30_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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
        // removed other assertion

        assertFalse("Invalid Char-A", regex.isValid("3004567x901234"));
    }

    public void testDinersValidator_31_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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
        // removed other assertion

        // removed other assertion
        assertFalse("Invalid Char-B", regex.isValid("3634567x901234"));
    }

    public void testDinersValidator_32_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        assertTrue("Valid regex",     regex.isValid(ERROR_DINERS));
    }

    public void testDinersValidator_33_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        assertFalse("Invalid",        validator.isValid(ERROR_DINERS));
    }

    public void testDinersValidator_34_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        assertNull("validate()",      validator.validate(ERROR_DINERS));
    }

    public void testDinersValidator_35_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(VALID_DINERS,    validator.validate(VALID_DINERS));
    }

    public void testDinersValidator_36_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse("Amex",           validator.isValid(VALID_AMEX));
    }

    public void testDinersValidator_37_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue("Diners",          validator.isValid(VALID_DINERS));
    }

    public void testDinersValidator_38_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse("Discover",       validator.isValid(VALID_DISCOVER));
    }

    public void testDinersValidator_39_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Mastercard",     validator.isValid(VALID_MASTERCARD));
    }

    public void testDinersValidator_40_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Visa",           validator.isValid(VALID_VISA));
    }

    public void testDinersValidator_41_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Visa Short",     validator.isValid(VALID_SHORT_VISA));
    }

    public void testDinersValidator_42_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
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
        
        assertTrue("Valid-A",         validator.isValid("30000000000004"));
    }

    public void testDinersValidator_43_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
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
        assertTrue("Valid-B",         validator.isValid("30123456789019"));
    }

    public void testDinersValidator_44_oe() {

        CodeValidator validator = CreditCardValidator.DINERS_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 14 and start with a "300-305" or "3095" or "36" or "38" or "39"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
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
        assertTrue("Valid-C",         validator.isValid("36432685260294"));
    }

    public void testDinersOption_1_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.DINERS);
        assertFalse("Invalid",        validator.isValid(ERROR_DINERS));
    }

    public void testDinersOption_2_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.DINERS);
        // removed other assertion
        assertNull("validate()",      validator.validate(ERROR_DINERS));
    }

    public void testDinersOption_3_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.DINERS);
        // removed other assertion
        // removed other assertion
        assertEquals(VALID_DINERS,    validator.validate(VALID_DINERS));
    }

    public void testDinersOption_4_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.DINERS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse("Amex",           validator.isValid(VALID_AMEX));
    }

    public void testDinersOption_5_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.DINERS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue("Diners",          validator.isValid(VALID_DINERS));
    }

    public void testDinersOption_6_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.DINERS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse("Discover",       validator.isValid(VALID_DISCOVER));
    }

    public void testDinersOption_7_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.DINERS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Mastercard",     validator.isValid(VALID_MASTERCARD));
    }

    public void testDinersOption_8_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.DINERS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Visa",           validator.isValid(VALID_VISA));
    }

    public void testDinersOption_9_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.DINERS);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Visa Short",     validator.isValid(VALID_SHORT_VISA));
    }

    public void testDiscoverValidator_1_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
        assertFalse("Length 12-6011", regex.isValid("601156789012"));
    }

    public void testDiscoverValidator_2_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
        // removed other assertion
        assertFalse("Length 12-65",   regex.isValid("653456789012"));
    }

    public void testDiscoverValidator_3_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
        // removed other assertion
        // removed other assertion
        assertFalse("Length 13-6011", regex.isValid("6011567890123"));
    }

    public void testDiscoverValidator_4_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Length 13-65",   regex.isValid("6534567890123"));
    }

    public void testDiscoverValidator_5_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Length 14-6011", regex.isValid("60115678901234"));
    }

    public void testDiscoverValidator_6_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Length 14-65",   regex.isValid("65345678901234"));
    }

    public void testDiscoverValidator_7_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Length 15-6011", regex.isValid("601156789012345"));
    }

    public void testDiscoverValidator_8_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Length 15-65",   regex.isValid("653456789012345"));
    }

    public void testDiscoverValidator_9_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Length 16-6011",  regex.isValid("6011567890123456"));
    }

    public void testDiscoverValidator_10_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Length 16-644",   regex.isValid("6444567890123456"));
    }

    public void testDiscoverValidator_11_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        assertTrue("Length 16-648",   regex.isValid("6484567890123456"));
    }

    public void testDiscoverValidator_12_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        assertTrue("Length 16-65",    regex.isValid("6534567890123456"));
    }

    public void testDiscoverValidator_13_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        assertFalse("Length 17-65",   regex.isValid("65345678901234567"));
    }

    public void testDiscoverValidator_14_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        assertFalse("Length 18-6011", regex.isValid("601156789012345678"));
    }

    public void testDiscoverValidator_15_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        assertFalse("Length 18-65",   regex.isValid("653456789012345678"));
    }

    public void testDiscoverValidator_16_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        // removed other assertion

        assertFalse("Prefix 640",     regex.isValid("6404567890123456"));
    }

    public void testDiscoverValidator_17_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        // removed other assertion

        // removed other assertion
        assertFalse("Prefix 641",     regex.isValid("6414567890123456"));
    }

    public void testDiscoverValidator_18_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse("Prefix 642",     regex.isValid("6424567890123456"));
    }

    public void testDiscoverValidator_19_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Prefix 643",     regex.isValid("6434567890123456"));
    }

    public void testDiscoverValidator_20_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Prefix 6010",    regex.isValid("6010567890123456"));
    }

    public void testDiscoverValidator_21_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Prefix 6012",    regex.isValid("6012567890123456"));
    }

    public void testDiscoverValidator_22_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Invalid Char",   regex.isValid("6011567x90123456"));
    }

    public void testDiscoverValidator_23_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        assertTrue("Valid regex",     regex.isValid(ERROR_DISCOVER));
    }

    public void testDiscoverValidator_24_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        assertTrue("Valid regex65",   regex.isValid(ERROR_DISCOVER65));
    }

    public void testDiscoverValidator_25_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        assertFalse("Invalid",        validator.isValid(ERROR_DISCOVER));
    }

    public void testDiscoverValidator_26_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Invalid65",      validator.isValid(ERROR_DISCOVER65));
    }

    public void testDiscoverValidator_27_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull("validate()",      validator.validate(ERROR_DISCOVER));
    }

    public void testDiscoverValidator_28_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(VALID_DISCOVER,  validator.validate(VALID_DISCOVER));
    }

    public void testDiscoverValidator_29_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(VALID_DISCOVER65, validator.validate(VALID_DISCOVER65));
    }

    public void testDiscoverValidator_30_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse("Amex",           validator.isValid(VALID_AMEX));
    }

    public void testDiscoverValidator_31_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse("Diners",         validator.isValid(VALID_DINERS));
    }

    public void testDiscoverValidator_32_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue("Discover",        validator.isValid(VALID_DISCOVER));
    }

    public void testDiscoverValidator_33_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
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
        assertTrue("Discover",        validator.isValid(VALID_DISCOVER65));
    }

    public void testDiscoverValidator_34_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
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
        assertFalse("Mastercard",     validator.isValid(VALID_MASTERCARD));
    }

    public void testDiscoverValidator_35_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
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
        assertFalse("Visa",           validator.isValid(VALID_VISA));
    }

    public void testDiscoverValidator_36_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
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
        assertFalse("Visa Short",     validator.isValid(VALID_SHORT_VISA));
    }

    public void testDiscoverValidator_37_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
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
        
        assertTrue("Valid-A",         validator.isValid("6011111111111117"));
    }

    public void testDiscoverValidator_38_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
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
        
        // removed other assertion
        assertTrue("Valid-B",         validator.isValid("6011000000000004"));
    }

    public void testDiscoverValidator_39_oe() {

        CodeValidator validator = CreditCardValidator.DISCOVER_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with either "6011" or or "64[4-9]" or "65"
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
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
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
        
        // removed other assertion
        // removed other assertion
        assertTrue("Valid-C",         validator.isValid("6011000000000012"));
    }

    public void testDiscoverOption_1_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.DISCOVER);
        assertFalse("Invalid",        validator.isValid(ERROR_DISCOVER));
    }

    public void testDiscoverOption_2_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.DISCOVER);
        // removed other assertion
        assertFalse("Invalid65",      validator.isValid(ERROR_DISCOVER65));
    }

    public void testDiscoverOption_3_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.DISCOVER);
        // removed other assertion
        // removed other assertion
        assertNull("validate()",      validator.validate(ERROR_DISCOVER));
    }

    public void testDiscoverOption_4_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.DISCOVER);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(VALID_DISCOVER,  validator.validate(VALID_DISCOVER));
    }

    public void testDiscoverOption_5_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.DISCOVER);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(VALID_DISCOVER65, validator.validate(VALID_DISCOVER65));
    }

    public void testDiscoverOption_6_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.DISCOVER);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse("Amex",           validator.isValid(VALID_AMEX));
    }

    public void testDiscoverOption_7_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.DISCOVER);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse("Diners",         validator.isValid(VALID_DINERS));
    }

    public void testDiscoverOption_8_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.DISCOVER);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue("Discover",        validator.isValid(VALID_DISCOVER));
    }

    public void testDiscoverOption_9_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.DISCOVER);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Discover",        validator.isValid(VALID_DISCOVER65));
    }

    public void testDiscoverOption_10_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.DISCOVER);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Mastercard",     validator.isValid(VALID_MASTERCARD));
    }

    public void testDiscoverOption_11_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.DISCOVER);
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
        assertFalse("Visa",           validator.isValid(VALID_VISA));
    }

    public void testDiscoverOption_12_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.DISCOVER);
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
        assertFalse("Visa Short",     validator.isValid(VALID_SHORT_VISA));
    }

    public void testMastercardValidator_1_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
        assertFalse("Length 12",      regex.isValid("513456789012"));
    }

    public void testMastercardValidator_2_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
        // removed other assertion
        assertFalse("Length 13",      regex.isValid("5134567890123"));
    }

    public void testMastercardValidator_3_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
        // removed other assertion
        // removed other assertion
        assertFalse("Length 14",      regex.isValid("51345678901234"));
    }

    public void testMastercardValidator_4_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Length 15",      regex.isValid("513456789012345"));
    }

    public void testMastercardValidator_5_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Length 16",       regex.isValid("5134567890123456"));
    }

    public void testMastercardValidator_6_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Length 17",      regex.isValid("51345678901234567"));
    }

    public void testMastercardValidator_7_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Length 18",      regex.isValid("513456789012345678"));
    }

    public void testMastercardValidator_8_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Prefix 41",      regex.isValid("4134567890123456"));
    }

    public void testMastercardValidator_9_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Prefix 50",      regex.isValid("5034567890123456"));
    }

    public void testMastercardValidator_10_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Prefix 51",       regex.isValid("5134567890123456"));
    }

    public void testMastercardValidator_11_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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
        assertTrue("Prefix 52",       regex.isValid("5234567890123456"));
    }

    public void testMastercardValidator_12_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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
        assertTrue("Prefix 53",       regex.isValid("5334567890123456"));
    }

    public void testMastercardValidator_13_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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
        assertTrue("Prefix 54",       regex.isValid("5434567890123456"));
    }

    public void testMastercardValidator_14_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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
        assertTrue("Prefix 55",       regex.isValid("5534567890123456"));
    }

    public void testMastercardValidator_15_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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
        assertFalse("Prefix 56",      regex.isValid("5634567890123456"));
    }

    public void testMastercardValidator_16_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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
        // removed other assertion
        assertFalse("Prefix 61",      regex.isValid("6134567890123456"));
    }

    public void testMastercardValidator_17_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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
        // removed other assertion
        // removed other assertion
        assertFalse("Invalid Char",   regex.isValid("5134567x90123456"));
    }

    public void testMastercardValidator_18_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        assertTrue("Valid regex",     regex.isValid(ERROR_MASTERCARD));
    }

    public void testMastercardValidator_19_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        assertFalse("Invalid",        validator.isValid(ERROR_MASTERCARD));
    }

    public void testMastercardValidator_20_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        assertNull("validate()",      validator.validate(ERROR_MASTERCARD));
    }

    public void testMastercardValidator_21_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(VALID_MASTERCARD, validator.validate(VALID_MASTERCARD));
    }

    public void testMastercardValidator_22_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse("Amex",           validator.isValid(VALID_AMEX));
    }

    public void testMastercardValidator_23_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse("Diners",         validator.isValid(VALID_DINERS));
    }

    public void testMastercardValidator_24_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse("Discover",       validator.isValid(VALID_DISCOVER));
    }

    public void testMastercardValidator_25_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Mastercard",      validator.isValid(VALID_MASTERCARD));
    }

    public void testMastercardValidator_26_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Visa",           validator.isValid(VALID_VISA));
    }

    public void testMastercardValidator_27_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Visa Short",     validator.isValid(VALID_SHORT_VISA));
    }

    public void testMastercardValidator_28_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
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
        
        assertTrue("Valid-A",         validator.isValid("5500000000000004"));
    }

    public void testMastercardValidator_29_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
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
        assertTrue("Valid-B",         validator.isValid("5424000000000015"));
    }

    public void testMastercardValidator_30_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
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
        assertTrue("Valid-C",         validator.isValid("5301250070000191"));
    }

    public void testMastercardValidator_31_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
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
        assertTrue("Valid-D",         validator.isValid("5123456789012346"));
    }

    public void testMastercardValidator_32_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
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
        assertTrue("Valid-E",         validator.isValid("5555555555554444"));
    }

    public void testMastercardValidator_33_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
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
        // removed other assertion
        
        RegexValidator rev = validator.getRegexValidator();
        final String PAD = "0000000000";
        assertFalse("222099",rev.isValid("222099"+PAD));
    }

    public void testMastercardValidator_34_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
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
        // removed other assertion
        
        RegexValidator rev = validator.getRegexValidator();
        final String PAD = "0000000000";
        // removed other assertion
        for(int i=222100; i <= 272099; i++) {
            String j = Integer.toString(i)+PAD;
            assertTrue(j, rev.isValid(j));
    }
    }

    public void testMastercardValidator_35_oe() {

        CodeValidator validator = CreditCardValidator.MASTERCARD_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // *********** Test Validator **********
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
        // removed other assertion
        
        RegexValidator rev = validator.getRegexValidator();
        final String PAD = "0000000000";
        // removed other assertion
        for(int i=222100; i <= 272099; i++) {
            String j = Integer.toString(i)+PAD;
            // removed other assertion
        }
        assertFalse("272100",rev.isValid("272100"+PAD));
    }

    public void testMastercardOption_1_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.MASTERCARD);
        assertFalse("Invalid",        validator.isValid(ERROR_MASTERCARD));
    }

    public void testMastercardOption_2_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.MASTERCARD);
        // removed other assertion
        assertNull("validate()",      validator.validate(ERROR_MASTERCARD));
    }

    public void testMastercardOption_3_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.MASTERCARD);
        // removed other assertion
        // removed other assertion
        assertEquals(VALID_MASTERCARD, validator.validate(VALID_MASTERCARD));
    }

    public void testMastercardOption_4_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.MASTERCARD);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse("Amex",           validator.isValid(VALID_AMEX));
    }

    public void testMastercardOption_5_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.MASTERCARD);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse("Diners",         validator.isValid(VALID_DINERS));
    }

    public void testMastercardOption_6_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.MASTERCARD);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse("Discover",       validator.isValid(VALID_DISCOVER));
    }

    public void testMastercardOption_7_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.MASTERCARD);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Mastercard",      validator.isValid(VALID_MASTERCARD));
    }

    public void testMastercardOption_8_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.MASTERCARD);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Visa",           validator.isValid(VALID_VISA));
    }

    public void testMastercardOption_9_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.MASTERCARD);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Visa Short",     validator.isValid(VALID_SHORT_VISA));
    }

    public void testVisaValidator_1_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
        assertFalse("Length 12",      regex.isValid("423456789012"));
    }

    public void testVisaValidator_2_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
        // removed other assertion
        assertTrue("Length 13",       regex.isValid("4234567890123"));
    }

    public void testVisaValidator_3_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
        // removed other assertion
        // removed other assertion
        assertFalse("Length 14",      regex.isValid("42345678901234"));
    }

    public void testVisaValidator_4_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Length 15",      regex.isValid("423456789012345"));
    }

    public void testVisaValidator_5_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Length 16",       regex.isValid("4234567890123456"));
    }

    public void testVisaValidator_6_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Length 17",      regex.isValid("42345678901234567"));
    }

    public void testVisaValidator_7_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Length 18",      regex.isValid("423456789012345678"));
    }

    public void testVisaValidator_8_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Invalid Pref-A", regex.isValid("3234567890123"));
    }

    public void testVisaValidator_9_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Invalid Pref-B", regex.isValid("3234567890123456"));
    }

    public void testVisaValidator_10_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Invalid Char-A", regex.isValid("4234567x90123"));
    }

    public void testVisaValidator_11_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
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
        assertFalse("Invalid Char-B", regex.isValid("4234567x90123456"));
    }

    public void testVisaValidator_12_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
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

        // *********** Test Validator **********
        assertTrue("Valid regex",     regex.isValid(ERROR_VISA));
    }

    public void testVisaValidator_13_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
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

        // *********** Test Validator **********
        // removed other assertion
        assertTrue("Valid regex-S",   regex.isValid(ERROR_SHORT_VISA));
    }

    public void testVisaValidator_14_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
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

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        assertFalse("Invalid",        validator.isValid(ERROR_VISA));
    }

    public void testVisaValidator_15_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
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

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Invalid-S",      validator.isValid(ERROR_SHORT_VISA));
    }

    public void testVisaValidator_16_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
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

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull("validate()",      validator.validate(ERROR_VISA));
    }

    public void testVisaValidator_17_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
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

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(VALID_VISA,      validator.validate(VALID_VISA));
    }

    public void testVisaValidator_18_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
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

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(VALID_SHORT_VISA, validator.validate(VALID_SHORT_VISA));
    }

    public void testVisaValidator_19_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
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

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse("Amex",           validator.isValid(VALID_AMEX));
    }

    public void testVisaValidator_20_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
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

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse("Diners",         validator.isValid(VALID_DINERS));
    }

    public void testVisaValidator_21_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
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

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse("Discover",       validator.isValid(VALID_DISCOVER));
    }

    public void testVisaValidator_22_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
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

        // *********** Test Validator **********
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
        assertFalse("Mastercard",     validator.isValid(VALID_MASTERCARD));
    }

    public void testVisaValidator_23_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
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

        // *********** Test Validator **********
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
        assertTrue("Visa",            validator.isValid(VALID_VISA));
    }

    public void testVisaValidator_24_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
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

        // *********** Test Validator **********
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
        assertTrue("Visa Short",      validator.isValid(VALID_SHORT_VISA));
    }

    public void testVisaValidator_25_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
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

        // *********** Test Validator **********
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
        
        assertTrue("Valid-A",         validator.isValid("4111111111111111"));
    }

    public void testVisaValidator_26_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
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

        // *********** Test Validator **********
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
        assertTrue("Valid-C",         validator.isValid("4543059999999982"));
    }

    public void testVisaValidator_27_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
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

        // *********** Test Validator **********
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
        // removed other assertion
        assertTrue("Valid-B",         validator.isValid("4462000000000003"));
    }

    public void testVisaValidator_28_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
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

        // *********** Test Validator **********
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
        // removed other assertion
        // removed other assertion
        assertTrue("Valid-D",         validator.isValid("4508750000000009")); // Electron;
    }

    public void testVisaValidator_29_oe() {

        CodeValidator validator = CreditCardValidator.VISA_VALIDATOR;
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 13 or 16, must start with a "4"
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

        // *********** Test Validator **********
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Valid-E",         validator.isValid("4012888888881881"));
    }

    public void testVisaOption_1_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.VISA);
        assertFalse("Invalid",        validator.isValid(ERROR_VISA));
    }

    public void testVisaOption_2_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.VISA);
        // removed other assertion
        assertFalse("Invalid-S",      validator.isValid(ERROR_SHORT_VISA));
    }

    public void testVisaOption_3_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.VISA);
        // removed other assertion
        // removed other assertion
        assertNull("validate()",      validator.validate(ERROR_VISA));
    }

    public void testVisaOption_4_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.VISA);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(VALID_VISA,      validator.validate(VALID_VISA));
    }

    public void testVisaOption_5_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.VISA);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(VALID_SHORT_VISA, validator.validate(VALID_SHORT_VISA));
    }

    public void testVisaOption_6_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.VISA);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse("Amex",           validator.isValid(VALID_AMEX));
    }

    public void testVisaOption_7_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.VISA);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse("Diners",         validator.isValid(VALID_DINERS));
    }

    public void testVisaOption_8_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.VISA);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse("Discover",       validator.isValid(VALID_DISCOVER));
    }

    public void testVisaOption_9_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.VISA);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Mastercard",     validator.isValid(VALID_MASTERCARD));
    }

    public void testVisaOption_10_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.VISA);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Visa",            validator.isValid(VALID_VISA));
    }

    public void testVisaOption_11_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.VISA);
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
        assertTrue("Visa Short",      validator.isValid(VALID_SHORT_VISA));
    }

    public void testVPayOption_1_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.VPAY);
        assertTrue("Valid",           validator.isValid(VALID_VPAY));
    }

    public void testVPayOption_2_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.VPAY);
        // removed other assertion
        assertTrue("Valid",           validator.isValid(VALID_VPAY2));
    }

    public void testVPayOption_3_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.VPAY);
        // removed other assertion
        // removed other assertion
        assertFalse("Invalid",        validator.isValid(ERROR_VPAY));
    }

    public void testVPayOption_4_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.VPAY);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(VALID_VPAY,      validator.validate(VALID_VPAY));
    }

    public void testVPayOption_5_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.VPAY);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(VALID_VPAY2,      validator.validate(VALID_VPAY2));
    }

    public void testVPayOption_6_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.VPAY);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse("Amex",           validator.isValid(VALID_AMEX));
    }

    public void testVPayOption_7_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.VPAY);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse("Diners",         validator.isValid(VALID_DINERS));
    }

    public void testVPayOption_8_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.VPAY);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse("Discover",       validator.isValid(VALID_DISCOVER));
    }

    public void testVPayOption_9_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.VPAY);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Mastercard",     validator.isValid(VALID_MASTERCARD));
    }

    public void testVPayOption_10_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.VPAY);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Visa",            validator.isValid(VALID_VISA));
    }

    public void testVPayOption_11_oe() {
        CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.VPAY);
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
        assertTrue("Visa Short",      validator.isValid(VALID_SHORT_VISA));
    }

    public void testMastercardUsingSeparators_1_oe() {

        String MASTERCARD_REGEX_SEP = "^(5[1-5]\\d{2})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})$";
        CodeValidator validator = new CodeValidator(MASTERCARD_REGEX_SEP, LuhnCheckDigit.LUHN_CHECK_DIGIT);
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
        assertEquals("Number",  "5134567890123456", regex.validate("5134567890123456"));
    }

    public void testMastercardUsingSeparators_2_oe() {

        String MASTERCARD_REGEX_SEP = "^(5[1-5]\\d{2})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})$";
        CodeValidator validator = new CodeValidator(MASTERCARD_REGEX_SEP, LuhnCheckDigit.LUHN_CHECK_DIGIT);
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
        // removed other assertion
        assertEquals("Hyphen",  "5134567890123456", regex.validate("5134-5678-9012-3456"));
    }

    public void testMastercardUsingSeparators_3_oe() {

        String MASTERCARD_REGEX_SEP = "^(5[1-5]\\d{2})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})$";
        CodeValidator validator = new CodeValidator(MASTERCARD_REGEX_SEP, LuhnCheckDigit.LUHN_CHECK_DIGIT);
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
        // removed other assertion
        // removed other assertion
        assertEquals("Space",   "5134567890123456", regex.validate("5134 5678 9012 3456"));
    }

    public void testMastercardUsingSeparators_4_oe() {

        String MASTERCARD_REGEX_SEP = "^(5[1-5]\\d{2})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})$";
        CodeValidator validator = new CodeValidator(MASTERCARD_REGEX_SEP, LuhnCheckDigit.LUHN_CHECK_DIGIT);
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("MixedA",  "5134567890123456", regex.validate("5134-5678 9012-3456"));
    }

    public void testMastercardUsingSeparators_5_oe() {

        String MASTERCARD_REGEX_SEP = "^(5[1-5]\\d{2})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})$";
        CodeValidator validator = new CodeValidator(MASTERCARD_REGEX_SEP, LuhnCheckDigit.LUHN_CHECK_DIGIT);
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("MixedB",  "5134567890123456", regex.validate("5134 5678-9012 3456"));
    }

    public void testMastercardUsingSeparators_6_oe() {

        String MASTERCARD_REGEX_SEP = "^(5[1-5]\\d{2})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})$";
        CodeValidator validator = new CodeValidator(MASTERCARD_REGEX_SEP, LuhnCheckDigit.LUHN_CHECK_DIGIT);
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse("Invalid Separator A",  regex.isValid("5134.5678.9012.3456"));
    }

    public void testMastercardUsingSeparators_7_oe() {

        String MASTERCARD_REGEX_SEP = "^(5[1-5]\\d{2})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})$";
        CodeValidator validator = new CodeValidator(MASTERCARD_REGEX_SEP, LuhnCheckDigit.LUHN_CHECK_DIGIT);
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse("Invalid Separator B",  regex.isValid("5134_5678_9012_3456"));
    }

    public void testMastercardUsingSeparators_8_oe() {

        String MASTERCARD_REGEX_SEP = "^(5[1-5]\\d{2})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})$";
        CodeValidator validator = new CodeValidator(MASTERCARD_REGEX_SEP, LuhnCheckDigit.LUHN_CHECK_DIGIT);
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse("Invalid Grouping A",   regex.isValid("513-45678-9012-3456"));
    }

    public void testMastercardUsingSeparators_9_oe() {

        String MASTERCARD_REGEX_SEP = "^(5[1-5]\\d{2})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})$";
        CodeValidator validator = new CodeValidator(MASTERCARD_REGEX_SEP, LuhnCheckDigit.LUHN_CHECK_DIGIT);
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Invalid Grouping B",   regex.isValid("5134-567-89012-3456"));
    }

    public void testMastercardUsingSeparators_10_oe() {

        String MASTERCARD_REGEX_SEP = "^(5[1-5]\\d{2})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})$";
        CodeValidator validator = new CodeValidator(MASTERCARD_REGEX_SEP, LuhnCheckDigit.LUHN_CHECK_DIGIT);
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Invalid Grouping C",   regex.isValid("5134-5678-901-23456"));
    }

    public void testMastercardUsingSeparators_11_oe() {

        String MASTERCARD_REGEX_SEP = "^(5[1-5]\\d{2})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})$";
        CodeValidator validator = new CodeValidator(MASTERCARD_REGEX_SEP, LuhnCheckDigit.LUHN_CHECK_DIGIT);
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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

        // *********** Test Validator **********
        assertEquals("Valid-A", "5500000000000004", validator.validate("5500-0000-0000-0004"));
    }

    public void testMastercardUsingSeparators_12_oe() {

        String MASTERCARD_REGEX_SEP = "^(5[1-5]\\d{2})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})$";
        CodeValidator validator = new CodeValidator(MASTERCARD_REGEX_SEP, LuhnCheckDigit.LUHN_CHECK_DIGIT);
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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

        // *********** Test Validator **********
        // removed other assertion
        assertEquals("Valid-B", "5424000000000015", validator.validate("5424 0000 0000 0015"));
    }

    public void testMastercardUsingSeparators_13_oe() {

        String MASTERCARD_REGEX_SEP = "^(5[1-5]\\d{2})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})$";
        CodeValidator validator = new CodeValidator(MASTERCARD_REGEX_SEP, LuhnCheckDigit.LUHN_CHECK_DIGIT);
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        assertEquals("Valid-C", "5301250070000191", validator.validate("5301-250070000191"));
    }

    public void testMastercardUsingSeparators_14_oe() {

        String MASTERCARD_REGEX_SEP = "^(5[1-5]\\d{2})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})(?:[- ])?(\\d{4})$";
        CodeValidator validator = new CodeValidator(MASTERCARD_REGEX_SEP, LuhnCheckDigit.LUHN_CHECK_DIGIT);
        RegexValidator regex    = validator.getRegexValidator();

        // ****** Test Regular Expression ******
        // length 16 and start with a "51-55"
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

        // *********** Test Validator **********
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Valid-D", "5123456789012346", validator.validate("5123456789012346"));
    }

    public void testGeneric_1_oe() {
        CreditCardValidator ccv = CreditCardValidator.genericCreditCardValidator();
        for(String s : VALID_CARDS) {
            assertTrue(s, ccv.isValid(s));
    }
    }

    public void testGeneric_2_oe() {
        CreditCardValidator ccv = CreditCardValidator.genericCreditCardValidator();
        for(String s : VALID_CARDS) {
            // removed other assertion
        }
        for(String s : ERROR_CARDS) {
            assertFalse(s, ccv.isValid(s));
    }
    }

    public void testRangeGeneratorNoLuhn_1_oe() {
        CodeValidator cv = CreditCardValidator.createRangeValidator(
            new CreditCardRange[]{
                new CreditCardRange("1",null,6,7),
                new CreditCardRange("644","65", 8, 8)
            }, 
            null);
        assertTrue(cv.isValid("1990000"));
    }

    public void testRangeGeneratorNoLuhn_2_oe() {
        CodeValidator cv = CreditCardValidator.createRangeValidator(
            new CreditCardRange[]{
                new CreditCardRange("1",null,6,7),
                new CreditCardRange("644","65", 8, 8)
            }, 
            null);
        // removed other assertion
        assertTrue(cv.isValid("199000"));
    }

    public void testRangeGeneratorNoLuhn_3_oe() {
        CodeValidator cv = CreditCardValidator.createRangeValidator(
            new CreditCardRange[]{
                new CreditCardRange("1",null,6,7),
                new CreditCardRange("644","65", 8, 8)
            }, 
            null);
        // removed other assertion
        // removed other assertion
        assertFalse(cv.isValid("000000"));
    }

    public void testRangeGeneratorNoLuhn_4_oe() {
        CodeValidator cv = CreditCardValidator.createRangeValidator(
            new CreditCardRange[]{
                new CreditCardRange("1",null,6,7),
                new CreditCardRange("644","65", 8, 8)
            }, 
            null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(cv.isValid("099999"));
    }

    public void testRangeGeneratorNoLuhn_5_oe() {
        CodeValidator cv = CreditCardValidator.createRangeValidator(
            new CreditCardRange[]{
                new CreditCardRange("1",null,6,7),
                new CreditCardRange("644","65", 8, 8)
            }, 
            null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(cv.isValid("200000"));
    }

    public void testRangeGeneratorNoLuhn_6_oe() {
        CodeValidator cv = CreditCardValidator.createRangeValidator(
            new CreditCardRange[]{
                new CreditCardRange("1",null,6,7),
                new CreditCardRange("644","65", 8, 8)
            }, 
            null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(cv.isValid("64399999"));
    }

    public void testRangeGeneratorNoLuhn_7_oe() {
        CodeValidator cv = CreditCardValidator.createRangeValidator(
            new CreditCardRange[]{
                new CreditCardRange("1",null,6,7),
                new CreditCardRange("644","65", 8, 8)
            }, 
            null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(cv.isValid("64400000"));
    }

    public void testRangeGeneratorNoLuhn_8_oe() {
        CodeValidator cv = CreditCardValidator.createRangeValidator(
            new CreditCardRange[]{
                new CreditCardRange("1",null,6,7),
                new CreditCardRange("644","65", 8, 8)
            }, 
            null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(cv.isValid("64900000"));
    }

    public void testRangeGeneratorNoLuhn_9_oe() {
        CodeValidator cv = CreditCardValidator.createRangeValidator(
            new CreditCardRange[]{
                new CreditCardRange("1",null,6,7),
                new CreditCardRange("644","65", 8, 8)
            }, 
            null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(cv.isValid("65000000"));
    }

    public void testRangeGeneratorNoLuhn_10_oe() {
        CodeValidator cv = CreditCardValidator.createRangeValidator(
            new CreditCardRange[]{
                new CreditCardRange("1",null,6,7),
                new CreditCardRange("644","65", 8, 8)
            }, 
            null);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(cv.isValid("65999999"));
    }

    public void testRangeGeneratorNoLuhn_11_oe() {
        CodeValidator cv = CreditCardValidator.createRangeValidator(
            new CreditCardRange[]{
                new CreditCardRange("1",null,6,7),
                new CreditCardRange("644","65", 8, 8)
            }, 
            null);
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
        assertFalse(cv.isValid("66000000"));
    }

    public void testRangeGenerator_1_oe() {
        CreditCardValidator ccv = new CreditCardValidator(
            new CodeValidator[] {
                CreditCardValidator.AMEX_VALIDATOR,
                CreditCardValidator.VISA_VALIDATOR,
                CreditCardValidator.MASTERCARD_VALIDATOR,
                CreditCardValidator.DISCOVER_VALIDATOR,
            },
            // Add missing validator
            new CreditCardRange[]{
                new CreditCardRange("300", "305", 14, 14), // Diners
                new CreditCardRange("3095", null, 14, 14), // Diners
                new CreditCardRange("36",   null, 14, 14), // Diners
                new CreditCardRange("38",   "39", 14, 14), // Diners
            }
            // we don't have any VPAY examples yet that aren't handled by VISA
            );
        for(String s : VALID_CARDS) {
            assertTrue(s, ccv.isValid(s));
    }
    }

    public void testRangeGenerator_2_oe() {
        CreditCardValidator ccv = new CreditCardValidator(
            new CodeValidator[] {
                CreditCardValidator.AMEX_VALIDATOR,
                CreditCardValidator.VISA_VALIDATOR,
                CreditCardValidator.MASTERCARD_VALIDATOR,
                CreditCardValidator.DISCOVER_VALIDATOR,
            },
            // Add missing validator
            new CreditCardRange[]{
                new CreditCardRange("300", "305", 14, 14), // Diners
                new CreditCardRange("3095", null, 14, 14), // Diners
                new CreditCardRange("36",   null, 14, 14), // Diners
                new CreditCardRange("38",   "39", 14, 14), // Diners
            }
            // we don't have any VPAY examples yet that aren't handled by VISA
            );
        for(String s : VALID_CARDS) {
            // removed other assertion
        }
        for(String s : ERROR_CARDS) {
            assertFalse(s, ccv.isValid(s));
    }
    }

    public void testValidLength_1_oe() {
        assertTrue(CreditCardValidator.validLength(14, new CreditCardRange("", "", 14, 14)));
    }

    public void testValidLength_2_oe() {
        // removed other assertion
        assertFalse(CreditCardValidator.validLength(15, new CreditCardRange("", "", 14, 14)));
    }

    public void testValidLength_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse(CreditCardValidator.validLength(13, new CreditCardRange("", "", 14, 14)));
    }

    public void testValidLength_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(CreditCardValidator.validLength(14, new CreditCardRange("", "", 15, 17)));
    }

    public void testValidLength_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(CreditCardValidator.validLength(15, new CreditCardRange("", "", 15, 17)));
    }

    public void testValidLength_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(CreditCardValidator.validLength(16, new CreditCardRange("", "", 15, 17)));
    }

    public void testValidLength_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(CreditCardValidator.validLength(17, new CreditCardRange("", "", 15, 17)));
    }

    public void testValidLength_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(CreditCardValidator.validLength(18, new CreditCardRange("", "", 15, 17)));
    }

    public void testValidLength_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(CreditCardValidator.validLength(14, new CreditCardRange("", "", new int[]{15, 17})));
    }

    public void testValidLength_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(CreditCardValidator.validLength(15, new CreditCardRange("", "", new int[]{15, 17})));
    }

    public void testValidLength_11_oe() {
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
        assertFalse(CreditCardValidator.validLength(16, new CreditCardRange("", "", new int[]{15, 17})));
    }

    public void testValidLength_12_oe() {
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
        assertTrue(CreditCardValidator.validLength(17, new CreditCardRange("", "", new int[]{15, 17})));
    }

    public void testValidLength_13_oe() {
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
        assertFalse(CreditCardValidator.validLength(18, new CreditCardRange("", "", new int[]{15, 17})));
    }

    public void testDisjointRange_1_oe() {
        CreditCardValidator ccv = new CreditCardValidator(
            new CreditCardRange[]{
                    new CreditCardRange("305", "4", new int[]{13, 16}),
                }
            );
        assertEquals(13, VALID_SHORT_VISA.length());
    }

    public void testDisjointRange_2_oe() {
        CreditCardValidator ccv = new CreditCardValidator(
            new CreditCardRange[]{
                    new CreditCardRange("305", "4", new int[]{13, 16}),
                }
            );
        // removed other assertion
        assertEquals(16, VALID_VISA.length());
    }

    public void testDisjointRange_3_oe() {
        CreditCardValidator ccv = new CreditCardValidator(
            new CreditCardRange[]{
                    new CreditCardRange("305", "4", new int[]{13, 16}),
                }
            );
        // removed other assertion
        // removed other assertion
        assertEquals(14, VALID_DINERS.length());
    }

    public void testDisjointRange_4_oe() {
        CreditCardValidator ccv = new CreditCardValidator(
            new CreditCardRange[]{
                    new CreditCardRange("305", "4", new int[]{13, 16}),
                }
            );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ccv.isValid(VALID_SHORT_VISA));
    }

    public void testDisjointRange_5_oe() {
        CreditCardValidator ccv = new CreditCardValidator(
            new CreditCardRange[]{
                    new CreditCardRange("305", "4", new int[]{13, 16}),
                }
            );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(ccv.isValid(VALID_VISA));
    }

    public void testDisjointRange_6_oe() {
        CreditCardValidator ccv = new CreditCardValidator(
            new CreditCardRange[]{
                    new CreditCardRange("305", "4", new int[]{13, 16}),
                }
            );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ccv.isValid(ERROR_SHORT_VISA));
    }

    public void testDisjointRange_7_oe() {
        CreditCardValidator ccv = new CreditCardValidator(
            new CreditCardRange[]{
                    new CreditCardRange("305", "4", new int[]{13, 16}),
                }
            );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ccv.isValid(ERROR_VISA));
    }

    public void testDisjointRange_8_oe() {
        CreditCardValidator ccv = new CreditCardValidator(
            new CreditCardRange[]{
                    new CreditCardRange("305", "4", new int[]{13, 16}),
                }
            );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(ccv.isValid(VALID_DINERS));
    }

    public void testDisjointRange_9_oe() {
        CreditCardValidator ccv = new CreditCardValidator(
            new CreditCardRange[]{
                    new CreditCardRange("305", "4", new int[]{13, 16}),
                }
            );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        ccv = new CreditCardValidator(
            new CreditCardRange[]{
                    // add 14 as a valid length
                    new CreditCardRange("305", "4", new int[]{13, 14, 16}),
                }
            );
        assertTrue(ccv.isValid(VALID_DINERS));
    }

}
