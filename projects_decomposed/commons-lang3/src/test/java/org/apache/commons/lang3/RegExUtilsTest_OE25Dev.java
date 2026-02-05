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
package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for methods of {@link org.apache.commons.lang3.RegExUtils} which been moved to their own test classes.
 */
public class RegExUtilsTest_OE25Dev {

@Test
    public void testRemoveAll_StringPattern_1_oe() {
        assertNull(RegExUtils.removeAll(null, Pattern.compile("")));
    }

@Test
    public void testRemoveAll_StringPattern_2_oe() {
        // removed other assertion
        assertEquals("any", RegExUtils.removeAll("any", (Pattern) null));
    }

@Test
    public void testRemoveAll_StringPattern_3_oe() {
        // removed other assertion
        // removed other assertion

        assertEquals("any", RegExUtils.removeAll("any", Pattern.compile("")));
    }

@Test
    public void testRemoveAll_StringPattern_4_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("", RegExUtils.removeAll("any", Pattern.compile(".*")));
    }

@Test
    public void testRemoveAll_StringPattern_5_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("", RegExUtils.removeAll("any", Pattern.compile(".+")));
    }

@Test
    public void testRemoveAll_StringPattern_6_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", RegExUtils.removeAll("any", Pattern.compile(".?")));
    }

@Test
    public void testRemoveAll_StringPattern_7_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("A\nB", RegExUtils.removeAll("A<__>\n<__>B", Pattern.compile("<.*>")));
    }

@Test
    public void testRemoveAll_StringPattern_8_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("AB", RegExUtils.removeAll("A<__>\n<__>B", Pattern.compile("(?s)<.*>")));
    }

@Test
    public void testRemoveAll_StringPattern_9_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("ABC123", RegExUtils.removeAll("ABCabc123abc", Pattern.compile("[a-z]")));
    }

@Test
    public void testRemoveAll_StringPattern_10_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("AB", RegExUtils.removeAll("A<__>\n<__>B", Pattern.compile("<.*>", Pattern.DOTALL)));
    }

@Test
    public void testRemoveAll_StringPattern_11_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("AB", RegExUtils.removeAll("A<__>\\n<__>B", Pattern.compile("<.*>")));
    }

@Test
    public void testRemoveAll_StringPattern_12_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("", RegExUtils.removeAll("<A>x\\ny</A>", Pattern.compile("<A>.*</A>")));
    }

@Test
    public void testRemoveAll_StringPattern_13_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", RegExUtils.removeAll("<A>\nxy\n</A>", Pattern.compile("<A>.*</A>", Pattern.DOTALL)));
    }

@Test
    public void testRemoveAll_StringString_1_oe() {
        assertNull(RegExUtils.removeAll(null, ""));
    }

@Test
    public void testRemoveAll_StringString_2_oe() {
        // removed other assertion
        assertEquals("any", RegExUtils.removeAll("any", (String) null));
    }

@Test
    public void testRemoveAll_StringString_3_oe() {
        // removed other assertion
        // removed other assertion

        assertEquals("any", RegExUtils.removeAll("any", ""));
    }

@Test
    public void testRemoveAll_StringString_4_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("", RegExUtils.removeAll("any", ".*"));
    }

@Test
    public void testRemoveAll_StringString_5_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("", RegExUtils.removeAll("any", ".+"));
    }

@Test
    public void testRemoveAll_StringString_6_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", RegExUtils.removeAll("any", ".?"));
    }

@Test
    public void testRemoveAll_StringString_7_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("A\nB", RegExUtils.removeAll("A<__>\n<__>B", "<.*>"));
    }

@Test
    public void testRemoveAll_StringString_8_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("AB", RegExUtils.removeAll("A<__>\n<__>B", "(?s)<.*>"));
    }

@Test
    public void testRemoveAll_StringString_9_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("ABC123", RegExUtils.removeAll("ABCabc123abc", "[a-z]"));
    }

@Test
    public void testRemoveAll_StringString_10_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertThrows( PatternSyntaxException.class, () -> RegExUtils.removeAll("any", "{badRegexSyntax}"), "RegExUtils.removeAll expecting PatternSyntaxException");
    }

@Test
    public void testRemoveFirst_StringPattern_1_oe() {
        assertNull(RegExUtils.removeFirst(null, Pattern.compile("")));
    }

