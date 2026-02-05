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
 * Test that the new Var attributes and the
 * digester rule changes work.
 *
 * @version $Revision$
 */
public class VarTest_OE25Dev extends AbstractCommonTest {

   /**
    * The key used to retrieve the set of validation
    * rules from the xml file.
    */
   protected static String FORM_KEY = "testForm";

   /**
    * The key used to retrieve the validator action.
    */
   protected static String ACTION = "byte";



   public VarTest_OE25Dev(String name) {
       super(name);
   }

   /**
    * Load <code>ValidatorResources</code> from
    * validator-multipletest.xml.
    */
   @Override
protected void setUp() throws IOException, SAXException {
      // Load resources
      loadResources("VarTest_OE25Dev-config.xml");
   }

   @Override
protected void tearDown() {
   }

   /**
    * With nothing provided, we should fail both because both are required.
    */

public void testVars_1_oe() {

       Form form = resources.getForm(Locale.getDefault(), FORM_KEY);

       // Get field 1
       Field field1 = form.getField("field-1");
       assertNotNull("field-1 is null.", field1);
   }

public void testVars_2_oe() {

       Form form = resources.getForm(Locale.getDefault(), FORM_KEY);

       // Get field 1
       Field field1 = form.getField("field-1");
       // removed other assertion
       assertEquals("field-1 property is wrong", "field-1", field1.getProperty());
   }

public void testVars_3_oe() {

       Form form = resources.getForm(Locale.getDefault(), FORM_KEY);

       // Get field 1
       Field field1 = form.getField("field-1");
       // removed other assertion
       // removed other assertion

       // Get var-1-1
       Var var11 = field1.getVar("var-1-1");
       assertNotNull("var-1-1 is null.", var11);
   }

public void testVars_4_oe() {

       Form form = resources.getForm(Locale.getDefault(), FORM_KEY);

       // Get field 1
       Field field1 = form.getField("field-1");
       // removed other assertion
       // removed other assertion

       // Get var-1-1
       Var var11 = field1.getVar("var-1-1");
       // removed other assertion
       assertEquals("var-1-1 name is wrong", "var-1-1", var11.getName());
   }

public void testVars_5_oe() {

       Form form = resources.getForm(Locale.getDefault(), FORM_KEY);

       // Get field 1
       Field field1 = form.getField("field-1");
       // removed other assertion
       // removed other assertion

       // Get var-1-1
       Var var11 = field1.getVar("var-1-1");
       // removed other assertion
       // removed other assertion
       assertEquals("var-1-1 value is wrong", "value-1-1", var11.getValue());
   }

public void testVars_6_oe() {

       Form form = resources.getForm(Locale.getDefault(), FORM_KEY);

       // Get field 1
       Field field1 = form.getField("field-1");
       // removed other assertion
       // removed other assertion

       // Get var-1-1
       Var var11 = field1.getVar("var-1-1");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       assertEquals("var-1-1 jstype is wrong", "jstype-1-1", var11.getJsType());
   }

public void testVars_7_oe() {

       Form form = resources.getForm(Locale.getDefault(), FORM_KEY);

       // Get field 1
       Field field1 = form.getField("field-1");
       // removed other assertion
       // removed other assertion

       // Get var-1-1
       Var var11 = field1.getVar("var-1-1");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       assertFalse("var-1-1 resource is true", var11.isResource());
   }

public void testVars_8_oe() {

       Form form = resources.getForm(Locale.getDefault(), FORM_KEY);

       // Get field 1
       Field field1 = form.getField("field-1");
       // removed other assertion
       // removed other assertion

       // Get var-1-1
       Var var11 = field1.getVar("var-1-1");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       assertNull("var-1-1 bundle is not null.", var11.getBundle());
   }

public void testVars_9_oe() {

       Form form = resources.getForm(Locale.getDefault(), FORM_KEY);

       // Get field 1
       Field field1 = form.getField("field-1");
       // removed other assertion
       // removed other assertion

       // Get var-1-1
       Var var11 = field1.getVar("var-1-1");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion

       // Get field 2
       Field field2 = form.getField("field-2");
       assertNotNull("field-2 is null.", field2);
   }

public void testVars_10_oe() {

       Form form = resources.getForm(Locale.getDefault(), FORM_KEY);

       // Get field 1
       Field field1 = form.getField("field-1");
       // removed other assertion
       // removed other assertion

       // Get var-1-1
       Var var11 = field1.getVar("var-1-1");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion

       // Get field 2
       Field field2 = form.getField("field-2");
       // removed other assertion
       assertEquals("field-2 property is wrong", "field-2", field2.getProperty());
   }

public void testVars_11_oe() {

       Form form = resources.getForm(Locale.getDefault(), FORM_KEY);

       // Get field 1
       Field field1 = form.getField("field-1");
       // removed other assertion
       // removed other assertion

       // Get var-1-1
       Var var11 = field1.getVar("var-1-1");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion

       // Get field 2
       Field field2 = form.getField("field-2");
       // removed other assertion
       // removed other assertion

       // Get var-2-1
       Var var21 = field2.getVar("var-2-1");
       assertNotNull("var-2-1 is null.", var21);
   }

public void testVars_12_oe() {

       Form form = resources.getForm(Locale.getDefault(), FORM_KEY);

       // Get field 1
       Field field1 = form.getField("field-1");
       // removed other assertion
       // removed other assertion

       // Get var-1-1
       Var var11 = field1.getVar("var-1-1");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion

       // Get field 2
       Field field2 = form.getField("field-2");
       // removed other assertion
       // removed other assertion

       // Get var-2-1
       Var var21 = field2.getVar("var-2-1");
       // removed other assertion
       assertEquals("var-2-1 name is wrong", "var-2-1", var21.getName());
   }

public void testVars_13_oe() {

       Form form = resources.getForm(Locale.getDefault(), FORM_KEY);

       // Get field 1
       Field field1 = form.getField("field-1");
       // removed other assertion
       // removed other assertion

       // Get var-1-1
       Var var11 = field1.getVar("var-1-1");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion

       // Get field 2
       Field field2 = form.getField("field-2");
       // removed other assertion
       // removed other assertion

       // Get var-2-1
       Var var21 = field2.getVar("var-2-1");
       // removed other assertion
       // removed other assertion
       assertEquals("var-2-1 value is wrong", "value-2-1", var21.getValue());
   }

public void testVars_14_oe() {

       Form form = resources.getForm(Locale.getDefault(), FORM_KEY);

       // Get field 1
       Field field1 = form.getField("field-1");
       // removed other assertion
       // removed other assertion

       // Get var-1-1
       Var var11 = field1.getVar("var-1-1");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion

       // Get field 2
       Field field2 = form.getField("field-2");
       // removed other assertion
       // removed other assertion

       // Get var-2-1
       Var var21 = field2.getVar("var-2-1");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       assertEquals("var-2-1 jstype is wrong", "jstype-2-1", var21.getJsType());
   }

public void testVars_15_oe() {

       Form form = resources.getForm(Locale.getDefault(), FORM_KEY);

       // Get field 1
       Field field1 = form.getField("field-1");
       // removed other assertion
       // removed other assertion

       // Get var-1-1
       Var var11 = field1.getVar("var-1-1");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion

       // Get field 2
       Field field2 = form.getField("field-2");
       // removed other assertion
       // removed other assertion

       // Get var-2-1
       Var var21 = field2.getVar("var-2-1");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       assertTrue("var-2-1 resource is false", var21.isResource());
   }

public void testVars_16_oe() {

       Form form = resources.getForm(Locale.getDefault(), FORM_KEY);

       // Get field 1
       Field field1 = form.getField("field-1");
       // removed other assertion
       // removed other assertion

       // Get var-1-1
       Var var11 = field1.getVar("var-1-1");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion

       // Get field 2
       Field field2 = form.getField("field-2");
       // removed other assertion
       // removed other assertion

       // Get var-2-1
       Var var21 = field2.getVar("var-2-1");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       assertEquals("var-2-1 bundle is wrong", "bundle-2-1", var21.getBundle());
   }

public void testVars_17_oe() {

       Form form = resources.getForm(Locale.getDefault(), FORM_KEY);

       // Get field 1
       Field field1 = form.getField("field-1");
       // removed other assertion
       // removed other assertion

       // Get var-1-1
       Var var11 = field1.getVar("var-1-1");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion

       // Get field 2
       Field field2 = form.getField("field-2");
       // removed other assertion
       // removed other assertion

       // Get var-2-1
       Var var21 = field2.getVar("var-2-1");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion

       // Get var-2-2
       Var var22 = field2.getVar("var-2-2");
       assertNotNull("var-2-2 is null.", var22);
   }

public void testVars_18_oe() {

       Form form = resources.getForm(Locale.getDefault(), FORM_KEY);

       // Get field 1
       Field field1 = form.getField("field-1");
       // removed other assertion
       // removed other assertion

       // Get var-1-1
       Var var11 = field1.getVar("var-1-1");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion

       // Get field 2
       Field field2 = form.getField("field-2");
       // removed other assertion
       // removed other assertion

       // Get var-2-1
       Var var21 = field2.getVar("var-2-1");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion

       // Get var-2-2
       Var var22 = field2.getVar("var-2-2");
       // removed other assertion
       assertEquals("var-2-2 name is wrong", "var-2-2", var22.getName());
   }

public void testVars_19_oe() {

       Form form = resources.getForm(Locale.getDefault(), FORM_KEY);

       // Get field 1
       Field field1 = form.getField("field-1");
       // removed other assertion
       // removed other assertion

       // Get var-1-1
       Var var11 = field1.getVar("var-1-1");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion

       // Get field 2
       Field field2 = form.getField("field-2");
       // removed other assertion
       // removed other assertion

       // Get var-2-1
       Var var21 = field2.getVar("var-2-1");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion

       // Get var-2-2
       Var var22 = field2.getVar("var-2-2");
       // removed other assertion
       // removed other assertion
       assertEquals("var-2-2 value is wrong", "value-2-2", var22.getValue());
   }

public void testVars_20_oe() {

       Form form = resources.getForm(Locale.getDefault(), FORM_KEY);

       // Get field 1
       Field field1 = form.getField("field-1");
       // removed other assertion
       // removed other assertion

       // Get var-1-1
       Var var11 = field1.getVar("var-1-1");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion

       // Get field 2
       Field field2 = form.getField("field-2");
       // removed other assertion
       // removed other assertion

       // Get var-2-1
       Var var21 = field2.getVar("var-2-1");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion

       // Get var-2-2
       Var var22 = field2.getVar("var-2-2");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       assertNull("var-2-2 jstype is not null", var22.getJsType());
   }

public void testVars_21_oe() {

       Form form = resources.getForm(Locale.getDefault(), FORM_KEY);

       // Get field 1
       Field field1 = form.getField("field-1");
       // removed other assertion
       // removed other assertion

       // Get var-1-1
       Var var11 = field1.getVar("var-1-1");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion

       // Get field 2
       Field field2 = form.getField("field-2");
       // removed other assertion
       // removed other assertion

       // Get var-2-1
       Var var21 = field2.getVar("var-2-1");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion

       // Get var-2-2
       Var var22 = field2.getVar("var-2-2");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       assertFalse("var-2-2 resource is true", var22.isResource());
   }

public void testVars_22_oe() {

       Form form = resources.getForm(Locale.getDefault(), FORM_KEY);

       // Get field 1
       Field field1 = form.getField("field-1");
       // removed other assertion
       // removed other assertion

       // Get var-1-1
       Var var11 = field1.getVar("var-1-1");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion

       // Get field 2
       Field field2 = form.getField("field-2");
       // removed other assertion
       // removed other assertion

       // Get var-2-1
       Var var21 = field2.getVar("var-2-1");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion

       // Get var-2-2
       Var var22 = field2.getVar("var-2-2");
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       // removed other assertion
       assertEquals("var-2-2 bundle is wrong", "bundle-2-2", var22.getBundle());
   }

}                                                         