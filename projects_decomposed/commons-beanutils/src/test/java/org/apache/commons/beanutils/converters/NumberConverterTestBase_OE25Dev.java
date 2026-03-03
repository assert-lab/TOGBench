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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import junit.framework.TestCase;

import org.apache.commons.beanutils.ConversionException;


/**
 * Abstract base for &lt;Number&gt;Converter classes.
 *
 * @version $Id$
 */

public abstract class NumberConverterTestBase_OE25Dev extends TestCase {

    /** Test Number values */
    protected Number[] numbers = new Number[4];

    // ------------------------------------------------------------------------

    public NumberConverterTestBase_OE25Dev(final String name) {
        super(name);
    }

    // ------------------------------------------------------------------------

    protected abstract NumberConverter makeConverter();
    protected abstract NumberConverter makeConverter(Object defaultValue);
    protected abstract Class<?> getExpectedType();

    // ------------------------------------------------------------------------

    /**
     * Assumes ConversionException in response to covert(getExpectedType(),null).
     */
    public void testConvertNull() {
        try {
            makeConverter().convert(getExpectedType(),null);
            fail("Expected ConversionException");
        } catch(final ConversionException e) {
            // expected
        }
    }

    /**
     * Assumes convert(getExpectedType(),Number) returns some non-null
     * instance of getExpectedType().
     */
    /**
     * Convert Number --> String (using a Pattern, with default and specified Locales)
     */

    /**
     * Convert Number --> String (using default and specified Locales)
     */

    /**
     * Convert Array --> Number
     */

    /**
     * Convert Number --> String (default conversion)
     */

    /**
     * Convert String --> Number (using a Pattern, with default and specified Locales)
     */

    /**
     * Convert String --> Number (using default and specified Locales)
     */

    /**
     * Convert String --> Number (default conversion)
     */

    /**
     * Convert String --> Number if the target type is not defined. Then the
     * default type should be used.
     */

    /**
     * Convert Boolean --> Number (default conversion)
     */

    /**
     * Convert Date --> Long
     */

    /**
     * Convert Calendar --> Long
     */

    /**
     * Convert Other --> String (default conversion)
     */

    /**
     * Convert Number --> String (using default and specified Locales)
     */

    /**
     * Convert Number --> String (using default and specified Locales)
     */
    public void testInvalidException() {

        final NumberConverter converter = makeConverter();

        try {
            converter.convert(getExpectedType(), null);
            fail("Null test, expected ConversionException");
        } catch (final ConversionException e) {
            // expected result
        }
        try {
            converter.convert(getExpectedType(), "XXXX");
            fail("Invalid test, expected ConversionException");
        } catch (final ConversionException e) {
            // expected result
        }
    }

    /**
     * Test specifying an invalid type.
     */
    public void testInvalidType() {

        final NumberConverter converter = makeConverter();

        try {
            converter.convert(Object.class, numbers[0]);
            fail("Invalid type test, expected ConversionException");
        } catch (final ConversionException e) {
            // expected result
        }
    }

    /**
     * Tests a conversion to an unsupported type if a default value is set.
     */
    public void testInvalidTypeWithDefault() {

        final NumberConverter converter = makeConverter(42);

        try {
            converter.convert(Object.class, numbers[0]);
            fail("Invalid type with default test, expected ConversionException");
        } catch(final ConversionException e) {
            // expected result
        }
    }

    public void testConvertNumber_1_oe() {
        final String[] message= {
            "from Byte",
            "from Short",
            "from Integer",
            "from Long",
            "from Float",
            "from Double",
            "from BigDecimal",
            "from BigInteger",
            "from Integer array",
        };

        final Object[] number = {
            new Byte((byte)7),
            new Short((short)8),
            new Integer(9),
            new Long(10),
            new Float(11.1),
            new Double(12.2),
            new BigDecimal("17.2"),
            new BigInteger("33"),
            new Integer[] {new Integer(3), new Integer(2), new Integer(1)}
        };

        for(int i=0;i<number.length;i++) {
            final Object val = makeConverter().convert(getExpectedType(),number[i]);
            assertNotNull("Convert " + message[i] + " should not be null",val);
    }
    }

    public void testConvertNumber_2_oe() {
        final String[] message= {
            "from Byte",
            "from Short",
            "from Integer",
            "from Long",
            "from Float",
            "from Double",
            "from BigDecimal",
            "from BigInteger",
            "from Integer array",
        };

        final Object[] number = {
            new Byte((byte)7),
            new Short((short)8),
            new Integer(9),
            new Long(10),
            new Float(11.1),
            new Double(12.2),
            new BigDecimal("17.2"),
            new BigInteger("33"),
            new Integer[] {new Integer(3), new Integer(2), new Integer(1)}
        };

        for(int i=0;i<number.length;i++) {
            final Object val = makeConverter().convert(getExpectedType(),number[i]);
            assertTrue("Convert " + message[i] + " should return a " + getExpectedType().getName(),getExpectedType().isInstance(val));
    }
    }

