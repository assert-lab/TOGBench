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

import java.util.Locale;

/**
 * Test Case for ByteValidator.
 * 
 * @version $Revision$
 */
public class ByteValidatorTest_OE25Dev extends AbstractNumberValidatorTest {

    private static final Byte BYTE_MIN_VAL = Byte.valueOf(Byte.MIN_VALUE);
    private static final Byte BYTE_MAX_VAL = Byte.valueOf(Byte.MAX_VALUE);
    private static final String BYTE_MAX   =  "127";
    private static final String BYTE_MAX_0 =  "127.99999999999999999999999"; // force double rounding
    private static final String BYTE_MAX_1 =  "128";
    private static final String BYTE_MIN   = "-128";
    private static final String BYTE_MIN_0 = "-128.99999999999999999999999"; // force double rounding";
    private static final String BYTE_MIN_1 = "-129";
    /**
     * Constructor
     * @param name test name
     */
    public ByteValidatorTest_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        validator       = new ByteValidator(false, 0);
        strictValidator = new ByteValidator();

        testPattern = "#,###";

        // testValidateMinMax()
        max = Byte.valueOf(Byte.MAX_VALUE);
        maxPlusOne = Long.valueOf(max.longValue() + 1);
        min = Byte.valueOf(Byte.MIN_VALUE);
        minMinusOne = Long.valueOf(min.longValue() - 1);

        // testInvalidStrict()
        invalidStrict = new String[] {null, "", "X", "X12", "12X", "1X2", "1.2", BYTE_MAX_1, BYTE_MIN_1, BYTE_MAX_0, BYTE_MIN_0};

        // testInvalidNotStrict()
        invalid       = new String[] {null, "", "X", "X12", BYTE_MAX_1, BYTE_MIN_1};

        // testValid()
        testNumber    = Byte.valueOf((byte)123);
        testZero      = Byte.valueOf((byte)0);
        validStrict          = new String[] {"0", "123", ",123", BYTE_MAX, BYTE_MIN};
        validStrictCompare   = new Number[] {testZero, testNumber, testNumber, BYTE_MAX_VAL, BYTE_MIN_VAL};
        valid                = new String[] {"0", "123", ",123", ",123.5", "123X", BYTE_MAX, BYTE_MIN, BYTE_MAX_0, BYTE_MIN_0};
        validCompare         = new Number[] {testZero, testNumber, testNumber, testNumber, testNumber, BYTE_MAX_VAL, BYTE_MIN_VAL, BYTE_MAX_VAL, BYTE_MIN_VAL};

        testStringUS = ",123";
        testStringDE = ".123";

        // Localized Pattern test
        localeValue = testStringDE;
        localePattern = "#.###";
        testLocale    = Locale.GERMANY;
        localeExpected = testNumber;

    }

    /**
     * Test ByteValidator validate Methods
     */

    /**
     * Test Byte Range/Min/Max
     */

public void testByteValidatorMethods_1_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00";
        String patternVal = "1,23";
        String germanPatternVal = "1.23";
        String localeVal  = ".123";
        String defaultVal = ",123";
        String XXXX    = "XXXX"; 
        Byte expected = Byte.valueOf((byte)123);
        assertEquals("validate(A) default", expected, ByteValidator.getInstance().validate(defaultVal));
    }

public void testByteValidatorMethods_2_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00";
        String patternVal = "1,23";
        String germanPatternVal = "1.23";
        String localeVal  = ".123";
        String defaultVal = ",123";
        String XXXX    = "XXXX"; 
        Byte expected = Byte.valueOf((byte)123);
        // removed other assertion
        assertEquals("validate(A) locale ", expected, ByteValidator.getInstance().validate(localeVal, locale));
    }

public void testByteValidatorMethods_3_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00";
        String patternVal = "1,23";
        String germanPatternVal = "1.23";
        String localeVal  = ".123";
        String defaultVal = ",123";
        String XXXX    = "XXXX"; 
        Byte expected = Byte.valueOf((byte)123);
        // removed other assertion
        // removed other assertion
        assertEquals("validate(A) pattern", expected, ByteValidator.getInstance().validate(patternVal, pattern));
    }

