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

package org.apache.commons.lang3.text;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.mutable.MutableObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for StrSubstitutor.
 */
@Deprecated
public class StrSubstitutorTest_OE25Dev {

    private Map<String, String> values;

    @BeforeEach
    public void setUp() {
        values = new HashMap<>();
        values.put("animal", "quick brown fox");
        values.put("target", "lazy dog");
    }

    @AfterEach
    public void tearDown() {
        values = null;
    }

    //-----------------------------------------------------------------------
    /**
     * Tests simple key replace.
     */
    @Test
    public void testReplaceSimple() {
        doTestReplace("The quick brown fox jumps over the lazy dog.", "The ${animal} jumps over the ${target}.", true);
    }

    /**
     * Tests simple key replace.
     */
    @Test
    public void testReplaceSolo() {
        doTestReplace("quick brown fox", "${animal}", false);
    }

    /**
     * Tests replace with no variables.
     */
    @Test
    public void testReplaceNoVariables() {
        doTestNoReplace("The balloon arrived.");
    }

    /**
     * Tests replace with null.
     */
    @Test
    public void testReplaceNull() {
        doTestNoReplace(null);
    }

    /**
     * Tests replace with null.
     */
    @Test
    public void testReplaceEmpty() {
        doTestNoReplace("");
    }

    /**
     * Tests key replace changing map after initialization (not recommended).
     */

    /**
     * Tests unknown key replace.
     */
    @Test
    public void testReplaceUnknownKey() {
        doTestReplace("The ${person} jumps over the lazy dog.", "The ${person} jumps over the ${target}.", true);
        doTestReplace("The ${person} jumps over the lazy dog. 1234567890.", "The ${person} jumps over the ${target}. ${undefined.number:-1234567890}.", true);
    }

    /**
     * Tests adjacent keys.
     */

    /**
     * Tests adjacent keys.
     */

    /**
     * Tests simple recursive replace.
     */
    @Test
    public void testReplaceRecursive() {
        values.put("animal", "${critter}");
        values.put("target", "${pet}");
        values.put("pet", "${petCharacteristic} dog");
        values.put("petCharacteristic", "lazy");
        values.put("critter", "${critterSpeed} ${critterColor} ${critterType}");
        values.put("critterSpeed", "quick");
        values.put("critterColor", "brown");
        values.put("critterType", "fox");
        doTestReplace("The quick brown fox jumps over the lazy dog.", "The ${animal} jumps over the ${target}.", true);

        values.put("pet", "${petCharacteristicUnknown:-lazy} dog");
        doTestReplace("The quick brown fox jumps over the lazy dog.", "The ${animal} jumps over the ${target}.", true);
    }

    /**
     * Tests escaping.
     */
    @Test
    public void testReplaceEscaping() {
        doTestReplace("The ${animal} jumps over the lazy dog.", "The $${animal} jumps over the ${target}.", true);
    }

    /**
     * Tests escaping.
     */
    @Test
    public void testReplaceSoloEscaping() {
        doTestReplace("${animal}", "$${animal}", false);
    }

    /**
     * Tests complex escaping.
     */
    @Test
    public void testReplaceComplexEscaping() {
        doTestReplace("The ${quick brown fox} jumps over the lazy dog.", "The $${${animal}} jumps over the ${target}.", true);
        doTestReplace("The ${quick brown fox} jumps over the lazy dog. ${1234567890}.", "The $${${animal}} jumps over the ${target}. $${${undefined.number:-1234567890}}.", true);
    }

    /**
     * Tests when no prefix or suffix.
     */
    @Test
    public void testReplaceNoPrefixNoSuffix() {
        doTestReplace("The animal jumps over the lazy dog.", "The animal jumps over the ${target}.", true);
    }

    /**
     * Tests when no incomplete prefix.
     */
    @Test
    public void testReplaceIncompletePrefix() {
        doTestReplace("The {animal} jumps over the lazy dog.", "The {animal} jumps over the ${target}.", true);
    }

    /**
     * Tests when prefix but no suffix.
     */
    @Test
    public void testReplacePrefixNoSuffix() {
        doTestReplace("The ${animal jumps over the ${target} lazy dog.", "The ${animal jumps over the ${target} ${target}.", true);
    }

    /**
     * Tests when suffix but no prefix.
     */
    @Test
    public void testReplaceNoPrefixSuffix() {
        doTestReplace("The animal} jumps over the lazy dog.", "The animal} jumps over the ${target}.", true);
    }

    /**
     * Tests when no variable name.
     */
    @Test
    public void testReplaceEmptyKeys() {
        doTestReplace("The ${} jumps over the lazy dog.", "The ${} jumps over the ${target}.", true);
        doTestReplace("The animal jumps over the lazy dog.", "The ${:-animal} jumps over the ${target}.", true);
    }

