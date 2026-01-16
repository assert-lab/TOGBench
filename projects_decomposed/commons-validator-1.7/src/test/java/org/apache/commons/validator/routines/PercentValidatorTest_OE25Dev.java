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
/**
 * Test Case for PercentValidator.
 * 
 * @version $Revision$
 */
public class PercentValidatorTest_OE25Dev extends TestCase {

    protected PercentValidator validator;

    /**
     * Constructor
     * @param name test name
     */
    public PercentValidatorTest_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        validator = new PercentValidator();
    }

    /**
     * Tear down
     * @throws Exception
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        validator = null;
    }

    /**
     * Test Format Type
     */

    /**
     * Test Valid percentage values
     */

    /**
     * Test Invalid percentage values
     */

    public void testFormatType_1_oe() {
        assertEquals("Format Type A", 2, PercentValidator.getInstance().getFormatType());
    }

    public void testFormatType_2_oe() {
        // removed other assertion
        assertEquals("Format Type B", AbstractNumberValidator.PERCENT_FORMAT, PercentValidator.getInstance().getFormatType());
    }

    public void testValid_1_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = PercentValidator.getInstance();
        BigDecimal expected = new BigDecimal("0.12");
        BigDecimal negative = new BigDecimal("-0.12");
        BigDecimal hundred  = new BigDecimal("1.00");

        assertEquals("Default locale", expected, validator.validate("12%"));
    }

    public void testValid_2_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = PercentValidator.getInstance();
        BigDecimal expected = new BigDecimal("0.12");
        BigDecimal negative = new BigDecimal("-0.12");
        BigDecimal hundred  = new BigDecimal("1.00");

        // removed other assertion
        assertEquals("Default negtve", negative, validator.validate("-12%"));
    }

    public void testValid_3_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = PercentValidator.getInstance();
        BigDecimal expected = new BigDecimal("0.12");
        BigDecimal negative = new BigDecimal("-0.12");
        BigDecimal hundred  = new BigDecimal("1.00");

        // removed other assertion
        // removed other assertion

        // Invalid UK
        assertEquals("UK locale",      expected, validator.validate("12%",   Locale.UK));
    }

    public void testValid_4_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = PercentValidator.getInstance();
        BigDecimal expected = new BigDecimal("0.12");
        BigDecimal negative = new BigDecimal("-0.12");
        BigDecimal hundred  = new BigDecimal("1.00");

        // removed other assertion
        // removed other assertion

        // Invalid UK
        // removed other assertion
        assertEquals("UK negative",    negative, validator.validate("-12%",  Locale.UK));
    }

    public void testValid_5_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = PercentValidator.getInstance();
        BigDecimal expected = new BigDecimal("0.12");
        BigDecimal negative = new BigDecimal("-0.12");
        BigDecimal hundred  = new BigDecimal("1.00");

        // removed other assertion
        // removed other assertion

        // Invalid UK
        // removed other assertion
        // removed other assertion
        assertEquals("UK No symbol",   expected, validator.validate("12",    Locale.UK));
    }

    public void testValid_6_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = PercentValidator.getInstance();
        BigDecimal expected = new BigDecimal("0.12");
        BigDecimal negative = new BigDecimal("-0.12");
        BigDecimal hundred  = new BigDecimal("1.00");

        // removed other assertion
        // removed other assertion

        // Invalid UK
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Invalid US - can't find a Locale with different symbols!
        assertEquals("US locale",      expected, validator.validate("12%",   Locale.US));
    }

    public void testValid_7_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = PercentValidator.getInstance();
        BigDecimal expected = new BigDecimal("0.12");
        BigDecimal negative = new BigDecimal("-0.12");
        BigDecimal hundred  = new BigDecimal("1.00");

        // removed other assertion
        // removed other assertion

        // Invalid UK
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Invalid US - can't find a Locale with different symbols!
        // removed other assertion
        assertEquals("US negative",    negative, validator.validate("-12%",  Locale.US));
    }

    public void testValid_8_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = PercentValidator.getInstance();
        BigDecimal expected = new BigDecimal("0.12");
        BigDecimal negative = new BigDecimal("-0.12");
        BigDecimal hundred  = new BigDecimal("1.00");

        // removed other assertion
        // removed other assertion

        // Invalid UK
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Invalid US - can't find a Locale with different symbols!
        // removed other assertion
        // removed other assertion
        assertEquals("US No symbol",   expected, validator.validate("12",    Locale.US));
    }

    public void testValid_9_oe() {
        // Set the default Locale
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(Locale.UK);

        BigDecimalValidator validator = PercentValidator.getInstance();
        BigDecimal expected = new BigDecimal("0.12");
        BigDecimal negative = new BigDecimal("-0.12");
        BigDecimal hundred  = new BigDecimal("1.00");

        // removed other assertion
        // removed other assertion

        // Invalid UK
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Invalid US - can't find a Locale with different symbols!
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("100%",           hundred, validator.validate("100%"));
    }

    public void testInvalid_1_oe() {
        BigDecimalValidator validator = PercentValidator.getInstance();

        // Invalid Missing
        assertFalse("isValid() Null Value",    validator.isValid(null));
    }

    public void testInvalid_2_oe() {
        BigDecimalValidator validator = PercentValidator.getInstance();

        // Invalid Missing
        // removed other assertion
        assertFalse("isValid() Empty Value",   validator.isValid(""));
    }

    public void testInvalid_3_oe() {
        BigDecimalValidator validator = PercentValidator.getInstance();

        // Invalid Missing
        // removed other assertion
        // removed other assertion
        assertNull("validate() Null Value",    validator.validate(null));
    }

    public void testInvalid_4_oe() {
        BigDecimalValidator validator = PercentValidator.getInstance();

        // Invalid Missing
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull("validate() Empty Value",   validator.validate(""));
    }

    public void testInvalid_5_oe() {
        BigDecimalValidator validator = PercentValidator.getInstance();

        // Invalid Missing
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Invalid UK
        assertFalse("UK wrong symbol",    validator.isValid("12@",   Locale.UK)); // ???;
    }

    public void testInvalid_6_oe() {
        BigDecimalValidator validator = PercentValidator.getInstance();

        // Invalid Missing
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Invalid UK
        // removed other assertion
        assertFalse("UK wrong negative",  validator.isValid("(12%)", Locale.UK));
    }

    public void testInvalid_7_oe() {
        BigDecimalValidator validator = PercentValidator.getInstance();

        // Invalid Missing
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Invalid UK
        // removed other assertion
        // removed other assertion

        // Invalid US - can't find a Locale with different symbols!
        assertFalse("US wrong symbol",    validator.isValid("12@",   Locale.US)); // ???;
    }

    public void testInvalid_8_oe() {
        BigDecimalValidator validator = PercentValidator.getInstance();

        // Invalid Missing
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Invalid UK
        // removed other assertion
        // removed other assertion

        // Invalid US - can't find a Locale with different symbols!
        // removed other assertion
        assertFalse("US wrong negative",  validator.isValid("(12%)", Locale.US));
    }

}