public void testByteValidatorMethods_4_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00";
        String patternVal = "1,23";
        String germanPatternVal = "1.23";
        String localeVal  = ".123";
        String defaultVal = ",123";
        String XXXX    = "XXXX"; 
        Byte expected = Byte.valueOf((byte)123);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("validate(A) both",    expected, ByteValidator.getInstance().validate(germanPatternVal, pattern, Locale.GERMAN));
    }

public void testByteValidatorMethods_5_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00";
        String patternVal = "1,23";
        String germanPatternVal = "1.23";
        String localeVal  = ".123";
        String defaultVal = ",123";
        String XXXX    = "XXXX"; 
        Byte expected = Byte.valueOf((byte)123);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue("isValid(A) default", ByteValidator.getInstance().isValid(defaultVal));
    }

public void testByteValidatorMethods_6_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00";
        String patternVal = "1,23";
        String germanPatternVal = "1.23";
        String localeVal  = ".123";
        String defaultVal = ",123";
        String XXXX    = "XXXX"; 
        Byte expected = Byte.valueOf((byte)123);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue("isValid(A) locale ", ByteValidator.getInstance().isValid(localeVal, locale));
    }

public void testByteValidatorMethods_7_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00";
        String patternVal = "1,23";
        String germanPatternVal = "1.23";
        String localeVal  = ".123";
        String defaultVal = ",123";
        String XXXX    = "XXXX"; 
        Byte expected = Byte.valueOf((byte)123);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue("isValid(A) pattern", ByteValidator.getInstance().isValid(patternVal, pattern));
    }

public void testByteValidatorMethods_8_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00";
        String patternVal = "1,23";
        String germanPatternVal = "1.23";
        String localeVal  = ".123";
        String defaultVal = ",123";
        String XXXX    = "XXXX"; 
        Byte expected = Byte.valueOf((byte)123);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("isValid(A) both",    ByteValidator.getInstance().isValid(germanPatternVal, pattern, Locale.GERMAN));
    }

public void testByteValidatorMethods_9_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00";
        String patternVal = "1,23";
        String germanPatternVal = "1.23";
        String localeVal  = ".123";
        String defaultVal = ",123";
        String XXXX    = "XXXX"; 
        Byte expected = Byte.valueOf((byte)123);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertNull("validate(B) default", ByteValidator.getInstance().validate(XXXX));
    }

public void testByteValidatorMethods_10_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00";
        String patternVal = "1,23";
        String germanPatternVal = "1.23";
        String localeVal  = ".123";
        String defaultVal = ",123";
        String XXXX    = "XXXX"; 
        Byte expected = Byte.valueOf((byte)123);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertNull("validate(B) locale ", ByteValidator.getInstance().validate(XXXX, locale));
    }

public void testByteValidatorMethods_11_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00";
        String patternVal = "1,23";
        String germanPatternVal = "1.23";
        String localeVal  = ".123";
        String defaultVal = ",123";
        String XXXX    = "XXXX"; 
        Byte expected = Byte.valueOf((byte)123);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertNull("validate(B) pattern", ByteValidator.getInstance().validate(XXXX, pattern));
    }

public void testByteValidatorMethods_12_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00";
        String patternVal = "1,23";
        String germanPatternVal = "1.23";
        String localeVal  = ".123";
        String defaultVal = ",123";
        String XXXX    = "XXXX"; 
        Byte expected = Byte.valueOf((byte)123);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull("validate(B) both",    ByteValidator.getInstance().validate(patternVal, pattern, Locale.GERMAN));
    }

public void testByteValidatorMethods_13_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00";
        String patternVal = "1,23";
        String germanPatternVal = "1.23";
        String localeVal  = ".123";
        String defaultVal = ",123";
        String XXXX    = "XXXX"; 
        Byte expected = Byte.valueOf((byte)123);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse("isValid(B) default", ByteValidator.getInstance().isValid(XXXX));
    }