@Test
    public void testRemoveFirst_StringPattern_2_oe() {
        // removed other assertion
        assertEquals("any", RegExUtils.removeFirst("any", (Pattern) null));
    }

@Test
    public void testRemoveFirst_StringPattern_3_oe() {
        // removed other assertion
        // removed other assertion

        assertEquals("any", RegExUtils.removeFirst("any", Pattern.compile("")));
    }

@Test
    public void testRemoveFirst_StringPattern_4_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("", RegExUtils.removeFirst("any", Pattern.compile(".*")));
    }

@Test
    public void testRemoveFirst_StringPattern_5_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("", RegExUtils.removeFirst("any", Pattern.compile(".+")));
    }

@Test
    public void testRemoveFirst_StringPattern_6_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("bc", RegExUtils.removeFirst("abc", Pattern.compile(".?")));
    }

@Test
    public void testRemoveFirst_StringPattern_7_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("A\n<__>B", RegExUtils.removeFirst("A<__>\n<__>B", Pattern.compile("<.*>")));
    }

@Test
    public void testRemoveFirst_StringPattern_8_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("AB", RegExUtils.removeFirst("A<__>\n<__>B", Pattern.compile("(?s)<.*>")));
    }

@Test
    public void testRemoveFirst_StringPattern_9_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("ABCbc123", RegExUtils.removeFirst("ABCabc123", Pattern.compile("[a-z]")));
    }

@Test
    public void testRemoveFirst_StringPattern_10_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ABC123abc", RegExUtils.removeFirst("ABCabc123abc", Pattern.compile("[a-z]+")));
    }

@Test
    public void testRemoveFirst_StringString_1_oe() {
        assertNull(RegExUtils.removeFirst(null, ""));
    }

@Test
    public void testRemoveFirst_StringString_2_oe() {
        // removed other assertion
        assertEquals("any", RegExUtils.removeFirst("any", (String) null));
    }

@Test
    public void testRemoveFirst_StringString_3_oe() {
        // removed other assertion
        // removed other assertion

        assertEquals("any", RegExUtils.removeFirst("any", ""));
    }

@Test
    public void testRemoveFirst_StringString_4_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("", RegExUtils.removeFirst("any", ".*"));
    }

@Test
    public void testRemoveFirst_StringString_5_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("", RegExUtils.removeFirst("any", ".+"));
    }

@Test
    public void testRemoveFirst_StringString_6_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("bc", RegExUtils.removeFirst("abc", ".?"));
    }

@Test
    public void testRemoveFirst_StringString_7_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("A\n<__>B", RegExUtils.removeFirst("A<__>\n<__>B", "<.*>"));
    }

@Test
    public void testRemoveFirst_StringString_8_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("AB", RegExUtils.removeFirst("A<__>\n<__>B", "(?s)<.*>"));
    }

@Test
    public void testRemoveFirst_StringString_9_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("ABCbc123", RegExUtils.removeFirst("ABCabc123", "[a-z]"));
    }

@Test
    public void testRemoveFirst_StringString_10_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ABC123abc", RegExUtils.removeFirst("ABCabc123abc", "[a-z]+"));
    }

@Test
    public void testRemoveFirst_StringString_11_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertThrows( PatternSyntaxException.class, () -> RegExUtils.removeFirst("any", "{badRegexSyntax}"), "RegExUtils.removeFirst expecting PatternSyntaxException");
    }

@Test
    public void testRemovePattern_StringString_1_oe() {
        assertNull(RegExUtils.removePattern(null, ""));
    }

@Test
    public void testRemovePattern_StringString_2_oe() {
        // removed other assertion
        assertEquals("any", RegExUtils.removePattern("any", (String) null));
    }

@Test
    public void testRemovePattern_StringString_3_oe() {
        // removed other assertion
        // removed other assertion

        assertEquals("", RegExUtils.removePattern("", ""));
    }

@Test
    public void testRemovePattern_StringString_4_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("", RegExUtils.removePattern("", ".*"));
    }

@Test
    public void testRemovePattern_StringString_5_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("", RegExUtils.removePattern("", ".+"));
    }

@Test
    public void testRemovePattern_StringString_6_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("AB", RegExUtils.removePattern("A<__>\n<__>B", "<.*>"));
    }

@Test
    public void testRemovePattern_StringString_7_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("AB", RegExUtils.removePattern("A<__>\\n<__>B", "<.*>"));
    }

