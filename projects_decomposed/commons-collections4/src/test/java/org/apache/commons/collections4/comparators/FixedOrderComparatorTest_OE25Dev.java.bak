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
package org.apache.commons.collections4.comparators;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

/**
 * Test class for FixedOrderComparator.
 *
 */
public class FixedOrderComparatorTest_OE25Dev extends AbstractComparatorTest<String> {

    /**
     * Top cities of the world, by population including metro areas.
     */
    private static final String topCities[] = new String[] {
        "Tokyo",
        "Mexico City",
        "Mumbai",
        "Sao Paulo",
        "New York",
        "Shanghai",
        "Lagos",
        "Los Angeles",
        "Calcutta",
        "Buenos Aires"
    };

    //
    // Initialization and busywork
    //

    public FixedOrderComparatorTest_OE25Dev(final String name) {
        super(name);
    }

    //
    // Set up and tear down
    //

    @Override
    public Comparator<String> makeObject() {
        final FixedOrderComparator<String> comparator = new FixedOrderComparator<>(topCities);
        return comparator;
    }

    @Override
    public List<String> getComparableObjectsOrdered() {
        return Arrays.asList(topCities);
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

//    public void testCreate() throws Exception {
//        writeExternalFormToDisk((java.io.Serializable) makeObject(), "src/test/resources/data/test/FixedOrderComparator.version4.obj");
//    }

    //
    // The tests
    //

    /**
     * Tests that the constructor plus add method compares items properly.
     */

    /**
     * Tests that the array constructor compares items properly.
     */

    /**
     * Tests the list constructor.
     */

    /**
     * Tests addAsEqual method.
     */

    /**
     * Tests whether or not updates are disabled after a comparison is made.
     */

    //
    // Helper methods
    //

    /** Shuffles the keys and asserts that the comparator sorts them back to
     * their original order.
     */
    private void assertComparatorYieldsOrder(final String[] orderedObjects,
                                             final Comparator<String> comparator) {
        final String[] keys = orderedObjects.clone();

        // shuffle until the order changes.  It's extremely rare that
        // this requires more than one shuffle.

        boolean isInNewOrder = false;
        final Random rand = new Random();
        while (keys.length > 1 && !isInNewOrder) {
            // shuffle:
            for (int i = keys.length-1; i > 0; i--) {
                final String swap = keys[i];
                final int j = rand.nextInt(i+1);
                keys[i] = keys[j];
                keys[j] = swap;
            }

            // testShuffle
            for (int i = 0; i < keys.length && !isInNewOrder; i++) {
                if( !orderedObjects[i].equals(keys[i])) {
                    isInNewOrder = true;
                }
            }
        }

        // The real test:  sort and make sure they come out right.

        Arrays.sort(keys, comparator);

        for (int i = 0; i < orderedObjects.length; i++) {
            assertEquals(orderedObjects[i], keys[i]);
        }
    }

    @Test
    public void testAddAsEqual_1_oe() {
        final FixedOrderComparator<String> comparator = new FixedOrderComparator<>(topCities);
        comparator.addAsEqual("New York", "Minneapolis");
        assertEquals(0, comparator.compare("New York", "Minneapolis"));
    }

    @Test
    public void testAddAsEqual_2_oe() {
        final FixedOrderComparator<String> comparator = new FixedOrderComparator<>(topCities);
        comparator.addAsEqual("New York", "Minneapolis");
        // removed other assertion
        assertEquals(-1, comparator.compare("Tokyo", "Minneapolis"));
    }

    @Test
    public void testAddAsEqual_3_oe() {
        final FixedOrderComparator<String> comparator = new FixedOrderComparator<>(topCities);
        comparator.addAsEqual("New York", "Minneapolis");
        // removed other assertion
        // removed other assertion
        assertEquals(1, comparator.compare("Shanghai", "Minneapolis"));
    }

    @Test
    public void testLock_1_oe() {
        final FixedOrderComparator<String> comparator = new FixedOrderComparator<>(topCities);
        assertEquals(false, comparator.isLocked());
    }

    @Test
    public void testLock_2_oe() {
        final FixedOrderComparator<String> comparator = new FixedOrderComparator<>(topCities);
        // removed other assertion
        comparator.compare("New York", "Tokyo");
        assertEquals(true, comparator.isLocked());
    }

    @Test
    public void testUnknownObjectBehavior_3_oe() {
        FixedOrderComparator<String> comparator = new FixedOrderComparator<>(topCities);
        try {
            comparator.compare("New York", "Minneapolis");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // success-- ignore
        }
        try {
            comparator.compare("Minneapolis", "New York");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // success-- ignore
        }
        assertEquals(FixedOrderComparator.UnknownObjectBehavior.EXCEPTION, comparator.getUnknownObjectBehavior());
    }

    @Test
    public void testUnknownObjectBehavior_4_oe() {
        FixedOrderComparator<String> comparator = new FixedOrderComparator<>(topCities);
        try {
            comparator.compare("New York", "Minneapolis");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // success-- ignore
        }
        try {
            comparator.compare("Minneapolis", "New York");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // success-- ignore
        }
        // removed other assertion

        comparator = new FixedOrderComparator<>(topCities);
        comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.BEFORE);
        assertEquals(FixedOrderComparator.UnknownObjectBehavior.BEFORE, comparator.getUnknownObjectBehavior());
    }

