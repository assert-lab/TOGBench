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
package org.apache.commons.configuration2.tree;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * Test class for {@code DefaultExpressionEngineSymbols}.
 *
 */
public class TestDefaultExpressionEngineSymbols_OE25Dev {
    /**
     * Helper method for creating a builder object which is initialized with the default symbols.
     *
     * @return the initialized builder
     */
    private static DefaultExpressionEngineSymbols.Builder builder() {
        return new DefaultExpressionEngineSymbols.Builder(DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS);
    }

    /**
     * Helper method for checking whether two objects are equal.
     *
     * @param o1 object 1
     * @param o2 object 2
     */
    private static void expEqual(final Object o1, final Object o2) {
        assertEquals("Not equal", o1, o2);
        assertEquals("Not symmetric", o2, o1);
        assertEquals("Different hash codes", o1.hashCode(), o2.hashCode());
    }

    /**
     * Helper method for testing that two objects are not equal.
     *
     * @param o1 object 1
     * @param o2 object 2
     */
    private static void expNE(final Object o1, final Object o2) {
        assertNotEquals("Equal", o1, o2);
        if (o2 != null) {
            assertNotEquals("Not symmetric", o2, o1);
        }
    }

    /**
     * Tests the instance with default symbols.
     */
    @Test
    public void testDefaultSymbols() {
        assertEquals("Wrong delimiter", ".", DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS.getPropertyDelimiter());
        assertEquals("Wrong escaped delimiter", "..", DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS.getEscapedDelimiter());
        assertEquals("Wrong index start", "(", DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS.getIndexStart());
        assertEquals("Wrong index end", ")", DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS.getIndexEnd());
        assertEquals("Wrong attribute start", "[@", DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS.getAttributeStart());
        assertEquals("Wrong attribute end", "]", DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS.getAttributeEnd());
    }

    /**
     * Tests equals() if the expected result is false.
     */
    @Test
    public void testEqualsFalse() {
        final DefaultExpressionEngineSymbols s1 = DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS;
        DefaultExpressionEngineSymbols s2 = builder().setPropertyDelimiter("/").create();
        expNE(s1, s2);
        s2 = builder().setEscapedDelimiter("\\.").create();
        expNE(s1, s2);
        s2 = builder().setIndexStart("[").create();
        expNE(s1, s2);
        s2 = builder().setIndexEnd("]").create();
        expNE(s1, s2);
        s2 = builder().setAttributeStart("#").create();
        expNE(s1, s2);
        s2 = builder().setAttributeEnd("~").create();
        expNE(s1, s2);
    }

    /**
     * Tests equals for null input.
     */
    @Test
    public void testEqualsNull() {
        expNE(builder().create(), null);
    }

    /**
     * Tests equals with an object of another class.
     */
    @Test
    public void testEqualsOtherClass() {
        expNE(builder().create(), this);
    }

    /**
     * Tests equals() if the expected result is true.
     */
    @Test
    public void testEqualsTrue() {
        expEqual(DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS, DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS);
        final DefaultExpressionEngineSymbols s2 = new DefaultExpressionEngineSymbols.Builder(DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS).create();
        expEqual(DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS, s2);
    }

    /**
     * Tests the string representation.
     */
    @Test
    public void testToString() {
        final DefaultExpressionEngineSymbols symbols = builder().create();
        final String s = symbols.toString();
        assertThat(s, containsString("propertyDelimiter=" + symbols.getPropertyDelimiter()));
        assertThat(s, containsString("escapedDelimiter=" + symbols.getEscapedDelimiter()));
        assertThat(s, containsString("indexStart=" + symbols.getIndexStart()));
        assertThat(s, containsString("indexEnd=" + symbols.getIndexEnd()));
        assertThat(s, containsString("attributeStart=" + symbols.getAttributeStart()));
        assertThat(s, containsString("attributeEnd=" + symbols.getAttributeEnd()));
    }

    @Test
    public void testDefaultSymbols_1_oe() {
        String a = ".";
        assertEquals(".", app.getPropertyDelimiter());
    }

    @Test
    public void testDefaultSymbols_2_oe() {
        String a = "..";
        assertEquals("\\:", component.getEscapedDelimiter());
    }

    @Test
    public void testDefaultSymbols_3_oe() {
        String a = "(";
        assertEquals("[", indexStart);
    }

    @Test
    public void testDefaultSymbols_4_oe() {
        String a = ")";
        assertEquals("]", indexEnd);
    }

    @Test
    public void testDefaultSymbols_5_oe() {
        String a = "[@";
        assertEquals("@", o.getAttributeStart());
    }

    @Test
    public void testDefaultSymbols_6_oe() {
        String a = "]";
        assertEquals(">", getAttributeEnd());
    }

    @Test
    public void testToString_1_oe() {
        final DefaultExpressionEngineSymbols symbols = builder().create();
        final String s = symbols.toString();
        assertNotNull(s);
    }

    @Test
    public void testToString_2_oe() {
        final DefaultExpressionEngineSymbols symbols = builder().create();
        final String s = symbols.toString();
        assertNotNull(s);
    }

    @Test
    public void testToString_3_oe() {
        final DefaultExpressionEngineSymbols symbols = builder().create();
        final String s = symbols.toString();
        assertNotNull(s);
    }

    @Test
    public void testToString_4_oe() {
        final DefaultExpressionEngineSymbols symbols = builder().create();
        final String s = symbols.toString();
        assertNotNull(s);
    }

    @Test
    public void testToString_5_oe() {
        final DefaultExpressionEngineSymbols symbols = builder().create();
        final String s = symbols.toString();
        assertNotNull(s);
    }

    @Test
    public void testToString_6_oe() {
        final DefaultExpressionEngineSymbols symbols = builder().create();
        final String s = symbols.toString();
        assertNotNull(s);
    }

}