@Test
    public void testRemovePattern_StringString_8_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("", RegExUtils.removePattern("<A>x\\ny</A>", "<A>.*</A>"));
    }

@Test
    public void testRemovePattern_StringString_9_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("", RegExUtils.removePattern("<A>\nxy\n</A>", "<A>.*</A>"));
    }

@Test
    public void testRemovePattern_StringString_10_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("ABC123", RegExUtils.removePattern("ABCabc123", "[a-z]"));
    }

@Test
    public void testReplaceAll_StringPatternString_1_oe() {
        assertNull(RegExUtils.replaceAll(null, Pattern.compile(""), ""));
    }

@Test
    public void testReplaceAll_StringPatternString_2_oe() {
        // removed other assertion

        assertEquals("any", RegExUtils.replaceAll("any", (Pattern) null, ""));
    }

@Test
    public void testReplaceAll_StringPatternString_3_oe() {
        // removed other assertion

        // removed other assertion
        assertEquals("any", RegExUtils.replaceAll("any", Pattern.compile(""), null));
    }

@Test
    public void testReplaceAll_StringPatternString_4_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals("zzz", RegExUtils.replaceAll("", Pattern.compile(""), "zzz"));
    }

@Test
    public void testReplaceAll_StringPatternString_5_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("zzz", RegExUtils.replaceAll("", Pattern.compile(".*"), "zzz"));
    }

@Test
    public void testReplaceAll_StringPatternString_6_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("", RegExUtils.replaceAll("", Pattern.compile(".+"), "zzz"));
    }

@Test
    public void testReplaceAll_StringPatternString_7_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ZZaZZbZZcZZ", RegExUtils.replaceAll("abc", Pattern.compile(""), "ZZ"));
    }

@Test
    public void testReplaceAll_StringPatternString_8_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("z\nz", RegExUtils.replaceAll("<__>\n<__>", Pattern.compile("<.*>"), "z"));
    }

@Test
    public void testReplaceAll_StringPatternString_9_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("z", RegExUtils.replaceAll("<__>\n<__>", Pattern.compile("(?s)<.*>"), "z"));
    }

@Test
    public void testReplaceAll_StringPatternString_10_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals("z", RegExUtils.replaceAll("<__>\n<__>", Pattern.compile("<.*>", Pattern.DOTALL), "z"));
    }

@Test
    public void testReplaceAll_StringPatternString_11_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("z", RegExUtils.replaceAll("<__>\\n<__>", Pattern.compile("<.*>"), "z"));
    }

@Test
    public void testReplaceAll_StringPatternString_12_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("X", RegExUtils.replaceAll("<A>\nxy\n</A>", Pattern.compile("<A>.*</A>", Pattern.DOTALL), "X"));
    }

@Test
    public void testReplaceAll_StringPatternString_13_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("ABC___123", RegExUtils.replaceAll("ABCabc123", Pattern.compile("[a-z]"), "_"));
    }

@Test
    public void testReplaceAll_StringPatternString_14_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("ABC_123", RegExUtils.replaceAll("ABCabc123", Pattern.compile("[^A-Z0-9]+"), "_"));
    }

@Test
    public void testReplaceAll_StringPatternString_15_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("ABC123", RegExUtils.replaceAll("ABCabc123", Pattern.compile("[^A-Z0-9]+"), ""));
    }

@Test
    public void testReplaceAll_StringPatternString_16_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Lorem_ipsum_dolor_sit",RegExUtils.replaceAll("Lorem ipsum dolor sit",Pattern.compile("(+)([a-z]+)"),"_$2"));
    }

@Test
    public void testReplaceAll_StringStringString_1_oe() {
        assertNull(RegExUtils.replaceAll(null, "", ""));
    }

@Test
    public void testReplaceAll_StringStringString_2_oe() {
        // removed other assertion

        assertEquals("any", RegExUtils.replaceAll("any", (String) null, ""));
    }

@Test
    public void testReplaceAll_StringStringString_3_oe() {
        // removed other assertion

        // removed other assertion
        assertEquals("any", RegExUtils.replaceAll("any", "", null));
    }

@Test
    public void testReplaceAll_StringStringString_4_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals("zzz", RegExUtils.replaceAll("", "", "zzz"));
    }

@Test
    public void testReplaceAll_StringStringString_5_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("zzz", RegExUtils.replaceAll("", ".*", "zzz"));
    }

@Test
    public void testReplaceAll_StringStringString_6_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("", RegExUtils.replaceAll("", ".+", "zzz"));
    }

