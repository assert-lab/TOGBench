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
 * Test cases for <code>BeanToPropertyValueTransformer</code>.
 *
 * @version $Id$
 */
public class BeanToPropertyValueTransformerTestCase_OE25Dev extends TestCase {

    private static final Integer expectedIntegerValue = new Integer(123);
    private static final Long expectedLongValue = new Long(123);
    private static final Float expectedFloatValue = new Float(123.123f);
    private static final Double expectedDoubleValue = new Double(567879.12344d);
    private static final Boolean expectedBooleanValue = Boolean.TRUE;
    private static final Byte expectedByteValue = new Byte("12");

    /**
     * Constructor for BeanToPropertyValueTransformerTestCase_OE25Dev.
     *
     * @param name Name of this test case.
     */
    public BeanToPropertyValueTransformerTestCase_OE25Dev(final String name) {
        super(name);
    }

    /**
     * Test transform with simple String property.
     */

    /**
     * Test transform with simple String property and null value.
     *
     */

    /**
     * Test transform with simple int property.
     */

    /**
     * Test transform with simple long property.
     */

    /**
     * Test transform with simple float property.
     */

    /**
     * Test transform with simple double property.
     */

    /**
     * Test transform with simple byte property.
     */

    /**
     * Test transform with simple boolean property.
     */

    /**
     * Test transform with write only property.
     */
    public void testTransformWithWriteOnlyProperty() {
        try {
            new BeanToPropertyValueTransformer("writeOnlyProperty").transform(new TestBean());
        } catch (final IllegalArgumentException e) {
            /* This is what should happen */
        }
    }

    /**
     * Test transform with read only property.
     */

    /**
     * Test transform with invalid property.
     */
    public void testTransformWithInvalidProperty() {
        try {
            new BeanToPropertyValueTransformer("bogusProperty").transform(new TestBean());
        } catch (final IllegalArgumentException e) {
            /* This is what should happen */
        }
    }

    /**
     * Test transform with nested property.
     */

    /**
     * Test transform with mapped property.
     */

    /**
     * Test transform with indexed property.
     */

    /**
     * Test transform with nested indexed property.
     */

