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
package org.apache.commons.collections4.map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections4.AbstractObjectTest;
import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.collection.AbstractCollectionTest;
import org.apache.commons.collections4.keyvalue.DefaultMapEntry;
import org.apache.commons.collections4.set.AbstractSetTest;

/**
 * Abstract test class for {@link java.util.Map} methods and contracts.
 * <p>
 * The forces at work here are similar to those in {@link AbstractCollectionTest}.
 * If your class implements the full Map interface, including optional
 * operations, simply extend this class, and implement the
 * {@link #makeObject()} method.
 * <p>
 * On the other hand, if your map implementation is weird, you may have to
 * override one or more of the other protected methods.  They're described
 * below.
 * <p>
 * <b>Entry Population Methods</b>
 * <p>
 * Override these methods if your map requires special entries:
 *
 * <ul>
 * <li>{@link #getSampleKeys()}
 * <li>{@link #getSampleValues()}
 * <li>{@link #getNewSampleValues()}
 * <li>{@link #getOtherKeys()}
 * <li>{@link #getOtherValues()}
 * </ul>
 *
 * <b>Supported Operation Methods</b>
 * <p>
 * Override these methods if your map doesn't support certain operations:
 *
 * <ul>
 * <li> {@link #isPutAddSupported()}
 * <li> {@link #isPutChangeSupported()}
 * <li> {@link #isSetValueSupported()}
 * <li> {@link #isRemoveSupported()}
 * <li> {@link #isGetStructuralModify()}
 * <li> {@link #isAllowDuplicateValues()}
 * <li> {@link #isAllowNullKey()}
 * <li> {@link #isAllowNullValue()}
 * </ul>
 *
 * <b>Fixture Methods</b>
 * <p>
 * For tests on modification operations (puts and removes), fixtures are used
 * to verify that that operation results in correct state for the map and its
 * collection views.  Basically, the modification is performed against your
 * map implementation, and an identical modification is performed against
 * a <I>confirmed</I> map implementation.  A confirmed map implementation is
 * something like <Code>java.util.HashMap</Code>, which is known to conform
 * exactly to the {@link Map} contract.  After the modification takes place
 * on both your map implementation and the confirmed map implementation, the
 * two maps are compared to see if their state is identical.  The comparison
 * also compares the collection views to make sure they're still the same.<P>
 *
 * The upshot of all that is that <I>any</I> test that modifies the map in
 * <I>any</I> way will verify that <I>all</I> of the map's state is still
 * correct, including the state of its collection views.  So for instance
 * if a key is removed by the map's key set's iterator, then the entry set
 * is checked to make sure the key/value pair no longer appears.<P>
 *
 * The {@link #map} field holds an instance of your collection implementation.
 * The {@link #entrySet}, {@link #keySet} and {@link #values} fields hold
 * that map's collection views.  And the {@link #confirmed} field holds
 * an instance of the confirmed collection implementation.  The
 * {@link #resetEmpty()} and {@link #resetFull()} methods set these fields to
 * empty or full maps, so that tests can proceed from a known state.<P>
 *
 * After a modification operation to both {@link #map} and {@link #confirmed},
 * the {@link #verify()} method is invoked to compare the results.  The
 * {@link #verify} method calls separate methods to verify the map and its three
 * collection views ({@link #verifyMap}, {@link #verifyEntrySet},
 * {@link #verifyKeySet}, and {@link #verifyValues}).  You may want to override
 * one of the verification methods to perform additional verifications.  For
 * instance, TestDoubleOrderedMap would want override its
 * {@link #verifyValues()} method to verify that the values are unique and in
 * ascending order.<P>
 *
 * <b>Other Notes</b>
 * <p>
 * If your {@link Map} fails one of these tests by design, you may still use
 * this base set of cases.  Simply override the test case (method) your map
 * fails and/or the methods that define the assumptions used by the test
 * cases.  For example, if your map does not allow duplicate values, override
 * {@link #isAllowDuplicateValues()} and have it return <code>false</code>
 *
 */
public abstract class AbstractMapTest_OE25Dev<K, V> extends AbstractObjectTest {

    /**
     * JDK1.2 has bugs in null handling of Maps, especially HashMap.Entry.toString
     * This avoids nulls for JDK1.2
     */
    private static final boolean JDK12;
    static {
        final String str = System.getProperty("java.version");
        JDK12 = str.startsWith("1.2");
    }

    // These instance variables are initialized with the reset method.
    // Tests for map methods that alter the map (put, putAll, remove)
    // first call reset() to create the map and its views; then perform
    // the modification on the map; perform the same modification on the
    // confirmed; and then call verify() to ensure that the map is equal
    // to the confirmed, that the already-constructed collection views
    // are still equal to the confirmed's collection views.

    /** Map created by reset(). */
    protected Map<K, V> map;

    /** Entry set of map created by reset(). */
    protected Set<Map.Entry<K, V>> entrySet;

    /** Key set of map created by reset(). */
    protected Set<K> keySet;

    /** Values collection of map created by reset(). */
    protected Collection<V> values;

    /** HashMap created by reset(). */
    protected Map<K, V> confirmed;

    /**
     * JUnit constructor.
     *
     * @param testName  the test name
     */
    public AbstractMapTest_OE25Dev(final String testName) {
        super(testName);
    }

    /**
     * Returns true if the maps produced by
     * {@link #makeObject()} and {@link #makeFullMap()}
     * support the <code>put</code> and <code>putAll</code> operations
     * adding new mappings.
     * <p>
     * Default implementation returns true.
     * Override if your collection class does not support put adding.
     */
    public boolean isPutAddSupported() {
        return true;
    }

    /**
     * Returns true if the maps produced by
     * {@link #makeObject()} and {@link #makeFullMap()}
     * support the <code>put</code> and <code>putAll</code> operations
     * changing existing mappings.
     * <p>
     * Default implementation returns true.
     * Override if your collection class does not support put changing.
     */
    public boolean isPutChangeSupported() {
        return true;
    }

    /**
     * Returns true if the maps produced by
     * {@link #makeObject()} and {@link #makeFullMap()}
     * support the <code>setValue</code> operation on entrySet entries.
     * <p>
     * Default implementation returns isPutChangeSupported().
     * Override if your collection class does not support setValue but does
     * support put changing.
     */
    public boolean isSetValueSupported() {
        return isPutChangeSupported();
    }

    /**
     * Returns true if the maps produced by
     * {@link #makeObject()} and {@link #makeFullMap()}
     * support the <code>remove</code> and <code>clear</code> operations.
     * <p>
     * Default implementation returns true.
     * Override if your collection class does not support removal operations.
     */
    public boolean isRemoveSupported() {
        return true;
    }

    /**
     * Returns true if the maps produced by
     * {@link #makeObject()} and {@link #makeFullMap()}
     * can cause structural modification on a get(). The example is LRUMap.
     * <p>
     * Default implementation returns false.
     * Override if your map class structurally modifies on get.
     */
    public boolean isGetStructuralModify() {
        return false;
    }

    /**
     * Returns whether the sub map views of SortedMap are serializable.
     * If the class being tested is based around a TreeMap then you should
     * override and return false as TreeMap has a bug in deserialization.
     *
     * @return false
     */
    public boolean isSubMapViewsSerializable() {
        return true;
    }

    /**
     * Returns true if the maps produced by
     * {@link #makeObject()} and {@link #makeFullMap()}
     * supports null keys.
     * <p>
     * Default implementation returns true.
     * Override if your collection class does not support null keys.
     */
    public boolean isAllowNullKey() {
        return true;
    }

    /**
     * Returns true if the maps produced by
     * {@link #makeObject()} and {@link #makeFullMap()}
     * supports null values.
     * <p>
     * Default implementation returns true.
     * Override if your collection class does not support null values.
     */
    public boolean isAllowNullValue() {
        return true;
    }

    /**
     * Returns true if the maps produced by
     * {@link #makeObject()} and {@link #makeFullMap()}
     * supports duplicate values.
     * <p>
     * Default implementation returns true.
     * Override if your collection class does not support duplicate values.
     */
    public boolean isAllowDuplicateValues() {
        return true;
    }

    /**
     * Returns true if the maps produced by
     * {@link #makeObject()} and {@link #makeFullMap()}
     * provide fail-fast behavior on their various iterators.
     * <p>
     * Default implementation returns true.
     * Override if your collection class does not support fast failure.
     */
    public boolean isFailFastExpected() {
        return true;
    }

    public boolean areEqualElementsDistinguishable() {
        return false;
    }

    /**
     *  Returns the set of keys in the mappings used to test the map.  This
     *  method must return an array with the same length as {@link
     *  #getSampleValues()} and all array elements must be different. The
     *  default implementation constructs a set of String keys, and includes a
     *  single null key if {@link #isAllowNullKey()} returns <code>true</code>.
     */
    @SuppressWarnings("unchecked")
    public K[] getSampleKeys() {
        final Object[] result = new Object[] {
            "blah", "foo", "bar", "baz", "tmp", "gosh", "golly", "gee",
            "hello", "goodbye", "we'll", "see", "you", "all", "again",
            "key",
            "key2",
            isAllowNullKey() && !JDK12 ? null : "nonnullkey"
        };
        return (K[]) result;
    }

    @SuppressWarnings("unchecked")
    public K[] getOtherKeys() {
        return (K[]) getOtherNonNullStringElements();
    }

    @SuppressWarnings("unchecked")
    public V[] getOtherValues() {
        return (V[]) getOtherNonNullStringElements();
    }

    @SuppressWarnings("unchecked")
    protected <E> List<E> getAsList(final Object[] o) {
        final ArrayList<E> result = new ArrayList<>();
        for (final Object element : o) {
            result.add((E) element);
        }
        return result;
    }

    /**
     * Returns a list of string elements suitable for return by
     * {@link #getOtherKeys()} or {@link #getOtherValues}.
     *
     * <p>Override getOtherElements to return the results of this method if your
     * collection does not support heterogenous elements or the null element.
     * </p>
     */
    public Object[] getOtherNonNullStringElements() {
        return new Object[] {
            "For","then","despite",/* of */"space","I","would","be","brought",
            "From","limits","far","remote","where","thou","dost","stay"
        };
    }

    /**
     * Returns the set of values in the mappings used to test the map.  This
     * method must return an array with the same length as
     * {@link #getSampleKeys()}.  The default implementation constructs a set of
     * String values and includes a single null value if
     * {@link #isAllowNullValue()} returns <code>true</code>, and includes
     * two values that are the same if {@link #isAllowDuplicateValues()} returns
     * <code>true</code>.
     */
    @SuppressWarnings("unchecked")
    public V[] getSampleValues() {
        final Object[] result = new Object[] {
            "blahv", "foov", "barv", "bazv", "tmpv", "goshv", "gollyv", "geev",
            "hellov", "goodbyev", "we'llv", "seev", "youv", "allv", "againv",
            isAllowNullValue() && !JDK12 ? null : "nonnullvalue",
            "value",
            isAllowDuplicateValues() ? "value" : "value2",
        };
        return (V[]) result;
    }

    /**
     * Returns a the set of values that can be used to replace the values
     * returned from {@link #getSampleValues()}.  This method must return an
     * array with the same length as {@link #getSampleValues()}.  The values
     * returned from this method should not be the same as those returned from
     * {@link #getSampleValues()}.  The default implementation constructs a
     * set of String values and includes a single null value if
     * {@link #isAllowNullValue()} returns <code>true</code>, and includes two values
     * that are the same if {@link #isAllowDuplicateValues()} returns
     * <code>true</code>.
     */
    @SuppressWarnings("unchecked")
    public V[] getNewSampleValues() {
        final Object[] result = new Object[] {
            isAllowNullValue() && !JDK12 && isAllowDuplicateValues() ? null : "newnonnullvalue",
            "newvalue",
            isAllowDuplicateValues() ? "newvalue" : "newvalue2",
            "newblahv", "newfoov", "newbarv", "newbazv", "newtmpv", "newgoshv",
            "newgollyv", "newgeev", "newhellov", "newgoodbyev", "newwe'llv",
            "newseev", "newyouv", "newallv", "newagainv",
        };
        return (V[]) result;
    }

    /**
     *  Helper method to add all the mappings described by
     * {@link #getSampleKeys()} and {@link #getSampleValues()}.
     */
    public void addSampleMappings(final Map<? super K, ? super V> m) {

        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();

        for (int i = 0; i < keys.length; i++) {
            try {
                m.put(keys[i], values[i]);
            } catch (final NullPointerException exception) {
                assertTrue("NullPointerException only allowed to be thrown " +
                           "if either the key or value is null.",
                           keys[i] == null || values[i] == null);

                assertTrue("NullPointerException on null key, but " +
                           "isAllowNullKey is not overridden to return false.",
                           keys[i] == null || !isAllowNullKey());

                assertTrue("NullPointerException on null value, but " +
                           "isAllowNullValue is not overridden to return false.",
                           values[i] == null || !isAllowNullValue());

                assertTrue("Unknown reason for NullPointer.", false);
            }
        }
        assertEquals("size must reflect number of mappings added.",
                     keys.length, m.size());
    }

    //-----------------------------------------------------------------------
    /**
     * Return a new, empty {@link Map} to be used for testing.
     *
     * @return the map to be tested
     */
    @Override
    public abstract Map<K,V> makeObject();

    /**
     * Return a new, populated map.  The mappings in the map should match the
     * keys and values returned from {@link #getSampleKeys()} and
     * {@link #getSampleValues()}.  The default implementation uses makeEmptyMap()
     * and calls {@link #addSampleMappings} to add all the mappings to the
     * map.
     *
     * @return the map to be tested
     */
    public Map<K, V> makeFullMap() {
        final Map<K, V> m = makeObject();
        addSampleMappings(m);
        return m;
    }

