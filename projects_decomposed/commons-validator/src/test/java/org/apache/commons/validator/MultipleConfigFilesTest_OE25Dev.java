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

import java.io.IOException;
import java.io.InputStream;

import junit.framework.TestCase;

import org.xml.sax.SAXException;

/**
 * Tests that validator rules split between 2 different XML files get 
 * merged properly.
 *
 * @version $Revision$
 */
public class MultipleConfigFilesTest_OE25Dev extends TestCase {

    /**
     * Resources used for validation tests.
     */
    private ValidatorResources resources = null;
    
    /**
     * The key used to retrieve the set of validation
     * rules from the xml file.
     */
    private static final String FORM_KEY = "nameForm";

    /**
     * The key used to retrieve the validator action.
     */
    private static final String ACTION = "required";

    /**
     * Constructor for MultipleConfigFilesTest_OE25Dev.
     * @param name
     */
    public MultipleConfigFilesTest_OE25Dev(String name) {
        super(name);
    }

    /** 
     * Load <code>ValidatorResources</code> from multiple xml files.
     */
    @Override
    protected void setUp() throws IOException, SAXException {
        InputStream[] streams =
            new InputStream[] {
                this.getClass().getResourceAsStream(
                    "MultipleConfigFilesTest_OE25Dev-1-config.xml"),
                this.getClass().getResourceAsStream(
                    "MultipleConfigFilesTest_OE25Dev-2-config.xml")};

        this.resources = new ValidatorResources(streams);

        for (int i = 0; i < streams.length; i++) {
            streams[i].close();
        }
    }

   /**
    * Check the forms and constants from different config files have
    * been merged into the same FormSet.
    */

    /**
    * With nothing provided, we should fail both because both are required.
    */

    /**
     * If the first name fails required, and the second test fails int, we should get two errors.
    */

    /**
     * If the first name is there, and the last name fails int, we should get one error.
    */

    /**
     * If first name is ok and last name is ok and is an int, no errors.
    */

    public void testMergedConfig_1_oe() {

        // *********** Default Locale *******************

        // Check the form from the first config file exists
        Form form1 = resources.getForm("", "", "", "testForm1");
        assertNotNull("Form 'testForm1' not found", form1);
    }

    public void testMergedConfig_2_oe() {

        // *********** Default Locale *******************

        // Check the form from the first config file exists
        Form form1 = resources.getForm("", "", "", "testForm1");
        // removed other assertion

        // Check the form from the second config file exists
        Form form2 = resources.getForm("", "", "", "testForm2");
        assertNotNull("Form 'testForm2' not found", form2);
    }

    public void testMergedConfig_3_oe() {

        // *********** Default Locale *******************

        // Check the form from the first config file exists
        Form form1 = resources.getForm("", "", "", "testForm1");
        // removed other assertion

        // Check the form from the second config file exists
        Form form2 = resources.getForm("", "", "", "testForm2");
        // removed other assertion

        // Check the Constants  for the form from the first config file
        Field field1 = form1.getField("testProperty1");
        assertEquals("testProperty1 - const 1", "testConstValue1", field1.getVarValue("var11"));
    }

    public void testMergedConfig_4_oe() {

        // *********** Default Locale *******************

        // Check the form from the first config file exists
        Form form1 = resources.getForm("", "", "", "testForm1");
        // removed other assertion

        // Check the form from the second config file exists
        Form form2 = resources.getForm("", "", "", "testForm2");
        // removed other assertion

        // Check the Constants  for the form from the first config file
        Field field1 = form1.getField("testProperty1");
        // removed other assertion
        assertEquals("testProperty1 - const 2", "testConstValue2", field1.getVarValue("var12"));
    }