    @Test
    public void testUnknownObjectBehavior_6_oe() {
        FixedOrderComparator<String> comparator = new FixedOrderComparator<>(topCities);
        try {
            comparator.compare("New York", "Minneapolis");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // success-- ignore
        }
        try {
            comparator.compare("Minneapolis", "New York");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // success-- ignore
        }
        // removed other assertion

        comparator = new FixedOrderComparator<>(topCities);
        comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.BEFORE);
        // removed other assertion
        LinkedList<String> keys = new LinkedList<>(Arrays.asList(topCities));
        keys.addFirst("Minneapolis");
        // removed other assertion

        assertEquals(-1, comparator.compare("Minneapolis", "New York"));
    }

    @Test
    public void testUnknownObjectBehavior_7_oe() {
        FixedOrderComparator<String> comparator = new FixedOrderComparator<>(topCities);
        try {
            comparator.compare("New York", "Minneapolis");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // success-- ignore
        }
        try {
            comparator.compare("Minneapolis", "New York");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // success-- ignore
        }
        // removed other assertion

        comparator = new FixedOrderComparator<>(topCities);
        comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.BEFORE);
        // removed other assertion
        LinkedList<String> keys = new LinkedList<>(Arrays.asList(topCities));
        keys.addFirst("Minneapolis");
        // removed other assertion

        // removed other assertion
        assertEquals( 1, comparator.compare("New York", "Minneapolis"));
    }

    @Test
    public void testUnknownObjectBehavior_8_oe() {
        FixedOrderComparator<String> comparator = new FixedOrderComparator<>(topCities);
        try {
            comparator.compare("New York", "Minneapolis");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // success-- ignore
        }
        try {
            comparator.compare("Minneapolis", "New York");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // success-- ignore
        }
        // removed other assertion

        comparator = new FixedOrderComparator<>(topCities);
        comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.BEFORE);
        // removed other assertion
        LinkedList<String> keys = new LinkedList<>(Arrays.asList(topCities));
        keys.addFirst("Minneapolis");
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals( 0, comparator.compare("Minneapolis", "St Paul"));
    }

    @Test
    public void testUnknownObjectBehavior_10_oe() {
        FixedOrderComparator<String> comparator = new FixedOrderComparator<>(topCities);
        try {
            comparator.compare("New York", "Minneapolis");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // success-- ignore
        }
        try {
            comparator.compare("Minneapolis", "New York");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // success-- ignore
        }
        // removed other assertion

        comparator = new FixedOrderComparator<>(topCities);
        comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.BEFORE);
        // removed other assertion
        LinkedList<String> keys = new LinkedList<>(Arrays.asList(topCities));
        keys.addFirst("Minneapolis");
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        comparator = new FixedOrderComparator<>(topCities);
        comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.AFTER);
        keys = new LinkedList<>(Arrays.asList(topCities));
        keys.add("Minneapolis");
        // removed other assertion

        assertEquals( 1, comparator.compare("Minneapolis", "New York"));
    }

    @Test
    public void testUnknownObjectBehavior_11_oe() {
        FixedOrderComparator<String> comparator = new FixedOrderComparator<>(topCities);
        try {
            comparator.compare("New York", "Minneapolis");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // success-- ignore
        }
        try {
            comparator.compare("Minneapolis", "New York");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // success-- ignore
        }
        // removed other assertion

        comparator = new FixedOrderComparator<>(topCities);
        comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.BEFORE);
        // removed other assertion
        LinkedList<String> keys = new LinkedList<>(Arrays.asList(topCities));
        keys.addFirst("Minneapolis");
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        comparator = new FixedOrderComparator<>(topCities);
        comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.AFTER);
        keys = new LinkedList<>(Arrays.asList(topCities));
        keys.add("Minneapolis");
        // removed other assertion

        // removed other assertion
        assertEquals(-1, comparator.compare("New York", "Minneapolis"));
    }

    @Test
    public void testUnknownObjectBehavior_12_oe() {
        FixedOrderComparator<String> comparator = new FixedOrderComparator<>(topCities);
        try {
            comparator.compare("New York", "Minneapolis");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // success-- ignore
        }
        try {
            comparator.compare("Minneapolis", "New York");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // success-- ignore
        }
        // removed other assertion

        comparator = new FixedOrderComparator<>(topCities);
        comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.BEFORE);
        // removed other assertion
        LinkedList<String> keys = new LinkedList<>(Arrays.asList(topCities));
        keys.addFirst("Minneapolis");
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        comparator = new FixedOrderComparator<>(topCities);
        comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.AFTER);
        keys = new LinkedList<>(Arrays.asList(topCities));
        keys.add("Minneapolis");
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals( 0, comparator.compare("Minneapolis", "St Paul"));
    }

    @Test
    public void testConstructorPlusAdd_1_oe_1_oe() {
        final FixedOrderComparator<String> comparator = new FixedOrderComparator<>();
        for (final String topCitie : topCities) {
            comparator.add(topCitie);
        }
        final String[] keys = topCities.clone();
                final String[] orderedObjects0 = keys;
        final Comparator<String> comparator0 = comparator;
        final String[] keys0 = orderedObjects0.clone();
        
                // shuffle until the order changes.  It's extremely rare that
                // this requires more than one shuffle.
        
                boolean isInNewOrder0 = false;
                final Random rand0 = new Random();
                while (keys0.length > 1 && !isInNewOrder0) {
                    // shuffle:
                    for (int i0 = keys0.length-1; i0 > 0; i0--) {
                        final String swap0 = keys0[i0];
                        final int j0 = rand0.nextInt(i0+1);
                        keys0[i0] = keys0[j0];
                        keys0[j0] = swap0;
                    }
        
                    // testShuffle
                    for (int i0 = 0; i0 < keys0.length && !isInNewOrder0; i0++) {
                        if( !orderedObjects0[i0].equals(keys0[i0])) {
                            isInNewOrder0 = true;
                        }
                    }
                }
        
                // The real test:  sort and make sure they come out right.
        
                Arrays.sort(keys0, comparator0);
        
                for (int i0 = 0; i0 < orderedObjects0.length; i0++) {
                    assertEquals(orderedObjects0[i0], keys0[i0]);
    }
    }

    @Test
    public void testArrayConstructor_1_oe_1_oe() {
        final String[] keys = topCities.clone();
        final String[] topCitiesForTest = topCities.clone();
        final FixedOrderComparator<String> comparator = new FixedOrderComparator<>(topCitiesForTest);
                final String[] orderedObjects0 = keys;
        final Comparator<String> comparator0 = comparator;
        final String[] keys0 = orderedObjects0.clone();
        
                // shuffle until the order changes.  It's extremely rare that
                // this requires more than one shuffle.
        
                boolean isInNewOrder0 = false;
                final Random rand0 = new Random();
                while (keys0.length > 1 && !isInNewOrder0) {
                    // shuffle:
                    for (int i0 = keys0.length-1; i0 > 0; i0--) {
                        final String swap0 = keys0[i0];
                        final int j0 = rand0.nextInt(i0+1);
                        keys0[i0] = keys0[j0];
                        keys0[j0] = swap0;
                    }
        
                    // testShuffle
                    for (int i0 = 0; i0 < keys0.length && !isInNewOrder0; i0++) {
                        if( !orderedObjects0[i0].equals(keys0[i0])) {
                            isInNewOrder0 = true;
                        }
                    }
                }
        
                // The real test:  sort and make sure they come out right.
        
                Arrays.sort(keys0, comparator0);
        
                for (int i0 = 0; i0 < orderedObjects0.length; i0++) {
                    assertEquals(orderedObjects0[i0], keys0[i0]);
    }
    }

    @Test
    public void testArrayConstructor_2_oe_1_oe() {
        final String[] keys = topCities.clone();
        final String[] topCitiesForTest = topCities.clone();
        final FixedOrderComparator<String> comparator = new FixedOrderComparator<>(topCitiesForTest);
        // removed other assertion
        // test that changing input after constructor has no effect
        topCitiesForTest[0] = "Brighton";
                final String[] orderedObjects0 = keys;
        final Comparator<String> comparator0 = comparator;
        final String[] keys0 = orderedObjects0.clone();
        
                // shuffle until the order changes.  It's extremely rare that
                // this requires more than one shuffle.
        
                boolean isInNewOrder0 = false;
                final Random rand0 = new Random();
                while (keys0.length > 1 && !isInNewOrder0) {
                    // shuffle:
                    for (int i0 = keys0.length-1; i0 > 0; i0--) {
                        final String swap0 = keys0[i0];
                        final int j0 = rand0.nextInt(i0+1);
                        keys0[i0] = keys0[j0];
                        keys0[j0] = swap0;
                    }
        
                    // testShuffle
                    for (int i0 = 0; i0 < keys0.length && !isInNewOrder0; i0++) {
                        if( !orderedObjects0[i0].equals(keys0[i0])) {
                            isInNewOrder0 = true;
                        }
                    }
                }
        
                // The real test:  sort and make sure they come out right.
        
                Arrays.sort(keys0, comparator0);
        
                for (int i0 = 0; i0 < orderedObjects0.length; i0++) {
                    assertEquals(orderedObjects0[i0], keys0[i0]);
    }
    }

    @Test
    public void testListConstructor_1_oe_1_oe() {
        final String[] keys = topCities.clone();
        final List<String> topCitiesForTest = new LinkedList<>(Arrays.asList(topCities));
        final FixedOrderComparator<String> comparator = new FixedOrderComparator<>(topCitiesForTest);
                final String[] orderedObjects0 = keys;
        final Comparator<String> comparator0 = comparator;
        final String[] keys0 = orderedObjects0.clone();
        
                // shuffle until the order changes.  It's extremely rare that
                // this requires more than one shuffle.
        
                boolean isInNewOrder0 = false;
                final Random rand0 = new Random();
                while (keys0.length > 1 && !isInNewOrder0) {
                    // shuffle:
                    for (int i0 = keys0.length-1; i0 > 0; i0--) {
                        final String swap0 = keys0[i0];
                        final int j0 = rand0.nextInt(i0+1);
                        keys0[i0] = keys0[j0];
                        keys0[j0] = swap0;
                    }
        
                    // testShuffle
                    for (int i0 = 0; i0 < keys0.length && !isInNewOrder0; i0++) {
                        if( !orderedObjects0[i0].equals(keys0[i0])) {
                            isInNewOrder0 = true;
                        }
                    }
                }
        
                // The real test:  sort and make sure they come out right.
        
                Arrays.sort(keys0, comparator0);
        
                for (int i0 = 0; i0 < orderedObjects0.length; i0++) {
                    assertEquals(orderedObjects0[i0], keys0[i0]);
    }
    }

    @Test
    public void testListConstructor_2_oe_1_oe() {
        final String[] keys = topCities.clone();
        final List<String> topCitiesForTest = new LinkedList<>(Arrays.asList(topCities));
        final FixedOrderComparator<String> comparator = new FixedOrderComparator<>(topCitiesForTest);
        // removed other assertion
        // test that changing input after constructor has no effect
        topCitiesForTest.set(0, "Brighton");
                final String[] orderedObjects0 = keys;
        final Comparator<String> comparator0 = comparator;
        final String[] keys0 = orderedObjects0.clone();
        
                // shuffle until the order changes.  It's extremely rare that
                // this requires more than one shuffle.
        
                boolean isInNewOrder0 = false;
                final Random rand0 = new Random();
                while (keys0.length > 1 && !isInNewOrder0) {
                    // shuffle:
                    for (int i0 = keys0.length-1; i0 > 0; i0--) {
                        final String swap0 = keys0[i0];
                        final int j0 = rand0.nextInt(i0+1);
                        keys0[i0] = keys0[j0];
                        keys0[j0] = swap0;
                    }
        
                    // testShuffle
                    for (int i0 = 0; i0 < keys0.length && !isInNewOrder0; i0++) {
                        if( !orderedObjects0[i0].equals(keys0[i0])) {
                            isInNewOrder0 = true;
                        }
                    }
                }
        
                // The real test:  sort and make sure they come out right.
        
                Arrays.sort(keys0, comparator0);
        
                for (int i0 = 0; i0 < orderedObjects0.length; i0++) {
                    assertEquals(orderedObjects0[i0], keys0[i0]);
    }
    }

    @Test
    public void testUnknownObjectBehavior_5_oe_1_oe() {
        FixedOrderComparator<String> comparator = new FixedOrderComparator<>(topCities);
        try {
            comparator.compare("New York", "Minneapolis");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // success-- ignore
        }
        try {
            comparator.compare("Minneapolis", "New York");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // success-- ignore
        }
        // removed other assertion

        comparator = new FixedOrderComparator<>(topCities);
        comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.BEFORE);
        // removed other assertion
        LinkedList<String> keys = new LinkedList<>(Arrays.asList(topCities));
        keys.addFirst("Minneapolis");
                final String[] orderedObjects0 = keys.toArray(new String[0]);
        final Comparator<String> comparator0 = comparator;
        final String[] keys0 = orderedObjects0.clone();
        
                // shuffle until the order changes.  It's extremely rare that
                // this requires more than one shuffle.
        
                boolean isInNewOrder0 = false;
                final Random rand0 = new Random();
                while (keys0.length > 1 && !isInNewOrder0) {
                    // shuffle:
                    for (int i0 = keys0.length-1; i0 > 0; i0--) {
                        final String swap0 = keys0[i0];
                        final int j0 = rand0.nextInt(i0+1);
                        keys0[i0] = keys0[j0];
                        keys0[j0] = swap0;
                    }
        
                    // testShuffle
                    for (int i0 = 0; i0 < keys0.length && !isInNewOrder0; i0++) {
                        if( !orderedObjects0[i0].equals(keys0[i0])) {
                            isInNewOrder0 = true;
                        }
                    }
                }
        
                // The real test:  sort and make sure they come out right.
        
                Arrays.sort(keys0, comparator0);
        
                for (int i0 = 0; i0 < orderedObjects0.length; i0++) {
                    assertEquals(orderedObjects0[i0], keys0[i0]);
    }
    }

    @Test
    public void testUnknownObjectBehavior_9_oe_1_oe() {
        FixedOrderComparator<String> comparator = new FixedOrderComparator<>(topCities);
        try {
            comparator.compare("New York", "Minneapolis");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // success-- ignore
        }
        try {
            comparator.compare("Minneapolis", "New York");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // success-- ignore
        }
        // removed other assertion

        comparator = new FixedOrderComparator<>(topCities);
        comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.BEFORE);
        // removed other assertion
        LinkedList<String> keys = new LinkedList<>(Arrays.asList(topCities));
        keys.addFirst("Minneapolis");
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        comparator = new FixedOrderComparator<>(topCities);
        comparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.AFTER);
        keys = new LinkedList<>(Arrays.asList(topCities));
        keys.add("Minneapolis");
                final String[] orderedObjects0 = keys.toArray(new String[0]);
        final Comparator<String> comparator0 = comparator;
        final String[] keys0 = orderedObjects0.clone();
        
                // shuffle until the order changes.  It's extremely rare that
                // this requires more than one shuffle.
        
                boolean isInNewOrder0 = false;
                final Random rand0 = new Random();
                while (keys0.length > 1 && !isInNewOrder0) {
                    // shuffle:
                    for (int i0 = keys0.length-1; i0 > 0; i0--) {
                        final String swap0 = keys0[i0];
                        final int j0 = rand0.nextInt(i0+1);
                        keys0[i0] = keys0[j0];
                        keys0[j0] = swap0;
                    }
        
                    // testShuffle
                    for (int i0 = 0; i0 < keys0.length && !isInNewOrder0; i0++) {
                        if( !orderedObjects0[i0].equals(keys0[i0])) {
                            isInNewOrder0 = true;
                        }
                    }
                }
        
                // The real test:  sort and make sure they come out right.
        
                Arrays.sort(keys0, comparator0);
        
                for (int i0 = 0; i0 < orderedObjects0.length; i0++) {
                    assertEquals(orderedObjects0[i0], keys0[i0]);
    }
    }

}
