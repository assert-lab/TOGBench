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

import java.util.Locale;
import java.math.BigDecimal;
import java.text.DecimalFormatSymbols;

/**
 * Test Case for CurrencyValidator.
 * 
 * @version $Revision$
 */
public class CurrencyValidatorTest_OE25Dev extends TestCase {
    
    private static final char CURRENCY_SYMBOL = '\u00A4';

    private String US_DOLLAR;
    private String UK_POUND;

    /**
     * Constructor
     * @param name test name
     */
    public CurrencyValidatorTest_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        US_DOLLAR = (new DecimalFormatSymbols(Locale.US)).getCurrencySymbol();
        UK_POUND  = (new DecimalFormatSymbols(Locale.UK)).getCurrencySymbol();
    }

    /**
     * Tear down
     * @throws Exception
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test Format Type
     */
    public void testFormatType() {
        assertEquals("Format Type A", 1, CurrencyValidator.getInstance().getFormatType());
        assertEquals("Format Type B", AbstractNumberValidator.CURRENCY_FORMAT, CurrencyValidator.getInstance().getFormatType());
    }

    /**
     * Test Valid currency values
     */
    public void testValid() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = CurrencyValidator.getInstance();
        BigDecimal expected   = new BigDecimal("1234.56");
        BigDecimal negative   = new BigDecimal("-1234.56");
        BigDecimal noDecimal  = new BigDecimal("1234.00");
        BigDecimal oneDecimal = new BigDecimal("1234.50");

        assertEquals("Default locale", expected, validator.validate(UK_POUND + "1,234.56"));

        assertEquals("UK locale",     expected,   validator.validate(UK_POUND  + "1,234.56",   Locale.UK));
        assertEquals("UK negative",   negative,   validator.validate("-" + UK_POUND  + "1,234.56",  Locale.UK));
        assertEquals("UK no decimal", noDecimal,  validator.validate(UK_POUND  + "1,234",      Locale.UK));
        assertEquals("UK 1 decimal",  oneDecimal, validator.validate(UK_POUND  + "1,234.5",    Locale.UK));
        assertEquals("UK 3 decimal",  expected,   validator.validate(UK_POUND  + "1,234.567",  Locale.UK));
        assertEquals("UK no symbol",  expected,   validator.validate("1,234.56",    Locale.UK));

        assertEquals("US locale",     expected,   validator.validate(US_DOLLAR + "1,234.56",   Locale.US));
        assertEquals("US negative",   negative,   validator.validate("(" + US_DOLLAR + "1,234.56)", Locale.US));
        assertEquals("US no decimal", noDecimal,  validator.validate(US_DOLLAR + "1,234",      Locale.US));
        assertEquals("US 1 decimal",  oneDecimal, validator.validate(US_DOLLAR + "1,234.5",    Locale.US));
        assertEquals("US 3 decimal",  expected,   validator.validate(US_DOLLAR + "1,234.567",  Locale.US));
        assertEquals("US no symbol",  expected,   validator.validate("1,234.56",    Locale.US));

        // Restore the original default
        Locale.setDefault(origDefault);
    }

    /**
     * Test Invalid currency values
     */
    public void testInvalid() {
        BigDecimalValidator validator = CurrencyValidator.getInstance();

        // Invalid Missing
        assertFalse("isValid() Null Value",    validator.isValid(null));
        assertFalse("isValid() Empty Value",   validator.isValid(""));
        assertNull("validate() Null Value",    validator.validate(null));
        assertNull("validate() Empty Value",   validator.validate(""));

        // Invalid UK
        assertFalse("UK wrong symbol",    validator.isValid(US_DOLLAR + "1,234.56",   Locale.UK));
        assertFalse("UK wrong negative",  validator.isValid("(" + UK_POUND  + "1,234.56)", Locale.UK));

        // Invalid US
        assertFalse("US wrong symbol",    validator.isValid(UK_POUND + "1,234.56",   Locale.US));
        assertFalse("US wrong negative",  validator.isValid("-" + US_DOLLAR + "1,234.56",  Locale.US));
    }

    /**
     * Test Valid integer (non-decimal) currency values
     */
    public void testIntegerValid() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        CurrencyValidator validator = new CurrencyValidator();
        BigDecimal expected = new BigDecimal("1234.00");
        BigDecimal negative = new BigDecimal("-1234.00");

        assertEquals("Default locale", expected, validator.validate(UK_POUND +"1,234"));

        assertEquals("UK locale",      expected, validator.validate(UK_POUND + "1,234",   Locale.UK));
        assertEquals("UK negative",    negative, validator.validate("-" + UK_POUND + "1,234",  Locale.UK));

        assertEquals("US locale",      expected, validator.validate(US_DOLLAR + "1,234",   Locale.US));
        assertEquals("US negative",    negative, validator.validate("(" + US_DOLLAR + "1,234)", Locale.US));

        // Restore the original default
        Locale.setDefault(origDefault);
    }

    /**
     * Test Invalid integer (non decimal) currency values
     */
    public void testIntegerInvalid() {
        CurrencyValidator validator = new CurrencyValidator(true, false);

        // Invalid UK - has decimals
        assertFalse("UK positive",    validator.isValid(UK_POUND + "1,234.56",   Locale.UK));
        assertFalse("UK negative",    validator.isValid("-" + UK_POUND + "1,234.56", Locale.UK));

        // Invalid US - has decimals
        assertFalse("US positive",    validator.isValid(US_DOLLAR + "1,234.56",   Locale.US));
        assertFalse("US negative",    validator.isValid("(" + US_DOLLAR + "1,234.56)",  Locale.US));
    }


    /**
     * Test currency values with a pattern
     */
    public void testPattern() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = CurrencyValidator.getInstance();
        String basicPattern = CURRENCY_SYMBOL + "#,##0.000";
        String pattern = basicPattern + ";[" + basicPattern +"]";
        BigDecimal expected   = new BigDecimal("1234.567");
        BigDecimal negative   = new BigDecimal("-1234.567");

        // Test Pattern
        assertEquals("default",        expected,   validator.validate(UK_POUND + "1,234.567", pattern));
        assertEquals("negative",       negative,   validator.validate("[" + UK_POUND + "1,234.567]", pattern));
        assertEquals("no symbol +ve",  expected,   validator.validate("1,234.567",    pattern));
        assertEquals("no symbol -ve",  negative,   validator.validate("[1,234.567]",  pattern));

        // Test Pattern & Locale
        assertEquals("default",        expected,   validator.validate(US_DOLLAR + "1,234.567", pattern, Locale.US));
        assertEquals("negative",       negative,   validator.validate("[" + US_DOLLAR + "1,234.567]", pattern, Locale.US));
        assertEquals("no symbol +ve",  expected,   validator.validate("1,234.567",    pattern, Locale.US));
        assertEquals("no symbol -ve",  negative,   validator.validate("[1,234.567]",  pattern, Locale.US));

        // invalid
        assertFalse("invalid symbol",  validator.isValid(US_DOLLAR + "1,234.567", pattern));
        assertFalse("invalid symbol",  validator.isValid(UK_POUND  + "1,234.567", pattern, Locale.US));

        // Restore the original default
        Locale.setDefault(origDefault);
    }

    public void testFormatType_1_oe() {
        assertEquals("Format Type A", 1, CurrencyValidator.getInstance().getFormatType());
    }

    public void testFormatType_2_oe() {
        // removed other assertion
        assertEquals("Format Type B", AbstractNumberValidator.CURRENCY_FORMAT, CurrencyValidator.getInstance().getFormatType());
    }

    public void testValid_1_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = CurrencyValidator.getInstance();
        BigDecimal expected   = new BigDecimal("1234.56");
        BigDecimal negative   = new BigDecimal("-1234.56");
        BigDecimal noDecimal  = new BigDecimal("1234.00");
        BigDecimal oneDecimal = new BigDecimal("1234.50");

        assertEquals("Default locale", expected, validator.validate(UK_POUND + "1,234.56"));
    }

    public void testValid_2_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = CurrencyValidator.getInstance();
        BigDecimal expected   = new BigDecimal("1234.56");
        BigDecimal negative   = new BigDecimal("-1234.56");
        BigDecimal noDecimal  = new BigDecimal("1234.00");
        BigDecimal oneDecimal = new BigDecimal("1234.50");

        // removed other assertion

        assertEquals("UK locale",     expected,   validator.validate(UK_POUND  + "1,234.56",   Locale.UK));
    }

    public void testValid_3_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = CurrencyValidator.getInstance();
        BigDecimal expected   = new BigDecimal("1234.56");
        BigDecimal negative   = new BigDecimal("-1234.56");
        BigDecimal noDecimal  = new BigDecimal("1234.00");
        BigDecimal oneDecimal = new BigDecimal("1234.50");

        // removed other assertion

        // removed other assertion
        assertEquals("UK negative",   negative,   validator.validate("-" + UK_POUND  + "1,234.56",  Locale.UK));
    }

    public void testValid_4_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = CurrencyValidator.getInstance();
        BigDecimal expected   = new BigDecimal("1234.56");
        BigDecimal negative   = new BigDecimal("-1234.56");
        BigDecimal noDecimal  = new BigDecimal("1234.00");
        BigDecimal oneDecimal = new BigDecimal("1234.50");

        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("UK no decimal", noDecimal,  validator.validate(UK_POUND  + "1,234",      Locale.UK));
    }

    public void testValid_5_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = CurrencyValidator.getInstance();
        BigDecimal expected   = new BigDecimal("1234.56");
        BigDecimal negative   = new BigDecimal("-1234.56");
        BigDecimal noDecimal  = new BigDecimal("1234.00");
        BigDecimal oneDecimal = new BigDecimal("1234.50");

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("UK 1 decimal",  oneDecimal, validator.validate(UK_POUND  + "1,234.5",    Locale.UK));
    }

    public void testValid_6_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = CurrencyValidator.getInstance();
        BigDecimal expected   = new BigDecimal("1234.56");
        BigDecimal negative   = new BigDecimal("-1234.56");
        BigDecimal noDecimal  = new BigDecimal("1234.00");
        BigDecimal oneDecimal = new BigDecimal("1234.50");

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("UK 3 decimal",  expected,   validator.validate(UK_POUND  + "1,234.567",  Locale.UK));
    }

    public void testValid_7_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = CurrencyValidator.getInstance();
        BigDecimal expected   = new BigDecimal("1234.56");
        BigDecimal negative   = new BigDecimal("-1234.56");
        BigDecimal noDecimal  = new BigDecimal("1234.00");
        BigDecimal oneDecimal = new BigDecimal("1234.50");

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("UK no symbol",  expected,   validator.validate("1,234.56",    Locale.UK));
    }

    public void testValid_8_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = CurrencyValidator.getInstance();
        BigDecimal expected   = new BigDecimal("1234.56");
        BigDecimal negative   = new BigDecimal("-1234.56");
        BigDecimal noDecimal  = new BigDecimal("1234.00");
        BigDecimal oneDecimal = new BigDecimal("1234.50");

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("US locale",     expected,   validator.validate(US_DOLLAR + "1,234.56",   Locale.US));
    }

    public void testValid_9_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = CurrencyValidator.getInstance();
        BigDecimal expected   = new BigDecimal("1234.56");
        BigDecimal negative   = new BigDecimal("-1234.56");
        BigDecimal noDecimal  = new BigDecimal("1234.00");
        BigDecimal oneDecimal = new BigDecimal("1234.50");

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("US negative",   negative,   validator.validate("(" + US_DOLLAR + "1,234.56)", Locale.US));
    }

    public void testValid_10_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = CurrencyValidator.getInstance();
        BigDecimal expected   = new BigDecimal("1234.56");
        BigDecimal negative   = new BigDecimal("-1234.56");
        BigDecimal noDecimal  = new BigDecimal("1234.00");
        BigDecimal oneDecimal = new BigDecimal("1234.50");

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("US no decimal", noDecimal,  validator.validate(US_DOLLAR + "1,234",      Locale.US));
    }

    public void testValid_11_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = CurrencyValidator.getInstance();
        BigDecimal expected   = new BigDecimal("1234.56");
        BigDecimal negative   = new BigDecimal("-1234.56");
        BigDecimal noDecimal  = new BigDecimal("1234.00");
        BigDecimal oneDecimal = new BigDecimal("1234.50");

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("US 1 decimal",  oneDecimal, validator.validate(US_DOLLAR + "1,234.5",    Locale.US));
    }

    public void testValid_12_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = CurrencyValidator.getInstance();
        BigDecimal expected   = new BigDecimal("1234.56");
        BigDecimal negative   = new BigDecimal("-1234.56");
        BigDecimal noDecimal  = new BigDecimal("1234.00");
        BigDecimal oneDecimal = new BigDecimal("1234.50");

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("US 3 decimal",  expected,   validator.validate(US_DOLLAR + "1,234.567",  Locale.US));
    }

    public void testValid_13_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = CurrencyValidator.getInstance();
        BigDecimal expected   = new BigDecimal("1234.56");
        BigDecimal negative   = new BigDecimal("-1234.56");
        BigDecimal noDecimal  = new BigDecimal("1234.00");
        BigDecimal oneDecimal = new BigDecimal("1234.50");

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("US no symbol",  expected,   validator.validate("1,234.56",    Locale.US));
    }

    public void testInvalid_1_oe() {
        BigDecimalValidator validator = CurrencyValidator.getInstance();

        // Invalid Missing
        assertFalse("isValid() Null Value",    validator.isValid(null));
    }

    public void testInvalid_2_oe() {
        BigDecimalValidator validator = CurrencyValidator.getInstance();

        // Invalid Missing
        // removed other assertion
        assertFalse("isValid() Empty Value",   validator.isValid(""));
    }

    public void testInvalid_3_oe() {
        BigDecimalValidator validator = CurrencyValidator.getInstance();

        // Invalid Missing
        // removed other assertion
        // removed other assertion
        assertNull("validate() Null Value",    validator.validate(null));
    }

    public void testInvalid_4_oe() {
        BigDecimalValidator validator = CurrencyValidator.getInstance();

        // Invalid Missing
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull("validate() Empty Value",   validator.validate(""));
    }

    public void testInvalid_5_oe() {
        BigDecimalValidator validator = CurrencyValidator.getInstance();

        // Invalid Missing
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Invalid UK
        assertFalse("UK wrong symbol",    validator.isValid(US_DOLLAR + "1,234.56",   Locale.UK));
    }

    public void testInvalid_6_oe() {
        BigDecimalValidator validator = CurrencyValidator.getInstance();

        // Invalid Missing
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Invalid UK
        // removed other assertion
        assertFalse("UK wrong negative",  validator.isValid("(" + UK_POUND  + "1,234.56)", Locale.UK));
    }

    public void testInvalid_7_oe() {
        BigDecimalValidator validator = CurrencyValidator.getInstance();

        // Invalid Missing
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Invalid UK
        // removed other assertion
        // removed other assertion

        // Invalid US
        assertFalse("US wrong symbol",    validator.isValid(UK_POUND + "1,234.56",   Locale.US));
    }

    public void testInvalid_8_oe() {
        BigDecimalValidator validator = CurrencyValidator.getInstance();

        // Invalid Missing
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Invalid UK
        // removed other assertion
        // removed other assertion

        // Invalid US
        // removed other assertion
        assertFalse("US wrong negative",  validator.isValid("-" + US_DOLLAR + "1,234.56",  Locale.US));
    }

    public void testIntegerValid_1_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        CurrencyValidator validator = new CurrencyValidator();
        BigDecimal expected = new BigDecimal("1234.00");
        BigDecimal negative = new BigDecimal("-1234.00");

        assertEquals("Default locale", expected, validator.validate(UK_POUND +"1,234"));
    }

    public void testIntegerValid_2_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        CurrencyValidator validator = new CurrencyValidator();
        BigDecimal expected = new BigDecimal("1234.00");
        BigDecimal negative = new BigDecimal("-1234.00");

        // removed other assertion

        assertEquals("UK locale",      expected, validator.validate(UK_POUND + "1,234",   Locale.UK));
    }

    public void testIntegerValid_3_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        CurrencyValidator validator = new CurrencyValidator();
        BigDecimal expected = new BigDecimal("1234.00");
        BigDecimal negative = new BigDecimal("-1234.00");

        // removed other assertion

        // removed other assertion
        assertEquals("UK negative",    negative, validator.validate("-" + UK_POUND + "1,234",  Locale.UK));
    }

    public void testIntegerValid_4_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        CurrencyValidator validator = new CurrencyValidator();
        BigDecimal expected = new BigDecimal("1234.00");
        BigDecimal negative = new BigDecimal("-1234.00");

        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals("US locale",      expected, validator.validate(US_DOLLAR + "1,234",   Locale.US));
    }

    public void testIntegerValid_5_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        CurrencyValidator validator = new CurrencyValidator();
        BigDecimal expected = new BigDecimal("1234.00");
        BigDecimal negative = new BigDecimal("-1234.00");

        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("US negative",    negative, validator.validate("(" + US_DOLLAR + "1,234)", Locale.US));
    }

    public void testIntegerInvalid_1_oe() {
        CurrencyValidator validator = new CurrencyValidator(true, false);

        // Invalid UK - has decimals
        assertFalse("UK positive",    validator.isValid(UK_POUND + "1,234.56",   Locale.UK));
    }

    public void testIntegerInvalid_2_oe() {
        CurrencyValidator validator = new CurrencyValidator(true, false);

        // Invalid UK - has decimals
        // removed other assertion
        assertFalse("UK negative",    validator.isValid("-" + UK_POUND + "1,234.56", Locale.UK));
    }

    public void testIntegerInvalid_3_oe() {
        CurrencyValidator validator = new CurrencyValidator(true, false);

        // Invalid UK - has decimals
        // removed other assertion
        // removed other assertion

        // Invalid US - has decimals
        assertFalse("US positive",    validator.isValid(US_DOLLAR + "1,234.56",   Locale.US));
    }

    public void testIntegerInvalid_4_oe() {
        CurrencyValidator validator = new CurrencyValidator(true, false);

        // Invalid UK - has decimals
        // removed other assertion
        // removed other assertion

        // Invalid US - has decimals
        // removed other assertion
        assertFalse("US negative",    validator.isValid("(" + US_DOLLAR + "1,234.56)",  Locale.US));
    }

    public void testPattern_1_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = CurrencyValidator.getInstance();
        String basicPattern = CURRENCY_SYMBOL + "#,##0.000";
        String pattern = basicPattern + ";[" + basicPattern +"]";
        BigDecimal expected   = new BigDecimal("1234.567");
        BigDecimal negative   = new BigDecimal("-1234.567");

        // Test Pattern
        assertEquals("default",        expected,   validator.validate(UK_POUND + "1,234.567", pattern));
    }

    public void testPattern_2_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = CurrencyValidator.getInstance();
        String basicPattern = CURRENCY_SYMBOL + "#,##0.000";
        String pattern = basicPattern + ";[" + basicPattern +"]";
        BigDecimal expected   = new BigDecimal("1234.567");
        BigDecimal negative   = new BigDecimal("-1234.567");

        // Test Pattern
        // removed other assertion
        assertEquals("negative",       negative,   validator.validate("[" + UK_POUND + "1,234.567]", pattern));
    }

    public void testPattern_3_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = CurrencyValidator.getInstance();
        String basicPattern = CURRENCY_SYMBOL + "#,##0.000";
        String pattern = basicPattern + ";[" + basicPattern +"]";
        BigDecimal expected   = new BigDecimal("1234.567");
        BigDecimal negative   = new BigDecimal("-1234.567");

        // Test Pattern
        // removed other assertion
        // removed other assertion
        assertEquals("no symbol +ve",  expected,   validator.validate("1,234.567",    pattern));
    }

    public void testPattern_4_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = CurrencyValidator.getInstance();
        String basicPattern = CURRENCY_SYMBOL + "#,##0.000";
        String pattern = basicPattern + ";[" + basicPattern +"]";
        BigDecimal expected   = new BigDecimal("1234.567");
        BigDecimal negative   = new BigDecimal("-1234.567");

        // Test Pattern
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("no symbol -ve",  negative,   validator.validate("[1,234.567]",  pattern));
    }

    public void testPattern_5_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = CurrencyValidator.getInstance();
        String basicPattern = CURRENCY_SYMBOL + "#,##0.000";
        String pattern = basicPattern + ";[" + basicPattern +"]";
        BigDecimal expected   = new BigDecimal("1234.567");
        BigDecimal negative   = new BigDecimal("-1234.567");

        // Test Pattern
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test Pattern & Locale
        assertEquals("default",        expected,   validator.validate(US_DOLLAR + "1,234.567", pattern, Locale.US));
    }

    public void testPattern_6_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = CurrencyValidator.getInstance();
        String basicPattern = CURRENCY_SYMBOL + "#,##0.000";
        String pattern = basicPattern + ";[" + basicPattern +"]";
        BigDecimal expected   = new BigDecimal("1234.567");
        BigDecimal negative   = new BigDecimal("-1234.567");

        // Test Pattern
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test Pattern & Locale
        // removed other assertion
        assertEquals("negative",       negative,   validator.validate("[" + US_DOLLAR + "1,234.567]", pattern, Locale.US));
    }

    public void testPattern_7_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = CurrencyValidator.getInstance();
        String basicPattern = CURRENCY_SYMBOL + "#,##0.000";
        String pattern = basicPattern + ";[" + basicPattern +"]";
        BigDecimal expected   = new BigDecimal("1234.567");
        BigDecimal negative   = new BigDecimal("-1234.567");

        // Test Pattern
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test Pattern & Locale
        // removed other assertion
        // removed other assertion
        assertEquals("no symbol +ve",  expected,   validator.validate("1,234.567",    pattern, Locale.US));
    }

    public void testPattern_8_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = CurrencyValidator.getInstance();
        String basicPattern = CURRENCY_SYMBOL + "#,##0.000";
        String pattern = basicPattern + ";[" + basicPattern +"]";
        BigDecimal expected   = new BigDecimal("1234.567");
        BigDecimal negative   = new BigDecimal("-1234.567");

        // Test Pattern
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test Pattern & Locale
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("no symbol -ve",  negative,   validator.validate("[1,234.567]",  pattern, Locale.US));
    }

    public void testPattern_9_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = CurrencyValidator.getInstance();
        String basicPattern = CURRENCY_SYMBOL + "#,##0.000";
        String pattern = basicPattern + ";[" + basicPattern +"]";
        BigDecimal expected   = new BigDecimal("1234.567");
        BigDecimal negative   = new BigDecimal("-1234.567");

        // Test Pattern
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test Pattern & Locale
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // invalid
        assertFalse("invalid symbol",  validator.isValid(US_DOLLAR + "1,234.567", pattern));
    }

    public void testPattern_10_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = CurrencyValidator.getInstance();
        String basicPattern = CURRENCY_SYMBOL + "#,##0.000";
        String pattern = basicPattern + ";[" + basicPattern +"]";
        BigDecimal expected   = new BigDecimal("1234.567");
        BigDecimal negative   = new BigDecimal("-1234.567");

        // Test Pattern
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test Pattern & Locale
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // invalid
        // removed other assertion
        assertFalse("invalid symbol",  validator.isValid(UK_POUND  + "1,234.567", pattern, Locale.US));
    }

}