    public void testNumberToStringPattern_1_oe() {

        final Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);

        final NumberConverter converter = makeConverter();
        converter.setPattern("[0,0.0];(0,0.0)");

        assertEquals("Default Locale " + numbers[0], "(12.0)", converter.convert(String.class, numbers[0]));
    }

    public void testNumberToStringPattern_2_oe() {

        final Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);

        final NumberConverter converter = makeConverter();
        converter.setPattern("[0,0.0];(0,0.0)");

        assertEquals("Default Locale " + numbers[1], "[13.0]", converter.convert(String.class, numbers[1]));
    }

    public void testNumberToStringPattern_3_oe() {

        final Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);

        final NumberConverter converter = makeConverter();
        converter.setPattern("[0,0.0];(0,0.0)");


        converter.setLocale(Locale.GERMAN);
        assertEquals("Locale.GERMAN " + numbers[2], "(22,0)", converter.convert(String.class, numbers[2]));
    }

    public void testNumberToStringPattern_4_oe() {

        final Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);

        final NumberConverter converter = makeConverter();
        converter.setPattern("[0,0.0];(0,0.0)");


        converter.setLocale(Locale.GERMAN);
        assertEquals("Locale.GERMAN " + numbers[3], "[23,0]", converter.convert(String.class, numbers[3]));
    }

    public void testNumberToStringLocale_1_oe() {

        final Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);

        final NumberConverter converter = makeConverter();
        converter.setUseLocaleFormat(true);

        assertEquals("Default Locale " + numbers[0], "-12", converter.convert(String.class, numbers[0]));
    }

    public void testNumberToStringLocale_2_oe() {

        final Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);

        final NumberConverter converter = makeConverter();
        converter.setUseLocaleFormat(true);

        assertEquals("Default Locale " + numbers[1], "13",  converter.convert(String.class, numbers[1]));
    }

    public void testNumberToStringLocale_3_oe() {

        final Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);

        final NumberConverter converter = makeConverter();
        converter.setUseLocaleFormat(true);


        converter.setLocale(Locale.GERMAN);
        assertEquals("Locale.GERMAN " + numbers[2], "-22", converter.convert(String.class, numbers[2]));
    }

    public void testNumberToStringLocale_4_oe() {

        final Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);

        final NumberConverter converter = makeConverter();
        converter.setUseLocaleFormat(true);


        converter.setLocale(Locale.GERMAN);
        assertEquals("Locale.GERMAN " + numbers[3], "23",  converter.convert(String.class, numbers[3]));
    }

    public void testStringArrayToInteger_1_oe() {

        final Integer defaultValue = new Integer(-1);
        final NumberConverter converter = makeConverter(defaultValue);

        assertEquals("Valid First",   new Integer(5), converter.convert(Integer.class, new String[] {"5", "4", "3"}));
    }

    public void testStringArrayToInteger_2_oe() {

        final Integer defaultValue = new Integer(-1);
        final NumberConverter converter = makeConverter(defaultValue);

        assertEquals("Invalid First", defaultValue,   converter.convert(Integer.class, new String[] {"FOO", "1", "2"}));
    }

    public void testStringArrayToInteger_3_oe() {

        final Integer defaultValue = new Integer(-1);
        final NumberConverter converter = makeConverter(defaultValue);

        assertEquals("Null First",    defaultValue,   converter.convert(Integer.class, new String[] {null, "1", "2"}));
    }

    public void testStringArrayToInteger_4_oe() {

        final Integer defaultValue = new Integer(-1);
        final NumberConverter converter = makeConverter(defaultValue);

        assertEquals("Long Array",    new Integer(9), converter.convert(Integer.class, new long[] {9, 2, 6}));
    }

    public void testNumberToStringDefault_1_oe() {

        final NumberConverter converter = makeConverter();

        assertEquals("Default Convert " + numbers[0], numbers[0].toString(), converter.convert(String.class, numbers[0]));
    }

    public void testNumberToStringDefault_2_oe() {

        final NumberConverter converter = makeConverter();

        assertEquals("Default Convert " + numbers[1], numbers[1].toString(), converter.convert(String.class, numbers[1]));
    }

    public void testStringToNumberPattern_1_oe() {

        final Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);

        final NumberConverter converter = makeConverter();
        converter.setPattern("[0,0];(0,0)");

        assertEquals("Default Locale " + numbers[0], numbers[0], converter.convert(getExpectedType(), "(1,2)"));
    }

    public void testStringToNumberPattern_2_oe() {

        final Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);

        final NumberConverter converter = makeConverter();
        converter.setPattern("[0,0];(0,0)");

        assertEquals("Default Locale " + numbers[1], numbers[1], converter.convert(getExpectedType(), "[1,3]"));
    }

    public void testStringToNumberPattern_3_oe() {

        final Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);

        final NumberConverter converter = makeConverter();
        converter.setPattern("[0,0];(0,0)");


        converter.setLocale(Locale.GERMAN);
        assertEquals("Locale.GERMAN " + numbers[2], numbers[2], converter.convert(getExpectedType(), "(2.2)"));
    }

    public void testStringToNumberPattern_4_oe() {

        final Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);

        final NumberConverter converter = makeConverter();
        converter.setPattern("[0,0];(0,0)");


        converter.setLocale(Locale.GERMAN);
        assertEquals("Locale.GERMAN " + numbers[3], numbers[3], converter.convert(getExpectedType(), "[2.3]"));
    }

    public void testStringToNumberLocale_1_oe() {

        final Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);

        final NumberConverter converter = makeConverter();
        converter.setUseLocaleFormat(true);

        assertEquals("Default Locale " + numbers[0], numbers[0], converter.convert(getExpectedType(), "-0,012"));
    }

    public void testStringToNumberLocale_2_oe() {

        final Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);

        final NumberConverter converter = makeConverter();
        converter.setUseLocaleFormat(true);

        assertEquals("Default Locale " + numbers[1], numbers[1], converter.convert(getExpectedType(), "0,013"));
    }

    public void testStringToNumberLocale_4_oe() {

        final Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);

        final NumberConverter converter = makeConverter();
        converter.setUseLocaleFormat(true);


        try {
            converter.convert(getExpectedType(), "0,02x");
        } catch (final Exception e) {
        }

        converter.setLocale(Locale.GERMAN);
        assertEquals("Locale.GERMAN " + numbers[2], numbers[2], converter.convert(getExpectedType(), "-0.022"));
    }

    public void testStringToNumberLocale_5_oe() {

        final Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);

        final NumberConverter converter = makeConverter();
        converter.setUseLocaleFormat(true);


        try {
            converter.convert(getExpectedType(), "0,02x");
        } catch (final Exception e) {
        }

        converter.setLocale(Locale.GERMAN);
        assertEquals("Locale.GERMAN " + numbers[3], numbers[3], converter.convert(getExpectedType(), "0.023"));
    }

    public void testStringToNumberDefault_1_oe() {

        final NumberConverter converter = makeConverter();
        converter.setUseLocaleFormat(false);

        assertEquals("Default Convert " + numbers[0], numbers[0], converter.convert(getExpectedType(), numbers[0].toString()));
    }

    public void testStringToNumberDefaultType_1_oe() {
        final NumberConverter converter = makeConverter();
        converter.setUseLocaleFormat(false);

        assertEquals("Default Convert " + numbers[0], numbers[0], converter.convert(null, numbers[0].toString()));
    }

    public void testBooleanToNumberDefault_1_oe() {

        final NumberConverter converter = makeConverter();

        assertEquals("Boolean.FALSE to Number ", 0, ((Number)converter.convert(getExpectedType(), Boolean.FALSE)).intValue());
    }

    public void testBooleanToNumberDefault_2_oe() {

        final NumberConverter converter = makeConverter();

        assertEquals("Boolean.TRUE to Number ",  1, ((Number)converter.convert(getExpectedType(), Boolean.TRUE)).intValue());
    }

    public void testDateToNumber_1_oe() {

        final NumberConverter converter = makeConverter();

        final Date dateValue = new Date();
        final long longValue = dateValue.getTime();

        assertEquals("Date to Long", new Long(longValue), converter.convert(Long.class, dateValue));
    }

    public void testCalendarToNumber_1_oe() {

        final NumberConverter converter = makeConverter();

        final Calendar calendarValue = Calendar.getInstance();
        final long longValue = calendarValue.getTime().getTime();

        assertEquals("Calendar to Long", new Long(longValue), converter.convert(Long.class, calendarValue));
    }

    public void testOtherToStringDefault_1_oe() {

        final NumberConverter converter = makeConverter();

        assertEquals("Default Convert ", "ABC", converter.convert(String.class, new StringBuilder("ABC")));
    }

    public void testInvalidDefault_1_oe() {

        final Object defaultvalue = numbers[0];
        final NumberConverter converter = makeConverter(defaultvalue);

        assertEquals("Invalid null ", defaultvalue, converter.convert(getExpectedType(), null));
    }

    public void testInvalidDefault_2_oe() {

        final Object defaultvalue = numbers[0];
        final NumberConverter converter = makeConverter(defaultvalue);

        assertEquals("Default XXXX ", defaultvalue, converter.convert(getExpectedType(), "XXXX"));
    }

}

