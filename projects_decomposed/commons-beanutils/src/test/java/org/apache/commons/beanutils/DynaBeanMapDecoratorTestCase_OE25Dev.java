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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * <p>Test Case for the <code>DynaBeanMapDecorator</code> implementation class.</p>
 *
 * @version $Id$
 */
@SuppressWarnings("deprecation")
public class DynaBeanMapDecoratorTestCase_OE25Dev extends TestCase {

    private static final DynaProperty stringProp = new DynaProperty("stringProp", String.class);
    private static final DynaProperty nullProp   = new DynaProperty("nullProp",   String.class);
    private static final DynaProperty intProp    = new DynaProperty("intProp",    Integer.class);
    private static final DynaProperty dateProp   = new DynaProperty("dateProp",   Date.class);
    private static final DynaProperty mapProp    = new DynaProperty("mapProp",    Map.class);
    private static final DynaProperty[] properties = new DynaProperty[] {
                      stringProp, nullProp, intProp, dateProp, mapProp};
    private static final DynaClass dynaClass = new BasicDynaClass("testDynaClass", BasicDynaBean.class, properties);

    private static String  stringVal = "somevalue";
    private static Integer intVal    = new Integer(5);
    private static Date    dateVal   = new Date();
    private final Map<Object, Object>     mapVal    = new HashMap<Object, Object>();

    private final Object[] values = new Object[] {stringVal, null, intVal, dateVal, mapVal};

    private BasicDynaBean dynaBean;
    private Map<Object, Object> decoratedMap;
    private Map<Object, Object> modifiableMap;
    private static final Map<Object, Object> emptyMap = new DynaBeanMapDecorator(new BasicDynaBean(new BasicDynaClass()));

    // ---------------------------------------------------------- Constructors

    /**
     * Construct a new instance of this test case.
     *
     * @param name Name of the test case
     */
    public DynaBeanMapDecoratorTestCase_OE25Dev(final String name) {
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
        return (new TestSuite(DynaBeanMapDecoratorTestCase_OE25Dev.class));
    }

    /**
     * Set up instance variables required by this test case.
     */
    @Override
    public void setUp() throws Exception {

        mapVal.clear();
        mapVal.put("key1", "key1Value");
        mapVal.put("key2", "key2Value");

        // Initialize DynaBean and properties
        dynaBean = new BasicDynaBean(dynaClass);
        for (int i = 0; i < properties.length; i++) {
            dynaBean.set(properties[i].getName(), values[i]);
        }

        // Create decorated Maps
        decoratedMap  = new DynaBeanMapDecorator(dynaBean);
        modifiableMap = new DynaBeanMapDecorator(dynaBean, false);

    }

    /**
     * Tear down instance variables required by this test case.
     */
    @Override
    public void tearDown() {
        dynaBean = null;
        decoratedMap = null;
        modifiableMap = null;
    }

    // ------------------------------------------------ Individual Test Methods

    /**
     * Test isReadOnly() method
     */

    /**
     * Test clear() method
     */
    public void testClear() {
        try {
            decoratedMap.clear();
            fail("decoratedMap.clear()");
        } catch(final UnsupportedOperationException ignore) {
            // expected result
        }
        try {
            modifiableMap.clear();
            fail("modifiableMap.clear()");
        } catch(final UnsupportedOperationException ignore) {
            // expected result
        }
    }

    /**
     * Test containsKey() method
     */

    /**
     * Test containsValue() method
     */

    /**
     * Test entrySet() method
     */

    /**
     * Test get() method
     */

    /**
     * Test isEmpty() method
     */

    /**
     * Test keySet() method
     */

    /**
     * Test put() method
     */

    /**
     * Test putAll() method
     */

    /**
     * Test remove() method
     */
    public void testRemove() {
        try {
            decoratedMap.remove(stringProp.getName());
            fail("decoratedMap.remove()");
        } catch(final UnsupportedOperationException ignore) {
            // expected result
        }
        try {
            modifiableMap.remove(stringProp.getName());
            fail("modifiableMap.remove()");
        } catch(final UnsupportedOperationException ignore) {
            // expected result
        }
    }

    /**
     * Test size() method
     */

    /**
     * Test values() method
     */

