/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.configuration2.convert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@code DisabledListDelimiterHandler}. Note that some functionality of the base class is tested, too.
 *
 */
public class TestDisabledListDelimiterHandler_OE25Dev {
    /** An array with some test values. */
    private static final Object[] VALUES = {20130630213801L, "A test value", 5};

    /** Constant for a test string value. */
    private static final String STR_VALUE = "  A test, string; value! ";

    /**
     * Checks whether the passed in container contains the expected values.
     *
     * @param container the iterator to test
     */
    private static void checkIterator(final Iterable<?> container) {
        final Iterator<?> it = container.iterator();
        for (final Object o : VALUES) {
            assertEquals("Wrong value", o, it.next());
        }
        assertFalse("Iterator has too many objects", it.hasNext());
    }

    /** The instance to be tested. */
    private DisabledListDelimiterHandler handler;

    @Before
    public void setUp() throws Exception {
        handler = new DisabledListDelimiterHandler();
    }

    /**
     * Tests escapeList(). This operation is not supported.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testEscapeList() {
        handler.escapeList(Arrays.asList(VALUES), ListDelimiterHandler.NOOP_TRANSFORMER);
    }

    /**
     * Tests whether a non-string value is correctly escaped. The object should not be modified.
     */

    /**
     * Tests whether the transformer is correctly called when escaping a non string value.
     */

    /**
     * Tests whether a string value is correctly escaped. The string should not be modified.
     */

    /**
     * Tests whether the transformer is correctly invoked when escaping a string.
     */

    /**
     * Tests whether a limit is applied when extracting values from an array.
     */

    /**
     * Tests whether elements can be extracted from a collection that contains an array if a limit is specified.
     */

    /**
     * Tests whether a limit is applied when extracting elements from a collection.
     */

    /**
     * Tests whether the values of an array can be extracted.
     */
    @Test
    public void testParseArray() {
        checkIterator(handler.parse(VALUES));
    }

    /**
     * Tests whether the values of an Iterable object can be extracted.
     */
    @Test
    public void testParseIterable() {
        checkIterator(handler.parse(Arrays.asList(VALUES)));
    }

    /**
     * Tests whether the values of an Iterator object can be extracted.
     */
    @Test
    public void testParseIterator() {
        checkIterator(handler.parse(Arrays.asList(VALUES).iterator()));
    }

    /**
     * Tests whether a null value can be parsed.
     */

    /**
     * Tests whether a simple string value can be parsed.
     */

    @Test
    public void testEscapeNonStringValue_1_oe() {
        final Object value = 42;
        assertEquals("Wrong escaped object", value, handler.escape(value, ListDelimiterHandler.NOOP_TRANSFORMER));
    }

    @Test
    public void testEscapeNonStringValueTransformer_1_oe() {
        final ValueTransformer trans = EasyMock.createMock(ValueTransformer.class);
        final Object value = 42;
        EasyMock.expect(trans.transformValue(value)).andReturn(STR_VALUE);
        EasyMock.replay(trans);
        assertEquals("Wrong escaped object", STR_VALUE, handler.escape(value, trans));
    }

    @Test
    public void testEscapeStringValue_1_oe() {
        assertEquals("Wrong escaped string", STR_VALUE, handler.escape(STR_VALUE, ListDelimiterHandler.NOOP_TRANSFORMER));
    }

    @Test
    public void testEscapeStringValueTransformer_1_oe() {
        final ValueTransformer trans = EasyMock.createMock(ValueTransformer.class);
        final String testStr = "Some other string";
        EasyMock.expect(trans.transformValue(testStr)).andReturn(STR_VALUE);
        EasyMock.replay(trans);
        assertEquals("Wrong escaped string", STR_VALUE, handler.escape(testStr, trans));
    }

    @Test
    public void testFlattenArrayWithLimit_1_oe() {
        final Collection<?> res = handler.flatten(VALUES, 1);
        assertEquals("Wrong collection size", 1, res.size());
    }

    @Test
    public void testFlattenArrayWithLimit_2_oe() {
        final Collection<?> res = handler.flatten(VALUES, 1);
        // removed other assertion
        assertEquals("Wrong element", VALUES[0], res.iterator().next());
    }

    @Test
    public void testFlattenCollectionWithArrayWithLimit_1_oe() {
        final Collection<Object> src = new ArrayList<>(2);
        src.add(STR_VALUE);
        src.add(VALUES);
        final Collection<?> res = handler.flatten(src, 2);
        assertEquals("Wrong collection size", 2, res.size());
    }

    @Test
    public void testFlattenCollectionWithArrayWithLimit_2_oe() {
        final Collection<Object> src = new ArrayList<>(2);
        src.add(STR_VALUE);
        src.add(VALUES);
        final Collection<?> res = handler.flatten(src, 2);
        // removed other assertion
        final Iterator<?> it = res.iterator();
        assertEquals("Wrong element (1)", STR_VALUE, it.next());
    }

    @Test
    public void testFlattenCollectionWithArrayWithLimit_3_oe() {
        final Collection<Object> src = new ArrayList<>(2);
        src.add(STR_VALUE);
        src.add(VALUES);
        final Collection<?> res = handler.flatten(src, 2);
        // removed other assertion
        final Iterator<?> it = res.iterator();
        // removed other assertion
        assertEquals("Wrong element (2)", VALUES[0], it.next());
    }

    @Test
    public void testFlattenCollectionWithLimit_1_oe() {
        final Collection<Object> src = Arrays.asList(VALUES);
        final Collection<?> res = handler.flatten(src, 1);
        assertEquals("Wrong collection size", 1, res.size());
    }

    @Test
    public void testFlattenCollectionWithLimit_2_oe() {
        final Collection<Object> src = Arrays.asList(VALUES);
        final Collection<?> res = handler.flatten(src, 1);
        // removed other assertion
        assertEquals("Wrong element", VALUES[0], res.iterator().next());
    }

    @Test
    public void testParseNull_1_oe() {
        assertFalse("Got a value", handler.parse(null).iterator().hasNext());
    }

    @Test
    public void testParseSimpleValue_1_oe() {
        final Iterator<?> it = handler.parse(STR_VALUE).iterator();
        assertEquals("Wrong value", STR_VALUE, it.next());
    }

    @Test
    public void testParseSimpleValue_2_oe() {
        final Iterator<?> it = handler.parse(STR_VALUE).iterator();
        // removed other assertion
        assertFalse("Too many values", it.hasNext());
    }

}