    public void testMergedConfig_5_oe() {

        // *********** Default Locale *******************

        // Check the form from the first config file exists
        Form form1 = resources.getForm("", "", "", "testForm1");
        // removed other assertion

        // Check the form from the second config file exists
        Form form2 = resources.getForm("", "", "", "testForm2");
        // removed other assertion

        // Check the Constants  for the form from the first config file
        Field field1 = form1.getField("testProperty1");
        // removed other assertion
        // removed other assertion

        // Check the Constants  for the form from the second config file
        Field field2 = form2.getField("testProperty2");
        assertEquals("testProperty2 - const 1", "testConstValue1", field2.getVarValue("var21"));
    }

    public void testMergedConfig_6_oe() {

        // *********** Default Locale *******************

        // Check the form from the first config file exists
        Form form1 = resources.getForm("", "", "", "testForm1");
        // removed other assertion

        // Check the form from the second config file exists
        Form form2 = resources.getForm("", "", "", "testForm2");
        // removed other assertion

        // Check the Constants  for the form from the first config file
        Field field1 = form1.getField("testProperty1");
        // removed other assertion
        // removed other assertion

        // Check the Constants  for the form from the second config file
        Field field2 = form2.getField("testProperty2");
        // removed other assertion
        assertEquals("testProperty2 - const 2", "testConstValue2", field2.getVarValue("var22"));
    }

    public void testMergedConfig_7_oe() {

        // *********** Default Locale *******************

        // Check the form from the first config file exists
        Form form1 = resources.getForm("", "", "", "testForm1");
        // removed other assertion

        // Check the form from the second config file exists
        Form form2 = resources.getForm("", "", "", "testForm2");
        // removed other assertion

        // Check the Constants  for the form from the first config file
        Field field1 = form1.getField("testProperty1");
        // removed other assertion
        // removed other assertion

        // Check the Constants  for the form from the second config file
        Field field2 = form2.getField("testProperty2");
        // removed other assertion
        // removed other assertion
 
        // *********** 'fr' locale *******************

        // Check the form from the first config file exists
        Form form1_fr = resources.getForm("fr", "", "", "testForm1_fr");
        assertNotNull("Form 'testForm1_fr' not found", form1_fr);
    }

    public void testMergedConfig_8_oe() {

        // *********** Default Locale *******************

        // Check the form from the first config file exists
        Form form1 = resources.getForm("", "", "", "testForm1");
        // removed other assertion

        // Check the form from the second config file exists
        Form form2 = resources.getForm("", "", "", "testForm2");
        // removed other assertion

        // Check the Constants  for the form from the first config file
        Field field1 = form1.getField("testProperty1");
        // removed other assertion
        // removed other assertion

        // Check the Constants  for the form from the second config file
        Field field2 = form2.getField("testProperty2");
        // removed other assertion
        // removed other assertion
 
        // *********** 'fr' locale *******************

        // Check the form from the first config file exists
        Form form1_fr = resources.getForm("fr", "", "", "testForm1_fr");
        // removed other assertion

        // Check the form from the second config file exists
        Form form2_fr = resources.getForm("fr", "", "", "testForm2_fr");
        assertNotNull("Form 'testForm2_fr' not found", form2_fr);
    }

    public void testMergedConfig_9_oe() {

        // *********** Default Locale *******************

        // Check the form from the first config file exists
        Form form1 = resources.getForm("", "", "", "testForm1");
        // removed other assertion

        // Check the form from the second config file exists
        Form form2 = resources.getForm("", "", "", "testForm2");
        // removed other assertion

        // Check the Constants  for the form from the first config file
        Field field1 = form1.getField("testProperty1");
        // removed other assertion
        // removed other assertion

        // Check the Constants  for the form from the second config file
        Field field2 = form2.getField("testProperty2");
        // removed other assertion
        // removed other assertion
 
        // *********** 'fr' locale *******************

        // Check the form from the first config file exists
        Form form1_fr = resources.getForm("fr", "", "", "testForm1_fr");
        // removed other assertion

        // Check the form from the second config file exists
        Form form2_fr = resources.getForm("fr", "", "", "testForm2_fr");
        // removed other assertion

        // Check the Constants  for the form from the first config file
        Field field1_fr = form1_fr.getField("testProperty1_fr");
        assertEquals("testProperty1_fr - const 1", "testConstValue1_fr", field1_fr.getVarValue("var11_fr"));
    }

