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
package org.apache.commons.beanutils.bugs;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.ArrayConverter;
import org.apache.commons.beanutils.converters.StringConverter;

/**
 * @version $Id$
 * @see <a href="https://issues.apache.org/jira/browse/BEANUTILS-359">https://issues.apache.org/jira/browse/BEANUTILS-359</a>
 */
public class Jira359TestCase_OE25Dev extends TestCase {

    /**
     * Create a test case with the specified name.
     *
     * @param name The name of the test
     */
    public Jira359TestCase_OE25Dev(final String name) {
        super(name);
    }

    /**
     * Run the Test.
     *
     * @param args Arguments
     */
    public static void main(final String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    /**
     * Create a test suite for this test.
     *
     * @return a test suite
     */
    public static Test suite() {
        return (new TestSuite(Jira359TestCase_OE25Dev.class));
    }

    /**
     * Set up.
     *
     * @throws java.lang.Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Tear Down.
     *
     * @throws java.lang.Exception
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test {@link BeanUtils} setProperty() String to array with colon value
     */

    /**
     * Test {@link BeanUtils} setProperty() String to array with colon value
     */

    /**
     * Test {@link BeanUtils} setProperty() String to array without colon value
     */

    /**
     * Test {@link BeanUtils} setProperty() String to array without colon value and no comma
     */

    /**
     * Show array contents.
     */
    private void showArray(final String text, final String[] array) {
        if (array == null) {
            System.out.println(text + " array is null");
        } else {
            System.out.println(text + " array length=" + array.length);
            for (int i = 0; i < array.length; i++) {
                System.out.println(text + " array[" + i + "]=" + array[i]);
            }
        }
    }

    public static class SimplePojoData {
        private String[] jcrMixinTypes = new String[1];
        public SimplePojoData() {
        }
        public String[] getJcrMixinTypes() {
            return this.jcrMixinTypes;
        }
        public void setJcrMixinTypes(final String[] mixinTypes) {
            this.jcrMixinTypes = mixinTypes;
        }
    }

    public void testBeanUtilsSetProperty_CustomConvertStringToArray_WithColonValue_1_oe() throws Exception{
        final ArrayConverter converter = new ArrayConverter(String[].class, new StringConverter());
        converter.setAllowedChars(new char[] {'.', '-', ':'});

        final BeanUtilsBean utils = new BeanUtilsBean();
        utils.getConvertUtils().register(converter, String[].class);

        final SimplePojoData simplePojo = new SimplePojoData();
        utils.setProperty(simplePojo, "jcrMixinTypes", "mix:rereferencible,mix:simple");
        showArray("Custom WithColonValue", simplePojo.getJcrMixinTypes());
        assertEquals("array size", 2, simplePojo.getJcrMixinTypes().length);
    }

    public void testBeanUtilsSetProperty_CustomConvertStringToArray_WithColonValue_2_oe() throws Exception{
        final ArrayConverter converter = new ArrayConverter(String[].class, new StringConverter());
        converter.setAllowedChars(new char[] {'.', '-', ':'});

        final BeanUtilsBean utils = new BeanUtilsBean();
        utils.getConvertUtils().register(converter, String[].class);

        final SimplePojoData simplePojo = new SimplePojoData();
        utils.setProperty(simplePojo, "jcrMixinTypes", "mix:rereferencible,mix:simple");
        showArray("Custom WithColonValue", simplePojo.getJcrMixinTypes());
        assertEquals("mix:rereferencible", simplePojo.getJcrMixinTypes()[0]);
    }

    public void testBeanUtilsSetProperty_CustomConvertStringToArray_WithColonValue_3_oe() throws Exception{
        final ArrayConverter converter = new ArrayConverter(String[].class, new StringConverter());
        converter.setAllowedChars(new char[] {'.', '-', ':'});

        final BeanUtilsBean utils = new BeanUtilsBean();
        utils.getConvertUtils().register(converter, String[].class);

        final SimplePojoData simplePojo = new SimplePojoData();
        utils.setProperty(simplePojo, "jcrMixinTypes", "mix:rereferencible,mix:simple");
        showArray("Custom WithColonValue", simplePojo.getJcrMixinTypes());
        assertEquals("mix:simple", simplePojo.getJcrMixinTypes()[1]);
    }

    public void testBeanUtilsSetProperty_DefaultConvertStringToArray_WithColonValue_1_oe() throws Exception{
        final SimplePojoData simplePojo = new SimplePojoData();
        BeanUtils.setProperty(simplePojo, "jcrMixinTypes", "mix:rereferencible,mix:simple");
        showArray("Default WithColonValue", simplePojo.getJcrMixinTypes());
        assertEquals("array size", 4, simplePojo.getJcrMixinTypes().length);
    }

    public void testBeanUtilsSetProperty_DefaultConvertStringToArray_WithColonValue_2_oe() throws Exception{
        final SimplePojoData simplePojo = new SimplePojoData();
        BeanUtils.setProperty(simplePojo, "jcrMixinTypes", "mix:rereferencible,mix:simple");
        showArray("Default WithColonValue", simplePojo.getJcrMixinTypes());
        assertEquals("mix", simplePojo.getJcrMixinTypes()[0]);
    }

    public void testBeanUtilsSetProperty_DefaultConvertStringToArray_WithColonValue_3_oe() throws Exception{
        final SimplePojoData simplePojo = new SimplePojoData();
        BeanUtils.setProperty(simplePojo, "jcrMixinTypes", "mix:rereferencible,mix:simple");
        showArray("Default WithColonValue", simplePojo.getJcrMixinTypes());
        assertEquals("rereferencible", simplePojo.getJcrMixinTypes()[1]);
    }

    public void testBeanUtilsSetProperty_DefaultConvertStringToArray_WithColonValue_4_oe() throws Exception{
        final SimplePojoData simplePojo = new SimplePojoData();
        BeanUtils.setProperty(simplePojo, "jcrMixinTypes", "mix:rereferencible,mix:simple");
        showArray("Default WithColonValue", simplePojo.getJcrMixinTypes());
        assertEquals("mix", simplePojo.getJcrMixinTypes()[2]);
    }

    public void testBeanUtilsSetProperty_DefaultConvertStringToArray_WithColonValue_5_oe() throws Exception{
        final SimplePojoData simplePojo = new SimplePojoData();
        BeanUtils.setProperty(simplePojo, "jcrMixinTypes", "mix:rereferencible,mix:simple");
        showArray("Default WithColonValue", simplePojo.getJcrMixinTypes());
        assertEquals("simple", simplePojo.getJcrMixinTypes()[3]);
    }

    public void testBeanUtilsSetProperty_DefaultConvertStringToArray_WithoutColonValue_1_oe() throws Exception{
        final SimplePojoData simplePojo = new SimplePojoData();
        BeanUtils.setProperty(simplePojo, "jcrMixinTypes", "mixrereferencible,mixsimple");
        showArray("Default WithoutColonValue", simplePojo.getJcrMixinTypes());
        assertEquals("array size", 2, simplePojo.getJcrMixinTypes().length);
    }

    public void testBeanUtilsSetProperty_DefaultConvertStringToArray_WithoutColonValue_2_oe() throws Exception{
        final SimplePojoData simplePojo = new SimplePojoData();
        BeanUtils.setProperty(simplePojo, "jcrMixinTypes", "mixrereferencible,mixsimple");
        showArray("Default WithoutColonValue", simplePojo.getJcrMixinTypes());
        assertEquals("mixrereferencible", simplePojo.getJcrMixinTypes()[0]);
    }

    public void testBeanUtilsSetProperty_DefaultConvertStringToArray_WithoutColonValue_3_oe() throws Exception{
        final SimplePojoData simplePojo = new SimplePojoData();
        BeanUtils.setProperty(simplePojo, "jcrMixinTypes", "mixrereferencible,mixsimple");
        showArray("Default WithoutColonValue", simplePojo.getJcrMixinTypes());
        assertEquals("mixsimple", simplePojo.getJcrMixinTypes()[1]);
    }

    public void testBeanUtilsSetProperty_DefaultConvertStringToArray_WithoutColonValueAndNocoma_1_oe() throws Exception{
        final SimplePojoData simplePojo = new SimplePojoData();
        BeanUtils.setProperty(simplePojo, "jcrMixinTypes", "mixrereferencible");
        showArray("Default WithoutColonAndNocoma", simplePojo.getJcrMixinTypes());
        assertEquals("array size", 1, simplePojo.getJcrMixinTypes().length);
    }

    public void testBeanUtilsSetProperty_DefaultConvertStringToArray_WithoutColonValueAndNocoma_2_oe() throws Exception{
        final SimplePojoData simplePojo = new SimplePojoData();
        BeanUtils.setProperty(simplePojo, "jcrMixinTypes", "mixrereferencible");
        showArray("Default WithoutColonAndNocoma", simplePojo.getJcrMixinTypes());
        assertEquals("mixrereferencible", simplePojo.getJcrMixinTypes()[0]);
    }

}