@Test
    public void testReplaceAll_StringStringString_7_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ZZaZZbZZcZZ", RegExUtils.replaceAll("abc", "", "ZZ"));
    }

@Test
    public void testReplaceAll_StringStringString_8_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("z\nz", RegExUtils.replaceAll("<__>\n<__>", "<.*>", "z"));
    }

@Test
    public void testReplaceAll_StringStringString_9_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("z", RegExUtils.replaceAll("<__>\n<__>", "(?s)<.*>", "z"));
    }

@Test
    public void testReplaceAll_StringStringString_10_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals("ABC___123", RegExUtils.replaceAll("ABCabc123", "[a-z]", "_"));
    }

@Test
    public void testReplaceAll_StringStringString_11_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("ABC_123", RegExUtils.replaceAll("ABCabc123", "[^A-Z0-9]+", "_"));
    }

@Test
    public void testReplaceAll_StringStringString_12_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("ABC123", RegExUtils.replaceAll("ABCabc123", "[^A-Z0-9]+", ""));
    }

@Test
    public void testReplaceAll_StringStringString_13_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Lorem_ipsum_dolor_sit", RegExUtils.replaceAll("Lorem ipsum  dolor   sit", "( +)([a-z]+)", "_$2"));
    }

@Test
    public void testReplaceAll_StringStringString_14_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertThrows( PatternSyntaxException.class, () -> RegExUtils.replaceAll("any", "{badRegexSyntax}", ""), "RegExUtils.replaceAll expecting PatternSyntaxException");
    }

@Test
    public void testReplaceFirst_StringPatternString_1_oe() {
        assertNull(RegExUtils.replaceFirst(null, Pattern.compile(""), ""));
    }

@Test
    public void testReplaceFirst_StringPatternString_2_oe() {
        // removed other assertion

        assertEquals("any", RegExUtils.replaceFirst("any", (Pattern) null, ""));
    }

@Test
    public void testReplaceFirst_StringPatternString_3_oe() {
        // removed other assertion

        // removed other assertion
        assertEquals("any", RegExUtils.replaceFirst("any", Pattern.compile(""), null));
    }

@Test
    public void testReplaceFirst_StringPatternString_4_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals("zzz", RegExUtils.replaceFirst("", Pattern.compile(""), "zzz"));
    }

@Test
    public void testReplaceFirst_StringPatternString_5_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("zzz", RegExUtils.replaceFirst("", Pattern.compile(".*"), "zzz"));
    }

@Test
    public void testReplaceFirst_StringPatternString_6_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("", RegExUtils.replaceFirst("", Pattern.compile(".+"), "zzz"));
    }

@Test
    public void testReplaceFirst_StringPatternString_7_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ZZabc", RegExUtils.replaceFirst("abc", Pattern.compile(""), "ZZ"));
    }

@Test
    public void testReplaceFirst_StringPatternString_8_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("z\n<__>", RegExUtils.replaceFirst("<__>\n<__>", Pattern.compile("<.*>"), "z"));
    }

@Test
    public void testReplaceFirst_StringPatternString_9_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("z", RegExUtils.replaceFirst("<__>\n<__>", Pattern.compile("(?s)<.*>"), "z"));
    }

@Test
    public void testReplaceFirst_StringPatternString_10_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals("ABC_bc123", RegExUtils.replaceFirst("ABCabc123", Pattern.compile("[a-z]"), "_"));
    }

@Test
    public void testReplaceFirst_StringPatternString_11_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("ABC_123abc", RegExUtils.replaceFirst("ABCabc123abc", Pattern.compile("[^A-Z0-9]+"), "_"));
    }

@Test
    public void testReplaceFirst_StringPatternString_12_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("ABC123abc", RegExUtils.replaceFirst("ABCabc123abc", Pattern.compile("[^A-Z0-9]+"), ""));
    }

@Test
    public void testReplaceFirst_StringPatternString_13_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Lorem_ipsum dolor sit",RegExUtils.replaceFirst("Lorem ipsum dolor sit",Pattern.compile("(+)([a-z]+)"),"_$2"));
    }

@Test
    public void testReplaceFirst_StringStringString_1_oe() {
        assertNull(RegExUtils.replaceFirst(null, "", ""));
    }

@Test
    public void testReplaceFirst_StringStringString_2_oe() {
        // removed other assertion

        assertEquals("any", RegExUtils.replaceFirst("any", (String) null, ""));
    }

