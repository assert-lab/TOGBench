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

import java.math.BigDecimal;
import java.util.Locale;

/**
 * Test Case for BigDecimalValidator.
 * 
 * @version $Revision$
 */
public class BigDecimalValidatorTest_OE25Dev extends AbstractNumberValidatorTest {

    /**
     * Constructor
     * @param name test name
     */
    public BigDecimalValidatorTest_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        validator       = new BigDecimalValidator(false);
        strictValidator = new BigDecimalValidator();

        testPattern = "#,###.###";

        // testValidateMinMax()
        max = null;
        maxPlusOne = null;
        min = null;
        minMinusOne = null;

        // testInvalidStrict()
        invalidStrict = new String[] {null, "", "X", "X12", "12X", "1X2", "1.234X"};

        // testInvalidNotStrict()
        invalid       = new String[] {null, "", "X", "X12"};

        // testValid()
        testNumber    = new BigDecimal("1234.5");
        Number testNumber2 = new BigDecimal(".1");
        Number testNumber3 = new BigDecimal("12345.67899");
        testZero      = new BigDecimal("0");
        validStrict          = new String[] {"0", "1234.5", "1,234.5", ".1", "12345.678990"};
        validStrictCompare   = new Number[] {testZero, testNumber, testNumber, testNumber2, testNumber3};
        valid                = new String[] {"0", "1234.5", "1,234.5", "1,234.5", "1234.5X"};
        validCompare         = new Number[] {testZero, testNumber, testNumber, testNumber, testNumber};

        testStringUS = "1,234.5";
        testStringDE = "1.234,5";

