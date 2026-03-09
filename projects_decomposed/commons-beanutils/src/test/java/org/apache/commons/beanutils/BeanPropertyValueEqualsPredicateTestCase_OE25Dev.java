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

package org.apache.commons.beanutils;

import junit.framework.TestCase;


/**
 * Test cases for <code>BeanPropertyValueEqualsPredicateTest</code>.
 *
 * @version $Id$
 */
public class BeanPropertyValueEqualsPredicateTestCase_OE25Dev extends TestCase {

    private static final Integer expectedIntegerValue = new Integer(123);
    private static final Float expectedFloatValue = new Float(123.123f);
    private static final Double expectedDoubleValue = new Double(567879.12344d);
    private static final Boolean expectedBooleanValue = Boolean.TRUE;
    private static final Byte expectedByteValue = new Byte("12");

    /**
     * Constructor for BeanPropertyValueEqualsPredicateTest.
     *
     * @param name Name of this test case.
     */
    public BeanPropertyValueEqualsPredicateTestCase_OE25Dev(final String name) {
        super(name);
    }

    /**
     * Test evaluate with simple String property.
     */
    public void testEvaluateWithSimpleStringProperty() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("stringProperty","foo");
        assertTrue(predicate.evaluate(new TestBean("foo")));
        assertTrue(!predicate.evaluate(new TestBean("bar")));
    }

    /**
     * Test evaluate with simple String property and null values.
     */
    public void testEvaluateWithSimpleStringPropertyWithNullValues() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("stringProperty",null);
        assertTrue(predicate.evaluate(new TestBean((String) null)));
        assertTrue(!predicate.evaluate(new TestBean("bar")));
    }

    /**
     * Test evaluate with nested property.
     */
    public void testEvaluateWithNestedProperty() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("anotherNested.stringProperty","match");
        final TestBean testBean = new TestBean();
        final TestBean nestedBean = new TestBean("match");
        testBean.setAnotherNested(nestedBean);
        assertTrue(predicate.evaluate(testBean));
        testBean.setAnotherNested(new TestBean("no-match"));
        assertTrue(!predicate.evaluate(testBean));
    }

    /**
     * Test evaluate with null in property path and ignore=false.
     */
    public void testEvaluateWithNullInPath() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("anotherNested.stringProperty","foo");
        try {
            // try to evaluate the predicate
            predicate.evaluate(new TestBean());
            fail("Should have throw IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            /* ignore this is what should happen */
        }
    }

    /**
     * Test evaluate with null in property path and ignore=true.
     */
    public void testEvaluateWithNullInPathAndIgnoreTrue() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("anotherNested.stringProperty","foo", true);
        try {
            assertTrue(!predicate.evaluate(new TestBean()));
        } catch (final IllegalArgumentException e) {
            fail("Should not have throw IllegalArgumentException");
        }
    }

    /**
     * Test evaluate with int property.
     */
    public void testEvaluateWithIntProperty() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("intProperty",expectedIntegerValue);
        assertTrue(predicate.evaluate(new TestBean(expectedIntegerValue.intValue())));
        assertTrue(!predicate.evaluate(new TestBean(expectedIntegerValue.intValue() - 1)));
    }

    /**
     * Test evaluate with float property.
     */
    public void testEvaluateWithFloatProperty() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("floatProperty",expectedFloatValue);
        assertTrue(predicate.evaluate(new TestBean(expectedFloatValue.floatValue())));
        assertTrue(!predicate.evaluate(new TestBean(expectedFloatValue.floatValue() - 1)));
    }

    /**
     * Test evaluate with double property.
     */
    public void testEvaluateWithDoubleProperty() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("doubleProperty",expectedDoubleValue);
        assertTrue(predicate.evaluate(new TestBean(expectedDoubleValue.doubleValue())));
        assertTrue(!predicate.evaluate(new TestBean(expectedDoubleValue.doubleValue() - 1)));
    }

    /**
     * Test evaluate with boolean property.
     */
    public void testEvaluateWithBooleanProperty() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("booleanProperty",expectedBooleanValue);
        assertTrue(predicate.evaluate(new TestBean(expectedBooleanValue.booleanValue())));
        assertTrue(!predicate.evaluate(new TestBean(!expectedBooleanValue.booleanValue())));
    }

    /**
     * Test evaluate with byte property.
     */
    public void testEvaluateWithByteProperty() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("byteProperty",expectedByteValue);
        final TestBean testBean = new TestBean();
        testBean.setByteProperty(expectedByteValue.byteValue());
        assertTrue(predicate.evaluate(testBean));
        testBean.setByteProperty((byte) (expectedByteValue.byteValue() - 1));
        assertTrue(!predicate.evaluate(testBean));
    }

    /**
     * Test evaluate with mapped property.
     */
    public void testEvaluateWithMappedProperty() {
        // try a key that is in the map
        BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("mappedProperty(test-key)","match");
        final TestBean testBean = new TestBean();
        testBean.setMappedProperty("test-key", "match");
        assertTrue(predicate.evaluate(testBean));
        testBean.setMappedProperty("test-key", "no-match");
        assertTrue(!predicate.evaluate(testBean));

        // try a key that isn't in the map
        predicate = new BeanPropertyValueEqualsPredicate("mappedProperty(invalid-key)", "match");
        assertTrue(!predicate.evaluate(testBean));
    }

    /**
     * Test evaluate with indexed property.
     */
    public void testEvaluateWithIndexedProperty() {
        // try a valid index
        BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("intIndexed[0]",expectedIntegerValue);
        final TestBean testBean = new TestBean();
        testBean.setIntIndexed(0, expectedIntegerValue.intValue());
        assertTrue(predicate.evaluate(testBean));
        testBean.setIntIndexed(0, expectedIntegerValue.intValue() - 1);
        assertTrue(!predicate.evaluate(testBean));

        // try an invalid index
        predicate = new BeanPropertyValueEqualsPredicate("intIndexed[999]", "exception-ahead");

        try {
            assertTrue(!predicate.evaluate(testBean));
        } catch (final ArrayIndexOutOfBoundsException e) {
            /* this is what should happen */
        }
    }

    /**
     * Test evaluate with primitive property and null value.
     */
    public void testEvaluateWithPrimitiveAndNull() {
        BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("intProperty",null);
        assertTrue(!predicate.evaluate(new TestBean(0)));

        predicate = new BeanPropertyValueEqualsPredicate("booleanProperty", null);
        assertTrue(!predicate.evaluate(new TestBean(true)));

        predicate = new BeanPropertyValueEqualsPredicate("floatProperty", null);
        assertTrue(!predicate.evaluate(new TestBean(expectedFloatValue.floatValue())));
    }

    /**
     * Test evaluate with nested mapped property.
     */
    public void testEvaluateWithNestedMappedProperty() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("anotherNested.mappedProperty(test-key)","match");
        final TestBean testBean = new TestBean();
        final TestBean nestedBean = new TestBean();
        nestedBean.setMappedProperty("test-key", "match");
        testBean.setAnotherNested(nestedBean);
        assertTrue(predicate.evaluate(testBean));
        nestedBean.setMappedProperty("test-key", "no-match");
        assertTrue(!predicate.evaluate(testBean));
    }

    /**
     * Test evaluate with write only property.
     */
    public void testEvaluateWithWriteOnlyProperty() {
        try {
            new BeanPropertyValueEqualsPredicate("writeOnlyProperty", null).evaluate(new TestBean());
        } catch (final IllegalArgumentException e) {
            /* This is what should happen */
        }
    }

    /**
     * Test evaluate with read only property.
     */
    public void testEvaluateWithReadOnlyProperty() {
        final TestBean testBean = new TestBean();
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("readOnlyProperty",testBean.getReadOnlyProperty());
        assertTrue(predicate.evaluate(new TestBean()));
    }

    /**
     * Test evaluate with an invalid property name.
     */
    public void testEvaluateWithInvalidPropertyName() {
        try {
            new BeanPropertyValueEqualsPredicate("bogusProperty", null).evaluate(new TestBean());
        } catch (final IllegalArgumentException e) {
            /* This is what should happen */
        }
    }

    public void testEvaluateWithSimpleStringProperty_1_oe() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("stringProperty","foo");
        assertTrue(predicate.evaluate(new TestBean("foo")));
    }

    public void testEvaluateWithSimpleStringProperty_2_oe() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("stringProperty","foo");
        assertTrue(!predicate.evaluate(new TestBean("bar")));
    }

    public void testEvaluateWithSimpleStringPropertyWithNullValues_1_oe() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("stringProperty",null);
        assertTrue(predicate.evaluate(new TestBean((String) null)));
    }

    public void testEvaluateWithSimpleStringPropertyWithNullValues_2_oe() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("stringProperty",null);
        assertTrue(!predicate.evaluate(new TestBean("bar")));
    }

    public void testEvaluateWithNestedProperty_1_oe() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("anotherNested.stringProperty","match");
        final TestBean testBean = new TestBean();
        final TestBean nestedBean = new TestBean("match");
        testBean.setAnotherNested(nestedBean);
        assertTrue(predicate.evaluate(testBean));
    }

    public void testEvaluateWithNestedProperty_2_oe() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("anotherNested.stringProperty","match");
        final TestBean testBean = new TestBean();
        final TestBean nestedBean = new TestBean("match");
        testBean.setAnotherNested(nestedBean);
        testBean.setAnotherNested(new TestBean("no-match"));
        assertTrue(!predicate.evaluate(testBean));
    }

    public void testEvaluateWithNullInPathAndIgnoreTrue_2_oe() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("anotherNested.stringProperty","foo", true);
        try {
        } catch (final IllegalArgumentException e) {
            fail("Should not have throw IllegalArgumentException");
    }
    }

    public void testEvaluateWithIntProperty_1_oe() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("intProperty",expectedIntegerValue);
        assertTrue(predicate.evaluate(new TestBean(expectedIntegerValue.intValue())));
    }

    public void testEvaluateWithIntProperty_2_oe() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("intProperty",expectedIntegerValue);
        assertTrue(!predicate.evaluate(new TestBean(expectedIntegerValue.intValue() - 1)));
    }

    public void testEvaluateWithFloatProperty_1_oe() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("floatProperty",expectedFloatValue);
        assertTrue(predicate.evaluate(new TestBean(expectedFloatValue.floatValue())));
    }

    public void testEvaluateWithFloatProperty_2_oe() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("floatProperty",expectedFloatValue);
        assertTrue(!predicate.evaluate(new TestBean(expectedFloatValue.floatValue() - 1)));
    }

    public void testEvaluateWithDoubleProperty_1_oe() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("doubleProperty",expectedDoubleValue);
        assertTrue(predicate.evaluate(new TestBean(expectedDoubleValue.doubleValue())));
    }

    public void testEvaluateWithDoubleProperty_2_oe() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("doubleProperty",expectedDoubleValue);
        assertTrue(!predicate.evaluate(new TestBean(expectedDoubleValue.doubleValue() - 1)));
    }

    public void testEvaluateWithBooleanProperty_1_oe() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("booleanProperty",expectedBooleanValue);
        assertTrue(predicate.evaluate(new TestBean(expectedBooleanValue.booleanValue())));
    }

    public void testEvaluateWithBooleanProperty_2_oe() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("booleanProperty",expectedBooleanValue);
        assertTrue(!predicate.evaluate(new TestBean(!expectedBooleanValue.booleanValue())));
    }

    public void testEvaluateWithByteProperty_1_oe() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("byteProperty",expectedByteValue);
        final TestBean testBean = new TestBean();
        testBean.setByteProperty(expectedByteValue.byteValue());
        assertTrue(predicate.evaluate(testBean));
    }

    public void testEvaluateWithByteProperty_2_oe() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("byteProperty",expectedByteValue);
        final TestBean testBean = new TestBean();
        testBean.setByteProperty(expectedByteValue.byteValue());
        testBean.setByteProperty((byte) (expectedByteValue.byteValue() - 1));
        assertTrue(!predicate.evaluate(testBean));
    }

    public void testEvaluateWithMappedProperty_1_oe() {
        BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("mappedProperty(test-key)","match");
        final TestBean testBean = new TestBean();
        testBean.setMappedProperty("test-key", "match");
        assertTrue(predicate.evaluate(testBean));
    }

    public void testEvaluateWithMappedProperty_2_oe() {
        BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("mappedProperty(test-key)","match");
        final TestBean testBean = new TestBean();
        testBean.setMappedProperty("test-key", "match");
        testBean.setMappedProperty("test-key", "no-match");
        assertTrue(!predicate.evaluate(testBean));
    }

    public void testEvaluateWithMappedProperty_3_oe() {
        BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("mappedProperty(test-key)","match");
        final TestBean testBean = new TestBean();
        testBean.setMappedProperty("test-key", "match");
        testBean.setMappedProperty("test-key", "no-match");

        predicate = new BeanPropertyValueEqualsPredicate("mappedProperty(invalid-key)", "match");
        assertTrue(!predicate.evaluate(testBean));
    }

    public void testEvaluateWithIndexedProperty_1_oe() {
        BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("intIndexed[0]",expectedIntegerValue);
        final TestBean testBean = new TestBean();
        testBean.setIntIndexed(0, expectedIntegerValue.intValue());
        assertTrue(predicate.evaluate(testBean));
    }

    public void testEvaluateWithIndexedProperty_2_oe() {
        BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("intIndexed[0]",expectedIntegerValue);
        final TestBean testBean = new TestBean();
        testBean.setIntIndexed(0, expectedIntegerValue.intValue());
        testBean.setIntIndexed(0, expectedIntegerValue.intValue() - 1);
        assertTrue(!predicate.evaluate(testBean));
    }

    public void testEvaluateWithPrimitiveAndNull_1_oe() {
        BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("intProperty",null);
        assertTrue(!predicate.evaluate(new TestBean(0)));
    }

    public void testEvaluateWithPrimitiveAndNull_2_oe() {
        BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("intProperty",null);

        predicate = new BeanPropertyValueEqualsPredicate("booleanProperty", null);
        assertTrue(!predicate.evaluate(new TestBean(true)));
    }

    public void testEvaluateWithPrimitiveAndNull_3_oe() {
        BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("intProperty",null);

        predicate = new BeanPropertyValueEqualsPredicate("booleanProperty", null);

        predicate = new BeanPropertyValueEqualsPredicate("floatProperty", null);
        assertTrue(!predicate.evaluate(new TestBean(expectedFloatValue.floatValue())));
    }

    public void testEvaluateWithNestedMappedProperty_1_oe() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("anotherNested.mappedProperty(test-key)","match");
        final TestBean testBean = new TestBean();
        final TestBean nestedBean = new TestBean();
        nestedBean.setMappedProperty("test-key", "match");
        testBean.setAnotherNested(nestedBean);
        assertTrue(predicate.evaluate(testBean));
    }

    public void testEvaluateWithNestedMappedProperty_2_oe() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("anotherNested.mappedProperty(test-key)","match");
        final TestBean testBean = new TestBean();
        final TestBean nestedBean = new TestBean();
        nestedBean.setMappedProperty("test-key", "match");
        testBean.setAnotherNested(nestedBean);
        nestedBean.setMappedProperty("test-key", "no-match");
        assertTrue(!predicate.evaluate(testBean));
    }

    public void testEvaluateWithReadOnlyProperty_1_oe() {
        final TestBean testBean = new TestBean();
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("readOnlyProperty",testBean.getReadOnlyProperty());
        assertTrue(predicate.evaluate(new TestBean()));
    }

public void testEvaluateWithNullInPath_oe_101_oe() {
        final BeanPropertyValueEqualsPredicate predicate =
            new BeanPropertyValueEqualsPredicate("anotherNested.stringProperty","foo");
        try {
            predicate.evaluate(new TestBean());
            fail("Should have throw IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            /* ignore this is what should happen */
        }
}

}