    /**
     * Tests replace creates output same as input.
     */
    @Test
    public void testReplaceToIdentical() {
        values.put("animal", "$${${thing}}");
        values.put("thing", "animal");
        doTestReplace("The ${animal} jumps.", "The ${animal} jumps.", true);
    }

    /**
     * Tests a cyclic replace operation.
     * The cycle should be detected and cause an exception to be thrown.
     */

    /**
     * Tests interpolation with weird boundary patterns.
     */
    @Test
    public void testReplaceWeirdPattens() {
        doTestNoReplace("");
        doTestNoReplace("${}");
        doTestNoReplace("${ }");
        doTestNoReplace("${\t}");
        doTestNoReplace("${\n}");
        doTestNoReplace("${\b}");
        doTestNoReplace("${");
        doTestNoReplace("$}");
        doTestNoReplace("}");
        doTestNoReplace("${}$");
        doTestNoReplace("${${");
        doTestNoReplace("${${}}");
        doTestNoReplace("${$${}}");
        doTestNoReplace("${$$${}}");
        doTestNoReplace("${$$${$}}");
        doTestNoReplace("${${}}");
        doTestNoReplace("${${ }}");
    }

    /**
     * Tests simple key replace.
     */

    /**
     * Tests whether a variable can be replaced in a variable name.
     */

    /**
     * Tests whether substitution in variable names is disabled per default.
     */

    /**
     * Tests complex and recursive substitution in variable names.
     */

    //-----------------------------------------------------------------------
    /**
     * Tests protected.
     */

    //-----------------------------------------------------------------------
    /**
     * Tests constructor.
     */

    /**
     * Tests constructor.
     */

    /**
     * Tests constructor.
     */

    //-----------------------------------------------------------------------
    /**
     * Tests get set.
     */

    /**
     * Tests get set.
     */

    /**
     * Tests get set.
     */

    /**
     * Tests get set.
     */

    //-----------------------------------------------------------------------
    /**
     * Tests static.
     */

    /**
     * Tests static.
     */

    /**
     * Tests interpolation with system properties.
     */

    /**
     * Test for LANG-1055: StrSubstitutor.replaceSystemProperties does not work consistently
     */

    /**
     * Test the replace of a properties object
     */

    //-----------------------------------------------------------------------
    private void doTestReplace(final String expectedResult, final String replaceTemplate, final boolean substring) {
        final String expectedShortResult = expectedResult.substring(1, expectedResult.length() - 1);
        final StrSubstitutor sub = new StrSubstitutor(values);

        // replace using String
        assertEquals(expectedResult, sub.replace(replaceTemplate));
        if (substring) {
            assertEquals(expectedShortResult, sub.replace(replaceTemplate, 1, replaceTemplate.length() - 2));
        }

        // replace using char[]
        final char[] chars = replaceTemplate.toCharArray();
        assertEquals(expectedResult, sub.replace(chars));
        if (substring) {
            assertEquals(expectedShortResult, sub.replace(chars, 1, chars.length - 2));
        }

        // replace using StringBuffer
        StringBuffer buf = new StringBuffer(replaceTemplate);
        assertEquals(expectedResult, sub.replace(buf));
        if (substring) {
            assertEquals(expectedShortResult, sub.replace(buf, 1, buf.length() - 2));
        }

        // replace using StringBuilder
        StringBuilder builder = new StringBuilder(replaceTemplate);
        assertEquals(expectedResult, sub.replace(builder));
        if (substring) {
            assertEquals(expectedShortResult, sub.replace(builder, 1, builder.length() - 2));
        }

        // replace using StrBuilder
        StrBuilder bld = new StrBuilder(replaceTemplate);
        assertEquals(expectedResult, sub.replace(bld));
        if (substring) {
            assertEquals(expectedShortResult, sub.replace(bld, 1, bld.length() - 2));
        }

        // replace using object
        final MutableObject<String> obj = new MutableObject<>(replaceTemplate);  // toString returns template
        assertEquals(expectedResult, sub.replace(obj));

        // replace in StringBuffer
        buf = new StringBuffer(replaceTemplate);
        assertTrue(sub.replaceIn(buf));
        assertEquals(expectedResult, buf.toString());
        if (substring) {
            buf = new StringBuffer(replaceTemplate);
            assertTrue(sub.replaceIn(buf, 1, buf.length() - 2));
            assertEquals(expectedResult, buf.toString());  // expect full result as remainder is untouched
        }

        // replace in StringBuilder
        builder = new StringBuilder(replaceTemplate);
        assertTrue(sub.replaceIn(builder));
        assertEquals(expectedResult, builder.toString());
        if (substring) {
            builder = new StringBuilder(replaceTemplate);
            assertTrue(sub.replaceIn(builder, 1, builder.length() - 2));
            assertEquals(expectedResult, builder.toString());  // expect full result as remainder is untouched
        }

        // replace in StrBuilder
        bld = new StrBuilder(replaceTemplate);
        assertTrue(sub.replaceIn(bld));
        assertEquals(expectedResult, bld.toString());
        if (substring) {
            bld = new StrBuilder(replaceTemplate);
            assertTrue(sub.replaceIn(bld, 1, bld.length() - 2));
            assertEquals(expectedResult, bld.toString());  // expect full result as remainder is untouched
        }
    }