    /**
     * Test transform with null in property path.
     */
    public void testTransformWithNullInPath() {
        final BeanToPropertyValueTransformer transformer =
            new BeanToPropertyValueTransformer("anotherNested.stringProperty");

        try {
            transformer.transform(new TestBean());
            fail("Should have throw IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            /* ignore this is what should happen */
        }
    }

    /**
     * Test transform with null in property path and ignore = true.
     */

public void testTransformWithSimpleStringProperty_1_oe() {
        final BeanToPropertyValueTransformer transformer =
            new BeanToPropertyValueTransformer("stringProperty");
        final TestBean testBean = new TestBean("foo");
        assertEquals("foo", transformer.transform(testBean));
    }

public void testTransformWithSimpleStringPropertyAndNullValue_1_oe() {
        final BeanToPropertyValueTransformer transformer =
            new BeanToPropertyValueTransformer("stringProperty");
        final TestBean testBean = new TestBean((String) null);
        assertNull(transformer.transform(testBean));
    }

public void testTransformWithSimpleIntProperty_1_oe() {
        final BeanToPropertyValueTransformer transformer =
            new BeanToPropertyValueTransformer("intProperty");
        final TestBean testBean = new TestBean(expectedIntegerValue.intValue());
        assertEquals(expectedIntegerValue, transformer.transform(testBean));
    }

public void testTransformWithSimpleLongProperty_1_oe() {
        final BeanToPropertyValueTransformer transformer =
            new BeanToPropertyValueTransformer("longProperty");
        final TestBean testBean = new TestBean();
        testBean.setLongProperty(expectedLongValue.longValue());
        assertEquals(expectedLongValue, transformer.transform(testBean));
    }

public void testTransformWithSimpleFloatProperty_1_oe() {
        final BeanToPropertyValueTransformer transformer =
            new BeanToPropertyValueTransformer("floatProperty");
        final TestBean testBean = new TestBean(expectedFloatValue.floatValue());
        assertEquals(expectedFloatValue, transformer.transform(testBean));
    }

public void testTransformWithSimpleDoubleProperty_1_oe() {
        final BeanToPropertyValueTransformer transformer =
            new BeanToPropertyValueTransformer("doubleProperty");
        final TestBean testBean = new TestBean(expectedDoubleValue.doubleValue());
        assertEquals(expectedDoubleValue, transformer.transform(testBean));
    }

public void testTransformWithSimpleByteProperty_1_oe() {
        final BeanToPropertyValueTransformer transformer =
            new BeanToPropertyValueTransformer("byteProperty");
        final TestBean testBean = new TestBean();
        testBean.setByteProperty(expectedByteValue.byteValue());
        assertEquals(expectedByteValue, transformer.transform(testBean));
    }

public void testTransformWithSimpleBooleanProperty_1_oe() {
        final BeanToPropertyValueTransformer transformer =
            new BeanToPropertyValueTransformer("booleanProperty");
        final TestBean testBean = new TestBean(expectedBooleanValue.booleanValue());
        assertEquals(expectedBooleanValue, transformer.transform(testBean));
    }

public void testTransformWithReadOnlyProperty_1_oe() {
        final BeanToPropertyValueTransformer transformer =
            new BeanToPropertyValueTransformer("readOnlyProperty");
        final TestBean testBean = new TestBean();
        assertEquals(testBean.getReadOnlyProperty(), transformer.transform(testBean));
    }

public void testTransformWithNestedProperty_1_oe() {
        final BeanToPropertyValueTransformer transformer =
            new BeanToPropertyValueTransformer("anotherNested.stringProperty");
        final TestBean testBean = new TestBean();
        final TestBean nestedBean = new TestBean("foo");
        testBean.setAnotherNested(nestedBean);
        assertEquals("foo", transformer.transform(testBean));
    }

public void testTransformWithMappedProperty_1_oe() {
        BeanToPropertyValueTransformer transformer =
            new BeanToPropertyValueTransformer("mappedProperty(test-key)");
        final TestBean testBean = new TestBean();

        // try a valid key
        testBean.setMappedProperty("test-key", "test-value");
        assertEquals("test-value", transformer.transform(testBean));
    }

public void testTransformWithMappedProperty_2_oe() {
        BeanToPropertyValueTransformer transformer =
            new BeanToPropertyValueTransformer("mappedProperty(test-key)");
        final TestBean testBean = new TestBean();

        // try a valid key
        testBean.setMappedProperty("test-key", "test-value");
        // removed other assertion

        // now try an invalid key
        transformer = new BeanToPropertyValueTransformer("mappedProperty(bogus-key)");
        assertEquals(null, transformer.transform(testBean));
    }

public void testTransformWithIndexedProperty_1_oe() {
        BeanToPropertyValueTransformer transformer =
            new BeanToPropertyValueTransformer("intIndexed[0]");
        final TestBean testBean = new TestBean();
        testBean.setIntIndexed(0, expectedIntegerValue.intValue());
        assertEquals(expectedIntegerValue, transformer.transform(testBean));
    }

public void testTransformWithNestedIndexedProperty_1_oe() {
        final BeanToPropertyValueTransformer transformer =
            new BeanToPropertyValueTransformer("anotherNested.intIndexed[0]");
        final TestBean testBean = new TestBean();
        final TestBean nestedBean = new TestBean();
        nestedBean.setIntIndexed(0, expectedIntegerValue.intValue());
        testBean.setAnotherNested(nestedBean);
        assertEquals(expectedIntegerValue, transformer.transform(testBean));
    }

public void testTransformWithNullInPathAndIgnoreTrue_1_oe() {
        final BeanToPropertyValueTransformer transformer =
            new BeanToPropertyValueTransformer("anotherNested.stringProperty",true);
        assertEquals(null, transformer.transform(new TestBean()));
    }

}
