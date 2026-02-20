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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * <p>Test Case for the <code>LazyDynaMap</code> implementation class.</p>
 *
 * @version $Id$
 */
public class LazyDynaMapTestCase_OE25Dev extends TestCase {

    protected LazyDynaMap  dynaMap    = null;
    protected String testProperty     = "myProperty";
    protected String testPropertyA    = "myProperty-A";
    protected String testPropertyB    = "myProperty-B";
    protected String testString1      = "myStringValue-1";
    protected String testString2      = "myStringValue-2";
    protected Integer testInteger1    = new Integer(30);
    protected Integer testInteger2    = new Integer(40);
    protected String testKey          = "myKey";

    // ---------------------------------------------------------- Constructors

    /**
     * Construct a new instance of this test case.
     *
     * @param name Name of the test case
     */
    public LazyDynaMapTestCase_OE25Dev(final String name) {
        super(name);
    }

    // -------------------------------------------------- Overall Test Methods

    /**
     * Run thus Test
     */
    public static void main(final String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    /**
     * Return the tests included in this test suite.
     */
    public static Test suite() {
        return (new TestSuite(LazyDynaMapTestCase_OE25Dev.class));
    }

    /**
     * Set up instance variables required by this test case.
     */
    @Override
    public void setUp() throws Exception {
        dynaMap = new LazyDynaMap();
        dynaMap.setReturnNull(true);
    }

    /**
     * Tear down instance variables required by this test case.
     */
    @Override
    public void tearDown() {
      dynaMap = null;
    }

    // ------------------------------------------------ Individual Test Methods

    /**
     * General Tests
     */

    /**
     * Test Getting/Setting a Simple Property
     */

    /**
     * Test Setting a Simple Property when MutableDynaClass is set to restricted
     */

    /**
     * Test Getting/Setting a 'Mapped' Property - default HashMap property
     */

    /**
     * Test Getting/Setting a 'Mapped' Property - use TreeMap property
     */

    /**
     * Test Setting a 'Mapped' Property using PropertyUtils
     */

    /**
     * Test Setting a Mapped Property when MutableDynaClass is set to restricted
     */

    /**
     * Test setting mapped property for type which is not Map
     */

    /**
     * Test Getting/Setting an 'Indexed' Property - default ArrayList property
     */

    /**
     * Test Getting/Setting a List 'Indexed' Property - use alternative List (LinkedList)
     */

    /**
     * Test Getting/Setting a primitive array 'Indexed' Property - use int[]
     */

    /**
     * Test Getting/Setting an Object array 'Indexed' Property - use String[]
     */

    /**
     * Test Getting/Setting an DynaBean[] array
     */

    /**
     * Test Setting an 'Indexed' Property using PropertyUtils
     */

    /**
     * Test Setting an Indexed Property when MutableDynaClass is set to restricted
     */

    /**
     * Test setting indexed property for type which is not List or Array
     */

    /**
     * Test creating using DynaClass.newInstance()
     */

    public void testGeneral_1_oe() {
//        LazyDynaMap bean = new LazyDynaMap("TestBean");
        assertEquals("Check DynaClass name", "TestBean", new LazyDynaMap("TestBean").getName());
    }

    public void testSimpleProperty_1_oe() {

        // Check the property & value doesn't exist
        assertNull("Check Property doesn't exist", dynaMap.getDynaProperty(testProperty));
    }

    public void testSimpleProperty_2_oe() {

        // Check the property & value doesn't exist
        // removed other assertion
        assertNull("Check Value is null", dynaMap.get(testProperty));
    }

    public void testSimpleProperty_3_oe() {

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Set a new property - should add new property and set value
        dynaMap.set(testProperty, testInteger1);
        assertEquals("Check First Value is correct", testInteger1, dynaMap.get(testProperty));
    }

    public void testSimpleProperty_4_oe() {

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Set a new property - should add new property and set value
        dynaMap.set(testProperty, testInteger1);
        // removed other assertion
        assertEquals("Check Property type is correct", Integer.class, dynaMap.getDynaProperty(testProperty).getType());
    }

    public void testSimpleProperty_5_oe() {

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Set a new property - should add new property and set value
        dynaMap.set(testProperty, testInteger1);
        // removed other assertion
        // removed other assertion

        // Set the property again - should set the new value
        dynaMap.set(testProperty, testInteger2);
        assertEquals("Check Second Value is correct", testInteger2, dynaMap.get(testProperty));
    }

    public void testSimpleProperty_6_oe() {

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Set a new property - should add new property and set value
        dynaMap.set(testProperty, testInteger1);
        // removed other assertion
        // removed other assertion

        // Set the property again - should set the new value
        dynaMap.set(testProperty, testInteger2);
        // removed other assertion

        // Set the property again - with a different type, should succeed
        dynaMap.set(testProperty, testString1);
        assertEquals("Check Third Value is correct", testString1, dynaMap.get(testProperty));
    }

    public void testSimplePropertyRestricted_1_oe() {

        // Set the MutableDyanClass to 'restricted' (i.e. no new properties cab be added
        dynaMap.setRestricted(true);
        assertTrue("Check MutableDynaClass is restricted", dynaMap.isRestricted());
    }

    public void testSimplePropertyRestricted_2_oe() {

        // Set the MutableDyanClass to 'restricted' (i.e. no new properties cab be added
        dynaMap.setRestricted(true);
        // removed other assertion

        // Check the property & value doesn't exist
        assertNull("Check Property doesn't exist", dynaMap.getDynaProperty(testProperty));
    }

    public void testSimplePropertyRestricted_3_oe() {

        // Set the MutableDyanClass to 'restricted' (i.e. no new properties cab be added
        dynaMap.setRestricted(true);
        // removed other assertion

        // Check the property & value doesn't exist
        // removed other assertion
        assertNull("Check Value is null", dynaMap.get(testProperty));
    }

    public void testMappedPropertyDefault_1_oe() {

        // Check the property & value doesn't exist
        assertNull("Check Mapped Property doesn't exist", dynaMap.getDynaProperty(testProperty));
    }

    public void testMappedPropertyDefault_2_oe() {

        // Check the property & value doesn't exist
        // removed other assertion
        assertNull("Check Map is null", dynaMap.get(testProperty));
    }

    public void testMappedPropertyDefault_3_oe() {

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion
        assertNull("Check Mapped Value is null", dynaMap.get(testProperty, testKey));
    }

    public void testMappedPropertyDefault_4_oe() {

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set a new mapped property - should add new HashMap property and set the mapped value
        dynaMap.set(testProperty, testKey, testInteger1);
        assertEquals("Check Mapped Property exists", HashMap.class, dynaMap.get(testProperty).getClass());
    }

    public void testMappedPropertyDefault_5_oe() {

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set a new mapped property - should add new HashMap property and set the mapped value
        dynaMap.set(testProperty, testKey, testInteger1);
        // removed other assertion
        assertEquals("Check First Mapped Value is correct(a)", testInteger1, dynaMap.get(testProperty, testKey));
    }

    public void testMappedPropertyDefault_6_oe() {

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set a new mapped property - should add new HashMap property and set the mapped value
        dynaMap.set(testProperty, testKey, testInteger1);
        // removed other assertion
        // removed other assertion
        assertEquals("Check First Mapped Value is correct(b)", testInteger1, ((HashMap<?, ?>)dynaMap.get(testProperty)).get(testKey));
    }

    public void testMappedPropertyDefault_7_oe() {

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set a new mapped property - should add new HashMap property and set the mapped value
        dynaMap.set(testProperty, testKey, testInteger1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set the property again - should set the new value
        dynaMap.set(testProperty, testKey, testInteger2);
        assertEquals("Check Second Mapped Value is correct(a)", testInteger2, dynaMap.get(testProperty, testKey));
    }

    public void testMappedPropertyDefault_8_oe() {

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set a new mapped property - should add new HashMap property and set the mapped value
        dynaMap.set(testProperty, testKey, testInteger1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set the property again - should set the new value
        dynaMap.set(testProperty, testKey, testInteger2);
        // removed other assertion
        assertEquals("Check Second Mapped Value is correct(b)", testInteger2, ((HashMap<?, ?>)dynaMap.get(testProperty)).get(testKey));
    }

    public void testMappedPropertyTreeMap_1_oe() {

        // Check the property & value doesn't exist
        assertNull("Check Mapped Property doesn't exist", dynaMap.getDynaProperty(testProperty));
    }

    public void testMappedPropertyTreeMap_2_oe() {

        // Check the property & value doesn't exist
        // removed other assertion
        assertNull("Check Map is null", dynaMap.get(testProperty));
    }

    public void testMappedPropertyTreeMap_3_oe() {

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a 'TreeMap' property to the DynaClass
        dynaMap.add(testProperty, TreeMap.class);
        assertTrue("Check Property is mapped", dynaMap.getDynaProperty(testProperty).isMapped());
    }

    public void testMappedPropertyTreeMap_4_oe() {

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a 'TreeMap' property to the DynaClass
        dynaMap.add(testProperty, TreeMap.class);
        // removed other assertion
        assertEquals("Check Property is correct type", TreeMap.class, dynaMap.getDynaProperty(testProperty).getType());
    }

    public void testMappedPropertyTreeMap_5_oe() {

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a 'TreeMap' property to the DynaClass
        dynaMap.add(testProperty, TreeMap.class);
        // removed other assertion
        // removed other assertion
        assertEquals("Check Mapped Property now exists", TreeMap.class, dynaMap.get(testProperty).getClass());
    }

    public void testMappedPropertyTreeMap_6_oe() {

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a 'TreeMap' property to the DynaClass
        dynaMap.add(testProperty, TreeMap.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set a new mapped property - should instatiate a new TreeMap property and set the mapped value
        dynaMap.set(testProperty, testKey, testInteger1);
        assertEquals("Check Mapped Property exists", TreeMap.class, dynaMap.get(testProperty).getClass());
    }

    public void testMappedPropertyTreeMap_7_oe() {

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a 'TreeMap' property to the DynaClass
        dynaMap.add(testProperty, TreeMap.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set a new mapped property - should instatiate a new TreeMap property and set the mapped value
        dynaMap.set(testProperty, testKey, testInteger1);
        // removed other assertion
        assertEquals("Check First Mapped Value is correct(a)", testInteger1, dynaMap.get(testProperty, testKey));
    }

    public void testMappedPropertyTreeMap_8_oe() {

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a 'TreeMap' property to the DynaClass
        dynaMap.add(testProperty, TreeMap.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set a new mapped property - should instatiate a new TreeMap property and set the mapped value
        dynaMap.set(testProperty, testKey, testInteger1);
        // removed other assertion
        // removed other assertion
        assertEquals("Check First Mapped Value is correct(b)", testInteger1, ((TreeMap<?, ?>)dynaMap.get(testProperty)).get(testKey));
    }

    public void testMappedPropertyTreeMap_9_oe() {

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a 'TreeMap' property to the DynaClass
        dynaMap.add(testProperty, TreeMap.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set a new mapped property - should instatiate a new TreeMap property and set the mapped value
        dynaMap.set(testProperty, testKey, testInteger1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set the property again - should set the new value
        dynaMap.set(testProperty, testKey, testInteger2);
        assertEquals("Check Second Mapped Value is correct(a)", testInteger2, dynaMap.get(testProperty, testKey));
    }

    public void testMappedPropertyTreeMap_10_oe() {

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a 'TreeMap' property to the DynaClass
        dynaMap.add(testProperty, TreeMap.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set a new mapped property - should instatiate a new TreeMap property and set the mapped value
        dynaMap.set(testProperty, testKey, testInteger1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set the property again - should set the new value
        dynaMap.set(testProperty, testKey, testInteger2);
        // removed other assertion
        assertEquals("Check Second Mapped Value is correct(b)", testInteger2, ((TreeMap<?, ?>)dynaMap.get(testProperty)).get(testKey));
    }

    public void testMappedPropertyUtils_1_oe() {

        dynaMap.setReturnNull(false);

        // Check the property & value doesn't exist
        assertFalse("Check Mapped Property doesn't exist", dynaMap.isDynaProperty(testProperty));
    }

    public void testMappedPropertyUtils_2_oe() {

        dynaMap.setReturnNull(false);

        // Check the property & value doesn't exist
        // removed other assertion
        assertNull("Check Map is null", dynaMap.get(testProperty));
    }

    public void testMappedPropertyUtils_3_oe() {

        dynaMap.setReturnNull(false);

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion
        assertNull("Check Mapped Value is null", dynaMap.get(testProperty, testKey));
    }

    public void testMappedPropertyUtils_6_oe() {

        dynaMap.setReturnNull(false);

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set the mapped property using PropertyUtils
        try {
          PropertyUtils.setProperty(dynaMap, testProperty+"("+testKey+")", testString1);
        }
        catch (final NoSuchMethodException ex) {
            // removed other assertion
        }
        catch (final InvocationTargetException ex) {
            // removed other assertion
        }
        catch (final IllegalAccessException ex) {
            fail("testIndexedPropertyUtils threw "+ex);
    }
    }

    public void testMappedPropertyUtils_7_oe() {

        dynaMap.setReturnNull(false);

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set the mapped property using PropertyUtils
        try {
          PropertyUtils.setProperty(dynaMap, testProperty+"("+testKey+")", testString1);
        }
        catch (final NoSuchMethodException ex) {
            // removed other assertion
        }
        catch (final InvocationTargetException ex) {
            // removed other assertion
        }
        catch (final IllegalAccessException ex) {
            // removed other assertion
        }

        // Check property value correctly set
        assertEquals("Check Mapped Bean Value is correct", testString1, dynaMap.get(testProperty, testKey));
    }

    public void testMappedPropertyRestricted_1_oe() {

        // Set the MutableDyanClass to 'restricted' (i.e. no new properties cab be added
        dynaMap.setRestricted(true);
        assertTrue("Check MutableDynaClass is restricted", dynaMap.isRestricted());
    }

    public void testMappedPropertyRestricted_2_oe() {

        // Set the MutableDyanClass to 'restricted' (i.e. no new properties cab be added
        dynaMap.setRestricted(true);
        // removed other assertion

        // Check the property & value doesn't exist
        assertNull("Check Property doesn't exist", dynaMap.getDynaProperty(testProperty));
    }

    public void testMappedPropertyRestricted_3_oe() {

        // Set the MutableDyanClass to 'restricted' (i.e. no new properties cab be added
        dynaMap.setRestricted(true);
        // removed other assertion

        // Check the property & value doesn't exist
        // removed other assertion
        assertNull("Check Value is null", dynaMap.get(testProperty));
    }

    public void testMappedInvalidType_1_oe() {
        dynaMap.set(testProperty, new Integer(1));
        assertFalse("Check Property is not mapped", dynaMap.getDynaProperty(testProperty).isMapped());
    }

    public void testIndexedPropertyDefault_1_oe() {

        int index = 3;

        // Check the property & value doesn't exist
        assertNull("Check Indexed Property doesn't exist", dynaMap.getDynaProperty(testProperty));
    }

    public void testIndexedPropertyDefault_2_oe() {

        int index = 3;

        // Check the property & value doesn't exist
        // removed other assertion
        assertNull("Check Indexed Property is null", dynaMap.get(testProperty));
    }

    public void testIndexedPropertyDefault_3_oe() {

        int index = 3;

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion
        assertNull("Check Indexed value is null", dynaMap.get(testProperty, index));
    }

    public void testIndexedPropertyDefault_4_oe() {

        int index = 3;

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set the property, should create new ArrayList and set appropriate indexed value
        dynaMap.set(testProperty, index, testInteger1);
        assertNotNull("Check Indexed Property is not null", dynaMap.get(testProperty));
    }

    public void testIndexedPropertyDefault_5_oe() {

        int index = 3;

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set the property, should create new ArrayList and set appropriate indexed value
        dynaMap.set(testProperty, index, testInteger1);
        // removed other assertion
        assertEquals("Check Indexed Property is correct type", ArrayList.class, dynaMap.get(testProperty).getClass());
    }

    public void testIndexedPropertyDefault_6_oe() {

        int index = 3;

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set the property, should create new ArrayList and set appropriate indexed value
        dynaMap.set(testProperty, index, testInteger1);
        // removed other assertion
        // removed other assertion
        assertEquals("Check First Indexed Value is correct", testInteger1, dynaMap.get(testProperty, index));
    }

    public void testIndexedPropertyDefault_7_oe() {

        int index = 3;

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set the property, should create new ArrayList and set appropriate indexed value
        dynaMap.set(testProperty, index, testInteger1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Check First Array length is correct", new Integer(index+1),  new Integer(((ArrayList<?>)dynaMap.get(testProperty)).size()));
    }

    public void testIndexedPropertyDefault_8_oe() {

        int index = 3;

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set the property, should create new ArrayList and set appropriate indexed value
        dynaMap.set(testProperty, index, testInteger1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set a second indexed value, should automatically grow the ArrayList and set appropriate indexed value
        index = index + 2;
        dynaMap.set(testProperty, index, testString1);
        assertEquals("Check Second Indexed Value is correct", testString1, dynaMap.get(testProperty, index));
    }

    public void testIndexedPropertyDefault_9_oe() {

        int index = 3;

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set the property, should create new ArrayList and set appropriate indexed value
        dynaMap.set(testProperty, index, testInteger1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set a second indexed value, should automatically grow the ArrayList and set appropriate indexed value
        index = index + 2;
        dynaMap.set(testProperty, index, testString1);
        // removed other assertion
        assertEquals("Check Second Array length is correct", new Integer(index+1),  new Integer(((ArrayList<?>)dynaMap.get(testProperty)).size()));
    }

    public void testIndexedLinkedList_1_oe() {

        int   index     = 3;

        // Check the property & value doesn't exist
        assertNull("Check Indexed Property doesn't exist", dynaMap.getDynaProperty(testProperty));
    }

    public void testIndexedLinkedList_2_oe() {

        int   index     = 3;

        // Check the property & value doesn't exist
        // removed other assertion
        assertNull("Check Indexed Property is null", dynaMap.get(testProperty));
    }

    public void testIndexedLinkedList_3_oe() {

        int   index     = 3;

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a 'LinkedList' property to the DynaClass - should instantiate a new LinkedList
        dynaMap.add(testProperty, LinkedList.class);
        assertTrue("Check Property is indexed", dynaMap.getDynaProperty(testProperty).isIndexed());
    }

    public void testIndexedLinkedList_4_oe() {

        int   index     = 3;

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a 'LinkedList' property to the DynaClass - should instantiate a new LinkedList
        dynaMap.add(testProperty, LinkedList.class);
        // removed other assertion
        assertEquals("Check Property is correct type", LinkedList.class, dynaMap.getDynaProperty(testProperty).getType());
    }

    public void testIndexedLinkedList_5_oe() {

        int   index     = 3;

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a 'LinkedList' property to the DynaClass - should instantiate a new LinkedList
        dynaMap.add(testProperty, LinkedList.class);
        // removed other assertion
        // removed other assertion
        assertEquals("Check Indexed Property now exists", LinkedList.class, dynaMap.get(testProperty).getClass());
    }

    public void testIndexedLinkedList_6_oe() {

        int   index     = 3;

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a 'LinkedList' property to the DynaClass - should instantiate a new LinkedList
        dynaMap.add(testProperty, LinkedList.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set the Indexed property, should grow the list to the correct size
        dynaMap.set(testProperty, index, testString1);
        assertEquals("Check Property type is correct", LinkedList.class, dynaMap.get(testProperty).getClass());
    }

    public void testIndexedLinkedList_7_oe() {

        int   index     = 3;

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a 'LinkedList' property to the DynaClass - should instantiate a new LinkedList
        dynaMap.add(testProperty, LinkedList.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set the Indexed property, should grow the list to the correct size
        dynaMap.set(testProperty, index, testString1);
        // removed other assertion
        assertEquals("Check First Indexed Value is correct", testString1, dynaMap.get(testProperty, index));
    }

    public void testIndexedLinkedList_8_oe() {

        int   index     = 3;

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a 'LinkedList' property to the DynaClass - should instantiate a new LinkedList
        dynaMap.add(testProperty, LinkedList.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set the Indexed property, should grow the list to the correct size
        dynaMap.set(testProperty, index, testString1);
        // removed other assertion
        // removed other assertion
        assertEquals("Check First Array length is correct", new Integer(index+1),  new Integer(((LinkedList<?>)dynaMap.get(testProperty)).size()));
    }

    public void testIndexedLinkedList_9_oe() {

        int   index     = 3;

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a 'LinkedList' property to the DynaClass - should instantiate a new LinkedList
        dynaMap.add(testProperty, LinkedList.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set the Indexed property, should grow the list to the correct size
        dynaMap.set(testProperty, index, testString1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set a second indexed value, should automatically grow the LinkedList and set appropriate indexed value
        index = index + 2;
        dynaMap.set(testProperty, index, testInteger1);
        assertEquals("Check Second Indexed Value is correct", testInteger1, dynaMap.get(testProperty, index));
    }

    public void testIndexedLinkedList_10_oe() {

        int   index     = 3;

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a 'LinkedList' property to the DynaClass - should instantiate a new LinkedList
        dynaMap.add(testProperty, LinkedList.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set the Indexed property, should grow the list to the correct size
        dynaMap.set(testProperty, index, testString1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set a second indexed value, should automatically grow the LinkedList and set appropriate indexed value
        index = index + 2;
        dynaMap.set(testProperty, index, testInteger1);
        // removed other assertion
        assertEquals("Check Second Array length is correct", new Integer(index+1),  new Integer(((LinkedList<?>)dynaMap.get(testProperty)).size()));
    }

    public void testIndexedPrimitiveArray_1_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];

        // Check the property & value doesn't exist
        assertNull("Check Indexed Property doesn't exist", dynaMap.getDynaProperty(testProperty));
    }

    public void testIndexedPrimitiveArray_2_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];

        // Check the property & value doesn't exist
        // removed other assertion
        assertNull("Check Indexed Property is null", dynaMap.get(testProperty));
    }

    public void testIndexedPrimitiveArray_3_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a DynaProperty of type int[]
        dynaMap.add(testProperty, primitiveArray.getClass());
        assertEquals("Check Indexed Property exists", primitiveArray.getClass(), dynaMap.getDynaProperty(testProperty).getType());
    }

    public void testIndexedPrimitiveArray_4_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a DynaProperty of type int[]
        dynaMap.add(testProperty, primitiveArray.getClass());
        // removed other assertion
        assertTrue("Check Indexed Property exists", dynaMap.get(testProperty).getClass().isInstance(primitiveArray));
    }

    public void testIndexedPrimitiveArray_5_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a DynaProperty of type int[]
        dynaMap.add(testProperty, primitiveArray.getClass());
        // removed other assertion
        // removed other assertion

        // Set an indexed value
        dynaMap.set(testProperty, index, testInteger1);
        assertNotNull("Check Indexed Property is not null", dynaMap.get(testProperty));
    }

    public void testIndexedPrimitiveArray_6_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a DynaProperty of type int[]
        dynaMap.add(testProperty, primitiveArray.getClass());
        // removed other assertion
        // removed other assertion

        // Set an indexed value
        dynaMap.set(testProperty, index, testInteger1);
        // removed other assertion
        assertEquals("Check Indexed Property is correct type", primitiveArray.getClass(), dynaMap.get(testProperty).getClass());
    }

    public void testIndexedPrimitiveArray_7_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a DynaProperty of type int[]
        dynaMap.add(testProperty, primitiveArray.getClass());
        // removed other assertion
        // removed other assertion

        // Set an indexed value
        dynaMap.set(testProperty, index, testInteger1);
        // removed other assertion
        // removed other assertion
        assertEquals("Check First Indexed Value is correct(a)", testInteger1, dynaMap.get(testProperty, index));
    }

    public void testIndexedPrimitiveArray_8_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a DynaProperty of type int[]
        dynaMap.add(testProperty, primitiveArray.getClass());
        // removed other assertion
        // removed other assertion

        // Set an indexed value
        dynaMap.set(testProperty, index, testInteger1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Check First Indexed Value is correct(b)", testInteger1, new Integer(((int[])dynaMap.get(testProperty))[index]));
    }

    public void testIndexedPrimitiveArray_9_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a DynaProperty of type int[]
        dynaMap.add(testProperty, primitiveArray.getClass());
        // removed other assertion
        // removed other assertion

        // Set an indexed value
        dynaMap.set(testProperty, index, testInteger1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Check Array length is correct", new Integer(index+1),  new Integer(((int[])dynaMap.get(testProperty)).length));
    }

    public void testIndexedPrimitiveArray_10_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a DynaProperty of type int[]
        dynaMap.add(testProperty, primitiveArray.getClass());
        // removed other assertion
        // removed other assertion

        // Set an indexed value
        dynaMap.set(testProperty, index, testInteger1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set a second indexed value, should automatically grow the int[] and set appropriate indexed value
        index = index + 2;
        dynaMap.set(testProperty, index, testInteger2);
        assertEquals("Check Second Indexed Value is correct(a)", testInteger2, dynaMap.get(testProperty, index));
    }

    public void testIndexedPrimitiveArray_11_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a DynaProperty of type int[]
        dynaMap.add(testProperty, primitiveArray.getClass());
        // removed other assertion
        // removed other assertion

        // Set an indexed value
        dynaMap.set(testProperty, index, testInteger1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set a second indexed value, should automatically grow the int[] and set appropriate indexed value
        index = index + 2;
        dynaMap.set(testProperty, index, testInteger2);
        // removed other assertion
        assertEquals("Check Second Indexed Value is correct(b)", testInteger2, new Integer(((int[])dynaMap.get(testProperty))[index]));
    }

    public void testIndexedPrimitiveArray_12_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a DynaProperty of type int[]
        dynaMap.add(testProperty, primitiveArray.getClass());
        // removed other assertion
        // removed other assertion

        // Set an indexed value
        dynaMap.set(testProperty, index, testInteger1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set a second indexed value, should automatically grow the int[] and set appropriate indexed value
        index = index + 2;
        dynaMap.set(testProperty, index, testInteger2);
        // removed other assertion
        // removed other assertion
        assertEquals("Check Second Array length is correct", new Integer(index+1),  new Integer(((int[])dynaMap.get(testProperty)).length));
    }

    public void testIndexedObjectArray_1_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];

        // Check the property & value doesn't exist
        assertNull("Check Indexed Property doesn't exist", dynaMap.getDynaProperty(testProperty));
    }

    public void testIndexedObjectArray_2_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];

        // Check the property & value doesn't exist
        // removed other assertion
        assertNull("Check Indexed Property is null", dynaMap.get(testProperty));
    }

    public void testIndexedObjectArray_3_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a DynaProperty of type String[]
        dynaMap.add(testProperty, objectArray.getClass());
        assertEquals("Check Indexed Property exists", objectArray.getClass(), dynaMap.getDynaProperty(testProperty).getType());
    }

    public void testIndexedObjectArray_4_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a DynaProperty of type String[]
        dynaMap.add(testProperty, objectArray.getClass());
        // removed other assertion
        assertTrue("Check Indexed Property exists", dynaMap.get(testProperty).getClass().isInstance(objectArray));
    }

    public void testIndexedObjectArray_5_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a DynaProperty of type String[]
        dynaMap.add(testProperty, objectArray.getClass());
        // removed other assertion
        // removed other assertion

        // Set an indexed value
        dynaMap.set(testProperty, index, testString1);
        assertNotNull("Check Indexed Property is not null", dynaMap.get(testProperty));
    }

    public void testIndexedObjectArray_6_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a DynaProperty of type String[]
        dynaMap.add(testProperty, objectArray.getClass());
        // removed other assertion
        // removed other assertion

        // Set an indexed value
        dynaMap.set(testProperty, index, testString1);
        // removed other assertion
        assertEquals("Check Indexed Property is correct type", objectArray.getClass(), dynaMap.get(testProperty).getClass());
    }

    public void testIndexedObjectArray_7_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a DynaProperty of type String[]
        dynaMap.add(testProperty, objectArray.getClass());
        // removed other assertion
        // removed other assertion

        // Set an indexed value
        dynaMap.set(testProperty, index, testString1);
        // removed other assertion
        // removed other assertion
        assertEquals("Check First Indexed Value is correct(a)", testString1, dynaMap.get(testProperty, index));
    }

    public void testIndexedObjectArray_8_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a DynaProperty of type String[]
        dynaMap.add(testProperty, objectArray.getClass());
        // removed other assertion
        // removed other assertion

        // Set an indexed value
        dynaMap.set(testProperty, index, testString1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Check First Indexed Value is correct(b)", testString1, ((String[])dynaMap.get(testProperty))[index]);
    }

    public void testIndexedObjectArray_9_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a DynaProperty of type String[]
        dynaMap.add(testProperty, objectArray.getClass());
        // removed other assertion
        // removed other assertion

        // Set an indexed value
        dynaMap.set(testProperty, index, testString1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Check Array length is correct", new Integer(index+1),  new Integer(((String[])dynaMap.get(testProperty)).length));
    }

    public void testIndexedObjectArray_10_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a DynaProperty of type String[]
        dynaMap.add(testProperty, objectArray.getClass());
        // removed other assertion
        // removed other assertion

        // Set an indexed value
        dynaMap.set(testProperty, index, testString1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set a second indexed value, should automatically grow the String[] and set appropriate indexed value
        index = index + 2;
        dynaMap.set(testProperty, index, testString2);
        assertEquals("Check Second Indexed Value is correct(a)", testString2, dynaMap.get(testProperty, index));
    }

    public void testIndexedObjectArray_11_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a DynaProperty of type String[]
        dynaMap.add(testProperty, objectArray.getClass());
        // removed other assertion
        // removed other assertion

        // Set an indexed value
        dynaMap.set(testProperty, index, testString1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set a second indexed value, should automatically grow the String[] and set appropriate indexed value
        index = index + 2;
        dynaMap.set(testProperty, index, testString2);
        // removed other assertion
        assertEquals("Check Second Indexed Value is correct(b)", testString2, ((String[])dynaMap.get(testProperty))[index]);
    }

    public void testIndexedObjectArray_12_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a DynaProperty of type String[]
        dynaMap.add(testProperty, objectArray.getClass());
        // removed other assertion
        // removed other assertion

        // Set an indexed value
        dynaMap.set(testProperty, index, testString1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Set a second indexed value, should automatically grow the String[] and set appropriate indexed value
        index = index + 2;
        dynaMap.set(testProperty, index, testString2);
        // removed other assertion
        // removed other assertion
        assertEquals("Check Second Array length is correct", new Integer(index+1),  new Integer(((String[])dynaMap.get(testProperty)).length));
    }

    public void testIndexedDynaBeanArray_1_oe() {

        final int   index     = 3;
        final Object objectArray = new LazyDynaBean[0];

        // Check the property & value doesn't exist
        assertNull("Check Indexed Property doesn't exist", dynaMap.getDynaProperty(testProperty));
    }

    public void testIndexedDynaBeanArray_2_oe() {

        final int   index     = 3;
        final Object objectArray = new LazyDynaBean[0];

        // Check the property & value doesn't exist
        // removed other assertion
        assertNull("Check Indexed Property is null", dynaMap.get(testProperty));
    }

    public void testIndexedDynaBeanArray_3_oe() {

        final int   index     = 3;
        final Object objectArray = new LazyDynaBean[0];

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a DynaProperty of type String[]
        dynaMap.add(testProperty, objectArray.getClass());
        assertEquals("Check Indexed Property exists", objectArray.getClass(), dynaMap.getDynaProperty(testProperty).getType());
    }

    public void testIndexedDynaBeanArray_4_oe() {

        final int   index     = 3;
        final Object objectArray = new LazyDynaBean[0];

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a DynaProperty of type String[]
        dynaMap.add(testProperty, objectArray.getClass());
        // removed other assertion
        assertEquals("Check Indexed Property is correct type", objectArray.getClass(), dynaMap.get(testProperty).getClass());
    }

    public void testIndexedDynaBeanArray_5_oe() {

        final int   index     = 3;
        final Object objectArray = new LazyDynaBean[0];

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a DynaProperty of type String[]
        dynaMap.add(testProperty, objectArray.getClass());
        // removed other assertion
        // removed other assertion

        // Retrieving from Array should initialize DynaBean
        for (int i = index; i >= 0; i--) {
            assertEquals("Check Array Components initialized", LazyDynaBean.class, dynaMap.get(testProperty, index).getClass());
    }
    }

    public void testIndexedDynaBeanArray_6_oe() {

        final int   index     = 3;
        final Object objectArray = new LazyDynaBean[0];

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion

        // Add a DynaProperty of type String[]
        dynaMap.add(testProperty, objectArray.getClass());
        // removed other assertion
        // removed other assertion

        // Retrieving from Array should initialize DynaBean
        for (int i = index; i >= 0; i--) {
            // removed other assertion
        }

        dynaMap.add(testPropertyB, objectArray.getClass());
        final LazyDynaBean newBean = new LazyDynaBean();
        newBean.set(testPropertyB, testString2);
        dynaMap.set(testPropertyA, index, newBean);
        assertEquals("Check Indexed Value is correct(a)", testString2, ((DynaBean)dynaMap.get(testPropertyA, index)).get(testPropertyB));
    }

    public void testIndexedPropertyUtils_1_oe() {

        final int   index     = 3;
        dynaMap.setReturnNull(false);

        // Check the property & value doesn't exist
        assertFalse("Check Indexed Property doesn't exist", dynaMap.isDynaProperty(testProperty));
    }

    public void testIndexedPropertyUtils_2_oe() {

        final int   index     = 3;
        dynaMap.setReturnNull(false);

        // Check the property & value doesn't exist
        // removed other assertion
        assertNull("Check Indexed Property is null", dynaMap.get(testProperty));
    }

    public void testIndexedPropertyUtils_3_oe() {

        final int   index     = 3;
        dynaMap.setReturnNull(false);

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion
        assertNull("Check Indexed value is null", dynaMap.get(testProperty, index));
    }

    public void testIndexedPropertyUtils_6_oe() {

        final int   index     = 3;
        dynaMap.setReturnNull(false);

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Use PropertyUtils to set the indexed value
        try {
          PropertyUtils.setProperty(dynaMap, testProperty+"["+index+"]", testString1);
        }
        catch (final NoSuchMethodException ex) {
            // removed other assertion
        }
        catch (final InvocationTargetException ex) {
            // removed other assertion
        }
        catch (final IllegalAccessException ex) {
            fail("testIndexedPropertyUtils threw "+ex);
    }
    }

    public void testIndexedPropertyUtils_7_oe() {

        final int   index     = 3;
        dynaMap.setReturnNull(false);

        // Check the property & value doesn't exist
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Use PropertyUtils to set the indexed value
        try {
          PropertyUtils.setProperty(dynaMap, testProperty+"["+index+"]", testString1);
        }
        catch (final NoSuchMethodException ex) {
            // removed other assertion
        }
        catch (final InvocationTargetException ex) {
            // removed other assertion
        }
        catch (final IllegalAccessException ex) {
            // removed other assertion
        }

        // Check property value correctly set
        assertEquals("Check Indexed Bean Value is correct", testString1, dynaMap.get(testProperty, index));
    }

    public void testIndexedPropertyRestricted_1_oe() {

        final int   index     = 3;

        // Set the MutableDyanClass to 'restricted' (i.e. no new properties cab be added
        dynaMap.setRestricted(true);
        assertTrue("Check MutableDynaClass is restricted", dynaMap.isRestricted());
    }

    public void testIndexedPropertyRestricted_2_oe() {

        final int   index     = 3;

        // Set the MutableDyanClass to 'restricted' (i.e. no new properties cab be added
        dynaMap.setRestricted(true);
        // removed other assertion

        // Check the property & value doesn't exist
        assertNull("Check Property doesn't exist", dynaMap.getDynaProperty(testProperty));
    }

    public void testIndexedPropertyRestricted_3_oe() {

        final int   index     = 3;

        // Set the MutableDyanClass to 'restricted' (i.e. no new properties cab be added
        dynaMap.setRestricted(true);
        // removed other assertion

        // Check the property & value doesn't exist
        // removed other assertion
        assertNull("Check Value is null", dynaMap.get(testProperty));
    }

    public void testIndexedInvalidType_1_oe() {
        final int   index     = 3;
        dynaMap.set(testProperty, "Test String");
        assertFalse("Check Property is not indexed", dynaMap.getDynaProperty(testProperty).isIndexed());
    }

    public void testNewInstance_1_oe() {

        // Create LazyDynaMap using TreeMap
        // containing some properties
        final LazyDynaMap orig = new LazyDynaMap(new TreeMap<String, Object>());
        orig.set("indexProp", 0, "indexVal0");
        orig.set("indexProp", 1, "indexVal1");
        assertEquals("Index prop size", 2, ((List<?>)orig.get("indexProp")).size());
    }

    public void testNewInstance_2_oe() {

        // Create LazyDynaMap using TreeMap
        // containing some properties
        final LazyDynaMap orig = new LazyDynaMap(new TreeMap<String, Object>());
        orig.set("indexProp", 0, "indexVal0");
        orig.set("indexProp", 1, "indexVal1");
        // removed other assertion

        final LazyDynaMap newOne = (LazyDynaMap)orig.newInstance();
        final Map<String, Object> newMap = newOne.getMap();
        assertEquals("Check Map type", TreeMap.class, newMap.getClass());
    }

    public void testNewInstance_3_oe() {

        // Create LazyDynaMap using TreeMap
        // containing some properties
        final LazyDynaMap orig = new LazyDynaMap(new TreeMap<String, Object>());
        orig.set("indexProp", 0, "indexVal0");
        orig.set("indexProp", 1, "indexVal1");
        // removed other assertion

        final LazyDynaMap newOne = (LazyDynaMap)orig.newInstance();
        final Map<String, Object> newMap = newOne.getMap();
        // removed other assertion

        final ArrayList<?> indexProp = (ArrayList<?>)newMap.get("indexProp");
        assertNotNull("Indexed Prop missing", indexProp);
    }

    public void testNewInstance_4_oe() {

        // Create LazyDynaMap using TreeMap
        // containing some properties
        final LazyDynaMap orig = new LazyDynaMap(new TreeMap<String, Object>());
        orig.set("indexProp", 0, "indexVal0");
        orig.set("indexProp", 1, "indexVal1");
        // removed other assertion

        final LazyDynaMap newOne = (LazyDynaMap)orig.newInstance();
        final Map<String, Object> newMap = newOne.getMap();
        // removed other assertion

        final ArrayList<?> indexProp = (ArrayList<?>)newMap.get("indexProp");
        // removed other assertion
        assertEquals("Index prop size", 0, indexProp.size());
    }

}