    /**
     * Override to return a map other than HashMap as the confirmed map.
     *
     * @return a map that is known to be valid
     */
    public Map<K, V> makeConfirmedMap() {
        return new HashMap<>();
    }

    /**
     * Creates a new Map Entry that is independent of the first and the map.
     */
    public static <K, V> Map.Entry<K, V> cloneMapEntry(final Map.Entry<K, V> entry) {
        final HashMap<K, V> map = new HashMap<>();
        map.put(entry.getKey(), entry.getValue());
        return map.entrySet().iterator().next();
    }

    /**
     * Gets the compatability version, needed for package access.
     */
    @Override
    public String getCompatibilityVersion() {
        return super.getCompatibilityVersion();
    }

    //-----------------------------------------------------------------------
    /**
     * Test to ensure the test setup is working properly.  This method checks
     * to ensure that the getSampleKeys and getSampleValues methods are
     * returning results that look appropriate.  That is, they both return a
     * non-null array of equal length.  The keys array must not have any
     * duplicate values, and may only contain a (single) null key if
     * isNullKeySupported() returns true.  The values array must only have a null
     * value if useNullValue() is true and may only have duplicate values if
     * isAllowDuplicateValues() returns true.
     */

    // tests begin here.  Each test adds a little bit of tested functionality.
    // Many methods assume previous methods passed.  That is, they do not
    // exhaustively recheck things that have already been checked in a previous
    // test methods.

    /**
     * Test to ensure that makeEmptyMap and makeFull returns a new non-null
     * map with each invocation.
     */

    /**
     * Tests Map.isEmpty()
     */

    /**
     * Tests Map.size()
     */

    /**
     * Tests {@link Map#clear()}.  If the map {@link #isRemoveSupported()}

    public void testSampleMappings_1_oe() {
        final Object[] keys = getSampleKeys();
        final Object[] values = getSampleValues();
        final Object[] newValues = getNewSampleValues();

        assertNotNull("failure in test: Must have keys returned from " + "getSampleKeys.", keys);
    }

    public void testSampleMappings_2_oe() {
        final Object[] keys = getSampleKeys();
        final Object[] values = getSampleValues();
        final Object[] newValues = getNewSampleValues();

        // removed other assertion

        assertNotNull("failure in test: Must have values returned from " + "getSampleValues.", values);
    }

    public void testSampleMappings_3_oe() {
        final Object[] keys = getSampleKeys();
        final Object[] values = getSampleValues();
        final Object[] newValues = getNewSampleValues();

        // removed other assertion

        // removed other assertion

        // verify keys and values have equivalent lengths (in case getSampleX are
        // overridden)
        assertEquals("failure in test: not the same number of sample " + "keys and values.",  keys.length, values.length);
    }

    public void testSampleMappings_4_oe() {
        final Object[] keys = getSampleKeys();
        final Object[] values = getSampleValues();
        final Object[] newValues = getNewSampleValues();

        // removed other assertion

        // removed other assertion

        // verify keys and values have equivalent lengths (in case getSampleX are
        // overridden)
        // removed other assertion

        assertEquals("failure in test: not the same number of values and new values.", values.length, newValues.length);
    }

    public void testSampleMappings_5_oe() {
        final Object[] keys = getSampleKeys();
        final Object[] values = getSampleValues();
        final Object[] newValues = getNewSampleValues();

        // removed other assertion

        // removed other assertion

        // verify keys and values have equivalent lengths (in case getSampleX are
        // overridden)
        // removed other assertion

        // removed other assertion

        // verify there aren't duplicate keys, and check values
        for (int i = 0; i < keys.length - 1; i++) {
            for (int j = i + 1; j < keys.length; j++) {
                assertTrue("failure in test: duplicate null keys.", keys[i] != null || keys[j] != null);
    }
    }
    }

    public void testSampleMappings_6_oe() {
        final Object[] keys = getSampleKeys();
        final Object[] values = getSampleValues();
        final Object[] newValues = getNewSampleValues();

        // removed other assertion

        // removed other assertion

        // verify keys and values have equivalent lengths (in case getSampleX are
        // overridden)
        // removed other assertion

        // removed other assertion

        // verify there aren't duplicate keys, and check values
        for (int i = 0; i < keys.length - 1; i++) {
            for (int j = i + 1; j < keys.length; j++) {
                // removed other assertion
                assertTrue( "failure in test: duplicate non-null key.", keys[i] == null || keys[j] == null || !keys[i].equals(keys[j]) && !keys[j] .equals(keys[i]));
    }
    }
    }

    public void testSampleMappings_7_oe() {
        final Object[] keys = getSampleKeys();
        final Object[] values = getSampleValues();
        final Object[] newValues = getNewSampleValues();

        // removed other assertion

        // removed other assertion

        // verify keys and values have equivalent lengths (in case getSampleX are
        // overridden)
        // removed other assertion

        // removed other assertion

        // verify there aren't duplicate keys, and check values
        for (int i = 0; i < keys.length - 1; i++) {
            for (int j = i + 1; j < keys.length; j++) {
                // removed other assertion
                // removed other assertion
            }
            assertTrue("failure in test: found null key, but isNullKeySupported " + "is false.", keys[i] != null || isAllowNullKey());
    }
    }

    public void testSampleMappings_8_oe() {
        final Object[] keys = getSampleKeys();
        final Object[] values = getSampleValues();
        final Object[] newValues = getNewSampleValues();

        // removed other assertion

        // removed other assertion

        // verify keys and values have equivalent lengths (in case getSampleX are
        // overridden)
        // removed other assertion

        // removed other assertion

        // verify there aren't duplicate keys, and check values
        for (int i = 0; i < keys.length - 1; i++) {
            for (int j = i + 1; j < keys.length; j++) {
                // removed other assertion
                // removed other assertion
            }
            // removed other assertion
            assertTrue( "failure in test: found null value, but isNullValueSupported " + "is false.", values[i] != null || isAllowNullValue());
    }
    }

    public void testSampleMappings_9_oe() {
        final Object[] keys = getSampleKeys();
        final Object[] values = getSampleValues();
        final Object[] newValues = getNewSampleValues();

        // removed other assertion

        // removed other assertion

        // verify keys and values have equivalent lengths (in case getSampleX are
        // overridden)
        // removed other assertion

        // removed other assertion

        // verify there aren't duplicate keys, and check values
        for (int i = 0; i < keys.length - 1; i++) {
            for (int j = i + 1; j < keys.length; j++) {
                // removed other assertion
                // removed other assertion
            }
            // removed other assertion
            // removed other assertion
            assertTrue("failure in test: found null new value, but isNullValueSupported " + "is false.", newValues[i] != null || isAllowNullValue());
    }
    }

    public void testSampleMappings_10_oe() {
        final Object[] keys = getSampleKeys();
        final Object[] values = getSampleValues();
        final Object[] newValues = getNewSampleValues();

        // removed other assertion

        // removed other assertion

        // verify keys and values have equivalent lengths (in case getSampleX are
        // overridden)
        // removed other assertion

        // removed other assertion

        // verify there aren't duplicate keys, and check values
        for (int i = 0; i < keys.length - 1; i++) {
            for (int j = i + 1; j < keys.length; j++) {
                // removed other assertion
                // removed other assertion
            }
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertTrue("failure in test: values should not be the same as new value", values[i] != newValues[i] && (values[i] == null || !values[i].equals(newValues[i])));
    }
    }

    public void testMakeMap_1_oe() {
        final Map<K, V> em = makeObject();
        assertTrue("failure in test: makeEmptyMap must return a non-null map.", em != null);
    }

    public void testMakeMap_2_oe() {
        final Map<K, V> em = makeObject();
        // removed other assertion

        final Map<K, V> em2 = makeObject();
        assertTrue("failure in test: makeEmptyMap must return a non-null map.", em != null);
    }

    public void testMakeMap_3_oe() {
        final Map<K, V> em = makeObject();
        // removed other assertion

        final Map<K, V> em2 = makeObject();
        // removed other assertion

        assertTrue("failure in test: makeEmptyMap must return a new map " + "with each invocation.", em != em2);
    }

    public void testMakeMap_4_oe() {
        final Map<K, V> em = makeObject();
        // removed other assertion

        final Map<K, V> em2 = makeObject();
        // removed other assertion

        // removed other assertion

        final Map<K, V> fm = makeFullMap();
        assertTrue("failure in test: makeFullMap must return a non-null map.", fm != null);
    }

    public void testMakeMap_5_oe() {
        final Map<K, V> em = makeObject();
        // removed other assertion

        final Map<K, V> em2 = makeObject();
        // removed other assertion

        // removed other assertion

        final Map<K, V> fm = makeFullMap();
        // removed other assertion

        final Map<K, V> fm2 = makeFullMap();
        assertTrue("failure in test: makeFullMap must return a non-null map.", fm != null);
    }

    public void testMakeMap_6_oe() {
        final Map<K, V> em = makeObject();
        // removed other assertion

        final Map<K, V> em2 = makeObject();
        // removed other assertion

        // removed other assertion

        final Map<K, V> fm = makeFullMap();
        // removed other assertion

        final Map<K, V> fm2 = makeFullMap();
        // removed other assertion

        assertTrue("failure in test: makeFullMap must return a new map " + "with each invocation.", fm != fm2);
    }

    public void testMapIsEmpty_1_oe() {
        resetEmpty();
        assertEquals("Map.isEmpty() should return true with an empty map", true, getMap().isEmpty());
    }

    public void testMapIsEmpty_2_oe() {
        resetEmpty();
        // removed other assertion
        verify();

        resetFull();
        assertEquals("Map.isEmpty() should return false with a non-empty map", false, getMap().isEmpty());
    }

    public void testMapSize_1_oe() {
        resetEmpty();
        assertEquals("Map.size() should be 0 with an empty map", 0, getMap().size());
    }

    public void testMapSize_2_oe() {
        resetEmpty();
        // removed other assertion
        verify();

        resetFull();
        assertEquals("Map.size() should equal the number of entries " + "in the map", getSampleKeys().length, getMap().size());
    }

    public void testMapContainsKey_1_oe() {
        final Object[] keys = getSampleKeys();

        resetEmpty();
        for (final Object key : keys) {
            assertTrue("Map must not contain key when map is empty", !getMap().containsKey(key));
    }
    }

    public void testMapContainsKey_2_oe() {
        final Object[] keys = getSampleKeys();

        resetEmpty();
        for (final Object key : keys) {
            // removed other assertion
        }
        verify();

        resetFull();
        for (final Object key : keys) {
            assertTrue("Map must contain key for a mapping in the map. " + "Missing: " + key, getMap().containsKey(key));
    }
    }

    public void testMapContainsValue_1_oe() {
        final Object[] values = getSampleValues();

        resetEmpty();
        for (final Object value : values) {
            assertTrue("Empty map must not contain value", !getMap().containsValue(value));
    }
    }

    public void testMapContainsValue_2_oe() {
        final Object[] values = getSampleValues();

        resetEmpty();
        for (final Object value : values) {
            // removed other assertion
        }
        verify();

        resetFull();
        for (final Object value : values) {
            assertTrue("Map must contain value for a mapping in the map.", getMap().containsValue(value));
    }
    }

    public void testMapEquals_1_oe() {
        resetEmpty();
        assertTrue("Empty maps unequal.", getMap().equals(confirmed));
    }

    public void testMapEquals_2_oe() {
        resetEmpty();
        // removed other assertion
        verify();

        resetFull();
        assertTrue("Full maps unequal.", getMap().equals(confirmed));
    }

    public void testMapEquals_3_oe() {
        resetEmpty();
        // removed other assertion
        verify();

        resetFull();
        // removed other assertion
        verify();

        resetFull();
        // modify the HashMap created from the full map and make sure this
        // change results in map.equals() to return false.
        final Iterator<K> iter = confirmed.keySet().iterator();
        iter.next();
        iter.remove();
        assertTrue("Different maps equal.", !getMap().equals(confirmed));
    }

    public void testMapEquals_4_oe() {
        resetEmpty();
        // removed other assertion
        verify();

        resetFull();
        // removed other assertion
        verify();

        resetFull();
        // modify the HashMap created from the full map and make sure this
        // change results in map.equals() to return false.
        final Iterator<K> iter = confirmed.keySet().iterator();
        iter.next();
        iter.remove();
        // removed other assertion

        resetFull();
        assertTrue("equals(null) returned true.", !getMap().equals(null));
    }

    public void testMapEquals_5_oe() {
        resetEmpty();
        // removed other assertion
        verify();

        resetFull();
        // removed other assertion
        verify();

        resetFull();
        // modify the HashMap created from the full map and make sure this
        // change results in map.equals() to return false.
        final Iterator<K> iter = confirmed.keySet().iterator();
        iter.next();
        iter.remove();
        // removed other assertion

        resetFull();
        // removed other assertion
        assertTrue("equals(new Object()) returned true.", !getMap().equals(new Object()));
    }

    public void testMapGet_1_oe() {
        resetEmpty();

        final Object[] keys = getSampleKeys();
        final Object[] values = getSampleValues();

        for (final Object key : keys) {
            assertTrue("Empty map.get() should return null.", getMap().get(key) == null);
    }
    }

    public void testMapGet_2_oe() {
        resetEmpty();

        final Object[] keys = getSampleKeys();
        final Object[] values = getSampleValues();

        for (final Object key : keys) {
            // removed other assertion
        }
        verify();

        resetFull();
        for (int i = 0; i < keys.length; i++) {
            assertEquals("Full map.get() should return value from mapping.", values[i], getMap().get(keys[i]));
    }
    }

    public void testMapHashCode_1_oe() {
        resetEmpty();
        assertTrue("Empty maps have different hashCodes.", getMap().hashCode() == confirmed.hashCode());
    }

    public void testMapHashCode_2_oe() {
        resetEmpty();
        // removed other assertion

        resetFull();
        assertTrue("Equal maps have different hashCodes.", getMap().hashCode() == confirmed.hashCode());
    }

    public void testMapToString_1_oe() {
        resetEmpty();
        assertTrue("Empty map toString() should not return null", getMap().toString() != null);
    }

    public void testMapToString_2_oe() {
        resetEmpty();
        // removed other assertion
        verify();

        resetFull();
        assertTrue("Empty map toString() should not return null", getMap().toString() != null);
    }

    public void testEmptyMapCompatibility_1_oe() throws Exception {
        /**
         * Create canonical objects with this code
        Map map = makeEmptyMap();
        if (!(map instanceof Serializable)) return;

        writeExternalFormToDisk((Serializable) map, getCanonicalEmptyCollectionName(map));
        */

