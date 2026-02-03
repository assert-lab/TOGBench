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
import java.util.Locale;

import org.xml.sax.SAXException;

/**
 * This TestCase is a confirmation of the parameter of the validator's method.
 *
 * @version $Revision$
 */
public class ParameterTest_OE25Dev extends AbstractCommonTest {

    private static final String FORM_KEY = "nameForm";

    private String firstName;

    private String middleName;

    private String lastName;

    /**
     * Constructor.
     */
    public ParameterTest_OE25Dev(String name) {
        super(name);
    }

    /**
     * Load <code>ValidatorResources</code> from
     * ValidatorResultsTest-config.xml.
     */
    @Override
    protected void setUp() throws IOException, SAXException {
        // Load resources
        loadResources("ParameterTest_OE25Dev-config.xml");

        // initialize values
        firstName = "foo";
        middleName = "123";
        lastName = "456";

    }

    @Override
    protected void tearDown() {
    }

    /**
     * Test all validations ran and passed.
     */

    private void assertParameterValue(Validator validator, String name,
            Class<?> type) {
        Object value = validator.getParameterValue(name);
        assertNotNull("Expected '" + type.getName() + "' but was null", value);
        assertTrue("Expected '" + type.getName()+ "' but was '" + value.getClass().getName()+ "'",type.isInstance(value));
    }

    /**
     * Create a NameBean.
     */
    private NameBean createNameBean() {
        NameBean name = new NameBean();
        name.setFirstName(firstName);
        name.setMiddleName(middleName);
        name.setLastName(lastName);
        return name;
    }

    public void testAllValid_2_oe_1_oe() {

        // Create bean to run test on.
        NameBean bean = createNameBean();

        Validator validator = new Validator(resources, FORM_KEY);

        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, bean);
        validator.setParameter(Validator.LOCALE_PARAM, Locale.getDefault());

