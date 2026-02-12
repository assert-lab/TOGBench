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

import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Test Case for FloatValidator.
 * 
 * @version $Revision$
 */
public class FloatValidatorTest_OE25Dev extends AbstractNumberValidatorTest {

    /**
     * Constructor
     * @param name test name
     */
    public FloatValidatorTest_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        validator       = new FloatValidator(false, 0);
        strictValidator = new FloatValidator();

        testPattern = "#,###.#";

        // testValidateMinMax()
        max = Float.valueOf(Float.MAX_VALUE);
        maxPlusOne = Double.valueOf(max.doubleValue() * 10);
        min = Float.valueOf(Float.MAX_VALUE * -1);
        minMinusOne = Double.valueOf(min.doubleValue() * 10);

        // testInvalidStrict()
        invalidStrict = new String[] {null, "", "X", "X12", "12X", "1X2"};

        // testInvalidNotStrict()
        invalid       = new String[] {null, "", "X", "X12"};

        // testValid()
        testNumber    = Float.valueOf(1234.5f);
        testZero      = Float.valueOf(0);
        validStrict          = new String[] {"0", "1234.5", "1,234.5"};
        validStrictCompare   = new Number[] {testZero, testNumber, testNumber};
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
     * Test FloatValidator validate Methods
     */

    /**
     * Test Float validation for values too small to handle.
     * (slightly different from max/min which are the largest +ve/-ve
     */

    /**
     * Test Float Range/Min/Max
     */