public void testByteValidatorMethods_14_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00";
        String patternVal = "1,23";
        String germanPatternVal = "1.23";
        String localeVal  = ".123";
        String defaultVal = ",123";
        String XXXX    = "XXXX"; 
        Byte expected = Byte.valueOf((byte)123);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertFalse("isValid(B) locale ", ByteValidator.getInstance().isValid(XXXX, locale));
    }

public void testByteValidatorMethods_15_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00";
        String patternVal = "1,23";
        String germanPatternVal = "1.23";
        String localeVal  = ".123";
        String defaultVal = ",123";
        String XXXX    = "XXXX"; 
        Byte expected = Byte.valueOf((byte)123);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertFalse("isValid(B) pattern", ByteValidator.getInstance().isValid(XXXX, pattern));
    }

public void testByteValidatorMethods_16_oe() {
        Locale locale     = Locale.GERMAN;
        String pattern    = "0,00";
        String patternVal = "1,23";
        String germanPatternVal = "1.23";
        String localeVal  = ".123";
        String defaultVal = ",123";
        String XXXX    = "XXXX"; 
        Byte expected = Byte.valueOf((byte)123);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("isValid(B) both",    ByteValidator.getInstance().isValid(patternVal, pattern, Locale.GERMAN));
    }

public void testByteRangeMinMax_1_oe() {
        ByteValidator validator = (ByteValidator)strictValidator;
        Byte number9  = validator.validate("9", "#");
        Byte number10 = validator.validate("10", "#");
        Byte number11 = validator.validate("11", "#");
        Byte number19 = validator.validate("19", "#");
        Byte number20 = validator.validate("20", "#");
        Byte number21 = validator.validate("21", "#");
        byte min = (byte)10;
        byte max = (byte)20;

        // Test isInRange()
        assertFalse("isInRange() < min",   validator.isInRange(number9,  min, max));
    }

public void testByteRangeMinMax_2_oe() {
        ByteValidator validator = (ByteValidator)strictValidator;
        Byte number9  = validator.validate("9", "#");
        Byte number10 = validator.validate("10", "#");
        Byte number11 = validator.validate("11", "#");
        Byte number19 = validator.validate("19", "#");
        Byte number20 = validator.validate("20", "#");
        Byte number21 = validator.validate("21", "#");
        byte min = (byte)10;
        byte max = (byte)20;

        // Test isInRange()
        // removed other assertion
        assertTrue("isInRange() = min",    validator.isInRange(number10, min, max));
    }

public void testByteRangeMinMax_3_oe() {
        ByteValidator validator = (ByteValidator)strictValidator;
        Byte number9  = validator.validate("9", "#");
        Byte number10 = validator.validate("10", "#");
        Byte number11 = validator.validate("11", "#");
        Byte number19 = validator.validate("19", "#");
        Byte number20 = validator.validate("20", "#");
        Byte number21 = validator.validate("21", "#");
        byte min = (byte)10;
        byte max = (byte)20;

        // Test isInRange()
        // removed other assertion
        // removed other assertion
        assertTrue("isInRange() in range", validator.isInRange(number11, min, max));
    }

public void testByteRangeMinMax_4_oe() {
        ByteValidator validator = (ByteValidator)strictValidator;
        Byte number9  = validator.validate("9", "#");
        Byte number10 = validator.validate("10", "#");
        Byte number11 = validator.validate("11", "#");
        Byte number19 = validator.validate("19", "#");
        Byte number20 = validator.validate("20", "#");
        Byte number21 = validator.validate("21", "#");
        byte min = (byte)10;
        byte max = (byte)20;

        // Test isInRange()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("isInRange() = max",    validator.isInRange(number20, min, max));
    }