        // Get results of the validation.
        try {
            validator.validate();
        } catch(Exception e) {
            // removed other assertion
        }
                final Validator validator1 = validator;
        final String name = Validator.BEAN_PARAM;
        final Class<?> type = Object.class;
        Object value = validator1.getParameterValue(name);
                assertNotNull("Expected '" + type.getName() + "' but was null", value);
    }

    public void testAllValid_2_oe_2_oe() {

        // Create bean to run test on.
        NameBean bean = createNameBean();

        Validator validator = new Validator(resources, FORM_KEY);

        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, bean);
        validator.setParameter(Validator.LOCALE_PARAM, Locale.getDefault());

        // Get results of the validation.
        try {
            validator.validate();
        } catch(Exception e) {
            // removed other assertion
        }
                final Validator validator1 = validator;
        final String name = Validator.BEAN_PARAM;
        final Class<?> type = Object.class;
        Object value = validator1.getParameterValue(name);
                // removed other assertion
                assertTrue("Expected '" + type.getName()+ "' but was '" + value.getClass().getName()+ "'",type.isInstance(value));
    }

    public void testAllValid_3_oe_1_oe() {

        // Create bean to run test on.
        NameBean bean = createNameBean();

        Validator validator = new Validator(resources, FORM_KEY);

        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, bean);
        validator.setParameter(Validator.LOCALE_PARAM, Locale.getDefault());

        // Get results of the validation.
        try {
            validator.validate();
        } catch(Exception e) {
            // removed other assertion
        }
        // removed other assertion
                final Validator validator1 = validator;
        final String name = Validator.FIELD_PARAM;
        final Class<?> type = Field.class;
        Object value = validator1.getParameterValue(name);
                assertNotNull("Expected '" + type.getName() + "' but was null", value);
    }

    public void testAllValid_3_oe_2_oe() {

        // Create bean to run test on.
        NameBean bean = createNameBean();

        Validator validator = new Validator(resources, FORM_KEY);

        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, bean);
        validator.setParameter(Validator.LOCALE_PARAM, Locale.getDefault());

        // Get results of the validation.
        try {
            validator.validate();
        } catch(Exception e) {
            // removed other assertion
        }
        // removed other assertion
                final Validator validator1 = validator;
        final String name = Validator.FIELD_PARAM;
        final Class<?> type = Field.class;
        Object value = validator1.getParameterValue(name);
                // removed other assertion
                assertTrue("Expected '" + type.getName()+ "' but was '" + value.getClass().getName()+ "'",type.isInstance(value));
    }

    public void testAllValid_4_oe_1_oe() {

        // Create bean to run test on.
        NameBean bean = createNameBean();

        Validator validator = new Validator(resources, FORM_KEY);

        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, bean);
        validator.setParameter(Validator.LOCALE_PARAM, Locale.getDefault());

        // Get results of the validation.
        try {
            validator.validate();
        } catch(Exception e) {
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
                final Validator validator1 = validator;
        final String name = Validator.FORM_PARAM;
        final Class<?> type = Form.class;
        Object value = validator1.getParameterValue(name);
                assertNotNull("Expected '" + type.getName() + "' but was null", value);
    }

    public void testAllValid_4_oe_2_oe() {

        // Create bean to run test on.
        NameBean bean = createNameBean();

        Validator validator = new Validator(resources, FORM_KEY);

        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, bean);
        validator.setParameter(Validator.LOCALE_PARAM, Locale.getDefault());

        // Get results of the validation.
        try {
            validator.validate();
        } catch(Exception e) {
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
                final Validator validator1 = validator;
        final String name = Validator.FORM_PARAM;
        final Class<?> type = Form.class;
        Object value = validator1.getParameterValue(name);
                // removed other assertion
                assertTrue("Expected '" + type.getName()+ "' but was '" + value.getClass().getName()+ "'",type.isInstance(value));
    }

    public void testAllValid_5_oe_1_oe() {

        // Create bean to run test on.
        NameBean bean = createNameBean();

        Validator validator = new Validator(resources, FORM_KEY);

        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, bean);
        validator.setParameter(Validator.LOCALE_PARAM, Locale.getDefault());

        // Get results of the validation.
        try {
            validator.validate();
        } catch(Exception e) {
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final Validator validator1 = validator;
        final String name = Validator.LOCALE_PARAM;
        final Class<?> type = Locale.class;
        Object value = validator1.getParameterValue(name);
                assertNotNull("Expected '" + type.getName() + "' but was null", value);
    }

    public void testAllValid_5_oe_2_oe() {

        // Create bean to run test on.
        NameBean bean = createNameBean();

        Validator validator = new Validator(resources, FORM_KEY);

        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, bean);
        validator.setParameter(Validator.LOCALE_PARAM, Locale.getDefault());

        // Get results of the validation.
        try {
            validator.validate();
        } catch(Exception e) {
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final Validator validator1 = validator;
        final String name = Validator.LOCALE_PARAM;
        final Class<?> type = Locale.class;
        Object value = validator1.getParameterValue(name);
                // removed other assertion
                assertTrue("Expected '" + type.getName()+ "' but was '" + value.getClass().getName()+ "'",type.isInstance(value));
    }

    public void testAllValid_6_oe_1_oe() {

        // Create bean to run test on.
        NameBean bean = createNameBean();

        Validator validator = new Validator(resources, FORM_KEY);

        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, bean);
        validator.setParameter(Validator.LOCALE_PARAM, Locale.getDefault());

        // Get results of the validation.
        try {
            validator.validate();
        } catch(Exception e) {
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final Validator validator1 = validator;
        final String name = Validator.VALIDATOR_ACTION_PARAM;
        final Class<?> type = ValidatorAction.class;
        Object value = validator1.getParameterValue(name);
                assertNotNull("Expected '" + type.getName() + "' but was null", value);
    }

    public void testAllValid_6_oe_2_oe() {

        // Create bean to run test on.
        NameBean bean = createNameBean();

        Validator validator = new Validator(resources, FORM_KEY);

        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, bean);
        validator.setParameter(Validator.LOCALE_PARAM, Locale.getDefault());

        // Get results of the validation.
        try {
            validator.validate();
        } catch(Exception e) {
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final Validator validator1 = validator;
        final String name = Validator.VALIDATOR_ACTION_PARAM;
        final Class<?> type = ValidatorAction.class;
        Object value = validator1.getParameterValue(name);
                // removed other assertion
                assertTrue("Expected '" + type.getName()+ "' but was '" + value.getClass().getName()+ "'",type.isInstance(value));
    }

    public void testAllValid_7_oe_1_oe() {

        // Create bean to run test on.
        NameBean bean = createNameBean();

        Validator validator = new Validator(resources, FORM_KEY);

        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, bean);
        validator.setParameter(Validator.LOCALE_PARAM, Locale.getDefault());

        // Get results of the validation.
        try {
            validator.validate();
        } catch(Exception e) {
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final Validator validator1 = validator;
        final String name = Validator.VALIDATOR_PARAM;
        final Class<?> type = Validator.class;
        Object value = validator1.getParameterValue(name);
                assertNotNull("Expected '" + type.getName() + "' but was null", value);
    }

    public void testAllValid_7_oe_2_oe() {

        // Create bean to run test on.
        NameBean bean = createNameBean();

        Validator validator = new Validator(resources, FORM_KEY);

        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, bean);
        validator.setParameter(Validator.LOCALE_PARAM, Locale.getDefault());

        // Get results of the validation.
        try {
            validator.validate();
        } catch(Exception e) {
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final Validator validator1 = validator;
        final String name = Validator.VALIDATOR_PARAM;
        final Class<?> type = Validator.class;
        Object value = validator1.getParameterValue(name);
                // removed other assertion
                assertTrue("Expected '" + type.getName()+ "' but was '" + value.getClass().getName()+ "'",type.isInstance(value));
    }

    public void testAllValid_8_oe_1_oe() {

        // Create bean to run test on.
        NameBean bean = createNameBean();

        Validator validator = new Validator(resources, FORM_KEY);

        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, bean);
        validator.setParameter(Validator.LOCALE_PARAM, Locale.getDefault());

        // Get results of the validation.
        try {
            validator.validate();
        } catch(Exception e) {
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final Validator validator1 = validator;
        final String name = Validator.VALIDATOR_RESULTS_PARAM;
        final Class<?> type = ValidatorResults.class;
        Object value = validator1.getParameterValue(name);
                assertNotNull("Expected '" + type.getName() + "' but was null", value);
    }

    public void testAllValid_8_oe_2_oe() {

        // Create bean to run test on.
        NameBean bean = createNameBean();

        Validator validator = new Validator(resources, FORM_KEY);

        // add the name bean to the validator as a resource
        // for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, bean);
        validator.setParameter(Validator.LOCALE_PARAM, Locale.getDefault());

        // Get results of the validation.
        try {
            validator.validate();
        } catch(Exception e) {
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
                final Validator validator1 = validator;
        final String name = Validator.VALIDATOR_RESULTS_PARAM;
        final Class<?> type = ValidatorResults.class;
        Object value = validator1.getParameterValue(name);
                // removed other assertion
                assertTrue("Expected '" + type.getName()+ "' but was '" + value.getClass().getName()+ "'",type.isInstance(value));
    }

}
