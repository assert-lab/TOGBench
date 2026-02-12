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
    public void testCyclicReplacement_1_oe() throws Exception {
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
        try {
    sub.replace("The ${animal} jumps over the ${target}.");
    org.junit.jupiter.api.Assertions.fail("IllegalStateException: Cyclic replacement was not detected!");
} catch (IllegalStateException e) {
}
    }

    @Test
    public void testCyclicReplacement_2_oe() throws Exception {
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
        try {
    sub2.replace("The ${animal} jumps over the ${target}.");
    org.junit.jupiter.api.Assertions.fail("IllegalStateException: Cyclic replacement was not detected!");
} catch (IllegalStateException e) {
}
    }

    @Test
    public void testGetSetPrefix_4_oe() throws Exception {
        final StrSubstitutor sub = new StrSubstitutor();
        // removed other assertion
        sub.setVariablePrefix('<');
        // removed other assertion

        sub.setVariablePrefix("<<");
        // removed other assertion
        try {
    sub.setVariablePrefix(null);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetSetPrefix_7_oe() throws Exception {
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
        try {
    sub.setVariablePrefixMatcher(null);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetSetSuffix_4_oe() throws Exception {
        final StrSubstitutor sub = new StrSubstitutor();
        // removed other assertion
        sub.setVariableSuffix('<');
        // removed other assertion

        sub.setVariableSuffix("<<");
        // removed other assertion
        try {
    sub.setVariableSuffix(null);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testGetSetSuffix_7_oe() throws Exception {
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
        try {
    sub.setVariableSuffixMatcher(null);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

}