    private void doTestNoReplace(final String replaceTemplate) {
        final StrSubstitutor sub = new StrSubstitutor(values);

        if (replaceTemplate == null) {
            assertNull(sub.replace((String) null));
            assertNull(sub.replace((String) null, 0, 100));
            assertNull(sub.replace((char[]) null));
            assertNull(sub.replace((char[]) null, 0, 100));
            assertNull(sub.replace((StringBuffer) null));
            assertNull(sub.replace((StringBuffer) null, 0, 100));
            assertNull(sub.replace((StrBuilder) null));
            assertNull(sub.replace((StrBuilder) null, 0, 100));
            assertNull(sub.replace((Object) null));
            assertFalse(sub.replaceIn((StringBuffer) null));
            assertFalse(sub.replaceIn((StringBuffer) null, 0, 100));
            assertFalse(sub.replaceIn((StrBuilder) null));
            assertFalse(sub.replaceIn((StrBuilder) null, 0, 100));
        } else {
            assertEquals(replaceTemplate, sub.replace(replaceTemplate));
            final StrBuilder bld = new StrBuilder(replaceTemplate);
            assertFalse(sub.replaceIn(bld));
            assertEquals(replaceTemplate, bld.toString());
        }
    }

    @Test
    public void testReplaceChangedMap_1_oe() {
        final StrSubstitutor sub = new StrSubstitutor(values);
        values.put("target", "moon");
        assertEquals("The quick brown fox jumps over the moon.", sub.replace("The ${animal} jumps over the ${target}."));
    }

    @Test
    public void testReplaceAdjacentAtStart_1_oe() {
        values.put("code", "GBP");
        values.put("amount", "12.50");
        final StrSubstitutor sub = new StrSubstitutor(values);
        assertEquals("GBP12.50 charged", sub.replace("${code}${amount} charged"));
    }

    @Test
    public void testReplaceAdjacentAtEnd_1_oe() {
        values.put("code", "GBP");
        values.put("amount", "12.50");
        final StrSubstitutor sub = new StrSubstitutor(values);
        assertEquals("Amount is GBP12.50", sub.replace("Amount is ${code}${amount}"));
    }

    @Test
    public void testCyclicReplacement_1_oe() {
        final Map<String, String> map = new HashMap<>();
        map.put("animal", "${critter}");
        map.put("target", "${pet}");
        map.put("pet", "${petCharacteristic} dog");
        map.put("petCharacteristic", "lazy");
        map.put("critter", "${critterSpeed} ${critterColor} ${critterType}");
        map.put("critterSpeed", "quick");
        map.put("critterColor", "brown");
        map.put("critterType", "${animal}");
        final StrSubstitutor sub = new StrSubstitutor(map);
        assertThrows( IllegalStateException.class, () -> sub.replace("The ${animal} jumps over the ${target}."), "Cyclic replacement was not detected!");
    }

    @Test
    public void testCyclicReplacement_2_oe() {
        final Map<String, String> map = new HashMap<>();
        map.put("animal", "${critter}");
        map.put("target", "${pet}");
        map.put("pet", "${petCharacteristic} dog");
        map.put("petCharacteristic", "lazy");
        map.put("critter", "${critterSpeed} ${critterColor} ${critterType}");
        map.put("critterSpeed", "quick");
        map.put("critterColor", "brown");
        map.put("critterType", "${animal}");
        final StrSubstitutor sub = new StrSubstitutor(map);
        // removed other assertion

        // also check even when default value is set.
        map.put("critterType", "${animal:-fox}");
        final StrSubstitutor sub2 = new StrSubstitutor(map);
        assertThrows( IllegalStateException.class, () -> sub2.replace("The ${animal} jumps over the ${target}."), "Cyclic replacement was not detected!");
    }

