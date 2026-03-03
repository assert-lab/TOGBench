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

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

/**
 * Test class for {@code IntrospectionContext}.
 *
 * @version $Id$
 */
public class DefaultIntrospectionContextTestCase_OE25Dev extends TestCase {
    /** Constant for the name of a property. */
    private static final String PROP = "foo";

    /** The context to be tested. */
    private DefaultIntrospectionContext context;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        context = new DefaultIntrospectionContext(getClass());
    }

    /**
     * Creates a property descriptor object for a property with the given name.
     *
     * @param propName the property name
     * @return the descriptor for this property
     */
    private static PropertyDescriptor createDescriptor(final String propName) {
        try {
            return new PropertyDescriptor(propName,
                    DefaultIntrospectionContextTestCase_OE25Dev.class, null, null);
        } catch (final IntrospectionException e) {
            throw new IllegalStateException("Unexpected exception: " + e);
        }
    }

    /**
     * Tests a newly created instance.
     */

    /**
     * Tests whether a property descriptor can be added.
     */

    /**
     * Tries to add a null descriptor.
     */
    public void testAddPropertyDescriptorNull() {
        try {
            context.addPropertyDescriptor(null);
            fail("Could add null descriptor!");
        } catch (final IllegalArgumentException iex) {
            // ok
        }
    }

    /**
     * Tests whether multiple descriptors can be added.
     */

    /**
     * Tries to add a null array with property descriptors.
     */
    public void testAddPropertyDescriptorsNull() {
        try {
            context.addPropertyDescriptors(null);
            fail("Could add a null array with descriptors!");
        } catch (final IllegalArgumentException iex) {
            // ok
        }
    }

    /**
     * Tests hasProperty() if the expected result is false.
     */

    /**
     * Tests getPropertyDescriptor() if the property name is unknown.
     */

    /**
     * Tests that the set with property names cannot be changed.
     */
    public void testPropertyNamesModify() {
        final Set<String> names = context.propertyNames();
        try {
            names.add(PROP);
            fail("Could modify property names set!");
        } catch (final UnsupportedOperationException uex) {
            // ok
        }
    }

    /**
     * Tests whether a descriptor can be removed.
     */

    public void testInit_1_oe() {
        assertEquals("Wrong current class",getClass(),context.getTargetClass());
    }

    public void testInit_2_oe() {
        assertTrue("Got property names", context.propertyNames().isEmpty());
    }

    public void testAddPropertyDescriptor_1_oe() {
        final PropertyDescriptor desc = createDescriptor(PROP);
        context.addPropertyDescriptor(desc);
        assertTrue("Property not found", context.hasProperty(PROP));
    }

    public void testAddPropertyDescriptor_2_oe() {
        final PropertyDescriptor desc = createDescriptor(PROP);
        context.addPropertyDescriptor(desc);
        assertSame("Wrong descriptor",desc,context.getPropertyDescriptor(PROP));
    }

    public void testAddPropertyDescriptors_1_oe() {
        final int count = 4;
        final PropertyDescriptor[] descs = new PropertyDescriptor[count];
        final Set<PropertyDescriptor> descSet = new HashSet<PropertyDescriptor>();
        for (int i = 0; i < count; i++) {
            descs[i] = createDescriptor(PROP + i);
            descSet.add(descs[i]);
        }
        context.addPropertyDescriptors(descs);
        final PropertyDescriptor d = createDescriptor(PROP);
        context.addPropertyDescriptor(d);
        descSet.add(d);
        final Set<String> names = context.propertyNames();
        assertEquals("Wrong number of property names", count + 1, names.size());
    }

    public void testAddPropertyDescriptors_2_oe() {
        final int count = 4;
        final PropertyDescriptor[] descs = new PropertyDescriptor[count];
        final Set<PropertyDescriptor> descSet = new HashSet<PropertyDescriptor>();
        for (int i = 0; i < count; i++) {
            descs[i] = createDescriptor(PROP + i);
            descSet.add(descs[i]);
        }
        context.addPropertyDescriptors(descs);
        final PropertyDescriptor d = createDescriptor(PROP);
        context.addPropertyDescriptor(d);
        descSet.add(d);
        final Set<String> names = context.propertyNames();
        assertTrue("Property not found: " + PROP, names.contains(PROP));
    }

    public void testAddPropertyDescriptors_3_oe() {
        final int count = 4;
        final PropertyDescriptor[] descs = new PropertyDescriptor[count];
        final Set<PropertyDescriptor> descSet = new HashSet<PropertyDescriptor>();
        for (int i = 0; i < count; i++) {
            descs[i] = createDescriptor(PROP + i);
            descSet.add(descs[i]);
        }
        context.addPropertyDescriptors(descs);
        final PropertyDescriptor d = createDescriptor(PROP);
        context.addPropertyDescriptor(d);
        descSet.add(d);
        final Set<String> names = context.propertyNames();
        for (int i = 0; i < count; i++) {
            assertTrue("Property not found: " +(PROP + i),names.contains(PROP + i));
    }
    }

    public void testAddPropertyDescriptors_4_oe() {
        final int count = 4;
        final PropertyDescriptor[] descs = new PropertyDescriptor[count];
        final Set<PropertyDescriptor> descSet = new HashSet<PropertyDescriptor>();
        for (int i = 0; i < count; i++) {
            descs[i] = createDescriptor(PROP + i);
            descSet.add(descs[i]);
        }
        context.addPropertyDescriptors(descs);
        final PropertyDescriptor d = createDescriptor(PROP);
        context.addPropertyDescriptor(d);
        descSet.add(d);
        final Set<String> names = context.propertyNames();
        for (int i = 0; i < count; i++) {
        }
        final PropertyDescriptor[] addedDescs = context.getPropertyDescriptors();
        assertEquals("Wrong number of added descriptors",count + 1,addedDescs.length);
    }

    public void testAddPropertyDescriptors_5_oe() {
        final int count = 4;
        final PropertyDescriptor[] descs = new PropertyDescriptor[count];
        final Set<PropertyDescriptor> descSet = new HashSet<PropertyDescriptor>();
        for (int i = 0; i < count; i++) {
            descs[i] = createDescriptor(PROP + i);
            descSet.add(descs[i]);
        }
        context.addPropertyDescriptors(descs);
        final PropertyDescriptor d = createDescriptor(PROP);
        context.addPropertyDescriptor(d);
        descSet.add(d);
        final Set<String> names = context.propertyNames();
        for (int i = 0; i < count; i++) {
        }
        final PropertyDescriptor[] addedDescs = context.getPropertyDescriptors();
        for (final PropertyDescriptor pd : addedDescs) {
            assertTrue("Unexpected descriptor: " + pd, descSet.remove(pd));
    }
    }

    public void testHasPropertyFalse_1_oe() {
        assertFalse("Wrong result (1)", context.hasProperty(PROP));
    }

    public void testHasPropertyFalse_2_oe() {
        context.addPropertyDescriptor(createDescriptor(PROP));
        assertFalse("Wrong result (2)", context.hasProperty("other"));
    }

    public void testGetPropertyDescriptorUnknown_1_oe() {
        assertNull("Got a property (1)", context.getPropertyDescriptor(PROP));
    }

    public void testGetPropertyDescriptorUnknown_2_oe() {
        context.addPropertyDescriptor(createDescriptor(PROP));
        assertNull("Got a property (2)", context.getPropertyDescriptor("other"));
    }

    public void testRemovePropertyDescriptor_1_oe() {
        context.addPropertyDescriptor(createDescriptor(PROP));
        context.removePropertyDescriptor(PROP);
        assertTrue("Got property names", context.propertyNames().isEmpty());
    }

    public void testRemovePropertyDescriptor_2_oe() {
        context.addPropertyDescriptor(createDescriptor(PROP));
        context.removePropertyDescriptor(PROP);
        assertEquals("Got descriptors",0,context.getPropertyDescriptors().length);
    }

}
