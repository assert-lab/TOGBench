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

import org.apache.commons.validator.routines.checkdigit.CheckDigit;
import org.apache.commons.validator.routines.checkdigit.EAN13CheckDigit;

import junit.framework.TestCase;

/**
 * CodeValidatorTest_OE25Dev.java.
 *    
 * @version $Revision$
 * @since Validator 1.4
 */
public class CodeValidatorTest_OE25Dev extends TestCase {

    /**
     * Construct a test with the specified name.
     * @param name The name of the test
     */
    public CodeValidatorTest_OE25Dev(String name) {
        super(name);
    }

    /**
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    /**
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test Check Digit.
     */

    /**
     * Test the minimum/maximum length
     */

    /**
     * Test Regular Expression.
     */

    /**
     * Test Regular Expression.
     */

    public void testValidator294_1() {
        CodeValidator validator = new CodeValidator((String)null, 0, -1, (CheckDigit)null);
        assertEquals("Null",         null, validator.validate(null));
        validator = new CodeValidator((String)null, -1, 0, (CheckDigit)null);
        assertEquals("Null",         null, validator.validate(null));
    }

    public void testValidator294_2() {
        CodeValidator validator = new CodeValidator((String)null, -1, 0, (CheckDigit)null);
        assertEquals("Null",         null, validator.validate(null));
    }

    /**
     * Test Regular Expression.
     */

    public void testCheckDigit_1_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String invalidEAN = "9781930110992";
        String validEAN   = "9781930110991";