    @Test
    public void testReplacePartialString_noReplace_1_oe() {
        final StrSubstitutor sub = new StrSubstitutor();
        assertEquals("${animal} jumps", sub.replace("The ${animal} jumps over the ${target}.", 4, 15));
    }

    @Test
    public void testReplaceInVariable_1_oe() {
        values.put("animal.1", "fox");
        values.put("animal.2", "mouse");
        values.put("species", "2");
        final StrSubstitutor sub = new StrSubstitutor(values);
        sub.setEnableSubstitutionInVariables(true);
        assertEquals( "The mouse jumps over the lazy dog.", sub.replace("The ${animal.${species}} jumps over the ${target}."), "Wrong result (1)");
    }

    @Test
    public void testReplaceInVariable_2_oe() {
        values.put("animal.1", "fox");
        values.put("animal.2", "mouse");
        values.put("species", "2");
        final StrSubstitutor sub = new StrSubstitutor(values);
        sub.setEnableSubstitutionInVariables(true);
        // removed other assertion
        values.put("species", "1");
        assertEquals( "The fox jumps over the lazy dog.", sub.replace("The ${animal.${species}} jumps over the ${target}."), "Wrong result (2)");
    }

    @Test
    public void testReplaceInVariable_3_oe() {
        values.put("animal.1", "fox");
        values.put("animal.2", "mouse");
        values.put("species", "2");
        final StrSubstitutor sub = new StrSubstitutor(values);
        sub.setEnableSubstitutionInVariables(true);
        // removed other assertion
        values.put("species", "1");
        // removed other assertion
        assertEquals( "The fox jumps over the lazy dog.", sub.replace("The ${unknown.animal.${unknown.species:-1}:-fox} jumps over the ${unknown.target:-lazy dog}."), "Wrong result (3)");
    }

    @Test
    public void testReplaceInVariableDisabled_1_oe() {
        values.put("animal.1", "fox");
        values.put("animal.2", "mouse");
        values.put("species", "2");
        final StrSubstitutor sub = new StrSubstitutor(values);
        assertEquals( "The ${animal.${species}} jumps over the lazy dog.", sub.replace("The ${animal.${species}} jumps over the ${target}."), "Wrong result (1)");
    }

    @Test
    public void testReplaceInVariableDisabled_2_oe() {
        values.put("animal.1", "fox");
        values.put("animal.2", "mouse");
        values.put("species", "2");
        final StrSubstitutor sub = new StrSubstitutor(values);
        // removed other assertion
        assertEquals( "The ${animal.${species:-1}} jumps over the lazy dog.", sub.replace("The ${animal.${species:-1}} jumps over the ${target}."), "Wrong result (2)");
    }

    @Test
    public void testReplaceInVariableRecursive_1_oe() {
        values.put("animal.2", "brown fox");
        values.put("animal.1", "white mouse");
        values.put("color", "white");
        values.put("species.white", "1");
        values.put("species.brown", "2");
        final StrSubstitutor sub = new StrSubstitutor(values);
        sub.setEnableSubstitutionInVariables(true);
        assertEquals( "The white mouse jumps over the lazy dog.", sub.replace("The ${animal.${species.${color}}} jumps over the ${target}."), "Wrong result (1)");
    }

    @Test
    public void testReplaceInVariableRecursive_2_oe() {
        values.put("animal.2", "brown fox");
        values.put("animal.1", "white mouse");
        values.put("color", "white");
        values.put("species.white", "1");
        values.put("species.brown", "2");
        final StrSubstitutor sub = new StrSubstitutor(values);
        sub.setEnableSubstitutionInVariables(true);
        // removed other assertion
        assertEquals( "The brown fox jumps over the lazy dog.", sub.replace("The ${animal.${species.${unknownColor:-brown}}} jumps over the ${target}."), "Wrong result (2)");
    }

    @Test
    public void testDefaultValueDelimiters_1_oe() {
        final Map<String, String> map = new HashMap<>();
        map.put("animal", "fox");
        map.put("target", "dog");

        StrSubstitutor sub = new StrSubstitutor(map, "${", "}", '$');
        assertEquals("The fox jumps over the lazy dog. 1234567890.", sub.replace("The ${animal} jumps over the lazy ${target}. ${undefined.number:-1234567890}."));
    }

    @Test
    public void testDefaultValueDelimiters_2_oe() {
        final Map<String, String> map = new HashMap<>();
        map.put("animal", "fox");
        map.put("target", "dog");

        StrSubstitutor sub = new StrSubstitutor(map, "${", "}", '$');
        // removed other assertion

        sub = new StrSubstitutor(map, "${", "}", '$', "?:");
        assertEquals("The fox jumps over the lazy dog. 1234567890.", sub.replace("The ${animal} jumps over the lazy ${target}. ${undefined.number?:1234567890}."));
    }