    /**
     * Check that a Collection is not modifiable
     */
    private <E> void checkUnmodifiable(final String desc, final Collection<E> collection, final E addElem) {
        // Check can't add()
        try {
            collection.add(addElem);
            fail(desc + ".add()");
        } catch(final UnsupportedOperationException ignore) {
            // expected result
        }

        // Check can't addAll()
        final List<E> list = new ArrayList<E>(1);
        list.add(addElem);
        try {
            collection.addAll(list);
            fail(desc + ".addAll()");
        } catch(final UnsupportedOperationException ignore) {
            // expected result
        }

        // Check can't clear()
        try {
            collection.clear();
            fail(desc + ".clear()");
        } catch(final UnsupportedOperationException ignore) {
            // expected result
        }

        // Check can't remove()
        try {
            collection.remove("abc");
            fail(desc + ".remove()");
        } catch(final UnsupportedOperationException ignore) {
            // expected result
        }

        // Check can't removeAll()
        try {
            collection.removeAll(list);
            fail(desc + ".removeAll()");
        } catch(final UnsupportedOperationException ignore) {
            // expected result
        }

        // Check can't retainAll()
        try {
            collection.retainAll(list);
            fail(desc + ".retainAll()");
        } catch(final UnsupportedOperationException ignore) {
            // expected result
        }
    }

public void testIsReadOnly_1_oe() {
        assertTrue("decoratedMap true",   ((DynaBeanMapDecorator)decoratedMap).isReadOnly());
    }

public void testIsReadOnly_2_oe() {
        // removed other assertion
        assertFalse("modifiableMap false", ((DynaBeanMapDecorator)modifiableMap).isReadOnly());
    }

public void testContainsKey_1_oe() {
        assertTrue("decoratedMap true",   decoratedMap.containsKey(stringProp.getName()));
    }

public void testContainsKey_2_oe() {
        // removed other assertion
        assertFalse("decoratedMap false", decoratedMap.containsKey("xyz"));
    }

public void testContainsValue_1_oe() {
        assertTrue("decoratedMap true",   decoratedMap.containsValue(stringVal));
    }

public void testContainsValue_2_oe() {
        // removed other assertion
        assertFalse("decoratedMap false", decoratedMap.containsValue("xyz"));
    }

public void testEntrySet_1_oe() {
        final Set<Map.Entry<Object, Object>> set = modifiableMap.entrySet();

        // Check the Set can't be modified
        final Map<Object, Object> m = new HashMap<Object, Object>();
        m.put("key", "value");
        checkUnmodifiable("entrySet()", set, m.entrySet().iterator().next());

        assertEquals("entrySet size", properties.length, set.size());
    }

public void testEntrySet_2_oe() {
        final Set<Map.Entry<Object, Object>> set = modifiableMap.entrySet();

        // Check the Set can't be modified
        final Map<Object, Object> m = new HashMap<Object, Object>();
        m.put("key", "value");
        checkUnmodifiable("entrySet()", set, m.entrySet().iterator().next());

        // removed other assertion

        final Iterator<Map.Entry<Object, Object>> iterator = set.iterator();
        final List<String> namesList = new ArrayList<String>();
        int i = 0;
        while (iterator.hasNext()) {
            final Map.Entry<Object, Object> entry = iterator.next();
            final String name  = (String)entry.getKey();
            namesList.add(name);
            final Object expectValue = decoratedMap.get(name);
            assertEquals("entrySet("+i+") val", expectValue, entry.getValue());
    }
    }

public void testEntrySet_3_oe() {
        final Set<Map.Entry<Object, Object>> set = modifiableMap.entrySet();

        // Check the Set can't be modified
        final Map<Object, Object> m = new HashMap<Object, Object>();
        m.put("key", "value");
        checkUnmodifiable("entrySet()", set, m.entrySet().iterator().next());

        // removed other assertion

        final Iterator<Map.Entry<Object, Object>> iterator = set.iterator();
        final List<String> namesList = new ArrayList<String>();
        int i = 0;
        while (iterator.hasNext()) {
            final Map.Entry<Object, Object> entry = iterator.next();
            final String name  = (String)entry.getKey();
            namesList.add(name);
            final Object expectValue = decoratedMap.get(name);
            // removed other assertion
            i++;
        }
        for (int j = 0; j < properties.length; j++) {
            final String name = properties[j].getName();
            assertTrue("Check property[" + j + "]", namesList.contains(name));
    }
    }

public void testGet_1_oe() {

        // valid property name
        assertEquals("decoratedMap valid", stringVal, decoratedMap.get(stringProp.getName()));
    }

public void testIsEmpty_1_oe() {
        assertTrue("Empty",      emptyMap.isEmpty());
    }

public void testIsEmpty_2_oe() {
        // removed other assertion
        assertFalse("Not Empty", decoratedMap.isEmpty());
    }

public void testKeySet_1_oe() {
        final Set<Object> set = modifiableMap.keySet();

        // Check the Set can't be modified
        checkUnmodifiable("keySet()", set, "xyz");

        assertEquals("keySet size", properties.length, set.size());
    }

public void testKeySet_2_oe() {
        final Set<Object> set = modifiableMap.keySet();

        // Check the Set can't be modified
        checkUnmodifiable("keySet()", set, "xyz");

        // removed other assertion

        for (int i = 0; i < properties.length; i++) {
            final String name = properties[i].getName();
            assertTrue("Check property[" + i + "]", set.contains(name));
    }
    }

public void testPut_2_oe() {

        final String newValue = "ABC";

        // Test read only
        try {
            decoratedMap.put(stringProp.getName(), newValue);
            // removed other assertion
        } catch(final UnsupportedOperationException ignore) {
            // expected result
        }

        // Test Writable
        assertEquals("modifiableMap put", stringVal, modifiableMap.put(stringProp.getName(), newValue));
    }

public void testPut_3_oe() {

        final String newValue = "ABC";

        // Test read only
        try {
            decoratedMap.put(stringProp.getName(), newValue);
            // removed other assertion
        } catch(final UnsupportedOperationException ignore) {
            // expected result
        }

        // Test Writable
        // removed other assertion
        assertEquals("dynaBean get", newValue, dynaBean.get(stringProp.getName()));
    }

public void testPut_4_oe() {

        final String newValue = "ABC";

        // Test read only
        try {
            decoratedMap.put(stringProp.getName(), newValue);
            // removed other assertion
        } catch(final UnsupportedOperationException ignore) {
            // expected result
        }

        // Test Writable
        // removed other assertion
        // removed other assertion
        assertEquals("modifiableMap get", newValue, modifiableMap.get(stringProp.getName()));
    }

public void testPutAll_2_oe() {

        final String newValue = "ABC";
        final Map<Object, Object> newMap = new HashMap<Object, Object>();
        newMap.put(stringProp.getName(), newValue);

        // Test read only
        try {
            decoratedMap.putAll(newMap);
            // removed other assertion
        } catch(final UnsupportedOperationException ignore) {
            // expected result
        }

        // Test Writable
        assertEquals("before putAll", stringVal, dynaBean.get(stringProp.getName()));
    }

public void testPutAll_3_oe() {

        final String newValue = "ABC";
        final Map<Object, Object> newMap = new HashMap<Object, Object>();
        newMap.put(stringProp.getName(), newValue);

        // Test read only
        try {
            decoratedMap.putAll(newMap);
            // removed other assertion
        } catch(final UnsupportedOperationException ignore) {
            // expected result
        }

        // Test Writable
        // removed other assertion
        modifiableMap.putAll(newMap);
        assertEquals("after putAll",  newValue,  dynaBean.get(stringProp.getName()));
    }

public void testSize_1_oe() {
        assertEquals("Empty", 0, emptyMap.size());
    }

public void testSize_2_oe() {
        // removed other assertion
        assertEquals("Not Empty", properties.length, decoratedMap.size());
    }

public void testValues_1_oe() {
        final Collection<Object> collection = modifiableMap.values();

        // Check the Collection can't be modified
        checkUnmodifiable("values()", collection, "xyz");

        assertEquals("values size", values.length, collection.size());
    }

public void testValues_2_oe() {
        final Collection<Object> collection = modifiableMap.values();

        // Check the Collection can't be modified
        checkUnmodifiable("values()", collection, "xyz");

        // removed other assertion

        // Collection should be ordered in same sequence as properties
        final Iterator<Object> iterator = collection.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            assertEquals("values("+i+")", values[i], iterator.next());
    }
    }

}