        // Localized Pattern test
        localeValue = testStringDE;
        localePattern = "#.###,#";
        testLocale    = Locale.GERMANY;
        localeExpected = testNumber;

    }

    /**
     * Test BigDecimalValidator validate Methods
     */

    /**
     * Test BigDecimal Range/Min/Max
     */

    public void testBigDecimalValidatorMethods_1_oe() {
        Locale locale           = Locale.GERMAN;
        String pattern          = "0,00,00";
        String patternVal       = "1,23,45";
        String germanPatternVal = "1.23.45";
        String localeVal        = "12.345";
        String defaultVal       = "12,345";
        String XXXX             = "XXXX"; 
        BigDecimal expected = new BigDecimal(12345);
        assertEquals("validate(A) default", expected, BigDecimalValidator.getInstance().validate(defaultVal));
    }

    public void testBigDecimalValidatorMethods_2_oe() {
        Locale locale           = Locale.GERMAN;
        String pattern          = "0,00,00";
        String patternVal       = "1,23,45";
        String germanPatternVal = "1.23.45";
        String localeVal        = "12.345";
        String defaultVal       = "12,345";
        String XXXX             = "XXXX"; 
        BigDecimal expected = new BigDecimal(12345);
        // removed other assertion
        assertEquals("validate(A) locale ", expected, BigDecimalValidator.getInstance().validate(localeVal, locale));
    }

    public void testBigDecimalValidatorMethods_3_oe() {
        Locale locale           = Locale.GERMAN;
        String pattern          = "0,00,00";
        String patternVal       = "1,23,45";
        String germanPatternVal = "1.23.45";
        String localeVal        = "12.345";
        String defaultVal       = "12,345";
        String XXXX             = "XXXX"; 
        BigDecimal expected = new BigDecimal(12345);
        // removed other assertion
        // removed other assertion
        assertEquals("validate(A) pattern", expected, BigDecimalValidator.getInstance().validate(patternVal, pattern));
    }

    public void testBigDecimalValidatorMethods_4_oe() {
        Locale locale           = Locale.GERMAN;
        String pattern          = "0,00,00";
        String patternVal       = "1,23,45";
        String germanPatternVal = "1.23.45";
        String localeVal        = "12.345";
        String defaultVal       = "12,345";
        String XXXX             = "XXXX"; 
        BigDecimal expected = new BigDecimal(12345);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("validate(A) both",    expected, BigDecimalValidator.getInstance().validate(germanPatternVal, pattern, Locale.GERMAN));
    }

    public void testBigDecimalValidatorMethods_5_oe() {
        Locale locale           = Locale.GERMAN;
        String pattern          = "0,00,00";
        String patternVal       = "1,23,45";
        String germanPatternVal = "1.23.45";
        String localeVal        = "12.345";
        String defaultVal       = "12,345";
        String XXXX             = "XXXX"; 
        BigDecimal expected = new BigDecimal(12345);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue("isValid(A) default", BigDecimalValidator.getInstance().isValid(defaultVal));
    }

    public void testBigDecimalValidatorMethods_6_oe() {
        Locale locale           = Locale.GERMAN;
        String pattern          = "0,00,00";
        String patternVal       = "1,23,45";
        String germanPatternVal = "1.23.45";
        String localeVal        = "12.345";
        String defaultVal       = "12,345";
        String XXXX             = "XXXX"; 
        BigDecimal expected = new BigDecimal(12345);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue("isValid(A) locale ", BigDecimalValidator.getInstance().isValid(localeVal, locale));
    }

    public void testBigDecimalValidatorMethods_7_oe() {
        Locale locale           = Locale.GERMAN;
        String pattern          = "0,00,00";
        String patternVal       = "1,23,45";
        String germanPatternVal = "1.23.45";
        String localeVal        = "12.345";
        String defaultVal       = "12,345";
        String XXXX             = "XXXX"; 
        BigDecimal expected = new BigDecimal(12345);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue("isValid(A) pattern", BigDecimalValidator.getInstance().isValid(patternVal, pattern));
    }

    public void testBigDecimalValidatorMethods_8_oe() {
        Locale locale           = Locale.GERMAN;
        String pattern          = "0,00,00";
        String patternVal       = "1,23,45";
        String germanPatternVal = "1.23.45";
        String localeVal        = "12.345";
        String defaultVal       = "12,345";
        String XXXX             = "XXXX"; 
        BigDecimal expected = new BigDecimal(12345);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("isValid(A) both",    BigDecimalValidator.getInstance().isValid(germanPatternVal, pattern, Locale.GERMAN));
    }

    public void testBigDecimalValidatorMethods_9_oe() {
        Locale locale           = Locale.GERMAN;
        String pattern          = "0,00,00";
        String patternVal       = "1,23,45";
        String germanPatternVal = "1.23.45";
        String localeVal        = "12.345";
        String defaultVal       = "12,345";
        String XXXX             = "XXXX"; 
        BigDecimal expected = new BigDecimal(12345);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertNull("validate(B) default", BigDecimalValidator.getInstance().validate(XXXX));
    }

    public void testBigDecimalValidatorMethods_10_oe() {
        Locale locale           = Locale.GERMAN;
        String pattern          = "0,00,00";
        String patternVal       = "1,23,45";
        String germanPatternVal = "1.23.45";
        String localeVal        = "12.345";
        String defaultVal       = "12,345";
        String XXXX             = "XXXX"; 
        BigDecimal expected = new BigDecimal(12345);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertNull("validate(B) locale ", BigDecimalValidator.getInstance().validate(XXXX, locale));
    }

    public void testBigDecimalValidatorMethods_11_oe() {
        Locale locale           = Locale.GERMAN;
        String pattern          = "0,00,00";
        String patternVal       = "1,23,45";
        String germanPatternVal = "1.23.45";
        String localeVal        = "12.345";
        String defaultVal       = "12,345";
        String XXXX             = "XXXX"; 
        BigDecimal expected = new BigDecimal(12345);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertNull("validate(B) pattern", BigDecimalValidator.getInstance().validate(XXXX, pattern));
    }

    public void testBigDecimalValidatorMethods_12_oe() {
        Locale locale           = Locale.GERMAN;
        String pattern          = "0,00,00";
        String patternVal       = "1,23,45";
        String germanPatternVal = "1.23.45";
        String localeVal        = "12.345";
        String defaultVal       = "12,345";
        String XXXX             = "XXXX"; 
        BigDecimal expected = new BigDecimal(12345);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull("validate(B) both",    BigDecimalValidator.getInstance().validate(patternVal, pattern, Locale.GERMAN));
    }

    public void testBigDecimalValidatorMethods_13_oe() {
        Locale locale           = Locale.GERMAN;
        String pattern          = "0,00,00";
        String patternVal       = "1,23,45";
        String germanPatternVal = "1.23.45";
        String localeVal        = "12.345";
        String defaultVal       = "12,345";
        String XXXX             = "XXXX"; 
        BigDecimal expected = new BigDecimal(12345);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse("isValid(B) default", BigDecimalValidator.getInstance().isValid(XXXX));
    }

    public void testBigDecimalValidatorMethods_14_oe() {
        Locale locale           = Locale.GERMAN;
        String pattern          = "0,00,00";
        String patternVal       = "1,23,45";
        String germanPatternVal = "1.23.45";
        String localeVal        = "12.345";
        String defaultVal       = "12,345";
        String XXXX             = "XXXX"; 
        BigDecimal expected = new BigDecimal(12345);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse("isValid(B) locale ", BigDecimalValidator.getInstance().isValid(XXXX, locale));
    }

    public void testBigDecimalValidatorMethods_15_oe() {
        Locale locale           = Locale.GERMAN;
        String pattern          = "0,00,00";
        String patternVal       = "1,23,45";
        String germanPatternVal = "1.23.45";
        String localeVal        = "12.345";
        String defaultVal       = "12,345";
        String XXXX             = "XXXX"; 
        BigDecimal expected = new BigDecimal(12345);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse("isValid(B) pattern", BigDecimalValidator.getInstance().isValid(XXXX, pattern));
    }

    public void testBigDecimalValidatorMethods_16_oe() {
        Locale locale           = Locale.GERMAN;
        String pattern          = "0,00,00";
        String patternVal       = "1,23,45";
        String germanPatternVal = "1.23.45";
        String localeVal        = "12.345";
        String defaultVal       = "12,345";
        String XXXX             = "XXXX"; 
        BigDecimal expected = new BigDecimal(12345);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("isValid(B) both",    BigDecimalValidator.getInstance().isValid(patternVal, pattern, Locale.GERMAN));
    }

    public void testBigDecimalRangeMinMax_1_oe() {
        BigDecimalValidator validator = new BigDecimalValidator(true, AbstractNumberValidator.STANDARD_FORMAT, true);
        BigDecimal number9  = new BigDecimal("9");
        BigDecimal number10 = new BigDecimal("10");
        BigDecimal number11 = new BigDecimal("11");
        BigDecimal number19 = new BigDecimal("19");
        BigDecimal number20 = new BigDecimal("20");
        BigDecimal number21 = new BigDecimal("21");
        
        float min = 10;
        float max = 20;

        // Test isInRange()
        assertFalse("isInRange(A) < min",   validator.isInRange(number9,  min, max));
    }

    public void testBigDecimalRangeMinMax_2_oe() {
        BigDecimalValidator validator = new BigDecimalValidator(true, AbstractNumberValidator.STANDARD_FORMAT, true);
        BigDecimal number9  = new BigDecimal("9");
        BigDecimal number10 = new BigDecimal("10");
        BigDecimal number11 = new BigDecimal("11");
        BigDecimal number19 = new BigDecimal("19");
        BigDecimal number20 = new BigDecimal("20");
        BigDecimal number21 = new BigDecimal("21");
        
        float min = 10;
        float max = 20;

        // Test isInRange()
        // removed other assertion
        assertTrue("isInRange(A) = min",    validator.isInRange(number10, min, max));
    }

    public void testBigDecimalRangeMinMax_3_oe() {
        BigDecimalValidator validator = new BigDecimalValidator(true, AbstractNumberValidator.STANDARD_FORMAT, true);
        BigDecimal number9  = new BigDecimal("9");
        BigDecimal number10 = new BigDecimal("10");
        BigDecimal number11 = new BigDecimal("11");
        BigDecimal number19 = new BigDecimal("19");
        BigDecimal number20 = new BigDecimal("20");
        BigDecimal number21 = new BigDecimal("21");
        
        float min = 10;
        float max = 20;

        // Test isInRange()
        // removed other assertion
        // removed other assertion
        assertTrue("isInRange(A) in range", validator.isInRange(number11, min, max));
    }

    public void testBigDecimalRangeMinMax_4_oe() {
        BigDecimalValidator validator = new BigDecimalValidator(true, AbstractNumberValidator.STANDARD_FORMAT, true);
        BigDecimal number9  = new BigDecimal("9");
        BigDecimal number10 = new BigDecimal("10");
        BigDecimal number11 = new BigDecimal("11");
        BigDecimal number19 = new BigDecimal("19");
        BigDecimal number20 = new BigDecimal("20");
        BigDecimal number21 = new BigDecimal("21");
        
        float min = 10;
        float max = 20;

        // Test isInRange()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("isInRange(A) = max",    validator.isInRange(number20, min, max));
    }

    public void testBigDecimalRangeMinMax_5_oe() {
        BigDecimalValidator validator = new BigDecimalValidator(true, AbstractNumberValidator.STANDARD_FORMAT, true);
        BigDecimal number9  = new BigDecimal("9");
        BigDecimal number10 = new BigDecimal("10");
        BigDecimal number11 = new BigDecimal("11");
        BigDecimal number19 = new BigDecimal("19");
        BigDecimal number20 = new BigDecimal("20");
        BigDecimal number21 = new BigDecimal("21");
        
        float min = 10;
        float max = 20;

        // Test isInRange()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("isInRange(A) > max",   validator.isInRange(number21, min, max));
    }

    public void testBigDecimalRangeMinMax_6_oe() {
        BigDecimalValidator validator = new BigDecimalValidator(true, AbstractNumberValidator.STANDARD_FORMAT, true);
        BigDecimal number9  = new BigDecimal("9");
        BigDecimal number10 = new BigDecimal("10");
        BigDecimal number11 = new BigDecimal("11");
        BigDecimal number19 = new BigDecimal("19");
        BigDecimal number20 = new BigDecimal("20");
        BigDecimal number21 = new BigDecimal("21");
        
        float min = 10;
        float max = 20;

        // Test isInRange()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test minValue()
        assertFalse("minValue(A) < min",    validator.minValue(number9,  min));
    }

    public void testBigDecimalRangeMinMax_7_oe() {
        BigDecimalValidator validator = new BigDecimalValidator(true, AbstractNumberValidator.STANDARD_FORMAT, true);
        BigDecimal number9  = new BigDecimal("9");
        BigDecimal number10 = new BigDecimal("10");
        BigDecimal number11 = new BigDecimal("11");
        BigDecimal number19 = new BigDecimal("19");
        BigDecimal number20 = new BigDecimal("20");
        BigDecimal number21 = new BigDecimal("21");
        
        float min = 10;
        float max = 20;

        // Test isInRange()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test minValue()
        // removed other assertion
        assertTrue("minValue(A) = min",     validator.minValue(number10, min));
    }

    public void testBigDecimalRangeMinMax_8_oe() {
        BigDecimalValidator validator = new BigDecimalValidator(true, AbstractNumberValidator.STANDARD_FORMAT, true);
        BigDecimal number9  = new BigDecimal("9");
        BigDecimal number10 = new BigDecimal("10");
        BigDecimal number11 = new BigDecimal("11");
        BigDecimal number19 = new BigDecimal("19");
        BigDecimal number20 = new BigDecimal("20");
        BigDecimal number21 = new BigDecimal("21");
        
        float min = 10;
        float max = 20;

        // Test isInRange()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test minValue()
        // removed other assertion
        // removed other assertion
        assertTrue("minValue(A) > min",     validator.minValue(number11, min));
    }

    public void testBigDecimalRangeMinMax_9_oe() {
        BigDecimalValidator validator = new BigDecimalValidator(true, AbstractNumberValidator.STANDARD_FORMAT, true);
        BigDecimal number9  = new BigDecimal("9");
        BigDecimal number10 = new BigDecimal("10");
        BigDecimal number11 = new BigDecimal("11");
        BigDecimal number19 = new BigDecimal("19");
        BigDecimal number20 = new BigDecimal("20");
        BigDecimal number21 = new BigDecimal("21");
        
        float min = 10;
        float max = 20;

        // Test isInRange()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test minValue()
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test minValue()
        assertTrue("maxValue(A) < max",     validator.maxValue(number19, max));
    }

    public void testBigDecimalRangeMinMax_10_oe() {
        BigDecimalValidator validator = new BigDecimalValidator(true, AbstractNumberValidator.STANDARD_FORMAT, true);
        BigDecimal number9  = new BigDecimal("9");
        BigDecimal number10 = new BigDecimal("10");
        BigDecimal number11 = new BigDecimal("11");
        BigDecimal number19 = new BigDecimal("19");
        BigDecimal number20 = new BigDecimal("20");
        BigDecimal number21 = new BigDecimal("21");
        
        float min = 10;
        float max = 20;

        // Test isInRange()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test minValue()
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test minValue()
        // removed other assertion
        assertTrue("maxValue(A) = max",     validator.maxValue(number20, max));
    }

    public void testBigDecimalRangeMinMax_11_oe() {
        BigDecimalValidator validator = new BigDecimalValidator(true, AbstractNumberValidator.STANDARD_FORMAT, true);
        BigDecimal number9  = new BigDecimal("9");
        BigDecimal number10 = new BigDecimal("10");
        BigDecimal number11 = new BigDecimal("11");
        BigDecimal number19 = new BigDecimal("19");
        BigDecimal number20 = new BigDecimal("20");
        BigDecimal number21 = new BigDecimal("21");
        
        float min = 10;
        float max = 20;

        // Test isInRange()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test minValue()
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test minValue()
        // removed other assertion
        // removed other assertion
        assertFalse("maxValue(A) > max",    validator.maxValue(number21, max));
    }

}