    @Test
    public void testDefaultValueDelimiters_3_oe() {
        final Map<String, String> map = new HashMap<>();
        map.put("animal", "fox");
        map.put("target", "dog");

        StrSubstitutor sub = new StrSubstitutor(map, "${", "}", '$');
        // removed other assertion

        sub = new StrSubstitutor(map, "${", "}", '$', "?:");
        // removed other assertion

        sub = new StrSubstitutor(map, "${", "}", '$', "||");
        assertEquals("The fox jumps over the lazy dog. 1234567890.", sub.replace("The ${animal} jumps over the lazy ${target}. ${undefined.number||1234567890}."));
    }

    @Test
    public void testDefaultValueDelimiters_4_oe() {
        final Map<String, String> map = new HashMap<>();
        map.put("animal", "fox");
        map.put("target", "dog");

        StrSubstitutor sub = new StrSubstitutor(map, "${", "}", '$');
        // removed other assertion

        sub = new StrSubstitutor(map, "${", "}", '$', "?:");
        // removed other assertion

        sub = new StrSubstitutor(map, "${", "}", '$', "||");
        // removed other assertion

        sub = new StrSubstitutor(map, "${", "}", '$', "!");
        assertEquals("The fox jumps over the lazy dog. 1234567890.", sub.replace("The ${animal} jumps over the lazy ${target}. ${undefined.number!1234567890}."));
    }

    @Test
    public void testDefaultValueDelimiters_5_oe() {
        final Map<String, String> map = new HashMap<>();
        map.put("animal", "fox");
        map.put("target", "dog");

        StrSubstitutor sub = new StrSubstitutor(map, "${", "}", '$');
        // removed other assertion

        sub = new StrSubstitutor(map, "${", "}", '$', "?:");
        // removed other assertion

        sub = new StrSubstitutor(map, "${", "}", '$', "||");
        // removed other assertion

        sub = new StrSubstitutor(map, "${", "}", '$', "!");
        // removed other assertion

        sub = new StrSubstitutor(map, "${", "}", '$', "");
        sub.setValueDelimiterMatcher(null);
        assertEquals("The fox jumps over the lazy dog. ${undefined.number!1234567890}.", sub.replace("The ${animal} jumps over the lazy ${target}. ${undefined.number!1234567890}."));
    }

    @Test
    public void testDefaultValueDelimiters_6_oe() {
        final Map<String, String> map = new HashMap<>();
        map.put("animal", "fox");
        map.put("target", "dog");

        StrSubstitutor sub = new StrSubstitutor(map, "${", "}", '$');
        // removed other assertion

        sub = new StrSubstitutor(map, "${", "}", '$', "?:");
        // removed other assertion

        sub = new StrSubstitutor(map, "${", "}", '$', "||");
        // removed other assertion

        sub = new StrSubstitutor(map, "${", "}", '$', "!");
        // removed other assertion

        sub = new StrSubstitutor(map, "${", "}", '$', "");
        sub.setValueDelimiterMatcher(null);
        // removed other assertion

        sub = new StrSubstitutor(map, "${", "}", '$');
        sub.setValueDelimiterMatcher(null);
        assertEquals("The fox jumps over the lazy dog. ${undefined.number!1234567890}.", sub.replace("The ${animal} jumps over the lazy ${target}. ${undefined.number!1234567890}."));
    }

    @Test
    public void testResolveVariable_5_oe() {
        final StrBuilder builder = new StrBuilder("Hi ${name}!");
        final Map<String, String> map = new HashMap<>();
        map.put("name", "commons");
        final StrSubstitutor sub = new StrSubstitutor(map) {
            @Override
            protected String resolveVariable(final String variableName, final StrBuilder buf, final int startPos, final int endPos) {
                // removed other assertion
                // removed other assertion
                // removed other assertion
                // removed other assertion
                return "jakarta";
            }
        };
        sub.replaceIn(builder);
        assertEquals("Hi jakarta!", builder.toString());
    }

    @Test
    public void testConstructorNoArgs_1_oe() {
        final StrSubstitutor sub = new StrSubstitutor();
        assertEquals("Hi ${name}", sub.replace("Hi ${name}"));
    }

    @Test
    public void testConstructorMapPrefixSuffix_1_oe() {
        final Map<String, String> map = new HashMap<>();
        map.put("name", "commons");
        final StrSubstitutor sub = new StrSubstitutor(map, "<", ">");
        assertEquals("Hi < commons", sub.replace("Hi $< <name>"));
    }