        assertNull("No CheckDigit", validator.getCheckDigit());
    }

    public void testCheckDigit_2_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String invalidEAN = "9781930110992";
        String validEAN   = "9781930110991";

        assertEquals("No CheckDigit invalid", invalidEAN, validator.validate(invalidEAN));
    }

    public void testCheckDigit_3_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String invalidEAN = "9781930110992";
        String validEAN   = "9781930110991";

        assertEquals("No CheckDigit valid",     validEAN, validator.validate(validEAN));
    }

    public void testCheckDigit_4_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String invalidEAN = "9781930110992";
        String validEAN   = "9781930110991";

        assertEquals("No CheckDigit (is) invalid",  true, validator.isValid(invalidEAN));
    }

    public void testCheckDigit_5_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String invalidEAN = "9781930110992";
        String validEAN   = "9781930110991";

        assertEquals("No CheckDigit (is) valid",    true, validator.isValid(validEAN));
    }

    public void testCheckDigit_6_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String invalidEAN = "9781930110992";
        String validEAN   = "9781930110991";


        validator = new CodeValidator((String)null, -1, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        assertNotNull("EAN CheckDigit", validator.getCheckDigit());
    }

    public void testCheckDigit_7_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String invalidEAN = "9781930110992";
        String validEAN   = "9781930110991";


        validator = new CodeValidator((String)null, -1, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        assertEquals("EAN CheckDigit invalid",       null, validator.validate(invalidEAN));
    }

    public void testCheckDigit_8_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String invalidEAN = "9781930110992";
        String validEAN   = "9781930110991";


        validator = new CodeValidator((String)null, -1, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        assertEquals("EAN CheckDigit valid",     validEAN, validator.validate(validEAN));
    }

    public void testCheckDigit_9_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String invalidEAN = "9781930110992";
        String validEAN   = "9781930110991";


        validator = new CodeValidator((String)null, -1, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        assertEquals("EAN CheckDigit (is) invalid", false, validator.isValid(invalidEAN));
    }

    public void testCheckDigit_10_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String invalidEAN = "9781930110992";
        String validEAN   = "9781930110991";


        validator = new CodeValidator((String)null, -1, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        assertEquals("EAN CheckDigit (is) valid",    true, validator.isValid(validEAN));
    }

    public void testCheckDigit_11_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String invalidEAN = "9781930110992";
        String validEAN   = "9781930110991";


        validator = new CodeValidator((String)null, -1, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        assertEquals("EAN CheckDigit ex",            null, validator.validate("978193011099X"));
    }

    public void testLength_1_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";

        assertEquals("No min", -1, validator.getMinLength());
    }

    public void testLength_2_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";

        assertEquals("No max", -1, validator.getMaxLength());
    }

    public void testLength_3_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        assertEquals("No Length 10", length_10, validator.validate(length_10));
    }

    public void testLength_4_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        assertEquals("No Length 11", length_11, validator.validate(length_11));
    }

    public void testLength_5_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        assertEquals("No Length 12", length_12, validator.validate(length_12));
    }

    public void testLength_6_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        assertEquals("No Length 20", length_20, validator.validate(length_20));
    }

    public void testLength_7_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        assertEquals("No Length 21", length_21, validator.validate(length_21));
    }

    public void testLength_8_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        assertEquals("No Length 22", length_22, validator.validate(length_22));
    }

    public void testLength_9_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        assertEquals("Min 11 - min", 11, validator.getMinLength());
    }

    public void testLength_10_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        assertEquals("Min 11 - max", -1, validator.getMaxLength());
    }

    public void testLength_11_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        assertEquals("Min 11 - 10", null,      validator.validate(length_10));
    }

    public void testLength_12_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        assertEquals("Min 11 - 11", length_11, validator.validate(length_11));
    }

    public void testLength_13_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        assertEquals("Min 11 - 12", length_12, validator.validate(length_12));
    }

    public void testLength_14_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        assertEquals("Min 11 - 20", length_20, validator.validate(length_20));
    }

    public void testLength_15_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        assertEquals("Min 11 - 21", length_21, validator.validate(length_21));
    }

    public void testLength_16_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        assertEquals("Min 11 - 22", length_22, validator.validate(length_22));
    }

    public void testLength_17_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, -1, 21, (CheckDigit)null);
        assertEquals("Max 21 - min", -1, validator.getMinLength());
    }

    public void testLength_18_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, -1, 21, (CheckDigit)null);
        assertEquals("Max 21 - max", 21, validator.getMaxLength());
    }

    public void testLength_19_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, -1, 21, (CheckDigit)null);
        assertEquals("Max 21 - 10", length_10, validator.validate(length_10));
    }

    public void testLength_20_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, -1, 21, (CheckDigit)null);
        assertEquals("Max 21 - 11", length_11, validator.validate(length_11));
    }

    public void testLength_21_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, -1, 21, (CheckDigit)null);
        assertEquals("Max 21 - 12", length_12, validator.validate(length_12));
    }

    public void testLength_22_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, -1, 21, (CheckDigit)null);
        assertEquals("Max 21 - 20", length_20, validator.validate(length_20));
    }

    public void testLength_23_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, -1, 21, (CheckDigit)null);
        assertEquals("Max 21 - 21", length_21, validator.validate(length_21));
    }

    public void testLength_24_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, -1, 21, (CheckDigit)null);
        assertEquals("Max 21 - 22", null,      validator.validate(length_22));
    }

    public void testLength_25_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, -1, 21, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, 11, 21, (CheckDigit)null);
        assertEquals("Min 11 / Max 21 - min", 11, validator.getMinLength());
    }

    public void testLength_26_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, -1, 21, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, 11, 21, (CheckDigit)null);
        assertEquals("Min 11 / Max 21 - max", 21, validator.getMaxLength());
    }

    public void testLength_27_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, -1, 21, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, 11, 21, (CheckDigit)null);
        assertEquals("Min 11 / Max 21 - 10", null,      validator.validate(length_10));
    }

    public void testLength_28_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, -1, 21, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, 11, 21, (CheckDigit)null);
        assertEquals("Min 11 / Max 21 - 11", length_11, validator.validate(length_11));
    }

    public void testLength_29_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, -1, 21, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, 11, 21, (CheckDigit)null);
        assertEquals("Min 11 / Max 21 - 12", length_12, validator.validate(length_12));
    }

    public void testLength_30_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, -1, 21, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, 11, 21, (CheckDigit)null);
        assertEquals("Min 11 / Max 21 - 20", length_20, validator.validate(length_20));
    }

    public void testLength_31_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, -1, 21, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, 11, 21, (CheckDigit)null);
        assertEquals("Min 11 / Max 21 - 21", length_21, validator.validate(length_21));
    }

    public void testLength_32_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, -1, 21, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, 11, 21, (CheckDigit)null);
        assertEquals("Min 11 / Max 21 - 22", null,      validator.validate(length_22));
    }

    public void testLength_33_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, -1, 21, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, 11, 21, (CheckDigit)null);

        validator = new CodeValidator((String)null, 11, 11, (CheckDigit)null);
        assertEquals("Exact 11 - min", 11, validator.getMinLength());
    }

    public void testLength_34_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, -1, 21, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, 11, 21, (CheckDigit)null);

        validator = new CodeValidator((String)null, 11, 11, (CheckDigit)null);
        assertEquals("Exact 11 - max", 11, validator.getMaxLength());
    }

    public void testLength_35_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, -1, 21, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, 11, 21, (CheckDigit)null);

        validator = new CodeValidator((String)null, 11, 11, (CheckDigit)null);
        assertEquals("Exact 11 - 10", null,      validator.validate(length_10));
    }

    public void testLength_36_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, -1, 21, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, 11, 21, (CheckDigit)null);

        validator = new CodeValidator((String)null, 11, 11, (CheckDigit)null);
        assertEquals("Exact 11 - 11", length_11, validator.validate(length_11));
    }

    public void testLength_37_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        String length_10  = "1234567890";
        String length_11  = "12345678901";
        String length_12  = "123456789012";
        String length_20  = "12345678901234567890";
        String length_21  = "123456789012345678901";
        String length_22  = "1234567890123456789012";


        
        validator = new CodeValidator((String)null, 11, -1, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, -1, 21, (CheckDigit)null);
        
        validator = new CodeValidator((String)null, 11, 21, (CheckDigit)null);

        validator = new CodeValidator((String)null, 11, 11, (CheckDigit)null);
        assertEquals("Exact 11 - 12", null,      validator.validate(length_12));
    }

    public void testRegex_1_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);

        String value2  = "12";
        String value3  = "123";
        String value4  = "1234";
        String value5  = "12345";
        String invalid = "12a4";

        assertNull("No Regex", validator.getRegexValidator());
    }

    public void testRegex_2_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);

        String value2  = "12";
        String value3  = "123";
        String value4  = "1234";
        String value5  = "12345";
        String invalid = "12a4";

        assertEquals("No Regex 2", value2, validator.validate(value2));
    }

    public void testRegex_3_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);

        String value2  = "12";
        String value3  = "123";
        String value4  = "1234";
        String value5  = "12345";
        String invalid = "12a4";

        assertEquals("No Regex 3", value3, validator.validate(value3));
    }

    public void testRegex_4_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);

        String value2  = "12";
        String value3  = "123";
        String value4  = "1234";
        String value5  = "12345";
        String invalid = "12a4";

        assertEquals("No Regex 4", value4, validator.validate(value4));
    }

    public void testRegex_5_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);

        String value2  = "12";
        String value3  = "123";
        String value4  = "1234";
        String value5  = "12345";
        String invalid = "12a4";

        assertEquals("No Regex 5", value5, validator.validate(value5));
    }

    public void testRegex_6_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);

        String value2  = "12";
        String value3  = "123";
        String value4  = "1234";
        String value5  = "12345";
        String invalid = "12a4";

        assertEquals("No Regex invalid", invalid, validator.validate(invalid));
    }

    public void testRegex_7_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);

        String value2  = "12";
        String value3  = "123";
        String value4  = "1234";
        String value5  = "12345";
        String invalid = "12a4";


        String regex = "^([0-9]{3,4})$";
        validator = new CodeValidator(regex, -1, -1, (CheckDigit)null);
        assertNotNull("No Regex", validator.getRegexValidator());
    }

    public void testRegex_8_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);

        String value2  = "12";
        String value3  = "123";
        String value4  = "1234";
        String value5  = "12345";
        String invalid = "12a4";


        String regex = "^([0-9]{3,4})$";
        validator = new CodeValidator(regex, -1, -1, (CheckDigit)null);
        assertEquals("Regex 2", null,   validator.validate(value2));
    }

    public void testRegex_9_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);

        String value2  = "12";
        String value3  = "123";
        String value4  = "1234";
        String value5  = "12345";
        String invalid = "12a4";


        String regex = "^([0-9]{3,4})$";
        validator = new CodeValidator(regex, -1, -1, (CheckDigit)null);
        assertEquals("Regex 3", value3, validator.validate(value3));
    }

    public void testRegex_10_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);

        String value2  = "12";
        String value3  = "123";
        String value4  = "1234";
        String value5  = "12345";
        String invalid = "12a4";


        String regex = "^([0-9]{3,4})$";
        validator = new CodeValidator(regex, -1, -1, (CheckDigit)null);
        assertEquals("Regex 4", value4, validator.validate(value4));
    }

    public void testRegex_11_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);

        String value2  = "12";
        String value3  = "123";
        String value4  = "1234";
        String value5  = "12345";
        String invalid = "12a4";


        String regex = "^([0-9]{3,4})$";
        validator = new CodeValidator(regex, -1, -1, (CheckDigit)null);
        assertEquals("Regex 5", null,   validator.validate(value5));
    }

    public void testRegex_12_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);

        String value2  = "12";
        String value3  = "123";
        String value4  = "1234";
        String value5  = "12345";
        String invalid = "12a4";


        String regex = "^([0-9]{3,4})$";
        validator = new CodeValidator(regex, -1, -1, (CheckDigit)null);
        assertEquals("Regex invalid", null, validator.validate(invalid));
    }

    public void testRegex_13_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);

        String value2  = "12";
        String value3  = "123";
        String value4  = "1234";
        String value5  = "12345";
        String invalid = "12a4";


        String regex = "^([0-9]{3,4})$";
        validator = new CodeValidator(regex, -1, -1, (CheckDigit)null);

        regex = "^([0-9]{3})(?:[-\\s])([0-9]{3})$";
        validator = new CodeValidator(new RegexValidator(regex), 6, (CheckDigit)null);
        assertEquals("Reformat 123-456", "123456", validator.validate("123-456"));
    }

    public void testRegex_14_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);

        String value2  = "12";
        String value3  = "123";
        String value4  = "1234";
        String value5  = "12345";
        String invalid = "12a4";


        String regex = "^([0-9]{3,4})$";
        validator = new CodeValidator(regex, -1, -1, (CheckDigit)null);

        regex = "^([0-9]{3})(?:[-\\s])([0-9]{3})$";
        validator = new CodeValidator(new RegexValidator(regex), 6, (CheckDigit)null);
        assertEquals("Reformat 123 456", "123456", validator.validate("123 456"));
    }

    public void testRegex_15_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);

        String value2  = "12";
        String value3  = "123";
        String value4  = "1234";
        String value5  = "12345";
        String invalid = "12a4";


        String regex = "^([0-9]{3,4})$";
        validator = new CodeValidator(regex, -1, -1, (CheckDigit)null);

        regex = "^([0-9]{3})(?:[-\\s])([0-9]{3})$";
        validator = new CodeValidator(new RegexValidator(regex), 6, (CheckDigit)null);
        assertEquals("Reformat 123456",  null,     validator.validate("123456"));
    }

    public void testRegex_16_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);

        String value2  = "12";
        String value3  = "123";
        String value4  = "1234";
        String value5  = "12345";
        String invalid = "12a4";


        String regex = "^([0-9]{3,4})$";
        validator = new CodeValidator(regex, -1, -1, (CheckDigit)null);

        regex = "^([0-9]{3})(?:[-\\s])([0-9]{3})$";
        validator = new CodeValidator(new RegexValidator(regex), 6, (CheckDigit)null);
        assertEquals("Reformat 123.456", null,     validator.validate("123.456"));
    }

    public void testRegex_17_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);

        String value2  = "12";
        String value3  = "123";
        String value4  = "1234";
        String value5  = "12345";
        String invalid = "12a4";


        String regex = "^([0-9]{3,4})$";
        validator = new CodeValidator(regex, -1, -1, (CheckDigit)null);

        regex = "^([0-9]{3})(?:[-\\s])([0-9]{3})$";
        validator = new CodeValidator(new RegexValidator(regex), 6, (CheckDigit)null);

        regex = "^(?:([0-9]{3})(?:[-\\s])([0-9]{3}))|([0-9]{6})$";
        validator = new CodeValidator(new RegexValidator(regex), 6, (CheckDigit)null);
        assertEquals("Reformat 2 Regex",  "RegexValidator{" + regex + "}", validator.getRegexValidator().toString());
    }

    public void testRegex_18_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);

        String value2  = "12";
        String value3  = "123";
        String value4  = "1234";
        String value5  = "12345";
        String invalid = "12a4";


        String regex = "^([0-9]{3,4})$";
        validator = new CodeValidator(regex, -1, -1, (CheckDigit)null);

        regex = "^([0-9]{3})(?:[-\\s])([0-9]{3})$";
        validator = new CodeValidator(new RegexValidator(regex), 6, (CheckDigit)null);

        regex = "^(?:([0-9]{3})(?:[-\\s])([0-9]{3}))|([0-9]{6})$";
        validator = new CodeValidator(new RegexValidator(regex), 6, (CheckDigit)null);
        assertEquals("Reformat 2 123-456", "123456", validator.validate("123-456"));
    }

    public void testRegex_19_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);

        String value2  = "12";
        String value3  = "123";
        String value4  = "1234";
        String value5  = "12345";
        String invalid = "12a4";


        String regex = "^([0-9]{3,4})$";
        validator = new CodeValidator(regex, -1, -1, (CheckDigit)null);

        regex = "^([0-9]{3})(?:[-\\s])([0-9]{3})$";
        validator = new CodeValidator(new RegexValidator(regex), 6, (CheckDigit)null);

        regex = "^(?:([0-9]{3})(?:[-\\s])([0-9]{3}))|([0-9]{6})$";
        validator = new CodeValidator(new RegexValidator(regex), 6, (CheckDigit)null);
        assertEquals("Reformat 2 123 456", "123456", validator.validate("123 456"));
    }

    public void testRegex_20_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);

        String value2  = "12";
        String value3  = "123";
        String value4  = "1234";
        String value5  = "12345";
        String invalid = "12a4";


        String regex = "^([0-9]{3,4})$";
        validator = new CodeValidator(regex, -1, -1, (CheckDigit)null);

        regex = "^([0-9]{3})(?:[-\\s])([0-9]{3})$";
        validator = new CodeValidator(new RegexValidator(regex), 6, (CheckDigit)null);

        regex = "^(?:([0-9]{3})(?:[-\\s])([0-9]{3}))|([0-9]{6})$";
        validator = new CodeValidator(new RegexValidator(regex), 6, (CheckDigit)null);
        assertEquals("Reformat 2 123456",  "123456", validator.validate("123456"));
    }

    public void testNoInput_1_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        assertEquals("Null",         null, validator.validate(null));
    }

    public void testNoInput_2_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        assertEquals("Zero Length",  null, validator.validate(""));
    }

    public void testNoInput_3_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        assertEquals("Spaces",       null, validator.validate("   "));
    }

    public void testNoInput_4_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, -1, (CheckDigit)null);
        assertEquals("Trimmed",      "A",  validator.validate(" A  "));
    }

    public void testValidator294_1_1_oe() {
        CodeValidator validator = new CodeValidator((String)null, 0, -1, (CheckDigit)null);
        assertEquals("Null",         null, validator.validate(null));
    }

    public void testValidator294_1_2_oe() {
        CodeValidator validator = new CodeValidator((String)null, 0, -1, (CheckDigit)null);
        validator = new CodeValidator((String)null, -1, 0, (CheckDigit)null);
        assertEquals("Null",         null, validator.validate(null));
    }

    public void testValidator294_2_1_oe() {
        CodeValidator validator = new CodeValidator((String)null, -1, 0, (CheckDigit)null);
        assertEquals("Null",         null, validator.validate(null));
    }

    public void testConstructors_1_oe() {
        CodeValidator validator = null;
        RegexValidator regex = new RegexValidator("^[0-9]*$");

        validator = new CodeValidator(regex, EAN13CheckDigit.EAN13_CHECK_DIGIT);
        assertEquals("Constructor 1 - regex",      regex, validator.getRegexValidator());
    }

    public void testConstructors_2_oe() {
        CodeValidator validator = null;
        RegexValidator regex = new RegexValidator("^[0-9]*$");

        validator = new CodeValidator(regex, EAN13CheckDigit.EAN13_CHECK_DIGIT);
        assertEquals("Constructor 1 - min length", -1, validator.getMinLength());
    }

    public void testConstructors_3_oe() {
        CodeValidator validator = null;
        RegexValidator regex = new RegexValidator("^[0-9]*$");

        validator = new CodeValidator(regex, EAN13CheckDigit.EAN13_CHECK_DIGIT);
        assertEquals("Constructor 1 - max length", -1, validator.getMaxLength());
    }

    public void testConstructors_4_oe() {
        CodeValidator validator = null;
        RegexValidator regex = new RegexValidator("^[0-9]*$");

        validator = new CodeValidator(regex, EAN13CheckDigit.EAN13_CHECK_DIGIT);
        assertEquals("Constructor 1 - check digit", EAN13CheckDigit.EAN13_CHECK_DIGIT, validator.getCheckDigit());
    }

    public void testConstructors_5_oe() {
        CodeValidator validator = null;
        RegexValidator regex = new RegexValidator("^[0-9]*$");

        validator = new CodeValidator(regex, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);
        assertEquals("Constructor 2 - regex",      regex, validator.getRegexValidator());
    }

    public void testConstructors_6_oe() {
        CodeValidator validator = null;
        RegexValidator regex = new RegexValidator("^[0-9]*$");

        validator = new CodeValidator(regex, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);
        assertEquals("Constructor 2 - min length", 13, validator.getMinLength());
    }

    public void testConstructors_7_oe() {
        CodeValidator validator = null;
        RegexValidator regex = new RegexValidator("^[0-9]*$");

        validator = new CodeValidator(regex, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);
        assertEquals("Constructor 2 - max length", 13, validator.getMaxLength());
    }

    public void testConstructors_8_oe() {
        CodeValidator validator = null;
        RegexValidator regex = new RegexValidator("^[0-9]*$");

        validator = new CodeValidator(regex, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);
        assertEquals("Constructor 2 - check digit", EAN13CheckDigit.EAN13_CHECK_DIGIT, validator.getCheckDigit());
    }

    public void testConstructors_9_oe() {
        CodeValidator validator = null;
        RegexValidator regex = new RegexValidator("^[0-9]*$");

        validator = new CodeValidator(regex, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 10, 20, EAN13CheckDigit.EAN13_CHECK_DIGIT);
        assertEquals("Constructor 3 - regex",      regex, validator.getRegexValidator());
    }

    public void testConstructors_10_oe() {
        CodeValidator validator = null;
        RegexValidator regex = new RegexValidator("^[0-9]*$");

        validator = new CodeValidator(regex, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 10, 20, EAN13CheckDigit.EAN13_CHECK_DIGIT);
        assertEquals("Constructor 3 - min length", 10, validator.getMinLength());
    }

    public void testConstructors_11_oe() {
        CodeValidator validator = null;
        RegexValidator regex = new RegexValidator("^[0-9]*$");

        validator = new CodeValidator(regex, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 10, 20, EAN13CheckDigit.EAN13_CHECK_DIGIT);
        assertEquals("Constructor 3 - max length", 20, validator.getMaxLength());
    }

    public void testConstructors_12_oe() {
        CodeValidator validator = null;
        RegexValidator regex = new RegexValidator("^[0-9]*$");

        validator = new CodeValidator(regex, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 10, 20, EAN13CheckDigit.EAN13_CHECK_DIGIT);
        assertEquals("Constructor 3 - check digit", EAN13CheckDigit.EAN13_CHECK_DIGIT, validator.getCheckDigit());
    }

    public void testConstructors_13_oe() {
        CodeValidator validator = null;
        RegexValidator regex = new RegexValidator("^[0-9]*$");

        validator = new CodeValidator(regex, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 10, 20, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator("^[0-9]*$", EAN13CheckDigit.EAN13_CHECK_DIGIT);
        assertEquals("Constructor 4 - regex",      "RegexValidator{^[0-9]*$}", validator.getRegexValidator().toString());
    }

    public void testConstructors_14_oe() {
        CodeValidator validator = null;
        RegexValidator regex = new RegexValidator("^[0-9]*$");

        validator = new CodeValidator(regex, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 10, 20, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator("^[0-9]*$", EAN13CheckDigit.EAN13_CHECK_DIGIT);
        assertEquals("Constructor 4 - min length", -1, validator.getMinLength());
    }

    public void testConstructors_15_oe() {
        CodeValidator validator = null;
        RegexValidator regex = new RegexValidator("^[0-9]*$");

        validator = new CodeValidator(regex, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 10, 20, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator("^[0-9]*$", EAN13CheckDigit.EAN13_CHECK_DIGIT);
        assertEquals("Constructor 4 - max length", -1, validator.getMaxLength());
    }

    public void testConstructors_16_oe() {
        CodeValidator validator = null;
        RegexValidator regex = new RegexValidator("^[0-9]*$");

        validator = new CodeValidator(regex, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 10, 20, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator("^[0-9]*$", EAN13CheckDigit.EAN13_CHECK_DIGIT);
        assertEquals("Constructor 4 - check digit", EAN13CheckDigit.EAN13_CHECK_DIGIT, validator.getCheckDigit());
    }

    public void testConstructors_17_oe() {
        CodeValidator validator = null;
        RegexValidator regex = new RegexValidator("^[0-9]*$");

        validator = new CodeValidator(regex, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 10, 20, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator("^[0-9]*$", EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator("^[0-9]*$", 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);
        assertEquals("Constructor 5 - regex",      "RegexValidator{^[0-9]*$}", validator.getRegexValidator().toString());
    }

    public void testConstructors_18_oe() {
        CodeValidator validator = null;
        RegexValidator regex = new RegexValidator("^[0-9]*$");

        validator = new CodeValidator(regex, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 10, 20, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator("^[0-9]*$", EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator("^[0-9]*$", 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);
        assertEquals("Constructor 5 - min length", 13, validator.getMinLength());
    }

    public void testConstructors_19_oe() {
        CodeValidator validator = null;
        RegexValidator regex = new RegexValidator("^[0-9]*$");

        validator = new CodeValidator(regex, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 10, 20, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator("^[0-9]*$", EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator("^[0-9]*$", 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);
        assertEquals("Constructor 5 - max length", 13, validator.getMaxLength());
    }

    public void testConstructors_20_oe() {
        CodeValidator validator = null;
        RegexValidator regex = new RegexValidator("^[0-9]*$");

        validator = new CodeValidator(regex, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 10, 20, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator("^[0-9]*$", EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator("^[0-9]*$", 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);
        assertEquals("Constructor 5 - check digit", EAN13CheckDigit.EAN13_CHECK_DIGIT, validator.getCheckDigit());
    }

    public void testConstructors_21_oe() {
        CodeValidator validator = null;
        RegexValidator regex = new RegexValidator("^[0-9]*$");

        validator = new CodeValidator(regex, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 10, 20, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator("^[0-9]*$", EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator("^[0-9]*$", 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator("^[0-9]*$", 10, 20, EAN13CheckDigit.EAN13_CHECK_DIGIT);
        assertEquals("Constructor 6 - regex",      "RegexValidator{^[0-9]*$}", validator.getRegexValidator().toString());
    }

    public void testConstructors_22_oe() {
        CodeValidator validator = null;
        RegexValidator regex = new RegexValidator("^[0-9]*$");

        validator = new CodeValidator(regex, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 10, 20, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator("^[0-9]*$", EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator("^[0-9]*$", 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator("^[0-9]*$", 10, 20, EAN13CheckDigit.EAN13_CHECK_DIGIT);
        assertEquals("Constructor 6 - min length", 10, validator.getMinLength());
    }

    public void testConstructors_23_oe() {
        CodeValidator validator = null;
        RegexValidator regex = new RegexValidator("^[0-9]*$");

        validator = new CodeValidator(regex, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 10, 20, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator("^[0-9]*$", EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator("^[0-9]*$", 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator("^[0-9]*$", 10, 20, EAN13CheckDigit.EAN13_CHECK_DIGIT);
        assertEquals("Constructor 6 - max length", 20, validator.getMaxLength());
    }

    public void testConstructors_24_oe() {
        CodeValidator validator = null;
        RegexValidator regex = new RegexValidator("^[0-9]*$");

        validator = new CodeValidator(regex, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator(regex, 10, 20, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator("^[0-9]*$", EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator("^[0-9]*$", 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);

        validator = new CodeValidator("^[0-9]*$", 10, 20, EAN13CheckDigit.EAN13_CHECK_DIGIT);
        assertEquals("Constructor 6 - check digit", EAN13CheckDigit.EAN13_CHECK_DIGIT, validator.getCheckDigit());
    }

}
