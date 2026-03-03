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
 * Test the GenericValidator class.
 *
 * @version $Revision$
 */
public class GenericValidatorTest_OE25Dev extends TestCase {
    
    /**
     * Constructor for GenericValidatorTest_OE25Dev.
     */
    public GenericValidatorTest_OE25Dev(String name) {
        super(name);
    }

    public void testMinLength_1_oe() {

        assertTrue("Min=5 End=0",  GenericValidator.minLength("12345\n\r", 5, 0));
    }

    public void testMinLength_2_oe() {

        assertFalse("Min=6 End=0", GenericValidator.minLength("12345\n\r", 6, 0));
    }

    public void testMinLength_3_oe() {

        assertFalse("Min=7 End=0", GenericValidator.minLength("12345\n\r", 7, 0));
    }

    public void testMinLength_4_oe() {

        assertFalse("Min=8 End=0", GenericValidator.minLength("12345\n\r", 8, 0));
    }

    public void testMinLength_5_oe() {


        assertTrue("Min=5 End=1",  GenericValidator.minLength("12345\n\r", 5, 1));
    }

    public void testMinLength_6_oe() {


        assertTrue("Min=6 End=1",  GenericValidator.minLength("12345\n\r", 6, 1));
    }

    public void testMinLength_7_oe() {


        assertFalse("Min=7 End=1", GenericValidator.minLength("12345\n\r", 7, 1));
    }

    public void testMinLength_8_oe() {


        assertFalse("Min=8 End=1", GenericValidator.minLength("12345\n\r", 8, 1));
    }

    public void testMinLength_9_oe() {



        assertTrue("Min=5 End=2",  GenericValidator.minLength("12345\n\r", 5, 2));
    }

    public void testMinLength_10_oe() {



        assertTrue("Min=6 End=2",  GenericValidator.minLength("12345\n\r", 6, 2));
    }

    public void testMinLength_11_oe() {



        assertTrue("Min=7 End=2",  GenericValidator.minLength("12345\n\r", 7, 2));
    }

    public void testMinLength_12_oe() {



        assertFalse("Min=8 End=2", GenericValidator.minLength("12345\n\r", 8, 2));
    }

    public void testMaxLength_1_oe() {

        assertFalse("Max=4 End=0", GenericValidator.maxLength("12345\n\r", 4, 0));
    }

    public void testMaxLength_2_oe() {

        assertTrue("Max=5 End=0",  GenericValidator.maxLength("12345\n\r", 5, 0));
    }

    public void testMaxLength_3_oe() {

        assertTrue("Max=6 End=0",  GenericValidator.maxLength("12345\n\r", 6, 0));
    }

    public void testMaxLength_4_oe() {

        assertTrue("Max=7 End=0",  GenericValidator.maxLength("12345\n\r", 7, 0));
    }

    public void testMaxLength_5_oe() {


        assertFalse("Max=4 End=1", GenericValidator.maxLength("12345\n\r", 4, 1));
    }

    public void testMaxLength_6_oe() {


        assertFalse("Max=5 End=1", GenericValidator.maxLength("12345\n\r", 5, 1));
    }

    public void testMaxLength_7_oe() {


        assertTrue("Max=6 End=1",  GenericValidator.maxLength("12345\n\r", 6, 1));
    }

    public void testMaxLength_8_oe() {


        assertTrue("Max=7 End=1",  GenericValidator.maxLength("12345\n\r", 7, 1));
    }

    public void testMaxLength_9_oe() {



        assertFalse("Max=4 End=2", GenericValidator.maxLength("12345\n\r", 4, 2));
    }

    public void testMaxLength_10_oe() {



        assertFalse("Max=5 End=2", GenericValidator.maxLength("12345\n\r", 5, 2));
    }

    public void testMaxLength_11_oe() {



        assertFalse("Max=6 End=2", GenericValidator.maxLength("12345\n\r", 6, 2));
    }

    public void testMaxLength_12_oe() {



        assertTrue("Max=7 End=2",  GenericValidator.maxLength("12345\n\r", 7, 2));
    }

}