    @Test
    public void testConstructorMapFull_1_oe() {
        final Map<String, String> map = new HashMap<>();
        map.put("name", "commons");
        StrSubstitutor sub = new StrSubstitutor(map, "<", ">", '!');
        assertEquals("Hi < commons", sub.replace("Hi !< <name>"));
    }

    @Test
    public void testConstructorMapFull_2_oe() {
        final Map<String, String> map = new HashMap<>();
        map.put("name", "commons");
        StrSubstitutor sub = new StrSubstitutor(map, "<", ">", '!');
        // removed other assertion
        sub = new StrSubstitutor(map, "<", ">", '!', "||");
        assertEquals("Hi < commons", sub.replace("Hi !< <name2||commons>"));
    }

    @Test
    public void testGetSetEscape_1_oe() {
        final StrSubstitutor sub = new StrSubstitutor();
        assertEquals('$', sub.getEscapeChar());
    }

    @Test
    public void testGetSetEscape_2_oe() {
        final StrSubstitutor sub = new StrSubstitutor();
        // removed other assertion
        sub.setEscapeChar('<');
        assertEquals('<', sub.getEscapeChar());
    }

    @Test
    public void testGetSetPrefix_1_oe() {
        final StrSubstitutor sub = new StrSubstitutor();
        assertTrue(sub.getVariablePrefixMatcher() instanceof StrMatcher.StringMatcher);
    }

    @Test
    public void testGetSetPrefix_2_oe() {
        final StrSubstitutor sub = new StrSubstitutor();
        // removed other assertion
        sub.setVariablePrefix('<');
        assertTrue(sub.getVariablePrefixMatcher() instanceof StrMatcher.CharMatcher);
    }

    @Test
    public void testGetSetPrefix_3_oe() {
        final StrSubstitutor sub = new StrSubstitutor();
        // removed other assertion
        sub.setVariablePrefix('<');
        // removed other assertion

        sub.setVariablePrefix("<<");
        assertTrue(sub.getVariablePrefixMatcher() instanceof StrMatcher.StringMatcher);
    }

    @Test
    public void testGetSetPrefix_4_oe() {
        final StrSubstitutor sub = new StrSubstitutor();
        // removed other assertion
        sub.setVariablePrefix('<');
        // removed other assertion

        sub.setVariablePrefix("<<");
        // removed other assertion
        assertThrows(IllegalArgumentException.class, () -> sub.setVariablePrefix(null));
    }

    @Test
    public void testGetSetPrefix_5_oe() {
        final StrSubstitutor sub = new StrSubstitutor();
        // removed other assertion
        sub.setVariablePrefix('<');
        // removed other assertion

        sub.setVariablePrefix("<<");
        // removed other assertion
        // removed other assertion
        assertTrue(sub.getVariablePrefixMatcher() instanceof StrMatcher.StringMatcher);
    }

    @Test
    public void testGetSetPrefix_6_oe() {
        final StrSubstitutor sub = new StrSubstitutor();
        // removed other assertion
        sub.setVariablePrefix('<');
        // removed other assertion

        sub.setVariablePrefix("<<");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrMatcher matcher = StrMatcher.commaMatcher();
        sub.setVariablePrefixMatcher(matcher);
        assertSame(matcher, sub.getVariablePrefixMatcher());
    }

    @Test
    public void testGetSetPrefix_7_oe() {
        final StrSubstitutor sub = new StrSubstitutor();
        // removed other assertion
        sub.setVariablePrefix('<');
        // removed other assertion

        sub.setVariablePrefix("<<");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrMatcher matcher = StrMatcher.commaMatcher();
        sub.setVariablePrefixMatcher(matcher);
        // removed other assertion
        assertThrows(IllegalArgumentException.class, () -> sub.setVariablePrefixMatcher(null));
    }

    @Test
    public void testGetSetPrefix_8_oe() {
        final StrSubstitutor sub = new StrSubstitutor();
        // removed other assertion
        sub.setVariablePrefix('<');
        // removed other assertion

        sub.setVariablePrefix("<<");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrMatcher matcher = StrMatcher.commaMatcher();
        sub.setVariablePrefixMatcher(matcher);
        // removed other assertion
        // removed other assertion
        assertSame(matcher, sub.getVariablePrefixMatcher());
    }

    @Test
    public void testGetSetSuffix_1_oe() {
        final StrSubstitutor sub = new StrSubstitutor();
        assertTrue(sub.getVariableSuffixMatcher() instanceof StrMatcher.StringMatcher);
    }

    @Test
    public void testGetSetSuffix_2_oe() {
        final StrSubstitutor sub = new StrSubstitutor();
        // removed other assertion
        sub.setVariableSuffix('<');
        assertTrue(sub.getVariableSuffixMatcher() instanceof StrMatcher.CharMatcher);
    }

