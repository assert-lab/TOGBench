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

import org.xml.sax.SAXException;

/**
 * Performs Validation Test.
 *
 * @version $Revision$
 */
public class MultipleTest_OE25Dev extends AbstractCommonTest {

   /**
    * The key used to retrieve the set of validation
    * rules from the xml file.
    */
   protected static String FORM_KEY = "nameForm";

   /**
    * The key used to retrieve the validator action.
    */
   protected static String ACTION = "required";



   public MultipleTest_OE25Dev(String name) {
       super(name);
   }

   /**
    * Load <code>ValidatorResources</code> from
    * validator-multipletest.xml.
    */
   @Override
protected void setUp() throws IOException, SAXException {
      // Load resources
      loadResources("MultipleTests-config.xml");
   }

   @Override
protected void tearDown() {
   }

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

   /**
    * If middle name is not there, then the required dependent test should fail.
    * No other tests should run
    *
    * @throws ValidatorException
    */

   /**
    * If middle name is there but not int, then the required dependent test
    * should pass, but the int dependent test should fail. No other tests should
    * run.
    *
    * @throws ValidatorException
    */

   /**
    * If middle name is there and a negative int, then the required and int
    * dependent tests should pass, but the positive test should fail.
    *
    * @throws ValidatorException
    */

   /**
    * If middle name is there and a positve int, then the required and int
    * dependent tests should pass, and the positive test should pass.
    *
    * @throws ValidatorException
    */

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

      assertNotNull("First Name ValidatorResult should not be null.", firstNameResult);
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
      assertTrue("First Name ValidatorResult should contain the '" + ACTION +"' action.", firstNameResult.containsAction(ACTION));
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
      assertTrue("First Name ValidatorResult for the '" + ACTION +"' action should have failed.", !firstNameResult.isValid(ACTION));
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

      assertNotNull("Last Name ValidatorResult should not be null.", lastNameResult);
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
      assertTrue("Last Name ValidatorResult should contain the '" + ACTION +"' action.", lastNameResult.containsAction(ACTION));
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
      assertTrue("Last Name ValidatorResult for the '" + ACTION +"' action should have failed.", !lastNameResult.isValid(ACTION));
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
      assertTrue("Last Name ValidatorResults should not contain the 'int' action.", !lastNameResult.containsAction("int"));
   }

