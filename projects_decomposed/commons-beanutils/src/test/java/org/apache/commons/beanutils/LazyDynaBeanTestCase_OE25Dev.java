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
import java.util.TreeMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * <p>Test Case for the <code>LazyDynaBean</code> implementation class.</p>
 *
 * @version $Id$
 */
public class LazyDynaBeanTestCase_OE25Dev extends TestCase {

    protected LazyDynaBean  bean      = null;
    protected LazyDynaClass dynaClass = null;
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
    public LazyDynaBeanTestCase_OE25Dev(final String name) {
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
        return (new TestSuite(LazyDynaBeanTestCase_OE25Dev.class));
    }

    /**
     * Set up instance variables required by this test case.
     */
    @Override
    public void setUp() throws Exception {
        bean = new LazyDynaBean();
        dynaClass = (LazyDynaClass)bean.getDynaClass();
        dynaClass.setReturnNull(true);
    }

    /**
     * Tear down instance variables required by this test case.
     */
    @Override
    public void tearDown() {
      bean = null;
    }

    // ------------------------------------------------ Individual Test Methods

    /**
     * Test Getting/Setting a Simple Property
     */

    /**
     * Test Getting/Setting a 'null' Property
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

    public void testSimpleProperty_1_oe() {

        assertNull("Check Property doesn't exist", dynaClass.getDynaProperty(testProperty));
    }

    public void testSimpleProperty_2_oe() {

        assertNull("Check Value is null", bean.get(testProperty));
    }

    public void testSimpleProperty_3_oe() {


        bean.set(testProperty, testInteger1);
        assertEquals("Check First Value is correct", testInteger1, bean.get(testProperty));
    }

    public void testSimpleProperty_4_oe() {


        bean.set(testProperty, testInteger1);
        assertEquals("Check Property type is correct", Integer.class, dynaClass.getDynaProperty(testProperty).getType());
    }

    public void testSimpleProperty_5_oe() {


        bean.set(testProperty, testInteger1);

        bean.set(testProperty, testInteger2);
        assertEquals("Check Second Value is correct", testInteger2, bean.get(testProperty));
    }

    public void testNullProperty_1_oe() {

        assertNull("Check Property doesn't exist", dynaClass.getDynaProperty(testProperty));
    }

    public void testNullProperty_2_oe() {

        assertNull("Check Value is null", bean.get(testProperty));
    }

    public void testNullProperty_3_oe() {


        bean.set(testProperty, null);
        assertNull("Check Value is still null", bean.get(testProperty));
    }

    public void testSimplePropertyRestricted_1_oe() {

        dynaClass.setRestricted(true);
        assertTrue("Check MutableDynaClass is restricted", dynaClass.isRestricted());
    }

    public void testSimplePropertyRestricted_2_oe() {

        dynaClass.setRestricted(true);

        assertNull("Check Property doesn't exist", dynaClass.getDynaProperty(testProperty));
    }

    public void testSimplePropertyRestricted_3_oe() {

        dynaClass.setRestricted(true);

        assertNull("Check Value is null", bean.get(testProperty));
    }

    public void testMappedPropertyDefault_1_oe() {

        assertNull("Check Mapped Property doesn't exist", dynaClass.getDynaProperty(testProperty));
    }

    public void testMappedPropertyDefault_2_oe() {

        assertNull("Check Map is null", bean.get(testProperty));
    }

    public void testMappedPropertyDefault_3_oe() {

        assertNull("Check Mapped Value is null", bean.get(testProperty, testKey));
    }

    public void testMappedPropertyDefault_4_oe() {


        bean.set(testProperty, testKey, testInteger1);
        assertEquals("Check Mapped Property exists", HashMap.class, bean.get(testProperty).getClass());
    }

    public void testMappedPropertyDefault_5_oe() {


        bean.set(testProperty, testKey, testInteger1);
        assertEquals("Check First Mapped Value is correct(a)", testInteger1, bean.get(testProperty, testKey));
    }

    public void testMappedPropertyDefault_6_oe() {


        bean.set(testProperty, testKey, testInteger1);
        assertEquals("Check First Mapped Value is correct(b)", testInteger1, ((HashMap<?, ?>)bean.get(testProperty)).get(testKey));
    }

    public void testMappedPropertyDefault_7_oe() {


        bean.set(testProperty, testKey, testInteger1);

        bean.set(testProperty, testKey, testInteger2);
        assertEquals("Check Second Mapped Value is correct(a)", testInteger2, bean.get(testProperty, testKey));
    }

    public void testMappedPropertyDefault_8_oe() {


        bean.set(testProperty, testKey, testInteger1);

        bean.set(testProperty, testKey, testInteger2);
        assertEquals("Check Second Mapped Value is correct(b)", testInteger2, ((HashMap<?, ?>)bean.get(testProperty)).get(testKey));
    }

    public void testMappedPropertyTreeMap_1_oe() {

        assertNull("Check Mapped Property doesn't exist", dynaClass.getDynaProperty(testProperty));
    }

    public void testMappedPropertyTreeMap_2_oe() {


        dynaClass.add(testProperty, TreeMap.class);
        assertTrue("Check Property is mapped", dynaClass.getDynaProperty(testProperty).isMapped());
    }

    public void testMappedPropertyTreeMap_3_oe() {


        dynaClass.add(testProperty, TreeMap.class);
        assertEquals("Check Property is correct type", TreeMap.class, dynaClass.getDynaProperty(testProperty).getType());
    }

    public void testMappedPropertyTreeMap_4_oe() {


        dynaClass.add(testProperty, TreeMap.class);
        assertEquals("Check Mapped Property exists", TreeMap.class, bean.get(testProperty).getClass());
    }

    public void testMappedPropertyTreeMap_5_oe() {


        dynaClass.add(testProperty, TreeMap.class);

        bean.set(testProperty, testKey, testInteger1);
        assertEquals("Check Mapped Property exists", TreeMap.class, bean.get(testProperty).getClass());
    }

    public void testMappedPropertyTreeMap_6_oe() {


        dynaClass.add(testProperty, TreeMap.class);

        bean.set(testProperty, testKey, testInteger1);
        assertEquals("Check First Mapped Value is correct(a)", testInteger1, bean.get(testProperty, testKey));
    }

    public void testMappedPropertyTreeMap_7_oe() {


        dynaClass.add(testProperty, TreeMap.class);

        bean.set(testProperty, testKey, testInteger1);
        assertEquals("Check First Mapped Value is correct(b)", testInteger1, ((TreeMap<?, ?>)bean.get(testProperty)).get(testKey));
    }

    public void testMappedPropertyTreeMap_8_oe() {


        dynaClass.add(testProperty, TreeMap.class);

        bean.set(testProperty, testKey, testInteger1);

        bean.set(testProperty, testKey, testInteger2);
        assertEquals("Check Second Mapped Value is correct(a)", testInteger2, bean.get(testProperty, testKey));
    }

    public void testMappedPropertyTreeMap_9_oe() {


        dynaClass.add(testProperty, TreeMap.class);

        bean.set(testProperty, testKey, testInteger1);

        bean.set(testProperty, testKey, testInteger2);
        assertEquals("Check Second Mapped Value is correct(b)", testInteger2, ((TreeMap<?, ?>)bean.get(testProperty)).get(testKey));
    }

    public void testMappedPropertyUtils_1_oe() {

        dynaClass.setReturnNull(false);

        assertFalse("Check Mapped Property doesn't exist", dynaClass.isDynaProperty(testProperty));
    }

    public void testMappedPropertyUtils_2_oe() {

        dynaClass.setReturnNull(false);

        assertNull("Check Map is null", bean.get(testProperty));
    }

    public void testMappedPropertyUtils_3_oe() {

        dynaClass.setReturnNull(false);

        assertNull("Check Mapped Value is null", bean.get(testProperty, testKey));
    }

    public void testMappedPropertyUtils_6_oe() {

        dynaClass.setReturnNull(false);


        try {
          PropertyUtils.setProperty(bean, testProperty+"("+testKey+")", testString1);
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

        dynaClass.setReturnNull(false);


        try {
          PropertyUtils.setProperty(bean, testProperty+"("+testKey+")", testString1);
        }
        catch (final NoSuchMethodException ex) {
        }
        catch (final InvocationTargetException ex) {
        }
        catch (final IllegalAccessException ex) {
        }

        assertEquals("Check Mapped Bean Value is correct", testString1, bean.get(testProperty, testKey));
    }

    public void testMappedPropertyRestricted_1_oe() {

        dynaClass.setRestricted(true);
        assertTrue("Check MutableDynaClass is restricted", dynaClass.isRestricted());
    }

    public void testMappedPropertyRestricted_2_oe() {

        dynaClass.setRestricted(true);

        assertNull("Check Property doesn't exist", dynaClass.getDynaProperty(testProperty));
    }

    public void testMappedPropertyRestricted_3_oe() {

        dynaClass.setRestricted(true);

        assertNull("Check Value is null", bean.get(testProperty));
    }

    public void testMappedInvalidType_1_oe() {
        dynaClass.add(testProperty, String.class);
        assertFalse("Check Property is not mapped", dynaClass.getDynaProperty(testProperty).isMapped());
    }

    public void testIndexedPropertyDefault_1_oe() {

        int index = 3;

        assertNull("Check Indexed Property doesn't exist", dynaClass.getDynaProperty(testProperty));
    }

    public void testIndexedPropertyDefault_2_oe() {

        int index = 3;

        assertNull("Check Indexed Property is null", bean.get(testProperty));
    }

    public void testIndexedPropertyDefault_3_oe() {

        int index = 3;

        assertNull("Check Indexed value is null", bean.get(testProperty, index));
    }

    public void testIndexedPropertyDefault_4_oe() {

        int index = 3;


        bean.set(testProperty, index, testInteger1);
        assertNotNull("Check Indexed Property is not null", bean.get(testProperty));
    }

    public void testIndexedPropertyDefault_5_oe() {

        int index = 3;


        bean.set(testProperty, index, testInteger1);
        assertEquals("Check Indexed Property is correct type", ArrayList.class, bean.get(testProperty).getClass());
    }

    public void testIndexedPropertyDefault_6_oe() {

        int index = 3;


        bean.set(testProperty, index, testInteger1);
        assertEquals("Check First Indexed Value is correct", testInteger1, bean.get(testProperty, index));
    }

    public void testIndexedPropertyDefault_7_oe() {

        int index = 3;


        bean.set(testProperty, index, testInteger1);
        assertEquals("Check First Array length is correct", new Integer(index+1),  new Integer(((ArrayList<?>)bean.get(testProperty)).size()));
    }

    public void testIndexedPropertyDefault_8_oe() {

        int index = 3;


        bean.set(testProperty, index, testInteger1);

        index = index + 2;
        bean.set(testProperty, index, testString1);
        assertEquals("Check Second Indexed Value is correct", testString1, bean.get(testProperty, index));
    }

    public void testIndexedPropertyDefault_9_oe() {

        int index = 3;


        bean.set(testProperty, index, testInteger1);

        index = index + 2;
        bean.set(testProperty, index, testString1);
        assertEquals("Check Second Array length is correct", new Integer(index+1),  new Integer(((ArrayList<?>)bean.get(testProperty)).size()));
    }

    public void testIndexedLinkedList_1_oe() {

        int   index     = 3;

        assertNull("Check Indexed Property doesn't exist", dynaClass.getDynaProperty(testProperty));
    }

    public void testIndexedLinkedList_2_oe() {

        int   index     = 3;

        assertNull("Check Indexed Property is null", bean.get(testProperty));
    }

    public void testIndexedLinkedList_3_oe() {

        int   index     = 3;


        dynaClass.add(testProperty, LinkedList.class);
        assertTrue("Check Property is indexed", dynaClass.getDynaProperty(testProperty).isIndexed());
    }

    public void testIndexedLinkedList_4_oe() {

        int   index     = 3;


        dynaClass.add(testProperty, LinkedList.class);
        assertEquals("Check Property is correct type", LinkedList.class, dynaClass.getDynaProperty(testProperty).getType());
    }

    public void testIndexedLinkedList_5_oe() {

        int   index     = 3;


        dynaClass.add(testProperty, LinkedList.class);
        assertEquals("Check Property type is correct", LinkedList.class, bean.get(testProperty).getClass());
    }

    public void testIndexedLinkedList_6_oe() {

        int   index     = 3;


        dynaClass.add(testProperty, LinkedList.class);

        bean.set(testProperty, index, testString1);
        assertEquals("Check Property type is correct", LinkedList.class, bean.get(testProperty).getClass());
    }

    public void testIndexedLinkedList_7_oe() {

        int   index     = 3;


        dynaClass.add(testProperty, LinkedList.class);

        bean.set(testProperty, index, testString1);
        assertEquals("Check First Indexed Value is correct", testString1, bean.get(testProperty, index));
    }

    public void testIndexedLinkedList_8_oe() {

        int   index     = 3;


        dynaClass.add(testProperty, LinkedList.class);

        bean.set(testProperty, index, testString1);
        assertEquals("Check First Array length is correct", new Integer(index+1),  new Integer(((LinkedList<?>)bean.get(testProperty)).size()));
    }

    public void testIndexedLinkedList_9_oe() {

        int   index     = 3;


        dynaClass.add(testProperty, LinkedList.class);

        bean.set(testProperty, index, testString1);

        index = index + 2;
        bean.set(testProperty, index, testInteger1);
        assertEquals("Check Second Indexed Value is correct", testInteger1, bean.get(testProperty, index));
    }

    public void testIndexedLinkedList_10_oe() {

        int   index     = 3;


        dynaClass.add(testProperty, LinkedList.class);

        bean.set(testProperty, index, testString1);

        index = index + 2;
        bean.set(testProperty, index, testInteger1);
        assertEquals("Check Second Array length is correct", new Integer(index+1),  new Integer(((LinkedList<?>)bean.get(testProperty)).size()));
    }

    public void testIndexedPrimitiveArray_1_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];

        assertNull("Check Indexed Property doesn't exist", dynaClass.getDynaProperty(testProperty));
    }

    public void testIndexedPrimitiveArray_2_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];

        assertNull("Check Indexed Property is null", bean.get(testProperty));
    }

    public void testIndexedPrimitiveArray_3_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];


        dynaClass.add(testProperty, primitiveArray.getClass());
        assertEquals("Check Indexed Property exists", primitiveArray.getClass(), dynaClass.getDynaProperty(testProperty).getType());
    }

    public void testIndexedPrimitiveArray_4_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];


        dynaClass.add(testProperty, primitiveArray.getClass());
        assertEquals("Check Indexed Property is correct type", primitiveArray.getClass(), bean.get(testProperty).getClass());
    }

    public void testIndexedPrimitiveArray_5_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];


        dynaClass.add(testProperty, primitiveArray.getClass());

        bean.set(testProperty, index, testInteger1);
        assertNotNull("Check Indexed Property is not null", bean.get(testProperty));
    }

    public void testIndexedPrimitiveArray_6_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];


        dynaClass.add(testProperty, primitiveArray.getClass());

        bean.set(testProperty, index, testInteger1);
        assertEquals("Check Indexed Property is correct type", primitiveArray.getClass(), bean.get(testProperty).getClass());
    }

    public void testIndexedPrimitiveArray_7_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];


        dynaClass.add(testProperty, primitiveArray.getClass());

        bean.set(testProperty, index, testInteger1);
        assertEquals("Check First Indexed Value is correct(a)", testInteger1, bean.get(testProperty, index));
    }

    public void testIndexedPrimitiveArray_8_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];


        dynaClass.add(testProperty, primitiveArray.getClass());

        bean.set(testProperty, index, testInteger1);
        assertEquals("Check First Indexed Value is correct(b)", testInteger1, new Integer(((int[])bean.get(testProperty))[index]));
    }

    public void testIndexedPrimitiveArray_9_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];


        dynaClass.add(testProperty, primitiveArray.getClass());

        bean.set(testProperty, index, testInteger1);
        assertEquals("Check Array length is correct", new Integer(index+1),  new Integer(((int[])bean.get(testProperty)).length));
    }

    public void testIndexedPrimitiveArray_10_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];


        dynaClass.add(testProperty, primitiveArray.getClass());

        bean.set(testProperty, index, testInteger1);

        index = index + 2;
        bean.set(testProperty, index, testInteger2);
        assertEquals("Check Second Indexed Value is correct(a)", testInteger2, bean.get(testProperty, index));
    }

    public void testIndexedPrimitiveArray_11_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];


        dynaClass.add(testProperty, primitiveArray.getClass());

        bean.set(testProperty, index, testInteger1);

        index = index + 2;
        bean.set(testProperty, index, testInteger2);
        assertEquals("Check Second Indexed Value is correct(b)", testInteger2, new Integer(((int[])bean.get(testProperty))[index]));
    }

    public void testIndexedPrimitiveArray_12_oe() {

        int   index     = 3;
        final int[] primitiveArray = new int[0];


        dynaClass.add(testProperty, primitiveArray.getClass());

        bean.set(testProperty, index, testInteger1);

        index = index + 2;
        bean.set(testProperty, index, testInteger2);
        assertEquals("Check Second Array length is correct", new Integer(index+1),  new Integer(((int[])bean.get(testProperty)).length));
    }

    public void testIndexedObjectArray_1_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];

        assertNull("Check Indexed Property doesn't exist", dynaClass.getDynaProperty(testProperty));
    }

    public void testIndexedObjectArray_2_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];

        assertNull("Check Indexed Property is null", bean.get(testProperty));
    }

    public void testIndexedObjectArray_3_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];


        dynaClass.add(testProperty, objectArray.getClass());
        assertEquals("Check Indexed Property exists", objectArray.getClass(), dynaClass.getDynaProperty(testProperty).getType());
    }

    public void testIndexedObjectArray_4_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];


        dynaClass.add(testProperty, objectArray.getClass());
        assertEquals("Check Indexed Property is correct type", objectArray.getClass(), bean.get(testProperty).getClass());
    }

    public void testIndexedObjectArray_5_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];


        dynaClass.add(testProperty, objectArray.getClass());

        bean.set(testProperty, index, testString1);
        assertNotNull("Check Indexed Property is not null", bean.get(testProperty));
    }

    public void testIndexedObjectArray_6_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];


        dynaClass.add(testProperty, objectArray.getClass());

        bean.set(testProperty, index, testString1);
        assertEquals("Check Indexed Property is correct type", objectArray.getClass(), bean.get(testProperty).getClass());
    }

    public void testIndexedObjectArray_7_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];


        dynaClass.add(testProperty, objectArray.getClass());

        bean.set(testProperty, index, testString1);
        assertEquals("Check First Indexed Value is correct(a)", testString1, bean.get(testProperty, index));
    }

    public void testIndexedObjectArray_8_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];


        dynaClass.add(testProperty, objectArray.getClass());

        bean.set(testProperty, index, testString1);
        assertEquals("Check First Indexed Value is correct(b)", testString1, ((String[])bean.get(testProperty))[index]);
    }

    public void testIndexedObjectArray_9_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];


        dynaClass.add(testProperty, objectArray.getClass());

        bean.set(testProperty, index, testString1);
        assertEquals("Check Array length is correct", new Integer(index+1),  new Integer(((String[])bean.get(testProperty)).length));
    }

    public void testIndexedObjectArray_10_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];


        dynaClass.add(testProperty, objectArray.getClass());

        bean.set(testProperty, index, testString1);

        index = index + 2;
        bean.set(testProperty, index, testString2);
        assertEquals("Check Second Indexed Value is correct(a)", testString2, bean.get(testProperty, index));
    }

    public void testIndexedObjectArray_11_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];


        dynaClass.add(testProperty, objectArray.getClass());

        bean.set(testProperty, index, testString1);

        index = index + 2;
        bean.set(testProperty, index, testString2);
        assertEquals("Check Second Indexed Value is correct(b)", testString2, ((String[])bean.get(testProperty))[index]);
    }

    public void testIndexedObjectArray_12_oe() {

        int   index     = 3;
        final Object objectArray = new String[0];


        dynaClass.add(testProperty, objectArray.getClass());

        bean.set(testProperty, index, testString1);

        index = index + 2;
        bean.set(testProperty, index, testString2);
        assertEquals("Check Second Array length is correct", new Integer(index+1),  new Integer(((String[])bean.get(testProperty)).length));
    }

    public void testIndexedDynaBeanArray_1_oe() {

        final int   index     = 3;
        final Object objectArray = new LazyDynaMap[0];

        assertNull("Check Indexed Property doesn't exist", dynaClass.getDynaProperty(testProperty));
    }

    public void testIndexedDynaBeanArray_2_oe() {

        final int   index     = 3;
        final Object objectArray = new LazyDynaMap[0];

        assertNull("Check Indexed Property is null", bean.get(testProperty));
    }

    public void testIndexedDynaBeanArray_3_oe() {

        final int   index     = 3;
        final Object objectArray = new LazyDynaMap[0];


        dynaClass.add(testProperty, objectArray.getClass());
        assertEquals("Check Indexed Property exists", objectArray.getClass(), dynaClass.getDynaProperty(testProperty).getType());
    }

    public void testIndexedDynaBeanArray_4_oe() {

        final int   index     = 3;
        final Object objectArray = new LazyDynaMap[0];


        dynaClass.add(testProperty, objectArray.getClass());
        assertEquals("Check Indexed Property is correct type", objectArray.getClass(), bean.get(testProperty).getClass());
    }

    public void testIndexedDynaBeanArray_5_oe() {

        final int   index     = 3;
        final Object objectArray = new LazyDynaMap[0];


        dynaClass.add(testProperty, objectArray.getClass());

        for (int i = index; i >= 0; i--) {
            assertEquals("Check Array Components initialized", LazyDynaMap.class, bean.get(testProperty, index).getClass());
    }
    }

    public void testIndexedDynaBeanArray_6_oe() {

        final int   index     = 3;
        final Object objectArray = new LazyDynaMap[0];


        dynaClass.add(testProperty, objectArray.getClass());

        for (int i = index; i >= 0; i--) {
        }

        dynaClass.add(testPropertyB, objectArray.getClass());
        final LazyDynaMap newMap = new LazyDynaMap();
        newMap.set(testPropertyB, testString2);
        bean.set(testPropertyA, index, newMap);
        assertEquals("Check Indexed Value is correct(a)", testString2, ((DynaBean)bean.get(testPropertyA, index)).get(testPropertyB));
    }

    public void testIndexedPropertyUtils_1_oe() {

        final int   index     = 3;
        dynaClass.setReturnNull(false);

        assertFalse("Check Indexed Property doesn't exist", dynaClass.isDynaProperty(testProperty));
    }

    public void testIndexedPropertyUtils_2_oe() {

        final int   index     = 3;
        dynaClass.setReturnNull(false);

        assertNull("Check Indexed Property is null", bean.get(testProperty));
    }

    public void testIndexedPropertyUtils_3_oe() {

        final int   index     = 3;
        dynaClass.setReturnNull(false);

        assertNull("Check Indexed value is null", bean.get(testProperty, index));
    }

    public void testIndexedPropertyUtils_6_oe() {

        final int   index     = 3;
        dynaClass.setReturnNull(false);


        try {
          PropertyUtils.setProperty(bean, testProperty+"["+index+"]", testString1);
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
        dynaClass.setReturnNull(false);


        try {
          PropertyUtils.setProperty(bean, testProperty+"["+index+"]", testString1);
        }
        catch (final NoSuchMethodException ex) {
        }
        catch (final InvocationTargetException ex) {
        }
        catch (final IllegalAccessException ex) {
        }

        assertEquals("Check Indexed Bean Value is correct", testString1, bean.get(testProperty, index));
    }

    public void testIndexedPropertyRestricted_1_oe() {

        final int   index     = 3;

        dynaClass.setRestricted(true);
        assertTrue("Check MutableDynaClass is restricted", dynaClass.isRestricted());
    }

    public void testIndexedPropertyRestricted_2_oe() {

        final int   index     = 3;

        dynaClass.setRestricted(true);

        assertNull("Check Property doesn't exist", dynaClass.getDynaProperty(testProperty));
    }

    public void testIndexedPropertyRestricted_3_oe() {

        final int   index     = 3;

        dynaClass.setRestricted(true);

        assertNull("Check Value is null", bean.get(testProperty));
    }

    public void testIndexedInvalidType_1_oe() {
        final int   index     = 3;
        dynaClass.add(testProperty, String.class);
        assertFalse("Check Property is not indexed", dynaClass.getDynaProperty(testProperty).isIndexed());
    }

}