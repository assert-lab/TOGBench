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
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

/**
 * Unit test for Tokenizer.
 */
@Deprecated
public class StrTokenizerTest_OE25Dev {

    private static final String CSV_SIMPLE_FIXTURE = "A,b,c";

    private static final String TSV_SIMPLE_FIXTURE = "A\tb\tc";

    private void checkClone(final StrTokenizer tokenizer) {
        assertNotSame(StrTokenizer.getCSVInstance(), tokenizer);
        assertNotSame(StrTokenizer.getTSVInstance(), tokenizer);
    }

    // -----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    private void testCSV(final String data) {
        this.testXSVAbc(StrTokenizer.getCSVInstance(data));
        this.testXSVAbc(StrTokenizer.getCSVInstance(data.toCharArray()));
    }

    @Test
    public void testCSVEmpty() {
        this.testEmpty(StrTokenizer.getCSVInstance());
        this.testEmpty(StrTokenizer.getCSVInstance(""));
    }

    @Test
    public void testCSVSimple() {
        this.testCSV(CSV_SIMPLE_FIXTURE);
    }

    @Test
    public void testCSVSimpleNeedsTrim() {
        this.testCSV("   " + CSV_SIMPLE_FIXTURE);
        this.testCSV("   \n\t  " + CSV_SIMPLE_FIXTURE);
        this.testCSV("   \n  " + CSV_SIMPLE_FIXTURE + "\n\n\r");
    }

    void testEmpty(final StrTokenizer tokenizer) {
        this.checkClone(tokenizer);
        assertFalse(tokenizer.hasNext());
        assertFalse(tokenizer.hasPrevious());
        assertNull(tokenizer.nextToken());
        assertEquals(0, tokenizer.size());
        assertThrows(NoSuchElementException.class, tokenizer::next);
    }

    //-----------------------------------------------------------------------

    /**
     * Tests that the {@link StrTokenizer#clone()} clone method catches {@link CloneNotSupportedException} and returns
     * {@code null}.
     */

    // -----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    @Test
    public void testTSV() {
        this.testXSVAbc(StrTokenizer.getTSVInstance(TSV_SIMPLE_FIXTURE));
        this.testXSVAbc(StrTokenizer.getTSVInstance(TSV_SIMPLE_FIXTURE.toCharArray()));
    }

    @Test
    public void testTSVEmpty() {
        this.testEmpty(StrTokenizer.getTSVInstance());
        this.testEmpty(StrTokenizer.getTSVInstance(""));
    }

    void testXSVAbc(final StrTokenizer tokenizer) {
        this.checkClone(tokenizer);
        assertEquals(-1, tokenizer.previousIndex());
        assertEquals(0, tokenizer.nextIndex());
        assertNull(tokenizer.previousToken());
        assertEquals("A", tokenizer.nextToken());
        assertEquals(1, tokenizer.nextIndex());
        assertEquals("b", tokenizer.nextToken());
        assertEquals(2, tokenizer.nextIndex());
        assertEquals("c", tokenizer.nextToken());
        assertEquals(3, tokenizer.nextIndex());
        assertNull(tokenizer.nextToken());
        assertEquals(3, tokenizer.nextIndex());
        assertEquals("c", tokenizer.previousToken());
        assertEquals(2, tokenizer.nextIndex());
        assertEquals("b", tokenizer.previousToken());
        assertEquals(1, tokenizer.nextIndex());
        assertEquals("A", tokenizer.previousToken());
        assertEquals(0, tokenizer.nextIndex());
        assertNull(tokenizer.previousToken());
        assertEquals(0, tokenizer.nextIndex());
        assertEquals(-1, tokenizer.previousIndex());
        assertEquals(3, tokenizer.size());
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    @Test
    public void test1_1_oe() {

        final String input = "a;b;c;\"d;\"\"e\";f; ; ;  ";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setDelimiterChar(';');
        tok.setQuoteChar('"');
        tok.setIgnoredMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        final String[] tokens = tok.getTokenArray();

        final String[] expected = new String[]{"a", "b", "c", "d;\"e", "f", "", "", ""};

        assertEquals(expected.length, tokens.length, ArrayUtils.toString(tokens));
    }

    @Test
    public void test1_2_oe() {

        final String input = "a;b;c;\"d;\"\"e\";f; ; ;  ";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setDelimiterChar(';');
        tok.setQuoteChar('"');
        tok.setIgnoredMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        final String[] tokens = tok.getTokenArray();

        final String[] expected = new String[]{"a", "b", "c", "d;\"e", "f", "", "", ""};

        // removed other assertion
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], tokens[i], "token[" + i + "] was '" + tokens[i] + "' but was expected to be '" + expected[i] + "'");
    }
    }

    @Test
    public void test2_1_oe() {

        final String input = "a;b;c ;\"d;\"\"e\";f; ; ;";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setDelimiterChar(';');
        tok.setQuoteChar('"');
        tok.setIgnoredMatcher(StrMatcher.noneMatcher());
        tok.setIgnoreEmptyTokens(false);
        final String[] tokens = tok.getTokenArray();

        final String[] expected = new String[]{"a", "b", "c ", "d;\"e", "f", " ", " ", ""};

        assertEquals(expected.length, tokens.length, ArrayUtils.toString(tokens));
    }

    @Test
    public void test2_2_oe() {

        final String input = "a;b;c ;\"d;\"\"e\";f; ; ;";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setDelimiterChar(';');
        tok.setQuoteChar('"');
        tok.setIgnoredMatcher(StrMatcher.noneMatcher());
        tok.setIgnoreEmptyTokens(false);
        final String[] tokens = tok.getTokenArray();

        final String[] expected = new String[]{"a", "b", "c ", "d;\"e", "f", " ", " ", ""};

        // removed other assertion
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], tokens[i], "token[" + i + "] was '" + tokens[i] + "' but was expected to be '" + expected[i] + "'");
    }
    }

    @Test
    public void test3_1_oe() {

        final String input = "a;b; c;\"d;\"\"e\";f; ; ;";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setDelimiterChar(';');
        tok.setQuoteChar('"');
        tok.setIgnoredMatcher(StrMatcher.noneMatcher());
        tok.setIgnoreEmptyTokens(false);
        final String[] tokens = tok.getTokenArray();

        final String[] expected = new String[]{"a", "b", " c", "d;\"e", "f", " ", " ", ""};

        assertEquals(expected.length, tokens.length, ArrayUtils.toString(tokens));
    }

    @Test
    public void test3_2_oe() {

        final String input = "a;b; c;\"d;\"\"e\";f; ; ;";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setDelimiterChar(';');
        tok.setQuoteChar('"');
        tok.setIgnoredMatcher(StrMatcher.noneMatcher());
        tok.setIgnoreEmptyTokens(false);
        final String[] tokens = tok.getTokenArray();

        final String[] expected = new String[]{"a", "b", " c", "d;\"e", "f", " ", " ", ""};

        // removed other assertion
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], tokens[i], "token[" + i + "] was '" + tokens[i] + "' but was expected to be '" + expected[i] + "'");
    }
    }

    @Test
    public void test4_1_oe() {

        final String input = "a;b; c;\"d;\"\"e\";f; ; ;";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setDelimiterChar(';');
        tok.setQuoteChar('"');
        tok.setIgnoredMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(true);
        final String[] tokens = tok.getTokenArray();

        final String[] expected = new String[]{"a", "b", "c", "d;\"e", "f"};

        assertEquals(expected.length, tokens.length, ArrayUtils.toString(tokens));
    }

    @Test
    public void test4_2_oe() {

        final String input = "a;b; c;\"d;\"\"e\";f; ; ;";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setDelimiterChar(';');
        tok.setQuoteChar('"');
        tok.setIgnoredMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(true);
        final String[] tokens = tok.getTokenArray();

        final String[] expected = new String[]{"a", "b", "c", "d;\"e", "f"};

        // removed other assertion
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], tokens[i], "token[" + i + "] was '" + tokens[i] + "' but was expected to be '" + expected[i] + "'");
    }
    }

    @Test
    public void test5_1_oe() {

        final String input = "a;b; c;\"d;\"\"e\";f; ; ;";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setDelimiterChar(';');
        tok.setQuoteChar('"');
        tok.setIgnoredMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        final String[] tokens = tok.getTokenArray();

        final String[] expected = new String[]{"a", "b", "c", "d;\"e", "f", null, null, null};

        assertEquals(expected.length, tokens.length, ArrayUtils.toString(tokens));
    }

    @Test
    public void test5_2_oe() {

        final String input = "a;b; c;\"d;\"\"e\";f; ; ;";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setDelimiterChar(';');
        tok.setQuoteChar('"');
        tok.setIgnoredMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        final String[] tokens = tok.getTokenArray();

        final String[] expected = new String[]{"a", "b", "c", "d;\"e", "f", null, null, null};

        // removed other assertion
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], tokens[i], "token[" + i + "] was '" + tokens[i] + "' but was expected to be '" + expected[i] + "'");
    }
    }

    @Test
    public void test6_1_oe() {

        final String input = "a;b; c;\"d;\"\"e\";f; ; ;";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setDelimiterChar(';');
        tok.setQuoteChar('"');
        tok.setIgnoredMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        // tok.setTreatingEmptyAsNull(true);
        final String[] tokens = tok.getTokenArray();

        final String[] expected = new String[]{"a", "b", " c", "d;\"e", "f", null, null, null};

        int nextCount = 0;
        while (tok.hasNext()) {
            tok.next();
            nextCount++;
        }

        int prevCount = 0;
        while (tok.hasPrevious()) {
            tok.previous();
            prevCount++;
        }

        assertEquals(expected.length, tokens.length, ArrayUtils.toString(tokens));
    }

    @Test
    public void test6_2_oe() {

        final String input = "a;b; c;\"d;\"\"e\";f; ; ;";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setDelimiterChar(';');
        tok.setQuoteChar('"');
        tok.setIgnoredMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        // tok.setTreatingEmptyAsNull(true);
        final String[] tokens = tok.getTokenArray();

        final String[] expected = new String[]{"a", "b", " c", "d;\"e", "f", null, null, null};

        int nextCount = 0;
        while (tok.hasNext()) {
            tok.next();
            nextCount++;
        }

        int prevCount = 0;
        while (tok.hasPrevious()) {
            tok.previous();
            prevCount++;
        }

        // removed other assertion

        assertEquals(nextCount, expected.length, "could not cycle through entire token list" + " using the 'hasNext' and 'next' methods");
    }

    @Test
    public void test6_3_oe() {

        final String input = "a;b; c;\"d;\"\"e\";f; ; ;";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setDelimiterChar(';');
        tok.setQuoteChar('"');
        tok.setIgnoredMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        // tok.setTreatingEmptyAsNull(true);
        final String[] tokens = tok.getTokenArray();

        final String[] expected = new String[]{"a", "b", " c", "d;\"e", "f", null, null, null};

        int nextCount = 0;
        while (tok.hasNext()) {
            tok.next();
            nextCount++;
        }

        int prevCount = 0;
        while (tok.hasPrevious()) {
            tok.previous();
            prevCount++;
        }

        // removed other assertion

        // removed other assertion

        assertEquals(prevCount, expected.length, "could not cycle through entire token list" + " using the 'hasPrevious' and 'previous' methods");
    }

    @Test
    public void test7_1_oe() {

        final String input = "a   b c \"d e\" f ";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setDelimiterMatcher(StrMatcher.spaceMatcher());
        tok.setQuoteMatcher(StrMatcher.doubleQuoteMatcher());
        tok.setIgnoredMatcher(StrMatcher.noneMatcher());
        tok.setIgnoreEmptyTokens(false);
        final String[] tokens = tok.getTokenArray();

        final String[] expected = new String[]{"a", "", "", "b", "c", "d e", "f", ""};

        assertEquals(expected.length, tokens.length, ArrayUtils.toString(tokens));
    }

    @Test
    public void test7_2_oe() {

        final String input = "a   b c \"d e\" f ";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setDelimiterMatcher(StrMatcher.spaceMatcher());
        tok.setQuoteMatcher(StrMatcher.doubleQuoteMatcher());
        tok.setIgnoredMatcher(StrMatcher.noneMatcher());
        tok.setIgnoreEmptyTokens(false);
        final String[] tokens = tok.getTokenArray();

        final String[] expected = new String[]{"a", "", "", "b", "c", "d e", "f", ""};

        // removed other assertion
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], tokens[i], "token[" + i + "] was '" + tokens[i] + "' but was expected to be '" + expected[i] + "'");
    }
    }

    @Test
    public void test8_1_oe() {

        final String input = "a   b c \"d e\" f ";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setDelimiterMatcher(StrMatcher.spaceMatcher());
        tok.setQuoteMatcher(StrMatcher.doubleQuoteMatcher());
        tok.setIgnoredMatcher(StrMatcher.noneMatcher());
        tok.setIgnoreEmptyTokens(true);
        final String[] tokens = tok.getTokenArray();

        final String[] expected = new String[]{"a", "b", "c", "d e", "f"};

        assertEquals(expected.length, tokens.length, ArrayUtils.toString(tokens));
    }

    @Test
    public void test8_2_oe() {

        final String input = "a   b c \"d e\" f ";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setDelimiterMatcher(StrMatcher.spaceMatcher());
        tok.setQuoteMatcher(StrMatcher.doubleQuoteMatcher());
        tok.setIgnoredMatcher(StrMatcher.noneMatcher());
        tok.setIgnoreEmptyTokens(true);
        final String[] tokens = tok.getTokenArray();

        final String[] expected = new String[]{"a", "b", "c", "d e", "f"};

        // removed other assertion
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], tokens[i], "token[" + i + "] was '" + tokens[i] + "' but was expected to be '" + expected[i] + "'");
    }
    }

    @Test
    public void testBasic1_1_oe() {
        final String input = "a  b c";
        final StrTokenizer tok = new StrTokenizer(input);
        assertEquals("a", tok.next());
    }

    @Test
    public void testBasic1_2_oe() {
        final String input = "a  b c";
        final StrTokenizer tok = new StrTokenizer(input);
        // removed other assertion
        assertEquals("b", tok.next());
    }

    @Test
    public void testBasic1_3_oe() {
        final String input = "a  b c";
        final StrTokenizer tok = new StrTokenizer(input);
        // removed other assertion
        // removed other assertion
        assertEquals("c", tok.next());
    }

    @Test
    public void testBasic1_4_oe() {
        final String input = "a  b c";
        final StrTokenizer tok = new StrTokenizer(input);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testBasic2_1_oe() {
        final String input = "a \nb\fc";
        final StrTokenizer tok = new StrTokenizer(input);
        assertEquals("a", tok.next());
    }

    @Test
    public void testBasic2_2_oe() {
        final String input = "a \nb\fc";
        final StrTokenizer tok = new StrTokenizer(input);
        // removed other assertion
        assertEquals("b", tok.next());
    }

    @Test
    public void testBasic2_3_oe() {
        final String input = "a \nb\fc";
        final StrTokenizer tok = new StrTokenizer(input);
        // removed other assertion
        // removed other assertion
        assertEquals("c", tok.next());
    }

    @Test
    public void testBasic2_4_oe() {
        final String input = "a \nb\fc";
        final StrTokenizer tok = new StrTokenizer(input);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testBasic3_1_oe() {
        final String input = "a \nb\u0001\fc";
        final StrTokenizer tok = new StrTokenizer(input);
        assertEquals("a", tok.next());
    }

    @Test
    public void testBasic3_2_oe() {
        final String input = "a \nb\u0001\fc";
        final StrTokenizer tok = new StrTokenizer(input);
        // removed other assertion
        assertEquals("b\u0001", tok.next());
    }

    @Test
    public void testBasic3_3_oe() {
        final String input = "a \nb\u0001\fc";
        final StrTokenizer tok = new StrTokenizer(input);
        // removed other assertion
        // removed other assertion
        assertEquals("c", tok.next());
    }

    @Test
    public void testBasic3_4_oe() {
        final String input = "a \nb\u0001\fc";
        final StrTokenizer tok = new StrTokenizer(input);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testBasic4_1_oe() {
        final String input = "a \"b\" c";
        final StrTokenizer tok = new StrTokenizer(input);
        assertEquals("a", tok.next());
    }

    @Test
    public void testBasic4_2_oe() {
        final String input = "a \"b\" c";
        final StrTokenizer tok = new StrTokenizer(input);
        // removed other assertion
        assertEquals("\"b\"", tok.next());
    }

    @Test
    public void testBasic4_3_oe() {
        final String input = "a \"b\" c";
        final StrTokenizer tok = new StrTokenizer(input);
        // removed other assertion
        // removed other assertion
        assertEquals("c", tok.next());
    }

    @Test
    public void testBasic4_4_oe() {
        final String input = "a \"b\" c";
        final StrTokenizer tok = new StrTokenizer(input);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testBasic5_1_oe() {
        final String input = "a:b':c";
        final StrTokenizer tok = new StrTokenizer(input, ':', '\'');
        assertEquals("a", tok.next());
    }

    @Test
    public void testBasic5_2_oe() {
        final String input = "a:b':c";
        final StrTokenizer tok = new StrTokenizer(input, ':', '\'');
        // removed other assertion
        assertEquals("b'", tok.next());
    }

    @Test
    public void testBasic5_3_oe() {
        final String input = "a:b':c";
        final StrTokenizer tok = new StrTokenizer(input, ':', '\'');
        // removed other assertion
        // removed other assertion
        assertEquals("c", tok.next());
    }

    @Test
    public void testBasic5_4_oe() {
        final String input = "a:b':c";
        final StrTokenizer tok = new StrTokenizer(input, ':', '\'');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testBasicDelim1_1_oe() {
        final String input = "a:b:c";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        assertEquals("a", tok.next());
    }

    @Test
    public void testBasicDelim1_2_oe() {
        final String input = "a:b:c";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        // removed other assertion
        assertEquals("b", tok.next());
    }

    @Test
    public void testBasicDelim1_3_oe() {
        final String input = "a:b:c";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        // removed other assertion
        // removed other assertion
        assertEquals("c", tok.next());
    }

    @Test
    public void testBasicDelim1_4_oe() {
        final String input = "a:b:c";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testBasicDelim2_1_oe() {
        final String input = "a:b:c";
        final StrTokenizer tok = new StrTokenizer(input, ',');
        assertEquals("a:b:c", tok.next());
    }

    @Test
    public void testBasicDelim2_2_oe() {
        final String input = "a:b:c";
        final StrTokenizer tok = new StrTokenizer(input, ',');
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testBasicEmpty1_1_oe() {
        final String input = "a  b c";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setIgnoreEmptyTokens(false);
        assertEquals("a", tok.next());
    }

    @Test
    public void testBasicEmpty1_2_oe() {
        final String input = "a  b c";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setIgnoreEmptyTokens(false);
        // removed other assertion
        assertEquals("", tok.next());
    }

    @Test
    public void testBasicEmpty1_3_oe() {
        final String input = "a  b c";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setIgnoreEmptyTokens(false);
        // removed other assertion
        // removed other assertion
        assertEquals("b", tok.next());
    }

    @Test
    public void testBasicEmpty1_4_oe() {
        final String input = "a  b c";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setIgnoreEmptyTokens(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("c", tok.next());
    }

    @Test
    public void testBasicEmpty1_5_oe() {
        final String input = "a  b c";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setIgnoreEmptyTokens(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testBasicEmpty2_1_oe() {
        final String input = "a  b c";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        assertEquals("a", tok.next());
    }

    @Test
    public void testBasicEmpty2_2_oe() {
        final String input = "a  b c";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        assertNull(tok.next());
    }

    @Test
    public void testBasicEmpty2_3_oe() {
        final String input = "a  b c";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        // removed other assertion
        assertEquals("b", tok.next());
    }

    @Test
    public void testBasicEmpty2_4_oe() {
        final String input = "a  b c";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("c", tok.next());
    }

    @Test
    public void testBasicEmpty2_5_oe() {
        final String input = "a  b c";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testBasicQuoted1_1_oe() {
        final String input = "a 'b' c";
        final StrTokenizer tok = new StrTokenizer(input, ' ', '\'');
        assertEquals("a", tok.next());
    }

    @Test
    public void testBasicQuoted1_2_oe() {
        final String input = "a 'b' c";
        final StrTokenizer tok = new StrTokenizer(input, ' ', '\'');
        // removed other assertion
        assertEquals("b", tok.next());
    }

    @Test
    public void testBasicQuoted1_3_oe() {
        final String input = "a 'b' c";
        final StrTokenizer tok = new StrTokenizer(input, ' ', '\'');
        // removed other assertion
        // removed other assertion
        assertEquals("c", tok.next());
    }

    @Test
    public void testBasicQuoted1_4_oe() {
        final String input = "a 'b' c";
        final StrTokenizer tok = new StrTokenizer(input, ' ', '\'');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testBasicQuoted2_1_oe() {
        final String input = "a:'b':";
        final StrTokenizer tok = new StrTokenizer(input, ':', '\'');
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        assertEquals("a", tok.next());
    }

    @Test
    public void testBasicQuoted2_2_oe() {
        final String input = "a:'b':";
        final StrTokenizer tok = new StrTokenizer(input, ':', '\'');
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        assertEquals("b", tok.next());
    }

    @Test
    public void testBasicQuoted2_3_oe() {
        final String input = "a:'b':";
        final StrTokenizer tok = new StrTokenizer(input, ':', '\'');
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        // removed other assertion
        assertNull(tok.next());
    }

    @Test
    public void testBasicQuoted2_4_oe() {
        final String input = "a:'b':";
        final StrTokenizer tok = new StrTokenizer(input, ':', '\'');
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testBasicQuoted3_1_oe() {
        final String input = "a:'b''c'";
        final StrTokenizer tok = new StrTokenizer(input, ':', '\'');
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        assertEquals("a", tok.next());
    }

    @Test
    public void testBasicQuoted3_2_oe() {
        final String input = "a:'b''c'";
        final StrTokenizer tok = new StrTokenizer(input, ':', '\'');
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        assertEquals("b'c", tok.next());
    }

    @Test
    public void testBasicQuoted3_3_oe() {
        final String input = "a:'b''c'";
        final StrTokenizer tok = new StrTokenizer(input, ':', '\'');
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testBasicQuoted4_1_oe() {
        final String input = "a: 'b' 'c' :d";
        final StrTokenizer tok = new StrTokenizer(input, ':', '\'');
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        assertEquals("a", tok.next());
    }

    @Test
    public void testBasicQuoted4_2_oe() {
        final String input = "a: 'b' 'c' :d";
        final StrTokenizer tok = new StrTokenizer(input, ':', '\'');
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        assertEquals("b c", tok.next());
    }

    @Test
    public void testBasicQuoted4_3_oe() {
        final String input = "a: 'b' 'c' :d";
        final StrTokenizer tok = new StrTokenizer(input, ':', '\'');
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        // removed other assertion
        assertEquals("d", tok.next());
    }

    @Test
    public void testBasicQuoted4_4_oe() {
        final String input = "a: 'b' 'c' :d";
        final StrTokenizer tok = new StrTokenizer(input, ':', '\'');
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testBasicQuoted5_1_oe() {
        final String input = "a: 'b'x'c' :d";
        final StrTokenizer tok = new StrTokenizer(input, ':', '\'');
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        assertEquals("a", tok.next());
    }

    @Test
    public void testBasicQuoted5_2_oe() {
        final String input = "a: 'b'x'c' :d";
        final StrTokenizer tok = new StrTokenizer(input, ':', '\'');
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        assertEquals("bxc", tok.next());
    }

    @Test
    public void testBasicQuoted5_3_oe() {
        final String input = "a: 'b'x'c' :d";
        final StrTokenizer tok = new StrTokenizer(input, ':', '\'');
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        // removed other assertion
        assertEquals("d", tok.next());
    }

    @Test
    public void testBasicQuoted5_4_oe() {
        final String input = "a: 'b'x'c' :d";
        final StrTokenizer tok = new StrTokenizer(input, ':', '\'');
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testBasicQuoted6_1_oe() {
        final String input = "a:'b'\"c':d";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        tok.setQuoteMatcher(StrMatcher.quoteMatcher());
        assertEquals("a", tok.next());
    }

    @Test
    public void testBasicQuoted6_2_oe() {
        final String input = "a:'b'\"c':d";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        tok.setQuoteMatcher(StrMatcher.quoteMatcher());
        // removed other assertion
        assertEquals("b\"c:d", tok.next());
    }

    @Test
    public void testBasicQuoted6_3_oe() {
        final String input = "a:'b'\"c':d";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        tok.setQuoteMatcher(StrMatcher.quoteMatcher());
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testBasicQuoted7_1_oe() {
        final String input = "a:\"There's a reason here\":b";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        tok.setQuoteMatcher(StrMatcher.quoteMatcher());
        assertEquals("a", tok.next());
    }

    @Test
    public void testBasicQuoted7_2_oe() {
        final String input = "a:\"There's a reason here\":b";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        tok.setQuoteMatcher(StrMatcher.quoteMatcher());
        // removed other assertion
        assertEquals("There's a reason here", tok.next());
    }

    @Test
    public void testBasicQuoted7_3_oe() {
        final String input = "a:\"There's a reason here\":b";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        tok.setQuoteMatcher(StrMatcher.quoteMatcher());
        // removed other assertion
        // removed other assertion
        assertEquals("b", tok.next());
    }

    @Test
    public void testBasicQuoted7_4_oe() {
        final String input = "a:\"There's a reason here\":b";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        tok.setQuoteMatcher(StrMatcher.quoteMatcher());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testBasicQuotedTrimmed1_1_oe() {
        final String input = "a: 'b' :";
        final StrTokenizer tok = new StrTokenizer(input, ':', '\'');
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        assertEquals("a", tok.next());
    }

    @Test
    public void testBasicQuotedTrimmed1_2_oe() {
        final String input = "a: 'b' :";
        final StrTokenizer tok = new StrTokenizer(input, ':', '\'');
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        assertEquals("b", tok.next());
    }

    @Test
    public void testBasicQuotedTrimmed1_3_oe() {
        final String input = "a: 'b' :";
        final StrTokenizer tok = new StrTokenizer(input, ':', '\'');
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        // removed other assertion
        assertNull(tok.next());
    }

    @Test
    public void testBasicQuotedTrimmed1_4_oe() {
        final String input = "a: 'b' :";
        final StrTokenizer tok = new StrTokenizer(input, ':', '\'');
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testBasicTrimmed1_1_oe() {
        final String input = "a: b :  ";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        assertEquals("a", tok.next());
    }

    @Test
    public void testBasicTrimmed1_2_oe() {
        final String input = "a: b :  ";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        assertEquals("b", tok.next());
    }

    @Test
    public void testBasicTrimmed1_3_oe() {
        final String input = "a: b :  ";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        // removed other assertion
        assertNull(tok.next());
    }

    @Test
    public void testBasicTrimmed1_4_oe() {
        final String input = "a: b :  ";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testBasicTrimmed2_1_oe() {
        final String input = "a:  b  :";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        tok.setTrimmerMatcher(StrMatcher.stringMatcher("  "));
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        assertEquals("a", tok.next());
    }

    @Test
    public void testBasicTrimmed2_2_oe() {
        final String input = "a:  b  :";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        tok.setTrimmerMatcher(StrMatcher.stringMatcher("  "));
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        assertEquals("b", tok.next());
    }

    @Test
    public void testBasicTrimmed2_3_oe() {
        final String input = "a:  b  :";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        tok.setTrimmerMatcher(StrMatcher.stringMatcher("  "));
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        // removed other assertion
        assertNull(tok.next());
    }

    @Test
    public void testBasicTrimmed2_4_oe() {
        final String input = "a:  b  :";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        tok.setTrimmerMatcher(StrMatcher.stringMatcher("  "));
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testBasicIgnoreTrimmed1_1_oe() {
        final String input = "a: bIGNOREc : ";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        tok.setIgnoredMatcher(StrMatcher.stringMatcher("IGNORE"));
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        assertEquals("a", tok.next());
    }

    @Test
    public void testBasicIgnoreTrimmed1_2_oe() {
        final String input = "a: bIGNOREc : ";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        tok.setIgnoredMatcher(StrMatcher.stringMatcher("IGNORE"));
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        assertEquals("bc", tok.next());
    }

    @Test
    public void testBasicIgnoreTrimmed1_3_oe() {
        final String input = "a: bIGNOREc : ";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        tok.setIgnoredMatcher(StrMatcher.stringMatcher("IGNORE"));
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        // removed other assertion
        assertNull(tok.next());
    }

    @Test
    public void testBasicIgnoreTrimmed1_4_oe() {
        final String input = "a: bIGNOREc : ";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        tok.setIgnoredMatcher(StrMatcher.stringMatcher("IGNORE"));
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testBasicIgnoreTrimmed2_1_oe() {
        final String input = "IGNOREaIGNORE: IGNORE bIGNOREc IGNORE : IGNORE ";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        tok.setIgnoredMatcher(StrMatcher.stringMatcher("IGNORE"));
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        assertEquals("a", tok.next());
    }

    @Test
    public void testBasicIgnoreTrimmed2_2_oe() {
        final String input = "IGNOREaIGNORE: IGNORE bIGNOREc IGNORE : IGNORE ";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        tok.setIgnoredMatcher(StrMatcher.stringMatcher("IGNORE"));
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        assertEquals("bc", tok.next());
    }

    @Test
    public void testBasicIgnoreTrimmed2_3_oe() {
        final String input = "IGNOREaIGNORE: IGNORE bIGNOREc IGNORE : IGNORE ";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        tok.setIgnoredMatcher(StrMatcher.stringMatcher("IGNORE"));
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        // removed other assertion
        assertNull(tok.next());
    }

    @Test
    public void testBasicIgnoreTrimmed2_4_oe() {
        final String input = "IGNOREaIGNORE: IGNORE bIGNOREc IGNORE : IGNORE ";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        tok.setIgnoredMatcher(StrMatcher.stringMatcher("IGNORE"));
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testBasicIgnoreTrimmed3_1_oe() {
        final String input = "IGNOREaIGNORE: IGNORE bIGNOREc IGNORE : IGNORE ";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        tok.setIgnoredMatcher(StrMatcher.stringMatcher("IGNORE"));
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        assertEquals("a", tok.next());
    }

    @Test
    public void testBasicIgnoreTrimmed3_2_oe() {
        final String input = "IGNOREaIGNORE: IGNORE bIGNOREc IGNORE : IGNORE ";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        tok.setIgnoredMatcher(StrMatcher.stringMatcher("IGNORE"));
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        assertEquals("  bc  ", tok.next());
    }

    @Test
    public void testBasicIgnoreTrimmed3_3_oe() {
        final String input = "IGNOREaIGNORE: IGNORE bIGNOREc IGNORE : IGNORE ";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        tok.setIgnoredMatcher(StrMatcher.stringMatcher("IGNORE"));
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        // removed other assertion
        assertEquals("  ", tok.next());
    }

    @Test
    public void testBasicIgnoreTrimmed3_4_oe() {
        final String input = "IGNOREaIGNORE: IGNORE bIGNOREc IGNORE : IGNORE ";
        final StrTokenizer tok = new StrTokenizer(input, ':');
        tok.setIgnoredMatcher(StrMatcher.stringMatcher("IGNORE"));
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testBasicIgnoreTrimmed4_1_oe() {
        final String input = "IGNOREaIGNORE: IGNORE 'bIGNOREc'IGNORE'd' IGNORE : IGNORE ";
        final StrTokenizer tok = new StrTokenizer(input, ':', '\'');
        tok.setIgnoredMatcher(StrMatcher.stringMatcher("IGNORE"));
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        assertEquals("a", tok.next());
    }

    @Test
    public void testBasicIgnoreTrimmed4_2_oe() {
        final String input = "IGNOREaIGNORE: IGNORE 'bIGNOREc'IGNORE'd' IGNORE : IGNORE ";
        final StrTokenizer tok = new StrTokenizer(input, ':', '\'');
        tok.setIgnoredMatcher(StrMatcher.stringMatcher("IGNORE"));
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        assertEquals("bIGNOREcd", tok.next());
    }

    @Test
    public void testBasicIgnoreTrimmed4_3_oe() {
        final String input = "IGNOREaIGNORE: IGNORE 'bIGNOREc'IGNORE'd' IGNORE : IGNORE ";
        final StrTokenizer tok = new StrTokenizer(input, ':', '\'');
        tok.setIgnoredMatcher(StrMatcher.stringMatcher("IGNORE"));
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        // removed other assertion
        assertNull(tok.next());
    }

    @Test
    public void testBasicIgnoreTrimmed4_4_oe() {
        final String input = "IGNOREaIGNORE: IGNORE 'bIGNOREc'IGNORE'd' IGNORE : IGNORE ";
        final StrTokenizer tok = new StrTokenizer(input, ':', '\'');
        tok.setIgnoredMatcher(StrMatcher.stringMatcher("IGNORE"));
        tok.setTrimmerMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testListArray_1_oe() {
        final String input = "a  b c";
        final StrTokenizer tok = new StrTokenizer(input);
        final String[] array = tok.getTokenArray();
        final List<?> list = tok.getTokenList();

        assertEquals(Arrays.asList(array), list);
    }

    @Test
    public void testListArray_2_oe() {
        final String input = "a  b c";
        final StrTokenizer tok = new StrTokenizer(input);
        final String[] array = tok.getTokenArray();
        final List<?> list = tok.getTokenList();

        // removed other assertion
        assertEquals(3, list.size());
    }

    @Test
    public void testGetContent_1_oe() {
        final String input = "a   b c \"d e\" f ";
        StrTokenizer tok = new StrTokenizer(input);
        assertEquals(input, tok.getContent());
    }

    @Test
    public void testGetContent_2_oe() {
        final String input = "a   b c \"d e\" f ";
        StrTokenizer tok = new StrTokenizer(input);
        // removed other assertion

        tok = new StrTokenizer(input.toCharArray());
        assertEquals(input, tok.getContent());
    }

    @Test
    public void testGetContent_3_oe() {
        final String input = "a   b c \"d e\" f ";
        StrTokenizer tok = new StrTokenizer(input);
        // removed other assertion

        tok = new StrTokenizer(input.toCharArray());
        // removed other assertion

        tok = new StrTokenizer();
        assertNull(tok.getContent());
    }

    @Test
    public void testChaining_1_oe() {
        final StrTokenizer tok = new StrTokenizer();
        assertEquals(tok, tok.reset());
    }

    @Test
    public void testChaining_2_oe() {
        final StrTokenizer tok = new StrTokenizer();
        // removed other assertion
        assertEquals(tok, tok.reset(""));
    }

    @Test
    public void testChaining_3_oe() {
        final StrTokenizer tok = new StrTokenizer();
        // removed other assertion
        // removed other assertion
        assertEquals(tok, tok.reset(new char[0]));
    }

    @Test
    public void testChaining_4_oe() {
        final StrTokenizer tok = new StrTokenizer();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(tok, tok.setDelimiterChar(' '));
    }

    @Test
    public void testChaining_5_oe() {
        final StrTokenizer tok = new StrTokenizer();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(tok, tok.setDelimiterString(" "));
    }

    @Test
    public void testChaining_6_oe() {
        final StrTokenizer tok = new StrTokenizer();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(tok, tok.setDelimiterMatcher(null));
    }

    @Test
    public void testChaining_7_oe() {
        final StrTokenizer tok = new StrTokenizer();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(tok, tok.setQuoteChar(' '));
    }

    @Test
    public void testChaining_8_oe() {
        final StrTokenizer tok = new StrTokenizer();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(tok, tok.setQuoteMatcher(null));
    }

    @Test
    public void testChaining_9_oe() {
        final StrTokenizer tok = new StrTokenizer();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(tok, tok.setIgnoredChar(' '));
    }

    @Test
    public void testChaining_10_oe() {
        final StrTokenizer tok = new StrTokenizer();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(tok, tok.setIgnoredMatcher(null));
    }

    @Test
    public void testChaining_11_oe() {
        final StrTokenizer tok = new StrTokenizer();
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
        assertEquals(tok, tok.setTrimmerMatcher(null));
    }

    @Test
    public void testChaining_12_oe() {
        final StrTokenizer tok = new StrTokenizer();
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
        assertEquals(tok, tok.setEmptyTokenAsNull(false));
    }

    @Test
    public void testChaining_13_oe() {
        final StrTokenizer tok = new StrTokenizer();
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
        assertEquals(tok, tok.setIgnoreEmptyTokens(false));
    }

    @Test
    public void testCloneNotSupportedException_1_oe() {
        final Object notCloned = new StrTokenizer() {
            @Override
            Object cloneReset() throws CloneNotSupportedException {
                throw new CloneNotSupportedException("test");
            }
        }.clone();
        assertNull(notCloned);
    }

    @Test
    public void testCloneNull_1_oe() {
        final StrTokenizer tokenizer = new StrTokenizer((char[]) null);
        // Start sanity check
        assertNull(tokenizer.nextToken());
    }

    @Test
    public void testCloneNull_2_oe() {
        final StrTokenizer tokenizer = new StrTokenizer((char[]) null);
        // Start sanity check
        // removed other assertion
        tokenizer.reset();
        assertNull(tokenizer.nextToken());
    }

    @Test
    public void testCloneNull_3_oe() {
        final StrTokenizer tokenizer = new StrTokenizer((char[]) null);
        // Start sanity check
        // removed other assertion
        tokenizer.reset();
        // removed other assertion
        // End sanity check
        final StrTokenizer clonedTokenizer = (StrTokenizer) tokenizer.clone();
        tokenizer.reset();
        assertNull(tokenizer.nextToken());
    }

    @Test
    public void testCloneNull_4_oe() {
        final StrTokenizer tokenizer = new StrTokenizer((char[]) null);
        // Start sanity check
        // removed other assertion
        tokenizer.reset();
        // removed other assertion
        // End sanity check
        final StrTokenizer clonedTokenizer = (StrTokenizer) tokenizer.clone();
        tokenizer.reset();
        // removed other assertion
        assertNull(clonedTokenizer.nextToken());
    }

    @Test
    public void testCloneReset_1_oe() {
        final char[] input = new char[]{'a'};
        final StrTokenizer tokenizer = new StrTokenizer(input);
        // Start sanity check
        assertEquals("a", tokenizer.nextToken());
    }

    @Test
    public void testCloneReset_2_oe() {
        final char[] input = new char[]{'a'};
        final StrTokenizer tokenizer = new StrTokenizer(input);
        // Start sanity check
        // removed other assertion
        tokenizer.reset(input);
        assertEquals("a", tokenizer.nextToken());
    }

    @Test
    public void testCloneReset_3_oe() {
        final char[] input = new char[]{'a'};
        final StrTokenizer tokenizer = new StrTokenizer(input);
        // Start sanity check
        // removed other assertion
        tokenizer.reset(input);
        // removed other assertion
        // End sanity check
        final StrTokenizer clonedTokenizer = (StrTokenizer) tokenizer.clone();
        input[0] = 'b';
        tokenizer.reset(input);
        assertEquals("b", tokenizer.nextToken());
    }

    @Test
    public void testCloneReset_4_oe() {
        final char[] input = new char[]{'a'};
        final StrTokenizer tokenizer = new StrTokenizer(input);
        // Start sanity check
        // removed other assertion
        tokenizer.reset(input);
        // removed other assertion
        // End sanity check
        final StrTokenizer clonedTokenizer = (StrTokenizer) tokenizer.clone();
        input[0] = 'b';
        tokenizer.reset(input);
        // removed other assertion
        assertEquals("a", clonedTokenizer.nextToken());
    }

    @Test
    public void testConstructor_String_1_oe() {
        StrTokenizer tok = new StrTokenizer("a b");
        assertEquals("a", tok.next());
    }

    @Test
    public void testConstructor_String_2_oe() {
        StrTokenizer tok = new StrTokenizer("a b");
        // removed other assertion
        assertEquals("b", tok.next());
    }

    @Test
    public void testConstructor_String_3_oe() {
        StrTokenizer tok = new StrTokenizer("a b");
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testConstructor_String_4_oe() {
        StrTokenizer tok = new StrTokenizer("a b");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tok = new StrTokenizer("");
        assertFalse(tok.hasNext());
    }

    @Test
    public void testConstructor_String_5_oe() {
        StrTokenizer tok = new StrTokenizer("a b");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tok = new StrTokenizer("");
        // removed other assertion

        tok = new StrTokenizer((String) null);
        assertFalse(tok.hasNext());
    }

    @Test
    public void testConstructor_String_char_1_oe() {
        StrTokenizer tok = new StrTokenizer("a b", ' ');
        assertEquals(1, tok.getDelimiterMatcher().isMatch(" ".toCharArray(), 0, 0, 1));
    }

    @Test
    public void testConstructor_String_char_2_oe() {
        StrTokenizer tok = new StrTokenizer("a b", ' ');
        // removed other assertion
        assertEquals("a", tok.next());
    }

    @Test
    public void testConstructor_String_char_3_oe() {
        StrTokenizer tok = new StrTokenizer("a b", ' ');
        // removed other assertion
        // removed other assertion
        assertEquals("b", tok.next());
    }

    @Test
    public void testConstructor_String_char_4_oe() {
        StrTokenizer tok = new StrTokenizer("a b", ' ');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testConstructor_String_char_5_oe() {
        StrTokenizer tok = new StrTokenizer("a b", ' ');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tok = new StrTokenizer("", ' ');
        assertFalse(tok.hasNext());
    }

    @Test
    public void testConstructor_String_char_6_oe() {
        StrTokenizer tok = new StrTokenizer("a b", ' ');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tok = new StrTokenizer("", ' ');
        // removed other assertion

        tok = new StrTokenizer((String) null, ' ');
        assertFalse(tok.hasNext());
    }

    @Test
    public void testConstructor_String_char_char_1_oe() {
        StrTokenizer tok = new StrTokenizer("a b", ' ', '"');
        assertEquals(1, tok.getDelimiterMatcher().isMatch(" ".toCharArray(), 0, 0, 1));
    }

    @Test
    public void testConstructor_String_char_char_2_oe() {
        StrTokenizer tok = new StrTokenizer("a b", ' ', '"');
        // removed other assertion
        assertEquals(1, tok.getQuoteMatcher().isMatch("\"".toCharArray(), 0, 0, 1));
    }

    @Test
    public void testConstructor_String_char_char_3_oe() {
        StrTokenizer tok = new StrTokenizer("a b", ' ', '"');
        // removed other assertion
        // removed other assertion
        assertEquals("a", tok.next());
    }

    @Test
    public void testConstructor_String_char_char_4_oe() {
        StrTokenizer tok = new StrTokenizer("a b", ' ', '"');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("b", tok.next());
    }

    @Test
    public void testConstructor_String_char_char_5_oe() {
        StrTokenizer tok = new StrTokenizer("a b", ' ', '"');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testConstructor_String_char_char_6_oe() {
        StrTokenizer tok = new StrTokenizer("a b", ' ', '"');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tok = new StrTokenizer("", ' ', '"');
        assertFalse(tok.hasNext());
    }

    @Test
    public void testConstructor_String_char_char_7_oe() {
        StrTokenizer tok = new StrTokenizer("a b", ' ', '"');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tok = new StrTokenizer("", ' ', '"');
        // removed other assertion

        tok = new StrTokenizer((String) null, ' ', '"');
        assertFalse(tok.hasNext());
    }

    @Test
    public void testConstructor_charArray_1_oe() {
        StrTokenizer tok = new StrTokenizer("a b".toCharArray());
        assertEquals("a", tok.next());
    }

    @Test
    public void testConstructor_charArray_2_oe() {
        StrTokenizer tok = new StrTokenizer("a b".toCharArray());
        // removed other assertion
        assertEquals("b", tok.next());
    }

    @Test
    public void testConstructor_charArray_3_oe() {
        StrTokenizer tok = new StrTokenizer("a b".toCharArray());
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testConstructor_charArray_4_oe() {
        StrTokenizer tok = new StrTokenizer("a b".toCharArray());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tok = new StrTokenizer(new char[0]);
        assertFalse(tok.hasNext());
    }

    @Test
    public void testConstructor_charArray_5_oe() {
        StrTokenizer tok = new StrTokenizer("a b".toCharArray());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tok = new StrTokenizer(new char[0]);
        // removed other assertion

        tok = new StrTokenizer((char[]) null);
        assertFalse(tok.hasNext());
    }

    @Test
    public void testConstructor_charArray_char_1_oe() {
        StrTokenizer tok = new StrTokenizer("a b".toCharArray(), ' ');
        assertEquals(1, tok.getDelimiterMatcher().isMatch(" ".toCharArray(), 0, 0, 1));
    }

    @Test
    public void testConstructor_charArray_char_2_oe() {
        StrTokenizer tok = new StrTokenizer("a b".toCharArray(), ' ');
        // removed other assertion
        assertEquals("a", tok.next());
    }

    @Test
    public void testConstructor_charArray_char_3_oe() {
        StrTokenizer tok = new StrTokenizer("a b".toCharArray(), ' ');
        // removed other assertion
        // removed other assertion
        assertEquals("b", tok.next());
    }

    @Test
    public void testConstructor_charArray_char_4_oe() {
        StrTokenizer tok = new StrTokenizer("a b".toCharArray(), ' ');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testConstructor_charArray_char_5_oe() {
        StrTokenizer tok = new StrTokenizer("a b".toCharArray(), ' ');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tok = new StrTokenizer(new char[0], ' ');
        assertFalse(tok.hasNext());
    }

    @Test
    public void testConstructor_charArray_char_6_oe() {
        StrTokenizer tok = new StrTokenizer("a b".toCharArray(), ' ');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tok = new StrTokenizer(new char[0], ' ');
        // removed other assertion

        tok = new StrTokenizer((char[]) null, ' ');
        assertFalse(tok.hasNext());
    }

    @Test
    public void testConstructor_charArray_char_char_1_oe() {
        StrTokenizer tok = new StrTokenizer("a b".toCharArray(), ' ', '"');
        assertEquals(1, tok.getDelimiterMatcher().isMatch(" ".toCharArray(), 0, 0, 1));
    }

    @Test
    public void testConstructor_charArray_char_char_2_oe() {
        StrTokenizer tok = new StrTokenizer("a b".toCharArray(), ' ', '"');
        // removed other assertion
        assertEquals(1, tok.getQuoteMatcher().isMatch("\"".toCharArray(), 0, 0, 1));
    }

    @Test
    public void testConstructor_charArray_char_char_3_oe() {
        StrTokenizer tok = new StrTokenizer("a b".toCharArray(), ' ', '"');
        // removed other assertion
        // removed other assertion
        assertEquals("a", tok.next());
    }

    @Test
    public void testConstructor_charArray_char_char_4_oe() {
        StrTokenizer tok = new StrTokenizer("a b".toCharArray(), ' ', '"');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("b", tok.next());
    }

    @Test
    public void testConstructor_charArray_char_char_5_oe() {
        StrTokenizer tok = new StrTokenizer("a b".toCharArray(), ' ', '"');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testConstructor_charArray_char_char_6_oe() {
        StrTokenizer tok = new StrTokenizer("a b".toCharArray(), ' ', '"');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tok = new StrTokenizer(new char[0], ' ', '"');
        assertFalse(tok.hasNext());
    }

    @Test
    public void testConstructor_charArray_char_char_7_oe() {
        StrTokenizer tok = new StrTokenizer("a b".toCharArray(), ' ', '"');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tok = new StrTokenizer(new char[0], ' ', '"');
        // removed other assertion

        tok = new StrTokenizer((char[]) null, ' ', '"');
        assertFalse(tok.hasNext());
    }

    @Test
    public void testReset_1_oe() {
        final StrTokenizer tok = new StrTokenizer("a b c");
        assertEquals("a", tok.next());
    }

    @Test
    public void testReset_2_oe() {
        final StrTokenizer tok = new StrTokenizer("a b c");
        // removed other assertion
        assertEquals("b", tok.next());
    }

    @Test
    public void testReset_3_oe() {
        final StrTokenizer tok = new StrTokenizer("a b c");
        // removed other assertion
        // removed other assertion
        assertEquals("c", tok.next());
    }

    @Test
    public void testReset_4_oe() {
        final StrTokenizer tok = new StrTokenizer("a b c");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testReset_5_oe() {
        final StrTokenizer tok = new StrTokenizer("a b c");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tok.reset();
        assertEquals("a", tok.next());
    }

    @Test
    public void testReset_6_oe() {
        final StrTokenizer tok = new StrTokenizer("a b c");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tok.reset();
        // removed other assertion
        assertEquals("b", tok.next());
    }

    @Test
    public void testReset_7_oe() {
        final StrTokenizer tok = new StrTokenizer("a b c");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tok.reset();
        // removed other assertion
        // removed other assertion
        assertEquals("c", tok.next());
    }

    @Test
    public void testReset_8_oe() {
        final StrTokenizer tok = new StrTokenizer("a b c");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tok.reset();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testReset_String_1_oe() {
        final StrTokenizer tok = new StrTokenizer("x x x");
        tok.reset("d e");
        assertEquals("d", tok.next());
    }

    @Test
    public void testReset_String_2_oe() {
        final StrTokenizer tok = new StrTokenizer("x x x");
        tok.reset("d e");
        // removed other assertion
        assertEquals("e", tok.next());
    }

    @Test
    public void testReset_String_3_oe() {
        final StrTokenizer tok = new StrTokenizer("x x x");
        tok.reset("d e");
        // removed other assertion
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testReset_String_4_oe() {
        final StrTokenizer tok = new StrTokenizer("x x x");
        tok.reset("d e");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        tok.reset((String) null);
        assertFalse(tok.hasNext());
    }

    @Test
    public void testReset_charArray_1_oe() {
        final StrTokenizer tok = new StrTokenizer("x x x");

        final char[] array = new char[] {'a', 'b', 'c'};
        tok.reset(array);
        assertEquals("abc", tok.next());
    }

    @Test
    public void testReset_charArray_2_oe() {
        final StrTokenizer tok = new StrTokenizer("x x x");

        final char[] array = new char[] {'a', 'b', 'c'};
        tok.reset(array);
        // removed other assertion
        assertFalse(tok.hasNext());
    }

    @Test
    public void testReset_charArray_3_oe() {
        final StrTokenizer tok = new StrTokenizer("x x x");

        final char[] array = new char[] {'a', 'b', 'c'};
        tok.reset(array);
        // removed other assertion
        // removed other assertion

        tok.reset((char[]) null);
        assertFalse(tok.hasNext());
    }

    @Test
    public void testIteration_1_oe() {
        final StrTokenizer tkn = new StrTokenizer("a b c");
        assertFalse(tkn.hasPrevious());
    }

    @Test
    public void testIteration_2_oe() {
        final StrTokenizer tkn = new StrTokenizer("a b c");
        // removed other assertion
        assertThrows(NoSuchElementException.class, tkn::previous);
    }

    @Test
    public void testIteration_3_oe() {
        final StrTokenizer tkn = new StrTokenizer("a b c");
        // removed other assertion
        // removed other assertion
        assertTrue(tkn.hasNext());
    }

    @Test
    public void testIteration_4_oe() {
        final StrTokenizer tkn = new StrTokenizer("a b c");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("a", tkn.next());
    }

    @Test
    public void testIteration_5_oe() {
        final StrTokenizer tkn = new StrTokenizer("a b c");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertThrows(UnsupportedOperationException.class, tkn::remove);
    }

    @Test
    public void testIteration_6_oe() {
        final StrTokenizer tkn = new StrTokenizer("a b c");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertThrows(UnsupportedOperationException.class, () -> tkn.set("x"));
    }

    @Test
    public void testIteration_7_oe() {
        final StrTokenizer tkn = new StrTokenizer("a b c");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertThrows(UnsupportedOperationException.class, () -> tkn.add("y"));
    }

    @Test
    public void testIteration_8_oe() {
        final StrTokenizer tkn = new StrTokenizer("a b c");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(tkn.hasPrevious());
    }

    @Test
    public void testIteration_9_oe() {
        final StrTokenizer tkn = new StrTokenizer("a b c");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(tkn.hasNext());
    }

    @Test
    public void testIteration_10_oe() {
        final StrTokenizer tkn = new StrTokenizer("a b c");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("b", tkn.next());
    }

    @Test
    public void testIteration_11_oe() {
        final StrTokenizer tkn = new StrTokenizer("a b c");
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
        assertTrue(tkn.hasPrevious());
    }

    @Test
    public void testIteration_12_oe() {
        final StrTokenizer tkn = new StrTokenizer("a b c");
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
        assertTrue(tkn.hasNext());
    }

    @Test
    public void testIteration_13_oe() {
        final StrTokenizer tkn = new StrTokenizer("a b c");
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

        assertEquals("c", tkn.next());
    }

    @Test
    public void testIteration_14_oe() {
        final StrTokenizer tkn = new StrTokenizer("a b c");
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
        assertTrue(tkn.hasPrevious());
    }

    @Test
    public void testIteration_15_oe() {
        final StrTokenizer tkn = new StrTokenizer("a b c");
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
        assertFalse(tkn.hasNext());
    }

    @Test
    public void testIteration_16_oe() {
        final StrTokenizer tkn = new StrTokenizer("a b c");
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

        assertThrows(NoSuchElementException.class, tkn::next);
    }

    @Test
    public void testIteration_17_oe() {
        final StrTokenizer tkn = new StrTokenizer("a b c");
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

        // removed other assertion
        assertTrue(tkn.hasPrevious());
    }

    @Test
    public void testIteration_18_oe() {
        final StrTokenizer tkn = new StrTokenizer("a b c");
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

        // removed other assertion
        // removed other assertion
        assertFalse(tkn.hasNext());
    }

    @Test
    public void testTokenizeSubclassInputChange_1_oe() {
        final StrTokenizer tkn = new StrTokenizer("a b c d e") {
            @Override
            protected List<String> tokenize(final char[] chars, final int offset, final int count) {
                return super.tokenize("w x y z".toCharArray(), 2, 5);
            }
        };
        assertEquals("x", tkn.next());
    }

    @Test
    public void testTokenizeSubclassInputChange_2_oe() {
        final StrTokenizer tkn = new StrTokenizer("a b c d e") {
            @Override
            protected List<String> tokenize(final char[] chars, final int offset, final int count) {
                return super.tokenize("w x y z".toCharArray(), 2, 5);
            }
        };
        // removed other assertion
        assertEquals("y", tkn.next());
    }

    @Test
    public void testTokenizeSubclassOutputChange_1_oe() {
        final StrTokenizer tkn = new StrTokenizer("a b c") {
            @Override
            protected List<String> tokenize(final char[] chars, final int offset, final int count) {
                final List<String> list = super.tokenize(chars, offset, count);
                Collections.reverse(list);
                return list;
            }
        };
        assertEquals("c", tkn.next());
    }

    @Test
    public void testTokenizeSubclassOutputChange_2_oe() {
        final StrTokenizer tkn = new StrTokenizer("a b c") {
            @Override
            protected List<String> tokenize(final char[] chars, final int offset, final int count) {
                final List<String> list = super.tokenize(chars, offset, count);
                Collections.reverse(list);
                return list;
            }
        };
        // removed other assertion
        assertEquals("b", tkn.next());
    }

    @Test
    public void testTokenizeSubclassOutputChange_3_oe() {
        final StrTokenizer tkn = new StrTokenizer("a b c") {
            @Override
            protected List<String> tokenize(final char[] chars, final int offset, final int count) {
                final List<String> list = super.tokenize(chars, offset, count);
                Collections.reverse(list);
                return list;
            }
        };
        // removed other assertion
        // removed other assertion
        assertEquals("a", tkn.next());
    }

    @Test
    public void testToString_1_oe() {
        final StrTokenizer tkn = new StrTokenizer("a b c d e");
        assertEquals("StrTokenizer[not tokenized yet]", tkn.toString());
    }

    @Test
    public void testToString_2_oe() {
        final StrTokenizer tkn = new StrTokenizer("a b c d e");
        // removed other assertion
        tkn.next();
        assertEquals("StrTokenizer[a, b, c, d, e]", tkn.toString());
    }

}