public void testRequiredFirstNameBlankLastNameShort_1_oe() throws ValidatorException {
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

public void testRequiredFirstNameBlankLastNameShort_2_oe() throws ValidatorException {
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

      assertNotNull("First Name ValidatorResult should not be null.", firstNameResult);
   }

public void testRequiredFirstNameBlankLastNameShort_3_oe() throws ValidatorException {
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
      assertTrue("First Name ValidatorResult should contain the '" + ACTION +"' action.", firstNameResult.containsAction(ACTION));
   }

public void testRequiredFirstNameBlankLastNameShort_4_oe() throws ValidatorException {
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
      assertTrue("First Name ValidatorResult for the '" + ACTION +"' action should have failed.", !firstNameResult.isValid(ACTION));
   }

public void testRequiredFirstNameBlankLastNameShort_5_oe() throws ValidatorException {
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

      assertNotNull("Last Name ValidatorResult should not be null.", lastNameResult);
   }

public void testRequiredFirstNameBlankLastNameShort_6_oe() throws ValidatorException {
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
      assertTrue("Last Name ValidatorResult should contain the 'int' action.", lastNameResult.containsAction("int"));
   }

public void testRequiredFirstNameBlankLastNameShort_7_oe() throws ValidatorException {
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
      assertTrue("Last Name ValidatorResult for the 'int' action should have failed.", !lastNameResult.isValid("int"));
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

      assertNotNull("First Name ValidatorResult should not be null.", firstNameResult);
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
      assertTrue("First Name ValidatorResult should contain the '" + ACTION +"' action.", firstNameResult.containsAction(ACTION));
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
      assertTrue("First Name ValidatorResult for the '" + ACTION +"' action should have passed.", firstNameResult.isValid(ACTION));
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

      assertNotNull("Last Name ValidatorResult should not be null.", lastNameResult);
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
      assertTrue("Last Name ValidatorResult should contain the 'int' action.", lastNameResult.containsAction("int"));
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
      assertTrue("Last Name ValidatorResult for the 'int' action should have failed.", !lastNameResult.isValid("int"));
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

      assertNotNull("First Name ValidatorResult should not be null.", firstNameResult);
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
      assertTrue("First Name ValidatorResult should contain the '" + ACTION +"' action.", firstNameResult.containsAction(ACTION));
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
      assertTrue("First Name ValidatorResult for the '" + ACTION +"' action should have passed.", firstNameResult.isValid(ACTION));
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

      assertNotNull("Last Name ValidatorResult should not be null.", lastNameResult);
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
      assertTrue("Last Name ValidatorResult should contain the 'int' action.", lastNameResult.containsAction("int"));
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
      assertTrue("Last Name ValidatorResult for the 'int' action should have passed.", lastNameResult.isValid("int"));
   }

public void testFailingFirstDependentValidator_1_oe() throws ValidatorException {
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

       results = validator.validate();

       assertNotNull("Results are null.", results);
   }

public void testFailingFirstDependentValidator_2_oe() throws ValidatorException {
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

       results = validator.validate();

       // removed other assertion

       ValidatorResult middleNameResult = results.getValidatorResult("middleName");

       assertNotNull("Middle Name ValidatorResult should not be null.", middleNameResult);
   }

public void testFailingFirstDependentValidator_3_oe() throws ValidatorException {
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

       results = validator.validate();

       // removed other assertion

       ValidatorResult middleNameResult = results.getValidatorResult("middleName");

       // removed other assertion

       assertTrue("Middle Name ValidatorResult should contain the 'required' action.", middleNameResult.containsAction("required"));
   }

public void testFailingFirstDependentValidator_4_oe() throws ValidatorException {
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

       results = validator.validate();

       // removed other assertion

       ValidatorResult middleNameResult = results.getValidatorResult("middleName");

       // removed other assertion

       // removed other assertion
       assertTrue("Middle Name ValidatorResult for the 'required' action should have failed", !middleNameResult.isValid("required"));
   }

public void testFailingFirstDependentValidator_5_oe() throws ValidatorException {
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

       results = validator.validate();

       // removed other assertion

       ValidatorResult middleNameResult = results.getValidatorResult("middleName");

       // removed other assertion

       // removed other assertion
       // removed other assertion

       assertTrue("Middle Name ValidatorResult should not contain the 'int' action.", !middleNameResult.containsAction("int"));
   }

public void testFailingFirstDependentValidator_6_oe() throws ValidatorException {
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

       results = validator.validate();

       // removed other assertion

       ValidatorResult middleNameResult = results.getValidatorResult("middleName");

       // removed other assertion

       // removed other assertion
       // removed other assertion

       // removed other assertion

       assertTrue("Middle Name ValidatorResult should not contain the 'positive' action.", !middleNameResult.containsAction("positive"));
   }

public void testFailingNextDependentValidator_1_oe() throws ValidatorException {
       // Create bean to run test on.
       NameBean name = new NameBean();
       name.setMiddleName("TEST");

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

public void testFailingNextDependentValidator_2_oe() throws ValidatorException {
       // Create bean to run test on.
       NameBean name = new NameBean();
       name.setMiddleName("TEST");

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

       ValidatorResult middleNameResult = results.getValidatorResult("middleName");

       assertNotNull("Middle Name ValidatorResult should not be null.", middleNameResult);
   }

public void testFailingNextDependentValidator_3_oe() throws ValidatorException {
       // Create bean to run test on.
       NameBean name = new NameBean();
       name.setMiddleName("TEST");

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

       ValidatorResult middleNameResult = results.getValidatorResult("middleName");

       // removed other assertion

       assertTrue("Middle Name ValidatorResult should contain the 'required' action.", middleNameResult.containsAction("required"));
   }

public void testFailingNextDependentValidator_4_oe() throws ValidatorException {
       // Create bean to run test on.
       NameBean name = new NameBean();
       name.setMiddleName("TEST");

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

       ValidatorResult middleNameResult = results.getValidatorResult("middleName");

       // removed other assertion

       // removed other assertion
       assertTrue("Middle Name ValidatorResult for the 'required' action should have passed", middleNameResult.isValid("required"));
   }

public void testFailingNextDependentValidator_5_oe() throws ValidatorException {
       // Create bean to run test on.
       NameBean name = new NameBean();
       name.setMiddleName("TEST");

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

       ValidatorResult middleNameResult = results.getValidatorResult("middleName");

       // removed other assertion

       // removed other assertion
       // removed other assertion

       assertTrue("Middle Name ValidatorResult should contain the 'int' action.", middleNameResult.containsAction("int"));
   }

public void testFailingNextDependentValidator_6_oe() throws ValidatorException {
       // Create bean to run test on.
       NameBean name = new NameBean();
       name.setMiddleName("TEST");

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

       ValidatorResult middleNameResult = results.getValidatorResult("middleName");

       // removed other assertion

       // removed other assertion
       // removed other assertion

       // removed other assertion
       assertTrue("Middle Name ValidatorResult for the 'int' action should have failed", !middleNameResult.isValid("int"));
   }

public void testFailingNextDependentValidator_7_oe() throws ValidatorException {
       // Create bean to run test on.
       NameBean name = new NameBean();
       name.setMiddleName("TEST");

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

       ValidatorResult middleNameResult = results.getValidatorResult("middleName");

       // removed other assertion

       // removed other assertion
       // removed other assertion

       // removed other assertion
       // removed other assertion

       assertTrue("Middle Name ValidatorResult should not contain the 'positive' action.", !middleNameResult.containsAction("positive"));
   }

public void testPassingDependentsFailingMain_1_oe() throws ValidatorException {
       // Create bean to run test on.
       NameBean name = new NameBean();
       name.setMiddleName("-2534");

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

public void testPassingDependentsFailingMain_2_oe() throws ValidatorException {
       // Create bean to run test on.
       NameBean name = new NameBean();
       name.setMiddleName("-2534");

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

       ValidatorResult middleNameResult = results.getValidatorResult("middleName");

       assertNotNull("Middle Name ValidatorResult should not be null.", middleNameResult);
   }

public void testPassingDependentsFailingMain_3_oe() throws ValidatorException {
       // Create bean to run test on.
       NameBean name = new NameBean();
       name.setMiddleName("-2534");

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

       ValidatorResult middleNameResult = results.getValidatorResult("middleName");

       // removed other assertion

       assertTrue("Middle Name ValidatorResult should contain the 'required' action.", middleNameResult.containsAction("required"));
   }

public void testPassingDependentsFailingMain_4_oe() throws ValidatorException {
       // Create bean to run test on.
       NameBean name = new NameBean();
       name.setMiddleName("-2534");

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

       ValidatorResult middleNameResult = results.getValidatorResult("middleName");

       // removed other assertion

       // removed other assertion
       assertTrue("Middle Name ValidatorResult for the 'required' action should have passed", middleNameResult.isValid("required"));
   }

public void testPassingDependentsFailingMain_5_oe() throws ValidatorException {
       // Create bean to run test on.
       NameBean name = new NameBean();
       name.setMiddleName("-2534");

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

       ValidatorResult middleNameResult = results.getValidatorResult("middleName");

       // removed other assertion

       // removed other assertion
       // removed other assertion

       assertTrue("Middle Name ValidatorResult should contain the 'int' action.", middleNameResult.containsAction("int"));
   }

public void testPassingDependentsFailingMain_6_oe() throws ValidatorException {
       // Create bean to run test on.
       NameBean name = new NameBean();
       name.setMiddleName("-2534");

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

       ValidatorResult middleNameResult = results.getValidatorResult("middleName");

       // removed other assertion

       // removed other assertion
       // removed other assertion

       // removed other assertion
       assertTrue("Middle Name ValidatorResult for the 'int' action should have passed", middleNameResult.isValid("int"));
   }

public void testPassingDependentsFailingMain_7_oe() throws ValidatorException {
       // Create bean to run test on.
       NameBean name = new NameBean();
       name.setMiddleName("-2534");

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

       ValidatorResult middleNameResult = results.getValidatorResult("middleName");

       // removed other assertion

       // removed other assertion
       // removed other assertion

       // removed other assertion
       // removed other assertion

       assertTrue("Middle Name ValidatorResult should contain the 'positive' action.", middleNameResult.containsAction("positive"));
   }

public void testPassingDependentsFailingMain_8_oe() throws ValidatorException {
       // Create bean to run test on.
       NameBean name = new NameBean();
       name.setMiddleName("-2534");

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

       ValidatorResult middleNameResult = results.getValidatorResult("middleName");

       // removed other assertion

       // removed other assertion
       // removed other assertion

       // removed other assertion
       // removed other assertion

       // removed other assertion
       assertTrue("Middle Name ValidatorResult for the 'positive' action should have failed", !middleNameResult.isValid("positive"));
   }

public void testPassingDependentsPassingMain_1_oe() throws ValidatorException {
       // Create bean to run test on.
       NameBean name = new NameBean();
       name.setMiddleName("2534");

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

public void testPassingDependentsPassingMain_2_oe() throws ValidatorException {
       // Create bean to run test on.
       NameBean name = new NameBean();
       name.setMiddleName("2534");

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

       ValidatorResult middleNameResult = results.getValidatorResult("middleName");

       assertNotNull("Middle Name ValidatorResult should not be null.", middleNameResult);
   }

public void testPassingDependentsPassingMain_3_oe() throws ValidatorException {
       // Create bean to run test on.
       NameBean name = new NameBean();
       name.setMiddleName("2534");

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

       ValidatorResult middleNameResult = results.getValidatorResult("middleName");

       // removed other assertion

       assertTrue("Middle Name ValidatorResult should contain the 'required' action.", middleNameResult.containsAction("required"));
   }

public void testPassingDependentsPassingMain_4_oe() throws ValidatorException {
       // Create bean to run test on.
       NameBean name = new NameBean();
       name.setMiddleName("2534");

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

       ValidatorResult middleNameResult = results.getValidatorResult("middleName");

       // removed other assertion

       // removed other assertion
       assertTrue("Middle Name ValidatorResult for the 'required' action should have passed", middleNameResult.isValid("required"));
   }

public void testPassingDependentsPassingMain_5_oe() throws ValidatorException {
       // Create bean to run test on.
       NameBean name = new NameBean();
       name.setMiddleName("2534");

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

       ValidatorResult middleNameResult = results.getValidatorResult("middleName");

       // removed other assertion

       // removed other assertion
       // removed other assertion

       assertTrue("Middle Name ValidatorResult should contain the 'int' action.", middleNameResult.containsAction("int"));
   }

public void testPassingDependentsPassingMain_6_oe() throws ValidatorException {
       // Create bean to run test on.
       NameBean name = new NameBean();
       name.setMiddleName("2534");

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

       ValidatorResult middleNameResult = results.getValidatorResult("middleName");

       // removed other assertion

       // removed other assertion
       // removed other assertion

       // removed other assertion
       assertTrue("Middle Name ValidatorResult for the 'int' action should have passed", middleNameResult.isValid("int"));
   }

public void testPassingDependentsPassingMain_7_oe() throws ValidatorException {
       // Create bean to run test on.
       NameBean name = new NameBean();
       name.setMiddleName("2534");

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

       ValidatorResult middleNameResult = results.getValidatorResult("middleName");

       // removed other assertion

       // removed other assertion
       // removed other assertion

       // removed other assertion
       // removed other assertion

       assertTrue("Middle Name ValidatorResult should contain the 'positive' action.", middleNameResult.containsAction("positive"));
   }

public void testPassingDependentsPassingMain_8_oe() throws ValidatorException {
       // Create bean to run test on.
       NameBean name = new NameBean();
       name.setMiddleName("2534");

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

       ValidatorResult middleNameResult = results.getValidatorResult("middleName");

       // removed other assertion

       // removed other assertion
       // removed other assertion

       // removed other assertion
       // removed other assertion

       // removed other assertion
       assertTrue("Middle Name ValidatorResult for the 'positive' action should have passed", middleNameResult.isValid("positive"));
   }

}
