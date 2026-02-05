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
package org.apache.commons.collections4.bag;

import junit.framework.Test;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.BulkTest;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.collection.TransformedCollectionTest;

/**
 * Extension of {@link AbstractBagTest} for exercising the {@link TransformedBag}
 * implementation.
 *
 * @since 3.0
 */
public class TransformedBagTest_OE25Dev<T> extends AbstractBagTest<T> {

    public TransformedBagTest_OE25Dev(final String testName) {
        super(testName);
    }

    public static Test suite() {
        return new junit.framework.TestSuite(TransformedBagTest_OE25Dev.class);
    }

    //-----------------------------------------------------------------------

    @Override
    @SuppressWarnings("unchecked")
    public Bag<T> makeObject() {
        return TransformedBag.transformingBag(new HashBag<T>(),
                (Transformer<T, T>) TransformedCollectionTest.NOOP_TRANSFORMER);
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

//    public void testCreate() throws Exception {
//        Bag<T> bag = makeObject();
//        writeExternalFormToDisk((java.io.Serializable) bag, "src/test/resources/data/test/TransformedBag.emptyCollection.version4.obj");
//        bag = makeFullCollection();
//        writeExternalFormToDisk((java.io.Serializable) bag, "src/test/resources/data/test/TransformedBag.fullCollection.version4.obj");
//    }

public void testTransformedBag_1_oe() {
        //T had better be Object!
        final Bag<T> bag = TransformedBag.transformingBag(new HashBag<T>(),
                (Transformer<T, T>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        assertEquals(0, bag.size());
    }

public void testTransformedBag_2_oe() {
        //T had better be Object!
        final Bag<T> bag = TransformedBag.transformingBag(new HashBag<T>(),
                (Transformer<T, T>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        // removed other assertion
        final Object[] els = new Object[] {"1", "3", "5", "7", "2", "4", "6"};
        for (int i = 0; i < els.length; i++) {
            bag.add((T) els[i]);
            assertEquals(i + 1, bag.size());
    }
    }

public void testTransformedBag_3_oe() {
        //T had better be Object!
        final Bag<T> bag = TransformedBag.transformingBag(new HashBag<T>(),
                (Transformer<T, T>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        // removed other assertion
        final Object[] els = new Object[] {"1", "3", "5", "7", "2", "4", "6"};
        for (int i = 0; i < els.length; i++) {
            bag.add((T) els[i]);
            // removed other assertion
            assertEquals(true, bag.contains(Integer.valueOf((String) els[i])));
    }
    }

public void testTransformedBag_4_oe() {
        //T had better be Object!
        final Bag<T> bag = TransformedBag.transformingBag(new HashBag<T>(),
                (Transformer<T, T>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        // removed other assertion
        final Object[] els = new Object[] {"1", "3", "5", "7", "2", "4", "6"};
        for (int i = 0; i < els.length; i++) {
            bag.add((T) els[i]);
            // removed other assertion
            // removed other assertion
            assertEquals(false, bag.contains(els[i]));
    }
    }

public void testTransformedBag_5_oe() {
        //T had better be Object!
        final Bag<T> bag = TransformedBag.transformingBag(new HashBag<T>(),
                (Transformer<T, T>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        // removed other assertion
        final Object[] els = new Object[] {"1", "3", "5", "7", "2", "4", "6"};
        for (int i = 0; i < els.length; i++) {
            bag.add((T) els[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        assertEquals(false, bag.remove(els[0]));
    }

public void testTransformedBag_6_oe() {
        //T had better be Object!
        final Bag<T> bag = TransformedBag.transformingBag(new HashBag<T>(),
                (Transformer<T, T>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        // removed other assertion
        final Object[] els = new Object[] {"1", "3", "5", "7", "2", "4", "6"};
        for (int i = 0; i < els.length; i++) {
            bag.add((T) els[i]);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        assertEquals(true, bag.remove(Integer.valueOf((String) els[0])));
    }

public void testTransformedBag_decorateTransform_1_oe() {
        final Bag<T> originalBag = new HashBag<>();
        final Object[] els = new Object[] {"1", "3", "5", "7", "2", "4", "6"};
        for (final Object el : els) {
            originalBag.add((T) el);
        }
        final Bag<T> bag = TransformedBag.transformedBag(originalBag,
                (Transformer<T, T>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        assertEquals(els.length, bag.size());
    }

public void testTransformedBag_decorateTransform_2_oe() {
        final Bag<T> originalBag = new HashBag<>();
        final Object[] els = new Object[] {"1", "3", "5", "7", "2", "4", "6"};
        for (final Object el : els) {
            originalBag.add((T) el);
        }
        final Bag<T> bag = TransformedBag.transformedBag(originalBag,
                (Transformer<T, T>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        // removed other assertion
        for (final Object el : els) {
            assertEquals(true, bag.contains(Integer.valueOf((String) el)));
    }
    }

public void testTransformedBag_decorateTransform_3_oe() {
        final Bag<T> originalBag = new HashBag<>();
        final Object[] els = new Object[] {"1", "3", "5", "7", "2", "4", "6"};
        for (final Object el : els) {
            originalBag.add((T) el);
        }
        final Bag<T> bag = TransformedBag.transformedBag(originalBag,
                (Transformer<T, T>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        // removed other assertion
        for (final Object el : els) {
            // removed other assertion
            assertEquals(false, bag.contains(el));
    }
    }

public void testTransformedBag_decorateTransform_4_oe() {
        final Bag<T> originalBag = new HashBag<>();
        final Object[] els = new Object[] {"1", "3", "5", "7", "2", "4", "6"};
        for (final Object el : els) {
            originalBag.add((T) el);
        }
        final Bag<T> bag = TransformedBag.transformedBag(originalBag,
                (Transformer<T, T>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        // removed other assertion
        for (final Object el : els) {
            // removed other assertion
            // removed other assertion
        }

        assertEquals(false, bag.remove(els[0]));
    }

public void testTransformedBag_decorateTransform_5_oe() {
        final Bag<T> originalBag = new HashBag<>();
        final Object[] els = new Object[] {"1", "3", "5", "7", "2", "4", "6"};
        for (final Object el : els) {
            originalBag.add((T) el);
        }
        final Bag<T> bag = TransformedBag.transformedBag(originalBag,
                (Transformer<T, T>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        // removed other assertion
        for (final Object el : els) {
            // removed other assertion
            // removed other assertion
        }

        // removed other assertion
        assertEquals(true, bag.remove(Integer.valueOf((String) els[0])));
    }

}