    @Test
    public void testGetSetSuffix_3_oe() {
        final StrSubstitutor sub = new StrSubstitutor();
        // removed other assertion
        sub.setVariableSuffix('<');
        // removed other assertion

        sub.setVariableSuffix("<<");
        assertTrue(sub.getVariableSuffixMatcher() instanceof StrMatcher.StringMatcher);
    }

    @Test
    public void testGetSetSuffix_4_oe() {
        final StrSubstitutor sub = new StrSubstitutor();
        // removed other assertion
        sub.setVariableSuffix('<');
        // removed other assertion

        sub.setVariableSuffix("<<");
        // removed other assertion
        assertThrows(IllegalArgumentException.class, () -> sub.setVariableSuffix(null));
    }

    @Test
    public void testGetSetSuffix_5_oe() {
        final StrSubstitutor sub = new StrSubstitutor();
        // removed other assertion
        sub.setVariableSuffix('<');
        // removed other assertion

        sub.setVariableSuffix("<<");
        // removed other assertion
        // removed other assertion
        assertTrue(sub.getVariableSuffixMatcher() instanceof StrMatcher.StringMatcher);
    }

    @Test
    public void testGetSetSuffix_6_oe() {
        final StrSubstitutor sub = new StrSubstitutor();
        // removed other assertion
        sub.setVariableSuffix('<');
        // removed other assertion

        sub.setVariableSuffix("<<");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrMatcher matcher = StrMatcher.commaMatcher();
        sub.setVariableSuffixMatcher(matcher);
        assertSame(matcher, sub.getVariableSuffixMatcher());
    }

    @Test
    public void testGetSetSuffix_7_oe() {
        final StrSubstitutor sub = new StrSubstitutor();
        // removed other assertion
        sub.setVariableSuffix('<');
        // removed other assertion

        sub.setVariableSuffix("<<");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrMatcher matcher = StrMatcher.commaMatcher();
        sub.setVariableSuffixMatcher(matcher);
        // removed other assertion
        assertThrows(IllegalArgumentException.class, () -> sub.setVariableSuffixMatcher(null));
    }

    @Test
    public void testGetSetSuffix_8_oe() {
        final StrSubstitutor sub = new StrSubstitutor();
        // removed other assertion
        sub.setVariableSuffix('<');
        // removed other assertion

        sub.setVariableSuffix("<<");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final StrMatcher matcher = StrMatcher.commaMatcher();
        sub.setVariableSuffixMatcher(matcher);
        // removed other assertion
        // removed other assertion
        assertSame(matcher, sub.getVariableSuffixMatcher());
    }

    @Test
    public void testGetSetValueDelimiter_1_oe() {
        final StrSubstitutor sub = new StrSubstitutor();
        assertTrue(sub.getValueDelimiterMatcher() instanceof StrMatcher.StringMatcher);
    }

    @Test
    public void testGetSetValueDelimiter_2_oe() {
        final StrSubstitutor sub = new StrSubstitutor();
        // removed other assertion
        sub.setValueDelimiter(':');
        assertTrue(sub.getValueDelimiterMatcher() instanceof StrMatcher.CharMatcher);
    }

    @Test
    public void testGetSetValueDelimiter_3_oe() {
        final StrSubstitutor sub = new StrSubstitutor();
        // removed other assertion
        sub.setValueDelimiter(':');
        // removed other assertion

        sub.setValueDelimiter("||");
        assertTrue(sub.getValueDelimiterMatcher() instanceof StrMatcher.StringMatcher);
    }

    @Test
    public void testGetSetValueDelimiter_4_oe() {
        final StrSubstitutor sub = new StrSubstitutor();
        // removed other assertion
        sub.setValueDelimiter(':');
        // removed other assertion

        sub.setValueDelimiter("||");
        // removed other assertion
        sub.setValueDelimiter(null);
        assertNull(sub.getValueDelimiterMatcher());
    }

    @Test
    public void testGetSetValueDelimiter_5_oe() {
        final StrSubstitutor sub = new StrSubstitutor();
        // removed other assertion
        sub.setValueDelimiter(':');
        // removed other assertion

        sub.setValueDelimiter("||");
        // removed other assertion
        sub.setValueDelimiter(null);
        // removed other assertion

        final StrMatcher matcher = StrMatcher.commaMatcher();
        sub.setValueDelimiterMatcher(matcher);
        assertSame(matcher, sub.getValueDelimiterMatcher());
    }