    public void testFloatValidatorMethods_1_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00,00";
        String patternVal = "1,23,45";
        String localeVal  = "12.345";
        String germanPatternVal = "1.23.45";
        String defaultVal = "12,345";
        String XXXX    = "XXXX"; 
        Float expected = Float.valueOf(12345);
        assertEquals("validate(A) default", expected, FloatValidator.getInstance().validate(defaultVal));
    }

    public void testFloatValidatorMethods_2_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00,00";
        String patternVal = "1,23,45";
        String localeVal  = "12.345";
        String germanPatternVal = "1.23.45";
        String defaultVal = "12,345";
        String XXXX    = "XXXX"; 
        Float expected = Float.valueOf(12345);
        // removed other assertion
        assertEquals("validate(A) locale ", expected, FloatValidator.getInstance().validate(localeVal, locale));
    }

    public void testFloatValidatorMethods_3_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00,00";
        String patternVal = "1,23,45";
        String localeVal  = "12.345";
        String germanPatternVal = "1.23.45";
        String defaultVal = "12,345";
        String XXXX    = "XXXX"; 
        Float expected = Float.valueOf(12345);
        // removed other assertion
        // removed other assertion
        assertEquals("validate(A) pattern", expected, FloatValidator.getInstance().validate(patternVal, pattern));
    }

    public void testFloatValidatorMethods_4_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00,00";
        String patternVal = "1,23,45";
        String localeVal  = "12.345";
        String germanPatternVal = "1.23.45";
        String defaultVal = "12,345";
        String XXXX    = "XXXX"; 
        Float expected = Float.valueOf(12345);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("validate(A) both",    expected, FloatValidator.getInstance().validate(germanPatternVal, pattern, Locale.GERMAN));
    }

    public void testFloatValidatorMethods_5_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00,00";
        String patternVal = "1,23,45";
        String localeVal  = "12.345";
        String germanPatternVal = "1.23.45";
        String defaultVal = "12,345";
        String XXXX    = "XXXX"; 
        Float expected = Float.valueOf(12345);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue("isValid(A) default", FloatValidator.getInstance().isValid(defaultVal));
    }

    public void testFloatValidatorMethods_6_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00,00";
        String patternVal = "1,23,45";
        String localeVal  = "12.345";
        String germanPatternVal = "1.23.45";
        String defaultVal = "12,345";
        String XXXX    = "XXXX"; 
        Float expected = Float.valueOf(12345);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue("isValid(A) locale ", FloatValidator.getInstance().isValid(localeVal, locale));
    }

    public void testFloatValidatorMethods_7_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00,00";
        String patternVal = "1,23,45";
        String localeVal  = "12.345";
        String germanPatternVal = "1.23.45";
        String defaultVal = "12,345";
        String XXXX    = "XXXX"; 
        Float expected = Float.valueOf(12345);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue("isValid(A) pattern", FloatValidator.getInstance().isValid(patternVal, pattern));
    }

    public void testFloatValidatorMethods_8_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00,00";
        String patternVal = "1,23,45";
        String localeVal  = "12.345";
        String germanPatternVal = "1.23.45";
        String defaultVal = "12,345";
        String XXXX    = "XXXX"; 
        Float expected = Float.valueOf(12345);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("isValid(A) both",    FloatValidator.getInstance().isValid(germanPatternVal, pattern, Locale.GERMAN));
    }

    public void testFloatValidatorMethods_9_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00,00";
        String patternVal = "1,23,45";
        String localeVal  = "12.345";
        String germanPatternVal = "1.23.45";
        String defaultVal = "12,345";
        String XXXX    = "XXXX"; 
        Float expected = Float.valueOf(12345);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertNull("validate(B) default", FloatValidator.getInstance().validate(XXXX));
    }

    public void testFloatValidatorMethods_10_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00,00";
        String patternVal = "1,23,45";
        String localeVal  = "12.345";
        String germanPatternVal = "1.23.45";
        String defaultVal = "12,345";
        String XXXX    = "XXXX"; 
        Float expected = Float.valueOf(12345);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertNull("validate(B) locale ", FloatValidator.getInstance().validate(XXXX, locale));
    }

    public void testFloatValidatorMethods_11_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00,00";
        String patternVal = "1,23,45";
        String localeVal  = "12.345";
        String germanPatternVal = "1.23.45";
        String defaultVal = "12,345";
        String XXXX    = "XXXX"; 
        Float expected = Float.valueOf(12345);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertNull("validate(B) pattern", FloatValidator.getInstance().validate(XXXX, pattern));
    }

    public void testFloatValidatorMethods_12_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00,00";
        String patternVal = "1,23,45";
        String localeVal  = "12.345";
        String germanPatternVal = "1.23.45";
        String defaultVal = "12,345";
        String XXXX    = "XXXX"; 
        Float expected = Float.valueOf(12345);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull("validate(B) both",    FloatValidator.getInstance().validate(patternVal, pattern, Locale.GERMAN));
    }

    public void testFloatValidatorMethods_13_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00,00";
        String patternVal = "1,23,45";
        String localeVal  = "12.345";
        String germanPatternVal = "1.23.45";
        String defaultVal = "12,345";
        String XXXX    = "XXXX"; 
        Float expected = Float.valueOf(12345);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse("isValid(B) default", FloatValidator.getInstance().isValid(XXXX));
    }

    public void testFloatValidatorMethods_14_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00,00";
        String patternVal = "1,23,45";
        String localeVal  = "12.345";
        String germanPatternVal = "1.23.45";
        String defaultVal = "12,345";
        String XXXX    = "XXXX"; 
        Float expected = Float.valueOf(12345);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse("isValid(B) locale ", FloatValidator.getInstance().isValid(XXXX, locale));
    }

    public void testFloatValidatorMethods_15_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00,00";
        String patternVal = "1,23,45";
        String localeVal  = "12.345";
        String germanPatternVal = "1.23.45";
        String defaultVal = "12,345";
        String XXXX    = "XXXX"; 
        Float expected = Float.valueOf(12345);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse("isValid(B) pattern", FloatValidator.getInstance().isValid(XXXX, pattern));
    }

    public void testFloatValidatorMethods_16_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00,00";
        String patternVal = "1,23,45";
        String localeVal  = "12.345";
        String germanPatternVal = "1.23.45";
        String defaultVal = "12,345";
        String XXXX    = "XXXX"; 
        Float expected = Float.valueOf(12345);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("isValid(B) both",    FloatValidator.getInstance().isValid(patternVal, pattern, Locale.GERMAN));
    }

    public void testFloatSmallestValues_1_oe() {
        String pattern = "#.#################################################################";
        DecimalFormat fmt = new DecimalFormat(pattern);

        // Validate Smallest +ve value
        Float smallestPositive  = Float.valueOf(Float.MIN_VALUE);
        String strSmallestPositive = fmt.format(smallestPositive);
        assertEquals("Smallest +ve", smallestPositive, FloatValidator.getInstance().validate(strSmallestPositive, pattern));
    }

    public void testFloatSmallestValues_2_oe() {
        String pattern = "#.#################################################################";
        DecimalFormat fmt = new DecimalFormat(pattern);

        // Validate Smallest +ve value
        Float smallestPositive  = Float.valueOf(Float.MIN_VALUE);
        String strSmallestPositive = fmt.format(smallestPositive);
        // removed other assertion

        // Validate Smallest -ve value
        Float smallestNegative  = Float.valueOf(Float.MIN_VALUE * -1);
        String strSmallestNegative = fmt.format(smallestNegative);
        assertEquals("Smallest -ve", smallestNegative, FloatValidator.getInstance().validate(strSmallestNegative, pattern));
    }

    public void testFloatSmallestValues_3_oe() {
        String pattern = "#.#################################################################";
        DecimalFormat fmt = new DecimalFormat(pattern);

        // Validate Smallest +ve value
        Float smallestPositive  = Float.valueOf(Float.MIN_VALUE);
        String strSmallestPositive = fmt.format(smallestPositive);
        // removed other assertion

        // Validate Smallest -ve value
        Float smallestNegative  = Float.valueOf(Float.MIN_VALUE * -1);
        String strSmallestNegative = fmt.format(smallestNegative);
        // removed other assertion

        // Validate Too Small +ve
        Double tooSmallPositive = Double.valueOf(((double)Float.MIN_VALUE / (double)10)); 
        String strTooSmallPositive = fmt.format(tooSmallPositive);
        assertFalse("Too small +ve", FloatValidator.getInstance().isValid(strTooSmallPositive, pattern));
    }

    public void testFloatSmallestValues_4_oe() {
        String pattern = "#.#################################################################";
        DecimalFormat fmt = new DecimalFormat(pattern);

        // Validate Smallest +ve value
        Float smallestPositive  = Float.valueOf(Float.MIN_VALUE);
        String strSmallestPositive = fmt.format(smallestPositive);
        // removed other assertion

        // Validate Smallest -ve value
        Float smallestNegative  = Float.valueOf(Float.MIN_VALUE * -1);
        String strSmallestNegative = fmt.format(smallestNegative);
        // removed other assertion

        // Validate Too Small +ve
        Double tooSmallPositive = Double.valueOf(((double)Float.MIN_VALUE / (double)10)); 
        String strTooSmallPositive = fmt.format(tooSmallPositive);
        // removed other assertion

        // Validate Too Small -ve
        Double tooSmallNegative = Double.valueOf(tooSmallPositive.doubleValue() * -1);
        String strTooSmallNegative = fmt.format(tooSmallNegative);
        assertFalse("Too small -ve", FloatValidator.getInstance().isValid(strTooSmallNegative, pattern));
    }

    public void testFloatRangeMinMax_1_oe() {
        FloatValidator validator = (FloatValidator)strictValidator;
        Float number9  = validator.validate("9", "#");
        Float number10 = validator.validate("10", "#");
        Float number11 = validator.validate("11", "#");
        Float number19 = validator.validate("19", "#");
        Float number20 = validator.validate("20", "#");
        Float number21 = validator.validate("21", "#");

        // Test isInRange()
        assertFalse("isInRange() < min",   validator.isInRange(number9,  10, 20));
    }

    public void testFloatRangeMinMax_2_oe() {
        FloatValidator validator = (FloatValidator)strictValidator;
        Float number9  = validator.validate("9", "#");
        Float number10 = validator.validate("10", "#");
        Float number11 = validator.validate("11", "#");
        Float number19 = validator.validate("19", "#");
        Float number20 = validator.validate("20", "#");
        Float number21 = validator.validate("21", "#");

        // Test isInRange()
        // removed other assertion
        assertTrue("isInRange() = min",    validator.isInRange(number10, 10, 20));
    }

    public void testFloatRangeMinMax_3_oe() {
        FloatValidator validator = (FloatValidator)strictValidator;
        Float number9  = validator.validate("9", "#");
        Float number10 = validator.validate("10", "#");
        Float number11 = validator.validate("11", "#");
        Float number19 = validator.validate("19", "#");
        Float number20 = validator.validate("20", "#");
        Float number21 = validator.validate("21", "#");

        // Test isInRange()
        // removed other assertion
        // removed other assertion
        assertTrue("isInRange() in range", validator.isInRange(number11, 10, 20));
    }

    public void testFloatRangeMinMax_4_oe() {
        FloatValidator validator = (FloatValidator)strictValidator;
        Float number9  = validator.validate("9", "#");
        Float number10 = validator.validate("10", "#");
        Float number11 = validator.validate("11", "#");
        Float number19 = validator.validate("19", "#");
        Float number20 = validator.validate("20", "#");
        Float number21 = validator.validate("21", "#");

        // Test isInRange()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("isInRange() = max",    validator.isInRange(number20, 10, 20));
    }

    public void testFloatRangeMinMax_5_oe() {
        FloatValidator validator = (FloatValidator)strictValidator;
        Float number9  = validator.validate("9", "#");
        Float number10 = validator.validate("10", "#");
        Float number11 = validator.validate("11", "#");
        Float number19 = validator.validate("19", "#");
        Float number20 = validator.validate("20", "#");
        Float number21 = validator.validate("21", "#");

        // Test isInRange()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("isInRange() > max",   validator.isInRange(number21, 10, 20));
    }

    public void testFloatRangeMinMax_6_oe() {
        FloatValidator validator = (FloatValidator)strictValidator;
        Float number9  = validator.validate("9", "#");
        Float number10 = validator.validate("10", "#");
        Float number11 = validator.validate("11", "#");
        Float number19 = validator.validate("19", "#");
        Float number20 = validator.validate("20", "#");
        Float number21 = validator.validate("21", "#");

        // Test isInRange()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test minValue()
        assertFalse("minValue() < min",    validator.minValue(number9,  10));
    }

    public void testFloatRangeMinMax_7_oe() {
        FloatValidator validator = (FloatValidator)strictValidator;
        Float number9  = validator.validate("9", "#");
        Float number10 = validator.validate("10", "#");
        Float number11 = validator.validate("11", "#");
        Float number19 = validator.validate("19", "#");
        Float number20 = validator.validate("20", "#");
        Float number21 = validator.validate("21", "#");

        // Test isInRange()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test minValue()
        // removed other assertion
        assertTrue("minValue() = min",     validator.minValue(number10, 10));
    }

    public void testFloatRangeMinMax_8_oe() {
        FloatValidator validator = (FloatValidator)strictValidator;
        Float number9  = validator.validate("9", "#");
        Float number10 = validator.validate("10", "#");
        Float number11 = validator.validate("11", "#");
        Float number19 = validator.validate("19", "#");
        Float number20 = validator.validate("20", "#");
        Float number21 = validator.validate("21", "#");

        // Test isInRange()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test minValue()
        // removed other assertion
        // removed other assertion
        assertTrue("minValue() > min",     validator.minValue(number11, 10));
    }

    public void testFloatRangeMinMax_9_oe() {
        FloatValidator validator = (FloatValidator)strictValidator;
        Float number9  = validator.validate("9", "#");
        Float number10 = validator.validate("10", "#");
        Float number11 = validator.validate("11", "#");
        Float number19 = validator.validate("19", "#");
        Float number20 = validator.validate("20", "#");
        Float number21 = validator.validate("21", "#");

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
        assertTrue("maxValue() < max",     validator.maxValue(number19, 20));
    }

    public void testFloatRangeMinMax_10_oe() {
        FloatValidator validator = (FloatValidator)strictValidator;
        Float number9  = validator.validate("9", "#");
        Float number10 = validator.validate("10", "#");
        Float number11 = validator.validate("11", "#");
        Float number19 = validator.validate("19", "#");
        Float number20 = validator.validate("20", "#");
        Float number21 = validator.validate("21", "#");

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
        assertTrue("maxValue() = max",     validator.maxValue(number20, 20));
    }

    public void testFloatRangeMinMax_11_oe() {
        FloatValidator validator = (FloatValidator)strictValidator;
        Float number9  = validator.validate("9", "#");
        Float number10 = validator.validate("10", "#");
        Float number11 = validator.validate("11", "#");
        Float number19 = validator.validate("19", "#");
        Float number20 = validator.validate("20", "#");
        Float number21 = validator.validate("21", "#");

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
        assertFalse("maxValue() > max",    validator.maxValue(number21, 20));
    }

}
