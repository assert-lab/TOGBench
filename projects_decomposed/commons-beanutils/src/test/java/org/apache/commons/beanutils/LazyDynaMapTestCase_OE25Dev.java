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
        assertEquals("Check DynaClass name", "TestBean", new LazyDynaMap("TestBean").getName());
    }

    public void testSimpleProperty_1_oe() {

        assertNull("Check Property doesn't exist", dynaMap.getDynaProperty(testProperty));
    }

    public void testSimpleProperty_2_oe() {

        assertNull("Check Value is null", dynaMap.get(testProperty));
    }

    public void testSimpleProperty_3_oe() {


        dynaMap.set(testProperty, testInteger1);
        assertEquals("Check First Value is correct", testInteger1, dynaMap.get(testProperty));
    }

    public void testSimpleProperty_4_oe() {


        dynaMap.set(testProperty, testInteger1);
        assertEquals("Check Property type is correct", Integer.class, dynaMap.getDynaProperty(testProperty).getType());
    }

    public void testSimpleProperty_5_oe() {


        dynaMap.set(testProperty, testInteger1);

        dynaMap.set(testProperty, testInteger2);
        assertEquals("Check Second Value is correct", testInteger2, dynaMap.get(testProperty));
    }

    public void testSimpleProperty_6_oe() {


        dynaMap.set(testProperty, testInteger1);

        dynaMap.set(testProperty, testInteger2);

        dynaMap.set(testProperty, testString1);
        assertEquals("Check Third Value is correct", testString1, dynaMap.get(testProperty));
    }

    public void testSimplePropertyRestricted_1_oe() {

        dynaMap.setRestricted(true);
        assertTrue("Check MutableDynaClass is restricted", dynaMap.isRestricted());
    }

    public void testSimplePropertyRestricted_2_oe() {

        dynaMap.setRestricted(true);

        assertNull("Check Property doesn't exist", dynaMap.getDynaProperty(testProperty));
    }

    public void testSimplePropertyRestricted_3_oe() {

        dynaMap.setRestricted(true);

        assertNull("Check Value is null", dynaMap.get(testProperty));
    }

    public void testMappedPropertyDefault_1_oe() {

        assertNull("Check Mapped Property doesn't exist", dynaMap.getDynaProperty(testProperty));
    }

    public void testMappedPropertyDefault_2_oe() {

        assertNull("Check Map is null", dynaMap.get(testProperty));
    }

    public void testMappedPropertyDefault_3_oe() {

        assertNull("Check Mapped Value is null", dynaMap.get(testProperty, testKey));
    }

    public void testMappedPropertyDefault_4_oe() {


        dynaMap.set(testProperty, testKey, testInteger1);
        assertEquals("Check Mapped Property exists", HashMap.class, dynaMap.get(testProperty).getClass());
    }

    public void testMappedPropertyDefault_5_oe() {


        dynaMap.set(testProperty, testKey, testInteger1);
        assertEquals("Check First Mapped Value is correct(a)", testInteger1, dynaMap.get(testProperty, testKey));
    }

    public void testMappedPropertyDefault_6_oe() {


        dynaMap.set(testProperty, testKey, testInteger1);
        assertEquals("Check First Mapped Value is correct(b)", testInteger1, ((HashMap<?, ?>)dynaMap.get(testProperty)).get(testKey));
    }

    public void testMappedPropertyDefault_7_oe() {


        dynaMap.set(testProperty, testKey, testInteger1);

        dynaMap.set(testProperty, testKey, testInteger2);
        assertEquals("Check Second Mapped Value is correct(a)", testInteger2, dynaMap.get(testProperty, testKey));
    }

    public void testMappedPropertyDefault_8_oe() {


        dynaMap.set(testProperty, testKey, testInteger1);

        dynaMap.set(testProperty, testKey, testInteger2);
        assertEquals("Check Second Mapped Value is correct(b)", testInteger2, ((HashMap<?, ?>)dynaMap.get(testProperty)).get(testKey));
    }

    public void testMappedPropertyTreeMap_1_oe() {

        assertNull("Check Mapped Property doesn't exist", dynaMap.getDynaProperty(testProperty));
    }

    public void testMappedPropertyTreeMap_2_oe() {

        assertNull("Check Map is null", dynaMap.get(testProperty));
    }

    public void testMappedPropertyTreeMap_3_oe() {


        dynaMap.add(testProperty, TreeMap.class);
        assertTrue("Check Property is mapped", dynaMap.getDynaProperty(testProperty).isMapped());
    }

    public void testMappedPropertyTreeMap_4_oe() {


        dynaMap.add(testProperty, TreeMap.class);
        assertEquals("Check Property is correct type", TreeMap.class, dynaMap.getDynaProperty(testProperty).getType());
    }

    public void testMappedPropertyTreeMap_5_oe() {


        dynaMap.add(testProperty, TreeMap.class);
        assertEquals("Check Mapped Property now exists", TreeMap.class, dynaMap.get(testProperty).getClass());
    }

    public void testMappedPropertyTreeMap_6_oe() {


        dynaMap.add(testProperty, TreeMap.class);

        dynaMap.set(testProperty, testKey, testInteger1);
        assertEquals("Check Mapped Property exists", TreeMap.class, dynaMap.get(testProperty).getClass());
    }

    public void testMappedPropertyTreeMap_7_oe() {


        dynaMap.add(testProperty, TreeMap.class);

        dynaMap.set(testProperty, testKey, testInteger1);
        assertEquals("Check First Mapped Value is correct(a)", testInteger1, dynaMap.get(testProperty, testKey));
    }

    public void testMappedPropertyTreeMap_8_oe() {


        dynaMap.add(testProperty, TreeMap.class);

        dynaMap.set(testProperty, testKey, testInteger1);
        assertEquals("Check First Mapped Value is correct(b)", testInteger1, ((TreeMap<?, ?>)dynaMap.get(testProperty)).get(testKey));
    }

    public void testMappedPropertyTreeMap_9_oe() {


        dynaMap.add(testProperty, TreeMap.class);

        dynaMap.set(testProperty, testKey, testInteger1);

        dynaMap.set(testProperty, testKey, testInteger2);
        assertEquals("Check Second Mapped Value is correct(a)", testInteger2, dynaMap.get(testProperty, testKey));
    }

    public void testMappedPropertyTreeMap_10_oe() {


        dynaMap.add(testProperty, TreeMap.class);

        dynaMap.set(testProperty, testKey, testInteger1);

        dynaMap.set(testProperty, testKey, testInteger2);
        assertEquals("Check Second Mapped Value is correct(b)", testInteger2, ((TreeMap<?, ?>)dynaMap.get(testProperty)).get(testKey));
    }

    public void testMappedPropertyUtils_1_oe() {

        dynaMap.setReturnNull(false);

        assertFalse("Check Mapped Property doesn't exist", dynaMap.isDynaProperty(testProperty));
    }

    public void testMappedPropertyUtils_2_oe() {

        dynaMap.setReturnNull(false);

        assertNull("Check Map is null", dynaMap.get(testProperty));
    }

    public void testMappedPropertyUtils_3_oe() {

        dynaMap.setReturnNull(false);

        assertNull("Check Mapped Value is null", dynaMap.get(testProperty, testKey));
    }

    public void testMappedPropertyUtils_6_oe() {

        dynaMap.setReturnNull(false);


        try {
          PropertyUtils.setProperty(dynaMap, testProperty+"("+testKey+")", testString1);
        }
        catch (final NoSuchMethodException ex) {
        }
        catch (final InvocationTargetException ex) {
        }
        catch (final IllegalAccessException ex) {
            fail("testIndexedPropertyUtils threw "+ex);
    }
    }

    public void testMappedPropertyUtils_7_oe() {

        dynaMap.setReturnNull(false);


        try {
          PropertyUtils.setProperty(dynaMap, testProperty+"("+testKey+")", testString1);
        }
        catch (final NoSuchMethodException ex) {
        }
        catch (final InvocationTargetException ex) {
        }
        catch (final IllegalAccessException ex) {
        }

        assertEquals("Check Mapped Bean Value is correct", testString1, dynaMap.get(testProperty, testKey));
    }

    public void testMappedPropertyRestricted_1_oe() {

        dynaMap.setRestricted(true);
        assertTrue("Check MutableDynaClass is restricted", dynaMap.isRestricted());
    }

    public void testMappedPropertyRestricted_2_oe() {

        dynaMap.setRestricted(true);

        assertNull("Check Property doesn't exist", dynaMap.getDynaProperty(testProperty));
    }

    public void testMappedPropertyRestricted_3_oe() {

        dynaMap.setRestricted(true);

        assertNull("Check Value is null", dynaMap.get(testProperty));
    }

    public void testMappedInvalidType_1_oe() {
        dynaMap.set(testProperty, new Integer(1));
        assertFalse("Check Property is not mapped", dynaMap.getDynaProperty(testProperty).isMapped());
    }

    public void testIndexedPropertyDefault_1_oe() {

        int index = 3;

        assertNull("Check Indexed Property doesn't exist", dynaMap.getDynaProperty(testProperty));
    }

    public void testIndexedPropertyDefault_2_oe() {

        int index = 3;

        assertNull("Check Indexed Property is null", dynaMap.get(testProperty));
    }

    public void testIndexedPropertyDefault_3_oe() {

        int index = 3;

        assertNull("Check Indexed value is null", dynaMap.get(testProperty, index));
    }

    public void testIndexedPropertyDefault_4_oe() {

        int index = 3;


        dynaMap.set(testProperty, index, testInteger1);
        assertNotNull("Check Indexed Property is not null", dynaMap.get(testProperty));
    }

    public void testIndexedPropertyDefault_5_oe() {

        int index = 3;


        dynaMap.set(testProperty, index, testInteger1);
        assertEquals("Check Indexed Property is correct type", ArrayList.class, dynaMap.get(testProperty).getClass());
    }

    public void testIndexedPropertyDefault_6_oe() {

        int index = 3;


        dynaMap.set(testProperty, index, testInteger1);
        assertEquals("Check First Indexed Value is correct", testInteger1, dynaMap.get(testProperty, index));
    }

    public void testIndexedPropertyDefault_7_oe() {

        int index = 3;


        dynaMap.set(testProperty, index, testInteger1);
        assertEquals("Check First Array length is correct", new Integer(index+1),  new Integer(((ArrayList<?>)dynaMap.get(testProperty)).size()));
    }

    public void testIndexedPropertyDefault_8_oe() {

        int index = 3;


        dynaMap.set(testProperty, index, testInteger1);

        index = index + 2;
        dynaMap.set(testProperty, index, testString1);
        assertEquals("Check Second Indexed Value is correct", testString1, dynaMap.get(testProperty, index));
    }

    public void testIndexedPropertyDefault_9_oe() {

        int index = 3;


        dynaMap.set(testProperty, index, testInteger1);

        index = index + 2;
        dynaMap.set(testProperty, index, testString1);
        assertEquals("Check Second Array length is correct", new Integer(index+1),  new Integer(((ArrayList<?>)dynaMap.get(testProperty)).size()));
    }

    public void testIndexedLinkedList_1_oe() {

        int   index     = 3;

        assertNull("Check Indexed Property doesn't exist", dynaMap.getDynaProperty(testProperty));
    }

    public void testIndexedLinkedList_2_oe() {

        int   index     = 3;

        assertNull("Check Indexed Property is null", dynaMap.get(testProperty));
    }

    public void testIndexedLinkedList_3_oe() {

        int   index     = 3;


        dynaMap.add(testProperty, LinkedList.class);
        assertTrue("Check Property is indexed", dynaMap.getDynaProperty(testProperty).isIndexed());
    }

    public void testIndexedLinkedList_4_oe() {

        int   index     = 3;


        dynaMap.add(testProperty, LinkedList.class);
        assertEquals("Check Property is correct type", LinkedList.class, dynaMap.getDynaProperty(testProperty).getType());
    }

    public void testIndexedLinkedList_5_oe() {

        int   index     = 3;


        dynaMap.add(testProperty, LinkedList.class);
        assertEquals("Check Indexed Property now exists", LinkedList.class, dynaMap.get(testProperty).getClass());
    }

    public void testIndexedLinkedList_6_oe() {

        int   index     = 3;


        dynaMap.add(testProperty, LinkedList.class);

        dynaMap.set(testProperty, index, testString1);
        assertEquals("Check Property type is correct", LinkedList.class, dynaMap.get(testProperty).getClass());
    }

    public void testIndexedLinkedList_7_oe() {

        int   index     = 3;


        dynaMap.add(testProperty, LinkedList.class);

        dynaMap.set(testProperty, index, testString1);
        assertEquals("Check First Indexed Value is correct", testString1, dynaMap.get(testProperty, index));
    }

    public void testIndexedLinkedList_8_oe() {

        int   index     = 3;


        dynaMap.add(testProperty, LinkedList.class);

        dynaMap.set(testProperty, index, testString1);
        assertEquals("Check First Array length is correct", new Integer(index+1),  new Integer(((LinkedList<?>)dynaMap.get(testProperty)).size()));
    }

    public void testIndexedLinkedList_9_oe() {

        int   index     = 3;


        dynaMap.add(testProperty, LinkedList.class);

        dynaMap.set(testProperty, index, testString1);

        index = index + 2;
        dynaMap.set(testProperty, index, testInteger1);
        assertEquals("Check Second Indexed Value is correct", testInteger1, dynaMap.get(testProperty, index));
    }

    public void testIndexedLinkedList_10_oe() {

        int   index     = 3;


        dynaMap.add(testProperty, LinkedList.class);

        dynaMap.set(testProperty, index, testString1);

        index = index + 2;
        dynaMap.set(testProperty, index, testInteger1);
        assertEquals("Check Second Array length is correct", new Integer(index+1),  new Integer(((LinkedList<?>)dynaMap.get(testProperty)).size()));
    }

    public void testIndexedPrimitiveArray_1_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];

        assertNull("Check Indexed Property doesn't exist", dynaMap.getDynaProperty(testProperty));
    }

    public void testIndexedPrimitiveArray_2_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];

        assertNull("Check Indexed Property is null", dynaMap.get(testProperty));
    }

    public void testIndexedPrimitiveArray_3_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];


        dynaMap.add(testProperty, primitiveArray.getClass());
        assertEquals("Check Indexed Property exists", primitiveArray.getClass(), dynaMap.getDynaProperty(testProperty).getType());
    }

    public void testIndexedPrimitiveArray_4_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];


        dynaMap.add(testProperty, primitiveArray.getClass());
        assertTrue("Check Indexed Property exists", dynaMap.get(testProperty).getClass().isInstance(primitiveArray));
    }

    public void testIndexedPrimitiveArray_5_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];


        dynaMap.add(testProperty, primitiveArray.getClass());

        dynaMap.set(testProperty, index, testInteger1);
        assertNotNull("Check Indexed Property is not null", dynaMap.get(testProperty));
    }

    public void testIndexedPrimitiveArray_6_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];


        dynaMap.add(testProperty, primitiveArray.getClass());

        dynaMap.set(testProperty, index, testInteger1);
        assertEquals("Check Indexed Property is correct type", primitiveArray.getClass(), dynaMap.get(testProperty).getClass());
    }

    public void testIndexedPrimitiveArray_7_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];


        dynaMap.add(testProperty, primitiveArray.getClass());

        dynaMap.set(testProperty, index, testInteger1);
        assertEquals("Check First Indexed Value is correct(a)", testInteger1, dynaMap.get(testProperty, index));
    }

    public void testIndexedPrimitiveArray_8_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];


        dynaMap.add(testProperty, primitiveArray.getClass());

        dynaMap.set(testProperty, index, testInteger1);
        assertEquals("Check First Indexed Value is correct(b)", testInteger1, new Integer(((int[])dynaMap.get(testProperty))[index]));
    }

    public void testIndexedPrimitiveArray_9_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];


        dynaMap.add(testProperty, primitiveArray.getClass());

        dynaMap.set(testProperty, index, testInteger1);
        assertEquals("Check Array length is correct", new Integer(index+1),  new Integer(((int[])dynaMap.get(testProperty)).length));
    }

    public void testIndexedPrimitiveArray_10_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];


        dynaMap.add(testProperty, primitiveArray.getClass());

        dynaMap.set(testProperty, index, testInteger1);

        index = index + 2;
        dynaMap.set(testProperty, index, testInteger2);
        assertEquals("Check Second Indexed Value is correct(a)", testInteger2, dynaMap.get(testProperty, index));
    }

    public void testIndexedPrimitiveArray_11_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];


        dynaMap.add(testProperty, primitiveArray.getClass());

        dynaMap.set(testProperty, index, testInteger1);

        index = index + 2;
        dynaMap.set(testProperty, index, testInteger2);
        assertEquals("Check Second Indexed Value is correct(b)", testInteger2, new Integer(((int[])dynaMap.get(testProperty))[index]));
    }

    public void testIndexedPrimitiveArray_12_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];


        dynaMap.add(testProperty, primitiveArray.getClass());

        dynaMap.set(testProperty, index, testInteger1);

        index = index + 2;
        dynaMap.set(testProperty, index, testInteger2);
        assertEquals("Check Second Array length is correct", new Integer(index+1),  new Integer(((int[])dynaMap.get(testProperty)).length));
    }

    public void testIndexedObjectArray_1_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];

        assertNull("Check Indexed Property doesn't exist", dynaMap.getDynaProperty(testProperty));
    }

    public void testIndexedObjectArray_2_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];

        assertNull("Check Indexed Property is null", dynaMap.get(testProperty));
    }

    public void testIndexedObjectArray_3_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];


        dynaMap.add(testProperty, objectArray.getClass());
        assertEquals("Check Indexed Property exists", objectArray.getClass(), dynaMap.getDynaProperty(testProperty).getType());
    }

    public void testIndexedObjectArray_4_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];


        dynaMap.add(testProperty, objectArray.getClass());
        assertTrue("Check Indexed Property exists", dynaMap.get(testProperty).getClass().isInstance(objectArray));
    }

    public void testIndexedObjectArray_5_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];


        dynaMap.add(testProperty, objectArray.getClass());

        dynaMap.set(testProperty, index, testString1);
        assertNotNull("Check Indexed Property is not null", dynaMap.get(testProperty));
    }

    public void testIndexedObjectArray_6_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];


        dynaMap.add(testProperty, objectArray.getClass());

        dynaMap.set(testProperty, index, testString1);
        assertEquals("Check Indexed Property is correct type", objectArray.getClass(), dynaMap.get(testProperty).getClass());
    }

    public void testIndexedObjectArray_7_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];


        dynaMap.add(testProperty, objectArray.getClass());

        dynaMap.set(testProperty, index, testString1);
        assertEquals("Check First Indexed Value is correct(a)", testString1, dynaMap.get(testProperty, index));
    }

    public void testIndexedObjectArray_8_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];


        dynaMap.add(testProperty, objectArray.getClass());

        dynaMap.set(testProperty, index, testString1);
        assertEquals("Check First Indexed Value is correct(b)", testString1, ((String[])dynaMap.get(testProperty))[index]);
    }

    public void testIndexedObjectArray_9_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];


        dynaMap.add(testProperty, objectArray.getClass());

        dynaMap.set(testProperty, index, testString1);
        assertEquals("Check Array length is correct", new Integer(index+1),  new Integer(((String[])dynaMap.get(testProperty)).length));
    }

    public void testIndexedObjectArray_10_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];


        dynaMap.add(testProperty, objectArray.getClass());

        dynaMap.set(testProperty, index, testString1);

        index = index + 2;
        dynaMap.set(testProperty, index, testString2);
        assertEquals("Check Second Indexed Value is correct(a)", testString2, dynaMap.get(testProperty, index));
    }

    public void testIndexedObjectArray_11_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];


        dynaMap.add(testProperty, objectArray.getClass());

        dynaMap.set(testProperty, index, testString1);

        index = index + 2;
        dynaMap.set(testProperty, index, testString2);
        assertEquals("Check Second Indexed Value is correct(b)", testString2, ((String[])dynaMap.get(testProperty))[index]);
    }

    public void testIndexedObjectArray_12_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];


        dynaMap.add(testProperty, objectArray.getClass());

        dynaMap.set(testProperty, index, testString1);

        index = index + 2;
        dynaMap.set(testProperty, index, testString2);
        assertEquals("Check Second Array length is correct", new Integer(index+1),  new Integer(((String[])dynaMap.get(testProperty)).length));
    }

    public void testIndexedDynaBeanArray_1_oe() {

        final int   index     = 3;
        final Object objectArray = new LazyDynaBean[0];

        assertNull("Check Indexed Property doesn't exist", dynaMap.getDynaProperty(testProperty));
    }

    public void testIndexedDynaBeanArray_2_oe() {

        final int   index     = 3;
        final Object objectArray = new LazyDynaBean[0];

        assertNull("Check Indexed Property is null", dynaMap.get(testProperty));
    }

    public void testIndexedDynaBeanArray_3_oe() {

        final int   index     = 3;
        final Object objectArray = new LazyDynaBean[0];


        dynaMap.add(testProperty, objectArray.getClass());
        assertEquals("Check Indexed Property exists", objectArray.getClass(), dynaMap.getDynaProperty(testProperty).getType());
    }

    public void testIndexedDynaBeanArray_4_oe() {

        final int   index     = 3;
        final Object objectArray = new LazyDynaBean[0];


        dynaMap.add(testProperty, objectArray.getClass());
        assertEquals("Check Indexed Property is correct type", objectArray.getClass(), dynaMap.get(testProperty).getClass());
    }

    public void testIndexedDynaBeanArray_5_oe() {

        final int   index     = 3;
        final Object objectArray = new LazyDynaBean[0];


        dynaMap.add(testProperty, objectArray.getClass());

        for (int i = index; i >= 0; i--) {
            assertEquals("Check Array Components initialized", LazyDynaBean.class, dynaMap.get(testProperty, index).getClass());
    }
    }

    public void testIndexedDynaBeanArray_6_oe() {

        final int   index     = 3;
        final Object objectArray = new LazyDynaBean[0];


        dynaMap.add(testProperty, objectArray.getClass());

        for (int i = index; i >= 0; i--) {
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

        assertFalse("Check Indexed Property doesn't exist", dynaMap.isDynaProperty(testProperty));
    }

    public void testIndexedPropertyUtils_2_oe() {

        final int   index     = 3;
        dynaMap.setReturnNull(false);

        assertNull("Check Indexed Property is null", dynaMap.get(testProperty));
    }

    public void testIndexedPropertyUtils_3_oe() {

        final int   index     = 3;
        dynaMap.setReturnNull(false);

        assertNull("Check Indexed value is null", dynaMap.get(testProperty, index));
    }

    public void testIndexedPropertyUtils_6_oe() {

        final int   index     = 3;
        dynaMap.setReturnNull(false);


        try {
          PropertyUtils.setProperty(dynaMap, testProperty+"["+index+"]", testString1);
        }
        catch (final NoSuchMethodException ex) {
        }
        catch (final InvocationTargetException ex) {
        }
        catch (final IllegalAccessException ex) {
            fail("testIndexedPropertyUtils threw "+ex);
    }
    }

    public void testIndexedPropertyUtils_7_oe() {

        final int   index     = 3;
        dynaMap.setReturnNull(false);


        try {
          PropertyUtils.setProperty(dynaMap, testProperty+"["+index+"]", testString1);
        }
        catch (final NoSuchMethodException ex) {
        }
        catch (final InvocationTargetException ex) {
        }
        catch (final IllegalAccessException ex) {
        }

        assertEquals("Check Indexed Bean Value is correct", testString1, dynaMap.get(testProperty, index));
    }

    public void testIndexedPropertyRestricted_1_oe() {

        final int   index     = 3;

        dynaMap.setRestricted(true);
        assertTrue("Check MutableDynaClass is restricted", dynaMap.isRestricted());
    }

    public void testIndexedPropertyRestricted_2_oe() {

        final int   index     = 3;

        dynaMap.setRestricted(true);

        assertNull("Check Property doesn't exist", dynaMap.getDynaProperty(testProperty));
    }

    public void testIndexedPropertyRestricted_3_oe() {

        final int   index     = 3;

        dynaMap.setRestricted(true);

        assertNull("Check Value is null", dynaMap.get(testProperty));
    }

    public void testIndexedInvalidType_1_oe() {
        final int   index     = 3;
        dynaMap.set(testProperty, "Test String");
        assertFalse("Check Property is not indexed", dynaMap.getDynaProperty(testProperty).isIndexed());
    }

    public void testNewInstance_1_oe() {

        final LazyDynaMap orig = new LazyDynaMap(new TreeMap<String, Object>());
        orig.set("indexProp", 0, "indexVal0");
        orig.set("indexProp", 1, "indexVal1");
        assertEquals("Index prop size", 2, ((List<?>)orig.get("indexProp")).size());
    }

    public void testNewInstance_2_oe() {

        final LazyDynaMap orig = new LazyDynaMap(new TreeMap<String, Object>());
        orig.set("indexProp", 0, "indexVal0");
        orig.set("indexProp", 1, "indexVal1");

        final LazyDynaMap newOne = (LazyDynaMap)orig.newInstance();
        final Map<String, Object> newMap = newOne.getMap();
        assertEquals("Check Map type", TreeMap.class, newMap.getClass());
    }

    public void testNewInstance_3_oe() {

        final LazyDynaMap orig = new LazyDynaMap(new TreeMap<String, Object>());
        orig.set("indexProp", 0, "indexVal0");
        orig.set("indexProp", 1, "indexVal1");

        final LazyDynaMap newOne = (LazyDynaMap)orig.newInstance();
        final Map<String, Object> newMap = newOne.getMap();

        final ArrayList<?> indexProp = (ArrayList<?>)newMap.get("indexProp");
        assertNotNull("Indexed Prop missing", indexProp);
    }

    public void testNewInstance_4_oe() {

        final LazyDynaMap orig = new LazyDynaMap(new TreeMap<String, Object>());
        orig.set("indexProp", 0, "indexVal0");
        orig.set("indexProp", 1, "indexVal1");

        final LazyDynaMap newOne = (LazyDynaMap)orig.newInstance();
        final Map<String, Object> newMap = newOne.getMap();

        final ArrayList<?> indexProp = (ArrayList<?>)newMap.get("indexProp");
        assertEquals("Index prop size", 0, indexProp.size());
    }

}