@Test
    public void testReplaceFirst_StringStringString_3_oe() {
        // removed other assertion

        // removed other assertion
        assertEquals("any", RegExUtils.replaceFirst("any", "", null));
    }

@Test
    public void testReplaceFirst_StringStringString_4_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals("zzz", RegExUtils.replaceFirst("", "", "zzz"));
    }

@Test
    public void testReplaceFirst_StringStringString_5_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("zzz", RegExUtils.replaceFirst("", ".*", "zzz"));
    }

@Test
    public void testReplaceFirst_StringStringString_6_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("", RegExUtils.replaceFirst("", ".+", "zzz"));
    }

@Test
    public void testReplaceFirst_StringStringString_7_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("ZZabc", RegExUtils.replaceFirst("abc", "", "ZZ"));
    }

@Test
    public void testReplaceFirst_StringStringString_8_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("z\n<__>", RegExUtils.replaceFirst("<__>\n<__>", "<.*>", "z"));
    }

@Test
    public void testReplaceFirst_StringStringString_9_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("z", RegExUtils.replaceFirst("<__>\n<__>", "(?s)<.*>", "z"));
    }

@Test
    public void testReplaceFirst_StringStringString_10_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        assertEquals("ABC_bc123", RegExUtils.replaceFirst("ABCabc123", "[a-z]", "_"));
    }

@Test
    public void testReplaceFirst_StringStringString_11_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("ABC_123abc", RegExUtils.replaceFirst("ABCabc123abc", "[^A-Z0-9]+", "_"));
    }

@Test
    public void testReplaceFirst_StringStringString_12_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("ABC123abc", RegExUtils.replaceFirst("ABCabc123abc", "[^A-Z0-9]+", ""));
    }

@Test
    public void testReplaceFirst_StringStringString_13_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Lorem_ipsum dolor sit",RegExUtils.replaceFirst("Lorem ipsum dolor sit","(+)([a-z]+)","_$2"));
    }

@Test
    public void testReplaceFirst_StringStringString_14_oe() {
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertThrows( PatternSyntaxException.class, () -> RegExUtils.replaceFirst("any", "{badRegexSyntax}", ""), "RegExUtils.replaceFirst expecting PatternSyntaxException");
    }

@Test
    public void testReplacePattern_StringStringString_1_oe() {
        assertNull(RegExUtils.replacePattern(null, "", ""));
    }

@Test
    public void testReplacePattern_StringStringString_2_oe() {
        // removed other assertion
        assertEquals("any", RegExUtils.replacePattern("any", (String) null, ""));
    }

@Test
    public void testReplacePattern_StringStringString_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("any", RegExUtils.replacePattern("any", "", null));
    }

@Test
    public void testReplacePattern_StringStringString_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("zzz", RegExUtils.replacePattern("", "", "zzz"));
    }

@Test
    public void testReplacePattern_StringStringString_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("zzz", RegExUtils.replacePattern("", ".*", "zzz"));
    }

@Test
    public void testReplacePattern_StringStringString_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("", RegExUtils.replacePattern("", ".+", "zzz"));
    }

@Test
    public void testReplacePattern_StringStringString_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("z", RegExUtils.replacePattern("<__>\n<__>", "<.*>", "z"));
    }

@Test
    public void testReplacePattern_StringStringString_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("z", RegExUtils.replacePattern("<__>\\n<__>", "<.*>", "z"));
    }

@Test
    public void testReplacePattern_StringStringString_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("X", RegExUtils.replacePattern("<A>\nxy\n</A>", "<A>.*</A>", "X"));
    }

@Test
    public void testReplacePattern_StringStringString_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("ABC___123", RegExUtils.replacePattern("ABCabc123", "[a-z]", "_"));
    }

@Test
    public void testReplacePattern_StringStringString_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("ABC_123", RegExUtils.replacePattern("ABCabc123", "[^A-Z0-9]+", "_"));
    }

@Test
    public void testReplacePattern_StringStringString_12_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("ABC123", RegExUtils.replacePattern("ABCabc123", "[^A-Z0-9]+", ""));
    }

@Test
    public void testReplacePattern_StringStringString_13_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Lorem_ipsum_dolor_sit",RegExUtils.replacePattern("Lorem ipsum dolor sit","(+)([a-z]+)","_$2"));
    }

}