public void testByteRangeMinMax_5_oe() {
        ByteValidator validator = (ByteValidator)strictValidator;
        Byte number9  = validator.validate("9", "#");
        Byte number10 = validator.validate("10", "#");
        Byte number11 = validator.validate("11", "#");
        Byte number19 = validator.validate("19", "#");
        Byte number20 = validator.validate("20", "#");
        Byte number21 = validator.validate("21", "#");
        byte min = (byte)10;
        byte max = (byte)20;

        // Test isInRange()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("isInRange() > max",   validator.isInRange(number21, min, max));
    }

public void testByteRangeMinMax_6_oe() {
        ByteValidator validator = (ByteValidator)strictValidator;
        Byte number9  = validator.validate("9", "#");
        Byte number10 = validator.validate("10", "#");
        Byte number11 = validator.validate("11", "#");
        Byte number19 = validator.validate("19", "#");
        Byte number20 = validator.validate("20", "#");
        Byte number21 = validator.validate("21", "#");
        byte min = (byte)10;
        byte max = (byte)20;

        // Test isInRange()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test minValue()
        assertFalse("minValue() < min",    validator.minValue(number9,  min));
    }

public void testByteRangeMinMax_7_oe() {
        ByteValidator validator = (ByteValidator)strictValidator;
        Byte number9  = validator.validate("9", "#");
        Byte number10 = validator.validate("10", "#");
        Byte number11 = validator.validate("11", "#");
        Byte number19 = validator.validate("19", "#");
        Byte number20 = validator.validate("20", "#");
        Byte number21 = validator.validate("21", "#");
        byte min = (byte)10;
        byte max = (byte)20;

        // Test isInRange()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test minValue()
        // removed other assertion
        assertTrue("minValue() = min",     validator.minValue(number10, min));
    }

public void testByteRangeMinMax_8_oe() {
        ByteValidator validator = (ByteValidator)strictValidator;
        Byte number9  = validator.validate("9", "#");
        Byte number10 = validator.validate("10", "#");
        Byte number11 = validator.validate("11", "#");
        Byte number19 = validator.validate("19", "#");
        Byte number20 = validator.validate("20", "#");
        Byte number21 = validator.validate("21", "#");
        byte min = (byte)10;
        byte max = (byte)20;

        // Test isInRange()
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test minValue()
        // removed other assertion
        // removed other assertion
        assertTrue("minValue() > min",     validator.minValue(number11, min));
    }

public void testByteRangeMinMax_9_oe() {
        ByteValidator validator = (ByteValidator)strictValidator;
        Byte number9  = validator.validate("9", "#");
        Byte number10 = validator.validate("10", "#");
        Byte number11 = validator.validate("11", "#");
        Byte number19 = validator.validate("19", "#");
        Byte number20 = validator.validate("20", "#");
        Byte number21 = validator.validate("21", "#");
        byte min = (byte)10;
        byte max = (byte)20;

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
        assertTrue("maxValue() < max",     validator.maxValue(number19, max));
    }

public void testByteRangeMinMax_10_oe() {
        ByteValidator validator = (ByteValidator)strictValidator;
        Byte number9  = validator.validate("9", "#");
        Byte number10 = validator.validate("10", "#");
        Byte number11 = validator.validate("11", "#");
        Byte number19 = validator.validate("19", "#");
        Byte number20 = validator.validate("20", "#");
        Byte number21 = validator.validate("21", "#");
        byte min = (byte)10;
        byte max = (byte)20;

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
        assertTrue("maxValue() = max",     validator.maxValue(number20, max));
    }

public void testByteRangeMinMax_11_oe() {
        ByteValidator validator = (ByteValidator)strictValidator;
        Byte number9  = validator.validate("9", "#");
        Byte number10 = validator.validate("10", "#");
        Byte number11 = validator.validate("11", "#");
        Byte number19 = validator.validate("19", "#");
        Byte number20 = validator.validate("20", "#");
        Byte number21 = validator.validate("21", "#");
        byte min = (byte)10;
        byte max = (byte)20;

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
        assertFalse("maxValue() > max",    validator.maxValue(number21, max));
    }

}