    public void testMergedConfig_10_oe() {

        // *********** Default Locale *******************

        // Check the form from the first config file exists
        Form form1 = resources.getForm("", "", "", "testForm1");
        // removed other assertion

        // Check the form from the second config file exists
        Form form2 = resources.getForm("", "", "", "testForm2");
        // removed other assertion

        // Check the Constants  for the form from the first config file
        Field field1 = form1.getField("testProperty1");
        // removed other assertion
        // removed other assertion

        // Check the Constants  for the form from the second config file
        Field field2 = form2.getField("testProperty2");
        // removed other assertion
        // removed other assertion
 
        // *********** 'fr' locale *******************

        // Check the form from the first config file exists
        Form form1_fr = resources.getForm("fr", "", "", "testForm1_fr");
        // removed other assertion

        // Check the form from the second config file exists
        Form form2_fr = resources.getForm("fr", "", "", "testForm2_fr");
        // removed other assertion

        // Check the Constants  for the form from the first config file
        Field field1_fr = form1_fr.getField("testProperty1_fr");
        // removed other assertion
        assertEquals("testProperty1_fr - const 2", "testConstValue2_fr", field1_fr.getVarValue("var12_fr"));
    }

    public void testMergedConfig_11_oe() {

        // *********** Default Locale *******************

        // Check the form from the first config file exists
        Form form1 = resources.getForm("", "", "", "testForm1");
        // removed other assertion

        // Check the form from the second config file exists
        Form form2 = resources.getForm("", "", "", "testForm2");
        // removed other assertion

        // Check the Constants  for the form from the first config file
        Field field1 = form1.getField("testProperty1");
        // removed other assertion
        // removed other assertion

        // Check the Constants  for the form from the second config file
        Field field2 = form2.getField("testProperty2");
        // removed other assertion
        // removed other assertion
 
        // *********** 'fr' locale *******************

        // Check the form from the first config file exists
        Form form1_fr = resources.getForm("fr", "", "", "testForm1_fr");
        // removed other assertion

        // Check the form from the second config file exists
        Form form2_fr = resources.getForm("fr", "", "", "testForm2_fr");
        // removed other assertion

        // Check the Constants  for the form from the first config file
        Field field1_fr = form1_fr.getField("testProperty1_fr");
        // removed other assertion
        // removed other assertion

        // Check the Constants  for the form from the second config file
        Field field2_fr = form2_fr.getField("testProperty2_fr");
        assertEquals("testProperty2_fr - const 1", "testConstValue1_fr", field2_fr.getVarValue("var21_fr"));
    }

    public void testMergedConfig_12_oe() {

        // *********** Default Locale *******************

        // Check the form from the first config file exists
        Form form1 = resources.getForm("", "", "", "testForm1");
        // removed other assertion

        // Check the form from the second config file exists
        Form form2 = resources.getForm("", "", "", "testForm2");
        // removed other assertion

        // Check the Constants  for the form from the first config file
        Field field1 = form1.getField("testProperty1");
        // removed other assertion
        // removed other assertion

        // Check the Constants  for the form from the second config file
        Field field2 = form2.getField("testProperty2");
        // removed other assertion
        // removed other assertion
 
        // *********** 'fr' locale *******************

        // Check the form from the first config file exists
        Form form1_fr = resources.getForm("fr", "", "", "testForm1_fr");
        // removed other assertion

        // Check the form from the second config file exists
        Form form2_fr = resources.getForm("fr", "", "", "testForm2_fr");
        // removed other assertion

        // Check the Constants  for the form from the first config file
        Field field1_fr = form1_fr.getField("testProperty1_fr");
        // removed other assertion
        // removed other assertion

        // Check the Constants  for the form from the second config file
        Field field2_fr = form2_fr.getField("testProperty2_fr");
        // removed other assertion
        assertEquals("testProperty2_fr - const 2", "testConstValue2_fr", field2_fr.getVarValue("var22_fr"));
    }