    @Test
    public void testGetSetValueDelimiter_6_oe() {
        final StrSubstitutor sub = new StrSubstitutor();
        // removed other assertion
        sub.setValueDelimiter(':');
        // removed other assertion

        sub.setValueDelimiter("||");
        // removed other assertion
        sub.setValueDelimiter(null);
        // removed other assertion

        final StrMatcher matcher = StrMatcher.commaMatcher();
        sub.setValueDelimiterMatcher(matcher);
        // removed other assertion
        sub.setValueDelimiterMatcher(null);
        assertNull(sub.getValueDelimiterMatcher());
    }

    @Test
    public void testStaticReplace_1_oe() {
        final Map<String, String> map = new HashMap<>();
        map.put("name", "commons");
        assertEquals("Hi commons!", StrSubstitutor.replace("Hi ${name}!", map));
    }

    @Test
    public void testStaticReplacePrefixSuffix_1_oe() {
        final Map<String, String> map = new HashMap<>();
        map.put("name", "commons");
        assertEquals("Hi commons!", StrSubstitutor.replace("Hi <name>!", map, "<", ">"));
    }

    @Test
    public void testStaticReplaceSystemProperties_1_oe() {
        final StrBuilder buf = new StrBuilder();
        buf.append("Hi ").append(System.getProperty("user.name"));
        buf.append(", you are working with ");
        buf.append(System.getProperty("os.name"));
        buf.append(", your home directory is ");
        buf.append(System.getProperty("user.home")).append('.');
        assertEquals(buf.toString(), StrSubstitutor.replaceSystemProperties("Hi ${user.name}, you are " + "working with ${os.name}, your home " + "directory is ${user.home}."));
    }

    @Test
    public void testLANG1055_1_oe() {
        System.setProperty("test_key",  "test_value");

        final String expected = StrSubstitutor.replace("test_key=${test_key}", System.getProperties());
        final String actual = StrSubstitutor.replaceSystemProperties("test_key=${test_key}");
        assertEquals(expected, actual);
    }

    @Test
    public void testSubstituteDefaultProperties_1_oe() {
        final String org = "${doesnotwork}";
        System.setProperty("doesnotwork", "It works!");

        // create a new Properties object with the System.getProperties as default
        final Properties props = new Properties(System.getProperties());

        assertEquals("It works!", StrSubstitutor.replace(org, props));
    }

    @Test
    public void testSamePrefixAndSuffix_1_oe() {
        final Map<String, String> map = new HashMap<>();
        map.put("greeting", "Hello");
        map.put(" there ", "XXX");
        map.put("name", "commons");
        assertEquals("Hi commons!", StrSubstitutor.replace("Hi @name@!", map, "@", "@"));
    }

    @Test
    public void testSamePrefixAndSuffix_2_oe() {
        final Map<String, String> map = new HashMap<>();
        map.put("greeting", "Hello");
        map.put(" there ", "XXX");
        map.put("name", "commons");
        // removed other assertion
        assertEquals("Hello there commons!", StrSubstitutor.replace("@greeting@ there @name@!", map, "@", "@"));
    }

    @Test
    public void testSubstitutePreserveEscape_1_oe() {
        final String org = "${not-escaped} $${escaped}";
        final Map<String, String> map = new HashMap<>();
        map.put("not-escaped", "value");

        final StrSubstitutor sub = new StrSubstitutor(map, "${", "}", '$');
        assertFalse(sub.isPreserveEscapes());
    }

    @Test
    public void testSubstitutePreserveEscape_2_oe() {
        final String org = "${not-escaped} $${escaped}";
        final Map<String, String> map = new HashMap<>();
        map.put("not-escaped", "value");

        final StrSubstitutor sub = new StrSubstitutor(map, "${", "}", '$');
        // removed other assertion
        assertEquals("value ${escaped}", sub.replace(org));
    }

    @Test
    public void testSubstitutePreserveEscape_3_oe() {
        final String org = "${not-escaped} $${escaped}";
        final Map<String, String> map = new HashMap<>();
        map.put("not-escaped", "value");

        final StrSubstitutor sub = new StrSubstitutor(map, "${", "}", '$');
        // removed other assertion
        // removed other assertion

        sub.setPreserveEscapes(true);
        assertTrue(sub.isPreserveEscapes());
    }

    @Test
    public void testSubstitutePreserveEscape_4_oe() {
        final String org = "${not-escaped} $${escaped}";
        final Map<String, String> map = new HashMap<>();
        map.put("not-escaped", "value");

        final StrSubstitutor sub = new StrSubstitutor(map, "${", "}", '$');
        // removed other assertion
        // removed other assertion

        sub.setPreserveEscapes(true);
        // removed other assertion
        assertEquals("value $${escaped}", sub.replace(org));
    }

}
