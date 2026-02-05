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
public class RequiredNameTest_OE25Dev extends AbstractCommonTest {
   
   /**
    * The key used to retrieve the set of validation 
    * rules from the xml file.
    */
   protected static String FORM_KEY = "nameForm";   

   /**
    * The key used to retrieve the validator action.
    */
   protected static String ACTION = "required";

   public RequiredNameTest_OE25Dev(String name) {                  
       super(name);                                      
   }                                                     

   /**
    * Load <code>ValidatorResources</code> from 
    * validator-name-required.xml.
    */
   @Override
protected void setUp() throws IOException, SAXException {
      // Load resources
      loadResources("RequiredNameTest_OE25Dev-config.xml");
   }

   @Override
protected void tearDown() {
   }

   /**
    * Tests the required validation failure.
    */

   /**
    * Tests the required validation for first name if it is blank.
    */

   /**
    * Tests the required validation for first name.
    */

   /**
    * Tests the required validation for last name if it is blank.
    */

   /**
    * Tests the required validation for last name.
    */

   /**
    * Tests the required validation for first and last name.
    */
   
public void testRequired_1_oe() throws ValidatorException {
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

public void testRequired_2_oe() throws ValidatorException {
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

public void testRequired_3_oe() throws ValidatorException {
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

public void testRequired_4_oe() throws ValidatorException {
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

public void testRequired_5_oe() throws ValidatorException {
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
      
      assertNotNull("First Name ValidatorResult should not be null.", lastNameResult);
   }

public void testRequired_6_oe() throws ValidatorException {
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

public void testRequired_7_oe() throws ValidatorException {
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

public void testRequiredFirstNameBlank_1_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setFirstName("");
      
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

public void testRequiredFirstNameBlank_2_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setFirstName("");
      
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

public void testRequiredFirstNameBlank_3_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setFirstName("");
      
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

public void testRequiredFirstNameBlank_4_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setFirstName("");
      
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

public void testRequiredFirstNameBlank_5_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setFirstName("");
      
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
      
      assertNotNull("First Name ValidatorResult should not be null.", lastNameResult);
   }

public void testRequiredFirstNameBlank_6_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setFirstName("");
      
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
      assertTrue("Last Name ValidatorResult should contain the '" + ACTION +"' action.", lastNameResult.containsAction(ACTION));
   }

public void testRequiredFirstNameBlank_7_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setFirstName("");
      
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
      assertTrue("Last Name ValidatorResult for the '" + ACTION +"' action should have failed.", !lastNameResult.isValid(ACTION));
   }

public void testRequiredFirstName_1_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setFirstName("Joe");
      
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

public void testRequiredFirstName_2_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setFirstName("Joe");
      
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

public void testRequiredFirstName_3_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setFirstName("Joe");
      
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

public void testRequiredFirstName_4_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setFirstName("Joe");
      
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

public void testRequiredFirstName_5_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setFirstName("Joe");
      
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
      
      assertNotNull("First Name ValidatorResult should not be null.", lastNameResult);
   }

public void testRequiredFirstName_6_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setFirstName("Joe");
      
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
      assertTrue("Last Name ValidatorResult should contain the '" + ACTION +"' action.", lastNameResult.containsAction(ACTION));
   }

public void testRequiredFirstName_7_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setFirstName("Joe");
      
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
      assertTrue("Last Name ValidatorResult for the '" + ACTION +"' action should have failed.", !lastNameResult.isValid(ACTION));
   }

public void testRequiredLastNameBlank_1_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setLastName("");
      
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

public void testRequiredLastNameBlank_2_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setLastName("");
      
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

public void testRequiredLastNameBlank_3_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setLastName("");
      
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

public void testRequiredLastNameBlank_4_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setLastName("");
      
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

public void testRequiredLastNameBlank_5_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setLastName("");
      
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
      
      assertNotNull("First Name ValidatorResult should not be null.", lastNameResult);
   }

public void testRequiredLastNameBlank_6_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setLastName("");
      
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
      assertTrue("Last Name ValidatorResult should contain the '" + ACTION +"' action.", lastNameResult.containsAction(ACTION));
   }

public void testRequiredLastNameBlank_7_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setLastName("");
      
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
      assertTrue("Last Name ValidatorResult for the '" + ACTION +"' action should have failed.", !lastNameResult.isValid(ACTION));
   }

public void testRequiredLastName_1_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setLastName("Smith");
      
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

public void testRequiredLastName_2_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setLastName("Smith");
      
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

public void testRequiredLastName_3_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setLastName("Smith");
      
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

public void testRequiredLastName_4_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setLastName("Smith");
      
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

public void testRequiredLastName_5_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setLastName("Smith");
      
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
      
      assertNotNull("First Name ValidatorResult should not be null.", lastNameResult);
   }

public void testRequiredLastName_6_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setLastName("Smith");
      
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
      assertTrue("Last Name ValidatorResult should contain the '" + ACTION +"' action.", lastNameResult.containsAction(ACTION));
   }

public void testRequiredLastName_7_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setLastName("Smith");
      
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
      assertTrue("Last Name ValidatorResult for the '" + ACTION +"' action should have passed.", lastNameResult.isValid(ACTION));
   }

public void testRequiredName_1_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setFirstName("Joe");
      name.setLastName("Smith");
      
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

public void testRequiredName_2_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setFirstName("Joe");
      name.setLastName("Smith");
      
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

public void testRequiredName_3_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setFirstName("Joe");
      name.setLastName("Smith");
      
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

public void testRequiredName_4_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setFirstName("Joe");
      name.setLastName("Smith");
      
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

public void testRequiredName_5_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setFirstName("Joe");
      name.setLastName("Smith");
      
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
      
      assertNotNull("First Name ValidatorResult should not be null.", lastNameResult);
   }

public void testRequiredName_6_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setFirstName("Joe");
      name.setLastName("Smith");
      
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
      assertTrue("Last Name ValidatorResult should contain the '" + ACTION +"' action.", lastNameResult.containsAction(ACTION));
   }

public void testRequiredName_7_oe() throws ValidatorException {
      // Create bean to run test on.
      NameBean name = new NameBean();
      name.setFirstName("Joe");
      name.setLastName("Smith");
      
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
      assertTrue("Last Name ValidatorResult for the '" + ACTION +"' action should have passed.", lastNameResult.isValid(ACTION));
   }

}                                                         