    public void testBothBlank_1_oe() throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        // throws ValidatorException,
        // but we aren't catching for testing
        // since no validation methods we use
        // throw this
        results = validator.validate();

        assertNotNull("Results are null.", results);
    }

    public void testBothBlank_2_oe() throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        // throws ValidatorException,
        // but we aren't catching for testing
        // since no validation methods we use
        // throw this
        results = validator.validate();

        // removed other assertion

        ValidatorResult firstNameResult = results.getValidatorResult("firstName");
        ValidatorResult lastNameResult = results.getValidatorResult("lastName");

        assertNotNull(firstNameResult);
    }

    public void testBothBlank_3_oe() throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        // throws ValidatorException,
        // but we aren't catching for testing
        // since no validation methods we use
        // throw this
        results = validator.validate();

        // removed other assertion

        ValidatorResult firstNameResult = results.getValidatorResult("firstName");
        ValidatorResult lastNameResult = results.getValidatorResult("lastName");

        // removed other assertion
        assertTrue(firstNameResult.containsAction(ACTION));
    }

    public void testBothBlank_4_oe() throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        // throws ValidatorException,
        // but we aren't catching for testing
        // since no validation methods we use
        // throw this
        results = validator.validate();

        // removed other assertion

        ValidatorResult firstNameResult = results.getValidatorResult("firstName");
        ValidatorResult lastNameResult = results.getValidatorResult("lastName");

        // removed other assertion
        // removed other assertion
        assertTrue(!firstNameResult.isValid(ACTION));
    }

    public void testBothBlank_5_oe() throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        // throws ValidatorException,
        // but we aren't catching for testing
        // since no validation methods we use
        // throw this
        results = validator.validate();

        // removed other assertion

        ValidatorResult firstNameResult = results.getValidatorResult("firstName");
        ValidatorResult lastNameResult = results.getValidatorResult("lastName");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertNotNull(lastNameResult);
    }

    public void testBothBlank_6_oe() throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        // throws ValidatorException,
        // but we aren't catching for testing
        // since no validation methods we use
        // throw this
        results = validator.validate();

        // removed other assertion

        ValidatorResult firstNameResult = results.getValidatorResult("firstName");
        ValidatorResult lastNameResult = results.getValidatorResult("lastName");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(lastNameResult.containsAction(ACTION));
    }

    public void testBothBlank_7_oe() throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        // throws ValidatorException,
        // but we aren't catching for testing
        // since no validation methods we use
        // throw this
        results = validator.validate();

        // removed other assertion

        ValidatorResult firstNameResult = results.getValidatorResult("firstName");
        ValidatorResult lastNameResult = results.getValidatorResult("lastName");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(!lastNameResult.isValid(ACTION));
    }

    public void testBothBlank_8_oe() throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        // throws ValidatorException,
        // but we aren't catching for testing
        // since no validation methods we use
        // throw this
        results = validator.validate();

        // removed other assertion

        ValidatorResult firstNameResult = results.getValidatorResult("firstName");
        ValidatorResult lastNameResult = results.getValidatorResult("lastName");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(!lastNameResult.containsAction("int"));
    }

    public void testRequiredFirstNameBlankLastNameShort_1_oe()
        throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();
        name.setFirstName("");
        name.setLastName("Test");

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        results = validator.validate();

        assertNotNull("Results are null.", results);
    }

    public void testRequiredFirstNameBlankLastNameShort_2_oe()
        throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();
        name.setFirstName("");
        name.setLastName("Test");

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        results = validator.validate();

        // removed other assertion

        ValidatorResult firstNameResult = results.getValidatorResult("firstName");
        ValidatorResult lastNameResult = results.getValidatorResult("lastName");

        assertNotNull(firstNameResult);
    }

    public void testRequiredFirstNameBlankLastNameShort_3_oe()
        throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();
        name.setFirstName("");
        name.setLastName("Test");

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        results = validator.validate();

        // removed other assertion

        ValidatorResult firstNameResult = results.getValidatorResult("firstName");
        ValidatorResult lastNameResult = results.getValidatorResult("lastName");

        // removed other assertion
        assertTrue(firstNameResult.containsAction(ACTION));
    }

    public void testRequiredFirstNameBlankLastNameShort_4_oe()
        throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();
        name.setFirstName("");
        name.setLastName("Test");

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        results = validator.validate();

        // removed other assertion

        ValidatorResult firstNameResult = results.getValidatorResult("firstName");
        ValidatorResult lastNameResult = results.getValidatorResult("lastName");

        // removed other assertion
        // removed other assertion
        assertTrue(!firstNameResult.isValid(ACTION));
    }

    public void testRequiredFirstNameBlankLastNameShort_5_oe()
        throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();
        name.setFirstName("");
        name.setLastName("Test");

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        results = validator.validate();

        // removed other assertion

        ValidatorResult firstNameResult = results.getValidatorResult("firstName");
        ValidatorResult lastNameResult = results.getValidatorResult("lastName");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertNotNull(lastNameResult);
    }

    public void testRequiredFirstNameBlankLastNameShort_6_oe()
        throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();
        name.setFirstName("");
        name.setLastName("Test");

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        results = validator.validate();

        // removed other assertion

        ValidatorResult firstNameResult = results.getValidatorResult("firstName");
        ValidatorResult lastNameResult = results.getValidatorResult("lastName");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(lastNameResult.containsAction("int"));
    }

    public void testRequiredFirstNameBlankLastNameShort_7_oe()
        throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();
        name.setFirstName("");
        name.setLastName("Test");

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        results = validator.validate();

        // removed other assertion

        ValidatorResult firstNameResult = results.getValidatorResult("firstName");
        ValidatorResult lastNameResult = results.getValidatorResult("lastName");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(!lastNameResult.isValid("int"));
    }

    public void testRequiredLastNameShort_1_oe() throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();
        name.setFirstName("Test");
        name.setLastName("Test");

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        results = validator.validate();

        assertNotNull("Results are null.", results);
    }

    public void testRequiredLastNameShort_2_oe() throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();
        name.setFirstName("Test");
        name.setLastName("Test");

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        results = validator.validate();

        // removed other assertion

        ValidatorResult firstNameResult = results.getValidatorResult("firstName");
        ValidatorResult lastNameResult = results.getValidatorResult("lastName");

        assertNotNull(firstNameResult);
    }

    public void testRequiredLastNameShort_3_oe() throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();
        name.setFirstName("Test");
        name.setLastName("Test");

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        results = validator.validate();

        // removed other assertion

        ValidatorResult firstNameResult = results.getValidatorResult("firstName");
        ValidatorResult lastNameResult = results.getValidatorResult("lastName");

        // removed other assertion
        assertTrue(firstNameResult.containsAction(ACTION));
    }

    public void testRequiredLastNameShort_4_oe() throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();
        name.setFirstName("Test");
        name.setLastName("Test");

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        results = validator.validate();

        // removed other assertion

        ValidatorResult firstNameResult = results.getValidatorResult("firstName");
        ValidatorResult lastNameResult = results.getValidatorResult("lastName");

        // removed other assertion
        // removed other assertion
        assertTrue(firstNameResult.isValid(ACTION));
    }

    public void testRequiredLastNameShort_5_oe() throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();
        name.setFirstName("Test");
        name.setLastName("Test");

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        results = validator.validate();

        // removed other assertion

        ValidatorResult firstNameResult = results.getValidatorResult("firstName");
        ValidatorResult lastNameResult = results.getValidatorResult("lastName");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertNotNull(lastNameResult);
    }

    public void testRequiredLastNameShort_6_oe() throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();
        name.setFirstName("Test");
        name.setLastName("Test");

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        results = validator.validate();

        // removed other assertion

        ValidatorResult firstNameResult = results.getValidatorResult("firstName");
        ValidatorResult lastNameResult = results.getValidatorResult("lastName");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(lastNameResult.containsAction("int"));
    }

    public void testRequiredLastNameShort_7_oe() throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();
        name.setFirstName("Test");
        name.setLastName("Test");

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        results = validator.validate();

        // removed other assertion

        ValidatorResult firstNameResult = results.getValidatorResult("firstName");
        ValidatorResult lastNameResult = results.getValidatorResult("lastName");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(!lastNameResult.isValid("int"));
    }

    public void testRequiredLastNameLong_1_oe() throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();
        name.setFirstName("Joe");
        name.setLastName("12345678");

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        results = validator.validate();

        assertNotNull("Results are null.", results);
    }

    public void testRequiredLastNameLong_2_oe() throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();
        name.setFirstName("Joe");
        name.setLastName("12345678");

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        results = validator.validate();

        // removed other assertion

        ValidatorResult firstNameResult = results.getValidatorResult("firstName");
        ValidatorResult lastNameResult = results.getValidatorResult("lastName");

        assertNotNull(firstNameResult);
    }

    public void testRequiredLastNameLong_3_oe() throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();
        name.setFirstName("Joe");
        name.setLastName("12345678");

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        results = validator.validate();

        // removed other assertion

        ValidatorResult firstNameResult = results.getValidatorResult("firstName");
        ValidatorResult lastNameResult = results.getValidatorResult("lastName");

        // removed other assertion
        assertTrue(firstNameResult.containsAction(ACTION));
    }

    public void testRequiredLastNameLong_4_oe() throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();
        name.setFirstName("Joe");
        name.setLastName("12345678");

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        results = validator.validate();

        // removed other assertion

        ValidatorResult firstNameResult = results.getValidatorResult("firstName");
        ValidatorResult lastNameResult = results.getValidatorResult("lastName");

        // removed other assertion
        // removed other assertion
        assertTrue(firstNameResult.isValid(ACTION));
    }

    public void testRequiredLastNameLong_5_oe() throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();
        name.setFirstName("Joe");
        name.setLastName("12345678");

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        results = validator.validate();

        // removed other assertion

        ValidatorResult firstNameResult = results.getValidatorResult("firstName");
        ValidatorResult lastNameResult = results.getValidatorResult("lastName");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertNotNull(lastNameResult);
    }

    public void testRequiredLastNameLong_6_oe() throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();
        name.setFirstName("Joe");
        name.setLastName("12345678");

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        results = validator.validate();

        // removed other assertion

        ValidatorResult firstNameResult = results.getValidatorResult("firstName");
        ValidatorResult lastNameResult = results.getValidatorResult("lastName");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue(lastNameResult.containsAction("int"));
    }

    public void testRequiredLastNameLong_7_oe() throws ValidatorException {
        // Create bean to run test on.
        NameBean name = new NameBean();
        name.setFirstName("Joe");
        name.setLastName("12345678");

        // Construct validator based on the loaded resources
        // and the form key
        Validator validator = new Validator(resources, FORM_KEY);
        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);

        // Get results of the validation.
        ValidatorResults results = null;

        results = validator.validate();

        // removed other assertion

        ValidatorResult firstNameResult = results.getValidatorResult("firstName");
        ValidatorResult lastNameResult = results.getValidatorResult("lastName");

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertTrue(lastNameResult.isValid("int"));
    }

}