        // test to make sure the canonical form has been preserved
        final Map<K, V> map = makeObject();
        if (map instanceof Serializable && !skipSerializedCanonicalTests() && isTestSerialization()) {
            @SuppressWarnings("unchecked")
            final Map<K, V> map2 = (Map<K, V>) readExternalFormFromDisk(getCanonicalEmptyCollectionName(map));
            assertEquals("Map is empty", 0, map2.size());
    }
    }

    public void testFullMapCompatibility_1_oe() throws Exception {
        /**
         * Create canonical objects with this code
        Map map = makeFullMap();
        if (!(map instanceof Serializable)) return;

        writeExternalFormToDisk((Serializable) map, getCanonicalFullCollectionName(map));
        */

        // test to make sure the canonical form has been preserved
        final Map<K, V> map = makeFullMap();
        if (map instanceof Serializable && !skipSerializedCanonicalTests() && isTestSerialization()) {
            @SuppressWarnings("unchecked")
            final Map<K, V> map2 = (Map<K, V>) readExternalFormFromDisk(getCanonicalFullCollectionName(map));
            assertEquals("Map is the right size", getSampleKeys().length, map2.size());
    }
    }

    public void testMapPut_1_oe() {
        resetEmpty();
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        final V[] newValues = getNewSampleValues();

        if (isPutAddSupported()) {
            for (int i = 0; i < keys.length; i++) {
                final Object o = getMap().put(keys[i], values[i]);
                getConfirmed().put(keys[i], values[i]);
                verify();
                assertTrue("First map.put should return null", o == null);
    }
    }
    }

    public void testMapPut_2_oe() {
        resetEmpty();
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        final V[] newValues = getNewSampleValues();

        if (isPutAddSupported()) {
            for (int i = 0; i < keys.length; i++) {
                final Object o = getMap().put(keys[i], values[i]);
                getConfirmed().put(keys[i], values[i]);
                verify();
                // removed other assertion
                assertTrue("Map should contain key after put", getMap().containsKey(keys[i]));
    }
    }
    }

    public void testMapPut_3_oe() {
        resetEmpty();
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        final V[] newValues = getNewSampleValues();

        if (isPutAddSupported()) {
            for (int i = 0; i < keys.length; i++) {
                final Object o = getMap().put(keys[i], values[i]);
                getConfirmed().put(keys[i], values[i]);
                verify();
                // removed other assertion
                // removed other assertion
                assertTrue("Map should contain value after put", getMap().containsValue(values[i]));
    }
    }
    }

    public void testMapPut_4_oe() {
        resetEmpty();
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        final V[] newValues = getNewSampleValues();

        if (isPutAddSupported()) {
            for (int i = 0; i < keys.length; i++) {
                final Object o = getMap().put(keys[i], values[i]);
                getConfirmed().put(keys[i], values[i]);
                verify();
                // removed other assertion
                // removed other assertion
                // removed other assertion
            }
            if (isPutChangeSupported()) {
                for (int i = 0; i < keys.length; i++) {
                    final Object o = getMap().put(keys[i], newValues[i]);
                    getConfirmed().put(keys[i], newValues[i]);
                    verify();
                    assertEquals("Map.put should return previous value when changed", values[i], o);
    }
    }
    }
    }

    public void testMapPut_5_oe() {
        resetEmpty();
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        final V[] newValues = getNewSampleValues();

        if (isPutAddSupported()) {
            for (int i = 0; i < keys.length; i++) {
                final Object o = getMap().put(keys[i], values[i]);
                getConfirmed().put(keys[i], values[i]);
                verify();
                // removed other assertion
                // removed other assertion
                // removed other assertion
            }
            if (isPutChangeSupported()) {
                for (int i = 0; i < keys.length; i++) {
                    final Object o = getMap().put(keys[i], newValues[i]);
                    getConfirmed().put(keys[i], newValues[i]);
                    verify();
                    // removed other assertion
                    assertTrue("Map should still contain key after put when changed", getMap().containsKey(keys[i]));
    }
    }
    }
    }

    public void testMapPut_6_oe() {
        resetEmpty();
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        final V[] newValues = getNewSampleValues();

        if (isPutAddSupported()) {
            for (int i = 0; i < keys.length; i++) {
                final Object o = getMap().put(keys[i], values[i]);
                getConfirmed().put(keys[i], values[i]);
                verify();
                // removed other assertion
                // removed other assertion
                // removed other assertion
            }
            if (isPutChangeSupported()) {
                for (int i = 0; i < keys.length; i++) {
                    final Object o = getMap().put(keys[i], newValues[i]);
                    getConfirmed().put(keys[i], newValues[i]);
                    verify();
                    // removed other assertion
                    // removed other assertion
                    assertTrue("Map should contain new value after put when changed", getMap().containsValue(newValues[i]));
    }
    }
    }
    }

    public void testMapPut_7_oe() {
        resetEmpty();
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        final V[] newValues = getNewSampleValues();

        if (isPutAddSupported()) {
            for (int i = 0; i < keys.length; i++) {
                final Object o = getMap().put(keys[i], values[i]);
                getConfirmed().put(keys[i], values[i]);
                verify();
                // removed other assertion
                // removed other assertion
                // removed other assertion
            }
            if (isPutChangeSupported()) {
                for (int i = 0; i < keys.length; i++) {
                    final Object o = getMap().put(keys[i], newValues[i]);
                    getConfirmed().put(keys[i], newValues[i]);
                    verify();
                    // removed other assertion
                    // removed other assertion
                    // removed other assertion

                    // if duplicates are allowed, we're not guaranteed that the value
                    // no longer exists, so don't try checking that.
                    if (!isAllowDuplicateValues()) {
                        assertTrue("Map should not contain old value after put when changed", !getMap().containsValue(values[i]));
    }
    }
    }
    }
    }

    public void testMapPut_10_oe() {
        resetEmpty();
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        final V[] newValues = getNewSampleValues();

        if (isPutAddSupported()) {
            for (int i = 0; i < keys.length; i++) {
                final Object o = getMap().put(keys[i], values[i]);
                getConfirmed().put(keys[i], values[i]);
                verify();
                // removed other assertion
                // removed other assertion
                // removed other assertion
            }
            if (isPutChangeSupported()) {
                for (int i = 0; i < keys.length; i++) {
                    final Object o = getMap().put(keys[i], newValues[i]);
                    getConfirmed().put(keys[i], newValues[i]);
                    verify();
                    // removed other assertion
                    // removed other assertion
                    // removed other assertion

                    // if duplicates are allowed, we're not guaranteed that the value
                    // no longer exists, so don't try checking that.
                    if (!isAllowDuplicateValues()) {
                        // removed other assertion
                    }
                }
            } else {
                try {
                    // two possible exception here, either valid
                    getMap().put(keys[0], newValues[0]);
                    // removed other assertion
                } catch (final IllegalArgumentException ex) {
                } catch (final UnsupportedOperationException ex) {}
            }

        } else if (isPutChangeSupported()) {
            resetEmpty();
            try {
                getMap().put(keys[0], values[0]);
                // removed other assertion
            } catch (final IllegalArgumentException ex) {
            } catch (final UnsupportedOperationException ex) {
            }

            resetFull();
            int i = 0;
            for (final Iterator<K> it = getMap().keySet().iterator(); it.hasNext() && i < newValues.length; i++) {
                final K  key = it.next();
                final V o = getMap().put(key, newValues[i]);
                final V value = getConfirmed().put(key, newValues[i]);
                verify();
                assertEquals("Map.put should return previous value when changed", value, o);
    }
    }
    }

    public void testMapPut_11_oe() {
        resetEmpty();
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        final V[] newValues = getNewSampleValues();

        if (isPutAddSupported()) {
            for (int i = 0; i < keys.length; i++) {
                final Object o = getMap().put(keys[i], values[i]);
                getConfirmed().put(keys[i], values[i]);
                verify();
                // removed other assertion
                // removed other assertion
                // removed other assertion
            }
            if (isPutChangeSupported()) {
                for (int i = 0; i < keys.length; i++) {
                    final Object o = getMap().put(keys[i], newValues[i]);
                    getConfirmed().put(keys[i], newValues[i]);
                    verify();
                    // removed other assertion
                    // removed other assertion
                    // removed other assertion

                    // if duplicates are allowed, we're not guaranteed that the value
                    // no longer exists, so don't try checking that.
                    if (!isAllowDuplicateValues()) {
                        // removed other assertion
                    }
                }
            } else {
                try {
                    // two possible exception here, either valid
                    getMap().put(keys[0], newValues[0]);
                    // removed other assertion
                } catch (final IllegalArgumentException ex) {
                } catch (final UnsupportedOperationException ex) {}
            }

        } else if (isPutChangeSupported()) {
            resetEmpty();
            try {
                getMap().put(keys[0], values[0]);
                // removed other assertion
            } catch (final IllegalArgumentException ex) {
            } catch (final UnsupportedOperationException ex) {
            }

            resetFull();
            int i = 0;
            for (final Iterator<K> it = getMap().keySet().iterator(); it.hasNext() && i < newValues.length; i++) {
                final K  key = it.next();
                final V o = getMap().put(key, newValues[i]);
                final V value = getConfirmed().put(key, newValues[i]);
                verify();
                // removed other assertion
                assertTrue("Map should still contain key after put when changed", getMap() .containsKey(key));
    }
    }
    }

    public void testMapPut_12_oe() {
        resetEmpty();
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        final V[] newValues = getNewSampleValues();

        if (isPutAddSupported()) {
            for (int i = 0; i < keys.length; i++) {
                final Object o = getMap().put(keys[i], values[i]);
                getConfirmed().put(keys[i], values[i]);
                verify();
                // removed other assertion
                // removed other assertion
                // removed other assertion
            }
            if (isPutChangeSupported()) {
                for (int i = 0; i < keys.length; i++) {
                    final Object o = getMap().put(keys[i], newValues[i]);
                    getConfirmed().put(keys[i], newValues[i]);
                    verify();
                    // removed other assertion
                    // removed other assertion
                    // removed other assertion

                    // if duplicates are allowed, we're not guaranteed that the value
                    // no longer exists, so don't try checking that.
                    if (!isAllowDuplicateValues()) {
                        // removed other assertion
                    }
                }
            } else {
                try {
                    // two possible exception here, either valid
                    getMap().put(keys[0], newValues[0]);
                    // removed other assertion
                } catch (final IllegalArgumentException ex) {
                } catch (final UnsupportedOperationException ex) {}
            }

        } else if (isPutChangeSupported()) {
            resetEmpty();
            try {
                getMap().put(keys[0], values[0]);
                // removed other assertion
            } catch (final IllegalArgumentException ex) {
            } catch (final UnsupportedOperationException ex) {
            }

            resetFull();
            int i = 0;
            for (final Iterator<K> it = getMap().keySet().iterator(); it.hasNext() && i < newValues.length; i++) {
                final K  key = it.next();
                final V o = getMap().put(key, newValues[i]);
                final V value = getConfirmed().put(key, newValues[i]);
                verify();
                // removed other assertion
                // removed other assertion
                assertTrue("Map should contain new value after put when changed", getMap() .containsValue(newValues[i]));
    }
    }
    }

    public void testMapPut_13_oe() {
        resetEmpty();
        final K[] keys = getSampleKeys();
        final V[] values = getSampleValues();
        final V[] newValues = getNewSampleValues();

        if (isPutAddSupported()) {
            for (int i = 0; i < keys.length; i++) {
                final Object o = getMap().put(keys[i], values[i]);
                getConfirmed().put(keys[i], values[i]);
                verify();
                // removed other assertion
                // removed other assertion
                // removed other assertion
            }
            if (isPutChangeSupported()) {
                for (int i = 0; i < keys.length; i++) {
                    final Object o = getMap().put(keys[i], newValues[i]);
                    getConfirmed().put(keys[i], newValues[i]);
                    verify();
                    // removed other assertion
                    // removed other assertion
                    // removed other assertion

                    // if duplicates are allowed, we're not guaranteed that the value
                    // no longer exists, so don't try checking that.
                    if (!isAllowDuplicateValues()) {
                        // removed other assertion
                    }
                }
            } else {
                try {
                    // two possible exception here, either valid
                    getMap().put(keys[0], newValues[0]);
                    // removed other assertion
                } catch (final IllegalArgumentException ex) {
                } catch (final UnsupportedOperationException ex) {}
            }

        } else if (isPutChangeSupported()) {
            resetEmpty();
            try {
                getMap().put(keys[0], values[0]);
                // removed other assertion
            } catch (final IllegalArgumentException ex) {
            } catch (final UnsupportedOperationException ex) {
            }

            resetFull();
            int i = 0;
            for (final Iterator<K> it = getMap().keySet().iterator(); it.hasNext() && i < newValues.length; i++) {
                final K  key = it.next();
                final V o = getMap().put(key, newValues[i]);
                final V value = getConfirmed().put(key, newValues[i]);
                verify();
                // removed other assertion
                // removed other assertion
                // removed other assertion

                // if duplicates are allowed, we're not guaranteed that the value
                // no longer exists, so don't try checking that.
                if (!isAllowDuplicateValues()) {
                    assertTrue("Map should not contain old value after put when changed", !getMap().containsValue(values[i]));
    }
    }
    }
    }

    public void testMapPutAll_2_oe() {
        if (!isPutAddSupported()) {
            if (!isPutChangeSupported()) {
                final Map<K, V> temp = makeFullMap();
                resetEmpty();
                try {
                    getMap().putAll(temp);
                    // removed other assertion
                } catch (final UnsupportedOperationException ex) {}
            }
            return;
        }

        // check putAll OK adding empty map to empty map
        resetEmpty();
        assertEquals(0, getMap().size());
    }

    public void testMapPutAll_3_oe() {
        if (!isPutAddSupported()) {
            if (!isPutChangeSupported()) {
                final Map<K, V> temp = makeFullMap();
                resetEmpty();
                try {
                    getMap().putAll(temp);
                    // removed other assertion
                } catch (final UnsupportedOperationException ex) {}
            }
            return;
        }

        // check putAll OK adding empty map to empty map
        resetEmpty();
        // removed other assertion
        getMap().putAll(new HashMap<K, V>());
        assertEquals(0, getMap().size());
    }

    public void testMapPutAll_4_oe() {
        if (!isPutAddSupported()) {
            if (!isPutChangeSupported()) {
                final Map<K, V> temp = makeFullMap();
                resetEmpty();
                try {
                    getMap().putAll(temp);
                    // removed other assertion
                } catch (final UnsupportedOperationException ex) {}
            }
            return;
        }

        // check putAll OK adding empty map to empty map
        resetEmpty();
        // removed other assertion
        getMap().putAll(new HashMap<K, V>());
        // removed other assertion

        // check putAll OK adding empty map to non-empty map
        resetFull();
        final int size = getMap().size();
        getMap().putAll(new HashMap<K, V>());
        assertEquals(size, getMap().size());
    }

    public void testMapRemove_2_oe() {
        if (!isRemoveSupported()) {
            try {
                resetFull();
                getMap().remove(getMap().keySet().iterator().next());
                // removed other assertion
            } catch (final UnsupportedOperationException ex) {}
            return;
        }

        resetEmpty();

        final Object[] keys = getSampleKeys();
        final Object[] values = getSampleValues();
        for (final Object key : keys) {
            final Object o = getMap().remove(key);
            assertTrue("First map.remove should return null", o == null);
    }
    }

    public void testMapRemove_3_oe() {
        if (!isRemoveSupported()) {
            try {
                resetFull();
                getMap().remove(getMap().keySet().iterator().next());
                // removed other assertion
            } catch (final UnsupportedOperationException ex) {}
            return;
        }

        resetEmpty();

        final Object[] keys = getSampleKeys();
        final Object[] values = getSampleValues();
        for (final Object key : keys) {
            final Object o = getMap().remove(key);
            // removed other assertion
        }
        verify();

        resetFull();

        for (int i = 0; i < keys.length; i++) {
            final Object o = getMap().remove(keys[i]);
            getConfirmed().remove(keys[i]);
            verify();

            assertEquals("map.remove with valid key should return value", values[i], o);
    }
    }

    public void testMapRemove_4_oe() {
        if (!isRemoveSupported()) {
            try {
                resetFull();
                getMap().remove(getMap().keySet().iterator().next());
                // removed other assertion
            } catch (final UnsupportedOperationException ex) {}
            return;
        }

        resetEmpty();

        final Object[] keys = getSampleKeys();
        final Object[] values = getSampleValues();
        for (final Object key : keys) {
            final Object o = getMap().remove(key);
            // removed other assertion
        }
        verify();

        resetFull();

        for (int i = 0; i < keys.length; i++) {
            final Object o = getMap().remove(keys[i]);
            getConfirmed().remove(keys[i]);
            verify();

            // removed other assertion
        }

        final Object[] other = getOtherKeys();

        resetFull();
        final int size = getMap().size();
        for (final Object element : other) {
            final Object o = getMap().remove(element);
            assertNull("map.remove for nonexistent key should return null", o);
    }
    }

    public void testMapRemove_5_oe() {
        if (!isRemoveSupported()) {
            try {
                resetFull();
                getMap().remove(getMap().keySet().iterator().next());
                // removed other assertion
            } catch (final UnsupportedOperationException ex) {}
            return;
        }

        resetEmpty();

        final Object[] keys = getSampleKeys();
        final Object[] values = getSampleValues();
        for (final Object key : keys) {
            final Object o = getMap().remove(key);
            // removed other assertion
        }
        verify();

        resetFull();

        for (int i = 0; i < keys.length; i++) {
            final Object o = getMap().remove(keys[i]);
            getConfirmed().remove(keys[i]);
            verify();

            // removed other assertion
        }

        final Object[] other = getOtherKeys();

        resetFull();
        final int size = getMap().size();
        for (final Object element : other) {
            final Object o = getMap().remove(element);
            // removed other assertion
            assertEquals("map.remove for nonexistent key should not " + "shrink map", size, getMap().size());
    }
    }

    public void testValuesClearChangesMap_1_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // clear values, reflected in map
        resetFull();
        Collection<V> values = getMap().values();
        assertTrue(getMap().size() > 0);
    }

    public void testValuesClearChangesMap_2_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // clear values, reflected in map
        resetFull();
        Collection<V> values = getMap().values();
        // removed other assertion
        assertTrue(values.size() > 0);
    }

    public void testValuesClearChangesMap_3_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // clear values, reflected in map
        resetFull();
        Collection<V> values = getMap().values();
        // removed other assertion
        // removed other assertion
        values.clear();
        assertTrue(getMap().size() == 0);
    }

    public void testValuesClearChangesMap_4_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // clear values, reflected in map
        resetFull();
        Collection<V> values = getMap().values();
        // removed other assertion
        // removed other assertion
        values.clear();
        // removed other assertion
        assertTrue(values.size() == 0);
    }

    public void testValuesClearChangesMap_5_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // clear values, reflected in map
        resetFull();
        Collection<V> values = getMap().values();
        // removed other assertion
        // removed other assertion
        values.clear();
        // removed other assertion
        // removed other assertion

        // clear map, reflected in values
        resetFull();
        values = getMap().values();
        assertTrue(getMap().size() > 0);
    }

    public void testValuesClearChangesMap_6_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // clear values, reflected in map
        resetFull();
        Collection<V> values = getMap().values();
        // removed other assertion
        // removed other assertion
        values.clear();
        // removed other assertion
        // removed other assertion

        // clear map, reflected in values
        resetFull();
        values = getMap().values();
        // removed other assertion
        assertTrue(values.size() > 0);
    }

    public void testValuesClearChangesMap_7_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // clear values, reflected in map
        resetFull();
        Collection<V> values = getMap().values();
        // removed other assertion
        // removed other assertion
        values.clear();
        // removed other assertion
        // removed other assertion

        // clear map, reflected in values
        resetFull();
        values = getMap().values();
        // removed other assertion
        // removed other assertion
        getMap().clear();
        assertTrue(getMap().size() == 0);
    }

    public void testValuesClearChangesMap_8_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // clear values, reflected in map
        resetFull();
        Collection<V> values = getMap().values();
        // removed other assertion
        // removed other assertion
        values.clear();
        // removed other assertion
        // removed other assertion

        // clear map, reflected in values
        resetFull();
        values = getMap().values();
        // removed other assertion
        // removed other assertion
        getMap().clear();
        // removed other assertion
        assertTrue(values.size() == 0);
    }

    public void testKeySetClearChangesMap_1_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // clear values, reflected in map
        resetFull();
        Set<K> keySet = getMap().keySet();
        assertTrue(getMap().size() > 0);
    }

    public void testKeySetClearChangesMap_2_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // clear values, reflected in map
        resetFull();
        Set<K> keySet = getMap().keySet();
        // removed other assertion
        assertTrue(keySet.size() > 0);
    }

    public void testKeySetClearChangesMap_3_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // clear values, reflected in map
        resetFull();
        Set<K> keySet = getMap().keySet();
        // removed other assertion
        // removed other assertion
        keySet.clear();
        assertTrue(getMap().size() == 0);
    }

    public void testKeySetClearChangesMap_4_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // clear values, reflected in map
        resetFull();
        Set<K> keySet = getMap().keySet();
        // removed other assertion
        // removed other assertion
        keySet.clear();
        // removed other assertion
        assertTrue(keySet.size() == 0);
    }

    public void testKeySetClearChangesMap_5_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // clear values, reflected in map
        resetFull();
        Set<K> keySet = getMap().keySet();
        // removed other assertion
        // removed other assertion
        keySet.clear();
        // removed other assertion
        // removed other assertion

        // clear map, reflected in values
        resetFull();
        keySet = getMap().keySet();
        assertTrue(getMap().size() > 0);
    }

    public void testKeySetClearChangesMap_6_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // clear values, reflected in map
        resetFull();
        Set<K> keySet = getMap().keySet();
        // removed other assertion
        // removed other assertion
        keySet.clear();
        // removed other assertion
        // removed other assertion

        // clear map, reflected in values
        resetFull();
        keySet = getMap().keySet();
        // removed other assertion
        assertTrue(keySet.size() > 0);
    }

    public void testKeySetClearChangesMap_7_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // clear values, reflected in map
        resetFull();
        Set<K> keySet = getMap().keySet();
        // removed other assertion
        // removed other assertion
        keySet.clear();
        // removed other assertion
        // removed other assertion

        // clear map, reflected in values
        resetFull();
        keySet = getMap().keySet();
        // removed other assertion
        // removed other assertion
        getMap().clear();
        assertTrue(getMap().size() == 0);
    }

    public void testKeySetClearChangesMap_8_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // clear values, reflected in map
        resetFull();
        Set<K> keySet = getMap().keySet();
        // removed other assertion
        // removed other assertion
        keySet.clear();
        // removed other assertion
        // removed other assertion

        // clear map, reflected in values
        resetFull();
        keySet = getMap().keySet();
        // removed other assertion
        // removed other assertion
        getMap().clear();
        // removed other assertion
        assertTrue(keySet.size() == 0);
    }

    public void testEntrySetClearChangesMap_1_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // clear values, reflected in map
        resetFull();
        Set<Map.Entry<K, V>> entrySet = getMap().entrySet();
        assertTrue(getMap().size() > 0);
    }

    public void testEntrySetClearChangesMap_2_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // clear values, reflected in map
        resetFull();
        Set<Map.Entry<K, V>> entrySet = getMap().entrySet();
        // removed other assertion
        assertTrue(entrySet.size() > 0);
    }

    public void testEntrySetClearChangesMap_3_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // clear values, reflected in map
        resetFull();
        Set<Map.Entry<K, V>> entrySet = getMap().entrySet();
        // removed other assertion
        // removed other assertion
        entrySet.clear();
        assertTrue(getMap().size() == 0);
    }

    public void testEntrySetClearChangesMap_4_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // clear values, reflected in map
        resetFull();
        Set<Map.Entry<K, V>> entrySet = getMap().entrySet();
        // removed other assertion
        // removed other assertion
        entrySet.clear();
        // removed other assertion
        assertTrue(entrySet.size() == 0);
    }

    public void testEntrySetClearChangesMap_5_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // clear values, reflected in map
        resetFull();
        Set<Map.Entry<K, V>> entrySet = getMap().entrySet();
        // removed other assertion
        // removed other assertion
        entrySet.clear();
        // removed other assertion
        // removed other assertion

        // clear map, reflected in values
        resetFull();
        entrySet = getMap().entrySet();
        assertTrue(getMap().size() > 0);
    }

    public void testEntrySetClearChangesMap_6_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // clear values, reflected in map
        resetFull();
        Set<Map.Entry<K, V>> entrySet = getMap().entrySet();
        // removed other assertion
        // removed other assertion
        entrySet.clear();
        // removed other assertion
        // removed other assertion

        // clear map, reflected in values
        resetFull();
        entrySet = getMap().entrySet();
        // removed other assertion
        assertTrue(entrySet.size() > 0);
    }

    public void testEntrySetClearChangesMap_7_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // clear values, reflected in map
        resetFull();
        Set<Map.Entry<K, V>> entrySet = getMap().entrySet();
        // removed other assertion
        // removed other assertion
        entrySet.clear();
        // removed other assertion
        // removed other assertion

        // clear map, reflected in values
        resetFull();
        entrySet = getMap().entrySet();
        // removed other assertion
        // removed other assertion
        getMap().clear();
        assertTrue(getMap().size() == 0);
    }

    public void testEntrySetClearChangesMap_8_oe() {
        if (!isRemoveSupported()) {
            return;
        }

        // clear values, reflected in map
        resetFull();
        Set<Map.Entry<K, V>> entrySet = getMap().entrySet();
        // removed other assertion
        // removed other assertion
        entrySet.clear();
        // removed other assertion
        // removed other assertion

        // clear map, reflected in values
        resetFull();
        entrySet = getMap().entrySet();
        // removed other assertion
        // removed other assertion
        getMap().clear();
        // removed other assertion
        assertTrue(entrySet.size() == 0);
    }

    public void testEntrySetContains1_1_oe() {
        resetFull();
        final Set<Map.Entry<K, V>> entrySet = getMap().entrySet();
        final Map.Entry<K, V> entry = entrySet.iterator().next();
        assertEquals(true, entrySet.contains(entry));
    }

    public void testEntrySetContains2_1_oe() {
        resetFull();
        final Set<Map.Entry<K, V>> entrySet = getMap().entrySet();
        final Map.Entry<K, V> entry = entrySet.iterator().next();
        final Map.Entry<K, V> test = cloneMapEntry(entry);
        assertEquals(true, entrySet.contains(test));
    }

    public void testEntrySetContains3_1_oe() {
        resetFull();
        final Set<Map.Entry<K, V>> entrySet = getMap().entrySet();
        final Map.Entry<K, V> entry = entrySet.iterator().next();
        final HashMap<K, V> temp = new HashMap<>();
        temp.put(entry.getKey(), (V) "A VERY DIFFERENT VALUE");
        final Map.Entry<K, V> test = temp.entrySet().iterator().next();
        assertEquals(false, entrySet.contains(test));
    }

    public void testEntrySetRemove1_1_oe() {
        if (!isRemoveSupported()) {
            return;
        }
        resetFull();
        final int size = getMap().size();
        final Set<Map.Entry<K, V>> entrySet = getMap().entrySet();
        final Map.Entry<K, V> entry = entrySet.iterator().next();
        final K key = entry.getKey();

        assertEquals(true, entrySet.remove(entry));
    }

    public void testEntrySetRemove1_2_oe() {
        if (!isRemoveSupported()) {
            return;
        }
        resetFull();
        final int size = getMap().size();
        final Set<Map.Entry<K, V>> entrySet = getMap().entrySet();
        final Map.Entry<K, V> entry = entrySet.iterator().next();
        final K key = entry.getKey();

        // removed other assertion
        assertEquals(false, getMap().containsKey(key));
    }

    public void testEntrySetRemove1_3_oe() {
        if (!isRemoveSupported()) {
            return;
        }
        resetFull();
        final int size = getMap().size();
        final Set<Map.Entry<K, V>> entrySet = getMap().entrySet();
        final Map.Entry<K, V> entry = entrySet.iterator().next();
        final K key = entry.getKey();

        // removed other assertion
        // removed other assertion
        assertEquals(size - 1, getMap().size());
    }

    public void testEntrySetRemove2_1_oe() {
        if (!isRemoveSupported()) {
            return;
        }
        resetFull();
        final int size = getMap().size();
        final Set<Map.Entry<K, V>> entrySet = getMap().entrySet();
        final Map.Entry<K, V> entry = entrySet.iterator().next();
        final K key = entry.getKey();
        final Map.Entry<K, V> test = cloneMapEntry(entry);

        assertEquals(true, entrySet.remove(test));
    }

    public void testEntrySetRemove2_2_oe() {
        if (!isRemoveSupported()) {
            return;
        }
        resetFull();
        final int size = getMap().size();
        final Set<Map.Entry<K, V>> entrySet = getMap().entrySet();
        final Map.Entry<K, V> entry = entrySet.iterator().next();
        final K key = entry.getKey();
        final Map.Entry<K, V> test = cloneMapEntry(entry);

        // removed other assertion
        assertEquals(false, getMap().containsKey(key));
    }

    public void testEntrySetRemove2_3_oe() {
        if (!isRemoveSupported()) {
            return;
        }
        resetFull();
        final int size = getMap().size();
        final Set<Map.Entry<K, V>> entrySet = getMap().entrySet();
        final Map.Entry<K, V> entry = entrySet.iterator().next();
        final K key = entry.getKey();
        final Map.Entry<K, V> test = cloneMapEntry(entry);

        // removed other assertion
        // removed other assertion
        assertEquals(size - 1, getMap().size());
    }

    public void testEntrySetRemove3_1_oe() {
        if (!isRemoveSupported()) {
            return;
        }
        resetFull();
        final int size = getMap().size();
        final Set<Map.Entry<K, V>> entrySet = getMap().entrySet();
        final Map.Entry<K, V> entry = entrySet.iterator().next();
        final K key = entry.getKey();
        final HashMap<K, V> temp = new HashMap<>();
        temp.put(entry.getKey(), (V) "A VERY DIFFERENT VALUE");
        final Map.Entry<K, V> test = temp.entrySet().iterator().next();

        assertEquals(false, entrySet.remove(test));
    }

    public void testEntrySetRemove3_2_oe() {
        if (!isRemoveSupported()) {
            return;
        }
        resetFull();
        final int size = getMap().size();
        final Set<Map.Entry<K, V>> entrySet = getMap().entrySet();
        final Map.Entry<K, V> entry = entrySet.iterator().next();
        final K key = entry.getKey();
        final HashMap<K, V> temp = new HashMap<>();
        temp.put(entry.getKey(), (V) "A VERY DIFFERENT VALUE");
        final Map.Entry<K, V> test = temp.entrySet().iterator().next();

        // removed other assertion
        assertEquals(true, getMap().containsKey(key));
    }

    public void testEntrySetRemove3_3_oe() {
        if (!isRemoveSupported()) {
            return;
        }
        resetFull();
        final int size = getMap().size();
        final Set<Map.Entry<K, V>> entrySet = getMap().entrySet();
        final Map.Entry<K, V> entry = entrySet.iterator().next();
        final K key = entry.getKey();
        final HashMap<K, V> temp = new HashMap<>();
        temp.put(entry.getKey(), (V) "A VERY DIFFERENT VALUE");
        final Map.Entry<K, V> test = temp.entrySet().iterator().next();

        // removed other assertion
        // removed other assertion
        assertEquals(size, getMap().size());
    }

    public void testValuesRemoveChangesMap_1_oe() {
        resetFull();
        final V[] sampleValues = getSampleValues();
        final Collection<V> values = getMap().values();
        for (final V sampleValue : sampleValues) {
            if (map.containsValue(sampleValue)) {
                int j = 0;  // loop counter prevents infinite loops when remove is broken
                while (values.contains(sampleValue) && j < 10000) {
                    try {
                        values.remove(sampleValue);
                    } catch (final UnsupportedOperationException e) {
                        // if values.remove is unsupported, just skip this test
                        return;
                    }
                    j++;
                }
                assertTrue("values().remove(obj) is broken", j < 10000);
    }
    }
    }

    public void testValuesRemoveChangesMap_2_oe() {
        resetFull();
        final V[] sampleValues = getSampleValues();
        final Collection<V> values = getMap().values();
        for (final V sampleValue : sampleValues) {
            if (map.containsValue(sampleValue)) {
                int j = 0;  // loop counter prevents infinite loops when remove is broken
                while (values.contains(sampleValue) && j < 10000) {
                    try {
                        values.remove(sampleValue);
                    } catch (final UnsupportedOperationException e) {
                        // if values.remove is unsupported, just skip this test
                        return;
                    }
                    j++;
                }
                // removed other assertion
                assertTrue( "Value should have been removed from the underlying map.", !getMap().containsValue(sampleValue));
    }
    }
    }

    public void testValuesRemoveAll_2_oe() {
        resetFull();
        final Collection<V> values = getMap().values();
        final List<V> sampleValuesAsList = Arrays.asList(getSampleValues());
        if (!values.equals(sampleValuesAsList)) {
            return;
        }
        try {
            // removed other assertion
        } catch (final UnsupportedOperationException e) {
            // if values.removeAll is unsupported, just skip this test
            return;
        }
        assertEquals(sampleValuesAsList.size(), getMap().size());
    }

    public void testValuesRemoveAll_4_oe() {
        resetFull();
        final Collection<V> values = getMap().values();
        final List<V> sampleValuesAsList = Arrays.asList(getSampleValues());
        if (!values.equals(sampleValuesAsList)) {
            return;
        }
        try {
            // removed other assertion
        } catch (final UnsupportedOperationException e) {
            // if values.removeAll is unsupported, just skip this test
            return;
        }
        // removed other assertion
        try {
            // removed other assertion
        } catch (final UnsupportedOperationException e) {
            // if values.removeAll is unsupported, just skip this test
            return;
        }
        assertTrue(getMap().isEmpty());
    }

    public void testValuesRetainAll_2_oe() {
        resetFull();
        final Collection<V> values = getMap().values();
        final List<V> sampleValuesAsList = Arrays.asList(getSampleValues());
        if (!values.equals(sampleValuesAsList)) {
            return;
        }
        try {
            // removed other assertion
        } catch (final UnsupportedOperationException e) {
            // if values.retainAll is unsupported, just skip this test
            return;
        }
        assertEquals(sampleValuesAsList.size(), getMap().size());
    }

    public void testValuesRetainAll_4_oe() {
        resetFull();
        final Collection<V> values = getMap().values();
        final List<V> sampleValuesAsList = Arrays.asList(getSampleValues());
        if (!values.equals(sampleValuesAsList)) {
            return;
        }
        try {
            // removed other assertion
        } catch (final UnsupportedOperationException e) {
            // if values.retainAll is unsupported, just skip this test
            return;
        }
        // removed other assertion
        try {
            // removed other assertion
        } catch (final UnsupportedOperationException e) {
            // if values.retainAll is unsupported, just skip this test
            return;
        }
        assertTrue(getMap().isEmpty());
    }

    public void testValuesIteratorRemoveChangesMap_1_oe() {
        resetFull();
        final List<V> sampleValuesAsList = Arrays.asList(getSampleValues());
        final Map<V, Integer> cardinality = CollectionUtils.getCardinalityMap(sampleValuesAsList);
        final Collection<V> values = getMap().values();
        for (final Iterator<V> iter = values.iterator(); iter.hasNext();) {
            final V value = iter.next();
            Integer count = cardinality.get(value);
            if (count == null) {
                return;
            }
            try {
                iter.remove();
                cardinality.put(value, --count);
            } catch (final UnsupportedOperationException e) {
                // if values.iterator.remove is unsupported, just skip this test
                return;
            }
            final boolean expected = count > 0;
            final StringBuilder msg = new StringBuilder("Value should ");
            msg.append(expected ? "yet " : "no longer ");
            msg.append("be present in the underlying map");
            assertEquals(msg.toString(), expected, getMap().containsValue(value));
    }
    }

    public void testValuesIteratorRemoveChangesMap_2_oe() {
        resetFull();
        final List<V> sampleValuesAsList = Arrays.asList(getSampleValues());
        final Map<V, Integer> cardinality = CollectionUtils.getCardinalityMap(sampleValuesAsList);
        final Collection<V> values = getMap().values();
        for (final Iterator<V> iter = values.iterator(); iter.hasNext();) {
            final V value = iter.next();
            Integer count = cardinality.get(value);
            if (count == null) {
                return;
            }
            try {
                iter.remove();
                cardinality.put(value, --count);
            } catch (final UnsupportedOperationException e) {
                // if values.iterator.remove is unsupported, just skip this test
                return;
            }
            final boolean expected = count > 0;
            final StringBuilder msg = new StringBuilder("Value should ");
            msg.append(expected ? "yet " : "no longer ");
            msg.append("be present in the underlying map");
            // removed other assertion
        }
        assertTrue(getMap().isEmpty());
    }

    public void testKeySetRemoveChangesMap_1_oe() {
        resetFull();
        final K[] sampleKeys = getSampleKeys();
        final Set<K> keys = getMap().keySet();
        for (final K sampleKey : sampleKeys) {
            try {
                keys.remove(sampleKey);
            } catch (final UnsupportedOperationException e) {
                // if key.remove is unsupported, just skip this test
                return;
            }
            assertTrue( "Key should have been removed from the underlying map.", !getMap().containsKey(sampleKey));
    }
    }

    public void testKeySetRemoveAll_2_oe() {
        resetFull();
        final Set<K> keys = getMap().keySet();
        final List<K> sampleKeysAsList = Arrays.asList(getSampleKeys());
        if (!keys.equals(sampleKeysAsList)) {
            return;
        }
        try {
            // removed other assertion
        } catch (final UnsupportedOperationException e) {
            return;
        }
        assertEquals(sampleKeysAsList, keys);
    }

    public void testKeySetRemoveAll_4_oe() {
        resetFull();
        final Set<K> keys = getMap().keySet();
        final List<K> sampleKeysAsList = Arrays.asList(getSampleKeys());
        if (!keys.equals(sampleKeysAsList)) {
            return;
        }
        try {
            // removed other assertion
        } catch (final UnsupportedOperationException e) {
            return;
        }
        // removed other assertion
        try {
            // removed other assertion
        } catch (final UnsupportedOperationException e) {
            return;
        }
        assertTrue(getMap().isEmpty());
    }

    public void testKeySetRetainAll_2_oe() {
        resetFull();
        final Set<K> keys = getMap().keySet();
        final List<K> sampleKeysAsList = Arrays.asList(getSampleKeys());
        if (!keys.equals(sampleKeysAsList)) {
            return;
        }
        try {
            // removed other assertion
        } catch (final UnsupportedOperationException e) {
            return;
        }
        assertEquals(sampleKeysAsList, keys);
    }

    public void testKeySetRetainAll_4_oe() {
        resetFull();
        final Set<K> keys = getMap().keySet();
        final List<K> sampleKeysAsList = Arrays.asList(getSampleKeys());
        if (!keys.equals(sampleKeysAsList)) {
            return;
        }
        try {
            // removed other assertion
        } catch (final UnsupportedOperationException e) {
            return;
        }
        // removed other assertion
        try {
            // removed other assertion
        } catch (final UnsupportedOperationException e) {
            return;
        }
        assertTrue(getMap().isEmpty());
    }

    public void testKeySetIteratorRemoveChangesMap_1_oe() {
        resetFull();
        for (final Iterator<K> iter = getMap().keySet().iterator(); iter.hasNext();) {
            final K key = iter.next();
            try {
                iter.remove();
            } catch (final UnsupportedOperationException e) {
                return;
            }
            assertFalse(getMap().containsKey(key));
    }
    }

    public void testEntrySetRemoveChangesMap_1_oe() {
        resetFull();
        final K[] sampleKeys = getSampleKeys();
        final V[] sampleValues = getSampleValues();
        final Set<Map.Entry<K, V>> entrySet = getMap().entrySet();
        for (int i = 0; i < sampleKeys.length; i++) {
            try {
                entrySet.remove(new DefaultMapEntry<>(sampleKeys[i], sampleValues[i]));
            } catch (final UnsupportedOperationException e) {
                // if entrySet removal is unsupported, just skip this test
                return;
            }
            assertTrue( "Entry should have been removed from the underlying map.", !getMap().containsKey(sampleKeys[i]));
    }
    }

    public void testEntrySetRemoveAll_2_oe() {
        resetFull();
        final K[] sampleKeys = getSampleKeys();
        final V[] sampleValues = getSampleValues();
        //verify map looks as expected:
        for (int i = 0; i < sampleKeys.length; i++) {
            if (!getMap().containsKey(sampleKeys[i])) {
                return;
            }
            final V value = sampleValues[i];
            final V test = getMap().get(sampleKeys[i]);
            if (value == test || value != null && value.equals(test)) {
                continue;
            }
            return;
        }
        final Set<Map.Entry<K, V>> entrySet = getMap().entrySet();
        final HashSet<Map.Entry<K, V>> comparisonSet = new HashSet<>(entrySet);
        try {
            // removed other assertion
        } catch (final UnsupportedOperationException e) {
            return;
        }
        assertEquals(sampleKeys.length, getMap().size());
    }

    public void testEntrySetRemoveAll_4_oe() {
        resetFull();
        final K[] sampleKeys = getSampleKeys();
        final V[] sampleValues = getSampleValues();
        //verify map looks as expected:
        for (int i = 0; i < sampleKeys.length; i++) {
            if (!getMap().containsKey(sampleKeys[i])) {
                return;
            }
            final V value = sampleValues[i];
            final V test = getMap().get(sampleKeys[i]);
            if (value == test || value != null && value.equals(test)) {
                continue;
            }
            return;
        }
        final Set<Map.Entry<K, V>> entrySet = getMap().entrySet();
        final HashSet<Map.Entry<K, V>> comparisonSet = new HashSet<>(entrySet);
        try {
            // removed other assertion
        } catch (final UnsupportedOperationException e) {
            return;
        }
        // removed other assertion
        try {
            // removed other assertion
        } catch (final UnsupportedOperationException e) {
            return;
        }
        assertTrue(getMap().isEmpty());
    }

    public void testEntrySetRetainAll_2_oe() {
        resetFull();
        final K[] sampleKeys = getSampleKeys();
        final V[] sampleValues = getSampleValues();
        //verify map looks as expected:
        for (int i = 0; i < sampleKeys.length; i++) {
            if (!getMap().containsKey(sampleKeys[i])) {
                return;
            }
            final V value = sampleValues[i];
            final V test = getMap().get(sampleKeys[i]);
            if (value == test || value != null && value.equals(test)) {
                continue;
            }
            return;
        }
        final Set<Map.Entry<K, V>> entrySet = getMap().entrySet();
        final HashSet<Map.Entry<K, V>> comparisonSet = new HashSet<>(entrySet);
        try {
            // removed other assertion
        } catch (final UnsupportedOperationException e) {
            return;
        }
        assertEquals(sampleKeys.length, getMap().size());
    }

    public void testEntrySetRetainAll_4_oe() {
        resetFull();
        final K[] sampleKeys = getSampleKeys();
        final V[] sampleValues = getSampleValues();
        //verify map looks as expected:
        for (int i = 0; i < sampleKeys.length; i++) {
            if (!getMap().containsKey(sampleKeys[i])) {
                return;
            }
            final V value = sampleValues[i];
            final V test = getMap().get(sampleKeys[i]);
            if (value == test || value != null && value.equals(test)) {
                continue;
            }
            return;
        }
        final Set<Map.Entry<K, V>> entrySet = getMap().entrySet();
        final HashSet<Map.Entry<K, V>> comparisonSet = new HashSet<>(entrySet);
        try {
            // removed other assertion
        } catch (final UnsupportedOperationException e) {
            return;
        }
        // removed other assertion
        try {
            // removed other assertion
        } catch (final UnsupportedOperationException e) {
            return;
        }
        assertTrue(getMap().isEmpty());
    }

    public void testEntrySetIteratorRemoveChangesMap_1_oe() {
        resetFull();
        for (final Iterator<Map.Entry<K, V>> iter = getMap().entrySet().iterator(); iter.hasNext();) {
            final K key = iter.next().getKey();
            try {
                iter.remove();
            } catch (final UnsupportedOperationException e) {
                return;
            }
            assertFalse(getMap().containsKey(key));
    }
    }

        public void testMapEntrySetIteratorEntry_1_oe() {
            resetFull();
            final Iterator<Map.Entry<K, V>> it = getCollection().iterator();
            int count = 0;
            while (it.hasNext()) {
                final Map.Entry<K, V> entry = it.next();
                assertEquals(true, AbstractMapTest.this.getMap().containsKey(entry.getKey()));
        }
        }

        public void testMapEntrySetIteratorEntry_2_oe() {
            resetFull();
            final Iterator<Map.Entry<K, V>> it = getCollection().iterator();
            int count = 0;
            while (it.hasNext()) {
                final Map.Entry<K, V> entry = it.next();
                // removed other assertion
                assertEquals(true, AbstractMapTest.this.getMap().containsValue(entry.getValue()));
        }
        }

        public void testMapEntrySetIteratorEntry_3_oe() {
            resetFull();
            final Iterator<Map.Entry<K, V>> it = getCollection().iterator();
            int count = 0;
            while (it.hasNext()) {
                final Map.Entry<K, V> entry = it.next();
                // removed other assertion
                // removed other assertion
                if (!isGetStructuralModify()) {
                    assertEquals(AbstractMapTest.this.getMap().get(entry.getKey()), entry.getValue());
        }
        }
        }

        public void testMapEntrySetIteratorEntry_4_oe() {
            resetFull();
            final Iterator<Map.Entry<K, V>> it = getCollection().iterator();
            int count = 0;
            while (it.hasNext()) {
                final Map.Entry<K, V> entry = it.next();
                // removed other assertion
                // removed other assertion
                if (!isGetStructuralModify()) {
                    // removed other assertion
                }
                count++;
            }
            assertEquals(getCollection().size(), count);
        }

        public void testMapEntrySetIteratorEntrySetValue_1_oe() {
            final K key1 = getSampleKeys()[0];
            final K key2 = getSampleKeys().length == 1 ? getSampleKeys()[0] : getSampleKeys()[1];
            final V newValue1 = getNewSampleValues()[0];
            final V newValue2 = getNewSampleValues().length ==1 ? getNewSampleValues()[0] : getNewSampleValues()[1];

            resetFull();
            // explicitly get entries as sample values/keys are connected for some maps
            // such as BeanMap
            Iterator<Map.Entry<K, V>> it = TestMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry1 = getEntry(it, key1);
            it = TestMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry2 = getEntry(it, key2);
            Iterator<Map.Entry<K, V>> itConfirmed = TestMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed1 = getEntry(itConfirmed, key1);
            itConfirmed = TestMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed2 = getEntry(itConfirmed, key2);
            verify();

            if (!isSetValueSupported()) {
                try {
                    entry1.setValue(newValue1);
                } catch (final UnsupportedOperationException ex) {
                }
                return;
            }

            entry1.setValue(newValue1);
            entryConfirmed1.setValue(newValue1);
            assertEquals(newValue1, entry1.getValue());
        }

        public void testMapEntrySetIteratorEntrySetValue_2_oe() {
            final K key1 = getSampleKeys()[0];
            final K key2 = getSampleKeys().length == 1 ? getSampleKeys()[0] : getSampleKeys()[1];
            final V newValue1 = getNewSampleValues()[0];
            final V newValue2 = getNewSampleValues().length ==1 ? getNewSampleValues()[0] : getNewSampleValues()[1];

            resetFull();
            // explicitly get entries as sample values/keys are connected for some maps
            // such as BeanMap
            Iterator<Map.Entry<K, V>> it = TestMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry1 = getEntry(it, key1);
            it = TestMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry2 = getEntry(it, key2);
            Iterator<Map.Entry<K, V>> itConfirmed = TestMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed1 = getEntry(itConfirmed, key1);
            itConfirmed = TestMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed2 = getEntry(itConfirmed, key2);
            verify();

            if (!isSetValueSupported()) {
                try {
                    entry1.setValue(newValue1);
                } catch (final UnsupportedOperationException ex) {
                }
                return;
            }

            entry1.setValue(newValue1);
            entryConfirmed1.setValue(newValue1);
            // removed other assertion
            assertEquals(true, AbstractMapTest.this.getMap().containsKey(entry1.getKey()));
        }

        public void testMapEntrySetIteratorEntrySetValue_3_oe() {
            final K key1 = getSampleKeys()[0];
            final K key2 = getSampleKeys().length == 1 ? getSampleKeys()[0] : getSampleKeys()[1];
            final V newValue1 = getNewSampleValues()[0];
            final V newValue2 = getNewSampleValues().length ==1 ? getNewSampleValues()[0] : getNewSampleValues()[1];

            resetFull();
            // explicitly get entries as sample values/keys are connected for some maps
            // such as BeanMap
            Iterator<Map.Entry<K, V>> it = TestMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry1 = getEntry(it, key1);
            it = TestMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry2 = getEntry(it, key2);
            Iterator<Map.Entry<K, V>> itConfirmed = TestMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed1 = getEntry(itConfirmed, key1);
            itConfirmed = TestMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed2 = getEntry(itConfirmed, key2);
            verify();

            if (!isSetValueSupported()) {
                try {
                    entry1.setValue(newValue1);
                } catch (final UnsupportedOperationException ex) {
                }
                return;
            }

            entry1.setValue(newValue1);
            entryConfirmed1.setValue(newValue1);
            // removed other assertion
            // removed other assertion
            assertEquals(true, AbstractMapTest.this.getMap().containsValue(newValue1));
        }

        public void testMapEntrySetIteratorEntrySetValue_4_oe() {
            final K key1 = getSampleKeys()[0];
            final K key2 = getSampleKeys().length == 1 ? getSampleKeys()[0] : getSampleKeys()[1];
            final V newValue1 = getNewSampleValues()[0];
            final V newValue2 = getNewSampleValues().length ==1 ? getNewSampleValues()[0] : getNewSampleValues()[1];

            resetFull();
            // explicitly get entries as sample values/keys are connected for some maps
            // such as BeanMap
            Iterator<Map.Entry<K, V>> it = TestMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry1 = getEntry(it, key1);
            it = TestMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry2 = getEntry(it, key2);
            Iterator<Map.Entry<K, V>> itConfirmed = TestMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed1 = getEntry(itConfirmed, key1);
            itConfirmed = TestMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed2 = getEntry(itConfirmed, key2);
            verify();

            if (!isSetValueSupported()) {
                try {
                    entry1.setValue(newValue1);
                } catch (final UnsupportedOperationException ex) {
                }
                return;
            }

            entry1.setValue(newValue1);
            entryConfirmed1.setValue(newValue1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(newValue1, AbstractMapTest.this.getMap().get(entry1.getKey()));
        }

        public void testMapEntrySetIteratorEntrySetValue_5_oe() {
            final K key1 = getSampleKeys()[0];
            final K key2 = getSampleKeys().length == 1 ? getSampleKeys()[0] : getSampleKeys()[1];
            final V newValue1 = getNewSampleValues()[0];
            final V newValue2 = getNewSampleValues().length ==1 ? getNewSampleValues()[0] : getNewSampleValues()[1];

            resetFull();
            // explicitly get entries as sample values/keys are connected for some maps
            // such as BeanMap
            Iterator<Map.Entry<K, V>> it = TestMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry1 = getEntry(it, key1);
            it = TestMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry2 = getEntry(it, key2);
            Iterator<Map.Entry<K, V>> itConfirmed = TestMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed1 = getEntry(itConfirmed, key1);
            itConfirmed = TestMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed2 = getEntry(itConfirmed, key2);
            verify();

            if (!isSetValueSupported()) {
                try {
                    entry1.setValue(newValue1);
                } catch (final UnsupportedOperationException ex) {
                }
                return;
            }

            entry1.setValue(newValue1);
            entryConfirmed1.setValue(newValue1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            verify();

            entry1.setValue(newValue1);
            entryConfirmed1.setValue(newValue1);
            assertEquals(newValue1, entry1.getValue());
        }

        public void testMapEntrySetIteratorEntrySetValue_6_oe() {
            final K key1 = getSampleKeys()[0];
            final K key2 = getSampleKeys().length == 1 ? getSampleKeys()[0] : getSampleKeys()[1];
            final V newValue1 = getNewSampleValues()[0];
            final V newValue2 = getNewSampleValues().length ==1 ? getNewSampleValues()[0] : getNewSampleValues()[1];

            resetFull();
            // explicitly get entries as sample values/keys are connected for some maps
            // such as BeanMap
            Iterator<Map.Entry<K, V>> it = TestMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry1 = getEntry(it, key1);
            it = TestMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry2 = getEntry(it, key2);
            Iterator<Map.Entry<K, V>> itConfirmed = TestMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed1 = getEntry(itConfirmed, key1);
            itConfirmed = TestMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed2 = getEntry(itConfirmed, key2);
            verify();

            if (!isSetValueSupported()) {
                try {
                    entry1.setValue(newValue1);
                } catch (final UnsupportedOperationException ex) {
                }
                return;
            }

            entry1.setValue(newValue1);
            entryConfirmed1.setValue(newValue1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            verify();

            entry1.setValue(newValue1);
            entryConfirmed1.setValue(newValue1);
            // removed other assertion
            assertEquals(true, AbstractMapTest.this.getMap().containsKey(entry1.getKey()));
        }

        public void testMapEntrySetIteratorEntrySetValue_7_oe() {
            final K key1 = getSampleKeys()[0];
            final K key2 = getSampleKeys().length == 1 ? getSampleKeys()[0] : getSampleKeys()[1];
            final V newValue1 = getNewSampleValues()[0];
            final V newValue2 = getNewSampleValues().length ==1 ? getNewSampleValues()[0] : getNewSampleValues()[1];

            resetFull();
            // explicitly get entries as sample values/keys are connected for some maps
            // such as BeanMap
            Iterator<Map.Entry<K, V>> it = TestMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry1 = getEntry(it, key1);
            it = TestMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry2 = getEntry(it, key2);
            Iterator<Map.Entry<K, V>> itConfirmed = TestMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed1 = getEntry(itConfirmed, key1);
            itConfirmed = TestMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed2 = getEntry(itConfirmed, key2);
            verify();

            if (!isSetValueSupported()) {
                try {
                    entry1.setValue(newValue1);
                } catch (final UnsupportedOperationException ex) {
                }
                return;
            }

            entry1.setValue(newValue1);
            entryConfirmed1.setValue(newValue1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            verify();

            entry1.setValue(newValue1);
            entryConfirmed1.setValue(newValue1);
            // removed other assertion
            // removed other assertion
            assertEquals(true, AbstractMapTest.this.getMap().containsValue(newValue1));
        }

        public void testMapEntrySetIteratorEntrySetValue_8_oe() {
            final K key1 = getSampleKeys()[0];
            final K key2 = getSampleKeys().length == 1 ? getSampleKeys()[0] : getSampleKeys()[1];
            final V newValue1 = getNewSampleValues()[0];
            final V newValue2 = getNewSampleValues().length ==1 ? getNewSampleValues()[0] : getNewSampleValues()[1];

            resetFull();
            // explicitly get entries as sample values/keys are connected for some maps
            // such as BeanMap
            Iterator<Map.Entry<K, V>> it = TestMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry1 = getEntry(it, key1);
            it = TestMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry2 = getEntry(it, key2);
            Iterator<Map.Entry<K, V>> itConfirmed = TestMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed1 = getEntry(itConfirmed, key1);
            itConfirmed = TestMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed2 = getEntry(itConfirmed, key2);
            verify();

            if (!isSetValueSupported()) {
                try {
                    entry1.setValue(newValue1);
                } catch (final UnsupportedOperationException ex) {
                }
                return;
            }

            entry1.setValue(newValue1);
            entryConfirmed1.setValue(newValue1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            verify();

            entry1.setValue(newValue1);
            entryConfirmed1.setValue(newValue1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(newValue1, AbstractMapTest.this.getMap().get(entry1.getKey()));
        }

        public void testMapEntrySetIteratorEntrySetValue_9_oe() {
            final K key1 = getSampleKeys()[0];
            final K key2 = getSampleKeys().length == 1 ? getSampleKeys()[0] : getSampleKeys()[1];
            final V newValue1 = getNewSampleValues()[0];
            final V newValue2 = getNewSampleValues().length ==1 ? getNewSampleValues()[0] : getNewSampleValues()[1];

            resetFull();
            // explicitly get entries as sample values/keys are connected for some maps
            // such as BeanMap
            Iterator<Map.Entry<K, V>> it = TestMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry1 = getEntry(it, key1);
            it = TestMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry2 = getEntry(it, key2);
            Iterator<Map.Entry<K, V>> itConfirmed = TestMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed1 = getEntry(itConfirmed, key1);
            itConfirmed = TestMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed2 = getEntry(itConfirmed, key2);
            verify();

            if (!isSetValueSupported()) {
                try {
                    entry1.setValue(newValue1);
                } catch (final UnsupportedOperationException ex) {
                }
                return;
            }

            entry1.setValue(newValue1);
            entryConfirmed1.setValue(newValue1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            verify();

            entry1.setValue(newValue1);
            entryConfirmed1.setValue(newValue1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            verify();

            entry2.setValue(newValue2);
            entryConfirmed2.setValue(newValue2);
            assertEquals(newValue2, entry2.getValue());
        }

        public void testMapEntrySetIteratorEntrySetValue_10_oe() {
            final K key1 = getSampleKeys()[0];
            final K key2 = getSampleKeys().length == 1 ? getSampleKeys()[0] : getSampleKeys()[1];
            final V newValue1 = getNewSampleValues()[0];
            final V newValue2 = getNewSampleValues().length ==1 ? getNewSampleValues()[0] : getNewSampleValues()[1];

            resetFull();
            // explicitly get entries as sample values/keys are connected for some maps
            // such as BeanMap
            Iterator<Map.Entry<K, V>> it = TestMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry1 = getEntry(it, key1);
            it = TestMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry2 = getEntry(it, key2);
            Iterator<Map.Entry<K, V>> itConfirmed = TestMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed1 = getEntry(itConfirmed, key1);
            itConfirmed = TestMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed2 = getEntry(itConfirmed, key2);
            verify();

            if (!isSetValueSupported()) {
                try {
                    entry1.setValue(newValue1);
                } catch (final UnsupportedOperationException ex) {
                }
                return;
            }

            entry1.setValue(newValue1);
            entryConfirmed1.setValue(newValue1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            verify();

            entry1.setValue(newValue1);
            entryConfirmed1.setValue(newValue1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            verify();

            entry2.setValue(newValue2);
            entryConfirmed2.setValue(newValue2);
            // removed other assertion
            assertEquals(true, AbstractMapTest.this.getMap().containsKey(entry2.getKey()));
        }

        public void testMapEntrySetIteratorEntrySetValue_11_oe() {
            final K key1 = getSampleKeys()[0];
            final K key2 = getSampleKeys().length == 1 ? getSampleKeys()[0] : getSampleKeys()[1];
            final V newValue1 = getNewSampleValues()[0];
            final V newValue2 = getNewSampleValues().length ==1 ? getNewSampleValues()[0] : getNewSampleValues()[1];

            resetFull();
            // explicitly get entries as sample values/keys are connected for some maps
            // such as BeanMap
            Iterator<Map.Entry<K, V>> it = TestMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry1 = getEntry(it, key1);
            it = TestMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry2 = getEntry(it, key2);
            Iterator<Map.Entry<K, V>> itConfirmed = TestMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed1 = getEntry(itConfirmed, key1);
            itConfirmed = TestMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed2 = getEntry(itConfirmed, key2);
            verify();

            if (!isSetValueSupported()) {
                try {
                    entry1.setValue(newValue1);
                } catch (final UnsupportedOperationException ex) {
                }
                return;
            }

            entry1.setValue(newValue1);
            entryConfirmed1.setValue(newValue1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            verify();

            entry1.setValue(newValue1);
            entryConfirmed1.setValue(newValue1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            verify();

            entry2.setValue(newValue2);
            entryConfirmed2.setValue(newValue2);
            // removed other assertion
            // removed other assertion
            assertEquals(true, AbstractMapTest.this.getMap().containsValue(newValue2));
        }

        public void testMapEntrySetIteratorEntrySetValue_12_oe() {
            final K key1 = getSampleKeys()[0];
            final K key2 = getSampleKeys().length == 1 ? getSampleKeys()[0] : getSampleKeys()[1];
            final V newValue1 = getNewSampleValues()[0];
            final V newValue2 = getNewSampleValues().length ==1 ? getNewSampleValues()[0] : getNewSampleValues()[1];

            resetFull();
            // explicitly get entries as sample values/keys are connected for some maps
            // such as BeanMap
            Iterator<Map.Entry<K, V>> it = TestMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry1 = getEntry(it, key1);
            it = TestMapEntrySet.this.getCollection().iterator();
            final Map.Entry<K, V> entry2 = getEntry(it, key2);
            Iterator<Map.Entry<K, V>> itConfirmed = TestMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed1 = getEntry(itConfirmed, key1);
            itConfirmed = TestMapEntrySet.this.getConfirmed().iterator();
            final Map.Entry<K, V> entryConfirmed2 = getEntry(itConfirmed, key2);
            verify();

            if (!isSetValueSupported()) {
                try {
                    entry1.setValue(newValue1);
                } catch (final UnsupportedOperationException ex) {
                }
                return;
            }

            entry1.setValue(newValue1);
            entryConfirmed1.setValue(newValue1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            verify();

            entry1.setValue(newValue1);
            entryConfirmed1.setValue(newValue1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            verify();

            entry2.setValue(newValue2);
            entryConfirmed2.setValue(newValue2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(newValue2, AbstractMapTest.this.getMap().get(entry2.getKey()));
        }

        public void testMapEntrySetRemoveNonMapEntry_1_oe() {
            if (!isRemoveSupported()) {
                return;
            }
            resetFull();
            assertEquals(false, getCollection().remove(null));
        }

        public void testMapEntrySetRemoveNonMapEntry_2_oe() {
            if (!isRemoveSupported()) {
                return;
            }
            resetFull();
            // removed other assertion
            assertEquals(false, getCollection().remove(new Object()));
        }

     * can add and remove elements}, then {@link Map#size()} and
     * {@link Map#isEmpty()} are used to ensure that map has no elements after
     * a call to clear.  If the map does not support adding and removing
     * elements, this method checks to ensure clear throws an
     * UnsupportedOperationException.
     */
    public void testMapClear() {
        if (!isRemoveSupported()) {
            try {
                resetFull();
                getMap().clear();
                fail("Expected UnsupportedOperationException on clear");
            } catch (final UnsupportedOperationException ex) {}
            return;
        }

        resetEmpty();
        getMap().clear();
        getConfirmed().clear();
        verify();

        resetFull();
        getMap().clear();
        getConfirmed().clear();
        verify();
    }

    /**
     * Tests Map.containsKey(Object) by verifying it returns false for all
     * sample keys on a map created using an empty map and returns true for
     * all sample keys returned on a full map.
     */

    /**
     * Tests Map.containsValue(Object) by verifying it returns false for all
     * sample values on an empty map and returns true for all sample values on
     * a full map.
     */


    /**
     * Tests Map.equals(Object)
     */

    /**
     * Tests Map.get(Object)
     */

    /**
     * Tests Map.hashCode()
     */

    /**
     * Tests Map.toString().  Since the format of the string returned by the
     * toString() method is not defined in the Map interface, there is no
     * common way to test the results of the toString() method.  Therefore,
     * it is encouraged that Map implementations override this test with one
     * that checks the format matches any format defined in its API.  This
     * default implementation just verifies that the toString() method does
     * not return null.
     */

    /**
     * Compare the current serialized form of the Map
     * against the canonical version in SVN.
     */

    /**
     * Compare the current serialized form of the Map
     * against the canonical version in SVN.
     */

    /**
     * Tests Map.put(Object, Object)
     */

    /**
     * Tests Map.put(null, value)
     */
    public void testMapPutNullKey() {
        resetFull();
        final V[] values = getSampleValues();

        if (isPutAddSupported()) {
            if (isAllowNullKey()) {
                getMap().put(null, values[0]);
            } else {
                try {
                    getMap().put(null, values[0]);
                    fail("put(null, value) should throw NPE/IAE");
                } catch (final NullPointerException ex) {
                } catch (final IllegalArgumentException ex) {}
            }
        }
    }

    /**
     * Tests Map.put(null, value)
     */
    public void testMapPutNullValue() {
        resetFull();
        final K[] keys = getSampleKeys();

        if (isPutAddSupported()) {
            if (isAllowNullValue()) {
                getMap().put(keys[0], null);
            } else {
                try {
                    getMap().put(keys[0], null);
                    fail("put(key, null) should throw NPE/IAE");
                } catch (final NullPointerException ex) {
                } catch (final IllegalArgumentException ex) {}
            }
        }
    }

    /**
     * Tests Map.putAll(map)
     */

    /**
     * Tests Map.remove(Object)
     */

    //-----------------------------------------------------------------------
    /**
     * Tests that the {@link Map#values} collection is backed by
     * the underlying map for clear().
     */

    /**
     * Tests that the {@link Map#keySet} collection is backed by
     * the underlying map for clear().
     */

    /**
     * Tests that the {@link Map#entrySet()} collection is backed by
     * the underlying map for clear().
     */

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    /**
     * Tests that the {@link Map#values} collection is backed by
     * the underlying map by removing from the values collection
     * and testing if the value was removed from the map.
     * <p>
     * We should really test the "vice versa" case--that values removed
     * from the map are removed from the values collection--also,
     * but that's a more difficult test to construct (lacking a
     * "removeValue" method.)
     * </p>
     * <p>
     * See bug <a href="http://issues.apache.org/bugzilla/show_bug.cgi?id=9573">
     * 9573</a>.
     * </p>
     */

    /**
     * Tests values.removeAll.
     */

    /**
     * Test values.retainAll.
     */

    /**
     * Verifies that values.iterator.remove changes the underlying map.
     */

    /**
     * Tests that the {@link Map#keySet} set is backed by
     * the underlying map by removing from the keySet set
     * and testing if the key was removed from the map.
     */

    /**
     * Test keySet.removeAll.
     */

    /**
     * Test keySet.retainAll.
     */

    /**
     * Verify that keySet.iterator.remove changes the underlying map.
     */

    /**
     * Tests that the {@link Map#entrySet} set is backed by
     * the underlying map by removing from the entrySet set
     * and testing if the entry was removed from the map.
     */

    /**
     * Test entrySet.removeAll.
     */

    /**
     * Test entrySet.retainAll.
     */

    /**
     * Verify that entrySet.iterator.remove changes the underlying map.
     */

    /**
     * Utility methods to create an array of Map.Entry objects
     * out of the given key and value arrays.<P>
     *
     * @param keys    the array of keys
     * @param values  the array of values
     * @return an array of Map.Entry of those keys to those values
     */
    @SuppressWarnings("unchecked")
    private Map.Entry<K, V>[] makeEntryArray(final K[] keys, final V[] values) {
        final Map.Entry<K, V>[] result = new Map.Entry[keys.length];
        for (int i = 0; i < keys.length; i++) {
            final Map<K, V> map = makeConfirmedMap();
            map.put(keys[i], values[i]);
            result[i] = map.entrySet().iterator().next();
        }
        return result;
    }

    /**
     * Bulk test {@link Map#entrySet()}.  This method runs through all of
     * the tests in {@link AbstractSetTest}.
     * After modification operations, {@link #verify()} is invoked to ensure
     * that the map and the other collection views are still valid.
     *
     * @return a {@link AbstractSetTest} instance for testing the map's entry set
     */
    public BulkTest bulkTestMapEntrySet() {
        return new TestMapEntrySet();
    }

    public class TestMapEntrySet extends AbstractSetTest<Map.Entry<K, V>> {
        public TestMapEntrySet() {
            super("MapEntrySet");
        }

        // Have to implement manually; entrySet doesn't support addAll
        /**
         * {@inheritDoc}
         */
        @Override
        public Entry<K, V>[] getFullElements() {
            return getFullNonNullElements();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Map.Entry<K, V>[] getFullNonNullElements() {
            final K[] k = getSampleKeys();
            final V[] v = getSampleValues();
            return makeEntryArray(k, v);
        }

        // Have to implement manually; entrySet doesn't support addAll
        @Override
        public Map.Entry<K, V>[] getOtherElements() {
            final K[] k = getOtherKeys();
            final V[] v = getOtherValues();
            return makeEntryArray(k, v);
        }

        @Override
        public Set<Map.Entry<K, V>> makeObject() {
            return AbstractMapTest_OE25Dev.this.makeObject().entrySet();
        }

        @Override
        public Set<Map.Entry<K, V>> makeFullCollection() {
            return makeFullMap().entrySet();
        }

        @Override
        public boolean isAddSupported() {
            // Collection views don't support add operations.
            return false;
        }

        @Override
        public boolean isRemoveSupported() {
            // Entry set should only support remove if map does
            return AbstractMapTest_OE25Dev.this.isRemoveSupported();
        }

        public boolean isGetStructuralModify() {
            return AbstractMapTest_OE25Dev.this.isGetStructuralModify();
        }

        @Override
        public boolean areEqualElementsDistinguishable() {
            return AbstractMapTest_OE25Dev.this.areEqualElementsDistinguishable();
        }

        @Override
        public boolean isTestSerialization() {
            return false;
        }

        @Override
        public void resetFull() {
            AbstractMapTest_OE25Dev.this.resetFull();
            setCollection(AbstractMapTest_OE25Dev.this.getMap().entrySet());
            TestMapEntrySet.this.setConfirmed(AbstractMapTest_OE25Dev.this.getConfirmed().entrySet());
        }

        @Override
        public void resetEmpty() {
            AbstractMapTest_OE25Dev.this.resetEmpty();
            setCollection(AbstractMapTest_OE25Dev.this.getMap().entrySet());
            TestMapEntrySet.this.setConfirmed(AbstractMapTest_OE25Dev.this.getConfirmed().entrySet());
        }

        public Map.Entry<K, V> getEntry(final Iterator<Map.Entry<K, V>> itConfirmed, final K key) {
            Map.Entry<K, V> entry = null;
            while (itConfirmed.hasNext()) {
                final Map.Entry<K, V> temp = itConfirmed.next();
                if (temp.getKey() == null) {
                    if (key == null) {
                        entry = temp;
                        break;
                    }
                } else if (temp.getKey().equals(key)) {
                    entry = temp;
                    break;
                }
            }
            assertNotNull("No matching entry in map for key '" + key + "'", entry);
            return entry;
        }

        @Override
        public void verify() {
            super.verify();
            AbstractMapTest_OE25Dev.this.verify();
        }
    }


    /**
     * Bulk test {@link Map#keySet()}.  This method runs through all of
     * the tests in {@link AbstractSetTest}.
     * After modification operations, {@link #verify()} is invoked to ensure
     * that the map and the other collection views are still valid.
     *
     * @return a {@link AbstractSetTest} instance for testing the map's key set
     */
    public BulkTest bulkTestMapKeySet() {
        return new TestMapKeySet();
    }

    public class TestMapKeySet extends AbstractSetTest<K> {
        public TestMapKeySet() {
            super("");
        }

        @Override
        public K[] getFullElements() {
            return getSampleKeys();
        }

        @Override
        public K[] getOtherElements() {
            return getOtherKeys();
        }

        @Override
        public Set<K> makeObject() {
            return AbstractMapTest_OE25Dev.this.makeObject().keySet();
        }

        @Override
        public Set<K> makeFullCollection() {
            return AbstractMapTest_OE25Dev.this.makeFullMap().keySet();
        }

        @Override
        public boolean isNullSupported() {
            return AbstractMapTest_OE25Dev.this.isAllowNullKey();
        }

        @Override
        public boolean isAddSupported() {
            return false;
        }

        @Override
        public boolean isRemoveSupported() {
            return AbstractMapTest_OE25Dev.this.isRemoveSupported();
        }

        @Override
        public boolean isTestSerialization() {
            return false;
        }

        @Override
        public void resetEmpty() {
            AbstractMapTest_OE25Dev.this.resetEmpty();
            setCollection(AbstractMapTest_OE25Dev.this.getMap().keySet());
            TestMapKeySet.this.setConfirmed(AbstractMapTest_OE25Dev.this.getConfirmed().keySet());
        }

        @Override
        public void resetFull() {
            AbstractMapTest_OE25Dev.this.resetFull();
            setCollection(AbstractMapTest_OE25Dev.this.getMap().keySet());
            TestMapKeySet.this.setConfirmed(AbstractMapTest_OE25Dev.this.getConfirmed().keySet());
        }

        @Override
        public void verify() {
            super.verify();
            AbstractMapTest_OE25Dev.this.verify();
        }
    }

    /**
     * Bulk test {@link Map#values()}.  This method runs through all of
     * the tests in {@link AbstractCollectionTest}.
     * After modification operations, {@link #verify()} is invoked to ensure
     * that the map and the other collection views are still valid.
     *
     * @return a {@link AbstractCollectionTest} instance for testing the map's
     *    values collection
     */
    public BulkTest bulkTestMapValues() {
        return new TestMapValues();
    }

    public class TestMapValues extends AbstractCollectionTest<V> {
        public TestMapValues() {
            super("");
        }

        @Override
        public V[] getFullElements() {
            return getSampleValues();
        }

        @Override
        public V[] getOtherElements() {
            return getOtherValues();
        }

        @Override
        public Collection<V> makeObject() {
            return AbstractMapTest_OE25Dev.this.makeObject().values();
        }

        @Override
        public Collection<V> makeFullCollection() {
            return AbstractMapTest_OE25Dev.this.makeFullMap().values();
        }

        @Override
        public boolean isNullSupported() {
            return AbstractMapTest_OE25Dev.this.isAllowNullKey();
        }

        @Override
        public boolean isAddSupported() {
            return false;
        }

        @Override
        public boolean isRemoveSupported() {
            return AbstractMapTest_OE25Dev.this.isRemoveSupported();
        }

        @Override
        public boolean isTestSerialization() {
            return false;
        }

        @Override
        public boolean areEqualElementsDistinguishable() {
            // equal values are associated with different keys, so they are
            // distinguishable.
            return true;
        }

        @Override
        public Collection<V> makeConfirmedCollection() {
            // never gets called, reset methods are overridden
            return null;
        }

        @Override
        public Collection<V> makeConfirmedFullCollection() {
            // never gets called, reset methods are overridden
            return null;
        }

        @Override
        public void resetFull() {
            AbstractMapTest_OE25Dev.this.resetFull();
            setCollection(map.values());
            TestMapValues.this.setConfirmed(AbstractMapTest_OE25Dev.this.getConfirmed().values());
        }

        @Override
        public void resetEmpty() {
            AbstractMapTest_OE25Dev.this.resetEmpty();
            setCollection(map.values());
            TestMapValues.this.setConfirmed(AbstractMapTest_OE25Dev.this.getConfirmed().values());
        }

        @Override
        public void verify() {
            super.verify();
            AbstractMapTest_OE25Dev.this.verify();
        }

        // TODO: should test that a remove on the values collection view
        // removes the proper mapping and not just any mapping that may have
        // the value equal to the value returned from the values iterator.
    }


    /**
     * Resets the {@link #map}, {@link #entrySet}, {@link #keySet},
     * {@link #values} and {@link #confirmed} fields to empty.
     */
    public void resetEmpty() {
        this.map = makeObject();
        views();
        this.confirmed = makeConfirmedMap();
    }

    /**
     * Resets the {@link #map}, {@link #entrySet}, {@link #keySet},
     * {@link #values} and {@link #confirmed} fields to full.
     */
    public void resetFull() {
        this.map = makeFullMap();
        views();
        this.confirmed = makeConfirmedMap();
        final K[] k = getSampleKeys();
        final V[] v = getSampleValues();
        for (int i = 0; i < k.length; i++) {
            confirmed.put(k[i], v[i]);
        }
    }

    /**
     * Resets the collection view fields.
     */
    private void views() {
        this.keySet = getMap().keySet();
        // see verifyValues: retrieve the values collection only when verifying them
        // this.values = getMap().values();
        this.entrySet = getMap().entrySet();
    }

    /**
     * Verifies that {@link #map} is still equal to {@link #confirmed}.
     * This method checks that the map is equal to the HashMap,
     * <I>and</I> that the map's collection views are still equal to
     * the HashMap's collection views.  An <Code>equals</Code> test
     * is done on the maps and their collection views; their size and
     * <Code>isEmpty</Code> results are compared; their hashCodes are
     * compared; and <Code>containsAll</Code> tests are run on the
     * collection views.
     */
    public void verify() {
        verifyMap();
        verifyEntrySet();
        verifyKeySet();
        verifyValues();
    }

    public void verifyMap() {
        final int size = getConfirmed().size();
        final boolean empty = getConfirmed().isEmpty();
        assertEquals("Map should be same size as HashMap", size, getMap().size());
        assertEquals("Map should be empty if HashMap is", empty, getMap().isEmpty());
        assertEquals("hashCodes should be the same", getConfirmed().hashCode(), getMap().hashCode());
        // changing the order of the assertion below fails for LRUMap because confirmed is
        // another collection (e.g. treemap) and confirmed.equals() creates a normal iterator (not
        // #mapIterator()), which modifies the parent expected modCount of the map object, causing
        // concurrent modification exceptions.
        // Because of this we have assertEquals(map, confirmed), and not the other way around.
        assertEquals("Map should still equal HashMap", map, confirmed);
        assertTrue("Map should still equal HashMap", getMap().equals(getConfirmed()));
    }

    public void verifyEntrySet() {
        final int size = getConfirmed().size();
        final boolean empty = getConfirmed().isEmpty();
        assertEquals("entrySet should be same size as HashMap's" +
                     "\nTest: " + entrySet + "\nReal: " + getConfirmed().entrySet(),
                     size, entrySet.size());
        assertEquals("entrySet should be empty if HashMap is" +
                     "\nTest: " + entrySet + "\nReal: " + getConfirmed().entrySet(),
                     empty, entrySet.isEmpty());
        assertTrue("entrySet should contain all HashMap's elements" +
                   "\nTest: " + entrySet + "\nReal: " + getConfirmed().entrySet(),
                   entrySet.containsAll(getConfirmed().entrySet()));
        assertEquals("entrySet hashCodes should be the same" +
                     "\nTest: " + entrySet + "\nReal: " + getConfirmed().entrySet(),
                     getConfirmed().entrySet().hashCode(), entrySet.hashCode());
        assertEquals("Map's entry set should still equal HashMap's",
                     getConfirmed().entrySet(), entrySet);
    }

    public void verifyKeySet() {
        final int size = getConfirmed().size();
        final boolean empty = getConfirmed().isEmpty();
        assertEquals("keySet should be same size as HashMap's" +
                     "\nTest: " + keySet + "\nReal: " + getConfirmed().keySet(),
                     size, keySet.size());
        assertEquals("keySet should be empty if HashMap is" +
                     "\nTest: " + keySet + "\nReal: " + getConfirmed().keySet(),
                     empty, keySet.isEmpty());
        assertTrue("keySet should contain all HashMap's elements" +
                   "\nTest: " + keySet + "\nReal: " + getConfirmed().keySet(),
                   keySet.containsAll(getConfirmed().keySet()));
        assertEquals("keySet hashCodes should be the same" +
                     "\nTest: " + keySet + "\nReal: " + getConfirmed().keySet(),
                     getConfirmed().keySet().hashCode(), keySet.hashCode());
        assertEquals("Map's key set should still equal HashMap's",
                getConfirmed().keySet(), keySet);
    }

    public void verifyValues() {
        final List<V> known = new ArrayList<>(getConfirmed().values());

        values = getMap().values();

        final List<V> test = new ArrayList<>(values);

        final int size = getConfirmed().size();
        final boolean empty = getConfirmed().isEmpty();
        assertEquals("values should be same size as HashMap's" +
                     "\nTest: " + test + "\nReal: " + known,
                     size, values.size());
        assertEquals("values should be empty if HashMap is" +
                     "\nTest: " + test + "\nReal: " + known,
                     empty, values.isEmpty());
        assertTrue("values should contain all HashMap's elements" +
                   "\nTest: " + test + "\nReal: " + known,
                    test.containsAll(known));
        assertTrue("values should contain all HashMap's elements" +
                   "\nTest: " + test + "\nReal: " + known,
                   known.containsAll(test));
        // originally coded to use a HashBag, but now separate jar so...
        for (final V v : known) {
            final boolean removed = test.remove(v);
            assertTrue("Map's values should still equal HashMap's", removed);
        }
        assertTrue("Map's values should still equal HashMap's", test.isEmpty());
    }

    /**
     * Erases any leftover instance variables by setting them to null.
     */
    @Override
    public void tearDown() throws Exception {
        map = null;
        keySet = null;
        entrySet = null;
        values = null;
        confirmed = null;
    }

    /**
     * Get the map.
     * @return Map<K,V>
     */
    public Map<K, V> getMap() {
        return map;
    }

    /**
     * Get the confirmed.
     * @return Map<K,V>
     */
    public Map<K, V> getConfirmed() {
        return confirmed;
    }
}
